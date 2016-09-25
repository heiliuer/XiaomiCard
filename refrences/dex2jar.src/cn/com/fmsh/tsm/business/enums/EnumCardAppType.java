package cn.com.fmsh.tsm.business.enums;

import c3;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import java.io.Serializable;

public enum EnumCardAppType
  implements Serializable
{
  static
  {
    try
    {
      CARD_APP_TYPE_SH = new EnumCardAppType(FM_Exception.getChars(2, 91, "\030\027CH\030\003M\b\fZP\024ZE\006\030"), 0, 1, FM_Int.lastIndexOf(4, "九洯份遀区"));
      CARD_APP_TYPE_LNT = new EnumCardAppType(FM_Exception.getChars(3, 104, "\037\005\036PCE\034\004CPU\004\031\033@Z\b"), 1, 2, FM_Long.concat("島卑逋", 3));
      CARD_APP_TYPE_SH_TOUR = new EnumCardAppType(FM_Utils.copyValueOf(156, 109, "DUS\n\004\tERP\b\020\006\006ONB\b\020\036KY"), 2, 3, CRCUtil.valueOf(5, "乑活斘湶卾"));
      EnumCardAppType[] arrayOfEnumCardAppType = new EnumCardAppType[3];
      arrayOfEnumCardAppType[0] = CARD_APP_TYPE_SH;
      arrayOfEnumCardAppType[1] = CARD_APP_TYPE_LNT;
      arrayOfEnumCardAppType[2] = CARD_APP_TYPE_SH_TOUR;
      c = arrayOfEnumCardAppType;
      label107: return;
    }
    catch (c3 localc3)
    {
      break label107;
    }
  }

  public static EnumCardAppType instance(int paramInt)
  {
    EnumCardAppType[] arrayOfEnumCardAppType = values();
    int i = arrayOfEnumCardAppType.length;
    for (int j = 0; ; j++)
    {
      if (j >= i);
      EnumCardAppType localEnumCardAppType;
      for (Object localObject = null; ; localObject = localEnumCardAppType)
      {
        return localObject;
        localEnumCardAppType = arrayOfEnumCardAppType[j];
        if (localEnumCardAppType.getId() != paramInt)
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumCardAppType
 * JD-Core Version:    0.6.0
 */