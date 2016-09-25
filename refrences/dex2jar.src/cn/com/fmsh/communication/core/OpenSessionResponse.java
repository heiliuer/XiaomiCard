package cn.com.fmsh.communication.core;

import cn.com.fmsh.communication.exception.session.OpenSessionException;
import cn.com.fmsh.util.FM_Long;
import p;

public class OpenSessionResponse
{
  public final int DATA_LENGTH = 32;
  public final int SESSION_KEY_LENGTH = 16;

  public void fromBytes(byte[] paramArrayOfByte)
    throws OpenSessionException
  {
    int m = 0;
    if (paramArrayOfByte == null)
      return;
    while (true)
    {
      try
      {
        if (paramArrayOfByte.length == 32)
          break label114;
        throw new OpenSessionException(FM_Long.concat("笤刵哝序敶捿镣廡也呕沝", 2));
        if (m >= 4)
          break;
        this.d[m] = paramArrayOfByte[(m + 28)];
        m++;
        continue;
        this.b[i1] = paramArrayOfByte[(i1 + 8)];
        i1++;
        break label127;
        this.c[i2] = paramArrayOfByte[(i2 + 12)];
        i2++;
        break label136;
        this.a[n] = paramArrayOfByte[(n + 0)];
        n++;
      }
      catch (p localp)
      {
      }
      break;
      label114: int n = 0;
      if (n < 8)
        continue;
      int i1 = 0;
      label127: if (i1 < 4)
        continue;
      int i2 = 0;
      label136: if (i2 < 16)
        continue;
    }
  }

  public byte[] getSerialNumber()
  {
    return this.d;
  }

  public byte[] getSessionKey()
  {
    return this.c;
  }

  public byte[] getSessionNumber()
  {
    return this.b;
  }

  public byte[] getTerminalRandom()
  {
    return this.a;
  }

  public void setSerialNumber(byte[] paramArrayOfByte)
  {
    try
    {
      this.d = paramArrayOfByte;
      label5: return;
    }
    catch (p localp)
    {
      break label5;
    }
  }

  public void setSessionKey(byte[] paramArrayOfByte)
  {
    try
    {
      this.c = paramArrayOfByte;
      label5: return;
    }
    catch (p localp)
    {
      break label5;
    }
  }

  public void setSessionNumber(byte[] paramArrayOfByte)
  {
    try
    {
      this.b = paramArrayOfByte;
      label5: return;
    }
    catch (p localp)
    {
      break label5;
    }
  }

  public void setTerminalRandom(byte[] paramArrayOfByte)
  {
    try
    {
      this.a = paramArrayOfByte;
      label5: return;
    }
    catch (p localp)
    {
      break label5;
    }
  }

  public byte[] toBytes()
  {
    int m = 0;
    byte[] arrayOfByte = new byte[32];
    for (int n = 0; ; n++)
    {
      if (n >= 8)
        for (int i1 = 0; ; i1++)
        {
          if (i1 >= 4)
            for (int i2 = 0; ; i2++)
            {
              if (i2 >= 16)
                while (true)
                {
                  if (m >= 4)
                    return arrayOfByte;
                  arrayOfByte[(m + 28)] = this.d[m];
                  m++;
                }
              arrayOfByte[(i2 + 12)] = this.c[i2];
            }
          arrayOfByte[(i1 + 8)] = this.b[i1];
        }
      arrayOfByte[(n + 0)] = this.a[n];
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.core.OpenSessionResponse
 * JD-Core Version:    0.6.0
 */