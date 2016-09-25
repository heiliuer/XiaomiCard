package com.tsmclient.smartcard;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.tech.IsoDep;
import com.tsmclient.smartcard.handler.BMACCardHandler;
import com.tsmclient.smartcard.handler.CityUCardHandler;
import com.tsmclient.smartcard.handler.ISmartCardHandler;
import com.tsmclient.smartcard.handler.LingNanCardHandler;
import com.tsmclient.smartcard.handler.SZTCardHandler;
import com.tsmclient.smartcard.handler.WHTCardHandler;

public class ReaderUtil
{
  public static String getFromSharedPrefs(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("nfc_read_card", 0).getString(paramString, null);
  }

  public static ISmartCardHandler<IsoDep> getHandlerById(String paramString)
  {
    Object localObject;
    if (paramString == null)
      localObject = null;
    while (true)
    {
      return localObject;
      if (paramString.equals("001"))
      {
        localObject = new BMACCardHandler();
        continue;
      }
      if (paramString.equals("004"))
      {
        localObject = new SZTCardHandler();
        continue;
      }
      if (paramString.equals("005"))
      {
        localObject = new WHTCardHandler();
        continue;
      }
      if (paramString.equals("013"))
      {
        localObject = new LingNanCardHandler();
        continue;
      }
      localObject = new CityUCardHandler();
    }
  }

  public static String invertString(String paramString, int paramInt)
  {
    char[] arrayOfChar = paramString.toCharArray();
    int i = paramString.length();
    int j = 0;
    while (j < i / 2)
    {
      for (int k = 0; k < paramInt; k++)
      {
        int m = arrayOfChar[(j + k)];
        arrayOfChar[(j + k)] = arrayOfChar[(k + (i - paramInt - j))];
        arrayOfChar[(k + (i - paramInt - j))] = m;
      }
      j += paramInt;
    }
    return new String(arrayOfChar);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.ReaderUtil
 * JD-Core Version:    0.6.0
 */