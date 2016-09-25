package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UserInfo
  implements Parcelable
{
  public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator()
  {
    public UserInfo createFromParcel(Parcel paramParcel)
    {
      UserInfo localUserInfo = new UserInfo();
      localUserInfo.username = paramParcel.readString();
      localUserInfo.password = paramParcel.readString();
      return localUserInfo;
    }

    public UserInfo[] newArray(int paramInt)
    {
      return new UserInfo[paramInt];
    }
  };
  public String password;
  public String username;

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.username = paramParcel.readString();
    this.password = paramParcel.readString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.username);
    paramParcel.writeString(this.password);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.UserInfo
 * JD-Core Version:    0.6.0
 */