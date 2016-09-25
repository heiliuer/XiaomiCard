package com.unionpay.tsmservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface OnSafetyKeyboardCallback extends IInterface
{
  public abstract void onEditorChanged(int paramInt)
    throws RemoteException;

  public abstract void onHide()
    throws RemoteException;

  public abstract void onShow()
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements OnSafetyKeyboardCallback
  {
    public Stub()
    {
      attachInterface(this, "com.unionpay.tsmservice.OnSafetyKeyboardCallback");
    }

    public static OnSafetyKeyboardCallback asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
        if ((localIInterface != null) && ((localIInterface instanceof OnSafetyKeyboardCallback)))
        {
          localObject = (OnSafetyKeyboardCallback)localIInterface;
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
      boolean bool = true;
      switch (paramInt1)
      {
      default:
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        return bool;
        paramParcel2.writeString("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
        onShow();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
        onHide();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
        onEditorChanged(paramParcel1.readInt());
        paramParcel2.writeNoException();
      }
    }

    private static class a
      implements OnSafetyKeyboardCallback
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

      public final void onEditorChanged(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
          localParcel1.writeInt(paramInt);
          this.a.transact(3, localParcel1, localParcel2, 0);
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

      public final void onHide()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
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

      public final void onShow()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
          this.a.transact(1, localParcel1, localParcel2, 0);
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
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.OnSafetyKeyboardCallback
 * JD-Core Version:    0.6.0
 */