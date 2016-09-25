package com.miui.tsmclient.util;

import android.text.Editable;

public class IdCardFormatter extends Formatter
{
  private static int[] ID_CARD_SEP;

  static
  {
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = 6;
    arrayOfInt[1] = 10;
    arrayOfInt[2] = 14;
    ID_CARD_SEP = arrayOfInt;
  }

  public void format(Editable paramEditable)
  {
    clean(paramEditable);
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < paramEditable.length())
    {
      if ((k < ID_CARD_SEP.length) && (j == ID_CARD_SEP[k]))
      {
        paramEditable.insert(i, Character.toString(this.SEPARATOR));
        k++;
        i++;
      }
      i++;
      j++;
    }
  }

  public boolean isValidCharacter(char paramChar)
  {
    if ((Character.isDigit(paramChar)) || ('x' == Character.toLowerCase(paramChar)));
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.IdCardFormatter
 * JD-Core Version:    0.6.0
 */