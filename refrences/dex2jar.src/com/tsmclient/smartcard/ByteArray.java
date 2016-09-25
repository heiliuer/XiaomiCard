package com.tsmclient.smartcard;

import java.util.Arrays;

public final class ByteArray
{
  public static final ByteArray EMPTY = wrap(new byte[0]);
  private byte[] mData;
  private int mLength;
  private int mOffset;

  private ByteArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.mData = paramArrayOfByte;
    this.mOffset = paramInt1;
    this.mLength = paramInt2;
  }

  public static boolean equals(ByteArray paramByteArray1, ByteArray paramByteArray2)
  {
    int i = 0;
    if (paramByteArray1 == null);
    while (true)
    {
      return i;
      if ((paramByteArray2 == null) || (paramByteArray1.mLength != paramByteArray2.mLength))
        continue;
      if ((paramByteArray1.mOffset == 0) && (paramByteArray2.mOffset == 0) && (Arrays.equals(paramByteArray1.mData, paramByteArray2.mData)))
      {
        i = 1;
        continue;
      }
      int j = paramByteArray1.mOffset;
      int k = paramByteArray2.mOffset;
      int m = j + paramByteArray1.mLength;
      while (true)
      {
        if (j >= m)
          break label106;
        if (paramByteArray1.mData[j] != paramByteArray2.mData[k])
          break;
        j++;
        k++;
      }
      label106: i = 1;
    }
  }

  public static ByteArray wrap(byte paramByte)
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = paramByte;
    return wrap(arrayOfByte, 0, 1);
  }

  public static ByteArray wrap(byte[] paramArrayOfByte)
  {
    return wrap(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static ByteArray wrap(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new ByteArray(paramArrayOfByte, paramInt1, paramInt2);
  }

  public ByteArray append(byte paramByte)
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = paramByte;
    return append(arrayOfByte);
  }

  public ByteArray append(ByteArray paramByteArray)
  {
    if (paramByteArray == null)
      throw new IllegalArgumentException("argument must not be null ");
    return append(paramByteArray.toBytes());
  }

  public ByteArray append(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("argument must not be null ");
    byte[] arrayOfByte = new byte[this.mLength + paramArrayOfByte.length];
    System.arraycopy(this.mData, this.mOffset, arrayOfByte, 0, this.mLength);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, this.mLength, paramArrayOfByte.length);
    this.mData = arrayOfByte;
    this.mLength += paramArrayOfByte.length;
    return this;
  }

  public boolean contains(byte paramByte)
  {
    int i = this.mOffset;
    if (i < this.mLength)
      if (this.mData[i] != paramByte);
    for (int j = 1; ; j = 0)
    {
      return j;
      i++;
      break;
    }
  }

  public ByteArray copy()
  {
    return copy(0, length());
  }

  public ByteArray copy(int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(this.mData, paramInt1 + this.mOffset, arrayOfByte, 0, paramInt2);
    return new ByteArray(arrayOfByte, 0, paramInt2);
  }

  public ByteArray duplicate(int paramInt1, int paramInt2)
  {
    return new ByteArray(this.mData, paramInt1 + this.mOffset, paramInt2);
  }

  public byte get(int paramInt)
  {
    return this.mData[(paramInt + this.mOffset)];
  }

  public int length()
  {
    return this.mLength;
  }

  public byte[] toBytes()
  {
    byte[] arrayOfByte;
    if ((this.mOffset == 0) && (this.mLength == this.mData.length))
      arrayOfByte = this.mData;
    while (true)
    {
      return arrayOfByte;
      arrayOfByte = new byte[this.mLength];
      System.arraycopy(this.mData, this.mOffset, arrayOfByte, 0, this.mLength);
    }
  }

  public String toString()
  {
    return Coder.bytesToHexString(toBytes());
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.ByteArray
 * JD-Core Version:    0.6.0
 */