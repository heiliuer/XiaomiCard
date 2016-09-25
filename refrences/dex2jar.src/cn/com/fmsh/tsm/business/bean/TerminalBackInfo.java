package cn.com.fmsh.tsm.business.bean;

import cd;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.tsm.business.enums.EnumBackInfoType;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class TerminalBackInfo
{
  public static TerminalBackInfo fromTag(ITag paramITag)
    throws FMCommunicationMessageException
  {
    try
    {
      FMLog localFMLog = LogFactory.getInstance().getLog();
      TerminalBackInfo localTerminalBackInfo2;
      Object localObject;
      int m;
      int n;
      if (paramITag == null)
      {
        if (localFMLog != null)
        {
          localFMLog.warn(BusinessOrder.class.getName(), BCCUtil.endsWith("k轮挻D&9寬谭刳厗香侩怰无ａP:u宰象中稴", 236, 55));
          break label295;
          localTerminalBackInfo2.setChildren(localObject.getBytesVal());
          break label299;
        }
      }
      else
      {
        ITag[] arrayOfITag = paramITag.getItemTags();
        if ((arrayOfITag == null) || (arrayOfITag.length < 1))
        {
          if (localFMLog == null)
            break label315;
          localFMLog.warn(BusinessOrder.class.getName(), CRCUtil.valueOf(220, "r輯捶Qw`审谨剪历馔俬恱旹，\005#4宽豴丼穭"));
          break label315;
          localTerminalBackInfo2.setQuestionFlag(localObject.getIntVal());
          break label299;
          localTerminalBackInfo2.setTitle(localObject.getStringVal());
          break label299;
        }
        localTerminalBackInfo2 = new TerminalBackInfo();
        m = arrayOfITag.length;
        n = 0;
        break label302;
        localObject = arrayOfITag[n];
      }
      switch (localObject.getId())
      {
      case 85:
      case 82:
      case 65:
      case 19:
        localTerminalBackInfo2.setDate(localObject.getStringVal());
        break;
      case 67:
        localTerminalBackInfo2.setInfoType(EnumBackInfoType.instance(localObject.getIntVal()));
        break;
      case 81:
        localTerminalBackInfo2.setId(localObject.getBytesVal());
        break;
      case 20:
        localTerminalBackInfo2.setTime(localObject.getStringVal());
        break;
        label295: localTerminalBackInfo1 = null;
        while (true)
        {
          return localTerminalBackInfo1;
          label299: n++;
          label302: if (n < m)
            break;
          localTerminalBackInfo1 = localTerminalBackInfo2;
          continue;
          label315: localTerminalBackInfo1 = null;
        }
      }
    }
    catch (cd localcd)
    {
      while (true)
      {
        TerminalBackInfo localTerminalBackInfo1 = null;
        continue;
      }
    }
  }

  public String getAppVersion()
  {
    return this.k;
  }

  public String getBasebandVersion()
  {
    return this.j;
  }

  public byte[] getChildren()
  {
    return this.b;
  }

  public String getDate()
  {
    return this.d;
  }

  public byte[] getId()
  {
    return this.a;
  }

  public EnumBackInfoType getInfoType()
  {
    return this.g;
  }

  public String getModelNumber()
  {
    return this.i;
  }

  public String getOsVersion()
  {
    return this.h;
  }

  public int getQuestionFlag()
  {
    return this.c;
  }

  public String getTime()
  {
    return this.e;
  }

  public String getTitle()
  {
    return this.f;
  }

  public void setAppVersion(String paramString)
  {
    try
    {
      this.k = paramString;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }

  public void setBasebandVersion(String paramString)
  {
    try
    {
      this.j = paramString;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }

  public void setChildren(byte[] paramArrayOfByte)
  {
    try
    {
      this.b = paramArrayOfByte;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }

  public void setDate(String paramString)
  {
    try
    {
      this.d = paramString;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }

  public void setId(byte[] paramArrayOfByte)
  {
    try
    {
      this.a = paramArrayOfByte;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }

  public void setInfoType(EnumBackInfoType paramEnumBackInfoType)
  {
    try
    {
      this.g = paramEnumBackInfoType;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }

  public void setModelNumber(String paramString)
  {
    try
    {
      this.i = paramString;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }

  public void setOsVersion(String paramString)
  {
    try
    {
      this.h = paramString;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }

  public void setQuestionFlag(int paramInt)
  {
    try
    {
      this.c = paramInt;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }

  public void setTime(String paramString)
  {
    try
    {
      this.e = paramString;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }

  public void setTitle(String paramString)
  {
    try
    {
      this.f = paramString;
      label5: return;
    }
    catch (cd localcd)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.TerminalBackInfo
 * JD-Core Version:    0.6.0
 */