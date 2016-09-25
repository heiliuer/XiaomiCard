package com.miui.tsmclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.miui.tsmclient.model.ErrorCode;
import com.miui.tsmclient.util.FormatterUtils;
import com.miui.tsmclient.util.FormatterUtils.FormatterType;
import com.miui.tsmclient.util.StringUtils;
import com.miui.tsmclient.util.UiUtils;
import miui.widget.ClearableEditText;

public class SendSmsFragment extends BaseCardFragment
{
  private static final int MSG_COMMON = 0;
  private static final int MSG_SEND_SMS_CODE_SUCCESS = 1;
  private View.OnClickListener mClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      if (paramView == SendSmsFragment.this.mSendSmsBtn)
      {
        SendSmsFragment.access$202(SendSmsFragment.this, FormatterUtils.clean(SendSmsFragment.this.mPhoneNumText.getText().toString()));
        if (StringUtils.checkPhoneNum(SendSmsFragment.this.mPhoneNum))
          break label68;
        UiUtils.showToast(SendSmsFragment.this.getActivity(), SendSmsFragment.this.getString(2131296438));
      }
      while (true)
      {
        return;
        label68: SendSmsFragment.this.showDialog(2131296439);
      }
    }
  };
  private Bundle mData;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 0:
      }
      while (true)
      {
        return;
        SendSmsFragment.this.dismissDialog();
        SendSmsFragment.this.verifySmsCode();
        continue;
        SendSmsFragment.this.dismissDialog();
        if (paramMessage.arg1 == 0)
        {
          UiUtils.showToast(SendSmsFragment.this.getActivity(), 2131296301);
          continue;
        }
        UiUtils.showToast(SendSmsFragment.this.getActivity(), ErrorCode.findText(SendSmsFragment.this.getActivity(), paramMessage.arg1));
      }
    }
  };
  private String mPhoneNum;
  private ClearableEditText mPhoneNumText;
  private Button mSendSmsBtn;

  private void verifySmsCode()
  {
    this.mData.putString("phone_num", this.mPhoneNum);
    this.mData.putParcelable("card_info", this.mCardInfo);
    VerifyCodeActivity.verifySmsCode(getActivity(), this.mData, this.mCardInfo);
  }

  public void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mData = getArguments();
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
    super.onDestroy();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903068, paramViewGroup, false);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    this.mPhoneNumText = ((ClearableEditText)paramView.findViewById(2131493019));
    FormatterUtils.setFormatter(this.mPhoneNumText, FormatterUtils.FormatterType.TYPE_PHONE);
    this.mSendSmsBtn = ((Button)paramView.findViewById(2131493020));
    this.mSendSmsBtn.setOnClickListener(this.mClickListener);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.SendSmsFragment
 * JD-Core Version:    0.6.0
 */