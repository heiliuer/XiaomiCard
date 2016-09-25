package com.miui.tsmclient.presenter;

import android.os.Bundle;
import com.miui.tsmclient.entity.SwitchPageData;
import com.miui.tsmclient.model.SwitchCardModel;
import com.miui.tsmclient.util.PrefUtils;

public class SwitchCardPresenter extends BasePresenter<SwitchCardContract.View>
  implements SwitchCardContract.Presenter
{
  private SwitchCardModel mModel;
  private SwitchPageData mSwitchPageData;

  public void changeLastUsedCard(String paramString)
  {
    PrefUtils.putString(getContext(), "key_last_card", paramString);
  }

  public void downloadCardData()
  {
    this.mModel.downloadCardData();
  }

  public SwitchPageData getSwitchPageData()
  {
    return this.mSwitchPageData;
  }

  public void loadCardDataFromCache()
  {
    this.mModel.fetchCardStackData();
  }

  protected void onChildModelChanged(int paramInt, Bundle paramBundle)
  {
    super.onChildModelChanged(paramInt, paramBundle);
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      ((SwitchCardContract.View)getView()).updateCardStack((SwitchPageData)paramBundle.getParcelable("switch_page_data"));
      continue;
      loadCardDataFromCache();
    }
  }

  protected void onInit()
  {
    super.onInit();
    this.mModel = new SwitchCardModel();
    subscribe(this.mModel);
    loadCardDataFromCache();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.presenter.SwitchCardPresenter
 * JD-Core Version:    0.6.0
 */