package com.unionpay.tsmservice.data;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class NinePatchInfo
  implements Parcelable
{
  public static final Parcelable.Creator<NinePatchInfo> CREATOR = new Parcelable.Creator()
  {
    public final NinePatchInfo createFromParcel(Parcel paramParcel)
    {
      return new NinePatchInfo(paramParcel);
    }

    public final NinePatchInfo[] newArray(int paramInt)
    {
      return new NinePatchInfo[paramInt];
    }
  };
  private Bitmap mBitmap;
  private byte[] mChunk;
  private Rect mPadding;

  public NinePatchInfo()
  {
  }

  public NinePatchInfo(Parcel paramParcel)
  {
    this.mBitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.mPadding = ((Rect)paramParcel.readParcelable(Rect.class.getClassLoader()));
    int i = paramParcel.readInt();
    if (i > 0)
    {
      this.mChunk = new byte[i];
      paramParcel.readByteArray(this.mChunk);
    }
    while (true)
    {
      return;
      this.mChunk = null;
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public Bitmap getBitmap()
  {
    return this.mBitmap;
  }

  public byte[] getChunk()
  {
    return this.mChunk;
  }

  public Rect getPadding()
  {
    return this.mPadding;
  }

  public void setBitmap(Bitmap paramBitmap)
  {
    this.mBitmap = paramBitmap;
  }

  public void setChunk(byte[] paramArrayOfByte)
  {
    this.mChunk = paramArrayOfByte;
  }

  public void setPadding(Rect paramRect)
  {
    this.mPadding = paramRect;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(this.mBitmap, paramInt);
    paramParcel.writeParcelable(this.mPadding, paramInt);
    if (this.mChunk != null)
    {
      paramParcel.writeInt(this.mChunk.length);
      paramParcel.writeByteArray(this.mChunk);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.data.NinePatchInfo
 * JD-Core Version:    0.6.0
 */