package com.nostra13.universalimageloader.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

public final class StorageUtils
{
  private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
  private static final String INDIVIDUAL_DIR_NAME = "uil-images";

  public static File getCacheDirectory(Context paramContext)
  {
    return getCacheDirectory(paramContext, true);
  }

  public static File getCacheDirectory(Context paramContext, boolean paramBoolean)
  {
    File localFile = null;
    try
    {
      String str3 = Environment.getExternalStorageState();
      str1 = str3;
      if ((paramBoolean) && ("mounted".equals(str1)) && (hasExternalStoragePermission(paramContext)))
        localFile = getExternalCacheDir(paramContext);
      if (localFile == null)
        localFile = paramContext.getCacheDir();
      if (localFile == null)
      {
        String str2 = "/data/data/" + paramContext.getPackageName() + "/cache/";
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = str2;
        L.w("Can't define system cache directory! '%s' will be used.", arrayOfObject);
        localFile = new File(str2);
      }
      return localFile;
    }
    catch (NullPointerException localNullPointerException)
    {
      while (true)
        str1 = "";
    }
    catch (IncompatibleClassChangeError localIncompatibleClassChangeError)
    {
      while (true)
        String str1 = "";
    }
  }

  private static File getExternalCacheDir(Context paramContext)
  {
    File localFile = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), paramContext.getPackageName()), "cache");
    if (!localFile.exists())
    {
      if (localFile.mkdirs())
        break label69;
      L.w("Unable to create external cache directory", new Object[0]);
      localFile = null;
    }
    while (true)
    {
      return localFile;
      try
      {
        label69: new File(localFile, ".nomedia").createNewFile();
      }
      catch (IOException localIOException)
      {
        L.i("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
      }
    }
  }

  public static File getIndividualCacheDirectory(Context paramContext)
  {
    return getIndividualCacheDirectory(paramContext, "uil-images");
  }

  public static File getIndividualCacheDirectory(Context paramContext, String paramString)
  {
    File localFile1 = getCacheDirectory(paramContext);
    File localFile2 = new File(localFile1, paramString);
    if ((!localFile2.exists()) && (!localFile2.mkdir()))
      localFile2 = localFile1;
    return localFile2;
  }

  public static File getOwnCacheDirectory(Context paramContext, String paramString)
  {
    File localFile = null;
    if (("mounted".equals(Environment.getExternalStorageState())) && (hasExternalStoragePermission(paramContext)))
      localFile = new File(Environment.getExternalStorageDirectory(), paramString);
    if ((localFile == null) || ((!localFile.exists()) && (!localFile.mkdirs())))
      localFile = paramContext.getCacheDir();
    return localFile;
  }

  public static File getOwnCacheDirectory(Context paramContext, String paramString, boolean paramBoolean)
  {
    File localFile = null;
    if ((paramBoolean) && ("mounted".equals(Environment.getExternalStorageState())) && (hasExternalStoragePermission(paramContext)))
      localFile = new File(Environment.getExternalStorageDirectory(), paramString);
    if ((localFile == null) || ((!localFile.exists()) && (!localFile.mkdirs())))
      localFile = paramContext.getCacheDir();
    return localFile;
  }

  private static boolean hasExternalStoragePermission(Context paramContext)
  {
    if (paramContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0);
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.utils.StorageUtils
 * JD-Core Version:    0.6.0
 */