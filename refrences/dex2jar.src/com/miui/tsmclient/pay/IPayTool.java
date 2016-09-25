package com.miui.tsmclient.pay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;

public abstract interface IPayTool
{
  public abstract IPayRule getPayRule();

  public abstract int parsePayResult(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle);

  public abstract int pay(Activity paramActivity, String paramString, Bundle paramBundle);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.pay.IPayTool
 * JD-Core Version:    0.6.0
 */