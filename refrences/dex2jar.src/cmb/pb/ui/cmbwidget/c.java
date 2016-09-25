package cmb.pb.ui.cmbwidget;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class c
  implements View.OnTouchListener
{
  private c(CmbEditText paramCmbEditText)
  {
  }

  public final boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = 1;
    CmbEditText localCmbEditText;
    if ((this.a.getCompoundDrawables()[2] != null) && (paramMotionEvent.getAction() == i))
    {
      localCmbEditText = this.a;
      if ((paramMotionEvent.getX() <= this.a.getWidth() - this.a.getPaddingRight() - CmbEditText.c(this.a).getIntrinsicWidth()) || (paramMotionEvent.getX() >= this.a.getWidth() - this.a.getPaddingRight()))
        break label148;
    }
    while (true)
    {
      CmbEditText.a(localCmbEditText, i);
      if (CmbEditText.d(this.a))
        this.a.a();
      CmbEditText.a(this.a);
      CmbEditText.a(this.a);
      if (!CmbEditText.b(this.a))
        this.a.b();
      return false;
      label148: int j = 0;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.ui.cmbwidget.c
 * JD-Core Version:    0.6.0
 */