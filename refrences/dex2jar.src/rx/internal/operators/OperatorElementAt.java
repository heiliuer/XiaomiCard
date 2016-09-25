package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;

public final class OperatorElementAt<T>
  implements Observable.Operator<T, T>
{
  private final T defaultValue;
  private final boolean hasDefault;
  private final int index;

  public OperatorElementAt(int paramInt)
  {
    this(paramInt, null, false);
  }

  public OperatorElementAt(int paramInt, T paramT)
  {
    this(paramInt, paramT, true);
  }

  private OperatorElementAt(int paramInt, T paramT, boolean paramBoolean)
  {
    if (paramInt < 0)
      throw new IndexOutOfBoundsException(paramInt + " is out of bounds");
    this.index = paramInt;
    this.defaultValue = paramT;
    this.hasDefault = paramBoolean;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    1 local1 = new Subscriber(paramSubscriber)
    {
      private int currentIndex = 0;

      public void onCompleted()
      {
        if (this.currentIndex <= OperatorElementAt.this.index)
        {
          if (!OperatorElementAt.this.hasDefault)
            break label46;
          this.val$child.onNext(OperatorElementAt.this.defaultValue);
          this.val$child.onCompleted();
        }
        while (true)
        {
          return;
          label46: this.val$child.onError(new IndexOutOfBoundsException(OperatorElementAt.this.index + " is out of bounds"));
        }
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$child.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        int i = this.currentIndex;
        this.currentIndex = (i + 1);
        if (i == OperatorElementAt.this.index)
        {
          this.val$child.onNext(paramT);
          this.val$child.onCompleted();
          unsubscribe();
        }
      }

      public void setProducer(Producer paramProducer)
      {
        this.val$child.setProducer(new OperatorElementAt.InnerProducer(paramProducer));
      }
    };
    paramSubscriber.add(local1);
    return local1;
  }

  static class InnerProducer extends AtomicBoolean
    implements Producer
  {
    private static final long serialVersionUID = 1L;
    final Producer actual;

    public InnerProducer(Producer paramProducer)
    {
      this.actual = paramProducer;
    }

    public void request(long paramLong)
    {
      if (paramLong < 0L)
        throw new IllegalArgumentException("n >= 0 required");
      if ((paramLong > 0L) && (compareAndSet(false, true)))
        this.actual.request(9223372036854775807L);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorElementAt
 * JD-Core Version:    0.6.0
 */