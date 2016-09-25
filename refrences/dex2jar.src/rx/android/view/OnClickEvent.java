package rx.android.view;

import android.view.View;

public abstract class OnClickEvent
{
  public static OnClickEvent create(View paramView)
  {
    return new AutoValue_OnClickEvent(paramView);
  }

  public abstract View view();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.view.OnClickEvent
 * JD-Core Version:    0.6.0
 */