package cn.com.fmsh.tsm.business.enums;

import c0;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;

public enum EnumBusinessOrderStatus
{
  static
  {
    try
    {
      noOrder = new EnumBusinessOrderStatus(FM_CN.subSequence("dv\007%\"0v", 156), 0, 0, FM_Exception.getChars(3, 111, "杶诩起"));
      orderIng = new EnumBusinessOrderStatus(FM_Exception.getChars(262, 40, "0u+2m\016!p"), 1, 1, BCCUtil.endsWith("讦赨乫+朢瞬诨账统柑", 5, 97));
      orderSucess = new EnumBusinessOrderStatus(FM_Long.concat("5wt~tBi$7.;", 2), 2, 2, FM_Utils.copyValueOf(198, 90, "诳账戕勀"));
      orderFail = new EnumBusinessOrderStatus(BCCUtil.endsWith("68)oH>ym", 122, 81), 3, 3, FM_CN.subSequence("记贬夡贺", 4));
      unsubscribeing = new EnumBusinessOrderStatus(FM_Int.lastIndexOf(138, "(0,ucq`vldbagm"), 4, 4, Util4Java.toString("逅课举", 6, 87));
      unsubscribeSucess = new EnumBusinessOrderStatus(FM_Int.lastIndexOf(152, "~b~{mcr`zvpEb{|ih"), 5, 5, FM_CN.subSequence("逑订戟劁", 3));
      unsubscribeFail = new EnumBusinessOrderStatus(Util4Java.toString("t=6\"+hnmxa0\0018\"q", 2, 114), 6, 6, Util4Java.toString("逃该夺赪", 4, 68));
      EnumBusinessOrderStatus[] arrayOfEnumBusinessOrderStatus = new EnumBusinessOrderStatus[7];
      arrayOfEnumBusinessOrderStatus[0] = noOrder;
      arrayOfEnumBusinessOrderStatus[1] = orderIng;
      arrayOfEnumBusinessOrderStatus[2] = orderSucess;
      arrayOfEnumBusinessOrderStatus[3] = orderFail;
      arrayOfEnumBusinessOrderStatus[4] = unsubscribeing;
      arrayOfEnumBusinessOrderStatus[5] = unsubscribeSucess;
      arrayOfEnumBusinessOrderStatus[6] = unsubscribeFail;
      c = arrayOfEnumBusinessOrderStatus;
      label251: return;
    }
    catch (c0 localc0)
    {
      break label251;
    }
  }

  public static EnumBusinessOrderStatus getBusinessOrderStatus4ID(int paramInt)
  {
    int i;
    int j;
    EnumBusinessOrderStatus localEnumBusinessOrderStatus1;
    try
    {
      EnumBusinessOrderStatus[] arrayOfEnumBusinessOrderStatus = values();
      i = arrayOfEnumBusinessOrderStatus.length;
      j = 0;
      break label45;
      EnumBusinessOrderStatus localEnumBusinessOrderStatus2 = arrayOfEnumBusinessOrderStatus[j];
      int k = localEnumBusinessOrderStatus2.getId();
      if (k == paramInt)
        localEnumBusinessOrderStatus1 = localEnumBusinessOrderStatus2;
    }
    catch (c0 localc0)
    {
      localEnumBusinessOrderStatus1 = null;
    }
    label45: 
    while (j >= i)
    {
      localEnumBusinessOrderStatus1 = null;
      return localEnumBusinessOrderStatus1;
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumBusinessOrderStatus
 * JD-Core Version:    0.6.0
 */