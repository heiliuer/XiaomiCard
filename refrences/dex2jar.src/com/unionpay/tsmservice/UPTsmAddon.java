package com.unionpay.tsmservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import com.unionpay.tsmservice.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.RequestParams;
import com.unionpay.tsmservice.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.result.EncryptDataResult;
import com.unionpay.tsmservice.result.GetEncryptDataResult;
import com.unionpay.tsmservice.result.InitResult;
import com.unionpay.tsmservice.utils.IUPJniInterface;
import java.util.ArrayList;
import java.util.HashMap;

public class UPTsmAddon
{
  private static UPTsmAddon a;
  private static ArrayList<UPTsmConnectionListener> b;
  private Context c = null;
  private ServiceConnection d = null;
  private ITsmService e = null;
  private boolean f = false;
  private HashMap<String, ITsmCallback> g = new HashMap();
  private HashMap<String, ITsmCallback> h = new HashMap();
  private HashMap<String, ITsmCallback> i = new HashMap();
  private HashMap<String, ITsmActivityCallback> j = new HashMap();
  private int[] k;
  private final Handler.Callback l = new Handler.Callback()
  {
    /** @deprecated */
    public final boolean handleMessage(Message paramMessage)
    {
      int i = 1;
      monitorenter;
      while (true)
      {
        try
        {
          int j = paramMessage.what;
          switch (j)
          {
          default:
            i = 0;
            return i;
          case 0:
            UPTsmAddon.a();
            continue;
          case 1:
          }
        }
        finally
        {
          monitorexit;
        }
        UPTsmAddon.b();
      }
    }
  };
  private final Handler m = new Handler(Looper.getMainLooper(), this.l);

  static
  {
    try
    {
      System.loadLibrary("uptsmaddon");
      a = null;
      b = null;
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      while (true)
        localUnsatisfiedLinkError.printStackTrace();
    }
  }

  private UPTsmAddon(Context paramContext)
  {
    this.c = paramContext;
    this.k = new int[6];
    if (!a(paramContext))
      throw new RuntimeException();
  }

  private static int a(int paramInt, RequestParams paramRequestParams, ITsmCallback paramITsmCallback)
    throws RemoteException
  {
    return new SessionKeyReExchange(a, paramInt, paramRequestParams, paramITsmCallback).reExchangeKey();
  }

  private static int a(int paramInt1, SafetyKeyboardRequestParams paramSafetyKeyboardRequestParams, int paramInt2, OnSafetyKeyboardCallback paramOnSafetyKeyboardCallback, Context paramContext)
    throws RemoteException
  {
    return new SessionKeyReExchange(a, paramInt1, paramSafetyKeyboardRequestParams, paramInt2, paramOnSafetyKeyboardCallback, paramContext).reExchangeKey();
  }

  private static boolean a(Context paramContext)
  {
    try
    {
      boolean bool2 = IUPJniInterface.iJE(paramContext);
      bool1 = bool2;
      return bool1;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      while (true)
      {
        localUnsatisfiedLinkError.printStackTrace();
        boolean bool1 = false;
      }
    }
  }

  private static boolean b(String paramString)
  {
    try
    {
      boolean bool2 = IUPJniInterface.cSKV(paramString);
      bool1 = bool2;
      return bool1;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      while (true)
      {
        localUnsatisfiedLinkError.printStackTrace();
        boolean bool1 = false;
      }
    }
  }

  private static String c(String paramString)
  {
    try
    {
      String str2 = IUPJniInterface.eMG(paramString);
      str1 = str2;
      return str1;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      while (true)
      {
        localUnsatisfiedLinkError.printStackTrace();
        String str1 = "";
      }
    }
  }

  private static String d(String paramString)
  {
    try
    {
      String str2 = IUPJniInterface.dMG(paramString);
      str1 = str2;
      return str1;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      while (true)
      {
        localUnsatisfiedLinkError.printStackTrace();
        String str1 = "";
      }
    }
  }

  public static UPTsmAddon getInstance(Context paramContext)
  {
    if (paramContext == null);
    for (UPTsmAddon localUPTsmAddon = null; ; localUPTsmAddon = a)
    {
      return localUPTsmAddon;
      if (a == null)
        a = new UPTsmAddon(paramContext.getApplicationContext());
      if (b != null)
        continue;
      b = new ArrayList();
    }
  }

  public void addConnectionListener(UPTsmConnectionListener paramUPTsmConnectionListener)
  {
    if (paramUPTsmConnectionListener != null)
      b.add(paramUPTsmConnectionListener);
  }

  public void bind()
  {
    if (this.d == null)
      this.d = new ServiceConnection()
      {
        /** @deprecated */
        public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
        {
          monitorenter;
          try
          {
            UPTsmAddon.a(UPTsmAddon.this, true);
            UPTsmAddon.a(UPTsmAddon.this, ITsmService.Stub.asInterface(paramIBinder));
            UPTsmAddon.a(UPTsmAddon.this).sendEmptyMessage(0);
            monitorexit;
            return;
          }
          finally
          {
            localObject = finally;
            monitorexit;
          }
          throw localObject;
        }

        /** @deprecated */
        public final void onServiceDisconnected(ComponentName paramComponentName)
        {
          monitorenter;
          try
          {
            UPTsmAddon.a(UPTsmAddon.this, false);
            UPTsmAddon.a(UPTsmAddon.this, null);
            UPTsmAddon.a(UPTsmAddon.this).sendEmptyMessage(1);
            monitorexit;
            return;
          }
          finally
          {
            localObject = finally;
            monitorexit;
          }
          throw localObject;
        }
      };
    if (!this.f)
    {
      Intent localIntent = new Intent("com.unionpay.tsmservice.UPTsmService");
      localIntent.setPackage("com.unionpay.tsmservice.mi");
      this.c.bindService(localIntent, this.d, 1);
    }
  }

  /** @deprecated */
  public int clearEncryptData(int paramInt)
    throws RemoteException
  {
    monitorenter;
    int n;
    if ((paramInt < 2000) || (paramInt > 2001))
      n = -3;
    while (true)
    {
      monitorexit;
      return n;
      try
      {
        if (this.e == null)
          break label116;
        boolean bool = b(this.c.getPackageName());
        if (bool)
          try
          {
            int i2 = this.e.clearEncryptData(paramInt);
            n = i2;
            if (-2 != n)
              continue;
            n = a(4, null, paramInt, null, null);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            throw new RemoteException();
          }
      }
      finally
      {
        monitorexit;
      }
      int i1 = a(4, null, paramInt, null, null);
      n = i1;
      continue;
      label116: n = -1;
    }
  }

  /** @deprecated */
  public int encryptData(EncryptDataRequestParams paramEncryptDataRequestParams, ITsmCallback paramITsmCallback)
    throws RemoteException
  {
    int n = 0;
    monitorenter;
    int i1;
    if ((paramEncryptDataRequestParams == null) || (paramITsmCallback == null))
    {
      i1 = -3;
      monitorexit;
      return i1;
    }
    while (true)
    {
      try
      {
        while (true)
        {
          if (this.e == null)
            break label296;
          if (!b(this.c.getPackageName()))
            break label281;
          EncryptDataRequestParams localEncryptDataRequestParams = new EncryptDataRequestParams();
          String str1 = paramEncryptDataRequestParams.getReserve();
          ArrayList localArrayList1 = (ArrayList)paramEncryptDataRequestParams.getData();
          if (!TextUtils.isEmpty(str1))
            localEncryptDataRequestParams.setReserve(c(str1));
          if (localArrayList1 != null)
          {
            int i4 = localArrayList1.size();
            if (i4 == 0)
            {
              i1 = -3;
              break;
            }
            ArrayList localArrayList2 = new ArrayList();
            if (n < i4)
            {
              String str2 = (String)localArrayList1.get(n);
              if (TextUtils.isEmpty(str2))
                break label303;
              localArrayList2.add(c(str2));
              break label303;
            }
            localEncryptDataRequestParams.setData(localArrayList2);
          }
          try
          {
            int i2 = this.e.encryptData(localEncryptDataRequestParams, new b(1, this.k[1], 0));
            i1 = i2;
            if (-2 == i1)
              i1 = a(1, paramEncryptDataRequestParams, paramITsmCallback);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            throw new RemoteException();
          }
        }
      }
      finally
      {
        monitorexit;
      }
      if (i1 != 0)
        break;
      HashMap localHashMap = this.h;
      int[] arrayOfInt = this.k;
      int i3 = arrayOfInt[1];
      arrayOfInt[1] = (i3 + 1);
      localHashMap.put(String.valueOf(i3), paramITsmCallback);
      break;
      label281: int i5 = a(1, paramEncryptDataRequestParams, paramITsmCallback);
      i1 = i5;
      break;
      label296: i1 = -1;
      break;
      label303: n++;
    }
  }

  public int exchangeKey(String paramString, String[] paramArrayOfString)
    throws RemoteException
  {
    int n = -3;
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return n;
      if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
        continue;
      if (this.e != null)
        try
        {
          int i1 = this.e.exchangeKey(paramString, paramArrayOfString);
          n = i1;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          throw new RemoteException();
        }
      n = -1;
    }
  }

  public Context getContext()
  {
    return this.c;
  }

  /** @deprecated */
  public int getEncryptData(GetEncryptDataRequestParams paramGetEncryptDataRequestParams, ITsmCallback paramITsmCallback)
    throws RemoteException
  {
    int n = -3;
    monitorenter;
    if (paramITsmCallback == null);
    while (true)
    {
      monitorexit;
      return n;
      try
      {
        int i1 = paramGetEncryptDataRequestParams.getType();
        String str1 = paramGetEncryptDataRequestParams.getPan();
        if ((i1 < 2000) || (i1 > 2001) || ((i1 == 2000) && (TextUtils.isEmpty(str1))))
          continue;
        if (this.e == null)
          break label299;
        if (b(this.c.getPackageName()))
        {
          GetEncryptDataRequestParams localGetEncryptDataRequestParams = new GetEncryptDataRequestParams();
          if (i1 == 2000)
            localGetEncryptDataRequestParams.setPan(c(str1));
          localGetEncryptDataRequestParams.setType(i1);
          String str2 = paramGetEncryptDataRequestParams.getReserve();
          if (!TextUtils.isEmpty(str2))
            localGetEncryptDataRequestParams.setReserve(c(str2));
          this.i.put(String.valueOf(this.k[2]), paramITsmCallback);
          try
          {
            ITsmService localITsmService = this.e;
            int[] arrayOfInt1 = this.k;
            int i2 = arrayOfInt1[2];
            arrayOfInt1[2] = (i2 + 1);
            int i3 = localITsmService.getEncryptData(localGetEncryptDataRequestParams, new b(2, i2, 0));
            n = i3;
            if (n != 0)
            {
              HashMap localHashMap = this.i;
              int[] arrayOfInt2 = this.k;
              int i4 = -1 + arrayOfInt2[2];
              arrayOfInt2[2] = i4;
              localHashMap.remove(String.valueOf(i4));
            }
            if (-2 != n)
              continue;
            n = a(2, paramGetEncryptDataRequestParams, paramITsmCallback);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            throw new RemoteException();
          }
        }
      }
      finally
      {
        monitorexit;
      }
      int i5 = a(2, paramGetEncryptDataRequestParams, paramITsmCallback);
      n = i5;
      continue;
      label299: n = -1;
    }
  }

  public int getListenerCount()
  {
    if (b != null);
    for (int n = b.size(); ; n = 0)
      return n;
  }

  public int getPubKey(int paramInt, String[] paramArrayOfString)
    throws RemoteException
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0));
    for (int n = -3; ; n = -1)
      while (true)
      {
        return n;
        if (this.e == null)
          break;
        try
        {
          int i1 = this.e.getPubKey(paramInt, paramArrayOfString);
          n = i1;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          throw new RemoteException();
        }
      }
  }

  /** @deprecated */
  public int hideKeyboard()
    throws RemoteException
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (this.e == null)
          break label97;
        boolean bool = b(this.c.getPackageName());
        if (bool)
          try
          {
            int i2 = this.e.hideKeyboard();
            n = i2;
            if (-2 != n)
              continue;
            int i3 = a(5, null, 0, null, null);
            n = i3;
            monitorexit;
            return n;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            throw new RemoteException();
          }
      }
      finally
      {
        monitorexit;
      }
      int i1 = a(5, null, 0, null, null);
      int n = i1;
      continue;
      label97: n = -1;
    }
  }

  /** @deprecated */
  public int init(InitRequestParams paramInitRequestParams, ITsmCallback paramITsmCallback)
    throws RemoteException
  {
    monitorenter;
    int n;
    if (paramITsmCallback == null)
      n = -3;
    while (true)
    {
      monitorexit;
      return n;
      try
      {
        if (this.e == null)
          break label263;
        if (b(this.c.getPackageName()))
        {
          InitRequestParams localInitRequestParams = new InitRequestParams();
          if (paramInitRequestParams != null)
          {
            String str1 = paramInitRequestParams.getReserve();
            String str2 = paramInitRequestParams.getSignature();
            if (!TextUtils.isEmpty(str1))
              localInitRequestParams.setReserve(c(str1));
            if (!TextUtils.isEmpty(str2))
              localInitRequestParams.setSignature(c(str2));
          }
          this.g.put(String.valueOf(this.k[0]), paramITsmCallback);
          try
          {
            ITsmService localITsmService = this.e;
            int[] arrayOfInt1 = this.k;
            int i1 = arrayOfInt1[0];
            arrayOfInt1[0] = (i1 + 1);
            int i2 = localITsmService.init(localInitRequestParams, new b(0, i1, 0));
            n = i2;
            if (n != 0)
            {
              HashMap localHashMap = this.g;
              int[] arrayOfInt2 = this.k;
              int i3 = -1 + arrayOfInt2[0];
              arrayOfInt2[0] = i3;
              localHashMap.remove(String.valueOf(i3));
            }
            if (-2 != n)
              continue;
            n = a(0, paramInitRequestParams, paramITsmCallback);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            throw new RemoteException();
          }
        }
      }
      finally
      {
        monitorexit;
      }
      int i4 = a(0, paramInitRequestParams, paramITsmCallback);
      n = i4;
      continue;
      label263: n = -1;
    }
  }

  public boolean isConnected()
  {
    return this.f;
  }

  public void removeConnectionListener(UPTsmConnectionListener paramUPTsmConnectionListener)
  {
    if (paramUPTsmConnectionListener != null)
      b.remove(paramUPTsmConnectionListener);
  }

  /** @deprecated */
  public int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams paramSafetyKeyboardRequestParams)
    throws RemoteException
  {
    monitorenter;
    int n;
    if (paramSafetyKeyboardRequestParams == null)
      n = -3;
    while (true)
    {
      monitorexit;
      return n;
      try
      {
        if (this.e == null)
          break label102;
        boolean bool = b(this.c.getPackageName());
        if (bool)
          try
          {
            int i2 = this.e.setSafetyKeyboardBitmap(paramSafetyKeyboardRequestParams);
            n = i2;
            if (-2 != n)
              continue;
            n = a(3, paramSafetyKeyboardRequestParams, null);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            throw new RemoteException();
          }
      }
      finally
      {
        monitorexit;
      }
      int i1 = a(3, paramSafetyKeyboardRequestParams, null);
      n = i1;
      continue;
      label102: n = -1;
    }
  }

  /** @deprecated */
  public int showSafetyKeyboard(SafetyKeyboardRequestParams paramSafetyKeyboardRequestParams, int paramInt, OnSafetyKeyboardCallback paramOnSafetyKeyboardCallback, Context paramContext)
    throws RemoteException
  {
    monitorenter;
    int n;
    if ((paramSafetyKeyboardRequestParams == null) || (paramInt < 2000) || (paramInt > 2001))
      n = -3;
    while (true)
    {
      monitorexit;
      return n;
      try
      {
        if (this.e == null)
          break label184;
        if (b(this.c.getPackageName()))
        {
          this.j.put(this.c.getPackageName(), new a(paramContext));
          try
          {
            int i2 = this.e.showSafetyKeyboard(paramSafetyKeyboardRequestParams, paramInt, paramOnSafetyKeyboardCallback, new a());
            n = i2;
            if (n != 0)
              this.j.remove(this.c.getPackageName());
            if (-2 != n)
              continue;
            n = a(1000, paramSafetyKeyboardRequestParams, paramInt, paramOnSafetyKeyboardCallback, paramContext);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            throw new RemoteException();
          }
        }
      }
      finally
      {
        monitorexit;
      }
      int i1 = a(1000, paramSafetyKeyboardRequestParams, paramInt, paramOnSafetyKeyboardCallback, paramContext);
      n = i1;
      continue;
      label184: n = -1;
    }
  }

  public void unbind()
  {
    if ((this.d != null) && (this.f))
    {
      this.c.unbindService(this.d);
      this.f = false;
    }
  }

  public class a extends ITsmActivityCallback.Stub
  {
    private int b = 1000;

    public a()
    {
    }

    public final void startActivity(String paramString1, String paramString2, int paramInt, Bundle paramBundle)
      throws RemoteException
    {
      UPTsmAddon.a((ITsmActivityCallback)UPTsmAddon.b(UPTsmAddon.this, this.b).get(UPTsmAddon.c(UPTsmAddon.this)), paramString1, paramString2, paramInt, paramBundle);
      UPTsmAddon.b(UPTsmAddon.this, this.b).remove(UPTsmAddon.c(UPTsmAddon.this));
    }
  }

  private class b extends ITsmCallback.Stub
  {
    private int b;
    private int c;

    private b(int paramInt1, int arg3)
    {
      this.b = paramInt1;
      int i;
      this.c = i;
    }

    public final void onError(String paramString1, String paramString2)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("errorCode", paramString1);
      localBundle.putString("errorDesc", paramString2);
      UPTsmAddon.a((ITsmCallback)UPTsmAddon.a(UPTsmAddon.this, this.b).get(String.valueOf(this.c)), localBundle);
      UPTsmAddon.a(UPTsmAddon.this, this.b).remove(String.valueOf(this.c));
      if (UPTsmAddon.a(UPTsmAddon.this, this.b).isEmpty())
        UPTsmAddon.b(UPTsmAddon.this)[this.b] = 0;
    }

    public final void onResult(Bundle paramBundle)
      throws RemoteException
    {
      new Bundle();
      int i = this.b;
      Bundle localBundle = new Bundle();
      Parcel localParcel = Parcel.obtain();
      String str1 = paramBundle.getString("errorCode");
      String str2 = paramBundle.getString("result");
      localBundle.putString("errorCode", str1);
      if (!TextUtils.isEmpty(str2))
      {
        byte[] arrayOfByte = Base64.decode(UPTsmAddon.a(str2), 0);
        if ((arrayOfByte != null) && (arrayOfByte.length != 0))
        {
          localParcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
          localParcel.setDataPosition(0);
        }
        if (localParcel.dataSize() == 0)
        {
          localBundle.putString("errorCode", "010035");
          paramBundle = localBundle;
        }
      }
      while (true)
      {
        localParcel.recycle();
        UPTsmAddon.a((ITsmCallback)UPTsmAddon.a(UPTsmAddon.this, this.b).get(String.valueOf(this.c)), paramBundle);
        UPTsmAddon.a(UPTsmAddon.this, this.b).remove(String.valueOf(this.c));
        if (UPTsmAddon.a(UPTsmAddon.this, this.b).isEmpty())
          UPTsmAddon.b(UPTsmAddon.this)[this.b] = 0;
        return;
        switch (i)
        {
        default:
          break;
        case 0:
          localBundle.putParcelable("result", (InitResult)localParcel.readParcelable(InitResult.class.getClassLoader()));
          paramBundle = localBundle;
          break;
        case 1:
          localBundle.putParcelable("result", (EncryptDataResult)localParcel.readParcelable(EncryptDataResult.class.getClassLoader()));
          paramBundle = localBundle;
          break;
        case 2:
          localBundle.putParcelable("result", (GetEncryptDataResult)localParcel.readParcelable(GetEncryptDataResult.class.getClassLoader()));
          paramBundle = localBundle;
        }
      }
    }
  }

  public static abstract interface UPTsmConnectionListener
  {
    public abstract void onTsmConnected();

    public abstract void onTsmDisconnected();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.UPTsmAddon
 * JD-Core Version:    0.6.0
 */