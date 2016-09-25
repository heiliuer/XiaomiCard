package rx.internal.util;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

public final class PlatformDependent
{
  private static final int ANDROID_API_VERSION = resolveAndroidApiVersion();
  public static final int ANDROID_API_VERSION_IS_NOT_ANDROID;
  private static final boolean IS_ANDROID;

  static
  {
    if (ANDROID_API_VERSION != 0);
    for (boolean bool = true; ; bool = false)
    {
      IS_ANDROID = bool;
      return;
    }
  }

  public static int getAndroidApiVersion()
  {
    return ANDROID_API_VERSION;
  }

  static ClassLoader getSystemClassLoader()
  {
    if (System.getSecurityManager() == null);
    for (ClassLoader localClassLoader = ClassLoader.getSystemClassLoader(); ; localClassLoader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
    {
      public ClassLoader run()
      {
        return ClassLoader.getSystemClassLoader();
      }
    }))
      return localClassLoader;
  }

  public static boolean isAndroid()
  {
    return IS_ANDROID;
  }

  private static int resolveAndroidApiVersion()
  {
    try
    {
      int j = ((Integer)Class.forName("android.os.Build$VERSION", true, getSystemClassLoader()).getField("SDK_INT").get(null)).intValue();
      i = j;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = 0;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.PlatformDependent
 * JD-Core Version:    0.6.0
 */