package cn.com.fmsh.communication.core;

import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import cn.com.fmsh.util.socket.DataLengthHandle;
import java.io.DataInputStream;
import java.io.IOException;
import m;

public class NFCosDataLengthHandler
  implements DataLengthHandle
{
  public int getDataSize(DataInputStream paramDataInputStream)
    throws IOException
  {
    if (this.a != null)
      this.a = LogFactory.getInstance().getLog();
    if (paramDataInputStream == null);
    byte[] arrayOfByte;
    for (int i1 = -1; ; i1 = FM_Bytes.bytesToInt(arrayOfByte))
    {
      return i1;
      int k;
      int m;
      int n;
      do
      {
        int i2 = paramDataInputStream.readByte();
        int j = k;
        k = i2;
        while (j != (k ^ 0xFFFFFFFF))
        {
          int i3 = paramDataInputStream.readByte();
          j = k;
          k = i3;
          continue;
          int i = paramDataInputStream.available();
          if (this.a != null)
            this.a.debug(this.b, FM_Utils.copyValueOf(156, 42, "C0/d\0067s8#R?'z(>=&'z,#x!!r{") + i);
          j = paramDataInputStream.readByte();
          k = paramDataInputStream.readByte();
        }
        m = paramDataInputStream.readByte();
        n = paramDataInputStream.readByte();
      }
      while (k != (m ^ n));
      arrayOfByte = new byte[4];
      arrayOfByte[2] = m;
      arrayOfByte[3] = n;
    }
  }

  public byte[] initDataSize(int paramInt)
  {
    try
    {
      arrayOfByte1 = new byte[4];
      byte[] arrayOfByte2 = FM_Bytes.intToBytes(paramInt, 2);
      arrayOfByte1[1] = (byte)(arrayOfByte2[0] ^ arrayOfByte2[1]);
      arrayOfByte1[0] = (byte)(0xFFFFFFFF ^ arrayOfByte1[1]);
      arrayOfByte1[2] = arrayOfByte2[0];
      arrayOfByte1[3] = arrayOfByte2[1];
      return arrayOfByte1;
    }
    catch (m localm)
    {
      while (true)
        byte[] arrayOfByte1 = null;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.core.NFCosDataLengthHandler
 * JD-Core Version:    0.6.0
 */