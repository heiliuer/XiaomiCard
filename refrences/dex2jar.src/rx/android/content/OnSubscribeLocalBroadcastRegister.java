package rx.android.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

class OnSubscribeLocalBroadcastRegister
  implements Observable.OnSubscribe<Intent>
{
  private final Context context;
  private final IntentFilter intentFilter;

  public OnSubscribeLocalBroadcastRegister(Context paramContext, IntentFilter paramIntentFilter)
  {
    this.context = paramContext;
    this.intentFilter = paramIntentFilter;
  }

  public void call(Subscriber<? super Intent> paramSubscriber)
  {
    LocalBroadcastManager localLocalBroadcastManager = LocalBroadcastManager.getInstance(this.context);
    1 local1 = new BroadcastReceiver(paramSubscriber)
    {
      public void onReceive(Context paramContext, Intent paramIntent)
      {
        this.val$subscriber.onNext(paramIntent);
      }
    };
    paramSubscriber.add(Subscriptions.create(new Action0(localLocalBroadcastManager, local1)
    {
      public void call()
      {
        this.val$localBroadcastManager.unregisterReceiver(this.val$broadcastReceiver);
      }
    }));
    localLocalBroadcastManager.registerReceiver(local1, this.intentFilter);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.content.OnSubscribeLocalBroadcastRegister
 * JD-Core Version:    0.6.0
 */