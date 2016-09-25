package com.miui.tsmclient.ui.quick;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager.AuthenticationResult;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfo.Status;
import com.miui.tsmclient.entity.SwitchPageData;
import com.miui.tsmclient.hcievent.HciEventInfo;
import com.miui.tsmclient.hcievent.HciEventUtils;
import com.miui.tsmclient.hcievent.IHciEventHandler;
import com.miui.tsmclient.model.CardManager;
import com.miui.tsmclient.presenter.IHandleError;
import com.miui.tsmclient.presenter.SwitchCardContract.View;
import com.miui.tsmclient.presenter.SwitchCardPresenter;
import com.miui.tsmclient.ui.BaseCardFragment;
import com.miui.tsmclient.ui.MainActivity;
import com.miui.tsmclient.ui.widget.CardStackLayout;
import com.miui.tsmclient.ui.widget.FlashView;
import com.miui.tsmclient.ui.widget.MiuiDigitFontTextView;
import com.miui.tsmclient.ui.widget.SlideView;
import com.miui.tsmclient.ui.widget.WaveCircle;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;
import com.miui.tsmclient.util.StringUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmManager;
import com.miui.tsmclientsdk.UnSupportedException;
import com.tsmclient.smartcard.Coder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SwitchCardFragment extends BaseCardFragment<CardInfo>
  implements IHandleError, SwitchCardContract.View
{
  private static final int DisableCe = 201326592;
  private static final int EnableCe = 1310720;
  private static final int EnableCeForLinNanTong = 262144;
  private static final int FINGER_VERIFY_MAX = 10;
  private static final int MSG_DELAY_DESTORY = 5;
  private static final int MSG_DELAY_SHOW_POS = 2;
  private static final int MSG_FINISHE_SELF = 4;
  private static final int MSG_REFRESH_VIEW = 1;
  private static final int MSG_UPDATE_ADAPTER_DATA = 3;
  private static final String TAG = "SwitchCardFragment";
  private static final String TIME_PATTERN = " yyyy-MM-dd HH:mm";
  private CardStackAdapter mAdapter;
  private String mAttatchedActivitySource;
  private boolean mCMOpened = false;
  private boolean mCalledFromRFOn = false;
  private boolean mCalledFromVolumnDown = false;
  private CancellationSignal mCancellationSignal;
  private CardStackLayout mCardLayout;
  private ArrayList<StackItem> mCardList = new ArrayList()
  {
    public boolean add(SwitchCardFragment.StackItem paramStackItem)
    {
      boolean bool;
      if ((paramStackItem.getCardInfo() instanceof BankCardInfo))
      {
        BankCardInfo localBankCardInfo = (BankCardInfo)paramStackItem.getCardInfo();
        LogUtils.i("add bankCard to MI Pay list: " + localBankCardInfo.mCardName + ", " + localBankCardInfo.mVCStatus);
        if (localBankCardInfo.mVCStatus != 0)
          break label135;
        bool = super.add(paramStackItem);
      }
      while (true)
      {
        return bool;
        CardInfo localCardInfo = paramStackItem.getCardInfo();
        LogUtils.i("add card to MI Pay list: " + localCardInfo.mCardName + ", " + localCardInfo.mStatus);
        if (localCardInfo.mStatus == CardInfo.Status.ACTIVE)
        {
          bool = super.add(paramStackItem);
          continue;
        }
        label135: bool = false;
      }
    }
  };
  private CardManager mCardManager;
  private CardStackAdapter.ICardStackViewChangedListener mDefaultCardChangedListener = new CardStackAdapter.ICardStackViewChangedListener()
  {
    public void onCardsPoppedUp()
    {
      SwitchCardFragment.this.mIcMore.setVisibility(8);
      if (SwitchCardFragment.this.mCMOpened)
      {
        SwitchCardFragment.this.mWaveCircle.stopWave();
        SwitchCardFragment.this.mNfcHandler.obtainMessage(2).sendToTarget();
      }
      while (true)
      {
        return;
        if ((!SwitchCardFragment.this.isBankCard(SwitchCardFragment.this.mDefaultCardInfo)) && (SwitchCardFragment.this.mDefaultView != null))
        {
          SwitchCardFragment.this.mDefaultView.stopShading();
          continue;
        }
      }
    }

    public void onDefaultCardChanged(int paramInt)
    {
      SwitchCardFragment.this.updateDefaultCardView(paramInt);
    }

    public void onDefaultCardSelected()
    {
      if ((SwitchCardFragment.this.mDefaultCardInfo != null) && (!SwitchCardFragment.this.isBankCard(SwitchCardFragment.this.mDefaultCardInfo)) && (!SwitchCardFragment.this.mCMOpened))
        SwitchCardFragment.this.mNfcHandler.obtainMessage(1).sendToTarget();
    }
  };
  private CardInfo mDefaultCardInfo;
  private SlideView mDefaultView;
  private Animation mFadeInAnim;
  private View mFingerLayer;
  private AnimationDrawable mFingerPrinterDrawable;
  private int mFingerVefifyedCount = 10;
  private FingerprintManager mFingerprintManager;
  private MyHandlerThread mHandlerThread;
  private ImageView mIcMore;
  private String mLastActivatedCardAid;
  private NfcAdapter mNfcAdapter;
  private Handler mNfcHandler;
  private ImageView mNoticeIcon;
  private TextView mNoticeText;
  private View.OnClickListener mOnClickListener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      if (paramView == SwitchCardFragment.this.mIcMore)
        SwitchCardFragment.this.showGuideDialog();
    }
  };
  private SwitchCardPresenter mPresenter;
  private boolean mSelfCancelled = true;
  private BroadcastReceiver mTransactionReciver = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      SwitchCardFragment.this.handleBroadCast(paramIntent);
    }
  };
  private WaveCircle mWaveCircle;
  private MyFingerAuthCallback myFingerAuthCallback = new MyFingerAuthCallback(null);

  private void disableCardEmulation()
  {
    if (this.mDefaultCardInfo == null);
    while (true)
    {
      return;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = "disableCardEmulation";
      AnalyticManager.recordEvent("nfc", String.format("operation_%s_launch", arrayOfObject1));
      try
      {
        if ((!TextUtils.isEmpty(this.mLastActivatedCardAid)) && (SysUtils.isBankCardAid(this.mLastActivatedCardAid)) && (SysUtils.deactivateCard(getActivity(), this.mLastActivatedCardAid)))
          this.mLastActivatedCardAid = null;
        this.mNfcAdapter.setListenTechMask(201326592);
        this.mCMOpened = false;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = "disableCardEmulation";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_success", arrayOfObject3));
      }
      catch (IOException localIOException)
      {
        LogUtils.e("failed to disable card emulation", localIOException);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = "disableCardEmulation";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_failed", arrayOfObject2));
      }
    }
  }

  private void enableCardEmulation()
  {
    long l = System.currentTimeMillis();
    if ((this.mDefaultCardInfo == null) || (TextUtils.isEmpty(this.mDefaultCardInfo.mAid)))
      return;
    this.mPresenter.changeLastUsedCard(this.mDefaultCardInfo.mAid);
    boolean bool = true;
    String str;
    label62: HashMap localHashMap;
    if (isBankCard(this.mDefaultCardInfo))
    {
      str = PrefUtils.getDefaultCard(getActivity(), false);
      if ((!TextUtils.equals(str, this.mDefaultCardInfo.mAid)) && (!TextUtils.isEmpty(str)))
        bool = SysUtils.deactivateCard(getActivity(), str);
      if (bool)
        bool = SysUtils.activateCard(getActivity(), this.mDefaultCardInfo.mAid);
      if (!bool)
        break label370;
      localHashMap = new HashMap();
      localHashMap.put("aid", StringUtils.getAidFactor(this.mDefaultCardInfo.mAid));
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = "enableCardEmulation";
      AnalyticManager.recordEvent("nfc", String.format("operation_%s_launch", arrayOfObject1), localHashMap);
    }
    while (true)
    {
      try
      {
        LogUtils.i("set card emulation mask");
        this.mLastActivatedCardAid = this.mDefaultCardInfo.mAid;
        NfcAdapter localNfcAdapter = this.mNfcAdapter;
        if (!TextUtils.equals(this.mDefaultCardInfo.mCardType, "LNT"))
          continue;
        int i = 262144;
        localNfcAdapter.setListenTechMask(i);
        this.mCMOpened = true;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = "enableCardEmulation";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_success", arrayOfObject3), localHashMap);
        LogUtils.i("active time = " + (System.currentTimeMillis() - l) + ", active result: " + bool);
        break;
        str = PrefUtils.getDefaultCard(getActivity(), true);
        break label62;
        i = 1310720;
        continue;
      }
      catch (IOException localIOException)
      {
        LogUtils.e("failed to enable card emulation", localIOException);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = "enableCardEmulation";
        AnalyticManager.recordEvent("nfc", String.format("operation_%s_failed", arrayOfObject2), localHashMap);
        AnalyticManager.bugReport(getActivity(), "MIQuickPay", l);
        continue;
      }
      label370: AnalyticManager.bugReport(getActivity(), "MIQuickPay", l);
    }
  }

  private int getTradeAmountTextSize(String paramString)
  {
    int i;
    if (paramString.length() <= 7)
      i = 2131230885;
    while (true)
    {
      return getResources().getDimensionPixelSize(i);
      if (paramString.length() <= 9)
      {
        i = 2131230886;
        continue;
      }
      if (paramString.length() <= 11)
      {
        i = 2131230887;
        continue;
      }
      i = 2131230888;
    }
  }

  private void handleBroadCast(Intent paramIntent)
  {
    if (paramIntent == null);
    while (true)
    {
      return;
      if (TextUtils.equals(paramIntent.getAction(), "com.miui.nfc.action.TRANSACTION"))
      {
        this.mHandler.removeMessages(2);
        byte[] arrayOfByte1 = paramIntent.getByteArrayExtra("com.miui.nfc.extras.AID");
        byte[] arrayOfByte2 = paramIntent.getByteArrayExtra("com.miui.nfc.extras.DATA");
        LogUtils.d("aid = " + Coder.bytesToHexString(arrayOfByte1));
        LogUtils.d("data = " + Coder.bytesToHexString(arrayOfByte2));
        IHciEventHandler localIHciEventHandler = HciEventUtils.getHciEventHandler(arrayOfByte1);
        if (localIHciEventHandler == null)
          continue;
        onReceiveHciEvent(localIHciEventHandler.handleData(arrayOfByte1, System.currentTimeMillis(), arrayOfByte2));
        continue;
      }
      if (!TextUtils.equals(paramIntent.getAction(), "android.intent.action.SCREEN_OFF"))
        continue;
      getActivity().finish();
    }
  }

  private void initView(SwitchPageData paramSwitchPageData)
  {
    if (this.mAdapter.getItem(0) == null);
    while (true)
    {
      return;
      this.mDefaultView = ((SlideView)this.mCardLayout.getChildAt(0));
      this.mFingerLayer.setVisibility(0);
      this.mCardLayout.setVisibility(0);
      updateDefaultCardView(-1);
    }
  }

  private boolean isBankCard(CardInfo paramCardInfo)
  {
    if ((paramCardInfo != null) && (TextUtils.equals(paramCardInfo.mCardType, "BANKCARD")));
    for (int i = 1; ; i = 0)
      return i;
  }

  private boolean isFingerAuthAvailable()
  {
    if ((this.mFingerprintManager.isHardwareDetected()) && (this.mFingerprintManager.hasEnrolledFingerprints()));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void onReceiveHciEvent(HciEventInfo paramHciEventInfo)
  {
    if (paramHciEventInfo == null);
    String str1;
    label82: TextView localTextView;
    FlashView localFlashView;
    ImageView localImageView;
    SlideView localSlideView;
    while (true)
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("aid", StringUtils.getAidFactor(this.mDefaultCardInfo.mAid));
      localHashMap.put("time", StringUtils.millsToTime(paramHciEventInfo.mTradeTime, "MM-dd hh:mm:ss"));
      localHashMap.put("trade_amount", String.valueOf(paramHciEventInfo.mTradeAmount));
      if (!paramHciEventInfo.isTradeSuccess())
        break;
      str1 = "operation_%s_success";
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = "hciEvent";
      AnalyticManager.recordEvent("MIQuickPay", String.format(str1, arrayOfObject), localHashMap);
      if (this.mDefaultView == null)
        continue;
      this.mCardLayout.setScreenTouchable(false);
      if (this.mAdapter.getCount() > 1)
        this.mCardLayout.removeViews(1, -1 + this.mAdapter.getCount());
      this.mCMOpened = false;
      this.mWaveCircle.stopWave();
      this.mNoticeText.setVisibility(8);
      this.mNoticeIcon.setVisibility(8);
      MiuiDigitFontTextView localMiuiDigitFontTextView = (MiuiDigitFontTextView)this.mDefaultView.findViewById(2131493005);
      String str2 = StringUtils.formatAmount(paramHciEventInfo.mTradeAmount);
      localMiuiDigitFontTextView.setTextSize(0, getTradeAmountTextSize(str2));
      localMiuiDigitFontTextView.setText(str2);
      ((TextView)this.mDefaultView.findViewById(2131493006)).setText(StringUtils.millsToTime(paramHciEventInfo.mTradeTime, " yyyy-MM-dd HH:mm"));
      localTextView = (TextView)this.mDefaultView.findViewById(2131493010);
      localFlashView = (FlashView)this.mDefaultView.findViewById(2131492907);
      localImageView = (ImageView)this.mDefaultView.findViewById(2131493009);
      localSlideView = this.mDefaultView;
      if (getActivity().getActionBar() == null)
        break label365;
    }
    label365: for (boolean bool = true; ; bool = false)
    {
      localSlideView.setShowTitleBar(bool);
      this.mDefaultView.showHeaderView(new View.OnLayoutChangeListener(paramHciEventInfo, localImageView, localTextView, localFlashView)
      {
        public void onLayoutChange(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
        {
          SwitchCardFragment.this.mDefaultView.setDelta(paramInt4);
          SwitchCardFragment.this.mDefaultView.slideDown(new Animator.AnimatorListener()
          {
            public void onAnimationCancel(Animator paramAnimator)
            {
            }

            public void onAnimationEnd(Animator paramAnimator)
            {
              if (SwitchCardFragment.5.this.val$hciEventInfo.isTradeSuccess())
              {
                if (!SwitchCardFragment.5.this.val$hciEventInfo.mIsBankEvent)
                  break label91;
                SwitchCardFragment.5.this.val$viewOkImg.setVisibility(0);
                SwitchCardFragment.5.this.val$viewOk.setText(2131296294);
              }
              while (true)
              {
                TextView localTextView2 = SwitchCardFragment.5.this.val$viewOk;
                localTextView2.setVisibility(0);
                SwitchCardFragment.this.mHandler.sendEmptyMessageDelayed(4, 3000L);
                return;
                label91: TextView localTextView1 = SwitchCardFragment.5.this.val$viewOk;
                SwitchCardFragment localSwitchCardFragment = SwitchCardFragment.this;
                Object[] arrayOfObject = new Object[1];
                arrayOfObject[0] = StringUtils.formatAmount(SwitchCardFragment.5.this.val$hciEventInfo.mBalance);
                localTextView1.setText(localSwitchCardFragment.getString(2131296542, arrayOfObject));
              }
            }

            public void onAnimationRepeat(Animator paramAnimator)
            {
            }

            public void onAnimationStart(Animator paramAnimator)
            {
            }
          });
          this.val$cardContent.flash();
          Uri localUri = RingtoneManager.getDefaultUri(2);
          RingtoneManager.getRingtone(SwitchCardFragment.this.getActivity(), localUri).play();
          Vibrator localVibrator = (Vibrator)SwitchCardFragment.this.getActivity().getSystemService("vibrator");
          if (localVibrator != null)
            localVibrator.vibrate(500L);
        }
      });
      this.mIcMore.setVisibility(8);
      break;
      str1 = "operation_%s_failed";
      break label82;
    }
  }

  private void openBankCard()
  {
    MiTsmManager localMiTsmManager = MiTsmManager.getInstance();
    try
    {
      localMiTsmManager.startOpenCard(getActivity(), 1, null);
      return;
    }
    catch (UnSupportedException localUnSupportedException)
    {
      while (true)
        LogUtils.e("Not support to open bankcard");
    }
  }

  private void openTransCard()
  {
    startActivity(new Intent(getActivity(), MainActivity.class));
  }

  private void refreshFingerView(boolean paramBoolean)
  {
    boolean bool = isBankCard(this.mDefaultCardInfo);
    if (paramBoolean)
    {
      if (this.mDefaultView != null)
        this.mDefaultView.stopShading();
      this.mWaveCircle.startWave();
      if (bool)
      {
        this.mNoticeIcon.setImageDrawable(this.mFingerPrinterDrawable);
        this.mFingerPrinterDrawable.start();
        this.mNoticeText.setText(2131296293);
        this.mNoticeText.startAnimation(this.mFadeInAnim);
        this.mHandler.sendEmptyMessageDelayed(2, 600L);
      }
    }
    while (true)
    {
      return;
      this.mNoticeIcon.setImageResource(2130837602);
      this.mNoticeText.setText(2131296298);
      continue;
      this.mWaveCircle.stopWave();
      if (bool)
      {
        this.mNoticeIcon.setImageResource(2130837596);
        TextView localTextView = this.mNoticeText;
        if (this.mFingerVefifyedCount < 10);
        for (int i = 2131296292; ; i = 2131296287)
        {
          localTextView.setText(i);
          break;
        }
      }
      this.mDefaultView.startShading();
      this.mNoticeIcon.setImageResource(2130837604);
      this.mNoticeText.setText(2131296297);
    }
  }

  private void refreshIcMoreView()
  {
    if (this.mCalledFromVolumnDown)
      this.mIcMore.setVisibility(8);
    while (true)
    {
      return;
      if (this.mCalledFromRFOn)
      {
        this.mIcMore.setVisibility(0);
        continue;
      }
      if (isBankCard(this.mDefaultCardInfo))
      {
        this.mIcMore.setVisibility(8);
        continue;
      }
      this.mIcMore.setVisibility(0);
    }
  }

  private void restartListener()
  {
    stopListener();
    startListener();
  }

  private void showEmptyView()
  {
    stopListener();
    this.mCardLayout.setVisibility(8);
    showGuideDialog();
  }

  private void showGuideDialog()
  {
    View localView = LayoutInflater.from(getActivity()).inflate(2130903075, null);
    TextView localTextView1 = (TextView)localView.findViewById(2131492876);
    TextView localTextView2 = (TextView)localView.findViewById(2131493053);
    ImageView localImageView = (ImageView)localView.findViewById(2131493054);
    TextView localTextView3 = (TextView)localView.findViewById(2131493055);
    if ((this.mCalledFromVolumnDown) || ((this.mDefaultCardInfo != null) && (!isBankCard(this.mDefaultCardInfo))))
    {
      localTextView1.setText(2131296530);
      localTextView2.setText(2131296531);
      localTextView3.setText(2131296534);
      localImageView.setImageResource(2130837653);
      if (!this.mCardList.isEmpty())
        break label165;
      showOpenCardDialog(localView);
    }
    while (true)
    {
      return;
      localTextView1.setText(2131296532);
      localTextView2.setText(2131296533);
      localTextView3.setText(2131296535);
      localImageView.setImageResource(2130837522);
      break;
      label165: localTextView3.setVisibility(8);
      showQuickStartDialog(localView);
    }
  }

  private void showOpenCardDialog(View paramView)
  {
    new AlertDialog.Builder(getActivity()).setView(paramView).setPositiveButton(2131296538, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        if (SwitchCardFragment.this.mCalledFromVolumnDown)
          SwitchCardFragment.this.openTransCard();
        while (true)
        {
          SwitchCardFragment.this.getActivity().finish();
          return;
          SwitchCardFragment.this.openBankCard();
        }
      }
    }).setNegativeButton(2131296537, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        SwitchCardFragment.this.getActivity().finish();
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        SwitchCardFragment.this.getActivity().finish();
      }
    }).create().show();
  }

  private void showQuickStartDialog(View paramView)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity()).setView(paramView).setNegativeButton(2131296318, null);
    if ((!isBankCard(this.mDefaultCardInfo)) && (!"public_transportation_shortcuts".equals(PrefUtils.getLongPressVolumeDownState(getActivity()))))
    {
      TextView localTextView = (TextView)paramView.findViewById(2131493055);
      localTextView.setText(2131296536);
      localTextView.setVisibility(0);
      localBuilder.setNegativeButton(2131296537, null);
      localBuilder.setPositiveButton(2131296539, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          PrefUtils.setLongPressVolumeDownStateToPay(SwitchCardFragment.this.getActivity());
          UiUtils.showToast(SwitchCardFragment.this.getActivity(), 2131296540);
        }
      });
    }
    localBuilder.create().show();
  }

  private void startListener()
  {
    if (!SysUtils.isForegroundApp(getActivity()));
    while (true)
    {
      return;
      if (!isFingerAuthAvailable())
      {
        LogUtils.w("finger auth unavailable");
        continue;
      }
      if (this.mCardList.isEmpty())
        continue;
      if (this.mCancellationSignal == null)
        this.mCancellationSignal = new CancellationSignal();
      if (!this.mSelfCancelled)
        continue;
      this.mSelfCancelled = false;
      LogUtils.d("start listening authentication");
      this.mFingerprintManager.authenticate(null, this.mCancellationSignal, 0, this.myFingerAuthCallback, null);
    }
  }

  private void stopListener()
  {
    if (this.mCancellationSignal != null)
    {
      this.mCancellationSignal.cancel();
      this.mSelfCancelled = true;
      this.mCancellationSignal = null;
    }
  }

  private void updateDefaultCardView(int paramInt)
  {
    restartListener();
    this.mNfcHandler.obtainMessage(2).sendToTarget();
    TextView localTextView = (TextView)this.mDefaultView.findViewById(2131492885);
    this.mDefaultCardInfo = this.mAdapter.getItem(0).mCardInfo;
    ((TextView)this.mDefaultView.findViewById(2131492908)).setText(this.mDefaultCardInfo.mCardName);
    if (paramInt != -1)
    {
      View localView = this.mCardLayout.getChildAt(paramInt);
      StackItem localStackItem = this.mAdapter.getItem(paramInt);
      if ((localView != null) && (localStackItem != null))
        ((TextView)localView.findViewById(2131492908)).setText(localStackItem.getCardInfo().mCardName);
    }
    refreshIcMoreView();
    if (isBankCard(this.mDefaultCardInfo))
    {
      this.mDefaultView.stopShading();
      this.mNoticeIcon.setImageResource(2130837596);
      this.mNoticeText.setText(2131296287);
      BankCardInfo localBankCardInfo = (BankCardInfo)this.mDefaultCardInfo;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = StringUtils.tail(localBankCardInfo.mPanLastDigits, 4);
      localTextView.setText(getString(2131296463, arrayOfObject));
      localTextView.setVisibility(0);
    }
    while (true)
    {
      this.mFingerVefifyedCount = 10;
      return;
      this.mNoticeIcon.setImageResource(2130837604);
      this.mNoticeText.setText(2131296297);
      localTextView.setVisibility(8);
    }
  }

  public void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mCardManager = new CardManager(getActivity());
    this.mNfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
    this.mHandlerThread = new MyHandlerThread("SwitchCardFragment");
    this.mHandlerThread.start();
    this.mNfcHandler = new Handler(this.mHandlerThread.getLooper(), this.mHandlerThread);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.miui.nfc.action.TRANSACTION");
    localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
    localIntentFilter.setPriority(1000);
    getActivity().registerReceiver(this.mTransactionReciver, localIntentFilter);
    this.mFingerprintManager = ((FingerprintManager)getActivity().getSystemService("fingerprint"));
    this.mPresenter = new SwitchCardPresenter();
    this.mPresenter.init(getActivity(), getArguments());
  }

  public void handleError(int paramInt, String paramString)
  {
    if (paramInt == 1)
      this.mPresenter.downloadCardData();
    while (true)
    {
      return;
      if (paramInt == 2)
      {
        showEmptyView();
        UiUtils.showToast(getActivity(), 2131296569);
        continue;
      }
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
      refreshFingerView(((Boolean)paramMessage.obj).booleanValue());
      continue;
      this.mNoticeIcon.setImageResource(2130837603);
      this.mNoticeIcon.startAnimation(this.mFadeInAnim);
      this.mNoticeText.setText(2131296291);
      this.mNoticeText.startAnimation(this.mFadeInAnim);
      continue;
      List localList = (List)paramMessage.obj;
      if ((localList != null) && (!localList.isEmpty()))
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          StackItem localStackItem = new StackItem((CardInfo)localIterator.next());
          this.mCardList.add(localStackItem);
        }
        this.mAdapter.notifyDataSetChanged();
        refreshFingerView(false);
        continue;
      }
      getActivity().finish();
      continue;
      LogUtils.d("SwitchCardFragment will finish its attached activity ");
      getActivity().finish();
    }
  }

  public void onDestroy()
  {
    disableCardEmulation();
    this.mCardManager.release();
    this.mHandlerThread.quit();
    this.mHandlerThread.interrupt();
    this.mNfcHandler.removeCallbacksAndMessages(null);
    getActivity().unregisterReceiver(this.mTransactionReciver);
    super.onDestroy();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903080, null);
  }

  public void onPause()
  {
    this.mWaveCircle.stopWave();
    this.mPresenter.detach();
    if (this.mDefaultView != null)
      this.mDefaultView.stopShading();
    this.mHandler.sendEmptyMessageDelayed(5, 3000L);
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
    this.mHandler.removeMessages(5);
    LogUtils.i("SwitchCardFragment onResumed, event_source: " + this.mAttatchedActivitySource);
    this.mCalledFromVolumnDown = TextUtils.equals(this.mAttatchedActivitySource, "key_volume_down");
    this.mCalledFromRFOn = TextUtils.equals(this.mAttatchedActivitySource, "key_rf_on");
    if (!this.mCalledFromVolumnDown)
      checkFingerStatus();
    refreshIcMoreView();
    if (this.mCMOpened)
    {
      this.mWaveCircle.startWave();
      if (this.mDefaultView != null)
        this.mDefaultView.stopShading();
    }
    while (true)
    {
      startListener();
      this.mPresenter.attach(this);
      return;
      if ((this.mDefaultCardInfo == null) || (isBankCard(this.mDefaultCardInfo)))
        continue;
      this.mDefaultView.startShading();
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    this.mCardLayout = ((CardStackLayout)paramView.findViewById(2131493007));
    this.mFingerLayer = paramView.findViewById(2131493077);
    this.mNoticeIcon = ((ImageView)paramView.findViewById(2131493078));
    this.mFadeInAnim = AnimationUtils.loadAnimation(getActivity(), 2130968579);
    this.mNoticeText = ((TextView)paramView.findViewById(2131493079));
    this.mWaveCircle = ((WaveCircle)paramView.findViewById(2131493076));
    this.mFingerPrinterDrawable = ((AnimationDrawable)getResources().getDrawable(2130837595));
    this.mFingerPrinterDrawable.setOneShot(true);
    this.mAdapter = new CardStackAdapter(getActivity(), this.mCardList);
    this.mAdapter.setDefaultCardChangedListener(this.mDefaultCardChangedListener);
    this.mCardLayout.setAdapter(this.mAdapter);
    this.mIcMore = ((ImageView)paramView.findViewById(2131493080));
    this.mIcMore.setOnClickListener(this.mOnClickListener);
  }

  public void setAttachedActivitySource(String paramString)
  {
    this.mAttatchedActivitySource = paramString;
  }

  protected boolean showErrorWhenNFCOff()
  {
    return false;
  }

  public void updateCardStack(SwitchPageData paramSwitchPageData)
  {
    if (this.mCalledFromVolumnDown)
    {
      if (paramSwitchPageData.mDefaultTransCard != null)
      {
        StackItem localStackItem5 = new StackItem(paramSwitchPageData.mDefaultTransCard);
        this.mCardList.add(localStackItem5);
      }
      if (this.mCardList.isEmpty())
        break label345;
      this.mAdapter.notifyDataSetChanged();
      initView(paramSwitchPageData);
      if (this.mCalledFromVolumnDown)
      {
        this.mDefaultCardInfo = ((StackItem)this.mCardList.get(0)).mCardInfo;
        this.mNfcHandler.obtainMessage(1).sendToTarget();
      }
      startListener();
    }
    while (true)
    {
      return;
      Object localObject = null;
      if (paramSwitchPageData.mLastUsedCard == null)
        if (paramSwitchPageData.mDefaultTransCard != null)
          this.mCardList.add(new StackItem(paramSwitchPageData.mDefaultTransCard));
      while (true)
      {
        Iterator localIterator = paramSwitchPageData.mBankCardInfos.iterator();
        while (localIterator.hasNext())
        {
          BankCardInfo localBankCardInfo = (BankCardInfo)localIterator.next();
          if (TextUtils.equals(localBankCardInfo.mAid, (CharSequence)localObject))
            continue;
          StackItem localStackItem2 = new StackItem(localBankCardInfo);
          this.mCardList.add(localStackItem2);
        }
        break;
        if (isBankCard(paramSwitchPageData.mLastUsedCard))
        {
          localObject = paramSwitchPageData.mLastUsedCard.mAid;
          StackItem localStackItem4 = new StackItem(paramSwitchPageData.mLastUsedCard);
          this.mCardList.add(localStackItem4);
          if (paramSwitchPageData.mDefaultTransCard == null)
            continue;
          this.mCardList.add(new StackItem(paramSwitchPageData.mDefaultTransCard));
          continue;
        }
        if (TextUtils.equals(paramSwitchPageData.mLastUsedCard.mAid, paramSwitchPageData.mDefaultTransCard.mAid))
        {
          StackItem localStackItem1 = new StackItem(paramSwitchPageData.mLastUsedCard);
          this.mCardList.add(localStackItem1);
          continue;
        }
        StackItem localStackItem3 = new StackItem(paramSwitchPageData.mDefaultTransCard);
        this.mCardList.add(localStackItem3);
      }
      label345: showEmptyView();
    }
  }

  public static class StackItem
  {
    private CardInfo mCardInfo;

    StackItem(CardInfo paramCardInfo)
    {
      this.mCardInfo = paramCardInfo;
    }

    public CardInfo getCardInfo()
    {
      return this.mCardInfo;
    }
  }

  private class MyFingerAuthCallback extends FingerprintManager.AuthenticationCallback
  {
    private MyFingerAuthCallback()
    {
    }

    public void onAuthenticationError(int paramInt, CharSequence paramCharSequence)
    {
      LogUtils.w("onAuthenticationErro: errorCode = " + paramInt + " ,errString = " + paramCharSequence);
    }

    public void onAuthenticationFailed()
    {
      LogUtils.d("onAuthenticationSucceeded");
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = "FingerprintAuthentication";
      AnalyticManager.recordEvent("pay", String.format("operation_%s_failed", arrayOfObject));
      if (SwitchCardFragment.access$2106(SwitchCardFragment.this) < 0)
        LogUtils.w("Fingerprint verification has exceeded the maximum number of times");
      while (true)
      {
        return;
        SwitchCardFragment.this.mNfcHandler.obtainMessage(2).sendToTarget();
      }
    }

    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult paramAuthenticationResult)
    {
      LogUtils.d("onAuthenticationSucceeded");
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = "FingerprintAuthentication";
      AnalyticManager.recordEvent("pay", String.format("operation_%s_success", arrayOfObject));
      SwitchCardFragment.access$2102(SwitchCardFragment.this, 10);
      SwitchCardFragment.this.mNfcHandler.obtainMessage(1).sendToTarget();
    }
  }

  private class MyHandlerThread extends HandlerThread
    implements Handler.Callback
  {
    public static final int MSG_CLOSE_CARD_EMULATION = 2;
    public static final int MSG_FP_AUTH_TIMER = 3;
    public static final int MSG_OEPN_CARD_EMULATION = 1;

    public MyHandlerThread(String arg2)
    {
      super();
    }

    public boolean handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        return false;
        SwitchCardFragment.this.mHandler.obtainMessage(1, Boolean.valueOf(true)).sendToTarget();
        SwitchCardFragment.this.mNfcHandler.removeMessages(3);
        SwitchCardFragment.this.mNfcHandler.sendEmptyMessageDelayed(3, 60000L);
        SwitchCardFragment.this.enableCardEmulation();
        continue;
        SwitchCardFragment.this.mHandler.obtainMessage(1, Boolean.valueOf(false)).sendToTarget();
        SwitchCardFragment.this.disableCardEmulation();
        continue;
        SwitchCardFragment.this.restartListener();
        SwitchCardFragment.this.mNfcHandler.obtainMessage(2).sendToTarget();
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.quick.SwitchCardFragment
 * JD-Core Version:    0.6.0
 */