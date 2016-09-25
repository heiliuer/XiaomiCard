package cn.com.fmsh.util;

import cn.com.fmsh.FM_Exception;
import df;
import java.io.PrintStream;

public class BCCUtil
{
  public static byte calculateBCC(byte[] paramArrayOfByte)
  {
    int i = 1;
    int j = 0;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < i));
    while (true)
    {
      return j;
      if (paramArrayOfByte.length == i)
      {
        j = paramArrayOfByte[0];
        continue;
      }
      j = paramArrayOfByte[0];
      while (i < paramArrayOfByte.length)
      {
        j ^= paramArrayOfByte[i];
        i++;
      }
    }
  }

  public static String endsWith(String paramString, int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = paramInt1 - 1;
    try
    {
      char[] arrayOfChar = paramString.toCharArray();
      int k = arrayOfChar.length;
      while (i != k)
      {
        int m = arrayOfChar[i] ^ j & 0x5F;
        int n = j + paramInt2;
        int i1 = i + 1;
        arrayOfChar[i] = (char)m;
        i = i1;
        j = n;
      }
      String str2 = String.valueOf(arrayOfChar, 0, k).intern();
      str1 = str2;
      return str1;
    }
    catch (df localdf)
    {
      while (true)
        String str1 = null;
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    try
    {
      byte b = calculateBCC(FM_Bytes.hexStringToBytes(FM_Exception.getChars(2, 41, "h<~\020-qi+ut\"[rS)p;$m6?h1:cl5~g0yb+t}&/x!*s<%n7 i2;dm6h1zc,u~'py\"+t=&o8!j3<en7`i2yd,r/sx\"(p7\"y0TbA;e\033=db2\t\020[\001a,{~RXv=*u;*\0324;\02299bk4}f")));
      PrintStream localPrintStream = System.out;
      String str = FM_Bytes.startsWith("#X", 5, 26);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Byte.valueOf(b);
      localPrintStream.println(String.format(str, arrayOfObject));
      label54: return;
    }
    catch (df localdf)
    {
      break label54;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.BCCUtil
 * JD-Core Version:    0.6.0
 */