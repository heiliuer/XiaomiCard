package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class SwitchPageData
  implements Parcelable
{
  public static final Parcelable.Creator<SwitchPageData> CREATOR = new Parcelable.Creator()
  {
    public SwitchPageData createFromParcel(Parcel paramParcel)
    {
      return new SwitchPageData(paramParcel);
    }

    public SwitchPageData[] newArray(int paramInt)
    {
      return new SwitchPageData[paramInt];
    }
  };
  public List<BankCardInfo> mBankCardInfos;
  public CardInfo mDefaultTransCard;
  public CardInfo mLastUsedCard;

  public SwitchPageData()
  {
  }

  protected SwitchPageData(Parcel paramParcel)
  {
    this.mDefaultTransCard = ((CardInfo)paramParcel.readParcelable(CardInfo.class.getClassLoader()));
    this.mLastUsedCard = ((CardInfo)paramParcel.readParcelable(CardInfo.class.getClassLoader()));
    this.mBankCardInfos = paramParcel.createTypedArrayList(BankCardInfo.CREATOR);
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(this.mDefaultTransCard, paramInt);
    paramParcel.writeParcelable(this.mLastUsedCard, paramInt);
    paramParcel.writeTypedList(this.mBankCardInfos);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.SwitchPageData
 * JD-Core Version:    0.6.0
 */