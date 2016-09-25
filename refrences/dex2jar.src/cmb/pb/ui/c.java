package cmb.pb.ui;

import android.content.res.Resources;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;
import cmb.pb.a.d;
import cmb.pb.ui.cmbwidget.e;

final class c
  implements KeyboardView.OnKeyboardActionListener
{
  c(PBKeyboardActivity paramPBKeyboardActivity)
  {
  }

  public final void onKey(int paramInt, int[] paramArrayOfInt)
  {
    Editable localEditable = PBKeyboardActivity.g(this.a).getText();
    int i = PBKeyboardActivity.g(this.a).getSelectionStart();
    if (paramInt == -3)
    {
      PBKeyboardActivity.a(this.a);
      return;
    }
    if (paramInt == -5)
      if ((localEditable != null) && (localEditable.length() > 0) && (i > 0))
      {
        localEditable.delete(i - 1, i);
        if ((PBKeyboardActivity.h(this.a)) && (PBKeyboardActivity.d() != null))
          PBKeyboardActivity.d().a(i);
      }
    while (true)
    {
      if (PBKeyboardActivity.n(this.a))
      {
        String str1 = localEditable.toString();
        Handler localHandler = PBKeyboardActivity.h();
        if (localHandler == null)
          break;
        Message localMessage = new Message();
        localMessage.what = 1;
        Bundle localBundle = new Bundle();
        localBundle.putString("KeyString", str1);
        localMessage.setData(localBundle);
        localHandler.sendMessage(localMessage);
        break;
        if (paramInt == -1)
        {
          if (PBKeyboardActivity.i(this.a) == PBKeyboardActivity.e())
          {
            PBKeyboardActivity.a(this.a, PBKeyboardActivity.j(this.a));
            PBKeyboardActivity.e(this.a).setKeyboard(PBKeyboardActivity.j(this.a));
            PBKeyboardActivity.a(this.a, PBKeyboardActivity.e());
            continue;
          }
          if (PBKeyboardActivity.i(this.a) == PBKeyboardActivity.f())
          {
            PBKeyboardActivity.e(this.a).setKeyboard(PBKeyboardActivity.k(this.a));
            PBKeyboardActivity.a(this.a, PBKeyboardActivity.g());
            continue;
          }
          if (PBKeyboardActivity.i(this.a) != PBKeyboardActivity.g())
            continue;
          PBKeyboardActivity.e(this.a).setKeyboard(PBKeyboardActivity.l(this.a));
          PBKeyboardActivity.a(this.a, PBKeyboardActivity.f());
          continue;
        }
        if (paramInt != -2)
          break label517;
        if (PBKeyboardActivity.i(this.a) == PBKeyboardActivity.e())
        {
          PBKeyboardActivity.e(this.a).setKeyboard(PBKeyboardActivity.l(this.a));
          PBKeyboardActivity.a(this.a, PBKeyboardActivity.f());
          continue;
        }
      }
      else
      {
        break;
      }
      if ((PBKeyboardActivity.i(this.a) != PBKeyboardActivity.c()) || (PBKeyboardActivity.b(this.a) != null));
      try
      {
        int j = d.a(PBKeyboardActivity.c(this.a), "string", "cmbkb_back");
        String str2 = this.a.getResources().getString(j);
        PBKeyboardActivity.b(this.a).setText(str2);
        label440: PBKeyboardActivity.b(this.a).setOnClickListener(PBKeyboardActivity.m(this.a));
        PBKeyboardActivity.e(this.a).setKeyboard(PBKeyboardActivity.j(this.a));
        PBKeyboardActivity.a(this.a, PBKeyboardActivity.e());
        continue;
        PBKeyboardActivity.e(this.a).setKeyboard(PBKeyboardActivity.j(this.a));
        PBKeyboardActivity.a(this.a, PBKeyboardActivity.e());
        continue;
        label517: if (paramInt == 57419)
        {
          if (i <= 0)
            continue;
          PBKeyboardActivity.g(this.a).setSelection(i - 1);
          continue;
        }
        if (paramInt == 57421)
        {
          if (i >= PBKeyboardActivity.g(this.a).length())
            continue;
          PBKeyboardActivity.g(this.a).setSelection(i + 1);
          continue;
        }
        if (paramInt == 128)
        {
          if ((PBKeyboardActivity.h(this.a)) && (PBKeyboardActivity.d() != null))
          {
            PBKeyboardActivity.d().a(i, "€");
            localEditable.insert(i, "*");
            continue;
          }
          localEditable.insert(i, "€");
          continue;
        }
        if ((PBKeyboardActivity.h(this.a)) && (PBKeyboardActivity.d() != null))
        {
          PBKeyboardActivity.d().a(i, Character.toString((char)paramInt));
          localEditable.insert(i, "*");
          continue;
        }
        localEditable.insert(i, Character.toString((char)paramInt));
      }
      catch (Exception localException)
      {
        break label440;
      }
    }
  }

  public final void onPress(int paramInt)
  {
  }

  public final void onRelease(int paramInt)
  {
  }

  public final void onText(CharSequence paramCharSequence)
  {
  }

  public final void swipeDown()
  {
  }

  public final void swipeLeft()
  {
  }

  public final void swipeRight()
  {
  }

  public final void swipeUp()
  {
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.ui.c
 * JD-Core Version:    0.6.0
 */