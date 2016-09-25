package com.miui.tsmclient.util;

import android.util.Log;

public class LogUtils
{
  public static final String TAG = "TSMClient";

  public static void d(String paramString)
  {
    Log.d("TSMClient", paramString);
  }

  public static void d(String paramString1, String paramString2)
  {
    Log.d("TSMClient." + paramString1, paramString2);
  }

  public static void e(String paramString)
  {
    Log.e("TSMClient", paramString);
  }

  public static void e(String paramString, Exception paramException)
  {
    Log.e("TSMClient", paramString, paramException);
  }

  public static void i(String paramString)
  {
    Log.i("TSMClient", paramString);
  }

  public static void i(String paramString1, String paramString2)
  {
    Log.i("TSMClient." + paramString1, paramString2);
  }

  public static void v(String paramString)
  {
    Log.d("TSMClient", paramString);
  }

  public static void w(String paramString)
  {
    Log.w("TSMClient", paramString);
  }

  public static void w(String paramString1, String paramString2)
  {
    Log.w("TSMClient." + paramString1, paramString2);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.LogUtils
 * JD-Core Version:    0.6.0
 */