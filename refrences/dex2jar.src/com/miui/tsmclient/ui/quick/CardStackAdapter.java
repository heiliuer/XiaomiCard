package com.miui.tsmclient.ui.quick;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardUIInfo;
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
import java.util.List;

public class CardStackAdapter extends BaseAdapter
{
  private DisplayImageOptions mBankCardImageOptions;
  private Context mContext;
  private List<SwitchCardFragment.StackItem> mData;
  private ImageLoader mImageLoader;
  private ImageLoadingListener mImageLoaderListener = new SimpleImageLoadingListener()
  {
    public void onLoadingComplete(String paramString, View paramView, Bitmap paramBitmap)
    {
      paramView.findViewById(2131492908).setVisibility(8);
    }

    public void onLoadingFailed(String paramString, View paramView, FailReason paramFailReason)
    {
      paramView.setWillNotDraw(true);
      paramView.findViewById(2131492908).setVisibility(0);
      LogUtils.d("onloading card image failed, reason: " + paramFailReason.getType().toString());
    }
  };
  private ImageUtil.ThumbnailFormat mImageThumbnailFormat;
  private DisplayImageOptions mTransCardImageOptions;
  private ICardStackViewChangedListener mViewChangedListener;

  public CardStackAdapter(Context paramContext, List<SwitchCardFragment.StackItem> paramList)
  {
    this.mContext = paramContext;
    this.mData = paramList;
    this.mBankCardImageOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565).showImageOnLoading(2130837520).showImageOnFail(2130837520).build();
    this.mTransCardImageOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565).showImageOnLoading(2130837605).showImageOnFail(2130837605).build();
    this.mImageThumbnailFormat = ImageUtil.ThumbnailFormat.getMaxWidthHeightThumnail(this.mContext.getResources().getDimensionPixelSize(2131230874), this.mContext.getResources().getDimensionPixelSize(2131230875), 2);
    this.mImageLoader = ImageLoader.getInstance();
  }

  public void addData(int paramInt, SwitchCardFragment.StackItem paramStackItem)
  {
    addData(paramInt, paramStackItem, true);
  }

  public void addData(int paramInt, SwitchCardFragment.StackItem paramStackItem, boolean paramBoolean)
  {
    if ((this.mData != null) && (paramInt >= 0) && (paramInt <= this.mData.size()))
      this.mData.add(paramInt, paramStackItem);
    if (paramBoolean)
      notifyDataSetChanged();
  }

  public int getCount()
  {
    if (this.mData != null);
    for (int i = this.mData.size(); ; i = 0)
      return i;
  }

  public SwitchCardFragment.StackItem getItem(int paramInt)
  {
    SwitchCardFragment.StackItem localStackItem = null;
    if (this.mData == null);
    while (true)
    {
      return localStackItem;
      if ((paramInt < 0) || (paramInt >= this.mData.size()))
        continue;
      localStackItem = (SwitchCardFragment.StackItem)this.mData.get(paramInt);
    }
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView;
    if (paramInt < 0)
    {
      localView = null;
      return localView;
    }
    if (paramView == null)
      paramView = LayoutInflater.from(this.mContext).inflate(2130903066, paramViewGroup, false);
    paramView.setTag(2131492864, Integer.valueOf(paramInt));
    CardInfo localCardInfo = getItem(paramInt).getCardInfo();
    TextView localTextView = (TextView)paramView.findViewById(2131492885);
    ((TextView)paramView.findViewById(2131492908)).setText(localCardInfo.mCardName);
    if ((localCardInfo instanceof BankCardInfo))
    {
      BankCardInfo localBankCardInfo = (BankCardInfo)localCardInfo;
      if (paramInt == 0)
      {
        Context localContext = this.mContext;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = StringUtils.tail(localBankCardInfo.mPanLastDigits, 4);
        localTextView.setText(localContext.getString(2131296463, arrayOfObject));
        localTextView.setVisibility(0);
      }
      this.mImageLoader.displayImage(ImageUtil.getUrl(localBankCardInfo.mCardArt, this.mImageThumbnailFormat), new LayoutAware(this.mContext, paramView), this.mBankCardImageOptions, this.mImageLoaderListener);
    }
    while (true)
    {
      localView = paramView;
      break;
      this.mImageLoader.displayImage(localCardInfo.mCardUIInfo.mCardIssuedListBgHdUrl, new LayoutAware(this.mContext, paramView), this.mTransCardImageOptions, this.mImageLoaderListener);
    }
  }

  public void onCardsPoppedUp()
  {
    if (this.mViewChangedListener != null)
      this.mViewChangedListener.onCardsPoppedUp();
  }

  public void onDefaultCardChanged(int paramInt)
  {
    if (this.mViewChangedListener != null)
      this.mViewChangedListener.onDefaultCardChanged(paramInt);
  }

  public void onDefaultCardSelected()
  {
    if (this.mViewChangedListener != null)
      this.mViewChangedListener.onDefaultCardSelected();
  }

  public void removeData(int paramInt)
  {
    removeData(paramInt, true);
  }

  public void removeData(int paramInt, boolean paramBoolean)
  {
    if ((this.mData != null) && (!this.mData.isEmpty()))
      this.mData.remove(paramInt);
    if (paramBoolean)
      notifyDataSetChanged();
  }

  public void setData(List<SwitchCardFragment.StackItem> paramList)
  {
    this.mData = paramList;
    notifyDataSetChanged();
  }

  public void setDefaultCardChangedListener(ICardStackViewChangedListener paramICardStackViewChangedListener)
  {
    this.mViewChangedListener = paramICardStackViewChangedListener;
  }

  public static abstract interface ICardStackViewChangedListener
  {
    public abstract void onCardsPoppedUp();

    public abstract void onDefaultCardChanged(int paramInt);

    public abstract void onDefaultCardSelected();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.quick.CardStackAdapter
 * JD-Core Version:    0.6.0
 */