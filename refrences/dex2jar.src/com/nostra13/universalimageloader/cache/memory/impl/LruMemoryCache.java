package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class LruMemoryCache
  implements MemoryCache
{
  private final LinkedHashMap<String, Bitmap> map;
  private final int maxSize;
  private int size;

  public LruMemoryCache(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("maxSize <= 0");
    this.maxSize = paramInt;
    this.map = new LinkedHashMap(0, 0.75F, true);
  }

  private int sizeOf(String paramString, Bitmap paramBitmap)
  {
    return paramBitmap.getRowBytes() * paramBitmap.getHeight();
  }

  // ERROR //
  private void trimToSize(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 48	com/nostra13/universalimageloader/cache/memory/impl/LruMemoryCache:size	I
    //   6: iflt +20 -> 26
    //   9: aload_0
    //   10: getfield 34	com/nostra13/universalimageloader/cache/memory/impl/LruMemoryCache:map	Ljava/util/LinkedHashMap;
    //   13: invokevirtual 52	java/util/LinkedHashMap:isEmpty	()Z
    //   16: ifeq +48 -> 64
    //   19: aload_0
    //   20: getfield 48	com/nostra13/universalimageloader/cache/memory/impl/LruMemoryCache:size	I
    //   23: ifeq +41 -> 64
    //   26: new 54	java/lang/IllegalStateException
    //   29: dup
    //   30: new 56	java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial 57	java/lang/StringBuilder:<init>	()V
    //   37: aload_0
    //   38: invokevirtual 61	java/lang/Object:getClass	()Ljava/lang/Class;
    //   41: invokevirtual 67	java/lang/Class:getName	()Ljava/lang/String;
    //   44: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc 73
    //   49: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: invokespecial 77	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   58: athrow
    //   59: astore_2
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_2
    //   63: athrow
    //   64: aload_0
    //   65: getfield 48	com/nostra13/universalimageloader/cache/memory/impl/LruMemoryCache:size	I
    //   68: iload_1
    //   69: if_icmple +13 -> 82
    //   72: aload_0
    //   73: getfield 34	com/nostra13/universalimageloader/cache/memory/impl/LruMemoryCache:map	Ljava/util/LinkedHashMap;
    //   76: invokevirtual 52	java/util/LinkedHashMap:isEmpty	()Z
    //   79: ifeq +8 -> 87
    //   82: aload_0
    //   83: monitorexit
    //   84: goto +87 -> 171
    //   87: aload_0
    //   88: getfield 34	com/nostra13/universalimageloader/cache/memory/impl/LruMemoryCache:map	Ljava/util/LinkedHashMap;
    //   91: invokevirtual 81	java/util/LinkedHashMap:entrySet	()Ljava/util/Set;
    //   94: invokeinterface 87 1 0
    //   99: invokeinterface 93 1 0
    //   104: checkcast 95	java/util/Map$Entry
    //   107: astore_3
    //   108: aload_3
    //   109: ifnonnull +8 -> 117
    //   112: aload_0
    //   113: monitorexit
    //   114: goto +57 -> 171
    //   117: aload_3
    //   118: invokeinterface 98 1 0
    //   123: checkcast 100	java/lang/String
    //   126: astore 4
    //   128: aload_3
    //   129: invokeinterface 103 1 0
    //   134: checkcast 38	android/graphics/Bitmap
    //   137: astore 5
    //   139: aload_0
    //   140: getfield 34	com/nostra13/universalimageloader/cache/memory/impl/LruMemoryCache:map	Ljava/util/LinkedHashMap;
    //   143: aload 4
    //   145: invokevirtual 107	java/util/LinkedHashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   148: pop
    //   149: aload_0
    //   150: aload_0
    //   151: getfield 48	com/nostra13/universalimageloader/cache/memory/impl/LruMemoryCache:size	I
    //   154: aload_0
    //   155: aload 4
    //   157: aload 5
    //   159: invokespecial 109	com/nostra13/universalimageloader/cache/memory/impl/LruMemoryCache:sizeOf	(Ljava/lang/String;Landroid/graphics/Bitmap;)I
    //   162: isub
    //   163: putfield 48	com/nostra13/universalimageloader/cache/memory/impl/LruMemoryCache:size	I
    //   166: aload_0
    //   167: monitorexit
    //   168: goto -168 -> 0
    //   171: return
    //
    // Exception table:
    //   from	to	target	type
    //   2	62	59	finally
    //   64	168	59	finally
  }

  public void clear()
  {
    trimToSize(-1);
  }

  public final Bitmap get(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException("key == null");
    monitorenter;
    try
    {
      Bitmap localBitmap = (Bitmap)this.map.get(paramString);
      monitorexit;
      return localBitmap;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public Collection<String> keys()
  {
    monitorenter;
    try
    {
      HashSet localHashSet = new HashSet(this.map.keySet());
      monitorexit;
      return localHashSet;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final boolean put(String paramString, Bitmap paramBitmap)
  {
    if ((paramString == null) || (paramBitmap == null))
      throw new NullPointerException("key == null || value == null");
    monitorenter;
    try
    {
      this.size += sizeOf(paramString, paramBitmap);
      Bitmap localBitmap = (Bitmap)this.map.put(paramString, paramBitmap);
      if (localBitmap != null)
        this.size -= sizeOf(paramString, localBitmap);
      monitorexit;
      trimToSize(this.maxSize);
      return true;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final Bitmap remove(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException("key == null");
    monitorenter;
    try
    {
      Bitmap localBitmap = (Bitmap)this.map.remove(paramString);
      if (localBitmap != null)
        this.size -= sizeOf(paramString, localBitmap);
      monitorexit;
      return localBitmap;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public final String toString()
  {
    monitorenter;
    try
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.maxSize);
      String str = String.format("LruCache[maxSize=%d]", arrayOfObject);
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
 * JD-Core Version:    0.6.0
 */