package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class GetEncryptDataRequestParams extends RequestParams
{
  public static final Parcelable.Creator<GetEncryptDataRequestParams> CREATOR = new Parcelable.Creator()
  {
    public final GetEncryptDataRequestParams createFromParcel(Parcel paramParcel)
    {
      return new GetEncryptDataRequestParams(paramParcel);
    }

    public final GetEncryptDataRequestParams[] newArray(int paramInt)
    {
      return new GetEncryptDataRequestParams[paramInt];
    }
  };
  private String mPan;
  private int mType;

  public GetEncryptDataRequestParams()
  {
  }

  public GetEncryptDataRequestParams(Parcel paramParcel)
  {
    super(paramParcel);
    this.mType = paramParcel.readInt();
    this.mPan = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getPan()
  {
    return this.mPan;
  }

  public int getType()
  {
    return this.mType;
  }

  public void setPan(String paramString)
  {
    this.mPan = paramString;
  }

  public void setType(int paramInt)
  {
    this.mType = paramInt;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.mType);
    paramParcel.writeString(this.mPan);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.request.GetEncryptDataRequestParams
 * JD-Core Version:    0.6.0
 */