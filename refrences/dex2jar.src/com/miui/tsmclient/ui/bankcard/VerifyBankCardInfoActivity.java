package com.miui.tsmclient.ui.bankcard;

import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;

public class VerifyBankCardInfoActivity extends Activity
{
  public static final int REQUEST_TRANS_ELEMENT;

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
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
    VerifyBankCardInfoFragment localVerifyBankCardInfoFragment = new VerifyBankCardInfoFragment();
    localVerifyBankCardInfoFragment.setArguments(getIntent().getExtras());
    UiUtils.replaceFragment(this, localVerifyBankCardInfoFragment, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.bankcard.VerifyBankCardInfoActivity
 * JD-Core Version:    0.6.0
 */