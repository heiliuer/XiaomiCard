package rx.functions;

public abstract interface Func3<T1, T2, T3, R> extends Function
{
  public abstract R call(T1 paramT1, T2 paramT2, T3 paramT3);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.functions.Func3
 * JD-Core Version:    0.6.0
 */