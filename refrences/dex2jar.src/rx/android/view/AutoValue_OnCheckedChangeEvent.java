package rx.android.view;

import android.widget.CompoundButton;

final class AutoValue_OnCheckedChangeEvent extends OnCheckedChangeEvent
{
  private final boolean value;
  private final CompoundButton view;

  AutoValue_OnCheckedChangeEvent(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    if (paramCompoundButton == null)
      throw new NullPointerException("Null view");
    this.view = paramCompoundButton;
    this.value = paramBoolean;
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (paramObject == this);
    while (true)
    {
      return i;
      if ((paramObject instanceof OnCheckedChangeEvent))
      {
        OnCheckedChangeEvent localOnCheckedChangeEvent = (OnCheckedChangeEvent)paramObject;
        if ((this.view.equals(localOnCheckedChangeEvent.view())) && (this.value == localOnCheckedChangeEvent.value()))
          continue;
        i = 0;
        continue;
      }
      i = 0;
    }
  }

  public int hashCode()
  {
    int i = 1000003 * (1 * 1000003 ^ this.view.hashCode());
    if (this.value);
    for (int j = 1231; ; j = 1237)
      return i ^ j;
  }

  public String toString()
  {
    return "OnCheckedChangeEvent{view=" + this.view + ", value=" + this.value + "}";
  }

  public boolean value()
  {
    return this.value;
  }

  public CompoundButton view()
  {
    return this.view;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.view.AutoValue_OnCheckedChangeEvent
 * JD-Core Version:    0.6.0
 */