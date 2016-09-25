package com.miui.tsmclient.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.tsmclient.SEInteractionService.SEInteractionState;
import com.miui.tsmclient.entity.CardGroupType;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.entity.CardUIInfo;
import com.miui.tsmclient.entity.IChildCity;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.entity.eventbus.DefaultCardEvent;
import com.miui.tsmclient.entity.gson.MyPositionInfo;
import com.miui.tsmclient.model.CardManager;
import com.miui.tsmclient.net.TSMAuthManager;
import com.miui.tsmclient.net.VolleySingleton;
import com.miui.tsmclient.net.request.MyPositionInfoRequest;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import de.greenrobot.event.EventBus;
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

public class CardIssueListFragment extends BaseCardFragment
  implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener
{
  public static final String ACTION_UPDATE_CARD_INFO = "com.xiaomi.tsmclient.action.UPDATE_CARD_INFO";
  public static final String ACTION_UPDATE_CARD_LIST = "com.xiaomi.tsmclient.action.UPDATE_CARD_LIST";
  public static final String EXTRA_CARD_LIST = "card_list";
  private static final int MSG_HANDLE_UNSOLVED_ORDER_FAILED = 2;
  private static final int MSG_HAS_CONFIRM_DOUBT_ORDER = 3;
  private static final int MSG_QUERY_ORDER = 4;
  private static final int MSG_REFRESH_DATA = 1;
  private CardIssueListAdapter mAdapter;
  private View mBtnIssue;
  private List<CardInfo> mCardList;
  private CardManager mCardManager;
  private Context mContext;
  private GetDefaultCardTask mGetDefaultCardTask;
  private int mGroupId = CardGroupType.TRANSCARD.getId();
  private View mLayoutError;
  private ExpandableListView mListView;
  private ImageView mLocatedCardBg;
  private CardInfo mLocatedCardInfo;
  private TextView mLocatedCardInstalled;
  private TextView mLocatedCardName;
  private View mLocatedCardView;
  private MyPositionInfoRequest mMyPositionInfoRequest;
  private TextView mOtherSupportedCity;
  private MyPositionInfo mPositionInfo;
  private QueryCardProductTask mQueryCardProductTask;
  private TSMAuthManager mTSMAuthManager;
  private String mTextSeparator;
  private UpdateCardListReceiver mUpdateCardListReceiver;

  private int findCardInfoPosition(CardInfo paramCardInfo)
  {
    int i = 0;
    if (i < this.mCardList.size())
      if (!TextUtils.equals(((CardInfo)this.mCardList.get(i)).mCardType, paramCardInfo.mCardType));
    while (true)
    {
      return i;
      i++;
      break;
      i = -1;
    }
  }

  private void getDefaultCard()
  {
    if (this.mGetDefaultCardTask != null)
      this.mGetDefaultCardTask.cancel(true);
    this.mGetDefaultCardTask = new GetDefaultCardTask(null);
    this.mGetDefaultCardTask.execute(new Void[0]);
  }

  private void initActionBar()
  {
    ActionBar localActionBar = (ActionBar)getActivity().getActionBar();
    if (localActionBar != null)
      localActionBar.setTitle(2131296544);
  }

  private void initList(View paramView)
  {
    this.mAdapter = new CardIssueListAdapter(getActivity());
    this.mListView = ((ExpandableListView)paramView.findViewById(16908298));
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setOnChildClickListener(this);
    this.mListView.setOnGroupClickListener(this);
  }

  private void initView(View paramView)
  {
    initList(paramView);
    this.mLocatedCardView = paramView.findViewById(2131492953);
    this.mOtherSupportedCity = ((TextView)paramView.findViewById(2131492958));
    this.mBtnIssue = paramView.findViewById(2131492957);
    this.mLocatedCardName = ((TextView)paramView.findViewById(2131492955));
    this.mLocatedCardInstalled = ((TextView)paramView.findViewById(2131492956));
    this.mLocatedCardBg = ((ImageView)paramView.findViewById(2131492954));
    this.mLocatedCardView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Object localObject1;
        if ((CardIssueListFragment.this.mLocatedCardInfo != null) && (CardIssueListFragment.this.mLocatedCardInfo.mHasIssue))
          localObject1 = null;
        try
        {
          if (CardIssueListFragment.this.mPositionInfo == null);
          label48: String str;
          for (Object localObject2 = null; ; localObject2 = str)
          {
            Integer localInteger = Integer.valueOf((String)localObject2);
            localObject1 = localInteger;
            CardIssueListFragment.this.onCardClick(CardIssueListFragment.this.mLocatedCardInfo, localObject1);
            return;
            str = CardIssueListFragment.this.mPositionInfo.getCityId();
          }
        }
        catch (NumberFormatException localNumberFormatException)
        {
          break label48;
        }
      }
    });
    this.mBtnIssue.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        CardIssueListFragment.this.onCardClick(CardIssueListFragment.this.mLocatedCardInfo, null);
      }
    });
    initActionBar();
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
        CardIssueListFragment.this.mHandler.obtainMessage(1, CardIssueListFragment.this.mCardList).sendToTarget();
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
            CardIssueListFragment.this.updateCardInfo(localCardInfo);
            continue;
          }
          Intent localIntent = new Intent("com.xiaomi.tsmclient.action.UPDATE_CARD_INFO");
          localIntent.putExtra("card_info", localCardInfo);
          LocalBroadcastManager.getInstance(CardIssueListFragment.this.mContext).sendBroadcast(localIntent);
        }
        if ((this.val$cardInfoList.size() == 1) && (!CardIssueListFragment.this.mCardList.isEmpty()))
          CardIssueListFragment.this.mHandler.obtainMessage(1, localList.toArray()[0]).sendToTarget();
        while (true)
        {
          return;
          CardIssueListFragment.this.mHandler.obtainMessage(1, localList).sendToTarget();
        }
      }
    });
  }

  private void onCardClick(CardInfo paramCardInfo, Integer paramInteger)
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("card_info", paramCardInfo);
    if (paramInteger != null)
      localBundle.putInt("cityId", paramInteger.intValue());
    if ((paramCardInfo != null) && (paramCardInfo.mHasIssue))
    {
      Intent localIntent2 = new Intent(getActivity(), CardDetailActivity.class);
      localIntent2.setFlags(67108864);
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

  private void orderList(List<CardInfo> paramList)
  {
    if (paramList == null);
    while (true)
    {
      return;
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        CardInfo localCardInfo = (CardInfo)localIterator.next();
        if (!TextUtils.equals(localCardInfo.mCardType, "LNT"))
          continue;
        localArrayList.add(localCardInfo);
      }
      if (localArrayList.isEmpty())
        continue;
      paramList.removeAll(localArrayList);
      paramList.addAll(localArrayList);
    }
  }

  private void processLocation()
  {
    if (this.mPositionInfo == null);
    while (true)
    {
      return;
      if (!TextUtils.isEmpty(this.mPositionInfo.getCity()))
      {
        refreshAll();
        continue;
      }
    }
  }

  private void queryPosition()
  {
    this.mMyPositionInfoRequest = new MyPositionInfoRequest(new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        LogUtils.e(paramString);
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        CardIssueListFragment.access$102(CardIssueListFragment.this, (MyPositionInfo)paramArrayOfObject[0]);
        if (UiUtils.isFragmentValid(CardIssueListFragment.this))
          CardIssueListFragment.this.processLocation();
      }
    });
    VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(this.mMyPositionInfoRequest);
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
    refreshCardLocated();
    orderList(this.mCardList);
    this.mAdapter.setData(this.mCardList);
  }

  private void refreshCard(CardInfo paramCardInfo)
  {
    if (paramCardInfo == null);
    while (true)
    {
      return;
      String str = paramCardInfo.mAid;
      if (this.mLocatedCardInfo == null);
      for (Object localObject = null; ; localObject = this.mLocatedCardInfo.mAid)
      {
        if (!str.equals(localObject))
          break label50;
        this.mLocatedCardInfo = paramCardInfo;
        refreshCardLocated();
        break;
      }
      label50: int i = findCardInfoPosition(paramCardInfo);
      if (i < 0)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramCardInfo.mCardName;
        LogUtils.d(String.format("cannot find cardinfo:%s on card list.", arrayOfObject));
        continue;
      }
      if (i < this.mCardList.size())
      {
        this.mCardList.remove(i);
        this.mCardList.add(i, paramCardInfo);
      }
      refreshAll();
    }
  }

  private void refreshCardLocated()
  {
    int i = 8;
    int j = 0;
    if ((this.mPositionInfo == null) || (this.mCardList == null));
    while (true)
    {
      return;
      Iterator localIterator = this.mCardList.iterator();
      while (localIterator.hasNext())
      {
        CardInfo localCardInfo = (CardInfo)localIterator.next();
        if (!TextUtils.equals(localCardInfo.mAid, this.mPositionInfo.getAId()))
          continue;
        this.mLocatedCardInfo = localCardInfo;
      }
      if (this.mLocatedCardInfo != null)
      {
        this.mLocatedCardView.setVisibility(0);
        this.mOtherSupportedCity.setText(2131296545);
        refreshLocatedCardName();
        this.mCardList.remove(this.mLocatedCardInfo);
        View localView = this.mBtnIssue;
        if (this.mLocatedCardInfo.mHasIssue)
        {
          label133: localView.setVisibility(i);
          TextView localTextView = this.mLocatedCardInstalled;
          if (!this.mLocatedCardInfo.mHasIssue)
            break label202;
          label155: localTextView.setVisibility(j);
          if (this.mLocatedCardInfo.mCardUIInfo != null)
            break label207;
        }
        label202: label207: for (Object localObject = null; ; localObject = this.mLocatedCardInfo.mCardUIInfo.mCardIssuedListBgHdUrl)
        {
          if (TextUtils.isEmpty((CharSequence)localObject))
            break label220;
          ImageLoader.getInstance().displayImage((String)localObject, this.mLocatedCardBg);
          break;
          i = 0;
          break label133;
          j = 4;
          break label155;
        }
        label220: continue;
      }
      this.mLocatedCardView.setVisibility(i);
      this.mOtherSupportedCity.setText(2131296546);
    }
  }

  private void refreshLocatedCardName()
  {
    int i;
    String str;
    int j;
    int k;
    ArrayList localArrayList;
    if ((this.mLocatedCardInfo != null) && (TextUtils.equals(this.mLocatedCardInfo.mAid, this.mDefaultCardAId)))
    {
      i = 1;
      StringBuilder localStringBuilder = new StringBuilder();
      if ((this.mPositionInfo != null) && (!TextUtils.isEmpty(this.mPositionInfo.getCity())))
        localStringBuilder.append(this.mPositionInfo.getCity());
      if (i != 0)
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(this.mTextSeparator);
        localStringBuilder.append(getActivity().getString(2131296505));
      }
      str = localStringBuilder.toString();
      j = this.mTextSeparator.length();
      k = 0;
      localArrayList = new ArrayList();
    }
    SpannableString localSpannableString;
    while (true)
    {
      int m = str.indexOf(this.mTextSeparator, k);
      if (m == -1)
      {
        int n = getResources().getColor(2131165280);
        localSpannableString = new SpannableString(str);
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
        {
          int i1 = ((Integer)localIterator.next()).intValue();
          localSpannableString.setSpan(new ForegroundColorSpan(n), i1, i1 + j, 17);
        }
        i = 0;
        break;
      }
      localArrayList.add(Integer.valueOf(m));
      k = m + j;
    }
    this.mLocatedCardName.setText(localSpannableString);
  }

  private void updateCardInfo(CardInfo paramCardInfo)
  {
    this.mCardManager.updateCardInfo(this.mContext, paramCardInfo, new MiTsmCallback(paramCardInfo)
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        int i = 0;
        Iterator localIterator = CardIssueListFragment.this.mCardList.iterator();
        while (localIterator.hasNext())
        {
          if (!TextUtils.equals(((CardInfo)localIterator.next()).mCardType, this.val$cardInfo.mCardType))
            continue;
          i = 1;
        }
        if (i != 0)
          CardIssueListFragment.this.loadCardsFromDB(Collections.singletonList(this.val$cardInfo), false);
        if (paramInt == 1003)
          CardIssueListFragment.this.mHandler.obtainMessage(2, this.val$cardInfo).sendToTarget();
        while (true)
        {
          return;
          if (paramInt == 1004)
          {
            CardIssueListFragment.this.mHandler.sendEmptyMessage(3);
            continue;
          }
          if (paramInt != 1010)
            continue;
          CardIssueListFragment.this.mHandler.obtainMessage(4, this.val$cardInfo).sendToTarget();
        }
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        CardIssueListFragment.this.loadCardsFromDB(Collections.singletonList(this.val$cardInfo), false);
      }
    });
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    EventBus.getDefault().register(this);
    this.mContext = getActivity().getApplicationContext();
    this.mTextSeparator = getString(2131296421);
    this.mTSMAuthManager = new TSMAuthManager();
    this.mCardList = new CopyOnWriteArrayList();
    this.mCardManager = new CardManager(this.mContext);
    this.mUpdateCardListReceiver = new UpdateCardListReceiver(null);
    LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mUpdateCardListReceiver, new IntentFilter("com.xiaomi.tsmclient.action.UPDATE_CARD_LIST"));
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
    }
    while (true)
    {
      return;
      if ((paramMessage.obj instanceof CardInfo))
      {
        refreshCard((CardInfo)paramMessage.obj);
        label62: if (!this.mCardList.isEmpty())
          break label148;
        this.mLayoutError.setVisibility(0);
      }
      while (true)
      {
        if (this.mCardList.isEmpty())
          break label158;
        getDefaultCard();
        break;
        if (!(paramMessage.obj instanceof List))
          break label62;
        List localList = (List)paramMessage.obj;
        this.mCardList.clear();
        this.mCardList.addAll(localList);
        refreshAll();
        break label62;
        label148: this.mLayoutError.setVisibility(8);
      }
      label158: continue;
      CardInfo localCardInfo = (CardInfo)paramMessage.obj;
      new AlertDialog.Builder(paramActivity).setTitle(2131296314).setMessage(localCardInfo.mCardName + getString(2131296315)).setPositiveButton(2131296273, new DialogInterface.OnClickListener(localCardInfo)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          CardIssueListFragment.this.updateCardInfo(this.val$cardInfo);
        }
      }).setNegativeButton(17039360, null).create().show();
      continue;
      new AlertDialog.Builder(paramActivity).setTitle(2131296316).setMessage(2131296317).setPositiveButton(2131296318, null).create().show();
      continue;
      if (!(paramMessage.obj instanceof CardInfo))
        continue;
      queryServiceStatus((CardInfo)paramMessage.obj);
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
    if (paramBundle == null)
      return;
    while (true)
    {
      if (paramBundle.containsKey("tag_card_type"))
      {
        String str = paramBundle.getString("tag_card_type");
        Iterator localIterator = this.mCardList.iterator();
        if (!localIterator.hasNext())
          continue;
        CardInfo localCardInfo2 = (CardInfo)localIterator.next();
        if (!TextUtils.equals(localCardInfo2.mCardType, str))
          break;
        updateCardInfo(localCardInfo2);
        refreshCard(localCardInfo2);
        continue;
      }
      if (paramBundle.containsKey("card_info"))
      {
        CardInfo localCardInfo1 = (CardInfo)paramBundle.getParcelable("card_info");
        if ((paramBundle.getInt("action_type") == 1) && ((localCardInfo1 instanceof PayableCardInfo)))
        {
          localCardInfo1.mHasIssue = true;
          refreshCard(localCardInfo1);
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

  public boolean onChildClick(ExpandableListView paramExpandableListView, View paramView, int paramInt1, int paramInt2, long paramLong)
  {
    onCardClick(this.mAdapter.getGroup(paramInt1), Integer.valueOf(this.mAdapter.getChild(paramInt1, paramInt2).getCityId()));
    return true;
  }

  public void onDestroy()
  {
    EventBus.getDefault().unregister(this);
    dismissDialog();
    if (this.mQueryCardProductTask != null)
      this.mQueryCardProductTask.cancel(true);
    if (this.mCardManager != null)
      this.mCardManager.release();
    LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mUpdateCardListReceiver);
    if (this.mGetDefaultCardTask != null)
      this.mGetDefaultCardTask.cancel(true);
    super.onDestroy();
  }

  public void onEventMainThread(DefaultCardEvent paramDefaultCardEvent)
  {
    this.mDefaultCardAId = paramDefaultCardEvent.getDefaultCardAId();
    refreshLocatedCardName();
  }

  public boolean onGroupClick(ExpandableListView paramExpandableListView, View paramView, int paramInt, long paramLong)
  {
    if (this.mAdapter.getChildrenCount(paramInt) == 0)
      onCardClick(this.mAdapter.getGroup(paramInt), null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903058, paramViewGroup, false);
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

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    initView(paramView);
    queryPosition();
  }

  private class GetDefaultCardTask extends AsyncTask<Void, Void, String>
  {
    private GetDefaultCardTask()
    {
    }

    protected String doInBackground(Void[] paramArrayOfVoid)
    {
      if (CardIssueListFragment.this.mGroupId == CardGroupType.BANKCARD.getId());
      for (String str = SysUtils.getDefaultBankCard(CardIssueListFragment.this.mContext); ; str = SysUtils.getDefaultTransCard(CardIssueListFragment.this.mContext))
        return str;
    }

    protected void onPostExecute(String paramString)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        CardIssueListFragment.this.mDefaultCardAId = paramString;
        CardIssueListFragment.this.refreshLocatedCardName();
      }
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
        CardIssueListFragment.this.onCardListUpdate(paramIntent.getExtras());
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
      while (true)
      {
        try
        {
          JSONArray localJSONArray1 = CardIssueListFragment.this.mTSMAuthManager.queryCardProduct(CardIssueListFragment.this.mContext, CardGroupType.newInstance(CardIssueListFragment.this.mGroupId));
          if (localJSONArray1 != null)
          {
            Context localContext = CardIssueListFragment.this.mContext;
            if (CardIssueListFragment.this.mGroupId != CardGroupType.TRANSCARD.getId())
              break label419;
            bool1 = true;
            String str = PrefUtils.getDefaultCard(localContext, bool1);
            localHashMap = new HashMap();
            i = 0;
            if (i < localJSONArray1.length())
            {
              JSONObject localJSONObject = localJSONArray1.getJSONObject(i);
              localCardInfo = CardInfoFactory.makeCardInfo(localJSONObject.optString("cardName"), null);
              localCardInfo.parse(localJSONObject);
              localCardInfo.mIsDefault = TextUtils.equals(localCardInfo.mAid, str);
              boolean bool2 = localCardInfo instanceof PayableCardInfo;
              if (!bool2)
                continue;
              if (localObject != null)
                continue;
              try
              {
                JSONArray localJSONArray2 = CardIssueListFragment.this.mTSMAuthManager.queryByUserId(CardIssueListFragment.this.mContext);
                localObject = localJSONArray2;
                if (localObject == null)
                  continue;
                int j = 0;
                if (j >= localObject.length())
                  continue;
                OrderInfo localOrderInfo = new OrderInfo();
                localOrderInfo.parse(localObject.getJSONObject(j));
                if (localHashMap.get(localOrderInfo.mCardType) != null)
                  continue;
                localHashMap.put(localOrderInfo.mCardType, new CopyOnWriteArrayList());
                ((List)localHashMap.get(localOrderInfo.mCardType)).add(localOrderInfo);
                j++;
                continue;
              }
              catch (IOException localIOException2)
              {
                LogUtils.e("queryByUserId failed!", localIOException2);
                continue;
              }
            }
          }
        }
        catch (IOException localIOException1)
        {
          HashMap localHashMap;
          int i;
          CardInfo localCardInfo;
          LogUtils.e("QueryCardProductTask failed!", localIOException1);
          break label417;
          PayableCardInfo localPayableCardInfo = (PayableCardInfo)localCardInfo;
          if (!localHashMap.containsKey(localCardInfo.mCardType))
            continue;
          localPayableCardInfo.mUnfinishOrderInfos = ((List)localHashMap.get(localCardInfo.mCardType));
          if ((!(this.mCardInfo instanceof PayableCardInfo)) || (!this.mCardInfo.equals(localPayableCardInfo)))
            continue;
          ((PayableCardInfo)this.mCardInfo).mUnfinishOrderInfos = localPayableCardInfo.mUnfinishOrderInfos;
          localArrayList.add(localCardInfo);
          i++;
          continue;
        }
        catch (JSONException localJSONException)
        {
          LogUtils.e("QueryCardProductTask failed!", localJSONException);
        }
        label417: return localArrayList;
        label419: boolean bool1 = false;
      }
    }

    protected void onPostExecute(List<CardInfo> paramList)
    {
      super.onPostExecute(paramList);
      CardIssueListFragment.this.dismissDialog();
      CardIssueListFragment.this.processLocation();
      if (this.mCardInfo == null)
        CardIssueListFragment.this.loadCards(paramList);
      while (true)
      {
        return;
        CardIssueListFragment.this.updateCardInfo(this.mCardInfo);
        CardIssueListFragment.this.refreshCard(this.mCardInfo);
      }
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
      CardIssueListFragment.this.showDialog(2131296274);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardIssueListFragment
 * JD-Core Version:    0.6.0
 */