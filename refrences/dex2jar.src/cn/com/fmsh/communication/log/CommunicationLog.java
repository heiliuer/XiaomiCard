package cn.com.fmsh.communication.log;

import android.util.Log;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.Level;
import z;

public class CommunicationLog
  implements FMLog
{
  public boolean showLogFlag = true;

  public void debug(String paramString1, String paramString2)
  {
    try
    {
      Log.d(paramString1, paramString2);
      label6: return;
    }
    catch (z localz)
    {
      break label6;
    }
  }

  public void error(String paramString1, String paramString2)
  {
    try
    {
      Log.e(paramString1, paramString2);
      label6: return;
    }
    catch (z localz)
    {
      break label6;
    }
  }

  public boolean getShowLogFlag()
  {
    return this.showLogFlag;
  }

  public Level getShowLogLevel()
  {
    return this.a;
  }

  public void info(String paramString1, String paramString2)
  {
    try
    {
      Log.i(paramString1, paramString2);
      label6: return;
    }
    catch (z localz)
    {
      break label6;
    }
  }

  public void setShowLogFlag(boolean paramBoolean)
  {
    try
    {
      this.showLogFlag = paramBoolean;
      label5: return;
    }
    catch (z localz)
    {
      break label5;
    }
  }

  public void setShowLogLevel(Level paramLevel)
  {
    try
    {
      this.a = paramLevel;
      label5: return;
    }
    catch (z localz)
    {
      break label5;
    }
  }

  public void warn(String paramString1, String paramString2)
  {
    try
    {
      Log.w(paramString1, paramString2);
      label6: return;
    }
    catch (z localz)
    {
      break label6;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.log.CommunicationLog
 * JD-Core Version:    0.6.0
 */