package com.miui.tsmclient.ui;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RechargeGridItem extends RelativeLayout
{
  private int mChildCount;
  private EditText mEditRechargeAmount;
  private RechargeAdapter.OnGridItemSelectListener mGridItemSelectListener;
  private View mHorizontalSplit;
  private int mNumColumns;
  private RechargeAdapter.OnRechargeAmountChangeListener mRechargeAmountChangeListener;
  private boolean mSelected;
  private TextView mTvYuan;
  private View mVerticalSplit;

  public RechargeGridItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void bindData(int paramInt, String paramString)
  {
    if (paramInt % this.mNumColumns == -1 + this.mNumColumns)
    {
      this.mVerticalSplit.setVisibility(8);
      if (paramInt < this.mChildCount - this.mNumColumns)
        break label80;
      this.mHorizontalSplit.setVisibility(8);
    }
    while (true)
    {
      if (!TextUtils.isEmpty(paramString))
        break label91;
      this.mTvYuan.setVisibility(8);
      setEnabled(false);
      return;
      this.mVerticalSplit.setVisibility(0);
      break;
      label80: this.mHorizontalSplit.setVisibility(0);
    }
    label91: this.mEditRechargeAmount.setText(paramString);
    if (this.mSelected)
    {
      setBackgroundResource(2130837559);
      this.mEditRechargeAmount.setTextColor(-1);
      this.mTvYuan.setTextColor(-1);
    }
    while (true)
    {
      this.mEditRechargeAmount.setFocusable(false);
      ((View)this.mEditRechargeAmount.getParent()).setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          RechargeGridItem.this.doClick(this.val$position);
        }
      });
      this.mEditRechargeAmount.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          RechargeGridItem.this.doClick(this.val$position);
        }
      });
      break;
      int i = getContext().getResources().getColor(2131165242);
      setBackgroundResource(2130837558);
      this.mEditRechargeAmount.setTextColor(i);
      this.mTvYuan.setTextColor(i);
    }
  }

  public void doClick(int paramInt)
  {
    String str = this.mEditRechargeAmount.getText().toString();
    if (this.mGridItemSelectListener != null)
      this.mGridItemSelectListener.onGridItemSelect(this.mEditRechargeAmount, paramInt);
    if (this.mRechargeAmountChangeListener != null)
      this.mRechargeAmountChangeListener.onRechargeAmountChange(this.mEditRechargeAmount, paramInt, str.replace(getContext().getString(2131296369), ""));
  }

  public void init(int paramInt1, int paramInt2)
  {
    this.mChildCount = paramInt1;
    this.mNumColumns = paramInt2;
    this.mEditRechargeAmount = ((EditText)findViewById(2131492991));
    this.mTvYuan = ((TextView)findViewById(2131492992));
    this.mVerticalSplit = findViewById(2131492993);
    this.mHorizontalSplit = findViewById(2131492994);
  }

  public void resetEditItemStatus()
  {
    this.mEditRechargeAmount.clearFocus();
  }

  public void setOnRechargeAmountChangeListener(RechargeAdapter.OnRechargeAmountChangeListener paramOnRechargeAmountChangeListener)
  {
    this.mRechargeAmountChangeListener = paramOnRechargeAmountChangeListener;
  }

  public void setOnSelectChangeListener(RechargeAdapter.OnGridItemSelectListener paramOnGridItemSelectListener)
  {
    this.mGridItemSelectListener = paramOnGridItemSelectListener;
  }

  public void setSelectStatus(boolean paramBoolean)
  {
    this.mSelected = paramBoolean;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.RechargeGridItem
 * JD-Core Version:    0.6.0
 */