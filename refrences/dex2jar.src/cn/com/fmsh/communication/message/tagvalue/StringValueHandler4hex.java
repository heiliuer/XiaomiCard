package cn.com.fmsh.communication.message.tagvalue;

import ay;
import cn.com.fmsh.util.FM_Bytes;

public class StringValueHandler4hex
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
          String str = FM_Bytes.bytesToHexString(paramArrayOfByte);
          localObject = str;
        }
      }
      catch (ay localay)
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
        byte[] arrayOfByte = FM_Bytes.hexStringToBytes(paramString);
        localObject = arrayOfByte;
      }
      catch (ay localay)
      {
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.tagvalue.StringValueHandler4hex
 * JD-Core Version:    0.6.0
 */