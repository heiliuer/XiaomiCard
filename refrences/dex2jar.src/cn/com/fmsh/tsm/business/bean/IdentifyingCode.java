package cn.com.fmsh.tsm.business.bean;

import b2;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class IdentifyingCode
{
  public static IdentifyingCode fromTag(ITag paramITag)
    throws FMCommunicationMessageException
  {
    Object localObject = null;
    FMLog localFMLog = LogFactory.getInstance().getLog();
    label38: ITag[] arrayOfITag;
    int j;
    ITag localITag;
    if (paramITag == null)
    {
      if (localFMLog != null)
        localFMLog.warn(BusinessOrder.class.getName(), FM_Exception.getChars(3, 119, "|輿挨\0259(宿豼判骇诃砘審豦斨ｙ\030\"=宨谩严穬"));
      return localObject;
      localITag = arrayOfITag[j];
      switch (localITag.getId())
      {
      default:
      case 11:
      case 64:
      }
    }
    while (true)
    {
      label76: j++;
      IdentifyingCode localIdentifyingCode;
      while (true)
      {
        if (j < i)
          break label38;
        localObject = localIdentifyingCode;
        break;
        localIdentifyingCode.setCode(localITag.getStringVal());
        break label76;
        arrayOfITag = paramITag.getItemTags();
        if ((arrayOfITag == null) || (arrayOfITag.length < 1))
        {
          if (localFMLog == null)
            break;
          localFMLog.warn(BusinessOrder.class.getName(), FM_Int.lastIndexOf(6, "y輶挹\b<9宦象刱骎诂砅导豧旱＄]kl寵豬临穵"));
          break;
        }
        localIdentifyingCode = new IdentifyingCode();
        int i = arrayOfITag.length;
        j = 0;
      }
      localIdentifyingCode.setSerial(localITag.getStringVal());
    }
  }

  public String getCode()
  {
    return this.a;
  }

  public String getSerial()
  {
    return this.b;
  }

  public void setCode(String paramString)
  {
    try
    {
      this.a = paramString;
      label5: return;
    }
    catch (b2 localb2)
    {
      break label5;
    }
  }

  public void setSerial(String paramString)
  {
    try
    {
      this.b = paramString;
      label5: return;
    }
    catch (b2 localb2)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.bean.IdentifyingCode
 * JD-Core Version:    0.6.0
 */