package com.miui.tsmclient.presenter;

import com.miui.tsmclient.entity.SwitchPageData;

public abstract interface SwitchCardContract
{
  public static abstract interface Presenter
  {
    public abstract void downloadCardData();

    public abstract void loadCardDataFromCache();
  }

  public static abstract interface View extends IView
  {
    public abstract void updateCardStack(SwitchPageData paramSwitchPageData);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.presenter.SwitchCardContract
 * JD-Core Version:    0.6.0
 */