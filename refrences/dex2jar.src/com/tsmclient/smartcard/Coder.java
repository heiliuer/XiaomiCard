package com.tsmclient.smartcard;

import android.text.TextUtils;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Coder
{
  private static final char[] HEX_DIGITS;
  private static final int[] sizeTable;

  static
  {
    char[] arrayOfChar = new char[16];
    arrayOfChar[0] = 48;
    arrayOfChar[1] = 49;
    arrayOfChar[2] = 50;
    arrayOfChar[3] = 51;
    arrayOfChar[4] = 52;
    arrayOfChar[5] = 53;
    arrayOfChar[6] = 54;
    arrayOfChar[7] = 55;
    arrayOfChar[8] = 56;
    arrayOfChar[9] = 57;
    arrayOfChar[10] = 65;
    arrayOfChar[11] = 66;
    arrayOfChar[12] = 67;
    arrayOfChar[13] = 68;
    arrayOfChar[14] = 69;
    arrayOfChar[15] = 70;
    HEX_DIGITS = arrayOfChar;
    int[] arrayOfInt = new int[10];
    arrayOfInt[0] = 9;
    arrayOfInt[1] = 99;
    arrayOfInt[2] = 999;
    arrayOfInt[3] = 9999;
    arrayOfInt[4] = 99999;
    arrayOfInt[5] = 999999;
    arrayOfInt[6] = 9999999;
    arrayOfInt[7] = 99999999;
    arrayOfInt[8] = 999999999;
    arrayOfInt[9] = 2147483647;
    sizeTable = arrayOfInt;
  }

  public static String bytesToHexString(byte paramByte)
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = paramByte;
    return bytesToHexString(arrayOfByte, 0, 1);
  }

  public static String bytesToHexString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null);
    for (String str = null; ; str = bytesToHexString(paramArrayOfByte, 0, paramArrayOfByte.length))
      return str;
  }

  private static String bytesToHexString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    char[] arrayOfChar = new char[paramInt2 * 2];
    int i = paramInt1;
    int j = 0;
    while (i < paramInt1 + paramInt2)
    {
      int k = paramArrayOfByte[i];
      int m = j + 1;
      arrayOfChar[j] = HEX_DIGITS[(0xF & k >>> 4)];
      j = m + 1;
      arrayOfChar[m] = HEX_DIGITS[(k & 0xF)];
      i++;
    }
    return new String(arrayOfChar);
  }

  public static int bytesToInt(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("argument is null");
    int i = 0;
    int j = paramArrayOfByte.length;
    for (int k = 0; k < j; k++)
    {
      int m = paramArrayOfByte[k];
      i = i << 8 | m & 0xFF;
    }
    return i;
  }

  public static int bytesToInt(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramArrayOfByte == null) || (paramInt1 < 0) || (paramInt2 > paramArrayOfByte.length))
      throw new IllegalArgumentException("argument is null");
    int i = 0;
    int j = paramInt1 + paramInt2;
    for (int k = paramInt1; k < j; k++)
      i = i << 8 | 0xFF & paramArrayOfByte[k];
    return i;
  }

  public static String decodeBase64Coder(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      paramString = new String(Base64.decode(paramString, 8));
    return paramString;
  }

  public static byte[] decodeBase64ToByteArray(String paramString)
  {
    if (!TextUtils.isEmpty(paramString));
    for (byte[] arrayOfByte = Base64.decode(paramString, 8); ; arrayOfByte = null)
      return arrayOfByte;
  }

  public static String encodeBase64Coder(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null);
    for (String str = Base64.encodeToString(paramArrayOfByte, 8); ; str = null)
      return str;
  }

  public static String encodeMD5(String paramString)
  {
    String str = null;
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return str;
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.update(paramString.getBytes());
        str = bytesToHexString(localMessageDigest.digest());
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
      }
    }
  }

  public static String encodeMD5(byte[] paramArrayOfByte)
  {
    String str = null;
    if (paramArrayOfByte == null);
    while (true)
    {
      return str;
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.update(paramArrayOfByte);
        str = bytesToHexString(localMessageDigest.digest());
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
      }
    }
  }

  public static String hashDeviceInfo(String paramString)
  {
    try
    {
      String str = Base64.encodeToString(MessageDigest.getInstance("SHA1").digest(paramString.getBytes()), 11);
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    throw new IllegalStateException("failed to init SHA1 digest");
  }

  public static byte[] hexStringToBytes(String paramString)
  {
    int i = paramString.length();
    byte[] arrayOfByte = new byte[i / 2];
    for (int j = 0; j < i; j += 2)
      arrayOfByte[(j / 2)] = (byte)(toByte(paramString.charAt(j)) << 4 | toByte(paramString.charAt(j + 1)));
    return arrayOfByte;
  }

  public static int hexStringToInt(String paramString)
  {
    return bytesToInt(hexStringToBytes(paramString));
  }

  public static int sizeOfInt(int paramInt)
  {
    if (paramInt < 0)
      paramInt = -paramInt;
    for (int i = 0; ; i++)
      if (paramInt <= sizeTable[i])
        return i + 1;
  }

  public static byte[] str2Bcd(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("argument is null");
    int i = paramString.length();
    if (i % 2 != 0)
    {
      paramString = "0" + paramString;
      i = paramString.length();
    }
    if (i >= 2)
      i /= 2;
    byte[] arrayOfByte1 = new byte[i];
    byte[] arrayOfByte2 = paramString.getBytes();
    int j = 0;
    if (j < paramString.length() / 2)
    {
      int k;
      label115: int m;
      if ((arrayOfByte2[(j * 2)] >= 48) && (arrayOfByte2[(j * 2)] <= 57))
      {
        k = -48 + arrayOfByte2[(j * 2)];
        if ((arrayOfByte2[(1 + j * 2)] < 48) || (arrayOfByte2[(1 + j * 2)] > 57))
          break label228;
        m = -48 + arrayOfByte2[(1 + j * 2)];
      }
      while (true)
      {
        arrayOfByte1[j] = (byte)(m + (k << 4));
        j++;
        break;
        if ((arrayOfByte2[(j * 2)] >= 97) && (arrayOfByte2[(j * 2)] <= 122))
        {
          k = 10 + (-97 + arrayOfByte2[(j * 2)]);
          break label115;
        }
        k = 10 + (-65 + arrayOfByte2[(j * 2)]);
        break label115;
        label228: if ((arrayOfByte2[(1 + j * 2)] >= 97) && (arrayOfByte2[(1 + j * 2)] <= 122))
        {
          m = 10 + (-97 + arrayOfByte2[(1 + j * 2)]);
          continue;
        }
        m = 10 + (-65 + arrayOfByte2[(1 + j * 2)]);
      }
    }
    return arrayOfByte1;
  }

  public static String to10Bytes(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    return String.format("%010d", arrayOfObject);
  }

  public static String to2Bytes(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    return String.format("%02d", arrayOfObject);
  }

  public static String to4Bytes(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    return String.format("%04d", arrayOfObject);
  }

  public static String to4HexBytes(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(0xFFFF & paramInt);
    return String.format("%04X", arrayOfObject);
  }

  private static int toByte(char paramChar)
  {
    int i;
    if ((paramChar >= '0') && (paramChar <= '9'))
      i = paramChar + '￐';
    while (true)
    {
      return i;
      if ((paramChar >= 'A') && (paramChar <= 'F'))
      {
        i = 10 + (paramChar + '﾿');
        continue;
      }
      if ((paramChar < 'a') || (paramChar > 'f'))
        break;
      i = 10 + (paramChar + 'ﾟ');
    }
    throw new RuntimeException("Invalid hex char '" + paramChar + "'");
  }

  public static byte toBytesLow(int paramInt)
  {
    return (byte)(paramInt & 0xFF);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.Coder
 * JD-Core Version:    0.6.0
 */