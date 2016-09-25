package com.tsmclient.smartcard.terminal;

public class CommandApdu
{
  protected int mCla;
  protected byte[] mData = new byte[0];
  protected int mIns;
  protected int mLc;
  protected int mLe;
  protected boolean mLeUsed;
  protected int mP1;
  protected int mP2;

  public CommandApdu()
  {
  }

  public CommandApdu(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mCla = paramInt1;
    this.mIns = paramInt2;
    this.mP1 = paramInt3;
    this.mP2 = paramInt4;
  }

  public CommandApdu(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    this.mCla = paramInt1;
    this.mIns = paramInt2;
    this.mP1 = paramInt3;
    this.mP2 = paramInt4;
    this.mLe = paramInt5;
    this.mLeUsed = true;
  }

  public CommandApdu(int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte)
  {
    this.mCla = paramInt1;
    this.mIns = paramInt2;
    this.mLc = paramArrayOfByte.length;
    this.mP1 = paramInt3;
    this.mP2 = paramInt4;
    this.mData = paramArrayOfByte;
  }

  public CommandApdu(int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte, int paramInt5)
  {
    this.mCla = paramInt1;
    this.mIns = paramInt2;
    this.mLc = paramArrayOfByte.length;
    this.mP1 = paramInt3;
    this.mP2 = paramInt4;
    this.mData = paramArrayOfByte;
    this.mLe = paramInt5;
    this.mLeUsed = true;
  }

  public CommandApdu clone()
  {
    CommandApdu localCommandApdu = new CommandApdu();
    localCommandApdu.mCla = this.mCla;
    localCommandApdu.mIns = this.mIns;
    localCommandApdu.mP1 = this.mP1;
    localCommandApdu.mP2 = this.mP2;
    localCommandApdu.mLc = this.mLc;
    localCommandApdu.mData = new byte[this.mData.length];
    System.arraycopy(this.mData, 0, localCommandApdu.mData, 0, this.mData.length);
    localCommandApdu.mLe = this.mLe;
    localCommandApdu.mLeUsed = this.mLeUsed;
    return localCommandApdu;
  }

  public byte[] getData()
  {
    return this.mData;
  }

  public int getLc()
  {
    return this.mLc;
  }

  public int getLe()
  {
    return this.mLe;
  }

  public int getP1()
  {
    return this.mP1;
  }

  public int getP2()
  {
    return this.mP2;
  }

  public void setData(byte[] paramArrayOfByte)
  {
    this.mLc = paramArrayOfByte.length;
    this.mData = paramArrayOfByte;
  }

  public void setLe(int paramInt)
  {
    this.mLe = paramInt;
    this.mLeUsed = true;
  }

  public void setP1(int paramInt)
  {
    this.mP1 = paramInt;
  }

  public void setP2(int paramInt)
  {
    this.mP2 = paramInt;
  }

  public byte[] toBytes()
  {
    int i = 4;
    if (this.mData.length != 0)
    {
      (i + 1);
      i = 5 + this.mData.length;
    }
    if (this.mLeUsed)
      i++;
    byte[] arrayOfByte = new byte[i];
    int j = 0 + 1;
    arrayOfByte[0] = (byte)this.mCla;
    int k = j + 1;
    arrayOfByte[j] = (byte)this.mIns;
    int m = k + 1;
    arrayOfByte[k] = (byte)this.mP1;
    int n = m + 1;
    arrayOfByte[m] = (byte)this.mP2;
    if (this.mData.length != 0)
    {
      arrayOfByte[n] = (byte)this.mLc;
      int i1 = n + 1;
      System.arraycopy(this.mData, 0, arrayOfByte, i1, this.mData.length);
      n = 5 + this.mData.length;
    }
    if (this.mLeUsed)
      arrayOfByte[n] += (byte)this.mLe;
    return arrayOfByte;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.terminal.CommandApdu
 * JD-Core Version:    0.6.0
 */