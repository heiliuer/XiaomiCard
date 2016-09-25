package cn.com.fmsh.tsm.business.enums;

import c_;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;

public enum EnumBackInfoType
{
  static
  {
    try
    {
      TRAFFIC_CARD = new EnumBackInfoType(BCCUtil.endsWith("I\f^F\007K��[\006G\025L", 62, 65), 0, 0, FM_Exception.getChars(3, 103, "仸遙匫庅田"));
      RECHARGE_REFUND = new EnumBackInfoType(CRCUtil.valueOf(126, "\006��\025\017Y[]N\003\037\033\t\025_F"), 1, 1, FM_Exception.getChars(208, 53, "儌偢叙遈欣"));
      CHANGE_MOVE = new EnumBackInfoType(FM_Bytes.startsWith("@\005V\017L\020@D\034K\002", 2, 74), 2, 2, CRCUtil.valueOf(2, "挺匨厐禰赘"));
      SUBWAY_PAY = new EnumBackInfoType(FM_Utils.copyValueOf(144, 71, "H\027K\007V\007\032\\\022C"), 3, 3, CRCUtil.valueOf(3, "坩钋儷仨刪卯"));
      IMPROVE_ADVICE = new EnumBackInfoType(FM_Exception.getChars(1, 113, "\023\006\f\037QYENC\027\022\034\005R"), 4, 4, FM_Int.lastIndexOf(4, "勈肥敠嗞庡诲"));
      OTHER = new EnumBackInfoType(FM_Long.concat("\017\037\036D^", 232), 5, 5, FM_Exception.getChars(3, 44, "仸递匵应甤"));
      EnumBackInfoType[] arrayOfEnumBackInfoType = new EnumBackInfoType[6];
      arrayOfEnumBackInfoType[0] = TRAFFIC_CARD;
      arrayOfEnumBackInfoType[1] = RECHARGE_REFUND;
      arrayOfEnumBackInfoType[2] = CHANGE_MOVE;
      arrayOfEnumBackInfoType[3] = SUBWAY_PAY;
      arrayOfEnumBackInfoType[4] = IMPROVE_ADVICE;
      arrayOfEnumBackInfoType[5] = OTHER;
      c = arrayOfEnumBackInfoType;
      label212: return;
    }
    catch (c_ localc_)
    {
      break label212;
    }
  }

  public static EnumBackInfoType instance(int paramInt)
  {
    EnumBackInfoType localEnumBackInfoType1;
    while (true)
    {
      int i;
      int j;
      try
      {
        EnumBackInfoType[] arrayOfEnumBackInfoType = values();
        i = arrayOfEnumBackInfoType.length;
        j = 0;
        break label51;
        EnumBackInfoType localEnumBackInfoType2 = arrayOfEnumBackInfoType[j];
        int k = localEnumBackInfoType2.getId();
        if (k != paramInt)
          continue;
        localEnumBackInfoType1 = localEnumBackInfoType2;
        break;
        j++;
      }
      catch (c_ localc_)
      {
        localEnumBackInfoType1 = null;
        break;
      }
      label51: if (j < i)
        continue;
      localEnumBackInfoType1 = null;
    }
    return localEnumBackInfoType1;
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumBackInfoType
 * JD-Core Version:    0.6.0
 */