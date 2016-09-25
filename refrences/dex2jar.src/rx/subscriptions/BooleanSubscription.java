package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.functions.Action0;

public final class BooleanSubscription
  implements Subscription
{
  static final Action0 EMPTY_ACTION = new Action0()
  {
    public void call()
    {
    }
  };
  final AtomicReference<Action0> actionRef;

  public BooleanSubscription()
  {
    this.actionRef = new AtomicReference();
  }

  private BooleanSubscription(Action0 paramAction0)
  {
    this.actionRef = new AtomicReference(paramAction0);
  }

  public static BooleanSubscription create()
  {
    return new BooleanSubscription();
  }

  public static BooleanSubscription create(Action0 paramAction0)
  {
    return new BooleanSubscription(paramAction0);
  }

  public boolean isUnsubscribed()
  {
    if (this.actionRef.get() == EMPTY_ACTION);
    for (int i = 1; ; i = 0)
      return i;
  }

  public final void unsubscribe()
  {
    if ((Action0)this.actionRef.get() != EMPTY_ACTION)
    {
      Action0 localAction0 = (Action0)this.actionRef.getAndSet(EMPTY_ACTION);
      if ((localAction0 != null) && (localAction0 != EMPTY_ACTION))
        localAction0.call();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.subscriptions.BooleanSubscription
 * JD-Core Version:    0.6.0
 */