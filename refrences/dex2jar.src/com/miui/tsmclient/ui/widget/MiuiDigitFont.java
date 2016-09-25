package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

public class MiuiDigitFont
{
  private static HashMap<DigitType, Typeface> sTypefaces = new HashMap();

  public static Typeface getMiuiDigitTypeface(Context paramContext, DigitType paramDigitType)
  {
    Typeface localTypeface = (Typeface)sTypefaces.get(paramDigitType);
    if (localTypeface == null)
    {
      localTypeface = Typeface.createFromAsset(paramContext.getAssets(), paramDigitType.getFontFile());
      sTypefaces.put(paramDigitType, localTypeface);
    }
    return localTypeface;
  }

  public static enum DigitType
  {
    private String mTTF;

    static
    {
      BOLD = new DigitType("BOLD", 1, "fonts/miui_digit_bold.ttf");
      LIGHT = new DigitType("LIGHT", 2, "fonts/miui_digit_light.ttf");
      DigitType[] arrayOfDigitType = new DigitType[3];
      arrayOfDigitType[0] = NORMAL;
      arrayOfDigitType[1] = BOLD;
      arrayOfDigitType[2] = LIGHT;
      $VALUES = arrayOfDigitType;
    }

    private DigitType(String paramString)
    {
      this.mTTF = paramString;
    }

    public static DigitType fromInt(int paramInt)
    {
      DigitType localDigitType;
      switch (paramInt)
      {
      default:
        localDigitType = NORMAL;
      case 0:
      case 1:
      case 2:
      }
      while (true)
      {
        return localDigitType;
        localDigitType = NORMAL;
        continue;
        localDigitType = BOLD;
        continue;
        localDigitType = LIGHT;
      }
    }

    public String getFontFile()
    {
      return this.mTTF;
    }

    public int toInt()
    {
      return ordinal();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.MiuiDigitFont
 * JD-Core Version:    0.6.0
 */