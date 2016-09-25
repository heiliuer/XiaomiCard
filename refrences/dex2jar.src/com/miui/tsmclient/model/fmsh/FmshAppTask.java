package com.miui.tsmclient.model.fmsh;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import cn.com.fmsh.nfcos.client.service.xm.CardAppManager;
import cn.com.fmsh.nfcos.client.service.xm.CardAppManager.Stub;
import com.miui.tsmclient.model.BaseAppTask;
import com.miui.tsmclient.util.LogUtils;

public abstract class FmshAppTask extends BaseAppTask
{
  private static final String FMSH_SERVICE_ACTION = "cn.com.fmsh.nfcos.client.service.xm.NfcosService4xm";
  private CardAppManager mCardAppManager = null;

  public FmshAppTask(Context paramContext)
  {
    super(paramContext);
  }

  /** @deprecated */
  protected void bindAppService()
  {
    monitorenter;
    try
    {
      Intent localIntent = new Intent("cn.com.fmsh.nfcos.client.service.xm.NfcosService4xm");
      localIntent.setPackage(this.mContext.getPackageName());
      boolean bool = this.mContext.bindService(localIntent, this, 1);
      LogUtils.d("FmshAppTask#bindAppService() called, execute result is:" + bool);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  protected void doServiceConnected(IBinder paramIBinder)
  {
    this.mCardAppManager = CardAppManager.Stub.asInterface(paramIBinder);
    super.doServiceConnected(paramIBinder);
  }

  public CardAppManager getService()
  {
    return this.mCardAppManager;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.fmsh.FmshAppTask
 * JD-Core Version:    0.6.0
 */