package com.miui.tsmclient.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.entity.eventbus.DefaultCardEvent;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IssuedTransCardListFragment extends BaseTransCardFragment<CardInfo>
  implements IssuedTransCardListAdapter.OnItemClickListener
{
  private View.OnClickListener mClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131493023:
      default:
      case 2131493022:
      case 2131493024:
      }
      while (true)
      {
        return;
        IssuedTransCardListFragment.this.getActivity().finish();
        continue;
        Intent localIntent = new Intent(IssuedTransCardListFragment.this.getActivity(), MainActivity.class);
        localIntent.addFlags(603979776);
        IssuedTransCardListFragment.this.startActivity(localIntent);
      }
    }
  };
  private List<CardInfo> mIssuedCardInfoList;
  private IssuedTransCardListAdapter mIssuedTransCardListAdapter;
  private RecyclerView mRecyclerView;
  private UpdateCardListReceiver mUpdateCardListReceiver;

  private void initView(View paramView)
  {
    ((miui.app.Activity)getActivity()).setTranslucentStatus(2);
    this.mRecyclerView = ((RecyclerView)paramView.findViewById(2131493023));
    IssuedTransCardListLayoutManager localIssuedTransCardListLayoutManager = new IssuedTransCardListLayoutManager(getActivity());
    this.mRecyclerView.setLayoutManager(localIssuedTransCardListLayoutManager);
    this.mIssuedTransCardListAdapter = new IssuedTransCardListAdapter(getActivity());
    this.mRecyclerView.setAdapter(this.mIssuedTransCardListAdapter);
    this.mRecyclerView.setItemAnimator(null);
    this.mIssuedTransCardListAdapter.setOnItemClickListener(this);
    paramView.findViewById(2131493024).setOnClickListener(this.mClickListener);
    paramView.findViewById(2131493022).setOnClickListener(this.mClickListener);
    if (TextUtils.isEmpty(this.mDefaultCardAId))
    {
      getDefaultCard();
      if (this.mCardInfoList != null)
        break label164;
      showDialog(2131296274);
      fetchCardProduct(new BaseTransCardFragment.SimpleFetchCardProductListener()
      {
        public void onGotCardInfoCompleteOnMainThread()
        {
          IssuedTransCardListFragment.this.dismissDialog();
        }

        public void onGotCardInfoErrorOnMainThread()
        {
          IssuedTransCardListFragment.this.dismissDialog();
        }

        public void onGotCardInfoOnMainThread(CardInfo paramCardInfo)
        {
          if ((paramCardInfo != null) && (IssuedTransCardListFragment.this.mIssuedTransCardListAdapter != null))
          {
            IssuedTransCardListFragment.this.dismissDialog();
            IssuedTransCardListFragment.this.mIssuedTransCardListAdapter.updateData(paramCardInfo);
          }
        }
      }
      , null);
    }
    while (true)
    {
      return;
      setDefaultCard(this.mDefaultCardAId);
      break;
      label164: Iterator localIterator = this.mCardInfoList.iterator();
      while (localIterator.hasNext())
      {
        CardInfo localCardInfo = (CardInfo)localIterator.next();
        if (!localCardInfo.mHasIssue)
          continue;
        this.mIssuedCardInfoList.add(localCardInfo);
      }
      this.mIssuedTransCardListAdapter.setData(this.mIssuedCardInfoList);
    }
  }

  private void setDefaultCard(String paramString)
  {
    if (this.mIssuedTransCardListAdapter != null)
      this.mIssuedTransCardListAdapter.setDefaultCard(paramString);
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mUpdateCardListReceiver = new UpdateCardListReceiver(null);
    LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mUpdateCardListReceiver, new IntentFilter("com.xiaomi.tsmclient.action.UPDATE_CARD_LIST"));
    EventBus.getDefault().register(this);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
      switch (paramInt1)
      {
      default:
      case 1:
      }
    while (true)
    {
      return;
      if (paramIntent != null)
      {
        this.mDefaultCardAId = paramIntent.getStringExtra("tag_default_card_aid");
        setDefaultCard(this.mDefaultCardAId);
        continue;
      }
    }
  }

  protected void onCardListUpdate(Bundle paramBundle)
  {
    if (paramBundle == null);
    while (true)
    {
      return;
      if (paramBundle.containsKey("card_info"))
      {
        CardInfo localCardInfo = (CardInfo)paramBundle.getParcelable("card_info");
        if ((paramBundle.getInt("action_type") != 1) || (!(localCardInfo instanceof PayableCardInfo)))
          continue;
        localCardInfo.mHasIssue = true;
        if (this.mIssuedTransCardListAdapter == null)
          continue;
        this.mIssuedTransCardListAdapter.addData(localCardInfo);
        continue;
      }
    }
  }

  public void onDestroy()
  {
    EventBus.getDefault().unregister(this);
    dismissDialog();
    LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mUpdateCardListReceiver);
    super.onDestroy();
  }

  public void onEventMainThread(DefaultCardEvent paramDefaultCardEvent)
  {
    this.mDefaultCardAId = paramDefaultCardEvent.getDefaultCardAId();
    setDefaultCard(this.mDefaultCardAId);
  }

  protected void onGotDefaultCardAId(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      setDefaultCard(paramString);
    while (true)
    {
      return;
      new AlertDialog.Builder(getActivity()).setTitle(2131296314).setMessage(2131296338).setPositiveButton(2131296279, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          Intent localIntent = new Intent(IssuedTransCardListFragment.this.getActivity(), SetDefaultCardActivity.class);
          IssuedTransCardListFragment.this.startActivityForResult(localIntent, 1);
        }
      }).setNegativeButton(17039360, null).create().show();
    }
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903069, paramViewGroup, false);
  }

  public void onItemClick(IssuedTransCardListAdapter paramIssuedTransCardListAdapter, View paramView, int paramInt)
  {
    Intent localIntent = new Intent(getActivity(), CardDetailActivity.class);
    localIntent.putExtra("card_info", paramIssuedTransCardListAdapter.getItemData(paramInt));
    localIntent.setFlags(67108864);
    startActivity(localIntent);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.mIssuedCardInfoList = new ArrayList();
    initView(paramView);
  }

  private class UpdateCardListReceiver extends BroadcastReceiver
  {
    private UpdateCardListReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (TextUtils.equals("com.xiaomi.tsmclient.action.UPDATE_CARD_LIST", paramIntent.getAction()))
        IssuedTransCardListFragment.this.onCardListUpdate(paramIntent.getExtras());
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.IssuedTransCardListFragment
 * JD-Core Version:    0.6.0
 */