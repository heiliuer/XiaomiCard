package com.nostra13.universalimageloader.core.display;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

public class CircleBitmapDisplayer
  implements BitmapDisplayer
{
  protected final Integer strokeColor;
  protected final float strokeWidth;

  public CircleBitmapDisplayer()
  {
    this(null);
  }

  public CircleBitmapDisplayer(Integer paramInteger)
  {
    this(paramInteger, 0.0F);
  }

  public CircleBitmapDisplayer(Integer paramInteger, float paramFloat)
  {
    this.strokeColor = paramInteger;
    this.strokeWidth = paramFloat;
  }

  public void display(Bitmap paramBitmap, ImageAware paramImageAware, LoadedFrom paramLoadedFrom)
  {
    if (!(paramImageAware instanceof ImageViewAware))
      throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
    paramImageAware.setImageDrawable(new CircleDrawable(paramBitmap, this.strokeColor, this.strokeWidth));
  }

  public static class CircleDrawable extends Drawable
  {
    protected final BitmapShader bitmapShader;
    protected final RectF mBitmapRect;
    protected final RectF mRect = new RectF();
    protected final Paint paint;
    protected float radius;
    protected final Paint strokePaint;
    protected float strokeRadius;
    protected final float strokeWidth;

    public CircleDrawable(Bitmap paramBitmap, Integer paramInteger, float paramFloat)
    {
      this.radius = (Math.min(paramBitmap.getWidth(), paramBitmap.getHeight()) / 2);
      this.bitmapShader = new BitmapShader(paramBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
      this.mBitmapRect = new RectF(0.0F, 0.0F, paramBitmap.getWidth(), paramBitmap.getHeight());
      this.paint = new Paint();
      this.paint.setAntiAlias(true);
      this.paint.setShader(this.bitmapShader);
      this.paint.setFilterBitmap(true);
      this.paint.setDither(true);
      if (paramInteger == null)
        this.strokePaint = null;
      while (true)
      {
        this.strokeWidth = paramFloat;
        this.strokeRadius = (this.radius - paramFloat / 2.0F);
        return;
        this.strokePaint = new Paint();
        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.strokePaint.setColor(paramInteger.intValue());
        this.strokePaint.setStrokeWidth(paramFloat);
        this.strokePaint.setAntiAlias(true);
      }
    }

    public void draw(Canvas paramCanvas)
    {
      paramCanvas.drawCircle(this.radius, this.radius, this.radius, this.paint);
      if (this.strokePaint != null)
        paramCanvas.drawCircle(this.radius, this.radius, this.strokeRadius, this.strokePaint);
    }

    public int getOpacity()
    {
      return -3;
    }

    protected void onBoundsChange(Rect paramRect)
    {
      super.onBoundsChange(paramRect);
      this.mRect.set(0.0F, 0.0F, paramRect.width(), paramRect.height());
      this.radius = (Math.min(paramRect.width(), paramRect.height()) / 2);
      this.strokeRadius = (this.radius - this.strokeWidth / 2.0F);
      Matrix localMatrix = new Matrix();
      localMatrix.setRectToRect(this.mBitmapRect, this.mRect, Matrix.ScaleToFit.FILL);
      this.bitmapShader.setLocalMatrix(localMatrix);
    }

    public void setAlpha(int paramInt)
    {
      this.paint.setAlpha(paramInt);
    }

    public void setColorFilter(ColorFilter paramColorFilter)
    {
      this.paint.setColorFilter(paramColorFilter);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer
 * JD-Core Version:    0.6.0
 */