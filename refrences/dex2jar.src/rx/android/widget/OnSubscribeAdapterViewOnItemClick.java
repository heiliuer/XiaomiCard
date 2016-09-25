package rx.android.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

class OnSubscribeAdapterViewOnItemClick
  implements Observable.OnSubscribe<OnItemClickEvent>
{
  private final AdapterView<?> adapterView;

  public OnSubscribeAdapterViewOnItemClick(AdapterView<?> paramAdapterView)
  {
    this.adapterView = paramAdapterView;
  }

  public void call(Subscriber<? super OnItemClickEvent> paramSubscriber)
  {
    Assertions.assertUiThread();
    CompositeOnClickListener localCompositeOnClickListener = CachedListeners.getFromViewOrCreate(this.adapterView);
    1 local1 = new AdapterView.OnItemClickListener(paramSubscriber)
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        this.val$observer.onNext(OnItemClickEvent.create(paramAdapterView, paramView, paramInt, paramLong));
      }
    };
    Subscription localSubscription = AndroidSubscriptions.unsubscribeInUiThread(new Action0(localCompositeOnClickListener, local1)
    {
      public void call()
      {
        this.val$composite.removeOnClickListener(this.val$listener);
      }
    });
    localCompositeOnClickListener.addOnClickListener(local1);
    paramSubscriber.add(localSubscription);
  }

  private static class CachedListeners
  {
    private static final Map<AdapterView<?>, OnSubscribeAdapterViewOnItemClick.CompositeOnClickListener> sCachedListeners = new WeakHashMap();

    public static OnSubscribeAdapterViewOnItemClick.CompositeOnClickListener getFromViewOrCreate(AdapterView<?> paramAdapterView)
    {
      Object localObject = (OnSubscribeAdapterViewOnItemClick.CompositeOnClickListener)sCachedListeners.get(paramAdapterView);
      if (localObject != null);
      while (true)
      {
        return localObject;
        OnSubscribeAdapterViewOnItemClick.CompositeOnClickListener localCompositeOnClickListener = new OnSubscribeAdapterViewOnItemClick.CompositeOnClickListener(null);
        sCachedListeners.put(paramAdapterView, localCompositeOnClickListener);
        paramAdapterView.setOnItemClickListener(localCompositeOnClickListener);
        localObject = localCompositeOnClickListener;
      }
    }
  }

  private static class CompositeOnClickListener
    implements AdapterView.OnItemClickListener
  {
    private final List<AdapterView.OnItemClickListener> listeners = new ArrayList();

    public boolean addOnClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
    {
      return this.listeners.add(paramOnItemClickListener);
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      Iterator localIterator = this.listeners.iterator();
      while (localIterator.hasNext())
        ((AdapterView.OnItemClickListener)localIterator.next()).onItemClick(paramAdapterView, paramView, paramInt, paramLong);
    }

    public boolean removeOnClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
    {
      return this.listeners.remove(paramOnItemClickListener);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.OnSubscribeAdapterViewOnItemClick
 * JD-Core Version:    0.6.0
 */