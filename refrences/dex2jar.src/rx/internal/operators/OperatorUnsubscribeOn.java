package rx.internal.operators;

import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public class OperatorUnsubscribeOn<T>
  implements Observable.Operator<T, T>
{
  private final Scheduler scheduler;

  public OperatorUnsubscribeOn(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    1 local1 = new Subscriber(paramSubscriber)
    {
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
        this.val$subscriber.onNext(paramT);
      }
    };
    paramSubscriber.add(Subscriptions.create(new Action0(local1)
    {
      public void call()
      {
        Scheduler.Worker localWorker = OperatorUnsubscribeOn.this.scheduler.createWorker();
        localWorker.schedule(new Action0(localWorker)
        {
          public void call()
          {
            OperatorUnsubscribeOn.2.this.val$parent.unsubscribe();
            this.val$inner.unsubscribe();
          }
        });
      }
    }));
    return local1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorUnsubscribeOn
 * JD-Core Version:    0.6.0
 */