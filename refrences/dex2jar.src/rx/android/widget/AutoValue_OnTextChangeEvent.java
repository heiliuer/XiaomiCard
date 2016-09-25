package rx.android.widget;

import android.widget.TextView;

final class AutoValue_OnTextChangeEvent extends OnTextChangeEvent
{
  private final CharSequence text;
  private final TextView view;

  AutoValue_OnTextChangeEvent(TextView paramTextView, CharSequence paramCharSequence)
  {
    if (paramTextView == null)
      throw new NullPointerException("Null view");
    this.view = paramTextView;
    if (paramCharSequence == null)
      throw new NullPointerException("Null text");
    this.text = paramCharSequence;
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (paramObject == this);
    while (true)
    {
      return i;
      if ((paramObject instanceof OnTextChangeEvent))
      {
        OnTextChangeEvent localOnTextChangeEvent = (OnTextChangeEvent)paramObject;
        if ((this.view.equals(localOnTextChangeEvent.view())) && (this.text.equals(localOnTextChangeEvent.text())))
          continue;
        i = 0;
        continue;
      }
      i = 0;
    }
  }

  public int hashCode()
  {
    return 1000003 * (1 * 1000003 ^ this.view.hashCode()) ^ this.text.hashCode();
  }

  public CharSequence text()
  {
    return this.text;
  }

  public String toString()
  {
    return "OnTextChangeEvent{view=" + this.view + ", text=" + this.text + "}";
  }

  public TextView view()
  {
    return this.view;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.AutoValue_OnTextChangeEvent
 * JD-Core Version:    0.6.0
 */