package rx.android.view;

import android.view.View;
import android.widget.TextView;
import rx.android.internal.Preconditions;
import rx.functions.Action1;

public final class ViewActions
{
  private ViewActions()
  {
    throw new IllegalStateException("No instances!");
  }

  public static Action1<? super Boolean> setActivated(View paramView)
  {
    Preconditions.checkNotNull(paramView, "view");
    return new ViewAction1(paramView)
    {
      public void call(View paramView, Boolean paramBoolean)
      {
        paramView.setActivated(paramBoolean.booleanValue());
      }
    };
  }

  public static Action1<? super Boolean> setClickable(View paramView)
  {
    Preconditions.checkNotNull(paramView, "view");
    return new ViewAction1(paramView)
    {
      public void call(View paramView, Boolean paramBoolean)
      {
        paramView.setClickable(paramBoolean.booleanValue());
      }
    };
  }

  public static Action1<? super Boolean> setEnabled(View paramView)
  {
    Preconditions.checkNotNull(paramView, "view");
    return new ViewAction1(paramView)
    {
      public void call(View paramView, Boolean paramBoolean)
      {
        paramView.setEnabled(paramBoolean.booleanValue());
      }
    };
  }

  public static Action1<? super Boolean> setFocusable(View paramView)
  {
    Preconditions.checkNotNull(paramView, "view");
    return new ViewAction1(paramView)
    {
      public void call(View paramView, Boolean paramBoolean)
      {
        paramView.setFocusable(paramBoolean.booleanValue());
      }
    };
  }

  public static Action1<? super Boolean> setSelected(View paramView)
  {
    Preconditions.checkNotNull(paramView, "view");
    return new ViewAction1(paramView)
    {
      public void call(View paramView, Boolean paramBoolean)
      {
        paramView.setSelected(paramBoolean.booleanValue());
      }
    };
  }

  public static Action1<? super CharSequence> setText(TextView paramTextView)
  {
    Preconditions.checkNotNull(paramTextView, "textView");
    return new ViewAction1(paramTextView)
    {
      public void call(TextView paramTextView, CharSequence paramCharSequence)
      {
        paramTextView.setText(paramCharSequence);
      }
    };
  }

  public static Action1<? super Integer> setTextResource(TextView paramTextView)
  {
    Preconditions.checkNotNull(paramTextView, "textView");
    return new ViewAction1(paramTextView)
    {
      public void call(TextView paramTextView, Integer paramInteger)
      {
        paramTextView.setText(paramInteger.intValue());
      }
    };
  }

  public static Action1<? super Boolean> setVisibility(View paramView)
  {
    return setVisibility(paramView, 8);
  }

  public static Action1<? super Boolean> setVisibility(View paramView, int paramInt)
  {
    Preconditions.checkNotNull(paramView, "view");
    if (paramInt != 0);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool, "Binding false to VISIBLE has no effect and is thus disallowed.");
      if ((paramInt == 4) || (paramInt == 8))
        break;
      throw new IllegalArgumentException(paramInt + " is not a valid visibility value.");
    }
    return new ViewAction1(paramView, paramInt)
    {
      public void call(View paramView, Boolean paramBoolean)
      {
        if (paramBoolean.booleanValue());
        for (int i = 0; ; i = this.val$visibilityOnFalse)
        {
          paramView.setVisibility(i);
          return;
        }
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.view.ViewActions
 * JD-Core Version:    0.6.0
 */