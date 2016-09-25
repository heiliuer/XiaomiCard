package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class EncryptDataResult
  implements Parcelable
{
  public static final Parcelable.Creator<EncryptDataResult> CREATOR = new Parcelable.Creator()
  {
    public final EncryptDataResult createFromParcel(Parcel paramParcel)
    {
      return new EncryptDataResult(paramParcel);
    }

    public final EncryptDataResult[] newArray(int paramInt)
    {
      return new EncryptDataResult[paramInt];
    }
  };
  private List<String> mEncryptData;

  public EncryptDataResult()
  {
  }

  public EncryptDataResult(Parcel paramParcel)
  {
    this.mEncryptData = new ArrayList();
    paramParcel.readList(this.mEncryptData, ClassLoader.getSystemClassLoader());
  }

  public int describeContents()
  {
    return 0;
  }

  public List<String> getEncryptData()
  {
    return this.mEncryptData;
  }

  public void setEncryptData(List<String> paramList)
  {
    this.mEncryptData = paramList;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeList(this.mEncryptData);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.result.EncryptDataResult
 * JD-Core Version:    0.6.0
 */