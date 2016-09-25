package com.miui.tsmclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.ui.quick.DoubleClickActivity;
import com.miui.tsmclient.util.LogUtils;

public class HciEventReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    LogUtils.d("receive hci event, action = " + str);
    if (TextUtils.equals(str, "com.android.nfc_extras.action.RF_FIELD_ON_DETECTED"))
    {
      Intent localIntent = new Intent(paramContext, DoubleClickActivity.class);
      Bundle localBundle = paramIntent.getExtras();
      if (localBundle != null)
        localIntent.putExtras(localBundle);
      localIntent.addFlags(268435456);
      localIntent.addFlags(536870912);
      paramContext.startActivity(localIntent);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.HciEventReceiver
 * JD-Core Version:    0.6.0
 */