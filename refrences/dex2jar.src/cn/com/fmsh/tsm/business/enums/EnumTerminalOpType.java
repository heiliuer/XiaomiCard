package cn.com.fmsh.tsm.business.enums;

import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import db;

public enum EnumTerminalOpType
{
  static
  {
    try
    {
      ANDROID = new EnumTerminalOpType(FM_CN.subSequence("\001\001\032\037\023B^", 306), 0, 1, FM_Utils.copyValueOf(4, 117, "NJ\035\034\f\021\t"));
      IOS = new EnumTerminalOpType(CRCUtil.valueOf(4, "\023\004\017"), 1, 2, FM_Utils.copyValueOf(212, 91, "6UF"));
      WINDOWS = new EnumTerminalOpType(CRCUtil.valueOf(98, "\017��TOSZ\r"), 2, 3, FM_Long.concat("udv'!.7", 170));
      EnumTerminalOpType[] arrayOfEnumTerminalOpType = new EnumTerminalOpType[3];
      arrayOfEnumTerminalOpType[0] = ANDROID;
      arrayOfEnumTerminalOpType[1] = IOS;
      arrayOfEnumTerminalOpType[2] = WINDOWS;
      c = arrayOfEnumTerminalOpType;
      label110: return;
    }
    catch (db localdb)
    {
      break label110;
    }
  }

  public static EnumTerminalOpType getEnumTerminalOpType4ID(int paramInt)
  {
    EnumTerminalOpType[] arrayOfEnumTerminalOpType = values();
    int i = arrayOfEnumTerminalOpType.length;
    for (int j = 0; ; j++)
    {
      if (j >= i);
      EnumTerminalOpType localEnumTerminalOpType;
      for (Object localObject = null; ; localObject = localEnumTerminalOpType)
      {
        return localObject;
        localEnumTerminalOpType = arrayOfEnumTerminalOpType[j];
        if (localEnumTerminalOpType.getId() != paramInt)
          break;
      }
    }
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumTerminalOpType
 * JD-Core Version:    0.6.0
 */