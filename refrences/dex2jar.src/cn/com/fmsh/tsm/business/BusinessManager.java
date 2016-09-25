package cn.com.fmsh.tsm.business;

import cn.com.fmsh.communication.CommunicationNotify;
import cn.com.fmsh.communication.core.LinkInfo;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.tsm.business.core.ErrorCodeHandler;

public abstract interface BusinessManager
{
  public abstract BusinessExtend getBusinessExtend();

  public abstract CardAppInstall getCardAppInstall();

  public abstract CardAppTrade getCardAppTrade();

  public abstract ErrorCodeHandler getErrorCodeHandler();

  public abstract void registerCommunicationNotify(CommunicationNotify paramCommunicationNotify);

  public abstract void registerLocalDataHandler(LocalDataHandler paramLocalDataHandler);

  public abstract void setApduHandler(ApduHandler paramApduHandler);

  public abstract void setLinkInfo(LinkInfo paramLinkInfo);

  public abstract void setMobileInfo(byte[] paramArrayOfByte);

  public abstract boolean setSecurityCode(byte[] paramArrayOfByte);

  public abstract void setSocketExceptionHandle(SocketExceptionHandler paramSocketExceptionHandler);

  public abstract boolean setTerminalNumber(byte[] paramArrayOfByte);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.BusinessManager
 * JD-Core Version:    0.6.0
 */