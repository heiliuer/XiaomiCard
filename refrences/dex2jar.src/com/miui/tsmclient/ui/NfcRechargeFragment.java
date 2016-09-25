package com.miui.tsmclient.ui;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.ActionToken.TokenType;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.model.PayableCardManager;
import com.miui.tsmclient.pay.OrderInfo;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NfcUtils;
import com.miui.tsmclient.util.UiUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import com.tsmclient.smartcard.ByteArray;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;

public class NfcRechargeFragment extends BaseCardFragment<PayableCardInfo>
{
  public static final String KEY_AMOUNT = "amount";
  public static final String KEY_ORDER_INFO = "order_info";
  private static final int MSG_CARD_INVALID = 4;
  private static final int MSG_CARD_ROTATE_SHOULD_CANCEL = 3;
  private static final int MSG_RECHARGE_FAILURE = 1;
  private static final int MSG_RECHARGE_SUCCESS = 2;
  private int mAmount;
  private PayableCardManager mCardManager;
  private TextView mCardText;
  private ImageView mCircle1;
  private ImageView mCircle2;
  private ImageView mCircle3;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      }
      while (true)
      {
        return;
        NfcRechargeFragment.this.mText.setText(2131296386);
        NfcRechargeFragment.this.mTextSub.setText(2131296387);
        NfcRechargeFragment.access$202(NfcRechargeFragment.this, true);
        NfcRechargeFragment.this.startCardAnimation();
        UiUtils.showToast(NfcRechargeFragment.this.getActivity(), 2131296301);
        continue;
        NfcRechargeFragment.access$202(NfcRechargeFragment.this, true);
        UiUtils.showToast(NfcRechargeFragment.this.getActivity(), 2131296318);
        continue;
        NfcRechargeFragment.this.mCardText.clearAnimation();
        continue;
        NfcRechargeFragment.this.mUnResolvedTip.setVisibility(0);
        if (paramMessage.obj == null)
          continue;
        TextView localTextView = NfcRechargeFragment.this.mUnResolvedTip;
        NfcRechargeFragment localNfcRechargeFragment = NfcRechargeFragment.this;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramMessage.obj;
        localTextView.setText(localNfcRechargeFragment.getString(2131296390, arrayOfObject));
      }
    }
  };
  private Tag mNewTag;
  private Tag mOldTag;
  private OrderInfo mOrderInfo;
  private ImageView mPhoneView;
  private boolean mRechargeFinished = false;
  private AnimatorSet mRippleSet1;
  private AnimatorSet mRippleSet2;
  private AnimatorSet mRippleSet3;
  private TextView mText;
  private TextView mTextSub;
  private TextView mUnResolvedTip;

  private void doRecharge(Tag paramTag)
  {
    this.mText.setText(2131296384);
    this.mTextSub.setText(2131296385);
    recharge(paramTag);
  }

  private AnimatorSet getRippleAnimatorSet(ImageView paramImageView, long paramLong)
  {
    float[] arrayOfFloat1 = new float[2];
    arrayOfFloat1[0] = 0.0F;
    arrayOfFloat1[1] = 0.5F;
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramImageView, "alpha", arrayOfFloat1);
    float[] arrayOfFloat2 = new float[2];
    arrayOfFloat2[0] = 1.0F;
    arrayOfFloat2[1] = 1.25F;
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramImageView, "scaleX", arrayOfFloat2);
    float[] arrayOfFloat3 = new float[2];
    arrayOfFloat3[0] = 1.0F;
    arrayOfFloat3[1] = 1.25F;
    ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(paramImageView, "scaleY", arrayOfFloat3);
    float[] arrayOfFloat4 = new float[2];
    arrayOfFloat4[0] = 0.5F;
    arrayOfFloat4[1] = 0.0F;
    ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(paramImageView, "alpha", arrayOfFloat4);
    float[] arrayOfFloat5 = new float[2];
    arrayOfFloat5[0] = 1.25F;
    arrayOfFloat5[1] = 1.5F;
    ObjectAnimator localObjectAnimator5 = ObjectAnimator.ofFloat(paramImageView, "scaleX", arrayOfFloat5);
    float[] arrayOfFloat6 = new float[2];
    arrayOfFloat6[0] = 1.25F;
    arrayOfFloat6[1] = 1.5F;
    ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(paramImageView, "scaleY", arrayOfFloat6);
    AnimatorSet localAnimatorSet1 = new AnimatorSet();
    AnimatorSet localAnimatorSet2 = new AnimatorSet();
    AnimatorSet localAnimatorSet3 = new AnimatorSet();
    localAnimatorSet1.setInterpolator(new LinearInterpolator());
    localAnimatorSet2.setInterpolator(new LinearInterpolator());
    localAnimatorSet1.play(localObjectAnimator1).with(localObjectAnimator2).with(localObjectAnimator3);
    localAnimatorSet2.play(localObjectAnimator4).with(localObjectAnimator5).with(localObjectAnimator6);
    localAnimatorSet3.play(localAnimatorSet1).before(localAnimatorSet2);
    localAnimatorSet3.setDuration(500L);
    localAnimatorSet3.setStartDelay(paramLong);
    return localAnimatorSet3;
  }

  private void recharge(Tag paramTag)
  {
    this.mCardManager.recharge(getActivity(), (PayableCardInfo)this.mCardInfo, this.mOrderInfo, paramTag, new MiTsmCallback()
    {
      public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
      {
        if (paramInt == 2002)
          NfcRechargeFragment.this.mHandler.obtainMessage(4, paramArrayOfObject[0]).sendToTarget();
        LogUtils.w("recharge failed!ErrorCode:" + paramInt + ",errorMsg:" + paramString);
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("recharge_amount", String.valueOf(NfcRechargeFragment.this.mAmount));
        localHashMap1.put("recharge_by_nfc", String.valueOf(true));
        AnalyticManager.getInstance().trackResult(NfcRechargeFragment.this.getActivity(), "recharge", ((PayableCardInfo)NfcRechargeFragment.this.mCardInfo).mCardType, paramInt, localHashMap1);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("error_code", Integer.toString(paramInt));
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = ((PayableCardInfo)NfcRechargeFragment.this.mCardInfo).mCardType;
        AnalyticManager.recordCalculateEvent("pay", String.format("nfc_recharge/%s", arrayOfObject), NfcRechargeFragment.this.mAmount, localHashMap2);
        NfcRechargeFragment.this.mHandler.sendEmptyMessage(1);
      }

      public void onSuccess(int paramInt, Object[] paramArrayOfObject)
      {
        LogUtils.d("RechargeFragment#OnActivityResult() called!recharge success!");
        HashMap localHashMap1 = new HashMap();
        localHashMap1.put("recharge_amount", String.valueOf(NfcRechargeFragment.this.mAmount));
        localHashMap1.put("recharge_by_nfc", String.valueOf(true));
        AnalyticManager.getInstance().trackResult(NfcRechargeFragment.this.getActivity(), "recharge", ((PayableCardInfo)NfcRechargeFragment.this.mCardInfo).mCardType, 0, localHashMap1);
        HashMap localHashMap2 = new HashMap();
        localHashMap2.put("error_code", Integer.toString(0));
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = ((PayableCardInfo)NfcRechargeFragment.this.mCardInfo).mCardType;
        AnalyticManager.recordCalculateEvent("pay", String.format("nfc_recharge/%s", arrayOfObject), NfcRechargeFragment.this.mAmount, localHashMap2);
        NfcRechargeFragment.this.startResultActivity();
      }
    });
  }

  private void startCardAnimation()
  {
    this.mPhoneView.setBackgroundColor(getResources().getColor(2131165260));
    Animation localAnimation = AnimationUtils.loadAnimation(getActivity(), 2130968576);
    localAnimation.setDuration(1000L);
    this.mCardText.startAnimation(localAnimation);
  }

  private void startRechargingAnim()
  {
    this.mHandler.sendEmptyMessage(3);
    this.mPhoneView.setBackgroundColor(getResources().getColor(2131165261));
    startRippleAnim();
  }

  private void startResultActivity()
  {
    Intent localIntent = new Intent(getActivity(), TSMResultActivity.class);
    localIntent.putExtra("card_info", this.mCardInfo);
    localIntent.putExtra("amount", this.mAmount);
    localIntent.putExtra(TSMResultFragment.KEY_INTENT_FROM, TSMResultFragment.FROM_NFC_RECHARGE);
    startActivityForResult(localIntent, 1);
  }

  private void startRippleAnim()
  {
    this.mRippleSet1 = getRippleAnimatorSet(this.mCircle1, 0L);
    this.mRippleSet2 = getRippleAnimatorSet(this.mCircle2, 250L);
    this.mRippleSet3 = getRippleAnimatorSet(this.mCircle3, 500L);
    this.mRippleSet3.addListener(new Animator.AnimatorListener()
    {
      public void onAnimationCancel(Animator paramAnimator)
      {
        NfcRechargeFragment.access$202(NfcRechargeFragment.this, true);
      }

      public void onAnimationEnd(Animator paramAnimator)
      {
        if (!NfcRechargeFragment.this.mRechargeFinished)
        {
          NfcRechargeFragment.this.mRippleSet1.start();
          NfcRechargeFragment.this.mRippleSet2.start();
          NfcRechargeFragment.this.mRippleSet3.start();
        }
      }

      public void onAnimationRepeat(Animator paramAnimator)
      {
      }

      public void onAnimationStart(Animator paramAnimator)
      {
        NfcRechargeFragment.access$202(NfcRechargeFragment.this, false);
      }
    });
    this.mRippleSet1.start();
    this.mRippleSet2.start();
    this.mRippleSet3.start();
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mCardManager = new PayableCardManager(getActivity().getApplicationContext());
    Bundle localBundle = getArguments();
    if (localBundle != null)
    {
      this.mOldTag = ((Tag)localBundle.getParcelable("nfc_tag"));
      this.mOrderInfo = ((OrderInfo)localBundle.getParcelable("order_info"));
    }
    if (this.mOrderInfo != null)
    {
      Iterator localIterator = this.mOrderInfo.mActionTokens.iterator();
      while (localIterator.hasNext())
      {
        ActionToken localActionToken = (ActionToken)localIterator.next();
        if (localActionToken.mType != ActionToken.TokenType.recharge)
          continue;
        this.mAmount = localActionToken.mRechargeAmount;
      }
    }
  }

  protected void doNewActivityIntent(Intent paramIntent)
  {
    startRechargingAnim();
    this.mNewTag = ((Tag)paramIntent.getParcelableExtra("android.nfc.extra.TAG"));
    if ((this.mNewTag != null) && (this.mOldTag != null))
    {
      if (!ByteArray.equals(ByteArray.wrap(this.mNewTag.getId()), ByteArray.wrap(this.mOldTag.getId())))
        break label67;
      doRecharge(this.mNewTag);
    }
    while (true)
    {
      return;
      label67: AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
      localBuilder.setMessage(2131296381).setTitle(2131296380);
      localBuilder.setPositiveButton(2131296281, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          NfcRechargeFragment.access$902(NfcRechargeFragment.this, NfcRechargeFragment.this.mNewTag);
          NfcRechargeFragment.this.doRecharge(NfcRechargeFragment.this.mNewTag);
        }
      });
      localBuilder.setNegativeButton(2131296282, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          NfcRechargeFragment.this.startCardAnimation();
          NfcRechargeFragment.access$202(NfcRechargeFragment.this, true);
          UiUtils.showToast(NfcRechargeFragment.this.getActivity(), 2131296382);
        }
      });
      localBuilder.create().show();
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default:
      LogUtils.i("No handler for request:" + paramInt1 + " result:" + paramInt2);
    case 1:
    }
    while (true)
    {
      return;
      switch (paramInt2)
      {
      default:
        break;
      case -1:
        getActivity().setResult(-1);
        getActivity().finish();
      }
    }
  }

  public void onDestroy()
  {
    this.mCardManager.release();
    super.onDestroy();
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130903074, paramViewGroup, false);
  }

  public void onPause()
  {
    NfcUtils.stopPoll(getActivity());
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
    NfcUtils.startPoll(getActivity());
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.mText = ((TextView)paramView.findViewById(2131493045));
    this.mTextSub = ((TextView)paramView.findViewById(2131493046));
    this.mCardText = ((TextView)paramView.findViewById(2131493048));
    this.mUnResolvedTip = ((TextView)paramView.findViewById(2131493047));
    this.mPhoneView = ((ImageView)paramView.findViewById(2131493049));
    this.mCircle1 = ((ImageView)paramView.findViewById(2131493050));
    this.mCircle2 = ((ImageView)paramView.findViewById(2131493051));
    this.mCircle3 = ((ImageView)paramView.findViewById(2131493052));
    this.mCircle1.setAlpha(0.0F);
    this.mCircle2.setAlpha(0.0F);
    this.mCircle3.setAlpha(0.0F);
    startCardAnimation();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.NfcRechargeFragment
 * JD-Core Version:    0.6.0
 */