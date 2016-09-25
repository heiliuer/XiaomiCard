package com.miui.tsmclient.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.tsmclient.pay.OrderInfo;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UnsolvedRecordItem extends LinearLayout
{
  private TextView mTvPurchaseTime;
  private TextView mTvTradeAmount;

  public UnsolvedRecordItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void bindData(OrderInfo paramOrderInfo)
  {
    if (paramOrderInfo == null);
    while (true)
    {
      return;
      TextView localTextView = this.mTvTradeAmount;
      Context localContext = getContext();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramOrderInfo.mPayFee / 100);
      localTextView.setText(localContext.getString(2131296415, arrayOfObject));
      Date localDate = new Date(1000L * paramOrderInfo.mPayTime);
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      this.mTvPurchaseTime.setText(localSimpleDateFormat.format(localDate));
    }
  }

  public void initView()
  {
    this.mTvTradeAmount = ((TextView)findViewById(2131492934));
    this.mTvPurchaseTime = ((TextView)findViewById(2131492930));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.UnsolvedRecordItem
 * JD-Core Version:    0.6.0
 */