package cn.com.fmsh.tsm.business.enums;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import dc;
import java.io.Serializable;

public enum EnumTradeType
  implements Serializable
{
  static
  {
    try
    {
      bus = new EnumTradeType(FM_Long.concat("8pc", 2), 0, 1, FM_Long.concat("儲仭", 230));
      privilege = new EnumTradeType(FM_Int.lastIndexOf(2, "%$>.06>;8"), 1, 2, FM_Exception.getChars(2, 92, "佃悷亷昜"));
      subwayConsumption = new EnumTradeType(FM_Utils.copyValueOf(5, 55, "c2<b-z\031~&,c t/{&n"), 2, 3, Util4Java.toString("轩遘亱逅涁贪", 2, 10));
      subwayUpdate = new EnumTradeType(FM_CN.subSequence("|kok*#\034(cwqq", 1), 3, 4, FM_CN.subSequence("轹道享逄暹旬", 3));
      maglev = new EnumTradeType(BCCUtil.endsWith("n'.`j$", 132, 99), 4, 5, FM_Bytes.startsWith("磇惿洮", 5, 109));
      recharge = new EnumTradeType(CRCUtil.valueOf(104, ",*#ycac0"), 5, 6, FM_Int.lastIndexOf(148, "兂赌"));
      ferry = new EnumTradeType(FM_Utils.copyValueOf(2, 21, "kge>8"), 6, 7, FM_Utils.copyValueOf(2, 12, "轣游"));
      taxi = new EnumTradeType(FM_Int.lastIndexOf(158, "esk}"), 7, 8, FM_Long.concat("冡秙", 3));
      expressway = new EnumTradeType(BCCUtil.endsWith("23/!\"h|tvr", 120, 116), 8, 9, FM_Long.concat("髀遜儢趶", 320));
      park = new EnumTradeType(Util4Java.toString("tx|(", 5, 21), 9, 10, Util4Java.toString("偝輫圣", 2, 76));
      gasStation = new EnumTradeType(FM_CN.subSequence(";jiZl&\",;m", 238), 10, 11, FM_Int.lastIndexOf(50, "劥沿竞"));
      onlineRecharge = new EnumTradeType(FM_Utils.copyValueOf(1, 19, "cq~,6.\ftg+/7f"), 11, 12, Util4Java.toString("缞乕兊贛", 208, 48));
      onlineConsumption = new EnumTradeType(FM_CN.subSequence("q\"4\">Ivfd384'k~n", 162), 12, 13, FM_Exception.getChars(144, 54, "罘丕淝贲"));
      elseTrade = new EnumTradeType(Util4Java.toString("gpe5\036v|7", 3, 26), 13, 14, FM_Exception.getChars(5, 28, "儨仌亲晁"));
      Consumption = new EnumTradeType(FM_CN.subSequence("Qn~l;0</cvf", 4), 14, 15, CRCUtil.valueOf(1, "淟赱"));
      CompositeConsumption = new EnumTradeType(BCCUtil.endsWith("@-l00m4h>Y6v$c8d'{>~", 4, 63), 15, 16, Util4Java.toString("夌呆淓赱", 2, 109));
      EnumTradeType[] arrayOfEnumTradeType = new EnumTradeType[16];
      arrayOfEnumTradeType[0] = bus;
      arrayOfEnumTradeType[1] = privilege;
      arrayOfEnumTradeType[2] = subwayConsumption;
      arrayOfEnumTradeType[3] = subwayUpdate;
      arrayOfEnumTradeType[4] = maglev;
      arrayOfEnumTradeType[5] = recharge;
      arrayOfEnumTradeType[6] = ferry;
      arrayOfEnumTradeType[7] = taxi;
      arrayOfEnumTradeType[8] = expressway;
      arrayOfEnumTradeType[9] = park;
      arrayOfEnumTradeType[10] = gasStation;
      arrayOfEnumTradeType[11] = onlineRecharge;
      arrayOfEnumTradeType[12] = onlineConsumption;
      arrayOfEnumTradeType[13] = elseTrade;
      arrayOfEnumTradeType[14] = Consumption;
      arrayOfEnumTradeType[15] = CompositeConsumption;
      c = arrayOfEnumTradeType;
      label576: return;
    }
    catch (dc localdc)
    {
      break label576;
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumTradeType
 * JD-Core Version:    0.6.0
 */