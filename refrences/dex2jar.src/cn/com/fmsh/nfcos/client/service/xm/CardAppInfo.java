package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public class CardAppInfo
  implements Parcelable
{
  public static final Parcelable.Creator<CardAppInfo> CREATOR = new Parcelable.Creator()
  {
    public CardAppInfo createFromParcel(Parcel paramParcel)
    {
      CardAppInfo localCardAppInfo = new CardAppInfo();
      localCardAppInfo.cardFaceNo = paramParcel.readString();
      localCardAppInfo.appNo = CardAppInfo.readBytesWithNull(paramParcel);
      localCardAppInfo.balance = paramParcel.readInt();
      localCardAppInfo.records = ((CardAppRecord[])paramParcel.readParcelableArray(CardAppRecord.class.getClassLoader()));
      localCardAppInfo.appLock = paramParcel.readInt();
      localCardAppInfo.cardType = paramParcel.readInt();
      localCardAppInfo.moc = paramParcel.readString();
      localCardAppInfo.time4Validity = paramParcel.readString();
      return localCardAppInfo;
    }

    public CardAppInfo[] newArray(int paramInt)
    {
      return new CardAppInfo[paramInt];
    }
  };
  public int appLock;
  public byte[] appNo;
  public int balance;
  public String cardFaceNo;
  public int cardType;
  public String moc;
  public CardAppRecord[] records = new CardAppRecord[0];
  public String time4Validity;

  static byte[] readBytesWithNull(Parcel paramParcel)
  {
    int i = paramParcel.readInt();
    byte[] arrayOfByte = null;
    if (i >= 0)
    {
      arrayOfByte = new byte[i];
      paramParcel.readByteArray(arrayOfByte);
    }
    return arrayOfByte;
  }

  static void writeBytesWithNull(Parcel paramParcel, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      paramParcel.writeInt(-1);
    while (true)
    {
      return;
      paramParcel.writeInt(paramArrayOfByte.length);
      paramParcel.writeByteArray(paramArrayOfByte);
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.cardFaceNo = paramParcel.readString();
    this.appNo = readBytesWithNull(paramParcel);
    this.balance = paramParcel.readInt();
    Parcelable[] arrayOfParcelable = paramParcel.readParcelableArray(CardAppRecord.class.getClassLoader());
    if (arrayOfParcelable != null)
      this.records = ((CardAppRecord[])Arrays.copyOf(arrayOfParcelable, arrayOfParcelable.length, [Lcn.com.fmsh.nfcos.client.service.xm.CardAppRecord.class));
    this.appLock = paramParcel.readInt();
    this.cardType = paramParcel.readInt();
    this.moc = paramParcel.readString();
    this.time4Validity = paramParcel.readString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.cardFaceNo);
    writeBytesWithNull(paramParcel, this.appNo);
    paramParcel.writeInt(this.balance);
    paramParcel.writeParcelableArray(this.records, 1);
    paramParcel.writeInt(this.appLock);
    paramParcel.writeInt(this.cardType);
    paramParcel.writeString(this.moc);
    paramParcel.writeString(this.time4Validity);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.CardAppInfo
 * JD-Core Version:    0.6.0
 */