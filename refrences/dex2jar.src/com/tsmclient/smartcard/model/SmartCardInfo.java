package com.tsmclient.smartcard.model;

public class SmartCardInfo
{
  public static final int ISSUABLE = 1;
  public static final int UNISSUABLE;
  protected String mCardCode;
  protected String mCardId;
  protected String mCardLogoPath;
  protected String mCardName;
  protected int mIssuable = 0;

  public String getCardCode()
  {
    return this.mCardCode;
  }

  public String getCardId()
  {
    return this.mCardId;
  }

  public String getCardLogo()
  {
    return this.mCardLogoPath;
  }

  public String getCardName()
  {
    return this.mCardName;
  }

  public int getIssuable()
  {
    return this.mIssuable;
  }

  public void setCardCode(String paramString)
  {
    this.mCardCode = paramString;
  }

  public void setCardId(String paramString)
  {
    this.mCardId = paramString;
  }

  public void setCardLogo(String paramString)
  {
    this.mCardLogoPath = paramString;
  }

  public void setCardName(String paramString)
  {
    this.mCardName = paramString;
  }

  public void setIssuable(int paramInt)
  {
    this.mIssuable = paramInt;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.model.SmartCardInfo
 * JD-Core Version:    0.6.0
 */