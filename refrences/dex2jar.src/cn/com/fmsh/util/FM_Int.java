package cn.com.fmsh.util;

import dk;

public class FM_Int
{
  public FM_Int()
  {
  }

  public FM_Int(int paramInt)
  {
    this.a = paramInt;
  }

  public static String lastIndexOf(int paramInt, String paramString)
  {
    int i = 0;
    int j = paramInt - 13;
    try
    {
      char[] arrayOfChar = paramString.toCharArray();
      int k = arrayOfChar.length;
      while (i != k)
      {
        int m = arrayOfChar[i] ^ j & 0x5F;
        int n = j + 1;
        int i1 = i + 1;
        arrayOfChar[i] = (char)m;
        i = i1;
        j = n;
      }
      String str2 = String.valueOf(arrayOfChar, 0, k).intern();
      str1 = str2;
      return str1;
    }
    catch (dk localdk)
    {
      while (true)
        String str1 = null;
    }
  }

  public int getValue()
  {
    return this.a;
  }

  public void setValue(int paramInt)
  {
    try
    {
      this.a = paramInt;
      label5: return;
    }
    catch (dk localdk)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.FM_Int
 * JD-Core Version:    0.6.0
 */