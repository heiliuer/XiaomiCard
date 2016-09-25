package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.nostra13.universalimageloader.core.imageaware.ViewAware;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class LayoutAware extends ViewAware
{
  protected Reference<Context> mContextRef;

  public LayoutAware(Context paramContext, View paramView)
  {
    super(paramView);
    this.mContextRef = new WeakReference(paramContext);
  }

  protected void setImageBitmapInto(Bitmap paramBitmap, View paramView)
  {
    Context localContext = (Context)this.mContextRef.get();
    if (localContext != null)
      paramView.setBackground(new BitmapDrawable(localContext.getResources(), paramBitmap));
  }

  protected void setImageDrawableInto(Drawable paramDrawable, View paramView)
  {
    paramView.setBackground(paramDrawable);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.LayoutAware
 * JD-Core Version:    0.6.0
 */