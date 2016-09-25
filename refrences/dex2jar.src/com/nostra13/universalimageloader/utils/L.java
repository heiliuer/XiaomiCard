package com.nostra13.universalimageloader.utils;

import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;

public final class L
{
  private static final String LOG_FORMAT = "%1$s\n%2$s";
  private static volatile boolean writeDebugLogs = false;
  private static volatile boolean writeLogs = true;

  public static void d(String paramString, Object[] paramArrayOfObject)
  {
    if (writeDebugLogs)
      log(3, null, paramString, paramArrayOfObject);
  }

  @Deprecated
  public static void disableLogging()
  {
    writeLogs(false);
  }

  public static void e(String paramString, Object[] paramArrayOfObject)
  {
    log(6, null, paramString, paramArrayOfObject);
  }

  public static void e(Throwable paramThrowable)
  {
    log(6, paramThrowable, null, new Object[0]);
  }

  public static void e(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    log(6, paramThrowable, paramString, paramArrayOfObject);
  }

  @Deprecated
  public static void enableLogging()
  {
    writeLogs(true);
  }

  public static void i(String paramString, Object[] paramArrayOfObject)
  {
    log(4, null, paramString, paramArrayOfObject);
  }

  private static void log(int paramInt, Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (!writeLogs);
    String str3;
    while (true)
    {
      return;
      if (paramArrayOfObject.length > 0)
        paramString = String.format(paramString, paramArrayOfObject);
      if (paramThrowable != null)
        break;
      str3 = paramString;
      Log.println(paramInt, ImageLoader.TAG, str3);
    }
    if (paramString == null);
    for (String str1 = paramThrowable.getMessage(); ; str1 = paramString)
    {
      String str2 = Log.getStackTraceString(paramThrowable);
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = str1;
      arrayOfObject[1] = str2;
      str3 = String.format("%1$s\n%2$s", arrayOfObject);
      break;
    }
  }

  public static void w(String paramString, Object[] paramArrayOfObject)
  {
    log(5, null, paramString, paramArrayOfObject);
  }

  public static void writeDebugLogs(boolean paramBoolean)
  {
    writeDebugLogs = paramBoolean;
  }

  public static void writeLogs(boolean paramBoolean)
  {
    writeLogs = paramBoolean;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.utils.L
 * JD-Core Version:    0.6.0
 */