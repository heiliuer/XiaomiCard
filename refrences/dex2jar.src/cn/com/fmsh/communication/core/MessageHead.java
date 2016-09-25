package cn.com.fmsh.communication.core;

import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import l;

public class MessageHead
{
  public static final int MESSAGE_HEAD_LENGTH = 12;
  public static final long SERIAL_MAK = 4294967295L;

  public void fromBytes(byte[] paramArrayOfByte)
  {
    int m = 0;
    if (paramArrayOfByte != null)
      try
      {
        byte[] arrayOfByte;
        int i1;
        if (paramArrayOfByte.length != 12)
        {
          return;
          arrayOfByte[i1] = paramArrayOfByte[(i1 + 4)];
          i1++;
          label30: if (i1 >= 4)
          {
            this.j = FM_Bytes.bytesToLong(FM_Bytes.join(new byte[1], arrayOfByte));
            while (m < 4)
            {
              this.k[m] = paramArrayOfByte[(m + 8)];
              m++;
            }
          }
        }
        label128: 
        while (true)
        {
          int n;
          this.i[n] = paramArrayOfByte[(n + 2)];
          n++;
          while (true)
          {
            if (n < 2)
              break label128;
            arrayOfByte = new byte[4];
            i1 = 0;
            break label30;
            break;
            this.g = paramArrayOfByte[0];
            this.h.fromBytes(paramArrayOfByte[1]);
            n = 0;
          }
        }
      }
      catch (l locall)
      {
      }
  }

  public ControlWord getControlWord()
  {
    return this.h;
  }

  public byte getProtocolVersion()
  {
    return this.g;
  }

  public byte[] getSecurityLevel()
  {
    return this.i;
  }

  public long getSerialNumber()
  {
    return this.j;
  }

  public byte[] getSessionNumber()
  {
    return this.k;
  }

  public void setControlWord(ControlWord paramControlWord)
  {
    try
    {
      this.h = paramControlWord;
      label5: return;
    }
    catch (l locall)
    {
      break label5;
    }
  }

  public void setProtocolVersion(byte paramByte)
  {
    try
    {
      this.g = paramByte;
      label5: return;
    }
    catch (l locall)
    {
      break label5;
    }
  }

  public void setSecurityLevel(byte[] paramArrayOfByte)
  {
    try
    {
      this.i = paramArrayOfByte;
      label5: return;
    }
    catch (l locall)
    {
      break label5;
    }
  }

  public void setSerialNumber(long paramLong)
  {
    try
    {
      this.j = paramLong;
      label5: return;
    }
    catch (l locall)
    {
      break label5;
    }
  }

  public void setSessionNumber(byte[] paramArrayOfByte)
  {
    try
    {
      this.k = paramArrayOfByte;
      label5: return;
    }
    catch (l locall)
    {
      break label5;
    }
  }

  public byte[] toBytes()
  {
    int m = 0;
    byte[] arrayOfByte1 = new byte[12];
    arrayOfByte1[m] = this.g;
    arrayOfByte1[1] = this.h.toBytes();
    int n = 0;
    byte[] arrayOfByte2;
    if (n >= 2)
      arrayOfByte2 = FM_Bytes.longToBytes(this.j, 4);
    for (int i1 = 0; ; i1++)
    {
      if (i1 >= 4)
        while (true)
        {
          if (m >= 4)
          {
            return arrayOfByte1;
            arrayOfByte1[(n + 2)] = this.i[n];
            n++;
            break;
          }
          arrayOfByte1[(m + 8)] = this.k[m];
          m++;
        }
      arrayOfByte1[(i1 + 4)] = arrayOfByte2[i1];
    }
  }

  public static enum CheckType
  {
    static
    {
      try
      {
        NOCHECK = new CheckType(FM_CN.subSequence("_OLV\b\037��", 3), 0, 0);
        MAC = new CheckType(BCCUtil.endsWith("I\023\003", 5, 110), 1, 1);
        CRC16 = new CheckType(FM_CN.subSequence("QSS.x", 4), 2, 2);
        CheckType[] arrayOfCheckType = new CheckType[3];
        arrayOfCheckType[0] = NOCHECK;
        arrayOfCheckType[1] = MAC;
        arrayOfCheckType[2] = CRC16;
        b = arrayOfCheckType;
        label83: return;
      }
      catch (l locall)
      {
        break label83;
      }
    }

    public int getValue()
    {
      return this.a;
    }
  }

  public static enum SecurityLevel
  {
    static
    {
      try
      {
        PLAIN = new SecurityLevel(FM_Long.concat("H\017\017\020\n", 192), 0, 0);
        CIPHER = new SecurityLevel(FM_Utils.copyValueOf(262, 92, "R\004\031MD\017"), 1, 1);
        SecurityLevel[] arrayOfSecurityLevel = new SecurityLevel[2];
        arrayOfSecurityLevel[0] = PLAIN;
        arrayOfSecurityLevel[1] = CIPHER;
        b = arrayOfSecurityLevel;
        label63: return;
      }
      catch (l locall)
      {
        break label63;
      }
    }

    public int getValue()
    {
      return this.a;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.core.MessageHead
 * JD-Core Version:    0.6.0
 */