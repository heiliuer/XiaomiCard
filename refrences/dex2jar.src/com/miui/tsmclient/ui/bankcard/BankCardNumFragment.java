package com.miui.tsmclient.ui.bankcard;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri.Builder;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.model.ErrorCode;
import com.miui.tsmclient.model.bankcard.BankCardManager;
import com.miui.tsmclient.seitsm.TsmRpcModels.BankCardType;
import com.miui.tsmclient.seitsm.TsmRpcModels.CaptureMethod;
import com.miui.tsmclient.seitsm.TsmRpcModels.CardIssueChannel;
import com.miui.tsmclient.seitsm.TsmRpcModels.CardIssuerInfo;
import com.miui.tsmclient.seitsm.TsmRpcModels.QueryPanResponse;
import com.miui.tsmclient.ui.WebViewActivity;
import com.miui.tsmclient.ui.widget.ExtraButtonEditText;
import com.miui.tsmclient.ui.widget.ExtraButtonEditText.OnExtraButtonClickListener;
import com.miui.tsmclient.ui.widget.ImmersionMenu;
import com.miui.tsmclient.ui.widget.KeyboardObserver;
import com.miui.tsmclient.ui.widget.KeyboardObserver.KeyboardVisibilityListener;
import com.miui.tsmclient.util.FormatterUtils;
import com.miui.tsmclient.util.FormatterUtils.FormatterType;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NfcUtils;
import com.miui.tsmclient.util.StringUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.unionpay.tsmservice.ITsmCallback;
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.tsmservice.UPTsmAddon.UPTsmConnectionListener;
import java.util.concurrent.atomic.AtomicInteger;
import miui.app.ActionBar;
import miui.app.ProgressDialog;

public class BankCardNumFragment extends BaseBankCardFragment
{
  private static final int MSG_CARD_NOT_SUPPORTED = 3;
  private static final int MSG_UP_INIT = 4;
  private static final int REQUEST_CODE_RECOGNIZE_BANK_CARD = 1;
  private static final int REQUEST_VERIFY_CARD_INFO = 1;
  public static final int RESULT_CANCEL_OPEN = 1;
  private final int MSG_COMMON = 1;
  private final int MSG_QUERY_SUCCESS = 2;
  private String URL_SUPPORT_BANK_LIST = "http://static.pay.xiaomi.com/mipay-web/dest/html/b/suppoted_bank_list.html";
  private BankCardManager mBankCardManager;
  private String mBankCardNum;
  private ExtraButtonEditText mCardNumEditText;
  private String mCardNumFromNfc;
  private String mCardNumFromOcr;
  private TextWatcher mCardNumTextWatcher = new TextWatcher()
  {
    int currentNumLength = 0;

    public void afterTextChanged(Editable paramEditable)
    {
      BankCardNumFragment.this.freshNextBtnState(paramEditable, BankCardNumFragment.this.mContractsCheckBox.isChecked());
      if (BankCardNumFragment.this.mCardNumEditText.getText().length() < this.currentNumLength)
      {
        BankCardNumFragment.access$902(BankCardNumFragment.this, "");
        BankCardNumFragment.access$1002(BankCardNumFragment.this, "");
      }
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      this.currentNumLength = BankCardNumFragment.this.mCardNumEditText.getText().length();
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }
  };
  private CheckBox mContractsCheckBox;
  private Bundle mData;
  private boolean mFromFast;
  private boolean mHasGo = false;
  private boolean mHasToast = false;
  private ImmersionMenu mImmersionMenu;
  private KeyboardObserver mKeyboardObserver;
  private KeyboardObserver.KeyboardVisibilityListener mKeyboardVisibilityListener = new KeyboardObserver.KeyboardVisibilityListener()
  {
    public void onKeyboardVisibilityChanged(boolean paramBoolean)
    {
      TextView localTextView = BankCardNumFragment.this.mQuickPassTv;
      if (paramBoolean);
      for (int i = 8; ; i = 0)
      {
        localTextView.setVisibility(i);
        return;
      }
    }
  };
  private TextView mMaskCardNumTv;
  private Button mNextBtn;
  private View.OnClickListener mNextClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      BankCardNumFragment.access$1102(BankCardNumFragment.this, false);
      Bundle localBundle = new Bundle();
      if (BankCardNumFragment.this.mFromFast)
        localBundle.putLong("bindId", BankCardNumFragment.this.mData.getLong("bind_id"));
      while (true)
      {
        BankCardNumFragment.this.showDialog(2131296275);
        BankCardNumFragment.this.mBankCardManager.queryPan(BankCardNumFragment.this.getActivity(), BankCardNumFragment.this.mCardInfo, localBundle, new MiTsmCallback()
        {
          public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
          {
            BankCardNumFragment.this.mQueryResult.set(-1);
            BankCardNumFragment.access$1102(BankCardNumFragment.this, true);
            BankCardNumFragment.this.mHandler.obtainMessage(1, paramInt, 0).sendToTarget();
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = "queryPan";
            AnalyticManager.recordEvent("bank", String.format("operation_%s_failed", arrayOfObject));
          }

          public void onSuccess(int paramInt, Object[] paramArrayOfObject)
          {
            BankCardNumFragment.this.mQueryResult.set(1);
            BankCardNumFragment.this.mHandler.obtainMessage(2, paramArrayOfObject[0]).sendToTarget();
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = "queryPan";
            AnalyticManager.recordEvent("bank", String.format("operation_%s_success", arrayOfObject));
          }
        });
        while (true)
        {
          return;
          BankCardNumFragment.access$1402(BankCardNumFragment.this, FormatterUtils.clean(BankCardNumFragment.this.mCardNumEditText.getText().toString()));
          if ((StringUtils.checkLength(BankCardNumFragment.this.mBankCardNum, 15, 19)) && (StringUtils.isBankCardValid(BankCardNumFragment.this.mBankCardNum)))
            break;
          UiUtils.showToast(BankCardNumFragment.this.getActivity(), BankCardNumFragment.this.getString(2131296489));
        }
        ((BankCardInfo)BankCardNumFragment.this.mCardInfo).mBankCardPan = BankCardNumFragment.this.mBankCardNum;
        localBundle.putString("bin", BankCardNumFragment.this.mBankCardNum.substring(0, 6));
        localBundle.putString("pan", ((BankCardInfo)BankCardNumFragment.this.mCardInfo).mBankCardPan);
      }
    }
  };
  private ExtraButtonEditText.OnExtraButtonClickListener mOnExtraButtonClickListener = new ExtraButtonEditText.OnExtraButtonClickListener()
  {
    public void onClick()
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      Uri.Builder localBuilder = new Uri.Builder();
      localBuilder.scheme("https");
      localBuilder.authority("app.mipay.com");
      localBuilder.appendQueryParameter("id", "mipay.ocr");
      localIntent.setData(localBuilder.build());
      BankCardNumFragment.this.startActivityForResult(localIntent, 1);
    }
  };
  private AtomicInteger mQueryResult = new AtomicInteger(0);
  private TextView mQuickPassTv;
  private AtomicInteger mUpIsInit = new AtomicInteger(0);
  private UPTsmAddon mUpTsmAddon;
  UPTsmAddon.UPTsmConnectionListener mUpTsmConnectionListener = new UPTsmAddon.UPTsmConnectionListener()
  {
    public void onTsmConnected()
    {
      LogUtils.i("uptsm addon bind onTsmConnected");
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = "bindUPT";
      AnalyticManager.recordEvent("bank", String.format("operation_%s_success", arrayOfObject1));
      try
      {
        BankCardNumFragment.this.initUpEnv();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
        {
          LogUtils.d("uptsm init error, RemoteException");
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = "bindUPT";
          AnalyticManager.recordEvent("bank", String.format("operation_%s_failed", arrayOfObject2));
        }
      }
    }

    public void onTsmDisconnected()
    {
      LogUtils.i("uptsm addon bind onTsmDisconnected");
    }
  };

  private void freshNextBtnState(Editable paramEditable, boolean paramBoolean)
  {
    if ((!TextUtils.isEmpty(paramEditable)) && (paramBoolean))
      this.mNextBtn.setEnabled(true);
    while (true)
    {
      return;
      this.mNextBtn.setEnabled(false);
    }
  }

  private void initUpEnv()
    throws RemoteException
  {
    this.mUpTsmAddon.init(null, new ITsmCallback()
    {
      public IBinder asBinder()
      {
        return null;
      }

      public void onError(String paramString1, String paramString2)
        throws RemoteException
      {
        BankCardNumFragment.this.mUpIsInit.set(-1);
        LogUtils.i("uptsm init error , code " + paramString1 + ", msg : " + paramString2);
        BankCardNumFragment.this.mHandler.obtainMessage(4).sendToTarget();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = "initUpEnv";
        AnalyticManager.recordEvent("bank", String.format("operation_%s_failed", arrayOfObject));
      }

      public void onResult(Bundle paramBundle)
        throws RemoteException
      {
        BankCardNumFragment.this.mUpIsInit.set(1);
        LogUtils.i("uptsm init success");
        BankCardNumFragment.this.mHandler.obtainMessage(4).sendToTarget();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = "initUpEnv";
        AnalyticManager.recordEvent("bank", String.format("operation_%s_success", arrayOfObject));
      }
    });
  }

  private void startVerifyActivity()
  {
    Intent localIntent;
    if (!this.mHasGo)
    {
      this.mHasGo = true;
      localIntent = new Intent(getActivity(), VerifyBankCardInfoActivity.class);
      localIntent.putExtra("card_info", this.mCardInfo);
      if (!TextUtils.equals(this.mBankCardNum, this.mCardNumFromNfc))
        break label68;
      localIntent.putExtra("capture_method", TsmRpcModels.CaptureMethod.NFC);
    }
    while (true)
    {
      startActivityForResult(localIntent, 1);
      return;
      label68: if (TextUtils.equals(this.mBankCardNum, this.mCardNumFromOcr))
      {
        localIntent.putExtra("capture_method", TsmRpcModels.CaptureMethod.CAMERA);
        continue;
      }
      localIntent.putExtra("capture_method", TsmRpcModels.CaptureMethod.MANUAL);
    }
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    setImmersionMenuEnabled(true);
    this.mData = getArguments();
    this.mBankCardManager = new BankCardManager(getActivity());
    this.mProgressDialog.setCancelable(false);
    this.mKeyboardObserver = new KeyboardObserver(getActivity());
    this.mKeyboardObserver.setKeyboardListener(this.mKeyboardVisibilityListener);
    this.mUpTsmAddon = UPTsmAddon.getInstance(getActivity());
    this.mUpTsmAddon.addConnectionListener(this.mUpTsmConnectionListener);
    this.mUpTsmAddon.bind();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = "BankCardNumFragment";
    AnalyticManager.recordEvent("bank", String.format("key_enter/%s", arrayOfObject));
  }

  protected void doNewIntent(Intent paramIntent)
  {
    Tag localTag = (Tag)paramIntent.getParcelableExtra("android.nfc.extra.TAG");
    try
    {
      this.mBankCardNum = NfcUtils.getBankCardNum(IsoDep.get(localTag));
      if (this.mBankCardNum != null)
      {
        this.mCardNumEditText.setText(this.mBankCardNum);
        this.mCardNumEditText.setSelection(this.mCardNumEditText.getText().length());
        this.mCardNumFromNfc = this.mBankCardNum;
        this.mCardNumFromOcr = "";
      }
      return;
    }
    catch (UnProcessableCardException localUnProcessableCardException)
    {
      while (true)
        UiUtils.showToast(getActivity(), 2131296473);
    }
  }

  protected void handleMessage(Message paramMessage, miui.app.Activity paramActivity)
  {
    super.handleMessage(paramMessage, paramActivity);
    switch (paramMessage.what)
    {
    default:
    case 2:
    case 1:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      TsmRpcModels.QueryPanResponse localQueryPanResponse = (TsmRpcModels.QueryPanResponse)paramMessage.obj;
      ((BankCardInfo)this.mCardInfo).mBankCardType = localQueryPanResponse.getCardType().getNumber();
      TsmRpcModels.CardIssuerInfo localCardIssuerInfo = localQueryPanResponse.getCardIssuerInfo();
      ((BankCardInfo)this.mCardInfo).mIssuerChannel = localCardIssuerInfo.getCardIssueChannel().getNumber();
      ((BankCardInfo)this.mCardInfo).mIssuerId = localCardIssuerInfo.getIssuerId();
      ((BankCardInfo)this.mCardInfo).mBankName = localCardIssuerInfo.getBankName();
      ((BankCardInfo)this.mCardInfo).mBankLogoUrl = localCardIssuerInfo.getLogoUrl();
      ((BankCardInfo)this.mCardInfo).mUserTerms = localQueryPanResponse.getUserTerms();
      if (((BankCardInfo)this.mCardInfo).mIssuerChannel == 1)
      {
        if (this.mUpIsInit.get() == 0)
          continue;
        if (this.mUpIsInit.get() == -1)
        {
          try
          {
            initUpEnv();
          }
          catch (RemoteException localRemoteException)
          {
            LogUtils.d("uptsm init error, RemoteException");
          }
          continue;
        }
      }
      dismissDialog();
      startVerifyActivity();
      continue;
      dismissDialog();
      if (this.mFromFast)
        this.mNextBtn.setEnabled(true);
      if (paramMessage.arg1 == 0)
      {
        UiUtils.showToast(paramActivity, getString(2131296301));
        continue;
      }
      UiUtils.showToast(paramActivity, ErrorCode.findText(paramActivity, paramMessage.arg1));
      continue;
      dismissDialog();
      UiUtils.showToast(paramActivity, getString(2131296473));
      continue;
      if (this.mQueryResult.get() == 0)
        continue;
      dismissDialog();
      if ((this.mQueryResult.get() == -1) || (this.mUpIsInit.get() == -1))
      {
        if (this.mHasToast)
          continue;
        UiUtils.showToast(paramActivity, getString(2131296301));
        this.mHasToast = true;
        continue;
      }
      startVerifyActivity();
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1) && (paramIntent != null))
    {
      Bundle localBundle = paramIntent.getBundleExtra("result");
      if (localBundle != null)
      {
        this.mCardNumFromOcr = FormatterUtils.clean(localBundle.getString("result"));
        this.mBankCardNum = this.mCardNumFromOcr;
        this.mCardNumFromNfc = "";
        this.mCardNumEditText.setText(this.mBankCardNum);
        this.mCardNumEditText.setSelection(this.mCardNumEditText.getText().length());
      }
    }
    while (true)
    {
      return;
      switch (paramInt2)
      {
      default:
        break;
      case -1:
      case 0:
      case 1:
        getActivity().setResult(paramInt2, paramIntent);
        getActivity().finish();
      }
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131623942, paramMenu);
    return true;
  }

  public void onDestroy()
  {
    this.mUpTsmAddon.removeConnectionListener(this.mUpTsmConnectionListener);
    this.mUpTsmAddon.unbind();
    this.mBankCardManager.release();
    super.onDestroy();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903045, paramViewGroup, false);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131493120)
    {
      String str = "?t=" + String.valueOf(System.currentTimeMillis());
      WebViewActivity.showWebPage(getActivity(), this.URL_SUPPORT_BANK_LIST + str, this.mCardInfo);
    }
    for (boolean bool = true; ; bool = super.onOptionsItemSelected(paramMenuItem))
      return bool;
  }

  public void onPause()
  {
    NfcUtils.stopPoll(getActivity());
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
    this.mHasGo = false;
    NfcUtils.startPoll(getActivity());
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    ActionBar localActionBar = (ActionBar)getActivity().getActionBar();
    if (localActionBar != null)
    {
      this.mImmersionMenu = ((ImmersionMenu)getActivity().getLayoutInflater().inflate(2130903041, null));
      localActionBar.setCustomView(this.mImmersionMenu, new ActionBar.LayoutParams(-2, -2, 5));
    }
    this.mCardNumEditText = ((ExtraButtonEditText)paramView.findViewById(2131492885));
    this.mCardNumEditText.addTextChangedListener(this.mCardNumTextWatcher);
    this.mCardNumEditText.setExtraButtonDrawable(getResources().getDrawable(2130837525));
    this.mCardNumEditText.setExtraButtonListener(this.mOnExtraButtonClickListener);
    FormatterUtils.setFormatter(this.mCardNumEditText, FormatterUtils.FormatterType.TYPE_NORMAL);
    this.mMaskCardNumTv = ((TextView)paramView.findViewById(2131492886));
    this.mNextBtn = ((Button)paramView.findViewById(2131492891));
    this.mNextBtn.setOnClickListener(this.mNextClickListener);
    ((TextView)paramView.findViewById(2131492884)).setText(2131296479);
    this.mQuickPassTv = ((TextView)paramView.findViewById(2131492887));
    if (this.mData.getLong("bind_id") != 0L)
    {
      this.mFromFast = true;
      this.mCardNumEditText.setVisibility(8);
      this.mMaskCardNumTv.setVisibility(0);
      this.mMaskCardNumTv.setText("**** **** **** " + this.mData.getString("tail_num"));
      ((BankCardInfo)this.mCardInfo).mBankCardPan = this.mMaskCardNumTv.getText().toString();
      this.mNextBtn.performClick();
    }
    this.mContractsCheckBox = ((CheckBox)paramView.findViewById(2131492889));
    this.mContractsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
      {
        BankCardNumFragment.this.freshNextBtnState(BankCardNumFragment.this.mCardNumEditText.getEditableText(), paramBoolean);
      }
    });
    TextView localTextView = (TextView)paramView.findViewById(2131492890);
    localTextView.getPaint().setFlags(8);
    localTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        WebViewActivity.showWebPage(BankCardNumFragment.this.getActivity(), "http://cdn.fds.api.xiaomi.com/mipay.nextpay/bankCard/protocol/MiPay_privacy_protocol.html", BankCardNumFragment.this.mCardInfo);
      }
    });
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.bankcard.BankCardNumFragment
 * JD-Core Version:    0.6.0
 */