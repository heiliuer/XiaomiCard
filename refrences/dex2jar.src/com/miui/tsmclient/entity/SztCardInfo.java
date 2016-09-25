package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class SztCardInfo extends PayableCardInfo
{
  public static final Parcelable.Creator<SztCardInfo> CREATOR = new Parcelable.Creator()
  {
    public SztCardInfo createFromParcel(Parcel paramParcel)
    {
      SztCardInfo localSztCardInfo = new SztCardInfo();
      localSztCardInfo.readFromParcel(paramParcel);
      return localSztCardInfo;
    }

    public SztCardInfo[] newArray(int paramInt)
    {
      return new SztCardInfo[paramInt];
    }
  };

  public SztCardInfo()
  {
    super(null);
    this.mCardType = "SZT";
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.SztCardInfo
 * JD-Core Version:    0.6.0
 */