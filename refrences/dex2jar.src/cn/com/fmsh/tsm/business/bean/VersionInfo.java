package cn.com.fmsh.tsm.business.bean;

import cf;

public class VersionInfo
{
  public String a;
  public boolean b;
  public String c;

  public String getUrl()
  {
    return this.c;
  }

  public String getViersion()
  {
    return this.a;
  }

  public boolean isUpdate()
  {
    return this.b;
  }

  public void setUpdate(boolean paramBoolean)
  {
    try
    {
      this.b = paramBoolean;
      label5: return;
    }
    catch (cf localcf)
    {
      break label5;
    }
  }

  public void setUrl(String paramString)
  {
    try
    {
      this.c = paramString;
      label5: return;
    }
    catch (cf localcf)
    {
      break label5;
    }
  }

  public void setViersion(String paramString)
  {
    try
    {
      this.a = paramString;
      label5: return;
    }
    catch (cf localcf)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.VersionInfo
 * JD-Core Version:    0.6.0
 */