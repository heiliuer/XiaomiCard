package com.miui.tsmclient.util;

import android.text.TextUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{
  private static final String REGEX_PHONE = "^\\d{11}$";

  public static boolean checkLength(String paramString, int paramInt)
  {
    return checkLength(paramString, paramInt, paramInt);
  }

  public static boolean checkLength(String paramString, int paramInt1, int paramInt2)
  {
    int i = 0;
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return i;
      if ((paramString.length() < paramInt1) || (paramString.length() > paramInt2))
        continue;
      i = 1;
    }
  }

  public static boolean checkPhoneNum(String paramString)
  {
    return checkValidByPatten(paramString, "^\\d{11}$");
  }

  public static boolean checkValidByPatten(String paramString1, String paramString2)
  {
    return Pattern.compile(paramString2).matcher(paramString1).matches();
  }

  public static String covertFloatToString(float paramFloat)
  {
    if (paramFloat == (int)paramFloat);
    for (String str = String.valueOf((int)paramFloat); ; str = String.valueOf(paramFloat))
      return str;
  }

  public static String createMarkedString(String paramString, int paramInt, char paramChar)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramInt > paramString.length()));
    for (String str = null; ; str = createMarkedString(paramString, paramString.length(), paramInt, paramChar))
      return str;
  }

  public static String createMarkedString(String paramString, int paramInt1, int paramInt2, char paramChar)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramInt2 > paramString.length()));
    StringBuilder localStringBuilder;
    for (String str1 = null; ; str1 = localStringBuilder.toString())
    {
      return str1;
      localStringBuilder = new StringBuilder(paramInt1);
      int j;
      for (int i = 0; ; i = j)
      {
        j = i + 1;
        if (i >= paramInt1)
          break;
        localStringBuilder.append(paramChar);
      }
      String str2 = paramString.subSequence(paramString.length() - paramInt2, paramString.length()).toString();
      localStringBuilder.replace(paramInt1 - paramInt2, paramInt1, str2);
    }
  }

  public static String formatAmount(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Float.valueOf(paramInt / 100.0F);
    return String.format(String.format("%.2f", arrayOfObject), new Object[0]);
  }

  public static String formatDateFromString(String paramString1, String paramString2, String paramString3)
  {
    String str;
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString3)))
      str = "";
    while (true)
    {
      return str;
      SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat(paramString2);
      SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat(paramString3);
      try
      {
        Date localDate = localSimpleDateFormat1.parse(paramString1);
        str = localSimpleDateFormat2.format(localDate);
      }
      catch (ParseException localParseException)
      {
        LogUtils.e("parse date failed,date string is:" + paramString1 + ",pattern is:" + paramString2, localParseException);
        str = null;
      }
    }
  }

  public static String getAidFactor(String paramString)
  {
    if ((paramString == null) || (paramString.length() < 3));
    while (true)
    {
      return paramString;
      if (!SysUtils.isBankCardAid(paramString))
        continue;
      paramString = paramString.substring(0, -3 + paramString.length());
    }
  }

  public static boolean isBankCardValid(String paramString)
  {
    int i = 0;
    int j = 0;
    if ((paramString == null) || (paramString.length() == 0));
    while (true)
    {
      return i;
      int k = -1 + paramString.length();
      if (k >= 0)
      {
        if ((-1 + paramString.length() - k) % 2 == 0)
          j += Character.getNumericValue(paramString.charAt(k));
        while (true)
        {
          k--;
          break;
          int m = 2 * Character.getNumericValue(paramString.charAt(k));
          j += m / 10 + m % 10;
        }
      }
      if (j % 10 != 0)
        continue;
      i = 1;
    }
  }

  public static String join(Map<String, String> paramMap, String paramString)
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
        localStringBuilder.append(str2).append("=").append(str3);
        i++;
      }
    }
  }

  public static String millsToTime(long paramLong, String paramString)
  {
    if (paramLong < 0L);
    for (String str = null; ; str = new SimpleDateFormat(paramString).format(new Date(paramLong)))
      return str;
  }

  public static String tail(String paramString, int paramInt)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.length() <= paramInt));
    while (true)
    {
      return paramString;
      paramString = paramString.substring(paramString.length() - paramInt);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.StringUtils
 * JD-Core Version:    0.6.0
 */