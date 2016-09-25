package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CardAppRecord
  implements Parcelable
{
  public static final Parcelable.Creator<CardAppRecord> CREATOR = new Parcelable.Creator()
  {
    public CardAppRecord createFromParcel(Parcel paramParcel)
    {
      CardAppRecord localCardAppRecord = new CardAppRecord();
      localCardAppRecord.tradeType = paramParcel.readInt();
      localCardAppRecord.tradeDate = paramParcel.readString();
      localCardAppRecord.tradeTime = paramParcel.readString();
      localCardAppRecord.amount = paramParcel.readInt();
      localCardAppRecord.balance = paramParcel.readInt();
      return localCardAppRecord;
    }

    public CardAppRecord[] newArray(int paramInt)
    {
      return new CardAppRecord[paramInt];
    }
  };
  public int amount;
  public int balance;
  public String tradeDate;
  public String tradeTime;
  public int tradeType;

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.tradeType = paramParcel.readInt();
    this.tradeDate = paramParcel.readString();
    this.tradeTime = paramParcel.readString();
    this.amount = paramParcel.readInt();
    this.balance = paramParcel.readInt();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.tradeType);
    paramParcel.writeString(this.tradeDate);
    paramParcel.writeString(this.tradeTime);
    paramParcel.writeInt(this.amount);
    paramParcel.writeInt(this.balance);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.CardAppRecord
 * JD-Core Version:    0.6.0
 */