package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class FuzzyKeyMemoryCache
  implements MemoryCache
{
  private final MemoryCache cache;
  private final Comparator<String> keyComparator;

  public FuzzyKeyMemoryCache(MemoryCache paramMemoryCache, Comparator<String> paramComparator)
  {
    this.cache = paramMemoryCache;
    this.keyComparator = paramComparator;
  }

  public void clear()
  {
    this.cache.clear();
  }

  public Bitmap get(String paramString)
  {
    return this.cache.get(paramString);
  }

  public Collection<String> keys()
  {
    return this.cache.keys();
  }

  public boolean put(String paramString, Bitmap paramBitmap)
  {
    MemoryCache localMemoryCache = this.cache;
    monitorenter;
    Object localObject1 = null;
    try
    {
      Iterator localIterator = this.cache.keys().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (this.keyComparator.compare(paramString, str) != 0)
          continue;
        localObject1 = str;
      }
      if (localObject1 != null)
        this.cache.remove(localObject1);
      return this.cache.put(paramString, paramBitmap);
    }
    finally
    {
      monitorexit;
    }
    throw localObject2;
  }

  public Bitmap remove(String paramString)
  {
    return this.cache.remove(paramString);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.memory.impl.FuzzyKeyMemoryCache
 * JD-Core Version:    0.6.0
 */