package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class VoucherInfo
  implements Parcelable
{
  public static final Parcelable.Creator<VoucherInfo> CREATOR = new Parcelable.Creator()
  {
    public VoucherInfo createFromParcel(Parcel paramParcel)
    {
      VoucherInfo localVoucherInfo = new VoucherInfo();
      localVoucherInfo.token = VoucherInfo.readBytesWithNull(paramParcel);
      return localVoucherInfo;
    }

    public VoucherInfo[] newArray(int paramInt)
    {
      return new VoucherInfo[paramInt];
    }
  };
  public byte[] token;

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
    this.token = readBytesWithNull(paramParcel);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    writeBytesWithNull(paramParcel, this.token);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.VoucherInfo
 * JD-Core Version:    0.6.0
 */