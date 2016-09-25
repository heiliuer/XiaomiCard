package com.miui.tsmclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.entity.eventbus.DefaultCardEvent;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.UiUtils;
import de.greenrobot.event.EventBus;
import java.util.Map;
import miui.app.Activity;

public class CardDetailActivity extends Activity
{
  public static final int REQUEST_CODE_CARD_RECHARGE = 1;
  private String mCardType;
  private CardDetailFragment mFragment;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    CardInfo localCardInfo = (CardInfo)localBundle.getParcelable("card_info");
    if (localCardInfo == null);
    for (String str = null; ; str = localCardInfo.mCardType)
    {
      this.mCardType = str;
      setImmersionMenuEnabled(true);
      this.mFragment = new CardDetailFragment();
      this.mFragment.setArguments(localBundle);
      UiUtils.replaceFragment(this, this.mFragment, false);
      EventBus.getDefault().register(this);
      return;
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return this.mFragment.onCreateOptionsMenu(paramMenu);
  }

  protected void onDestroy()
  {
    EventBus.getDefault().unregister(this);
    super.onDestroy();
  }

  public void onEventMainThread(DefaultCardEvent paramDefaultCardEvent)
  {
    String str1 = paramDefaultCardEvent.getDefaultCardAId();
    Pair localPair = (Pair)SysUtils.getSupportedTransCardMap().get(str1);
    String str2;
    if (localPair == null)
    {
      str2 = null;
      LogUtils.d("CardDetailActivity onEventMainThread defaultCardAId:" + str1 + ", defaultCardType:" + str2 + ", mCardType:" + this.mCardType);
      if (!TextUtils.isEmpty(str2))
      {
        if (TextUtils.equals(this.mCardType, str2))
          break label163;
        this.mCardType = str2;
        CardInfo localCardInfo = CardInfoFactory.makeCardInfo(str2, null);
        Bundle localBundle = getIntent().getExtras();
        localBundle.putParcelable("card_info", localCardInfo);
        this.mFragment = new CardDetailFragment();
        this.mFragment.setArguments(localBundle);
        UiUtils.replaceFragment(this, this.mFragment, false);
      }
    }
    while (true)
    {
      return;
      str2 = (String)localPair.second;
      break;
      label163: if (!UiUtils.isFragmentValid(this.mFragment))
        continue;
      this.mFragment.setDefaultCard(str1);
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return this.mFragment.onOptionsItemSelected(paramMenuItem);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardDetailActivity
 * JD-Core Version:    0.6.0
 */