package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import cn.com.fmsh.nfcos.client.service.xm.CardAppInfo;

public class SptcCardInfo extends FmshCardInfo
{
  public static final Parcelable.Creator<SptcCardInfo> CREATOR = new Parcelable.Creator()
  {
    public SptcCardInfo createFromParcel(Parcel paramParcel)
    {
      SptcCardInfo localSptcCardInfo = new SptcCardInfo();
      localSptcCardInfo.readFromParcel(paramParcel);
      return localSptcCardInfo;
    }

    public SptcCardInfo[] newArray(int paramInt)
    {
      return new SptcCardInfo[paramInt];
    }
  };

  public SptcCardInfo()
  {
    this(null);
  }

  public SptcCardInfo(CardAppInfo paramCardAppInfo)
  {
    super(paramCardAppInfo, "SPTC");
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.SptcCardInfo
 * JD-Core Version:    0.6.0
 */