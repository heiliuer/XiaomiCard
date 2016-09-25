package rx.android.widget;

import android.view.View;
import android.widget.AdapterView;

final class AutoValue_OnItemClickEvent extends OnItemClickEvent
{
  private final long id;
  private final AdapterView<?> parent;
  private final int position;
  private final View view;

  AutoValue_OnItemClickEvent(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramAdapterView == null)
      throw new NullPointerException("Null parent");
    this.parent = paramAdapterView;
    if (paramView == null)
      throw new NullPointerException("Null view");
    this.view = paramView;
    this.position = paramInt;
    this.id = paramLong;
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (paramObject == this);
    while (true)
    {
      return i;
      if ((paramObject instanceof OnItemClickEvent))
      {
        OnItemClickEvent localOnItemClickEvent = (OnItemClickEvent)paramObject;
        if ((this.parent.equals(localOnItemClickEvent.parent())) && (this.view.equals(localOnItemClickEvent.view())) && (this.position == localOnItemClickEvent.position()) && (this.id == localOnItemClickEvent.id()))
          continue;
        i = 0;
        continue;
      }
      i = 0;
    }
  }

  public int hashCode()
  {
    return (int)(1000003 * (1000003 * (1000003 * (1 * 1000003 ^ this.parent.hashCode()) ^ this.view.hashCode()) ^ this.position) ^ (this.id >>> 32 ^ this.id));
  }

  public long id()
  {
    return this.id;
  }

  public AdapterView<?> parent()
  {
    return this.parent;
  }

  public int position()
  {
    return this.position;
  }

  public String toString()
  {
    return "OnItemClickEvent{parent=" + this.parent + ", view=" + this.view + ", position=" + this.position + ", id=" + this.id + "}";
  }

  public View view()
  {
    return this.view;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.AutoValue_OnItemClickEvent
 * JD-Core Version:    0.6.0
 */