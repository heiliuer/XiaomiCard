package rx.internal.operators;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.observers.Subscribers;

public final class OnSubscribeDelaySubscriptionWithSelector<T, U>
  implements Observable.OnSubscribe<T>
{
  final Observable<? extends T> source;
  final Func0<? extends Observable<U>> subscriptionDelay;

  public OnSubscribeDelaySubscriptionWithSelector(Observable<? extends T> paramObservable, Func0<? extends Observable<U>> paramFunc0)
  {
    this.source = paramObservable;
    this.subscriptionDelay = paramFunc0;
  }

  public void call(Subscriber<? super T> paramSubscriber)
  {
    try
    {
      ((Observable)this.subscriptionDelay.call()).take(1).unsafeSubscribe(new Subscriber(paramSubscriber)
      {
        public void onCompleted()
        {
          OnSubscribeDelaySubscriptionWithSelector.this.source.unsafeSubscribe(Subscribers.wrap(this.val$child));
        }

        public void onError(Throwable paramThrowable)
        {
          this.val$child.onError(paramThrowable);
        }

        public void onNext(U paramU)
        {
        }
      });
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Exceptions.throwOrReport(localThrowable, paramSubscriber);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OnSubscribeDelaySubscriptionWithSelector
 * JD-Core Version:    0.6.0
 */