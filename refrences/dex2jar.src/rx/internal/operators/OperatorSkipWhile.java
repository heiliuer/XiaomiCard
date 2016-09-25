package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

public final class OperatorSkipWhile<T>
  implements Observable.Operator<T, T>
{
  private final Func2<? super T, Integer, Boolean> predicate;

  public OperatorSkipWhile(Func2<? super T, Integer, Boolean> paramFunc2)
  {
    this.predicate = paramFunc2;
  }

  public static <T> Func2<T, Integer, Boolean> toPredicate2(Func1<? super T, Boolean> paramFunc1)
  {
    return new Func2(paramFunc1)
    {
      public Boolean call(T paramT, Integer paramInteger)
      {
        return (Boolean)OperatorSkipWhile.this.call(paramT);
      }
    };
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      int index;
      boolean skipping = true;

      public void onCompleted()
      {
        this.val$child.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$child.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        if (!this.skipping)
          this.val$child.onNext(paramT);
        while (true)
        {
          return;
          Func2 localFunc2 = OperatorSkipWhile.this.predicate;
          int i = this.index;
          this.index = (i + 1);
          if (!((Boolean)localFunc2.call(paramT, Integer.valueOf(i))).booleanValue())
          {
            this.skipping = false;
            this.val$child.onNext(paramT);
            continue;
          }
          request(1L);
        }
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSkipWhile
 * JD-Core Version:    0.6.0
 */