package cn.com.fmsh.util.algorithm;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import du;
import java.io.PrintStream;
import java.lang.reflect.Array;

public class MAC
{
  static
  {
    try
    {
      a = LogFactory.getInstance().getLog();
      label9: return;
    }
    catch (du localdu)
    {
      break label9;
    }
  }

  public static byte[] MAC4DESNoPadding(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    if (paramArrayOfByte3 == null);
    while (true)
    {
      byte[] arrayOfByte1;
      int j;
      try
      {
        a.error(MAC.class.getName(), FM_CN.subSequence("_��\023课篙斫＀敫捤丣&\"*9", 292));
        arrayOfByte1 = null;
        break label209;
        if (paramArrayOfByte3.length % 8 == 0)
          continue;
        a.error(MAC.class.getName(), FM_Exception.getChars(5, 116, "\023\023\005读篙斴：敺捰镭庠乗晡z盒假敮"));
        arrayOfByte1 = null;
        break label209;
        if (j < i)
          break label212;
        Object localObject = FM_Bytes.xor(paramArrayOfByte2, arrayOfByte[0]);
        int k = 1;
        if (k < i)
          continue;
        arrayOfByte1 = DES.encrypt4des(paramArrayOfByte1, localObject);
        break label209;
        arrayOfByte[j][m] = paramArrayOfByte3[(m + (j << 3))];
        m++;
        break label215;
        if (paramArrayOfByte2 != null)
          continue;
        paramArrayOfByte2 = new byte[8];
        int i = paramArrayOfByte3.length / 8;
        int[] arrayOfInt = new int[2];
        arrayOfInt[0] = i;
        arrayOfInt[1] = 8;
        byte[][] arrayOfByte = (byte[][])Array.newInstance(Byte.TYPE, arrayOfInt);
        j = 0;
        continue;
        byte[] arrayOfByte2 = FM_Bytes.xor(DES.encrypt4des(paramArrayOfByte1, localObject), arrayOfByte[k]);
        localObject = arrayOfByte2;
        k++;
        continue;
      }
      catch (du localdu)
      {
        arrayOfByte1 = null;
      }
      label209: return arrayOfByte1;
      label212: int m = 0;
      label215: if (m < 8)
        continue;
      j++;
    }
  }

  public static byte[] calculateMAC4DES(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    byte[] arrayOfByte1 = new byte[8];
    arrayOfByte1[0] = -128;
    if (paramArrayOfByte3 == null)
      a.error(MAC.class.getName(), Util4Java.toString("J\024��记篈旻ｗ改捹乿}t#q", 168, 78));
    if (paramArrayOfByte2 == null)
      paramArrayOfByte2 = new byte[8];
    int i;
    byte[] arrayOfByte2;
    int j;
    byte[][] arrayOfByte;
    int k;
    label117: byte[] arrayOfByte3;
    if (paramArrayOfByte3.length % 8 != 0)
    {
      i = 8 - paramArrayOfByte3.length % 8;
      arrayOfByte2 = FM_Bytes.join(paramArrayOfByte3, FM_Bytes.copyOf(arrayOfByte1, i));
      j = arrayOfByte2.length / 8;
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = j;
      arrayOfInt[1] = 8;
      arrayOfByte = (byte[][])Array.newInstance(Byte.TYPE, arrayOfInt);
      k = 0;
      if (k >= j)
        arrayOfByte3 = FM_Bytes.xor(paramArrayOfByte2, arrayOfByte[0]);
    }
    for (int n = 1; ; n++)
    {
      if (n >= j)
      {
        return DES.encrypt4des(paramArrayOfByte1, arrayOfByte3);
        for (int m = 0; ; m++)
        {
          if (m >= 8)
          {
            k++;
            break label117;
            i = 8;
            break;
          }
          arrayOfByte[k][m] = arrayOfByte2[(m + (k << 3))];
        }
      }
      arrayOfByte3 = FM_Bytes.xor(DES.encrypt4des(paramArrayOfByte1, arrayOfByte3), arrayOfByte[n]);
    }
  }

  public static byte[] calculateMAC4DES3(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    byte[] arrayOfByte1 = new byte[8];
    arrayOfByte1[0] = -128;
    if (paramArrayOfByte3 == null)
      a.error(MAC.class.getName(), FM_Utils.copyValueOf(4, 124, "BJD订篈断｛攣挡乱)637"));
    if (paramArrayOfByte2 == null)
      paramArrayOfByte2 = new byte[8];
    int i;
    byte[] arrayOfByte2;
    int j;
    byte[][] arrayOfByte;
    int k;
    byte[] arrayOfByte3;
    if (paramArrayOfByte3.length % 8 != 0)
    {
      i = 8 - paramArrayOfByte3.length % 8;
      arrayOfByte2 = FM_Bytes.join(paramArrayOfByte3, FM_Bytes.copyOf(arrayOfByte1, i));
      j = arrayOfByte2.length / 8;
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = j;
      arrayOfInt[1] = 8;
      arrayOfByte = (byte[][])Array.newInstance(Byte.TYPE, arrayOfInt);
      k = 0;
      if (k >= j)
        arrayOfByte3 = FM_Bytes.xor(paramArrayOfByte2, arrayOfByte[0]);
    }
    for (int n = 1; ; n++)
    {
      if (n >= j)
      {
        return DES.encrypt4des3(paramArrayOfByte1, arrayOfByte3);
        for (int m = 0; ; m++)
        {
          if (m >= 8)
          {
            k++;
            break;
          }
          arrayOfByte[k][m] = arrayOfByte2[(m + (k << 3))];
        }
        i = 8;
        break;
      }
      arrayOfByte3 = FM_Bytes.xor(DES.encrypt4des(paramArrayOfByte1, arrayOfByte3), arrayOfByte[n]);
    }
  }

  public static byte[] calculateMAC4DES3AN919(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    byte[] arrayOfByte1 = new byte[8];
    if (paramArrayOfByte3 == null)
      a.error(MAC.class.getName(), FM_Exception.getChars(3, 72, "\021\005O诵箋斲＀攤捲举\"a0h"));
    if (paramArrayOfByte2 == null)
      paramArrayOfByte2 = new byte[8];
    if (paramArrayOfByte3.length % 8 != 0);
    for (int i = 8 - paramArrayOfByte3.length % 8; ; i = 0)
    {
      byte[] arrayOfByte2 = FM_Bytes.join(paramArrayOfByte3, FM_Bytes.copyOf(arrayOfByte1, i));
      int j = arrayOfByte2.length / 8;
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = j;
      arrayOfInt[1] = 8;
      byte[][] arrayOfByte = (byte[][])Array.newInstance(Byte.TYPE, arrayOfInt);
      int k = 0;
      byte[] arrayOfByte3;
      int n;
      if (k >= j)
      {
        arrayOfByte3 = FM_Bytes.xor(paramArrayOfByte2, arrayOfByte[0]);
        n = 1;
        label130: if (n < j)
          break label160;
        return DES.encrypt4des3(paramArrayOfByte1, arrayOfByte3);
      }
      for (int m = 0; ; m++)
      {
        if (m >= 8)
        {
          k++;
          break;
          label160: arrayOfByte3 = FM_Bytes.xor(DES.encrypt4des(paramArrayOfByte1, arrayOfByte3), arrayOfByte[n]);
          n++;
          break label130;
        }
        arrayOfByte[k][m] = arrayOfByte2[(m + (k << 3))];
      }
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    try
    {
      byte[] arrayOfByte1 = new byte[16];
      arrayOfByte1[1] = -106;
      arrayOfByte1[2] = 43;
      arrayOfByte1[3] = 96;
      arrayOfByte1[4] = -86;
      arrayOfByte1[5] = 85;
      arrayOfByte1[6] = 110;
      arrayOfByte1[7] = 101;
      arrayOfByte1[8] = 80;
      arrayOfByte1[9] = -12;
      arrayOfByte1[10] = -105;
      arrayOfByte1[11] = 72;
      arrayOfByte1[12] = 84;
      arrayOfByte1[13] = 99;
      arrayOfByte1[14] = -73;
      arrayOfByte1[15] = 24;
      byte[] arrayOfByte2 = calculateMAC4DES3AN919(FM_Bytes.hexStringToBytes(Util4Java.toString("6n|\004g{\002 3A$Ilb\017kc|Y >_)3o\022q\031y\006!<", 4, 117)), new byte[8], arrayOfByte1);
      System.out.println(FM_Long.concat("1fq'", 4) + FM_Bytes.bytesToHexString(arrayOfByte2));
      label139: return;
    }
    catch (du localdu)
    {
      break label139;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.algorithm.MAC
 * JD-Core Version:    0.6.0
 */