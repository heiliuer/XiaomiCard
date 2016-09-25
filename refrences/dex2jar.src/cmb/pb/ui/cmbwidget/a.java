package cmb.pb.ui.cmbwidget;

import android.text.Editable;
import android.view.View;
import android.view.View.OnFocusChangeListener;

final class a
  implements View.OnFocusChangeListener
{
  private a(CmbEditText paramCmbEditText)
  {
  }

  public final void onFocusChange(View paramView, boolean paramBoolean)
  {
    boolean bool = false;
    if (paramBoolean)
    {
      CmbEditText.a(this.a);
      CmbEditText.a(this.a);
      CmbEditText.a(this.a);
      if (!CmbEditText.b(this.a))
        this.a.b();
      CmbEditText localCmbEditText = this.a;
      if (this.a.getText().length() > 0)
        bool = true;
      localCmbEditText.a(bool);
    }
    while (true)
    {
      return;
      this.a.c();
      this.a.a(false);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.ui.cmbwidget.a
 * JD-Core Version:    0.6.0
 */