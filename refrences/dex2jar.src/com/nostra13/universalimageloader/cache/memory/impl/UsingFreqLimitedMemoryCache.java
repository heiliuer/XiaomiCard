package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UsingFreqLimitedMemoryCache extends LimitedMemoryCache
{
  private final Map<Bitmap, Integer> usingCounts = Collections.synchronizedMap(new HashMap());

  public UsingFreqLimitedMemoryCache(int paramInt)
  {
    super(paramInt);
  }

  public void clear()
  {
    this.usingCounts.clear();
    super.clear();
  }

  protected Reference<Bitmap> createReference(Bitmap paramBitmap)
  {
    return new WeakReference(paramBitmap);
  }

  public Bitmap get(String paramString)
  {
    Bitmap localBitmap = super.get(paramString);
    if (localBitmap != null)
    {
      Integer localInteger = (Integer)this.usingCounts.get(localBitmap);
      if (localInteger != null)
        this.usingCounts.put(localBitmap, Integer.valueOf(1 + localInteger.intValue()));
    }
    return localBitmap;
  }

  protected int getSize(Bitmap paramBitmap)
  {
    return paramBitmap.getRowBytes() * paramBitmap.getHeight();
  }

  public boolean put(String paramString, Bitmap paramBitmap)
  {
    int i = 0;
    if (super.put(paramString, paramBitmap))
    {
      this.usingCounts.put(paramBitmap, Integer.valueOf(0));
      i = 1;
    }
    return i;
  }

  public Bitmap remove(String paramString)
  {
    Bitmap localBitmap = super.get(paramString);
    if (localBitmap != null)
      this.usingCounts.remove(localBitmap);
    return super.remove(paramString);
  }

  protected Bitmap removeNext()
  {
    Object localObject1 = null;
    Bitmap localBitmap = null;
    Set localSet = this.usingCounts.entrySet();
    synchronized (this.usingCounts)
    {
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (localBitmap == null)
        {
          localBitmap = (Bitmap)localEntry.getKey();
          localObject1 = (Integer)localEntry.getValue();
          continue;
        }
        Integer localInteger = (Integer)localEntry.getValue();
        if (localInteger.intValue() >= ((Integer)localObject1).intValue())
          continue;
        localObject1 = localInteger;
        localBitmap = (Bitmap)localEntry.getKey();
      }
      this.usingCounts.remove(localBitmap);
      return localBitmap;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache
 * JD-Core Version:    0.6.0
 */