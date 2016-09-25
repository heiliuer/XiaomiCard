package cn.com.fmsh.util.socket;

import java.io.DataInputStream;
import java.io.IOException;

public abstract interface DataLengthHandle
{
  public abstract int getDataSize(DataInputStream paramDataInputStream)
    throws IOException;

  public abstract byte[] initDataSize(int paramInt);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.socket.DataLengthHandle
 * JD-Core Version:    0.6.0
 */