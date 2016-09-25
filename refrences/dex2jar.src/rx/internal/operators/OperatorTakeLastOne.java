package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

public class OperatorTakeLastOne<T>
  implements Observable.Operator<T, T>
{
  public static <T> OperatorTakeLastOne<T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    ParentSubscriber localParentSubscriber = new ParentSubscriber(paramSubscriber);
    paramSubscriber.setProducer(new Producer(localParentSubscriber)
    {
      public void request(long paramLong)
      {
        this.val$parent.requestMore(paramLong);
      }
    });
    paramSubscriber.add(localParentSubscriber);
    return localParentSubscriber;
  }

  private static class ParentSubscriber<T> extends Subscriber<T>
  {
    private static final Object ABSENT = new Object();
    private static final int NOT_REQUESTED_COMPLETED = 1;
    private static final int NOT_REQUESTED_NOT_COMPLETED = 0;
    private static final int REQUESTED_COMPLETED = 3;
    private static final int REQUESTED_NOT_COMPLETED = 2;
    private final Subscriber<? super T> child;
    private T last = ABSENT;
    private final AtomicInteger state = new AtomicInteger(0);

    ParentSubscriber(Subscriber<? super T> paramSubscriber)
    {
      this.child = paramSubscriber;
    }

    private void emit()
    {
      if (isUnsubscribed())
        this.last = null;
      while (true)
      {
        return;
        Object localObject = this.last;
        this.last = null;
        if (localObject != ABSENT);
        try
        {
          this.child.onNext(localObject);
          if (isUnsubscribed())
            continue;
          this.child.onCompleted();
        }
        catch (Throwable localThrowable)
        {
          Exceptions.throwOrReport(localThrowable, this.child);
        }
      }
    }

    public void onCompleted()
    {
      if (this.last == ABSENT)
        this.child.onCompleted();
      while (true)
      {
        return;
        do
        {
          int i;
          while (true)
          {
            i = this.state.get();
            if (i == 0)
            {
              if (!this.state.compareAndSet(0, 1))
                continue;
              break;
            }
          }
          if (i != 2)
            break;
        }
        while (!this.state.compareAndSet(2, 3));
        emit();
      }
    }

    public void onError(Throwable paramThrowable)
    {
      this.child.onError(paramThrowable);
    }

    public void onNext(T paramT)
    {
      this.last = paramT;
    }

    void requestMore(long paramLong)
    {
      int i;
      if (paramLong > 0L)
        do
        {
          i = this.state.get();
          if (i != 0)
            break;
        }
        while (!this.state.compareAndSet(0, 2));
      while (true)
      {
        return;
        if (i == 1)
        {
          if (!this.state.compareAndSet(1, 3))
            break;
          emit();
          continue;
        }
      }
    }
  }

  private static class Holder
  {
    static final OperatorTakeLastOne<Object> INSTANCE = new OperatorTakeLastOne(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTakeLastOne
 * JD-Core Version:    0.6.0
 */