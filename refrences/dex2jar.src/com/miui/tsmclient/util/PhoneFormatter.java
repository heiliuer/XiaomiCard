package com.miui.tsmclient.util;

import android.text.Editable;

public class PhoneFormatter extends Formatter
{
  private static int[] PHONE_NUM_SEP;

  static
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 3;
    arrayOfInt[1] = 7;
    PHONE_NUM_SEP = arrayOfInt;
  }

  public void format(Editable paramEditable)
  {
    clean(paramEditable);
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < paramEditable.length())
    {
      if ((k < PHONE_NUM_SEP.length) && (j == PHONE_NUM_SEP[k]))
      {
        paramEditable.insert(i, Character.toString(this.SEPARATOR));
        k++;
        i++;
      }
      i++;
      j++;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.PhoneFormatter
 * JD-Core Version:    0.6.0
 */