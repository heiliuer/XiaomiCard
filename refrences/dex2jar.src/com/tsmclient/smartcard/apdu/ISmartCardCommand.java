package com.tsmclient.smartcard.apdu;

import com.tsmclient.smartcard.ByteArray;

public abstract interface ISmartCardCommand
{
  public abstract byte getCls();

  public abstract ByteArray getData();

  public abstract byte getIns();

  public abstract int getLc();

  public abstract int getLe();

  public abstract byte getP1();

  public abstract byte getP2();

  public abstract ByteArray toRawAPDU();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.apdu.ISmartCardCommand
 * JD-Core Version:    0.6.0
 */