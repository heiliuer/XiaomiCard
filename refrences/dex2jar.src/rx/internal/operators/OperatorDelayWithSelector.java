package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.observers.Subscribers;
import rx.subjects.PublishSubject;

public final class OperatorDelayWithSelector<T, V>
  implements Observable.Operator<T, T>
{
  final Func1<? super T, ? extends Observable<V>> itemDelay;
  final Observable<? extends T> source;

  public OperatorDelayWithSelector(Observable<? extends T> paramObservable, Func1<? super T, ? extends Observable<V>> paramFunc1)
  {
    this.source = paramObservable;
    this.itemDelay = paramFunc1;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    PublishSubject localPublishSubject = PublishSubject.create();
    paramSubscriber.add(Observable.merge(localPublishSubject).unsafeSubscribe(Subscribers.from(localSerializedSubscriber)));
    return new Subscriber(paramSubscriber, localPublishSubject, localSerializedSubscriber)
    {
      public void onCompleted()
      {
        this.val$delayedEmissions.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$child.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        try
        {
          this.val$delayedEmissions.onNext(((Observable)OperatorDelayWithSelector.this.itemDelay.call(paramT)).take(1).defaultIfEmpty(null).map(new Func1(paramT)
          {
            public T call(V paramV)
            {
              return this.val$t;
            }
          }));
          return;
        }
        catch (Throwable localThrowable)
        {
          while (true)
            Exceptions.throwOrReport(localThrowable, this);
        }
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorDelayWithSelector
 * JD-Core Version:    0.6.0
 */