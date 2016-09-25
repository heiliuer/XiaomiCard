package rx.android.widget;

import android.widget.AbsListView;

public abstract class OnListViewScrollEvent
{
  public static OnListViewScrollEvent create(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return new AutoValue_OnListViewScrollEvent(paramAbsListView, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public abstract int firstVisibleItem();

  public abstract AbsListView listView();

  public abstract int scrollState();

  public abstract int totalItemCount();

  public abstract int visibleItemCount();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.OnListViewScrollEvent
 * JD-Core Version:    0.6.0
 */