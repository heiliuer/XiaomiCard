package rx.observers;

import java.util.Arrays;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.exceptions.UnsubscribeFailedException;
import rx.internal.util.RxJavaPluginUtils;

public class SafeSubscriber<T> extends Subscriber<T>
{
  private final Subscriber<? super T> actual;
  boolean done = false;

  public SafeSubscriber(Subscriber<? super T> paramSubscriber)
  {
    super(paramSubscriber);
    this.actual = paramSubscriber;
  }

  protected void _onError(Throwable paramThrowable)
  {
    RxJavaPluginUtils.handleException(paramThrowable);
    try
    {
      this.actual.onError(paramThrowable);
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        unsubscribe();
        return;
        localThrowable1 = localThrowable1;
        if ((localThrowable1 instanceof OnErrorNotImplementedException))
          try
          {
            unsubscribe();
            throw ((OnErrorNotImplementedException)localThrowable1);
          }
          catch (Throwable localThrowable3)
          {
            RxJavaPluginUtils.handleException(localThrowable3);
            Throwable[] arrayOfThrowable3 = new Throwable[2];
            arrayOfThrowable3[0] = paramThrowable;
            arrayOfThrowable3[1] = localThrowable3;
            throw new RuntimeException("Observer.onError not implemented and error while unsubscribing.", new CompositeException(Arrays.asList(arrayOfThrowable3)));
          }
        RxJavaPluginUtils.handleException(localThrowable1);
        try
        {
          unsubscribe();
          Throwable[] arrayOfThrowable2 = new Throwable[2];
          arrayOfThrowable2[0] = paramThrowable;
          arrayOfThrowable2[1] = localThrowable1;
          throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError", new CompositeException(Arrays.asList(arrayOfThrowable2)));
        }
        catch (Throwable localThrowable2)
        {
          RxJavaPluginUtils.handleException(localThrowable2);
          Throwable[] arrayOfThrowable1 = new Throwable[3];
          arrayOfThrowable1[0] = paramThrowable;
          arrayOfThrowable1[1] = localThrowable1;
          arrayOfThrowable1[2] = localThrowable2;
          throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(arrayOfThrowable1)));
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        RxJavaPluginUtils.handleException(localRuntimeException);
      }
    }
    throw new OnErrorFailedException(localRuntimeException);
  }

  public Subscriber<? super T> getActual()
  {
    return this.actual;
  }

  public void onCompleted()
  {
    if (!this.done)
      this.done = true;
    try
    {
      this.actual.onCompleted();
      try
      {
        unsubscribe();
        return;
      }
      catch (Throwable localThrowable3)
      {
        RxJavaPluginUtils.handleException(localThrowable3);
        throw new UnsubscribeFailedException(localThrowable3.getMessage(), localThrowable3);
      }
    }
    catch (Throwable localThrowable2)
    {
      Exceptions.throwIfFatal(localThrowable2);
      RxJavaPluginUtils.handleException(localThrowable2);
      throw new OnCompletedFailedException(localThrowable2.getMessage(), localThrowable2);
    }
    finally
    {
      try
      {
        unsubscribe();
        throw localObject;
      }
      catch (Throwable localThrowable1)
      {
        RxJavaPluginUtils.handleException(localThrowable1);
      }
    }
    throw new UnsubscribeFailedException(localThrowable1.getMessage(), localThrowable1);
  }

  public void onError(Throwable paramThrowable)
  {
    Exceptions.throwIfFatal(paramThrowable);
    if (!this.done)
    {
      this.done = true;
      _onError(paramThrowable);
    }
  }

  public void onNext(T paramT)
  {
    try
    {
      if (!this.done)
        this.actual.onNext(paramT);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        Exceptions.throwIfFatal(localThrowable);
        onError(localThrowable);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.observers.SafeSubscriber
 * JD-Core Version:    0.6.0
 */