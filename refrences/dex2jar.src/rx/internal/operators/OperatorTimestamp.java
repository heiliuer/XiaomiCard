package rx.internal.operators;

import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Timestamped;

public final class OperatorTimestamp<T>
  implements Observable.Operator<Timestamped<T>, T>
{
  private final Scheduler scheduler;

  public OperatorTimestamp(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super Timestamped<T>> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      public void onCompleted()
      {
        this.val$o.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$o.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        this.val$o.onNext(new Timestamped(OperatorTimestamp.this.scheduler.now(), paramT));
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTimestamp
 * JD-Core Version:    0.6.0
 */