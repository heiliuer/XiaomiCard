package cn.com.fmsh.communication.message.tagvalue;

import at;
import cn.com.fmsh.communication.message.enumerate.ETagType;
import java.util.Map;

public class HandlerFactory
{
  public static HandlerFactory instance()
  {
    if (a == null)
      a = new HandlerFactory();
    return a;
  }

  public StringValueHandler getStringValueHandle(ETagType paramETagType)
  {
    try
    {
      localStringValueHandler = (StringValueHandler)this.b.get(paramETagType);
      return localStringValueHandler;
    }
    catch (at localat)
    {
      while (true)
        StringValueHandler localStringValueHandler = null;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.tagvalue.HandlerFactory
 * JD-Core Version:    0.6.0
 */