package cn.com.fmsh.script.exception;

import bn;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;

public class FMScriptHandleException extends FM_Exception
{
  public FMScriptHandleException(String paramString)
  {
    super(paramString);
  }

  public ScriptHandleExceptionType getType()
  {
    return this.a;
  }

  public void setType(ScriptHandleExceptionType paramScriptHandleExceptionType)
  {
    try
    {
      this.a = paramScriptHandleExceptionType;
      label5: return;
    }
    catch (bn localbn)
    {
      break label5;
    }
  }

  public static enum ScriptHandleExceptionType
  {
    static
    {
      try
      {
        STOPED = new ScriptHandleExceptionType(FM_Exception.getChars(2, 76, "\b\023\\\017N\023"), 0, 1, BCCUtil.endsWith("归前擒伂裶偀歹", 2, 31));
        INVALID_FIRST_ID = new ScriptHandleExceptionType(FM_CN.subSequence("[OF^\002\024\b\004LPZD\022\n\r\027", 4), 1, 2, BCCUtil.endsWith("笩乏朸挄亩斷敉", 166, 42));
        INVALID_NEXT = new ScriptHandleExceptionType(FM_Bytes.startsWith("M\fV_\020S\034I\032W\bZ", 3, 62), 2, 3, FM_Long.concat("三杬挟产廁厮奣仁弉剈废召", 298));
        UNKNOW = new ScriptHandleExceptionType(FM_Utils.copyValueOf(1, 22, "YLS��\013\r"), 3, -1, FM_Utils.copyValueOf(5, 43, "机矾镟课"));
        ScriptHandleExceptionType[] arrayOfScriptHandleExceptionType = new ScriptHandleExceptionType[4];
        arrayOfScriptHandleExceptionType[0] = STOPED;
        arrayOfScriptHandleExceptionType[1] = INVALID_FIRST_ID;
        arrayOfScriptHandleExceptionType[2] = INVALID_NEXT;
        arrayOfScriptHandleExceptionType[3] = UNKNOW;
        c = arrayOfScriptHandleExceptionType;
        label146: return;
      }
      catch (bn localbn)
      {
        break label146;
      }
    }

    public String getDescription()
    {
      return this.b;
    }

    public int getValue()
    {
      return this.a;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.exception.FMScriptHandleException
 * JD-Core Version:    0.6.0
 */