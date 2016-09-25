package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import com.miui.tsmclient.R.styleable;

public class MiuiDigitFontTextView extends TextView
{
  public MiuiDigitFontTextView(Context paramContext)
  {
    this(paramContext, null);
  }

  public MiuiDigitFontTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MiuiDigitFontTextView);
    setTypeface(MiuiDigitFont.getMiuiDigitTypeface(paramContext, MiuiDigitFont.DigitType.fromInt(localTypedArray.getInteger(0, 0))));
    localTypedArray.recycle();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.MiuiDigitFontTextView
 * JD-Core Version:    0.6.0
 */