package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.utils.L;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class ViewAware
  implements ImageAware
{
  public static final String WARN_CANT_SET_BITMAP = "Can't set a bitmap into view. You should call ImageLoader on UI thread for it.";
  public static final String WARN_CANT_SET_DRAWABLE = "Can't set a drawable into view. You should call ImageLoader on UI thread for it.";
  protected boolean checkActualViewSize;
  protected Reference<View> viewRef;

  public ViewAware(View paramView)
  {
    this(paramView, true);
  }

  public ViewAware(View paramView, boolean paramBoolean)
  {
    if (paramView == null)
      throw new IllegalArgumentException("view must not be null");
    this.viewRef = new WeakReference(paramView);
    this.checkActualViewSize = paramBoolean;
  }

  public int getHeight()
  {
    View localView = (View)this.viewRef.get();
    ViewGroup.LayoutParams localLayoutParams;
    if (localView != null)
    {
      localLayoutParams = localView.getLayoutParams();
      i = 0;
      if ((this.checkActualViewSize) && (localLayoutParams != null) && (localLayoutParams.height != -2))
        i = localView.getHeight();
      if ((i > 0) || (localLayoutParams == null));
    }
    for (int i = localLayoutParams.height; ; i = 0)
      return i;
  }

  public int getId()
  {
    View localView = (View)this.viewRef.get();
    if (localView == null);
    for (int i = super.hashCode(); ; i = localView.hashCode())
      return i;
  }

  public ViewScaleType getScaleType()
  {
    return ViewScaleType.CROP;
  }

  public int getWidth()
  {
    View localView = (View)this.viewRef.get();
    ViewGroup.LayoutParams localLayoutParams;
    if (localView != null)
    {
      localLayoutParams = localView.getLayoutParams();
      i = 0;
      if ((this.checkActualViewSize) && (localLayoutParams != null) && (localLayoutParams.width != -2))
        i = localView.getWidth();
      if ((i > 0) || (localLayoutParams == null));
    }
    for (int i = localLayoutParams.width; ; i = 0)
      return i;
  }

  public View getWrappedView()
  {
    return (View)this.viewRef.get();
  }

  public boolean isCollected()
  {
    if (this.viewRef.get() == null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean setImageBitmap(Bitmap paramBitmap)
  {
    int i = 0;
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      View localView = (View)this.viewRef.get();
      if (localView != null)
      {
        setImageBitmapInto(paramBitmap, localView);
        i = 1;
      }
    }
    while (true)
    {
      return i;
      L.w("Can't set a bitmap into view. You should call ImageLoader on UI thread for it.", new Object[0]);
    }
  }

  protected abstract void setImageBitmapInto(Bitmap paramBitmap, View paramView);

  public boolean setImageDrawable(Drawable paramDrawable)
  {
    int i = 0;
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      View localView = (View)this.viewRef.get();
      if (localView != null)
      {
        setImageDrawableInto(paramDrawable, localView);
        i = 1;
      }
    }
    while (true)
    {
      return i;
      L.w("Can't set a drawable into view. You should call ImageLoader on UI thread for it.", new Object[0]);
    }
  }

  protected abstract void setImageDrawableInto(Drawable paramDrawable, View paramView);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.imageaware.ViewAware
 * JD-Core Version:    0.6.0
 */