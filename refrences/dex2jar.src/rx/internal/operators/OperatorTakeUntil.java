package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

public final class OperatorTakeUntil<T, E>
  implements Observable.Operator<T, T>
{
  private final Observable<? extends E> other;

  public OperatorTakeUntil(Observable<? extends E> paramObservable)
  {
    this.other = paramObservable;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber, false);
    1 local1 = new Subscriber(localSerializedSubscriber, false, localSerializedSubscriber)
    {
      public void onCompleted()
      {
        try
        {
          this.val$serial.onCompleted();
          return;
        }
        finally
        {
          this.val$serial.unsubscribe();
        }
        throw localObject;
      }

      public void onError(Throwable paramThrowable)
      {
        try
        {
          this.val$serial.onError(paramThrowable);
          return;
        }
        finally
        {
          this.val$serial.unsubscribe();
        }
        throw localObject;
      }

      public void onNext(T paramT)
      {
        this.val$serial.onNext(paramT);
      }
    };
    2 local2 = new Subscriber(local1)
    {
      public void onCompleted()
      {
        this.val$main.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$main.onError(paramThrowable);
      }

      public void onNext(E paramE)
      {
        onCompleted();
      }

      public void onStart()
      {
        request(9223372036854775807L);
      }
    };
    localSerializedSubscriber.add(local1);
    localSerializedSubscriber.add(local2);
    paramSubscriber.add(localSerializedSubscriber);
    this.other.unsafeSubscribe(local2);
    return local1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTakeUntil
 * JD-Core Version:    0.6.0
 */