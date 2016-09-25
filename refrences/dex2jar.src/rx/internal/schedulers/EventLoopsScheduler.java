package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.SubscriptionList;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public class EventLoopsScheduler extends Scheduler
  implements SchedulerLifecycle
{
  static final String KEY_MAX_THREADS = "rx.scheduler.max-computation-threads";
  static final int MAX_THREADS = 0;
  static final FixedSchedulerPool NONE;
  static final PoolWorker SHUTDOWN_WORKER;
  private static final RxThreadFactory THREAD_FACTORY = new RxThreadFactory("RxComputationThreadPool-");
  private static final String THREAD_NAME_PREFIX = "RxComputationThreadPool-";
  final AtomicReference<FixedSchedulerPool> pool = new AtomicReference(NONE);

  static
  {
    int i = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
    int j = Runtime.getRuntime().availableProcessors();
    if ((i <= 0) || (i > j));
    for (int k = j; ; k = i)
    {
      MAX_THREADS = k;
      SHUTDOWN_WORKER = new PoolWorker(new RxThreadFactory("RxComputationShutdown-"));
      SHUTDOWN_WORKER.unsubscribe();
      NONE = new FixedSchedulerPool(0);
      return;
    }
  }

  public EventLoopsScheduler()
  {
    start();
  }

  public Scheduler.Worker createWorker()
  {
    return new EventLoopWorker(((FixedSchedulerPool)this.pool.get()).getEventLoop());
  }

  public Subscription scheduleDirect(Action0 paramAction0)
  {
    return ((FixedSchedulerPool)this.pool.get()).getEventLoop().scheduleActual(paramAction0, -1L, TimeUnit.NANOSECONDS);
  }

  public void shutdown()
  {
    FixedSchedulerPool localFixedSchedulerPool = (FixedSchedulerPool)this.pool.get();
    if (localFixedSchedulerPool == NONE);
    while (true)
    {
      return;
      if (!this.pool.compareAndSet(localFixedSchedulerPool, NONE))
        break;
      localFixedSchedulerPool.shutdown();
    }
  }

  public void start()
  {
    FixedSchedulerPool localFixedSchedulerPool = new FixedSchedulerPool(MAX_THREADS);
    if (!this.pool.compareAndSet(NONE, localFixedSchedulerPool))
      localFixedSchedulerPool.shutdown();
  }

  private static final class PoolWorker extends NewThreadWorker
  {
    PoolWorker(ThreadFactory paramThreadFactory)
    {
      super();
    }
  }

  private static class EventLoopWorker extends Scheduler.Worker
  {
    private final SubscriptionList both;
    private final EventLoopsScheduler.PoolWorker poolWorker;
    private final SubscriptionList serial = new SubscriptionList();
    private final CompositeSubscription timed = new CompositeSubscription();

    EventLoopWorker(EventLoopsScheduler.PoolWorker paramPoolWorker)
    {
      Subscription[] arrayOfSubscription = new Subscription[2];
      arrayOfSubscription[0] = this.serial;
      arrayOfSubscription[1] = this.timed;
      this.both = new SubscriptionList(arrayOfSubscription);
      this.poolWorker = paramPoolWorker;
    }

    public boolean isUnsubscribed()
    {
      return this.both.isUnsubscribed();
    }

    public Subscription schedule(Action0 paramAction0)
    {
      if (isUnsubscribed());
      for (Object localObject = Subscriptions.unsubscribed(); ; localObject = this.poolWorker.scheduleActual(paramAction0, 0L, null, this.serial))
        return localObject;
    }

    public Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
    {
      if (isUnsubscribed());
      for (Object localObject = Subscriptions.unsubscribed(); ; localObject = this.poolWorker.scheduleActual(paramAction0, paramLong, paramTimeUnit, this.timed))
        return localObject;
    }

    public void unsubscribe()
    {
      this.both.unsubscribe();
    }
  }

  static final class FixedSchedulerPool
  {
    final int cores;
    final EventLoopsScheduler.PoolWorker[] eventLoops;
    long n;

    FixedSchedulerPool(int paramInt)
    {
      this.cores = paramInt;
      this.eventLoops = new EventLoopsScheduler.PoolWorker[paramInt];
      for (int i = 0; i < paramInt; i++)
        this.eventLoops[i] = new EventLoopsScheduler.PoolWorker(EventLoopsScheduler.THREAD_FACTORY);
    }

    public EventLoopsScheduler.PoolWorker getEventLoop()
    {
      int i = this.cores;
      if (i == 0);
      EventLoopsScheduler.PoolWorker[] arrayOfPoolWorker;
      long l;
      for (EventLoopsScheduler.PoolWorker localPoolWorker = EventLoopsScheduler.SHUTDOWN_WORKER; ; localPoolWorker = arrayOfPoolWorker[(int)(l % i)])
      {
        return localPoolWorker;
        arrayOfPoolWorker = this.eventLoops;
        l = this.n;
        this.n = (1L + l);
      }
    }

    public void shutdown()
    {
      EventLoopsScheduler.PoolWorker[] arrayOfPoolWorker = this.eventLoops;
      int i = arrayOfPoolWorker.length;
      for (int j = 0; j < i; j++)
        arrayOfPoolWorker[j].unsubscribe();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.schedulers.EventLoopsScheduler
 * JD-Core Version:    0.6.0
 */