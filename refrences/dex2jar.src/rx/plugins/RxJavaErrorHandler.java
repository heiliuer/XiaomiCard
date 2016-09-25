package rx.plugins;

import rx.annotations.Beta;
import rx.exceptions.Exceptions;

public abstract class RxJavaErrorHandler
{
  protected static final String ERROR_IN_RENDERING_SUFFIX = ".errorRendering";

  public void handleError(Throwable paramThrowable)
  {
  }

  @Beta
  public final String handleOnNextValueRendering(Object paramObject)
  {
    try
    {
      String str2 = render(paramObject);
      str1 = str2;
      return str1;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
      {
        Thread.currentThread().interrupt();
        String str1 = paramObject.getClass().getName() + ".errorRendering";
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Exceptions.throwIfFatal(localThrowable);
    }
  }

  @Beta
  protected String render(Object paramObject)
    throws InterruptedException
  {
    return null;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.plugins.RxJavaErrorHandler
 * JD-Core Version:    0.6.0
 */