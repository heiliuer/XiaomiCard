package com.miui.tsmclient.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.miui.tsmclient.entity.LntChildCity;
import com.miui.tsmclient.model.LntChildCityGetter;
import java.util.ArrayList;
import java.util.List;

public class LntSelectLocationFragment extends BaseCardFragment
  implements AdapterView.OnItemClickListener
{
  public static final String LNT_ALL_CITY_NAME = "lnt_all_city_name";
  public static final String SELECTED_LOCATION_NAME = "selected_location_name";
  private LntSelectLocationAdapter mAdapter;
  private ArrayList<String> mCities;
  private LntChildCityGetter mCityGetter;
  private String mCurrentLocationName;
  private String mLastSelectLocationName;
  private ListView mListView;

  private void initView(View paramView)
  {
    this.mListView = ((ListView)paramView.findViewById(16908298));
    this.mAdapter = new LntSelectLocationAdapter(getActivity());
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setOnItemClickListener(this);
  }

  private void loadLocationData()
  {
    if ((this.mCities == null) || (this.mCities.isEmpty()))
    {
      this.mCities = new ArrayList();
      List localList = this.mCityGetter.getChildCardDescs();
      int i = localList.size();
      for (int j = 0; j < i; j++)
      {
        LntChildCity localLntChildCity = (LntChildCity)localList.get(j);
        this.mCities.add(getString(localLntChildCity.getCityNameRes()));
      }
    }
    this.mAdapter.updateData(this.mCities);
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    Bundle localBundle = getArguments();
    if (localBundle != null)
    {
      this.mLastSelectLocationName = localBundle.getString("selected_location_name");
      this.mCities = localBundle.getStringArrayList("lnt_all_city_name");
    }
    this.mCityGetter = new LntChildCityGetter();
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    ActionBar localActionBar = getActivity().getActionBar();
    if (localActionBar != null)
      localActionBar.setTitle(getString(2131296391));
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903083, paramViewGroup, false);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    int i = 0;
    Object localObject = this.mAdapter.getItem(paramInt);
    String str;
    if (localObject != null)
    {
      str = localObject.toString();
      this.mCurrentLocationName = str;
      if (TextUtils.equals(this.mLastSelectLocationName, this.mCurrentLocationName))
        break label107;
    }
    label107: for (int j = 1; ; j = 0)
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("selected_location_name", this.mCurrentLocationName);
      Activity localActivity = getActivity();
      if (j != 0)
        i = -1;
      localActivity.setResult(i, localIntent);
      getActivity().finish();
      return;
      str = null;
      break;
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    initView(paramView);
    loadLocationData();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.LntSelectLocationFragment
 * JD-Core Version:    0.6.0
 */