package com.miui.tsmclient.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsCaptchaBroadcastReceiver extends BroadcastReceiver
{
  public static final int PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
  public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
  private static final String TAG = "SmsCaptchaBroadcastReceiver";
  private SmsCaptchaMessageListener mSmsCaptchaMessageListener;

  public SmsCaptchaBroadcastReceiver(SmsCaptchaMessageListener paramSmsCaptchaMessageListener)
  {
    this.mSmsCaptchaMessageListener = paramSmsCaptchaMessageListener;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    Object[] arrayOfObject;
    int i;
    if (localBundle != null)
    {
      arrayOfObject = (Object[])(Object[])localBundle.get("pdus");
      i = arrayOfObject.length;
    }
    for (int j = 0; ; j++)
    {
      if (j < i)
      {
        String str = SmsMessage.createFromPdu((byte[])(byte[])arrayOfObject[j]).getMessageBody();
        Matcher localMatcher = Pattern.compile("(?<![0-9])([0-9]{6})(?![0-9])").matcher(str);
        if (!localMatcher.find())
          continue;
        this.mSmsCaptchaMessageListener.onReceived(localMatcher.group(0));
      }
      return;
    }
  }

  public static abstract interface SmsCaptchaMessageListener
  {
    public abstract void onReceived(String paramString);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.receiver.SmsCaptchaBroadcastReceiver
 * JD-Core Version:    0.6.0
 */