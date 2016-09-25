package com.miui.tsmclient.ui.widget;

import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.Iterator;

public class SafeKeyboard
{
  public static final String INPUT_EXTRA_EXTENDED_INPUT_TYPE = "com.mipay.input_extra.extended_input_type";
  private static KeyboardVisibilityListener mKeyboardVisibilityListener;

  static void bindLayout(View paramView)
  {
    SafeKeyboardView localSafeKeyboardView = findKeyboardView(paramView);
    if (localSafeKeyboardView == null)
    {
      initLayout(paramView.getRootView());
      localSafeKeyboardView = findKeyboardView(paramView);
    }
    localSafeKeyboardView.attachEditView(paramView);
  }

  private static SafeKeyboardView findKeyboardView(View paramView)
  {
    return (SafeKeyboardView)paramView.getRootView().findViewById(2131493030);
  }

  private static int getSoftInputMode(Activity paramActivity)
  {
    return paramActivity.getWindow().getAttributes().softInputMode;
  }

  public static void hide(View paramView)
  {
    hideKeyboard(paramView);
  }

  private static void hideKeyboard(View paramView)
  {
    SafeKeyboardView localSafeKeyboardView = findKeyboardView(paramView);
    if ((localSafeKeyboardView == null) || (localSafeKeyboardView.getVisibility() != 0));
    while (true)
    {
      return;
      localSafeKeyboardView.hide();
    }
  }

  private static void initLayout(View paramView)
  {
    Context localContext = paramView.getContext();
    new KeyboardContainer(localContext, getSoftInputMode((Activity)localContext)).attach(paramView);
  }

  public static boolean isKeyboardVisible(View paramView)
  {
    View localView = paramView.getRootView().findViewById(2131493030);
    if ((localView != null) && (localView.getVisibility() == 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static void onKeyboardVisibilityChanged(boolean paramBoolean)
  {
    if (mKeyboardVisibilityListener != null)
      mKeyboardVisibilityListener.onKeyboardVisibilityChanged(paramBoolean);
  }

  private static void overrideSoftInputMode(View paramView)
  {
    ((Activity)((ContextThemeWrapper)paramView.getContext()).getBaseContext()).getWindow().setSoftInputMode(3);
  }

  public static void register(View paramView)
  {
    paramView.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramView, boolean paramBoolean)
      {
        if (paramBoolean)
        {
          SafeKeyboard.bindLayout(paramView);
          SafeKeyboard.SafeKeyboardInfo localSafeKeyboardInfo = SafeKeyboard.access$200(paramView);
          SafeKeyboard.access$300(paramView);
          if (SafeKeyboard.access$400(localSafeKeyboardInfo))
            SafeKeyboard.access$500(paramView);
        }
        while (true)
        {
          return;
          SafeKeyboard.access$600(paramView);
          SafeKeyboard.access$700(paramView);
        }
      }
    });
    paramView.setOnKeyListener(new View.OnKeyListener()
    {
      public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
      {
        if ((paramKeyEvent.getKeyCode() == 4) && (paramKeyEvent.getAction() == 0))
        {
          SafeKeyboardView localSafeKeyboardView = SafeKeyboard.access$800(paramView);
          if ((localSafeKeyboardView != null) && (localSafeKeyboardView.isHideEnabled()) && (SafeKeyboard.isKeyboardVisible(paramView)))
            SafeKeyboard.access$700(paramView);
        }
        for (int i = 1; ; i = 0)
          return i;
      }
    });
    paramView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        if (paramMotionEvent.getAction() == 1)
        {
          SafeKeyboard.bindLayout(paramView);
          SafeKeyboard.access$500(paramView);
        }
        return false;
      }
    });
    paramView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener()
    {
      public void onViewAttachedToWindow(View paramView)
      {
        View localView = paramView.getRootView().findFocus();
        SafeKeyboard.bindLayout(paramView);
        if (localView != null)
          localView.requestFocus();
      }

      public void onViewDetachedFromWindow(View paramView)
      {
        SafeKeyboardView localSafeKeyboardView = SafeKeyboard.access$800(paramView);
        if (localSafeKeyboardView != null)
          localSafeKeyboardView.detachEditView();
      }
    });
  }

  public static void removeKeyboardVisibilityListener()
  {
    mKeyboardVisibilityListener = null;
  }

  private static void requestShowKeyboard(View paramView)
  {
    SafeKeyboardView localSafeKeyboardView = findKeyboardView(paramView);
    if ((localSafeKeyboardView.isEditing(paramView)) && (localSafeKeyboardView.isShown()));
    while (true)
    {
      return;
      localSafeKeyboardView.requestShow(paramView);
    }
  }

  private static void restoreSoftInputMode(View paramView)
  {
    SafeKeyboardView localSafeKeyboardView = findKeyboardView(paramView);
    if (localSafeKeyboardView == null);
    while (true)
    {
      return;
      SafeKeyboardInfo localSafeKeyboardInfo = (SafeKeyboardInfo)localSafeKeyboardView.getTag();
      if (localSafeKeyboardInfo == null)
        continue;
      ((Activity)((ContextThemeWrapper)paramView.getContext()).getBaseContext()).getWindow().setSoftInputMode(localSafeKeyboardInfo.mOriginSoftInputMode);
      localSafeKeyboardView.setTag(null);
    }
  }

  private static SafeKeyboardInfo saveKeyboardInfo(View paramView)
  {
    SafeKeyboardView localSafeKeyboardView = findKeyboardView(paramView);
    SafeKeyboardInfo localSafeKeyboardInfo = (SafeKeyboardInfo)localSafeKeyboardView.getTag();
    if (localSafeKeyboardInfo == null)
    {
      localSafeKeyboardInfo = new SafeKeyboardInfo(null);
      SafeKeyboardInfo.access$102(localSafeKeyboardInfo, ((Activity)localSafeKeyboardView.getContext()).getWindow().getAttributes().softInputMode);
      localSafeKeyboardView.setTag(localSafeKeyboardInfo);
    }
    return localSafeKeyboardInfo;
  }

  public static void setKeyboardVisibilityListener(KeyboardVisibilityListener paramKeyboardVisibilityListener)
  {
    mKeyboardVisibilityListener = paramKeyboardVisibilityListener;
  }

  private static boolean showKeyboardWhenFocused(SafeKeyboardInfo paramSafeKeyboardInfo)
  {
    int i = 0xF & paramSafeKeyboardInfo.mOriginSoftInputMode;
    if ((i != 3) && (i != 2));
    for (int j = 1; ; j = 0)
      return j;
  }

  public static abstract interface KeyboardVisibilityListener
  {
    public abstract void onKeyboardVisibilityChanged(boolean paramBoolean);
  }

  private static class KeyboardContainer
  {
    private ViewGroup mContainer;
    private ViewGroup.MarginLayoutParams mContentLayoutParams;
    private ViewGroup.MarginLayoutParams mKeyboardLayoutParams;

    public KeyboardContainer(Context paramContext, int paramInt)
    {
      if ((paramInt & 0xF0) == 48)
      {
        this.mContainer = new FrameLayout(paramContext);
        this.mKeyboardLayoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
      }
      for (this.mContentLayoutParams = new FrameLayout.LayoutParams(-1, -2); ; this.mContentLayoutParams = new LinearLayout.LayoutParams(-1, -2, 1.0F))
      {
        return;
        this.mContainer = new LinearLayout(paramContext);
        ((LinearLayout)this.mContainer).setOrientation(1);
        this.mKeyboardLayoutParams = new LinearLayout.LayoutParams(-1, -2);
      }
    }

    private void addContentView(View paramView)
    {
      ViewGroup localViewGroup = (ViewGroup)paramView.findViewById(16908290);
      ArrayList localArrayList = new ArrayList();
      for (int i = 0; i < localViewGroup.getChildCount(); i++)
        localArrayList.add(localViewGroup.getChildAt(i));
      localViewGroup.removeAllViews();
      localViewGroup.setId(-1);
      ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localViewGroup.getLayoutParams();
      localMarginLayoutParams.width = -1;
      localMarginLayoutParams.height = -1;
      int j = localMarginLayoutParams.leftMargin;
      int k = localMarginLayoutParams.rightMargin;
      int m = localMarginLayoutParams.topMargin;
      int n = localMarginLayoutParams.bottomMargin;
      localMarginLayoutParams.setMargins(0, 0, 0, 0);
      this.mContentLayoutParams.setMargins(j, m, k, n);
      localViewGroup.addView(this.mContainer);
      int i1 = localViewGroup.getPaddingLeft();
      int i2 = localViewGroup.getPaddingRight();
      int i3 = localViewGroup.getPaddingTop();
      int i4 = localViewGroup.getPaddingBottom();
      localViewGroup.setPadding(0, 0, 0, 0);
      Drawable localDrawable = localViewGroup.getBackground();
      localViewGroup.setBackground(new ColorDrawable(0));
      FrameLayout localFrameLayout = new FrameLayout(paramView.getContext());
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
        localFrameLayout.addView((View)localIterator.next());
      localFrameLayout.setId(16908290);
      localFrameLayout.setPadding(i1, i3, i2, i4);
      localFrameLayout.setBackground(localDrawable);
      this.mContainer.addView(localFrameLayout, 0, this.mContentLayoutParams);
    }

    private void addKeyboardView(View paramView)
    {
      SafeKeyboardView localSafeKeyboardView = (SafeKeyboardView)LayoutInflater.from(paramView.getContext()).inflate(2130903073, null);
      this.mContainer.addView(localSafeKeyboardView, this.mKeyboardLayoutParams);
    }

    private LayoutTransition createLayoutTransition(Context paramContext)
    {
      LayoutTransition localLayoutTransition = new LayoutTransition();
      localLayoutTransition.setAnimator(0, null);
      localLayoutTransition.setAnimator(1, null);
      localLayoutTransition.setAnimator(2, (ObjectAnimator)AnimatorInflater.loadAnimator(paramContext, 2131034116));
      localLayoutTransition.setStartDelay(2, 0L);
      localLayoutTransition.setAnimator(3, (ObjectAnimator)AnimatorInflater.loadAnimator(paramContext, 2131034117));
      localLayoutTransition.setStartDelay(3, 0L);
      return localLayoutTransition;
    }

    public void attach(View paramView)
    {
      addKeyboardView(paramView);
      addContentView(paramView);
      this.mContainer.setLayoutTransition(createLayoutTransition(paramView.getContext()));
    }
  }

  private static class SafeKeyboardInfo
  {
    private int mOriginSoftInputMode;
  }

  public static abstract interface Extended_Input_Type
  {
    public static final int DENOMINATION = 2;
    public static final int IDENTITY = 1;
    public static final int NONE;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.SafeKeyboard
 * JD-Core Version:    0.6.0
 */