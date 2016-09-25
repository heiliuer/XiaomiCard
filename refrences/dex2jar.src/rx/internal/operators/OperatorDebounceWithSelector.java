package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

public final class OperatorDebounceWithSelector<T, U>
  implements Observable.Operator<T, T>
{
  final Func1<? super T, ? extends Observable<U>> selector;

  public OperatorDebounceWithSelector(Func1<? super T, ? extends Observable<U>> paramFunc1)
  {
    this.selector = paramFunc1;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    SerialSubscription localSerialSubscription = new SerialSubscription();
    paramSubscriber.add(localSerialSubscription);
    return new Subscriber(paramSubscriber, localSerializedSubscriber, localSerialSubscription)
    {
      final Subscriber<?> self = this;
      final OperatorDebounceWithTime.DebounceState<T> state = new OperatorDebounceWithTime.DebounceState();

      public void onCompleted()
      {
        this.state.emitAndComplete(this.val$s, this);
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$s.onError(paramThrowable);
        unsubscribe();
        this.state.clear();
      }

      public void onNext(T paramT)
      {
        try
        {
          Observable localObservable = (Observable)OperatorDebounceWithSelector.this.selector.call(paramT);
          1 local1 = new Subscriber(this.state.next(paramT))
          {
            public void onCompleted()
            {
              OperatorDebounceWithSelector.1.this.state.emit(this.val$index, OperatorDebounceWithSelector.1.this.val$s, OperatorDebounceWithSelector.1.this.self);
              unsubscribe();
            }

            public void onError(Throwable paramThrowable)
            {
              OperatorDebounceWithSelector.1.this.self.onError(paramThrowable);
            }

            public void onNext(U paramU)
            {
              onCompleted();
            }
          };
          this.val$ssub.set(local1);
          localObservable.unsafeSubscribe(local1);
          return;
        }
        catch (Throwable localThrowable)
        {
          while (true)
            Exceptions.throwOrReport(localThrowable, this);
        }
      }

      public void onStart()
      {
        request(9223372036854775807L);
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorDebounceWithSelector
 * JD-Core Version:    0.6.0
 */