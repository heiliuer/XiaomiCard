package com.mipay.sdk;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IMipayService extends IInterface
{
  public abstract void pay(IMipayResponse paramIMipayResponse, Account paramAccount, String paramString, Bundle paramBundle)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IMipayService
  {
    private static final String DESCRIPTOR = "com.mipay.sdk.IMipayService";
    static final int TRANSACTION_pay = 1;

    public Stub()
    {
      attachInterface(this, "com.mipay.sdk.IMipayService");
    }

    public static IMipayService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.mipay.sdk.IMipayService");
        if ((localIInterface != null) && ((localIInterface instanceof IMipayService)))
        {
          localObject = (IMipayService)localIInterface;
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
          paramParcel2.writeString("com.mipay.sdk.IMipayService");
        }
      case 1:
      }
      paramParcel1.enforceInterface("com.mipay.sdk.IMipayService");
      IMipayResponse localIMipayResponse = IMipayResponse.Stub.asInterface(paramParcel1.readStrongBinder());
      Account localAccount;
      label91: String str;
      if (paramParcel1.readInt() != 0)
      {
        localAccount = (Account)Account.CREATOR.createFromParcel(paramParcel1);
        str = paramParcel1.readString();
        if (paramParcel1.readInt() == 0)
          break label143;
      }
      label143: for (Bundle localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle = null)
      {
        pay(localIMipayResponse, localAccount, str, localBundle);
        paramParcel2.writeNoException();
        break;
        localAccount = null;
        break label91;
      }
    }

    private static class Proxy
      implements IMipayService
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
        return "com.mipay.sdk.IMipayService";
      }

      public void pay(IMipayResponse paramIMipayResponse, Account paramAccount, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.mipay.sdk.IMipayService");
            if (paramIMipayResponse == null)
              continue;
            IBinder localIBinder = paramIMipayResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            if (paramAccount == null)
              continue;
            localParcel1.writeInt(1);
            paramAccount.writeToParcel(localParcel1, 0);
            localParcel1.writeString(paramString);
            if (paramBundle != null)
            {
              localParcel1.writeInt(1);
              paramBundle.writeToParcel(localParcel1, 0);
              this.mRemote.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
              return;
              localIBinder = null;
              continue;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          localParcel1.writeInt(0);
        }
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.mipay.sdk.IMipayService
 * JD-Core Version:    0.6.0
 */