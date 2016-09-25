package com.tsmclient.smartcard.apdu;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;

public abstract class BaseCommand
  implements ISmartCardCommand
{
  private byte mCls;
  private ByteArray mData = ByteArray.EMPTY;
  private byte mIns;
  private byte mP1;
  private byte mP2;

  public byte getCls()
  {
    return this.mCls;
  }

  public ByteArray getData()
  {
    return this.mData;
  }

  public byte getIns()
  {
    return this.mIns;
  }

  public int getLc()
  {
    return getData().length();
  }

  public int getLe()
  {
    return 0;
  }

  public byte getP1()
  {
    return this.mP1;
  }

  public byte getP2()
  {
    return this.mP2;
  }

  protected void setCls(byte paramByte)
  {
    this.mCls = paramByte;
  }

  public void setData(ByteArray paramByteArray)
  {
    this.mData = paramByteArray;
  }

  protected void setIns(byte paramByte)
  {
    this.mIns = paramByte;
  }

  public void setP1(byte paramByte)
  {
    this.mP1 = paramByte;
  }

  public void setP2(byte paramByte)
  {
    this.mP2 = paramByte;
  }

  public ByteArray toRawAPDU()
  {
    ByteArray localByteArray = getData();
    byte[] arrayOfByte = new byte[5 + localByteArray.length()];
    arrayOfByte[0] = getCls();
    arrayOfByte[1] = getIns();
    arrayOfByte[2] = getP1();
    arrayOfByte[3] = getP2();
    if (localByteArray.length() > 0)
    {
      arrayOfByte[4] = (byte)localByteArray.length();
      System.arraycopy(localByteArray.toBytes(), 0, arrayOfByte, 5, localByteArray.length());
    }
    return ByteArray.wrap(arrayOfByte);
  }

  public String toString()
  {
    return Coder.bytesToHexString(toRawAPDU().toBytes());
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.apdu.BaseCommand
 * JD-Core Version:    0.6.0
 */