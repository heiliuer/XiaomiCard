package com.miui.tsmclient;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.CardManager;
import com.miui.tsmclient.util.SysUtils;
import java.util.Timer;
import java.util.TimerTask;

public class SEInteractionService extends IntentService
{
  public static final String ACTION_DELETE_CARD = "com.miui.action.DELETE_CARD";
  public static final String ACTION_INSTALL_CARD = "com.miui.action.INSTALL_CARD";
  public static final String EXTRA_ACTION_TYPE = "action_type";
  public static final String EXTRA_CARD_INFO = "extra_card_info";
  public static final String EXTRA_ISSUE_SOURCE = "extra_issue_source";
  public static final String EXTRA_PRE_LOAD = "pre_load";
  public static final int PARAM_ACTION_TYPE_ISSUE = 1;
  private static final String TAG = "SEInteractionService";
  private final IBinder mBinder = new LocalBinder();
  private CardManager mCardManager;
  private SEInteractionState mInteractionState;
  private int mProgress;
  private SEInteractionListener mSEInteractionListener;
  private Timer mTimer;
  private TimerTask mTimerTask;

  public SEInteractionService()
  {
    super("SEInteractionService");
  }

  public static void deleteCard(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    Intent localIntent = new Intent("com.miui.action.DELETE_CARD");
    if (paramBundle != null)
      localIntent.putExtras(paramBundle);
    localIntent.putExtra("extra_card_info", paramCardInfo);
    localIntent.setPackage(paramContext.getPackageName());
    if (paramContext != null)
      paramContext.startService(localIntent);
  }

  private void doDeleteCard(Intent paramIntent)
  {
    CardInfo localCardInfo = (CardInfo)paramIntent.getParcelableExtra("extra_card_info");
    startTimer();
    if (localCardInfo != null)
      if ((this.mInteractionState.isInstalling()) && (!this.mInteractionState.isAppInstalling(localCardInfo.mCardType)))
        onTaskFinished(9);
    while (true)
    {
      return;
      this.mInteractionState.setCurrentApp(localCardInfo.mCardType);
      onTaskStart(SEInteractionService.SEInteractionState.Type.DELETE_APP);
      onTaskFinished(this.mCardManager.deleteCard(getApplicationContext(), localCardInfo, paramIntent.getExtras()));
      this.mInteractionState.removeCurrentApp();
      stopTimer();
    }
  }

  private void doInstallCard(Intent paramIntent)
  {
    CardInfo localCardInfo = (CardInfo)paramIntent.getParcelableExtra("extra_card_info");
    boolean bool = paramIntent.getBooleanExtra("pre_load", false);
    startTimer();
    if (localCardInfo != null)
      if ((this.mInteractionState.isInstalling()) && (!this.mInteractionState.isAppInstalling(localCardInfo.mCardType)))
        onTaskFinished(9);
    while (true)
    {
      return;
      this.mInteractionState.setCurrentApp(localCardInfo.mCardType);
      if (!bool)
        onTaskStart(SEInteractionService.SEInteractionState.Type.INSTALL_APP);
      int i = this.mCardManager.issue(getApplicationContext(), localCardInfo, paramIntent.getExtras());
      if (!bool)
      {
        if (i == 0)
        {
          if ((!TextUtils.equals(localCardInfo.mCardType, "BANKCARD")) && (TextUtils.isEmpty(SysUtils.getDefaultTransCard(getApplicationContext()))))
            SysUtils.setDefaultCard(getApplicationContext(), localCardInfo.mAid);
          notifyCardInfoChanges(localCardInfo);
        }
        onTaskFinished(i);
      }
      this.mInteractionState.removeCurrentApp();
      stopTimer();
    }
  }

  public static void installCard(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    if ((SEInteractionState.getInstance().isInstalling()) && (SEInteractionState.getInstance().isAppInstalling(paramCardInfo.mCardType)));
    while (true)
    {
      return;
      Intent localIntent = new Intent("com.miui.action.INSTALL_CARD");
      if (paramBundle != null)
        localIntent.putExtras(paramBundle);
      localIntent.putExtra("extra_card_info", paramCardInfo);
      localIntent.setPackage(paramContext.getPackageName());
      if (paramContext == null)
        continue;
      paramContext.startService(localIntent);
    }
  }

  public static void loadCard(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    Intent localIntent = new Intent("com.miui.action.INSTALL_CARD");
    if (paramBundle != null)
      localIntent.putExtras(paramBundle);
    localIntent.putExtra("extra_card_info", paramCardInfo);
    localIntent.putExtra("pre_load", true);
    localIntent.setPackage(paramContext.getPackageName());
    if (paramContext != null)
      paramContext.startService(localIntent);
  }

  private void notifyCardInfoChanges(CardInfo paramCardInfo)
  {
    Intent localIntent = new Intent("com.xiaomi.tsmclient.action.UPDATE_CARD_LIST");
    localIntent.putExtra("card_info", paramCardInfo);
    localIntent.putExtra("action_type", 1);
    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(localIntent);
  }

  private void onTaskFinished(int paramInt)
  {
    this.mInteractionState.setStatus(SEInteractionService.SEInteractionState.Status.FINISHED);
    if (this.mSEInteractionListener != null)
      this.mSEInteractionListener.onFinished(paramInt);
  }

  private void onTaskStart(SEInteractionService.SEInteractionState.Type paramType)
  {
    this.mInteractionState.setType(paramType);
    this.mInteractionState.setStatus(SEInteractionService.SEInteractionState.Status.RUNNING);
    if (this.mSEInteractionListener != null)
      this.mSEInteractionListener.onStart();
  }

  private void startTimer()
  {
    this.mProgress = 0;
    if (this.mTimer == null)
      this.mTimer = new Timer();
    if (this.mTimerTask == null)
      this.mTimerTask = new TimerTask()
      {
        public void run()
        {
          if (SEInteractionService.this.mProgress < 99)
          {
            SEInteractionService.access$008(SEInteractionService.this);
            if (SEInteractionService.this.mSEInteractionListener != null)
              SEInteractionService.this.mSEInteractionListener.onProgressChanged(SEInteractionService.this.mProgress);
          }
        }
      };
    this.mTimer.schedule(this.mTimerTask, 0L, 1000L);
  }

  private void stopTimer()
  {
    this.mProgress = 0;
    if (this.mTimer != null)
    {
      this.mTimer.cancel();
      this.mTimer = null;
    }
    if (this.mTimerTask != null)
    {
      this.mTimerTask.cancel();
      this.mTimerTask = null;
    }
  }

  public int getProgress()
  {
    return this.mProgress;
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }

  public void onCreate()
  {
    super.onCreate();
    this.mInteractionState = SEInteractionState.getInstance();
    this.mCardManager = new CardManager(getApplicationContext());
  }

  public void onDestroy()
  {
    if (this.mCardManager != null)
    {
      this.mCardManager.release();
      this.mCardManager = null;
    }
    super.onDestroy();
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    String str;
    if (paramIntent != null)
    {
      str = paramIntent.getAction();
      if (!TextUtils.equals("com.miui.action.INSTALL_CARD", str))
        break label24;
      doInstallCard(paramIntent);
    }
    while (true)
    {
      return;
      label24: if (TextUtils.equals("com.miui.action.DELETE_CARD", str))
      {
        doDeleteCard(paramIntent);
        continue;
      }
    }
  }

  public void setSEInteractionListener(SEInteractionListener paramSEInteractionListener)
  {
    this.mSEInteractionListener = paramSEInteractionListener;
  }

  public static abstract interface SEInteractionListener
  {
    public abstract void onFinished(int paramInt);

    public abstract void onProgressChanged(int paramInt);

    public abstract void onStart();
  }

  public static class SEInteractionState
  {
    private static final SEInteractionState INSTANCE = new SEInteractionState();
    private static String mCardType;
    private Status mStatus = Status.PENDING;
    private Type mType;

    public static SEInteractionState getInstance()
    {
      return INSTANCE;
    }

    public String getCurrentAppAid()
    {
      return mCardType;
    }

    public Status getStatus()
    {
      return this.mStatus;
    }

    public Type getType()
    {
      return this.mType;
    }

    public boolean isAppInstalling(String paramString)
    {
      if ((isInstalling()) && (TextUtils.equals(mCardType, paramString)));
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean isInstalling()
    {
      if ((this.mStatus == Status.RUNNING) && (this.mType == Type.INSTALL_APP));
      for (int i = 1; ; i = 0)
        return i;
    }

    public void removeCurrentApp()
    {
      mCardType = null;
    }

    public void setCurrentApp(String paramString)
    {
      mCardType = paramString;
    }

    public void setStatus(Status paramStatus)
    {
      monitorenter;
      try
      {
        this.mStatus = paramStatus;
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

    public void setType(Type paramType)
    {
      monitorenter;
      try
      {
        this.mType = paramType;
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

    public static enum Status
    {
      static
      {
        FINISHED = new Status("FINISHED", 2);
        Status[] arrayOfStatus = new Status[3];
        arrayOfStatus[0] = PENDING;
        arrayOfStatus[1] = RUNNING;
        arrayOfStatus[2] = FINISHED;
        $VALUES = arrayOfStatus;
      }
    }

    public static enum Type
    {
      static
      {
        DELETE_APP = new Type("DELETE_APP", 1);
        LOCK_APP = new Type("LOCK_APP", 2);
        Type[] arrayOfType = new Type[3];
        arrayOfType[0] = INSTALL_APP;
        arrayOfType[1] = DELETE_APP;
        arrayOfType[2] = LOCK_APP;
        $VALUES = arrayOfType;
      }
    }
  }

  public class LocalBinder extends Binder
  {
    public LocalBinder()
    {
    }

    public SEInteractionService getService()
    {
      return SEInteractionService.this;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.SEInteractionService
 * JD-Core Version:    0.6.0
 */