package com.miui.tsmclientsdk;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import java.util.List;

public abstract interface IMiTsmService extends IInterface
{
  public abstract void createSSD(IMiTsmResponse paramIMiTsmResponse, int paramInt)
    throws RemoteException;

  public abstract void deleteAllBankCard(IMiTsmResponse paramIMiTsmResponse)
    throws RemoteException;

  public abstract void deleteBankCard(IMiTsmResponse paramIMiTsmResponse, String paramString)
    throws RemoteException;

  public abstract void getActiveCards(IMiTsmResponse paramIMiTsmResponse, String paramString)
    throws RemoteException;

  public abstract void getCPLC(IMiTsmResponse paramIMiTsmResponse)
    throws RemoteException;

  public abstract void getCardInfo(IMiTsmResponse paramIMiTsmResponse, List<String> paramList)
    throws RemoteException;

  public abstract void getCardsQuantity(IMiTsmResponse paramIMiTsmResponse, String paramString)
    throws RemoteException;

  public abstract void getDefaultCard(IMiTsmResponse paramIMiTsmResponse, String paramString)
    throws RemoteException;

  public abstract void getSeBankCards(IMiTsmResponse paramIMiTsmResponse)
    throws RemoteException;

  public abstract void getTransCardState(IMiTsmResponse paramIMiTsmResponse)
    throws RemoteException;

  public abstract void isBankCardAvailable(IMiTsmResponse paramIMiTsmResponse)
    throws RemoteException;

  public abstract void manageBankCard(IMiTsmResponse paramIMiTsmResponse, String paramString, int paramInt)
    throws RemoteException;

  public abstract void setDefaultCard(IMiTsmResponse paramIMiTsmResponse, String paramString, Bundle paramBundle)
    throws RemoteException;

  public abstract void syncBankCardStatus(IMiTsmResponse paramIMiTsmResponse)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IMiTsmService
  {
    private static final String DESCRIPTOR = "com.miui.tsmclientsdk.IMiTsmService";
    static final int TRANSACTION_createSSD = 4;
    static final int TRANSACTION_deleteAllBankCard = 10;
    static final int TRANSACTION_deleteBankCard = 9;
    static final int TRANSACTION_getActiveCards = 7;
    static final int TRANSACTION_getCPLC = 1;
    static final int TRANSACTION_getCardInfo = 6;
    static final int TRANSACTION_getCardsQuantity = 5;
    static final int TRANSACTION_getDefaultCard = 2;
    static final int TRANSACTION_getSeBankCards = 8;
    static final int TRANSACTION_getTransCardState = 13;
    static final int TRANSACTION_isBankCardAvailable = 12;
    static final int TRANSACTION_manageBankCard = 11;
    static final int TRANSACTION_setDefaultCard = 3;
    static final int TRANSACTION_syncBankCardStatus = 14;

    public Stub()
    {
      attachInterface(this, "com.miui.tsmclientsdk.IMiTsmService");
    }

    public static IMiTsmService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.miui.tsmclientsdk.IMiTsmService");
        if ((localIInterface != null) && ((localIInterface instanceof IMiTsmService)))
        {
          localObject = (IMiTsmService)localIInterface;
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
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      }
      while (true)
      {
        return bool;
        paramParcel2.writeString("com.miui.tsmclientsdk.IMiTsmService");
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        getCPLC(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        getDefaultCard(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        IMiTsmResponse localIMiTsmResponse = IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder());
        String str = paramParcel1.readString();
        if (paramParcel1.readInt() != 0);
        for (Bundle localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; localBundle = null)
        {
          setDefaultCard(localIMiTsmResponse, str, localBundle);
          paramParcel2.writeNoException();
          break;
        }
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        createSSD(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        getCardsQuantity(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        getCardInfo(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.createStringArrayList());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        getActiveCards(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        getSeBankCards(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        deleteBankCard(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        deleteAllBankCard(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        manageBankCard(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        isBankCardAvailable(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        getTransCardState(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.miui.tsmclientsdk.IMiTsmService");
        syncBankCardStatus(IMiTsmResponse.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
      }
    }

    private static class Proxy
      implements IMiTsmService
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

      public void createSSD(IMiTsmResponse paramIMiTsmResponse, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            this.mRemote.transact(4, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void deleteAllBankCard(IMiTsmResponse paramIMiTsmResponse)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(10, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void deleteBankCard(IMiTsmResponse paramIMiTsmResponse, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.mRemote.transact(9, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void getActiveCards(IMiTsmResponse paramIMiTsmResponse, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.mRemote.transact(7, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void getCPLC(IMiTsmResponse paramIMiTsmResponse)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void getCardInfo(IMiTsmResponse paramIMiTsmResponse, List<String> paramList)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeStringList(paramList);
            this.mRemote.transact(6, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void getCardsQuantity(IMiTsmResponse paramIMiTsmResponse, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.mRemote.transact(5, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void getDefaultCard(IMiTsmResponse paramIMiTsmResponse, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            this.mRemote.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public String getInterfaceDescriptor()
      {
        return "com.miui.tsmclientsdk.IMiTsmService";
      }

      public void getSeBankCards(IMiTsmResponse paramIMiTsmResponse)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(8, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void getTransCardState(IMiTsmResponse paramIMiTsmResponse)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(13, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void isBankCardAvailable(IMiTsmResponse paramIMiTsmResponse)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(12, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void manageBankCard(IMiTsmResponse paramIMiTsmResponse, String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            this.mRemote.transact(11, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void setDefaultCard(IMiTsmResponse paramIMiTsmResponse, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          IBinder localIBinder;
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeString(paramString);
            if (paramBundle == null)
              break label97;
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.mRemote.transact(3, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localIBinder = null;
            break;
            label97: localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void syncBankCardStatus(IMiTsmResponse paramIMiTsmResponse)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.miui.tsmclientsdk.IMiTsmService");
          if (paramIMiTsmResponse != null)
          {
            localIBinder = paramIMiTsmResponse.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            this.mRemote.transact(14, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclientsdk.IMiTsmService
 * JD-Core Version:    0.6.0
 */