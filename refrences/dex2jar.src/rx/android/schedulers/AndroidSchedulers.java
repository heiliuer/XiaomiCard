package rx.android.schedulers;

import android.os.Handler;
import android.os.Looper;
import rx.Scheduler;

public final class AndroidSchedulers
{
  private static final Scheduler MAIN_THREAD_SCHEDULER = new HandlerThreadScheduler(new Handler(Looper.getMainLooper()));

  private AndroidSchedulers()
  {
    throw new AssertionError("No instances");
  }

  public static Scheduler handlerThread(Handler paramHandler)
  {
    return new HandlerThreadScheduler(paramHandler);
  }

  public static Scheduler mainThread()
  {
    return MAIN_THREAD_SCHEDULER;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.schedulers.AndroidSchedulers
 * JD-Core Version:    0.6.0
 */