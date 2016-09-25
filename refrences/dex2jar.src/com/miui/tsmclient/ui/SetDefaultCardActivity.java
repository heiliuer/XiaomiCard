package com.miui.tsmclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;

public class SetDefaultCardActivity extends Activity
{
  private SetDefaultCardFragment mFragment;
  private String mInitDefaultCardAId;

  public void finish()
  {
    int i = 0;
    Object localObject;
    if (this.mFragment == null)
    {
      localObject = null;
      if (TextUtils.equals((CharSequence)localObject, this.mInitDefaultCardAId))
        break label69;
    }
    label69: for (int j = 1; ; j = 0)
    {
      Intent localIntent = getIntent();
      localIntent.putExtra("tag_default_card_aid", (String)localObject);
      if (j != 0)
        i = -1;
      setResult(i, localIntent);
      super.finish();
      return;
      localObject = this.mFragment.mDefaultCardAId;
      break;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
      this.mInitDefaultCardAId = localBundle.getString("tag_default_card_aid");
    this.mFragment = new SetDefaultCardFragment();
    this.mFragment.setArguments(getIntent().getExtras());
    UiUtils.replaceFragment(this, this.mFragment, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.SetDefaultCardActivity
 * JD-Core Version:    0.6.0
 */