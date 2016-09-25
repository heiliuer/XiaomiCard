package com.unionpay.tsmservice;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface ITsmActivityCallback extends IInterface
{
  public abstract void startActivity(String paramString1, String paramString2, int paramInt, Bundle paramBundle)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ITsmActivityCallback
  {
    public Stub()
    {
      attachInterface(this, "com.unionpay.tsmservice.ITsmActivityCallback");
    }

    public static ITsmActivityCallback asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.unionpay.tsmservice.ITsmActivityCallback");
        if ((localIInterface != null) && ((localIInterface instanceof ITsmActivityCallback)))
        {
          localObject = (ITsmActivityCallback)localIInterface;
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
      case 1598968902:
        for (bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); ; bool = true)
        {
          return bool;
          paramParcel2.writeString("com.unionpay.tsmservice.ITsmActivityCallback");
        }
      case 1:
      }
      paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmActivityCallback");
      String str1 = paramParcel1.readString();
      String str2 = paramParcel1.readString();
      int i = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0);
      for (Bundle localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle = null)
      {
        startActivity(str1, str2, i, localBundle);
        paramParcel2.writeNoException();
        bool = true;
        break;
      }
    }

    private static class a
      implements ITsmActivityCallback
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

      public final void startActivity(String paramString1, String paramString2, int paramInt, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmActivityCallback");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeInt(paramInt);
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
 * Qualified Name:     com.unionpay.tsmservice.ITsmActivityCallback
 * JD-Core Version:    0.6.0
 */