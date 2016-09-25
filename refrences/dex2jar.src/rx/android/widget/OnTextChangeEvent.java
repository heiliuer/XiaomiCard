package rx.android.widget;

import android.text.SpannableString;
import android.widget.TextView;

public abstract class OnTextChangeEvent
{
  public static OnTextChangeEvent create(TextView paramTextView)
  {
    return create(paramTextView, new SpannableString(paramTextView.getText()));
  }

  public static OnTextChangeEvent create(TextView paramTextView, CharSequence paramCharSequence)
  {
    return new AutoValue_OnTextChangeEvent(paramTextView, paramCharSequence);
  }

  public abstract CharSequence text();

  public abstract TextView view();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.OnTextChangeEvent
 * JD-Core Version:    0.6.0
 */