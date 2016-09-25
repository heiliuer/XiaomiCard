package rx.android.lifecycle;

import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

final class OperatorSubscribeUntil<T, R>
  implements Observable.Operator<T, T>
{
  private final Observable<? extends R> other;

  public OperatorSubscribeUntil(Observable<? extends R> paramObservable)
  {
    this.other = paramObservable;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    this.other.unsafeSubscribe(new Subscriber(paramSubscriber, localSerializedSubscriber)
    {
      public void onCompleted()
      {
        this.val$parent.unsubscribe();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$parent.onError(paramThrowable);
      }

      public void onNext(R paramR)
      {
        this.val$parent.unsubscribe();
      }
    });
    return localSerializedSubscriber;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.lifecycle.OperatorSubscribeUntil
 * JD-Core Version:    0.6.0
 */