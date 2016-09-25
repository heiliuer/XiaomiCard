package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;
import com.miui.tsmclient.R.styleable;

public class MiuiDigitFontEditText extends EditText
{
  public MiuiDigitFontEditText(Context paramContext)
  {
    this(paramContext, null);
  }

  public MiuiDigitFontEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MiuiDigitFontTextView);
    setTypeface(MiuiDigitFont.getMiuiDigitTypeface(paramContext, MiuiDigitFont.DigitType.fromInt(localTypedArray.getInteger(0, 0))));
    localTypedArray.recycle();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.MiuiDigitFontEditText
 * JD-Core Version:    0.6.0
 */