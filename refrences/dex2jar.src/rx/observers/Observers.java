package rx.observers;

import rx.Observer;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;

public final class Observers
{
  private static final Observer<Object> EMPTY = new Observer()
  {
    public final void onCompleted()
    {
    }

    public final void onError(Throwable paramThrowable)
    {
      throw new OnErrorNotImplementedException(paramThrowable);
    }

    public final void onNext(Object paramObject)
    {
    }
  };

  private Observers()
  {
    throw new IllegalStateException("No instances!");
  }

  public static final <T> Observer<T> create(Action1<? super T> paramAction1)
  {
    if (paramAction1 == null)
      throw new IllegalArgumentException("onNext can not be null");
    return new Observer(paramAction1)
    {
      public final void onCompleted()
      {
      }

      public final void onError(Throwable paramThrowable)
      {
        throw new OnErrorNotImplementedException(paramThrowable);
      }

      public final void onNext(T paramT)
      {
        Observers.this.call(paramT);
      }
    };
  }

  public static final <T> Observer<T> create(Action1<? super T> paramAction1, Action1<Throwable> paramAction11)
  {
    if (paramAction1 == null)
      throw new IllegalArgumentException("onNext can not be null");
    if (paramAction11 == null)
      throw new IllegalArgumentException("onError can not be null");
    return new Observer(paramAction11, paramAction1)
    {
      public final void onCompleted()
      {
      }

      public final void onError(Throwable paramThrowable)
      {
        Observers.this.call(paramThrowable);
      }

      public final void onNext(T paramT)
      {
        this.val$onNext.call(paramT);
      }
    };
  }

  public static final <T> Observer<T> create(Action1<? super T> paramAction1, Action1<Throwable> paramAction11, Action0 paramAction0)
  {
    if (paramAction1 == null)
      throw new IllegalArgumentException("onNext can not be null");
    if (paramAction11 == null)
      throw new IllegalArgumentException("onError can not be null");
    if (paramAction0 == null)
      throw new IllegalArgumentException("onComplete can not be null");
    return new Observer(paramAction0, paramAction11, paramAction1)
    {
      public final void onCompleted()
      {
        Observers.this.call();
      }

      public final void onError(Throwable paramThrowable)
      {
        this.val$onError.call(paramThrowable);
      }

      public final void onNext(T paramT)
      {
        this.val$onNext.call(paramT);
      }
    };
  }

  public static <T> Observer<T> empty()
  {
    return EMPTY;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.observers.Observers
 * JD-Core Version:    0.6.0
 */