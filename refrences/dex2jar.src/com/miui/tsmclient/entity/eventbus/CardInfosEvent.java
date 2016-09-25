package com.miui.tsmclient.entity.eventbus;

import com.miui.tsmclient.entity.CardInfo;
import java.util.ArrayList;

public class CardInfosEvent
{
  private ArrayList<CardInfo> cardInfos;

  public CardInfosEvent(ArrayList<CardInfo> paramArrayList)
  {
    this.cardInfos = paramArrayList;
  }

  public ArrayList<CardInfo> getCardInfos()
  {
    return this.cardInfos;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.entity.eventbus.CardInfosEvent
 * JD-Core Version:    0.6.0
 */