package cmb.pb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.security.Key;
import java.util.List;

public class PBKeyboardActivity extends Activity
{
  private static int E;
  private static int F;
  private static int G;
  private static int H;
  private static int I;
  private static int J;
  private static int K;
  private static PBKeyboardActivity L;
  private static f S;
  public static int c;
  private static cmb.pb.ui.cmbwidget.e i = null;
  private static Key t = null;
  private static List u = null;
  private static Handler v = null;
  private String A = null;
  private String B = null;
  private boolean C = false;
  private int D = 0;
  private final String M = "PBKeyboardActivity";
  private View.OnClickListener N = new a(this);
  private View.OnClickListener O = new b(this);
  private KeyboardView.OnKeyboardActionListener P = new c(this);
  private View.OnFocusChangeListener Q = new d(this);
  private View.OnTouchListener R = new e(this);
  public boolean a = false;
  public boolean b = false;
  private float d = 0.0F;
  private float e = 0.0F;
  private Context f;
  private Activity g;
  private KeyboardView h;
  private int j = 0;
  private Keyboard k;
  private Keyboard l;
  private Keyboard m;
  private Keyboard n;
  private Keyboard o;
  private Keyboard p;
  private Keyboard q;
  private EditText r;
  private TextView s = null;
  private int w = 0;
  private boolean x = false;
  private int y = 0;
  private String z = null;

  static
  {
    E = 0;
    F = 1;
    G = 2;
    H = 3;
    I = 4;
    J = 5;
    K = 6;
    L = null;
    c = 9555;
    S = null;
  }

  public static void a(Handler paramHandler)
  {
    v = paramHandler;
  }

  public static void a(cmb.pb.ui.cmbwidget.e parame)
  {
    i = parame;
  }

  private static boolean a(String paramString)
  {
    if ("abcdefghijklmnopqrstuvwxyz".indexOf(paramString.toLowerCase()) >= 0);
    for (int i1 = 1; ; i1 = 0)
      return i1;
  }

  public static PBKeyboardActivity b()
  {
    return L;
  }

  public final void a()
  {
    this.B = null;
    this.r.setText("");
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(cmb.pb.a.d.a(this, "layout", "cmbkeyboard"));
    Intent localIntent = getIntent();
    this.w = localIntent.getIntExtra("KeyboardType", 0);
    this.x = localIntent.getBooleanExtra("IsPassword", false);
    this.y = localIntent.getIntExtra("Length", 0);
    this.z = localIntent.getStringExtra("Hint");
    this.A = localIntent.getStringExtra("Label");
    this.B = localIntent.getStringExtra("OldText");
    this.C = localIntent.getBooleanExtra("UseHandler", false);
    L = this;
    this.g = this;
    this.f = this;
    this.r = ((EditText)findViewById(cmb.pb.a.d.a(this, "id", "edit_cmbinput")));
    Log.v("PBKeyboardActivity", "android.os.Build.VERSION.SDK_INT:" + Build.VERSION.SDK_INT);
    if (Build.VERSION.SDK_INT <= 10)
      this.r.setInputType(0);
    while (true)
    {
      label198: View localView;
      label267: label572: label962: Context localContext;
      if (this.x)
      {
        this.r.setInputType(129);
        if (this.y > 0)
        {
          EditText localEditText1 = this.r;
          InputFilter[] arrayOfInputFilter = new InputFilter[1];
          arrayOfInputFilter[0] = new InputFilter.LengthFilter(this.y);
          localEditText1.setFilters(arrayOfInputFilter);
        }
        if ((this.z == null) || (this.z.length() <= 0))
          break label1249;
        this.r.setHint(this.z);
        if ((this.A != null) && (this.A.length() > 0))
        {
          TextView localTextView2 = (TextView)findViewById(cmb.pb.a.d.a(this, "id", "cmbkb_tvLabel_id"));
          if (localTextView2 != null)
            localTextView2.setText(this.A);
        }
        if (this.B != null)
        {
          this.r.setText(this.B);
          Editable localEditable = this.r.getText();
          if ((localEditable instanceof Spannable))
            Selection.setSelection((Spannable)localEditable, localEditable.length());
        }
        this.r.setOnFocusChangeListener(this.Q);
        this.r.setOnTouchListener(this.R);
        if (this.C)
        {
          TextView localTextView1 = (TextView)findViewById(cmb.pb.a.d.a(this, "id", "cmbkb_tvLabel"));
          if (localTextView1 != null)
            localTextView1.setVisibility(8);
          if (this.r != null)
            this.r.setVisibility(8);
          String str2 = "";
          if (this.B != null)
            str2 = this.B;
          Handler localHandler = v;
          if (localHandler != null)
          {
            Message localMessage = new Message();
            localMessage.what = 1;
            Bundle localBundle = new Bundle();
            localBundle.putString("KeyString", str2);
            localMessage.setData(localBundle);
            localHandler.sendMessage(localMessage);
          }
        }
        localView = findViewById(cmb.pb.a.d.a(this, "id", "cmbkb_safeSign"));
        if (localView != null)
        {
          if ((this.w != 2) && (this.w != 3) && (this.w != 4))
            break label1289;
          localView.setVisibility(8);
        }
        this.s = ((TextView)findViewById(cmb.pb.a.d.a(this, "id", "cmbkb_tvComplete")));
        if (this.s != null)
        {
          if ((this.w != 2) && (this.w != 3) && (this.w != 4))
            break label1298;
          this.s.setVisibility(0);
          this.s.setOnClickListener(this.N);
        }
        label643: int i1 = cmb.pb.a.d.a(this, "xml", "cmbkb_number");
        int i2 = cmb.pb.a.d.a(this, "xml", "cmbkb_number_symbols");
        int i3 = cmb.pb.a.d.a(this, "xml", "cmbkb_number_with_dot");
        int i4 = cmb.pb.a.d.a(this, "xml", "cmbkb_number_with_x");
        int i5 = cmb.pb.a.d.a(this, "xml", "cmbkb_number_with_change");
        int i6 = cmb.pb.a.d.a(this, "xml", "cmbkb_symbols");
        int i7 = cmb.pb.a.d.a(this, "xml", "cmbkb_qwerty");
        this.k = new Keyboard(this.f, i1);
        this.l = new Keyboard(this.f, i2);
        this.m = new Keyboard(this.f, i3);
        this.n = new Keyboard(this.f, i4);
        this.o = new Keyboard(this.f, i5);
        this.p = new Keyboard(this.f, i6);
        this.q = new Keyboard(this.f, i7);
        cmb.pb.cmbsafe.a.a(this.k);
        cmb.pb.cmbsafe.a.a(this.l);
        cmb.pb.cmbsafe.a.a(this.m);
        cmb.pb.cmbsafe.a.a(this.n);
        cmb.pb.cmbsafe.a.a(this.o);
        int i8 = cmb.pb.a.d.a(this, "id", "cmbkeyboard_view");
        this.h = ((KeyboardView)this.g.findViewById(i8));
        this.h.setEnabled(true);
        this.h.setPreviewEnabled(false);
        this.h.setOnKeyboardActionListener(this.P);
        if (this.w != 1)
          break label1318;
        this.h.setKeyboard(this.k);
        this.D = E;
        Display localDisplay = getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.width = (int)(1.0D * localDisplay.getWidth());
        localLayoutParams.alpha = 1.0F;
        localLayoutParams.dimAmount = 0.0F;
        getWindow().setAttributes(localLayoutParams);
        getWindow().setType(2006);
        localContext = getApplicationContext();
      }
      try
      {
        while (true)
        {
          while (true)
          {
            int i9 = cmb.pb.a.d.a(localContext, "string", "cmbkb_publickey");
            String str1 = localContext.getResources().getString(i9);
            Log.v("pK", str1);
            cmb.pb.a.c.a(str1);
            return;
            getWindow().setSoftInputMode(3);
            try
            {
              while (true)
              {
                Class[] arrayOfClass2 = new Class[1];
                arrayOfClass2[0] = Boolean.TYPE;
                Method localMethod2 = EditText.class.getMethod("setSoftInputShownOnFocus", arrayOfClass2);
                localMethod2.setAccessible(true);
                EditText localEditText3 = this.r;
                Object[] arrayOfObject2 = new Object[1];
                arrayOfObject2[0] = Boolean.valueOf(false);
                localMethod2.invoke(localEditText3, arrayOfObject2);
                try
                {
                  Class[] arrayOfClass1 = new Class[1];
                  arrayOfClass1[0] = Boolean.TYPE;
                  Method localMethod1 = EditText.class.getMethod("setShowSoftInputOnFocus", arrayOfClass1);
                  localMethod1.setAccessible(true);
                  EditText localEditText2 = this.r;
                  Object[] arrayOfObject1 = new Object[1];
                  arrayOfObject1[0] = Boolean.valueOf(false);
                  localMethod1.invoke(localEditText2, arrayOfObject1);
                }
                catch (Exception localException2)
                {
                  localException2.printStackTrace();
                }
              }
            }
            catch (Exception localException1)
            {
              while (true)
                localException1.printStackTrace();
            }
          }
          this.r.setInputType(144);
          this.r.setHint("");
          break label198;
          try
          {
            label1249: int i10 = cmb.pb.a.d.a(this, "string", "cmbkb_please_input");
            String str3 = getResources().getString(i10);
            this.r.setHint(str3);
          }
          catch (Exception localException3)
          {
          }
        }
        break label267;
        label1289: localView.setVisibility(0);
        break label572;
        label1298: this.s.setOnClickListener(null);
        this.s.setVisibility(8);
        break label643;
        label1318: if (this.w == 2)
        {
          this.h.setKeyboard(this.m);
          this.D = I;
          break label962;
        }
        if (this.w == 3)
        {
          this.h.setKeyboard(this.n);
          this.D = J;
          break label962;
        }
        if (this.w == 4)
        {
          this.h.setKeyboard(this.o);
          this.D = K;
          break label962;
        }
        this.h.setKeyboard(this.q);
        this.D = F;
      }
      catch (Exception localException4)
      {
        while (true)
          localException4.printStackTrace();
      }
    }
  }

  protected void onDestroy()
  {
    if (v != null)
    {
      Message localMessage = new Message();
      localMessage.what = 2;
      Bundle localBundle = new Bundle();
      localBundle.putFloat("rawX", this.d);
      localBundle.putFloat("rawY", this.e);
      localMessage.setData(localBundle);
      v.sendMessage(localMessage);
      v = null;
    }
    super.onDestroy();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    this.d = paramMotionEvent.getRawX();
    this.e = paramMotionEvent.getRawY();
    finish();
    return true;
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if ((paramBoolean) && (S != null))
      if (this.j == 0)
        this.j = ((LinearLayout)findViewById(cmb.pb.a.d.a(this, "id", "cmbkb_contentLayout"))).getMeasuredHeight();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.ui.PBKeyboardActivity
 * JD-Core Version:    0.6.0
 */