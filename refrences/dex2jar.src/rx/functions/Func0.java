package rx.functions;

import java.util.concurrent.Callable;

public abstract interface Func0<R> extends Function, Callable<R>
{
  public abstract R call();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.functions.Func0
 * JD-Core Version:    0.6.0
 */