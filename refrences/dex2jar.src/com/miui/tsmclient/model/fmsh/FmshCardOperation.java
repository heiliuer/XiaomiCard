package com.miui.tsmclient.model.fmsh;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import cn.com.fmsh.nfcos.client.service.xm.CardAppInfo;
import cn.com.fmsh.nfcos.client.service.xm.CardAppRecord;
import cn.com.fmsh.nfcos.client.service.xm.NfcosBusinessOrder;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.tsm.business.enums.EnumRechargeMode;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.ActionToken.TokenType;
import com.miui.tsmclient.entity.CardInfo.Status;
import com.miui.tsmclient.entity.FmshCardInfo;
import com.miui.tsmclient.entity.SptcCardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.IPayableCardOperation;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NetworkUtil;
import com.tsmclient.smartcard.model.TradeLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miui.os.Build;

public class FmshCardOperation
  implements IPayableCardOperation<FmshCardInfo>
{
  public static final String APPLIACTION_NAME = "MI-APP";
  private FmshCardClient mFmshCardClient = new FmshCardClient();
  private MiTSMCardClient mMiTSMCardClient = new MiTSMCardClient();

  private TradeLog convertToTradeLog(CardAppRecord paramCardAppRecord)
  {
    TradeLog localTradeLog;
    if (paramCardAppRecord == null)
      localTradeLog = null;
    while (true)
    {
      return localTradeLog;
      localTradeLog = new TradeLog();
      localTradeLog.setAuAmount(paramCardAppRecord.amount);
      localTradeLog.setTradeType(getTradeType(paramCardAppRecord.tradeType));
      localTradeLog.setTradeTime(paramCardAppRecord.tradeTime);
      localTradeLog.setTradeDate(paramCardAppRecord.tradeDate);
    }
  }

  private int getRechargeType(OrderInfo paramOrderInfo)
  {
    switch (1.$SwitchMap$com$miui$tsmclient$pay$PayType[paramOrderInfo.mPayType.ordinal()])
    {
    default:
    case 1:
    }
    for (int i = EnumRechargeMode.MIPAY_MI.getId(); ; i = EnumRechargeMode.MIPAY_MI.getId())
      return i;
  }

  private static int getTradeType(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 6:
    case 12:
    }
    for (int i = 1; ; i = 2)
      return i;
  }

  private BaseResponse updateCardInfo(Context paramContext, FmshCardInfo paramFmshCardInfo, boolean paramBoolean)
  {
    int i = FmshCardClient.getCardAppType(paramFmshCardInfo.mCardType);
    BaseResponse localBaseResponse1 = this.mFmshCardClient.getInfo(paramContext, i, 3);
    FmshCardInfo localFmshCardInfo;
    int j;
    BaseResponse localBaseResponse2;
    if ((localBaseResponse1 != null) && (localBaseResponse1.mResultCode == 0) && (localBaseResponse1.mDatas != null))
    {
      localFmshCardInfo = new FmshCardInfo((CardAppInfo)localBaseResponse1.mDatas[0], paramFmshCardInfo.mCardType);
      paramFmshCardInfo.updateInfo(localFmshCardInfo);
      paramFmshCardInfo.mStatus = CardInfo.Status.ACTIVE;
      if ((paramBoolean) && (paramFmshCardInfo.mUnfinishOrderInfos != null) && (!paramFmshCardInfo.mUnfinishOrderInfos.isEmpty()))
      {
        j = 1;
        BaseResponse localBaseResponse3 = this.mFmshCardClient.queryUnsolvedOrder(paramContext, i, null);
        if ((localBaseResponse3.mResultCode != 0) || (localBaseResponse3.mDatas == null) || (((List)localBaseResponse3.mDatas[0]).isEmpty()))
          break label375;
        List localList = (List)localBaseResponse3.mDatas[0];
        int k = 0;
        Iterator localIterator3 = localList.iterator();
        while (localIterator3.hasNext())
        {
          NfcosBusinessOrder localNfcosBusinessOrder = (NfcosBusinessOrder)localIterator3.next();
          if (localNfcosBusinessOrder.tradeState == EnumOrderStatus.dubious.getId())
          {
            k = 1;
            continue;
          }
          localBaseResponse1 = this.mFmshCardClient.handleUnsolvedOrder(paramContext, i, paramFmshCardInfo, null, localNfcosBusinessOrder);
          if (localBaseResponse1.mResultCode == 0)
            continue;
          LogUtils.d("handleUnsolvedOrder failed.error:" + localBaseResponse1.mResultCode);
          localBaseResponse1.mResultCode = 1003;
          j = 0;
        }
        if ((j != 0) && (localList.size() != paramFmshCardInfo.mUnfinishOrderInfos.size()))
        {
          localBaseResponse2 = new BaseResponse(1010, new Object[0]);
          return localBaseResponse2;
        }
        if ((j != 0) && (k != 0))
        {
          Object[] arrayOfObject = new Object[0];
          localBaseResponse1 = new BaseResponse(1004, "has confirm doubt order which orderstate is 12", arrayOfObject);
          label353: break label386;
        }
        label354: if (j == 0)
          break label532;
        localBaseResponse1 = updateCardInfo(paramContext, paramFmshCardInfo, false);
      }
    }
    while (true)
    {
      localBaseResponse2 = localBaseResponse1;
      break;
      label375: Iterator localIterator1 = paramFmshCardInfo.mUnfinishOrderInfos.iterator();
      label386: if (!localIterator1.hasNext())
        break label354;
      OrderInfo localOrderInfo = (OrderInfo)localIterator1.next();
      Iterator localIterator2 = localOrderInfo.mActionTokens.iterator();
      while (localIterator2.hasNext())
      {
        ActionToken localActionToken = (ActionToken)localIterator2.next();
        if (localActionToken.mType != ActionToken.TokenType.recharge)
          continue;
        localBaseResponse1 = this.mFmshCardClient.recharge(paramContext, i, localActionToken, localFmshCardInfo.mAppNo, getRechargeType(localOrderInfo), null);
        if (localBaseResponse1.mResultCode == 0)
          continue;
        LogUtils.d("handle unfinished order failed.error:" + localBaseResponse1.mResultCode);
        localBaseResponse1.mResultCode = 1003;
        j = 0;
      }
      if (j != 0)
        break label353;
      break label354;
      label532: updateCardInfo(paramContext, paramFmshCardInfo, false);
      continue;
      paramFmshCardInfo.mHasIssue = false;
      paramFmshCardInfo.mCardBalance = 0;
      paramFmshCardInfo.mCardNo = null;
    }
  }

  public BaseResponse deleteCard(Context paramContext, FmshCardInfo paramFmshCardInfo, Bundle paramBundle)
  {
    return null;
  }

  public BaseResponse issue(Context paramContext, FmshCardInfo paramFmshCardInfo, Bundle paramBundle)
  {
    BaseResponse localBaseResponse1;
    if (!NetworkUtil.isConnected(paramContext))
    {
      Object[] arrayOfObject2 = new Object[0];
      localBaseResponse1 = new BaseResponse(2, arrayOfObject2);
    }
    while (true)
    {
      return localBaseResponse1;
      int i = FmshCardClient.getCardAppType(paramFmshCardInfo.mCardType);
      byte b = 0;
      label54: Object localObject1;
      if (EnumCardAppType.CARD_APP_TYPE_SH.getId() == i)
      {
        b = 1;
        localObject1 = null;
      }
      try
      {
        byte[] arrayOfByte = this.mFmshCardClient.getSeid(paramContext);
        localObject1 = arrayOfByte;
        if (paramBundle == null)
          paramBundle = new Bundle();
        if (!paramBundle.containsKey("pre_load"))
        {
          j = 1;
          paramBundle.putBoolean("pre_load", true);
          localBaseResponse1 = this.mMiTSMCardClient.issue(paramContext, paramFmshCardInfo, paramBundle);
          LogUtils.d("install fmsh sd result:" + localBaseResponse1.mResultCode);
          if (localBaseResponse1.mResultCode != 0)
            continue;
          if (j != 0)
            break label212;
          localBaseResponse1 = this.mFmshCardClient.downloadApplet(paramContext, i, localObject1, Build.MODEL);
          continue;
          if (EnumCardAppType.CARD_APP_TYPE_LNT.getId() != i)
            break label54;
          b = 3;
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          LogUtils.e("get seid failed!", localIOException);
          continue;
          int j = 0;
        }
        label212: if (paramFmshCardInfo.mUnfinishOrderInfos == null)
        {
          Object[] arrayOfObject1 = new Object[0];
          localBaseResponse1 = new BaseResponse(1, arrayOfObject1);
          continue;
        }
        BaseResponse localBaseResponse2 = this.mFmshCardClient.queryUnsolvedOrder(paramContext, i, null);
        if ((localBaseResponse2.mResultCode == 0) && (localBaseResponse2.mDatas != null) && (!((List)localBaseResponse2.mDatas[0]).isEmpty()))
        {
          Iterator localIterator3 = ((List)localBaseResponse2.mDatas[0]).iterator();
          while (true)
            if (localIterator3.hasNext())
            {
              NfcosBusinessOrder localNfcosBusinessOrder = (NfcosBusinessOrder)localIterator3.next();
              if (localNfcosBusinessOrder.businessOrderType != EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId())
                continue;
              localBaseResponse1 = this.mFmshCardClient.doIssue(paramContext, i, localNfcosBusinessOrder.order, b, localObject1, null, null);
              break;
            }
        }
        Object localObject2 = null;
        int k = EnumRechargeMode.MIPAY_MI.getId();
        Iterator localIterator1 = paramFmshCardInfo.mUnfinishOrderInfos.iterator();
        while (true)
        {
          if (!localIterator1.hasNext())
            break label470;
          OrderInfo localOrderInfo = (OrderInfo)localIterator1.next();
          Iterator localIterator2 = localOrderInfo.mActionTokens.iterator();
          if (!localIterator2.hasNext())
            continue;
          ActionToken localActionToken = (ActionToken)localIterator2.next();
          if (localActionToken.mType != ActionToken.TokenType.issue)
            break;
          k = getRechargeType(localOrderInfo);
          localObject2 = localActionToken;
        }
        label470: FmshCardClient localFmshCardClient = this.mFmshCardClient;
        String str = Build.MODEL;
        int m = paramFmshCardInfo.mIssueFee;
        localBaseResponse1 = localFmshCardClient.doIssueEx(paramContext, i, k, str, localObject1, localObject2, m, paramBundle);
      }
    }
  }

  public BaseResponse queryCardInfo(Context paramContext, FmshCardInfo paramFmshCardInfo, Bundle paramBundle)
  {
    BaseResponse localBaseResponse = this.mFmshCardClient.getInfo(paramContext, FmshCardClient.getCardAppType(paramFmshCardInfo.mCardType), 3);
    Object localObject = new FmshCardInfo();
    ((FmshCardInfo)localObject).mCardType = paramFmshCardInfo.mCardType;
    if ((localBaseResponse != null) && (localBaseResponse.mResultCode == 0) && (localBaseResponse.mDatas != null) && (TextUtils.equals(paramFmshCardInfo.mCardType, "SPTC")))
    {
      localObject = new SptcCardInfo((CardAppInfo)localBaseResponse.mDatas[0]);
      ((FmshCardInfo)localObject).mStatus = CardInfo.Status.ACTIVE;
    }
    if (localBaseResponse == null);
    for (int i = 9999; ; i = localBaseResponse.mResultCode)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localObject;
      return new BaseResponse(i, arrayOfObject);
    }
  }

  public BaseResponse queryPurchaseRecord(Context paramContext, FmshCardInfo paramFmshCardInfo)
  {
    BaseResponse localBaseResponse = this.mFmshCardClient.getInfo(paramContext, FmshCardClient.getCardAppType(paramFmshCardInfo.mCardType), 4);
    if (localBaseResponse.mResultCode == 0)
    {
      CardAppInfo localCardAppInfo = (CardAppInfo)localBaseResponse.mDatas[0];
      ArrayList localArrayList = new ArrayList();
      if (localCardAppInfo.records != null)
        for (int j = 0; j < localCardAppInfo.records.length; j++)
          localArrayList.add(convertToTradeLog(localCardAppInfo.records[j]));
      paramFmshCardInfo.mTradeLogs = localArrayList;
      int i = localBaseResponse.mResultCode;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramFmshCardInfo;
      localBaseResponse = new BaseResponse(i, arrayOfObject);
    }
    return localBaseResponse;
  }

  public BaseResponse recharge(Context paramContext, FmshCardInfo paramFmshCardInfo, OrderInfo paramOrderInfo, Tag paramTag)
  {
    Object localObject2;
    if (!NetworkUtil.isConnected(paramContext))
    {
      Object[] arrayOfObject2 = new Object[0];
      localObject2 = new BaseResponse(2, arrayOfObject2);
    }
    while (true)
    {
      return localObject2;
      int i = FmshCardClient.getCardAppType(paramFmshCardInfo.mCardType);
      Object localObject1 = null;
      Iterator localIterator1 = paramOrderInfo.mActionTokens.iterator();
      while (localIterator1.hasNext())
      {
        ActionToken localActionToken = (ActionToken)localIterator1.next();
        if ((!paramFmshCardInfo.mHasIssue) || (localActionToken.mType != ActionToken.TokenType.recharge))
          continue;
        localObject1 = localActionToken;
      }
      BaseResponse localBaseResponse = this.mFmshCardClient.queryUnsolvedOrder(paramContext, i, paramTag);
      if ((localBaseResponse.mResultCode == 0) && (localBaseResponse.mDatas != null) && (!((List)localBaseResponse.mDatas[0]).isEmpty()))
      {
        Iterator localIterator2 = ((List)localBaseResponse.mDatas[0]).iterator();
        while (true)
          if (localIterator2.hasNext())
          {
            NfcosBusinessOrder localNfcosBusinessOrder = (NfcosBusinessOrder)localIterator2.next();
            localObject2 = this.mFmshCardClient.handleUnsolvedOrder(paramContext, i, paramFmshCardInfo, paramTag, localNfcosBusinessOrder);
            if (((BaseResponse)localObject2).mResultCode == 0)
              continue;
            if ((((BaseResponse)localObject2).mResultCode != 2002) || (((BaseResponse)localObject2).mDatas == null))
              break;
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = localNfcosBusinessOrder.faceNo;
            ((BaseResponse)localObject2).mDatas = arrayOfObject1;
            break;
          }
        localObject2 = localBaseResponse;
        continue;
      }
      localObject2 = this.mFmshCardClient.recharge(paramContext, i, localObject1, paramFmshCardInfo.mAppNo, getRechargeType(paramOrderInfo), paramTag);
    }
  }

  public void terminate()
  {
    this.mFmshCardClient.shutDown();
  }

  public BaseResponse updateCardInfo(Context paramContext, FmshCardInfo paramFmshCardInfo)
  {
    return updateCardInfo(paramContext, paramFmshCardInfo, true);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.fmsh.FmshCardOperation
 * JD-Core Version:    0.6.0
 */