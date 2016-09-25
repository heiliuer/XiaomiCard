package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;
import com.miui.tsmclient.R.styleable;
import java.util.ArrayList;
import java.util.List;

public class WaveCircle extends View
{
  private static final int INITIAL_ALPHA = 80;
  private static final int INITIAL_RADIUS = 200;
  private static final int INTERVAL = 60;
  private static final int WAVE_CENTER_IN_X = 540;
  private static final int WAVE_CENTER_IN_Y = 498;
  private static final int WAVE_COUNTS = 2;
  private static final int WAVE_RATE = 3;
  private static final int WAVE_STEPS = 5;
  private List<Integer> mAlphaList = new ArrayList();
  private boolean mIsStarting = false;
  private int mMaxRadius;
  private Paint mPaint;
  private int mPaintColor;
  private List<Integer> mRadiusList = new ArrayList();
  private long[] mVibratePattern;
  private Vibrator mVibrator;
  private int mWaveGap;

  public WaveCircle(Context paramContext)
  {
    this(paramContext, null);
  }

  public WaveCircle(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public WaveCircle(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }

  public WaveCircle(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    long[] arrayOfLong = new long[5];
    arrayOfLong[0] = 450L;
    arrayOfLong[1] = 20L;
    arrayOfLong[2] = 60L;
    arrayOfLong[3] = 20L;
    arrayOfLong[4] = 850L;
    this.mVibratePattern = arrayOfLong;
    TypedArray localTypedArray = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.WaveCircle, paramInt1, paramInt2);
    this.mMaxRadius = (int)localTypedArray.getDimension(1, 700.0F);
    this.mPaintColor = localTypedArray.getColor(0, -1);
    initPaint();
  }

  private void initPaint()
  {
    this.mPaint = new Paint();
    this.mPaint.setColor(this.mPaintColor);
    this.mAlphaList.add(Integer.valueOf(80));
    this.mRadiusList.add(Integer.valueOf(200));
    this.mWaveGap = 100;
  }

  private void vibrate()
  {
    if (this.mVibrator == null)
      this.mVibrator = ((Vibrator)this.mContext.getSystemService("vibrator"));
    this.mVibrator.vibrate(this.mVibratePattern, 0);
  }

  public boolean isWaving()
  {
    return this.mIsStarting;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.mIsStarting)
    {
      int i = 0;
      if (i < this.mRadiusList.size())
      {
        int j = ((Integer)this.mAlphaList.get(i)).intValue();
        int k = ((Integer)this.mRadiusList.get(i)).intValue();
        this.mPaint.setAlpha(j);
        paramCanvas.drawCircle(540.0F, 498.0F, k, this.mPaint);
        if ((j > 0) && (k < this.mMaxRadius))
        {
          this.mAlphaList.set(i, Integer.valueOf(j - 1));
          this.mRadiusList.set(i, Integer.valueOf(k + 5));
        }
        while (true)
        {
          i++;
          break;
          this.mAlphaList.set(i, Integer.valueOf(80));
          this.mRadiusList.set(i, Integer.valueOf(200));
        }
      }
      if ((this.mRadiusList.size() < 2) && (200 + this.mWaveGap == ((Integer)this.mRadiusList.get(-1 + this.mRadiusList.size())).intValue()))
      {
        this.mAlphaList.add(Integer.valueOf(80));
        this.mRadiusList.add(Integer.valueOf(200));
      }
      postInvalidateDelayed(3L);
    }
  }

  public void startWave()
  {
    this.mIsStarting = true;
    invalidate();
    vibrate();
  }

  public void stopWave()
  {
    this.mIsStarting = false;
    if (this.mVibrator != null)
      this.mVibrator.cancel();
    this.mAlphaList.clear();
    this.mRadiusList.clear();
    this.mAlphaList.add(Integer.valueOf(80));
    this.mRadiusList.add(Integer.valueOf(200));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.WaveCircle
 * JD-Core Version:    0.6.0
 */