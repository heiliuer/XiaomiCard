package com.miui.tsmclient.ui;

import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.os.Bundle;
import miui.R.id;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.view.ViewPager;
import miui.view.ViewPager.OnPageChangeListener;

public abstract class BaseTabActivity extends Activity
{
  protected ActionBar mActionBar;
  private int mCurrentPagePos;
  private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener()
  {
    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      BaseTabActivity.access$002(BaseTabActivity.this, paramInt1);
    }

    public void onPageSelected(int paramInt)
    {
      BaseTabActivity.access$002(BaseTabActivity.this, paramInt);
    }
  };
  protected ViewPager mViewPager;

  private void initActionBar()
  {
    this.mActionBar = getActionBar();
    if (this.mActionBar != null)
      this.mActionBar.setFragmentViewPagerMode(this, getFragmentManager());
  }

  private void initFragmentTabs()
  {
    getFragmentManager();
    int i = 0;
    if (i < getTabCounts())
    {
      FragmentInfo localFragmentInfo = getFragment(i);
      ActionBar.Tab localTab1;
      if (this.mActionBar != null)
      {
        localTab1 = this.mActionBar.newTab();
        if (getTabCounts() != 1)
          break label93;
      }
      label93: for (String str = ""; ; str = getString(getTabText(i)))
      {
        ActionBar.Tab localTab2 = localTab1.setText(str);
        this.mActionBar.addFragmentTab(localFragmentInfo.clz.getName(), localTab2, localFragmentInfo.clz, localFragmentInfo.args, localFragmentInfo.hasActionMenu);
        i++;
        break;
      }
    }
  }

  protected Fragment getCurrentFragment()
  {
    return this.mActionBar.getFragmentAt(this.mCurrentPagePos);
  }

  protected abstract FragmentInfo getFragment(int paramInt);

  protected abstract int getTabCounts();

  protected abstract int getTabText(int paramInt);

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903081);
    initActionBar();
    this.mViewPager = ((ViewPager)findViewById(R.id.view_pager));
    this.mViewPager.setOnPageChangeListener(this.mOnPageChangeListener);
    initFragmentTabs();
  }

  public static class FragmentInfo
  {
    public Bundle args;
    public Class<? extends Fragment> clz;
    public boolean hasActionMenu;

    public FragmentInfo(Class<? extends Fragment> paramClass, Bundle paramBundle, boolean paramBoolean)
    {
      this.clz = paramClass;
      this.args = paramBundle;
      this.hasActionMenu = paramBoolean;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.BaseTabActivity
 * JD-Core Version:    0.6.0
 */