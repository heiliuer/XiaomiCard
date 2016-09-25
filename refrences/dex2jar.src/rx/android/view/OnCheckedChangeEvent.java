package rx.android.view;

import android.widget.CompoundButton;

public abstract class OnCheckedChangeEvent
{
  public static OnCheckedChangeEvent create(CompoundButton paramCompoundButton)
  {
    return create(paramCompoundButton, paramCompoundButton.isChecked());
  }

  public static OnCheckedChangeEvent create(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    return new AutoValue_OnCheckedChangeEvent(paramCompoundButton, paramBoolean);
  }

  public abstract boolean value();

  public abstract CompoundButton view();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.view.OnCheckedChangeEvent
 * JD-Core Version:    0.6.0
 */