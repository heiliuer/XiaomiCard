package com.miui.tsmclient.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.miui.tsmclient.database.CardDataUtil;
import com.miui.tsmclient.entity.CardGroupType;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.CardExecutor;
import com.miui.tsmclient.model.CardManager;
import com.miui.tsmclient.model.ICardOperation;
import com.miui.tsmclient.net.TSMAuthManager;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.UiUtils;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class BaseTransCardFragment<T extends CardInfo> extends BaseCardFragment<T>
{
  protected CardManager mCardManager;
  private BaseTransCardFragment<T>.GetDefaultCardTask mGetDefaultCardTask;
  protected int mGroupId = CardGroupType.TRANSCARD.getId();
  protected Subscription mSubscription;
  protected TSMAuthManager mTSMAuthManager;

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mTSMAuthManager = new TSMAuthManager();
    this.mCardManager = new CardManager(getActivity().getApplicationContext());
    if (getArguments() != null)
      this.mGroupId = getArguments().getInt("card_group_type", CardGroupType.TRANSCARD.getId());
  }

  protected void fetchCardProduct(IFetchCardProductListener paramIFetchCardProductListener, String paramString)
  {
    fetchCardProduct(paramIFetchCardProductListener, true, true, paramString);
  }

  protected void fetchCardProduct(IFetchCardProductListener paramIFetchCardProductListener, boolean paramBoolean1, boolean paramBoolean2, String paramString)
  {
    LogUtils.d("fetchCardProduct");
    if (this.mSubscription != null)
      this.mSubscription.unsubscribe();
    this.mCardInfoList = new ArrayList();
    this.mSubscription = Observable.concat(newGetCardsFromDB(paramBoolean1), newGetCardsFromNetwork(paramBoolean2), newGetCardsFromSE(paramString)).doOnNext(new Action1(paramIFetchCardProductListener)
    {
      public void call(CardInfo paramCardInfo)
      {
        if (!UiUtils.isFragmentValid(BaseTransCardFragment.this));
        label161: 
        while (true)
        {
          return;
          LogUtils.d("got card " + paramCardInfo.mCardName + ", status:" + paramCardInfo.mStatus + ", pid:" + Thread.currentThread().getId());
          if (BaseTransCardFragment.this.mCardInfoList.contains(paramCardInfo))
          {
            int i = BaseTransCardFragment.this.mCardInfoList.indexOf(paramCardInfo);
            paramCardInfo.updateInfo((CardInfo)BaseTransCardFragment.this.mCardInfoList.remove(i));
            BaseTransCardFragment.this.mCardInfoList.add(i, paramCardInfo);
          }
          while (true)
          {
            if (this.val$listener == null)
              break label161;
            this.val$listener.onGotCardInfoOnWorkerThread(paramCardInfo);
            break;
            BaseTransCardFragment.this.mCardInfoList.add(paramCardInfo);
          }
        }
      }
    }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber(paramIFetchCardProductListener)
    {
      public void onCompleted()
      {
        LogUtils.d("done loading all data");
        if ((this.val$listener == null) || (!UiUtils.isFragmentValid(BaseTransCardFragment.this)));
        while (true)
        {
          return;
          this.val$listener.onGotCardInfoCompleteOnMainThread();
        }
      }

      public void onError(Throwable paramThrowable)
      {
        LogUtils.e("fetchCardProduct error:" + paramThrowable.getMessage());
        if ((this.val$listener == null) || (!UiUtils.isFragmentValid(BaseTransCardFragment.this)));
        while (true)
        {
          return;
          this.val$listener.onGotCardInfoErrorOnMainThread();
        }
      }

      public void onNext(CardInfo paramCardInfo)
      {
        if ((this.val$listener == null) || (!UiUtils.isFragmentValid(BaseTransCardFragment.this)));
        while (true)
        {
          return;
          this.val$listener.onGotCardInfoOnMainThread(paramCardInfo);
        }
      }
    });
  }

  protected CardInfo findOrCreateCardInfo(String paramString)
  {
    Object localObject = null;
    if (this.mCardInfoList != null)
    {
      Iterator localIterator = this.mCardInfoList.iterator();
      while (localIterator.hasNext())
      {
        CardInfo localCardInfo = (CardInfo)localIterator.next();
        if (!localCardInfo.mCardType.equals(paramString))
          continue;
        localObject = localCardInfo;
      }
    }
    if (localObject == null)
      localObject = CardInfoFactory.makeCardInfo(paramString, null);
    return (CardInfo)localObject;
  }

  protected String getCardOperationTag()
  {
    return getClass().getSimpleName();
  }

  protected List<CardInfo> getCardsFromDB()
  {
    List localList = CardDataUtil.loadCardList(getActivity(), null);
    if (localList == null)
      localList = Collections.emptyList();
    return localList;
  }

  protected List<CardInfo> getCardsFromNetwork()
  {
    ArrayList localArrayList = new ArrayList();
    JSONArray localJSONArray1 = null;
    while (true)
    {
      int i;
      try
      {
        JSONArray localJSONArray2 = this.mTSMAuthManager.queryCardProduct(getActivity(), CardGroupType.newInstance(this.mGroupId));
        if (localJSONArray2 != null)
        {
          HashMap localHashMap = new HashMap();
          i = 0;
          if (i < localJSONArray2.length())
          {
            JSONObject localJSONObject = localJSONArray2.getJSONObject(i);
            CardInfo localCardInfo = CardInfoFactory.makeCardInfo(localJSONObject.optString("cardName"), null);
            localCardInfo.parse(localJSONObject);
            localArrayList.add(localCardInfo);
            if (!(localCardInfo instanceof PayableCardInfo))
              break label187;
            if (localJSONArray1 != null)
              continue;
            localJSONArray1 = queryByUserId(localHashMap);
            PayableCardInfo localPayableCardInfo = (PayableCardInfo)localCardInfo;
            if (!localHashMap.containsKey(localCardInfo.mCardType))
              break label187;
            localPayableCardInfo.mUnfinishOrderInfos = ((List)localHashMap.get(localCardInfo.mCardType));
          }
        }
      }
      catch (IOException localIOException)
      {
        LogUtils.e("failed to get cards on network!", localIOException);
      }
      catch (JSONException localJSONException)
      {
        LogUtils.e("failed to get cards on network!", localJSONException);
      }
      return localArrayList;
      label187: i++;
    }
  }

  protected void getDefaultCard()
  {
    if (this.mGetDefaultCardTask != null)
      this.mGetDefaultCardTask.cancel(true);
    this.mGetDefaultCardTask = new GetDefaultCardTask(null);
    this.mGetDefaultCardTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
  }

  protected boolean isCardInfoSanity(T paramT)
  {
    return true;
  }

  protected Observable<CardInfo> newGetCardsFromDB(boolean paramBoolean)
  {
    if (!paramBoolean);
    for (Observable localObservable = Observable.empty(); ; localObservable = Observable.defer(new Func0()
    {
      public Observable<CardInfo> call()
      {
        try
        {
          Observable localObservable2 = Observable.from(BaseTransCardFragment.this.getCardsFromDB());
          localObservable1 = localObservable2;
          return localObservable1;
        }
        catch (Exception localException)
        {
          while (true)
          {
            LogUtils.e("newGetCardsFromDB failed", localException);
            Observable localObservable1 = Observable.error(localException);
          }
        }
      }
    }))
    {
      return localObservable;
      LogUtils.d("newGetCardsFromDB");
    }
  }

  protected Observable<CardInfo> newGetCardsFromNetwork(boolean paramBoolean)
  {
    if (!paramBoolean);
    for (Observable localObservable = Observable.empty(); ; localObservable = Observable.defer(new Func0()
    {
      public Observable<CardInfo> call()
      {
        try
        {
          Observable localObservable2 = Observable.from(BaseTransCardFragment.this.getCardsFromNetwork());
          localObservable1 = localObservable2;
          return localObservable1;
        }
        catch (Exception localException)
        {
          while (true)
          {
            LogUtils.e("newGetCardsFromNetwork failed", localException);
            Observable localObservable1 = Observable.error(localException);
          }
        }
      }
    }))
    {
      return localObservable;
      LogUtils.d("newGetCardsFromNetwork");
    }
  }

  protected Observable<CardInfo> newGetCardsFromSE(String paramString)
  {
    LogUtils.d("newGetCardsFromSE");
    return Observable.defer(new Func0(paramString)
    {
      public Observable<CardInfo> call()
      {
        try
        {
          Observable localObservable2 = Observable.from(SysUtils.getSupportedTransCardAids()).filter(new Func1()
          {
            public Boolean call(ByteArray paramByteArray)
            {
              if (TextUtils.isEmpty(BaseTransCardFragment.6.this.val$cardType));
              for (boolean bool = true; ; bool = TextUtils.equals(BaseTransCardFragment.6.this.val$cardType, (CharSequence)((Pair)SysUtils.getSupportedTransCardMap().get(Coder.bytesToHexString(paramByteArray.toBytes()))).second))
                return Boolean.valueOf(bool);
            }
          }).map(new Func1()
          {
            public CardInfo call(ByteArray paramByteArray)
            {
              String str = (String)((Pair)SysUtils.getSupportedTransCardMap().get(Coder.bytesToHexString(paramByteArray.toBytes()))).second;
              CardInfo localCardInfo = BaseTransCardFragment.this.findOrCreateCardInfo(str);
              if (localCardInfo != null)
              {
                BaseResponse localBaseResponse = CardExecutor.getInstance().createCardOperation(BaseTransCardFragment.this.getCardOperationTag(), str).updateCardInfo(BaseTransCardFragment.this.getActivity(), localCardInfo);
                LogUtils.d("after read ese, got card " + localCardInfo.mCardName + ", status:" + localCardInfo.mStatus + ", pid:" + Thread.currentThread().getId() + ", response:" + localBaseResponse.mResultCode);
                CardDataUtil.saveCardInfo(BaseTransCardFragment.this.getActivity(), localCardInfo);
                BaseTransCardFragment.this.processCardInfoUpdateError(localCardInfo, localBaseResponse.mResultCode, localBaseResponse.mMsg);
              }
              return localCardInfo;
            }
          }).filter(new Func1()
          {
            public Boolean call(CardInfo paramCardInfo)
            {
              if ((paramCardInfo != null) && (paramCardInfo.mStatus != null));
              for (boolean bool = true; ; bool = false)
                return Boolean.valueOf(bool);
            }
          });
          localObservable1 = localObservable2;
          return localObservable1;
        }
        catch (Exception localException)
        {
          while (true)
          {
            LogUtils.e("newGetCardsFromNetwork failed", localException);
            Observable localObservable1 = Observable.error(localException);
          }
        }
      }
    });
  }

  public void onDestroy()
  {
    if (this.mGetDefaultCardTask != null)
      this.mGetDefaultCardTask.cancel(true);
    if (this.mCardManager != null)
      this.mCardManager.release();
    super.onDestroy();
  }

  public void onDestroyView()
  {
    if (this.mSubscription != null)
      this.mSubscription.unsubscribe();
    super.onDestroyView();
  }

  protected void onGotCardProduct(boolean paramBoolean)
  {
  }

  protected void onGotDefaultCardAId(String paramString)
  {
  }

  protected void processCardInfoUpdateError(CardInfo paramCardInfo, int paramInt, String paramString)
  {
    if (paramInt == 1003)
      new AlertDialog.Builder(getActivity()).setTitle(2131296314).setMessage(paramCardInfo.mCardName + getString(2131296315)).setPositiveButton(2131296273, new DialogInterface.OnClickListener(paramCardInfo)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseTransCardFragment.this.fetchCardProduct(null, false, false, this.val$cardInfo.mCardType);
        }
      }).setNegativeButton(17039360, null).create().show();
    while (true)
    {
      return;
      if (paramInt == 1004)
      {
        new AlertDialog.Builder(getActivity()).setTitle(2131296316).setMessage(2131296317).setPositiveButton(2131296318, null).create().show();
        continue;
      }
      if (paramInt != 1010)
        continue;
      queryCardProduct();
    }
  }

  protected JSONArray queryByUserId(Map<String, List<OrderInfo>> paramMap)
    throws JSONException
  {
    Object localObject1 = null;
    try
    {
      JSONArray localJSONArray = this.mTSMAuthManager.queryByUserId(getActivity());
      localObject1 = localJSONArray;
      if (localObject1 == null)
      {
        localObject3 = localObject1;
        return localObject3;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        LogUtils.e("queryByUserId failed!", localIOException);
        for (int i = 0; i < localObject1.length(); i++)
        {
          OrderInfo localOrderInfo = new OrderInfo();
          localOrderInfo.parse(localObject1.getJSONObject(i));
          Object localObject2 = (List)paramMap.get(localOrderInfo.mCardType);
          if (localObject2 == null)
          {
            localObject2 = new CopyOnWriteArrayList();
            paramMap.put(localOrderInfo.mCardType, localObject2);
          }
          ((List)localObject2).add(localOrderInfo);
        }
        Object localObject3 = localObject1;
      }
    }
  }

  protected void queryCardProduct()
  {
    showDialog(2131296274);
    fetchCardProduct(new SimpleFetchCardProductListener()
    {
      public void onGotCardInfoCompleteOnMainThread()
      {
        BaseTransCardFragment.this.dismissDialog();
      }

      public void onGotCardInfoErrorOnMainThread()
      {
        BaseTransCardFragment.this.dismissDialog();
      }

      public void onGotCardInfoOnMainThread(CardInfo paramCardInfo)
      {
        if (TextUtils.equals(paramCardInfo.mCardType, BaseTransCardFragment.this.mCardInfo.mCardType))
        {
          BaseTransCardFragment.this.dismissDialog();
          BaseTransCardFragment.this.mCardInfo = paramCardInfo;
          BaseTransCardFragment.this.onGotCardProduct(BaseTransCardFragment.this.isCardInfoSanity(BaseTransCardFragment.this.mCardInfo));
        }
      }
    }
    , false, true, this.mCardInfo.mCardType);
  }

  private class GetDefaultCardTask extends AsyncTask<Void, Void, String>
  {
    private GetDefaultCardTask()
    {
    }

    protected String doInBackground(Void[] paramArrayOfVoid)
    {
      if (BaseTransCardFragment.this.mGroupId == CardGroupType.BANKCARD.getId());
      for (String str = SysUtils.getDefaultBankCard(BaseTransCardFragment.this.getActivity()); ; str = SysUtils.getDefaultTransCard(BaseTransCardFragment.this.getActivity()))
        return str;
    }

    protected void onPostExecute(String paramString)
    {
      BaseTransCardFragment.this.mDefaultCardAId = paramString;
      if (UiUtils.isFragmentValid(BaseTransCardFragment.this))
        BaseTransCardFragment.this.onGotDefaultCardAId(BaseTransCardFragment.this.mDefaultCardAId);
    }
  }

  public static class SimpleFetchCardProductListener
    implements BaseTransCardFragment.IFetchCardProductListener
  {
    public void onGotCardInfoCompleteOnMainThread()
    {
    }

    public void onGotCardInfoErrorOnMainThread()
    {
    }

    public void onGotCardInfoOnMainThread(CardInfo paramCardInfo)
    {
    }

    public void onGotCardInfoOnWorkerThread(CardInfo paramCardInfo)
    {
    }
  }

  public static abstract interface IFetchCardProductListener
  {
    public abstract void onGotCardInfoCompleteOnMainThread();

    public abstract void onGotCardInfoErrorOnMainThread();

    public abstract void onGotCardInfoOnMainThread(CardInfo paramCardInfo);

    public abstract void onGotCardInfoOnWorkerThread(CardInfo paramCardInfo);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.BaseTransCardFragment
 * JD-Core Version:    0.6.0
 */