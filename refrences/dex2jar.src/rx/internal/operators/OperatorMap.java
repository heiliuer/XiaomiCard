package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

public final class OperatorMap<T, R>
  implements Observable.Operator<R, T>
{
  private final Func1<? super T, ? extends R> transformer;

  public OperatorMap(Func1<? super T, ? extends R> paramFunc1)
  {
    this.transformer = paramFunc1;
  }

  public Subscriber<? super T> call(Subscriber<? super R> paramSubscriber)
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
          this.val$o.onNext(OperatorMap.this.transformer.call(paramT));
          return;
        }
        catch (Throwable localThrowable)
        {
          while (true)
            Exceptions.throwOrReport(localThrowable, this, paramT);
        }
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorMap
 * JD-Core Version:    0.6.0
 */