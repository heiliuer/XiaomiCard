package com.miui.tsmclient.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.miui.tsmclient.database.CardDataUtil;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.eventbus.DefaultCardEvent;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.UiUtils;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SetDefaultCardFragment extends BaseCardFragment
  implements AdapterView.OnItemClickListener
{
  private SetDefaultCardAdapter mAdapter;
  private ExecutorService mExecutor;
  private ListView mListView;
  private SetDefaultCardTask mSetDefaultCardTask;

  private void initView(View paramView)
  {
    this.mListView = ((ListView)paramView.findViewById(16908298));
    this.mAdapter = new SetDefaultCardAdapter(getActivity());
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setOnItemClickListener(this);
  }

  private void loadCards()
  {
    this.mExecutor.execute(new Runnable()
    {
      public void run()
      {
        List localList;
        ArrayList localArrayList;
        Iterator localIterator;
        if (SetDefaultCardFragment.this.mCardInfoList != null)
        {
          localList = SetDefaultCardFragment.this.mCardInfoList;
          localArrayList = new ArrayList();
          if (localList != null)
            localIterator = localList.iterator();
        }
        else
        {
          label37: label117: 
          while (true)
          {
            if (!localIterator.hasNext())
              break label119;
            CardInfo localCardInfo = (CardInfo)localIterator.next();
            if (!TextUtils.equals(localCardInfo.mCardType, "BANKCARD"));
            for (int i = 1; ; i = 0)
            {
              if ((!localCardInfo.mHasIssue) || (i == 0))
                break label117;
              localArrayList.add(localCardInfo);
              break label37;
              localList = CardDataUtil.loadCardList(SetDefaultCardFragment.this.getActivity(), null);
              break;
            }
          }
        }
        label119: if (UiUtils.isFragmentValid(SetDefaultCardFragment.this))
        {
          SetDefaultCardFragment.this.getActivity().runOnUiThread(new Runnable(localArrayList)
          {
            public void run()
            {
              if (SetDefaultCardFragment.this.mAdapter != null)
              {
                SetDefaultCardFragment.this.mAdapter.updateData(this.val$issuedTransCardInfoList);
                SetDefaultCardFragment.this.mAdapter.setDefaultCard(SetDefaultCardFragment.this.mDefaultCardAId);
              }
            }
          });
          if (TextUtils.isEmpty(SetDefaultCardFragment.this.mDefaultCardAId))
            break label162;
        }
        while (true)
        {
          return;
          label162: SetDefaultCardFragment.this.mDefaultCardAId = SysUtils.getDefaultTransCard(SetDefaultCardFragment.this.getActivity());
          if (!UiUtils.isFragmentValid(SetDefaultCardFragment.this))
            continue;
          SetDefaultCardFragment.this.getActivity().runOnUiThread(new Runnable()
          {
            public void run()
            {
              SetDefaultCardFragment.this.mAdapter.setDefaultCard(SetDefaultCardFragment.this.mDefaultCardAId);
            }
          });
        }
      }
    });
  }

  private void setDefaultCard(CardInfo paramCardInfo)
  {
    if (this.mSetDefaultCardTask != null)
      this.mSetDefaultCardTask.cancel(true);
    this.mSetDefaultCardTask = new SetDefaultCardTask(paramCardInfo);
    this.mSetDefaultCardTask.execute(new Void[0]);
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mExecutor = Executors.newSingleThreadExecutor();
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    ActionBar localActionBar = getActivity().getActionBar();
    if (localActionBar != null)
      localActionBar.setTitle(getString(2131296283));
  }

  public void onDestroy()
  {
    this.mExecutor.shutdownNow();
    if (this.mSetDefaultCardTask != null)
      this.mSetDefaultCardTask.cancel(true);
    super.onDestroy();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903077, paramViewGroup, false);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    CardInfo localCardInfo = (CardInfo)this.mAdapter.getItem(paramInt);
    if (!TextUtils.equals(localCardInfo.mAid, this.mDefaultCardAId))
      setDefaultCard(localCardInfo);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    initView(paramView);
    loadCards();
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
      return Boolean.valueOf(SysUtils.setDefaultCard(SetDefaultCardFragment.this.getActivity(), this.mCardInfo.mAid));
    }

    protected void onPostExecute(Boolean paramBoolean)
    {
      if (paramBoolean.booleanValue())
      {
        SetDefaultCardFragment.this.mDefaultCardAId = this.mCardInfo.mAid;
        EventBus.getDefault().post(new DefaultCardEvent(SetDefaultCardFragment.this.mDefaultCardAId));
      }
      if (!UiUtils.isFragmentValid(SetDefaultCardFragment.this));
      while (true)
      {
        return;
        SetDefaultCardFragment.this.dismissDialog();
        if (paramBoolean.booleanValue())
        {
          SetDefaultCardFragment.this.mAdapter.setDefaultCard(SetDefaultCardFragment.this.mDefaultCardAId);
          UiUtils.showToast(SetDefaultCardFragment.this.getActivity(), 2131296507);
          continue;
        }
        UiUtils.showToast(SetDefaultCardFragment.this.getActivity(), 2131296508);
      }
    }

    protected void onPreExecute()
    {
      SetDefaultCardFragment.this.showDialog(2131296274);
      super.onPreExecute();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.SetDefaultCardFragment
 * JD-Core Version:    0.6.0
 */