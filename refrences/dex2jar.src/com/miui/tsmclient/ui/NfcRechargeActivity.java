package com.miui.tsmclient.ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;
import miui.app.Fragment;

public class NfcRechargeActivity extends Activity
{
  public static final int REQUEST_CODE_NFC_RECHARGE = 1;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    NfcRechargeFragment localNfcRechargeFragment = new NfcRechargeFragment();
    localNfcRechargeFragment.setArguments(localBundle);
    UiUtils.replaceFragment(this, localNfcRechargeFragment, false);
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    ((NfcRechargeFragment)getFragmentManager().findFragmentByTag(NfcRechargeFragment.class.getName())).doNewActivityIntent(paramIntent);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.NfcRechargeActivity
 * JD-Core Version:    0.6.0
 */