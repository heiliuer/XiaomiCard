package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;

public class OperatorCast<T, R>
  implements Observable.Operator<R, T>
{
  private final Class<R> castClass;

  public OperatorCast(Class<R> paramClass)
  {
    this.castClass = paramClass;
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
          this.val$o.onNext(OperatorCast.this.castClass.cast(paramT));
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
 * Qualified Name:     rx.internal.operators.OperatorCast
 * JD-Core Version:    0.6.0
 */