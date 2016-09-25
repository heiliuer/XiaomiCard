package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func2;
import rx.observers.SerializedSubscriber;

public final class OperatorWithLatestFrom<T, U, R>
  implements Observable.Operator<R, T>
{
  static final Object EMPTY = new Object();
  final Observable<? extends U> other;
  final Func2<? super T, ? super U, ? extends R> resultSelector;

  public OperatorWithLatestFrom(Observable<? extends U> paramObservable, Func2<? super T, ? super U, ? extends R> paramFunc2)
  {
    this.other = paramObservable;
    this.resultSelector = paramFunc2;
  }

  public Subscriber<? super T> call(Subscriber<? super R> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber, false);
    paramSubscriber.add(localSerializedSubscriber);
    AtomicReference localAtomicReference = new AtomicReference(EMPTY);
    1 local1 = new Subscriber(localSerializedSubscriber, true, localAtomicReference, localSerializedSubscriber)
    {
      public void onCompleted()
      {
        this.val$s.onCompleted();
        this.val$s.unsubscribe();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$s.onError(paramThrowable);
        this.val$s.unsubscribe();
      }

      public void onNext(T paramT)
      {
        Object localObject1 = this.val$current.get();
        if (localObject1 != OperatorWithLatestFrom.EMPTY);
        try
        {
          Object localObject2 = OperatorWithLatestFrom.this.resultSelector.call(paramT, localObject1);
          this.val$s.onNext(localObject2);
          return;
        }
        catch (Throwable localThrowable)
        {
          while (true)
            Exceptions.throwOrReport(localThrowable, this);
        }
      }
    };
    2 local2 = new Subscriber(localAtomicReference, localSerializedSubscriber)
    {
      public void onCompleted()
      {
        if (this.val$current.get() == OperatorWithLatestFrom.EMPTY)
        {
          this.val$s.onCompleted();
          this.val$s.unsubscribe();
        }
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$s.onError(paramThrowable);
        this.val$s.unsubscribe();
      }

      public void onNext(U paramU)
      {
        this.val$current.set(paramU);
      }
    };
    localSerializedSubscriber.add(local1);
    localSerializedSubscriber.add(local2);
    this.other.unsafeSubscribe(local2);
    return local1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorWithLatestFrom
 * JD-Core Version:    0.6.0
 */