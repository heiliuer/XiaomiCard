package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LoginInfo
  implements Parcelable
{
  public static final Parcelable.Creator<LoginInfo> CREATOR = new Parcelable.Creator()
  {
    public LoginInfo createFromParcel(Parcel paramParcel)
    {
      LoginInfo localLoginInfo = new LoginInfo();
      localLoginInfo.loginResult = paramParcel.readInt();
      localLoginInfo.loginFailureCount = paramParcel.readInt();
      localLoginInfo.userLockTime = paramParcel.readInt();
      return localLoginInfo;
    }

    public LoginInfo[] newArray(int paramInt)
    {
      return new LoginInfo[paramInt];
    }
  };
  public int loginFailureCount;
  public int loginResult;
  public int userLockTime;

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.loginResult = paramParcel.readInt();
    this.loginFailureCount = paramParcel.readInt();
    this.userLockTime = paramParcel.readInt();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.loginResult);
    paramParcel.writeInt(this.loginFailureCount);
    paramParcel.writeInt(this.userLockTime);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.LoginInfo
 * JD-Core Version:    0.6.0
 */