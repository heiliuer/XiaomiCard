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
import cy;

public enum EnumAppActivationStatus
{
  static
  {
    try
    {
      noActivity = new EnumAppActivationStatus(FM_Int.lastIndexOf(1, "::\0274,0,2($"), 0, 0, FM_Utils.copyValueOf(3, 66, "朤彐逈"));
      activiting = new EnumAppActivationStatus(FM_Exception.getChars(178, 74, "j6k`%t3x5\""), 1, 1, BCCUtil.endsWith("弃逍书", 4, 52));
      activitySucess = new EnumAppActivationStatus(Util4Java.toString("<:ax; qx\016lv4>z", 126, 92), 2, 2, Util4Java.toString("弄逑戂劆", 5, 7));
      activityFail = new EnumAppActivationStatus(FM_CN.subSequence("pc{w;5?#Oynz", 3), 3, 3, FM_CN.subSequence("弑通夾贻", 3));
      closeing = new EnumAppActivationStatus(FM_CN.subSequence("rl`m(5%=", 3), 4, 4, FM_Long.concat("儩门丽", 2));
      closeSucess = new EnumAppActivationStatus(CRCUtil.valueOf(5, "8 2=zCtqf'6"), 5, 5, CRCUtil.valueOf(318, "內门戆劘"));
      closeFail = new EnumAppActivationStatus(CRCUtil.valueOf(4, "9'3>{Iaxn"), 6, 6, FM_Bytes.startsWith("共閩奷购", 1, 98));
      EnumAppActivationStatus[] arrayOfEnumAppActivationStatus = new EnumAppActivationStatus[7];
      arrayOfEnumAppActivationStatus[0] = noActivity;
      arrayOfEnumAppActivationStatus[1] = activiting;
      arrayOfEnumAppActivationStatus[2] = activitySucess;
      arrayOfEnumAppActivationStatus[3] = activityFail;
      arrayOfEnumAppActivationStatus[4] = closeing;
      arrayOfEnumAppActivationStatus[5] = closeSucess;
      arrayOfEnumAppActivationStatus[6] = closeFail;
      c = arrayOfEnumAppActivationStatus;
      label240: return;
    }
    catch (cy localcy)
    {
      break label240;
    }
  }

  public static EnumAppActivationStatus getActivationStatus4ID(int paramInt)
  {
    EnumAppActivationStatus[] arrayOfEnumAppActivationStatus = values();
    int i = arrayOfEnumAppActivationStatus.length;
    for (int j = 0; ; j++)
    {
      EnumAppActivationStatus localEnumAppActivationStatus;
      if (j >= i)
        localEnumAppActivationStatus = null;
      do
      {
        return localEnumAppActivationStatus;
        localEnumAppActivationStatus = arrayOfEnumAppActivationStatus[j];
      }
      while (localEnumAppActivationStatus.getId() == paramInt);
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumAppActivationStatus
 * JD-Core Version:    0.6.0
 */