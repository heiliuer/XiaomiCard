package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GetPubKeyResult
  implements Parcelable
{
  public static final Parcelable.Creator<GetPubKeyResult> CREATOR = new Parcelable.Creator()
  {
    public final GetPubKeyResult createFromParcel(Parcel paramParcel)
    {
      return new GetPubKeyResult(paramParcel);
    }

    public final GetPubKeyResult[] newArray(int paramInt)
    {
      return new GetPubKeyResult[paramInt];
    }
  };
  private String key;

  public GetPubKeyResult()
  {
  }

  public GetPubKeyResult(Parcel paramParcel)
  {
    this.key = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getKey()
  {
    return this.key;
  }

  public void setKey(String paramString)
  {
    this.key = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.key);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.result.GetPubKeyResult
 * JD-Core Version:    0.6.0
 */