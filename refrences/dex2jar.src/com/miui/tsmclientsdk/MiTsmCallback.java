package com.miui.tsmclientsdk;

public abstract interface MiTsmCallback
{
  public abstract void onFail(int paramInt, String paramString, Object[] paramArrayOfObject);

  public abstract void onSuccess(int paramInt, Object[] paramArrayOfObject);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclientsdk.MiTsmCallback
 * JD-Core Version:    0.6.0
 */