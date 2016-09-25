package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.tsmclient.R.styleable;
import com.miui.tsmclient.util.DisplayUtils;

public class RechargeItemView extends LinearLayout
{
  private ImageView mIvRightArrow;
  private String mLeftText;
  private int mLeftTextColor;
  private float mLeftTextSize;
  private String mRightText;
  private int mRightTextColor;
  private float mRightTextSize;
  private boolean mShowRightArrow;
  private TextView mTvLeft;
  private TextView mTvRight;

  public RechargeItemView(Context paramContext)
  {
    super(paramContext);
    initView(null);
  }

  public RechargeItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramAttributeSet);
  }

  public RechargeItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initView(paramAttributeSet);
  }

  private void initView()
  {
    LayoutInflater.from(getContext()).inflate(2130903085, this);
    this.mTvLeft = ((TextView)findViewById(2131493084));
    this.mTvRight = ((TextView)findViewById(2131493085));
    this.mIvRightArrow = ((ImageView)findViewById(2131493086));
  }

  private void initView(AttributeSet paramAttributeSet)
  {
    int i = 0;
    initView();
    ImageView localImageView;
    if (paramAttributeSet != null)
    {
      TypedArray localTypedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.RechargeItemView);
      this.mLeftText = localTypedArray.getString(0);
      this.mLeftTextSize = localTypedArray.getDimension(1, 14.0F);
      this.mLeftTextColor = localTypedArray.getColor(2, 0);
      this.mRightTextSize = localTypedArray.getDimension(3, 12.0F);
      this.mRightTextColor = localTypedArray.getColor(4, 0);
      this.mShowRightArrow = localTypedArray.getBoolean(5, false);
      localTypedArray.recycle();
      this.mTvLeft.setText(this.mLeftText);
      this.mTvLeft.setTextSize(2, DisplayUtils.pxToSp(getContext(), this.mLeftTextSize));
      this.mTvLeft.setTextColor(this.mLeftTextColor);
      this.mTvRight.setTextSize(2, DisplayUtils.pxToSp(getContext(), this.mRightTextSize));
      this.mTvRight.setTextColor(this.mRightTextColor);
      localImageView = this.mIvRightArrow;
      if (!this.mShowRightArrow)
        break label178;
    }
    while (true)
    {
      localImageView.setVisibility(i);
      return;
      label178: i = 4;
    }
  }

  public void bindData(String paramString)
  {
    this.mTvRight.setText(paramString);
  }

  public void bindData(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.mLeftText = paramString1;
    this.mRightText = paramString2;
    this.mShowRightArrow = paramBoolean;
    this.mTvLeft.setText(this.mLeftText);
    this.mTvRight.setText(this.mRightText);
    ImageView localImageView = this.mIvRightArrow;
    if (this.mShowRightArrow);
    for (int i = 0; ; i = 4)
    {
      localImageView.setVisibility(i);
      return;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.RechargeItemView
 * JD-Core Version:    0.6.0
 */