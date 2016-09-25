package com.miui.tsmclient.ui;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.CardManager;
import com.miui.tsmclient.ui.widget.ImmersionMenu;
import com.miui.tsmclientsdk.MiTsmCallback;
import miui.app.ActionBar;

public class CardPurchaseRecordFragment extends BaseCardFragment
{
  private static final int MSG_REFRESH = 1;
  private PurchaseRecordAdapter mAdapter;
  private CardManager mCardManager;
  private LinearLayout mEmptyView;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      }
      while (true)
      {
        return;
        CardPurchaseRecordFragment.this.dismissDialog();
        if (paramMessage.obj == null)
          continue;
        CardPurchaseRecordFragment.this.mCardInfo = ((CardInfo)paramMessage.obj);
        CardPurchaseRecordFragment.this.mAdapter.updateData(CardPurchaseRecordFragment.this.mCardInfo.mTradeLogs);
      }
    }
  };
  private ListView mListView;

  private void initView(View paramView)
  {
    ActionBar localActionBar = (ActionBar)getActivity().getActionBar();
    if (localActionBar != null)
      localActionBar.setCustomView((ImmersionMenu)getActivity().getLayoutInflater().inflate(2130903041, null), new ActionBar.LayoutParams(-2, -2, 5));
    this.mListView = ((ListView)paramView.findViewById(16908298));
    this.mEmptyView = ((LinearLayout)paramView.findViewById(2131492925));
    this.mAdapter = new PurchaseRecordAdapter(getActivity());
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setEmptyView(this.mEmptyView);
  }

  private void loadData()
  {
    if ((this.mCardInfo != null) && (this.mCardInfo.mTradeLogs == null))
    {
      showDialog(2131296274);
      this.mCardManager.queryPurchaseRecord(getActivity(), this.mCardInfo, new MiTsmCallback()
      {
        public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
        {
          CardPurchaseRecordFragment.this.mHandler.obtainMessage(1).sendToTarget();
        }

        public void onSuccess(int paramInt, Object[] paramArrayOfObject)
        {
          if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
            CardPurchaseRecordFragment.this.mHandler.obtainMessage(1, paramArrayOfObject[0]).sendToTarget();
          while (true)
          {
            return;
            CardPurchaseRecordFragment.this.mHandler.obtainMessage(1).sendToTarget();
          }
        }
      });
    }
    while (true)
    {
      return;
      this.mAdapter.updateData(this.mCardInfo.mTradeLogs);
    }
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mCardManager = new CardManager(getActivity().getApplicationContext());
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    loadData();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131623938, paramMenu);
    return true;
  }

  public void onDestroy()
  {
    this.mCardManager.release();
    this.mHandler.removeCallbacksAndMessages(null);
    super.onDestroy();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903053, paramViewGroup, false);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (this.mAdapter != null)
    {
      if (i != 2131493117)
        break label34;
      this.mAdapter.setOnlyDisplayRecharge(false);
    }
    while (true)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      label34: if (i != 2131493118)
        continue;
      this.mAdapter.setOnlyDisplayRecharge(true);
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    initView(paramView);
  }

  protected boolean showErrorWhenNFCOff()
  {
    return false;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardPurchaseRecordFragment
 * JD-Core Version:    0.6.0
 */