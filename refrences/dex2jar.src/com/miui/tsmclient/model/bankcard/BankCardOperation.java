package com.miui.tsmclient.model.bankcard;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.database.CardDataUtil;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.mitsm.MiTSMCardOperation;
import com.miui.tsmclient.seitsm.TsmRpcModels.BankCardInfo;
import com.miui.tsmclient.seitsm.TsmRpcModels.QueryBankCardInfoResponse;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.tsmclient.smartcard.exception.InvalidTLVException;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BankCardOperation extends MiTSMCardOperation
{
  private BankCardClient mBankCardClient = new BankCardClient();

  private void deleteInvalidCard(Context paramContext, List<TsmRpcModels.BankCardInfo> paramList)
  {
    Object localObject = null;
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = paramList.iterator();
    while (localIterator1.hasNext())
      localArrayList.add(((TsmRpcModels.BankCardInfo)localIterator1.next()).getAid());
    try
    {
      Set localSet = SysUtils.getSeBankCards(paramContext);
      localObject = localSet;
      if (localObject != null)
      {
        Iterator localIterator2 = localObject.iterator();
        while (localIterator2.hasNext())
        {
          String str = (String)localIterator2.next();
          if (localArrayList.contains(str))
            continue;
          BankCardInfo localBankCardInfo = new BankCardInfo();
          localBankCardInfo.mAid = str;
          this.mBankCardClient.delete(paramContext, localBankCardInfo, null);
        }
      }
    }
    catch (InvalidTLVException localInvalidTLVException)
    {
      while (true)
        LogUtils.e("InvalidTLVException in deleteInvalidCard", localInvalidTLVException);
    }
    catch (IOException localIOException)
    {
      while (true)
        LogUtils.e("IOException in deleteInvalidCard", localIOException);
    }
    catch (TagNotFoundException localTagNotFoundException)
    {
      while (true)
        LogUtils.e("TagNotFoundException in deleteInvalidCard", localTagNotFoundException);
    }
  }

  public BaseResponse deleteCards(Context paramContext)
  {
    return this.mBankCardClient.deleteCards(paramContext);
  }

  public BaseResponse enrollUPCard(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    return this.mBankCardClient.enrollUPCard(paramContext, paramCardInfo, paramBundle);
  }

  public BaseResponse isBankCardServiceAvailable(Context paramContext)
  {
    return this.mBankCardClient.isBankCardServiceAvailable(paramContext);
  }

  public BaseResponse lock(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    return this.mBankCardClient.lock(paramContext, paramCardInfo, paramBundle);
  }

  public BaseResponse preparePayApplet(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    return this.mBankCardClient.preparePayApplet(paramContext, paramCardInfo, paramBundle);
  }

  public BaseResponse pullPersoData(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    return this.mBankCardClient.pullPersoData(paramContext, paramCardInfo, paramBundle);
  }

  public BaseResponse queryBankCardInfo(Context paramContext, CardInfo paramCardInfo)
  {
    return this.mBankCardClient.queryBankCardInfo(paramContext, paramCardInfo, null);
  }

  public BaseResponse queryPan(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    return this.mBankCardClient.queryPan(paramContext, paramCardInfo, paramBundle);
  }

  public BaseResponse requestVerificationCode(Context paramContext, CardInfo paramCardInfo, String paramString)
  {
    return this.mBankCardClient.requestVerificationCode(paramContext, paramCardInfo, paramString);
  }

  public BaseResponse updateCardInfo(Context paramContext, CardInfo paramCardInfo)
  {
    BaseResponse localBaseResponse = this.mBankCardClient.queryBankCardInfo(paramContext, paramCardInfo, null);
    if (localBaseResponse.mResultCode == 0)
    {
      TsmRpcModels.QueryBankCardInfoResponse localQueryBankCardInfoResponse = (TsmRpcModels.QueryBankCardInfoResponse)localBaseResponse.mDatas[0];
      localBaseResponse.mDatas[0] = BankCardInfo.fillFromTsm(localQueryBankCardInfoResponse.getBankCardInfoListList());
      deleteInvalidCard(paramContext, localQueryBankCardInfoResponse.getBankCardInfoListList());
      CardDataUtil.saveBankCardInfo(paramContext, (List)localBaseResponse.mDatas[0]);
    }
    return localBaseResponse;
  }

  public BaseResponse verifyCardInfo(Context paramContext, CardInfo paramCardInfo, String paramString, byte[] paramArrayOfByte, Bundle paramBundle)
  {
    return this.mBankCardClient.verifyCardInfo(paramContext, paramCardInfo, paramString, paramArrayOfByte, paramBundle);
  }

  public BaseResponse verifyVerificationCode(Context paramContext, CardInfo paramCardInfo, String paramString1, String paramString2)
  {
    return this.mBankCardClient.verifyVerificationCode(paramContext, paramCardInfo, paramString1, paramString2);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.bankcard.BankCardOperation
 * JD-Core Version:    0.6.0
 */