package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

public final class OperatorSkipUntil<T, U>
  implements Observable.Operator<T, T>
{
  final Observable<U> other;

  public OperatorSkipUntil(Observable<U> paramObservable)
  {
    this.other = paramObservable;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    AtomicBoolean localAtomicBoolean = new AtomicBoolean();
    1 local1 = new Subscriber(localAtomicBoolean, localSerializedSubscriber)
    {
      public void onCompleted()
      {
        unsubscribe();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$s.onError(paramThrowable);
        this.val$s.unsubscribe();
      }

      public void onNext(U paramU)
      {
        this.val$gate.set(true);
        unsubscribe();
      }
    };
    paramSubscriber.add(local1);
    this.other.unsafeSubscribe(local1);
    return new Subscriber(paramSubscriber, localAtomicBoolean, localSerializedSubscriber)
    {
      public void onCompleted()
      {
        this.val$s.onCompleted();
        unsubscribe();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$s.onError(paramThrowable);
        unsubscribe();
      }

      public void onNext(T paramT)
      {
        if (this.val$gate.get())
          this.val$s.onNext(paramT);
        while (true)
        {
          return;
          request(1L);
        }
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSkipUntil
 * JD-Core Version:    0.6.0
 */