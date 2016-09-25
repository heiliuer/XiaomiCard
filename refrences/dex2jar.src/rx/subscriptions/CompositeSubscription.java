package rx.subscriptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class CompositeSubscription
  implements Subscription
{
  private Set<Subscription> subscriptions;
  private volatile boolean unsubscribed;

  public CompositeSubscription()
  {
  }

  public CompositeSubscription(Subscription[] paramArrayOfSubscription)
  {
    this.subscriptions = new HashSet(Arrays.asList(paramArrayOfSubscription));
  }

  private static void unsubscribeFromAll(Collection<Subscription> paramCollection)
  {
    if (paramCollection == null);
    while (true)
    {
      return;
      ArrayList localArrayList = null;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Subscription localSubscription = (Subscription)localIterator.next();
        try
        {
          localSubscription.unsubscribe();
        }
        catch (Throwable localThrowable)
        {
          if (localArrayList == null)
            localArrayList = new ArrayList();
          localArrayList.add(localThrowable);
        }
      }
      Exceptions.throwIfAny(localArrayList);
    }
  }

  // ERROR //
  public void add(Subscription paramSubscription)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 70 1 0
    //   6: ifeq +4 -> 10
    //   9: return
    //   10: aload_0
    //   11: getfield 72	rx/subscriptions/CompositeSubscription:unsubscribed	Z
    //   14: ifne +54 -> 68
    //   17: aload_0
    //   18: monitorenter
    //   19: aload_0
    //   20: getfield 72	rx/subscriptions/CompositeSubscription:unsubscribed	Z
    //   23: ifne +43 -> 66
    //   26: aload_0
    //   27: getfield 29	rx/subscriptions/CompositeSubscription:subscriptions	Ljava/util/Set;
    //   30: ifnonnull +15 -> 45
    //   33: aload_0
    //   34: new 18	java/util/HashSet
    //   37: dup
    //   38: iconst_4
    //   39: invokespecial 75	java/util/HashSet:<init>	(I)V
    //   42: putfield 29	rx/subscriptions/CompositeSubscription:subscriptions	Ljava/util/Set;
    //   45: aload_0
    //   46: getfield 29	rx/subscriptions/CompositeSubscription:subscriptions	Ljava/util/Set;
    //   49: aload_1
    //   50: invokeinterface 78 2 0
    //   55: pop
    //   56: aload_0
    //   57: monitorexit
    //   58: goto -49 -> 9
    //   61: astore_2
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_2
    //   65: athrow
    //   66: aload_0
    //   67: monitorexit
    //   68: aload_1
    //   69: invokeinterface 51 1 0
    //   74: goto -65 -> 9
    //
    // Exception table:
    //   from	to	target	type
    //   19	64	61	finally
    //   66	68	61	finally
  }

  public void clear()
  {
    if (!this.unsubscribed)
    {
      monitorenter;
      try
      {
        if ((this.unsubscribed) || (this.subscriptions == null))
        {
          monitorexit;
        }
        else
        {
          Set localSet = this.subscriptions;
          this.subscriptions = null;
          monitorexit;
          unsubscribeFromAll(localSet);
        }
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public boolean hasSubscriptions()
  {
    int i = 0;
    if (!this.unsubscribed)
    {
      monitorenter;
      try
      {
        if ((!this.unsubscribed) && (this.subscriptions != null) && (!this.subscriptions.isEmpty()))
          i = 1;
        monitorexit;
      }
      finally
      {
        localObject = finally;
        monitorexit;
        throw localObject;
      }
    }
    return i;
  }

  public boolean isUnsubscribed()
  {
    return this.unsubscribed;
  }

  public void remove(Subscription paramSubscription)
  {
    if (!this.unsubscribed)
    {
      monitorenter;
      try
      {
        if ((this.unsubscribed) || (this.subscriptions == null))
        {
          monitorexit;
        }
        else
        {
          boolean bool = this.subscriptions.remove(paramSubscription);
          monitorexit;
          if (bool)
            paramSubscription.unsubscribe();
        }
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public void unsubscribe()
  {
    if (!this.unsubscribed)
    {
      monitorenter;
      try
      {
        if (this.unsubscribed)
        {
          monitorexit;
        }
        else
        {
          this.unsubscribed = true;
          Set localSet = this.subscriptions;
          this.subscriptions = null;
          monitorexit;
          unsubscribeFromAll(localSet);
        }
      }
      finally
      {
        monitorexit;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.subscriptions.CompositeSubscription
 * JD-Core Version:    0.6.0
 */