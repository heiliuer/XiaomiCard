package com.miui.tsmclient;

import android.content.Context;
import com.miui.tsmclient.util.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import miui.external.Application;
import miui.external.ApplicationDelegate;

public class App extends Application
{
  private static final String APP_ID = "2882303761517368855";
  private static final String APP_KEY = "5281736870855";

  public ApplicationDelegate onCreateApplicationDelegate()
  {
    return new ApplicationDelegate(null);
  }

  private static class ApplicationDelegate extends ApplicationDelegate
  {
    public static void initImageLoader(Context paramContext)
    {
      ImageLoaderConfiguration.Builder localBuilder = new ImageLoaderConfiguration.Builder(paramContext);
      localBuilder.threadPriority(5);
      localBuilder.diskCacheFileNameGenerator(new Md5FileNameGenerator());
      localBuilder.diskCacheSize(83886080);
      localBuilder.tasksProcessingOrder(QueueProcessingType.LIFO);
      localBuilder.defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).build());
      ImageLoader.getInstance().init(localBuilder.build());
    }

    public void onCreate()
    {
      super.onCreate();
      MiStatInterface.initialize(this, "2882303761517368855", "5281736870855", null);
      MiStatInterface.setUploadPolicy(4, 600000L);
      initImageLoader(getApplicationContext());
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.App
 * JD-Core Version:    0.6.0
 */