package rx.android.widget;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.AndroidSubscriptions;
import rx.android.internal.Assertions;
import rx.functions.Action0;

class OnSubscribeListViewScroll
  implements Observable.OnSubscribe<OnListViewScrollEvent>
{
  private final AbsListView listView;

  public OnSubscribeListViewScroll(AbsListView paramAbsListView)
  {
    this.listView = paramAbsListView;
  }

  public void call(Subscriber<? super OnListViewScrollEvent> paramSubscriber)
  {
    Assertions.assertUiThread();
    CompositeOnScrollListener localCompositeOnScrollListener = CachedListeners.getFromViewOrCreate(this.listView);
    1 local1 = new AbsListView.OnScrollListener(paramSubscriber)
    {
      int currentScrollState = 0;

      public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
      {
        OnListViewScrollEvent localOnListViewScrollEvent = OnListViewScrollEvent.create(paramAbsListView, this.currentScrollState, paramInt1, paramInt2, paramInt3);
        this.val$observer.onNext(localOnListViewScrollEvent);
      }

      public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
      {
        this.currentScrollState = paramInt;
      }
    };
    localCompositeOnScrollListener.addOnScrollListener(local1);
    paramSubscriber.add(AndroidSubscriptions.unsubscribeInUiThread(new Action0(localCompositeOnScrollListener, local1)
    {
      public void call()
      {
        this.val$composite.removeOnScrollListener(this.val$listener);
      }
    }));
  }

  private static class CachedListeners
  {
    private static final Map<AdapterView<?>, OnSubscribeListViewScroll.CompositeOnScrollListener> sCachedListeners = new WeakHashMap();

    public static OnSubscribeListViewScroll.CompositeOnScrollListener getFromViewOrCreate(AbsListView paramAbsListView)
    {
      Object localObject = (OnSubscribeListViewScroll.CompositeOnScrollListener)sCachedListeners.get(paramAbsListView);
      if (localObject != null);
      while (true)
      {
        return localObject;
        OnSubscribeListViewScroll.CompositeOnScrollListener localCompositeOnScrollListener = new OnSubscribeListViewScroll.CompositeOnScrollListener(null);
        sCachedListeners.put(paramAbsListView, localCompositeOnScrollListener);
        paramAbsListView.setOnScrollListener(localCompositeOnScrollListener);
        localObject = localCompositeOnScrollListener;
      }
    }
  }

  private static class CompositeOnScrollListener
    implements AbsListView.OnScrollListener
  {
    private final List<AbsListView.OnScrollListener> listeners = new ArrayList();

    public boolean addOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
    {
      return this.listeners.add(paramOnScrollListener);
    }

    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
    {
      Iterator localIterator = this.listeners.iterator();
      while (localIterator.hasNext())
        ((AbsListView.OnScrollListener)localIterator.next()).onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
    }

    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
      Iterator localIterator = this.listeners.iterator();
      while (localIterator.hasNext())
        ((AbsListView.OnScrollListener)localIterator.next()).onScrollStateChanged(paramAbsListView, paramInt);
    }

    public boolean removeOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
    {
      return this.listeners.remove(paramOnScrollListener);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.OnSubscribeListViewScroll
 * JD-Core Version:    0.6.0
 */