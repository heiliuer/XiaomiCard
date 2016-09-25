package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;

public final class OperatorDelay<T>
  implements Observable.Operator<T, T>
{
  final long delay;
  final Scheduler scheduler;
  final TimeUnit unit;

  public OperatorDelay(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.delay = paramLong;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    return new Subscriber(paramSubscriber, localWorker, paramSubscriber)
    {
      boolean done;

      public void onCompleted()
      {
        this.val$worker.schedule(new Action0()
        {
          public void call()
          {
            if (!OperatorDelay.1.this.done)
            {
              OperatorDelay.1.this.done = true;
              OperatorDelay.1.this.val$child.onCompleted();
            }
          }
        }
        , OperatorDelay.this.delay, OperatorDelay.this.unit);
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$worker.schedule(new Action0(paramThrowable)
        {
          public void call()
          {
            if (!OperatorDelay.1.this.done)
            {
              OperatorDelay.1.this.done = true;
              OperatorDelay.1.this.val$child.onError(this.val$e);
              OperatorDelay.1.this.val$worker.unsubscribe();
            }
          }
        });
      }

      public void onNext(T paramT)
      {
        this.val$worker.schedule(new Action0(paramT)
        {
          public void call()
          {
            if (!OperatorDelay.1.this.done)
              OperatorDelay.1.this.val$child.onNext(this.val$t);
          }
        }
        , OperatorDelay.this.delay, OperatorDelay.this.unit);
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorDelay
 * JD-Core Version:    0.6.0
 */