package miui.external;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Application extends android.app.Application
  implements SdkConstants
{
  private static final String PACKAGE_NAME = "com.miui.core";
  private boolean a;
  private boolean b;
  private ApplicationDelegate c;

  public Application()
  {
    if (!a());
    while (true)
    {
      return;
      if (b())
      {
        this.a = true;
        continue;
      }
    }
  }

  private void a(String paramString, int paramInt)
  {
    Log.e("miuisdk", "MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support. phase: " + paramString + " code: " + paramInt);
    b.a(SdkConstants.SdkError.GENERIC);
  }

  private void a(Throwable paramThrowable)
  {
    while (true)
    {
      if ((paramThrowable == null) || (paramThrowable.getCause() == null));
      do
      {
        Log.e("miuisdk", "MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support.", paramThrowable);
        b.a(SdkConstants.SdkError.GENERIC);
        return;
        if (!(paramThrowable instanceof InvocationTargetException))
          continue;
        paramThrowable = paramThrowable.getCause();
        break;
      }
      while (!(paramThrowable instanceof ExceptionInInitializerError));
      paramThrowable = paramThrowable.getCause();
    }
  }

  private boolean a()
  {
    int i = 0;
    try
    {
      if ((!c.isMiuiSystem()) && (!d.load(c.getApkPath(null, "com.miui.core", "miui"), null, c.getLibPath(null, "com.miui.core"), Application.class.getClassLoader())))
      {
        b.a(SdkConstants.SdkError.NO_SDK);
        return i;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        a(localThrowable);
        continue;
        i = 1;
      }
    }
  }

  private boolean b()
  {
    try
    {
      HashMap localHashMap = new HashMap();
      Class localClass = a.g();
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = android.app.Application.class;
      arrayOfClass[1] = Map.class;
      Method localMethod = localClass.getMethod("initialize", arrayOfClass);
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this;
      arrayOfObject[1] = localHashMap;
      int j = ((Integer)localMethod.invoke(null, arrayOfObject)).intValue();
      if (j != 0)
      {
        a("initialize", j);
        i = 0;
        return i;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        a(localThrowable);
        int i = 0;
        continue;
        i = 1;
      }
    }
  }

  private boolean c()
  {
    try
    {
      HashMap localHashMap = new HashMap();
      Class localClass = a.g();
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Map.class;
      Method localMethod = localClass.getMethod("start", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localHashMap;
      int j = ((Integer)localMethod.invoke(null, arrayOfObject)).intValue();
      if (j == 1)
      {
        b.a(SdkConstants.SdkError.LOW_SDK_VERSION);
        i = 0;
        break label111;
      }
      if (j != 0)
      {
        a("start", j);
        i = 0;
      }
    }
    catch (Throwable localThrowable)
    {
      a(localThrowable);
      i = 0;
    }
    int i = 1;
    label111: return i;
  }

  final void a(int paramInt)
  {
    super.onTrimMemory(paramInt);
  }

  final void a(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
    if (!this.a);
    while (true)
    {
      return;
      if (c())
      {
        this.c = onCreateApplicationDelegate();
        if (this.c != null)
          this.c.a(this);
        this.b = true;
        continue;
      }
    }
  }

  final void d()
  {
    super.onCreate();
  }

  final void e()
  {
    super.onTerminate();
  }

  final void f()
  {
    super.onLowMemory();
  }

  public final ApplicationDelegate getApplicationDelegate()
  {
    return this.c;
  }

  public final void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.c != null)
      this.c.onConfigurationChanged(paramConfiguration);
    while (true)
    {
      return;
      a(paramConfiguration);
    }
  }

  public final void onCreate()
  {
    if (!this.b);
    while (true)
    {
      return;
      if (this.c != null)
      {
        this.c.onCreate();
        continue;
      }
      d();
    }
  }

  public ApplicationDelegate onCreateApplicationDelegate()
  {
    return null;
  }

  public final void onLowMemory()
  {
    if (this.c != null)
      this.c.onLowMemory();
    while (true)
    {
      return;
      f();
    }
  }

  public final void onTerminate()
  {
    if (this.c != null)
      this.c.onTerminate();
    while (true)
    {
      return;
      e();
    }
  }

  public final void onTrimMemory(int paramInt)
  {
    if (this.c != null)
      this.c.onTrimMemory(paramInt);
    while (true)
    {
      return;
      a(paramInt);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     miui.external.Application
 * JD-Core Version:    0.6.0
 */