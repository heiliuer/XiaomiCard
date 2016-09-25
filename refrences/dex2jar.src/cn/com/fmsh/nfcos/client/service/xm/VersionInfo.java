package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class VersionInfo
  implements Parcelable
{
  public static final Parcelable.Creator<VersionInfo> CREATOR = new Parcelable.Creator()
  {
    public VersionInfo createFromParcel(Parcel paramParcel)
    {
      int i = 1;
      VersionInfo localVersionInfo = new VersionInfo();
      localVersionInfo.version = paramParcel.readString();
      if (paramParcel.readInt() == i);
      while (true)
      {
        localVersionInfo.isUpdate = i;
        localVersionInfo.url = paramParcel.readString();
        return localVersionInfo;
        i = 0;
      }
    }

    public VersionInfo[] newArray(int paramInt)
    {
      return new VersionInfo[paramInt];
    }
  };
  public boolean isUpdate;
  public String url;
  public String version;

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    int i = 1;
    this.version = paramParcel.readString();
    if (paramParcel.readInt() == i);
    while (true)
    {
      this.isUpdate = i;
      this.url = paramParcel.readString();
      return;
      i = 0;
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.version);
    if (this.isUpdate);
    for (int i = 1; ; i = 0)
    {
      paramParcel.writeInt(i);
      paramParcel.writeString(this.url);
      return;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.VersionInfo
 * JD-Core Version:    0.6.0
 */