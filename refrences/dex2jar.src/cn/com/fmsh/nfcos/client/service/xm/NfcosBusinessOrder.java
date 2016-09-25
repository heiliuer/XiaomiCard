package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class NfcosBusinessOrder
  implements Parcelable
{
  public static final Parcelable.Creator<NfcosBusinessOrder> CREATOR = new Parcelable.Creator()
  {
    public NfcosBusinessOrder createFromParcel(Parcel paramParcel)
    {
      NfcosBusinessOrder localNfcosBusinessOrder = new NfcosBusinessOrder();
      localNfcosBusinessOrder.tradeDate = paramParcel.readString();
      localNfcosBusinessOrder.tradeTime = paramParcel.readString();
      localNfcosBusinessOrder.order = NfcosBusinessOrder.readBytesWithNull(paramParcel);
      localNfcosBusinessOrder.amount = paramParcel.readInt();
      localNfcosBusinessOrder.faceNo = paramParcel.readString();
      localNfcosBusinessOrder.tradeState = paramParcel.readInt();
      localNfcosBusinessOrder.invoiceStatus = paramParcel.readInt();
      localNfcosBusinessOrder.cardIoType = paramParcel.readInt();
      localNfcosBusinessOrder.businessOrderType = paramParcel.readInt();
      localNfcosBusinessOrder.product = paramParcel.readString();
      localNfcosBusinessOrder.seid = NfcosBusinessOrder.readBytesWithNull(paramParcel);
      localNfcosBusinessOrder.deviceModel = paramParcel.readString();
      localNfcosBusinessOrder.mainOrder = NfcosBusinessOrder.readBytesWithNull(paramParcel);
      return localNfcosBusinessOrder;
    }

    public NfcosBusinessOrder[] newArray(int paramInt)
    {
      return new NfcosBusinessOrder[paramInt];
    }
  };
  public int amount;
  public int businessOrderType;
  public int cardIoType;
  public String deviceModel;
  public String faceNo;
  public int invoiceStatus;
  public byte[] mainOrder;
  public byte[] order;
  public String product;
  public byte[] seid;
  public String tradeDate;
  public int tradeState;
  public String tradeTime;

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
    this.tradeDate = paramParcel.readString();
    this.tradeTime = paramParcel.readString();
    this.order = readBytesWithNull(paramParcel);
    this.amount = paramParcel.readInt();
    this.faceNo = paramParcel.readString();
    this.tradeState = paramParcel.readInt();
    this.invoiceStatus = paramParcel.readInt();
    this.cardIoType = paramParcel.readInt();
    this.businessOrderType = paramParcel.readInt();
    this.product = paramParcel.readString();
    this.seid = readBytesWithNull(paramParcel);
    this.deviceModel = paramParcel.readString();
    this.mainOrder = readBytesWithNull(paramParcel);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.tradeDate);
    paramParcel.writeString(this.tradeTime);
    writeBytesWithNull(paramParcel, this.order);
    paramParcel.writeInt(this.amount);
    paramParcel.writeString(this.faceNo);
    paramParcel.writeInt(this.tradeState);
    paramParcel.writeInt(this.invoiceStatus);
    paramParcel.writeInt(this.cardIoType);
    paramParcel.writeInt(this.businessOrderType);
    paramParcel.writeString(this.product);
    writeBytesWithNull(paramParcel, this.seid);
    paramParcel.writeString(this.deviceModel);
    writeBytesWithNull(paramParcel, this.mainOrder);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.NfcosBusinessOrder
 * JD-Core Version:    0.6.0
 */