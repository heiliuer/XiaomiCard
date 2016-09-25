package rx.internal.util;

import rx.Subscription;

public class SynchronizedSubscription
  implements Subscription
{
  private final Subscription s;

  public SynchronizedSubscription(Subscription paramSubscription)
  {
    this.s = paramSubscription;
  }

  /** @deprecated */
  public boolean isUnsubscribed()
  {
    monitorenter;
    try
    {
      boolean bool = this.s.isUnsubscribed();
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void unsubscribe()
  {
    monitorenter;
    try
    {
      this.s.unsubscribe();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.SynchronizedSubscription
 * JD-Core Version:    0.6.0
 */