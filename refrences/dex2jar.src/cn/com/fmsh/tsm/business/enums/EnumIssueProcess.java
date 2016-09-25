package cn.com.fmsh.tsm.business.enums;

import c6;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;

public enum EnumIssueProcess
{
  static
  {
    try
    {
      APPLIED = new EnumIssueProcess(Util4Java.toString("B\035\007\rBP\033", 4, 106), 0, 0, BCCUtil.endsWith("敱捰嶩甫订", 2, 61));
      SSD_KEY_UPDATED = new EnumIssueProcess(FM_Exception.getChars(270, 83, "T\tI_\030C��\023J\002\001Y\037[U"), 1, 10, FM_Bytes.startsWith("UX\024宓钿巭暰方", 5, 37));
      APP_LOAD = new EnumIssueProcess(FM_Int.lastIndexOf(148, "FXYUGCLJ"), 2, 20, FM_Bytes.startsWith("廉用己劦轴", 252, 3));
      APP_INSTALL = new EnumIssueProcess(FM_Utils.copyValueOf(276, 125, "^LIIZ^^^FHM"), 3, 30, FM_CN.subSequence("庆甩巢宖袋", 4));
      APP_PERSONAL = new EnumIssueProcess(FM_Exception.getChars(5, 125, "\037\013\b\n\002\n\036\032\t\r\001\021"), 4, 40, CRCUtil.valueOf(154, "庄甩嶠乩仮卓"));
      APP_ACTIVATION = new EnumIssueProcess(CRCUtil.valueOf(60, "SSDZ\027\004\f��LJHD\021\001"), 5, 50, CRCUtil.valueOf(4, "廎畣嶮澍津"));
      APP_LOCK = new EnumIssueProcess(FM_Utils.copyValueOf(2, 25, "LVO\007\035E@W"), 6, 60, FM_Exception.getChars(5, 66, "廊畨巰镅宜"));
      APP_REMOVE = new EnumIssueProcess(FM_Bytes.startsWith("\\V_\007\023\017^SS\013", 188, 105), 7, 70, FM_CN.subSequence("廈畣嶨利陼", 206));
      EnumIssueProcess[] arrayOfEnumIssueProcess = new EnumIssueProcess[8];
      arrayOfEnumIssueProcess[0] = APPLIED;
      arrayOfEnumIssueProcess[1] = SSD_KEY_UPDATED;
      arrayOfEnumIssueProcess[2] = APP_LOAD;
      arrayOfEnumIssueProcess[3] = APP_INSTALL;
      arrayOfEnumIssueProcess[4] = APP_PERSONAL;
      arrayOfEnumIssueProcess[5] = APP_ACTIVATION;
      arrayOfEnumIssueProcess[6] = APP_LOCK;
      arrayOfEnumIssueProcess[7] = APP_REMOVE;
      c = arrayOfEnumIssueProcess;
      label295: return;
    }
    catch (c6 localc6)
    {
      break label295;
    }
  }

  public static EnumIssueProcess instance(int paramInt)
  {
    EnumIssueProcess localEnumIssueProcess1;
    while (true)
    {
      int i;
      int j;
      try
      {
        EnumIssueProcess[] arrayOfEnumIssueProcess = values();
        i = arrayOfEnumIssueProcess.length;
        j = 0;
        break label51;
        EnumIssueProcess localEnumIssueProcess2 = arrayOfEnumIssueProcess[j];
        int k = localEnumIssueProcess2.getId();
        if (k != paramInt)
          continue;
        localEnumIssueProcess1 = localEnumIssueProcess2;
        break;
        j++;
      }
      catch (c6 localc6)
      {
        localEnumIssueProcess1 = null;
        break;
      }
      label51: if (j < i)
        continue;
      localEnumIssueProcess1 = null;
    }
    return localEnumIssueProcess1;
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumIssueProcess
 * JD-Core Version:    0.6.0
 */