package cn.com.fmsh.communication.core;

import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import j;

public class ControlWord
{
  public void fromBytes(byte paramByte)
  {
    if ((paramByte & 0x80) == 0)
      this.a = MessageType.BUSINESS;
    while (true)
    {
      if ((paramByte & 0x10) == 0)
      {
        this.b = Direction.REQUEST;
        label29: if (this.b == Direction.RESPONSE)
        {
          if (this.a != MessageType.BUSINESS)
            break label91;
          if ((paramByte & 0xF) != 0)
            break label181;
          this.d = false;
        }
      }
      while (true)
      {
        label61: return;
        label91: 
        do
        {
          this.d = true;
          break label61;
          do
          {
            this.e = (paramByte & 0xF);
            break label61;
            this.c = CommandType.OPENSESSION;
            break;
          }
          while (this.c != CommandType.HEARTBEAT);
        }
        while ((paramByte & 0xF) != 0);
        this.d = false;
        continue;
        this.b = Direction.RESPONSE;
        break label29;
        this.a = MessageType.CONTROL;
        switch ((paramByte & 0x60) >> 5)
        {
        case 1:
        default:
          break;
        case 0:
          this.c = CommandType.HEARTBEAT;
          break;
          label181: this.d = true;
        case 2:
        case 3:
        }
      }
      this.c = CommandType.CLOSESESSION;
      continue;
      this.c = CommandType.BUSINESS_ERROR;
    }
  }

  public CommandType getCommandType()
  {
    return this.c;
  }

  public Direction getDirection()
  {
    return this.b;
  }

  public byte getReponseCode()
  {
    return this.e;
  }

  public MessageType getType()
  {
    return this.a;
  }

  public boolean isHaveNews()
  {
    return this.d;
  }

  public void setCommandType(CommandType paramCommandType)
  {
    try
    {
      this.c = paramCommandType;
      label5: return;
    }
    catch (j localj)
    {
      break label5;
    }
  }

  public void setDirection(Direction paramDirection)
  {
    try
    {
      this.b = paramDirection;
      label5: return;
    }
    catch (j localj)
    {
      break label5;
    }
  }

  public void setHaveNews(boolean paramBoolean)
  {
    try
    {
      this.d = paramBoolean;
      label5: return;
    }
    catch (j localj)
    {
      break label5;
    }
  }

  public void setReponseCode(byte paramByte)
  {
    try
    {
      this.e = paramByte;
      label5: return;
    }
    catch (j localj)
    {
      break label5;
    }
  }

  public void setType(MessageType paramMessageType)
  {
    try
    {
      this.a = paramMessageType;
      label5: return;
    }
    catch (j localj)
    {
      break label5;
    }
  }

  public byte toBytes()
  {
    int i = 0;
    if (this.a == MessageType.CONTROL)
    {
      i = (byte)-128;
      switch (a()[this.c.ordinal()])
      {
      default:
      case 4:
      case 2:
      case 3:
      case 1:
      }
    }
    while (true)
    {
      if (this.b == Direction.RESPONSE)
      {
        i &= 16;
        if ((this.c != CommandType.HEARTBEAT) && (this.a != MessageType.BUSINESS))
          break label104;
        if (this.d)
          i |= 1;
      }
      while (true)
      {
        return i;
        label104: i |= 0xF & this.e;
      }
      i |= 96;
      continue;
      i |= 32;
      continue;
      i |= 64;
      continue;
    }
  }

  public static enum CommandType
  {
    static
    {
      try
      {
        HEARTBEAT = new CommandType(FM_Utils.copyValueOf(126, 86, "A\032TY\025UH\002M"), 0, 0);
        OPENSESSION = new CommandType(CRCUtil.valueOf(1, "\030\030\034\004HIN]\026\037\017"), 1, 1);
        CLOSESESSION = new CommandType(FM_CN.subSequence("QM_L\013\016\t\bYPGY", 4), 2, 2);
        BUSINESS_ERROR = new CommandType(FM_Long.concat("\037]@WGQL\031\n\005\031\004N^", 5), 3, 3);
        CommandType[] arrayOfCommandType = new CommandType[4];
        arrayOfCommandType[0] = HEARTBEAT;
        arrayOfCommandType[1] = OPENSESSION;
        arrayOfCommandType[2] = CLOSESESSION;
        arrayOfCommandType[3] = BUSINESS_ERROR;
        b = arrayOfCommandType;
        label108: return;
      }
      catch (j localj)
      {
        break label108;
      }
    }
  }

  public static enum Direction
  {
    static
    {
      try
      {
        REQUEST = new Direction(FM_Long.concat("\013A^O@CO", 1), 0, 0);
        RESPONSE = new Direction(Util4Java.toString("Q\021V\006\bV\032_", 4, 81), 1, 1);
        Direction[] arrayOfDirection = new Direction[2];
        arrayOfDirection[0] = REQUEST;
        arrayOfDirection[1] = RESPONSE;
        b = arrayOfDirection;
        label59: return;
      }
      catch (j localj)
      {
        break label59;
      }
    }
  }

  public static enum MessageType
  {
    static
    {
      try
      {
        BUSINESS = new MessageType(FM_Utils.copyValueOf(6, 81, "S\027@M\033C\004\033"), 0, 0);
        CONTROL = new MessageType(FM_Utils.copyValueOf(4, 126, "LBE]UJO"), 1, 1);
        MessageType[] arrayOfMessageType = new MessageType[2];
        arrayOfMessageType[0] = BUSINESS;
        arrayOfMessageType[1] = CONTROL;
        b = arrayOfMessageType;
        label62: return;
      }
      catch (j localj)
      {
        break label62;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.core.ControlWord
 * JD-Core Version:    0.6.0
 */