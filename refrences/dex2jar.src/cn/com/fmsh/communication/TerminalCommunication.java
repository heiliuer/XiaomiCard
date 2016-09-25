package cn.com.fmsh.communication;

import cn.com.fmsh.communication.core.CloseSessionRequest;
import cn.com.fmsh.communication.core.LinkInfo;
import cn.com.fmsh.communication.core.TerminalInfo;
import cn.com.fmsh.communication.exception.CommunicationException;
import cn.com.fmsh.communication.exception.SocketException;
import cn.com.fmsh.communication.exception.session.CloseSessionException;
import cn.com.fmsh.communication.exception.session.OpenSessionException;
import cn.com.fmsh.exception.InvalidParameterException;
import java.util.Date;

public abstract interface TerminalCommunication
{
  public static final int SERVER_SESSION_TIMEOUT = 1200000;

  public abstract void cancel();

  public abstract boolean closeSession(CloseSessionRequest paramCloseSessionRequest)
    throws InvalidParameterException, SocketException, CommunicationException, CloseSessionException;

  public abstract boolean connect(LinkInfo paramLinkInfo)
    throws InvalidParameterException, SocketException;

  public abstract boolean disconnect()
    throws SocketException;

  public abstract Date getLastHeartBeat();

  public abstract byte[] getSessionNumber();

  public abstract long getSessionSerialNumber();

  public abstract boolean isConnect();

  public abstract boolean isOpenSession();

  public abstract boolean lastRequestDataIsNull();

  public abstract boolean openSession(TerminalInfo paramTerminalInfo, boolean paramBoolean)
    throws InvalidParameterException, SocketException, CommunicationException, OpenSessionException;

  public abstract void registerCommunicationNotify(CommunicationNotify paramCommunicationNotify);

  public abstract byte[] repeat()
    throws SocketException, CommunicationException;

  public abstract int repeatAsynchronous()
    throws InvalidParameterException;

  public abstract byte[] sendMessage(byte[] paramArrayOfByte)
    throws InvalidParameterException, SocketException, CommunicationException;

  public abstract int sendMessageAsynchronous(byte[] paramArrayOfByte)
    throws InvalidParameterException;

  public abstract void setExceptionTryCount(int paramInt);

  public abstract void setInterval4Heartbeat(int paramInt);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.TerminalCommunication
 * JD-Core Version:    0.6.0
 */