package rx.functions;

public final class Actions
{
  private static final EmptyAction EMPTY_ACTION = new EmptyAction(null);

  private Actions()
  {
    throw new IllegalStateException("No instances!");
  }

  public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8> empty()
  {
    return EMPTY_ACTION;
  }

  public static Func0<Void> toFunc(Action0 paramAction0)
  {
    return toFunc(paramAction0, (Void)null);
  }

  public static <R> Func0<R> toFunc(Action0 paramAction0, R paramR)
  {
    return new Func0(paramAction0, paramR)
    {
      public R call()
      {
        Actions.this.call();
        return this.val$result;
      }
    };
  }

  public static <T1> Func1<T1, Void> toFunc(Action1<T1> paramAction1)
  {
    return toFunc(paramAction1, (Void)null);
  }

  public static <T1, R> Func1<T1, R> toFunc(Action1<T1> paramAction1, R paramR)
  {
    return new Func1(paramAction1, paramR)
    {
      public R call(T1 paramT1)
      {
        Actions.this.call(paramT1);
        return this.val$result;
      }
    };
  }

  public static <T1, T2> Func2<T1, T2, Void> toFunc(Action2<T1, T2> paramAction2)
  {
    return toFunc(paramAction2, (Void)null);
  }

  public static <T1, T2, R> Func2<T1, T2, R> toFunc(Action2<T1, T2> paramAction2, R paramR)
  {
    return new Func2(paramAction2, paramR)
    {
      public R call(T1 paramT1, T2 paramT2)
      {
        Actions.this.call(paramT1, paramT2);
        return this.val$result;
      }
    };
  }

  public static <T1, T2, T3> Func3<T1, T2, T3, Void> toFunc(Action3<T1, T2, T3> paramAction3)
  {
    return toFunc(paramAction3, (Void)null);
  }

  public static <T1, T2, T3, R> Func3<T1, T2, T3, R> toFunc(Action3<T1, T2, T3> paramAction3, R paramR)
  {
    return new Func3(paramAction3, paramR)
    {
      public R call(T1 paramT1, T2 paramT2, T3 paramT3)
      {
        Actions.this.call(paramT1, paramT2, paramT3);
        return this.val$result;
      }
    };
  }

  public static <T1, T2, T3, T4> Func4<T1, T2, T3, T4, Void> toFunc(Action4<T1, T2, T3, T4> paramAction4)
  {
    return toFunc(paramAction4, (Void)null);
  }

  public static <T1, T2, T3, T4, R> Func4<T1, T2, T3, T4, R> toFunc(Action4<T1, T2, T3, T4> paramAction4, R paramR)
  {
    return new Func4(paramAction4, paramR)
    {
      public R call(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4)
      {
        Actions.this.call(paramT1, paramT2, paramT3, paramT4);
        return this.val$result;
      }
    };
  }

  public static <T1, T2, T3, T4, T5> Func5<T1, T2, T3, T4, T5, Void> toFunc(Action5<T1, T2, T3, T4, T5> paramAction5)
  {
    return toFunc(paramAction5, (Void)null);
  }

  public static <T1, T2, T3, T4, T5, R> Func5<T1, T2, T3, T4, T5, R> toFunc(Action5<T1, T2, T3, T4, T5> paramAction5, R paramR)
  {
    return new Func5(paramAction5, paramR)
    {
      public R call(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5)
      {
        Actions.this.call(paramT1, paramT2, paramT3, paramT4, paramT5);
        return this.val$result;
      }
    };
  }

  public static <T1, T2, T3, T4, T5, T6> Func6<T1, T2, T3, T4, T5, T6, Void> toFunc(Action6<T1, T2, T3, T4, T5, T6> paramAction6)
  {
    return toFunc(paramAction6, (Void)null);
  }

  public static <T1, T2, T3, T4, T5, T6, R> Func6<T1, T2, T3, T4, T5, T6, R> toFunc(Action6<T1, T2, T3, T4, T5, T6> paramAction6, R paramR)
  {
    return new Func6(paramAction6, paramR)
    {
      public R call(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6)
      {
        Actions.this.call(paramT1, paramT2, paramT3, paramT4, paramT5, paramT6);
        return this.val$result;
      }
    };
  }

  public static <T1, T2, T3, T4, T5, T6, T7> Func7<T1, T2, T3, T4, T5, T6, T7, Void> toFunc(Action7<T1, T2, T3, T4, T5, T6, T7> paramAction7)
  {
    return toFunc(paramAction7, (Void)null);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Func7<T1, T2, T3, T4, T5, T6, T7, R> toFunc(Action7<T1, T2, T3, T4, T5, T6, T7> paramAction7, R paramR)
  {
    return new Func7(paramAction7, paramR)
    {
      public R call(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7)
      {
        Actions.this.call(paramT1, paramT2, paramT3, paramT4, paramT5, paramT6, paramT7);
        return this.val$result;
      }
    };
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8> Func8<T1, T2, T3, T4, T5, T6, T7, T8, Void> toFunc(Action8<T1, T2, T3, T4, T5, T6, T7, T8> paramAction8)
  {
    return toFunc(paramAction8, (Void)null);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> toFunc(Action8<T1, T2, T3, T4, T5, T6, T7, T8> paramAction8, R paramR)
  {
    return new Func8(paramAction8, paramR)
    {
      public R call(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8)
      {
        Actions.this.call(paramT1, paramT2, paramT3, paramT4, paramT5, paramT6, paramT7, paramT8);
        return this.val$result;
      }
    };
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, Void> toFunc(Action9<T1, T2, T3, T4, T5, T6, T7, T8, T9> paramAction9)
  {
    return toFunc(paramAction9, (Void)null);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> toFunc(Action9<T1, T2, T3, T4, T5, T6, T7, T8, T9> paramAction9, R paramR)
  {
    return new Func9(paramAction9, paramR)
    {
      public R call(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8, T9 paramT9)
      {
        Actions.this.call(paramT1, paramT2, paramT3, paramT4, paramT5, paramT6, paramT7, paramT8, paramT9);
        return this.val$result;
      }
    };
  }

  public static FuncN<Void> toFunc(ActionN paramActionN)
  {
    return toFunc(paramActionN, (Void)null);
  }

  public static <R> FuncN<R> toFunc(ActionN paramActionN, R paramR)
  {
    return new FuncN(paramActionN, paramR)
    {
      public R call(Object[] paramArrayOfObject)
      {
        Actions.this.call(paramArrayOfObject);
        return this.val$result;
      }
    };
  }

  private static final class EmptyAction<T0, T1, T2, T3, T4, T5, T6, T7, T8>
    implements Action0, Action1<T0>, Action2<T0, T1>, Action3<T0, T1, T2>, Action4<T0, T1, T2, T3>, Action5<T0, T1, T2, T3, T4>, Action6<T0, T1, T2, T3, T4, T5>, Action7<T0, T1, T2, T3, T4, T5, T6>, Action8<T0, T1, T2, T3, T4, T5, T6, T7>, Action9<T0, T1, T2, T3, T4, T5, T6, T7, T8>, ActionN
  {
    public void call()
    {
    }

    public void call(T0 paramT0)
    {
    }

    public void call(T0 paramT0, T1 paramT1)
    {
    }

    public void call(T0 paramT0, T1 paramT1, T2 paramT2)
    {
    }

    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3)
    {
    }

    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4)
    {
    }

    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5)
    {
    }

    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6)
    {
    }

    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7)
    {
    }

    public void call(T0 paramT0, T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8)
    {
    }

    public void call(Object[] paramArrayOfObject)
    {
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.functions.Actions
 * JD-Core Version:    0.6.0
 */