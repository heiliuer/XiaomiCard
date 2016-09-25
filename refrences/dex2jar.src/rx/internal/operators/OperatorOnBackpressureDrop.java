package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action1;

public class OperatorOnBackpressureDrop<T>
  implements Observable.Operator<T, T>
{
  private final Action1<? super T> onDrop;

  private OperatorOnBackpressureDrop()
  {
    this(null);
  }

  public OperatorOnBackpressureDrop(Action1<? super T> paramAction1)
  {
    this.onDrop = paramAction1;
  }

  public static <T> OperatorOnBackpressureDrop<T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    AtomicLong localAtomicLong = new AtomicLong();
    paramSubscriber.setProducer(new Producer(localAtomicLong)
    {
      public void request(long paramLong)
      {
        BackpressureUtils.getAndAddRequest(this.val$requested, paramLong);
      }
    });
    return new Subscriber(paramSubscriber, paramSubscriber, localAtomicLong)
    {
      public void onCompleted()
      {
        this.val$child.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$child.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        if (this.val$requested.get() > 0L)
        {
          this.val$child.onNext(paramT);
          this.val$requested.decrementAndGet();
        }
        while (true)
        {
          return;
          if (OperatorOnBackpressureDrop.this.onDrop != null)
          {
            OperatorOnBackpressureDrop.this.onDrop.call(paramT);
            continue;
          }
        }
      }

      public void onStart()
      {
        request(9223372036854775807L);
      }
    };
  }

  private static final class Holder
  {
    static final OperatorOnBackpressureDrop<Object> INSTANCE = new OperatorOnBackpressureDrop(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorOnBackpressureDrop
 * JD-Core Version:    0.6.0
 */