package com.tsmclient.smartcard.tlv;

import com.tsmclient.smartcard.ByteArray;

public abstract interface ITLVObject
{
  public abstract ByteArray getLength();

  public abstract ByteArray getTag();

  public abstract ITLVValue getValue();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.tlv.ITLVObject
 * JD-Core Version:    0.6.0
 */