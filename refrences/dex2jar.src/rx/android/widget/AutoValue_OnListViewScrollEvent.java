package rx.android.widget;

import android.widget.AbsListView;

final class AutoValue_OnListViewScrollEvent extends OnListViewScrollEvent
{
  private final int firstVisibleItem;
  private final AbsListView listView;
  private final int scrollState;
  private final int totalItemCount;
  private final int visibleItemCount;

  AutoValue_OnListViewScrollEvent(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramAbsListView == null)
      throw new NullPointerException("Null listView");
    this.listView = paramAbsListView;
    this.scrollState = paramInt1;
    this.firstVisibleItem = paramInt2;
    this.visibleItemCount = paramInt3;
    this.totalItemCount = paramInt4;
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (paramObject == this);
    while (true)
    {
      return i;
      if ((paramObject instanceof OnListViewScrollEvent))
      {
        OnListViewScrollEvent localOnListViewScrollEvent = (OnListViewScrollEvent)paramObject;
        if ((this.listView.equals(localOnListViewScrollEvent.listView())) && (this.scrollState == localOnListViewScrollEvent.scrollState()) && (this.firstVisibleItem == localOnListViewScrollEvent.firstVisibleItem()) && (this.visibleItemCount == localOnListViewScrollEvent.visibleItemCount()) && (this.totalItemCount == localOnListViewScrollEvent.totalItemCount()))
          continue;
        i = 0;
        continue;
      }
      i = 0;
    }
  }

  public int firstVisibleItem()
  {
    return this.firstVisibleItem;
  }

  public int hashCode()
  {
    return 1000003 * (1000003 * (1000003 * (1000003 * (1 * 1000003 ^ this.listView.hashCode()) ^ this.scrollState) ^ this.firstVisibleItem) ^ this.visibleItemCount) ^ this.totalItemCount;
  }

  public AbsListView listView()
  {
    return this.listView;
  }

  public int scrollState()
  {
    return this.scrollState;
  }

  public String toString()
  {
    return "OnListViewScrollEvent{listView=" + this.listView + ", scrollState=" + this.scrollState + ", firstVisibleItem=" + this.firstVisibleItem + ", visibleItemCount=" + this.visibleItemCount + ", totalItemCount=" + this.totalItemCount + "}";
  }

  public int totalItemCount()
  {
    return this.totalItemCount;
  }

  public int visibleItemCount()
  {
    return this.visibleItemCount;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.AutoValue_OnListViewScrollEvent
 * JD-Core Version:    0.6.0
 */