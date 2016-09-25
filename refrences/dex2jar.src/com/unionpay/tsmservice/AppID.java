package com.unionpay.tsmservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class AppID
  implements Parcelable
{
  public static final Parcelable.Creator<AppID> CREATOR = new Parcelable.Creator()
  {
  };
  String a = "";
  String b = "";

  public AppID(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
  }

  public AppID(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAppAid()
  {
    return this.a;
  }

  public String getAppVersion()
  {
    return this.b;
  }

  public void setAppAid(String paramString)
  {
    this.a = paramString;
  }

  public void setAppVersion(String paramString)
  {
    this.b = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.AppID
 * JD-Core Version:    0.6.0
 */