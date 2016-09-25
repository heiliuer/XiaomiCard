package com.nostra13.universalimageloader.utils;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class MemoryCacheUtils
{
  private static final String URI_AND_SIZE_SEPARATOR = "_";
  private static final String WIDTH_AND_HEIGHT_SEPARATOR = "x";

  public static Comparator<String> createFuzzyKeyComparator()
  {
    return new Comparator()
    {
      public int compare(String paramString1, String paramString2)
      {
        return paramString1.substring(0, paramString1.lastIndexOf("_")).compareTo(paramString2.substring(0, paramString2.lastIndexOf("_")));
      }
    };
  }

  public static List<String> findCacheKeysForImageUri(String paramString, MemoryCache paramMemoryCache)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramMemoryCache.keys().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!str.startsWith(paramString))
        continue;
      localArrayList.add(str);
    }
    return localArrayList;
  }

  public static List<Bitmap> findCachedBitmapsForImageUri(String paramString, MemoryCache paramMemoryCache)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramMemoryCache.keys().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!str.startsWith(paramString))
        continue;
      localArrayList.add(paramMemoryCache.get(str));
    }
    return localArrayList;
  }

  public static String generateKey(String paramString, ImageSize paramImageSize)
  {
    return paramString + "_" + paramImageSize.getWidth() + "x" + paramImageSize.getHeight();
  }

  public static void removeFromCache(String paramString, MemoryCache paramMemoryCache)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = paramMemoryCache.keys().iterator();
    while (localIterator1.hasNext())
    {
      String str = (String)localIterator1.next();
      if (!str.startsWith(paramString))
        continue;
      localArrayList.add(str);
    }
    Iterator localIterator2 = localArrayList.iterator();
    while (localIterator2.hasNext())
      paramMemoryCache.remove((String)localIterator2.next());
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.utils.MemoryCacheUtils
 * JD-Core Version:    0.6.0
 */