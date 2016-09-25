package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class IssueProcess
  implements Parcelable
{
  public static final Parcelable.Creator<IssueProcess> CREATOR = new Parcelable.Creator()
  {
    public IssueProcess createFromParcel(Parcel paramParcel)
    {
      IssueProcess localIssueProcess = new IssueProcess();
      localIssueProcess.process = paramParcel.readInt();
      return localIssueProcess;
    }

    public IssueProcess[] newArray(int paramInt)
    {
      return new IssueProcess[paramInt];
    }
  };
  public int process;

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.process = paramParcel.readInt();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.process);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.IssueProcess
 * JD-Core Version:    0.6.0
 */