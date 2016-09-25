package cn.com.fmsh.util.algorithm;

import B;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import dt;

public class Diversify
{
  static
  {
    try
    {
      a = LogFactory.getInstance().getLog();
      label9: return;
    }
    catch (dt localdt)
    {
      break label9;
    }
  }

  public static byte[] doubleLength(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    if (paramArrayOfByte1 == null);
    int i;
    byte[] arrayOfByte2;
    try
    {
      a.warn(Diversify.class.getName(), Util4Java.toString("刅整们甀宕钢昴ｃ爵宑钮严=r7c", 4, 52));
      paramArrayOfByte1 = null;
      break label221;
      if (paramInt != 0)
      {
        if (paramInt > 0)
        {
          if (paramArrayOfByte2 != null)
            break label182;
          a.warn(Diversify.class.getName(), CRCUtil.valueOf(3, "剟攩仼畓寛钫早＜切攱攳挺乿xrte"));
          paramArrayOfByte1 = null;
          break label221;
          byte[] arrayOfByte3 = FM_Bytes.copyOfRange(paramArrayOfByte2, i * 8, 8 * (i + 1));
          arrayOfByte2 = FM_Bytes.join(DES.encrypt4des3(arrayOfByte2, arrayOfByte3), DES.encrypt4des3(arrayOfByte2, FM_Bytes.not(arrayOfByte3)));
          i++;
          break label223;
        }
        while (true)
        {
          while (j / 8 == paramInt)
          {
            byte[] arrayOfByte1 = FM_Bytes.copyOfRange(paramArrayOfByte2, 0, 8);
            arrayOfByte2 = FM_Bytes.join(DES.encrypt4des3(paramArrayOfByte1, arrayOfByte1), DES.encrypt4des3(paramArrayOfByte1, FM_Bytes.not(arrayOfByte1)));
            i = 1;
            break;
          }
          a.warn(Diversify.class.getName(), FM_Long.concat("刚敤亵甂宎银斨ｅ剒攼敺捻长庭丛呉沙", 292));
          paramArrayOfByte1 = null;
          break;
          label182: int j = paramArrayOfByte2.length;
          if (j % 8 == 0)
            continue;
          a.warn(Diversify.class.getName(), FM_CN.subSequence("刖敼亩甂宊链於ｕ刎整敶捻锻廵乏呙法", 2));
          paramArrayOfByte1 = null;
        }
      }
    }
    catch (dt localdt)
    {
      paramArrayOfByte1 = null;
    }
    while (true)
    {
      label221: return paramArrayOfByte1;
      label223: if (i < paramInt)
        break;
      paramArrayOfByte1 = arrayOfByte2;
    }
  }

  public static byte[] singleLength(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    if (paramArrayOfByte1 == null);
    Object localObject;
    int i;
    try
    {
      a.warn(Diversify.class.getName(), BCCUtil.endsWith("刄攺亷甘官钰昣ｏ爬寗铭严x8(w", 3, 87));
      paramArrayOfByte1 = null;
      break label198;
      int j = paramArrayOfByte2.length;
      if (j % 8 != 0)
      {
        a.warn(Diversify.class.getName(), FM_Exception.getChars(3, 20, "剚敳亣甇宊铥斢＄刚敳攴挶锳度丙吀泉"));
        paramArrayOfByte1 = null;
        break label198;
        localObject = DES.encrypt4des(localObject, FM_Bytes.copyOfRange(paramArrayOfByte2, i * 8, 8 * (i + 1)));
        i++;
        break label200;
      }
      else if (j / 8 != paramInt)
      {
        a.warn(Diversify.class.getName(), FM_Exception.getChars(3, 37, "剚敢仡畔寖钰斬ｓ刂攪放捽镧廻丏吏沙"));
        paramArrayOfByte1 = null;
      }
      else
      {
        do
        {
          if (paramInt > 0)
          {
            if (paramArrayOfByte2 != null)
              break;
            a.warn(Diversify.class.getName(), FM_Bytes.startsWith("刀敩亩甍寐钿旨．刀敩敾捼丬tk.*", 5, 4));
            paramArrayOfByte1 = null;
            break label198;
          }
          byte[] arrayOfByte = DES.encrypt4des(paramArrayOfByte1, FM_Bytes.copyOfRange(paramArrayOfByte2, 0, 8));
          localObject = arrayOfByte;
          i = 1;
          break label200;
        }
        while (paramInt != 0);
      }
    }
    catch (dt localdt)
    {
      paramArrayOfByte1 = null;
    }
    while (true)
    {
      label198: return paramArrayOfByte1;
      label200: if (i < paramInt)
        break;
      paramArrayOfByte1 = (B)localObject;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.algorithm.Diversify
 * JD-Core Version:    0.6.0
 */