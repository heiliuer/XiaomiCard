package com.miui.tsmclient.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.miui.tsmclient.util.UiUtils;
import java.util.ArrayList;
import java.util.List;

public class RechargeAdapter extends ArrayAdapter<String>
{
  private int mCurrentSelectPosition = -1;
  private RechargeGridItem mCurrentSelectView;
  private List<Integer> mDisabledItemList;
  private boolean mFirst;
  private boolean mHasIssue;
  private LayoutInflater mInflater = LayoutInflater.from(this.mContext);
  private OnRechargeAmountChangeListener mListener;
  private int mNumColumns;

  public RechargeAdapter(Context paramContext, OnRechargeAmountChangeListener paramOnRechargeAmountChangeListener, int paramInt, boolean paramBoolean)
  {
    super(paramContext);
    this.mListener = paramOnRechargeAmountChangeListener;
    this.mNumColumns = paramInt;
    this.mHasIssue = paramBoolean;
    this.mFirst = true;
    this.mDisabledItemList = new ArrayList();
  }

  public void addItemDisabled()
  {
    if (!this.mDisabledItemList.contains(Integer.valueOf(this.mCurrentSelectPosition)))
      this.mDisabledItemList.add(Integer.valueOf(this.mCurrentSelectPosition));
  }

  public void bindData(View paramView, int paramInt, String paramString)
  {
    RechargeGridItem localRechargeGridItem = (RechargeGridItem)paramView;
    localRechargeGridItem.setOnRechargeAmountChangeListener(this.mListener);
    localRechargeGridItem.setOnSelectChangeListener(new OnGridItemSelectListener(localRechargeGridItem)
    {
      public void onGridItemSelect(View paramView, int paramInt)
      {
        if (RechargeAdapter.this.mCurrentSelectPosition == paramInt)
          return;
        if ((RechargeAdapter.this.mHasIssue) && (paramInt == -1 + RechargeAdapter.this.getCount()))
          UiUtils.showSoftInputMethod(RechargeAdapter.this.mContext, paramView, true);
        while (true)
        {
          if (RechargeAdapter.this.mCurrentSelectView != null)
            RechargeAdapter.this.mCurrentSelectView.setSelectStatus(false);
          this.val$itemView.setSelectStatus(true);
          RechargeAdapter.access$202(RechargeAdapter.this, this.val$itemView);
          RechargeAdapter.access$002(RechargeAdapter.this, paramInt);
          RechargeAdapter.this.notifyDataSetChanged();
          break;
          if ((!RechargeAdapter.this.mHasIssue) || (RechargeAdapter.this.mCurrentSelectPosition != -1 + RechargeAdapter.this.getCount()))
            continue;
          RechargeAdapter.this.mCurrentSelectView.resetEditItemStatus();
          UiUtils.showSoftInputMethod(RechargeAdapter.this.mContext, paramView, false);
        }
      }
    });
    localRechargeGridItem.bindData(paramInt, paramString);
    if (this.mDisabledItemList.contains(Integer.valueOf(paramInt)))
      localRechargeGridItem.setEnabled(false);
    while (true)
    {
      if ((this.mFirst) && (paramInt == 0))
      {
        localRechargeGridItem.doClick(paramInt);
        this.mFirst = false;
      }
      return;
      localRechargeGridItem.setEnabled(true);
    }
  }

  public View newView(Context paramContext, String paramString, ViewGroup paramViewGroup)
  {
    RechargeGridItem localRechargeGridItem = (RechargeGridItem)this.mInflater.inflate(2130903064, null);
    localRechargeGridItem.init(getCount(), this.mNumColumns);
    return localRechargeGridItem;
  }

  public static abstract interface OnGridItemSelectListener
  {
    public abstract void onGridItemSelect(View paramView, int paramInt);
  }

  public static abstract interface OnRechargeAmountChangeListener
  {
    public abstract void onRechargeAmountChange(TextView paramTextView, int paramInt, String paramString);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.RechargeAdapter
 * JD-Core Version:    0.6.0
 */