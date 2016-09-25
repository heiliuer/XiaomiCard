package com.miui.tsmclient.ui;

import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;
import miui.app.Fragment;

public class CardIssueListActivity extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    CardIssueListFragment localCardIssueListFragment = new CardIssueListFragment();
    localCardIssueListFragment.setArguments(localBundle);
    UiUtils.replaceFragment(this, localCardIssueListFragment, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardIssueListActivity
 * JD-Core Version:    0.6.0
 */