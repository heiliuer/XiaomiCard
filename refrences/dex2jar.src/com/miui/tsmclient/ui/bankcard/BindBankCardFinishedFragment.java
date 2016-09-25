package com.miui.tsmclient.ui.bankcard;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.miui.tsmclient.UpdateCardsService;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.eventbus.CardInfosEvent;
import com.miui.tsmclient.ui.widget.LayoutAware;
import com.miui.tsmclient.util.ImageUtil;
import com.miui.tsmclient.util.ImageUtil.ThumbnailFormat;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.StringUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.FailReason.FailType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;

public class BindBankCardFinishedFragment extends BaseBankCardFragment
{
  private DisplayImageOptions mBankCardImageOptions;
  private TextView mCardNumView;
  private TextView mCardTitleView;
  private View mConvertView;
  private Button mFinishedButton;
  private ImageLoader mImageLoader;
  private ImageLoadingListener mImageLoaderListener = new SimpleImageLoadingListener()
  {
    public void onLoadingComplete(String paramString, View paramView, Bitmap paramBitmap)
    {
      LogUtils.d("BindBankCardFinishedFragment: onloading card image succeed, url: " + paramString);
      paramView.findViewById(2131492908).setVisibility(8);
      paramView.setWillNotDraw(false);
    }

    public void onLoadingFailed(String paramString, View paramView, FailReason paramFailReason)
    {
      paramView.setWillNotDraw(true);
      paramView.findViewById(2131492908).setVisibility(0);
      LogUtils.w("onloading card image failed, reason: " + paramFailReason.getType().toString());
    }
  };
  private ImageUtil.ThumbnailFormat mImageThumbnailFormat;

  private void refreshView(BankCardInfo paramBankCardInfo)
  {
    this.mCardTitleView.setText(paramBankCardInfo.mBankName);
    TextView localTextView = this.mCardNumView;
    Activity localActivity = getActivity();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = StringUtils.tail(paramBankCardInfo.mPanLastDigits, 4);
    localTextView.setText(localActivity.getString(2131296463, arrayOfObject));
    this.mImageLoader.displayImage(ImageUtil.getUrl(paramBankCardInfo.mCardArt, this.mImageThumbnailFormat), new LayoutAware(getActivity(), this.mConvertView), this.mBankCardImageOptions, this.mImageLoaderListener);
  }

  protected void doCreate(Bundle paramBundle)
  {
    super.doCreate(paramBundle);
    this.mImageThumbnailFormat = ImageUtil.ThumbnailFormat.getMaxWidthHeightThumnail(getResources().getDimensionPixelSize(2131230874), getResources().getDimensionPixelSize(2131230875), 2);
    this.mImageLoader = ImageLoader.getInstance();
    this.mBankCardImageOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565).showImageOnLoading(2130837520).showImageOnFail(2130837520).build();
    EventBus.getDefault().register(this);
    UpdateCardsService.queryCardInfos(getActivity());
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = "BindBankCardFinishedFragment";
    AnalyticManager.recordEvent("bank", String.format("key_enter/%s", arrayOfObject));
  }

  public void onDestroy()
  {
    EventBus.getDefault().unregister(this);
    super.onDestroy();
  }

  public void onEventMainThread(CardInfosEvent paramCardInfosEvent)
  {
    Iterator localIterator = paramCardInfosEvent.getCardInfos().iterator();
    while (localIterator.hasNext())
    {
      CardInfo localCardInfo = (CardInfo)localIterator.next();
      if (!(localCardInfo instanceof BankCardInfo))
        continue;
      BankCardInfo localBankCardInfo = (BankCardInfo)localCardInfo;
      if (!localBankCardInfo.mAid.equals(((BankCardInfo)this.mCardInfo).mAid))
        continue;
      LogUtils.d("BindBankCardFinishedFragment broadcastReceiver: get bankCardInfo succeed.");
      refreshView(localBankCardInfo);
    }
  }

  public View onInflateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903049, paramViewGroup, false);
    this.mConvertView = localView.findViewById(2131492906);
    this.mCardNumView = ((TextView)this.mConvertView.findViewById(2131492885));
    this.mCardTitleView = ((TextView)this.mConvertView.findViewById(2131492908));
    this.mFinishedButton = ((Button)localView.findViewById(2131492909));
    this.mFinishedButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        BindBankCardFinishedFragment.this.getActivity().setResult(-1);
        BindBankCardFinishedFragment.this.getActivity().finish();
      }
    });
    refreshView((BankCardInfo)this.mCardInfo);
    return localView;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.bankcard.BindBankCardFinishedFragment
 * JD-Core Version:    0.6.0
 */