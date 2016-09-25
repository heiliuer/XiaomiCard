package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.EditText;
import com.miui.tsmclient.R.styleable;

public class SafeEditText extends EditText
{
  public SafeEditText(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public SafeEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public SafeEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SafeEditText);
    boolean bool = localTypedArray.getBoolean(0, true);
    int i = localTypedArray.getInt(1, 0);
    localTypedArray.recycle();
    if (bool)
    {
      SafeKeyboard.register(this);
      setFocusableInTouchMode(true);
      setTextIsSelectable(true);
      setExtendedInputType(i);
    }
  }

  public void setExtendedInputType(int paramInt)
  {
    Bundle localBundle = getInputExtras(true);
    if (localBundle == null);
    while (true)
    {
      return;
      localBundle.putInt("com.mipay.input_extra.extended_input_type", paramInt);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.SafeEditText
 * JD-Core Version:    0.6.0
 */