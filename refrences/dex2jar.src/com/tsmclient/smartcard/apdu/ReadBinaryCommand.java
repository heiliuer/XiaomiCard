package com.tsmclient.smartcard.apdu;

public class ReadBinaryCommand extends BaseCommand
{
  public byte getCls()
  {
    return 0;
  }

  public byte getIns()
  {
    return -80;
  }

  public byte getP2()
  {
    return 0;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.apdu.ReadBinaryCommand
 * JD-Core Version:    0.6.0
 */