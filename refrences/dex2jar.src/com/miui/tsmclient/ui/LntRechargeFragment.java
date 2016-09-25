package com.miui.tsmclient.ui;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.miui.tsmclient.entity.LntChildCity;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.entity.gson.MyPositionInfo;
import com.miui.tsmclient.model.LntChildCityGetter;
import com.miui.tsmclient.net.VolleySingleton;
import com.miui.tsmclient.net.request.MyPositionInfoRequest;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import java.util.ArrayList;
import java.util.List;

public class LntRechargeFragment extends IssueRechargeFragment<PayableCardInfo>
{
  private static final String LNT_ALL_CITY_NAME = "lnt_all_city_name";
  private static final String SELECTED_LOCATION_NAME = "selected_location_name";
  private ArrayList<String> mCities = new ArrayList();
  private LntChildCityGetter mCityGetter;
  private String mInitLocationName;
  private TextView mLocation;
  private MyPositionInfoRequest mMyPositionInfoRequest;
  private MyPositionInfo mPositionInfo;

  private int getCityId()
  {
    String str = this.mInitLocationName;
    int i = LntChildCity.getCityIdByName(getActivity().getApplicationContext(), str);
    if (i == -1)
      i = LntChildCity.ALL_PROVINCE.getCityId();
    return i;
  }

  private void initActionBar()
  {
    ActionBar localActionBar = getActivity().getActionBar();
    if (localActionBar != null)
    {
      if (TextUtils.isEmpty(localActionBar.getTitle()))
        localActionBar.setTitle(2131296266);
      this.mLocation = new TextView(getActivity());
      this.mLocation.setBackgroundResource(2130837509);
      this.mLocation.setGravity(17);
      this.mLocation.setTextSize(0, getResources().getDimension(2131230841));
      this.mLocation.setTextColor(getResources().getColor(2131165285));
      if (((PayableCardInfo)this.mCardInfo).mHasIssue)
        break label176;
      this.mLocation.setVisibility(0);
    }
    while (true)
    {
      this.mLocation.setClickable(false);
      this.mLocation.setText(getResources().getString(2131296391));
      this.mLocation.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Intent localIntent = new Intent(LntRechargeFragment.this.getActivity(), LntSelectLocationActivity.class);
          localIntent.putExtra("selected_location_name", LntRechargeFragment.this.mInitLocationName);
          localIntent.putStringArrayListExtra("lnt_all_city_name", LntRechargeFragment.this.mCities);
          LntRechargeFragment.this.startActivityForResult(localIntent, 4);
        }
      });
      localActionBar.setCustomView(this.mLocation, new ActionBar.LayoutParams(-2, -2, 21));
      return;
      label176: this.mLocation.setVisibility(8);
    }
  }

  private void processLocation()
  {
    this.mCities = new ArrayList();
    List localList = this.mCityGetter.getChildCardDescs();
    int i = localList.size();
    int j = 0;
    if (j < i)
    {
      LntChildCity localLntChildCity = (LntChildCity)localList.get(j);
      this.mCities.add(getString(localLntChildCity.getCityNameRes()));
      if ((this.mPositionInfo != null) && (TextUtils.equals(this.mPositionInfo.getCityId(), String.valueOf(localLntChildCity.getCityId()))));
      for (this.mInitLocationName = getString(localLntChildCity.getCityNameRes()); ; this.mInitLocationName = getString(2131296547))
      {
        j++;
        break;
      }
    }
    this.mLocation.setText(this.mInitLocationName);
    this.mLocation.setClickable(true);
  }

  private void queryPosition()
  {
    this.mMyPositionInfoRequest = new MyPositionInfoRequest(new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        if (UiUtils.isFragmentValid(LntRechargeFragment.this))
        {
          LogUtils.e(paramString);
          UiUtils.showToast(LntRechargeFragment.this.getActivity(), 2131296311);
          LntRechargeFragment.this.processLocation();
        }
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        LntRechargeFragment.access$202(LntRechargeFragment.this, (MyPositionInfo)paramArrayOfObject[0]);
        if (UiUtils.isFragmentValid(LntRechargeFragment.this))
          LntRechargeFragment.this.processLocation();
      }
    });
    VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(this.mMyPositionInfoRequest);
  }

  protected void createOrder(Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (this.mLocation.getVisibility() == 0)
    {
      if (localBundle == null)
        localBundle = new Bundle();
      localBundle.putInt("cityId", getCityId());
    }
    super.createOrder(localBundle);
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mCityGetter = new LntChildCityGetter();
    this.mInitLocationName = getString(2131296547);
  }

  protected void doIssue(Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (localBundle == null)
      localBundle = new Bundle();
    localBundle.putString("extra_city_id", String.valueOf(getCityId()));
    super.doIssue(localBundle);
  }

  protected void initView(View paramView)
  {
    super.initView(paramView);
    initActionBar();
    if ((!((PayableCardInfo)this.mCardInfo).mHasIssue) && (SysUtils.isNetworkLocationServiceEnabled(getActivity())))
      queryPosition();
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1);
    switch (paramInt1)
    {
    default:
      return;
    case 4:
    }
    this.mInitLocationName = paramIntent.getStringExtra("selected_location_name");
    TextView localTextView = this.mLocation;
    if (TextUtils.isEmpty(this.mInitLocationName));
    for (String str = getString(2131296547); ; str = this.mInitLocationName)
    {
      localTextView.setText(str);
      break;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.LntRechargeFragment
 * JD-Core Version:    0.6.0
 */