package com.miui.tsmclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.model.PayableCardManager;
import com.miui.tsmclient.pay.OrderInfo;
import java.util.List;

public class UnsolvedRecordFragment extends BaseCardFragment<PayableCardInfo>
{
  private static final int MSG_REFRESH = 1;
  private UnsolvedRecordAdapter mAdapter;
  private PayableCardManager mCardManager;
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
        UnsolvedRecordFragment.this.dismissDialog();
        if ((UnsolvedRecordFragment.this.mOrderInfos == null) || (UnsolvedRecordFragment.this.mOrderInfos.isEmpty()))
          continue;
        UnsolvedRecordFragment.this.mAdapter.updateData(UnsolvedRecordFragment.this.mOrderInfos);
      }
    }
  };
  private ListView mListView;
  private List<OrderInfo> mOrderInfos;
  private Tag mTag;

  private void initView(View paramView)
  {
    this.mListView = ((ListView)paramView.findViewById(16908298));
    this.mEmptyView = ((LinearLayout)paramView.findViewById(2131492925));
    this.mAdapter = new UnsolvedRecordAdapter(getActivity());
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setEmptyView(this.mEmptyView);
    this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        if ((paramInt < 0) || (paramInt == UnsolvedRecordFragment.this.mAdapter.getCount()));
        while (true)
        {
          return;
          Intent localIntent = new Intent(UnsolvedRecordFragment.this.getActivity(), NfcRechargeActivity.class);
          localIntent.putExtra("nfc_tag", UnsolvedRecordFragment.this.mTag);
          localIntent.putExtra("card_info", UnsolvedRecordFragment.this.mCardInfo);
          localIntent.putExtra("order_info", (Parcelable)UnsolvedRecordFragment.this.mOrderInfos.get(paramInt));
          UnsolvedRecordFragment.this.startActivityForResult(localIntent, 1);
        }
      }
    });
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mCardManager = new PayableCardManager(getActivity().getApplicationContext());
    Bundle localBundle = getArguments();
    if (localBundle != null)
    {
      this.mTag = ((Tag)localBundle.getParcelable("nfc_tag"));
      this.mOrderInfos = ((PayableCardInfo)this.mCardInfo).mUnfinishOrderInfos;
    }
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
 * Qualified Name:     com.miui.tsmclient.ui.UnsolvedRecordFragment
 * JD-Core Version:    0.6.0
 */