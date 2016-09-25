package com.miui.tsmclient.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;

public class IssuedTransCardListLayoutManager extends RecyclerView.LayoutManager
{
  private static final String TAG = IssuedTransCardListLayoutManager.class.getSimpleName();
  public static final int TAG_NEED_OFFSET = 285212672;
  private static final int TOLERANCE_WHEN_CONTENT_HEIGHT_NOT_FILL = 10;
  private Context mContext;
  private DistanceBetweenChild mDistanceBetweenChild;
  private int mOldItemCount;
  private State state;

  public IssuedTransCardListLayoutManager(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private void fixScrollOffset()
  {
    if (getState().contentHeight <= getVerticalSpace())
      getState().scrolledY = 0;
    while (true)
    {
      return;
      if (getState().scrolledY > getState().contentHeight - getVerticalSpace())
      {
        getState().scrolledY = (getState().contentHeight - getVerticalSpace());
        continue;
      }
    }
  }

  private int getVerticalSpace()
  {
    return getHeight() - getPaddingBottom() - getPaddingTop();
  }

  private void initDistanceBetweenChild(Context paramContext)
  {
    int i = 0;
    View localView = getChildAt(0);
    int j = getVerticalSpace();
    if (localView == null);
    while (true)
    {
      float f1 = paramContext.getResources().getDimension(2131230775);
      float f2 = (j - 3.0F * i) / 2.0F;
      float f3 = (j - 4.0F * i) / 3.0F;
      float f4 = 1.0F * -i / 2.0F;
      this.mDistanceBetweenChild.init((int)f1, (int)f2, (int)f3, (int)f4);
      return;
      i = localView.getMeasuredHeight();
    }
  }

  private void layoutItems()
  {
    int i = getState().scrolledY;
    Log.v(TAG, "layoutItems, scrolledY:" + i);
    for (int j = 0; j < getChildCount(); j++)
    {
      View localView1 = getChildAt(j);
      Rect localRect = (Rect)getState().itemsFrames.get(j);
      layoutDecorated(localView1, localRect.left, localRect.top - i, localRect.right, localRect.bottom - i);
      View localView2 = (View)localView1.getTag(285212672);
      int k = getDistanceBetweenChild();
      if ((k >= 0) || (j >= -1 + getChildCount()))
        continue;
      localView2.setPadding(0, 0, 0, -k);
    }
  }

  public boolean canScrollVertically()
  {
    return true;
  }

  public RecyclerView.LayoutParams generateDefaultLayoutParams()
  {
    return null;
  }

  public int getDistanceBetweenChild()
  {
    if (this.mDistanceBetweenChild == null)
    {
      this.mDistanceBetweenChild = new DistanceBetweenChild(null);
      initDistanceBetweenChild(this.mContext);
    }
    int i = getItemCount();
    int j;
    if (i <= 2)
      j = this.mDistanceBetweenChild.mDistOfFullDisplayChild;
    while (true)
    {
      return j;
      if (i == 3)
      {
        j = this.mDistanceBetweenChild.mDistOfMostPartDisplayChild;
        continue;
      }
      if (i == 4)
      {
        j = this.mDistanceBetweenChild.mDistOfHalfPartDisplayChild;
        continue;
      }
      j = this.mDistanceBetweenChild.mDistOfPartialPartDisplayChild;
    }
  }

  public State getState()
  {
    if (this.state == null)
      this.state = new State();
    return this.state;
  }

  public void onLayoutChildren(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    Log.v(TAG, "onLayoutChildren, getChildCount():" + getChildCount() + ", getItemCount():" + getItemCount());
    detachAndScrapAttachedViews(paramRecycler);
    getState().contentHeight = 0;
    int i = 0;
    for (int j = 0; j < getItemCount(); j++)
    {
      View localView = paramRecycler.getViewForPosition(j);
      addView(localView);
      measureChildWithMargins(localView, 0, 0);
      int k = getDecoratedMeasuredWidth(localView);
      int m = getDecoratedMeasuredHeight(localView);
      Rect localRect = (Rect)getState().itemsFrames.get(j);
      if (localRect == null)
        localRect = new Rect();
      ChildLayoutInfo localChildLayoutInfo = new ChildLayoutInfo(j);
      localChildLayoutInfo.resolveLayoutInfo(k, m);
      localRect.set(localChildLayoutInfo.mLeft, localChildLayoutInfo.mTop, localChildLayoutInfo.mRight, localChildLayoutInfo.mBottom);
      getState().itemsFrames.put(j, localRect);
      getState().itemsAttached.put(j, false);
      i = localChildLayoutInfo.mBottom;
      Log.v(TAG, "onLayoutChildren, i = " + j + ", width:" + k + ", height:" + m + ",left:" + localRect.left + ", top:" + localRect.top + ", right:" + localRect.right + ", bottom:" + localRect.bottom);
    }
    getState().contentHeight = (i - getPaddingTop());
    layoutItems();
    if (this.mOldItemCount != getItemCount())
    {
      this.mOldItemCount = getItemCount();
      fixScrollOffset();
    }
  }

  public void scrollToPosition(int paramInt)
  {
    Log.v(TAG, "scrollToPosition, position:" + paramInt);
    int i = Math.min(Math.max(paramInt, 0), getItemCount());
    getState().scrolledY = ((Rect)getState().itemsFrames.get(i)).top;
    requestLayout();
  }

  public int scrollVerticallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    int i = paramInt;
    int j = i;
    if (getState().contentHeight <= getVerticalSpace())
      if (i + getState().scrolledY < -10)
      {
        j = -10 - getState().scrolledY;
        State localState = getState();
        localState.scrolledY = (j + localState.scrolledY);
        Log.v(TAG, "scrollVerticallyBy, dy:" + paramInt + ", willScroll:" + j + ", verticalSpace:" + getVerticalSpace() + ", contentHeight:" + getState().contentHeight + ", scrolledY:" + getState().scrolledY);
        if (j != 0)
          break label265;
        paramInt = 0;
      }
    while (true)
    {
      return paramInt;
      if (i + getState().scrolledY <= 10)
        break;
      j = 10 - getState().scrolledY;
      break;
      if (i + getState().scrolledY < 0)
      {
        j = -getState().scrolledY;
        break;
      }
      if (i + getState().scrolledY <= getState().contentHeight - getVerticalSpace())
        break;
      j = getState().contentHeight - getVerticalSpace() - getState().scrolledY;
      break;
      label265: layoutItems();
    }
  }

  public void smoothScrollToPosition(RecyclerView paramRecyclerView, RecyclerView.State paramState, int paramInt)
  {
    Log.v(TAG, "smoothScrollToPosition, targetPosition:" + paramInt);
    int i = Math.min(Math.max(paramInt, 0), getItemCount());
    1 local1 = new LinearSmoothScroller(paramRecyclerView.getContext())
    {
      public PointF computeScrollVectorForPosition(int paramInt)
      {
        int i = IssuedTransCardListLayoutManager.this.getState().scrolledY;
        IssuedTransCardListLayoutManager.this.getState().scrolledY = ((Rect)IssuedTransCardListLayoutManager.this.getState().itemsFrames.get(paramInt)).top;
        int j = IssuedTransCardListLayoutManager.this.getState().scrolledY;
        IssuedTransCardListLayoutManager.this.getState().scrolledY = i;
        return new PointF(0.0F, j - i);
      }
    };
    local1.setTargetPosition(i);
    startSmoothScroll(local1);
  }

  class State
  {
    int contentHeight = 0;
    SparseBooleanArray itemsAttached = new SparseBooleanArray();
    SparseArray<Rect> itemsFrames = new SparseArray();
    int scrolledY = 0;

    public State()
    {
    }
  }

  private class ChildLayoutInfo
  {
    public int mBottom;
    private int mChildIndex;
    public int mLeft;
    public int mRight;
    public int mTop;

    public ChildLayoutInfo(int arg2)
    {
      int i;
      this.mChildIndex = i;
    }

    private int getChildBottom(int paramInt1, int paramInt2)
    {
      return paramInt2 + getChildTop(paramInt1);
    }

    private int getChildLeft()
    {
      return IssuedTransCardListLayoutManager.this.getPaddingLeft();
    }

    private int getChildRight(int paramInt)
    {
      return paramInt + getChildLeft();
    }

    private int getChildTop(int paramInt)
    {
      return innerGetChildTop(paramInt, IssuedTransCardListLayoutManager.this.getItemCount(), IssuedTransCardListLayoutManager.this.getDistanceBetweenChild());
    }

    private int innerGetChildTop(int paramInt1, int paramInt2, int paramInt3)
    {
      int i = IssuedTransCardListLayoutManager.this.getPaddingTop();
      for (int j = 0; ; j++)
      {
        if ((j >= paramInt2) || (j == paramInt1));
        View localView;
        while (true)
        {
          return i;
          localView = IssuedTransCardListLayoutManager.this.getChildAt(j);
          if ((paramInt1 >= 0) || (localView.getMeasuredHeight() > Math.abs(paramInt1)))
            break;
          i = -1;
        }
        i += paramInt3 + localView.getMeasuredHeight();
      }
    }

    public void resolveLayoutInfo(int paramInt1, int paramInt2)
    {
      int i = this.mChildIndex;
      this.mLeft = getChildLeft();
      this.mRight = getChildRight(paramInt1);
      this.mTop = getChildTop(i);
      this.mBottom = getChildBottom(i, paramInt2);
    }
  }

  private class DistanceBetweenChild
  {
    int mDistOfFullDisplayChild;
    int mDistOfHalfPartDisplayChild;
    int mDistOfMostPartDisplayChild;
    int mDistOfPartialPartDisplayChild;

    private DistanceBetweenChild()
    {
    }

    public void init(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this.mDistOfFullDisplayChild = paramInt1;
      this.mDistOfMostPartDisplayChild = paramInt2;
      this.mDistOfHalfPartDisplayChild = paramInt3;
      this.mDistOfPartialPartDisplayChild = paramInt4;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.IssuedTransCardListLayoutManager
 * JD-Core Version:    0.6.0
 */