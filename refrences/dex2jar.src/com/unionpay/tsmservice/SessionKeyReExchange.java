package com.unionpay.tsmservice;

import android.content.Context;
import android.os.RemoteException;
import com.unionpay.tsmservice.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.RequestParams;
import com.unionpay.tsmservice.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.utils.IUPJniInterface;

public class SessionKeyReExchange
{
  private UPTsmAddon a;
  private int b = -1;
  private RequestParams c;
  private ITsmCallback d;
  private int e = 1000;
  private OnSafetyKeyboardCallback f;
  private Context g;
  private int h;

  public SessionKeyReExchange(UPTsmAddon paramUPTsmAddon, int paramInt, ITsmCallback paramITsmCallback)
  {
    this(paramUPTsmAddon, paramInt, null, paramITsmCallback);
  }

  public SessionKeyReExchange(UPTsmAddon paramUPTsmAddon, int paramInt, RequestParams paramRequestParams, ITsmCallback paramITsmCallback)
  {
    this(paramUPTsmAddon, paramInt, paramRequestParams, paramITsmCallback, 1000);
  }

  public SessionKeyReExchange(UPTsmAddon paramUPTsmAddon, int paramInt1, RequestParams paramRequestParams, ITsmCallback paramITsmCallback, int paramInt2)
  {
    this.a = paramUPTsmAddon;
    this.b = paramInt1;
    this.c = paramRequestParams;
    this.d = paramITsmCallback;
    this.e = paramInt2;
  }

  public SessionKeyReExchange(UPTsmAddon paramUPTsmAddon, int paramInt1, SafetyKeyboardRequestParams paramSafetyKeyboardRequestParams, int paramInt2, OnSafetyKeyboardCallback paramOnSafetyKeyboardCallback, Context paramContext)
  {
    this.a = paramUPTsmAddon;
    this.b = paramInt1;
    this.h = paramInt2;
    this.c = paramSafetyKeyboardRequestParams;
    this.f = paramOnSafetyKeyboardCallback;
    this.g = paramContext;
  }

  public int reExchangeKey()
    throws RemoteException
  {
    int i = 0;
    String[] arrayOfString = new String[1];
    int j = this.a.getPubKey(1000, arrayOfString);
    if (j != 0)
      i = j;
    while (true)
    {
      return i;
      String str1 = IUPJniInterface.rER(arrayOfString[0], IUPJniInterface.mSK());
      int k = this.a.exchangeKey(str1, arrayOfString);
      if (k != 0)
      {
        i = k;
        continue;
      }
      String str2 = IUPJniInterface.dMG(arrayOfString[0]);
      IUPJniInterface.sSK(str2);
      Context localContext = this.a.getContext();
      if (localContext != null)
        IUPJniInterface.uSKT(localContext.getPackageName(), str2);
      switch (this.b)
      {
      default:
        break;
      case 0:
        InitRequestParams localInitRequestParams = (InitRequestParams)this.c;
        i = this.a.init(localInitRequestParams, this.d);
        break;
      case 1:
        EncryptDataRequestParams localEncryptDataRequestParams = (EncryptDataRequestParams)this.c;
        i = this.a.encryptData(localEncryptDataRequestParams, this.d);
        break;
      case 3:
        SafetyKeyboardRequestParams localSafetyKeyboardRequestParams2 = (SafetyKeyboardRequestParams)this.c;
        i = this.a.setSafetyKeyboardBitmap(localSafetyKeyboardRequestParams2);
        break;
      case 1000:
        SafetyKeyboardRequestParams localSafetyKeyboardRequestParams1 = (SafetyKeyboardRequestParams)this.c;
        i = this.a.showSafetyKeyboard(localSafetyKeyboardRequestParams1, this.h, this.f, this.g);
        break;
      case 4:
        i = this.a.clearEncryptData(this.h);
        break;
      case 2:
        GetEncryptDataRequestParams localGetEncryptDataRequestParams = (GetEncryptDataRequestParams)this.c;
        i = this.a.getEncryptData(localGetEncryptDataRequestParams, this.d);
        break;
      case 5:
        i = this.a.hideKeyboard();
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.SessionKeyReExchange
 * JD-Core Version:    0.6.0
 */