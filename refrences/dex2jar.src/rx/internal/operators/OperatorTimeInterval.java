package rx.internal.operators;

import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.TimeInterval;

public final class OperatorTimeInterval<T>
  implements Observable.Operator<TimeInterval<T>, T>
{
  private final Scheduler scheduler;

  public OperatorTimeInterval(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super TimeInterval<T>> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      private long lastTimestamp = OperatorTimeInterval.this.scheduler.now();

      public void onCompleted()
      {
        this.val$subscriber.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$subscriber.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        long l = OperatorTimeInterval.this.scheduler.now();
        this.val$subscriber.onNext(new TimeInterval(l - this.lastTimestamp, paramT));
        this.lastTimestamp = l;
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTimeInterval
 * JD-Core Version:    0.6.0
 */