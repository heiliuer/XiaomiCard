package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Notice
  implements Parcelable
{
  public static final Parcelable.Creator<Notice> CREATOR;
  public static int NOTICE_TXT = 22;
  public static int NOTICE_UNSOLVED = 33;
  public String content;
  public String endDate;
  public int no;
  public byte[] order;
  public String startDate;
  public String title;
  public int type;

  static
  {
    CREATOR = new Parcelable.Creator()
    {
      public Notice createFromParcel(Parcel paramParcel)
      {
        Notice localNotice = new Notice();
        localNotice.no = paramParcel.readInt();
        localNotice.title = paramParcel.readString();
        localNotice.content = paramParcel.readString();
        localNotice.startDate = paramParcel.readString();
        localNotice.endDate = paramParcel.readString();
        localNotice.type = paramParcel.readInt();
        localNotice.order = Notice.readBytesWithNull(paramParcel);
        return localNotice;
      }

      public Notice[] newArray(int paramInt)
      {
        return new Notice[paramInt];
      }
    };
  }

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
    this.no = paramParcel.readInt();
    this.title = paramParcel.readString();
    this.content = paramParcel.readString();
    this.startDate = paramParcel.readString();
    this.endDate = paramParcel.readString();
    this.type = paramParcel.readInt();
    this.order = readBytesWithNull(paramParcel);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.no);
    paramParcel.writeString(this.title);
    paramParcel.writeString(this.content);
    paramParcel.writeString(this.startDate);
    paramParcel.writeString(this.endDate);
    paramParcel.writeInt(this.type);
    writeBytesWithNull(paramParcel, this.order);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.Notice
 * JD-Core Version:    0.6.0
 */