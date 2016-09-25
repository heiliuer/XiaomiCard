package cn.com.fmsh.communication.core;

import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import k;

public class LinkInfo
{
  public String getAddress()
  {
    return this.a;
  }

  public int getPort()
  {
    return this.b;
  }

  public int getTimeout()
  {
    return this.c;
  }

  public void setAddress(String paramString)
  {
    try
    {
      this.a = paramString;
      label5: return;
    }
    catch (k localk)
    {
      break label5;
    }
  }

  public void setPort(int paramInt)
  {
    try
    {
      this.b = paramInt;
      label5: return;
    }
    catch (k localk)
    {
      break label5;
    }
  }

  public void setTimeout(int paramInt)
  {
    try
    {
      this.c = paramInt;
      label5: return;
    }
    catch (k localk)
    {
      break label5;
    }
  }

  public static enum LinkType
  {
    static
    {
      try
      {
        TCP = new LinkType(FM_Bytes.startsWith("PG\024", 3, 32), 0);
        UDP = new LinkType(FM_CN.subSequence("K\t\f", 176), 1);
        HTTP = new LinkType(BCCUtil.endsWith("J\006V\002", 3, 80), 2);
        LinkType[] arrayOfLinkType = new LinkType[3];
        arrayOfLinkType[0] = TCP;
        arrayOfLinkType[1] = UDP;
        arrayOfLinkType[2] = HTTP;
        a = arrayOfLinkType;
        label84: return;
      }
      catch (k localk)
      {
        break label84;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.core.LinkInfo
 * JD-Core Version:    0.6.0
 */