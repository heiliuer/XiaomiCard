package cn.com.fmsh.tsm.business.bean;

import ce;
import java.io.Serializable;

public class UserInfo
  implements Serializable
{
  public String a = null;
  public String b = null;
  public int c = -1;
  public String d = null;
  public String e = null;
  public String f = null;
  public int g = -1;
  public String h = null;

  public String getCertNo()
  {
    return this.h;
  }

  public int getCertType()
  {
    return this.g;
  }

  public String getMail()
  {
    return this.d;
  }

  public String getPassword()
  {
    return this.b;
  }

  public String getPhone()
  {
    return this.e;
  }

  public String getRealName()
  {
    return this.f;
  }

  public String getUserName()
  {
    return this.a;
  }

  public int getUserType()
  {
    return this.c;
  }

  public void setCertNo(String paramString)
  {
    try
    {
      this.h = paramString;
      label5: return;
    }
    catch (ce localce)
    {
      break label5;
    }
  }

  public void setCertType(int paramInt)
  {
    try
    {
      this.g = paramInt;
      label5: return;
    }
    catch (ce localce)
    {
      break label5;
    }
  }

  public void setMail(String paramString)
  {
    try
    {
      this.d = paramString;
      label5: return;
    }
    catch (ce localce)
    {
      break label5;
    }
  }

  public void setPassword(String paramString)
  {
    try
    {
      this.b = paramString;
      label5: return;
    }
    catch (ce localce)
    {
      break label5;
    }
  }

  public void setPhone(String paramString)
  {
    try
    {
      this.e = paramString;
      label5: return;
    }
    catch (ce localce)
    {
      break label5;
    }
  }

  public void setRealName(String paramString)
  {
    try
    {
      this.f = paramString;
      label5: return;
    }
    catch (ce localce)
    {
      break label5;
    }
  }

  public void setUserName(String paramString)
  {
    try
    {
      this.a = paramString;
      label5: return;
    }
    catch (ce localce)
    {
      break label5;
    }
  }

  public void setUserType(int paramInt)
  {
    try
    {
      this.c = paramInt;
      label5: return;
    }
    catch (ce localce)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.UserInfo
 * JD-Core Version:    0.6.0
 */