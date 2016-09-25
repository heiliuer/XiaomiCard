package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.TsmRpcModels.TsmSessionInfo;
import java.io.IOException;

public class DecoratorMiTSMClient extends MiTSMCardClient
{
  private MiTSMCardClient mMiTSMCardClient;

  public DecoratorMiTSMClient(MiTSMCardClient paramMiTSMCardClient)
  {
    this.mMiTSMCardClient = paramMiTSMCardClient;
  }

  public BaseResponse delete(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    return this.mMiTSMCardClient.delete(paramContext, paramCardInfo, paramBundle);
  }

  public TsmRpcModels.TsmSessionInfo getSession(Context paramContext, CardInfo paramCardInfo, TSMSessionManager.BusinessType paramBusinessType)
    throws IOException, SeiTSMApiException
  {
    return this.mMiTSMCardClient.getSession(paramContext, paramCardInfo, paramBusinessType);
  }

  public TsmRpcModels.TsmSessionInfo getSession(Context paramContext, CardInfo paramCardInfo, TSMSessionManager.BusinessType paramBusinessType, boolean paramBoolean)
    throws IOException, SeiTSMApiException
  {
    return this.mMiTSMCardClient.getSession(paramContext, paramCardInfo, paramBusinessType, paramBoolean);
  }

  public BaseResponse issue(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    return this.mMiTSMCardClient.issue(paramContext, paramCardInfo, paramBundle);
  }

  public BaseResponse lock(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    return this.mMiTSMCardClient.lock(paramContext, paramCardInfo, paramBundle);
  }

  public BaseResponse syncEse(Context paramContext, CardInfo paramCardInfo, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, Bundle paramBundle)
    throws IOException, SeiTSMApiException
  {
    return this.mMiTSMCardClient.syncEse(paramContext, paramCardInfo, paramTsmSessionInfo, paramBundle);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.mitsm.DecoratorMiTSMClient
 * JD-Core Version:    0.6.0
 */