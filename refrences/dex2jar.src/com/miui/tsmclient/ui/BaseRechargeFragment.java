package com.miui.tsmclient.ui;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfo.Status;
import com.miui.tsmclient.entity.FeeInfo;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.model.ErrorCode;
import com.miui.tsmclient.model.PayableCardManager;
import com.miui.tsmclient.net.AuthApiException;
import com.miui.tsmclient.net.TSMAuthManager;
import com.miui.tsmclient.pay.IPayRule;
import com.miui.tsmclient.pay.IPayTool;
import com.miui.tsmclient.pay.IPluginAPKPayTool;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.pay.OrderInfo.OrderStatus;
import com.miui.tsmclient.pay.PayToolFactory;
import com.miui.tsmclient.pay.PayType;
import com.miui.tsmclient.ui.widget.ImmersionMenu;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;
import miui.app.ProgressDialog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseRechargeFragment<T extends PayableCardInfo> extends BaseTransCardFragment<T>
{
  private static final String DATA_SCHEME_PACKAGE = "package";
  public static final String EXTRA_NEED_CHECK_CARD_STATUS = "need_check_card_status";
  public static final String KEY_TAG = "nfc_tag";
  private static final int MAX_RETRY_COUNT_FOR_QUERY_ORDER = 1;
  protected static final int MSG_ERROR = 1;
  protected static final int MSG_MIUI_VERSION_RESTRICTED = 8;
  protected static final int MSG_NEED_ISSUE = 6;
  protected static final int MSG_NO_STOCK = 5;
  protected static final int MSG_ORDER_HASNOT_BE_HANDLED = 2;
  protected static final int MSG_PAY_DUPLICATE = 3;
  protected static final int MSG_QUERY_ORDER = 7;
  private static final int TIME_QUERY_DELAY = 500;
  protected int mAmountValue;
  protected Button mBtnPay;
  protected PayableCardManager mCardManager;
  protected View mContentView;
  private BaseRechargeFragment<T>.CreateOrderTask mCreateOrderTask;
  protected FeeInfo mFeeInfo;
  protected Handler mHandler;
  protected boolean mHasClickPay;
  private BaseRechargeFragment<T>.InstallReceiver mInstallReceiver;
  protected View mLayoutError;
  private boolean mNeedAutoPay;
  private boolean mNeedCheckCardStatus;
  protected OrderInfo mOrderInfo;
  protected int mPayResult;
  private IPayTool mPayTool;
  private PayType mPayType;
  private BaseRechargeFragment<T>.QueryOrderTask mQueryOrderTask;
  private BaseRechargeFragment<T>.QueryUnfinishedOrderTask mQueryUnfinishedOrderTask;
  private int mRetryCount;
  protected boolean mShowing;
  protected Tag mTag;

  private void queryUnfinishedOrder()
  {
    if (this.mQueryUnfinishedOrderTask != null)
      this.mQueryUnfinishedOrderTask.cancel(true);
    this.mQueryUnfinishedOrderTask = new QueryUnfinishedOrderTask(null);
    this.mQueryUnfinishedOrderTask.execute(new Void[0]);
  }

  private void startNfcRfchargeActivity(Bundle paramBundle)
  {
    Intent localIntent = new Intent(getActivity(), NfcRechargeActivity.class);
    localIntent.putExtra("nfc_tag", this.mTag);
    localIntent.putExtra("card_info", this.mCardInfo);
    localIntent.putExtras(paramBundle);
    startActivityForResult(localIntent, 1);
  }

  private void startResultActivity()
  {
    Intent localIntent = new Intent(getActivity(), TSMResultActivity.class);
    localIntent.putExtra(TSMResultFragment.KEY_INTENT_FROM, TSMResultFragment.FROM_RECHARGE);
    localIntent.putExtra("card_info", this.mCardInfo);
    startActivityForResult(localIntent, 2);
  }

  protected boolean checkCardStatus()
  {
    int i;
    if (!UiUtils.isFragmentValid(this))
      i = 0;
    while (true)
    {
      return i;
      if (((PayableCardInfo)this.mCardInfo).mStatus == CardInfo.Status.NEGATIVE)
      {
        new AlertDialog.Builder(getActivity()).setTitle(2131296314).setMessage(2131296344).setPositiveButton(2131296318, null).create().show();
        i = 0;
        continue;
      }
      if (((PayableCardInfo)this.mCardInfo).mStatus == CardInfo.Status.IN_BLACKLIST)
      {
        new AlertDialog.Builder(getActivity()).setTitle(2131296314).setMessage(2131296345).setPositiveButton(2131296318, null).create().show();
        i = 0;
        continue;
      }
      if (((PayableCardInfo)this.mCardInfo).mStatus == CardInfo.Status.DATA_ILLEGAL)
      {
        new AlertDialog.Builder(getActivity()).setTitle(2131296314).setMessage(2131296346).setPositiveButton(2131296318, null).create().show();
        i = 0;
        continue;
      }
      i = 1;
    }
  }

  protected boolean checkValue()
  {
    String str = "";
    int i = this.mPayTool.getPayRule().checkPayAmount(100 * this.mAmountValue, ((PayableCardInfo)this.mCardInfo).mCardBalance);
    if (i == 1006)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.mPayTool.getPayRule().getMinPayAmount() / 100);
      str = getString(2131296370, arrayOfObject);
      if (TextUtils.isEmpty(str))
        break label134;
      processRechargeInvalidValue(str);
    }
    label134: for (int j = 0; ; j = 1)
    {
      return j;
      if (i == 1008)
      {
        str = getString(2131296304);
        break;
      }
      if (i != 1013)
        break;
      str = getString(2131296313);
      break;
    }
  }

  protected void clickRechargeBtn()
  {
    this.mHasClickPay = true;
    if ((this.mOrderInfo != null) && (this.mOrderInfo.mOrderStatus == OrderInfo.OrderStatus.paid))
      handleOrder();
    while (true)
    {
      return;
      if ((this.mPayResult == 0) && (this.mOrderInfo != null) && ((this.mOrderInfo.mOrderStatus == null) || (this.mOrderInfo.mOrderStatus == OrderInfo.OrderStatus.waitingpay)))
      {
        queryOrderStatus();
        continue;
      }
      if ((!checkCardStatus()) || (!checkValue()))
        continue;
      if (this.mBtnPay != null)
        this.mBtnPay.setEnabled(false);
      showDialog(2131296375);
      createOrder(null);
    }
  }

  protected void createOrder(Bundle paramBundle)
  {
    Bundle localBundle1 = paramBundle;
    if ((getArguments() != null) && (getArguments().containsKey("cityId")))
    {
      if (localBundle1 == null)
        localBundle1 = new Bundle();
      if (!localBundle1.containsKey("cityId"))
        localBundle1.putInt("cityId", getArguments().getInt("cityId"));
    }
    Bundle localBundle2 = localBundle1;
    if (this.mNeedCheckCardStatus)
      this.mCardManager.queryCardInfo(getActivity(), this.mCardInfo, getArguments(), new MiTsmCallback(localBundle2)
      {
        public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
        {
          BaseRechargeFragment.this.mHandler.obtainMessage(6).sendToTarget();
        }

        public void onSuccess(int paramInt, Object[] paramArrayOfObject)
        {
          CardInfo localCardInfo = (CardInfo)paramArrayOfObject[0];
          if ((localCardInfo != null) && (localCardInfo.mHasIssue))
          {
            BaseRechargeFragment.access$002(BaseRechargeFragment.this, false);
            ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mHasIssue = localCardInfo.mHasIssue;
            BaseRechargeFragment.this.createOrder(this.val$localExtras);
          }
          while (true)
          {
            return;
            BaseRechargeFragment.this.mHandler.obtainMessage(6).sendToTarget();
          }
        }
      });
    while (true)
    {
      return;
      if (this.mCreateOrderTask != null)
        this.mCreateOrderTask.cancel(true);
      this.mCreateOrderTask = new CreateOrderTask(this.mFeeInfo, localBundle1);
      this.mCreateOrderTask.execute(new Void[0]);
    }
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mTag = ((Tag)getArguments().getParcelable("nfc_tag"));
    this.mInstallReceiver = new InstallReceiver();
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addDataScheme("package");
    getActivity().registerReceiver(this.mInstallReceiver, localIntentFilter);
    this.mHandler = new BaseFragment.CardHandler(this, (miui.app.Activity)getActivity());
    this.mCardManager = new PayableCardManager(getActivity().getApplicationContext());
    this.mNeedCheckCardStatus = getArguments().getBoolean("need_check_card_status");
    this.mPayType = PayType.Mipay;
    this.mPayTool = PayToolFactory.getPayTool(this.mPayType);
    if (this.mTag != null)
      queryUnfinishedOrder();
  }

  protected void doIssue(Bundle paramBundle)
  {
    Intent localIntent1 = new Intent("com.xiaomi.tsmclient.action.UPDATE_CARD_LIST");
    localIntent1.putExtra("card_info", this.mCardInfo);
    LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(localIntent1);
    Intent localIntent2 = new Intent();
    localIntent2.putExtra("card_info", this.mCardInfo);
    if (paramBundle != null)
      localIntent2.putExtras(paramBundle);
    getActivity().setResult(-1, localIntent2);
    getActivity().finish();
  }

  protected void getFeesFromServer()
  {
    if ((((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList() != null) && (!((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().isEmpty()))
    {
      this.mFeeInfo = ((FeeInfo)((PayableCardInfo)this.mCardInfo).getActiveFeeInfoList().get(0));
      showContentView();
    }
    while (true)
    {
      return;
      queryCardProduct();
    }
  }

  protected void handleMessage(Message paramMessage, miui.app.Activity paramActivity)
  {
    switch (paramMessage.what)
    {
    case 4:
    default:
    case 1:
    case 2:
    case 3:
    case 5:
    case 6:
    case 7:
    case 8:
    }
    while (true)
    {
      return;
      resetViewToNormalStatus();
      if ((paramMessage.obj instanceof Integer))
      {
        int j = ((Integer)paramMessage.obj).intValue();
        if (j == 1004)
        {
          new AlertDialog.Builder(paramActivity).setTitle(2131296340).setMessage(2131296341).setPositiveButton(2131296318, null).create().show();
          this.mOrderInfo = null;
          continue;
        }
        if (j == 1012)
        {
          new AlertDialog.Builder(paramActivity).setTitle(2131296342).setMessage(2131296343).setPositiveButton(2131296318, null).create().show();
          this.mOrderInfo = null;
          continue;
        }
        Toast.makeText(paramActivity, ErrorCode.findText(paramActivity, j), 0).show();
        continue;
      }
      if (!(paramMessage.obj instanceof String))
        continue;
      Toast.makeText(paramActivity, String.valueOf(paramMessage.obj), 0).show();
      continue;
      dismissDialog();
      Toast.makeText(paramActivity, 2131296373, 0).show();
      continue;
      resetViewToNormalStatus();
      new AlertDialog.Builder(paramActivity).setTitle(2131296314).setMessage(2131296330).setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseRechargeFragment.this.queryUnfinishedOrder();
        }
      }).create().show();
      continue;
      dismissDialog();
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramActivity).setTitle(2131296321);
      if (((PayableCardInfo)this.mCardInfo).mHasIssue);
      for (int i = 2131296326; ; i = 2131296325)
      {
        localBuilder.setMessage(i).setPositiveButton(17039370, null).create().show();
        break;
      }
      resetViewToNormalStatus();
      new AlertDialog.Builder(paramActivity).setTitle(2131296314).setMessage(2131296334).setPositiveButton(17039370, new DialogInterface.OnClickListener(paramActivity)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          Intent localIntent = new Intent(this.val$activity, CardIntroActivity.class);
          ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mHasIssue = false;
          localIntent.putExtra("card_info", BaseRechargeFragment.this.mCardInfo);
          BaseRechargeFragment.this.startActivityForResult(localIntent, 3);
        }
      }).create().show();
      continue;
      queryOrderStatus();
      continue;
      resetViewToNormalStatus();
      new AlertDialog.Builder(paramActivity).setTitle(2131296314).setMessage(2131296339).setPositiveButton(2131296318, null).create().show();
    }
  }

  protected void handleOrder()
  {
    if (((PayableCardInfo)this.mCardInfo).mHasIssue)
      recharge();
    while (true)
    {
      return;
      doIssue(getArguments());
    }
  }

  protected void handlePayResult(Intent paramIntent)
  {
    if (paramIntent == null);
    while (true)
    {
      return;
      this.mPayResult = this.mPayTool.parsePayResult(getActivity(), this.mCardInfo, paramIntent.getExtras());
      if (this.mPayResult != 0)
        continue;
      queryOrderStatus();
    }
  }

  protected void initView(View paramView)
  {
  }

  protected boolean isCardInfoSanity(T paramT)
  {
    if ((paramT.getActiveFeeInfoList() != null) && (!paramT.getActiveFeeInfoList().isEmpty()));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected boolean needLogin()
  {
    return true;
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
      LogUtils.i("No handler for request:" + paramInt1 + " result:" + paramInt2);
    case 1:
    case 2:
    case 3:
    case 424:
    }
    while (true)
    {
      return;
      switch (paramInt2)
      {
      default:
        break;
      case -1:
        getActivity().setResult(-1, paramIntent);
        getActivity().finish();
        break;
      case 0:
        queryUnfinishedOrder();
        continue;
        switch (paramInt2)
        {
        default:
          break;
        case -1:
          getActivity().setResult(-1, paramIntent);
          getActivity().finish();
          continue;
          handlePayResult(paramIntent);
        }
      }
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131623940, paramMenu);
    if ((this.mTag != null) && (((PayableCardInfo)this.mCardInfo).mUnfinishOrderInfos != null));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onDestroy()
  {
    if (!this.mHasClickPay)
    {
      AnalyticManager.getInstance().trackPluginPayStatus(getActivity(), ((PayableCardInfo)this.mCardInfo).mCardType, ((PayableCardInfo)this.mCardInfo).mHasIssue, "union_apk", "not_pay", null);
      HashMap localHashMap = new HashMap();
      localHashMap.put("has_issue", String.valueOf(((PayableCardInfo)this.mCardInfo).mHasIssue));
      localHashMap.put("card_type", ((PayableCardInfo)this.mCardInfo).mCardType);
      localHashMap.put("pay_plugin_type", "union_apk");
      localHashMap.put("pay_result", "not_pay");
      AnalyticManager.recordEvent("pay", "pay_result", localHashMap);
    }
    if (this.mCardManager != null)
      this.mCardManager.release();
    if (this.mCreateOrderTask != null)
      this.mCreateOrderTask.cancel(true);
    if (this.mQueryOrderTask != null)
      this.mQueryOrderTask.cancel(true);
    this.mHandler.removeCallbacksAndMessages(null);
    getActivity().unregisterReceiver(this.mInstallReceiver);
    super.onDestroy();
  }

  protected void onGotCardProduct(boolean paramBoolean)
  {
    if (paramBoolean)
      getFeesFromServer();
    while (true)
    {
      return;
      if (this.mLayoutError != null)
        this.mLayoutError.setVisibility(0);
      if (this.mContentView == null)
        continue;
      this.mContentView.setVisibility(8);
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 2131493119:
    }
    while (true)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      Intent localIntent = new Intent(getActivity(), CardPurchaseRecordActivity.class);
      localIntent.putExtra("nfc_tag", this.mTag);
      localIntent.putExtra("card_info", this.mCardInfo);
      localIntent.putExtra("content_type", "unsolved_order");
      startActivity(localIntent);
    }
  }

  public void onPause()
  {
    super.onPause();
    this.mShowing = false;
  }

  public void onResume()
  {
    super.onResume();
    this.mShowing = true;
    if (this.mNeedAutoPay)
    {
      this.mPayTool.pay(getActivity(), this.mOrderInfo.mOrderId, null);
      this.mNeedAutoPay = false;
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    initView(paramView);
  }

  protected void processRechargeInvalidValue(String paramString)
  {
  }

  protected void queryOrderStatus()
  {
    if (this.mQueryOrderTask != null)
      this.mQueryOrderTask.cancel(true);
    this.mQueryOrderTask = new QueryOrderTask(this.mOrderInfo.mOrderId);
    this.mQueryOrderTask.execute(new Void[0]);
  }

  protected void recharge()
  {
    showDialog(2131296372);
    this.mProgressDialog.setCancelable(false);
    this.mCardManager.recharge(getActivity(), (PayableCardInfo)this.mCardInfo, this.mOrderInfo, this.mTag, new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        LogUtils.w("recharge failed!ErrorCode:" + paramInt + ",errorMsg:" + paramString);
        HashMap localHashMap = new HashMap();
        localHashMap.put("has_issue", String.valueOf(true));
        localHashMap.put("recharge_amount", String.valueOf(BaseRechargeFragment.this.mAmountValue));
        AnalyticManager.getInstance().trackResult(BaseRechargeFragment.this.getActivity(), "recharge", ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mCardType, paramInt, localHashMap);
        localHashMap.put("error_code", Integer.toString(paramInt));
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mCardType;
        AnalyticManager.recordCalculateEvent("pay", String.format("recharge_amount/%s", arrayOfObject), BaseRechargeFragment.this.mAmountValue, localHashMap);
        BaseRechargeFragment.this.mHandler.obtainMessage(1, Integer.valueOf(paramInt)).sendToTarget();
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        LogUtils.d("recharge success!");
        HashMap localHashMap = new HashMap();
        localHashMap.put("has_issue", String.valueOf(true));
        localHashMap.put("recharge_amount", String.valueOf(BaseRechargeFragment.this.mAmountValue));
        AnalyticManager.getInstance().trackResult(BaseRechargeFragment.this.getActivity(), "recharge", ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mCardType, 0, localHashMap);
        localHashMap.put("error_code", Integer.toString(0));
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mCardType;
        AnalyticManager.recordCalculateEvent("pay", String.format("recharge_amount/%s", arrayOfObject), BaseRechargeFragment.this.mAmountValue, localHashMap);
        Intent localIntent = new Intent("com.xiaomi.tsmclient.action.UPDATE_CARD_LIST");
        localIntent.putExtra("tag_card_type", ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mCardType);
        LocalBroadcastManager.getInstance(BaseRechargeFragment.this.getActivity().getApplicationContext()).sendBroadcast(localIntent);
        BaseRechargeFragment.this.startResultActivity();
      }
    });
  }

  protected void resetViewToNormalStatus()
  {
  }

  protected void showContentView()
  {
  }

  private class QueryUnfinishedOrderTask extends AsyncTask<Void, Void, Void>
  {
    private QueryUnfinishedOrderTask()
    {
    }

    protected Void doInBackground(Void[] paramArrayOfVoid)
    {
      Object localObject = null;
      try
      {
        JSONArray localJSONArray = BaseRechargeFragment.this.mTSMAuthManager.queryByUserId(BaseRechargeFragment.this.getActivity());
        localObject = localJSONArray;
        if (localObject == null)
          return null;
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          LogUtils.e("queryByUserId failed!", localIOException);
          continue;
          int i = 0;
          try
          {
            while (i < localObject.length())
            {
              JSONObject localJSONObject = localObject.getJSONObject(i);
              LogUtils.d("queryByUserIdResp", localJSONObject.toString());
              String str = localJSONObject.optString("cardName");
              if (TextUtils.equals(((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mCardType, str))
              {
                OrderInfo localOrderInfo = new OrderInfo();
                localOrderInfo.parse(localJSONObject);
                ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mUnfinishOrderInfos.add(localOrderInfo);
              }
              i++;
            }
          }
          catch (JSONException localJSONException)
          {
            LogUtils.e("parse queryByUserId resp failed!", localJSONException);
          }
        }
      }
    }

    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      BaseRechargeFragment.this.dismissDialog();
      if ((((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mUnfinishOrderInfos == null) || (((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mUnfinishOrderInfos.isEmpty()));
      while (true)
      {
        return;
        if (BaseRechargeFragment.this.mTag == null)
        {
          BaseRechargeFragment.this.mOrderInfo = ((OrderInfo)((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mUnfinishOrderInfos.get(0));
          BaseRechargeFragment.this.handleOrder();
          continue;
        }
        ((miui.app.Activity)BaseRechargeFragment.this.getActivity()).setImmersionMenuEnabled(true);
        ImmersionMenu localImmersionMenu = (ImmersionMenu)BaseRechargeFragment.this.getActivity().getActionBar().getCustomView();
        localImmersionMenu.setVisibility(0);
        localImmersionMenu.setNewIconVisibile(true);
      }
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
      BaseRechargeFragment.this.showDialog(2131296274);
    }
  }

  private class QueryOrderTask extends AsyncTask<Void, Void, OrderInfo>
  {
    private String mOrderId;

    public QueryOrderTask(String arg2)
    {
      Object localObject;
      this.mOrderId = localObject;
    }

    protected OrderInfo doInBackground(Void[] paramArrayOfVoid)
    {
      try
      {
        JSONObject localJSONObject = BaseRechargeFragment.this.mTSMAuthManager.queryByOrderId(BaseRechargeFragment.this.getActivity(), String.valueOf(this.mOrderId));
        if (localJSONObject != null)
        {
          localOrderInfo = new OrderInfo();
          localOrderInfo.parse(localJSONObject);
          return localOrderInfo;
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          LogUtils.e("queryOrderStatus from mipay failed.", localIOException);
          BaseRechargeFragment.this.mHandler.obtainMessage(1, Integer.valueOf(2)).sendToTarget();
          OrderInfo localOrderInfo = null;
        }
      }
      catch (AuthApiException localAuthApiException)
      {
        while (true)
        {
          LogUtils.e("queryOrderStatus from mipay failed. errorcode:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
          if (localAuthApiException.mErrorCode == 2001)
          {
            BaseRechargeFragment.this.mHandler.sendEmptyMessage(5);
            continue;
          }
          BaseRechargeFragment.this.mHandler.obtainMessage(1, localAuthApiException.mErrorMsg).sendToTarget();
        }
      }
    }

    protected void onPostExecute(OrderInfo paramOrderInfo)
    {
      super.onPostExecute(paramOrderInfo);
      BaseRechargeFragment.this.resetViewToNormalStatus();
      if (paramOrderInfo != null)
      {
        BaseRechargeFragment.this.mOrderInfo = paramOrderInfo;
        if (paramOrderInfo.mOrderStatus != OrderInfo.OrderStatus.waitingpay)
          break label104;
        if (BaseRechargeFragment.this.mRetryCount >= 1)
          break label80;
        BaseRechargeFragment.access$608(BaseRechargeFragment.this);
        LogUtils.d("order status has not changed to paid ,so wait a moment and retry.");
        new Timer().schedule(new TimerTask()
        {
          public void run()
          {
            BaseRechargeFragment.this.mHandler.sendEmptyMessage(7);
            cancel();
          }
        }
        , 500L);
      }
      while (true)
      {
        return;
        label80: BaseRechargeFragment.access$602(BaseRechargeFragment.this, 0);
        BaseRechargeFragment.this.mHandler.sendEmptyMessage(2);
        continue;
        label104: if (paramOrderInfo.mOrderStatus != OrderInfo.OrderStatus.paid)
          continue;
        ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mUnfinishOrderInfos.add(paramOrderInfo);
        if (BaseRechargeFragment.this.mTag != null)
        {
          BaseRechargeFragment.this.startNfcRfchargeActivity(new Bundle());
          continue;
        }
        BaseRechargeFragment.this.handleOrder();
      }
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
      BaseRechargeFragment.this.showDialog(2131296371);
    }
  }

  private class CreateOrderTask extends AsyncTask<Void, Void, OrderInfo>
  {
    private Bundle mExtras;
    private FeeInfo mFeeInfo;

    public CreateOrderTask(FeeInfo paramBundle, Bundle arg3)
    {
      this.mFeeInfo = paramBundle;
      Object localObject;
      this.mExtras = localObject;
    }

    protected OrderInfo doInBackground(Void[] paramArrayOfVoid)
    {
      try
      {
        JSONObject localJSONObject = BaseRechargeFragment.this.mTSMAuthManager.createOrder(BaseRechargeFragment.this.getActivity(), this.mFeeInfo.mId, BaseRechargeFragment.this.mCardInfo, this.mExtras);
        if (localJSONObject != null)
        {
          localOrderInfo = new OrderInfo();
          localOrderInfo.parse(localJSONObject);
          LogUtils.d("order create success,orderId:" + localOrderInfo.mOrderId);
          return localOrderInfo;
        }
      }
      catch (NfcEeIOException localNfcEeIOException)
      {
        while (true)
        {
          LogUtils.e("create order from mipay failed caused by nfc. errorCode:" + localNfcEeIOException.getErrorCode(), localNfcEeIOException);
          BaseRechargeFragment.this.mHandler.obtainMessage(1, Integer.valueOf(10)).sendToTarget();
          OrderInfo localOrderInfo = null;
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          LogUtils.e("create order from mipay failed.", localIOException);
          BaseRechargeFragment.this.mHandler.obtainMessage(1, Integer.valueOf(2)).sendToTarget();
        }
      }
      catch (AuthApiException localAuthApiException)
      {
        while (true)
        {
          LogUtils.e("create order from mipay failed. errorcode:" + localAuthApiException.mErrorCode + ",msg:" + localAuthApiException.mErrorMsg, localAuthApiException);
          if (localAuthApiException.mErrorCode == 1000)
          {
            BaseRechargeFragment.this.mHandler.sendEmptyMessage(3);
            continue;
          }
          if (localAuthApiException.mErrorCode == 2001)
          {
            BaseRechargeFragment.this.mHandler.sendEmptyMessage(5);
            continue;
          }
          if (localAuthApiException.mErrorCode == 1011)
          {
            BaseRechargeFragment.this.mHandler.sendEmptyMessage(8);
            continue;
          }
          BaseRechargeFragment.this.mHandler.obtainMessage(1, localAuthApiException.mErrorMsg).sendToTarget();
        }
      }
    }

    protected void onPostExecute(OrderInfo paramOrderInfo)
    {
      super.onPostExecute(paramOrderInfo);
      BaseRechargeFragment.this.resetViewToNormalStatus();
      if (paramOrderInfo == null);
      while (true)
      {
        return;
        if (this.mFeeInfo.mPayFee == 0)
        {
          BaseRechargeFragment.this.mOrderInfo = paramOrderInfo;
          BaseRechargeFragment.this.queryOrderStatus();
          continue;
        }
        int i = BaseRechargeFragment.this.mPayTool.pay(BaseRechargeFragment.this.getActivity(), paramOrderInfo.mPayExtra, null);
        HashMap localHashMap = new HashMap();
        localHashMap.put("has_issue", String.valueOf(((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mHasIssue));
        AnalyticManager.getInstance().trackResult(BaseRechargeFragment.this.getActivity(), "pay", ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mCardType, i, localHashMap);
        localHashMap.put("error_code", Integer.toString(i));
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = ((PayableCardInfo)BaseRechargeFragment.this.mCardInfo).mCardType;
        AnalyticManager.recordEvent("pay", String.format("create_order/%s", arrayOfObject), localHashMap);
        if (i == 0)
        {
          LogUtils.d("order create success,orderId:" + paramOrderInfo.mOrderId);
          BaseRechargeFragment.this.mOrderInfo = paramOrderInfo;
          continue;
        }
        if (i != 1005)
          continue;
        new AlertDialog.Builder(BaseRechargeFragment.this.getActivity()).setTitle(2131296314).setMessage(2131296319).setPositiveButton(17039370, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            ((IPluginAPKPayTool)BaseRechargeFragment.this.mPayTool).downloadPlugin();
          }
        }).setNegativeButton(17039360, null).create().show();
      }
    }
  }

  public class InstallReceiver extends BroadcastReceiver
  {
    public InstallReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str1 = paramIntent.getAction();
      LogUtils.d("InstallReceiver received, action:" + str1);
      Uri localUri;
      if (TextUtils.equals(str1, "android.intent.action.PACKAGE_ADDED"))
      {
        localUri = paramIntent.getData();
        if (localUri != null)
          break label48;
      }
      while (true)
      {
        return;
        label48: String str2 = localUri.getEncodedSchemeSpecificPart();
        if ((!(BaseRechargeFragment.this.mPayTool instanceof IPluginAPKPayTool)) || (!TextUtils.equals(str2, ((IPluginAPKPayTool)BaseRechargeFragment.this.mPayTool).getPluginPackageName())))
          continue;
        if (BaseRechargeFragment.this.mShowing)
        {
          BaseRechargeFragment.this.mPayTool.pay(BaseRechargeFragment.this.getActivity(), BaseRechargeFragment.this.mOrderInfo.mOrderId, null);
          continue;
        }
        BaseRechargeFragment.access$502(BaseRechargeFragment.this, true);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.BaseRechargeFragment
 * JD-Core Version:    0.6.0
 */