package cn.com.fmsh.communication.core;

import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.Util4Java;
import h;

public class CloseSessionRequest
{
  public void fromBytes(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
      try
      {
        if (paramArrayOfByte.length == 7)
          this.a = paramArrayOfByte;
      }
      catch (h localh)
      {
      }
  }

  public byte[] getTerminalTime()
  {
    return this.a;
  }

  public void setTerminalTime(byte[] paramArrayOfByte)
  {
    try
    {
      this.a = paramArrayOfByte;
      label5: return;
    }
    catch (h localh)
    {
      break label5;
    }
  }

  public byte[] toBytes()
  {
    byte[] arrayOfByte = new byte[7];
    if (this.a == null)
      this.a = FM_CN.string2Bcd(Util4Java.date2string(Util4Java.toString(" 1.?XIwf\031\b\"s~o", 250, 111)));
    for (int i = 0; ; i++)
    {
      if (i >= 7)
        return arrayOfByte;
      arrayOfByte[(i + 0)] = this.a[i];
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.core.CloseSessionRequest
 * JD-Core Version:    0.6.0
 */