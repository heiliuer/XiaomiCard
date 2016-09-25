package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;

public final class OperatorTakeTimed<T>
  implements Observable.Operator<T, T>
{
  final Scheduler scheduler;
  final long time;
  final TimeUnit unit;

  public OperatorTakeTimed(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.time = paramLong;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    TakeSubscriber localTakeSubscriber = new TakeSubscriber(new SerializedSubscriber(paramSubscriber));
    localWorker.schedule(localTakeSubscriber, this.time, this.unit);
    return localTakeSubscriber;
  }

  static final class TakeSubscriber<T> extends Subscriber<T>
    implements Action0
  {
    final Subscriber<? super T> child;

    public TakeSubscriber(Subscriber<? super T> paramSubscriber)
    {
      super();
      this.child = paramSubscriber;
    }

    public void call()
    {
      onCompleted();
    }

    public void onCompleted()
    {
      this.child.onCompleted();
      unsubscribe();
    }

    public void onError(Throwable paramThrowable)
    {
      this.child.onError(paramThrowable);
      unsubscribe();
    }

    public void onNext(T paramT)
    {
      this.child.onNext(paramT);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTakeTimed
 * JD-Core Version:    0.6.0
 */