package com.miui.tsmclient.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tsmclient.smartcard.model.TradeLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PurchaseRecordAdapter extends ArrayAdapter<TradeLog>
{
  private boolean isOnlyDisplayRecharge;
  private List<TradeLog> mRechargeTradeLogList;
  private List<TradeLog> mTradeLogList;

  public PurchaseRecordAdapter(Context paramContext)
  {
    super(paramContext);
  }

  public void bindData(View paramView, int paramInt, TradeLog paramTradeLog)
  {
    ((PurchaseRecordItem)paramView).bindData(paramTradeLog);
  }

  public View newView(Context paramContext, TradeLog paramTradeLog, ViewGroup paramViewGroup)
  {
    PurchaseRecordItem localPurchaseRecordItem = (PurchaseRecordItem)LayoutInflater.from(this.mContext).inflate(2130903054, null);
    localPurchaseRecordItem.initView();
    return localPurchaseRecordItem;
  }

  public void setOnlyDisplayRecharge(boolean paramBoolean)
  {
    if (this.isOnlyDisplayRecharge != paramBoolean)
    {
      this.isOnlyDisplayRecharge = paramBoolean;
      if (!paramBoolean)
        break label28;
    }
    label28: for (List localList = this.mRechargeTradeLogList; ; localList = this.mTradeLogList)
    {
      super.updateData(localList);
      return;
    }
  }

  public void updateData(List<TradeLog> paramList)
  {
    if (paramList == null)
      paramList = Collections.emptyList();
    this.mTradeLogList = paramList;
    this.mRechargeTradeLogList = new ArrayList();
    Iterator localIterator = this.mTradeLogList.iterator();
    while (localIterator.hasNext())
    {
      TradeLog localTradeLog = (TradeLog)localIterator.next();
      if (localTradeLog.getTradeType() != 2)
        continue;
      this.mRechargeTradeLogList.add(localTradeLog);
    }
    if (this.isOnlyDisplayRecharge);
    for (List localList = this.mRechargeTradeLogList; ; localList = this.mTradeLogList)
    {
      super.updateData(localList);
      return;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.PurchaseRecordAdapter
 * JD-Core Version:    0.6.0
 */