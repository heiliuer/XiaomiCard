package cn.com.fmsh.tsm.business.bean;

import ca;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class PasswordPrompt
{
  public static PasswordPrompt fromTag(ITag paramITag)
    throws FMCommunicationMessageException
  {
    try
    {
      FMLog localFMLog = LogFactory.getInstance().getLog();
      PasswordPrompt localPasswordPrompt2;
      Object localObject;
      ITag[] arrayOfITag;
      int j;
      if (paramITag == null)
      {
        if (localFMLog != null)
        {
          localFMLog.warn(BusinessOrder.class.getName(), FM_Exception.getChars(6, 103, "輪振@ze宰谱刧寘砄掜祩俻恮寱谮斠１Pj5宠谡丽穴"));
          break label175;
          localPasswordPrompt2.setCount(localObject.getIntVal());
          break label179;
        }
      }
      else
      {
        arrayOfITag = paramITag.getItemTags();
        if ((arrayOfITag == null) || (arrayOfITag.length < 1))
        {
          if (localFMLog == null)
            break label195;
          localFMLog.warn(BusinessOrder.class.getName(), FM_Bytes.startsWith("\"輤挬@{g宿谭刢寞硟掔祰俱恹宥谣斾＂@;'宿豭丨稢", 1, 102));
          break label195;
          localObject = arrayOfITag[j];
        }
      }
      int i;
      switch (localObject.getId())
      {
      case 36:
      case 4:
        localPasswordPrompt2.setEmail(localObject.getStringVal());
        break;
        localPasswordPrompt2 = new PasswordPrompt();
        i = arrayOfITag.length;
        j = 0;
        break label182;
        label175: localPasswordPrompt1 = null;
        label177: return localPasswordPrompt1;
      }
      while (true)
      {
        label179: j++;
        label182: if (j < i)
          break;
        localPasswordPrompt1 = localPasswordPrompt2;
        break label177;
        label195: localPasswordPrompt1 = null;
        break label177;
      }
    }
    catch (ca localca)
    {
      while (true)
        PasswordPrompt localPasswordPrompt1 = null;
    }
  }

  public int getCount()
  {
    return this.b;
  }

  public String getEmail()
  {
    return this.a;
  }

  public void setCount(int paramInt)
  {
    try
    {
      this.b = paramInt;
      label5: return;
    }
    catch (ca localca)
    {
      break label5;
    }
  }

  public void setEmail(String paramString)
  {
    try
    {
      this.a = paramString;
      label5: return;
    }
    catch (ca localca)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.PasswordPrompt
 * JD-Core Version:    0.6.0
 */