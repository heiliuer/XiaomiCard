package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ErrorCodeHandler
{
  public int getCode(String paramString)
  {
    String str = this.c.getProperty(paramString);
    if ((str == null) || (str.length() < 1))
      if (this.a != null)
        this.a.debug(this.b, FM_Int.lastIndexOf(202, "莪又D") + paramString + FM_Int.lastIndexOf(152, "V皈哀庚砎夡贴"));
    for (int i = 99; ; i = Integer.parseInt(str.trim()))
      return i;
  }

  public boolean init(InputStream paramInputStream)
  {
    int i = 0;
    if (paramInputStream == null)
      if (this.a != null)
        this.a.warn(this.b, CRCUtil.valueOf(1, "咚廜硘剗姐匚夬贫斩｜佡儷盇酙罫斑仱乢稳"));
    while (true)
    {
      return i;
      try
      {
        this.c.load(paramInputStream);
        i = 1;
      }
      catch (IOException localIOException)
      {
      }
      if (this.a == null)
        continue;
      this.a.warn(this.b, Util4Java.toString("哌庁硈刀妚匓夨赨;", 258, 52) + Util4Java.getExceptionInfo(localIOException));
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.core.ErrorCodeHandler
 * JD-Core Version:    0.6.0
 */