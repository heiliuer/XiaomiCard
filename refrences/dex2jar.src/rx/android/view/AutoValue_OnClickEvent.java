package rx.android.view;

import android.view.View;

final class AutoValue_OnClickEvent extends OnClickEvent
{
  private final View view;

  AutoValue_OnClickEvent(View paramView)
  {
    if (paramView == null)
      throw new NullPointerException("Null view");
    this.view = paramView;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (paramObject == this)
      bool = true;
    while (true)
    {
      return bool;
      if ((paramObject instanceof OnClickEvent))
      {
        OnClickEvent localOnClickEvent = (OnClickEvent)paramObject;
        bool = this.view.equals(localOnClickEvent.view());
        continue;
      }
      bool = false;
    }
  }

  public int hashCode()
  {
    return 1 * 1000003 ^ this.view.hashCode();
  }

  public String toString()
  {
    return "OnClickEvent{view=" + this.view + "}";
  }

  public View view()
  {
    return this.view;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.view.AutoValue_OnClickEvent
 * JD-Core Version:    0.6.0
 */