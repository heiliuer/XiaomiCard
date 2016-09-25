package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;

public final class OperatorTimeout<T> extends OperatorTimeoutBase<T>
{
  public OperatorTimeout(long paramLong, TimeUnit paramTimeUnit, Observable<? extends T> paramObservable, Scheduler paramScheduler)
  {
    super(new OperatorTimeoutBase.FirstTimeoutStub(paramTimeUnit)
    {
      public Subscription call(OperatorTimeoutBase.TimeoutSubscriber<T> paramTimeoutSubscriber, Long paramLong, Scheduler.Worker paramWorker)
      {
        return paramWorker.schedule(new Action0(paramTimeoutSubscriber, paramLong)
        {
          public void call()
          {
            this.val$timeoutSubscriber.onTimeout(this.val$seqId.longValue());
          }
        }
        , this.val$timeout, this.val$timeUnit);
      }
    }
    , new OperatorTimeoutBase.TimeoutStub(paramTimeUnit)
    {
      public Subscription call(OperatorTimeoutBase.TimeoutSubscriber<T> paramTimeoutSubscriber, Long paramLong, T paramT, Scheduler.Worker paramWorker)
      {
        return paramWorker.schedule(new Action0(paramTimeoutSubscriber, paramLong)
        {
          public void call()
          {
            this.val$timeoutSubscriber.onTimeout(this.val$seqId.longValue());
          }
        }
        , this.val$timeout, this.val$timeUnit);
      }
    }
    , paramObservable, paramScheduler);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTimeout
 * JD-Core Version:    0.6.0
 */