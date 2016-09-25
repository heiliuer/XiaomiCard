package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;

public final class MultipleAssignmentSubscription
  implements Subscription
{
  final AtomicReference<State> state = new AtomicReference(new State(false, Subscriptions.empty()));

  public Subscription get()
  {
    return ((State)this.state.get()).subscription;
  }

  public boolean isUnsubscribed()
  {
    return ((State)this.state.get()).isUnsubscribed;
  }

  public void set(Subscription paramSubscription)
  {
    if (paramSubscription == null)
      throw new IllegalArgumentException("Subscription can not be null");
    AtomicReference localAtomicReference = this.state;
    while (true)
    {
      State localState = (State)localAtomicReference.get();
      if (localState.isUnsubscribed)
        paramSubscription.unsubscribe();
      while (true)
      {
        return;
        if (!localAtomicReference.compareAndSet(localState, localState.set(paramSubscription)))
          break;
      }
    }
  }

  public void unsubscribe()
  {
    AtomicReference localAtomicReference = this.state;
    while (true)
    {
      State localState = (State)localAtomicReference.get();
      if (localState.isUnsubscribed);
      while (true)
      {
        return;
        if (!localAtomicReference.compareAndSet(localState, localState.unsubscribe()))
          break;
        localState.subscription.unsubscribe();
      }
    }
  }

  private static final class State
  {
    final boolean isUnsubscribed;
    final Subscription subscription;

    State(boolean paramBoolean, Subscription paramSubscription)
    {
      this.isUnsubscribed = paramBoolean;
      this.subscription = paramSubscription;
    }

    State set(Subscription paramSubscription)
    {
      return new State(this.isUnsubscribed, paramSubscription);
    }

    State unsubscribe()
    {
      return new State(true, this.subscription);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.subscriptions.MultipleAssignmentSubscription
 * JD-Core Version:    0.6.0
 */