package com.miui.tsmclient.ui.bankcard;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.tsmclient.SEInteractionService;
import com.miui.tsmclient.SEInteractionService.LocalBinder;
import com.miui.tsmclient.SEInteractionService.SEInteractionListener;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.model.ErrorCode;
import com.miui.tsmclient.model.bankcard.BankCardManager;
import com.miui.tsmclient.ui.BaseCardFragment;
import com.miui.tsmclient.util.StringUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import miui.R.drawable;
import miui.app.ProgressDialog;

public class BankCardDetailFragment extends BaseCardFragment<BankCardInfo>
{
  private static final int DELETE_CARD = 1;
  private static final int LOCK_CARD = 2;
  private static final int MSG_COMMON = 0;
  private static final int MSG_DELETE_SUCCESS = 1;
  private static final int MSG_LOCK_SUCCESS = 2;
  private static final int SET_DEFAULT_CARD = 3;
  private BankCardManager mBankCardManage;
  private ImageView mBankLogoIv;
  private TextView mBankNameTv;
  private TextView mCardNumTv;
  private TextView mCardTypeTv;
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      BankCardDetailFragment.access$202(BankCardDetailFragment.this, ((SEInteractionService.LocalBinder)paramIBinder).getService());
      BankCardDetailFragment.this.registerListener();
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      BankCardDetailFragment.this.unbindService();
    }
  };
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 0:
      case 1:
      case 2:
      }
      while (true)
      {
        return;
        BankCardDetailFragment.this.dismissDialog();
        if (paramMessage.arg1 == 0)
        {
          UiUtils.showToast(BankCardDetailFragment.this.getActivity(), 2131296301);
          continue;
        }
        UiUtils.showToast(BankCardDetailFragment.this.getActivity(), ErrorCode.findText(BankCardDetailFragment.this.getActivity(), paramMessage.arg1));
        continue;
        UiUtils.showToast(BankCardDetailFragment.this.getActivity(), 2131296467);
        BankCardDetailFragment.this.getActivity().setResult(-1);
        BankCardDetailFragment.this.getActivity().finish();
        continue;
        UiUtils.showToast(BankCardDetailFragment.this.getActivity(), 2131296524);
        BankCardDetailFragment.this.getActivity().setResult(-1);
        BankCardDetailFragment.this.getActivity().finish();
      }
    }
  };
  private TextView mPanTv;
  private SEInteractionService.SEInteractionListener mSeInteractionListener = new SEInteractionService.SEInteractionListener()
  {
    public void onFinished(int paramInt)
    {
      if (paramInt == 0)
        BankCardDetailFragment.this.mHandler.obtainMessage(1).sendToTarget();
      while (true)
      {
        return;
        BankCardDetailFragment.this.mHandler.obtainMessage(0, paramInt, 0).sendToTarget();
      }
    }

    public void onProgressChanged(int paramInt)
    {
    }

    public void onStart()
    {
    }
  };
  private SEInteractionService mSeInteractionService;
  private SetDefaultCardTask mSetDefaultCardTask;

  private void bindService()
  {
    Activity localActivity = getActivity();
    localActivity.bindService(new Intent(localActivity, SEInteractionService.class), this.mConnection, 1);
  }

  private void initView()
  {
    this.mCardNumTv.setText(StringUtils.tail(((BankCardInfo)this.mCardInfo).mVCardNumber, 4));
    this.mPanTv.setText(((BankCardInfo)this.mCardInfo).mPanLastDigits);
    TextView localTextView = this.mCardTypeTv;
    if (((BankCardInfo)this.mCardInfo).mBankCardType == 2);
    for (int i = 2131296469; ; i = 2131296468)
    {
      localTextView.setText(i);
      return;
    }
  }

  private void registerListener()
  {
    this.mSeInteractionService.setSEInteractionListener(this.mSeInteractionListener);
  }

  private void unbindService()
  {
    if (this.mSeInteractionService != null)
      getActivity().unbindService(this.mConnection);
    this.mSeInteractionService = null;
  }

  private void unregisterListener()
  {
    if (this.mSeInteractionService != null)
      this.mSeInteractionService.setSEInteractionListener(null);
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mProgressDialog.setCancelable(false);
    this.mBankCardManage = new BankCardManager(getActivity());
    bindService();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    MenuItem localMenuItem1 = paramMenu.add(0, 1, 0, getString(2131296525));
    localMenuItem1.setIcon(R.drawable.action_button_delete_light);
    localMenuItem1.setShowAsAction(1);
    MenuItem localMenuItem2 = paramMenu.add(0, 3, 0, getString(2131296526));
    localMenuItem2.setIcon(R.drawable.action_button_delete_light);
    localMenuItem2.setShowAsAction(8);
    if (((BankCardInfo)this.mCardInfo).mVCStatus != 2)
    {
      MenuItem localMenuItem3 = paramMenu.add(0, 2, 0, getString(2131296523));
      localMenuItem3.setIcon(R.drawable.action_button_new_light);
      localMenuItem3.setShowAsAction(8);
    }
    return true;
  }

  public void onDestroy()
  {
    if (this.mSetDefaultCardTask != null)
      this.mSetDefaultCardTask.cancel(true);
    this.mHandler.removeCallbacksAndMessages(null);
    unbindService();
    unregisterListener();
    super.onDestroy();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903044, null);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      showDialog(2131296274);
      SEInteractionService.deleteCard(getActivity(), this.mCardInfo, null);
      continue;
      showDialog(2131296274);
      this.mBankCardManage.lock(getActivity(), this.mCardInfo, null, new MiTsmCallback()
      {
        public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
        {
          BankCardDetailFragment.this.mHandler.obtainMessage(0, paramInt, 0).sendToTarget();
        }

        public void onSuccess(int paramInt, Object[] paramArrayOfObject)
        {
          BankCardDetailFragment.this.mHandler.obtainMessage(2).sendToTarget();
        }
      });
      continue;
      if (this.mSetDefaultCardTask != null)
        this.mSetDefaultCardTask.cancel(true);
      this.mSetDefaultCardTask = new SetDefaultCardTask(getActivity());
      this.mSetDefaultCardTask.execute(new Void[0]);
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.mBankLogoIv = ((ImageView)paramView.findViewById(2131492892));
    this.mBankNameTv = ((TextView)paramView.findViewById(2131492893));
    this.mCardTypeTv = ((TextView)paramView.findViewById(2131492894));
    this.mCardNumTv = ((TextView)paramView.findViewById(2131492881));
    this.mPanTv = ((TextView)paramView.findViewById(2131492883));
    initView();
  }

  private class SetDefaultCardTask extends AsyncTask<Void, Void, Boolean>
  {
    private Activity mContext;

    public SetDefaultCardTask(Activity arg2)
    {
      Object localObject;
      this.mContext = localObject;
    }

    protected Boolean doInBackground(Void[] paramArrayOfVoid)
    {
      return Boolean.valueOf(SysUtils.setDefaultCard(this.mContext, ((BankCardInfo)BankCardDetailFragment.this.mCardInfo).mAid));
    }

    protected void onPostExecute(Boolean paramBoolean)
    {
      if (paramBoolean.booleanValue())
        UiUtils.showToast(BankCardDetailFragment.this.getActivity(), 2131296507);
      while (true)
      {
        return;
        UiUtils.showToast(BankCardDetailFragment.this.getActivity(), 2131296508);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.bankcard.BankCardDetailFragment
 * JD-Core Version:    0.6.0
 */