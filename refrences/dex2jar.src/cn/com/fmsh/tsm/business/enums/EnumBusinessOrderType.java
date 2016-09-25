package cn.com.fmsh.tsm.business.enums;

import c1;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;

public enum EnumBusinessOrderType
{
  static
  {
    try
    {
      ORDER_TYPE_RECHARGE = new EnumBusinessOrderType(FM_Utils.copyValueOf(5, 80, "_\022TE\002_\004\031@\005OR\025C\030\001B\007U"), 0, 1, FM_Bytes.startsWith("元偷讲匀", 5, 69));
      ORDER_TYPE_ISSUE = new EnumBusinessOrderType(BCCUtil.endsWith("T\006I\003\rG\005S\023Y\nG\024SL\027", 156, 57), 1, 2, FM_Exception.getChars(290, 17, "及卭访匛"));
      ORDER_TYPE_PROMOTION = new EnumBusinessOrderType(FM_CN.subSequence("]STZ\034\002\030\002Z\\WG\024\032\t\034VXOA", 4), 2, 3, FM_Long.concat("侟锇记午", 4));
      TRANSFER = new EnumBusinessOrderType(FM_Long.concat("\017TPRTTX\032", 3), 3, 4, FM_Int.lastIndexOf(190, "载贴讱十"));
      UNKNOW = new EnumBusinessOrderType(FM_Int.lastIndexOf(2, "��\030\034\026\026\r"), 4, 0, Util4Java.toString("木瞳", 3, 84));
      EnumBusinessOrderType[] arrayOfEnumBusinessOrderType = new EnumBusinessOrderType[5];
      arrayOfEnumBusinessOrderType[0] = ORDER_TYPE_RECHARGE;
      arrayOfEnumBusinessOrderType[1] = ORDER_TYPE_ISSUE;
      arrayOfEnumBusinessOrderType[2] = ORDER_TYPE_PROMOTION;
      arrayOfEnumBusinessOrderType[3] = TRANSFER;
      arrayOfEnumBusinessOrderType[4] = UNKNOW;
      c = arrayOfEnumBusinessOrderType;
      label175: return;
    }
    catch (c1 localc1)
    {
      break label175;
    }
  }

  public static EnumBusinessOrderType instance(int paramInt)
  {
    EnumBusinessOrderType[] arrayOfEnumBusinessOrderType = values();
    int i = arrayOfEnumBusinessOrderType.length;
    for (int j = 0; ; j++)
    {
      if (j >= i);
      EnumBusinessOrderType localEnumBusinessOrderType;
      for (Object localObject = null; ; localObject = localEnumBusinessOrderType)
      {
        return localObject;
        localEnumBusinessOrderType = arrayOfEnumBusinessOrderType[j];
        if (localEnumBusinessOrderType.getId() != paramInt)
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType
 * JD-Core Version:    0.6.0
 */