package com.tsmclient.smartcard.apdu;

public class ReadRecordCommand extends BaseCommand
{
  public byte getCls()
  {
    return 0;
  }

  public byte getIns()
  {
    return -78;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.apdu.ReadRecordCommand
 * JD-Core Version:    0.6.0
 */