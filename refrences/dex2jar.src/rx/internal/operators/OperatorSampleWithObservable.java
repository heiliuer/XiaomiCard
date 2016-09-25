package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

public final class OperatorSampleWithObservable<T, U>
  implements Observable.Operator<T, T>
{
  static final Object EMPTY_TOKEN = new Object();
  final Observable<U> sampler;

  public OperatorSampleWithObservable(Observable<U> paramObservable)
  {
    this.sampler = paramObservable;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    AtomicReference localAtomicReference = new AtomicReference(EMPTY_TOKEN);
    1 local1 = new Subscriber(paramSubscriber, localAtomicReference, localSerializedSubscriber)
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

      public void onNext(U paramU)
      {
        Object localObject = this.val$value.getAndSet(OperatorSampleWithObservable.EMPTY_TOKEN);
        if (localObject != OperatorSampleWithObservable.EMPTY_TOKEN)
          this.val$s.onNext(localObject);
      }
    };
    2 local2 = new Subscriber(paramSubscriber, localAtomicReference, localSerializedSubscriber)
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
        this.val$value.set(paramT);
      }
    };
    this.sampler.unsafeSubscribe(local1);
    return local2;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSampleWithObservable
 * JD-Core Version:    0.6.0
 */