package com.nostra13.universalimageloader.utils;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import java.io.File;

public final class DiskCacheUtils
{
  public static File findInCache(String paramString, DiskCache paramDiskCache)
  {
    File localFile = paramDiskCache.get(paramString);
    if ((localFile != null) && (localFile.exists()));
    while (true)
    {
      return localFile;
      localFile = null;
    }
  }

  public static boolean removeFromCache(String paramString, DiskCache paramDiskCache)
  {
    File localFile = paramDiskCache.get(paramString);
    if ((localFile != null) && (localFile.exists()) && (localFile.delete()));
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.utils.DiskCacheUtils
 * JD-Core Version:    0.6.0
 */