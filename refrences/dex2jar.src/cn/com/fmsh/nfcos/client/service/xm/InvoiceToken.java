package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class InvoiceToken
  implements Parcelable
{
  public static final Parcelable.Creator<InvoiceToken> CREATOR = new Parcelable.Creator()
  {
    public InvoiceToken createFromParcel(Parcel paramParcel)
    {
      InvoiceToken localInvoiceToken = new InvoiceToken();
      localInvoiceToken.token = paramParcel.readString();
      return localInvoiceToken;
    }

    public InvoiceToken[] newArray(int paramInt)
    {
      return new InvoiceToken[paramInt];
    }
  };
  public String token;

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.token = paramParcel.readString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.token);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.InvoiceToken
 * JD-Core Version:    0.6.0
 */