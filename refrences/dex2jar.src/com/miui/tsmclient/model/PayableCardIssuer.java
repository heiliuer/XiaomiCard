package com.miui.tsmclient.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.ActionToken.TokenType;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.ui.CardIntroFragment;
import com.miui.tsmclient.ui.RechargeActivity;
import java.util.Iterator;
import java.util.List;

public class PayableCardIssuer extends Issuer<PayableCardInfo>
{
  protected PayableCardManager mCardManager;
  private String mCityId;
  private String mSourceChannel;

  protected Bundle buildIssueParams()
  {
    Bundle localBundle1 = super.buildIssueParams();
    if ((this.mCardInfo == null) || (((PayableCardInfo)this.mCardInfo).mUnfinishOrderInfos == null));
    for (Bundle localBundle2 = null; ; localBundle2 = localBundle1)
    {
      return localBundle2;
      if (localBundle1 == null)
        localBundle1 = new Bundle();
      Iterator localIterator1 = ((PayableCardInfo)this.mCardInfo).mUnfinishOrderInfos.iterator();
      while (true)
      {
        if (!localIterator1.hasNext())
          break label132;
        Iterator localIterator2 = ((OrderInfo)localIterator1.next()).mActionTokens.iterator();
        if (!localIterator2.hasNext())
          continue;
        ActionToken localActionToken = (ActionToken)localIterator2.next();
        if (localActionToken.mType != ActionToken.TokenType.issue)
          break;
        localBundle1.putString("authentication_code", localActionToken.mToken);
      }
      label132: if (!TextUtils.isEmpty(this.mCityId))
        localBundle1.putString("extra_city_id", this.mCityId);
      if (TextUtils.isEmpty(this.mSourceChannel))
        continue;
      localBundle1.putString("extra_source_channel", this.mSourceChannel);
    }
  }

  protected void init(CardIntroFragment paramCardIntroFragment, PayableCardInfo paramPayableCardInfo, Bundle paramBundle)
  {
    super.init(paramCardIntroFragment, paramPayableCardInfo, paramBundle);
    this.mCardManager = new PayableCardManager(this.mFragment.getActivity().getApplicationContext());
  }

  public void onPreIssueFinished(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      this.mCityId = paramIntent.getStringExtra("extra_city_id");
      this.mCardInfo = ((CardInfo)paramIntent.getParcelableExtra("card_info"));
      this.mSourceChannel = paramIntent.getStringExtra("extra_source_channel");
    }
  }

  public void preIssue()
  {
    super.preIssue();
    Intent localIntent = new Intent(this.mFragment.getActivity(), RechargeActivity.class);
    Bundle localBundle = this.mFragment.getArguments();
    if (localBundle != null)
      localBundle.putParcelable("card_info", this.mCardInfo);
    localIntent.putExtras(localBundle);
    this.mFragment.startActivityForResult(localIntent, 1);
  }

  public void release()
  {
    synchronized (this.mCardManager)
    {
      this.mCardManager.release();
      super.release();
      return;
    }
  }

  public boolean shouldAutoIssue()
  {
    int i;
    if ((this.mCardInfo == null) || (((PayableCardInfo)this.mCardInfo).mUnfinishOrderInfos == null))
      i = 0;
    while (true)
    {
      return i;
      while (true)
      {
        label39: Iterator localIterator1 = ((PayableCardInfo)this.mCardInfo).mUnfinishOrderInfos.iterator();
        while (true)
          if (localIterator1.hasNext())
          {
            OrderInfo localOrderInfo = (OrderInfo)localIterator1.next();
            Iterator localIterator2 = localOrderInfo.mActionTokens.iterator();
            if (!localIterator2.hasNext())
              continue;
            if (((ActionToken)localIterator2.next()).mType != ActionToken.TokenType.issue)
              break label39;
            this.mCityId = localOrderInfo.mCityId;
            i = 1;
            break;
          }
      }
      i = 0;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.PayableCardIssuer
 * JD-Core Version:    0.6.0
 */