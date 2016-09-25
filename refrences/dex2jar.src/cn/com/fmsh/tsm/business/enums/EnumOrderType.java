package cn.com.fmsh.tsm.business.enums;

import c8;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;

public enum EnumOrderType
{
  static
  {
    try
    {
      MAIN = new EnumOrderType(Util4Java.toString("\034\035\016\\", 242, 107), 0, 1, FM_CN.subSequence("乿诱北", 182));
      BUSINESS = new EnumOrderType(FM_CN.subSequence("D@WZ\f\024\023\034", 120), 1, 2, FM_Bytes.startsWith("乏劰讯匜", 244, 28));
      PAY = new EnumOrderType(FM_CN.subSequence("AAV", 3), 2, 3, FM_Utils.copyValueOf(334, 56, "敶仉诫協"));
      EnumOrderType[] arrayOfEnumOrderType = new EnumOrderType[3];
      arrayOfEnumOrderType[0] = MAIN;
      arrayOfEnumOrderType[1] = BUSINESS;
      arrayOfEnumOrderType[2] = PAY;
      c = arrayOfEnumOrderType;
      label114: return;
    }
    catch (c8 localc8)
    {
      break label114;
    }
  }

  public static EnumOrderType instance(int paramInt)
  {
    EnumOrderType[] arrayOfEnumOrderType = values();
    int i = arrayOfEnumOrderType.length;
    for (int j = 0; ; j++)
    {
      if (j >= i);
      EnumOrderType localEnumOrderType;
      for (Object localObject = null; ; localObject = localEnumOrderType)
      {
        return localObject;
        localEnumOrderType = arrayOfEnumOrderType[j];
        if (localEnumOrderType.getId() != paramInt)
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumOrderType
 * JD-Core Version:    0.6.0
 */