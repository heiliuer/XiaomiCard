package com.miui.tsmclient.pay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.mipay.sdk.Mipay;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.util.LogUtils;
import java.util.HashMap;
import java.util.Map;

public class MiPayTool
  implements IPayTool
{
  private IPayRule mPayRule = new MipayPayRule();

  public IPayRule getPayRule()
  {
    return this.mPayRule;
  }

  public int parsePayResult(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    int i = paramBundle.getInt("code");
    String str = paramBundle.getString("message");
    LogUtils.d("pay result:" + i + ",msg:" + str);
    AnalyticManager.getInstance().trackPluginPayStatus(paramContext, paramCardInfo.mCardType, paramCardInfo.mHasIssue, "mipay", String.valueOf(i), null);
    HashMap localHashMap = new HashMap();
    localHashMap.put("card_type", paramCardInfo.mCardType);
    localHashMap.put("pay_result", String.valueOf(i));
    AnalyticManager.recordEvent("pay", "pay_result", localHashMap);
    return i;
  }

  public int pay(Activity paramActivity, String paramString, Bundle paramBundle)
  {
    Mipay.get(paramActivity).pay(paramActivity, paramString, paramBundle);
    return 0;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.pay.MiPayTool
 * JD-Core Version:    0.6.0
 */