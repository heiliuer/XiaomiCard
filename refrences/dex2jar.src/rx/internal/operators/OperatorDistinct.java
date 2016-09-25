package rx.internal.operators;

import java.util.HashSet;
import java.util.Set;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;

public final class OperatorDistinct<T, U>
  implements Observable.Operator<T, T>
{
  final Func1<? super T, ? extends U> keySelector;

  public OperatorDistinct(Func1<? super T, ? extends U> paramFunc1)
  {
    this.keySelector = paramFunc1;
  }

  public static <T> OperatorDistinct<T, T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      Set<U> keyMemory = new HashSet();

      public void onCompleted()
      {
        this.keyMemory = null;
        this.val$child.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.keyMemory = null;
        this.val$child.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        Object localObject = OperatorDistinct.this.keySelector.call(paramT);
        if (this.keyMemory.add(localObject))
          this.val$child.onNext(paramT);
        while (true)
        {
          return;
          request(1L);
        }
      }
    };
  }

  private static class Holder
  {
    static final OperatorDistinct<?, ?> INSTANCE = new OperatorDistinct(UtilityFunctions.identity());
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorDistinct
 * JD-Core Version:    0.6.0
 */