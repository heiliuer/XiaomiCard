package com.miui.tsmclient.entity;

import android.text.TextUtils;

public class CardDetailResInfo
{
  public int mCardDetailColorRes;

  public static CardDetailResInfo newInstance(String paramString)
  {
    CardDetailResInfo localCardDetailResInfo = new CardDetailResInfo();
    if (!TextUtils.equals(paramString, "BANKCARD"))
      localCardDetailResInfo.mCardDetailColorRes = 2131165256;
    return localCardDetailResInfo;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.CardDetailResInfo
 * JD-Core Version:    0.6.0
 */