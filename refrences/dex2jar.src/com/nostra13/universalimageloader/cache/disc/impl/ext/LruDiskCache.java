package com.nostra13.universalimageloader.cache.disc.impl.ext;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import com.nostra13.universalimageloader.utils.L;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LruDiskCache
  implements DiskCache
{
  public static final int DEFAULT_BUFFER_SIZE = 32768;
  public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
  public static final int DEFAULT_COMPRESS_QUALITY = 100;
  private static final String ERROR_ARG_NEGATIVE = " argument must be positive number";
  private static final String ERROR_ARG_NULL = " argument must be not null";
  protected int bufferSize = 32768;
  protected DiskLruCache cache;
  protected Bitmap.CompressFormat compressFormat = DEFAULT_COMPRESS_FORMAT;
  protected int compressQuality = 100;
  protected final FileNameGenerator fileNameGenerator;
  private File reserveCacheDir;

  public LruDiskCache(File paramFile, FileNameGenerator paramFileNameGenerator, long paramLong)
    throws IOException
  {
    this(paramFile, null, paramFileNameGenerator, paramLong, 0);
  }

  public LruDiskCache(File paramFile1, File paramFile2, FileNameGenerator paramFileNameGenerator, long paramLong, int paramInt)
    throws IOException
  {
    if (paramFile1 == null)
      throw new IllegalArgumentException("cacheDir argument must be not null");
    if (paramLong < 0L)
      throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
    if (paramInt < 0)
      throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
    if (paramFileNameGenerator == null)
      throw new IllegalArgumentException("fileNameGenerator argument must be not null");
    if (paramLong == 0L)
      paramLong = 9223372036854775807L;
    if (paramInt == 0)
      paramInt = 2147483647;
    this.reserveCacheDir = paramFile2;
    this.fileNameGenerator = paramFileNameGenerator;
    initCache(paramFile1, paramFile2, paramLong, paramInt);
  }

  private String getKey(String paramString)
  {
    return this.fileNameGenerator.generate(paramString);
  }

  private void initCache(File paramFile1, File paramFile2, long paramLong, int paramInt)
    throws IOException
  {
    try
    {
      this.cache = DiskLruCache.open(paramFile1, 1, 1, paramLong, paramInt);
      return;
    }
    catch (IOException localIOException)
    {
      do
      {
        L.e(localIOException);
        if (paramFile2 == null)
          continue;
        initCache(paramFile2, null, paramLong, paramInt);
      }
      while (this.cache != null);
    }
    throw localIOException;
  }

  public void clear()
  {
    try
    {
      this.cache.delete();
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          initCache(this.cache.getDirectory(), this.reserveCacheDir, this.cache.getMaxSize(), this.cache.getMaxFileCount());
          return;
          localIOException1 = localIOException1;
          L.e(localIOException1);
        }
      }
      catch (IOException localIOException2)
      {
        while (true)
          L.e(localIOException2);
      }
    }
  }

  public void close()
  {
    try
    {
      this.cache.close();
      this.cache = null;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        L.e(localIOException);
    }
  }

  public File get(String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    try
    {
      DiskLruCache.Snapshot localSnapshot = this.cache.get(getKey(paramString));
      localObject2 = localSnapshot;
      if (localObject2 == null);
      while (true)
      {
        return localObject1;
        File localFile = localObject2.getFile(0);
        localObject1 = localFile;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        L.e(localIOException);
        if (localObject2 == null)
          continue;
        localObject2.close();
      }
    }
    finally
    {
      if (localObject2 != null)
        localObject2.close();
    }
    throw localObject3;
  }

  public File getDirectory()
  {
    return this.cache.getDirectory();
  }

  public boolean remove(String paramString)
  {
    try
    {
      boolean bool2 = this.cache.remove(getKey(paramString));
      bool1 = bool2;
      return bool1;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        L.e(localIOException);
        boolean bool1 = false;
      }
    }
  }

  public boolean save(String paramString, Bitmap paramBitmap)
    throws IOException
  {
    int i = 0;
    DiskLruCache.Editor localEditor = this.cache.edit(getKey(paramString));
    if (localEditor == null);
    while (true)
    {
      return i;
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localEditor.newOutputStream(0), this.bufferSize);
      try
      {
        boolean bool = paramBitmap.compress(this.compressFormat, this.compressQuality, localBufferedOutputStream);
        i = bool;
        IoUtils.closeSilently(localBufferedOutputStream);
        if (i != 0)
          localEditor.commit();
      }
      finally
      {
        IoUtils.closeSilently(localBufferedOutputStream);
      }
    }
  }

  public boolean save(String paramString, InputStream paramInputStream, IoUtils.CopyListener paramCopyListener)
    throws IOException
  {
    int i = 0;
    DiskLruCache.Editor localEditor = this.cache.edit(getKey(paramString));
    if (localEditor == null);
    while (true)
    {
      return i;
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localEditor.newOutputStream(0), this.bufferSize);
      try
      {
        boolean bool = IoUtils.copyStream(paramInputStream, localBufferedOutputStream, paramCopyListener, this.bufferSize);
        i = bool;
        IoUtils.closeSilently(localBufferedOutputStream);
        if (i != 0)
        {
          localEditor.commit();
          continue;
        }
        localEditor.abort();
      }
      finally
      {
        IoUtils.closeSilently(localBufferedOutputStream);
        if (0 != 0)
          localEditor.commit();
      }
    }
  }

  public void setBufferSize(int paramInt)
  {
    this.bufferSize = paramInt;
  }

  public void setCompressFormat(Bitmap.CompressFormat paramCompressFormat)
  {
    this.compressFormat = paramCompressFormat;
  }

  public void setCompressQuality(int paramInt)
  {
    this.compressQuality = paramInt;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache
 * JD-Core Version:    0.6.0
 */