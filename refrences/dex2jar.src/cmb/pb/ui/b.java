package cmb.pb.ui;

import android.content.res.Resources;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cmb.pb.a.d;

final class b
  implements View.OnClickListener
{
  b(PBKeyboardActivity paramPBKeyboardActivity)
  {
  }

  public final void onClick(View paramView)
  {
    if (PBKeyboardActivity.b(this.a) != null);
    try
    {
      int i = d.a(PBKeyboardActivity.c(this.a), "string", "cmbkb_finish_id");
      String str = this.a.getResources().getString(i);
      PBKeyboardActivity.b(this.a).setText(str);
      label50: PBKeyboardActivity.b(this.a).setOnClickListener(PBKeyboardActivity.d(this.a));
      PBKeyboardActivity.e(this.a).setKeyboard(PBKeyboardActivity.f(this.a));
      PBKeyboardActivity.a(this.a, PBKeyboardActivity.c());
      return;
    }
    catch (Exception localException)
    {
      break label50;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.ui.b
 * JD-Core Version:    0.6.0
 */