package com.miui.tsmclient.entity;

public enum CardGroupType
{
  private int mDesRes;
  private int mId;

  static
  {
    BANKCARD = new CardGroupType("BANKCARD", 1, 2, 2131296267);
    CardGroupType[] arrayOfCardGroupType = new CardGroupType[2];
    arrayOfCardGroupType[0] = TRANSCARD;
    arrayOfCardGroupType[1] = BANKCARD;
    $VALUES = arrayOfCardGroupType;
  }

  private CardGroupType(int paramInt1, int paramInt2)
  {
    this.mId = paramInt1;
    this.mDesRes = paramInt2;
  }

  public static CardGroupType newInstance(int paramInt)
  {
    CardGroupType[] arrayOfCardGroupType = values();
    int i = arrayOfCardGroupType.length;
    int j = 0;
    CardGroupType localCardGroupType;
    if (j < i)
    {
      localCardGroupType = arrayOfCardGroupType[j];
      if (localCardGroupType.mId != paramInt);
    }
    while (true)
    {
      return localCardGroupType;
      j++;
      break;
      localCardGroupType = null;
    }
  }

  public int getDesRes()
  {
    return this.mDesRes;
  }

  public int getId()
  {
    return this.mId;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.CardGroupType
 * JD-Core Version:    0.6.0
 */