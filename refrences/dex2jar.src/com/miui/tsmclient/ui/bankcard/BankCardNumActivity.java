package com.miui.tsmclient.ui.bankcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;

public class BankCardNumActivity extends Activity
{
  public static final int REQUEST_INPUT_BANK_CARD_NUM;
  private BankCardNumFragment mFragment;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().addFlags(4194304);
    this.mFragment = new BankCardNumFragment();
    this.mFragment.setArguments(getIntent().getExtras());
    setImmersionMenuEnabled(true);
    UiUtils.replaceFragment(this, this.mFragment, false);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return this.mFragment.onCreateOptionsMenu(paramMenu);
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    this.mFragment.doNewIntent(paramIntent);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return this.mFragment.onOptionsItemSelected(paramMenuItem);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.bankcard.BankCardNumActivity
 * JD-Core Version:    0.6.0
 */