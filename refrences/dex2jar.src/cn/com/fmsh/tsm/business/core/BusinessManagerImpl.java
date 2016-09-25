package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.communication.CommunicationNotify;
import cn.com.fmsh.communication.core.LinkInfo;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.tsm.business.BusinessExtend;
import cn.com.fmsh.tsm.business.BusinessManager;
import cn.com.fmsh.tsm.business.CardAppInstall;
import cn.com.fmsh.tsm.business.CardAppTrade;
import cn.com.fmsh.tsm.business.LocalDataHandler;
import cn.com.fmsh.tsm.business.SocketExceptionHandler;
import cq;

public class BusinessManagerImpl
  implements BusinessManager
{
  public BusinessExtend getBusinessExtend()
  {
    return this.c;
  }

  public CardAppInstall getCardAppInstall()
  {
    return this.a;
  }

  public CardAppTrade getCardAppTrade()
  {
    return this.b;
  }

  public CardBusinessBasic getCardBusinessBasic()
  {
    return this.d;
  }

  public ErrorCodeHandler getErrorCodeHandler()
  {
    try
    {
      ErrorCodeHandler localErrorCodeHandler2 = this.d.getErrorCodeHandler();
      localErrorCodeHandler1 = localErrorCodeHandler2;
      return localErrorCodeHandler1;
    }
    catch (cq localcq)
    {
      while (true)
        ErrorCodeHandler localErrorCodeHandler1 = null;
    }
  }

  public void registerCommunicationNotify(CommunicationNotify paramCommunicationNotify)
  {
    try
    {
      this.d.registerCommunicationNotify(paramCommunicationNotify);
      label8: return;
    }
    catch (cq localcq)
    {
      break label8;
    }
  }

  public void registerLocalDataHandler(LocalDataHandler paramLocalDataHandler)
  {
    try
    {
      this.d.registerLocalDataHandler(paramLocalDataHandler);
      label8: return;
    }
    catch (cq localcq)
    {
      break label8;
    }
  }

  public void setApduHandler(ApduHandler paramApduHandler)
  {
    try
    {
      this.d.setApduHandler(paramApduHandler);
      label8: return;
    }
    catch (cq localcq)
    {
      break label8;
    }
  }

  public void setLinkInfo(LinkInfo paramLinkInfo)
  {
    try
    {
      this.d.setLinkInfo(paramLinkInfo);
      label8: return;
    }
    catch (cq localcq)
    {
      break label8;
    }
  }

  public void setMobileInfo(byte[] paramArrayOfByte)
  {
    try
    {
      this.d.setMobileInfo(paramArrayOfByte);
      label8: return;
    }
    catch (cq localcq)
    {
      break label8;
    }
  }

  public boolean setSecurityCode(byte[] paramArrayOfByte)
  {
    int i = 1;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < i))
      i = 0;
    while (true)
    {
      return i;
      do
      {
        this.d.setSecurityCode(paramArrayOfByte);
        break;
      }
      while (paramArrayOfByte[0] == -1 + paramArrayOfByte.length);
      i = 0;
    }
  }

  public void setSocketExceptionHandle(SocketExceptionHandler paramSocketExceptionHandler)
  {
    try
    {
      this.d.setSocketExceptionHandle(paramSocketExceptionHandler);
      label8: return;
    }
    catch (cq localcq)
    {
      break label8;
    }
  }

  public boolean setTerminalNumber(byte[] paramArrayOfByte)
  {
    int i = 0;
    if (paramArrayOfByte != null)
      try
      {
        if (paramArrayOfByte.length == 32)
        {
          this.d.setTerminalNumber(paramArrayOfByte);
          i = 1;
        }
      }
      catch (cq localcq)
      {
      }
    return i;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.core.BusinessManagerImpl
 * JD-Core Version:    0.6.0
 */