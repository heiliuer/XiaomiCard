import cn.com.fmsh.communication.message.core.MessageTagDefine;
import java.util.ArrayList;
import java.util.List;

public class ai
{
  public List<MessageTagDefine> d = new ArrayList();

  public void addMessageData(MessageTagDefine paramMessageTagDefine)
  {
    try
    {
      this.d.add(paramMessageTagDefine);
      label11: return;
    }
    catch (aj localaj)
    {
      break label11;
    }
  }

  public String getDesc()
  {
    return this.b;
  }

  public int getMessageCode()
  {
    return this.a;
  }

  public MessageTagDefine[] getMessageTagDefines()
  {
    try
    {
      MessageTagDefine[] arrayOfMessageTagDefine2 = new MessageTagDefine[0];
      arrayOfMessageTagDefine1 = (MessageTagDefine[])this.d.toArray(arrayOfMessageTagDefine2);
      return arrayOfMessageTagDefine1;
    }
    catch (aj localaj)
    {
      while (true)
        MessageTagDefine[] arrayOfMessageTagDefine1 = null;
    }
  }

  public String getRetCode()
  {
    return this.c;
  }

  public void setDesc(String paramString)
  {
    try
    {
      this.b = paramString;
      label5: return;
    }
    catch (aj localaj)
    {
      break label5;
    }
  }

  public void setMessageCode(int paramInt)
  {
    try
    {
      this.a = paramInt;
      label5: return;
    }
    catch (aj localaj)
    {
      break label5;
    }
  }

  public void setRetCode(String paramString)
  {
    try
    {
      this.c = paramString;
      label5: return;
    }
    catch (aj localaj)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     ai
 * JD-Core Version:    0.6.0
 */