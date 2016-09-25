package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfo.Status;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.ICardOperation;
import com.tsmclient.smartcard.handler.SmartCardReader;

public class MiTSMCardOperation<T extends MiTSMCardClient, C extends CardInfo>
  implements ICardOperation<C>
{
  protected T mMiTSMCardClient = new MiTSMCardClient();

  public BaseResponse deleteCard(Context paramContext, C paramC, Bundle paramBundle)
  {
    return this.mMiTSMCardClient.delete(paramContext, paramC, paramBundle);
  }

  public BaseResponse issue(Context paramContext, C paramC, Bundle paramBundle)
  {
    return this.mMiTSMCardClient.issue(paramContext, paramC, paramBundle);
  }

  public BaseResponse queryCardInfo(Context paramContext, C paramC, Bundle paramBundle)
  {
    String str1 = "";
    Bundle localBundle;
    Object[] arrayOfObject3;
    if (TextUtils.equals(paramC.mCardType, "SZT"))
    {
      str1 = "004";
      localBundle = SmartCardReader.readCard(str1, paramContext);
      if (localBundle != null)
        break label156;
      arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = paramC;
    }
    label156: Object[] arrayOfObject2;
    for (BaseResponse localBaseResponse = new BaseResponse(9999, arrayOfObject3); ; localBaseResponse = new BaseResponse(9999, arrayOfObject2))
    {
      return localBaseResponse;
      if (TextUtils.equals(paramC.mCardType, "BMAC"))
      {
        str1 = "001";
        break;
      }
      if (TextUtils.equals(paramC.mCardType, "LNT"))
      {
        str1 = "013";
        break;
      }
      if (TextUtils.equals(paramC.mCardType, "SUZHOUTONG"))
      {
        str1 = "019";
        break;
      }
      if (TextUtils.equals(paramC.mCardType, "WHT"))
      {
        str1 = "005";
        break;
      }
      if (!TextUtils.equals(paramC.mCardType, "HZT"))
        break;
      str1 = "020";
      break;
      if (localBundle.getBoolean("success"))
        break label220;
      if (localBundle.getInt("error") == 2)
      {
        paramC.mHasIssue = false;
        paramC.mCardBalance = 0;
        paramC.mCardNo = null;
      }
      arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramC;
    }
    label220: String str2 = localBundle.getString("account_num");
    if (!TextUtils.isEmpty(str2))
      paramC.mHasIssue = true;
    paramC.mCardNo = str2;
    paramC.mCardBalance = localBundle.getInt("e_balance");
    paramC.mTradeLogs = localBundle.getParcelableArrayList("trade_log");
    paramC.mStatus = CardInfo.Status.ACTIVE;
    int i = localBundle.getInt("status");
    if (localBundle.getInt("in_black_list") == 1);
    for (int j = 1; ; j = 0)
    {
      if (i != 0)
        paramC.mStatus = CardInfo.Status.NEGATIVE;
      if (j != 0)
        paramC.mStatus = CardInfo.Status.IN_BLACKLIST;
      int k = localBundle.getInt("overdrawn");
      if ((k < 0) || ((paramC.mCardBalance > 0) && (k > 0)))
        paramC.mStatus = CardInfo.Status.DATA_ILLEGAL;
      boolean bool1 = localBundle.getBoolean("is_valid_start_date", true);
      boolean bool2 = localBundle.getBoolean("is_valid_end_date", true);
      if ((!bool1) || (!bool2))
        paramC.mStatus = CardInfo.Status.DATA_ILLEGAL;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = paramC;
      localBaseResponse = new BaseResponse(0, arrayOfObject1);
      break;
    }
  }

  public BaseResponse queryPurchaseRecord(Context paramContext, C paramC)
  {
    return queryCardInfo(paramContext, paramC, null);
  }

  public void terminate()
  {
    this.mMiTSMCardClient.shutDown();
  }

  public BaseResponse updateCardInfo(Context paramContext, C paramC)
  {
    return queryCardInfo(paramContext, paramC, null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.mitsm.MiTSMCardOperation
 * JD-Core Version:    0.6.0
 */