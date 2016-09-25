package rx.android.widget;

import android.view.View;
import android.widget.AdapterView;

public abstract class OnItemClickEvent
{
  public static OnItemClickEvent create(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    return new AutoValue_OnItemClickEvent(paramAdapterView, paramView, paramInt, paramLong);
  }

  public abstract long id();

  public abstract AdapterView<?> parent();

  public abstract int position();

  public abstract View view();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.widget.OnItemClickEvent
 * JD-Core Version:    0.6.0
 */