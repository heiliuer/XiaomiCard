package rx.android.view;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;

final class OnSubscribeViewDetachedFromWindowFirst
  implements Observable.OnSubscribe<View>
{
  private final View view;

  public OnSubscribeViewDetachedFromWindowFirst(View paramView)
  {
    this.view = paramView;
  }

  public void call(Subscriber<? super View> paramSubscriber)
  {
    SubscriptionAdapter localSubscriptionAdapter = new SubscriptionAdapter(paramSubscriber, this.view);
    paramSubscriber.add(localSubscriptionAdapter);
    this.view.addOnAttachStateChangeListener(localSubscriptionAdapter);
  }

  private static class SubscriptionAdapter
    implements View.OnAttachStateChangeListener, Subscription
  {
    private Subscriber<? super View> subscriber;
    private View view;

    public SubscriptionAdapter(Subscriber<? super View> paramSubscriber, View paramView)
    {
      this.subscriber = paramSubscriber;
      this.view = paramView;
    }

    public boolean isUnsubscribed()
    {
      if (this.view == null);
      for (int i = 1; ; i = 0)
        return i;
    }

    public void onViewAttachedToWindow(View paramView)
    {
    }

    public void onViewDetachedFromWindow(View paramView)
    {
      if (!isUnsubscribed())
      {
        Subscriber localSubscriber = this.subscriber;
        unsubscribe();
        localSubscriber.onNext(paramView);
        localSubscriber.onCompleted();
      }
    }

    public void unsubscribe()
    {
      if (!isUnsubscribed())
      {
        this.view.removeOnAttachStateChangeListener(this);
        this.view = null;
        this.subscriber = null;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.view.OnSubscribeViewDetachedFromWindowFirst
 * JD-Core Version:    0.6.0
 */