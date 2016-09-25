package com.miui.tsmclient.ui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.tsmclient.util.StringUtils;
import com.tsmclient.smartcard.model.TradeLog;

public class PurchaseRecordItem extends RelativeLayout
{
  private Context mContext;
  private TextView mTvPurchaseTime;
  private TextView mTvRechargeAmount;
  private TextView mTvTradeType;

  public PurchaseRecordItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public void bindData(TradeLog paramTradeLog)
  {
    if (paramTradeLog == null);
    while (true)
    {
      return;
      if (paramTradeLog.getTradeType() == 1)
      {
        int j = this.mContext.getResources().getColor(2131165245);
        this.mTvRechargeAmount.setTextColor(j);
        this.mTvTradeType.setText(2131296513);
        TextView localTextView2 = this.mTvRechargeAmount;
        Resources localResources2 = getResources();
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Float.valueOf(paramTradeLog.getAuAmount() / 100.0F);
        localTextView2.setText(localResources2.getString(2131296408, arrayOfObject2));
      }
      while (true)
      {
        if (paramTradeLog.getTradeDate() == null)
          break label233;
        if (paramTradeLog.getTradeDate().length() <= 4)
          break label235;
        this.mTvPurchaseTime.setText(StringUtils.formatDateFromString(paramTradeLog.getTradeDate() + paramTradeLog.getTradeTime(), "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss"));
        break;
        if (paramTradeLog.getTradeType() != 2)
          continue;
        int i = this.mContext.getResources().getColor(2131165246);
        this.mTvRechargeAmount.setTextColor(i);
        this.mTvTradeType.setText(2131296512);
        TextView localTextView1 = this.mTvRechargeAmount;
        Resources localResources1 = getResources();
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Float.valueOf(paramTradeLog.getAuAmount() / 100.0F);
        localTextView1.setText(localResources1.getString(2131296407, arrayOfObject1));
      }
      label233: continue;
      label235: if (paramTradeLog.getTradeDate().length() != 4)
        continue;
      this.mTvPurchaseTime.setText(StringUtils.formatDateFromString(paramTradeLog.getTradeDate() + paramTradeLog.getTradeTime(), "MMddHHmmss", "MM/dd HH:mm:ss"));
    }
  }

  public void initView()
  {
    this.mTvRechargeAmount = ((TextView)findViewById(2131492929));
    this.mTvTradeType = ((TextView)findViewById(2131492931));
    this.mTvPurchaseTime = ((TextView)findViewById(2131492930));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.PurchaseRecordItem
 * JD-Core Version:    0.6.0
 */