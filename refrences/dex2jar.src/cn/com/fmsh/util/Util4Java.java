package cn.com.fmsh.util;

import cn.com.fmsh.FM_Exception;
import dp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util4Java
{
  static
  {
    try
    {
      int[] arrayOfInt = new int[8];
      arrayOfInt[1] = 1;
      arrayOfInt[2] = 3;
      arrayOfInt[3] = 7;
      arrayOfInt[4] = 15;
      arrayOfInt[5] = 31;
      arrayOfInt[6] = 63;
      arrayOfInt[7] = 127;
      a = arrayOfInt;
      label44: return;
    }
    catch (dp localdp)
    {
      break label44;
    }
  }

  public static byte String2Byte(String paramString, byte paramByte)
  {
    try
    {
      byte b = Byte.parseByte(paramString, 16);
      paramByte = b;
      label9: return paramByte;
    }
    catch (Exception localException)
    {
      break label9;
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

  public static String date2string(String paramString)
  {
    Object localObject = null;
    try
    {
      String str = date2string(null, paramString);
      localObject = str;
      label10: return localObject;
    }
    catch (dp localdp)
    {
      break label10;
    }
  }

  public static String date2string(Date paramDate, String paramString)
  {
    if (paramDate == null)
      paramDate = new Date();
    if (paramString == null)
      paramString = FM_Long.concat("'pmf'X\rf2%l\037J7un4j7", 6);
    return new SimpleDateFormat(paramString).format(paramDate);
  }

  public static int getBitNumber(byte paramByte, int paramInt1, int paramInt2)
  {
    int i = -1;
    if ((paramInt2 > 8) || (paramInt2 < 0));
    while (true)
    {
      return i;
      byte b;
      do
      {
        i = paramByte >> b & a[paramInt2];
        break;
        b = paramInt1 - paramInt2;
      }
      while (b >= 0);
    }
  }

  public static String getCurrentTime()
  {
    Object localObject = null;
    try
    {
      String str = date2string(null, FM_CN.subSequence("[J+-\"d>/", 5));
      localObject = str;
      label15: return localObject;
    }
    catch (dp localdp)
    {
      break label15;
    }
  }

  public static String getExceptionInfo(Exception paramException)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream localPrintStream = new PrintStream(localByteArrayOutputStream);
    paramException.printStackTrace(localPrintStream);
    String str = localByteArrayOutputStream.toString();
    if (localPrintStream != null);
    try
    {
      localPrintStream.close();
      if (localByteArrayOutputStream != null)
        localByteArrayOutputStream.close();
      return str;
    }
    catch (IOException localIOException)
    {
      while (true)
        str = "";
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    try
    {
      int i = String2Byte(FM_Exception.getChars(5, 102, "o\006"), 0);
      System.out.println(i);
      label20: return;
    }
    catch (dp localdp)
    {
      break label20;
    }
  }

  public static String toString(String paramString, int paramInt1, int paramInt2)
  {
    int i = paramInt1 - 1;
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
 * Qualified Name:     cn.com.fmsh.util.Util4Java
 * JD-Core Version:    0.6.0
 */