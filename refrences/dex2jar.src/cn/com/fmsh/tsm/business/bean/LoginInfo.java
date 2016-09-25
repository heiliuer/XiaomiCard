package cn.com.fmsh.tsm.business.bean;

import b4;

public class LoginInfo
{
  public int a;
  public int b;
  public int c;

  public int getFailureNum()
  {
    return this.b;
  }

  public int getResult()
  {
    return this.a;
  }

  public int getUserLockTime()
  {
    return this.c;
  }

  public void setFailureNum(int paramInt)
  {
    try
    {
      this.b = paramInt;
      label5: return;
    }
    catch (b4 localb4)
    {
      break label5;
    }
  }

  public void setResult(int paramInt)
  {
    try
    {
      this.a = paramInt;
      label5: return;
    }
    catch (b4 localb4)
    {
      break label5;
    }
  }

  public void setUserLockTime(int paramInt)
  {
    try
    {
      this.c = paramInt;
      label5: return;
    }
    catch (b4 localb4)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.LoginInfo
 * JD-Core Version:    0.6.0
 */