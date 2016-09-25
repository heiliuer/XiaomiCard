package cn.com.fmsh.tsm.business.enums;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Long;
import da;

public enum EnumResultsSortType
{
  static
  {
    try
    {
      FORWARD = new EnumResultsSortType(FM_Long.concat("\035ICKF@Y", 3), 0, 1, FM_Exception.getChars(202, 15, "呒刟枤讲ｗ卽尒亂袰枯讻盌嬀殳＜$"));
      BACKWARD = new EnumResultsSortType(CRCUtil.valueOf(5, "\031\r\036\005HQSV"), 1, 2, FM_Bytes.startsWith("吔向枼诱ｅ却奦井裾柪讫皇孊毢８", 4, 58));
      EnumResultsSortType[] arrayOfEnumResultsSortType = new EnumResultsSortType[2];
      arrayOfEnumResultsSortType[0] = FORWARD;
      arrayOfEnumResultsSortType[1] = BACKWARD;
      c = arrayOfEnumResultsSortType;
      label75: return;
    }
    catch (da localda)
    {
      break label75;
    }
  }

  public static EnumResultsSortType instance(int paramInt)
  {
    EnumResultsSortType localEnumResultsSortType1;
    while (true)
    {
      int i;
      int j;
      try
      {
        EnumResultsSortType[] arrayOfEnumResultsSortType = values();
        i = arrayOfEnumResultsSortType.length;
        j = 0;
        break label51;
        EnumResultsSortType localEnumResultsSortType2 = arrayOfEnumResultsSortType[j];
        int k = localEnumResultsSortType2.getId();
        if (k != paramInt)
          continue;
        localEnumResultsSortType1 = localEnumResultsSortType2;
        break;
        j++;
      }
      catch (da localda)
      {
        localEnumResultsSortType1 = null;
        break;
      }
      label51: if (j < i)
        continue;
      localEnumResultsSortType1 = null;
    }
    return localEnumResultsSortType1;
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
 * Qualified Name:     cn.com.fmsh.tsm.business.enums.EnumResultsSortType
 * JD-Core Version:    0.6.0
 */