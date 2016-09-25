package cn.com.fmsh.util;

import cn.com.fmsh.FM_Exception;
import dn;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FM_Utils
{
  static
  {
    try
    {
      a = FM_Exception.getChars(134, 48, "o>m<+z)xg6\036M\\\013Z\t");
      label13: return;
    }
    catch (dn localdn)
    {
      break label13;
    }
  }

  public static int String2Int(String paramString, int paramInt)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      paramInt = i;
      label7: return paramInt;
    }
    catch (Exception localException)
    {
      break label7;
    }
  }

  public static String copyValueOf(int paramInt1, int paramInt2, String paramString)
  {
    int i = paramInt1 + 11;
    char[] arrayOfChar = paramString.toCharArray();
    int j = arrayOfChar.length;
    int k = i;
    int m = 0;
    while (m != j)
    {
      int n = arrayOfChar[m] ^ k & 0x5F;
      int i1 = k + paramInt2;
      int i2 = m + 1;
      arrayOfChar[m] = (char)n;
      m = i2;
      k = i1;
    }
    return String.valueOf(arrayOfChar, 0, j).intern();
  }

  public static void exceptionHandle(Exception paramException)
  {
    try
    {
      throw paramException;
    }
    catch (Exception localException)
    {
    }
  }

  public static String getExceptionInfo(Exception paramException)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramException.printStackTrace(new PrintStream(localByteArrayOutputStream));
      String str2 = localByteArrayOutputStream.toString();
      str1 = str2;
      return str1;
    }
    catch (dn localdn)
    {
      while (true)
        String str1 = null;
    }
  }

  public static long hexStringToLong(String paramString)
  {
    int i = paramString.length();
    long l = 0L;
    char[] arrayOfChar = paramString.toCharArray();
    int j = 0;
    if (j >= i)
      return l;
    int k = Integer.parseInt(arrayOfChar[j], 16);
    for (int m = 0; ; m++)
    {
      if ((m >= -1 + (i - j)) || (k == 0))
      {
        l += k;
        j++;
        break;
      }
      k <<= 4;
    }
  }

  public static String intToHexString(int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    byte[] arrayOfByte = FM_Bytes.intToBytes(paramInt1, paramInt2);
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfByte.length)
        return localStringBuffer.toString();
      int j = arrayOfByte[i];
      localStringBuffer.append(a.charAt((j & 0xF0) >> 4));
      localStringBuffer.append(a.charAt(j & 0xF));
    }
  }

  public static boolean isChinese(char paramChar)
  {
    Character.UnicodeBlock localUnicodeBlock = Character.UnicodeBlock.of(paramChar);
    if ((localUnicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) || (localUnicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) || (localUnicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) || (localUnicodeBlock == Character.UnicodeBlock.GENERAL_PUNCTUATION) || (localUnicodeBlock == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) || (localUnicodeBlock == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isNotEmpty(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString)));
    for (int i = 0; ; i = 1)
      return i;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.FM_Utils
 * JD-Core Version:    0.6.0
 */