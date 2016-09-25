package com.miui.tsmclient;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.eventbus.CardInfosEvent;
import com.miui.tsmclient.model.bankcard.BankCardManager;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;

public class UpdateCardsService extends IntentService
{
  public static final String ACTION_QUERY_CARDS = "com.miui.tsmclient.action.QUERY_CARDS";
  private static final String TAG = "UpdateCardsService";

  public UpdateCardsService()
  {
    super("UpdateCardsService");
  }

  public static void queryCardInfos(Context paramContext)
  {
    Intent localIntent = new Intent("com.miui.tsmclient.action.QUERY_CARDS");
    localIntent.setPackage(paramContext.getPackageName());
    if (paramContext != null)
      paramContext.startService(localIntent);
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    LogUtils.d("UpdateCardsService onHandleIntent: update cardInfos and refresh cache");
    new BankCardManager(getApplicationContext()).updateCardInfo(getApplicationContext(), null, new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        LogUtils.e("UpdateCardService: update cardInfos failed, errorMsg: " + paramString + ", errorCode: " + paramInt);
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        LogUtils.d("UpdateCardService resultCode: " + paramInt);
        if ((paramArrayOfObject[0] instanceof ArrayList))
        {
          ArrayList localArrayList1 = (ArrayList)paramArrayOfObject[0];
          ArrayList localArrayList2 = new ArrayList();
          for (int i = 0; i < localArrayList1.size(); i++)
          {
            localArrayList2.add((CardInfo)localArrayList1.get(i));
            LogUtils.d("UpdateCardService cardInfo: " + ((CardInfo)localArrayList2.get(i)).mCardName);
          }
          LogUtils.d("UpdateCardService cardInfosNum: " + localArrayList2.size());
          EventBus.getDefault().post(new CardInfosEvent(localArrayList2));
        }
      }
    });
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.UpdateCardsService
 * JD-Core Version:    0.6.0
 */