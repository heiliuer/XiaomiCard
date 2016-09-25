package com.miui.tsmclient.ui.widget;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class KeyboardObserver
{
  private Activity mActivity;
  private ViewTreeObserver.OnGlobalLayoutListener mLayoutListener;

  public KeyboardObserver(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }

  public final void removeKeyboardListener()
  {
    if (this.mActivity == null);
    while (true)
    {
      return;
      if (this.mLayoutListener != null)
      {
        ((ViewGroup)this.mActivity.findViewById(16908290)).getViewTreeObserver().removeOnGlobalLayoutListener(this.mLayoutListener);
        this.mLayoutListener = null;
        continue;
      }
    }
  }

  public final void setKeyboardListener(KeyboardVisibilityListener paramKeyboardVisibilityListener)
  {
    if (this.mActivity == null);
    while (true)
    {
      return;
      View localView = this.mActivity.findViewById(16908290);
      if (this.mLayoutListener != null)
        removeKeyboardListener();
      this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener(localView, paramKeyboardVisibilityListener)
      {
        private final Rect r = new Rect();
        private boolean wasOpened;

        public void onGlobalLayout()
        {
          this.val$activityRootView.getWindowVisibleDisplayFrame(this.r);
          boolean bool;
          if (this.val$activityRootView.getRootView().getHeight() - this.r.height() > 100)
          {
            bool = true;
            if (bool != this.wasOpened)
              break label50;
          }
          while (true)
          {
            return;
            bool = false;
            break;
            label50: this.wasOpened = bool;
            if (this.val$listener == null)
              continue;
            this.val$listener.onKeyboardVisibilityChanged(bool);
          }
        }
      };
      localView.getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
    }
  }

  public static abstract interface KeyboardVisibilityListener
  {
    public abstract void onKeyboardVisibilityChanged(boolean paramBoolean);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.KeyboardObserver
 * JD-Core Version:    0.6.0
 */