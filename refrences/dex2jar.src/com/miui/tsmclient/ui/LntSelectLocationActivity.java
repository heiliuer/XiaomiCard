package com.miui.tsmclient.ui;

import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;

public class LntSelectLocationActivity extends Activity
{
  private LntSelectLocationFragment mFragment;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mFragment = new LntSelectLocationFragment();
    this.mFragment.setArguments(getIntent().getExtras());
    UiUtils.replaceFragment(this, this.mFragment, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.LntSelectLocationActivity
 * JD-Core Version:    0.6.0
 */