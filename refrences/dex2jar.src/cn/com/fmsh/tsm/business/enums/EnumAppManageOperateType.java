package cn.com.fmsh.tsm.business.enums;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.Util4Java;
import cz;

public enum EnumAppManageOperateType
{
  static
  {
    try
    {
      APP_LOCK = new EnumAppManageOperateType(CRCUtil.valueOf(342, "\rM^@\\N\021\b"), 0, 1, Util4Java.toString("卢乏廓甡典閠", 164, 34));
      APP_UNLOCK = new EnumAppManageOperateType(FM_Long.concat("\030T_EP^W\t\022\027", 1), 1, 2, FM_CN.subSequence("印上庛甶彍味", 3));
      APP_STATUS_QUERY = new EnumAppManageOperateType(BCCUtil.endsWith("@A\021N\022\005@\005TB\036@\024\024S\b", 2, 48), 2, 3, Util4Java.toString("卤丘廋甤彊刋犥恁枨诸", 134, 45));
      APP_DELETE = new EnumAppManageOperateType(FM_Exception.getChars(2, 78, "\032\031G\032WD\003X\037\\"), 3, 4, FM_Long.concat("医丏庄申刦陵", 2));
      APP_MOVE = new EnumAppManageOperateType(Util4Java.toString("\bI\031\006D\026_\\", 106, 48), 4, 5, FM_Exception.getChars(6, 68, "匾义库畣迎冩"));
      APP_DOWNLOAD = new EnumAppManageOperateType(CRCUtil.valueOf(50, "IIZ\004\b\022\031Q\\NSG"), 5, 6, FM_CN.subSequence("印上庛甶剭阸", 3));
      EnumAppManageOperateType[] arrayOfEnumAppManageOperateType = new EnumAppManageOperateType[6];
      arrayOfEnumAppManageOperateType[0] = APP_LOCK;
      arrayOfEnumAppManageOperateType[1] = APP_UNLOCK;
      arrayOfEnumAppManageOperateType[2] = APP_STATUS_QUERY;
      arrayOfEnumAppManageOperateType[3] = APP_DELETE;
      arrayOfEnumAppManageOperateType[4] = APP_MOVE;
      arrayOfEnumAppManageOperateType[5] = APP_DOWNLOAD;
      c = arrayOfEnumAppManageOperateType;
      label212: return;
    }
    catch (cz localcz)
    {
      break label212;
    }
  }

  public static EnumAppManageOperateType instance(int paramInt)
  {
    EnumAppManageOperateType localEnumAppManageOperateType1;
    while (true)
    {
      int i;
      int j;
      try
      {
        EnumAppManageOperateType[] arrayOfEnumAppManageOperateType = values();
        i = arrayOfEnumAppManageOperateType.length;
        j = 0;
        break label51;
        EnumAppManageOperateType localEnumAppManageOperateType2 = arrayOfEnumAppManageOperateType[j];
        int k = localEnumAppManageOperateType2.getId();
        if (k != paramInt)
          continue;
        localEnumAppManageOperateType1 = localEnumAppManageOperateType2;
        break;
        j++;
      }
      catch (cz localcz)
      {
        localEnumAppManageOperateType1 = null;
        break;
      }
      label51: if (j < i)
        continue;
      localEnumAppManageOperateType1 = null;
    }
    return localEnumAppManageOperateType1;
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumAppManageOperateType
 * JD-Core Version:    0.6.0
 */