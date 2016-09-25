package cn.com.fmsh.communication.message.tagvalue;

import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_Bytes;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class StringValueHandler4utf
  implements StringValueHandler
{
  public static void main(String[] paramArrayOfString)
  {
    Object localObject = null;
    try
    {
      byte[] arrayOfByte = "€".getBytes(BCCUtil.endsWith("*tgo{", 96, 33));
      localObject = arrayOfByte;
      System.out.println(FM_Bytes.bytesToHexString(localObject));
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        localUnsupportedEncodingException.printStackTrace();
    }
  }

  public String getTagvalue(byte[] paramArrayOfByte)
  {
    String str;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 1))
      str = null;
    while (true)
    {
      return str;
      try
      {
        str = new String(paramArrayOfByte, BCCUtil.endsWith("v-)hc", 4, 118));
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        localUnsupportedEncodingException.printStackTrace();
        str = null;
      }
    }
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
        byte[] arrayOfByte = paramString.getBytes(BCCUtil.endsWith("t`!7u", 2, 51));
        localObject = arrayOfByte;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        localUnsupportedEncodingException.printStackTrace();
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.tagvalue.StringValueHandler4utf
 * JD-Core Version:    0.6.0
 */