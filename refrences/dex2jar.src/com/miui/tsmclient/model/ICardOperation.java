package com.miui.tsmclient.model;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.IOperation;
import com.miui.tsmclient.entity.CardInfo;

public abstract interface ICardOperation<T extends CardInfo> extends IOperation
{
  public abstract BaseResponse deleteCard(Context paramContext, T paramT, Bundle paramBundle);

  public abstract BaseResponse issue(Context paramContext, T paramT, Bundle paramBundle);

  public abstract BaseResponse queryCardInfo(Context paramContext, T paramT, Bundle paramBundle);

  public abstract BaseResponse queryPurchaseRecord(Context paramContext, T paramT);

  public abstract BaseResponse updateCardInfo(Context paramContext, T paramT);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.ICardOperation
 * JD-Core Version:    0.6.0
 */