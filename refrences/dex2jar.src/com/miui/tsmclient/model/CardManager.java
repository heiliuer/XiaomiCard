package com.miui.tsmclient.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.database.CardDataUtil;
import com.miui.tsmclient.database.DatabaseConstants;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfo.ServiceStatus;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import com.tsmclient.smartcard.handler.SmartCardReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardManager
{
  protected Context mContext;
  protected String mTag;

  public CardManager(Context paramContext)
  {
    this.mContext = paramContext;
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    if (arrayOfStackTraceElement != null);
    for (int i = 0; ; i++)
    {
      if (i < arrayOfStackTraceElement.length)
      {
        if ((!TextUtils.equals(getClass().getName(), arrayOfStackTraceElement[i].getClassName())) || (i >= -1 + arrayOfStackTraceElement.length))
          continue;
        this.mTag = arrayOfStackTraceElement[(i + 1)].getClassName();
      }
      if (this.mTag == null)
        this.mTag = toString();
      return;
    }
  }

  public CardManager(String paramString, Context paramContext)
  {
    this.mTag = paramString;
    this.mContext = paramContext;
  }

  public int cleanCard(Context paramContext, CardInfo paramCardInfo)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("extras_key_se_operation", 7);
    return deleteCard(paramContext, paramCardInfo, localBundle);
  }

  public int deleteCard(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    int i;
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#deleteCard() called,but param info is null!");
      i = 1;
    }
    while (true)
    {
      return i;
      BaseResponse localBaseResponse = CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType).deleteCard(paramContext, paramCardInfo, paramBundle);
      if (localBaseResponse == null)
      {
        i = 9999;
        continue;
      }
      i = localBaseResponse.mResultCode;
    }
  }

  public int issue(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    int i;
    if (paramCardInfo == null)
      i = 1;
    while (true)
    {
      return i;
      BaseResponse localBaseResponse = CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType).issue(paramContext, paramCardInfo, paramBundle);
      if (localBaseResponse == null)
      {
        i = 9999;
        continue;
      }
      i = localBaseResponse.mResultCode;
    }
  }

  public void loadCards(List<CardInfo> paramList, MiTsmCallback paramMiTsmCallback)
  {
    CardExecutor.getInstance().submit(this.mTag, new Runnable(paramList, paramMiTsmCallback)
    {
      public void run()
      {
        List localList = CardDataUtil.loadCardList(CardManager.this.mContext, this.val$cardInfoList);
        if (((this.val$cardInfoList == null) || (this.val$cardInfoList.isEmpty())) && ((localList == null) || (localList.isEmpty())))
        {
          BaseResponse localBaseResponse1 = new BaseResponse(2, new Object[0]);
          CardExecutor.getInstance().notifyResult(localBaseResponse1, this.val$callback);
        }
        while (true)
        {
          return;
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator1;
          if ((this.val$cardInfoList != null) && (!this.val$cardInfoList.isEmpty()))
            localIterator1 = this.val$cardInfoList.iterator();
          while (localIterator1.hasNext())
          {
            CardInfo localCardInfo1 = (CardInfo)localIterator1.next();
            Iterator localIterator2 = localList.iterator();
            while (localIterator2.hasNext())
            {
              CardInfo localCardInfo2 = (CardInfo)localIterator2.next();
              if (!TextUtils.equals(localCardInfo1.mCardType, localCardInfo2.mCardType))
                continue;
              localCardInfo1.updateInfo(localCardInfo2);
            }
            if ((!localCardInfo1.mHasIssue) && (localCardInfo1.mServiceStatus == CardInfo.ServiceStatus.negative))
            {
              LogUtils.d("service status of card :" + localCardInfo1.mCardType + " is negative.");
              continue;
            }
            localArrayList.add(localCardInfo1);
            continue;
            localArrayList.addAll(localList);
          }
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localArrayList;
          BaseResponse localBaseResponse2 = new BaseResponse(0, arrayOfObject);
          CardExecutor.getInstance().notifyResult(localBaseResponse2, this.val$callback);
        }
      }
    });
  }

  public void queryCardInfo(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#queryCardInfo() called, but param cardInfo is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      ICardOperation localICardOperation = CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().submit(this.mTag, new Runnable(localICardOperation, paramContext, paramCardInfo, paramBundle, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.queryCardInfo(this.val$context, this.val$cardInfo, this.val$bundle);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }

  public void queryPurchaseRecord(Context paramContext, CardInfo paramCardInfo, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#queryPurchaseRecord() called, but param info is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      ICardOperation localICardOperation = CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().submit(this.mTag, new Runnable(localICardOperation, paramContext, paramCardInfo, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.queryPurchaseRecord(this.val$context, this.val$cardInfo);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }

  public void readCardsFromSe(MiTsmCallback paramMiTsmCallback)
  {
    CardExecutor.getInstance().submit(this.mTag, new Runnable(paramMiTsmCallback)
    {
      public void run()
      {
        ArrayList localArrayList = new ArrayList();
        Bundle localBundle = SmartCardReader.readCard("A0000003330101", CardManager.this.mContext);
        if (localBundle != null)
        {
          ContentResolver localContentResolver = CardManager.this.mContext.getContentResolver();
          Iterator localIterator = localBundle.getStringArrayList("extras_key_pan_list").iterator();
          while (localIterator.hasNext())
          {
            String[] arrayOfString = ((String)localIterator.next()).split("&");
            BankCardInfo localBankCardInfo = new BankCardInfo();
            localBankCardInfo.mAid = arrayOfString[0];
            localBankCardInfo.mCardNo = arrayOfString[1];
            String str = "bin_code=" + localBankCardInfo.mCardNo.substring(0, 6);
            Cursor localCursor = localContentResolver.query(DatabaseConstants.CONTENT_URI_BANK_BIN, null, str, null, null);
            if (localCursor != null)
              try
              {
                if (localCursor.moveToNext())
                {
                  localBankCardInfo.mCardName = localCursor.getString(localCursor.getColumnIndex("bank_name"));
                  localBankCardInfo.mBankCardType = localCursor.getInt(localCursor.getColumnIndex("card_type"));
                }
              }
              finally
              {
                localCursor.close();
              }
            localArrayList.add(localBankCardInfo);
          }
        }
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localArrayList;
        BaseResponse localBaseResponse = new BaseResponse(0, arrayOfObject);
        CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
      }
    });
  }

  public void release()
  {
    CardExecutor.getInstance().release(this.mTag);
  }

  public void unrestrictESE(Context paramContext, MiTsmCallback paramMiTsmCallback)
  {
    CardExecutor.getInstance().execute(new Runnable(paramContext, paramMiTsmCallback)
    {
      public void run()
      {
        Object localObject = new BaseResponse();
        MiTSMCardClient localMiTSMCardClient = new MiTSMCardClient();
        try
        {
          BaseResponse localBaseResponse = localMiTSMCardClient.unrestrictEse(this.val$context, null);
          localObject = localBaseResponse;
          CardExecutor.getInstance().notifyResult((BaseResponse)localObject, this.val$callback);
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            ((BaseResponse)localObject).mResultCode = 2;
            LogUtils.e("unrestrictESE failed with an io exception.", localIOException);
          }
        }
        catch (SeiTSMApiException localSeiTSMApiException)
        {
          while (true)
          {
            ((BaseResponse)localObject).mResultCode = localSeiTSMApiException.getErrorCode();
            LogUtils.e("unrestrictESE failed with an tsmapi exception.", localSeiTSMApiException);
          }
        }
      }
    });
  }

  public void updateCardInfo(Context paramContext, CardInfo paramCardInfo, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#updateCardInfo() called, but param cardInfo is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      ICardOperation localICardOperation = CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().submit(this.mTag, new Runnable(localICardOperation, paramContext, paramCardInfo, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.updateCardInfo(this.val$context, this.val$cardInfo);
          CardDataUtil.saveCardInfo(this.val$context, this.val$cardInfo);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.CardManager
 * JD-Core Version:    0.6.0
 */