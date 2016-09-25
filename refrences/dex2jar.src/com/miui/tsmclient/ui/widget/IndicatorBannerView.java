package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import java.util.List;
import miui.view.ViewPager;
import miui.view.ViewPager.OnPageChangeListener;

public class IndicatorBannerView extends FrameLayout
{
  private static final int DEFAULT_SCROLL_INTERVAL = 5000;
  private Handler mHandler;
  private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener()
  {
    public void onPageScrollStateChanged(int paramInt)
    {
      if (paramInt == 1)
        IndicatorBannerView.this.stopAutoScrollViewPager();
      while (true)
      {
        return;
        if (paramInt == 0)
        {
          IndicatorBannerView.this.startAutoScrollViewPager();
          continue;
        }
      }
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
      IndicatorBannerView.this.mViewPagerIndicatorBar.setSelected(paramInt);
    }
  };
  private ImagePagerAdapter mPagerAdapter;
  private int mScrollInterval;
  private Runnable mScrollToNextPageRunnable = new Runnable()
  {
    public void run()
    {
      int i = IndicatorBannerView.this.mViewPager.getCurrentItem();
      int j = IndicatorBannerView.this.mPagerAdapter.getCount();
      int k = (i + 1) % j;
      IndicatorBannerView.this.mViewPager.setCurrentItem(k);
    }
  };
  private ViewPager mViewPager;
  private ViewPagerIndicatorBar mViewPagerIndicatorBar;

  public IndicatorBannerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  private void init()
  {
    setScrollInterval(5000);
    this.mHandler = new Handler();
  }

  private void startAutoScrollViewPager()
  {
    if (this.mPagerAdapter.getCount() > 1)
      this.mHandler.postDelayed(this.mScrollToNextPageRunnable, this.mScrollInterval);
  }

  private void stopAutoScrollViewPager()
  {
    if (this.mPagerAdapter.getCount() > 1)
      this.mHandler.removeCallbacks(this.mScrollToNextPageRunnable);
  }

  protected void onDetachedFromWindow()
  {
    if (this.mPagerAdapter != null)
      this.mPagerAdapter.release();
    super.onDetachedFromWindow();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mViewPager = ((ViewPager)findViewById(2131492939));
    this.mViewPagerIndicatorBar = ((ViewPagerIndicatorBar)findViewById(2131492940));
  }

  public void setScrollInterval(int paramInt)
  {
    this.mScrollInterval = paramInt;
  }

  public void updateData(List<String> paramList)
  {
    if (this.mPagerAdapter == null)
      this.mPagerAdapter = new ImagePagerAdapter(getContext());
    ViewPagerIndicatorBar localViewPagerIndicatorBar = this.mViewPagerIndicatorBar;
    if (paramList == null);
    for (int i = 0; ; i = paramList.size())
    {
      localViewPagerIndicatorBar.setIndicatorNum(i);
      this.mPagerAdapter.setOnPageChangeListener(this.mOnPageChangeListener);
      this.mPagerAdapter.updateData(paramList);
      this.mPagerAdapter.setViewPager(this.mViewPager);
      stopAutoScrollViewPager();
      startAutoScrollViewPager();
      return;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.IndicatorBannerView
 * JD-Core Version:    0.6.0
 */