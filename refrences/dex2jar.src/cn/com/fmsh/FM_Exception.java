package cn.com.fmsh;

public class FM_Exception extends Exception
{
  public FM_Exception(String paramString)
  {
    super(paramString);
  }

  public static String getChars(int paramInt1, int paramInt2, String paramString)
  {
    int i = paramInt1 - 7;
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
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.FM_Exception
 * JD-Core Version:    0.6.0
 */