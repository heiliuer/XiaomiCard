package cn.com.fmsh.tsm.business.enums;

import c9;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;

public enum EnumRechargeMode
{
  static
  {
    try
    {
      COMPANY_GIVE = new EnumRechargeMode(FM_Utils.copyValueOf(4, 11, "LUH@Z\b\b\003��\033\013M"), 0, 0, Util4Java.toString("啇扤伝悷", 2, 82));
      ALIPAY_ONE_KEY = new EnumRechargeMode(FM_Long.concat("\003\001\021\023\017��[@T@OP\003\b", 202), 1, 1, Util4Java.toString("攫亅寋乏镦敮亂", 5, 121));
      ALIPAY_SECURE = new EnumRechargeMode(Util4Java.toString("Z[Z_J^\\\f\036\024\006\035\016", 156, 124), 2, 2, FM_Utils.copyValueOf(2, 96, "攢井寐宄入敢井"));
      UNIONPAY = new EnumRechargeMode(FM_CN.subSequence("FLX\017\001\016\f\005", 5), 3, 3, FM_Utils.copyValueOf(2, 82, "铻耋旱卢x卦儵厳兔奨a"));
      UNIONPAY_CARD = new EnumRechargeMode(FM_Int.lastIndexOf(258, "��\030\036\027\027\n\032\005\002\035\036RE"), 4, 49, FM_Int.lastIndexOf(5, "钮耍杓区q匼儲厧光夦"));
      UNIONPAY_FM = new EnumRechargeMode(FM_CN.subSequence("GOYP��\r\r\002U_E", 4), 5, 4, Util4Java.toString("铭耐 夛方徦", 316, 41));
      UNIONPAY_CARD_FM = new EnumRechargeMode(FM_Bytes.startsWith("LRVMKXJWNWVHY\037\005\013", 152, 3), 6, 65, FM_Utils.copyValueOf(4, 39, "铹聂杔卥&奟斿微赡厹5"));
      CARD_SHIFT_CAPITAL = new EnumRechargeMode(BCCUtil.endsWith("@X]\001\004\002OTU\035��\026JQ^\031\002\025", 4, 22), 7, 58, FM_Bytes.startsWith("价逜卸蠩党偮-丕厤题厀祬>", 82, 51));
      MIPAY_MI = new EnumRechargeMode(FM_Long.concat("\017\004\bBWFIF", 234), 8, 81, FM_Utils.copyValueOf(168, 105, "尜籯攪亖"));
      SAMSUNG_PAY = new EnumRechargeMode(FM_CN.subSequence("BABM\030\022\f\005YY^", 3), 9, 86, FM_Long.concat("专晚敿亃", 194));
      UNIONPAY_CARD_PT = new EnumRechargeMode(FM_Utils.copyValueOf(3, 91, "[\007\rP\024\005QR\031\002]\005\026RX\027"), 10, 87, FM_Exception.getChars(122, 100, "钥考朒卾.鸈治丆昌"));
      MOBILE_PROMOTION = new EnumRechargeMode(Util4Java.toString("L\007\r_QA\024\002KOJ\001\001ULD", 2, 103), 11, 60, CRCUtil.valueOf(5, "禠勤侞镎7丝叮颔叒礼"));
      HW_PAY = new EnumRechargeMode(FM_Bytes.startsWith("L\n\t\037\t\030", 3, 121), 12, 69, Util4Java.toString("卍丼敦五", 4, 35));
      LKL_PAY = new EnumRechargeMode(FM_Long.concat("\027M]CWSD", 3), 13, 80, Util4Java.toString("拋匽拟政互", 3, 90));
      EnumRechargeMode[] arrayOfEnumRechargeMode = new EnumRechargeMode[14];
      arrayOfEnumRechargeMode[0] = COMPANY_GIVE;
      arrayOfEnumRechargeMode[1] = ALIPAY_ONE_KEY;
      arrayOfEnumRechargeMode[2] = ALIPAY_SECURE;
      arrayOfEnumRechargeMode[3] = UNIONPAY;
      arrayOfEnumRechargeMode[4] = UNIONPAY_CARD;
      arrayOfEnumRechargeMode[5] = UNIONPAY_FM;
      arrayOfEnumRechargeMode[6] = UNIONPAY_CARD_FM;
      arrayOfEnumRechargeMode[7] = CARD_SHIFT_CAPITAL;
      arrayOfEnumRechargeMode[8] = MIPAY_MI;
      arrayOfEnumRechargeMode[9] = SAMSUNG_PAY;
      arrayOfEnumRechargeMode[10] = UNIONPAY_CARD_PT;
      arrayOfEnumRechargeMode[11] = MOBILE_PROMOTION;
      arrayOfEnumRechargeMode[12] = HW_PAY;
      arrayOfEnumRechargeMode[13] = LKL_PAY;
      c = arrayOfEnumRechargeMode;
      label508: return;
    }
    catch (c9 localc9)
    {
      break label508;
    }
  }

  public static EnumRechargeMode instance(int paramInt)
  {
    EnumRechargeMode[] arrayOfEnumRechargeMode = values();
    int i = arrayOfEnumRechargeMode.length;
    for (int j = 0; ; j++)
    {
      if (j >= i);
      EnumRechargeMode localEnumRechargeMode;
      for (Object localObject = null; ; localObject = localEnumRechargeMode)
      {
        return localObject;
        localEnumRechargeMode = arrayOfEnumRechargeMode[j];
        if (localEnumRechargeMode.getId() != paramInt)
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumRechargeMode
 * JD-Core Version:    0.6.0
 */