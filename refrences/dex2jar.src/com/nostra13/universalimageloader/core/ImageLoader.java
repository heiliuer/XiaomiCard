package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import java.util.concurrent.locks.ReentrantLock;

public class ImageLoader
{
  private static final String ERROR_INIT_CONFIG_WITH_NULL = "ImageLoader configuration can not be initialized with null";
  private static final String ERROR_NOT_INIT = "ImageLoader must be init with configuration before using";
  private static final String ERROR_WRONG_ARGUMENTS = "Wrong arguments were passed to displayImage() method (ImageView reference must not be null)";
  static final String LOG_DESTROY = "Destroy ImageLoader";
  static final String LOG_INIT_CONFIG = "Initialize ImageLoader with configuration";
  static final String LOG_LOAD_IMAGE_FROM_MEMORY_CACHE = "Load image from memory cache [%s]";
  public static final String TAG = ImageLoader.class.getSimpleName();
  private static final String WARNING_RE_INIT_CONFIG = "Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.";
  private static volatile ImageLoader instance;
  private ImageLoaderConfiguration configuration;
  private ImageLoadingListener defaultListener = new SimpleImageLoadingListener();
  private ImageLoaderEngine engine;

  private void checkConfiguration()
  {
    if (this.configuration == null)
      throw new IllegalStateException("ImageLoader must be init with configuration before using");
  }

  private static Handler defineHandler(DisplayImageOptions paramDisplayImageOptions)
  {
    Handler localHandler = paramDisplayImageOptions.getHandler();
    if (paramDisplayImageOptions.isSyncLoading());
    for (localHandler = null; ; localHandler = new Handler())
      do
        return localHandler;
      while ((localHandler != null) || (Looper.myLooper() != Looper.getMainLooper()));
  }

  public static ImageLoader getInstance()
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new ImageLoader();
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void cancelDisplayTask(ImageView paramImageView)
  {
    this.engine.cancelDisplayTaskFor(new ImageViewAware(paramImageView));
  }

  public void cancelDisplayTask(ImageAware paramImageAware)
  {
    this.engine.cancelDisplayTaskFor(paramImageAware);
  }

  @Deprecated
  public void clearDiscCache()
  {
    clearDiskCache();
  }

  public void clearDiskCache()
  {
    checkConfiguration();
    this.configuration.diskCache.clear();
  }

  public void clearMemoryCache()
  {
    checkConfiguration();
    this.configuration.memoryCache.clear();
  }

  public void denyNetworkDownloads(boolean paramBoolean)
  {
    this.engine.denyNetworkDownloads(paramBoolean);
  }

  public void destroy()
  {
    if (this.configuration != null)
      L.d("Destroy ImageLoader", new Object[0]);
    stop();
    this.configuration.diskCache.close();
    this.engine = null;
    this.configuration = null;
  }

  public void displayImage(String paramString, ImageView paramImageView)
  {
    displayImage(paramString, new ImageViewAware(paramImageView), null, null, null);
  }

  public void displayImage(String paramString, ImageView paramImageView, DisplayImageOptions paramDisplayImageOptions)
  {
    displayImage(paramString, new ImageViewAware(paramImageView), paramDisplayImageOptions, null, null);
  }

  public void displayImage(String paramString, ImageView paramImageView, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener)
  {
    displayImage(paramString, paramImageView, paramDisplayImageOptions, paramImageLoadingListener, null);
  }

  public void displayImage(String paramString, ImageView paramImageView, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener, ImageLoadingProgressListener paramImageLoadingProgressListener)
  {
    displayImage(paramString, new ImageViewAware(paramImageView), paramDisplayImageOptions, paramImageLoadingListener, paramImageLoadingProgressListener);
  }

  public void displayImage(String paramString, ImageView paramImageView, ImageSize paramImageSize)
  {
    displayImage(paramString, new ImageViewAware(paramImageView), null, paramImageSize, null, null);
  }

  public void displayImage(String paramString, ImageView paramImageView, ImageLoadingListener paramImageLoadingListener)
  {
    displayImage(paramString, new ImageViewAware(paramImageView), null, paramImageLoadingListener, null);
  }

  public void displayImage(String paramString, ImageAware paramImageAware)
  {
    displayImage(paramString, paramImageAware, null, null, null);
  }

  public void displayImage(String paramString, ImageAware paramImageAware, DisplayImageOptions paramDisplayImageOptions)
  {
    displayImage(paramString, paramImageAware, paramDisplayImageOptions, null, null);
  }

  public void displayImage(String paramString, ImageAware paramImageAware, DisplayImageOptions paramDisplayImageOptions, ImageSize paramImageSize, ImageLoadingListener paramImageLoadingListener, ImageLoadingProgressListener paramImageLoadingProgressListener)
  {
    checkConfiguration();
    if (paramImageAware == null)
      throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
    if (paramImageLoadingListener == null)
      paramImageLoadingListener = this.defaultListener;
    if (paramDisplayImageOptions == null)
      paramDisplayImageOptions = this.configuration.defaultDisplayImageOptions;
    if (TextUtils.isEmpty(paramString))
    {
      this.engine.cancelDisplayTaskFor(paramImageAware);
      View localView3 = paramImageAware.getWrappedView();
      paramImageLoadingListener.onLoadingStarted(paramString, localView3);
      if (paramDisplayImageOptions.shouldShowImageForEmptyUri())
      {
        paramImageAware.setImageDrawable(paramDisplayImageOptions.getImageForEmptyUri(this.configuration.resources));
        View localView4 = paramImageAware.getWrappedView();
        paramImageLoadingListener.onLoadingComplete(paramString, localView4, null);
      }
    }
    while (true)
    {
      return;
      paramImageAware.setImageDrawable(null);
      break;
      if (paramImageSize == null)
        paramImageSize = ImageSizeUtils.defineTargetSizeForView(paramImageAware, this.configuration.getMaxImageSize());
      String str = MemoryCacheUtils.generateKey(paramString, paramImageSize);
      this.engine.prepareDisplayTaskFor(paramImageAware, str);
      View localView1 = paramImageAware.getWrappedView();
      paramImageLoadingListener.onLoadingStarted(paramString, localView1);
      Bitmap localBitmap = this.configuration.memoryCache.get(str);
      if ((localBitmap != null) && (!localBitmap.isRecycled()))
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = str;
        L.d("Load image from memory cache [%s]", arrayOfObject);
        if (paramDisplayImageOptions.shouldPostProcess())
        {
          ReentrantLock localReentrantLock2 = this.engine.getLockForUri(paramString);
          ImageLoadingInfo localImageLoadingInfo2 = new ImageLoadingInfo(paramString, paramImageAware, paramImageSize, str, paramDisplayImageOptions, paramImageLoadingListener, paramImageLoadingProgressListener, localReentrantLock2);
          ProcessAndDisplayImageTask localProcessAndDisplayImageTask = new ProcessAndDisplayImageTask(this.engine, localBitmap, localImageLoadingInfo2, defineHandler(paramDisplayImageOptions));
          if (paramDisplayImageOptions.isSyncLoading())
          {
            localProcessAndDisplayImageTask.run();
            continue;
          }
          this.engine.submit(localProcessAndDisplayImageTask);
          continue;
        }
        paramDisplayImageOptions.getDisplayer().display(localBitmap, paramImageAware, LoadedFrom.MEMORY_CACHE);
        View localView2 = paramImageAware.getWrappedView();
        paramImageLoadingListener.onLoadingComplete(paramString, localView2, localBitmap);
        continue;
      }
      if (paramDisplayImageOptions.shouldShowImageOnLoading())
        paramImageAware.setImageDrawable(paramDisplayImageOptions.getImageOnLoading(this.configuration.resources));
      LoadAndDisplayImageTask localLoadAndDisplayImageTask;
      while (true)
      {
        ReentrantLock localReentrantLock1 = this.engine.getLockForUri(paramString);
        ImageLoadingInfo localImageLoadingInfo1 = new ImageLoadingInfo(paramString, paramImageAware, paramImageSize, str, paramDisplayImageOptions, paramImageLoadingListener, paramImageLoadingProgressListener, localReentrantLock1);
        localLoadAndDisplayImageTask = new LoadAndDisplayImageTask(this.engine, localImageLoadingInfo1, defineHandler(paramDisplayImageOptions));
        if (!paramDisplayImageOptions.isSyncLoading())
          break label466;
        localLoadAndDisplayImageTask.run();
        break;
        if (!paramDisplayImageOptions.isResetViewBeforeLoading())
          continue;
        paramImageAware.setImageDrawable(null);
      }
      label466: this.engine.submit(localLoadAndDisplayImageTask);
    }
  }

  public void displayImage(String paramString, ImageAware paramImageAware, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener)
  {
    displayImage(paramString, paramImageAware, paramDisplayImageOptions, paramImageLoadingListener, null);
  }

  public void displayImage(String paramString, ImageAware paramImageAware, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener, ImageLoadingProgressListener paramImageLoadingProgressListener)
  {
    displayImage(paramString, paramImageAware, paramDisplayImageOptions, null, paramImageLoadingListener, paramImageLoadingProgressListener);
  }

  public void displayImage(String paramString, ImageAware paramImageAware, ImageLoadingListener paramImageLoadingListener)
  {
    displayImage(paramString, paramImageAware, null, paramImageLoadingListener, null);
  }

  @Deprecated
  public DiskCache getDiscCache()
  {
    return getDiskCache();
  }

  public DiskCache getDiskCache()
  {
    checkConfiguration();
    return this.configuration.diskCache;
  }

  public String getLoadingUriForView(ImageView paramImageView)
  {
    return this.engine.getLoadingUriForView(new ImageViewAware(paramImageView));
  }

  public String getLoadingUriForView(ImageAware paramImageAware)
  {
    return this.engine.getLoadingUriForView(paramImageAware);
  }

  public MemoryCache getMemoryCache()
  {
    checkConfiguration();
    return this.configuration.memoryCache;
  }

  public void handleSlowNetwork(boolean paramBoolean)
  {
    this.engine.handleSlowNetwork(paramBoolean);
  }

  /** @deprecated */
  public void init(ImageLoaderConfiguration paramImageLoaderConfiguration)
  {
    monitorenter;
    if (paramImageLoaderConfiguration == null)
      try
      {
        throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
      }
      finally
      {
        monitorexit;
      }
    if (this.configuration == null)
    {
      L.d("Initialize ImageLoader with configuration", new Object[0]);
      this.engine = new ImageLoaderEngine(paramImageLoaderConfiguration);
      this.configuration = paramImageLoaderConfiguration;
    }
    while (true)
    {
      monitorexit;
      return;
      L.w("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
    }
  }

  public boolean isInited()
  {
    if (this.configuration != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void loadImage(String paramString, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener)
  {
    loadImage(paramString, null, paramDisplayImageOptions, paramImageLoadingListener, null);
  }

  public void loadImage(String paramString, ImageSize paramImageSize, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener)
  {
    loadImage(paramString, paramImageSize, paramDisplayImageOptions, paramImageLoadingListener, null);
  }

  public void loadImage(String paramString, ImageSize paramImageSize, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener, ImageLoadingProgressListener paramImageLoadingProgressListener)
  {
    checkConfiguration();
    if (paramImageSize == null)
      paramImageSize = this.configuration.getMaxImageSize();
    if (paramDisplayImageOptions == null)
      paramDisplayImageOptions = this.configuration.defaultDisplayImageOptions;
    displayImage(paramString, new NonViewAware(paramString, paramImageSize, ViewScaleType.CROP), paramDisplayImageOptions, paramImageLoadingListener, paramImageLoadingProgressListener);
  }

  public void loadImage(String paramString, ImageSize paramImageSize, ImageLoadingListener paramImageLoadingListener)
  {
    loadImage(paramString, paramImageSize, null, paramImageLoadingListener, null);
  }

  public void loadImage(String paramString, ImageLoadingListener paramImageLoadingListener)
  {
    loadImage(paramString, null, null, paramImageLoadingListener, null);
  }

  public Bitmap loadImageSync(String paramString)
  {
    return loadImageSync(paramString, null, null);
  }

  public Bitmap loadImageSync(String paramString, DisplayImageOptions paramDisplayImageOptions)
  {
    return loadImageSync(paramString, null, paramDisplayImageOptions);
  }

  public Bitmap loadImageSync(String paramString, ImageSize paramImageSize)
  {
    return loadImageSync(paramString, paramImageSize, null);
  }

  public Bitmap loadImageSync(String paramString, ImageSize paramImageSize, DisplayImageOptions paramDisplayImageOptions)
  {
    if (paramDisplayImageOptions == null)
      paramDisplayImageOptions = this.configuration.defaultDisplayImageOptions;
    DisplayImageOptions localDisplayImageOptions = new DisplayImageOptions.Builder().cloneFrom(paramDisplayImageOptions).syncLoading(true).build();
    SyncImageLoadingListener localSyncImageLoadingListener = new SyncImageLoadingListener(null);
    loadImage(paramString, paramImageSize, localDisplayImageOptions, localSyncImageLoadingListener);
    return localSyncImageLoadingListener.getLoadedBitmap();
  }

  public void pause()
  {
    this.engine.pause();
  }

  public void resume()
  {
    this.engine.resume();
  }

  public void setDefaultLoadingListener(ImageLoadingListener paramImageLoadingListener)
  {
    if (paramImageLoadingListener == null)
      paramImageLoadingListener = new SimpleImageLoadingListener();
    this.defaultListener = paramImageLoadingListener;
  }

  public void stop()
  {
    this.engine.stop();
  }

  private static class SyncImageLoadingListener extends SimpleImageLoadingListener
  {
    private Bitmap loadedImage;

    public Bitmap getLoadedBitmap()
    {
      return this.loadedImage;
    }

    public void onLoadingComplete(String paramString, View paramView, Bitmap paramBitmap)
    {
      this.loadedImage = paramBitmap;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.ImageLoader
 * JD-Core Version:    0.6.0
 */