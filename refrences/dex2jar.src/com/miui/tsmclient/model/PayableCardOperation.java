package com.miui.tsmclient.model;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.ActionToken.TokenType;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.model.mitsm.AsyncMiTSMClient;
import com.miui.tsmclient.model.mitsm.MiTSMCardOperation;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.LogUtils;
import java.util.Iterator;
import java.util.List;

public class PayableCardOperation<T extends PayableCardClient> extends MiTSMCardOperation<PayableCardClient, PayableCardInfo>
  implements IPayableCardOperation<PayableCardInfo>
{
  public PayableCardOperation(String paramString)
  {
    if (TextUtils.equals(paramString, "HZT"));
    for (this.mMiTSMCardClient = new AsyncPayableCardClient(new AsyncMiTSMClient(this.mMiTSMCardClient)); ; this.mMiTSMCardClient = new PayableCardClient(this.mMiTSMCardClient))
      return;
  }

  public BaseResponse recharge(Context paramContext, PayableCardInfo paramPayableCardInfo, OrderInfo paramOrderInfo, Tag paramTag)
  {
    Bundle localBundle = new Bundle();
    if (!TextUtils.isEmpty(paramOrderInfo.mCityId))
      localBundle.putString("extra_city_id", paramOrderInfo.mCityId);
    BaseResponse localBaseResponse = ((PayableCardClient)this.mMiTSMCardClient).recharge(paramContext, paramPayableCardInfo, paramOrderInfo, paramTag, localBundle);
    if (localBaseResponse.mResultCode == 0)
      paramPayableCardInfo.mUnfinishOrderInfos.remove(paramOrderInfo);
    return localBaseResponse;
  }

  public BaseResponse updateCardInfo(Context paramContext, PayableCardInfo paramPayableCardInfo)
  {
    int i = 0;
    Object localObject1 = null;
    if ((paramPayableCardInfo.mHasIssue) && (paramPayableCardInfo.mUnfinishOrderInfos != null))
    {
      Iterator localIterator2 = paramPayableCardInfo.mUnfinishOrderInfos.iterator();
      while (localIterator2.hasNext())
      {
        OrderInfo localOrderInfo2 = (OrderInfo)localIterator2.next();
        Object localObject2 = null;
        Iterator localIterator3 = localOrderInfo2.mActionTokens.iterator();
        while (localIterator3.hasNext())
        {
          ActionToken localActionToken = (ActionToken)localIterator3.next();
          if (localActionToken.mType != ActionToken.TokenType.issue)
            continue;
          localObject1 = localOrderInfo2;
          localObject2 = localActionToken;
        }
        if (localObject1 == null)
          continue;
        LogUtils.d("card has not issue finished.");
        Bundle localBundle = new Bundle();
        localBundle.putString("authentication_code", localObject2.mToken);
        if (!TextUtils.isEmpty(localObject1.mCityId))
          localBundle.putString("extra_city_id", localObject1.mCityId);
        i = issue(paramContext, paramPayableCardInfo, localBundle).mResultCode;
      }
    }
    BaseResponse localBaseResponse;
    if (i == 0)
    {
      localBaseResponse = super.updateCardInfo(paramContext, paramPayableCardInfo);
      if ((localBaseResponse.mResultCode == 0) && (paramPayableCardInfo.mUnfinishOrderInfos != null))
      {
        Iterator localIterator1 = paramPayableCardInfo.mUnfinishOrderInfos.iterator();
        while (localIterator1.hasNext())
        {
          OrderInfo localOrderInfo1 = (OrderInfo)localIterator1.next();
          localBaseResponse = recharge(paramContext, paramPayableCardInfo, localOrderInfo1, null);
          if (localBaseResponse.mResultCode == 0)
            continue;
          LogUtils.d("handle unfinished order failed.error:" + localBaseResponse.mResultCode + ",card:" + paramPayableCardInfo.mCardType + ",order:" + localOrderInfo1.mOrderId);
          if (localBaseResponse.mResultCode == 1012)
            continue;
          if (localBaseResponse.mResultCode != 1004)
            break label345;
        }
        if (localBaseResponse.mResultCode != 0)
          break label356;
        localBaseResponse = super.updateCardInfo(paramContext, paramPayableCardInfo);
      }
    }
    while (true)
    {
      return localBaseResponse;
      label345: localBaseResponse.mResultCode = 1003;
      break;
      label356: super.updateCardInfo(paramContext, paramPayableCardInfo);
      continue;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramPayableCardInfo;
      localBaseResponse = new BaseResponse(1003, arrayOfObject);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.PayableCardOperation
 * JD-Core Version:    0.6.0
 */