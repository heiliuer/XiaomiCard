package com.miui.tsmclient.ui;

import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;
import miui.app.Fragment;

public class SendSmsActivity extends Activity
{
  public static final int REQUEST_CODE_SEND_SMS_CODE;

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      setResult(paramInt2, paramIntent);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    SendSmsFragment localSendSmsFragment = new SendSmsFragment();
    localSendSmsFragment.setArguments(getIntent().getExtras());
    UiUtils.replaceFragment(this, localSendSmsFragment, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.SendSmsActivity
 * JD-Core Version:    0.6.0
 */