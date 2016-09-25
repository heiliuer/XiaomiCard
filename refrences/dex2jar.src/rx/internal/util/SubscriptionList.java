package rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class SubscriptionList
  implements Subscription
{
  private LinkedList<Subscription> subscriptions;
  private volatile boolean unsubscribed;

  public SubscriptionList()
  {
  }

  public SubscriptionList(Subscription paramSubscription)
  {
    this.subscriptions = new LinkedList();
    this.subscriptions.add(paramSubscription);
  }

  public SubscriptionList(Subscription[] paramArrayOfSubscription)
  {
    this.subscriptions = new LinkedList(Arrays.asList(paramArrayOfSubscription));
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
    //   1: invokeinterface 72 1 0
    //   6: ifeq +4 -> 10
    //   9: return
    //   10: aload_0
    //   11: getfield 74	rx/internal/util/SubscriptionList:unsubscribed	Z
    //   14: ifne +52 -> 66
    //   17: aload_0
    //   18: monitorenter
    //   19: aload_0
    //   20: getfield 74	rx/internal/util/SubscriptionList:unsubscribed	Z
    //   23: ifne +41 -> 64
    //   26: aload_0
    //   27: getfield 21	rx/internal/util/SubscriptionList:subscriptions	Ljava/util/LinkedList;
    //   30: astore_3
    //   31: aload_3
    //   32: ifnonnull +16 -> 48
    //   35: new 18	java/util/LinkedList
    //   38: dup
    //   39: invokespecial 19	java/util/LinkedList:<init>	()V
    //   42: astore_3
    //   43: aload_0
    //   44: aload_3
    //   45: putfield 21	rx/internal/util/SubscriptionList:subscriptions	Ljava/util/LinkedList;
    //   48: aload_3
    //   49: aload_1
    //   50: invokevirtual 25	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   53: pop
    //   54: aload_0
    //   55: monitorexit
    //   56: goto -47 -> 9
    //   59: astore_2
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_2
    //   63: athrow
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: invokeinterface 57 1 0
    //   72: goto -63 -> 9
    //
    // Exception table:
    //   from	to	target	type
    //   19	62	59	finally
    //   64	66	59	finally
  }

  public void clear()
  {
    if (!this.unsubscribed)
      monitorenter;
    try
    {
      LinkedList localLinkedList = this.subscriptions;
      this.subscriptions = null;
      monitorexit;
      unsubscribeFromAll(localLinkedList);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
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
        LinkedList localLinkedList = this.subscriptions;
        if ((this.unsubscribed) || (localLinkedList == null))
        {
          monitorexit;
        }
        else
        {
          boolean bool = localLinkedList.remove(paramSubscription);
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
          LinkedList localLinkedList = this.subscriptions;
          this.subscriptions = null;
          monitorexit;
          unsubscribeFromAll(localLinkedList);
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
 * Qualified Name:     rx.internal.util.SubscriptionList
 * JD-Core Version:    0.6.0
 */