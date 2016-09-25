package com.tsmclient.smartcard.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TradeLog
  implements Parcelable, Comparable<TradeLog>
{
  public static final Parcelable.Creator<TradeLog> CREATOR = new Parcelable.Creator()
  {
    public TradeLog createFromParcel(Parcel paramParcel)
    {
      TradeLog localTradeLog = new TradeLog();
      localTradeLog.setTradeDate(paramParcel.readString());
      localTradeLog.setTradeTime(paramParcel.readString());
      localTradeLog.setAuAmount(paramParcel.readFloat());
      localTradeLog.setCountryCode(paramParcel.readString());
      localTradeLog.setCurCode(paramParcel.readInt());
      localTradeLog.setBusinessName(paramParcel.readString());
      localTradeLog.setTradeType(paramParcel.readInt());
      localTradeLog.setAtc(paramParcel.readString());
      return localTradeLog;
    }

    public TradeLog[] newArray(int paramInt)
    {
      return new TradeLog[paramInt];
    }
  };
  private String mAtc;
  private float mAuAmount;
  private String mBusinessName;
  private String mCountryCode;
  private int mCurCode;
  private String mTradeDate;
  private String mTradeTime;
  private int mTradeType;

  public int compareTo(TradeLog paramTradeLog)
  {
    long l1 = Long.parseLong(getTradeDate() + getTradeTime());
    long l2 = Long.parseLong(paramTradeLog.getTradeDate() + paramTradeLog.getTradeTime());
    int i;
    if (l1 - l2 > 0L)
      i = -1;
    while (true)
    {
      return i;
      if (l1 - l2 < 0L)
      {
        i = 1;
        continue;
      }
      i = 0;
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAtc()
  {
    return this.mAtc;
  }

  public float getAuAmount()
  {
    return this.mAuAmount;
  }

  public String getBusinessName()
  {
    return this.mBusinessName;
  }

  public String getCountryCode()
  {
    return this.mCountryCode;
  }

  public int getCurCode()
  {
    return this.mCurCode;
  }

  public String getTradeDate()
  {
    return this.mTradeDate;
  }

  public String getTradeTime()
  {
    return this.mTradeTime;
  }

  public int getTradeType()
  {
    return this.mTradeType;
  }

  public void setAtc(String paramString)
  {
    this.mAtc = paramString;
  }

  public void setAuAmount(float paramFloat)
  {
    this.mAuAmount = paramFloat;
  }

  public void setBusinessName(String paramString)
  {
    this.mBusinessName = paramString;
  }

  public void setCountryCode(String paramString)
  {
    this.mCountryCode = paramString;
  }

  public void setCurCode(int paramInt)
  {
    this.mCurCode = paramInt;
  }

  public void setTradeDate(String paramString)
  {
    this.mTradeDate = paramString;
  }

  public void setTradeTime(String paramString)
  {
    this.mTradeTime = paramString;
  }

  public void setTradeType(int paramInt)
  {
    this.mTradeType = paramInt;
  }

  public String toString()
  {
    return "\nTrade Date :" + getTradeDate() + "\nTrade Time :" + getTradeTime() + "\nAuAmount :" + getAuAmount() + "\nCountry Code :" + getCountryCode() + "\nCurrency code :" + getCurCode() + "\nBusiness Name :" + getBusinessName() + "\nTrade type :" + getTradeType() + "\nAtc :" + getAtc();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mTradeDate);
    paramParcel.writeString(this.mTradeTime);
    paramParcel.writeFloat(this.mAuAmount);
    paramParcel.writeString(this.mCountryCode);
    paramParcel.writeInt(this.mCurCode);
    paramParcel.writeString(this.mBusinessName);
    paramParcel.writeInt(this.mTradeType);
    paramParcel.writeString(this.mAtc);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.model.TradeLog
 * JD-Core Version:    0.6.0
 */