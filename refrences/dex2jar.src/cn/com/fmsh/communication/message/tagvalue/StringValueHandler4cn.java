package cn.com.fmsh.communication.message.tagvalue;

import aw;
import cn.com.fmsh.util.FM_CN;

public class StringValueHandler4cn
  implements StringValueHandler
{
  public String getTagvalue(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    if (paramArrayOfByte != null)
      try
      {
        if (paramArrayOfByte.length >= 1)
        {
          String str = FM_CN.bcdBytesToString(paramArrayOfByte);
          localObject = str;
        }
      }
      catch (aw localaw)
      {
      }
    return localObject;
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
        byte[] arrayOfByte = FM_CN.string2Bcd(paramString);
        localObject = arrayOfByte;
      }
      catch (aw localaw)
      {
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.tagvalue.StringValueHandler4cn
 * JD-Core Version:    0.6.0
 */