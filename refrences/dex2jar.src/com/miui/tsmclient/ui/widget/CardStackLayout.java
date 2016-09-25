package com.miui.tsmclient.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Property;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import com.miui.tsmclient.R.styleable;
import com.miui.tsmclient.ui.quick.CardStackAdapter;
import com.miui.tsmclient.ui.quick.SwitchCardFragment.StackItem;
import com.miui.tsmclient.util.LogUtils;
import java.util.ArrayList;
import java.util.List;

public class CardStackLayout extends FrameLayout
{
  public static final int ANIM_DURATION = 500;
  public static final int DECELERATION_FACTOR = 2;
  private static final float DEFAULT_COLLAPSED_AREA_HEIGHT = 720.0F;
  private static final float DEFAULT_MIN_COLLAPSED_CARDS_GAP = 300.0F;
  public static final int INVALID_CARD_POSITION = -1;
  private static final int INVALID_POSITION = -1;
  private static final int TOUCH_STATE_DEFAULT = 0;
  private static final int TOUCH_STATE_DOWN = 3;
  private static final int TOUCH_STATE_FLING = 2;
  private static final int TOUCH_STATE_SCROLL = 1;
  private CardStackAdapter mAdapter;
  private float mColAreaHeight;
  private float mColGap;
  private AdapterDataSetObserver mDataSetObserver;
  private GestureDetector mGestureDetector = new GestureDetector(paramContext, this.mGestureListener);
  private GestureDetector.OnGestureListener mGestureListener = new GestureDetector.OnGestureListener()
  {
    public boolean onDown(MotionEvent paramMotionEvent)
    {
      return true;
    }

    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      CardStackLayout.this.scrollCards(paramFloat2 / 5.0F, true);
      return true;
    }

    public void onLongPress(MotionEvent paramMotionEvent)
    {
    }

    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      CardStackLayout.this.scrollCards(-paramFloat2);
      return true;
    }

    public void onShowPress(MotionEvent paramMotionEvent)
    {
    }

    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      if ((!CardStackLayout.this.mHasPopup) && (CardStackLayout.this.mLastMotion > CardStackLayout.this.mScreenHeight - 120.0F) && (CardStackLayout.this.mAdapter.getCount() > 4))
        CardStackLayout.this.popupCards();
      while (true)
      {
        return true;
        CardStackLayout.this.performClickEvent(CardStackLayout.access$600(CardStackLayout.this));
      }
    }
  };
  private boolean mHasPopup = false;
  private float mInvisibleCollapsedArea;
  private float mInvisibleCollapsedCardGap;
  private float mLastMotion;
  private RecycleBin mRecycler = new RecycleBin(null);
  private float mScreenHeight;
  private boolean mScreenTouchable = true;
  private AnimatorSet mScrollAnimatorSet = new AnimatorSet();
  private int mSelectedCardPosition = -1;
  private boolean mShowTitleBar;
  private float mTitleBarHeight;
  private int mTouchSlop = ViewConfiguration.getTouchSlop();
  private int mTouchState = 0;

  public CardStackLayout(Context paramContext)
  {
    this(paramContext, null);
  }

  public CardStackLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public CardStackLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }

  public CardStackLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    TypedArray localTypedArray = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.CardStackLayout, paramInt1, paramInt2);
    this.mColAreaHeight = localTypedArray.getDimension(1, 720.0F);
    this.mShowTitleBar = localTypedArray.getBoolean(3, false);
    this.mTitleBarHeight = getResources().getDimension(2131230722);
    localTypedArray.recycle();
  }

  private void addViewItem(View paramView, int paramInt1, int paramInt2)
  {
    paramView.setLayoutParams(LayoutParams.create(paramView.getLayoutParams(), paramInt1));
    paramView.setY(getCardOriginalY(paramInt2));
    addView(paramView);
  }

  private void calViewLayout()
  {
    for (int i = 0; i < getChildCount(); i++)
    {
      View localView3 = getChildAt(i);
      this.mRecycler.addScrapView(localView3);
    }
    detachAllViewsFromParent();
    if (this.mAdapter != null)
    {
      int j = this.mAdapter.getCount();
      if (j <= 3)
        this.mColGap = 300.0F;
      while (true)
      {
        for (int m = 0; m < j; m++)
        {
          int n = this.mAdapter.getItemViewType(m);
          View localView1 = this.mRecycler.getScrapView(n);
          View localView2 = this.mAdapter.getView(m, localView1, this);
          if (localView2 == null)
            continue;
          addViewItem(localView2, n, m);
        }
        if (j <= 3)
          continue;
        this.mColGap = 300.0F;
        int k = j - 3;
        if (k > 6)
          k = 6;
        this.mInvisibleCollapsedArea = (this.mColAreaHeight % this.mColGap);
        this.mInvisibleCollapsedCardGap = (this.mInvisibleCollapsedArea / k);
      }
    }
  }

  private View findViewFromTouchY()
  {
    View localView1;
    if (!this.mScreenTouchable)
      localView1 = null;
    while (true)
    {
      return localView1;
      for (int i = 0; ; i++)
      {
        if (i >= this.mAdapter.getCount())
          break label109;
        localView1 = getChildAt(i);
        View localView2 = getChildAt(i + 1);
        if (60.0F + localView1.getY() >= this.mLastMotion)
          break label104;
        if (((localView2 != null) && (60.0F + localView2.getY() > this.mLastMotion)) || ((localView2 == null) && (605.0F + (60.0F + localView1.getY()) > this.mLastMotion)))
          break;
      }
      label104: localView1 = null;
      continue;
      label109: localView1 = null;
    }
  }

  private float getCardFinalY(int paramInt)
  {
    float f;
    if (this.mShowTitleBar)
      f = this.mColGap * paramInt - 60.0F;
    while (true)
    {
      return f;
      f = this.mColGap * paramInt + this.mTitleBarHeight - 60.0F;
    }
  }

  private float getCardOriginalY(int paramInt)
  {
    float f;
    if (paramInt == 0)
      if (this.mShowTitleBar)
        f = -60.0F;
    while (true)
    {
      return f;
      f = this.mTitleBarHeight - 60.0F;
      continue;
      if (paramInt == 1)
      {
        if (this.mAdapter.getCount() == 2)
        {
          f = this.mScreenHeight - this.mColGap - 60.0F;
          continue;
        }
        if (this.mAdapter.getCount() == 3)
        {
          f = this.mScreenHeight - 2.0F * this.mColGap - 60.0F;
          continue;
        }
        f = this.mScreenHeight - this.mColAreaHeight - 60.0F;
        continue;
      }
      if (paramInt < 4)
      {
        f = getCardOriginalY(1) + (paramInt - 1) * this.mColGap;
        continue;
      }
      f = getCardOriginalY(3) + (paramInt - 3) * this.mInvisibleCollapsedCardGap;
    }
  }

  private void performClickEvent(View paramView)
  {
    if ((!this.mScreenTouchable) || (paramView == null));
    while (true)
    {
      return;
      this.mSelectedCardPosition = ((Integer)paramView.getTag(2131492864)).intValue();
      if ((this.mSelectedCardPosition == 0) && (!this.mHasPopup))
      {
        this.mAdapter.onDefaultCardSelected();
        continue;
      }
      this.mScreenTouchable = false;
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      if (i < this.mAdapter.getCount())
      {
        View localView = getChildAt(i);
        if (i == this.mSelectedCardPosition)
        {
          Property localProperty3 = View.Y;
          float[] arrayOfFloat3 = new float[2];
          arrayOfFloat3[0] = getCardOriginalY(0);
          arrayOfFloat3[1] = getCardOriginalY(i);
          localArrayList.add(ObjectAnimator.ofFloat(localView, localProperty3, arrayOfFloat3));
        }
        while (true)
        {
          i++;
          break;
          if (i == 0)
          {
            Property localProperty2 = View.Y;
            float[] arrayOfFloat2 = new float[2];
            arrayOfFloat2[0] = getCardOriginalY(this.mSelectedCardPosition);
            arrayOfFloat2[1] = getCardOriginalY(0);
            localArrayList.add(ObjectAnimator.ofFloat(localView, localProperty2, arrayOfFloat2));
            continue;
          }
          Property localProperty1 = View.Y;
          float[] arrayOfFloat1 = new float[2];
          arrayOfFloat1[0] = (int)localView.getY();
          arrayOfFloat1[1] = getCardOriginalY(i);
          localArrayList.add(ObjectAnimator.ofFloat(localView, localProperty1, arrayOfFloat1));
        }
      }
      SwitchCardFragment.StackItem localStackItem = this.mAdapter.getItem(this.mSelectedCardPosition);
      this.mAdapter.removeData(this.mSelectedCardPosition, false);
      this.mAdapter.addData(this.mSelectedCardPosition, this.mAdapter.getItem(0), false);
      this.mAdapter.removeData(0, false);
      this.mAdapter.addData(0, localStackItem, false);
      Drawable localDrawable = getChildAt(0).getBackground();
      getChildAt(0).setBackground(getChildAt(this.mSelectedCardPosition).getBackground());
      getChildAt(this.mSelectedCardPosition).setBackground(localDrawable);
      startAnimations(localArrayList, new Runnable()
      {
        public void run()
        {
          CardStackLayout.access$1002(CardStackLayout.this, true);
          CardStackLayout.access$102(CardStackLayout.this, false);
          CardStackLayout.this.mAdapter.onDefaultCardChanged(CardStackLayout.this.mSelectedCardPosition);
          CardStackLayout.access$1102(CardStackLayout.this, -1);
        }
      });
    }
  }

  private void popupCards()
  {
    if ((!this.mScreenTouchable) || (this.mAdapter.getCount() < 5));
    while (true)
    {
      return;
      this.mScreenTouchable = false;
      ArrayList localArrayList = new ArrayList();
      for (int i = 0; i < this.mAdapter.getCount(); i++)
      {
        View localView = getChildAt(i);
        Property localProperty = View.Y;
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = (int)localView.getY();
        arrayOfFloat[1] = getCardFinalY(i);
        localArrayList.add(ObjectAnimator.ofFloat(localView, localProperty, arrayOfFloat));
      }
      startAnimations(localArrayList, 500L, 0L, new Runnable()
      {
        public void run()
        {
          CardStackLayout.access$102(CardStackLayout.this, true);
          CardStackLayout.access$1002(CardStackLayout.this, true);
          if (CardStackLayout.this.mAdapter != null)
            CardStackLayout.this.mAdapter.onCardsPoppedUp();
        }
      });
    }
  }

  private void scrollCards(float paramFloat)
  {
    scrollCards(paramFloat, false);
  }

  private void scrollCards(float paramFloat, boolean paramBoolean)
  {
    if (!this.mScreenTouchable);
    while (true)
    {
      return;
      if (!this.mHasPopup)
      {
        if (this.mTouchState != 0)
          continue;
        if ((getChildCount() > 4) && (this.mLastMotion > this.mScreenHeight - 120.0F))
        {
          popupCards();
          continue;
        }
        performClickEvent(findViewFromTouchY());
        continue;
      }
      float f = Math.abs(paramFloat) / 24.0F * (1 + 2 * this.mAdapter.getCount());
      if (paramFloat < 0.0F)
      {
        scrollUp(f, this.mAdapter.getCount(), paramBoolean);
        continue;
      }
      if (paramFloat <= 0.0F)
        continue;
      scrollDown(this.mAdapter.getCount(), f, paramBoolean);
    }
  }

  private void scrollDown(int paramInt, float paramFloat, boolean paramBoolean)
  {
    if (paramFloat <= 0.0F);
    while (true)
    {
      return;
      View localView1 = getChildAt(0);
      if (paramFloat + localView1.getY() > getCardOriginalY(0))
        paramFloat = getCardOriginalY(0) - localView1.getY();
      if (paramFloat == 0.0F)
        continue;
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      if (i < paramInt)
      {
        View localView2 = getChildAt(i);
        float f = paramFloat + localView2.getY();
        if (paramBoolean)
        {
          Property localProperty = View.Y;
          float[] arrayOfFloat = new float[2];
          arrayOfFloat[0] = localView2.getY();
          arrayOfFloat[1] = f;
          localArrayList.add(ObjectAnimator.ofFloat(localView2, localProperty, arrayOfFloat));
        }
        while (true)
        {
          i++;
          break;
          localView2.setY(f);
        }
      }
      if (!paramBoolean)
        continue;
      startScrollAnimations(localArrayList, null);
    }
  }

  private void scrollUp(float paramFloat, int paramInt, boolean paramBoolean)
  {
    View localView1 = getChildAt(paramInt - 1);
    float f1 = this.mScreenHeight - this.mColGap;
    if (localView1.getY() - paramFloat < f1)
      paramFloat = localView1.getY() - f1;
    if (paramFloat <= 0.0F);
    while (true)
    {
      return;
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      if (i < paramInt)
      {
        View localView2 = getChildAt(i);
        float f2 = localView2.getY() - paramFloat;
        if (paramBoolean)
        {
          Property localProperty = View.Y;
          float[] arrayOfFloat = new float[2];
          arrayOfFloat[0] = localView2.getY();
          arrayOfFloat[1] = f2;
          localArrayList.add(ObjectAnimator.ofFloat(localView2, localProperty, arrayOfFloat));
        }
        while (true)
        {
          i++;
          break;
          localView2.setY(f2);
        }
      }
      if (!paramBoolean)
        continue;
      startScrollAnimations(localArrayList, null);
    }
  }

  private void startAnimations(List<Animator> paramList, long paramLong1, long paramLong2, Runnable paramRunnable)
  {
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.playTogether(paramList);
    localAnimatorSet.setInterpolator(new DecelerateInterpolator(2.0F));
    localAnimatorSet.setStartDelay(paramLong2);
    localAnimatorSet.setDuration(paramLong1);
    localAnimatorSet.addListener(new AnimatorListenerAdapter(paramRunnable)
    {
      public void onAnimationEnd(Animator paramAnimator)
      {
        CardStackLayout.access$1102(CardStackLayout.this, -1);
      }

      public void onAnimationStart(Animator paramAnimator)
      {
        if (this.val$runnable != null)
          this.val$runnable.run();
      }
    });
    localAnimatorSet.start();
  }

  private void startAnimations(List<Animator> paramList, Runnable paramRunnable)
  {
    startAnimations(paramList, 500L, 0L, paramRunnable);
  }

  private void startScrollAnimations(List<Animator> paramList, Runnable paramRunnable)
  {
    if (this.mScrollAnimatorSet == null)
    {
      this.mScrollAnimatorSet = new AnimatorSet();
      this.mScrollAnimatorSet.setInterpolator(new DecelerateInterpolator(2.0F));
      this.mScrollAnimatorSet.setStartDelay(0L);
      this.mScrollAnimatorSet.setDuration(1000L);
    }
    this.mScrollAnimatorSet.playTogether(paramList);
    this.mScrollAnimatorSet.addListener(new AnimatorListenerAdapter(paramRunnable)
    {
      public void onAnimationEnd(Animator paramAnimator)
      {
        CardStackLayout.access$1002(CardStackLayout.this, true);
        CardStackLayout.access$1102(CardStackLayout.this, -1);
      }

      public void onAnimationStart(Animator paramAnimator)
      {
        if (this.val$runnable != null)
          this.val$runnable.run();
      }
    });
    this.mScrollAnimatorSet.start();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mScreenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    LogUtils.v("onInterceptTouchEvent action: " + i);
    if ((i == 0) && (paramMotionEvent.getEdgeFlags() != 0));
    for (int j = 0; ; j = 1)
      return j;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    float f = paramMotionEvent.getRawY();
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
      this.mTouchState = 0;
    case 0:
    case 2:
    }
    while (true)
    {
      return this.mGestureDetector.onTouchEvent(paramMotionEvent);
      if (this.mScrollAnimatorSet.isRunning())
        this.mScrollAnimatorSet.end();
      this.mLastMotion = f;
      this.mTouchState = 3;
      continue;
      if (Math.abs(f - this.mLastMotion) <= this.mTouchSlop)
        continue;
      this.mTouchState = 1;
    }
  }

  public void setAdapter(CardStackAdapter paramCardStackAdapter)
  {
    if (this.mAdapter != null)
      this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
    this.mAdapter = paramCardStackAdapter;
    if (this.mAdapter != null)
    {
      this.mDataSetObserver = new AdapterDataSetObserver();
      this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
    }
    calViewLayout();
  }

  public void setScreenTouchable(boolean paramBoolean)
  {
    this.mScreenTouchable = paramBoolean;
  }

  private static class RecycleBin
  {
    private ArrayList<View>[] mScrapViews;
    private int mViewTypeCount;

    public void addScrapView(View paramView)
    {
      CardStackLayout.LayoutParams localLayoutParams = (CardStackLayout.LayoutParams)paramView.getLayoutParams();
      this.mScrapViews[localLayoutParams.viewType].add(paramView);
    }

    public View getScrapView(int paramInt)
    {
      View localView = null;
      if (paramInt >= this.mViewTypeCount);
      while (true)
      {
        return localView;
        int i = this.mScrapViews[paramInt].size();
        if (i <= 0)
          continue;
        localView = (View)this.mScrapViews[paramInt].remove(i - 1);
      }
    }

    public void setViewTypeCount(int paramInt)
    {
      if (paramInt < 1)
        paramInt = 1;
      if (this.mViewTypeCount == paramInt);
      while (true)
      {
        return;
        this.mViewTypeCount = paramInt;
        ArrayList[] arrayOfArrayList = new ArrayList[paramInt];
        for (int i = 0; i < paramInt; i++)
          arrayOfArrayList[i] = new ArrayList();
        this.mScrapViews = arrayOfArrayList;
      }
    }
  }

  public static class LayoutParams extends ViewGroup.MarginLayoutParams
  {
    int viewType;

    public LayoutParams()
    {
      super(-1);
    }

    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }

    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }

    public static LayoutParams create(ViewGroup.LayoutParams paramLayoutParams)
    {
      return create(paramLayoutParams, 0);
    }

    public static LayoutParams create(ViewGroup.LayoutParams paramLayoutParams, int paramInt)
    {
      LayoutParams localLayoutParams = new LayoutParams(paramLayoutParams);
      localLayoutParams.viewType = paramInt;
      return localLayoutParams;
    }
  }

  class AdapterDataSetObserver extends DataSetObserver
  {
    AdapterDataSetObserver()
    {
    }

    public void onChanged()
    {
      super.onChanged();
      CardStackLayout.this.calViewLayout();
    }

    public void onInvalidated()
    {
      super.onInvalidated();
      CardStackLayout.this.calViewLayout();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.CardStackLayout
 * JD-Core Version:    0.6.0
 */