package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RequestParams
  implements Parcelable
{
  public static final Parcelable.Creator<RequestParams> CREATOR = new Parcelable.Creator()
  {
    public final RequestParams createFromParcel(Parcel paramParcel)
    {
      return new RequestParams(paramParcel);
    }

    public final RequestParams[] newArray(int paramInt)
    {
      return new RequestParams[paramInt];
    }
  };
  private String mReserve = "";

  public RequestParams()
  {
  }

  public RequestParams(Parcel paramParcel)
  {
    this.mReserve = paramParcel.readString();
  }

  public RequestParams(String paramString)
  {
    this.mReserve = paramString;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getReserve()
  {
    return this.mReserve;
  }

  public void setReserve(String paramString)
  {
    this.mReserve = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mReserve);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.request.RequestParams
 * JD-Core Version:    0.6.0
 */