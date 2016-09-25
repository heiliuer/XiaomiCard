package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;

public final class OperatorDistinctUntilChanged<T, U>
  implements Observable.Operator<T, T>
{
  final Func1<? super T, ? extends U> keySelector;

  public OperatorDistinctUntilChanged(Func1<? super T, ? extends U> paramFunc1)
  {
    this.keySelector = paramFunc1;
  }

  public static <T> OperatorDistinctUntilChanged<T, T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      boolean hasPrevious;
      U previousKey;

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
        Object localObject1 = this.previousKey;
        Object localObject2 = OperatorDistinctUntilChanged.this.keySelector.call(paramT);
        this.previousKey = localObject2;
        if (this.hasPrevious)
          if ((localObject1 != localObject2) && ((localObject2 == null) || (!localObject2.equals(localObject1))))
            this.val$child.onNext(paramT);
        while (true)
        {
          return;
          request(1L);
          continue;
          this.hasPrevious = true;
          this.val$child.onNext(paramT);
        }
      }
    };
  }

  private static class Holder
  {
    static final OperatorDistinctUntilChanged<?, ?> INSTANCE = new OperatorDistinctUntilChanged(UtilityFunctions.identity());
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorDistinctUntilChanged
 * JD-Core Version:    0.6.0
 */