package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;
import miui.view.PagerAdapter;
import miui.view.ViewPager;
import miui.view.ViewPager.OnPageChangeListener;

public class ImagePagerAdapter extends PagerAdapter
{
  private Context mContext;
  private Handler mHandler;
  private SparseArray<ImageView> mImageViewCache = new SparseArray();
  private ViewPager.OnPageChangeListener mInnerPageChangeListener = new ViewPager.OnPageChangeListener()
  {
    public void onPageScrollStateChanged(int paramInt)
    {
      if (ImagePagerAdapter.this.mPageChangeListener != null)
        ImagePagerAdapter.this.mPageChangeListener.onPageScrollStateChanged(paramInt);
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      if (ImagePagerAdapter.this.mPageChangeListener != null)
        ImagePagerAdapter.this.mPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }

    public void onPageSelected(int paramInt)
    {
      int i = paramInt;
      if (ImagePagerAdapter.this.getCount() > 1)
      {
        if (paramInt != 0)
          break label92;
        i = -2 + ImagePagerAdapter.this.getCount();
      }
      while (true)
      {
        if (paramInt != i)
        {
          int j = i;
          ImagePagerAdapter.this.mHandler.postDelayed(new Runnable(j)
          {
            public void run()
            {
              ImagePagerAdapter.this.mViewPager.setCurrentItem(this.val$i, false);
            }
          }
          , 500L);
        }
        if (ImagePagerAdapter.this.mPageChangeListener != null)
          ImagePagerAdapter.this.mPageChangeListener.onPageSelected((i - 1) % ImagePagerAdapter.this.getItemCount());
        return;
        label92: if (paramInt != -1 + ImagePagerAdapter.this.getCount())
          continue;
        i = 1;
      }
    }
  };
  private List<String> mItemInfoList;
  private ViewPager.OnPageChangeListener mPageChangeListener;
  private ViewPager mViewPager;

  public ImagePagerAdapter(Context paramContext)
  {
    this.mContext = paramContext;
    this.mHandler = new Handler();
  }

  private int getItemIndex(int paramInt)
  {
    int i = getItemCount();
    if (i < 2);
    while (true)
    {
      return paramInt;
      if (paramInt == 0)
      {
        paramInt = i - 1;
        continue;
      }
      if (paramInt == -1 + getCount())
      {
        paramInt = 0;
        continue;
      }
      paramInt--;
    }
  }

  private ImageView getViewPagerItem(int paramInt)
  {
    ImageView localImageView1 = (ImageView)this.mImageViewCache.get(paramInt);
    int i = getItemIndex(paramInt);
    if (localImageView1 == null)
    {
      localImageView1 = new ImageView(this.mContext);
      ImageView localImageView2 = localImageView1;
      String str = (String)this.mItemInfoList.get(i);
      ImageLoader.getInstance().displayImage(str, localImageView2);
      this.mImageViewCache.put(paramInt, localImageView2);
    }
    return localImageView1;
  }

  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    paramViewGroup.removeView((View)paramObject);
  }

  public int getCount()
  {
    int i;
    if (this.mItemInfoList == null)
      i = 0;
    while (true)
    {
      return i;
      if (this.mItemInfoList.size() > 1)
      {
        i = 2 + this.mItemInfoList.size();
        continue;
      }
      i = this.mItemInfoList.size();
    }
  }

  public int getItemCount()
  {
    if (this.mItemInfoList == null);
    for (int i = 0; ; i = this.mItemInfoList.size())
      return i;
  }

  public boolean hasActionMenu(int paramInt)
  {
    return false;
  }

  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    ImageView localImageView = getViewPagerItem(paramInt);
    paramViewGroup.addView(localImageView, new ViewGroup.LayoutParams(-1, -1));
    return localImageView;
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    if (paramView == paramObject);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void release()
  {
    for (int i = 0; i < this.mImageViewCache.size(); i++)
    {
      ImageView localImageView = (ImageView)this.mImageViewCache.get(i);
      if ((localImageView == null) || (localImageView.getParent() == null))
        continue;
      ((ViewGroup)localImageView.getParent()).removeView(localImageView);
    }
    this.mImageViewCache.clear();
  }

  public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener)
  {
    this.mPageChangeListener = paramOnPageChangeListener;
  }

  public void setViewPager(ViewPager paramViewPager)
  {
    this.mViewPager = paramViewPager;
    this.mViewPager.setOnPageChangeListener(this.mInnerPageChangeListener);
    this.mViewPager.setAdapter(this);
    this.mViewPager.setCurrentItem(1);
  }

  public void updateData(List<String> paramList)
  {
    this.mItemInfoList = paramList;
    notifyDataSetChanged();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.ImagePagerAdapter
 * JD-Core Version:    0.6.0
 */