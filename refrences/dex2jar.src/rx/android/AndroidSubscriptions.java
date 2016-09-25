package rx.android;

import android.os.Looper;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public final class AndroidSubscriptions
{
  private AndroidSubscriptions()
  {
    throw new AssertionError("No instances");
  }

  public static Subscription unsubscribeInUiThread(Action0 paramAction0)
  {
    return Subscriptions.create(new Action0(paramAction0)
    {
      public void call()
      {
        if (Looper.getMainLooper() == Looper.myLooper())
          AndroidSubscriptions.this.call();
        while (true)
        {
          return;
          Scheduler.Worker localWorker = AndroidSchedulers.mainThread().createWorker();
          localWorker.schedule(new Action0(localWorker)
          {
            public void call()
            {
              AndroidSubscriptions.this.call();
              this.val$inner.unsubscribe();
            }
          });
        }
      }
    });
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.AndroidSubscriptions
 * JD-Core Version:    0.6.0
 */