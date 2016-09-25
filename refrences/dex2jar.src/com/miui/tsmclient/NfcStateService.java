package com.miui.tsmclient;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;
import com.miui.tsmclient.util.SysUtils;

public class NfcStateService extends IntentService
{
  public static final String ACTION_QUERY_CARD_STATE = "com.miui.action.QUERY_CARD_STATE";
  private static final String TAG = "NfcStateService";

  public NfcStateService()
  {
    super("NfcStateService");
  }

  public static void queryCardState(Context paramContext)
  {
    Intent localIntent = new Intent("com.miui.action.QUERY_CARD_STATE");
    localIntent.setPackage(paramContext.getPackageName());
    if (paramContext != null)
      paramContext.startService(localIntent);
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    LogUtils.d("NfcStateService onHandleIntent");
    if (!paramIntent.getAction().equals("com.miui.action.QUERY_CARD_STATE"));
    while (true)
    {
      return;
      if (!PrefUtils.getBoolean(getApplicationContext(), "key_set_press_volume_down_already", false))
      {
        String str1 = SysUtils.getDefaultTransCard(getApplicationContext());
        String str2 = PrefUtils.getLongPressVolumeDownState(getApplicationContext());
        LogUtils.i("onHandleIntent defaultTransCard:" + str1 + ", longPressState:" + str2);
        if ((!TextUtils.isEmpty(str1)) && (str2 != "Street-snap"))
          PrefUtils.setLongPressVolumeDownStateToPay(getApplicationContext());
        PrefUtils.putBoolean(getApplicationContext(), "key_set_press_volume_down_already", true);
        continue;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.NfcStateService
 * JD-Core Version:    0.6.0
 */