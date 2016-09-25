package com.miui.tsmclient.net;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton
{
  private static Context mContext;
  private static VolleySingleton mInstance;
  private ImageLoader mImageLoader;
  private RequestQueue mRequestQueue;

  private VolleySingleton(Context paramContext)
  {
    mContext = paramContext;
    this.mRequestQueue = getRequestQueue();
    this.mImageLoader = new ImageLoader(this.mRequestQueue, new BitmapCache());
  }

  /** @deprecated */
  public static VolleySingleton getInstance(Context paramContext)
  {
    monitorenter;
    try
    {
      if (mInstance == null)
        mInstance = new VolleySingleton(paramContext);
      VolleySingleton localVolleySingleton = mInstance;
      monitorexit;
      return localVolleySingleton;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public <T> void addToRequestQueue(Request<T> paramRequest)
  {
    getRequestQueue().add(paramRequest);
  }

  public ImageLoader getImageLoader()
  {
    return this.mImageLoader;
  }

  public RequestQueue getRequestQueue()
  {
    if (this.mRequestQueue == null)
      this.mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
    return this.mRequestQueue;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.VolleySingleton
 * JD-Core Version:    0.6.0
 */