package com.miui.tsmclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;
import miui.app.Fragment;

public class CardPurchaseRecordActivity extends Activity
{
  public static final String TAG_CONTENT_TYPE = "content_type";
  public static final String TAG_RECORDS = "records";
  public static final String UNSOLVED_ORDER = "unsolved_order";
  private Fragment mFragment;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    Object localObject;
    if (localBundle == null)
    {
      localObject = null;
      if (!TextUtils.equals((CharSequence)localObject, "unsolved_order"))
        break label72;
    }
    label72: for (this.mFragment = new UnsolvedRecordFragment(); ; this.mFragment = new CardPurchaseRecordFragment())
    {
      this.mFragment.setArguments(localBundle);
      setImmersionMenuEnabled(true);
      UiUtils.replaceFragment(this, this.mFragment, false);
      return;
      localObject = localBundle.getString("content_type");
      break;
    }
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
 * Qualified Name:     com.miui.tsmclient.ui.CardPurchaseRecordActivity
 * JD-Core Version:    0.6.0
 */