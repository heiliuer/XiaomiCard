package rx.internal.operators;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.util.UtilityFunctions;

public final class OperatorSequenceEqual
{
  private static final Object LOCAL_ONCOMPLETED = new Object();

  private OperatorSequenceEqual()
  {
    throw new IllegalStateException("No instances!");
  }

  static <T> Observable<Object> materializeLite(Observable<T> paramObservable)
  {
    return Observable.concat(paramObservable.map(new Func1()
    {
      public Object call(T paramT)
      {
        return paramT;
      }
    }), Observable.just(LOCAL_ONCOMPLETED));
  }

  public static <T> Observable<Boolean> sequenceEqual(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Func2<? super T, ? super T, Boolean> paramFunc2)
  {
    return Observable.zip(materializeLite(paramObservable1), materializeLite(paramObservable2), new Func2(paramFunc2)
    {
      public Boolean call(Object paramObject1, Object paramObject2)
      {
        int i;
        int j;
        label19: Boolean localBoolean;
        if (paramObject1 == OperatorSequenceEqual.LOCAL_ONCOMPLETED)
        {
          i = 1;
          if (paramObject2 != OperatorSequenceEqual.LOCAL_ONCOMPLETED)
            break label42;
          j = 1;
          if ((i == 0) || (j == 0))
            break label48;
          localBoolean = Boolean.valueOf(true);
        }
        while (true)
        {
          return localBoolean;
          i = 0;
          break;
          label42: j = 0;
          break label19;
          label48: if ((i != 0) || (j != 0))
          {
            localBoolean = Boolean.valueOf(false);
            continue;
          }
          localBoolean = (Boolean)OperatorSequenceEqual.this.call(paramObject1, paramObject2);
        }
      }
    }).all(UtilityFunctions.identity());
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSequenceEqual
 * JD-Core Version:    0.6.0
 */