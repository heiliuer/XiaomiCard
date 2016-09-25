package com.miui.tsmclient.model.bankcard;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.database.CardDataUtil;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.CardExecutor;
import com.miui.tsmclient.model.CardManager;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import java.util.List;

public class BankCardManager extends CardManager
{
  public BankCardManager(Context paramContext)
  {
    super(paramContext);
  }

  public BankCardManager(String paramString, Context paramContext)
  {
    super(paramString, paramContext);
  }

  public int deleteAllBankCard(Context paramContext)
  {
    BaseResponse localBaseResponse = ((BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, "BANKCARD")).deleteCards(paramContext);
    if (localBaseResponse == null);
    for (int i = 9999; ; i = localBaseResponse.mResultCode)
      return i;
  }

  public void enrollUPCard(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#enrollUPCard() called,but param info is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      BankCardOperation localBankCardOperation = (BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().execute(new Runnable(localBankCardOperation, paramContext, paramCardInfo, paramBundle, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.enrollUPCard(this.val$context, this.val$cardInfo, this.val$extras);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }

  public void isBankCardServiceAvailable(Context paramContext, MiTsmCallback paramMiTsmCallback)
  {
    BankCardOperation localBankCardOperation = (BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, new BankCardInfo().mCardType);
    CardExecutor.getInstance().execute(new Runnable(localBankCardOperation, paramContext, paramMiTsmCallback)
    {
      public void run()
      {
        BaseResponse localBaseResponse = this.val$operation.isBankCardServiceAvailable(this.val$context);
        CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
      }
    });
  }

  public void loadCards(List<CardInfo> paramList, MiTsmCallback paramMiTsmCallback)
  {
    CardExecutor.getInstance().execute(new Runnable(paramMiTsmCallback)
    {
      public void run()
      {
        List localList = CardDataUtil.loadBankCardList(BankCardManager.this.mContext);
        if (localList == null)
          CardExecutor.getInstance().notifyResult(new BaseResponse(2, new Object[0]), this.val$callback);
        while (true)
        {
          return;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localList;
          BaseResponse localBaseResponse = new BaseResponse(0, arrayOfObject);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      }
    });
  }

  public void lock(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#lockCard() called,but param info is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      BankCardOperation localBankCardOperation = (BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().execute(new Runnable(localBankCardOperation, paramContext, paramCardInfo, paramBundle, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.lock(this.val$context, this.val$cardInfo, this.val$extras);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }

  public void preparePayApplet(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#preparePayApplet() called,but param info is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      BankCardOperation localBankCardOperation = (BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().execute(new Runnable(localBankCardOperation, paramContext, paramCardInfo, paramBundle, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.preparePayApplet(this.val$context, this.val$cardInfo, this.val$extras);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }

  public void pullPersoData(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#pullPersoData() called,but param info is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      BankCardOperation localBankCardOperation = (BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().execute(new Runnable(localBankCardOperation, paramContext, paramCardInfo, paramBundle, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.pullPersoData(this.val$context, this.val$cardInfo, this.val$extras);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }

  public void queryPan(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#queryPan() called,but param info is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      BankCardOperation localBankCardOperation = (BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().execute(new Runnable(localBankCardOperation, paramContext, paramCardInfo, paramBundle, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.queryPan(this.val$context, this.val$cardInfo, this.val$extras);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }

  public void requestVerificationCode(Context paramContext, CardInfo paramCardInfo, String paramString, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#requestVerificationCode() called,but param info is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      BankCardOperation localBankCardOperation = (BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().execute(new Runnable(localBankCardOperation, paramContext, paramCardInfo, paramString, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.requestVerificationCode(this.val$context, this.val$cardInfo, this.val$referenceId);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }

  public void updateCardInfo(Context paramContext, CardInfo paramCardInfo, MiTsmCallback paramMiTsmCallback)
  {
    BankCardOperation localBankCardOperation = (BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, "BANKCARD");
    CardExecutor.getInstance().execute(new Runnable(localBankCardOperation, paramContext, paramCardInfo, paramMiTsmCallback)
    {
      public void run()
      {
        BaseResponse localBaseResponse = this.val$operation.updateCardInfo(this.val$context, this.val$cardInfo);
        CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
      }
    });
  }

  public void verifyCardInfo(Context paramContext, CardInfo paramCardInfo, String paramString, byte[] paramArrayOfByte, Bundle paramBundle, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#verifyCardInfo() called,but param info is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      BankCardOperation localBankCardOperation = (BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().execute(new Runnable(localBankCardOperation, paramContext, paramCardInfo, paramString, paramArrayOfByte, paramBundle, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.verifyCardInfo(this.val$context, this.val$cardInfo, this.val$cardNum, this.val$cipherData, this.val$extras);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }

  public void verifyVerificationCode(Context paramContext, CardInfo paramCardInfo, String paramString1, String paramString2, MiTsmCallback paramMiTsmCallback)
  {
    if (paramCardInfo == null)
    {
      LogUtils.d(CardManager.class.getSimpleName() + "#verifyVerificationCode() called,but param info is null!");
      if (paramMiTsmCallback != null)
        paramMiTsmCallback.onFail(1, "", new Object[0]);
    }
    while (true)
    {
      return;
      BankCardOperation localBankCardOperation = (BankCardOperation)CardExecutor.getInstance().createCardOperation(this.mTag, paramCardInfo.mCardType);
      CardExecutor.getInstance().execute(new Runnable(localBankCardOperation, paramContext, paramCardInfo, paramString1, paramString2, paramMiTsmCallback)
      {
        public void run()
        {
          BaseResponse localBaseResponse = this.val$operation.verifyVerificationCode(this.val$context, this.val$cardInfo, this.val$referenceId, this.val$code);
          CardExecutor.getInstance().notifyResult(localBaseResponse, this.val$callback);
        }
      });
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.bankcard.BankCardManager
 * JD-Core Version:    0.6.0
 */