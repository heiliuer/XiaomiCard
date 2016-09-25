package com.miui.tsmclient.ui.bankcard;

import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;
import miui.app.Fragment;

public class BindBankCardFinishedActivity extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    BindBankCardFinishedFragment localBindBankCardFinishedFragment = new BindBankCardFinishedFragment();
    localBindBankCardFinishedFragment.setArguments(getIntent().getExtras());
    UiUtils.replaceFragment(this, localBindBankCardFinishedFragment, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.bankcard.BindBankCardFinishedActivity
 * JD-Core Version:    0.6.0
 */