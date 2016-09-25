package rx.android.content;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

class OnSubscribeSharedPreferenceChange
  implements Observable.OnSubscribe<String>
{
  private final SharedPreferences sharedPreferences;

  public OnSubscribeSharedPreferenceChange(SharedPreferences paramSharedPreferences)
  {
    this.sharedPreferences = paramSharedPreferences;
  }

  public void call(Subscriber<? super String> paramSubscriber)
  {
    1 local1 = new SharedPreferences.OnSharedPreferenceChangeListener(paramSubscriber)
    {
      public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
      {
        this.val$subscriber.onNext(paramString);
      }
    };
    paramSubscriber.add(Subscriptions.create(new Action0(local1)
    {
      public void call()
      {
        OnSubscribeSharedPreferenceChange.this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this.val$listener);
      }
    }));
    this.sharedPreferences.registerOnSharedPreferenceChangeListener(local1);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.content.OnSubscribeSharedPreferenceChange
 * JD-Core Version:    0.6.0
 */