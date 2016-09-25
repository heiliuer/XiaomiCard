package cn.com.fmsh.tsm.business.bean;

import b3;

public class IssuerPrepareResult
{
  public byte[] getFailDesc()
  {
    return this.b;
  }

  public byte[] getSir()
  {
    return this.a;
  }

  public void setFailDesc(byte[] paramArrayOfByte)
  {
    try
    {
      this.b = paramArrayOfByte;
      label5: return;
    }
    catch (b3 localb3)
    {
      break label5;
    }
  }

  public void setSir(byte[] paramArrayOfByte)
  {
    try
    {
      this.a = paramArrayOfByte;
      label5: return;
    }
    catch (b3 localb3)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.IssuerPrepareResult
 * JD-Core Version:    0.6.0
 */