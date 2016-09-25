package com.miui.tsmclient.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.tsmclient.SEInteractionService;
import com.miui.tsmclient.SEInteractionService.LocalBinder;
import com.miui.tsmclient.SEInteractionService.SEInteractionListener;
import com.miui.tsmclient.SEInteractionService.SEInteractionState.Type;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfo.ServiceStatus;
import com.miui.tsmclient.entity.CardUIInfo;
import com.miui.tsmclient.entity.gson.ConfigInfo;
import com.miui.tsmclient.entity.gson.ConfigInfo.ConfigItem;
import com.miui.tsmclient.model.CardManager;
import com.miui.tsmclient.model.Issuer;
import com.miui.tsmclient.model.Issuer.IssuerFactory;
import com.miui.tsmclient.net.VolleySingleton;
import com.miui.tsmclient.net.request.ConfigRequest;
import com.miui.tsmclient.ui.widget.IndicatorBannerView;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import miui.app.ActionBar;
import miui.app.ProgressDialog;
import miui.widget.ProgressBar;
import org.json.JSONArray;
import org.json.JSONException;

public class CardIntroFragment extends BaseTransCardFragment
{
  private static final int DIALOG_ID_CARD_HAS_ISSUE = 1;
  private static final int DIALOG_ID_CONFLICT_APP = 0;
  private static final int DIALOG_ID_NO_STOCK = 2;
  public static final int MSG_INTERACTION_FINISHED = 3;
  public static final int MSG_INTERACTION_PROGRESS_CHANGED = 2;
  public static final int MSG_INTERACTION_START = 1;
  public static final int MSG_QUERY_CARD_FINISHED = 5;
  public static final int MSG_SUCCESS_FOR_RESULT = 4;
  private ImageView mBgIssueView;
  protected Button mBtnAccept;
  private CardInfoChangeReceiver mCardInfoChangeReceiver;
  protected CardManager mCardManager;
  private CheckBox mCheckBox;
  private CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener()
  {
    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      if (paramCompoundButton == CardIntroFragment.this.mCheckBox)
        CardIntroFragment.this.mBtnAccept.setEnabled(paramBoolean);
    }
  };
  private View.OnClickListener mClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131492948:
      case 2131492949:
      case 2131492951:
      default:
      case 2131492947:
      case 2131492950:
      case 2131492952:
      }
      while (true)
      {
        return;
        if (CardIntroFragment.this.mIssuer.hasCardIssuing())
        {
          CardIntroFragment.this.createDialog(0);
          continue;
        }
        CardIntroFragment.this.openCard(CardIntroFragment.this.getActivity());
        continue;
        WebViewActivity.showWebPage(CardIntroFragment.this.getActivity(), CardIntroFragment.this.getProtocolUrl(), CardIntroFragment.this.mCardInfo);
        continue;
        CardIntroFragment.this.getActivity().finish();
      }
    }
  };
  private ConfigInfo mConfigInfo;
  private ConfigRequest mConfigRequest;
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      CardIntroFragment.access$302(CardIntroFragment.this, ((SEInteractionService.LocalBinder)paramIBinder).getService());
      CardIntroFragment.this.registerListener();
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      CardIntroFragment.this.unbindService();
    }
  };
  private IndicatorBannerView mIndicatorBannerView;
  protected Issuer mIssuer;
  private RelativeLayout mLayoutBottom;
  private View mLayoutContent;
  private View mLayoutError;
  private View mLayoutErrorStatusBar;
  private View mLayoutProgress;
  protected LinearLayout mLayoutProtocols;
  private ProgressBar mProgressBar;
  private SEInteractionService.SEInteractionListener mSEInteractionListener = new SEInteractionService.SEInteractionListener()
  {
    public void onFinished(int paramInt)
    {
      CardIntroFragment.this.mHandler.obtainMessage(3, Integer.valueOf(paramInt)).sendToTarget();
    }

    public void onProgressChanged(int paramInt)
    {
      CardIntroFragment.this.mHandler.obtainMessage(2, Integer.valueOf(paramInt)).sendToTarget();
    }

    public void onStart()
    {
      CardIntroFragment.this.mHandler.obtainMessage(1).sendToTarget();
    }
  };
  private SEInteractionService mService;
  protected TextView mTvIntro;
  protected TextView mTvPromotion;
  private TextView mTvProtocols;

  private void bindService()
  {
    android.app.Activity localActivity = getActivity();
    localActivity.bindService(new Intent(localActivity, SEInteractionService.class), this.mConnection, 1);
  }

  private void createDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity()).setTitle(2131296321);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.mCardInfo.mCardName;
      localBuilder.setMessage(getString(2131296324, arrayOfObject)).setPositiveButton(17039370, null).create().show();
      continue;
      new AlertDialog.Builder(getActivity()).setTitle(2131296320).setMessage(2131296322).setNegativeButton(17039360, null).setPositiveButton(2131296273, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          CardIntroFragment.this.getActivity().setResult(-1);
          CardIntroFragment.this.getActivity().finish();
        }
      }).create().show();
      continue;
      new AlertDialog.Builder(getActivity()).setTitle(2131296321).setMessage(2131296325).setNegativeButton(17039360, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          CardIntroFragment.this.onNoStock();
        }
      }).setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          CardIntroFragment.this.getActivity().finish();
        }
      }).create().show();
    }
  }

  private int getDefaultIssueBg()
  {
    int i;
    if (TextUtils.equals(this.mCardInfo.mCardType, "SPTC"))
      i = 2130837545;
    while (true)
    {
      return i;
      if ((TextUtils.equals(this.mCardInfo.mCardType, "LNT")) || (TextUtils.equals(this.mCardInfo.mCardType, "BMAC")))
      {
        i = 2130837544;
        continue;
      }
      i = -1;
    }
  }

  private String getProtocolUrl()
  {
    return "http://cdn.fds.api.xiaomi.com/mipay.nextpay/app/protocols_" + this.mCardInfo.mCardType.toLowerCase() + ".htm";
  }

  private void hideProgressView()
  {
    if ((this.mLayoutProgress != null) && (this.mLayoutProgress.getVisibility() == 0))
    {
      this.mLayoutProgress.setVisibility(8);
      this.mProgressBar.setProgress(0);
    }
    if ((this.mLayoutBottom != null) && (this.mLayoutBottom.getVisibility() != 0))
      this.mLayoutBottom.setVisibility(0);
  }

  private void initView(View paramView)
  {
    this.mBgIssueView = ((ImageView)paramView.findViewById(2131492937));
    this.mBgIssueView.setImageResource(getDefaultIssueBg());
    this.mIndicatorBannerView = ((IndicatorBannerView)paramView.findViewById(2131492938));
    this.mLayoutError = paramView.findViewById(2131492951);
    this.mLayoutContent = paramView.findViewById(2131492936);
    this.mBtnAccept = ((Button)paramView.findViewById(2131492947));
    this.mBtnAccept.setOnClickListener(this.mClickListener);
    this.mCheckBox = ((CheckBox)paramView.findViewById(2131492949));
    this.mCheckBox.setOnCheckedChangeListener(this.mCheckedChangeListener);
    this.mLayoutErrorStatusBar = paramView.findViewById(2131492952);
    this.mLayoutErrorStatusBar.setOnClickListener(this.mClickListener);
    this.mTvIntro = ((TextView)paramView.findViewById(2131492942));
    this.mTvPromotion = ((TextView)paramView.findViewById(2131492941));
    this.mLayoutBottom = ((RelativeLayout)paramView.findViewById(2131492946));
    this.mLayoutProtocols = ((LinearLayout)paramView.findViewById(2131492948));
    this.mTvProtocols = ((TextView)paramView.findViewById(2131492950));
    this.mLayoutProgress = paramView.findViewById(2131492943);
    this.mProgressBar = ((ProgressBar)paramView.findViewById(2131492945));
  }

  private void onNoStock()
  {
    this.mTvPromotion.setVisibility(8);
    this.mLayoutProtocols.setVisibility(8);
    this.mBtnAccept.setEnabled(false);
    this.mBtnAccept.setText(2131296427);
  }

  private void queryConfig()
  {
    if (this.mConfigInfo != null)
      updateBanner(this.mConfigInfo);
    while (true)
    {
      return;
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
          CardIntroFragment.access$802(CardIntroFragment.this, (ConfigInfo)paramArrayOfObject[0]);
          if ((CardIntroFragment.this.mConfigInfo == null) || (CardIntroFragment.this.mConfigInfo.getConfigMap() == null) || (!UiUtils.isFragmentValid(CardIntroFragment.this)));
          while (true)
          {
            return;
            CardIntroFragment.this.updateBanner(CardIntroFragment.this.mConfigInfo);
          }
        }
      });
      VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(this.mConfigRequest);
    }
  }

  private void registerListener()
  {
    this.mService.setSEInteractionListener(this.mSEInteractionListener);
  }

  private void showContentView(boolean paramBoolean)
  {
    View localView1 = this.mLayoutError;
    int i;
    View localView2;
    if (paramBoolean)
    {
      i = 8;
      localView1.setVisibility(i);
      localView2 = this.mLayoutContent;
      if (!paramBoolean)
        break label87;
    }
    ActionBar localActionBar;
    label87: for (int j = 0; ; j = 8)
    {
      localView2.setVisibility(j);
      localActionBar = (ActionBar)getActivity().getActionBar();
      if (paramBoolean)
        break label94;
      ((miui.app.Activity)getActivity()).setTranslucentStatus(1);
      this.mLayoutErrorStatusBar.setPadding(0, UiUtils.getStatusBarHeight(getActivity()), 0, 0);
      return;
      i = 0;
      break;
    }
    label94: if ((this.mCardInfo.mCardUIInfo != null) && (!TextUtils.isEmpty(this.mCardInfo.mCardUIInfo.mPreIssuedDetailBgUrl)))
      ImageLoader.getInstance().displayImage(this.mCardInfo.mCardUIInfo.mPreIssuedDetailBgUrl, this.mBgIssueView);
    if (localActionBar != null)
      localActionBar.setTitle(this.mCardInfo.mCardName);
    if (this.mCardInfo.mCardUIInfo != null)
      this.mTvIntro.setText(this.mCardInfo.mCardUIInfo.mCardDetailDesc);
    this.mTvProtocols.setOnClickListener(this.mClickListener);
    TextView localTextView = this.mTvProtocols;
    Object[] arrayOfObject1 = new Object[1];
    Object[] arrayOfObject2 = new Object[1];
    if (this.mCardInfo.mCardName == null);
    for (String str = ""; ; str = this.mCardInfo.mCardName)
    {
      arrayOfObject2[0] = str;
      arrayOfObject1[0] = getString(2131296429, arrayOfObject2);
      localTextView.setText(String.format("《%s》", arrayOfObject1));
      this.mTvProtocols.getPaint().setFlags(8);
      if (this.mIssuer.isCurrentCardIssuing(this.mCardInfo.mCardType))
        showProgressView();
      if (this.mCardInfo.mServiceStatus == CardInfo.ServiceStatus.no_stock)
        onNoStock();
      if (this.mCardInfo.mServiceStatus != CardInfo.ServiceStatus.negative)
        break;
      onServiceUnreachable();
      break;
    }
  }

  private void showProgressView()
  {
    if ((this.mLayoutProgress != null) && (this.mLayoutProgress.getVisibility() != 0))
      this.mLayoutProgress.setVisibility(0);
    if ((this.mLayoutBottom != null) && (this.mLayoutBottom.getVisibility() == 0))
      this.mLayoutBottom.setVisibility(8);
    this.mProgressBar.setProgress(0);
  }

  private void unbindService()
  {
    if (this.mService != null)
      getActivity().unbindService(this.mConnection);
    this.mService = null;
  }

  private void unregisterListener()
  {
    if (this.mService != null)
      this.mService.setSEInteractionListener(null);
  }

  private void updateBanner(ConfigInfo paramConfigInfo)
  {
    if (paramConfigInfo.getConfigMap() == null);
    while (true)
    {
      return;
      ConfigInfo.ConfigItem localConfigItem = (ConfigInfo.ConfigItem)paramConfigInfo.getConfigMap().get("USER_GUIDE_" + this.mCardInfo.mCardType);
      if (localConfigItem == null)
        localConfigItem = (ConfigInfo.ConfigItem)paramConfigInfo.getConfigMap().get("USER_GUIDE");
      if (localConfigItem == null)
        continue;
      try
      {
        JSONArray localJSONArray = new JSONArray(localConfigItem.mContent);
        int i = localJSONArray.length();
        if (i < 1)
          continue;
        ArrayList localArrayList = new ArrayList();
        for (int j = 0; j < i; j++)
          localArrayList.add(localJSONArray.getString(j));
        this.mBgIssueView.setVisibility(4);
        this.mIndicatorBannerView.updateData(localArrayList);
      }
      catch (JSONException localJSONException)
      {
        LogUtils.e("parsing user guide failed", localJSONException);
      }
    }
  }

  public void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    bindService();
    setThemeRes(2131361845);
    this.mCardManager = new CardManager(getActivity().getApplicationContext());
    this.mCardInfoChangeReceiver = new CardInfoChangeReceiver(null);
    LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(this.mCardInfoChangeReceiver, new IntentFilter("com.xiaomi.tsmclient.action.UPDATE_CARD_INFO"));
    Bundle localBundle = getArguments();
    this.mIssuer = Issuer.IssuerFactory.makeIssuer(this, this.mCardInfo, localBundle);
    if (isCardInfoSanity(this.mCardInfo))
    {
      if (!this.mIssuer.shouldAutoIssue())
        break label122;
      openCard(getActivity());
    }
    while (true)
    {
      return;
      label122: this.mIssuer.preIssue();
    }
  }

  public Handler getHandler()
  {
    return this.mHandler;
  }

  protected void handleInteractionFailure(int paramInt)
  {
    hideProgressView();
    if (this.mIssuer.shouldAutoIssue())
      this.mCheckBox.setChecked(true);
    if (paramInt == 9)
      createDialog(0);
    while (true)
    {
      return;
      if (paramInt == 2001)
      {
        createDialog(2);
        continue;
      }
      android.app.Activity localActivity = getActivity();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      UiUtils.showToast(localActivity, getString(2131296301, arrayOfObject));
    }
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
      if (this.mIssuer.getInteractionType() != SEInteractionService.SEInteractionState.Type.INSTALL_APP)
        continue;
      showProgressView();
      continue;
      int j = ((Integer)paramMessage.obj).intValue();
      if (this.mIssuer.getInteractionType() != SEInteractionService.SEInteractionState.Type.INSTALL_APP)
        continue;
      this.mProgressBar.setProgress(j);
      continue;
      int i = ((Integer)paramMessage.obj).intValue();
      dismissDialog();
      if (i == 0)
      {
        if (this.mIssuer.getInteractionType() != SEInteractionService.SEInteractionState.Type.INSTALL_APP)
          continue;
        this.mCardInfo.mHasIssue = true;
        paramActivity.setResult(-1);
        hideProgressView();
        this.mIssuer.postIssue();
        continue;
      }
      handleInteractionFailure(i);
      continue;
      Intent localIntent2 = new Intent(paramActivity, TSMResultActivity.class);
      localIntent2.putExtra(TSMResultFragment.KEY_INTENT_FROM, TSMResultFragment.FROM_OPEN_CARD);
      localIntent2.putExtra("card_info", this.mCardInfo);
      localIntent2.putExtras(localIntent2);
      startActivityForResult(localIntent2, 2);
      continue;
      CardInfo localCardInfo = (CardInfo)paramMessage.obj;
      dismissDialog();
      if ((paramMessage.arg1 == 0) && (localCardInfo != null) && (localCardInfo.mHasIssue))
      {
        this.mCardInfo.mHasIssue = true;
        createDialog(1);
        Intent localIntent1 = new Intent("com.xiaomi.tsmclient.action.UPDATE_CARD_LIST");
        localIntent1.putExtra("card_info", this.mCardInfo);
        localIntent1.putExtra("action_type", 1);
        LocalBroadcastManager.getInstance(paramActivity.getApplicationContext()).sendBroadcast(localIntent1);
        continue;
      }
      if (this.mIssuer.shouldAutoIssue())
      {
        this.mIssuer.issue();
        continue;
      }
      this.mIssuer.preIssue();
    }
  }

  protected boolean isCardInfoSanity(CardInfo paramCardInfo)
  {
    if (!TextUtils.isEmpty(paramCardInfo.mCardName));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected boolean needLogin()
  {
    return true;
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      if (paramInt2 == -1)
      {
        this.mCardInfo = ((CardInfo)paramIntent.getParcelableExtra("card_info"));
        this.mIssuer.onPreIssueFinished(paramIntent);
        if (!this.mIssuer.shouldAutoIssue())
          continue;
        showProgressView();
        this.mIssuer.issue();
        continue;
      }
      if (paramInt2 != 0)
        continue;
      getActivity().finish();
      continue;
      if (paramInt2 != -1)
        continue;
      PrefUtils.remove(getActivity(), "transcard_num");
      getActivity().setResult(-1, paramIntent);
      getActivity().finish();
    }
  }

  public void onDestroy()
  {
    unbindService();
    this.mCardManager.release();
    this.mIssuer.release();
    LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).unregisterReceiver(this.mCardInfoChangeReceiver);
    unregisterListener();
    if (this.mConfigRequest != null)
      this.mConfigRequest.cancel();
    super.onDestroy();
  }

  protected void onGotCardProduct(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mIssuer.updateCardInfo(this.mCardInfo);
      if (this.mIssuer.shouldAutoIssue())
        openCard(getActivity());
      if (this.mIssuer.isCurrentCardIssuing(this.mCardInfo.mCardType))
        this.mCheckBox.setChecked(true);
    }
    showContentView(paramBoolean);
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903057, paramViewGroup, false);
  }

  public void onResume()
  {
    super.onResume();
    if (this.mIssuer.isCurrentCardIssuing(this.mCardInfo.mCardType))
      this.mCheckBox.setChecked(true);
  }

  protected void onServiceUnreachable()
  {
    this.mTvPromotion.setVisibility(8);
    this.mLayoutProtocols.setVisibility(8);
    this.mBtnAccept.setEnabled(false);
    this.mBtnAccept.setText(2131296285);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    initView(paramView);
    queryConfig();
    if (!isCardInfoSanity(this.mCardInfo))
      queryCardProduct();
    while (true)
    {
      return;
      showContentView(true);
    }
  }

  public void openCard(Context paramContext)
  {
    if (this.mIssuer.isCurrentCardIssuing(this.mCardInfo.mCardType));
    while (true)
    {
      return;
      this.mProgressDialog.setCancelable(false);
      showDialog(2131296275);
      this.mCardManager.queryCardInfo(getActivity(), this.mCardInfo, this.mIssuer.buildQueryParams(), new MiTsmCallback()
      {
        public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
        {
          CardIntroFragment.this.mHandler.obtainMessage(5, 0, 0, null).sendToTarget();
        }

        public void onSuccess(int paramInt, Object[] paramArrayOfObject)
        {
          CardIntroFragment.this.mHandler.obtainMessage(5, paramInt, 0, paramArrayOfObject[0]).sendToTarget();
        }
      });
    }
  }

  private class CardInfoChangeReceiver extends BroadcastReceiver
  {
    private CardInfoChangeReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (!UiUtils.isFragmentValid(CardIntroFragment.this));
      while (true)
      {
        return;
        if (TextUtils.equals("com.xiaomi.tsmclient.action.UPDATE_CARD_INFO", paramIntent.getAction()))
        {
          CardInfo localCardInfo = (CardInfo)paramIntent.getParcelableExtra("card_info");
          if ((localCardInfo == null) || (!TextUtils.equals(localCardInfo.mCardType, CardIntroFragment.this.mCardInfo.mCardType)))
            continue;
          CardIntroFragment.this.mCardInfo = localCardInfo;
          continue;
        }
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardIntroFragment
 * JD-Core Version:    0.6.0
 */