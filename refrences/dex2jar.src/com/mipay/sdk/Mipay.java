package com.mipay.sdk;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.mipay.sdk.exception.MipayException;
import java.security.InvalidParameterException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class Mipay
{
  private static final String ACTION_MIPAY_COUNTER = "com.xiaomi.action.MIPAY_PAY_ORDER";
  private static final String ACTION_MIPAY_COUNTER_SERVICE = "com.xiaomi.action.MIPAY";
  private static final String ACTION_MIPAY_WALLET = "com.xiaomi.action.VIEW_MIPAY_WALLET";
  public static final int CAPABILITY = 0;
  public static final int ERROR_CODE_ACCOUNT_ERROR = 4;
  public static final int ERROR_CODE_CALL_TOO_FAST = 3;
  public static final int ERROR_CODE_CANCELED = 2;
  public static final int ERROR_CODE_DUPLICATE_PURCHASE = 9;
  public static final int ERROR_CODE_EXCEPTION = 1;
  public static final int ERROR_CODE_IDENTITY_NEED_REVIEW = 11;
  public static final int ERROR_CODE_INVALID_CALLER = 8;
  public static final int ERROR_CODE_INVALID_DATA = 7;
  public static final int ERROR_CODE_INVALID_DEVICE = 10;
  public static final int ERROR_CODE_NETWORK_ERROR = 5;
  public static final int ERROR_CODE_OK = 0;
  public static final int ERROR_CODE_SERVER_ERROR = 6;
  public static final String KEY_CODE = "code";
  public static final String KEY_EXTRA = "extra";
  public static final String KEY_FULL_RESULT = "fullResult";
  public static final String KEY_INTENT = "intent";
  public static final String KEY_MESSAGE = "message";
  public static final String KEY_ORDER = "order";
  public static final String KEY_RESULT = "result";
  private static final String PACKAGE_MIPAY_COUNTER = "com.mipay.counter";
  private static final String PACKAGE_MIPAY_WALLET = "com.mipay.wallet";
  private static final String PACKAGE_MIPAY_WALLET_TV = "com.mipay.wallet.tv";
  public static final int REQUEST_MIPAY = 424;
  private static final String TAG = "Mipay";
  public static final String XIAOMI_ACCOUNT_TYPE = "com.xiaomi";
  private final Context mContext;
  private final Handler mMainHandler;
  private boolean mUseWallet;
  private boolean mUseWalletTv;

  private Mipay(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mMainHandler = new Handler(this.mContext.getMainLooper());
    this.mUseWallet = isMipayWalletInstalled(paramContext);
    this.mUseWalletTv = isMipayWalletTVInstalled(paramContext);
  }

  public static Mipay get(Context paramContext)
  {
    return new Mipay(paramContext);
  }

  private MipayFuture<Bundle> internalPay(Activity paramActivity, String paramString, Bundle paramBundle, MipayCallback<Bundle> paramMipayCallback)
  {
    return new PmsTask(paramActivity, paramMipayCallback, paramBundle, paramString)
    {
      protected void doWork()
        throws RemoteException
      {
        IMipayService localIMipayService = getService();
        Bundle localBundle = new Bundle();
        if (this.val$extra != null)
          localBundle.putAll(this.val$extra);
        localIMipayService.pay(getResponse(), null, this.val$order, localBundle);
      }
    }
    .start();
  }

  private void internalShowWallet(Activity paramActivity)
  {
    if (this.mUseWallet)
    {
      Intent localIntent = new Intent("com.xiaomi.action.VIEW_MIPAY_WALLET");
      localIntent.setPackage("com.mipay.wallet");
      paramActivity.startActivity(localIntent);
    }
  }

  private static boolean isMipayCounterInstalled(Context paramContext)
  {
    int i = 0;
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      localPackageManager.getPackageInfo("com.mipay.counter", 0);
      i = 1;
      label17: return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label17;
    }
  }

  public static boolean isMipayInstalled(Context paramContext)
  {
    int i = 0;
    if (isTablet());
    while (true)
    {
      return i;
      if ((!isMipayWalletInstalled(paramContext)) && (!isMipayCounterInstalled(paramContext)) && (!isMipayWalletTVInstalled(paramContext)))
        continue;
      i = 1;
    }
  }

  private static boolean isMipayWalletInstalled(Context paramContext)
  {
    int i = 0;
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      localPackageManager.getPackageInfo("com.mipay.wallet", 0);
      i = 1;
      label17: return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label17;
    }
  }

  private static boolean isMipayWalletTVInstalled(Context paramContext)
  {
    int i = 0;
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      localPackageManager.getPackageInfo("com.mipay.wallet.tv", 0);
      i = 1;
      label17: return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label17;
    }
  }

  private static boolean isTablet()
  {
    DisplayMetrics localDisplayMetrics = Resources.getSystem().getDisplayMetrics();
    if ((int)(0.5F + Math.min(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels) / localDisplayMetrics.density) >= 600);
    for (int i = 1; ; i = 0)
      return i;
  }

  private Message makeMessage(int paramInt1, int paramInt2, String paramString)
  {
    return makeMessage(paramInt1, paramInt2, paramString, null);
  }

  private Message makeMessage(int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("code", paramInt2);
      if (!TextUtils.isEmpty(paramString1))
        localJSONObject.put("message", paramString1);
      if (!TextUtils.isEmpty(paramString2))
        localJSONObject.put("result", paramString2);
      label52: Message localMessage = new Message();
      localMessage.what = paramInt1;
      localMessage.obj = localJSONObject.toString();
      return localMessage;
    }
    catch (JSONException localJSONException)
    {
      break label52;
    }
  }

  public void pay(Activity paramActivity, String paramString, Bundle paramBundle)
  {
    if (paramActivity == null)
      throw new InvalidParameterException("activity cannot be null");
    if (TextUtils.isEmpty(paramString))
      throw new InvalidParameterException("order cannot be empty");
    Intent localIntent = new Intent("com.xiaomi.action.MIPAY_PAY_ORDER");
    if (this.mUseWalletTv)
      localIntent.setPackage("com.mipay.wallet.tv");
    while (true)
    {
      localIntent.putExtra("order", paramString);
      localIntent.putExtra("extra", paramBundle);
      paramActivity.startActivityForResult(localIntent, 424);
      return;
      localIntent.setPackage("com.mipay.wallet");
    }
  }

  @Deprecated
  public void pay(Activity paramActivity, String paramString, Bundle paramBundle, int paramInt, Handler paramHandler)
  {
    if (paramActivity == null)
      throw new InvalidParameterException("activity cannot be null");
    if (TextUtils.isEmpty(paramString))
      throw new InvalidParameterException("order cannot be empty");
    internalPay(paramActivity, paramString, paramBundle, new MipayCallbackImpl(paramInt, paramHandler));
  }

  public void pay(Fragment paramFragment, String paramString, Bundle paramBundle)
  {
    if (paramFragment == null)
      throw new InvalidParameterException("activity cannot be null");
    if (TextUtils.isEmpty(paramString))
      throw new InvalidParameterException("order cannot be empty");
    Intent localIntent = new Intent("com.xiaomi.action.MIPAY_PAY_ORDER");
    if (this.mUseWalletTv)
      localIntent.setPackage("com.mipay.wallet.tv");
    while (true)
    {
      localIntent.putExtra("order", paramString);
      localIntent.putExtra("extra", paramBundle);
      paramFragment.startActivityForResult(localIntent, 424);
      return;
      localIntent.setPackage("com.mipay.wallet");
    }
  }

  public void showMipayCenter(Activity paramActivity)
  {
    internalShowWallet(paramActivity);
  }

  private static abstract interface MipayFuture<V>
  {
    public abstract boolean cancel(boolean paramBoolean);

    public abstract V getResult()
      throws MipayException;

    public abstract V getResult(long paramLong, TimeUnit paramTimeUnit)
      throws MipayException;

    public abstract boolean isCancelled();

    public abstract boolean isDone();
  }

  private abstract class PmsTask extends FutureTask<Bundle>
    implements Mipay.MipayFuture<Bundle>, ServiceConnection
  {
    private final int HOST_MONITOR_HEART_INTERNAL = 5000;
    private Activity mActivity;
    private Mipay.MipayCallback<Bundle> mCallback;
    private Runnable mHostActivityMonitor = new Runnable()
    {
      public void run()
      {
        Activity localActivity = Mipay.PmsTask.this.mActivity;
        if ((!Mipay.PmsTask.this.isDone()) && (localActivity != null))
        {
          if (!localActivity.isFinishing())
            break label47;
          Mipay.PmsTask.this.setException(new MipayException(2, "Operation has been cancelled because host activity has finished."));
        }
        while (true)
        {
          return;
          label47: Mipay.this.mMainHandler.postDelayed(this, 5000L);
        }
      }
    };
    private boolean mIsBound = false;
    private IMipayResponse mResponse;
    private IMipayService mService;

    protected PmsTask(Mipay.MipayCallback<Bundle> arg2)
    {
      super();
      Object localObject1;
      this.mActivity = localObject1;
      Object localObject2;
      this.mCallback = localObject2;
      this.mResponse = new MipayResponseImpl();
    }

    private Exception convertErrorCodeToException(int paramInt, String paramString, Bundle paramBundle)
    {
      if (TextUtils.isEmpty(paramString))
        paramString = "Unknown failure";
      return new MipayException(paramInt, paramString, paramBundle);
    }

    private void ensureNotOnMainThread()
    {
      Looper localLooper = Looper.myLooper();
      if ((localLooper != null) && (localLooper == Mipay.this.mContext.getMainLooper()))
      {
        IllegalStateException localIllegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
        Log.e("Mipay", "calling this from your main thread can lead to deadlock and/or ANRs", localIllegalStateException);
        throw localIllegalStateException;
      }
    }

    // ERROR //
    private Bundle internalGetResult(Long paramLong, TimeUnit paramTimeUnit)
      throws MipayException
    {
      // Byte code:
      //   0: aload_0
      //   1: invokevirtual 139	com/mipay/sdk/Mipay$PmsTask:isDone	()Z
      //   4: ifne +7 -> 11
      //   7: aload_0
      //   8: invokespecial 141	com/mipay/sdk/Mipay$PmsTask:ensureNotOnMainThread	()V
      //   11: aload_1
      //   12: ifnonnull +21 -> 33
      //   15: aload_0
      //   16: invokevirtual 145	com/mipay/sdk/Mipay$PmsTask:get	()Ljava/lang/Object;
      //   19: checkcast 147	android/os/Bundle
      //   22: astore 11
      //   24: aload_0
      //   25: iconst_1
      //   26: invokevirtual 151	com/mipay/sdk/Mipay$PmsTask:cancel	(Z)Z
      //   29: pop
      //   30: aload 11
      //   32: areturn
      //   33: aload_0
      //   34: aload_1
      //   35: invokevirtual 157	java/lang/Long:longValue	()J
      //   38: aload_2
      //   39: invokevirtual 160	com/mipay/sdk/Mipay$PmsTask:get	(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
      //   42: checkcast 147	android/os/Bundle
      //   45: astore 11
      //   47: aload_0
      //   48: iconst_1
      //   49: invokevirtual 151	com/mipay/sdk/Mipay$PmsTask:cancel	(Z)Z
      //   52: pop
      //   53: goto -23 -> 30
      //   56: astore 10
      //   58: new 86	com/mipay/sdk/exception/MipayException
      //   61: dup
      //   62: iconst_2
      //   63: ldc 162
      //   65: invokespecial 165	com/mipay/sdk/exception/MipayException:<init>	(ILjava/lang/String;)V
      //   68: athrow
      //   69: astore 4
      //   71: aload_0
      //   72: iconst_1
      //   73: invokevirtual 151	com/mipay/sdk/Mipay$PmsTask:cancel	(Z)Z
      //   76: pop
      //   77: aload 4
      //   79: athrow
      //   80: astore 9
      //   82: new 86	com/mipay/sdk/exception/MipayException
      //   85: dup
      //   86: iconst_2
      //   87: ldc 167
      //   89: invokespecial 165	com/mipay/sdk/exception/MipayException:<init>	(ILjava/lang/String;)V
      //   92: athrow
      //   93: astore 8
      //   95: new 86	com/mipay/sdk/exception/MipayException
      //   98: dup
      //   99: iconst_1
      //   100: aload 8
      //   102: invokespecial 170	com/mipay/sdk/exception/MipayException:<init>	(ILjava/lang/Throwable;)V
      //   105: athrow
      //   106: astore 6
      //   108: aload 6
      //   110: invokevirtual 174	java/util/concurrent/ExecutionException:getCause	()Ljava/lang/Throwable;
      //   113: astore 7
      //   115: aload 7
      //   117: instanceof 86
      //   120: ifeq +9 -> 129
      //   123: aload 7
      //   125: checkcast 86	com/mipay/sdk/exception/MipayException
      //   128: athrow
      //   129: new 86	com/mipay/sdk/exception/MipayException
      //   132: dup
      //   133: iconst_1
      //   134: aload 7
      //   136: invokespecial 170	com/mipay/sdk/exception/MipayException:<init>	(ILjava/lang/Throwable;)V
      //   139: athrow
      //   140: astore_3
      //   141: new 86	com/mipay/sdk/exception/MipayException
      //   144: dup
      //   145: iconst_1
      //   146: aload_3
      //   147: invokespecial 170	com/mipay/sdk/exception/MipayException:<init>	(ILjava/lang/Throwable;)V
      //   150: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   15	24	56	java/util/concurrent/CancellationException
      //   33	47	56	java/util/concurrent/CancellationException
      //   15	24	69	finally
      //   33	47	69	finally
      //   58	69	69	finally
      //   82	151	69	finally
      //   15	24	80	java/util/concurrent/TimeoutException
      //   33	47	80	java/util/concurrent/TimeoutException
      //   15	24	93	java/lang/InterruptedException
      //   33	47	93	java/lang/InterruptedException
      //   15	24	106	java/util/concurrent/ExecutionException
      //   33	47	106	java/util/concurrent/ExecutionException
      //   15	24	140	java/lang/Throwable
      //   33	47	140	java/lang/Throwable
    }

    protected void bind()
    {
      if (!bindToMipayService())
        setException(new MipayException(1, "bind to service failed"));
      while (true)
      {
        return;
        this.mIsBound = true;
        Log.d("Mipay", "service bound");
      }
    }

    protected boolean bindToMipayService()
    {
      Intent localIntent = new Intent("com.xiaomi.action.MIPAY");
      if (Mipay.this.mUseWallet)
        localIntent.setPackage("com.mipay.wallet");
      while (true)
      {
        return Mipay.this.mContext.bindService(localIntent, this, 1);
        localIntent.setPackage("com.mipay.counter");
      }
    }

    protected abstract void doWork()
      throws RemoteException;

    protected void done()
    {
      if (this.mCallback != null)
        Mipay.this.mMainHandler.post(new Runnable()
        {
          public void run()
          {
            Mipay.PmsTask.this.mCallback.run(Mipay.PmsTask.this);
            Mipay.PmsTask.access$602(Mipay.PmsTask.this, null);
          }
        });
      Mipay.this.mMainHandler.removeCallbacks(this.mHostActivityMonitor);
      this.mActivity = null;
    }

    protected IMipayResponse getResponse()
    {
      return this.mResponse;
    }

    public Bundle getResult()
      throws MipayException
    {
      return internalGetResult(null, null);
    }

    public Bundle getResult(long paramLong, TimeUnit paramTimeUnit)
      throws MipayException
    {
      return internalGetResult(Long.valueOf(paramLong), paramTimeUnit);
    }

    protected IMipayService getService()
    {
      return this.mService;
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      Log.d("Mipay", "service connected, component:" + paramComponentName);
      this.mService = IMipayService.Stub.asInterface(paramIBinder);
      try
      {
        doWork();
        Mipay.this.mMainHandler.postDelayed(this.mHostActivityMonitor, 5000L);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
          setException(localRemoteException);
      }
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      Log.e("Mipay", "service disconnected");
      if (!isDone())
      {
        Log.e("Mipay", "task is not completed");
        setException(new MipayException(1, "active service exits unexpectedly"));
      }
      this.mService = null;
    }

    protected void set(Bundle paramBundle)
    {
      super.set(paramBundle);
      unBind();
    }

    protected void setException(Throwable paramThrowable)
    {
      super.setException(paramThrowable);
      unBind();
    }

    public final Mipay.MipayFuture<Bundle> start()
    {
      bind();
      return this;
    }

    protected void unBind()
    {
      if (!this.mIsBound);
      while (true)
      {
        return;
        Mipay.this.mContext.unbindService(this);
        this.mIsBound = false;
        Log.d("Mipay", "service unbinded");
      }
    }

    class MipayResponseImpl extends IMipayResponse.Stub
    {
      MipayResponseImpl()
      {
      }

      public void onError(int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        if (paramInt == 2)
        {
          Mipay.PmsTask.this.cancel(true);
          Mipay.PmsTask.this.unBind();
        }
        while (true)
        {
          return;
          Mipay.PmsTask.this.setException(Mipay.PmsTask.this.convertErrorCodeToException(paramInt, paramString, paramBundle));
        }
      }

      public void onResult(Bundle paramBundle)
        throws RemoteException
      {
        Intent localIntent = (Intent)paramBundle.getParcelable("intent");
        if (localIntent != null)
          if (Mipay.PmsTask.this.mActivity != null)
            Mipay.PmsTask.this.mActivity.startActivity(localIntent);
        while (true)
        {
          return;
          Mipay.PmsTask.this.setException(new MipayException(7, "activity cannot be null"));
          continue;
          Mipay.PmsTask.this.set(paramBundle);
        }
      }
    }
  }

  private class MipayCallbackImpl
    implements Mipay.MipayCallback<Bundle>
  {
    private Handler mCallback;
    private int mWhat;

    public MipayCallbackImpl(int paramHandler, Handler arg3)
    {
      this.mWhat = paramHandler;
      Object localObject;
      this.mCallback = localObject;
    }

    public void run(Mipay.MipayFuture<Bundle> paramMipayFuture)
    {
      if (this.mCallback == null);
      while (true)
      {
        return;
        try
        {
          Bundle localBundle = (Bundle)paramMipayFuture.getResult();
          if (localBundle != null)
          {
            String str = localBundle.getString("result");
            this.mCallback.sendMessage(Mipay.this.makeMessage(this.mWhat, 0, null, str));
          }
          while (true)
          {
            this.mCallback = null;
            break;
            this.mCallback.sendMessage(Mipay.this.makeMessage(this.mWhat, 1, "error"));
          }
        }
        catch (MipayException localMipayException)
        {
          this.mCallback.sendMessage(Mipay.this.makeMessage(this.mWhat, localMipayException.getError(), localMipayException.getMessage()));
          this.mCallback = null;
          continue;
        }
        finally
        {
          this.mCallback = null;
        }
      }
      throw localObject;
    }
  }

  private static abstract interface MipayCallback<V>
  {
    public abstract void run(Mipay.MipayFuture<V> paramMipayFuture);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.mipay.sdk.Mipay
 * JD-Core Version:    0.6.0
 */