package com.miui.tsmclient.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.miui.tsmclient.entity.CardUIInfo;
import com.miui.tsmclient.entity.FeeInfo;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.ui.widget.RechargeItemView;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;

public class IssueRechargeFragment<T extends PayableCardInfo> extends BaseRechargeFragment<T>
{
  private CheckBox mCheckBox;
  private CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener()
  {
    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      if (paramCompoundButton == IssueRechargeFragment.this.mCheckBox)
        IssueRechargeFragment.this.mBtnPay.setEnabled(paramBoolean);
    }
  };
  private ArrayList<String> mFees = new ArrayList();
  private ImageView mIvCurrentCardBg;
  private View.OnClickListener mOnClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
      case 2131492987:
      case 2131492950:
      }
      while (true)
      {
        return;
        IssueRechargeFragment.this.clickRechargeBtn();
        continue;
        WebViewActivity.showWebPage(IssueRechargeFragment.this.getActivity(), IssueRechargeFragment.this.getProtocolUrl(), IssueRechargeFragment.this.mCardInfo);
      }
    }
  };
  private TextView mTvCurrentCardName;
  private RechargeItemView mTvDiscountItem;
  private RechargeItemView mTvIssueItem;
  private RechargeItemView mTvPayItem;
  private TextView mTvProtocols;
  private RechargeItemView mTvRechargeItem;

  private String getProtocolUrl()
  {
    return "http://cdn.fds.api.xiaomi.com/mipay.nextpay/app/protocols_" + ((PayableCardInfo)this.mCardInfo).mCardType.toLowerCase() + ".htm";
  }

  private void rechargeAmountChange(int paramInt)
  {
    if ((((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList() != null) && (((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().size() > paramInt))
      this.mFeeInfo = ((FeeInfo)((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().get(paramInt));
    updateRechargeListView();
  }

  private void updateRechargeListView()
  {
    int i;
    if ((!((PayableCardInfo)this.mCardInfo).mHasIssue) && (((PayableCardInfo)this.mCardInfo).mIssueFee > 0))
    {
      this.mTvIssueItem.setVisibility(0);
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Float.valueOf(((PayableCardInfo)this.mCardInfo).mIssueFee / 100.0F);
      String str1 = getString(2131296397, arrayOfObject1);
      this.mTvIssueItem.bindData(str1);
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Float.valueOf(this.mFeeInfo.mRechargeFee / 100.0F);
      String str2 = getString(2131296397, arrayOfObject2);
      this.mTvRechargeItem.bindData(str2);
      if (!((PayableCardInfo)this.mCardInfo).mHasIssue)
        break label253;
      i = this.mFeeInfo.mPayFee - this.mFeeInfo.mRechargeFee;
      label145: Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Float.valueOf(i / 100.0F);
      String str3 = getString(2131296397, arrayOfObject3);
      this.mTvDiscountItem.bindData(str3);
      if (i != 0)
        break label284;
      this.mTvDiscountItem.setVisibility(8);
    }
    while (true)
    {
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Float.valueOf(this.mFeeInfo.mPayFee / 100.0F);
      String str4 = getString(2131296397, arrayOfObject4);
      this.mTvPayItem.bindData(str4);
      return;
      this.mTvIssueItem.setVisibility(8);
      break;
      label253: i = this.mFeeInfo.mPayFee - this.mFeeInfo.mRechargeFee - ((PayableCardInfo)this.mCardInfo).mIssueFee;
      break label145;
      label284: this.mTvDiscountItem.setVisibility(0);
    }
  }

  protected void initView(View paramView)
  {
    this.mContentView = paramView.findViewById(2131492977);
    this.mLayoutError = paramView.findViewById(2131492989);
    this.mIvCurrentCardBg = ((ImageView)paramView.findViewById(2131492996));
    this.mTvCurrentCardName = ((TextView)paramView.findViewById(2131492955));
    if ((this.mCardInfo != null) && (!TextUtils.isEmpty(((PayableCardInfo)this.mCardInfo).mCardName)))
      this.mTvCurrentCardName.setText(((PayableCardInfo)this.mCardInfo).mCardName);
    this.mTvIssueItem = ((RechargeItemView)paramView.findViewById(2131492998));
    this.mTvRechargeItem = ((RechargeItemView)paramView.findViewById(2131492999));
    this.mTvDiscountItem = ((RechargeItemView)paramView.findViewById(2131493000));
    this.mTvPayItem = ((RechargeItemView)paramView.findViewById(2131493001));
    this.mCheckBox = ((CheckBox)paramView.findViewById(2131492949));
    this.mCheckBox.setOnCheckedChangeListener(this.mCheckedChangeListener);
    this.mTvProtocols = ((TextView)paramView.findViewById(2131492950));
    this.mBtnPay = ((Button)paramView.findViewById(2131492987));
    this.mBtnPay.setOnClickListener(this.mOnClickListener);
    this.mTvRechargeItem.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        new AlertDialog.Builder(IssueRechargeFragment.this.getActivity()).setTitle(IssueRechargeFragment.this.getActivity().getResources().getString(2131296347)).setSingleChoiceItems((CharSequence[])IssueRechargeFragment.this.mFees.toArray(new String[IssueRechargeFragment.this.mFees.size()]), 0, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            IssueRechargeFragment.this.rechargeAmountChange(paramInt);
            paramDialogInterface.dismiss();
          }
        }).setNegativeButton(IssueRechargeFragment.this.getActivity().getResources().getString(2131296271), null).create().show();
      }
    });
    getFeesFromServer();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903065, paramViewGroup, false);
  }

  protected void processRechargeInvalidValue(String paramString)
  {
    super.processRechargeInvalidValue(paramString);
    Toast.makeText(getActivity(), paramString, 0).show();
  }

  protected void resetViewToNormalStatus()
  {
    super.resetViewToNormalStatus();
    this.mBtnPay.setEnabled(true);
    dismissDialog();
  }

  protected void showContentView()
  {
    ActionBar localActionBar = getActivity().getActionBar();
    if (localActionBar != null)
      localActionBar.setTitle(((PayableCardInfo)this.mCardInfo).mCardName);
    this.mContentView.setVisibility(0);
    this.mLayoutError.setVisibility(8);
    Object localObject;
    TextView localTextView;
    Object[] arrayOfObject1;
    Object[] arrayOfObject2;
    if (((PayableCardInfo)this.mCardInfo).mCardUIInfo == null)
    {
      localObject = null;
      if (!TextUtils.isEmpty((CharSequence)localObject))
        ImageLoader.getInstance().displayImage((String)localObject, this.mIvCurrentCardBg);
      updateRechargeListView();
      this.mTvProtocols.setOnClickListener(this.mOnClickListener);
      localTextView = this.mTvProtocols;
      arrayOfObject1 = new Object[1];
      arrayOfObject2 = new Object[1];
      if (((PayableCardInfo)this.mCardInfo).mCardName != null)
        break label287;
    }
    label287: for (String str = ""; ; str = ((PayableCardInfo)this.mCardInfo).mCardName)
    {
      arrayOfObject2[0] = str;
      arrayOfObject1[0] = getString(2131296429, arrayOfObject2);
      localTextView.setText(String.format("《%s》", arrayOfObject1));
      this.mTvProtocols.getPaint().setFlags(8);
      int i = ((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().size();
      this.mFees.clear();
      for (int j = 0; j < i; j++)
      {
        ArrayList localArrayList = this.mFees;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = Float.valueOf(((FeeInfo)((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().get(j)).mRechargeFee / 100.0F);
        localArrayList.add(getString(2131296348, arrayOfObject3));
      }
      localObject = ((PayableCardInfo)this.mCardInfo).mCardUIInfo.mCardIssuedListBgHdUrl;
      break;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.IssueRechargeFragment
 * JD-Core Version:    0.6.0
 */