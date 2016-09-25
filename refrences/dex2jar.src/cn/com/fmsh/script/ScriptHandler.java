package cn.com.fmsh.script;

import cn.com.fmsh.script.bean.ApduReponseList;
import cn.com.fmsh.script.bean.ApduRequestList;
import cn.com.fmsh.script.exception.FMScriptHandleException;

public abstract interface ScriptHandler
{
  public abstract void cancel();

  public abstract ApduReponseList execute(ApduRequestList paramApduRequestList)
    throws FMScriptHandleException;

  public abstract byte[] execute(byte[] paramArrayOfByte)
    throws FMScriptHandleException;

  public abstract void setApduFilterDataInit(ApduFilterDataInit paramApduFilterDataInit);

  public abstract void setApduHandler(ApduHandler paramApduHandler);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.ScriptHandler
 * JD-Core Version:    0.6.0
 */