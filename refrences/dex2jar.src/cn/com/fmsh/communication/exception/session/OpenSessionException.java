package cn.com.fmsh.communication.exception.session;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import y;

public class OpenSessionException extends FM_Exception
{
  public OpenSessionException(String paramString)
  {
    super(paramString);
  }

  public OpenSessionExceptionType getExceptionType()
  {
    return this.a;
  }

  public void setExceptionType(OpenSessionExceptionType paramOpenSessionExceptionType)
  {
    try
    {
      this.a = paramOpenSessionExceptionType;
      label5: return;
    }
    catch (y localy)
    {
      break label5;
    }
  }

  public static enum OpenSessionExceptionType
  {
    static
    {
      try
      {
        SYSTEM_BUSY = new OpenSessionExceptionType(FM_CN.subSequence("\025\f\027\007G\\_MK\036\005", 184), 0, -128, BCCUtil.endsWith("糺绊忐１曓候揼全朌労", 2, 20));
        INVALID_TERMINAL = new OpenSessionExceptionType(FM_CN.subSequence("O[RR\016\030\004\020\nHNFS\007\031\013", 120), 1, -127, FM_Utils.copyValueOf(5, 21, "旰敍绒窠簿埒"));
        INVALID_KEY_INDEX = new OpenSessionExceptionType(FM_Utils.copyValueOf(4, 83, "F\fCI\027GE\013L\037\024_\032\b]\tG"), 2, -126, FM_Long.concat("筢刷佭电宎银絼彜斴攗", 292));
        DECRYPT_FAIL = new OpenSessionExceptionType(FM_CN.subSequence("VDSM\027\r\030\004LXA[", 4), 3, -125, FM_Utils.copyValueOf(3, 56, "筰剶让汔放捨覽寐奿责"));
        DATA_FORMAT_ERROR = new OpenSessionExceptionType(BCCUtil.endsWith("G\b\033TDG\b\037^X\013\032\016CE\022\021", 4, 102), 4, -124, FM_Int.lastIndexOf(338, "笻剶记氊改挤桷彃镔计"));
        INVALID_TERMINAL_ID = new OpenSessionExceptionType(FM_Bytes.startsWith("L\nU\003M\t\033A\tY\tW\020V\026Z\n]\027", 4, 63), 5, -123, CRCUtil.valueOf(5, "斻攄纕窡缉叧"));
        SECURITY_CODE = new OpenSessionExceptionType(FM_Int.lastIndexOf(258, "\006\023\024\r\013\023\017\005\002\035\020DD"), 6, -122, FM_CN.subSequence("阠伫砑骓讏奬赩", 4));
        INVALID_TIME = new OpenSessionExceptionType(FM_Bytes.startsWith("OQN\020\006JXJ\032\016M\\", 5, 25), 7, -121, Util4Java.toString("绋竧斻閦引帤", 4, 37));
        UNKNOW = new OpenSessionExceptionType(FM_Int.lastIndexOf(316, "Z^Z\\\\C"), 8, -1, CRCUtil.valueOf(5, "東瞩镄计"));
        OpenSessionExceptionType[] arrayOfOpenSessionExceptionType = new OpenSessionExceptionType[9];
        arrayOfOpenSessionExceptionType[0] = SYSTEM_BUSY;
        arrayOfOpenSessionExceptionType[1] = INVALID_TERMINAL;
        arrayOfOpenSessionExceptionType[2] = INVALID_KEY_INDEX;
        arrayOfOpenSessionExceptionType[3] = DECRYPT_FAIL;
        arrayOfOpenSessionExceptionType[4] = DATA_FORMAT_ERROR;
        arrayOfOpenSessionExceptionType[5] = INVALID_TERMINAL_ID;
        arrayOfOpenSessionExceptionType[6] = SECURITY_CODE;
        arrayOfOpenSessionExceptionType[7] = INVALID_TIME;
        arrayOfOpenSessionExceptionType[8] = UNKNOW;
        c = arrayOfOpenSessionExceptionType;
        label322: return;
      }
      catch (y localy)
      {
        break label322;
      }
    }

    public static OpenSessionExceptionType instance(int paramInt)
    {
      while (true)
      {
        int j;
        Object localObject;
        try
        {
          OpenSessionExceptionType[] arrayOfOpenSessionExceptionType = values();
          int i = arrayOfOpenSessionExceptionType.length;
          j = 0;
          if (j < i)
            continue;
          localObject = UNKNOW;
          break label53;
          OpenSessionExceptionType localOpenSessionExceptionType = arrayOfOpenSessionExceptionType[j];
          int k = localOpenSessionExceptionType.getValue();
          if (k != paramInt)
            break label55;
          localObject = localOpenSessionExceptionType;
        }
        catch (y localy)
        {
          localObject = null;
        }
        label53: return localObject;
        label55: j++;
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
 * Qualified Name:     cn.com.fmsh.communication.exception.session.OpenSessionException
 * JD-Core Version:    0.6.0
 */