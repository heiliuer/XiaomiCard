package cn.com.fmsh.communication;

import cn.com.fmsh.communication.exception.SocketException;

public abstract interface TerminalCommunicationList
{
  public abstract void disConnect()
    throws SocketException;

  public abstract String[] getNames();

  public abstract TerminalCommunication getTerminalCommunication(String paramString);

  public abstract void removeTerminalCommunication(String paramString);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.TerminalCommunicationList
 * JD-Core Version:    0.6.0
 */