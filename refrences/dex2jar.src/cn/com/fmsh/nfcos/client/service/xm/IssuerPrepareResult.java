package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class IssuerPrepareResult
  implements Parcelable
{
  public static final Parcelable.Creator<IssuerPrepareResult> CREATOR = new Parcelable.Creator()
  {
    public IssuerPrepareResult createFromParcel(Parcel paramParcel)
    {
      IssuerPrepareResult localIssuerPrepareResult = new IssuerPrepareResult();
      localIssuerPrepareResult.sir = IssuerPrepareResult.readBytesWithNull(paramParcel);
      localIssuerPrepareResult.failDesc = IssuerPrepareResult.readBytesWithNull(paramParcel);
      return localIssuerPrepareResult;
    }

    public IssuerPrepareResult[] newArray(int paramInt)
    {
      return new IssuerPrepareResult[paramInt];
    }
  };
  public byte[] failDesc;
  public byte[] sir;

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
    this.sir = readBytesWithNull(paramParcel);
    this.failDesc = readBytesWithNull(paramParcel);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    writeBytesWithNull(paramParcel, this.sir);
    writeBytesWithNull(paramParcel, this.failDesc);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.IssuerPrepareResult
 * JD-Core Version:    0.6.0
 */