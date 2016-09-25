package com.miui.tsmclient.ui;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v13.app.FragmentCompat;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.model.ErrorCode;
import com.miui.tsmclient.model.bankcard.BankCardManager;
import com.miui.tsmclient.receiver.SmsCaptchaBroadcastReceiver;
import com.miui.tsmclient.receiver.SmsCaptchaBroadcastReceiver.SmsCaptchaMessageListener;
import com.miui.tsmclient.task.OpenFindDeviceTask;
import com.miui.tsmclient.task.TaskListener;
import com.miui.tsmclient.ui.bankcard.BindBankCardFinishedActivity;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NfcUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import miui.app.ProgressDialog;
import miui.util.async.Task;
import miui.util.async.TaskManager;
import miui.widget.ClearableEditText;

public class VerifyCodeFragment extends BaseCardFragment<BankCardInfo>
{
  private static final int GUIDE_PASSWORD_REQUEST_CODE = 8;
  public static final String KEY_PHONE_NUM = "phone_num";
  private static final int MSG_COMMON = 5;
  private static final int MSG_OPEN_FIND_DEVICE = 7;
  private static final int MSG_SEND_SMS_FAILURE = 3;
  private static final int MSG_SEND_SMS_SUCCESS = 4;
  private static final int MSG_TIME_COUNT = 1;
  private static final int MSG_VERIFY_SUCCESS = 2;
  private static final int MSG_WAIT_INSTALL = 6;
  private static final int TIME_COUNT = 60;
  private BankCardManager mBankCardManager;
  private View.OnClickListener mClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
      case 2131492890:
      case 2131493101:
      }
      while (true)
      {
        return;
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(VerifyCodeFragment.this.getActivity());
        localBuilder.setItems(VerifyCodeFragment.this.mUserTerms, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            switch (paramInt)
            {
            default:
            case 0:
            }
            while (true)
            {
              return;
              WebViewActivity.showWebPage(VerifyCodeFragment.this.getActivity(), ((BankCardInfo)VerifyCodeFragment.this.mCardInfo).mUserTerms, VerifyCodeFragment.this.mCardInfo);
            }
          }
        });
        localBuilder.show();
        continue;
        VerifyCodeFragment.this.showDialog(2131296274);
        if (VerifyCodeFragment.this.mOpenFindDeviceTask.isRunning())
          continue;
        VerifyCodeFragment.this.mTaskManager.add(VerifyCodeFragment.this.mOpenFindDeviceTask);
      }
    }
  };
  private CheckBox mContractsCheckBox;
  private Handler mHandler;
  private OpenFindDeviceTask mOpenFindDeviceTask;
  private TaskListener mOpenFindDeviceTaskListener = new TaskListener()
  {
    public Object onResult(TaskManager paramTaskManager, Task<?> paramTask, Object paramObject)
    {
      VerifyCodeFragment.this.mHandler.obtainMessage(7, paramObject).sendToTarget();
      return paramObject;
    }
  };
  private View.OnClickListener mResendClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      VerifyCodeFragment.this.showDialog(2131296274);
      VerifyCodeFragment.this.freshVerifyBtnState(VerifyCodeFragment.this.mSmsCodeEt.getEditableText(), VerifyCodeFragment.this.mContractsCheckBox.isChecked());
      VerifyCodeFragment.this.startTimeCount();
      VerifyCodeFragment.this.sendSmsCode();
    }
  };
  private Button mRetryBtn;
  private ClearableEditText mSmsCodeEt;
  private TaskManager mTaskManager;
  private int mTimeCount = 60;
  private String[] mUserTerms;
  private Button mVerifyBtn;
  private SmsCaptchaBroadcastReceiver smsReceive;

  private void cancelTimeCount()
  {
    this.mHandler.removeMessages(1);
    this.mRetryBtn.setText(getString(2131296444));
    this.mRetryBtn.setEnabled(true);
    this.mTimeCount = 60;
  }

  private void freshVerifyBtnState(Editable paramEditable, boolean paramBoolean)
  {
    if ((paramEditable.length() == getResources().getInteger(2131558401)) && (paramBoolean))
      this.mVerifyBtn.setEnabled(true);
    while (true)
    {
      return;
      this.mVerifyBtn.setEnabled(false);
    }
  }

  private void sendSmsCode()
  {
    this.mBankCardManager.requestVerificationCode(getActivity(), this.mCardInfo, ((BankCardInfo)this.mCardInfo).mVCReferenceId, new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        if (paramInt == 3011)
          VerifyCodeFragment.this.mHandler.obtainMessage(2, paramInt, 0).sendToTarget();
        while (true)
        {
          return;
          VerifyCodeFragment.this.mHandler.obtainMessage(5, paramInt, 0).sendToTarget();
        }
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        VerifyCodeFragment.this.mHandler.obtainMessage(4).sendToTarget();
      }
    });
  }

  private void startTimeCount()
  {
    this.mHandler.sendEmptyMessageDelayed(1, 1000L);
    this.mRetryBtn.setEnabled(false);
  }

  private void verifyCode()
  {
    this.mBankCardManager.verifyVerificationCode(getActivity(), this.mCardInfo, ((BankCardInfo)this.mCardInfo).mVCReferenceId, this.mSmsCodeEt.getText().toString(), new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        if (paramInt == 3011)
          VerifyCodeFragment.this.mHandler.obtainMessage(2, paramInt, 0).sendToTarget();
        while (true)
        {
          return;
          VerifyCodeFragment.this.mHandler.obtainMessage(5, paramInt, 0).sendToTarget();
        }
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        VerifyCodeFragment.this.mHandler.obtainMessage(2).sendToTarget();
      }
    });
  }

  public void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mBankCardManager = new BankCardManager(getActivity());
    this.mProgressDialog.setCancelable(true);
    this.mHandler = new BaseFragment.CardHandler(this, (miui.app.Activity)getActivity());
    this.mTaskManager = TaskManager.getDefault();
    this.mOpenFindDeviceTask = new OpenFindDeviceTask(getActivity());
    this.mOpenFindDeviceTask.addListener(this.mOpenFindDeviceTaskListener);
    this.mUserTerms = getResources().getStringArray(2131427333);
    sendSmsCode();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = "VerifyCodeFragment";
    AnalyticManager.recordEvent("bank", String.format("key_enter/%s", arrayOfObject));
  }

  protected void handleMessage(Message paramMessage, miui.app.Activity paramActivity)
  {
    switch (paramMessage.what)
    {
    case 6:
    default:
    case 1:
    case 2:
    case 5:
    case 3:
    case 4:
    case 7:
    }
    while (true)
    {
      return;
      if (this.mTimeCount == 0)
      {
        cancelTimeCount();
        continue;
      }
      Button localButton = this.mRetryBtn;
      Object[] arrayOfObject = new Object[1];
      int i = this.mTimeCount;
      this.mTimeCount = (i - 1);
      arrayOfObject[0] = Integer.valueOf(i);
      localButton.setText(getString(2131296436, arrayOfObject));
      this.mHandler.sendEmptyMessageDelayed(1, 1000L);
      continue;
      boolean bool = NfcUtils.disableAndroidBeam(getActivity().getApplicationContext());
      LogUtils.d("issue bank card success, disable android beam success:" + bool);
      dismissDialog();
      if (paramMessage.arg1 == 3011)
        UiUtils.showToast(getActivity().getApplicationContext(), ErrorCode.findText(getActivity(), paramMessage.arg1));
      Intent localIntent = new Intent(getActivity(), BindBankCardFinishedActivity.class);
      localIntent.putExtra("card_info", this.mCardInfo);
      startActivityForResult(localIntent, 8);
      continue;
      dismissDialog();
      if (paramMessage.arg1 == 0)
      {
        UiUtils.showToast(getActivity(), 2131296301);
        continue;
      }
      UiUtils.showToast(getActivity().getApplicationContext(), ErrorCode.findText(getActivity(), paramMessage.arg1));
      if ((paramMessage.arg1 != 3010) && (paramMessage.arg1 != 3021))
        continue;
      getActivity().setResult(1);
      getActivity().finish();
      continue;
      cancelTimeCount();
      if (paramMessage.arg1 == 3021)
      {
        UiUtils.showToast(getActivity().getApplicationContext(), ErrorCode.findText(getActivity(), paramMessage.arg1));
        paramActivity.setResult(1);
        paramActivity.finish();
        continue;
      }
      dismissDialog();
      UiUtils.showToast(getActivity(), 2131296442);
      continue;
      dismissDialog();
      this.mHandler.obtainMessage(6).sendToTarget();
      UiUtils.showToast(getActivity(), 2131296441);
      continue;
      if (((Boolean)paramMessage.obj).booleanValue())
      {
        verifyCode();
        continue;
      }
      dismissDialog();
      UiUtils.showToast(getActivity(), 2131296296);
    }
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.smsReceive = new SmsCaptchaBroadcastReceiver(new SmsCaptchaBroadcastReceiver.SmsCaptchaMessageListener()
    {
      public void onReceived(String paramString)
      {
        VerifyCodeFragment.this.mSmsCodeEt.setText(paramString);
        VerifyCodeFragment.this.mSmsCodeEt.setSelection(VerifyCodeFragment.this.mSmsCodeEt.getText().length());
      }
    });
    if (!FragmentCompat.shouldShowRequestPermissionRationale(this, "android.permission.RECEIVE_SMS"))
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "android.permission.RECEIVE_SMS";
      FragmentCompat.requestPermissions(this, arrayOfString, 1);
    }
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
    getActivity().registerReceiver(this.smsReceive, localIntentFilter);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      getActivity().setResult(paramInt2, paramIntent);
      getActivity().finish();
    }
  }

  public void onDestroy()
  {
    this.mHandler.removeCallbacksAndMessages(null);
    this.mBankCardManager.release();
    this.mTaskManager.shutdown();
    if (this.smsReceive != null)
      getActivity().unregisterReceiver(this.smsReceive);
    super.onDestroy();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903088, paramViewGroup, false);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.mSmsCodeEt = ((ClearableEditText)paramView.findViewById(2131493099));
    this.mRetryBtn = ((Button)paramView.findViewById(2131493100));
    Button localButton = this.mRetryBtn;
    int i = this.mTimeCount;
    this.mTimeCount = (i - 1);
    localButton.setText(String.valueOf(i));
    this.mRetryBtn.setOnClickListener(this.mResendClickListener);
    this.mVerifyBtn = ((Button)paramView.findViewById(2131493101));
    this.mVerifyBtn.setOnClickListener(this.mClickListener);
    TextView localTextView1 = (TextView)paramView.findViewById(2131493098);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = ((BankCardInfo)this.mCardInfo).mPhoneLastDigits;
    localTextView1.setText(getString(2131296434, arrayOfObject));
    this.mSmsCodeEt.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramEditable)
      {
        VerifyCodeFragment.this.freshVerifyBtnState(paramEditable, VerifyCodeFragment.this.mContractsCheckBox.isChecked());
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }
    });
    this.mContractsCheckBox = ((CheckBox)paramView.findViewById(2131492889));
    this.mContractsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
      {
        VerifyCodeFragment.this.freshVerifyBtnState(VerifyCodeFragment.this.mSmsCodeEt.getEditableText(), paramBoolean);
      }
    });
    TextView localTextView2 = (TextView)paramView.findViewById(2131492890);
    localTextView2.getPaint().setFlags(8);
    localTextView2.setOnClickListener(this.mClickListener);
    startTimeCount();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.VerifyCodeFragment
 * JD-Core Version:    0.6.0
 */