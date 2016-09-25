package cn.com.fmsh.script;

import bd;
import cn.com.fmsh.script.core.ScriptHandlerImpl;

public class ScriptHandlerFactory
{
  public static ScriptHandlerFactory getInstance()
  {
    if (a == null)
      scriptHandlerFactoryInit();
    return a;
  }

  public static void scriptHandlerFactoryInit()
  {
    if (a == null)
      a = new ScriptHandlerFactory();
  }

  public ScriptHandler getScriptHandler(ApduHandler paramApduHandler)
  {
    try
    {
      localScriptHandlerImpl = new ScriptHandlerImpl(paramApduHandler);
      return localScriptHandlerImpl;
    }
    catch (bd localbd)
    {
      while (true)
        ScriptHandlerImpl localScriptHandlerImpl = null;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.ScriptHandlerFactory
 * JD-Core Version:    0.6.0
 */