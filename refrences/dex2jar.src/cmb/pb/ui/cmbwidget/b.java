package cmb.pb.ui.cmbwidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.view.MotionEvent;
import cmb.pb.cmbsafe.CmbService;
import cmb.pb.ui.PBKeyboardActivity;

final class b extends Handler
{
  public b(CmbEditText paramCmbEditText)
  {
  }

  public final void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      if (this.a == null)
        continue;
      this.a.requestFocus();
      if (!CmbEditText.d(this.a))
      {
        int i = paramMessage.getData().getInt("KeyCode");
        this.a.getText().insert(this.a.getSelectionStart(), Character.toString((char)i));
        continue;
      }
      PBKeyboardActivity.b().a();
      this.a.setText("");
      CmbEditText.a(this.a, false);
      continue;
      if (this.a == null)
        continue;
      this.a.requestFocus();
      if (!CmbEditText.d(this.a))
      {
        String str = paramMessage.getData().getString("KeyString");
        this.a.setText(str);
        CmbEditText.a(this.a);
        continue;
      }
      PBKeyboardActivity.b().a();
      this.a.setText("");
      CmbEditText.a(this.a, false);
      continue;
      CmbEditText.e(this.a);
      if (CmbEditText.f(this.a) != null)
      {
        Bundle localBundle = paramMessage.getData();
        float f1 = localBundle.getFloat("rawX");
        float f2 = localBundle.getFloat("rawY");
        CmbEditText.f(this.a).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, f1, f2, 0));
        CmbEditText.f(this.a).dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, f1, f2, 0));
      }
      CmbEditText.f(this.a).stopService(new Intent(CmbEditText.f(this.a), CmbService.class));
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.ui.cmbwidget.b
 * JD-Core Version:    0.6.0
 */