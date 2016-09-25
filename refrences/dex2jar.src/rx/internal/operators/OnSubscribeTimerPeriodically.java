package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.OnSubscribe;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;

public final class OnSubscribeTimerPeriodically
  implements Observable.OnSubscribe<Long>
{
  final long initialDelay;
  final long period;
  final Scheduler scheduler;
  final TimeUnit unit;

  public OnSubscribeTimerPeriodically(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.initialDelay = paramLong1;
    this.period = paramLong2;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }

  public void call(Subscriber<? super Long> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    localWorker.schedulePeriodically(new Action0(paramSubscriber, localWorker)
    {
      long counter;

      public void call()
      {
        try
        {
          Subscriber localSubscriber = this.val$child;
          long l = this.counter;
          this.counter = (1L + l);
          localSubscriber.onNext(Long.valueOf(l));
          return;
        }
        catch (Throwable localThrowable)
        {
          try
          {
            this.val$worker.unsubscribe();
            Exceptions.throwOrReport(localThrowable, this.val$child);
          }
          finally
          {
            Exceptions.throwOrReport(localThrowable, this.val$child);
          }
        }
      }
    }
    , this.initialDelay, this.period, this.unit);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OnSubscribeTimerPeriodically
 * JD-Core Version:    0.6.0
 */