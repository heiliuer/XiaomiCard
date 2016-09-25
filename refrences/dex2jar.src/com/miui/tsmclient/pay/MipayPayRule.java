package com.miui.tsmclient.pay;

public class MipayPayRule
  implements IPayRule
{
  public int checkPayAmount(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 < getMinPayAmount())
      i = 1006;
    while (true)
    {
      return i;
      if (paramInt1 > getMaxPayAmount())
      {
        i = 1007;
        continue;
      }
      if (paramInt1 + paramInt2 > getMaxTotalAmount())
      {
        i = 1008;
        continue;
      }
      if (paramInt1 + paramInt2 < getMinBalance())
      {
        i = 1013;
        continue;
      }
      i = 0;
    }
  }

  public int getMaxPayAmount()
  {
    return 100000;
  }

  public int getMaxTotalAmount()
  {
    return 100000;
  }

  public int getMinBalance()
  {
    return 0;
  }

  public int getMinPayAmount()
  {
    return 0;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.pay.MipayPayRule
 * JD-Core Version:    0.6.0
 */