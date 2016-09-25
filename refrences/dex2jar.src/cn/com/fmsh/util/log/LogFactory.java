package cn.com.fmsh.util.log;

import dz;

public class LogFactory
{
  public static LogFactory getInstance()
  {
    if (a == null)
      a = new LogFactory();
    return a;
  }

  public FMLog getLog()
  {
    return this.b;
  }

  public void setLog(FMLog paramFMLog)
  {
    try
    {
      this.b = paramFMLog;
      label5: return;
    }
    catch (dz localdz)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.log.LogFactory
 * JD-Core Version:    0.6.0
 */