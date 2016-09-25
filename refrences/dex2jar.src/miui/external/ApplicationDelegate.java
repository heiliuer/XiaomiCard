package miui.external;

import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.ContextWrapper;
import android.content.res.Configuration;

public abstract class ApplicationDelegate extends ContextWrapper
  implements ComponentCallbacks2
{
  private Application d;

  public ApplicationDelegate()
  {
    super(null);
  }

  void a(Application paramApplication)
  {
    this.d = paramApplication;
    attachBaseContext(paramApplication);
  }

  public Application getApplication()
  {
    return this.d;
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    this.d.a(paramConfiguration);
  }

  public void onCreate()
  {
    this.d.d();
  }

  public void onLowMemory()
  {
    this.d.f();
  }

  public void onTerminate()
  {
    this.d.e();
  }

  public void onTrimMemory(int paramInt)
  {
    this.d.a(paramInt);
  }

  public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks paramActivityLifecycleCallbacks)
  {
    this.d.registerActivityLifecycleCallbacks(paramActivityLifecycleCallbacks);
  }

  public void registerComponentCallbacks(ComponentCallbacks paramComponentCallbacks)
  {
    this.d.registerComponentCallbacks(paramComponentCallbacks);
  }

  public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks paramActivityLifecycleCallbacks)
  {
    this.d.unregisterActivityLifecycleCallbacks(paramActivityLifecycleCallbacks);
  }

  public void unregisterComponentCallbacks(ComponentCallbacks paramComponentCallbacks)
  {
    this.d.unregisterComponentCallbacks(paramComponentCallbacks);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     miui.external.ApplicationDelegate
 * JD-Core Version:    0.6.0
 */