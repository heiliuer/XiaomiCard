package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class SuzhoutongCardInfo extends PayableCardInfo
{
  public static final Parcelable.Creator<SuzhoutongCardInfo> CREATOR = new Parcelable.Creator()
  {
    public SuzhoutongCardInfo createFromParcel(Parcel paramParcel)
    {
      SuzhoutongCardInfo localSuzhoutongCardInfo = new SuzhoutongCardInfo();
      localSuzhoutongCardInfo.readFromParcel(paramParcel);
      return localSuzhoutongCardInfo;
    }

    public SuzhoutongCardInfo[] newArray(int paramInt)
    {
      return new SuzhoutongCardInfo[paramInt];
    }
  };

  public SuzhoutongCardInfo()
  {
    super(null);
    this.mCardType = "SUZHOUTONG";
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.SuzhoutongCardInfo
 * JD-Core Version:    0.6.0
 */