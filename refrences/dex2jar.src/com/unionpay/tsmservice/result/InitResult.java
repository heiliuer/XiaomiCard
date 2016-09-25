package com.unionpay.tsmservice.result;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.data.UpdateInfo;

public class InitResult
  implements Parcelable
{
  public static final Parcelable.Creator<InitResult> CREATOR = new Parcelable.Creator()
  {
    public final InitResult createFromParcel(Parcel paramParcel)
    {
      return new InitResult(paramParcel);
    }

    public final InitResult[] newArray(int paramInt)
    {
      return new InitResult[paramInt];
    }
  };
  private UpdateInfo mUpdateInfo;

  public InitResult()
  {
  }

  public InitResult(Parcel paramParcel)
  {
    this.mUpdateInfo = ((UpdateInfo)paramParcel.readParcelable(UpdateInfo.class.getClassLoader()));
  }

  public int describeContents()
  {
    return 0;
  }

  public UpdateInfo getUpdateInfo()
  {
    return this.mUpdateInfo;
  }

  public void setUpdateInfo(UpdateInfo paramUpdateInfo)
  {
    this.mUpdateInfo = paramUpdateInfo;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(this.mUpdateInfo, paramInt);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.result.InitResult
 * JD-Core Version:    0.6.0
 */