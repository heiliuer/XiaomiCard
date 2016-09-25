package com.miui.tsmclient.pay;

public abstract interface IPayRule
{
  public abstract int checkPayAmount(int paramInt1, int paramInt2);

  public abstract int getMaxPayAmount();

  public abstract int getMaxTotalAmount();

  public abstract int getMinBalance();

  public abstract int getMinPayAmount();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.pay.IPayRule
 * JD-Core Version:    0.6.0
 */