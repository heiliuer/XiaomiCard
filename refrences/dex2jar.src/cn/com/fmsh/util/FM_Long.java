package cn.com.fmsh.util;

import dl;

public class FM_Long
{
  public FM_Long()
  {
  }

  public FM_Long(long paramLong)
  {
    this.a = paramLong;
  }

  public static String concat(String paramString, int paramInt)
  {
    int i = 0;
    int j = paramInt - 8;
    try
    {
      char[] arrayOfChar = paramString.toCharArray();
      int k = arrayOfChar.length;
      while (i != k)
      {
        int m = arrayOfChar[i] ^ j & 0x5F;
        int n = j + 11;
        int i1 = i + 1;
        arrayOfChar[i] = (char)m;
        i = i1;
        j = n;
      }
      String str2 = String.valueOf(arrayOfChar, 0, k).intern();
      str1 = str2;
      return str1;
    }
    catch (dl localdl)
    {
      while (true)
        String str1 = null;
    }
  }

  public long getValue()
  {
    return this.a;
  }

  public void setValue(long paramLong)
  {
    try
    {
      this.a = paramLong;
      label5: return;
    }
    catch (dl localdl)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.FM_Long
 * JD-Core Version:    0.6.0
 */