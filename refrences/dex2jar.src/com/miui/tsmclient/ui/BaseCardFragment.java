package com.miui.tsmclient.ui;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.CardManager;
import com.miui.tsmclient.task.CheckNfcEEStatusTask;
import com.miui.tsmclient.task.TaskListener;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import java.util.List;
import miui.util.async.Task;
import miui.util.async.TaskManager;

public class BaseCardFragment<T extends CardInfo> extends BaseFragment
{
  public static final String EXTRA_CARD_INFO = "card_info";
  public static final String EXTRA_CARD_INFO_LIST = "card_info_list";
  public static final String EXTRA_CARD_TYPE = "tag_card_type";
  public static final String EXTRA_DEFAULT_CARD_AID = "tag_default_card_aid";
  private static final int MSG_CHECK_NFC_EE_STATUS = 1000;
  private static final int MSG_UN_RESTRICTE_SE = 1001;
  protected T mCardInfo;
  protected List<T> mCardInfoList;
  private CardManager mCardManager;
  private TaskListener mCheckNfcEEListener = new TaskListener()
  {
    public Object onResult(TaskManager paramTaskManager, Task<?> paramTask, Object paramObject)
    {
      BaseCardFragment.this.mHandler.obtainMessage(1000, paramObject).sendToTarget();
      return paramObject;
    }
  };
  private CheckNfcEEStatusTask mCheckNfcEEStatusTask;
  protected String mDefaultCardAId;
  private AlertDialog mFingerprintDialog;
  private FingerprintManager mFingerprintManager;
  protected Handler mHandler;
  private AlertDialog mNfcOpenDialog;
  private TaskManager mTaskManager;
  private AlertDialog mUnrestricteSEDialog;

  private void dismissNfcOpenDialog()
  {
    if ((this.mNfcOpenDialog != null) && (this.mNfcOpenDialog.isShowing()))
    {
      this.mNfcOpenDialog.dismiss();
      this.mNfcOpenDialog = null;
    }
  }

  private void handleNfcEEStatus(int paramInt)
  {
    LogUtils.d("checked nfc ee state: " + paramInt);
    switch (paramInt)
    {
    default:
    case 1:
    case 0:
    case 2:
    case 3:
    }
    while (true)
    {
      return;
      onNFCDisable();
      continue;
      dismissNfcOpenDialog();
      onNFCEnable();
      continue;
      onNfcEERestricted(2131296349);
      continue;
      onESEDisable();
    }
  }

  private void onNfcEEUnRestrictedSuccess()
  {
    new AlertDialog.Builder(getActivity()).setTitle(2131296350).setPositiveButton(2131296318, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        BaseCardFragment.this.getActivity().finish();
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        BaseCardFragment.this.getActivity().finish();
      }
    }).create().show();
  }

  private void onUnRestrictNfcEERejected()
  {
    new AlertDialog.Builder(getActivity()).setTitle(2131296309).setMessage(2131296310).setPositiveButton(2131296318, null).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        BaseCardFragment.this.getActivity().finish();
      }
    }).create().show();
  }

  private void showFingerprintAlertDialog()
  {
    if (this.mFingerprintDialog == null)
      this.mFingerprintDialog = new AlertDialog.Builder(getActivity()).setTitle(2131296288).setMessage(2131296289).setPositiveButton(2131296290, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          Intent localIntent = new Intent();
          localIntent.setClassName("com.android.settings", "com.android.settings.NewFingerprintActivity");
          BaseCardFragment.this.getActivity().startActivity(localIntent);
        }
      }).setNegativeButton(2131296271, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseCardFragment.this.getActivity().finish();
        }
      }).setCancelable(false).create();
    this.mFingerprintDialog.show();
  }

  private void showNfcOpenDialog(boolean paramBoolean)
  {
    AlertDialog.Builder localBuilder;
    if (this.mNfcOpenDialog == null)
    {
      localBuilder = new AlertDialog.Builder(getActivity()).setTitle(2131296314);
      if (!paramBoolean)
        break label90;
    }
    label90: for (String str = getRoutingNotESEPrompt(); ; str = getString(2131296305))
    {
      this.mNfcOpenDialog = localBuilder.setMessage(str).setPositiveButton(2131296279, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseCardFragment.this.startNfcSettings();
          BaseCardFragment.this.dismissNfcOpenDialog();
        }
      }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseCardFragment.this.getActivity().finish();
        }
      }).setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          BaseCardFragment.this.getActivity().finish();
        }
      }).create();
      this.mNfcOpenDialog.show();
      return;
    }
  }

  private void startNfcSettings()
  {
    int i = 0;
    Intent localIntent1 = new Intent("android.settings.NFC_SETTINGS");
    try
    {
      startActivity(localIntent1);
      i = 1;
      if (i != 0);
    }
    catch (ActivityNotFoundException localActivityNotFoundException3)
    {
      try
      {
        localIntent2 = new Intent("android.settings.NFC_SETTINGS");
      }
      catch (ActivityNotFoundException localActivityNotFoundException3)
      {
        try
        {
          Intent localIntent2;
          startActivity(localIntent2);
          while (true)
          {
            label40: return;
            localActivityNotFoundException1 = localActivityNotFoundException1;
            break;
            localActivityNotFoundException3 = localActivityNotFoundException3;
          }
        }
        catch (ActivityNotFoundException localActivityNotFoundException2)
        {
          break label40;
        }
      }
    }
  }

  private void unrestrictEse()
  {
    showDialog(2131296275);
    this.mCardManager.unrestrictESE(getActivity(), new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        BaseCardFragment.this.mHandler.obtainMessage(1001, Integer.valueOf(paramInt)).sendToTarget();
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        BaseCardFragment.this.mHandler.obtainMessage(1001, Integer.valueOf(paramInt)).sendToTarget();
      }
    });
  }

  protected void checkFingerStatus()
  {
    if (this.mFingerprintManager.hasEnrolledFingerprints());
    while (true)
    {
      return;
      showFingerprintAlertDialog();
    }
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    Bundle localBundle = getArguments();
    if (localBundle != null)
    {
      this.mCardInfo = ((CardInfo)localBundle.getParcelable("card_info"));
      this.mDefaultCardAId = localBundle.getString("tag_default_card_aid");
      this.mCardInfoList = localBundle.getParcelableArrayList("card_info_list");
    }
    this.mTaskManager = TaskManager.getDefault();
    this.mHandler = new BaseFragment.CardHandler(this, (miui.app.Activity)getActivity());
    this.mCardManager = new CardManager(getActivity());
    this.mFingerprintManager = ((FingerprintManager)getActivity().getSystemService("fingerprint"));
  }

  protected String getRoutingNotESEPrompt()
  {
    return getString(2131296306);
  }

  protected void handleMessage(Message paramMessage, miui.app.Activity paramActivity)
  {
    switch (paramMessage.what)
    {
    default:
    case 1000:
    case 1001:
    }
    while (true)
    {
      return;
      handleNfcEEStatus(((Integer)paramMessage.obj).intValue());
      continue;
      dismissDialog();
      int i = ((Integer)paramMessage.obj).intValue();
      if (i == 0)
      {
        onNfcEEUnRestrictedSuccess();
        continue;
      }
      if (i == 3026)
      {
        onUnRestrictNfcEERejected();
        continue;
      }
      onNfcEERestricted(2131296309);
    }
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    ActionBar localActionBar = getActivity().getActionBar();
    if ((localActionBar != null) && (this.mCardInfo != null))
    {
      String str = this.mCardInfo.mCardName;
      if (!TextUtils.isEmpty(this.mCardInfo.mCardSubName))
        str = str + "·" + this.mCardInfo.mCardSubName;
      localActionBar.setTitle(str);
    }
  }

  public void onDestroy()
  {
    this.mCardManager.release();
    this.mTaskManager.shutdown();
    this.mHandler.removeCallbacksAndMessages(null);
    super.onDestroy();
  }

  protected void onESEDisable()
  {
    showNfcOpenDialog(true);
  }

  protected void onNFCDisable()
  {
    LogUtils.d("onNFCDisabled");
    showNfcOpenDialog(false);
  }

  protected void onNFCEnable()
  {
    if ((this.mNfcOpenDialog != null) && (this.mNfcOpenDialog.isShowing()))
    {
      this.mNfcOpenDialog.dismiss();
      this.mNfcOpenDialog = null;
    }
    if ((this.mUnrestricteSEDialog != null) && (this.mUnrestricteSEDialog.isShowing()))
    {
      this.mUnrestricteSEDialog.dismiss();
      this.mUnrestricteSEDialog = null;
    }
  }

  protected void onNfcEERestricted(int paramInt)
  {
    if (this.mUnrestricteSEDialog == null)
      this.mUnrestricteSEDialog = new AlertDialog.Builder(getActivity()).setTitle(paramInt).setMessage(2131296308).setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseCardFragment.this.unrestrictEse();
        }
      }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseCardFragment.this.getActivity().finish();
        }
      }).setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          BaseCardFragment.this.getActivity().finish();
        }
      }).create();
    this.mUnrestricteSEDialog.setTitle(paramInt);
    this.mUnrestricteSEDialog.show();
  }

  public void onResume()
  {
    super.onResume();
    if (!showErrorWhenNFCOff());
    while (true)
    {
      return;
      if (this.mCheckNfcEEStatusTask != null)
        this.mCheckNfcEEStatusTask.cancel();
      this.mCheckNfcEEStatusTask = new CheckNfcEEStatusTask(getActivity());
      this.mCheckNfcEEStatusTask.addListener(this.mCheckNfcEEListener);
      this.mTaskManager.add(this.mCheckNfcEEStatusTask);
    }
  }

  protected boolean showErrorWhenNFCOff()
  {
    return true;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.BaseCardFragment
 * JD-Core Version:    0.6.0
 */