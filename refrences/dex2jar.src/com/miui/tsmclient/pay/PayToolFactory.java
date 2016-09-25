package com.miui.tsmclient.pay;

public class PayToolFactory
{
  public static IPayTool getPayTool(PayType paramPayType)
  {
    switch (1.$SwitchMap$com$miui$tsmclient$pay$PayType[paramPayType.ordinal()])
    {
    default:
    case 1:
    }
    for (MiPayTool localMiPayTool = new MiPayTool(); ; localMiPayTool = new MiPayTool())
      return localMiPayTool;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.pay.PayToolFactory
 * JD-Core Version:    0.6.0
 */