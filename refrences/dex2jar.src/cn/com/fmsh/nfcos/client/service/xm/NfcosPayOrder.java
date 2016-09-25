package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class NfcosPayOrder
  implements Parcelable
{
  public static final Parcelable.Creator<NfcosPayOrder> CREATOR = new Parcelable.Creator()
  {
    public NfcosPayOrder createFromParcel(Parcel paramParcel)
    {
      NfcosPayOrder localNfcosPayOrder = new NfcosPayOrder();
      localNfcosPayOrder.state = paramParcel.readInt();
      localNfcosPayOrder.id = NfcosPayOrder.readBytesWithNull(paramParcel);
      localNfcosPayOrder.date = paramParcel.readString();
      localNfcosPayOrder.time = paramParcel.readString();
      localNfcosPayOrder.amount = paramParcel.readInt();
      localNfcosPayOrder.thirdPayInfo = paramParcel.readString();
      localNfcosPayOrder.channel = paramParcel.readInt();
      localNfcosPayOrder.mainOrder = NfcosPayOrder.readBytesWithNull(paramParcel);
      return localNfcosPayOrder;
    }

    public NfcosPayOrder[] newArray(int paramInt)
    {
      return new NfcosPayOrder[paramInt];
    }
  };
  public int amount;
  public int channel;
  public String date;
  public byte[] id;
  public byte[] mainOrder;
  public int state;
  public String thirdPayInfo;
  public String time;

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
    this.state = paramParcel.readInt();
    this.id = readBytesWithNull(paramParcel);
    this.date = paramParcel.readString();
    this.time = paramParcel.readString();
    this.amount = paramParcel.readInt();
    this.thirdPayInfo = paramParcel.readString();
    this.channel = paramParcel.readInt();
    this.mainOrder = readBytesWithNull(paramParcel);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.state);
    writeBytesWithNull(paramParcel, this.id);
    paramParcel.writeString(this.date);
    paramParcel.writeString(this.time);
    paramParcel.writeInt(this.amount);
    paramParcel.writeString(this.thirdPayInfo);
    paramParcel.writeInt(this.channel);
    writeBytesWithNull(paramParcel, this.mainOrder);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.NfcosPayOrder
 * JD-Core Version:    0.6.0
 */