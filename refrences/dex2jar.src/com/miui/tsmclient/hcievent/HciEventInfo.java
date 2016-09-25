package com.miui.tsmclient.hcievent;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class HciEventInfo
  implements Parcelable
{
  public static final Parcelable.Creator<HciEventInfo> CREATOR = new Parcelable.Creator()
  {
    public HciEventInfo createFromParcel(Parcel paramParcel)
    {
      return new HciEventInfo(paramParcel);
    }

    public HciEventInfo[] newArray(int paramInt)
    {
      return new HciEventInfo[paramInt];
    }
  };
  public String mAid;
  public int mBalance;
  public boolean mIsBankEvent;
  private boolean mIsTradeSuccess = true;
  public int mTradeAmount;
  public long mTradeTime;

  public HciEventInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public HciEventInfo(String paramString, long paramLong, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.mAid = paramString;
    this.mTradeTime = paramLong;
    this.mTradeAmount = paramInt1;
    this.mBalance = paramInt2;
    this.mIsBankEvent = paramBoolean;
  }

  public HciEventInfo(String paramString, long paramLong, int paramInt, boolean paramBoolean)
  {
    this(paramString, paramLong, paramInt, 0, paramBoolean);
  }

  public HciEventInfo(String paramString, long paramLong, boolean paramBoolean)
  {
    this(paramString, paramLong, 0, paramBoolean);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean isTradeSuccess()
  {
    return this.mIsTradeSuccess;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    boolean bool1 = false;
    this.mAid = paramParcel.readString();
    this.mTradeTime = paramParcel.readLong();
    this.mTradeAmount = paramParcel.readInt();
    this.mBalance = paramParcel.readInt();
    boolean bool2;
    if (paramParcel.readInt() == 0)
    {
      bool2 = true;
      this.mIsBankEvent = bool2;
      if (paramParcel.readInt() != 1)
        break label67;
    }
    while (true)
    {
      this.mIsTradeSuccess = bool1;
      return;
      bool2 = false;
      break;
      label67: bool1 = true;
    }
  }

  public void setTradeState(boolean paramBoolean)
  {
    this.mIsTradeSuccess = paramBoolean;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 0;
    paramParcel.writeString(this.mAid);
    paramParcel.writeLong(this.mTradeTime);
    paramParcel.writeInt(this.mTradeAmount);
    paramParcel.writeInt(this.mBalance);
    int j;
    if (this.mIsBankEvent)
    {
      j = 0;
      paramParcel.writeInt(j);
      if (!this.mIsTradeSuccess)
        break label69;
    }
    while (true)
    {
      paramParcel.writeInt(i);
      return;
      j = 1;
      break;
      label69: i = 1;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.hcievent.HciEventInfo
 * JD-Core Version:    0.6.0
 */