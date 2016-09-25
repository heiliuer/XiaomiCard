package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ExchangeKeyResult
  implements Parcelable
{
  public static final Parcelable.Creator<ExchangeKeyResult> CREATOR = new Parcelable.Creator()
  {
    public final ExchangeKeyResult createFromParcel(Parcel paramParcel)
    {
      return new ExchangeKeyResult(paramParcel);
    }

    public final ExchangeKeyResult[] newArray(int paramInt)
    {
      return new ExchangeKeyResult[paramInt];
    }
  };
  private String key;

  public ExchangeKeyResult()
  {
  }

  public ExchangeKeyResult(Parcel paramParcel)
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
 * Qualified Name:     com.unionpay.tsmservice.result.ExchangeKeyResult
 * JD-Core Version:    0.6.0
 */