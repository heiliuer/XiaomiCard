package cmb.pb.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class e
  implements View.OnTouchListener
{
  e(PBKeyboardActivity paramPBKeyboardActivity)
  {
  }

  public final boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0)
      PBKeyboardActivity.a(this.a, paramView);
    return true;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.ui.e
 * JD-Core Version:    0.6.0
 */