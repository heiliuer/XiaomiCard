package rx.android.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

class OnSubscribeBroadcastRegister
  implements Observable.OnSubscribe<Intent>
{
  private final String broadcastPermission;
  private final Context context;
  private final IntentFilter intentFilter;
  private final Handler schedulerHandler;

  public OnSubscribeBroadcastRegister(Context paramContext, IntentFilter paramIntentFilter, String paramString, Handler paramHandler)
  {
    this.context = paramContext;
    this.intentFilter = paramIntentFilter;
    this.broadcastPermission = paramString;
    this.schedulerHandler = paramHandler;
  }

  public void call(Subscriber<? super Intent> paramSubscriber)
  {
    1 local1 = new BroadcastReceiver(paramSubscriber)
    {
      public void onReceive(Context paramContext, Intent paramIntent)
      {
        this.val$subscriber.onNext(paramIntent);
      }
    };
    paramSubscriber.add(Subscriptions.create(new Action0(local1)
    {
      public void call()
      {
        OnSubscribeBroadcastRegister.this.context.unregisterReceiver(this.val$broadcastReceiver);
      }
    }));
    this.context.registerReceiver(local1, this.intentFilter, this.broadcastPermission, this.schedulerHandler);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.content.OnSubscribeBroadcastRegister
 * JD-Core Version:    0.6.0
 */