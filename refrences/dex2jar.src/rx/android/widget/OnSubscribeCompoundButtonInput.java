package rx.android.widget;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.android.AndroidSubscriptions;
import rx.android.internal.Assertions;
import rx.android.view.OnCheckedChangeEvent;
import rx.functions.Action0;

class OnSubscribeCompoundButtonInput
  implements Observable.OnSubscribe<OnCheckedChangeEvent>
{
  private final CompoundButton button;
  private final boolean emitInitialValue;

  public OnSubscribeCompoundButtonInput(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    this.emitInitialValue = paramBoolean;
    this.button = paramCompoundButton;
  }

  public void call(Subscriber<? super OnCheckedChangeEvent> paramSubscriber)
  {
    Assertions.assertUiThread();
    CompositeOnCheckedChangeListener localCompositeOnCheckedChangeListener = CachedListeners.getFromViewOrCreate(this.button);
    1 local1 = new CompoundButton.OnCheckedChangeListener(paramSubscriber)
    {
      public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
      {
        this.val$observer.onNext(OnCheckedChangeEvent.create(OnSubscribeCompoundButtonInput.this.button, paramBoolean));
      }
    };
    Subscription localSubscription = AndroidSubscriptions.unsubscribeInUiThread(new Action0(localCompositeOnCheckedChangeListener, local1)
    {
      public void call()
      {
        this.val$composite.removeOnCheckedChangeListener(this.val$listener);
      }
    });
    if (this.emitInitialValue)
      paramSubscriber.onNext(OnCheckedChangeEvent.create(this.button));
    localCompositeOnCheckedChangeListener.addOnCheckedChangeListener(local1);
    paramSubscriber.add(localSubscription);
  }

  private static class CachedListeners
  {
    private static final Map<View, OnSubscribeCompoundButtonInput.CompositeOnCheckedChangeListener> sCachedListeners = new WeakHashMap();

    public static OnSubscribeCompoundButtonInput.CompositeOnCheckedChangeListener getFromViewOrCreate(CompoundButton paramCompoundButton)
    {
      Object localObject = (OnSubscribeCompoundButtonInput.CompositeOnCheckedChangeListener)sCachedListeners.get(paramCompoundButton);
      if (localObject != null);
      while (true)
      {
        return localObject;
        OnSubscribeCompoundButtonInput.CompositeOnCheckedChangeListener localCompositeOnCheckedChangeListener = new OnSubscribeCompoundButtonInput.CompositeOnCheckedChangeListener(null);
        sCachedListeners.put(paramCompoundButton, localCompositeOnCheckedChangeListener);
        paramCompoundButton.setOnCheckedChangeListener(localCompositeOnCheckedChangeListener);
        localObject = localCompositeOnCheckedChangeListener;
      }
    }
  }

  private static class CompositeOnCheckedChangeListener
    implements CompoundButton.OnCheckedChangeListener
  {
    private final List<CompoundButton.OnCheckedChangeListener> listeners = new ArrayList();

    public boolean addOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener paramOnCheckedChangeListener)
    {
      return this.listeners.add(paramOnCheckedChangeListener);
    }

    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      Iterator localIterator = this.listeners.iterator();
      while (localIterator.hasNext())
        ((CompoundButton.OnCheckedChangeListener)localIterator.next()).onCheckedChanged(paramCompoundButton, paramBoolean);
    }

    public boolean removeOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener paramOnCheckedChangeListener)
    {
      return this.listeners.remove(paramOnCheckedChangeListener);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.OnSubscribeCompoundButtonInput
 * JD-Core Version:    0.6.0
 */