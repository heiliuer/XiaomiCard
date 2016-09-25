package cn.com.fmsh.script.bean;

import bf;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.log.FMLog;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class ApduRequest
{
  public boolean addExpectationAndNext(byte[] paramArrayOfByte)
  {
    int i = 0;
    if (paramArrayOfByte == null);
    try
    {
      this.a.warn(FM_Int.lastIndexOf(4, "\026(=/\t9,+:su"), FM_Utils.copyValueOf(4, 81, "N\020UW认汆杊杝迃嚖绊枖{丂乖乎松捗亥栕讅墊劥斠＋讯氋朅材迈囓纍枓为稫．菤厒夤赣"));
      break label109;
      if (paramArrayOfByte.length != 3)
      {
        this.a.warn(BCCUtil.endsWith("@z7)W+&u,!o", 2, 41), FM_Long.concat("[UTN讱氓权杜辆嚃绛枏>万丟丟末捒交桌讐墟劬旡．丆丘朢捉亽义呇沏", 290));
      }
      else
      {
        byte[] arrayOfByte1 = Arrays.copyOf(paramArrayOfByte, 2);
        byte[] arrayOfByte2 = Arrays.copyOfRange(paramArrayOfByte, 2, 3);
        this.h.put(Integer.valueOf(FM_Bytes.bytesToInt(arrayOfByte1)), Integer.valueOf(FM_Bytes.bytesToInt(arrayOfByte2)));
        i = 1;
      }
      label109: return i;
    }
    catch (bf localbf)
    {
      break label109;
    }
  }

  public int compareTo(Object paramObject)
  {
    ApduRequest localApduRequest = (ApduRequest)paramObject;
    int i;
    if (this.f > localApduRequest.getId())
      i = 1;
    while (true)
    {
      return i;
      if (this.f < localApduRequest.getId())
      {
        i = -1;
        continue;
      }
      i = 0;
    }
  }

  public void fromBytes(byte[] paramArrayOfByte)
    throws FMScriptHandleException, IOException
  {
    if (paramArrayOfByte == null);
  }

  public byte[] getApdu()
  {
    return this.g;
  }

  public int getExpectationCount()
  {
    try
    {
      int j = this.h.size();
      i = j;
      return i;
    }
    catch (bf localbf)
    {
      while (true)
        int i = 0;
    }
  }

  public int getId()
  {
    return this.f;
  }

  public int getNext4Expectation(byte[] paramArrayOfByte)
  {
    int i;
    if (paramArrayOfByte == null)
    {
      this.a.warn(BCCUtil.endsWith("B'o*\001b*:f$?", 4, 84), FM_Utils.copyValueOf(2, 33, "栴捠\016��UG认氖朊服较嚆绊枆菬厊世丞捘令皅桅讅旲）讱氅朗朒辞嚕统枑乴稵＜莦厄奢贱"));
      i = -1;
    }
    while (true)
    {
      return i;
      Integer localInteger = (Integer)this.h.get(Integer.valueOf(FM_Bytes.bytesToInt(paramArrayOfByte)));
      if (localInteger == null)
      {
        i = -1;
        continue;
      }
      i = localInteger.intValue();
    }
  }

  public byte getTag()
  {
    return this.e;
  }

  public boolean isHaveExpectation()
  {
    int i = 0;
    try
    {
      boolean bool = this.h.isEmpty();
      if (bool);
      while (true)
      {
        label16: return i;
        i = 1;
      }
    }
    catch (bf localbf)
    {
      break label16;
    }
  }

  public void setApdu(byte[] paramArrayOfByte)
  {
    try
    {
      this.g = paramArrayOfByte;
      label5: return;
    }
    catch (bf localbf)
    {
      break label5;
    }
  }

  public void setId(int paramInt)
  {
    try
    {
      this.f = paramInt;
      label5: return;
    }
    catch (bf localbf)
    {
      break label5;
    }
  }

  public void setTag(byte paramByte)
  {
    try
    {
      this.e = paramByte;
      label5: return;
    }
    catch (bf localbf)
    {
      break label5;
    }
  }

  public byte[] toBytes()
  {
    return null;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.bean.ApduRequest
 * JD-Core Version:    0.6.0
 */