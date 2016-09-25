package rx.internal.operators;

import java.io.Serializable;
import rx.Notification.Kind;
import rx.Observer;

public final class NotificationLite<T>
{
  private static final NotificationLite INSTANCE = new NotificationLite();
  private static final Object ON_COMPLETED_SENTINEL = new Serializable()
  {
    private static final long serialVersionUID = 1L;

    public String toString()
    {
      return "Notification=>Completed";
    }
  };
  private static final Object ON_NEXT_NULL_SENTINEL = new Serializable()
  {
    private static final long serialVersionUID = 2L;

    public String toString()
    {
      return "Notification=>NULL";
    }
  };

  public static <T> NotificationLite<T> instance()
  {
    return INSTANCE;
  }

  public boolean accept(Observer<? super T> paramObserver, Object paramObject)
  {
    int i = 1;
    if (paramObject == ON_COMPLETED_SENTINEL)
      paramObserver.onCompleted();
    while (true)
    {
      return i;
      if (paramObject == ON_NEXT_NULL_SENTINEL)
      {
        paramObserver.onNext(null);
        i = 0;
        continue;
      }
      if (paramObject == null)
        break;
      if (paramObject.getClass() == OnErrorSentinel.class)
      {
        paramObserver.onError(((OnErrorSentinel)paramObject).e);
        continue;
      }
      paramObserver.onNext(paramObject);
      i = 0;
    }
    throw new IllegalArgumentException("The lite notification can not be null");
  }

  public Object completed()
  {
    return ON_COMPLETED_SENTINEL;
  }

  public Object error(Throwable paramThrowable)
  {
    return new OnErrorSentinel(paramThrowable);
  }

  public Throwable getError(Object paramObject)
  {
    return ((OnErrorSentinel)paramObject).e;
  }

  public T getValue(Object paramObject)
  {
    if (paramObject == ON_NEXT_NULL_SENTINEL)
      paramObject = null;
    return paramObject;
  }

  public boolean isCompleted(Object paramObject)
  {
    if (paramObject == ON_COMPLETED_SENTINEL);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isError(Object paramObject)
  {
    return paramObject instanceof OnErrorSentinel;
  }

  public boolean isNext(Object paramObject)
  {
    if ((paramObject != null) && (!isError(paramObject)) && (!isCompleted(paramObject)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isNull(Object paramObject)
  {
    if (paramObject == ON_NEXT_NULL_SENTINEL);
    for (int i = 1; ; i = 0)
      return i;
  }

  public Notification.Kind kind(Object paramObject)
  {
    if (paramObject == null)
      throw new IllegalArgumentException("The lite notification can not be null");
    Notification.Kind localKind;
    if (paramObject == ON_COMPLETED_SENTINEL)
      localKind = Notification.Kind.OnCompleted;
    while (true)
    {
      return localKind;
      if ((paramObject instanceof OnErrorSentinel))
      {
        localKind = Notification.Kind.OnError;
        continue;
      }
      localKind = Notification.Kind.OnNext;
    }
  }

  public Object next(T paramT)
  {
    if (paramT == null)
      paramT = ON_NEXT_NULL_SENTINEL;
    return paramT;
  }

  private static class OnErrorSentinel
    implements Serializable
  {
    private static final long serialVersionUID = 3L;
    private final Throwable e;

    public OnErrorSentinel(Throwable paramThrowable)
    {
      this.e = paramThrowable;
    }

    public String toString()
    {
      return "Notification=>Error:" + this.e;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.NotificationLite
 * JD-Core Version:    0.6.0
 */