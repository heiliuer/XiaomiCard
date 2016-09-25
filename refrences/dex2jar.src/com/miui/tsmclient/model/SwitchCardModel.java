package com.miui.tsmclient.model;

import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.SwitchPageData;
import com.miui.tsmclient.task.GetCardsFromDbTask;
import com.miui.tsmclient.task.GetCardsFromDbTask.Result;
import com.miui.tsmclient.task.QueryCardInfoTask;
import com.miui.tsmclient.task.QueryCardInfoTask.Result;
import com.miui.tsmclient.task.RxBaseTaskListener;
import com.miui.tsmclient.util.PrefUtils;
import java.util.Iterator;
import java.util.List;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SwitchCardModel extends BaseModel
{
  public static final int DATA_ID_GET_CARD_DATA_FROM_DB = 1;
  public static final int DATA_ID_QUERY_CARD_INFO = 2;
  public static final int ERROR_NO_CACHE = 1;
  public static final int ERROR_QUERY_ERROR = 2;

  public void downloadCardData()
  {
    Observable.create(new QueryCardInfoTask(getContext())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new RxBaseTaskListener()
    {
      protected void handleResult(QueryCardInfoTask.Result paramResult)
      {
        super.handleResult(paramResult);
        Bundle localBundle = new Bundle();
        if ((paramResult.mBankCardInfos == null) || (paramResult.mTransCardInfos == null))
        {
          localBundle.putInt("error_code", 2);
          SwitchCardModel.this.notifyChanged(2147483647, localBundle);
        }
        while (true)
        {
          return;
          SwitchCardModel.this.notifyChanged(2);
        }
      }
    });
  }

  public void fetchCardStackData()
  {
    Observable.create(new GetCardsFromDbTask(getContext())).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new RxBaseTaskListener()
    {
      protected void handleResult(GetCardsFromDbTask.Result paramResult)
      {
        super.handleResult(paramResult);
        Bundle localBundle = new Bundle();
        if ((paramResult.mBankCards.isEmpty()) && (paramResult.mTransCards.isEmpty()))
        {
          localBundle.putInt("error_code", 1);
          SwitchCardModel.this.notifyChanged(2147483647, localBundle);
        }
        while (true)
        {
          return;
          Object localObject = null;
          String str1 = PrefUtils.getDefaultCard(SwitchCardModel.this.getContext(), true);
          SwitchPageData localSwitchPageData = new SwitchPageData();
          String str2 = PrefUtils.getString(SwitchCardModel.this.getContext(), "key_last_card", null);
          Iterator localIterator1 = paramResult.mTransCards.iterator();
          while (localIterator1.hasNext())
          {
            CardInfo localCardInfo2 = (CardInfo)localIterator1.next();
            if (TextUtils.equals(localCardInfo2.mAid, str2))
              localSwitchPageData.mLastUsedCard = localCardInfo2;
            if (!TextUtils.equals(localCardInfo2.mAid, str1))
              continue;
            localObject = localCardInfo2;
          }
          Iterator localIterator2 = paramResult.mBankCards.iterator();
          while (localIterator2.hasNext())
          {
            CardInfo localCardInfo1 = (CardInfo)localIterator2.next();
            if (!TextUtils.equals(localCardInfo1.mAid, str2))
              continue;
            localSwitchPageData.mLastUsedCard = localCardInfo1;
          }
          localSwitchPageData.mBankCardInfos = paramResult.mBankCards;
          localSwitchPageData.mDefaultTransCard = localObject;
          localBundle.putParcelable("switch_page_data", localSwitchPageData);
          SwitchCardModel.this.notifyChanged(1, localBundle);
        }
      }
    });
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.SwitchCardModel
 * JD-Core Version:    0.6.0
 */