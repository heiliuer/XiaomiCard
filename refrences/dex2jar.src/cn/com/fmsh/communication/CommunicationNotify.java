package cn.com.fmsh.communication;

import cn.com.fmsh.FM_Exception;

public abstract interface CommunicationNotify
{
  public abstract void exceptionNotify(FM_Exception paramFM_Exception, Class<?> paramClass);

  public abstract void heartbeatNoReponseNotify();

  public abstract void newsNotify();

  public abstract void reponseMessageNotify(byte[] paramArrayOfByte);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.CommunicationNotify
 * JD-Core Version:    0.6.0
 */