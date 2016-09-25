package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import cn.com.fmsh.util.FM_Bytes;

public class ActParameter
  implements Parcelable
{
  public static final Parcelable.Creator<ActParameter> CREATOR = new Parcelable.Creator()
  {
    public ActParameter createFromParcel(Parcel paramParcel)
    {
      ActParameter localActParameter = new ActParameter();
      localActParameter.tagName = paramParcel.readByte();
      localActParameter.tagValue = paramParcel.readString();
      return localActParameter;
    }

    public ActParameter[] newArray(int paramInt)
    {
      return new ActParameter[paramInt];
    }
  };
  public byte tagName;
  public String tagValue;

  public ActParameter()
  {
  }

  public ActParameter(byte paramByte1, byte paramByte2)
  {
    this.tagName = paramByte1;
    this.tagValue = String.valueOf(paramByte2);
  }

  public ActParameter(byte paramByte, int paramInt)
  {
    this.tagName = paramByte;
    this.tagValue = String.valueOf(paramInt);
  }

  public ActParameter(byte paramByte, byte[] paramArrayOfByte)
  {
    this.tagName = paramByte;
    this.tagValue = FM_Bytes.bytesToHexString(paramArrayOfByte);
  }

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.tagName = paramParcel.readByte();
    this.tagValue = paramParcel.readString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeByte(this.tagName);
    paramParcel.writeString(this.tagValue);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.ActParameter
 * JD-Core Version:    0.6.0
 */