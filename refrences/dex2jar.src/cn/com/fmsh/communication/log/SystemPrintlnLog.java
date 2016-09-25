package cn.com.fmsh.communication.log;

import _;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.Level;
import java.io.PrintStream;

public class SystemPrintlnLog
  implements FMLog
{
  public void debug(String paramString1, String paramString2)
  {
    if (this.b.getId() <= Level.DEBUG.getId())
      System.out.println(Util4Java.toString("E\030[@\026`$", 2, 92) + paramString1 + " " + paramString2);
  }

  public void error(String paramString1, String paramString2)
  {
    if (this.b.getId() <= Level.ERROR.getId())
      System.out.println(Util4Java.toString("Z\tE\034]f*", 160, 60) + paramString1 + " " + paramString2);
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
      System.out.println(Util4Java.toString("K]BZkz", 3, 17) + paramString1 + " " + paramString2);
  }

  public void setShowLogFlag(boolean paramBoolean)
  {
    try
    {
      this.a = paramBoolean;
      label5: return;
    }
    catch (_ local_)
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
    catch (_ local_)
    {
      break label5;
    }
  }

  public void warn(String paramString1, String paramString2)
  {
    if (this.b.getId() <= Level.WARNING.getId())
      System.out.println(BCCUtil.endsWith("\fC\033\036:3", 252, 39) + paramString1 + " " + paramString2);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.log.SystemPrintlnLog
 * JD-Core Version:    0.6.0
 */