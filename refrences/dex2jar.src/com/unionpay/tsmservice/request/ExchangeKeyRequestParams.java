package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ExchangeKeyRequestParams extends RequestParams
{
  public static final Parcelable.Creator<ExchangeKeyRequestParams> CREATOR = new Parcelable.Creator()
  {
    public final ExchangeKeyRequestParams createFromParcel(Parcel paramParcel)
    {
      return new ExchangeKeyRequestParams(paramParcel);
    }

    public final ExchangeKeyRequestParams[] newArray(int paramInt)
    {
      return new ExchangeKeyRequestParams[paramInt];
    }
  };
  private String mTempKey;
  private int mType;

  public ExchangeKeyRequestParams()
  {
  }

  public ExchangeKeyRequestParams(Parcel paramParcel)
  {
    super(paramParcel);
    this.mType = paramParcel.readInt();
    this.mTempKey = paramParcel.readString();
  }

  public String getTempKey()
  {
    return this.mTempKey;
  }

  public int getType()
  {
    return this.mType;
  }

  public void setTempKey(String paramString)
  {
    this.mTempKey = paramString;
  }

  public void setType(int paramInt)
  {
    this.mType = paramInt;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.mType);
    paramParcel.writeString(this.mTempKey);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.request.ExchangeKeyRequestParams
 * JD-Core Version:    0.6.0
 */