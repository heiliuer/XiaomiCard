package com.miui.tsmclient.model;

import android.content.Context;
import android.nfc.Tag;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;

public class PayableCardManager extends CardManager
{
  public PayableCardManager(Context paramContext)
  {
    super(paramContext);
  }

  public void recharge(Context paramContext, PayableCardInfo paramPayableCardInfo, OrderInfo paramOrderInfo, Tag paramTag, MiTsmCallback paramMiTsmCallback)
  {
    boolean bool;
    if ((paramPayableCardInfo == null) || (paramOrderInfo == null))
    {
      StringBuilder localStringBuilder = new StringBuilder().append(CardManager.class.getSimpleName()).append("recharge() param is invalid! info == null:");
      if (paramPayableCardInfo == null)
      {
        bool = true;
        LogUtils.d(bool);
        if (paramMiTsmCallback != null)
          paramMiTsmCallback.onFail(1, "", new Object[0]);
      }
    }
    while (true)
    {
      return;
      bool = false;
      break;
      IPayableCardOperation localIPayableCardOperation = (IPayableCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, paramPayableCardInfo.mCardType);
      CardExecutor.getInstance().execute(new Runnable(localIPayableCardOperation, paramContext, paramPayableCardInfo, paramOrderInfo, paramTag, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.recharge(this.val$context, this.val$cardInfo, this.val$orderInfo, this.val$tag);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.PayableCardManager
 * JD-Core Version:    0.6.0
 */