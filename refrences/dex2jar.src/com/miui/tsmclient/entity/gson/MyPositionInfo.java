package com.miui.tsmclient.entity.gson;

import com.google.gson.annotations.SerializedName;

public class MyPositionInfo extends CommonResponseInfo
{

  @SerializedName("data")
  private PositionData mPositionData;

  public String getAId()
  {
    if (this.mPositionData == null);
    for (String str = null; ; str = this.mPositionData.mAId)
      return str;
  }

  public String getCity()
  {
    if (this.mPositionData == null);
    for (String str = null; ; str = this.mPositionData.mCity)
      return str;
  }

  public String getCityId()
  {
    if (this.mPositionData == null);
    for (String str = null; ; str = this.mPositionData.mCityId)
      return str;
  }

  public String getCountry()
  {
    if (this.mPositionData == null);
    for (String str = null; ; str = this.mPositionData.mCountry)
      return str;
  }

  public String getDistrict()
  {
    if (this.mPositionData == null);
    for (String str = null; ; str = this.mPositionData.mDistrict)
      return str;
  }

  public String getIsp()
  {
    if (this.mPositionData == null);
    for (String str = null; ; str = this.mPositionData.mIsp)
      return str;
  }

  public String getProvince()
  {
    if (this.mPositionData == null);
    for (String str = null; ; str = this.mPositionData.mProvince)
      return str;
  }

  public String getSrcIp()
  {
    if (this.mPositionData == null);
    for (String str = null; ; str = this.mPositionData.mSrcIp)
      return str;
  }

  public static class PositionData
  {

    @SerializedName("aid")
    String mAId;

    @SerializedName("city")
    String mCity;

    @SerializedName("cityId")
    String mCityId;

    @SerializedName("country")
    String mCountry;

    @SerializedName("district")
    String mDistrict;

    @SerializedName("isp")
    String mIsp;

    @SerializedName("province")
    String mProvince;

    @SerializedName("srcip")
    String mSrcIp;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.gson.MyPositionInfo
 * JD-Core Version:    0.6.0
 */