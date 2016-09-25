package cn.com.fmsh.communication.exception;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import v;

public class CommunicationException extends FM_Exception
{
  public CommunicationException(String paramString)
  {
    super(paramString);
  }

  public CommandDirection getDirection()
  {
    return this.b;
  }

  public CommunicationExceptionType getExceptionType()
  {
    return this.a;
  }

  public void setDirection(CommandDirection paramCommandDirection)
  {
    try
    {
      this.b = paramCommandDirection;
      label5: return;
    }
    catch (v localv)
    {
      break label5;
    }
  }

  public void setExceptionType(CommunicationExceptionType paramCommunicationExceptionType)
  {
    try
    {
      this.a = paramCommunicationExceptionType;
      label5: return;
    }
    catch (v localv)
    {
      break label5;
    }
  }

  public static enum CommandDirection
  {
    static
    {
      try
      {
        REQUESR = new CommandDirection(FM_Exception.getChars(4, 124, "\017\034\004\004\b\032\027"), 0, 0, FM_CN.subSequence("绘竰诹江", 2));
        RESPONSE = new CommandDirection(FM_Utils.copyValueOf(4, 83, "]\007FX\024@R\021"), 1, 1, BCCUtil.endsWith("帨叹哚廑", 92, 46));
        CommandDirection[] arrayOfCommandDirection = new CommandDirection[2];
        arrayOfCommandDirection[0] = REQUESR;
        arrayOfCommandDirection[1] = RESPONSE;
        c = arrayOfCommandDirection;
        label76: return;
      }
      catch (v localv)
      {
        break label76;
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

  public static enum CommunicationExceptionType
  {
    static
    {
      try
      {
        INVALID_VERSION = new CommunicationExceptionType(FM_Bytes.startsWith("MM\024��LVZ\002\n^H\n\021XX", 3, 31), 0, 1, FM_Utils.copyValueOf(2, 45, "旭敒匈论爉杢"));
        INVALID_FORMAT = new CommunicationExceptionType(CRCUtil.valueOf(142, "M\033\020\026\004PNDJ\022\034\022\021\025"), 1, 2, FM_CN.subSequence("抴文栳弑断攔", 3));
        CHECK_FAILED = new CommunicationExceptionType(FM_Long.concat("\t\035EH]^JV\013\001\035\007", 242), 2, 3, FM_Long.concat("拸斏棓骒夸贱", 5));
        INVALID_CONTROL = new CommunicationExceptionType(FM_Long.concat("\024FE_E][\025\026\017\005\002SC[", 5), 3, 4, FM_Utils.copyValueOf(5, 5, "抵斒掽利孓早敆"));
        INVALID_SESSION = new CommunicationExceptionType(BCCUtil.endsWith("\026\035QZC\n\023\024\fVTH\006\f\031", 224, 20), 4, 5, FM_Int.lastIndexOf(234, "佇讃斿攈"));
        INVALID_SESSION_NUMBER = new CommunicationExceptionType(FM_Exception.getChars(4, 38, "\024M\037\016YR\005X^\026\nL\f\004_H\023VD\r\020I"), 5, 6, Util4Java.toString("伙诃浘池斯敂", 4, 27));
        INVALID_DIRECTION = new CommunicationExceptionType(FM_Exception.getChars(4, 110, "\024\005\017\006YJU��\t\022\033RFGH��\023"), 6, 7, FM_Exception.getChars(4, 83, "攭挾斺呇彋帤"));
        NO_REPONSE = new CommunicationExceptionType(FM_Bytes.startsWith("_\035\fFP\006\030VJ\037", 144, 97), 7, 8, BCCUtil.endsWith("朩敿剿哘序敱挩", 4, 102));
        INVALID_REPONSE = new CommunicationExceptionType(CRCUtil.valueOf(100, "\023\005JLRFD\016\020\026\024ZHDM"), 8, 9, FM_Int.lastIndexOf(2, "咘廂攧挶乔呒沎"));
        UNKNOW = new CommunicationExceptionType(FM_Int.lastIndexOf(1, "\001\033\035\031\027\016"), 9, -1, FM_Int.lastIndexOf(296, "朱矹锄诱"));
        CommunicationExceptionType[] arrayOfCommunicationExceptionType = new CommunicationExceptionType[10];
        arrayOfCommunicationExceptionType[0] = INVALID_VERSION;
        arrayOfCommunicationExceptionType[1] = INVALID_FORMAT;
        arrayOfCommunicationExceptionType[2] = CHECK_FAILED;
        arrayOfCommunicationExceptionType[3] = INVALID_CONTROL;
        arrayOfCommunicationExceptionType[4] = INVALID_SESSION;
        arrayOfCommunicationExceptionType[5] = INVALID_SESSION_NUMBER;
        arrayOfCommunicationExceptionType[6] = INVALID_DIRECTION;
        arrayOfCommunicationExceptionType[7] = NO_REPONSE;
        arrayOfCommunicationExceptionType[8] = INVALID_REPONSE;
        arrayOfCommunicationExceptionType[9] = UNKNOW;
        c = arrayOfCommunicationExceptionType;
        label355: return;
      }
      catch (v localv)
      {
        break label355;
      }
    }

    public static CommunicationExceptionType instance(int paramInt)
    {
      Object localObject;
      try
      {
        CommunicationExceptionType[] arrayOfCommunicationExceptionType = values();
        int i = arrayOfCommunicationExceptionType.length;
        for (int j = 0; ; j++)
        {
          if (j >= i)
          {
            localObject = UNKNOW;
            break;
          }
          CommunicationExceptionType localCommunicationExceptionType = arrayOfCommunicationExceptionType[j];
          int k = localCommunicationExceptionType.getValue();
          if (k != paramInt)
            continue;
          localObject = localCommunicationExceptionType;
          break;
        }
      }
      catch (v localv)
      {
        localObject = null;
      }
      return (CommunicationExceptionType)localObject;
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
 * Qualified Name:     cn.com.fmsh.communication.exception.CommunicationException
 * JD-Core Version:    0.6.0
 */