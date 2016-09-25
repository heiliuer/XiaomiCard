package cn.com.fmsh.tsm.business.bean;

import bx;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class Activity
{
  public static Activity fromTag(ITag paramITag)
    throws FMCommunicationMessageException
  {
    try
    {
      FMLog localFMLog = LogFactory.getInstance().getLog();
      Activity localActivity2;
      ITag localITag;
      int j;
      int k;
      if (paramITag == null)
      {
        if (localFMLog != null)
          localFMLog.warn(BusinessOrder.class.getName(), FM_Exception.getChars(3, 5, "|轭捤_qr寣豾刴洲劦俲恷旫ｎ\023-6宯谺乺稿"));
      }
      else
      {
        ITag[] arrayOfITag = paramITag.getItemTags();
        if ((arrayOfITag == null) || (arrayOfITag.length < 1))
        {
          if (localFMLog == null)
            break label308;
          localFMLog.warn(BusinessOrder.class.getName(), Util4Java.toString("#轨捧Rfo寰豫刻洷劥俯恠旦＝Frs寬豷中穢", 4, 1));
          break label308;
          localActivity2.setPayMin(localITag.getIntVal());
          break label313;
        }
        localActivity2 = new Activity();
        j = arrayOfITag.length;
        k = 0;
        break label316;
        localITag = arrayOfITag[k];
      }
      switch (localITag.getId())
      {
      case -117:
      case -126:
        localActivity2.setName(localITag.getStringVal());
        break;
      case -120:
        localActivity2.setDefinition(localITag.getStringVal());
        break;
      case -125:
        localActivity2.setCode(localITag.getStringVal());
        break;
      case -121:
        localActivity2.setRemainder(localITag.getIntVal());
        break;
      case -124:
        localActivity2.setStart(localITag.getStringVal());
        break;
      case -123:
        localActivity2.setEnd(localITag.getStringVal());
        break;
      case -118:
        localActivity2.setPayChannel(localITag.getStringVal());
        break;
      case -122:
        localActivity2.setTotal(localITag.getIntVal());
        break;
        label306: label308: for (localActivity1 = null; ; localActivity1 = null)
          return localActivity1;
      case -119:
      }
      while (true)
      {
        label313: k++;
        label316: if (k < j)
          break;
        localActivity1 = localActivity2;
        break label306;
      }
    }
    catch (bx localbx)
    {
      while (true)
        Activity localActivity1 = null;
    }
  }

  public String getCode()
  {
    return this.b;
  }

  public String getDefinition()
  {
    return this.g;
  }

  public String getEnd()
  {
    return this.d;
  }

  public String getName()
  {
    return this.a;
  }

  public String getPayChannel()
  {
    return this.h;
  }

  public int getPayMin()
  {
    return this.i;
  }

  public int getRemainder()
  {
    return this.f;
  }

  public String getStart()
  {
    return this.c;
  }

  public int getTotal()
  {
    return this.e;
  }

  public void setCode(String paramString)
  {
    try
    {
      this.b = paramString;
      label5: return;
    }
    catch (bx localbx)
    {
      break label5;
    }
  }

  public void setDefinition(String paramString)
  {
    try
    {
      this.g = paramString;
      label5: return;
    }
    catch (bx localbx)
    {
      break label5;
    }
  }

  public void setEnd(String paramString)
  {
    try
    {
      this.d = paramString;
      label5: return;
    }
    catch (bx localbx)
    {
      break label5;
    }
  }

  public void setName(String paramString)
  {
    try
    {
      this.a = paramString;
      label5: return;
    }
    catch (bx localbx)
    {
      break label5;
    }
  }

  public void setPayChannel(String paramString)
  {
    try
    {
      this.h = paramString;
      label5: return;
    }
    catch (bx localbx)
    {
      break label5;
    }
  }

  public void setPayMin(int paramInt)
  {
    try
    {
      this.i = paramInt;
      label5: return;
    }
    catch (bx localbx)
    {
      break label5;
    }
  }

  public void setRemainder(int paramInt)
  {
    try
    {
      this.f = paramInt;
      label5: return;
    }
    catch (bx localbx)
    {
      break label5;
    }
  }

  public void setStart(String paramString)
  {
    try
    {
      this.c = paramString;
      label5: return;
    }
    catch (bx localbx)
    {
      break label5;
    }
  }

  public void setTotal(int paramInt)
  {
    try
    {
      this.e = paramInt;
      label5: return;
    }
    catch (bx localbx)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.Activity
 * JD-Core Version:    0.6.0
 */