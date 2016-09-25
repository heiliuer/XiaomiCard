package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ViewPagerIndicatorBar extends LinearLayout
{
  private Context mContext;
  private int mItemDimen;
  private int mItemInterval;
  private int mTotalIndicatorNum;

  public ViewPagerIndicatorBar(Context paramContext)
  {
    this(paramContext, null);
  }

  public ViewPagerIndicatorBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    initialize();
  }

  private void initialize()
  {
    this.mItemDimen = getResources().getDimensionPixelSize(2131230755);
    this.mItemInterval = getResources().getDimensionPixelSize(2131230754);
    setOrientation(0);
  }

  public void setIndicatorNum(int paramInt)
  {
    setIndicatorNum(paramInt, 0);
  }

  public void setIndicatorNum(int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    if (paramInt1 <= 0)
    {
      removeAllViews();
      return;
    }
    this.mTotalIndicatorNum = paramInt1;
    label22: LinearLayout.LayoutParams localLayoutParams;
    int j;
    label62: ImageView localImageView;
    if (i <= 0)
    {
      i = 0;
      removeAllViews();
      localLayoutParams = new LinearLayout.LayoutParams(this.mItemDimen, this.mItemDimen, 1.0F);
      localLayoutParams.setMargins(this.mItemInterval, 0, this.mItemInterval, 0);
      j = 0;
      if (j < paramInt1)
      {
        localImageView = new ImageView(this.mContext);
        localImageView.setImageResource(2130837660);
        if (j != i)
          break label130;
      }
    }
    label130: for (boolean bool = true; ; bool = false)
    {
      localImageView.setSelected(bool);
      addView(localImageView, localLayoutParams);
      j++;
      break label62;
      break;
      if (i < paramInt1)
        break label22;
      i = paramInt1 - 1;
      break label22;
    }
  }

  public void setSelected(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.mTotalIndicatorNum))
      return;
    int i = 0;
    label15: ImageView localImageView;
    if (i < getChildCount())
    {
      localImageView = (ImageView)getChildAt(i);
      if (i != paramInt)
        break label52;
    }
    label52: for (boolean bool = true; ; bool = false)
    {
      localImageView.setSelected(bool);
      i++;
      break label15;
      break;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.ViewPagerIndicatorBar
 * JD-Core Version:    0.6.0
 */