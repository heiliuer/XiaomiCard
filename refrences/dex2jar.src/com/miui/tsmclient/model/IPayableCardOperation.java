package com.miui.tsmclient.model;

import android.content.Context;
import android.nfc.Tag;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.pay.OrderInfo;

public abstract interface IPayableCardOperation<T extends PayableCardInfo> extends ICardOperation<T>
{
  public abstract BaseResponse recharge(Context paramContext, T paramT, OrderInfo paramOrderInfo, Tag paramTag);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.IPayableCardOperation
 * JD-Core Version:    0.6.0
 */