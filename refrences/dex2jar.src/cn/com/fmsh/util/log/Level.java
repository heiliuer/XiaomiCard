package cn.com.fmsh.util.log;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Int;
import dy;

public enum Level
{
  static
  {
    try
    {
      DEBUG = new Level(FM_Exception.getChars(132, 115, "\031\025\001\003\016"), 0, 0);
      INFO = new Level(FM_Bytes.startsWith("\022\032\013\t", 122, 121), 1, 1);
      WARNING = new Level(FM_Int.lastIndexOf(240, "\024\005\027\b\016\006\016"), 2, 2);
      ERROR = new Level(FM_Exception.getChars(116, 67, "\bB\001Y\013"), 3, 3);
      Level[] arrayOfLevel = new Level[4];
      arrayOfLevel[0] = DEBUG;
      arrayOfLevel[1] = INFO;
      arrayOfLevel[2] = WARNING;
      arrayOfLevel[3] = ERROR;
      b = arrayOfLevel;
      label117: return;
    }
    catch (dy localdy)
    {
      break label117;
    }
  }

  public static Level instance(int paramInt)
  {
    Level[] arrayOfLevel = values();
    int i = arrayOfLevel.length;
    for (int j = 0; ; j++)
    {
      if (j >= i);
      Level localLevel;
      for (Object localObject = null; ; localObject = localLevel)
      {
        return localObject;
        localLevel = arrayOfLevel[j];
        if (localLevel.getId() != paramInt)
          break;
      }
    }
  }

  public int getId()
  {
    return this.a;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.log.Level
 * JD-Core Version:    0.6.0
 */