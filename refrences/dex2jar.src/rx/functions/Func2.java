package rx.functions;

public abstract interface Func2<T1, T2, R> extends Function
{
  public abstract R call(T1 paramT1, T2 paramT2);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.functions.Func2
 * JD-Core Version:    0.6.0
 */