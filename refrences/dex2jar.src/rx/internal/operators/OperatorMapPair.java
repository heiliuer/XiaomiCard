package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;

public final class OperatorMapPair<T, U, R>
  implements Observable.Operator<Observable<? extends R>, T>
{
  final Func1<? super T, ? extends Observable<? extends U>> collectionSelector;
  final Func2<? super T, ? super U, ? extends R> resultSelector;

  public OperatorMapPair(Func1<? super T, ? extends Observable<? extends U>> paramFunc1, Func2<? super T, ? super U, ? extends R> paramFunc2)
  {
    this.collectionSelector = paramFunc1;
    this.resultSelector = paramFunc2;
  }

  public static <T, U> Func1<T, Observable<U>> convertSelector(Func1<? super T, ? extends Iterable<? extends U>> paramFunc1)
  {
    return new Func1(paramFunc1)
    {
      public Observable<U> call(T paramT)
      {
        return Observable.from((Iterable)OperatorMapPair.this.call(paramT));
      }
    };
  }

  public Subscriber<? super T> call(Subscriber<? super Observable<? extends R>> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      public void onCompleted()
      {
        this.val$o.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$o.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        try
        {
          this.val$o.onNext(((Observable)OperatorMapPair.this.collectionSelector.call(paramT)).map(new Func1(paramT)
          {
            public R call(U paramU)
            {
              return OperatorMapPair.this.resultSelector.call(this.val$outer, paramU);
            }
          }));
          return;
        }
        catch (Throwable localThrowable)
        {
          while (true)
            Exceptions.throwOrReport(localThrowable, this.val$o, paramT);
        }
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorMapPair
 * JD-Core Version:    0.6.0
 */