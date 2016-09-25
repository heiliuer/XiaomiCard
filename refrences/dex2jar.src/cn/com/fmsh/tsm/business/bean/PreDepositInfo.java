package cn.com.fmsh.tsm.business.bean;

import cc;

public class PreDepositInfo
{
  public int getBlance()
  {
    return this.b;
  }

  public int getTotal()
  {
    return this.a;
  }

  public void setBlance(int paramInt)
  {
    try
    {
      this.b = paramInt;
      label5: return;
    }
    catch (cc localcc)
    {
      break label5;
    }
  }

  public void setTotal(int paramInt)
  {
    try
    {
      this.a = paramInt;
      label5: return;
    }
    catch (cc localcc)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.PreDepositInfo
 * JD-Core Version:    0.6.0
 */