package com.unionpay.tsmservice;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface ITsmCallback extends IInterface
{
  public abstract void onError(String paramString1, String paramString2)
    throws RemoteException;

  public abstract void onResult(Bundle paramBundle)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ITsmCallback
  {
    public Stub()
    {
      attachInterface(this, "com.unionpay.tsmservice.ITsmCallback");
    }

    public static ITsmCallback asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.unionpay.tsmservice.ITsmCallback");
        if ((localIInterface != null) && ((localIInterface instanceof ITsmCallback)))
        {
          localObject = (ITsmCallback)localIInterface;
          continue;
        }
        localObject = new a(paramIBinder);
      }
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool;
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
        paramParcel2.writeString("com.unionpay.tsmservice.ITsmCallback");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmCallback");
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle = null)
        {
          onResult(localBundle);
          paramParcel2.writeNoException();
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmCallback");
        onError(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        bool = true;
      }
    }

    private static class a
      implements ITsmCallback
    {
      private IBinder a;

      a(IBinder paramIBinder)
      {
        this.a = paramIBinder;
      }

      public final IBinder asBinder()
      {
        return this.a;
      }

      public final void onError(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmCallback");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.a.transact(2, localParcel1, localParcel2, 0);
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

      public final void onResult(Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmCallback");
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.a.transact(1, localParcel1, localParcel2, 0);
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
 * Qualified Name:     com.unionpay.tsmservice.ITsmCallback
 * JD-Core Version:    0.6.0
 */