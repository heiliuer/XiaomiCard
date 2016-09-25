package cn.com.fmsh.communication.core;

import s;

public class TerminalInfo
{
  public byte[] getAppend()
  {
    return this.d;
  }

  public byte getBusinessVersion()
  {
    return this.a;
  }

  public byte[] getExponent()
  {
    return this.g;
  }

  public byte getKeyIndex()
  {
    return this.e;
  }

  public byte[] getModulus()
  {
    return this.f;
  }

  public byte[] getSecurityCode()
  {
    return this.c;
  }

  public byte[] getTerminalNumber()
  {
    return this.h;
  }

  public byte[] getTerminalType()
  {
    return this.b;
  }

  public void setAppend(byte[] paramArrayOfByte)
  {
    try
    {
      this.d = paramArrayOfByte;
      label5: return;
    }
    catch (s locals)
    {
      break label5;
    }
  }

  public void setBusinessVersion(byte paramByte)
  {
    try
    {
      this.a = paramByte;
      label5: return;
    }
    catch (s locals)
    {
      break label5;
    }
  }

  public void setExponent(byte[] paramArrayOfByte)
  {
    try
    {
      this.g = paramArrayOfByte;
      label5: return;
    }
    catch (s locals)
    {
      break label5;
    }
  }

  public void setKeyIndex(byte paramByte)
  {
    try
    {
      this.e = paramByte;
      label5: return;
    }
    catch (s locals)
    {
      break label5;
    }
  }

  public void setModulus(byte[] paramArrayOfByte)
  {
    try
    {
      this.f = paramArrayOfByte;
      label5: return;
    }
    catch (s locals)
    {
      break label5;
    }
  }

  public void setSecurityCode(byte[] paramArrayOfByte)
  {
    try
    {
      this.c = paramArrayOfByte;
      label5: return;
    }
    catch (s locals)
    {
      break label5;
    }
  }

  public void setTerminalNumber(byte[] paramArrayOfByte)
  {
    try
    {
      this.h = paramArrayOfByte;
      label5: return;
    }
    catch (s locals)
    {
      break label5;
    }
  }

  public void setTerminalType(byte[] paramArrayOfByte)
  {
    try
    {
      this.b = paramArrayOfByte;
      label5: return;
    }
    catch (s locals)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.core.TerminalInfo
 * JD-Core Version:    0.6.0
 */