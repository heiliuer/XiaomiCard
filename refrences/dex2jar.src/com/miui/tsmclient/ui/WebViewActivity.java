package com.miui.tsmclient.ui;

import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Fragment;

public class WebViewActivity extends miui.app.Activity
{
  public static void showWebPage(android.app.Activity paramActivity, String paramString)
  {
    showWebPage(paramActivity, paramString, null);
  }

  public static void showWebPage(android.app.Activity paramActivity, String paramString, CardInfo paramCardInfo)
  {
    Intent localIntent = new Intent(paramActivity, WebViewActivity.class);
    localIntent.putExtra("url", paramString);
    localIntent.putExtra("card_info", paramCardInfo);
    paramActivity.startActivity(localIntent);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    WebViewFragment localWebViewFragment = new WebViewFragment();
    localWebViewFragment.setArguments(localBundle);
    UiUtils.replaceFragment(this, localWebViewFragment, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.WebViewActivity
 * JD-Core Version:    0.6.0
 */