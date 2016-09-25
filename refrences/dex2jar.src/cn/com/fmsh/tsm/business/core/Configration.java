package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.core.LinkInfo;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.Level;
import cw;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Configration
{
  public void addAid(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
      try
      {
        if (paramArrayOfByte.length >= 1)
          this.i.add(paramArrayOfByte);
      }
      catch (cw localcw)
      {
      }
  }

  public void addBusinessAndServer(int paramInt, String paramString)
  {
    if (paramString != null)
      this.k.put(Integer.valueOf(paramInt), paramString);
  }

  public void addKey(String paramString, int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if ((paramString == null) || (paramArrayOfByte1 == null) || (paramArrayOfByte2 == null) || (paramInt == -1))
      if (this.a != null)
        this.a.warn(this.b, FM_Exception.getChars(4, 63, "勽轡鄖罴旞仮乺寐铰俵怼旤｝徕勯轳盉寊铮俫怦旨攏"));
    while (true)
    {
      return;
      Key localKey = new Key();
      localKey.index = paramInt;
      localKey.exponent = paramArrayOfByte1;
      localKey.modulus = paramArrayOfByte2;
      Object localObject = (List)this.h.get(paramString);
      if (localObject == null)
      {
        localObject = new ArrayList();
        this.h.put(paramString, localObject);
      }
      ((List)localObject).add(localKey);
    }
  }

  public void addServers(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    if (paramString1 != null);
    try
    {
      if ((paramString1.length() > 0) && (paramInt1 > 0) && (paramString2 != null) && (paramString2.length() > 0))
      {
        LinkInfo localLinkInfo = new LinkInfo();
        localLinkInfo.setAddress(paramString1);
        localLinkInfo.setPort(paramInt1);
        localLinkInfo.setTimeout(paramInt2);
        this.j.put(paramString2, localLinkInfo);
      }
      label69: return;
    }
    catch (cw localcw)
    {
      break label69;
    }
  }

  public byte[][] getAids()
  {
    try
    {
      List localList = this.i;
      int[] arrayOfInt = new int[2];
      arrayOfInt[0] = 1;
      arrayOfInt[1] = 1;
      arrayOfByte = (byte[][])localList.toArray((byte[][])Array.newInstance(Byte.TYPE, arrayOfInt));
      return arrayOfByte;
    }
    catch (cw localcw)
    {
      while (true)
        byte[][] arrayOfByte = null;
    }
  }

  public byte getBusinessVersion()
  {
    return this.c;
  }

  public String getCompanyCode()
  {
    return this.o;
  }

  public String[] getKeies4Server()
  {
    try
    {
      arrayOfString = (String[])this.j.keySet().toArray(new String[0]);
      return arrayOfString;
    }
    catch (cw localcw)
    {
      while (true)
        String[] arrayOfString = null;
    }
  }

  public Key[] getKeys(String paramString)
  {
    Key[] arrayOfKey;
    try
    {
      List localList = (List)this.h.get(paramString);
      if (localList != null)
      {
        arrayOfKey = (Key[])localList.toArray(new Key[0]);
      }
      else
      {
        if (this.a != null)
          this.a.warn(this.b, Util4Java.toString("酎罪斂仰沦朁R", 4, 1) + paramString + FM_Utils.copyValueOf(5, 93, "M宴廞皃o$g"));
        arrayOfKey = null;
      }
    }
    catch (cw localcw)
    {
      arrayOfKey = null;
    }
    return arrayOfKey;
  }

  public LinkInfo getLinkInfo(String paramString)
  {
    try
    {
      localLinkInfo = (LinkInfo)this.j.get(paramString);
      return localLinkInfo;
    }
    catch (cw localcw)
    {
      while (true)
        LinkInfo localLinkInfo = null;
    }
  }

  public Level getLogLevel()
  {
    return this.p;
  }

  public byte getOrderSource()
  {
    return this.n;
  }

  public String getServer4Business(int paramInt)
  {
    try
    {
      str = (String)this.k.get(Integer.valueOf(paramInt));
      return str;
    }
    catch (cw localcw)
    {
      while (true)
        String str = null;
    }
  }

  public String getServerDomain()
  {
    return this.d;
  }

  public int getServerPort()
  {
    return this.e;
  }

  public int getSocketTimeout()
  {
    return this.l;
  }

  public byte[] getTerminalNumber()
  {
    return this.g;
  }

  public byte[] getTerminalType()
  {
    return this.f;
  }

  public String getUserCode()
  {
    return this.m;
  }

  public void setBusinessVersion(byte paramByte)
  {
    try
    {
      this.c = paramByte;
      label5: return;
    }
    catch (cw localcw)
    {
      break label5;
    }
  }

  public void setCompanyCode(String paramString)
  {
    try
    {
      this.o = paramString;
      label5: return;
    }
    catch (cw localcw)
    {
      break label5;
    }
  }

  public void setLogLevel(Level paramLevel)
  {
    try
    {
      this.p = paramLevel;
      label5: return;
    }
    catch (cw localcw)
    {
      break label5;
    }
  }

  public void setOrderSource(byte paramByte)
  {
    try
    {
      this.n = paramByte;
      label5: return;
    }
    catch (cw localcw)
    {
      break label5;
    }
  }

  public void setServerDomain(String paramString)
  {
    try
    {
      this.d = paramString;
      label5: return;
    }
    catch (cw localcw)
    {
      break label5;
    }
  }

  public void setServerPort(int paramInt)
  {
    try
    {
      this.e = paramInt;
      label5: return;
    }
    catch (cw localcw)
    {
      break label5;
    }
  }

  public void setSocketTimeout(int paramInt)
  {
    try
    {
      this.l = paramInt;
      label5: return;
    }
    catch (cw localcw)
    {
      break label5;
    }
  }

  public void setTerminalNumber(byte[] paramArrayOfByte)
  {
    try
    {
      this.g = paramArrayOfByte;
      label5: return;
    }
    catch (cw localcw)
    {
      break label5;
    }
  }

  public void setTerminalType(byte[] paramArrayOfByte)
  {
    try
    {
      this.f = paramArrayOfByte;
      label5: return;
    }
    catch (cw localcw)
    {
      break label5;
    }
  }

  public void setUserCode(String paramString)
  {
    try
    {
      this.m = paramString;
      label5: return;
    }
    catch (cw localcw)
    {
      break label5;
    }
  }

  public class Key
  {
    public byte[] exponent;
    public int index = 0;
    public byte[] modulus;

    public Key()
    {
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.core.Configration
 * JD-Core Version:    0.6.0
 */