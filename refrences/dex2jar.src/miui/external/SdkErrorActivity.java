package miui.external;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SdkErrorActivity extends Activity
  implements SdkConstants
{
  private String h;
  private DialogInterface.OnClickListener i = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      paramDialogInterface.dismiss();
      SdkErrorActivity.this.finish();
      System.exit(0);
    }
  };
  private DialogInterface.OnClickListener j = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      paramDialogInterface.dismiss();
      Dialog localDialog = SdkErrorActivity.a(SdkErrorActivity.this);
      new SdkErrorActivity.a(SdkErrorActivity.this, localDialog).show(SdkErrorActivity.this.getFragmentManager(), "SdkUpdatePromptDialog");
      new AsyncTask(localDialog)
      {
        protected Boolean a(Void[] paramArrayOfVoid)
        {
          try
          {
            Thread.sleep(5000L);
            return Boolean.valueOf(SdkErrorActivity.b(SdkErrorActivity.this));
          }
          catch (InterruptedException localInterruptedException)
          {
            while (true)
              localInterruptedException.printStackTrace();
          }
        }

        protected void a(Boolean paramBoolean)
        {
          this.l.dismiss();
          if (paramBoolean.booleanValue());
          for (Dialog localDialog = SdkErrorActivity.c(SdkErrorActivity.this); ; localDialog = SdkErrorActivity.d(SdkErrorActivity.this))
          {
            new SdkErrorActivity.a(SdkErrorActivity.this, localDialog).show(SdkErrorActivity.this.getFragmentManager(), "SdkUpdateFinishDialog");
            return;
          }
        }
      }
      .execute(new Void[0]);
    }
  };

  private Dialog a(String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener)
  {
    return new AlertDialog.Builder(this).setTitle(paramString1).setMessage(paramString2).setPositiveButton(17039370, paramOnClickListener).setIcon(17301543).setCancelable(false).create();
  }

  private Dialog a(String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    return new AlertDialog.Builder(this).setTitle(paramString1).setMessage(paramString2).setPositiveButton(17039370, paramOnClickListener1).setNegativeButton(17039360, paramOnClickListener2).setIcon(17301543).setCancelable(false).create();
  }

  private Dialog h()
  {
    String str1;
    if (Locale.CHINESE.getLanguage().equals(this.h))
      str1 = "MIUI SDK发生错误";
    for (String str2 = "请重新安装MIUI SDK再运行本程序。"; ; str2 = "Please re-install MIUI SDK and then re-run this application.")
    {
      return a(str1, str2, this.i);
      str1 = "MIUI SDK encounter errors";
    }
  }

  private Dialog i()
  {
    String str1;
    if (Locale.CHINESE.getLanguage().equals(this.h))
      str1 = "没有找到MIUI SDK";
    for (String str2 = "请先安装MIUI SDK再运行本程序。"; ; str2 = "Please install MIUI SDK and then re-run this application.")
    {
      return a(str1, str2, this.i);
      str1 = "MIUI SDK not found";
    }
  }

  private Dialog j()
  {
    Dialog localDialog;
    if (!n())
    {
      String str3;
      if (Locale.CHINESE.getLanguage().equals(this.h))
        str3 = "MIUI SDK版本过低";
      for (String str4 = "请先升级MIUI SDK再运行本程序。"; ; str4 = "Please upgrade MIUI SDK and then re-run this application.")
      {
        localDialog = a(str3, str4, this.i);
        return localDialog;
        str3 = "MIUI SDK too old";
      }
    }
    String str1;
    if (Locale.CHINESE.getLanguage().equals(this.h))
      str1 = "MIUI SDK版本过低";
    for (String str2 = "请先升级MIUI SDK再运行本程序。是否现在升级？"; ; str2 = "Please upgrade MIUI SDK and then re-run this application. Upgrade now?")
    {
      localDialog = a(str1, str2, this.j, this.i);
      break;
      str1 = "MIUI SDK too old";
    }
  }

  private Dialog k()
  {
    String str1;
    if (Locale.CHINESE.getLanguage().equals(this.h))
      str1 = "MIUI SDK正在更新";
    for (String str2 = "请稍候..."; ; str2 = "Please wait...")
    {
      return ProgressDialog.show(this, str1, str2, true, false);
      str1 = "MIUI SDK updating";
    }
  }

  private Dialog l()
  {
    String str1;
    if (Locale.CHINESE.getLanguage().equals(this.h))
      str1 = "MIUI SDK更新完成";
    for (String str2 = "请重新运行本程序。"; ; str2 = "Please re-run this application.")
    {
      return a(str1, str2, this.i);
      str1 = "MIUI SDK updated";
    }
  }

  private Dialog m()
  {
    String str1;
    if (Locale.CHINESE.getLanguage().equals(this.h))
      str1 = "MIUI SDK更新失败";
    for (String str2 = "请稍后重试。"; ; str2 = "Please try it later.")
    {
      return a(str1, str2, this.i);
      str1 = "MIUI SDK update failed";
    }
  }

  private boolean n()
  {
    try
    {
      Class localClass = a.g();
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Map.class;
      Method localMethod = localClass.getMethod("supportUpdate", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = null;
      boolean bool2 = ((Boolean)localMethod.invoke(null, arrayOfObject)).booleanValue();
      bool1 = bool2;
      return bool1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        boolean bool1 = false;
      }
    }
  }

  private boolean o()
  {
    try
    {
      HashMap localHashMap = new HashMap();
      Class localClass = a.g();
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Map.class;
      Method localMethod = localClass.getMethod("update", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localHashMap;
      boolean bool2 = ((Boolean)localMethod.invoke(null, arrayOfObject)).booleanValue();
      bool1 = bool2;
      return bool1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        boolean bool1 = false;
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    setTheme(16973909);
    super.onCreate(paramBundle);
    this.h = Locale.getDefault().getLanguage();
    SdkConstants.SdkError localSdkError = null;
    Intent localIntent = getIntent();
    if (localIntent != null)
      localSdkError = (SdkConstants.SdkError)localIntent.getSerializableExtra("com.miui.sdk.error");
    if (localSdkError == null)
      localSdkError = SdkConstants.SdkError.GENERIC;
    Dialog localDialog;
    switch (3.n[localSdkError.ordinal()])
    {
    default:
      localDialog = h();
    case 1:
    case 2:
    }
    while (true)
    {
      new a(localDialog).show(getFragmentManager(), "SdkErrorPromptDialog");
      return;
      localDialog = i();
      continue;
      localDialog = j();
    }
  }

  class a extends DialogFragment
  {
    private Dialog o;

    public a(Dialog arg2)
    {
      Object localObject;
      this.o = localObject;
    }

    public Dialog onCreateDialog(Bundle paramBundle)
    {
      return this.o;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     miui.external.SdkErrorActivity
 * JD-Core Version:    0.6.0
 */