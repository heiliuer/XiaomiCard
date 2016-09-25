package rx.android.content;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;
import rx.Observable;

public final class ContentObservable
{
  private ContentObservable()
  {
    throw new AssertionError("No instances");
  }

  public static Observable<Intent> fromBroadcast(Context paramContext, IntentFilter paramIntentFilter)
  {
    return Observable.create(new OnSubscribeBroadcastRegister(paramContext, paramIntentFilter, null, null));
  }

  public static Observable<Intent> fromBroadcast(Context paramContext, IntentFilter paramIntentFilter, String paramString, Handler paramHandler)
  {
    return Observable.create(new OnSubscribeBroadcastRegister(paramContext, paramIntentFilter, paramString, paramHandler));
  }

  public static Observable<Cursor> fromCursor(Cursor paramCursor)
  {
    return Observable.create(new OnSubscribeCursor(paramCursor));
  }

  public static Observable<Intent> fromLocalBroadcast(Context paramContext, IntentFilter paramIntentFilter)
  {
    return Observable.create(new OnSubscribeLocalBroadcastRegister(paramContext, paramIntentFilter));
  }

  public static Observable<String> fromSharedPreferencesChanges(SharedPreferences paramSharedPreferences)
  {
    return Observable.create(new OnSubscribeSharedPreferenceChange(paramSharedPreferences));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.content.ContentObservable
 * JD-Core Version:    0.6.0
 */