package rx;

import java.util.concurrent.TimeUnit;
import rx.functions.Action0;
import rx.subscriptions.MultipleAssignmentSubscription;

public abstract class Scheduler
{
  public abstract Worker createWorker();

  public long now()
  {
    return System.currentTimeMillis();
  }

  public static abstract class Worker
    implements Subscription
  {
    public long now()
    {
      return System.currentTimeMillis();
    }

    public abstract Subscription schedule(Action0 paramAction0);

    public abstract Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit);

    public Subscription schedulePeriodically(Action0 paramAction0, long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      long l1 = paramTimeUnit.toNanos(paramLong2);
      long l2 = TimeUnit.MILLISECONDS.toNanos(now()) + paramTimeUnit.toNanos(paramLong1);
      MultipleAssignmentSubscription localMultipleAssignmentSubscription1 = new MultipleAssignmentSubscription();
      1 local1 = new Action0(localMultipleAssignmentSubscription1, paramAction0, l2, l1)
      {
        long count = 0L;

        public void call()
        {
          if (!this.val$mas.isUnsubscribed())
          {
            this.val$action.call();
            long l1 = this.val$startInNanos;
            long l2 = 1L + this.count;
            this.count = l2;
            long l3 = l1 + l2 * this.val$periodInNanos;
            this.val$mas.set(Scheduler.Worker.this.schedule(this, l3 - TimeUnit.MILLISECONDS.toNanos(Scheduler.Worker.this.now()), TimeUnit.NANOSECONDS));
          }
        }
      };
      MultipleAssignmentSubscription localMultipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
      localMultipleAssignmentSubscription1.set(localMultipleAssignmentSubscription2);
      localMultipleAssignmentSubscription2.set(schedule(local1, paramLong1, paramTimeUnit));
      return localMultipleAssignmentSubscription1;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.Scheduler
 * JD-Core Version:    0.6.0
 */