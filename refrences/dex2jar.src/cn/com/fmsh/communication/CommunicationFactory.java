package cn.com.fmsh.communication;

import b;
import cn.com.fmsh.communication.core.TerminalCommunicationListImpl;

public class CommunicationFactory
{
  public static TerminalCommunication getTerminalCommunication()
  {
    if (a == null)
      monitorenter;
    try
    {
      if (a == null)
        a();
      return a;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static TerminalCommunicationList getTerminalCommunicationList()
  {
    try
    {
      localTerminalCommunicationListImpl = new TerminalCommunicationListImpl();
      return localTerminalCommunicationListImpl;
    }
    catch (b localb)
    {
      while (true)
        TerminalCommunicationListImpl localTerminalCommunicationListImpl = null;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.CommunicationFactory
 * JD-Core Version:    0.6.0
 */