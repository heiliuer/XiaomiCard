package com.miui.tsmclient.ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;
import miui.app.Fragment;

public class TSMResultActivity extends Activity
{
  public void onBackPressed()
  {
    ((TSMResultFragment)getFragmentManager().findFragmentByTag(TSMResultFragment.class.getName())).onBackPressed();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    TSMResultFragment localTSMResultFragment = new TSMResultFragment();
    localTSMResultFragment.setArguments(localBundle);
    UiUtils.replaceFragment(this, localTSMResultFragment, false);
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    ((TSMResultFragment)getFragmentManager().findFragmentByTag(TSMResultFragment.class.getName())).doNewActivityIntent(paramIntent);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.TSMResultActivity
 * JD-Core Version:    0.6.0
 */