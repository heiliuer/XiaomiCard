package cn.com.fmsh.communication.core;

import cn.com.fmsh.communication.TerminalCommunication;
import cn.com.fmsh.communication.TerminalCommunicationList;
import cn.com.fmsh.communication.exception.SocketException;
import java.util.Map;
import java.util.Set;
import r;

public class TerminalCommunicationListImpl
  implements TerminalCommunicationList
{
  public void disConnect()
    throws SocketException
  {
    String[] arrayOfString = (String[])this.a.keySet().toArray(new String[0]);
    int i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = arrayOfString[j];
      TerminalCommunication localTerminalCommunication = (TerminalCommunication)this.a.get(str);
      if ((localTerminalCommunication == null) || (!localTerminalCommunication.isConnect()))
        continue;
      localTerminalCommunication.disconnect();
    }
  }

  public void disConnect(String paramString)
    throws SocketException
  {
    try
    {
      TerminalCommunication localTerminalCommunication = (TerminalCommunication)this.a.get(paramString);
      if ((localTerminalCommunication != null) && (localTerminalCommunication.isConnect()))
        localTerminalCommunication.disconnect();
      label34: return;
    }
    catch (r localr)
    {
      break label34;
    }
  }

  public String[] getNames()
  {
    try
    {
      arrayOfString = (String[])this.a.keySet().toArray(new String[0]);
      return arrayOfString;
    }
    catch (r localr)
    {
      while (true)
        String[] arrayOfString = null;
    }
  }

  public TerminalCommunication getTerminalCommunication(String paramString)
  {
    Object localObject;
    if ((paramString == null) || (paramString.length() == 0))
      localObject = null;
    while (true)
    {
      return localObject;
      localObject = (TerminalCommunication)this.a.get(paramString);
      if (localObject != null)
        continue;
      localObject = new TerminalCommunicationHandler();
      this.a.put(paramString, localObject);
    }
  }

  public void removeTerminalCommunication(String paramString)
  {
    try
    {
      this.a.remove(paramString);
      label11: return;
    }
    catch (r localr)
    {
      break label11;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.core.TerminalCommunicationListImpl
 * JD-Core Version:    0.6.0
 */