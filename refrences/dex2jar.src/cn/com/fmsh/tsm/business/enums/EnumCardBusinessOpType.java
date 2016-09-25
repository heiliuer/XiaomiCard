package cn.com.fmsh.tsm.business.enums;

import c4;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;

public enum EnumCardBusinessOpType
{
  static
  {
    try
    {
      ORDER = new EnumCardBusinessOpType(CRCUtil.valueOf(4, "\025\031\030\bL"), 0, 1, FM_Bytes.startsWith("讧赨澅派", 4, 96));
      UNSUBSCRIBE = new EnumCardBusinessOpType(FM_CN.subSequence("Q]QD\002\034\035\037\025I_", 374), 1, 2, FM_Exception.getChars(3, 82, "遜诬"));
      EnumCardBusinessOpType[] arrayOfEnumCardBusinessOpType = new EnumCardBusinessOpType[2];
      arrayOfEnumCardBusinessOpType[0] = ORDER;
      arrayOfEnumCardBusinessOpType[1] = UNSUBSCRIBE;
      c = arrayOfEnumCardBusinessOpType;
      label75: return;
    }
    catch (c4 localc4)
    {
      break label75;
    }
  }

  public static EnumCardBusinessOpType getCardIoType(int paramInt)
  {
    int i;
    int j;
    EnumCardBusinessOpType localEnumCardBusinessOpType1;
    try
    {
      EnumCardBusinessOpType[] arrayOfEnumCardBusinessOpType = values();
      i = arrayOfEnumCardBusinessOpType.length;
      j = 0;
      break label45;
      EnumCardBusinessOpType localEnumCardBusinessOpType2 = arrayOfEnumCardBusinessOpType[j];
      int k = localEnumCardBusinessOpType2.getId();
      if (k == paramInt)
        localEnumCardBusinessOpType1 = localEnumCardBusinessOpType2;
    }
    catch (c4 localc4)
    {
      localEnumCardBusinessOpType1 = null;
    }
    label45: 
    while (j >= i)
    {
      localEnumCardBusinessOpType1 = null;
      return localEnumCardBusinessOpType1;
      j++;
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumCardBusinessOpType
 * JD-Core Version:    0.6.0
 */