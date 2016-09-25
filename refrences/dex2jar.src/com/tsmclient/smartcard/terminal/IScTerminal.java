package com.tsmclient.smartcard.terminal;

import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.Closeable;
import java.io.IOException;

public abstract interface IScTerminal extends Closeable
{
  public abstract void connect()
    throws IOException;

  public abstract ScResponse transmit(byte[] paramArrayOfByte)
    throws IOException;
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.terminal.IScTerminal
 * JD-Core Version:    0.6.0
 */