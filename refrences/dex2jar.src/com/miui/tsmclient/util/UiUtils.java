package com.miui.tsmclient.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import miui.app.ProgressDialog;

public class UiUtils
{
  private static final String APP_URL_IN_MARKET = "market://details?id=%s";
  private static final String PACKAGE_NAME_XIAOMI_MARKET = "com.xiaomi.market";

  public static void clearFragmentBackStack(Activity paramActivity)
  {
    if (paramActivity == null)
      LogUtils.w("clearFragmentBackStack() called!But param activity is null!");
    while (true)
    {
      return;
      FragmentManager localFragmentManager = paramActivity.getFragmentManager();
      if (localFragmentManager.getBackStackEntryCount() <= 0)
        continue;
      localFragmentManager.popBackStack(localFragmentManager.getBackStackEntryAt(0).getId(), 1);
    }
  }

  public static void dismissProgressDialog(ProgressDialog paramProgressDialog)
  {
    if ((paramProgressDialog != null) && (paramProgressDialog.isShowing()))
      paramProgressDialog.dismiss();
  }

  public static int getStatusBarHeight(Context paramContext)
  {
    int i = 0;
    int j = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (j > 0)
      i = paramContext.getResources().getDimensionPixelSize(j);
    return i;
  }

  public static boolean isFragmentValid(Fragment paramFragment)
  {
    if ((paramFragment != null) && (paramFragment.getActivity() != null) && (!paramFragment.getActivity().isFinishing()) && (!paramFragment.isRemoving()) && (!paramFragment.isDetached()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static void replaceFragment(Activity paramActivity, Fragment paramFragment)
  {
    replaceFragment(paramActivity, paramFragment, false, false, true);
  }

  public static void replaceFragment(Activity paramActivity, Fragment paramFragment, boolean paramBoolean)
  {
    replaceFragment(paramActivity, paramFragment, false, false, paramBoolean);
  }

  public static void replaceFragment(Activity paramActivity, Fragment paramFragment, boolean paramBoolean1, boolean paramBoolean2)
  {
    replaceFragment(paramActivity, paramFragment, paramBoolean1, paramBoolean2, true);
  }

  public static void replaceFragment(Activity paramActivity, Fragment paramFragment, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      FragmentManager localFragmentManager = paramActivity.getFragmentManager();
      if (paramBoolean1)
        localFragmentManager.popBackStack();
      FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
      if (paramBoolean3)
        localFragmentTransaction.setCustomAnimations(2131034112, 2131034113, 2131034114, 2131034115);
      localFragmentTransaction.replace(16908290, paramFragment, paramFragment.getClass().getName());
      if (paramBoolean2)
        localFragmentTransaction.addToBackStack(null);
      localFragmentTransaction.commitAllowingStateLoss();
    }
  }

  public static void showProgressDialog(Context paramContext, ProgressDialog paramProgressDialog, int paramInt)
  {
    showProgressDialog(paramProgressDialog, paramContext.getString(paramInt));
  }

  public static void showProgressDialog(ProgressDialog paramProgressDialog, String paramString)
  {
    if (paramProgressDialog == null);
    while (true)
    {
      return;
      paramProgressDialog.setMessage(paramString);
      if (paramProgressDialog.isShowing())
        continue;
      paramProgressDialog.show();
    }
  }

  public static void showSoftInputMethod(Context paramContext, View paramView, boolean paramBoolean)
  {
    if (paramView == null);
    while (true)
    {
      return;
      InputMethodManager localInputMethodManager = (InputMethodManager)paramContext.getSystemService("input_method");
      if (paramBoolean)
      {
        localInputMethodManager.showSoftInput(paramView, 1);
        continue;
      }
      localInputMethodManager.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
  }

  public static void showToast(Context paramContext, int paramInt)
  {
    showToast(paramContext, paramContext.getString(paramInt));
  }

  public static void showToast(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 1).show();
  }

  public static void startXiaomiMarket(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramString;
    localIntent.setData(Uri.parse(String.format("market://details?id=%s", arrayOfObject)));
    localIntent.setPackage("com.xiaomi.market");
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      while (true)
        LogUtils.e("cannot find plugin in market,packageName:" + paramString, localActivityNotFoundException);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.UiUtils
 * JD-Core Version:    0.6.0
 */