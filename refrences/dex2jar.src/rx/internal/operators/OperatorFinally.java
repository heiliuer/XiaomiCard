package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Action0;

public final class OperatorFinally<T>
  implements Observable.Operator<T, T>
{
  final Action0 action;

  public OperatorFinally(Action0 paramAction0)
  {
    if (paramAction0 == null)
      throw new NullPointerException("Action can not be null");
    this.action = paramAction0;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      public void onCompleted()
      {
        try
        {
          this.val$child.onCompleted();
          return;
        }
        finally
        {
          OperatorFinally.this.action.call();
        }
        throw localObject;
      }

      public void onError(Throwable paramThrowable)
      {
        try
        {
          this.val$child.onError(paramThrowable);
          return;
        }
        finally
        {
          OperatorFinally.this.action.call();
        }
        throw localObject;
      }

      public void onNext(T paramT)
      {
        this.val$child.onNext(paramT);
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorFinally
 * JD-Core Version:    0.6.0
 */