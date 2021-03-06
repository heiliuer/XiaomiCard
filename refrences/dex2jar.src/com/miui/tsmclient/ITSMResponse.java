package com.miui.tsmclient;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface ITSMResponse extends IInterface
{
  public abstract void onError(int paramInt, String paramString)
    throws RemoteException;

  public abstract void onResult(Bundle paramBundle)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ITSMResponse
  {
    private static final String DESCRIPTOR = "com.miui.tsmclient.ITSMResponse";
    static final int TRANSACTION_onError = 2;
    static final int TRANSACTION_onResult = 1;

    public Stub()
    {
      attachInterface(this, "com.miui.tsmclient.ITSMResponse");
    }

    public static ITSMResponse asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.miui.tsmclient.ITSMResponse");
        if ((localIInterface != null) && ((localIInterface instanceof ITSMResponse)))
        {
          localObject = (ITSMResponse)localIInterface;
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
      case 1:
      case 2:
      }
      while (true)
      {
        return bool;
        paramParcel2.writeString("com.miui.tsmclient.ITSMResponse");
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclient.ITSMResponse");
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle = null)
        {
          onResult(localBundle);
          paramParcel2.writeNoException();
          break;
        }
        paramParcel1.enforceInterface("com.miui.tsmclient.ITSMResponse");
        onError(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
      }
    }

    private static class Proxy
      implements ITSMResponse
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
        return "com.miui.tsmclient.ITSMResponse";
      }

      public void onError(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclient.ITSMResponse");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void onResult(Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclient.ITSMResponse");
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ITSMResponse
 * JD-Core Version:    0.6.0
 */