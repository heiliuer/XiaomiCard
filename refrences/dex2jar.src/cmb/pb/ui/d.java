package cmb.pb.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class d
  implements View.OnFocusChangeListener
{
  d(PBKeyboardActivity paramPBKeyboardActivity)
  {
  }

  public final void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
      PBKeyboardActivity.a(this.a, paramView);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.ui.d
 * JD-Core Version:    0.6.0
 */