package rx.functions;

public final class Functions
{
  private Functions()
  {
    throw new IllegalStateException("No instances!");
  }

  public static FuncN<Void> fromAction(Action0 paramAction0)
  {
    return new FuncN(paramAction0)
    {
      public Void call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 0)
          throw new RuntimeException("Action0 expecting 0 arguments.");
        Functions.this.call();
        return null;
      }
    };
  }

  public static <T0> FuncN<Void> fromAction(Action1<? super T0> paramAction1)
  {
    return new FuncN(paramAction1)
    {
      public Void call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 1)
          throw new RuntimeException("Action1 expecting 1 argument.");
        Functions.this.call(paramArrayOfObject[0]);
        return null;
      }
    };
  }

  public static <T0, T1> FuncN<Void> fromAction(Action2<? super T0, ? super T1> paramAction2)
  {
    return new FuncN(paramAction2)
    {
      public Void call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 2)
          throw new RuntimeException("Action3 expecting 2 arguments.");
        Functions.this.call(paramArrayOfObject[0], paramArrayOfObject[1]);
        return null;
      }
    };
  }

  public static <T0, T1, T2> FuncN<Void> fromAction(Action3<? super T0, ? super T1, ? super T2> paramAction3)
  {
    return new FuncN(paramAction3)
    {
      public Void call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 3)
          throw new RuntimeException("Action3 expecting 3 arguments.");
        Functions.this.call(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2]);
        return null;
      }
    };
  }

  public static <R> FuncN<R> fromFunc(Func0<? extends R> paramFunc0)
  {
    return new FuncN(paramFunc0)
    {
      public R call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 0)
          throw new RuntimeException("Func0 expecting 0 arguments.");
        return Functions.this.call();
      }
    };
  }

  public static <T0, R> FuncN<R> fromFunc(Func1<? super T0, ? extends R> paramFunc1)
  {
    return new FuncN(paramFunc1)
    {
      public R call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 1)
          throw new RuntimeException("Func1 expecting 1 argument.");
        return Functions.this.call(paramArrayOfObject[0]);
      }
    };
  }

  public static <T0, T1, R> FuncN<R> fromFunc(Func2<? super T0, ? super T1, ? extends R> paramFunc2)
  {
    return new FuncN(paramFunc2)
    {
      public R call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 2)
          throw new RuntimeException("Func2 expecting 2 arguments.");
        return Functions.this.call(paramArrayOfObject[0], paramArrayOfObject[1]);
      }
    };
  }

  public static <T0, T1, T2, R> FuncN<R> fromFunc(Func3<? super T0, ? super T1, ? super T2, ? extends R> paramFunc3)
  {
    return new FuncN(paramFunc3)
    {
      public R call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 3)
          throw new RuntimeException("Func3 expecting 3 arguments.");
        return Functions.this.call(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2]);
      }
    };
  }

  public static <T0, T1, T2, T3, R> FuncN<R> fromFunc(Func4<? super T0, ? super T1, ? super T2, ? super T3, ? extends R> paramFunc4)
  {
    return new FuncN(paramFunc4)
    {
      public R call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 4)
          throw new RuntimeException("Func4 expecting 4 arguments.");
        return Functions.this.call(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3]);
      }
    };
  }

  public static <T0, T1, T2, T3, T4, R> FuncN<R> fromFunc(Func5<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? extends R> paramFunc5)
  {
    return new FuncN(paramFunc5)
    {
      public R call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 5)
          throw new RuntimeException("Func5 expecting 5 arguments.");
        return Functions.this.call(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4]);
      }
    };
  }

  public static <T0, T1, T2, T3, T4, T5, R> FuncN<R> fromFunc(Func6<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> paramFunc6)
  {
    return new FuncN(paramFunc6)
    {
      public R call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 6)
          throw new RuntimeException("Func6 expecting 6 arguments.");
        return Functions.this.call(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramArrayOfObject[5]);
      }
    };
  }

  public static <T0, T1, T2, T3, T4, T5, T6, R> FuncN<R> fromFunc(Func7<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> paramFunc7)
  {
    return new FuncN(paramFunc7)
    {
      public R call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 7)
          throw new RuntimeException("Func7 expecting 7 arguments.");
        return Functions.this.call(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramArrayOfObject[5], paramArrayOfObject[6]);
      }
    };
  }

  public static <T0, T1, T2, T3, T4, T5, T6, T7, R> FuncN<R> fromFunc(Func8<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> paramFunc8)
  {
    return new FuncN(paramFunc8)
    {
      public R call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 8)
          throw new RuntimeException("Func8 expecting 8 arguments.");
        return Functions.this.call(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramArrayOfObject[5], paramArrayOfObject[6], paramArrayOfObject[7]);
      }
    };
  }

  public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, R> FuncN<R> fromFunc(Func9<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> paramFunc9)
  {
    return new FuncN(paramFunc9)
    {
      public R call(Object[] paramArrayOfObject)
      {
        if (paramArrayOfObject.length != 9)
          throw new RuntimeException("Func9 expecting 9 arguments.");
        return Functions.this.call(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramArrayOfObject[5], paramArrayOfObject[6], paramArrayOfObject[7], paramArrayOfObject[8]);
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.functions.Functions
 * JD-Core Version:    0.6.0
 */