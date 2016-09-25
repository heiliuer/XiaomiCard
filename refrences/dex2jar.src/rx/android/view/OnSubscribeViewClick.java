package rx.android.view;

import android.view.View;
import android.view.View.OnClickListener;
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
import rx.functions.Action0;

final class OnSubscribeViewClick
  implements Observable.OnSubscribe<OnClickEvent>
{
  private final boolean emitInitialValue;
  private final View view;

  public OnSubscribeViewClick(View paramView, boolean paramBoolean)
  {
    this.emitInitialValue = paramBoolean;
    this.view = paramView;
  }

  public void call(Subscriber<? super OnClickEvent> paramSubscriber)
  {
    Assertions.assertUiThread();
    CompositeOnClickListener localCompositeOnClickListener = CachedListeners.getFromViewOrCreate(this.view);
    1 local1 = new View.OnClickListener(paramSubscriber)
    {
      public void onClick(View paramView)
      {
        this.val$observer.onNext(OnClickEvent.create(OnSubscribeViewClick.this.view));
      }
    };
    Subscription localSubscription = AndroidSubscriptions.unsubscribeInUiThread(new Action0(localCompositeOnClickListener, local1)
    {
      public void call()
      {
        this.val$composite.removeOnClickListener(this.val$listener);
      }
    });
    if (this.emitInitialValue)
      paramSubscriber.onNext(OnClickEvent.create(this.view));
    localCompositeOnClickListener.addOnClickListener(local1);
    paramSubscriber.add(localSubscription);
  }

  private static class CachedListeners
  {
    private static final Map<View, OnSubscribeViewClick.CompositeOnClickListener> sCachedListeners = new WeakHashMap();

    public static OnSubscribeViewClick.CompositeOnClickListener getFromViewOrCreate(View paramView)
    {
      Object localObject = (OnSubscribeViewClick.CompositeOnClickListener)sCachedListeners.get(paramView);
      if (localObject != null);
      while (true)
      {
        return localObject;
        OnSubscribeViewClick.CompositeOnClickListener localCompositeOnClickListener = new OnSubscribeViewClick.CompositeOnClickListener(null);
        sCachedListeners.put(paramView, localCompositeOnClickListener);
        paramView.setOnClickListener(localCompositeOnClickListener);
        localObject = localCompositeOnClickListener;
      }
    }
  }

  private static class CompositeOnClickListener
    implements View.OnClickListener
  {
    private final List<View.OnClickListener> listeners = new ArrayList();

    public boolean addOnClickListener(View.OnClickListener paramOnClickListener)
    {
      return this.listeners.add(paramOnClickListener);
    }

    public void onClick(View paramView)
    {
      Iterator localIterator = this.listeners.iterator();
      while (localIterator.hasNext())
        ((View.OnClickListener)localIterator.next()).onClick(paramView);
    }

    public boolean removeOnClickListener(View.OnClickListener paramOnClickListener)
    {
      return this.listeners.remove(paramOnClickListener);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.view.OnSubscribeViewClick
 * JD-Core Version:    0.6.0
 */