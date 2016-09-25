package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class FlashView extends RelativeLayout
{
  private boolean mAnimating;
  private Matrix mGradientMatrix = new Matrix();
  private LinearGradient mLinearGradient;
  private Paint mPaint = new Paint();
  private int mTop = 0;
  private int mTranslateX = 0;
  private int mTranslateY = 0;

  public FlashView(Context paramContext)
  {
    this(paramContext, null);
  }

  public FlashView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public FlashView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }

  public FlashView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
  }

  public void flash()
  {
    this.mAnimating = true;
    invalidate();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.mAnimating)
    {
      this.mTranslateX += getWidth() / 10;
      this.mTranslateY += getHeight() / 10;
      if (this.mTranslateX <= getWidth())
        break label61;
      this.mAnimating = false;
    }
    while (true)
    {
      return;
      label61: this.mGradientMatrix.setTranslate(this.mTranslateX, this.mTranslateY);
      this.mLinearGradient.setLocalMatrix(this.mGradientMatrix);
      paramCanvas.drawRect(0.0F, 0.0F, getWidth(), getHeight(), this.mPaint);
      postInvalidateDelayed(20L);
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mTop == 0)
    {
      this.mTop = getTop();
      this.mTranslateX = (-(getWidth() / 3));
      this.mTranslateY = (-(getHeight() / 3));
    }
    if (this.mLinearGradient == null)
    {
      float f1 = getWidth();
      float f2 = getHeight();
      int[] arrayOfInt = new int[5];
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 234881023;
      arrayOfInt[2] = 452984831;
      arrayOfInt[3] = 234881023;
      arrayOfInt[4] = 0;
      float[] arrayOfFloat = new float[5];
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 0.125F;
      arrayOfFloat[2] = 0.25F;
      arrayOfFloat[3] = 0.375F;
      arrayOfFloat[4] = 0.5F;
      this.mLinearGradient = new LinearGradient(0.0F, 0.0F, f1, f2, arrayOfInt, arrayOfFloat, Shader.TileMode.CLAMP);
      this.mPaint.setShader(this.mLinearGradient);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.FlashView
 * JD-Core Version:    0.6.0
 */