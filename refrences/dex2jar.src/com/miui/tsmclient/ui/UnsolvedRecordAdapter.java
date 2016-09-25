package com.miui.tsmclient.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.tsmclient.pay.OrderInfo;

public class UnsolvedRecordAdapter extends ArrayAdapter<OrderInfo>
{
  public UnsolvedRecordAdapter(Context paramContext)
  {
    super(paramContext);
  }

  public void bindData(View paramView, int paramInt, OrderInfo paramOrderInfo)
  {
    ((UnsolvedRecordItem)paramView).bindData(paramOrderInfo);
  }

  public View newView(Context paramContext, OrderInfo paramOrderInfo, ViewGroup paramViewGroup)
  {
    UnsolvedRecordItem localUnsolvedRecordItem = (UnsolvedRecordItem)LayoutInflater.from(this.mContext).inflate(2130903056, null);
    localUnsolvedRecordItem.initView();
    return localUnsolvedRecordItem;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.UnsolvedRecordAdapter
 * JD-Core Version:    0.6.0
 */