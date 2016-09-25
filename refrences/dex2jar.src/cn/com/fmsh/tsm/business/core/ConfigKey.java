package cn.com.fmsh.tsm.business.core;

import cu;

public class ConfigKey
{
  public int getIndex()
  {
    return this.a;
  }

  public byte[] getPrivateKey()
  {
    return this.c;
  }

  public byte[] getPublicKey()
  {
    return this.b;
  }

  public void setIndex(int paramInt)
  {
    try
    {
      this.a = paramInt;
      label5: return;
    }
    catch (cu localcu)
    {
      break label5;
    }
  }

  public void setPrivateKey(byte[] paramArrayOfByte)
  {
    try
    {
      this.c = paramArrayOfByte;
      label5: return;
    }
    catch (cu localcu)
    {
      break label5;
    }
  }

  public void setPublicKey(byte[] paramArrayOfByte)
  {
    try
    {
      this.b = paramArrayOfByte;
      label5: return;
    }
    catch (cu localcu)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.core.ConfigKey
 * JD-Core Version:    0.6.0
 */