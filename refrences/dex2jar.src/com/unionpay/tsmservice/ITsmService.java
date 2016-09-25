package com.unionpay.tsmservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.unionpay.tsmservice.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.SafetyKeyboardRequestParams;

public abstract interface ITsmService extends IInterface
{
  public abstract int clearEncryptData(int paramInt)
    throws RemoteException;

  public abstract int encryptData(EncryptDataRequestParams paramEncryptDataRequestParams, ITsmCallback paramITsmCallback)
    throws RemoteException;

  public abstract int exchangeKey(String paramString, String[] paramArrayOfString)
    throws RemoteException;

  public abstract int getEncryptData(GetEncryptDataRequestParams paramGetEncryptDataRequestParams, ITsmCallback paramITsmCallback)
    throws RemoteException;

  public abstract int getPubKey(int paramInt, String[] paramArrayOfString)
    throws RemoteException;

  public abstract int hideKeyboard()
    throws RemoteException;

  public abstract int init(InitRequestParams paramInitRequestParams, ITsmCallback paramITsmCallback)
    throws RemoteException;

  public abstract int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams paramSafetyKeyboardRequestParams)
    throws RemoteException;

  public abstract int showSafetyKeyboard(SafetyKeyboardRequestParams paramSafetyKeyboardRequestParams, int paramInt, OnSafetyKeyboardCallback paramOnSafetyKeyboardCallback, ITsmActivityCallback paramITsmActivityCallback)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements ITsmService
  {
    public Stub()
    {
      attachInterface(this, "com.unionpay.tsmservice.ITsmService");
    }

    public static ITsmService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.unionpay.tsmservice.ITsmService");
        if ((localIInterface != null) && ((localIInterface instanceof ITsmService)))
        {
          localObject = (ITsmService)localIInterface;
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
      Object localObject = null;
      boolean bool;
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
      }
      while (true)
      {
        return bool;
        paramParcel2.writeString("com.unionpay.tsmservice.ITsmService");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmService");
        if (paramParcel1.readInt() != 0)
          localObject = (InitRequestParams)InitRequestParams.CREATOR.createFromParcel(paramParcel1);
        int i7 = init((InitRequestParams)localObject, ITsmCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i7);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmService");
        int i4 = paramParcel1.readInt();
        int i5 = paramParcel1.readInt();
        if (i5 < 0);
        while (true)
        {
          int i6 = getPubKey(i4, localObject);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i6);
          paramParcel2.writeStringArray(localObject);
          bool = true;
          break;
          localObject = new String[i5];
        }
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmService");
        String str = paramParcel1.readString();
        int i2 = paramParcel1.readInt();
        if (i2 < 0);
        while (true)
        {
          int i3 = exchangeKey(str, localObject);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i3);
          paramParcel2.writeStringArray(localObject);
          bool = true;
          break;
          localObject = new String[i2];
        }
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmService");
        if (paramParcel1.readInt() != 0)
          localObject = (EncryptDataRequestParams)EncryptDataRequestParams.CREATOR.createFromParcel(paramParcel1);
        int i1 = encryptData((EncryptDataRequestParams)localObject, ITsmCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmService");
        if (paramParcel1.readInt() != 0)
          localObject = (SafetyKeyboardRequestParams)SafetyKeyboardRequestParams.CREATOR.createFromParcel(paramParcel1);
        int n = showSafetyKeyboard((SafetyKeyboardRequestParams)localObject, paramParcel1.readInt(), OnSafetyKeyboardCallback.Stub.asInterface(paramParcel1.readStrongBinder()), ITsmActivityCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(n);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmService");
        if (paramParcel1.readInt() != 0)
          localObject = (SafetyKeyboardRequestParams)SafetyKeyboardRequestParams.CREATOR.createFromParcel(paramParcel1);
        int m = setSafetyKeyboardBitmap((SafetyKeyboardRequestParams)localObject);
        paramParcel2.writeNoException();
        paramParcel2.writeInt(m);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmService");
        if (paramParcel1.readInt() != 0)
          localObject = (GetEncryptDataRequestParams)GetEncryptDataRequestParams.CREATOR.createFromParcel(paramParcel1);
        int k = getEncryptData((GetEncryptDataRequestParams)localObject, ITsmCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeInt(k);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmService");
        int j = clearEncryptData(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(j);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.unionpay.tsmservice.ITsmService");
        int i = hideKeyboard();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i);
        bool = true;
      }
    }

    private static class a
      implements ITsmService
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

      public final int clearEncryptData(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
          localParcel1.writeInt(paramInt);
          this.a.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public final int encryptData(EncryptDataRequestParams paramEncryptDataRequestParams, ITsmCallback paramITsmCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
            if (paramEncryptDataRequestParams == null)
              continue;
            localParcel1.writeInt(1);
            paramEncryptDataRequestParams.writeToParcel(localParcel1, 0);
            if (paramITsmCallback != null)
            {
              localIBinder = paramITsmCallback.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.a.transact(4, localParcel1, localParcel2, 0);
              localParcel2.readException();
              int i = localParcel2.readInt();
              return i;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          IBinder localIBinder = null;
        }
      }

      public final int exchangeKey(String paramString, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
          localParcel1.writeString(paramString);
          if (paramArrayOfString == null)
            localParcel1.writeInt(-1);
          while (true)
          {
            this.a.transact(3, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            localParcel2.readStringArray(paramArrayOfString);
            return i;
            localParcel1.writeInt(paramArrayOfString.length);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public final int getEncryptData(GetEncryptDataRequestParams paramGetEncryptDataRequestParams, ITsmCallback paramITsmCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
            if (paramGetEncryptDataRequestParams == null)
              continue;
            localParcel1.writeInt(1);
            paramGetEncryptDataRequestParams.writeToParcel(localParcel1, 0);
            if (paramITsmCallback != null)
            {
              localIBinder = paramITsmCallback.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.a.transact(7, localParcel1, localParcel2, 0);
              localParcel2.readException();
              int i = localParcel2.readInt();
              return i;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          IBinder localIBinder = null;
        }
      }

      public final int getPubKey(int paramInt, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
          localParcel1.writeInt(paramInt);
          if (paramArrayOfString == null)
            localParcel1.writeInt(-1);
          while (true)
          {
            this.a.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            localParcel2.readStringArray(paramArrayOfString);
            return i;
            localParcel1.writeInt(paramArrayOfString.length);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public final int hideKeyboard()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
          this.a.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public final int init(InitRequestParams paramInitRequestParams, ITsmCallback paramITsmCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
            if (paramInitRequestParams == null)
              continue;
            localParcel1.writeInt(1);
            paramInitRequestParams.writeToParcel(localParcel1, 0);
            if (paramITsmCallback != null)
            {
              localIBinder = paramITsmCallback.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              this.a.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
              int i = localParcel2.readInt();
              return i;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          IBinder localIBinder = null;
        }
      }

      public final int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams paramSafetyKeyboardRequestParams)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
          if (paramSafetyKeyboardRequestParams != null)
          {
            localParcel1.writeInt(1);
            paramSafetyKeyboardRequestParams.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.a.transact(6, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
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

      public final int showSafetyKeyboard(SafetyKeyboardRequestParams paramSafetyKeyboardRequestParams, int paramInt, OnSafetyKeyboardCallback paramOnSafetyKeyboardCallback, ITsmActivityCallback paramITsmActivityCallback)
        throws RemoteException
      {
        IBinder localIBinder1 = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
            if (paramSafetyKeyboardRequestParams == null)
              continue;
            localParcel1.writeInt(1);
            paramSafetyKeyboardRequestParams.writeToParcel(localParcel1, 0);
            localParcel1.writeInt(paramInt);
            if (paramOnSafetyKeyboardCallback != null)
            {
              localIBinder2 = paramOnSafetyKeyboardCallback.asBinder();
              localParcel1.writeStrongBinder(localIBinder2);
              if (paramITsmActivityCallback == null)
                continue;
              localIBinder1 = paramITsmActivityCallback.asBinder();
              localParcel1.writeStrongBinder(localIBinder1);
              this.a.transact(5, localParcel1, localParcel2, 0);
              localParcel2.readException();
              int i = localParcel2.readInt();
              return i;
              localParcel1.writeInt(0);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          IBinder localIBinder2 = null;
        }
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.ITsmService
 * JD-Core Version:    0.6.0
 */