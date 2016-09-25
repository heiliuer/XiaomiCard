package rx.android.schedulers;

import android.os.Handler;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.schedulers.ScheduledAction;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

@Deprecated
public class HandlerThreadScheduler extends Scheduler
{
  private final Handler handler;

  @Deprecated
  public HandlerThreadScheduler(Handler paramHandler)
  {
    this.handler = paramHandler;
  }

  public Scheduler.Worker createWorker()
  {
    return new InnerHandlerThreadScheduler(this.handler);
  }

  private static class InnerHandlerThreadScheduler extends Scheduler.Worker
  {
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    private final Handler handler;

    public InnerHandlerThreadScheduler(Handler paramHandler)
    {
      this.handler = paramHandler;
    }

    public boolean isUnsubscribed()
    {
      return this.compositeSubscription.isUnsubscribed();
    }

    public Subscription schedule(Action0 paramAction0)
    {
      return schedule(paramAction0, 0L, TimeUnit.MILLISECONDS);
    }

    public Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
    {
      ScheduledAction localScheduledAction = new ScheduledAction(paramAction0);
      localScheduledAction.add(Subscriptions.create(new Action0(localScheduledAction)
      {
        public void call()
        {
          HandlerThreadScheduler.InnerHandlerThreadScheduler.this.handler.removeCallbacks(this.val$scheduledAction);
        }
      }));
      localScheduledAction.addParent(this.compositeSubscription);
      this.compositeSubscription.add(localScheduledAction);
      this.handler.postDelayed(localScheduledAction, paramTimeUnit.toMillis(paramLong));
      return localScheduledAction;
    }

    public void unsubscribe()
    {
      this.compositeSubscription.unsubscribe();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.schedulers.HandlerThreadScheduler
 * JD-Core Version:    0.6.0
 */