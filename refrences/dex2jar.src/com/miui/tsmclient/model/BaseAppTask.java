package com.miui.tsmclient.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.miui.tsmclient.util.LogUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseAppTask
  implements ServiceConnection
{
  private static final String TAG = "BaseAppTask";
  protected AtomicBoolean mConnected;
  protected Context mContext;
  protected final CountDownLatch mLatch = new CountDownLatch(1);
  protected boolean mNeedUnbind;

  public BaseAppTask(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mConnected = new AtomicBoolean(false);
  }

  protected abstract void bindAppService();

  protected abstract BaseResponse doInBackgroud();

  protected void doServiceConnected(IBinder paramIBinder)
  {
    LogUtils.d("BaseAppTask: onServiceConnected()");
    this.mConnected.set(true);
    this.mLatch.countDown();
  }

  protected void doUnBindAppService()
  {
    this.mContext.unbindService(this);
  }

  public BaseResponse execute()
  {
    onPreExecute();
    try
    {
      this.mLatch.await();
      monitorenter;
    }
    catch (InterruptedException localInterruptedException)
    {
      try
      {
        BaseResponse localBaseResponse;
        if (this.mNeedUnbind)
        {
          unBindAppService();
          localBaseResponse = new BaseResponse(6, new Object[0]);
        }
        while (true)
        {
          return localBaseResponse;
          localInterruptedException = localInterruptedException;
          Thread.currentThread().interrupt();
          break;
          monitorexit;
          localBaseResponse = doInBackgroud();
          LogUtils.i("BaseAppTask: Execute finished, the result code is:" + localBaseResponse.mResultCode);
          onPostExecute();
        }
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  public abstract Object getService();

  protected void onPostExecute()
  {
    unBindAppService();
  }

  protected void onPreExecute()
  {
    bindAppService();
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    doServiceConnected(paramIBinder);
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    LogUtils.d("BaseAppTask: onServiceDisconnected()");
    unBindAppService();
  }

  public void terminate()
  {
    unBindAppService();
  }

  /** @deprecated */
  protected void unBindAppService()
  {
    monitorenter;
    try
    {
      if (this.mConnected.get())
      {
        LogUtils.d("BaseAppTask: unbind service");
        doUnBindAppService();
        this.mConnected.set(false);
      }
      this.mNeedUnbind = true;
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
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.BaseAppTask
 * JD-Core Version:    0.6.0
 */