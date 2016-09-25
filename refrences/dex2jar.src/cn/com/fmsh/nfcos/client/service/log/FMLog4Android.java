package cn.com.fmsh.nfcos.client.service.log;

import a8;
import android.util.Log;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.Level;

public class FMLog4Android
  implements FMLog
{
  public void debug(String paramString1, String paramString2)
  {
    if (this.b.getId() <= Level.DEBUG.getId())
      Log.d(paramString1, paramString2);
  }

  public void error(String paramString1, String paramString2)
  {
    if (this.b.getId() <= Level.ERROR.getId())
      Log.e(paramString1, paramString2);
  }

  public boolean getShowLogFlag()
  {
    return this.a;
  }

  public Level getShowLogLevel()
  {
    return this.b;
  }

  public void info(String paramString1, String paramString2)
  {
    if (this.b.getId() <= Level.INFO.getId())
      Log.i(paramString1, paramString2);
  }

  public void setShowLogFlag(boolean paramBoolean)
  {
    try
    {
      this.a = paramBoolean;
      label5: return;
    }
    catch (a8 locala8)
    {
      break label5;
    }
  }

  public void setShowLogLevel(Level paramLevel)
  {
    try
    {
      this.b = paramLevel;
      label5: return;
    }
    catch (a8 locala8)
    {
      break label5;
    }
  }

  public void warn(String paramString1, String paramString2)
  {
    if (this.b.getId() <= Level.WARNING.getId())
      Log.w(paramString1, paramString2);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.log.FMLog4Android
 * JD-Core Version:    0.6.0
 */