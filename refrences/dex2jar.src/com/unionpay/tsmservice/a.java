package com.unionpay.tsmservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;

public final class a extends ITsmActivityCallback.Stub
{
  private Context a;

  public a(Context paramContext)
  {
    this.a = paramContext;
  }

  public final void startActivity(String paramString1, String paramString2, int paramInt, Bundle paramBundle)
    throws RemoteException
  {
    ComponentName localComponentName = new ComponentName(paramString1, paramString2);
    Intent localIntent = new Intent();
    localIntent.putExtras(paramBundle);
    if (paramInt != -1)
      localIntent.setFlags(paramInt);
    localIntent.setComponent(localComponentName);
    this.a.startActivity(localIntent);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.unionpay.tsmservice.a
 * JD-Core Version:    0.6.0
 */