package cn.com.fmsh.tsm.business.enums;

import c7;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import java.io.Serializable;

public enum EnumOrderStatus
  implements Serializable
{
  static
  {
    try
    {
      notExist = new EnumOrderStatus(Util4Java.toString("%\";\024+<$-", 204, 2), 0, 0, FM_CN.subSequence("训卋一孄坣", 1));
      notPay = new EnumOrderStatus(FM_CN.subSequence("o{N,%", 3), 1, 1, FM_Exception.getChars(94, 43, "杽攭仕"));
      hasPaid = new EnumOrderStatus(FM_Utils.copyValueOf(138, 5, "}{lThgw"), 2, 2, Util4Java.toString("巳亟欳", 2, 70));
      success = new EnumOrderStatus(FM_Int.lastIndexOf(4, "$-:9>/."), 3, 3, Util4Java.toString("亯昛払勝", 268, 29));
      failure = new EnumOrderStatus(FM_Long.concat("nrweam/", 144), 4, 4, FM_Long.concat("份昗夾贿", 1));
      unsettled = new EnumOrderStatus(FM_Utils.copyValueOf(250, 107, "p>(#ehk79"), 5, 5, FM_CN.subSequence("仺晞狪怊朰矬", 80));
      apilyForRefund = new EnumOrderStatus(FM_Int.lastIndexOf(6, "8*20$\0300rSgeqkb"), 6, 6, Util4Java.toString("田诿逍欬为", 4, 5));
      hasRefunded = new EnumOrderStatus(FM_Utils.copyValueOf(204, 9, "?!:��>bxx{mu"), 7, 7, BCCUtil.endsWith("巶逘歲", 5, 52));
      refundFailure = new EnumOrderStatus(FM_Bytes.startsWith("e|}hqeEdne~j", 150, 2), 8, 8, FM_Bytes.startsWith("逓歰奸贡", 178, 27));
      payFailure = new EnumOrderStatus(FM_Exception.getChars(3, 75, ",&k\033i:r|&z"), 9, 9, FM_Int.lastIndexOf(2, "亍歨奦赽"));
      waitForPay = new EnumOrderStatus(CRCUtil.valueOf(286, "cd?3\036&h[}t"), 10, 10, CRCUtil.valueOf(236, "忇敼亜"));
      recharging = new EnumOrderStatus(CRCUtil.valueOf(2, "*,9#}yfn6"), 11, 11, FM_Bytes.startsWith("产遆匴儋偻乭", 2, 121));
      dubious = new EnumOrderStatus(Util4Java.toString("ok3-8n", 300, 19), 12, 12, Util4Java.toString("叮痃赥忑奁琐", 2, 113));
      invalid = new EnumOrderStatus(FM_Long.concat("4fee}{", 5), 13, 13, FM_Utils.copyValueOf(4, 69, "训匁兪閳"));
      unknown = new EnumOrderStatus(FM_Int.lastIndexOf(2, " 8<66-5"), 14, 99, FM_Exception.getChars(5, 84, "杴瞷"));
      EnumOrderStatus[] arrayOfEnumOrderStatus = new EnumOrderStatus[15];
      arrayOfEnumOrderStatus[0] = notExist;
      arrayOfEnumOrderStatus[1] = notPay;
      arrayOfEnumOrderStatus[2] = hasPaid;
      arrayOfEnumOrderStatus[3] = success;
      arrayOfEnumOrderStatus[4] = failure;
      arrayOfEnumOrderStatus[5] = unsettled;
      arrayOfEnumOrderStatus[6] = apilyForRefund;
      arrayOfEnumOrderStatus[7] = hasRefunded;
      arrayOfEnumOrderStatus[8] = refundFailure;
      arrayOfEnumOrderStatus[9] = payFailure;
      arrayOfEnumOrderStatus[10] = waitForPay;
      arrayOfEnumOrderStatus[11] = recharging;
      arrayOfEnumOrderStatus[12] = dubious;
      arrayOfEnumOrderStatus[13] = invalid;
      arrayOfEnumOrderStatus[14] = unknown;
      c = arrayOfEnumOrderStatus;
      label542: return;
    }
    catch (c7 localc7)
    {
      break label542;
    }
  }

  public static EnumOrderStatus getOrderStatus4ID(int paramInt)
  {
    EnumOrderStatus localEnumOrderStatus1;
    while (true)
    {
      int i;
      int j;
      try
      {
        EnumOrderStatus[] arrayOfEnumOrderStatus = values();
        i = arrayOfEnumOrderStatus.length;
        j = 0;
        break label51;
        EnumOrderStatus localEnumOrderStatus2 = arrayOfEnumOrderStatus[j];
        int k = localEnumOrderStatus2.getId();
        if (k != paramInt)
          continue;
        localEnumOrderStatus1 = localEnumOrderStatus2;
        break;
        j++;
      }
      catch (c7 localc7)
      {
        localEnumOrderStatus1 = null;
        break;
      }
      label51: if (j < i)
        continue;
      localEnumOrderStatus1 = null;
    }
    return localEnumOrderStatus1;
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumOrderStatus
 * JD-Core Version:    0.6.0
 */