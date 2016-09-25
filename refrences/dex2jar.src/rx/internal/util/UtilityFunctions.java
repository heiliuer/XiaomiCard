package rx.internal.util;

import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;
import rx.functions.FuncN;

public final class UtilityFunctions
{
  private static final NullFunction NULL_FUNCTION = new NullFunction(null);

  public static <T> Func1<? super T, Boolean> alwaysFalse()
  {
    return AlwaysFalse.INSTANCE;
  }

  public static <T> Func1<? super T, Boolean> alwaysTrue()
  {
    return AlwaysTrue.INSTANCE;
  }

  public static <T> Func1<T, T> identity()
  {
    return new Func1()
    {
      public T call(T paramT)
      {
        return paramT;
      }
    };
  }

  public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R> NullFunction<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R> returnNull()
  {
    return NULL_FUNCTION;
  }

  private static final class NullFunction<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R>
    implements Func0<R>, Func1<T0, R>, Func2<T0, T1, R>, Func3<T0, T1, T2, R>, Func4<T0, T1, T2, T3, R>, Func5<T0, T1, T2, T3, T4, R>, Func6<T0, T1, T2, T3, T4, T5, R>, Func7<T0, T1, T2, T3, T4, T5, T6, R>, Func8<T0, T1, T2, T3, T4, T5, T6, T7, R>, Func9<T0, T1, T2, T3, T4, T5, T6, T7, T8, R>, FuncN<R>
  {
    public R call()
    {
      return null;
    }

    public R call(T0 paramT0)
    {
      return null;
    }

    public R call(T0 paramT0, T1 paramT1)
    {
      return null;
    }

    public R call(T0 paramT0, T1 paramT1, T2 paramT2)
    {
      return null;
    }

    public R call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3)
    {
      return null;
    }

    public R call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4)
    {
      return null;
    }

    public R call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5)
    {
      return null;
    }

    public R call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6)
    {
      return null;
    }

    public R call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7)
    {
      return null;
    }

    public R call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8)
    {
      return null;
    }

    public R call(Object[] paramArrayOfObject)
    {
      return null;
    }
  }

  private static enum AlwaysFalse
    implements Func1<Object, Boolean>
  {
    static
    {
      AlwaysFalse[] arrayOfAlwaysFalse = new AlwaysFalse[1];
      arrayOfAlwaysFalse[0] = INSTANCE;
      $VALUES = arrayOfAlwaysFalse;
    }

    public Boolean call(Object paramObject)
    {
      return Boolean.valueOf(false);
    }
  }

  private static enum AlwaysTrue
    implements Func1<Object, Boolean>
  {
    static
    {
      AlwaysTrue[] arrayOfAlwaysTrue = new AlwaysTrue[1];
      arrayOfAlwaysTrue[0] = INSTANCE;
      $VALUES = arrayOfAlwaysTrue;
    }

    public Boolean call(Object paramObject)
    {
      return Boolean.valueOf(true);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.UtilityFunctions
 * JD-Core Version:    0.6.0
 */