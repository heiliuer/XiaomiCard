package com.miui.tsmclient.model;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.ActionToken.TokenType;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.model.mitsm.DecoratorMiTSMClient;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.model.mitsm.MiTsmErrorCode;
import com.miui.tsmclient.model.mitsm.TSMSessionManager;
import com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.SeiTsmAuthManager;
import com.miui.tsmclient.seitsm.TsmRpcModels.TsmAPDUCommand;
import com.miui.tsmclient.seitsm.TsmRpcModels.TsmSessionInfo;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import com.tsmclient.smartcard.terminal.AbstractTerminal;
import com.tsmclient.smartcard.terminal.I2CSmartMxTerminal;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PayableCardClient extends DecoratorMiTSMClient
{
  public PayableCardClient(MiTSMCardClient paramMiTSMCardClient)
  {
    super(paramMiTSMCardClient);
  }

  private BaseResponse startTopupOperation(Context paramContext, CardInfo paramCardInfo, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, String paramString, Bundle paramBundle)
    throws IOException, SeiTSMApiException
  {
    Object localObject1 = null;
    if (mNeedSync.get())
      localObject1 = syncEse(paramContext, paramCardInfo, paramTsmSessionInfo, null);
    TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand;
    if (!mNeedSync.get())
    {
      localTsmAPDUCommand = this.mSeiTsmAuthManager.startTopupOperation(paramContext, paramTsmSessionInfo, paramCardInfo, paramString, paramBundle);
      if (localTsmAPDUCommand != null)
        break label76;
      LogUtils.d("cannnot get apduCommand,startTopupOperation failed.");
      localObject1 = new BaseResponse(9999, new Object[0]);
    }
    label76: int i;
    while (true)
    {
      return localObject1;
      i = localTsmAPDUCommand.getResult();
      if (i != 0)
        break;
      if ((localTsmAPDUCommand.getApdusList() == null) || (localTsmAPDUCommand.getApdusList().isEmpty()))
      {
        localObject1 = new BaseResponse(0, new Object[0]);
        continue;
      }
      I2CSmartMxTerminal localI2CSmartMxTerminal = new I2CSmartMxTerminal(paramContext);
      try
      {
        localI2CSmartMxTerminal.connect();
        BaseResponse localBaseResponse = executeCapdu(paramContext, localI2CSmartMxTerminal, paramTsmSessionInfo, localTsmAPDUCommand);
        localObject1 = localBaseResponse;
        localI2CSmartMxTerminal.close();
        continue;
      }
      catch (SeiTSMApiException localSeiTSMApiException)
      {
        throw new SeiTSMApiException(MiTsmErrorCode.format(localSeiTSMApiException.getErrorCode()), localSeiTSMApiException.getMessage());
      }
      finally
      {
        localI2CSmartMxTerminal.close();
      }
    }
    throw new SeiTSMApiException(i, "startTopupOperation failed,errorCode:" + i);
  }

  protected BaseResponse recharge(Context paramContext, PayableCardInfo paramPayableCardInfo, OrderInfo paramOrderInfo, Tag paramTag, Bundle paramBundle)
  {
    Object localObject1;
    if ((paramPayableCardInfo == null) || (paramOrderInfo == null))
      localObject1 = new BaseResponse(1, new Object[0]);
    while (true)
    {
      return localObject1;
      String str = null;
      Iterator localIterator = paramOrderInfo.mActionTokens.iterator();
      while (localIterator.hasNext())
      {
        ActionToken localActionToken = (ActionToken)localIterator.next();
        if (localActionToken.mType != ActionToken.TokenType.recharge)
          continue;
        str = localActionToken.mToken;
      }
      TSMSessionManager.BusinessType localBusinessType = TSMSessionManager.BusinessType.RECHARGE;
      try
      {
        BaseResponse localBaseResponse = startTopupOperation(paramContext, paramPayableCardInfo, getSession(paramContext, paramPayableCardInfo, localBusinessType), str, paramBundle);
        localObject1 = localBaseResponse;
        if ((paramBundle != null) && (paramBundle.getBoolean("extras_key_session_not_finish")))
          continue;
        TSMSessionManager.getInstance().removeSession(paramPayableCardInfo, localBusinessType);
        continue;
      }
      catch (NfcEeIOException localNfcEeIOException)
      {
        LogUtils.e("recharge failed with an nfc exception. errorCode:" + localNfcEeIOException.getErrorCode(), localNfcEeIOException);
        j = 10;
        if ((paramBundle == null) || (!paramBundle.getBoolean("extras_key_session_not_finish")))
          TSMSessionManager.getInstance().removeSession(paramPayableCardInfo, localBusinessType);
        localObject1 = new BaseResponse(j, new Object[0]);
        continue;
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          LogUtils.e("recharge failed with an io exception.", localIOException);
          j = 2;
          if ((paramBundle != null) && (paramBundle.getBoolean("extras_key_session_not_finish")))
            continue;
          TSMSessionManager.getInstance().removeSession(paramPayableCardInfo, localBusinessType);
        }
      }
      catch (SeiTSMApiException localSeiTSMApiException)
      {
        int j;
        LogUtils.e("recharge failed with an tsmapi exception.", localSeiTSMApiException);
        int i = localSeiTSMApiException.getErrorCode();
        switch (i)
        {
        default:
          j = 1001;
        case 1130:
        case 1133:
        case 4003:
        case 1134:
        case 1135:
        }
        while ((paramBundle == null) || (!paramBundle.getBoolean("extras_key_session_not_finish")))
        {
          TSMSessionManager.getInstance().removeSession(paramPayableCardInfo, localBusinessType);
          break;
          j = 1004;
          continue;
          j = 1012;
          continue;
          j = 0;
        }
      }
      finally
      {
        if ((paramBundle != null) && (paramBundle.getBoolean("extras_key_session_not_finish")))
          break;
        TSMSessionManager.getInstance().removeSession(paramPayableCardInfo, localBusinessType);
      }
    }
    throw localObject2;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.PayableCardClient
 * JD-Core Version:    0.6.0
 */