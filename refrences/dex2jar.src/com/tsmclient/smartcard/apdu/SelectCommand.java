package com.tsmclient.smartcard.apdu;

public class SelectCommand extends BaseCommand
{
  public byte getCls()
  {
    return 0;
  }

  public byte getIns()
  {
    return -92;
  }

  public byte getP2()
  {
    return 0;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.apdu.SelectCommand
 * JD-Core Version:    0.6.0
 */