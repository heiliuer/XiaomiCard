package cn.com.fmsh.tsm.business.enums;

import c5;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;

public enum EnumCardIoType
{
  static
  {
    try
    {
      CARD_IO_UNKNOW = new EnumCardIoType(FM_CN.subSequence("S^\\Y\023\022\005\006]YM[\013\004", 2), 0, 0, FM_CN.subSequence("朻知", 3));
      CARD_IO_TYPE_OUT = new EnumCardIoType(FM_Int.lastIndexOf(248, "\b\r\037\n\020\031\036\r\007\r\005\023\b\027\f\016"), 1, 1, BCCUtil.endsWith("外邲狸竅匩ｊ栛匷け弈彦匿１", 1, 90));
      CARD_IO_TYPE_IN = new EnumCardIoType(FM_Long.concat("\023\032TUCN]B\034\n\016\f\013\026D", 248), 2, 2, FM_CN.subSequence("冖卣９\023\006\023匬弾怊〛lK\002弴恄｝", 5));
      EnumCardIoType[] arrayOfEnumCardIoType = new EnumCardIoType[3];
      arrayOfEnumCardIoType[0] = CARD_IO_UNKNOW;
      arrayOfEnumCardIoType[1] = CARD_IO_TYPE_OUT;
      arrayOfEnumCardIoType[2] = CARD_IO_TYPE_IN;
      c = arrayOfEnumCardIoType;
      label105: return;
    }
    catch (c5 localc5)
    {
      break label105;
    }
  }

  public static EnumCardIoType getCardIoType(int paramInt)
  {
    EnumCardIoType[] arrayOfEnumCardIoType = values();
    int i = arrayOfEnumCardIoType.length;
    int j = 0;
    if (j >= i);
    EnumCardIoType localEnumCardIoType;
    for (Object localObject = null; ; localObject = localEnumCardIoType)
    {
      return localObject;
      do
      {
        j++;
        break;
        localEnumCardIoType = arrayOfEnumCardIoType[j];
      }
      while (localEnumCardIoType.getId() != paramInt);
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumCardIoType
 * JD-Core Version:    0.6.0
 */