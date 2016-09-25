package com.miui.tsmclient.ui.bankcard;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import cmb.pb.ui.cmbwidget.CmbEditText;
import com.android.internal.util.HexDump;
import com.miui.tsmclient.SEInteractionService;
import com.miui.tsmclient.SEInteractionService.LocalBinder;
import com.miui.tsmclient.SEInteractionService.SEInteractionListener;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.database.BankCardUtil;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.RiskInfo;
import com.miui.tsmclient.entity.RiskInfo.Builder;
import com.miui.tsmclient.model.ErrorCode;
import com.miui.tsmclient.model.bankcard.BankCardManager;
import com.miui.tsmclient.seitsm.TsmRpcModels.ApplyChannel;
import com.miui.tsmclient.seitsm.TsmRpcModels.CaptureMethod;
import com.miui.tsmclient.seitsm.TsmRpcModels.DeviceType;
import com.miui.tsmclient.seitsm.TsmRpcModels.VirtualCardInfoResponse;
import com.miui.tsmclient.ui.BaseFragment.CardHandler;
import com.miui.tsmclient.ui.VerifyCodeActivity;
import com.miui.tsmclient.ui.widget.SafeEditText;
import com.miui.tsmclient.ui.widget.ValidDatePickerDialog;
import com.miui.tsmclient.ui.widget.ValidDatePickerDialog.OnDateSetListener;
import com.miui.tsmclient.util.ImageUtil;
import com.miui.tsmclient.util.ImageUtil.ThumbnailFormat;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.StringUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.unionpay.tsmservice.ITsmCallback;
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.tsmservice.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.result.EncryptDataResult;
import com.unionpay.tsmservice.widget.KeyboardDrawableErrorException;
import com.unionpay.tsmservice.widget.UPSaftyKeyboard;
import com.unionpay.tsmservice.widget.UPSaftyKeyboard.OnEditorListener;
import com.unionpay.tsmservice.widget.UPSaftyKeyboard.OnHideListener;
import com.unionpay.tsmservice.widget.UPSaftyKeyboard.OnShowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;
import miui.app.ProgressDialog;
import miui.widget.DatePicker;

public class VerifyBankCardInfoFragment extends BaseBankCardFragment
{
  private static final int MSG_ENCRYT_FINISH = 7;
  private static final int MSG_ERROR = 1;
  private static final int MSG_INSTALL_SUCCESS = 3;
  private static final int MSG_PREPARE_SUCCESS = 5;
  private static final int MSG_REQUEST_INSTALL_CARD = 4;
  private static final int MSG_VERIFY_SUCCESS = 2;
  private static final int MSG_WAIT_TO_PERSO = 6;
  private static final int PULL_PERSO_TRY_COUNTS = 10;
  private static final int REQUEST_VERIFY_CODE = 1;
  private BankCardManager mBankCardManager;
  private ImageView mBankLogo;
  private TextView mBankNameTv;
  private TableRow mCardCvn2Tr;
  private TableRow mCardPinTr;
  private TextView mCardTypeTv;
  private TableRow mCardValidDateTr;
  private CleanCardTask mCleanCardTask;
  private int mCmbSafetyKeyboardDimen;
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      VerifyBankCardInfoFragment.access$3202(VerifyBankCardInfoFragment.this, ((SEInteractionService.LocalBinder)paramIBinder).getService());
      VerifyBankCardInfoFragment.this.registerListener();
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      VerifyBankCardInfoFragment.this.unregisterListener();
      VerifyBankCardInfoFragment.this.unbindService();
    }
  };
  private EditText mCvv2Et;
  private CmbEditText mCvv2EtCmb;
  private Button mNextBtn;
  private View.OnClickListener mNextClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      if (!VerifyBankCardInfoFragment.this.preTransElementCheck());
      while (true)
      {
        return;
        VerifyBankCardInfoFragment.this.showDialog(2131296460);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = "verifyCardInfo";
        AnalyticManager.recordEvent("bank", String.format("operation_%s_launch", arrayOfObject));
        VerifyBankCardInfoFragment.this.verifyCardInfo();
      }
    }
  };
  private SafeEditText mPhoneEt;
  private TableRow mPhoneTr;
  private CmbEditText mPinCmbEt;
  private EditText mPinUpEt;
  private int mPrepareResult = 0;
  private SEInteractionService.SEInteractionListener mSEInteractionListener = new SEInteractionService.SEInteractionListener()
  {
    public void onFinished(int paramInt)
    {
      if (paramInt == 0)
        VerifyBankCardInfoFragment.this.mHandler.obtainMessage(3).sendToTarget();
      while (true)
      {
        return;
        if (paramInt == 1002001)
        {
          new AlertDialog.Builder(VerifyBankCardInfoFragment.this.getActivity()).setTitle(2131296502).setMessage(2131296503).setPositiveButton(2131296504, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramDialogInterface, int paramInt)
            {
              VerifyBankCardInfoFragment.this.getActivity().setResult(0);
              VerifyBankCardInfoFragment.this.getActivity().finish();
            }
          }).setCancelable(false).create().show();
          continue;
        }
        VerifyBankCardInfoFragment.this.mHandler.obtainMessage(1, paramInt, 0).sendToTarget();
      }
    }

    public void onProgressChanged(int paramInt)
    {
    }

    public void onStart()
    {
    }
  };
  private SEInteractionService mService;
  private TextView mTailNumTv;
  private int mTryCounts = 10;
  private String mUPCipherData = null;
  private UPSaftyKeyboard.OnEditorListener mUPSafeKeyboardEditorListener = new UPSaftyKeyboard.OnEditorListener()
  {
    public void onEditorChanged(int paramInt)
    {
      StringBuilder localStringBuilder = new StringBuilder(paramInt);
      int j;
      for (int i = paramInt; ; i = j)
      {
        j = i - 1;
        if (i <= 0)
          break;
        localStringBuilder.append('*');
      }
      if (((BankCardInfo)VerifyBankCardInfoFragment.this.mCardInfo).mBankCardType == 1)
      {
        VerifyBankCardInfoFragment.this.mPinUpEt.setText(localStringBuilder.toString());
        VerifyBankCardInfoFragment.this.mPinUpEt.setSelection(localStringBuilder.length());
      }
      while (true)
      {
        return;
        VerifyBankCardInfoFragment.this.mCvv2Et.setText(localStringBuilder.toString());
        VerifyBankCardInfoFragment.this.mCvv2Et.setSelection(localStringBuilder.length());
      }
    }
  };
  private UPSaftyKeyboard.OnHideListener mUPSafeKeyboardHindListener = new UPSaftyKeyboard.OnHideListener()
  {
    public void onHide()
    {
      VerifyBankCardInfoFragment.this.moveNextButton(0, 0, 0, 0);
    }
  };
  private UPSaftyKeyboard.OnShowListener mUPSafeKeyboardShowListener = new UPSaftyKeyboard.OnShowListener()
  {
    public void onShow()
    {
      VerifyBankCardInfoFragment.this.moveNextButton(0, 0, 0, VerifyBankCardInfoFragment.this.mUpSafetyKeyboardDimen);
    }
  };
  private View.OnClickListener mUPSafeTextClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      VerifyBankCardInfoFragment.this.mUpSafetyKeyboard.show();
    }
  };
  private View.OnFocusChangeListener mUPSafeTextFocusedChangeListener = new View.OnFocusChangeListener()
  {
    public void onFocusChange(View paramView, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        VerifyBankCardInfoFragment.this.mUpSafetyKeyboard.show();
        VerifyBankCardInfoFragment.this.getActivity().getWindow().setSoftInputMode(3);
      }
      while (true)
      {
        return;
        VerifyBankCardInfoFragment.this.getActivity().getWindow().setSoftInputMode(4);
      }
    }
  };
  private UPSaftyKeyboard mUpSafetyKeyboard;
  private int mUpSafetyKeyboardDimen;
  private UPTsmAddon mUpTsmAddon;
  private CmbEditText mValidDataTvCmb;
  private TextView mValidDateTv;
  private View.OnClickListener mValidDateTvListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      VerifyBankCardInfoFragment.this.performClickDataView();
    }
  };

  private void bindService()
  {
    android.app.Activity localActivity = getActivity();
    localActivity.bindService(new Intent(localActivity, SEInteractionService.class), this.mConnection, 1);
  }

  private RiskInfo buildRiskInfo()
  {
    RiskInfo localRiskInfo = new RiskInfo.Builder().build();
    localRiskInfo.setDeviceType(TsmRpcModels.DeviceType.PHONE);
    localRiskInfo.setApplyChannel(TsmRpcModels.ApplyChannel.XIAOMI);
    localRiskInfo.setCaptureMethod((TsmRpcModels.CaptureMethod)getArguments().getSerializable("capture_method"));
    return localRiskInfo;
  }

  private void checkCardStatus()
  {
    if (this.mCleanCardTask != null)
    {
      this.mCleanCardTask.cancel(true);
      this.mCleanCardTask = null;
    }
    this.mCleanCardTask = new CleanCardTask(getActivity());
    CleanCardTask localCleanCardTask = this.mCleanCardTask;
    String[] arrayOfString = new String[1];
    arrayOfString[0] = ((BankCardInfo)this.mCardInfo).mAid;
    localCleanCardTask.execute(arrayOfString);
  }

  private void enrollUPCard(Context paramContext, Bundle paramBundle)
  {
    this.mBankCardManager.enrollUPCard(paramContext, this.mCardInfo, paramBundle, new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        VerifyBankCardInfoFragment.this.mHandler.obtainMessage(1, paramInt, 0).sendToTarget();
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        VerifyBankCardInfoFragment.this.mHandler.obtainMessage(2, paramArrayOfObject[0]).sendToTarget();
      }
    });
  }

  private byte[] generateCipherData()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (((BankCardInfo)this.mCardInfo).mBankCardType == 1)
    {
      localStringBuilder.append("0001");
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Integer.valueOf(((BankCardInfo)this.mCardInfo).mBankCardPan.length());
      localStringBuilder.append(String.format("%03d", arrayOfObject4));
      localStringBuilder.append(((BankCardInfo)this.mCardInfo).mBankCardPan);
      localStringBuilder.append("0003");
      Object[] arrayOfObject5 = new Object[1];
      arrayOfObject5[0] = Integer.valueOf(this.mPinCmbEt.getEnctyptText().length());
      localStringBuilder.append(String.format("%03d", arrayOfObject5));
      localStringBuilder.append(this.mPinCmbEt.getEnctyptText());
    }
    while (true)
    {
      return localStringBuilder.toString().getBytes();
      localStringBuilder.append("0002");
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(((BankCardInfo)this.mCardInfo).mBankCardPan.length());
      localStringBuilder.append(String.format("%03d", arrayOfObject1));
      localStringBuilder.append(((BankCardInfo)this.mCardInfo).mBankCardPan);
      localStringBuilder.append("0004");
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(this.mCvv2EtCmb.getEnctyptText().length());
      localStringBuilder.append(String.format("%03d", arrayOfObject2));
      localStringBuilder.append(this.mCvv2EtCmb.getEnctyptText());
      localStringBuilder.append("0005");
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Integer.valueOf(this.mValidDataTvCmb.getEnctyptText().length());
      localStringBuilder.append(String.format("%03d", arrayOfObject3));
      localStringBuilder.append(this.mValidDataTvCmb.getEnctyptText());
    }
  }

  private void initCMBView()
  {
    if (((BankCardInfo)this.mCardInfo).mBankCardType == 1)
    {
      this.mPinCmbEt.setRecvTouchEventActivity(getActivity());
      this.mPinCmbEt.setSessionID("1234567890121234");
      this.mPinCmbEt.setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramView, boolean paramBoolean)
        {
          if (paramBoolean)
          {
            VerifyBankCardInfoFragment.this.mPinCmbEt.showCMBKeyboardWindow2();
            VerifyBankCardInfoFragment.this.getActivity().getWindow().setSoftInputMode(3);
          }
          while (true)
          {
            return;
            VerifyBankCardInfoFragment.this.getActivity().getWindow().setSoftInputMode(4);
          }
        }
      });
      this.mPinCmbEt.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramEditable)
        {
          if (paramEditable.length() != 6)
            VerifyBankCardInfoFragment.this.mNextBtn.setEnabled(false);
          while (true)
          {
            return;
            VerifyBankCardInfoFragment.this.mNextBtn.setEnabled(true);
          }
        }

        public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
        {
        }

        public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
        {
        }
      });
    }
    while (true)
    {
      return;
      this.mCvv2EtCmb.setVisibility(0);
      this.mCvv2EtCmb.setRecvTouchEventActivity(getActivity());
      this.mCvv2EtCmb.setSessionID("1234567890121234");
      this.mCvv2EtCmb.setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramView, boolean paramBoolean)
        {
          if (paramBoolean)
            VerifyBankCardInfoFragment.this.mCvv2EtCmb.showCMBKeyboardWindow2();
        }
      });
      this.mValidDataTvCmb.setVisibility(0);
      this.mValidDataTvCmb.setRecvTouchEventActivity(getActivity());
      this.mValidDataTvCmb.setSessionID("1234567890121234");
      this.mValidDataTvCmb.setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramView, boolean paramBoolean)
        {
          if (paramBoolean)
            VerifyBankCardInfoFragment.this.mValidDataTvCmb.showCMBKeyboardWindow2();
        }
      });
    }
  }

  private void initUPView()
  {
    Resources localResources = getActivity().getResources();
    try
    {
      if (((BankCardInfo)this.mCardInfo).mBankCardType == 1);
      for (this.mUpSafetyKeyboard = new UPSaftyKeyboard(getActivity(), 2000); ; this.mUpSafetyKeyboard = new UPSaftyKeyboard(getActivity(), 2001))
      {
        Drawable[] arrayOfDrawable = new Drawable[10];
        arrayOfDrawable[0] = localResources.getDrawable(2130837625);
        arrayOfDrawable[1] = localResources.getDrawable(2130837626);
        arrayOfDrawable[2] = localResources.getDrawable(2130837627);
        arrayOfDrawable[3] = localResources.getDrawable(2130837628);
        arrayOfDrawable[4] = localResources.getDrawable(2130837629);
        arrayOfDrawable[5] = localResources.getDrawable(2130837630);
        arrayOfDrawable[6] = localResources.getDrawable(2130837631);
        arrayOfDrawable[7] = localResources.getDrawable(2130837632);
        arrayOfDrawable[8] = localResources.getDrawable(2130837633);
        arrayOfDrawable[9] = localResources.getDrawable(2130837634);
        this.mUpSafetyKeyboard.enableLightStatusBar(true);
        this.mUpSafetyKeyboard.setKeyBoardSize(localResources.getDimensionPixelSize(2131230866), localResources.getDimensionPixelSize(2131230867));
        this.mUpSafetyKeyboard.setKeyboardBackground(localResources.getDrawable(2130837656));
        this.mUpSafetyKeyboard.setNumberKeyDrawable(arrayOfDrawable);
        this.mUpSafetyKeyboard.setDelKeyDrawable(localResources.getDrawable(2130837657));
        this.mUpSafetyKeyboard.setDoneKeyDrawable(localResources.getDrawable(2130837658));
        int i = (int)localResources.getDimension(2131230868);
        this.mUpSafetyKeyboard.setKeyboardPadding(i, 0, i, i);
        int j = (int)localResources.getDimension(2131230869);
        this.mUpSafetyKeyboard.setKeyAreaPadding(0, i, 0, 0);
        this.mUpSafetyKeyboard.setNumKeyMargin(j, j);
        this.mUpSafetyKeyboard.setTitleBackground(localResources.getDrawable(2130837659));
        this.mUpSafetyKeyboard.setTitleHeight(localResources.getDimensionPixelSize(2131230870));
        this.mUpSafetyKeyboard.setTitleText("银联安全键盘");
        this.mUpSafetyKeyboard.setTitleSize(localResources.getDimensionPixelSize(2131230871));
        this.mUpSafetyKeyboard.setTitleColor(localResources.getColor(2131165262));
        this.mUpSafetyKeyboard.setTitleDrawablePadding((int)localResources.getDimension(2131230872));
        this.mUpSafetyKeyboard.setTitleDrawable(localResources.getDrawable(2130837642));
        this.mUpSafetyKeyboard.setTitleDrawableSize((int)localResources.getDimension(2131230873), (int)localResources.getDimension(2131230873));
        this.mUpSafetyKeyboard.setOnEditorListener(this.mUPSafeKeyboardEditorListener);
        this.mUpSafetyKeyboard.setOnHideListener(this.mUPSafeKeyboardHindListener);
        this.mUpSafetyKeyboard.setOnShowListener(this.mUPSafeKeyboardShowListener);
        this.mCvv2Et.setOnClickListener(this.mUPSafeTextClickListener);
        this.mCvv2Et.setOnFocusChangeListener(this.mUPSafeTextFocusedChangeListener);
        this.mCvv2Et.setShowSoftInputOnFocus(false);
        this.mPinUpEt.setOnClickListener(this.mUPSafeTextClickListener);
        this.mPinUpEt.setOnFocusChangeListener(this.mUPSafeTextFocusedChangeListener);
        this.mPinUpEt.setShowSoftInputOnFocus(false);
        break;
      }
    }
    catch (RemoteException localRemoteException)
    {
      LogUtils.e("call UP keyboard error", localRemoteException);
    }
    catch (KeyboardDrawableErrorException localKeyboardDrawableErrorException)
    {
      LogUtils.e("UP keyboard drawable error", localKeyboardDrawableErrorException);
    }
  }

  private void moveNextButton(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ((LinearLayout.LayoutParams)this.mNextBtn.getLayoutParams()).setMargins(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mNextBtn.requestLayout();
  }

  private void performClickDataView()
  {
    Calendar localCalendar;
    if (this.mValidDateTv.getTag() != null)
      localCalendar = (Calendar)this.mValidDateTv.getTag();
    while (true)
    {
      int i = 1 + localCalendar.get(1);
      int j = localCalendar.get(2);
      ValidDatePickerDialog localValidDatePickerDialog = new ValidDatePickerDialog(getActivity(), new ValidDatePickerDialog.OnDateSetListener(localCalendar)
      {
        public void onDateSet(int paramInt1, int paramInt2, int paramInt3)
        {
          this.val$calendar.set(paramInt1, paramInt2, 1);
          if (((BankCardInfo)VerifyBankCardInfoFragment.this.mCardInfo).mIssuerChannel == 2)
          {
            CharSequence localCharSequence2 = DateFormat.format("MMyy", this.val$calendar);
            LogUtils.i("data = " + localCharSequence2);
            VerifyBankCardInfoFragment.this.mValidDataTvCmb.setText(localCharSequence2);
            VerifyBankCardInfoFragment.this.mValidDataTvCmb.setTag(this.val$calendar);
          }
          while (true)
          {
            return;
            CharSequence localCharSequence1 = DateFormat.format("MM/yy", this.val$calendar);
            VerifyBankCardInfoFragment.this.mValidDateTv.setText(localCharSequence1);
            VerifyBankCardInfoFragment.this.mValidDateTv.setTag(this.val$calendar);
          }
        }
      }
      , i, j, 1);
      long l1 = System.currentTimeMillis();
      localCalendar.setTimeInMillis(l1);
      localCalendar.roll(1, 50);
      localCalendar.set(2, 11);
      long l2 = localCalendar.getTimeInMillis();
      localValidDatePickerDialog.getDatePicker().setMinDate(l1);
      localValidDatePickerDialog.getDatePicker().setMaxDate(l2);
      localValidDatePickerDialog.show();
      return;
      if (this.mValidDataTvCmb.getTag() != null)
      {
        localCalendar = (Calendar)this.mValidDataTvCmb.getTag();
        continue;
      }
      localCalendar = Calendar.getInstance();
    }
  }

  private boolean preTransElementCheck()
  {
    int i;
    if ((this.mCardPinTr.getVisibility() == 0) && (((((BankCardInfo)this.mCardInfo).mIssuerChannel == 2) && (this.mPinCmbEt.getInputLength() != 6)) || ((((BankCardInfo)this.mCardInfo).mIssuerChannel == 1) && (this.mPinUpEt.getText().length() != 6))))
    {
      UiUtils.showToast(getActivity(), 2131296492);
      i = 0;
    }
    while (true)
    {
      return i;
      if ((this.mPhoneTr.getVisibility() == 0) && (this.mPhoneEt.getText().length() != 11))
      {
        UiUtils.showToast(getActivity(), 2131296500);
        i = 0;
        continue;
      }
      if ((this.mCardCvn2Tr.getVisibility() == 0) && (this.mCvv2Et.getText().length() != 3) && (this.mCvv2EtCmb.getInputLength() != 3))
      {
        UiUtils.showToast(getActivity(), 2131296490);
        i = 0;
        continue;
      }
      if ((this.mCardValidDateTr.getVisibility() == 0) && (TextUtils.isEmpty(this.mValidDateTv.getText())) && (TextUtils.isEmpty(this.mValidDataTvCmb.getText())))
      {
        UiUtils.showToast(getActivity(), 2131296491);
        i = 0;
        continue;
      }
      i = 1;
    }
  }

  private void preparePayApplet()
  {
    this.mPrepareResult = 0;
    Bundle localBundle = new Bundle();
    localBundle.putInt("bankcard_type", ((BankCardInfo)this.mCardInfo).mBankCardType);
    localBundle.putInt("issuer_channel", ((BankCardInfo)this.mCardInfo).mIssuerChannel);
    localBundle.putString("issuer_id", ((BankCardInfo)this.mCardInfo).mIssuerId);
    this.mBankCardManager.preparePayApplet(getActivity(), this.mCardInfo, localBundle, new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        VerifyBankCardInfoFragment.access$1202(VerifyBankCardInfoFragment.this, -1);
        VerifyBankCardInfoFragment.this.mHandler.obtainMessage(1, paramInt, 0).sendToTarget();
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        VerifyBankCardInfoFragment.access$1202(VerifyBankCardInfoFragment.this, 1);
        VerifyBankCardInfoFragment.this.mHandler.obtainMessage(5).sendToTarget();
      }
    });
  }

  private void pullPersoData(Context paramContext)
  {
    this.mBankCardManager.pullPersoData(paramContext, this.mCardInfo, null, new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        if ((paramInt == 10048) && (VerifyBankCardInfoFragment.this.mTryCounts > 0))
        {
          VerifyBankCardInfoFragment.access$510(VerifyBankCardInfoFragment.this);
          VerifyBankCardInfoFragment.this.mHandler.postDelayed(new Runnable()
          {
            public void run()
            {
              VerifyBankCardInfoFragment.this.mHandler.obtainMessage(6).sendToTarget();
            }
          }
          , 4000L);
        }
        while (true)
        {
          return;
          VerifyBankCardInfoFragment.access$502(VerifyBankCardInfoFragment.this, 10);
          if (paramInt != 10048)
            VerifyBankCardInfoFragment.this.preparePayApplet();
          VerifyBankCardInfoFragment.this.mHandler.obtainMessage(1, paramInt, 0).sendToTarget();
        }
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        VerifyBankCardInfoFragment.this.mHandler.obtainMessage(3).sendToTarget();
      }
    });
  }

  private void registerListener()
  {
    this.mService.setSEInteractionListener(this.mSEInteractionListener);
  }

  private void unbindService()
  {
    if (this.mService != null)
    {
      getActivity().unbindService(this.mConnection);
      unregisterListener();
    }
    this.mService = null;
  }

  private void unregisterListener()
  {
    if (this.mService != null)
      this.mService.setSEInteractionListener(null);
  }

  private void verifyCardInfo()
  {
    if (((BankCardInfo)this.mCardInfo).mIssuerChannel == 1)
      if (this.mPrepareResult != 0);
    while (true)
    {
      return;
      if (this.mPrepareResult == -1)
      {
        preparePayApplet();
        continue;
      }
      EncryptDataRequestParams localEncryptDataRequestParams = new EncryptDataRequestParams();
      ArrayList localArrayList = new ArrayList();
      if (((BankCardInfo)this.mCardInfo).mBankCardType == 1);
      String str2;
      String str3;
      for (String str4 = ((BankCardInfo)this.mCardInfo).mBankCardPan + "|" + this.mPhoneEt.getText().toString(); ; str4 = ((BankCardInfo)this.mCardInfo).mBankCardPan + "|" + str2 + str3 + "|" + this.mPhoneEt.getText().toString())
      {
        while (true)
        {
          localArrayList.add(str4);
          localEncryptDataRequestParams.setData(localArrayList);
          try
          {
            this.mUpTsmAddon.encryptData(localEncryptDataRequestParams, new ITsmCallback()
            {
              public IBinder asBinder()
              {
                return null;
              }

              public void onError(String paramString1, String paramString2)
                throws RemoteException
              {
                VerifyBankCardInfoFragment.this.mHandler.obtainMessage(7).sendToTarget();
                Object[] arrayOfObject = new Object[1];
                arrayOfObject[0] = "encryptDataUP";
                AnalyticManager.recordEvent("bank", String.format("operation_%s_failed", arrayOfObject));
              }

              public void onResult(Bundle paramBundle)
                throws RemoteException
              {
                EncryptDataResult localEncryptDataResult = (EncryptDataResult)paramBundle.getParcelable("result");
                if (localEncryptDataResult != null)
                  VerifyBankCardInfoFragment.access$1802(VerifyBankCardInfoFragment.this, (String)localEncryptDataResult.getEncryptData().get(0));
                VerifyBankCardInfoFragment.this.mHandler.obtainMessage(7).sendToTarget();
                Object[] arrayOfObject = new Object[1];
                arrayOfObject[0] = "encryptDataUP";
                AnalyticManager.recordEvent("bank", String.format("operation_%s_success", arrayOfObject));
              }
            });
          }
          catch (RemoteException localRemoteException)
          {
            LogUtils.e("Remote exception when encrypt data using UP tsm addon", localRemoteException);
            this.mHandler.obtainMessage(1).sendToTarget();
          }
        }
        break;
        String str1 = this.mValidDateTv.getText().toString();
        int i = str1.indexOf('/');
        str2 = str1.substring(0, i);
        str3 = str1.substring(i + 1);
      }
      byte[] arrayOfByte = generateCipherData();
      RiskInfo localRiskInfo = buildRiskInfo();
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("risk_info", localRiskInfo);
      this.mBankCardManager.verifyCardInfo(getActivity(), this.mCardInfo, ((BankCardInfo)this.mCardInfo).mBankCardPan, arrayOfByte, localBundle, new MiTsmCallback()
      {
        public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
        {
          VerifyBankCardInfoFragment.this.mHandler.obtainMessage(1, paramInt, 0).sendToTarget();
        }

        public void onSuccess(int paramInt, Object[] paramArrayOfObject)
        {
          VerifyBankCardInfoFragment.this.mHandler.obtainMessage(2, paramArrayOfObject[0]).sendToTarget();
        }
      });
    }
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mBankCardManager = new BankCardManager(getActivity());
    this.mProgressDialog.setCancelable(false);
    this.mUpTsmAddon = UPTsmAddon.getInstance(getActivity());
    this.mHandler = new BaseFragment.CardHandler(this, (miui.app.Activity)getActivity());
    bindService();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = "VerifyBankCardInfoFragment";
    AnalyticManager.recordEvent("bank", String.format("key_enter/%s", arrayOfObject));
  }

  protected void handleMessage(Message paramMessage, miui.app.Activity paramActivity)
  {
    super.handleMessage(paramMessage, paramActivity);
    switch (paramMessage.what)
    {
    default:
      LogUtils.v("There is no message to respond");
    case 1:
    case 2:
    case 4:
    case 3:
    case 6:
    case 5:
    case 7:
    }
    while (true)
    {
      return;
      dismissDialog();
      UiUtils.showToast(paramActivity, ErrorCode.findText(paramActivity, paramMessage.arg1));
      continue;
      TsmRpcModels.VirtualCardInfoResponse localVirtualCardInfoResponse = (TsmRpcModels.VirtualCardInfoResponse)paramMessage.obj;
      if (localVirtualCardInfoResponse != null)
      {
        ((BankCardInfo)this.mCardInfo).mVCReferenceId = localVirtualCardInfoResponse.getVirtualCardReferenceId();
        ((BankCardInfo)this.mCardInfo).mVCardNumber = localVirtualCardInfoResponse.getVirtualCardNumber();
        ((BankCardInfo)this.mCardInfo).mPanLastDigits = localVirtualCardInfoResponse.getLastDigits();
        ((BankCardInfo)this.mCardInfo).mAid = localVirtualCardInfoResponse.getAid();
        ((BankCardInfo)this.mCardInfo).mPhoneLastDigits = localVirtualCardInfoResponse.getPhoneNumLastDigits();
      }
      if (((BankCardInfo)this.mCardInfo).mIssuerChannel == 2)
        checkCardStatus();
      while (true)
      {
        showDialog(2131296461);
        break;
        ((BankCardInfo)this.mCardInfo).mPhoneLastDigits = StringUtils.tail(this.mPhoneEt.getText().toString(), 4);
        Message localMessage = new Message();
        localMessage.what = 6;
        this.mHandler.sendMessageDelayed(localMessage, 10000L);
      }
      Bundle localBundle2 = new Bundle();
      localBundle2.putBoolean("extras_key_session_not_finish", true);
      SEInteractionService.installCard(getActivity(), this.mCardInfo, localBundle2);
      continue;
      dismissDialog();
      Intent localIntent = new Intent(paramActivity, VerifyCodeActivity.class);
      localIntent.putExtra("card_info", this.mCardInfo);
      startActivityForResult(localIntent, 1);
      continue;
      pullPersoData(paramActivity);
      continue;
      if (!this.mProgressDialog.isShowing())
        continue;
      verifyCardInfo();
      continue;
      if (this.mUPCipherData != null)
        break;
      this.mHandler.obtainMessage(1).sendToTarget();
    }
    Bundle localBundle1 = new Bundle();
    localBundle1.putByteArray("cipher_card_info", HexDump.hexStringToByteArray(this.mUPCipherData));
    if (((BankCardInfo)this.mCardInfo).mBankCardType == 1)
      localBundle1.putByteArray("cipher_pin_info", HexDump.hexStringToByteArray(this.mUpSafetyKeyboard.getInput(((BankCardInfo)this.mCardInfo).mBankCardPan)));
    while (true)
    {
      localBundle1.putInt("bankcard_type", ((BankCardInfo)this.mCardInfo).mBankCardType);
      localBundle1.putParcelable("risk_info", buildRiskInfo());
      enrollUPCard(paramActivity, localBundle1);
      this.mUPCipherData = null;
      break;
      localBundle1.putByteArray("cipher_cvv2_info", HexDump.hexStringToByteArray(this.mUpSafetyKeyboard.getInput()));
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt2 == -1) || (paramInt2 == 1))
    {
      getActivity().setResult(paramInt2, paramIntent);
      getActivity().finish();
    }
  }

  public void onDestroy()
  {
    unbindService();
    if (this.mCleanCardTask != null)
      this.mCleanCardTask.cancel(true);
    if (this.mUpSafetyKeyboard != null)
      this.mUpSafetyKeyboard.clearPwd();
    this.mHandler.removeCallbacksAndMessages(null);
    this.mBankCardManager.release();
    super.onDestroy();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903086, paramViewGroup, false);
    this.mBankLogo = ((ImageView)localView.findViewById(2131492892));
    this.mBankNameTv = ((TextView)localView.findViewById(2131492893));
    this.mCardTypeTv = ((TextView)localView.findViewById(2131492894));
    this.mTailNumTv = ((TextView)localView.findViewById(2131492896));
    this.mNextBtn = ((Button)localView.findViewById(2131492891));
    this.mCardPinTr = ((TableRow)localView.findViewById(2131493093));
    this.mCardValidDateTr = ((TableRow)localView.findViewById(2131493090));
    this.mCardCvn2Tr = ((TableRow)localView.findViewById(2131493087));
    this.mValidDateTv = ((TextView)localView.findViewById(2131493092));
    this.mPinCmbEt = ((CmbEditText)localView.findViewById(2131493094));
    this.mCvv2EtCmb = ((CmbEditText)localView.findViewById(2131493088));
    this.mValidDataTvCmb = ((CmbEditText)localView.findViewById(2131493091));
    this.mPinUpEt = ((EditText)localView.findViewById(2131493095));
    this.mPhoneTr = ((TableRow)localView.findViewById(2131493096));
    this.mPhoneEt = ((SafeEditText)localView.findViewById(2131493019));
    this.mNextBtn.setOnClickListener(this.mNextClickListener);
    this.mValidDateTv.setOnClickListener(this.mValidDateTvListener);
    float f = getResources().getDimension(2131230720);
    this.mCmbSafetyKeyboardDimen = (int)(getResources().getDimension(2131230721) + 4.0F * f);
    this.mUpSafetyKeyboardDimen = (int)getResources().getDimension(2131230867);
    this.mCvv2Et = ((EditText)localView.findViewById(2131493089));
    if (((BankCardInfo)this.mCardInfo).mBankCardType == 1)
    {
      this.mCardCvn2Tr.setVisibility(8);
      this.mCardValidDateTr.setVisibility(8);
      if (((BankCardInfo)this.mCardInfo).mIssuerChannel != 2)
        break label457;
      this.mPinUpEt.setVisibility(8);
      this.mPhoneTr.setVisibility(8);
      initCMBView();
    }
    while (true)
    {
      return localView;
      this.mCardPinTr.setVisibility(8);
      if (((BankCardInfo)this.mCardInfo).mIssuerChannel != 2)
        break;
      getActivity().getWindow().setSoftInputMode(3);
      this.mPhoneEt.setVisibility(8);
      this.mCvv2Et.setVisibility(8);
      this.mValidDateTv.setVisibility(8);
      break;
      label457: this.mPinCmbEt.setVisibility(8);
      initUPView();
    }
  }

  public void onPause()
  {
    if (((BankCardInfo)this.mCardInfo).mIssuerChannel == 2)
      moveNextButton(0, 0, 0, this.mCmbSafetyKeyboardDimen);
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
    checkUPServiceStatus();
    if (((BankCardInfo)this.mCardInfo).mIssuerChannel == 2)
      moveNextButton(0, 0, 0, 0);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.mBankNameTv.setText(((BankCardInfo)this.mCardInfo).mBankName);
    DisplayImageOptions localDisplayImageOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565).showImageOnLoading(2130837616).showImageForEmptyUri(2130837616).showImageOnFail(2130837616).build();
    ImageUtil.ThumbnailFormat localThumbnailFormat = ImageUtil.ThumbnailFormat.getMaxWidthHeightThumnail(getResources().getDimensionPixelSize(2131230846), getResources().getDimensionPixelSize(2131230847), 2);
    String str = ImageUtil.getUrl(((BankCardInfo)this.mCardInfo).mBankLogoUrl, localThumbnailFormat);
    ImageLoader.getInstance().displayImage(str, this.mBankLogo, localDisplayImageOptions);
    this.mCardTypeTv.setText(BankCardUtil.getBankCardTypeString(getActivity(), ((BankCardInfo)this.mCardInfo).mBankCardType));
    this.mTailNumTv.setText(StringUtils.tail(((BankCardInfo)this.mCardInfo).mBankCardPan, 4));
    if (((BankCardInfo)this.mCardInfo).mIssuerChannel == 1)
      preparePayApplet();
  }

  private class CleanCardTask extends AsyncTask<String, Void, Integer>
  {
    private Context mContext;

    public CleanCardTask(Context arg2)
    {
      Object localObject;
      this.mContext = localObject;
    }

    protected Integer doInBackground(String[] paramArrayOfString)
    {
      String str = paramArrayOfString[0];
      try
      {
        if (SysUtils.isAppletExist(this.mContext, str))
        {
          Integer localInteger2 = Integer.valueOf(VerifyBankCardInfoFragment.this.mBankCardManager.cleanCard(VerifyBankCardInfoFragment.this.getActivity(), VerifyBankCardInfoFragment.this.mCardInfo));
          localInteger1 = localInteger2;
          return localInteger1;
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          LogUtils.e("IOException when cleaning card", localIOException);
          Integer localInteger1 = Integer.valueOf(8);
          continue;
          localInteger1 = Integer.valueOf(0);
        }
      }
    }

    protected void onPostExecute(Integer paramInteger)
    {
      if (paramInteger.intValue() == 0)
        VerifyBankCardInfoFragment.this.mHandler.sendEmptyMessage(4);
      while (true)
      {
        return;
        VerifyBankCardInfoFragment.this.mHandler.obtainMessage(1, paramInteger.intValue(), 0).sendToTarget();
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.bankcard.VerifyBankCardInfoFragment
 * JD-Core Version:    0.6.0
 */