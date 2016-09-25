package com.miui.tsmclient.ui;

import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;

public class IssuedTransCardListActivity extends Activity
{
  public static final int REQUEST_CODE_SET_DEFAULT_CARD = 1;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    IssuedTransCardListFragment localIssuedTransCardListFragment = new IssuedTransCardListFragment();
    localIssuedTransCardListFragment.setArguments(getIntent().getExtras());
    UiUtils.replaceFragment(this, localIssuedTransCardListFragment, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.IssuedTransCardListActivity
 * JD-Core Version:    0.6.0
 */