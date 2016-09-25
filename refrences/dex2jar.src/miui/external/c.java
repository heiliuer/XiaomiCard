package miui.external;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;

class c
{
  public static final String MIUI_SYSTEM_APK_NAME = "miui";

  private static PackageInfo a(Context paramContext, String paramString)
  {
    Object localObject = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramString, 128);
      localObject = localPackageInfo;
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        localNameNotFoundException.printStackTrace();
    }
  }

  private static String a(String paramString)
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = ("/data/app/" + paramString + "-1.apk");
    arrayOfString[1] = ("/data/app/" + paramString + "-2.apk");
    arrayOfString[2] = ("/data/app/" + paramString + "-1/base.apk");
    arrayOfString[3] = ("/data/app/" + paramString + "-2/base.apk");
    return a(arrayOfString);
  }

  private static String a(String paramString1, String paramString2)
  {
    String str = a(paramString1);
    if (str == null)
      str = b(paramString2);
    return str;
  }

  private static String a(String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    int j = 0;
    String str;
    if (j < i)
    {
      str = paramArrayOfString[j];
      if (!new File(str).exists());
    }
    while (true)
    {
      return str;
      j++;
      break;
      str = null;
    }
  }

  private static String b(String paramString)
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = ("/system/app/" + paramString + ".apk");
    arrayOfString[1] = ("/system/priv-app/" + paramString + ".apk");
    arrayOfString[2] = ("/system/app/" + paramString + "/" + paramString + ".apk");
    arrayOfString[3] = ("/system/priv-app/" + paramString + "/" + paramString + ".apk");
    return a(arrayOfString);
  }

  private static String c(String paramString)
  {
    return "/data/data/" + paramString + "/lib/";
  }

  public static String getApkPath(Context paramContext, String paramString1, String paramString2)
  {
    String str = null;
    if (paramContext == null)
      str = a(paramString1, paramString2);
    while (true)
    {
      return str;
      PackageInfo localPackageInfo = a(paramContext, paramString1);
      if (localPackageInfo == null)
        continue;
      str = localPackageInfo.applicationInfo.publicSourceDir;
    }
  }

  public static String getLibPath(Context paramContext, String paramString)
  {
    String str = null;
    if (paramContext == null)
      str = c(paramString);
    while (true)
    {
      return str;
      PackageInfo localPackageInfo = a(paramContext, paramString);
      if (localPackageInfo == null)
        continue;
      str = localPackageInfo.applicationInfo.nativeLibraryDir;
    }
  }

  public static boolean isMiuiSystem()
  {
    if (b("miui") != null);
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     miui.external.c
 * JD-Core Version:    0.6.0
 */