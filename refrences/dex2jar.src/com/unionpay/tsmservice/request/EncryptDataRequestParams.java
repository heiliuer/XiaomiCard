package com.unionpay.tsmservice.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class EncryptDataRequestParams extends RequestParams
{
  public static final Parcelable.Creator<EncryptDataRequestParams> CREATOR = new Parcelable.Creator()
  {
    public final EncryptDataRequestParams createFromParcel(Parcel paramParcel)
    {
      return new EncryptDataRequestParams(paramParcel);
    }

    public final EncryptDataRequestParams[] newArray(int paramInt)
    {
      return new EncryptDataRequestParams[paramInt];
    }
  };
  private List<String> mData;

  public EncryptDataRequestParams()
  {
  }

  public EncryptDataRequestParams(Parcel paramParcel)
  {
    super(paramParcel);
    this.mData = new ArrayList();
    paramParcel.readList(this.mData, ClassLoader.getSystemClassLoader());
  }

  public List<String> getData()
  {
    return this.mData;
  }

  public void setData(List<String> paramList)
  {
    this.mData = paramList;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeList(this.mData);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.request.EncryptDataRequestParams
 * JD-Core Version:    0.6.0
 */