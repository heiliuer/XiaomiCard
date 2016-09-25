package cn.com.fmsh.util.log;

public abstract interface FMLog
{
  public abstract void debug(String paramString1, String paramString2);

  public abstract void error(String paramString1, String paramString2);

  public abstract boolean getShowLogFlag();

  public abstract Level getShowLogLevel();

  public abstract void info(String paramString1, String paramString2);

  public abstract void setShowLogFlag(boolean paramBoolean);

  public abstract void setShowLogLevel(Level paramLevel);

  public abstract void warn(String paramString1, String paramString2);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.log.FMLog
 * JD-Core Version:    0.6.0
 */