package cn.com.fmsh.script;

import bb;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.Util4Java;

public abstract interface ApduHandler
{
  public abstract void close();

  public abstract boolean connect();

  public abstract ApduHandlerType getApduHandlerType();

  public abstract boolean isConnect();

  public abstract boolean open(byte[] paramArrayOfByte);

  public abstract byte[] transceive(byte[] paramArrayOfByte)
    throws FMScriptHandleException;

  public static enum ApduHandlerType
  {
    static
    {
      try
      {
        OPEN_MOBILE = new ApduHandlerType(FM_Long.concat("\025UUUY\\S\005\033\021\r", 2), 0, 1, FM_Exception.getChars(2, 33, "4lx0\022o#+oa"));
        NFC = new ApduHandlerType(Util4Java.toString("_\001^", 146, 86), 1, 2, CRCUtil.valueOf(3, "\027\f\030"));
        FM8301 = new ApduHandlerType(FM_Exception.getChars(3, 10, "\032K()4?"), 2, 3, BCCUtil.endsWith("cso#y3", 6, 57));
        BLUETOOTH = new ApduHandlerType(FM_Int.lastIndexOf(4, "\025\024\f\037\017\023\022\n\027"), 3, 4, FM_CN.subSequence("z+# ��l}ux", 170));
        ApduHandlerType[] arrayOfApduHandlerType = new ApduHandlerType[4];
        arrayOfApduHandlerType[0] = OPEN_MOBILE;
        arrayOfApduHandlerType[1] = NFC;
        arrayOfApduHandlerType[2] = FM8301;
        arrayOfApduHandlerType[3] = BLUETOOTH;
        c = arrayOfApduHandlerType;
        label142: return;
      }
      catch (bb localbb)
      {
        break label142;
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
 * Qualified Name:     cn.com.fmsh.script.ApduHandler
 * JD-Core Version:    0.6.0
 */