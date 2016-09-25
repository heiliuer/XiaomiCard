package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;

public class OperatorSubscribeOn<T>
  implements Observable.Operator<T, Observable<T>>
{
  private final Scheduler scheduler;

  public OperatorSubscribeOn(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super Observable<T>> call(Subscriber<? super T> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    return new Subscriber(paramSubscriber, paramSubscriber, localWorker)
    {
      public void onCompleted()
      {
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$subscriber.onError(paramThrowable);
      }

      public void onNext(Observable<T> paramObservable)
      {
        this.val$inner.schedule(new Action0(paramObservable)
        {
          public void call()
          {
            Thread localThread = Thread.currentThread();
            this.val$o.unsafeSubscribe(new Subscriber(OperatorSubscribeOn.1.this.val$subscriber, localThread)
            {
              public void onCompleted()
              {
                OperatorSubscribeOn.1.this.val$subscriber.onCompleted();
              }

              public void onError(Throwable paramThrowable)
              {
                OperatorSubscribeOn.1.this.val$subscriber.onError(paramThrowable);
              }

              public void onNext(T paramT)
              {
                OperatorSubscribeOn.1.this.val$subscriber.onNext(paramT);
              }

              public void setProducer(Producer paramProducer)
              {
                OperatorSubscribeOn.1.this.val$subscriber.setProducer(new Producer(paramProducer)
                {
                  public void request(long paramLong)
                  {
                    if (Thread.currentThread() == OperatorSubscribeOn.1.1.1.this.val$t)
                      this.val$producer.request(paramLong);
                    while (true)
                    {
                      return;
                      OperatorSubscribeOn.1.this.val$inner.schedule(new Action0(paramLong)
                      {
                        public void call()
                        {
                          OperatorSubscribeOn.1.1.1.1.this.val$producer.request(this.val$n);
                        }
                      });
                    }
                  }
                });
              }
            });
          }
        });
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSubscribeOn
 * JD-Core Version:    0.6.0
 */