package com.tsmclient.smartcard.tlv;

import com.tsmclient.smartcard.ByteArray;

class DefaultTLVObject
  implements ITLVObject
{
  private ByteArray mLength;
  private ByteArray mTag;
  private ITLVValue mValue;

  public DefaultTLVObject(ByteArray paramByteArray1, ByteArray paramByteArray2, ITLVValue paramITLVValue)
  {
    this.mTag = paramByteArray1;
    this.mLength = paramByteArray2;
    this.mValue = paramITLVValue;
  }

  public ByteArray getLength()
  {
    return this.mLength;
  }

  public ByteArray getTag()
  {
    return this.mTag;
  }

  public ITLVValue getValue()
  {
    return this.mValue;
  }

  public void setLength(ByteArray paramByteArray)
  {
    this.mLength = paramByteArray;
  }

  public void setTag(ByteArray paramByteArray)
  {
    this.mTag = paramByteArray;
  }

  public void setValue(ITLVValue paramITLVValue)
  {
    this.mValue = paramITLVValue;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.tlv.DefaultTLVObject
 * JD-Core Version:    0.6.0
 */