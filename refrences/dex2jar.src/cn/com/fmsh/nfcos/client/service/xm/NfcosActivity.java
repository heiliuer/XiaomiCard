package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class NfcosActivity
  implements Parcelable
{
  public static final Parcelable.Creator<NfcosActivity> CREATOR = new Parcelable.Creator()
  {
    public NfcosActivity createFromParcel(Parcel paramParcel)
    {
      NfcosActivity localNfcosActivity = new NfcosActivity();
      localNfcosActivity.name = paramParcel.readString();
      localNfcosActivity.code = paramParcel.readString();
      localNfcosActivity.start = paramParcel.readString();
      localNfcosActivity.end = paramParcel.readString();
      localNfcosActivity.total = paramParcel.readInt();
      localNfcosActivity.remainder = paramParcel.readInt();
      localNfcosActivity.definition = paramParcel.readString();
      localNfcosActivity.payChannel = paramParcel.readString();
      localNfcosActivity.payMin = paramParcel.readInt();
      return localNfcosActivity;
    }

    public NfcosActivity[] newArray(int paramInt)
    {
      return new NfcosActivity[paramInt];
    }
  };
  public String code;
  public String definition;
  public String end;
  public String name;
  public String payChannel;
  public int payMin;
  public int remainder;
  public String start;
  public int total;

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.name = paramParcel.readString();
    this.code = paramParcel.readString();
    this.start = paramParcel.readString();
    this.end = paramParcel.readString();
    this.total = paramParcel.readInt();
    this.remainder = paramParcel.readInt();
    this.definition = paramParcel.readString();
    this.payChannel = paramParcel.readString();
    this.payMin = paramParcel.readInt();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.name);
    paramParcel.writeString(this.code);
    paramParcel.writeString(this.start);
    paramParcel.writeString(this.end);
    paramParcel.writeInt(this.total);
    paramParcel.writeInt(this.remainder);
    paramParcel.writeString(this.definition);
    paramParcel.writeString(this.payChannel);
    paramParcel.writeInt(this.payMin);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.NfcosActivity
 * JD-Core Version:    0.6.0
 */