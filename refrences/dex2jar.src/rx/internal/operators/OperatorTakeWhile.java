package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;

public final class OperatorTakeWhile<T>
  implements Observable.Operator<T, T>
{
  private final Func2<? super T, ? super Integer, Boolean> predicate;

  public OperatorTakeWhile(Func1<? super T, Boolean> paramFunc1)
  {
    this(new Func2()
    {
      public Boolean call(T paramT, Integer paramInteger)
      {
        return (Boolean)OperatorTakeWhile.this.call(paramT);
      }
    });
  }

  public OperatorTakeWhile(Func2<? super T, ? super Integer, Boolean> paramFunc2)
  {
    this.predicate = paramFunc2;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    2 local2 = new Subscriber(paramSubscriber, false, paramSubscriber)
    {
      private int counter = 0;
      private boolean done = false;

      public void onCompleted()
      {
        if (!this.done)
          this.val$subscriber.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        if (!this.done)
          this.val$subscriber.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        try
        {
          Func2 localFunc2 = OperatorTakeWhile.this.predicate;
          int i = this.counter;
          this.counter = (i + 1);
          boolean bool = ((Boolean)localFunc2.call(paramT, Integer.valueOf(i))).booleanValue();
          if (bool)
          {
            this.val$subscriber.onNext(paramT);
            return;
          }
        }
        catch (Throwable localThrowable)
        {
          while (true)
          {
            this.done = true;
            Exceptions.throwOrReport(localThrowable, this.val$subscriber, paramT);
            unsubscribe();
            continue;
            this.done = true;
            this.val$subscriber.onCompleted();
            unsubscribe();
          }
        }
      }
    };
    paramSubscriber.add(local2);
    return local2;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTakeWhile
 * JD-Core Version:    0.6.0
 */