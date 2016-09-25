package com.miui.tsmclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NfcUtils;
import com.miui.tsmclient.util.PrefUtils;
import java.util.List;

public class TSMResultFragment extends BaseCardFragment
{
  public static String FROM_BANK_CARD_APPLY;
  public static String FROM_BANK_CARD_DELETE;
  public static String FROM_NFC_RECHARGE;
  public static String FROM_OPEN_CARD;
  public static String FROM_RECHARGE;
  public static String KEY_INTENT_FROM = "intent_from";
  private String PACKAGE_NAME_MIPAY = "com.mipay.wallet";
  private String TAG = "TSMResultFragment";
  private int mAmount;
  private View.OnClickListener mFinishListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      TSMResultFragment.this.doFinish();
    }
  };
  private String mIntentFrom;

  static
  {
    FROM_NFC_RECHARGE = "nfc_recharge";
    FROM_RECHARGE = "recharge";
    FROM_OPEN_CARD = "open_card";
    FROM_BANK_CARD_APPLY = "bank_card_apply";
    FROM_BANK_CARD_DELETE = "bank_card_delete";
  }

  private void doFinish()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("card_info", this.mCardInfo);
    getActivity().setResult(-1, localIntent);
    getActivity().finish();
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    Bundle localBundle = getArguments();
    if (localBundle != null)
    {
      this.mAmount = localBundle.getInt("amount");
      this.mIntentFrom = localBundle.getString(KEY_INTENT_FROM);
      if (TextUtils.isEmpty(this.mIntentFrom))
        throw new IllegalArgumentException(this.TAG + " missing intent_from");
    }
  }

  protected void doNewActivityIntent(Intent paramIntent)
  {
    Tag localTag = (Tag)paramIntent.getParcelableExtra("android.nfc.extra.TAG");
    Intent localIntent = new Intent("android.nfc.action.TECH_DISCOVERED");
    localIntent.setPackage(this.PACKAGE_NAME_MIPAY);
    localIntent.addFlags(67108864);
    localIntent.putExtra("android.nfc.extra.TAG", localTag);
    if (localTag != null)
    {
      List localList = getActivity().getPackageManager().queryIntentActivities(localIntent, 32);
      if ((localList != null) && (!localList.isEmpty()))
      {
        startActivity(localIntent);
        getActivity().finish();
      }
    }
  }

  protected void onBackPressed()
  {
    doFinish();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903076, paramViewGroup, false);
  }

  public void onPause()
  {
    if (TextUtils.equals(this.mIntentFrom, FROM_NFC_RECHARGE))
      NfcUtils.stopPoll(getActivity());
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
    if (TextUtils.equals(this.mIntentFrom, FROM_NFC_RECHARGE))
      NfcUtils.startPoll(getActivity());
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    boolean bool1 = true;
    super.onViewCreated(paramView, paramBundle);
    TextView localTextView1 = (TextView)paramView.findViewById(2131493058);
    TextView localTextView2 = (TextView)paramView.findViewById(2131493056);
    TextView localTextView3 = (TextView)paramView.findViewById(2131493059);
    Button localButton = (Button)paramView.findViewById(2131493060);
    CheckBox localCheckBox = (CheckBox)paramView.findViewById(2131493057);
    localButton.setOnClickListener(this.mFinishListener);
    if (TextUtils.equals(this.mIntentFrom, FROM_NFC_RECHARGE))
    {
      localTextView2.setText(getString(2131296378));
      Object[] arrayOfObject = new Object[bool1];
      arrayOfObject[0] = Integer.valueOf(this.mAmount);
      localTextView1.setText(getString(2131296379, arrayOfObject));
      localTextView1.setVisibility(0);
      localTextView3.setText(getString(2131296388));
    }
    while (true)
    {
      return;
      if (TextUtils.equals(this.mIntentFrom, FROM_OPEN_CARD))
      {
        localTextView2.setText(getString(2131296402));
        localButton.setVisibility(0);
        String str = PrefUtils.getLongPressVolumeDownState(getActivity());
        if (!"Street-snap".equals(str));
        while (true)
        {
          localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(str)
          {
            public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
            {
              LogUtils.d("TSMResultFragment onCheckedChanged:" + paramBoolean);
              Activity localActivity = TSMResultFragment.this.getActivity();
              if (paramBoolean);
              for (String str = "public_transportation_shortcuts"; ; str = this.val$longPressState)
              {
                PrefUtils.setLongPressVolumeDownState(localActivity, str);
                return;
              }
            }
          });
          localCheckBox.setText(2131296403);
          localCheckBox.setChecked(bool1);
          localCheckBox.setVisibility(0);
          boolean bool2 = NfcUtils.disableAndroidBeam(getActivity().getApplicationContext());
          LogUtils.d("issue transit card success, disable android beam success:" + bool2);
          break;
          bool1 = false;
        }
      }
      if (TextUtils.equals(this.mIntentFrom, FROM_RECHARGE))
      {
        localTextView2.setText(getString(2131296378));
        localButton.setVisibility(0);
        continue;
      }
      if (!TextUtils.equals(this.mIntentFrom, FROM_BANK_CARD_DELETE))
        continue;
      localTextView2.setText(getString(2131296467));
      localButton.setVisibility(0);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.TSMResultFragment
 * JD-Core Version:    0.6.0
 */