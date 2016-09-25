package com.tsmclient.smartcard.apdu;

public class GetDataCommand extends BaseCommand
{
  public byte getCls()
  {
    return -128;
  }

  public byte getIns()
  {
    return -54;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.apdu.GetDataCommand
 * JD-Core Version:    0.6.0
 */