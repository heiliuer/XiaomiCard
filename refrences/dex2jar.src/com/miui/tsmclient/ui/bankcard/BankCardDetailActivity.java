package com.miui.tsmclient.ui.bankcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;
import miui.app.Fragment;

public class BankCardDetailActivity extends Activity
{
  private Fragment mFragment;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mFragment = new BankCardDetailFragment();
    this.mFragment.setArguments(getIntent().getExtras());
    UiUtils.replaceFragment(this, this.mFragment, false);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return this.mFragment.onCreateOptionsMenu(paramMenu);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return this.mFragment.onOptionsItemSelected(paramMenuItem);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.bankcard.BankCardDetailActivity
 * JD-Core Version:    0.6.0
 */