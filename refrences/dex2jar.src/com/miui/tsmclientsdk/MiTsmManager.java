package com.miui.tsmclientsdk;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MiTsmManager
{
  private static final String ACTION_MI_TSM_SERVICE = "com.miui.tsmclientsdk.action.MI_TSM_SERVICE";
  private static final MiTsmManager INSTANCE = new MiTsmManager();
  private static final String KEY_INTENT = "key_intent";
  private static final String PACKAGE_MIPAY_WALLET = "com.mipay.wallet";
  private static final String PACKAGE_NAME_TSMCLIENT = "com.miui.tsmclient";
  private static final String TAG = "MiTsmManager";
  private static final String URI_APP_BANK_CARD = "mipay://walletapp?id=mipay.bankCardList";

  private void checkVersion(Context paramContext, int paramInt)
    throws UnSupportedException
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo("com.miui.tsmclient", 0).versionCode;
      if (i < paramInt)
        throw new UnSupportedException("requires minVersionCode is " + paramInt + ", current versionCode is" + " " + i);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new UnSupportedException("PackageNotFoundException");
    }
  }

  public static MiTsmManager getInstance()
  {
    return INSTANCE;
  }

  public MiTsmFuture<Bundle> createSSD(Context paramContext, int paramInt)
    throws UnSupportedException
  {
    checkVersion(paramContext, 3);
    return new TSMTask(paramContext, paramInt)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.createSSD(this.mResponse, this.val$spTsmId);
      }
    }
    .start();
  }

  public MiTsmFuture<Bundle> deleteBankCard(Context paramContext, String paramString)
    throws UnSupportedException
  {
    throw new UnSupportedException("This method has been deprecated");
  }

  public MiTsmFuture<Bundle> deleteBankCards(Context paramContext)
    throws UnSupportedException
  {
    checkVersion(paramContext, 13);
    return new TSMTask(paramContext)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.deleteAllBankCard(this.mResponse);
      }
    }
    .start();
  }

  public MiTsmFuture<Bundle> getActiveCards(Context paramContext, MiTsmConstants.CardType paramCardType)
    throws UnSupportedException
  {
    checkVersion(paramContext, 9);
    return new TSMTask(paramContext, paramCardType)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.getActiveCards(this.mResponse, this.val$cardType.toString());
      }
    }
    .start();
  }

  public MiTsmFuture<Bundle> getCPLC(Context paramContext)
    throws UnSupportedException
  {
    checkVersion(paramContext, 2);
    return new TSMTask(paramContext)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.getCPLC(this.mResponse);
      }
    }
    .start();
  }

  public MiTsmFuture<Bundle> getCardInfo(Context paramContext, List<String> paramList)
    throws UnSupportedException
  {
    checkVersion(paramContext, 9);
    return new TSMTask(paramContext, paramList)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.getCardInfo(this.mResponse, this.val$cardTypes);
      }
    }
    .start();
  }

  public MiTsmFuture<Bundle> getCardsQuantity(Context paramContext, MiTsmConstants.CardType paramCardType)
    throws UnSupportedException
  {
    Log.i("MiTsmManager", "start to getCardsQuantity");
    checkVersion(paramContext, 8);
    return new TSMTask(paramContext, true, paramCardType)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.getCardsQuantity(this.mResponse, this.val$cardType.toString());
      }
    }
    .start();
  }

  @Deprecated
  public MiTsmFuture<Bundle> getDefaultCard(Context paramContext, MiTsmConstants.CardType paramCardType)
    throws UnSupportedException
  {
    throw new UnSupportedException("This method has been deprecated");
  }

  public MiTsmFuture<Bundle> getSeBankCards(Context paramContext)
    throws UnSupportedException
  {
    checkVersion(paramContext, 11);
    return new TSMTask(paramContext)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.getSeBankCards(this.mResponse);
      }
    }
    .start();
  }

  public MiTsmFuture<Bundle> getTransCardState(Context paramContext)
    throws UnSupportedException
  {
    checkVersion(paramContext, 16);
    return new TSMTask(paramContext)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.getTransCardState(this.mResponse);
      }
    }
    .start();
  }

  public MiTsmFuture<Bundle> isBankCardAvailable(Context paramContext)
    throws UnSupportedException
  {
    checkVersion(paramContext, 15);
    return new TSMTask(paramContext)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.isBankCardAvailable(this.mResponse);
      }
    }
    .start();
  }

  public int isTSMServiceAvailable(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    int i = 0;
    int j = 0;
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      if (!TextUtils.equals("com.miui.tsmclient", ((PackageInfo)localIterator.next()).packageName))
        continue;
      j = 1;
    }
    if (j == 0)
      i = 0 + 1;
    NfcAdapter localNfcAdapter = NfcAdapter.getDefaultAdapter(paramContext);
    if (localNfcAdapter == null)
      i += 2;
    if ((localNfcAdapter != null) && (!localNfcAdapter.isEnabled()))
      i += 4;
    return i;
  }

  public MiTsmFuture<Bundle> manageBankCard(Activity paramActivity, String paramString, MiTsmConstants.OperationType paramOperationType)
    throws UnSupportedException
  {
    checkVersion(paramActivity, 14);
    return new TSMTask(paramActivity, paramString, paramOperationType)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.manageBankCard(this.mResponse, this.val$aid, this.val$operationType.ordinal());
      }
    }
    .start();
  }

  @Deprecated
  public MiTsmFuture<Bundle> setDefaultCard(Context paramContext, MiTsmConstants.CardType paramCardType, Bundle paramBundle)
    throws UnSupportedException
  {
    throw new UnSupportedException("This method has been deprecated");
  }

  public void showBankcardList(Activity paramActivity, String paramString)
    throws UnSupportedException
  {
    if ((TextUtils.isEmpty(paramString)) || (paramActivity == null))
      throw new UnSupportedException("Channel ref must not be empty");
    checkVersion(paramActivity, 18);
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("mipay://walletapp?id=mipay.bankCardList&miref=" + paramString));
    localIntent.setPackage("com.mipay.wallet");
    paramActivity.startActivityForResult(localIntent, 10000);
  }

  public void startOpenCard(Activity paramActivity, int paramInt, Bundle paramBundle)
    throws UnSupportedException
  {
    checkVersion(paramActivity, 11);
    Intent localIntent = new Intent();
    localIntent.setPackage("com.miui.tsmclient");
    localIntent.setData(Uri.parse("https://tsmclient.miui.com?action=issue&type=BANKCARD"));
    if (paramBundle != null)
      localIntent.putExtras(paramBundle);
    paramActivity.startActivityForResult(localIntent, paramInt);
  }

  public MiTsmFuture<Bundle> syncBankCardStatus(Context paramContext)
    throws UnSupportedException
  {
    checkVersion(paramContext, 17);
    return new TSMTask(paramContext)
    {
      protected void doWork()
        throws RemoteException
      {
        this.mService.syncBankCardStatus(this.mResponse);
      }
    }
    .start();
  }

  private static abstract class TSMTask extends FutureTask<Bundle>
    implements ServiceConnection, MiTsmFuture<Bundle>
  {
    private Context mContext;
    private boolean mIsBound = false;
    private boolean mIsLauntchActivity = false;
    private CountDownLatch mLatch = new CountDownLatch(1);
    protected IMiTsmResponse mResponse;
    protected IMiTsmService mService;

    protected TSMTask(Context paramContext)
    {
      this(paramContext, false);
    }

    protected TSMTask(Context paramContext, boolean paramBoolean)
    {
      super();
      this.mContext = paramContext;
      this.mResponse = new IMiTsmResponseImpl(null);
      this.mIsLauntchActivity = paramBoolean;
    }

    private void bind()
    {
      if (!this.mIsBound)
      {
        if (!bindService())
          break label28;
        this.mIsBound = true;
        Log.v("MiTsmManager", "bind service: successed");
      }
      while (true)
      {
        return;
        label28: Log.w("MiTsmManager", "bind service: failed");
      }
    }

    private boolean bindService()
    {
      Intent localIntent = new Intent("com.miui.tsmclientsdk.action.MI_TSM_SERVICE");
      localIntent.setPackage("com.miui.tsmclient");
      return this.mContext.bindService(localIntent, this, 1);
    }

    private void ensureNotOnMainThread()
    {
      Looper localLooper = Looper.myLooper();
      if ((localLooper != null) && (localLooper == this.mContext.getMainLooper()))
      {
        IllegalStateException localIllegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
        Log.e("MiTsmManager", localIllegalStateException.getMessage(), localIllegalStateException);
        throw localIllegalStateException;
      }
    }

    private Bundle internalGetResult(Long paramLong, TimeUnit paramTimeUnit)
      throws OperationCanceledException, IOException, ExecutionException
    {
      if (!isDone())
        ensureNotOnMainThread();
      if (paramTimeUnit == null);
      try
      {
        Bundle localBundle = (Bundle)get();
        while (true)
        {
          return localBundle;
          localBundle = (Bundle)get(paramLong.longValue(), paramTimeUnit);
          cancel(true);
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        throw new OperationCanceledException();
      }
      catch (TimeoutException localTimeoutException)
      {
        while (true)
          cancel(true);
      }
      catch (CancellationException localCancellationException)
      {
        while (true)
          cancel(true);
      }
      catch (ExecutionException localExecutionException)
      {
        Throwable localThrowable = localExecutionException.getCause();
        if ((localThrowable instanceof IOException))
          throw ((IOException)localThrowable);
      }
      finally
      {
        cancel(true);
      }
      throw localExecutionException;
    }

    private void unbind()
    {
      if (!this.mIsBound);
      while (true)
      {
        return;
        this.mIsBound = false;
        this.mContext.unbindService(this);
        Log.v("MiTsmManager", "unbind service");
      }
    }

    protected abstract void doWork()
      throws RemoteException;

    public Bundle getResult()
      throws OperationCanceledException, IOException, ExecutionException
    {
      return internalGetResult(null, null);
    }

    public Bundle getResult(long paramLong, TimeUnit paramTimeUnit)
      throws OperationCanceledException, IOException, ExecutionException
    {
      return internalGetResult(Long.valueOf(paramLong), paramTimeUnit);
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      Log.v("MiTsmManager", "onServiceConnected, component:" + paramComponentName);
      this.mService = IMiTsmService.Stub.asInterface(paramIBinder);
      this.mLatch.countDown();
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      Log.v("MiTsmManager", "onServicedisconnected");
      if (!isDone())
        Log.v("MiTsmManager", "task is not completed");
      this.mService = null;
    }

    protected void set(Bundle paramBundle)
    {
      super.set(paramBundle);
      unbind();
    }

    protected void setException(Throwable paramThrowable)
    {
      super.setException(paramThrowable);
      unbind();
    }

    public final MiTsmFuture<Bundle> start()
    {
      bind();
      try
      {
        this.mLatch.await();
        doWork();
        return this;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
          setException(localRemoteException);
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
          localInterruptedException.printStackTrace();
      }
    }

    private class IMiTsmResponseImpl extends IMiTsmResponse.Stub
    {
      private IMiTsmResponseImpl()
      {
      }

      public void onError(int paramInt, String paramString)
        throws RemoteException
      {
        Log.w("MiTsmManager", "on Error: code=" + paramInt + " msg=" + paramString);
        Bundle localBundle = new Bundle();
        localBundle.putInt("key_result_code", paramInt);
        localBundle.putString("key_result_msg", paramString);
        MiTsmManager.TSMTask.this.set(localBundle);
      }

      public void onResult(Bundle paramBundle)
        throws RemoteException
      {
        paramBundle.putInt("key_result_code", 0);
        Intent localIntent = (Intent)paramBundle.getParcelable("key_intent");
        if ((localIntent != null) && (MiTsmManager.TSMTask.this.mIsLauntchActivity))
          MiTsmManager.TSMTask.this.mContext.startActivity(localIntent);
        while (true)
        {
          return;
          MiTsmManager.TSMTask.this.set(paramBundle);
        }
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclientsdk.MiTsmManager
 * JD-Core Version:    0.6.0
 */