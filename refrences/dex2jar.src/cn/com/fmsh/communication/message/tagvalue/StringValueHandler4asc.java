package cn.com.fmsh.communication.message.tagvalue;

import av;

public class StringValueHandler4asc
  implements StringValueHandler
{
  public String getTagvalue(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
      try
      {
        if (paramArrayOfByte.length >= 1)
          str = new String(paramArrayOfByte);
      }
      catch (av localav)
      {
        str = null;
      }
    String str = null;
    return str;
  }

  public byte[] setTagValue(String paramString)
  {
    Object localObject = null;
    if (paramString == null);
    while (true)
    {
      return localObject;
      try
      {
        byte[] arrayOfByte = paramString.getBytes();
        localObject = arrayOfByte;
      }
      catch (av localav)
      {
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.tagvalue.StringValueHandler4asc
 * JD-Core Version:    0.6.0
 */