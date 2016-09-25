package com.miui.tsmclient.util;

import android.widget.EditText;

public class FormatterUtils
{
  public static String clean(String paramString)
  {
    return clean(paramString, FormatterType.TYPE_NORMAL);
  }

  public static String clean(String paramString, FormatterType paramFormatterType)
  {
    return paramFormatterType.getFormatter().clean(paramString);
  }

  public static String cover(String paramString, FormatterType paramFormatterType)
  {
    return paramFormatterType.getFormatter().cover(paramString);
  }

  public static String format(String paramString, FormatterType paramFormatterType)
  {
    return paramFormatterType.getFormatter().format(paramString);
  }

  public static void setFormatter(EditText paramEditText, FormatterType paramFormatterType)
  {
    paramFormatterType.getFormatter().bindFormattingTextWatcher(paramEditText);
  }

  public static enum FormatterType
  {
    private Formatter mFormatter;

    static
    {
      TYPE_ID_CARD = new FormatterType("TYPE_ID_CARD", 2, new IdCardFormatter());
      FormatterType[] arrayOfFormatterType = new FormatterType[3];
      arrayOfFormatterType[0] = TYPE_NORMAL;
      arrayOfFormatterType[1] = TYPE_PHONE;
      arrayOfFormatterType[2] = TYPE_ID_CARD;
      $VALUES = arrayOfFormatterType;
    }

    private FormatterType(Formatter paramFormatter)
    {
      this.mFormatter = paramFormatter;
    }

    public Formatter getFormatter()
    {
      return this.mFormatter;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.FormatterUtils
 * JD-Core Version:    0.6.0
 */