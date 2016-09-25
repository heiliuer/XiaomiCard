package com.miui.tsmclient.ui;

import android.accounts.Account;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import com.miui.tsmclient.account.TSMAccountManager;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.UiUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import miui.app.Fragment;
import miui.app.ProgressDialog;

public class BaseFragment extends Fragment
{
  protected ProgressDialog mProgressDialog;
  private TSMAccountManager mTSMAccountManager;

  private void checkLoginStatus()
  {
    if (!needLogin());
    while (true)
    {
      return;
      Account localAccount = this.mTSMAccountManager.getXiaomiAccount(getActivity());
      if ((localAccount != null) && (localAccount.name != null))
        continue;
      new AlertDialog.Builder(getActivity()).setTitle(2131296314).setMessage(2131296312).setPositiveButton(2131296318, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseFragment.this.getActivity().finish();
        }
      }).setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramDialogInterface)
        {
          BaseFragment.this.getActivity().finish();
        }
      }).setCancelable(false).create().show();
    }
  }

  protected void dismissDialog()
  {
    UiUtils.dismissProgressDialog(this.mProgressDialog);
  }

  protected void doCreate(Bundle paramBundle)
  {
  }

  protected void handleMessage(Message paramMessage, miui.app.Activity paramActivity)
  {
  }

  protected boolean needLogin()
  {
    return false;
  }

  public final void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setThemeRes(2131361844);
    this.mProgressDialog = new ProgressDialog(getActivity());
    this.mTSMAccountManager = new TSMAccountManager();
    doCreate(paramBundle);
  }

  public void onDestroy()
  {
    dismissDialog();
    super.onDestroy();
  }

  public void onPause()
  {
    AnalyticManager.recordPageEnd();
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
    checkLoginStatus();
    AnalyticManager.recordPageStart(getActivity(), getClass().getSimpleName());
  }

  public void sendBroadcast(String paramString1, String paramString2, Parcelable paramParcelable)
  {
    Intent localIntent = new Intent(paramString1);
    localIntent.putExtra(paramString2, paramParcelable);
    LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(localIntent);
  }

  public void sendBroadcast(String paramString1, String paramString2, List<? extends Parcelable> paramList)
  {
    Intent localIntent = new Intent(paramString1);
    if ((paramList instanceof ArrayList));
    for (ArrayList localArrayList = (ArrayList)paramList; ; localArrayList = new ArrayList(paramList))
    {
      localIntent.putParcelableArrayListExtra(paramString2, localArrayList);
      LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(localIntent);
      return;
    }
  }

  protected void showDialog(int paramInt)
  {
    UiUtils.showProgressDialog(getActivity(), this.mProgressDialog, paramInt);
  }

  protected void showDialog(String paramString)
  {
    UiUtils.showProgressDialog(this.mProgressDialog, paramString);
  }

  public class CardHandler extends Handler
  {
    private final WeakReference<miui.app.Activity> mActivityWeakReference;

    public CardHandler(miui.app.Activity arg2)
    {
      Object localObject;
      this.mActivityWeakReference = new WeakReference(localObject);
    }

    public void handleMessage(Message paramMessage)
    {
      miui.app.Activity localActivity = (miui.app.Activity)this.mActivityWeakReference.get();
      if (localActivity == null);
      while (true)
      {
        return;
        if ((localActivity.isFinishing()) || (BaseFragment.this.isRemoving()) || (BaseFragment.this.isDetached()))
        {
          LogUtils.d("This fragment has been detached,do not handle message.");
          continue;
        }
        BaseFragment.this.handleMessage(paramMessage, localActivity);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.BaseFragment
 * JD-Core Version:    0.6.0
 */