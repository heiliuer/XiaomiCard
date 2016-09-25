package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class InitRequestParams extends RequestParams
{
  public static final Parcelable.Creator<InitRequestParams> CREATOR = new Parcelable.Creator()
  {
    public final InitRequestParams createFromParcel(Parcel paramParcel)
    {
      return new InitRequestParams(paramParcel);
    }

    public final InitRequestParams[] newArray(int paramInt)
    {
      return new InitRequestParams[paramInt];
    }
  };
  private String mSignature = "";

  public InitRequestParams()
  {
  }

  public InitRequestParams(Parcel paramParcel)
  {
    super(paramParcel);
    this.mSignature = paramParcel.readString();
  }

  public String getSignature()
  {
    return this.mSignature;
  }

  public void setSignature(String paramString)
  {
    this.mSignature = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.mSignature);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.request.InitRequestParams
 * JD-Core Version:    0.6.0
 */