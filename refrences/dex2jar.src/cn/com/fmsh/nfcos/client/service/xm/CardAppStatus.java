package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CardAppStatus
  implements Parcelable
{
  public static final Parcelable.Creator<CardAppStatus> CREATOR = new Parcelable.Creator()
  {
    public CardAppStatus createFromParcel(Parcel paramParcel)
    {
      CardAppStatus localCardAppStatus = new CardAppStatus();
      localCardAppStatus.setStatus(paramParcel.readInt());
      return localCardAppStatus;
    }

    public CardAppStatus[] newArray(int paramInt)
    {
      return new CardAppStatus[paramInt];
    }
  };
  private int status;

  public int describeContents()
  {
    return 0;
  }

  public int getStatus()
  {
    return this.status;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    setStatus(paramParcel.readInt());
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.status);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.CardAppStatus
 * JD-Core Version:    0.6.0
 */