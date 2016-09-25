package cn.com.fmsh.util;

import dg;
import java.io.PrintStream;

public class CRCUtil
{
  public static byte[] calculateCRC16(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    int j = 0;
    int k = 0;
    if (j >= i)
      return FM_Bytes.intToBytes(k, 2);
    label35: label79: 
    while (true)
    {
      int m;
      int n;
      if ((short)(k ^ m) <= 0)
      {
        k = 0x1021 ^ (short)(k << 1);
        m = (short)(m << 1);
        n++;
      }
      while (true)
      {
        if (n < 8)
          break label79;
        j++;
        break;
        k = (short)(k << 1);
        break label35;
        m = (short)(paramArrayOfByte[j] << 8);
        n = 0;
      }
    }
  }

  public static void main(String[] paramArrayOfString)
  {
    try
    {
      byte[] arrayOfByte = calculateCRC16(FM_Bytes.hexStringToBytes(FM_Utils.copyValueOf(206, 81, "i;k|-~/ q\"3d5fw(y);l=n0ar#t%6g8iz+|->o 1b3du&w(9j;l}.p!r#4e6gx)z+<m>o`1bs$u&7h9j{,}.?p!2c4ev'x):k<m~,`x#sQ5g7iy_z,9l<2e6`t&~$:m>h~.|+$r#0`1bs+}/8l5\0331as")));
      System.out.println(FM_Bytes.bytesToHexString(arrayOfByte));
      System.out.println(65535);
      label35: return;
    }
    catch (dg localdg)
    {
      break label35;
    }
  }

  public static String valueOf(int paramInt, String paramString)
  {
    int i = paramInt - 10;
    char[] arrayOfChar = paramString.toCharArray();
    int j = arrayOfChar.length;
    int k = i;
    int m = 0;
    while (m != j)
    {
      int n = arrayOfChar[m] ^ k & 0x5F;
      int i1 = k - 15;
      int i2 = m + 1;
      arrayOfChar[m] = (char)n;
      m = i2;
      k = i1;
    }
    return String.valueOf(arrayOfChar, 0, j).intern();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.CRCUtil
 * JD-Core Version:    0.6.0
 */