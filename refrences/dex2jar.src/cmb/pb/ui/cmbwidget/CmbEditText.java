package cmb.pb.ui.cmbwidget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.Window;
import android.widget.EditText;
import android.widget.PopupWindow;
import cmb.pb.cmbsafe.CmbService;
import cmb.pb.ui.PBKeyboardActivity;
import java.lang.reflect.Method;

public class CmbEditText extends EditText
{
  private static String c = "CmbEditText";
  private static PopupWindow m = null;
  public boolean a = false;
  public boolean b = false;
  private int d = 0;
  private int e = 0;
  private boolean f = false;
  private boolean g = false;
  private b h = null;
  private d i = null;
  private e j = null;
  private Drawable k = null;
  private boolean l = false;
  private Activity n = null;
  private String o = "";

  public CmbEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramAttributeSet);
    d();
  }

  public CmbEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramAttributeSet);
    d();
  }

  private void a(AttributeSet paramAttributeSet)
  {
    int i1;
    int i2;
    String str1;
    String str2;
    try
    {
      i1 = paramAttributeSet.getAttributeCount();
      i2 = 0;
      break label107;
      str1 = paramAttributeSet.getAttributeName(i2);
      str2 = paramAttributeSet.getAttributeValue(i2);
      if (str1.equals("KeyBoardType"))
      {
        this.d = Integer.parseInt(str2);
        break label114;
      }
      if (str1.equals("Length"))
        this.e = Integer.parseInt(str2);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      break label113;
    }
    if (str1.equals("isPassword"))
      this.f = Boolean.parseBoolean(str2);
    label107: 
    while (i2 >= i1)
    {
      label113: return;
      label114: i2++;
    }
  }

  private void d()
  {
    try
    {
      this.h = new b(this);
      this.i = new d(this);
      if (this != null)
      {
        if (Build.VERSION.SDK_INT <= 10)
          setInputType(0);
      }
      else
      {
        setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.k = getCompoundDrawables()[2];
        if (this.k == null)
        {
          int i1 = cmb.pb.a.d.a(getContext(), "drawable", "cmbkb_emotionstore_progresscancelbtn");
          this.k = getResources().getDrawable(i1);
        }
        this.k.setBounds(0, 0, this.k.getIntrinsicWidth(), this.k.getIntrinsicHeight());
        a(false);
        setOnFocusChangeListener(new a(this, 0));
        setOnTouchListener(new c(this, 0));
        if (this.f)
          this.j = new e(this.e, "");
        return;
      }
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          ((Activity)getContext()).getWindow().setSoftInputMode(3);
          try
          {
            label182: Class[] arrayOfClass2 = new Class[1];
            arrayOfClass2[0] = Boolean.TYPE;
            Method localMethod2 = EditText.class.getMethod("setSoftInputShownOnFocus", arrayOfClass2);
            localMethod2.setAccessible(true);
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Boolean.valueOf(false);
            localMethod2.invoke(this, arrayOfObject2);
            try
            {
              label236: Class[] arrayOfClass1 = new Class[1];
              arrayOfClass1[0] = Boolean.TYPE;
              Method localMethod1 = EditText.class.getMethod("setShowSoftInputOnFocus", arrayOfClass1);
              localMethod1.setAccessible(true);
              Object[] arrayOfObject1 = new Object[1];
              arrayOfObject1[0] = Boolean.valueOf(false);
              localMethod1.invoke(this, arrayOfObject1);
            }
            catch (Exception localException4)
            {
            }
            continue;
            localException1 = localException1;
          }
          catch (Exception localException3)
          {
            break label236;
          }
        }
      }
      catch (Exception localException2)
      {
        break label182;
      }
    }
  }

  public final void a()
  {
    super.setText("");
    if ((this.f) && (this.j != null))
      this.j.c();
  }

  protected final void a(boolean paramBoolean)
  {
    if (paramBoolean);
    for (Drawable localDrawable = this.k; ; localDrawable = null)
    {
      setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], localDrawable, getCompoundDrawables()[3]);
      return;
    }
  }

  public final void b()
  {
    cmb.pb.cmbsafe.b.a(this);
    cmb.pb.cmbsafe.b.a(this);
    cmb.pb.cmbsafe.b.a("showCMBKeyboardWindow2");
    this.n.startService(new Intent(this.n, CmbService.class));
  }

  public final void c()
  {
    this.g = false;
    this.n.stopService(new Intent(this.n, CmbService.class));
  }

  public String getEnctyptText()
  {
    if ((this.f) && (this.j != null));
    for (String str = this.j.b(); ; str = "")
      return cmb.pb.a.c.a(str, this.o);
  }

  public int getInputLength()
  {
    return this.j.a();
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramCharSequence.length() > 0);
    for (boolean bool = true; ; bool = false)
    {
      a(bool);
      return;
    }
  }

  public void setInputType(int paramInt)
  {
    super.setInputType(0);
  }

  public void setRecvTouchEventActivity(Activity paramActivity)
  {
    this.n = paramActivity;
  }

  public void setSessionID(String paramString)
  {
    this.o = paramString;
  }

  public void showCMBKeyboardWindow2()
  {
    if (this.g);
    while (true)
    {
      return;
      this.g = true;
      try
      {
        setCursorVisible(true);
        setFocusable(true);
        requestFocus();
        Thread.sleep(100L);
        PBKeyboardActivity.a(this.h);
        if (this.f)
          PBKeyboardActivity.a(this.j);
        Intent localIntent = new Intent();
        localIntent.setFlags(268435456);
        localIntent.putExtra("IsPassword", this.f);
        localIntent.putExtra("Hint", getHint());
        localIntent.putExtra("KeyboardType", this.d);
        localIntent.putExtra("Length", this.e);
        localIntent.putExtra("OldText", getText().toString());
        localIntent.putExtra("UseHandler", true);
        localIntent.setClass(this.n, PBKeyboardActivity.class);
        this.n.startActivityForResult(localIntent, PBKeyboardActivity.c);
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.ui.cmbwidget.CmbEditText
 * JD-Core Version:    0.6.0
 */