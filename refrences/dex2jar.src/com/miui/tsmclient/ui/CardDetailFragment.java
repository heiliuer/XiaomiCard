package com.miui.tsmclient.ui;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.gson.ConfigInfo;
import com.miui.tsmclient.entity.gson.ConfigInfo.ConfigItem;
import com.miui.tsmclient.net.VolleySingleton;
import com.miui.tsmclient.net.request.ConfigRequest;
import com.miui.tsmclient.ui.widget.ImmersionMenu;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NetworkUtil;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CardDetailFragment extends BaseTransCardFragment
  implements View.OnClickListener, AdapterView.OnItemClickListener
{
  private MoreItemAdapter mAdapter;
  private View mBalanceUnit;
  private View mBtnRecharge;
  private CardInfoChangeReceiver mCardInfoChangeReceiver;
  private ConfigInfo mConfigInfo;
  private ConfigRequest mConfigRequest;
  private View mFooter;
  private TextView mFooterContent;
  private View mFooterLink;
  private ImmersionMenu mImmersionMenu;
  private ImageView mIvBanner;
  private GridView mList;
  private View mLlBanner;
  private String mTextSeparator;
  private TextView mTvBalance;
  private TextView mTvCardName;

  private String getHelpUrl()
  {
    return "http://cdn.fds.api.xiaomi.com/mipay.nextpay/app/help_" + this.mCardInfo.mCardType.toLowerCase() + ".htm";
  }

  private void initAdapter()
  {
    ArrayList localArrayList = new ArrayList();
    List localList1 = Arrays.asList(getResources().getStringArray(2131427331));
    TypedArray localTypedArray = getResources().obtainTypedArray(2131427330);
    List localList2 = Arrays.asList(getResources().getStringArray(2131427332));
    for (int i = 0; i < localList1.size(); i++)
      localArrayList.add(new CardDetailItemInfo(localTypedArray.getResourceId(i, 0), (String)localList1.get(i), (String)localList2.get(i)));
    localTypedArray.recycle();
    this.mAdapter.updateData(localArrayList);
  }

  private void initView(View paramView)
  {
    miui.app.ActionBar localActionBar = (miui.app.ActionBar)getActivity().getActionBar();
    if (localActionBar != null)
    {
      this.mImmersionMenu = ((ImmersionMenu)getActivity().getLayoutInflater().inflate(2130903041, null));
      localActionBar.setCustomView(this.mImmersionMenu, new ActionBar.LayoutParams(-2, -2, 5));
    }
    this.mTvBalance = ((TextView)paramView.findViewById(2131492913));
    this.mBalanceUnit = paramView.findViewById(2131492914);
    this.mTvCardName = ((TextView)paramView.findViewById(2131492915));
    this.mBtnRecharge = paramView.findViewById(2131492911);
    this.mBtnRecharge.setOnClickListener(this);
    this.mList = ((GridView)paramView.findViewById(16908298));
    this.mAdapter = new MoreItemAdapter(getActivity());
    this.mList.setAdapter(this.mAdapter);
    initAdapter();
    this.mList.setOnItemClickListener(this);
    this.mIvBanner = ((ImageView)paramView.findViewById(2131492917));
    this.mLlBanner = paramView.findViewById(2131492916);
    this.mFooter = paramView.findViewById(2131492918);
    this.mFooterContent = ((TextView)paramView.findViewById(2131492919));
    this.mFooterLink = paramView.findViewById(2131492920);
  }

  private void launchOrInstallApp(String paramString)
  {
    Activity localActivity = getActivity();
    if ((localActivity == null) || (localActivity.isFinishing()));
    while (true)
    {
      return;
      PackageManager localPackageManager = localActivity.getPackageManager();
      List localList = localPackageManager.getInstalledPackages(0);
      int i = 0;
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        if (!((PackageInfo)localIterator.next()).packageName.equalsIgnoreCase(paramString))
          continue;
        i = 1;
      }
      if (i != 0)
      {
        startActivity(localPackageManager.getLaunchIntentForPackage(paramString));
        continue;
      }
      UiUtils.startXiaomiMarket(getActivity(), paramString);
    }
  }

  private void onRechargeClicked()
  {
    if (NetworkUtil.isConnected(getActivity()))
    {
      Intent localIntent = new Intent(getActivity(), RechargeActivity.class);
      putCardInfoToIntent(localIntent);
      startActivityForResult(localIntent, 1);
    }
    while (true)
    {
      return;
      new AlertDialog.Builder(getActivity()).setMessage(2131296299).setPositiveButton(17039370, null).create().show();
    }
  }

  private void putCardInfoToIntent(Intent paramIntent)
  {
    Bundle localBundle = getArguments();
    if (localBundle == null)
      localBundle = new Bundle();
    localBundle.putParcelable("card_info", this.mCardInfo);
    paramIntent.putExtras(localBundle);
  }

  private void queryConfig()
  {
    if (this.mConfigRequest != null)
      this.mConfigRequest.cancel();
    this.mConfigRequest = new ConfigRequest(getActivity(), new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        LogUtils.e(paramString);
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        CardDetailFragment.access$402(CardDetailFragment.this, (ConfigInfo)paramArrayOfObject[0]);
        if ((CardDetailFragment.this.mConfigInfo == null) || (CardDetailFragment.this.mConfigInfo.getConfigMap() == null) || (CardDetailFragment.this.getActivity() == null) || (CardDetailFragment.this.getActivity().isFinishing()));
        while (true)
        {
          return;
          ConfigInfo.ConfigItem localConfigItem1 = (ConfigInfo.ConfigItem)CardDetailFragment.this.mConfigInfo.getConfigMap().get("BANNER_IMG");
          if (localConfigItem1 != null)
            ImageLoader.getInstance().displayImage(localConfigItem1.mContent, CardDetailFragment.this.mIvBanner, new SimpleImageLoadingListener()
            {
              public void onLoadingComplete(String paramString, View paramView, Bitmap paramBitmap)
              {
                CardDetailFragment.this.mLlBanner.setVisibility(0);
              }
            });
          ConfigInfo.ConfigItem localConfigItem2 = (ConfigInfo.ConfigItem)CardDetailFragment.this.mConfigInfo.getConfigMap().get("BANNER_LINK");
          if (localConfigItem2 != null)
            CardDetailFragment.this.mIvBanner.setOnClickListener(new View.OnClickListener(localConfigItem2)
            {
              public void onClick(View paramView)
              {
                WebViewActivity.showWebPage(CardDetailFragment.this.getActivity(), this.val$bannerLink.mContent);
              }
            });
          ConfigInfo.ConfigItem localConfigItem3 = (ConfigInfo.ConfigItem)CardDetailFragment.this.mConfigInfo.getConfigMap().get("DETAIL_FOOTER");
          if (localConfigItem3 == null)
            continue;
          CardDetailFragment.this.mFooter.setVisibility(0);
          CardDetailFragment.this.mFooterContent.setText(localConfigItem3.mContent);
          ConfigInfo.ConfigItem localConfigItem4 = (ConfigInfo.ConfigItem)CardDetailFragment.this.mConfigInfo.getConfigMap().get("DETAIL_FOOTER_LINK");
          if (localConfigItem4 == null)
            continue;
          CardDetailFragment.this.mFooterLink.setOnClickListener(new View.OnClickListener(localConfigItem4)
          {
            public void onClick(View paramView)
            {
              CardDetailFragment.this.launchOrInstallApp(this.val$footerLink.mContent);
            }
          });
        }
      }
    });
    VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(this.mConfigRequest);
  }

  private void refreshCardName()
  {
    if (this.mCardInfo != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      if (!TextUtils.isEmpty(this.mCardInfo.mCardName))
        localStringBuilder.append(this.mCardInfo.mCardName);
      int i = 0;
      if (TextUtils.equals(this.mCardInfo.mAid, this.mDefaultCardAId))
      {
        i = 1;
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(this.mTextSeparator);
        localStringBuilder.append(getActivity().getString(2131296505));
      }
      if (!TextUtils.isEmpty(this.mCardInfo.mCardNo))
      {
        if (i == 0)
        {
          String str2 = getActivity().getString(2131296422);
          if (localStringBuilder.length() > 0)
            str2 = this.mTextSeparator + str2;
          localStringBuilder.append(str2);
        }
        localStringBuilder.append(" " + this.mCardInfo.mCardNo);
      }
      String str1 = localStringBuilder.toString();
      int j = this.mTextSeparator.length();
      int k = 0;
      ArrayList localArrayList = new ArrayList();
      SpannableString localSpannableString;
      while (true)
      {
        int m = str1.indexOf(this.mTextSeparator, k);
        if (m == -1)
        {
          int n = getResources().getColor(2131165280);
          localSpannableString = new SpannableString(str1);
          Iterator localIterator = localArrayList.iterator();
          while (localIterator.hasNext())
          {
            int i1 = ((Integer)localIterator.next()).intValue();
            localSpannableString.setSpan(new ForegroundColorSpan(n), i1, i1 + j, 17);
          }
        }
        localArrayList.add(Integer.valueOf(m));
        k = m + j;
      }
      this.mTvCardName.setText(localSpannableString);
    }
  }

  private void showContentView()
  {
    if (this.mCardInfo != null)
    {
      TextView localTextView = this.mTvBalance;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Float.valueOf(this.mCardInfo.mCardBalance / 100.0F);
      localTextView.setText(String.format("%.2f", arrayOfObject));
      this.mBalanceUnit.setVisibility(0);
      refreshCardName();
    }
  }

  public void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mCardInfoChangeReceiver = new CardInfoChangeReceiver(null);
    this.mTextSeparator = getString(2131296421);
    LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(this.mCardInfoChangeReceiver, new IntentFilter("com.xiaomi.tsmclient.action.UPDATE_CARD_INFO"));
  }

  protected boolean isCardInfoSanity(CardInfo paramCardInfo)
  {
    if (paramCardInfo.mStatus != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    getActivity().getActionBar().setTitle(getActivity().getString(2131296266));
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
      Activity localActivity = getActivity();
      if (localActivity == null)
        continue;
      localActivity.setResult(paramInt2, paramIntent);
      localActivity.finish();
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131492911:
    }
    while (true)
    {
      return;
      onRechargeClicked();
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131623937, paramMenu);
    return true;
  }

  public void onDestroy()
  {
    LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).unregisterReceiver(this.mCardInfoChangeReceiver);
    if (this.mConfigRequest != null)
      this.mConfigRequest.cancel();
    super.onDestroy();
  }

  protected void onGotCardProduct(boolean paramBoolean)
  {
    showContentView();
  }

  protected void onGotDefaultCardAId(String paramString)
  {
    refreshCardName();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903050, paramViewGroup, false);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("card_type", this.mCardInfo.mCardType);
    if (paramInt == 0)
    {
      AnalyticManager.getInstance().trackClick(getActivity(), "click_purchase_record", this.mCardInfo.mCardType, null);
      AnalyticManager.recordEvent("common", "click_purchase_record", localHashMap);
      Intent localIntent = new Intent(getActivity(), CardPurchaseRecordActivity.class);
      putCardInfoToIntent(localIntent);
      startActivity(localIntent);
    }
    while (true)
    {
      return;
      if (paramInt == 1)
      {
        AnalyticManager.getInstance().trackClick(getActivity(), "click_help", this.mCardInfo.mCardType, null);
        AnalyticManager.recordEvent("common", "click_help", localHashMap);
        WebViewActivity.showWebPage(getActivity(), getHelpUrl(), this.mCardInfo);
        continue;
      }
    }
  }

  protected void onNFCDisable()
  {
    super.onNFCDisable();
    if (isCardInfoSanity(this.mCardInfo))
      showContentView();
  }

  protected void onNFCEnable()
  {
    super.onNFCEnable();
    if (!isCardInfoSanity(this.mCardInfo))
    {
      if (!TextUtils.isEmpty(this.mCardInfo.mCardName))
        showContentView();
      this.mBtnRecharge.setEnabled(false);
      fetchCardProduct(new BaseTransCardFragment.SimpleFetchCardProductListener()
      {
        public void onGotCardInfoOnMainThread(CardInfo paramCardInfo)
        {
          if (TextUtils.equals(paramCardInfo.mCardType, CardDetailFragment.this.mCardInfo.mCardType))
          {
            CardDetailFragment.this.mCardInfo = paramCardInfo;
            CardDetailFragment.this.showContentView();
            if (paramCardInfo.mStatus != null)
              CardDetailFragment.this.mBtnRecharge.setEnabled(true);
          }
        }
      }
      , this.mCardInfo.mCardType);
    }
    while (true)
    {
      return;
      showContentView();
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i == 2131493116)
    {
      Intent localIntent1 = new Intent(getActivity(), SetDefaultCardActivity.class);
      localIntent1.putExtra("tag_default_card_aid", this.mDefaultCardAId);
      startActivity(localIntent1);
    }
    while (true)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      if (i == 2131493115)
      {
        Intent localIntent2 = new Intent(getActivity(), MainActivity.class);
        localIntent2.putExtra("tag_default_card_aid", this.mDefaultCardAId);
        localIntent2.setFlags(603979776);
        startActivity(localIntent2);
        continue;
      }
      if (i != 2131493114)
        continue;
      Intent localIntent3 = new Intent(getActivity(), IssuedTransCardListActivity.class);
      localIntent3.putExtra("tag_default_card_aid", this.mDefaultCardAId);
      localIntent3.setFlags(603979776);
      startActivity(localIntent3);
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    initView(paramView);
    queryConfig();
    if (TextUtils.isEmpty(this.mDefaultCardAId))
      getDefaultCard();
  }

  public void setDefaultCard(String paramString)
  {
    this.mDefaultCardAId = paramString;
    refreshCardName();
  }

  private class CardInfoChangeReceiver extends BroadcastReceiver
  {
    private CardInfoChangeReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (!UiUtils.isFragmentValid(CardDetailFragment.this));
      while (true)
      {
        return;
        if (TextUtils.equals("com.xiaomi.tsmclient.action.UPDATE_CARD_INFO", paramIntent.getAction()))
        {
          CardInfo localCardInfo = (CardInfo)paramIntent.getParcelableExtra("card_info");
          if ((localCardInfo == null) || (!TextUtils.equals(localCardInfo.mCardType, CardDetailFragment.this.mCardInfo.mCardType)))
            continue;
          CardDetailFragment.this.mCardInfo = localCardInfo;
          CardDetailFragment.this.showContentView();
          continue;
        }
      }
    }
  }

  public static class CardDetailItemInfo
  {
    private int mIconResourceId;
    private String mIconUrl;
    private String mSummary;
    private String mTitle;

    public CardDetailItemInfo(int paramInt, String paramString1, String paramString2)
    {
      this.mIconResourceId = paramInt;
      this.mTitle = paramString1;
      this.mSummary = paramString2;
    }

    public CardDetailItemInfo(String paramString1, String paramString2, String paramString3)
    {
      this.mIconUrl = paramString1;
      this.mTitle = paramString2;
      this.mSummary = paramString3;
    }

    public int getIconResourceId()
    {
      return this.mIconResourceId;
    }

    public String getIconUrl()
    {
      return this.mIconUrl;
    }

    public String getSummary()
    {
      return this.mSummary;
    }

    public String getTitle()
    {
      return this.mTitle;
    }

    public void setIconResourceId(int paramInt)
    {
      this.mIconResourceId = paramInt;
    }

    public void setIconUrl(String paramString)
    {
      this.mIconUrl = paramString;
    }

    public void setSummary(String paramString)
    {
      this.mSummary = paramString;
    }

    public void setTitle(String paramString)
    {
      this.mTitle = paramString;
    }
  }

  private static class MoreItemAdapter extends ArrayAdapter<CardDetailFragment.CardDetailItemInfo>
  {
    private LayoutInflater mInflater = LayoutInflater.from(this.mContext);

    public MoreItemAdapter(Context paramContext)
    {
      super();
    }

    public void bindData(View paramView, int paramInt, CardDetailFragment.CardDetailItemInfo paramCardDetailItemInfo)
    {
      ViewHolder localViewHolder = (ViewHolder)paramView.getTag();
      if (paramCardDetailItemInfo.getIconResourceId() != 0)
        localViewHolder.mIvIcon.setImageResource(paramCardDetailItemInfo.getIconResourceId());
      while (true)
      {
        localViewHolder.mTvTitle.setText(paramCardDetailItemInfo.getTitle());
        localViewHolder.mTvSummary.setText(paramCardDetailItemInfo.getSummary());
        return;
        ImageLoader.getInstance().displayImage(paramCardDetailItemInfo.getIconUrl(), localViewHolder.mIvIcon);
      }
    }

    public View newView(Context paramContext, CardDetailFragment.CardDetailItemInfo paramCardDetailItemInfo, ViewGroup paramViewGroup)
    {
      View localView = this.mInflater.inflate(2130903051, null);
      ViewHolder localViewHolder = new ViewHolder(null);
      localViewHolder.mIvIcon = ((ImageView)localView.findViewById(2131492921));
      localViewHolder.mTvTitle = ((TextView)localView.findViewById(2131492876));
      localViewHolder.mTvSummary = ((TextView)localView.findViewById(2131492922));
      localView.setTag(localViewHolder);
      return localView;
    }

    private static final class ViewHolder
    {
      ImageView mIvIcon;
      TextView mTvSummary;
      TextView mTvTitle;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardDetailFragment
 * JD-Core Version:    0.6.0
 */