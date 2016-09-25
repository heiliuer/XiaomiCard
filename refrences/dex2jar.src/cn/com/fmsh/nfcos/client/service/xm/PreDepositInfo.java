package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PreDepositInfo
  implements Parcelable
{
  public static final Parcelable.Creator<PreDepositInfo> CREATOR = new Parcelable.Creator()
  {
    public PreDepositInfo createFromParcel(Parcel paramParcel)
    {
      PreDepositInfo localPreDepositInfo = new PreDepositInfo();
      localPreDepositInfo.total = paramParcel.readInt();
      localPreDepositInfo.blance = paramParcel.readInt();
      return localPreDepositInfo;
    }

    public PreDepositInfo[] newArray(int paramInt)
    {
      return new PreDepositInfo[paramInt];
    }
  };
  public int blance;
  public int total;

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.total = paramParcel.readInt();
    this.blance = paramParcel.readInt();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.total);
    paramParcel.writeInt(this.blance);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.PreDepositInfo
 * JD-Core Version:    0.6.0
 */