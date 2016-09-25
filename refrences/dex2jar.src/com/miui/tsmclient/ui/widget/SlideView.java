package com.miui.tsmclient.ui.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

public class SlideView extends LinearLayout
{
  private static final Object LOCK = new Object();
  private Drawable mBackground;
  private int[] mColorAdded;
  private View mContentView;
  private int mCurrentColorPos = -1;
  private float mDelta;
  private View mFootView;
  private boolean mGradientShading;
  private View mHeaderView;
  private long mLastInvalideTime;
  private Bitmap mShadingBitmap;
  private boolean mShowTitleBar = false;

  public SlideView(Context paramContext)
  {
    this(paramContext, null);
  }

  public SlideView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public SlideView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mColorAdded = paramContext.getResources().getIntArray(2131427335);
  }

  public Drawable getBackground()
  {
    if (this.mBackground != null);
    for (Drawable localDrawable = this.mBackground; ; localDrawable = super.getBackground())
      return localDrawable;
  }

  public void onDrawForeground(Canvas paramCanvas)
  {
    super.onDrawForeground(paramCanvas);
    long l = System.currentTimeMillis() - this.mLastInvalideTime;
    if ((this.mGradientShading) && (this.mColorAdded != null) && ((l >= 40L) || (this.mCurrentColorPos == -1)))
    {
      if (this.mCurrentColorPos >= -1 + this.mColorAdded.length)
        this.mCurrentColorPos = -1;
      this.mCurrentColorPos = (1 + this.mCurrentColorPos);
      Paint localPaint = new Paint();
      localPaint.setColorFilter(new LightingColorFilter(16777215, this.mColorAdded[this.mCurrentColorPos]));
      synchronized (LOCK)
      {
        if (this.mShadingBitmap != null)
        {
          paramCanvas.drawBitmap(this.mShadingBitmap, 60 + getLeft(), 60 + getTop(), localPaint);
          this.mLastInvalideTime = System.currentTimeMillis();
        }
      }
    }
  }

  protected void onFinishInflate()
  {
    this.mHeaderView = findViewById(2131493003);
    this.mContentView = findViewById(2131492907);
    this.mFootView = findViewById(2131493008);
  }

  public void setBackground(Drawable paramDrawable)
  {
    if (this.mContentView != null)
    {
      this.mBackground = paramDrawable;
      this.mContentView.setBackground(paramDrawable);
    }
    while (true)
    {
      return;
      super.setBackground(paramDrawable);
    }
  }

  public void setDelta(float paramFloat)
  {
    this.mDelta = paramFloat;
  }

  public void setShowTitleBar(boolean paramBoolean)
  {
    this.mShowTitleBar = paramBoolean;
  }

  public void showHeaderView(View.OnLayoutChangeListener paramOnLayoutChangeListener)
  {
    if (this.mHeaderView != null)
    {
      this.mHeaderView.setVisibility(0);
      this.mHeaderView.addOnLayoutChangeListener(paramOnLayoutChangeListener);
    }
  }

  public void slideDown(Animator.AnimatorListener paramAnimatorListener)
  {
    this.mGradientShading = false;
    float f1 = -this.mDelta;
    float f2 = 0.0F;
    if (!this.mShowTitleBar)
    {
      float f3 = getResources().getDimension(2131230722);
      f1 += f3;
      f2 = 0.0F + f3;
    }
    Property localProperty1 = View.Y;
    float[] arrayOfFloat1 = new float[2];
    arrayOfFloat1[0] = f1;
    arrayOfFloat1[1] = f2;
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(this, localProperty1, arrayOfFloat1);
    localObjectAnimator1.setInterpolator(new DecelerateInterpolator());
    View localView1 = this.mHeaderView;
    Property localProperty2 = View.ALPHA;
    float[] arrayOfFloat2 = new float[2];
    arrayOfFloat2[0] = 0.0F;
    arrayOfFloat2[1] = 1.0F;
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(localView1, localProperty2, arrayOfFloat2);
    View localView2 = this.mFootView;
    Property localProperty3 = View.ALPHA;
    float[] arrayOfFloat3 = new float[2];
    arrayOfFloat3[0] = 0.0F;
    arrayOfFloat3[1] = 1.0F;
    ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(localView2, localProperty3, arrayOfFloat3);
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.setDuration(200L);
    localAnimatorSet.addListener(paramAnimatorListener);
    Animator[] arrayOfAnimator = new Animator[5];
    arrayOfAnimator[0] = localObjectAnimator1;
    arrayOfAnimator[1] = localObjectAnimator2;
    arrayOfAnimator[2] = localObjectAnimator3;
    View localView3 = this.mHeaderView;
    Property localProperty4 = View.SCALE_X;
    float[] arrayOfFloat4 = new float[2];
    arrayOfFloat4[0] = 0.0F;
    arrayOfFloat4[1] = 1.0F;
    arrayOfAnimator[3] = ObjectAnimator.ofFloat(localView3, localProperty4, arrayOfFloat4);
    View localView4 = this.mHeaderView;
    Property localProperty5 = View.SCALE_Y;
    float[] arrayOfFloat5 = new float[2];
    arrayOfFloat5[0] = 0.0F;
    arrayOfFloat5[1] = 1.0F;
    arrayOfAnimator[4] = ObjectAnimator.ofFloat(localView4, localProperty5, arrayOfFloat5);
    localAnimatorSet.playTogether(arrayOfAnimator);
    localAnimatorSet.start();
  }

  public void startShading()
  {
    this.mGradientShading = true;
    if ((this.mShadingBitmap == null) && (this.mBackground != null))
      this.mShadingBitmap = ((BitmapDrawable)this.mBackground).getBitmap().copy(Bitmap.Config.ARGB_4444, true);
    invalidate();
  }

  public void stopShading()
  {
    this.mGradientShading = false;
    this.mCurrentColorPos = -1;
    if (this.mShadingBitmap != null)
      synchronized (LOCK)
      {
        this.mShadingBitmap.recycle();
        this.mShadingBitmap = null;
      }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.SlideView
 * JD-Core Version:    0.6.0
 */