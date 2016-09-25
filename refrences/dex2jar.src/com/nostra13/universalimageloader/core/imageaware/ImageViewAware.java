package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.utils.L;
import java.lang.ref.Reference;
import java.lang.reflect.Field;

public class ImageViewAware extends ViewAware
{
  public ImageViewAware(ImageView paramImageView)
  {
    super(paramImageView);
  }

  public ImageViewAware(ImageView paramImageView, boolean paramBoolean)
  {
    super(paramImageView, paramBoolean);
  }

  private static int getImageViewFieldValue(Object paramObject, String paramString)
  {
    int i = 0;
    try
    {
      Field localField = ImageView.class.getDeclaredField(paramString);
      localField.setAccessible(true);
      int j = ((Integer)localField.get(paramObject)).intValue();
      if ((j > 0) && (j < 2147483647))
        i = j;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        L.e(localException);
    }
  }

  public int getHeight()
  {
    int i = super.getHeight();
    if (i <= 0)
    {
      ImageView localImageView = (ImageView)this.viewRef.get();
      if (localImageView != null)
        i = getImageViewFieldValue(localImageView, "mMaxHeight");
    }
    return i;
  }

  public ViewScaleType getScaleType()
  {
    ImageView localImageView = (ImageView)this.viewRef.get();
    if (localImageView != null);
    for (ViewScaleType localViewScaleType = ViewScaleType.fromImageView(localImageView); ; localViewScaleType = super.getScaleType())
      return localViewScaleType;
  }

  public int getWidth()
  {
    int i = super.getWidth();
    if (i <= 0)
    {
      ImageView localImageView = (ImageView)this.viewRef.get();
      if (localImageView != null)
        i = getImageViewFieldValue(localImageView, "mMaxWidth");
    }
    return i;
  }

  public ImageView getWrappedView()
  {
    return (ImageView)super.getWrappedView();
  }

  protected void setImageBitmapInto(Bitmap paramBitmap, View paramView)
  {
    ((ImageView)paramView).setImageBitmap(paramBitmap);
  }

  protected void setImageDrawableInto(Drawable paramDrawable, View paramView)
  {
    ((ImageView)paramView).setImageDrawable(paramDrawable);
    if ((paramDrawable instanceof AnimationDrawable))
      ((AnimationDrawable)paramDrawable).start();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.imageaware.ImageViewAware
 * JD-Core Version:    0.6.0
 */