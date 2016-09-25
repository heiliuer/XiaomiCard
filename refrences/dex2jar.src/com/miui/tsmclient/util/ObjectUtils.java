package com.miui.tsmclient.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ObjectUtils
{
  public static String joinMap(Map<String, String> paramMap, String paramString)
  {
    if (paramMap == null);
    StringBuilder localStringBuilder;
    for (String str1 = null; ; str1 = localStringBuilder.toString())
    {
      return str1;
      localStringBuilder = new StringBuilder();
      Set localSet = paramMap.entrySet();
      int i = 0;
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (i > 0)
          localStringBuilder.append(paramString);
        String str2 = (String)localEntry.getKey();
        String str3 = (String)localEntry.getValue();
        localStringBuilder.append(str2);
        localStringBuilder.append("=");
        localStringBuilder.append(str3);
        i++;
      }
    }
  }

  public static Map<String, String> listToMap(Map<String, List<String>> paramMap)
  {
    HashMap localHashMap = new HashMap();
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        String str = (String)localEntry.getKey();
        List localList = (List)localEntry.getValue();
        if ((str == null) || (localList == null) || (localList.size() <= 0))
          continue;
        localHashMap.put(str, localList.get(0));
      }
    }
    return localHashMap;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.ObjectUtils
 * JD-Core Version:    0.6.0
 */