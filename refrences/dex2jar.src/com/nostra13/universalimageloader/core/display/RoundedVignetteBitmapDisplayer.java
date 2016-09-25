package com.nostra13.universalimageloader.core.display;

import android.graphics.Bitmap;
import android.graphics.ComposeShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

public class RoundedVignetteBitmapDisplayer extends RoundedBitmapDisplayer
{
  public RoundedVignetteBitmapDisplayer(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }

  public void display(Bitmap paramBitmap, ImageAware paramImageAware, LoadedFrom paramLoadedFrom)
  {
    if (!(paramImageAware instanceof ImageViewAware))
      throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
    paramImageAware.setImageDrawable(new RoundedVignetteDrawable(paramBitmap, this.cornerRadius, this.margin));
  }

  protected static class RoundedVignetteDrawable extends RoundedBitmapDisplayer.RoundedDrawable
  {
    RoundedVignetteDrawable(Bitmap paramBitmap, int paramInt1, int paramInt2)
    {
      super(paramInt1, paramInt2);
    }

    protected void onBoundsChange(Rect paramRect)
    {
      super.onBoundsChange(paramRect);
      float f1 = this.mRect.centerX();
      float f2 = 1.0F * this.mRect.centerY() / 0.7F;
      float f3 = 1.3F * this.mRect.centerX();
      int[] arrayOfInt = new int[3];
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 0;
      arrayOfInt[2] = 2130706432;
      float[] arrayOfFloat = new float[3];
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 0.7F;
      arrayOfFloat[2] = 1.0F;
      RadialGradient localRadialGradient = new RadialGradient(f1, f2, f3, arrayOfInt, arrayOfFloat, Shader.TileMode.CLAMP);
      Matrix localMatrix = new Matrix();
      localMatrix.setScale(1.0F, 0.7F);
      localRadialGradient.setLocalMatrix(localMatrix);
      this.paint.setShader(new ComposeShader(this.bitmapShader, localRadialGradient, PorterDuff.Mode.SRC_OVER));
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer
 * JD-Core Version:    0.6.0
 */