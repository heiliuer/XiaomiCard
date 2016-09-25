package com.tsmclient.smartcard.apdu;

public class GPOCommand extends BaseCommand
{
  public byte getCls()
  {
    return -128;
  }

  public byte getIns()
  {
    return -88;
  }

  public byte getP1()
  {
    return 0;
  }

  public byte getP2()
  {
    return 0;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.apdu.GPOCommand
 * JD-Core Version:    0.6.0
 */