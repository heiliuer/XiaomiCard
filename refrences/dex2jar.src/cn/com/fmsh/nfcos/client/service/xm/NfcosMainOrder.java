package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class NfcosMainOrder
  implements Parcelable
{
  public static final Parcelable.Creator<NfcosMainOrder> CREATOR = new Parcelable.Creator()
  {
    public NfcosMainOrder createFromParcel(Parcel paramParcel)
    {
      NfcosMainOrder localNfcosMainOrder = new NfcosMainOrder();
      localNfcosMainOrder.state = paramParcel.readInt();
      localNfcosMainOrder.id = NfcosMainOrder.readBytesWithNull(paramParcel);
      localNfcosMainOrder.date = paramParcel.readString();
      localNfcosMainOrder.time = paramParcel.readString();
      localNfcosMainOrder.amount = paramParcel.readInt();
      localNfcosMainOrder.businessOrders = new ArrayList();
      paramParcel.readList(localNfcosMainOrder.businessOrders, NfcosBusinessOrder.class.getClassLoader());
      localNfcosMainOrder.payOrders = new ArrayList();
      paramParcel.readList(localNfcosMainOrder.payOrders, NfcosPayOrder.class.getClassLoader());
      return localNfcosMainOrder;
    }

    public NfcosMainOrder[] newArray(int paramInt)
    {
      return new NfcosMainOrder[paramInt];
    }
  };
  public int amount;
  public List<NfcosBusinessOrder> businessOrders;
  public String date;
  public byte[] id;
  public List<NfcosPayOrder> payOrders;
  public int state;
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
    this.businessOrders = new ArrayList();
    paramParcel.readList(this.businessOrders, NfcosBusinessOrder.class.getClassLoader());
    this.payOrders = new ArrayList();
    paramParcel.readList(this.payOrders, NfcosPayOrder.class.getClassLoader());
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.state);
    writeBytesWithNull(paramParcel, this.id);
    paramParcel.writeString(this.date);
    paramParcel.writeString(this.time);
    paramParcel.writeInt(this.amount);
    paramParcel.writeList(this.businessOrders);
    paramParcel.writeList(this.payOrders);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder
 * JD-Core Version:    0.6.0
 */