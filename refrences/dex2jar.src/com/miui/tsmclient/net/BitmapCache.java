package com.miui.tsmclient.net;

import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache
  implements ImageLoader.ImageCache
{
  LruCache<String, Bitmap> mCache = new LruCache((int)(Runtime.getRuntime().maxMemory() / 1024L) / 8)
  {
    protected int sizeOf(String paramString, Bitmap paramBitmap)
    {
      return paramBitmap.getByteCount() / 1024;
    }
  };

  public Bitmap getBitmap(String paramString)
  {
    return (Bitmap)this.mCache.get(paramString);
  }

  public void putBitmap(String paramString, Bitmap paramBitmap)
  {
    this.mCache.put(paramString, paramBitmap);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.BitmapCache
 * JD-Core Version:    0.6.0
 */