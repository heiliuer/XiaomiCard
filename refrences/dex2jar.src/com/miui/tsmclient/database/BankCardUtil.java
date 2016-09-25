package com.miui.tsmclient.database;

import android.content.Context;
import android.content.res.Resources;
import com.miui.tsmclient.entity.BankCardInfo;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class BankCardUtil
{
  public static String getBankCardTypeString(Context paramContext, int paramInt)
  {
    String str;
    if (paramInt == 1)
      str = paramContext.getResources().getString(2131296468);
    while (true)
    {
      return str;
      if (paramInt == 2)
      {
        str = paramContext.getResources().getString(2131296469);
        continue;
      }
      str = null;
    }
  }

  public static JSONArray serialize(List<BankCardInfo> paramList)
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      localJSONArray.put(((BankCardInfo)localIterator.next()).serialize());
    return localJSONArray;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.database.BankCardUtil
 * JD-Core Version:    0.6.0
 */