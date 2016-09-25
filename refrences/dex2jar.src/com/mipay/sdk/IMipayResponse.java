package com.mipay.sdk;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IMipayResponse extends IInterface
{
  public abstract void onError(int paramInt, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void onResult(Bundle paramBundle)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IMipayResponse
  {
    private static final String DESCRIPTOR = "com.mipay.sdk.IMipayResponse";
    static final int TRANSACTION_onError = 2;
    static final int TRANSACTION_onResult = 1;

    public Stub()
    {
      attachInterface(this, "com.mipay.sdk.IMipayResponse");
    }

    public static IMipayResponse asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.mipay.sdk.IMipayResponse");
        if ((localIInterface != null) && ((localIInterface instanceof IMipayResponse)))
        {
          localObject = (IMipayResponse)localIInterface;
          continue;
        }
        localObject = new Proxy(paramIBinder);
      }
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool = true;
      switch (paramInt1)
      {
      default:
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        while (true)
        {
          return bool;
          paramParcel2.writeString("com.mipay.sdk.IMipayResponse");
        }
      case 1:
        paramParcel1.enforceInterface("com.mipay.sdk.IMipayResponse");
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle2 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle2 = null)
        {
          onResult(localBundle2);
          break;
        }
      case 2:
      }
      paramParcel1.enforceInterface("com.mipay.sdk.IMipayResponse");
      int i = paramParcel1.readInt();
      String str = paramParcel1.readString();
      if (paramParcel1.readInt() != 0);
      for (Bundle localBundle1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle1 = null)
      {
        onError(i, str, localBundle1);
        break;
      }
    }

    private static class Proxy
      implements IMipayResponse
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public String getInterfaceDescriptor()
      {
        return "com.mipay.sdk.IMipayResponse";
      }

      public void onError(int paramInt, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.mipay.sdk.IMipayResponse");
          localParcel.writeInt(paramInt);
          localParcel.writeString(paramString);
          if (paramBundle != null)
          {
            localParcel.writeInt(1);
            paramBundle.writeToParcel(localParcel, 0);
          }
          while (true)
          {
            this.mRemote.transact(2, localParcel, null, 1);
            return;
            localParcel.writeInt(0);
          }
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }

      public void onResult(Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.mipay.sdk.IMipayResponse");
          if (paramBundle != null)
          {
            localParcel.writeInt(1);
            paramBundle.writeToParcel(localParcel, 0);
          }
          while (true)
          {
            this.mRemote.transact(1, localParcel, null, 1);
            return;
            localParcel.writeInt(0);
          }
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.mipay.sdk.IMipayResponse
 * JD-Core Version:    0.6.0
 */