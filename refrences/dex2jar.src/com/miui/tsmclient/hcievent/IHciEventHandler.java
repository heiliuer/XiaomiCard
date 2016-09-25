package com.miui.tsmclient.hcievent;

public abstract interface IHciEventHandler
{
  public abstract HciEventInfo handleData(byte[] paramArrayOfByte1, long paramLong, byte[] paramArrayOfByte2);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.hcievent.IHciEventHandler
 * JD-Core Version:    0.6.0
 */