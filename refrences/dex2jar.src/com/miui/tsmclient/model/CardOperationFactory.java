package com.miui.tsmclient.model;

import android.text.TextUtils;
import com.miui.tsmclient.model.bankcard.BankCardOperation;
import com.miui.tsmclient.model.fmsh.FmshCardOperation;

public class CardOperationFactory
{
  public static ICardOperation createCardOperation(String paramString)
  {
    Object localObject;
    if (TextUtils.equals(paramString, "SPTC"))
      localObject = new FmshCardOperation();
    while (true)
    {
      return localObject;
      if (TextUtils.equals(paramString, "BANKCARD"))
      {
        localObject = new BankCardOperation();
        continue;
      }
      localObject = new PayableCardOperation(paramString);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.CardOperationFactory
 * JD-Core Version:    0.6.0
 */