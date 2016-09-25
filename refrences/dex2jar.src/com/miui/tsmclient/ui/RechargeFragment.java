package com.miui.tsmclient.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import com.miui.tsmclient.entity.FeeInfo;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;
import com.miui.tsmclient.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class RechargeFragment<T extends PayableCardInfo> extends BaseRechargeFragment<T>
  implements RechargeAdapter.OnRechargeAmountChangeListener
{
  private RechargeAdapter mAdapter;
  private GridView mGridView;
  private View.OnClickListener mOnClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
      case 2131492987:
      }
      while (true)
      {
        return;
        RechargeFragment.this.onRechargeClicked();
      }
    }
  };
  private TextView mTvDepositAmount;
  private TextView mTvDepositInfo;
  private TextView mTvFeeMsg;
  private TextView mTvInfo;
  private TextView mTvIssueFee;
  private TextView mTvRechargeAmount;
  private TextView mTvValueInvalid;

  private void onRechargeClicked()
  {
    if (((((PayableCardInfo)this.mCardInfo).mCardBalance >= 15000) || (this.mFeeInfo.mRechargeFee >= 10000)) && (!PrefUtils.getBoolean(getActivity(), "key_popup_recharge_tips_already", false)))
    {
      new AlertDialog.Builder(getActivity()).setTitle(2131296399).setMessage(2131296400).setPositiveButton(2131296401, null).create().show();
      PrefUtils.putBoolean(getActivity(), "key_popup_recharge_tips_already", true);
    }
    while (true)
    {
      return;
      clickRechargeBtn();
    }
  }

  protected void initView(View paramView)
  {
    this.mContentView = paramView.findViewById(2131492977);
    this.mLayoutError = paramView.findViewById(2131492989);
    this.mTvInfo = ((TextView)paramView.findViewById(2131492978));
    this.mTvInfo.setText(2131296359);
    this.mBtnPay = ((Button)paramView.findViewById(2131492987));
    this.mBtnPay.setOnClickListener(this.mOnClickListener);
    int i = getResources().getInteger(2131558407);
    this.mGridView = ((GridView)paramView.findViewById(2131492979));
    this.mAdapter = new RechargeAdapter(getActivity(), this, i, ((PayableCardInfo)this.mCardInfo).mHasIssue);
    this.mGridView.setAdapter(this.mAdapter);
    this.mTvDepositInfo = ((TextView)paramView.findViewById(2131492982));
    this.mTvDepositInfo.setText(2131296362);
    this.mTvDepositAmount = ((TextView)paramView.findViewById(2131492983));
    TextView localTextView1 = this.mTvDepositAmount;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Float.valueOf(0.0F);
    localTextView1.setText(getString(2131296365, arrayOfObject1));
    this.mTvIssueFee = ((TextView)paramView.findViewById(2131492981));
    if ((!((PayableCardInfo)this.mCardInfo).mHasIssue) && (((PayableCardInfo)this.mCardInfo).mIssueFee > 0))
    {
      paramView.findViewById(2131492980).setVisibility(0);
      this.mTvIssueFee.setVisibility(0);
    }
    this.mContentView.setVisibility(8);
    getFeesFromServer();
    this.mTvRechargeAmount = ((TextView)paramView.findViewById(2131492985));
    TextView localTextView2 = this.mTvRechargeAmount;
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Float.valueOf(0.0F);
    localTextView2.setText(getString(2131296361, arrayOfObject2));
    this.mTvValueInvalid = ((TextView)paramView.findViewById(2131492986));
    this.mTvFeeMsg = ((TextView)paramView.findViewById(2131492988));
    this.mTvFeeMsg.setVisibility(0);
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903063, paramViewGroup, false);
  }

  public void onRechargeAmountChange(TextView paramTextView, int paramInt, String paramString)
  {
    String str1 = paramString;
    if ((((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList() != null) && (((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().size() > paramInt))
      this.mFeeInfo = ((FeeInfo)((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().get(paramInt));
    this.mTvValueInvalid.setVisibility(8);
    if (TextUtils.isEmpty(str1))
      str1 = "0";
    try
    {
      this.mAmountValue = Integer.valueOf(str1).intValue();
      if (!this.mBtnPay.isEnabled())
        this.mBtnPay.setEnabled(true);
      if ((((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList() != null) && (((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().size() > paramInt))
      {
        TextView localTextView1 = this.mTvRechargeAmount;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Float.valueOf(this.mFeeInfo.mPayFee / 100.0F);
        localTextView1.setText(getString(2131296361, arrayOfObject1));
        TextView localTextView2 = this.mTvDepositAmount;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Float.valueOf(this.mFeeInfo.mRechargeFee / 100.0F);
        localTextView2.setText(getString(2131296365, arrayOfObject2));
        if (!((PayableCardInfo)this.mCardInfo).mHasIssue)
        {
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = Float.valueOf(((PayableCardInfo)this.mCardInfo).mIssueFee / 100.0F);
          str2 = getString(2131296364, arrayOfObject3);
          if (this.mFeeInfo.mPayFee - this.mFeeInfo.mRechargeFee == ((PayableCardInfo)this.mCardInfo).mIssueFee)
            break label477;
          int i = this.mFeeInfo.mPayFee - this.mFeeInfo.mRechargeFee;
          if (i < 0)
            i = 0;
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = Float.valueOf(i / 100.0F);
          String str3 = getString(2131296364, arrayOfObject4);
          SpannableString localSpannableString = new SpannableString(str2 + " " + str3);
          localSpannableString.setSpan(new StrikethroughSpan(), 0, str2.length(), 33);
          this.mTvIssueFee.setText(localSpannableString);
        }
        this.mTvFeeMsg.setText(this.mFeeInfo.mMsg);
        if (this.mFeeInfo.mPayFee == 0)
          this.mBtnPay.setText(17039370);
      }
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
      {
        String str2;
        LogUtils.e("onRechargeAmountChange()called!Amount is not a valid number!", localNumberFormatException);
        continue;
        label477: this.mTvIssueFee.setText(str2);
      }
    }
  }

  protected void processRechargeInvalidValue(String paramString)
  {
    super.processRechargeInvalidValue(paramString);
    TextView localTextView = this.mTvValueInvalid;
    String str = getString(2131296398);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramString;
    localTextView.setText(String.format(str, arrayOfObject));
    this.mTvValueInvalid.setVisibility(0);
  }

  protected void resetViewToNormalStatus()
  {
    this.mBtnPay.setEnabled(true);
    dismissDialog();
  }

  protected void showContentView()
  {
    getActivity().getActionBar().setTitle(((PayableCardInfo)this.mCardInfo).mCardName);
    this.mContentView.setVisibility(0);
    this.mLayoutError.setVisibility(8);
    int i = ((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().size();
    int j = getResources().getInteger(2131558407);
    ArrayList localArrayList = new ArrayList();
    for (int k = 0; k < i; k++)
      localArrayList.add(StringUtils.covertFloatToString(((FeeInfo)((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().get(k)).mRechargeFee / 100.0F));
    if (i % j != 0)
      for (int m = i % j; m < j; m++)
        localArrayList.add("");
    this.mAdapter.updateData(localArrayList);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.RechargeFragment
 * JD-Core Version:    0.6.0
 */