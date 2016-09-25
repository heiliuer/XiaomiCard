package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import miui.util.ViewUtils;
import miui.widget.ClearableEditText;

public class ExtraButtonEditText extends ClearableEditText
{
  private Drawable mDrawable;
  private OnExtraButtonClickListener mOnExtraButtonClickListener;

  public ExtraButtonEditText(Context paramContext)
  {
    this(paramContext, null);
  }

  public ExtraButtonEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public ExtraButtonEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = 1;
    int j;
    if ((this.mOnExtraButtonClickListener != null) && (getText().length() == 0))
    {
      j = 0;
      if (ViewUtils.isLayoutRtl(this))
      {
        if (paramMotionEvent.getX() < this.mDrawable.getIntrinsicWidth() + getPaddingLeft())
          j = 1;
        if ((paramMotionEvent.getAction() != i) || (j == 0))
          break label107;
        this.mOnExtraButtonClickListener.onClick();
      }
    }
    while (true)
    {
      return i;
      if (paramMotionEvent.getX() <= getWidth() - getPaddingRight() - this.mDrawable.getIntrinsicWidth())
        break;
      j = 1;
      break;
      label107: boolean bool = super.dispatchTouchEvent(paramMotionEvent);
    }
  }

  public void setExtraButtonDrawable(Drawable paramDrawable)
  {
    paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
    setCompoundDrawablesRelative(null, null, paramDrawable, null);
    this.mDrawable = paramDrawable;
  }

  public void setExtraButtonListener(OnExtraButtonClickListener paramOnExtraButtonClickListener)
  {
    this.mOnExtraButtonClickListener = paramOnExtraButtonClickListener;
  }

  public static abstract interface OnExtraButtonClickListener
  {
    public abstract void onClick();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.ExtraButtonEditText
 * JD-Core Version:    0.6.0
 */