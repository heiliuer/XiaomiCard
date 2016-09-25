package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;

public final class OperatorSkipTimed<T>
  implements Observable.Operator<T, T>
{
  final Scheduler scheduler;
  final long time;
  final TimeUnit unit;

  public OperatorSkipTimed(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.time = paramLong;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    AtomicBoolean localAtomicBoolean = new AtomicBoolean();
    localWorker.schedule(new Action0(localAtomicBoolean)
    {
      public void call()
      {
        this.val$gate.set(true);
      }
    }
    , this.time, this.unit);
    return new Subscriber(paramSubscriber, localAtomicBoolean, paramSubscriber)
    {
      public void onCompleted()
      {
        try
        {
          this.val$child.onCompleted();
          return;
        }
        finally
        {
          unsubscribe();
        }
        throw localObject;
      }

      public void onError(Throwable paramThrowable)
      {
        try
        {
          this.val$child.onError(paramThrowable);
          return;
        }
        finally
        {
          unsubscribe();
        }
        throw localObject;
      }

      public void onNext(T paramT)
      {
        if (this.val$gate.get())
          this.val$child.onNext(paramT);
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSkipTimed
 * JD-Core Version:    0.6.0
 */