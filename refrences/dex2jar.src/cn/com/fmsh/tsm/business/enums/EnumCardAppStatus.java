package cn.com.fmsh.tsm.business.enums;

import c2;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.Util4Java;

public enum EnumCardAppStatus
{
  static
  {
    try
    {
      STATUS_NO_APP = new EnumCardAppStatus(BCCUtil.endsWith("P\021\006]^\036\020_\\\n\026IK", 4, 98), 0, 1, FM_CN.subSequence("庆甩机宖袋", 4));
      STATUS_LOADED = new EnumCardAppStatus(BCCUtil.endsWith("PLL\026\002\037^ZD\001\021\017\033", 4, 21), 1, 2, BCCUtil.endsWith("可截蠖袂轩斆仸\027\007\024\006JX", 1, 109));
      STATUS_INSTALL = new EnumCardAppStatus(FM_Bytes.startsWith("VF^XL\025\f\t\003\tSUMB", 4, 13), 2, 3, FM_Exception.getChars(3, 116, "乖洧仠遂匭廔甼宁裙"));
      STATUS_PERSONALIZED = new EnumCardAppStatus(FM_CN.subSequence("BTNJ\030\017\024\nLJTY\013\025\017\033[U[", 3), 3, 4, FM_Int.lastIndexOf(4, "九洯份遀区乶仧午"));
      STATUS_ACTIVATE = new EnumCardAppStatus(FM_Exception.getChars(2, 50, "\bY\036E\026\006X\030HI\006W\022QR"), 4, 5, CRCUtil.valueOf(164, "丐浼仸遗匿彏遚"));
      STATUS_UNKNOW = new EnumCardAppStatus(Util4Java.toString("P��D\002\022K\026OE\027C\021\030", 4, 81), 5, 10, FM_CN.subSequence("杲瞢狠怄", 330));
      EnumCardAppStatus[] arrayOfEnumCardAppStatus = new EnumCardAppStatus[6];
      arrayOfEnumCardAppStatus[0] = STATUS_NO_APP;
      arrayOfEnumCardAppStatus[1] = STATUS_LOADED;
      arrayOfEnumCardAppStatus[2] = STATUS_INSTALL;
      arrayOfEnumCardAppStatus[3] = STATUS_PERSONALIZED;
      arrayOfEnumCardAppStatus[4] = STATUS_ACTIVATE;
      arrayOfEnumCardAppStatus[5] = STATUS_UNKNOW;
      c = arrayOfEnumCardAppStatus;
      label209: return;
    }
    catch (c2 localc2)
    {
      break label209;
    }
  }

  public static EnumCardAppStatus instance(int paramInt)
  {
    EnumCardAppStatus localEnumCardAppStatus1;
    while (true)
    {
      int i;
      int j;
      try
      {
        EnumCardAppStatus[] arrayOfEnumCardAppStatus = values();
        i = arrayOfEnumCardAppStatus.length;
        j = 0;
        break label51;
        EnumCardAppStatus localEnumCardAppStatus2 = arrayOfEnumCardAppStatus[j];
        int k = localEnumCardAppStatus2.getId();
        if (k != paramInt)
          continue;
        localEnumCardAppStatus1 = localEnumCardAppStatus2;
        break;
        j++;
      }
      catch (c2 localc2)
      {
        localEnumCardAppStatus1 = null;
        break;
      }
      label51: if (j < i)
        continue;
      localEnumCardAppStatus1 = null;
    }
    return localEnumCardAppStatus1;
  }

  public String getDesc()
  {
    return this.b;
  }

  public int getId()
  {
    return this.a;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumCardAppStatus
 * JD-Core Version:    0.6.0
 */