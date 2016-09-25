package rx.internal.operators;

import rx.Observable;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class OperatorTimeoutWithSelector<T, U, V> extends OperatorTimeoutBase<T>
{
  public OperatorTimeoutWithSelector(Func0<? extends Observable<U>> paramFunc0, Func1<? super T, ? extends Observable<V>> paramFunc1, Observable<? extends T> paramObservable)
  {
    super(new OperatorTimeoutBase.FirstTimeoutStub()
    {
      public Subscription call(OperatorTimeoutBase.TimeoutSubscriber<T> paramTimeoutSubscriber, Long paramLong, Scheduler.Worker paramWorker)
      {
        if (OperatorTimeoutWithSelector.this != null);
        while (true)
        {
          try
          {
            Observable localObservable = (Observable)OperatorTimeoutWithSelector.this.call();
            localSubscription = localObservable.unsafeSubscribe(new Subscriber(paramTimeoutSubscriber, paramLong)
            {
              public void onCompleted()
              {
                this.val$timeoutSubscriber.onTimeout(this.val$seqId.longValue());
              }

              public void onError(Throwable paramThrowable)
              {
                this.val$timeoutSubscriber.onError(paramThrowable);
              }

              public void onNext(U paramU)
              {
                this.val$timeoutSubscriber.onTimeout(this.val$seqId.longValue());
              }
            });
            return localSubscription;
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwOrReport(localThrowable, paramTimeoutSubscriber);
            localSubscription = Subscriptions.unsubscribed();
            continue;
          }
          Subscription localSubscription = Subscriptions.unsubscribed();
        }
      }
    }
    , new OperatorTimeoutBase.TimeoutStub()
    {
      public Subscription call(OperatorTimeoutBase.TimeoutSubscriber<T> paramTimeoutSubscriber, Long paramLong, T paramT, Scheduler.Worker paramWorker)
      {
        try
        {
          Observable localObservable = (Observable)OperatorTimeoutWithSelector.this.call(paramT);
          localSubscription = localObservable.unsafeSubscribe(new Subscriber(paramTimeoutSubscriber, paramLong)
          {
            public void onCompleted()
            {
              this.val$timeoutSubscriber.onTimeout(this.val$seqId.longValue());
            }

            public void onError(Throwable paramThrowable)
            {
              this.val$timeoutSubscriber.onError(paramThrowable);
            }

            public void onNext(V paramV)
            {
              this.val$timeoutSubscriber.onTimeout(this.val$seqId.longValue());
            }
          });
          return localSubscription;
        }
        catch (Throwable localThrowable)
        {
          while (true)
          {
            Exceptions.throwOrReport(localThrowable, paramTimeoutSubscriber);
            Subscription localSubscription = Subscriptions.unsubscribed();
          }
        }
      }
    }
    , paramObservable, Schedulers.immediate());
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTimeoutWithSelector
 * JD-Core Version:    0.6.0
 */