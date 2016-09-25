package com.miui.tsmclient.entity;

import android.text.TextUtils;
import org.json.JSONObject;

public class CardInfoFactory
{
  public static CardInfo makeCardInfo(String paramString, JSONObject paramJSONObject)
  {
    CardInfo localCardInfo = null;
    if (paramString == null);
    label113: 
    while (true)
    {
      return localCardInfo;
      Object localObject;
      if (TextUtils.equals(paramString, "SPTC"))
        localObject = new SptcCardInfo();
      while (true)
      {
        if (localObject == null)
          break label113;
        localCardInfo = (CardInfo)((ObjectParser)localObject).parse(paramJSONObject);
        break;
        if (TextUtils.equals(paramString, "SZT"))
        {
          localObject = new SztCardInfo();
          continue;
        }
        if (TextUtils.equals(paramString, "SUZHOUTONG"))
        {
          localObject = new SuzhoutongCardInfo();
          continue;
        }
        if (TextUtils.equals(paramString, "BANKCARD"))
        {
          localObject = new BankCardInfo();
          continue;
        }
        localObject = new PayableCardInfo(paramString);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.CardInfoFactory
 * JD-Core Version:    0.6.0
 */