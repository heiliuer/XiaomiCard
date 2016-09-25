package com.miui.tsmclient.entity;

import android.content.Context;
import android.text.TextUtils;

public enum LntChildCity
  implements IChildCity
{
  public static final int INVALID_CITY_ID = -1;
  private int mCityId;
  private int mCityNameRes;

  static
  {
    FOSHAN = new LntChildCity("FOSHAN", 2, 5211, 2131296549);
    SHENZHEN = new LntChildCity("SHENZHEN", 3, 5220, 2131296550);
    ZHUHAI = new LntChildCity("ZHUHAI", 4, 5225, 2131296551);
    SHANWEI = new LntChildCity("SHANWEI", 5, 5230, 2131296552);
    JIANGMEN = new LntChildCity("JIANGMEN", 6, 5235, 2131296553);
    ZHAOQING = new LntChildCity("ZHAOQING", 7, 5240, 2131296554);
    ZHONGSHAN = new LntChildCity("ZHONGSHAN", 8, 5242, 2131296555);
    DONGGUAN = new LntChildCity("DONGGUAN", 9, 5245, 2131296556);
    HUIZHOU = new LntChildCity("HUIZHOU", 10, 5248, 2131296557);
    ZHANJIANG = new LntChildCity("ZHANJIANG", 11, 5250, 2131296558);
    SHANTOU = new LntChildCity("SHANTOU", 12, 5251, 2131296559);
    SHAOGUAN = new LntChildCity("SHAOGUAN", 13, 5252, 2131296560);
    HEYUAN = new LntChildCity("HEYUAN", 14, 5253, 2131296561);
    YANGJIANG = new LntChildCity("YANGJIANG", 15, 5254, 2131296562);
    QINGYUAN = new LntChildCity("QINGYUAN", 16, 5255, 2131296563);
    MAOMING = new LntChildCity("MAOMING", 17, 5256, 2131296564);
    MEIZHOU = new LntChildCity("MEIZHOU", 18, 5257, 2131296565);
    CHAOZHOU = new LntChildCity("CHAOZHOU", 19, 5258, 2131296566);
    JIEYANG = new LntChildCity("JIEYANG", 20, 5259, 2131296567);
    YUNFU = new LntChildCity("YUNFU", 21, 5260, 2131296568);
    LntChildCity[] arrayOfLntChildCity = new LntChildCity[22];
    arrayOfLntChildCity[0] = ALL_PROVINCE;
    arrayOfLntChildCity[1] = GUANGZHOU;
    arrayOfLntChildCity[2] = FOSHAN;
    arrayOfLntChildCity[3] = SHENZHEN;
    arrayOfLntChildCity[4] = ZHUHAI;
    arrayOfLntChildCity[5] = SHANWEI;
    arrayOfLntChildCity[6] = JIANGMEN;
    arrayOfLntChildCity[7] = ZHAOQING;
    arrayOfLntChildCity[8] = ZHONGSHAN;
    arrayOfLntChildCity[9] = DONGGUAN;
    arrayOfLntChildCity[10] = HUIZHOU;
    arrayOfLntChildCity[11] = ZHANJIANG;
    arrayOfLntChildCity[12] = SHANTOU;
    arrayOfLntChildCity[13] = SHAOGUAN;
    arrayOfLntChildCity[14] = HEYUAN;
    arrayOfLntChildCity[15] = YANGJIANG;
    arrayOfLntChildCity[16] = QINGYUAN;
    arrayOfLntChildCity[17] = MAOMING;
    arrayOfLntChildCity[18] = MEIZHOU;
    arrayOfLntChildCity[19] = CHAOZHOU;
    arrayOfLntChildCity[20] = JIEYANG;
    arrayOfLntChildCity[21] = YUNFU;
    $VALUES = arrayOfLntChildCity;
  }

  private LntChildCity(int paramInt1, int paramInt2)
  {
    this.mCityId = paramInt1;
    this.mCityNameRes = paramInt2;
  }

  public static LntChildCity getCityById(String paramString)
  {
    LntChildCity[] arrayOfLntChildCity = values();
    int i = arrayOfLntChildCity.length;
    int j = 0;
    LntChildCity localLntChildCity;
    if (j < i)
    {
      localLntChildCity = arrayOfLntChildCity[j];
      if (!TextUtils.equals(String.valueOf(localLntChildCity.getCityId()), paramString));
    }
    while (true)
    {
      return localLntChildCity;
      j++;
      break;
      localLntChildCity = null;
    }
  }

  public static int getCityIdByName(Context paramContext, String paramString)
  {
    int i = -1;
    if (paramContext == null);
    label62: 
    while (true)
    {
      return i;
      LntChildCity[] arrayOfLntChildCity = values();
      int j = arrayOfLntChildCity.length;
      for (int k = 0; ; k++)
      {
        if (k >= j)
          break label62;
        LntChildCity localLntChildCity = arrayOfLntChildCity[k];
        if (!TextUtils.equals(paramContext.getString(localLntChildCity.mCityNameRes), paramString))
          continue;
        i = localLntChildCity.mCityId;
        break;
      }
    }
  }

  public int getCityId()
  {
    return this.mCityId;
  }

  public int getCityNameRes()
  {
    return this.mCityNameRes;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.LntChildCity
 * JD-Core Version:    0.6.0
 */