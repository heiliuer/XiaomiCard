package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GetEncryptDataResult
  implements Parcelable
{
  public static final Parcelable.Creator<GetEncryptDataResult> CREATOR = new Parcelable.Creator()
  {
    public final GetEncryptDataResult createFromParcel(Parcel paramParcel)
    {
      return new GetEncryptDataResult(paramParcel);
    }

    public final GetEncryptDataResult[] newArray(int paramInt)
    {
      return new GetEncryptDataResult[paramInt];
    }
  };
  private String mData;

  public GetEncryptDataResult()
  {
  }

  public GetEncryptDataResult(Parcel paramParcel)
  {
    this.mData = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getData()
  {
    return this.mData;
  }

  public void setData(String paramString)
  {
    this.mData = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mData);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.result.GetEncryptDataResult
 * JD-Core Version:    0.6.0
 */