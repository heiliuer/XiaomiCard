package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class BroadCastParameter
  implements Parcelable
{
  public static final Parcelable.Creator<BroadCastParameter> CREATOR = new Parcelable.Creator()
  {
    public BroadCastParameter createFromParcel(Parcel paramParcel)
    {
      BroadCastParameter localBroadCastParameter = new BroadCastParameter();
      localBroadCastParameter.broadcastType = paramParcel.readInt();
      localBroadCastParameter.process = paramParcel.readInt();
      return localBroadCastParameter;
    }

    public BroadCastParameter[] newArray(int paramInt)
    {
      return new BroadCastParameter[paramInt];
    }
  };
  public int broadcastType;
  public int process;

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.broadcastType = paramParcel.readInt();
    this.process = paramParcel.readInt();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.broadcastType);
    paramParcel.writeInt(this.process);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.BroadCastParameter
 * JD-Core Version:    0.6.0
 */