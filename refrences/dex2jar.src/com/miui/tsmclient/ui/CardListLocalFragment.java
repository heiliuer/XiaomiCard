package com.miui.tsmclient.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.miui.tsmclient.SEInteractionService.SEInteractionState;
import com.miui.tsmclient.entity.CardGroupType;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.model.CardManager;
import com.miui.tsmclient.net.TSMAuthManager;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.ui.widget.ImmersionMenu;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import miui.app.ActionBar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CardListLocalFragment extends BaseCardFragment
  implements AdapterView.OnItemClickListener
{
  public static final String ACTION_UPDATE_CARD_INFO = "com.xiaomi.tsmclient.action.UPDATE_CARD_INFO";
  public static final String ACTION_UPDATE_CARD_LIST = "com.xiaomi.tsmclient.action.UPDATE_CARD_LIST";
  public static final String EXTRA_CARD_LIST = "card_list";
  private static final int MSG_HANDLE_UNSOLVED_ORDER_FAILED = 2;
  private static final int MSG_HAS_CONFIRM_DOUBT_ORDER = 3;
  private static final int MSG_QUERY_ORDER = 4;
  private static final int MSG_REFRESH_DATA = 1;
  private static final int MSG_SET_DAFAULT_CARD = 5;
  private CardListLocalAdapter mAdapter;
  private List<CardInfo> mCardList;
  private CardManager mCardManager;
  private GetDefaultCardTask mGetDefaultCardTask;
  private int mGroupId = CardGroupType.TRANSCARD.getId();
  private boolean mHasShowDefaultCardRemind;
  private View mLayoutError;
  private ListView mListView;
  private QueryCardProductTask mQueryCardProductTask;
  private SetDefaultCardTask mSetDefaultCardTask;
  private TSMAuthManager mTSMAuthManager;
  private UpdateCardListReceiver mUpdateCardListReceiver;

  private void checkImmersionMenu(miui.app.Activity paramActivity)
  {
    ActionBar localActionBar = paramActivity.getActionBar();
    if (localActionBar != null)
    {
      ImmersionMenu localImmersionMenu = (ImmersionMenu)localActionBar.getCustomView();
      if (!localImmersionMenu.isEnable())
      {
        Iterator localIterator = this.mCardList.iterator();
        while (localIterator.hasNext())
        {
          if (!((CardInfo)localIterator.next()).mHasIssue)
            continue;
          paramActivity.setImmersionMenuEnabled(true);
          localImmersionMenu.setVisibility(0);
        }
      }
    }
  }

  private void getDefaultCard()
  {
    if (this.mGetDefaultCardTask != null)
      this.mGetDefaultCardTask.cancel(true);
    this.mGetDefaultCardTask = new GetDefaultCardTask(null);
    this.mGetDefaultCardTask.execute(new Void[0]);
  }

  private void initView(View paramView)
  {
    this.mAdapter = new CardListLocalAdapter(getActivity());
    this.mListView = ((ListView)paramView.findViewById(16908298));
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setOnItemClickListener(this);
    CardGroupType localCardGroupType = CardGroupType.newInstance(this.mGroupId);
    ActionBar localActionBar = (ActionBar)getActivity().getActionBar();
    if ((localCardGroupType != null) && (localActionBar != null))
      localActionBar.setTitle(localCardGroupType.getDesRes());
    this.mLayoutError = paramView.findViewById(2131492959);
  }

  private void loadCards(List<CardInfo> paramList)
  {
    if (SEInteractionService.SEInteractionState.getInstance().getCurrentAppAid() != null)
      loadCardsFromDB(paramList, false);
    while (true)
    {
      return;
      loadCardsFromDB(paramList, true);
    }
  }

  private void loadCardsFromDB(List<CardInfo> paramList, boolean paramBoolean)
  {
    this.mCardManager.loadCards(paramList, new MiTsmCallback(paramBoolean, paramList)
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        CardListLocalFragment.this.mHandler.obtainMessage(1, CardListLocalFragment.this.mCardList).sendToTarget();
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        List localList = (List)paramArrayOfObject[0];
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          CardInfo localCardInfo = (CardInfo)localIterator.next();
          if (this.val$needUpdate)
          {
            CardListLocalFragment.this.updateCardInfo(localCardInfo);
            continue;
          }
          Intent localIntent = new Intent("com.xiaomi.tsmclient.action.UPDATE_CARD_INFO");
          localIntent.putExtra("card_info", localCardInfo);
          LocalBroadcastManager.getInstance(CardListLocalFragment.this.getActivity()).sendBroadcast(localIntent);
        }
        if ((this.val$cardInfoList.size() == 1) && (!CardListLocalFragment.this.mCardList.isEmpty()))
          CardListLocalFragment.this.mHandler.obtainMessage(1, localList.toArray()[0]).sendToTarget();
        while (true)
        {
          return;
          CardListLocalFragment.this.mHandler.obtainMessage(1, localList).sendToTarget();
        }
      }
    });
  }

  private void queryServiceStatus(CardInfo paramCardInfo)
  {
    if (this.mQueryCardProductTask != null)
    {
      this.mQueryCardProductTask.cancel(true);
      this.mQueryCardProductTask = null;
    }
    this.mQueryCardProductTask = new QueryCardProductTask(paramCardInfo);
    this.mQueryCardProductTask.execute(new Void[0]);
  }

  private void refreshAll()
  {
    this.mAdapter.setData(this.mCardList);
  }

  private void refreshCard(CardInfo paramCardInfo)
  {
    int i = this.mAdapter.findDataPosition(paramCardInfo);
    if (i < 0)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramCardInfo.mCardName;
      LogUtils.d(String.format("cannot find cardinfo:%s on card list.", arrayOfObject));
    }
    while (true)
    {
      return;
      if (i < this.mCardList.size())
      {
        this.mCardList.remove(i);
        this.mCardList.add(i, paramCardInfo);
      }
      this.mAdapter.setData(this.mCardList);
    }
  }

  private void setDefaultCard(CardInfo paramCardInfo)
  {
    if (this.mSetDefaultCardTask != null)
      this.mSetDefaultCardTask.cancel(true);
    this.mSetDefaultCardTask = new SetDefaultCardTask(paramCardInfo);
    this.mSetDefaultCardTask.execute(new Void[0]);
  }

  private void updateCardInfo(CardInfo paramCardInfo)
  {
    this.mAdapter.startLoading(paramCardInfo.mCardType);
    this.mCardManager.updateCardInfo(getActivity(), paramCardInfo, new MiTsmCallback(paramCardInfo)
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        int i = 0;
        Iterator localIterator = CardListLocalFragment.this.mCardList.iterator();
        while (localIterator.hasNext())
        {
          if (!TextUtils.equals(((CardInfo)localIterator.next()).mCardType, this.val$cardInfo.mCardType))
            continue;
          i = 1;
        }
        if (i != 0)
        {
          CardListLocalFragment.this.mAdapter.stopLoading(this.val$cardInfo.mCardType);
          CardListLocalFragment.this.loadCardsFromDB(Collections.singletonList(this.val$cardInfo), false);
        }
        if (paramInt == 1003)
          CardListLocalFragment.this.mHandler.obtainMessage(2, this.val$cardInfo).sendToTarget();
        while (true)
        {
          return;
          if (paramInt == 1004)
          {
            CardListLocalFragment.this.mHandler.sendEmptyMessage(3);
            continue;
          }
          if (paramInt != 1010)
            continue;
          CardListLocalFragment.this.mHandler.obtainMessage(4, this.val$cardInfo).sendToTarget();
        }
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        CardListLocalFragment.this.loadCardsFromDB(Collections.singletonList(this.val$cardInfo), false);
        CardListLocalFragment.this.mAdapter.stopLoading(this.val$cardInfo.mCardType);
      }
    });
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mTSMAuthManager = new TSMAuthManager();
    this.mCardList = new CopyOnWriteArrayList();
    this.mCardManager = new CardManager(getActivity().getApplicationContext());
    this.mUpdateCardListReceiver = new UpdateCardListReceiver(null);
    LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(this.mUpdateCardListReceiver, new IntentFilter("com.xiaomi.tsmclient.action.UPDATE_CARD_LIST"));
    if (getArguments() != null)
      this.mGroupId = getArguments().getInt("card_group_type", CardGroupType.TRANSCARD.getId());
  }

  protected void handleMessage(Message paramMessage, miui.app.Activity paramActivity)
  {
    super.handleMessage(paramMessage, paramActivity);
    switch (paramMessage.what)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      return;
      if ((paramMessage.obj instanceof CardInfo))
      {
        refreshCard((CardInfo)paramMessage.obj);
        label66: if (!this.mCardList.isEmpty())
          break label152;
        this.mLayoutError.setVisibility(0);
      }
      while (true)
      {
        if (this.mCardList.isEmpty())
          break label162;
        getDefaultCard();
        break;
        if (!(paramMessage.obj instanceof List))
          break label66;
        List localList = (List)paramMessage.obj;
        this.mCardList.clear();
        this.mCardList.addAll(localList);
        refreshAll();
        break label66;
        label152: this.mLayoutError.setVisibility(8);
      }
      label162: continue;
      CardInfo localCardInfo = (CardInfo)paramMessage.obj;
      new AlertDialog.Builder(paramActivity).setTitle(2131296314).setMessage(localCardInfo.mCardName + getString(2131296315)).setPositiveButton(2131296273, new DialogInterface.OnClickListener(localCardInfo)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          CardListLocalFragment.this.updateCardInfo(this.val$cardInfo);
        }
      }).setNegativeButton(17039360, null).create().show();
      continue;
      new AlertDialog.Builder(paramActivity).setTitle(2131296316).setMessage(2131296317).setPositiveButton(2131296318, null).create().show();
      continue;
      if (!(paramMessage.obj instanceof CardInfo))
        continue;
      queryServiceStatus((CardInfo)paramMessage.obj);
      continue;
      new AlertDialog.Builder(paramActivity).setTitle(2131296314).setMessage(2131296338).setPositiveButton(17039370, new DialogInterface.OnClickListener(paramActivity)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          if (this.val$activity.getActionBar() != null)
            ((ImmersionMenu)this.val$activity.getActionBar().getCustomView()).showOptionsMenu();
        }
      }).setNegativeButton(17039360, null).create().show();
    }
  }

  protected boolean needLogin()
  {
    return true;
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Uri localUri = getActivity().getIntent().getData();
    if (localUri != null)
    {
      String str = localUri.getQueryParameter("action");
      if ((TextUtils.equals(str, "issue")) || (TextUtils.equals(str, "recharge")))
      {
        getActivity().setResult(paramInt2);
        getActivity().finish();
      }
    }
  }

  protected void onCardListUpdate(Bundle paramBundle)
  {
    if (paramBundle.containsKey("tag_card_type"))
    {
      String str = paramBundle.getString("tag_card_type");
      Iterator localIterator = this.mCardList.iterator();
      while (localIterator.hasNext())
      {
        CardInfo localCardInfo2 = (CardInfo)localIterator.next();
        if (!TextUtils.equals(localCardInfo2.mCardType, str))
          continue;
        updateCardInfo(localCardInfo2);
        refreshCard(localCardInfo2);
      }
    }
    while (true)
    {
      return;
      if (paramBundle.containsKey("card_info"))
      {
        CardInfo localCardInfo1 = (CardInfo)paramBundle.getParcelable("card_info");
        if ((paramBundle.getInt("action_type") == 1) && ((localCardInfo1 instanceof PayableCardInfo)))
        {
          queryServiceStatus(localCardInfo1);
          continue;
        }
        updateCardInfo(localCardInfo1);
        refreshCard(localCardInfo1);
        continue;
      }
      if (!paramBundle.containsKey("card_list"))
        continue;
      loadCardsFromDB(paramBundle.getParcelableArrayList("card_list"), false);
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131623939, paramMenu);
    if (!this.mCardList.isEmpty());
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onDestroy()
  {
    dismissDialog();
    if (this.mQueryCardProductTask != null)
      this.mQueryCardProductTask.cancel(true);
    if (this.mCardManager != null)
      this.mCardManager.release();
    LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).unregisterReceiver(this.mUpdateCardListReceiver);
    this.mAdapter.release();
    if (this.mSetDefaultCardTask != null)
      this.mSetDefaultCardTask.cancel(true);
    if (this.mGetDefaultCardTask != null)
      this.mGetDefaultCardTask.cancel(true);
    super.onDestroy();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903062, paramViewGroup, false);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    CardInfo localCardInfo = this.mAdapter.getItem(paramInt);
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("card_info", localCardInfo);
    localBundle.putString("tag_default_card_aid", this.mDefaultCardAId);
    if ((localCardInfo != null) && (localCardInfo.mHasIssue))
    {
      Intent localIntent2 = new Intent(getActivity(), CardDetailActivity.class);
      localIntent2.putExtras(localBundle);
      startActivityForResult(localIntent2, 2);
    }
    while (true)
    {
      return;
      Intent localIntent1 = new Intent(getActivity(), CardIntroActivity.class);
      localIntent1.putExtras(localBundle);
      startActivityForResult(localIntent1, 1);
    }
  }

  protected void onNFCDisable()
  {
    super.onNFCDisable();
    if (!this.mCardList.isEmpty())
    {
      this.mCardList.clear();
      this.mAdapter.setData(this.mCardList);
    }
  }

  protected void onNFCEnable()
  {
    super.onNFCEnable();
    if (this.mCardList.isEmpty())
      queryServiceStatus(null);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (this.mCardList != null)
    {
      Iterator localIterator = this.mCardList.iterator();
      while (localIterator.hasNext())
      {
        CardInfo localCardInfo = (CardInfo)localIterator.next();
        if (paramMenuItem.getItemId() != localCardInfo.hashCode())
          continue;
        setDefaultCard(localCardInfo);
      }
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }

  public void onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    SubMenu localSubMenu = paramMenu.addSubMenu(0, 2131493116, 0, 2131296356);
    Iterator localIterator = this.mCardList.iterator();
    while (localIterator.hasNext())
    {
      CardInfo localCardInfo = (CardInfo)localIterator.next();
      if (!localCardInfo.mHasIssue)
        continue;
      localSubMenu.add(2131493116, localCardInfo.hashCode(), 0, localCardInfo.mCardName);
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    initView(paramView);
  }

  private class GetDefaultCardTask extends AsyncTask<Void, Void, String>
  {
    private GetDefaultCardTask()
    {
    }

    protected String doInBackground(Void[] paramArrayOfVoid)
    {
      if (CardListLocalFragment.this.mGroupId == CardGroupType.BANKCARD.getId());
      for (String str = SysUtils.getDefaultBankCard(CardListLocalFragment.this.getActivity()); ; str = SysUtils.getDefaultTransCard(CardListLocalFragment.this.getActivity()))
        return str;
    }

    protected void onPostExecute(String paramString)
    {
      CardListLocalFragment.this.mDefaultCardAId = paramString;
      if (!TextUtils.isEmpty(paramString))
      {
        CardListLocalFragment.this.mAdapter.setDefaultCard(paramString);
        return;
      }
      while (true)
        if (TextUtils.isEmpty(paramString))
        {
          Iterator localIterator = CardListLocalFragment.this.mCardList.iterator();
          if (!localIterator.hasNext())
            continue;
          if ((!((CardInfo)localIterator.next()).mHasIssue) || (CardListLocalFragment.this.mHasShowDefaultCardRemind))
            break;
          CardListLocalFragment.access$1002(CardListLocalFragment.this, true);
          continue;
        }
    }
  }

  private class SetDefaultCardTask extends AsyncTask<Void, Void, Boolean>
  {
    private CardInfo mCardInfo;

    public SetDefaultCardTask(CardInfo arg2)
    {
      Object localObject;
      this.mCardInfo = localObject;
    }

    protected Boolean doInBackground(Void[] paramArrayOfVoid)
    {
      return Boolean.valueOf(SysUtils.setDefaultCard(CardListLocalFragment.this.getActivity(), this.mCardInfo.mAid));
    }

    protected void onPostExecute(Boolean paramBoolean)
    {
      CardListLocalFragment.this.dismissDialog();
      if (paramBoolean.booleanValue())
      {
        CardListLocalFragment.this.mAdapter.setDefaultCard(this.mCardInfo.mAid);
        UiUtils.showToast(CardListLocalFragment.this.getActivity(), 2131296507);
      }
      while (true)
      {
        return;
        UiUtils.showToast(CardListLocalFragment.this.getActivity(), 2131296508);
      }
    }

    protected void onPreExecute()
    {
      CardListLocalFragment.this.showDialog(2131296274);
      super.onPreExecute();
    }
  }

  private class UpdateCardListReceiver extends BroadcastReceiver
  {
    private UpdateCardListReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (TextUtils.equals("com.xiaomi.tsmclient.action.UPDATE_CARD_LIST", paramIntent.getAction()))
        CardListLocalFragment.this.onCardListUpdate(paramIntent.getExtras());
    }
  }

  private class QueryCardProductTask extends AsyncTask<Void, Void, List<CardInfo>>
  {
    private CardInfo mCardInfo;

    public QueryCardProductTask(CardInfo arg2)
    {
      Object localObject;
      this.mCardInfo = localObject;
    }

    protected List<CardInfo> doInBackground(Void[] paramArrayOfVoid)
    {
      Object localObject = null;
      ArrayList localArrayList = new ArrayList();
      try
      {
        JSONArray localJSONArray1 = CardListLocalFragment.this.mTSMAuthManager.queryCardProduct(CardListLocalFragment.this.getActivity(), CardGroupType.newInstance(CardListLocalFragment.this.mGroupId));
        if (localJSONArray1 != null)
        {
          localHashMap = new HashMap();
          i = 0;
          if (i < localJSONArray1.length())
          {
            JSONObject localJSONObject = localJSONArray1.getJSONObject(i);
            localCardInfo = CardInfoFactory.makeCardInfo(localJSONObject.optString("cardName"), null);
            localCardInfo.parse(localJSONObject);
            boolean bool = localCardInfo instanceof PayableCardInfo;
            if (bool)
              if (localObject == null)
                try
                {
                  JSONArray localJSONArray2 = CardListLocalFragment.this.mTSMAuthManager.queryByUserId(CardListLocalFragment.this.getActivity());
                  localObject = localJSONArray2;
                  if (localObject != null)
                    for (int j = 0; j < localObject.length(); j++)
                    {
                      OrderInfo localOrderInfo = new OrderInfo();
                      localOrderInfo.parse(localObject.getJSONObject(j));
                      if (localHashMap.get(localOrderInfo.mCardType) == null)
                        localHashMap.put(localOrderInfo.mCardType, new CopyOnWriteArrayList());
                      ((List)localHashMap.get(localOrderInfo.mCardType)).add(localOrderInfo);
                    }
                }
                catch (IOException localIOException2)
                {
                  while (true)
                    LogUtils.e("queryByUserId failed!", localIOException2);
                }
          }
        }
      }
      catch (IOException localIOException1)
      {
        while (true)
        {
          HashMap localHashMap;
          int i;
          CardInfo localCardInfo;
          LogUtils.e("QueryCardProductTask failed!", localIOException1);
          break;
          PayableCardInfo localPayableCardInfo = (PayableCardInfo)localCardInfo;
          if (localHashMap.containsKey(localCardInfo.mCardType))
            localPayableCardInfo.mUnfinishOrderInfos = ((List)localHashMap.get(localCardInfo.mCardType));
          localArrayList.add(localCardInfo);
          i++;
        }
      }
      catch (JSONException localJSONException)
      {
        LogUtils.e("QueryCardProductTask failed!", localJSONException);
      }
      return localArrayList;
    }

    protected void onPostExecute(List<CardInfo> paramList)
    {
      super.onPostExecute(paramList);
      CardListLocalFragment.this.dismissDialog();
      if (this.mCardInfo == null)
        CardListLocalFragment.this.loadCards(paramList);
      while (true)
      {
        return;
        CardListLocalFragment.this.updateCardInfo(this.mCardInfo);
        CardListLocalFragment.this.refreshCard(this.mCardInfo);
      }
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
      CardListLocalFragment.this.showDialog(2131296274);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardListLocalFragment
 * JD-Core Version:    0.6.0
 */