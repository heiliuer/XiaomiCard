package com.miui.tsmclient.util;

import android.text.Editable;

public class CommonNumberFormatter extends Formatter
{
  public void format(Editable paramEditable)
  {
    clean(paramEditable);
    if (paramEditable.length() <= 4);
    while (true)
    {
      return;
      int i = 0;
      for (int j = 0; i < paramEditable.length(); j++)
      {
        if ((j % 4 == 0) && (j != 0))
        {
          paramEditable.insert(i, Character.toString(this.SEPARATOR));
          i++;
        }
        i++;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.CommonNumberFormatter
 * JD-Core Version:    0.6.0
 */