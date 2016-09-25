package com.miui.tsmclient.ui;

import android.content.Intent;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;
import miui.app.Fragment;

public class CardIntroActivity extends Activity
{
  public static final int REQUEST_CODE_CARD_PRE_ISSUE = 1;
  public static final int REQUEST_CODE_RECHARGE = 3;
  public static final int REQUEST_CODE_RESULT = 2;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    if ((localBundle != null) && ((CardInfo)localBundle.getParcelable("card_info") == null))
      localBundle.putParcelable("card_info", CardInfoFactory.makeCardInfo(localBundle.getString("card_info"), null));
    CardIntroFragment localCardIntroFragment = new CardIntroFragment();
    localCardIntroFragment.setArguments(localBundle);
    UiUtils.replaceFragment(this, localCardIntroFragment, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardIntroActivity
 * JD-Core Version:    0.6.0
 */