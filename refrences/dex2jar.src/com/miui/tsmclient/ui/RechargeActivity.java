package com.miui.tsmclient.ui;

import android.app.ActionBar.LayoutParams;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.ui.widget.ImmersionMenu;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;
import miui.app.Fragment;

public class RechargeActivity extends Activity
{
  public static final int REQUEST_CODE_NFC_RECHARGE = 1;
  public static final int REQUEST_CODE_RESULT = 2;
  public static final int REQUEST_CODE_SELECT_LOCATION = 4;
  public static final int REQUEST_ISSUE = 3;
  public static final String TAG_ORDER_CHIEF_INFO = "order_chief_info";
  private BaseFragment mFragment;
  private ImmersionMenu mImmersionMenu;

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 424)
      ((Fragment)getFragmentManager().findFragmentByTag(this.mFragment.getClass().getName())).onActivityResult(paramInt1, paramInt2, paramIntent);
    while (true)
    {
      return;
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle = getIntent().getExtras();
    CardInfo localCardInfo = (CardInfo)localBundle.getParcelable("card_info");
    Object localObject2;
    if (localCardInfo != null)
      if ((TextUtils.equals(localCardInfo.mCardType, "LNT")) && (!localBundle.containsKey("cityId")))
      {
        if (localCardInfo.mHasIssue)
        {
          localObject2 = new RechargeFragment();
          this.mFragment = ((BaseFragment)localObject2);
        }
      }
      else
        label70: if (this.mFragment == null)
          if (!localCardInfo.mHasIssue)
            break label227;
    label227: for (Object localObject1 = new RechargeFragment(); ; localObject1 = new IssueRechargeFragment())
    {
      this.mFragment = ((BaseFragment)localObject1);
      this.mFragment.setArguments(localBundle);
      UiUtils.replaceFragment(this, this.mFragment, false);
      miui.app.ActionBar localActionBar = getActionBar();
      if (localActionBar != null)
      {
        this.mImmersionMenu = ((ImmersionMenu)getLayoutInflater().inflate(2130903041, null));
        this.mImmersionMenu.setVisibility(8);
        localActionBar.setCustomView(this.mImmersionMenu, new ActionBar.LayoutParams(-2, -2, 5));
      }
      return;
      localObject2 = new LntRechargeFragment();
      break;
      String str = localBundle.getString("card_info");
      localCardInfo = CardInfoFactory.makeCardInfo(str, null);
      if (!TextUtils.equals(str, "SPTC"))
        break label70;
      localCardInfo.mHasIssue = true;
      localBundle.putParcelable("card_info", localCardInfo);
      break label70;
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
 * Qualified Name:     com.miui.tsmclient.ui.RechargeActivity
 * JD-Core Version:    0.6.0
 */