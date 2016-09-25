package com.tsmclient.smartcard.tlv;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.exception.InvalidTLVException;
import java.util.ArrayList;

public class TLVParser
{
  private static final ByteArray APP_DISCRETIONARY_TAG;

  static
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = -90;
    APP_DISCRETIONARY_TAG = ByteArray.wrap(arrayOfByte);
  }

  private static int[] getTLVLength(ByteArray paramByteArray, int paramInt)
    throws InvalidTLVException
  {
    int i = 0xFF & paramByteArray.get(paramInt);
    if (i == 128)
      throw new InvalidTLVException("find infinite tag length");
    int[] arrayOfInt;
    if ((i & 0x80) == 128)
    {
      int j = i & 0x7F;
      int k = 0;
      for (int m = 0; m < j; m++)
      {
        k <<= 8;
        int n = 1 + (paramInt + m);
        if (n >= paramByteArray.length())
          continue;
        k += (0xFF & paramByteArray.get(n));
      }
      if (-1 + (paramByteArray.length() - paramInt - j) < k)
        throw new InvalidTLVException("insufficient remaining value, long form len: " + k);
      arrayOfInt = new int[2];
      arrayOfInt[0] = k;
      arrayOfInt[1] = (j + 1);
    }
    while (true)
    {
      return arrayOfInt;
      if (-1 + (paramByteArray.length() - paramInt) < i)
        throw new InvalidTLVException("insufficient remaining value, len: " + i);
      arrayOfInt = new int[2];
      arrayOfInt[0] = i;
      arrayOfInt[1] = 1;
    }
  }

  public static ITLVObject parse(ByteArray paramByteArray)
    throws InvalidTLVException
  {
    if (paramByteArray.length() < 2)
      throw new InvalidTLVException("data too small");
    int i = 1;
    if ((0x1F & paramByteArray.get(0)) == 31)
    {
      i++;
      int k = 1;
      while ((0x80 & paramByteArray.get(k)) == 128)
      {
        k++;
        i++;
      }
    }
    ByteArray localByteArray1 = paramByteArray.duplicate(0, i);
    int[] arrayOfInt = getTLVLength(paramByteArray, i);
    ByteArray localByteArray2 = paramByteArray.duplicate(i, arrayOfInt[1]);
    int j = i + arrayOfInt[1];
    if (((0x20 & paramByteArray.get(0)) == 0) || (APP_DISCRETIONARY_TAG.contains(paramByteArray.get(0))));
    for (DefaultTLVObject localDefaultTLVObject = new DefaultTLVObject(localByteArray1, localByteArray2, new PrimitiveTLVValue(paramByteArray.duplicate(j, paramByteArray.length() - j))); ; localDefaultTLVObject = new DefaultTLVObject(localByteArray1, localByteArray2, parseTLVValue(paramByteArray.duplicate(j, paramByteArray.length() - j))))
      return localDefaultTLVObject;
  }

  public static ITLVValue parseTLVValue(ByteArray paramByteArray)
    throws InvalidTLVException
  {
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    while (i < paramByteArray.length())
    {
      int j = 1;
      if ((0x1F & paramByteArray.get(i)) == 31)
        j = 2;
      int[] arrayOfInt = getTLVLength(paramByteArray, i + j);
      int k = arrayOfInt[0];
      int m = j + arrayOfInt[1];
      if (paramByteArray.length() - i - m < k)
        throw new InvalidTLVException("insufficient len when parsing value, len: " + k);
      localArrayList.add(parse(paramByteArray.duplicate(i, k + m)));
      i += k + m;
    }
    return new ArrayTLVValue(paramByteArray, localArrayList);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.tlv.TLVParser
 * JD-Core Version:    0.6.0
 */