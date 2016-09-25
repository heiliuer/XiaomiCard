package rx.schedulers;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.schedulers.NewThreadWorker;
import rx.internal.schedulers.ScheduledAction;
import rx.internal.schedulers.SchedulerLifecycle;
import rx.internal.util.RxThreadFactory;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

final class CachedThreadScheduler extends Scheduler
  implements SchedulerLifecycle
{
  private static final RxThreadFactory EVICTOR_THREAD_FACTORY;
  private static final String EVICTOR_THREAD_NAME_PREFIX = "RxCachedWorkerPoolEvictor-";
  private static final long KEEP_ALIVE_TIME = 60L;
  private static final TimeUnit KEEP_ALIVE_UNIT;
  static final CachedWorkerPool NONE;
  static final ThreadWorker SHUTDOWN_THREADWORKER;
  private static final RxThreadFactory WORKER_THREAD_FACTORY = new RxThreadFactory("RxCachedThreadScheduler-");
  private static final String WORKER_THREAD_NAME_PREFIX = "RxCachedThreadScheduler-";
  final AtomicReference<CachedWorkerPool> pool = new AtomicReference(NONE);

  static
  {
    EVICTOR_THREAD_FACTORY = new RxThreadFactory("RxCachedWorkerPoolEvictor-");
    KEEP_ALIVE_UNIT = TimeUnit.SECONDS;
    SHUTDOWN_THREADWORKER = new ThreadWorker(new RxThreadFactory("RxCachedThreadSchedulerShutdown-"));
    SHUTDOWN_THREADWORKER.unsubscribe();
    NONE = new CachedWorkerPool(0L, null);
    NONE.shutdown();
  }

  public CachedThreadScheduler()
  {
    start();
  }

  public Scheduler.Worker createWorker()
  {
    return new EventLoopWorker((CachedWorkerPool)this.pool.get());
  }

  public void shutdown()
  {
    CachedWorkerPool localCachedWorkerPool = (CachedWorkerPool)this.pool.get();
    if (localCachedWorkerPool == NONE);
    while (true)
    {
      return;
      if (!this.pool.compareAndSet(localCachedWorkerPool, NONE))
        break;
      localCachedWorkerPool.shutdown();
    }
  }

  public void start()
  {
    CachedWorkerPool localCachedWorkerPool = new CachedWorkerPool(60L, KEEP_ALIVE_UNIT);
    if (!this.pool.compareAndSet(NONE, localCachedWorkerPool))
      localCachedWorkerPool.shutdown();
  }

  private static final class ThreadWorker extends NewThreadWorker
  {
    private long expirationTime = 0L;

    ThreadWorker(ThreadFactory paramThreadFactory)
    {
      super();
    }

    public long getExpirationTime()
    {
      return this.expirationTime;
    }

    public void setExpirationTime(long paramLong)
    {
      this.expirationTime = paramLong;
    }
  }

  private static final class EventLoopWorker extends Scheduler.Worker
  {
    static final AtomicIntegerFieldUpdater<EventLoopWorker> ONCE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(EventLoopWorker.class, "once");
    private final CompositeSubscription innerSubscription = new CompositeSubscription();
    volatile int once;
    private final CachedThreadScheduler.CachedWorkerPool pool;
    private final CachedThreadScheduler.ThreadWorker threadWorker;

    EventLoopWorker(CachedThreadScheduler.CachedWorkerPool paramCachedWorkerPool)
    {
      this.pool = paramCachedWorkerPool;
      this.threadWorker = paramCachedWorkerPool.get();
    }

    public boolean isUnsubscribed()
    {
      return this.innerSubscription.isUnsubscribed();
    }

    public Subscription schedule(Action0 paramAction0)
    {
      return schedule(paramAction0, 0L, null);
    }

    public Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
    {
      Object localObject;
      if (this.innerSubscription.isUnsubscribed())
        localObject = Subscriptions.unsubscribed();
      while (true)
      {
        return localObject;
        localObject = this.threadWorker.scheduleActual(paramAction0, paramLong, paramTimeUnit);
        this.innerSubscription.add((Subscription)localObject);
        ((ScheduledAction)localObject).addParent(this.innerSubscription);
      }
    }

    public void unsubscribe()
    {
      if (ONCE_UPDATER.compareAndSet(this, 0, 1))
        this.pool.release(this.threadWorker);
      this.innerSubscription.unsubscribe();
    }
  }

  private static final class CachedWorkerPool
  {
    private final CompositeSubscription allWorkers;
    private final ScheduledExecutorService evictorService;
    private final Future<?> evictorTask;
    private final ConcurrentLinkedQueue<CachedThreadScheduler.ThreadWorker> expiringWorkerQueue;
    private final long keepAliveTime;

    CachedWorkerPool(long paramLong, TimeUnit paramTimeUnit)
    {
      long l;
      if (paramTimeUnit != null)
        l = paramTimeUnit.toNanos(paramLong);
      while (true)
      {
        this.keepAliveTime = l;
        this.expiringWorkerQueue = new ConcurrentLinkedQueue();
        this.allWorkers = new CompositeSubscription();
        ScheduledExecutorService localScheduledExecutorService = null;
        ScheduledFuture localScheduledFuture = null;
        if (paramTimeUnit != null)
        {
          localScheduledExecutorService = Executors.newScheduledThreadPool(1, CachedThreadScheduler.EVICTOR_THREAD_FACTORY);
          NewThreadWorker.tryEnableCancelPolicy(localScheduledExecutorService);
          localScheduledFuture = localScheduledExecutorService.scheduleWithFixedDelay(new Runnable()
          {
            public void run()
            {
              CachedThreadScheduler.CachedWorkerPool.this.evictExpiredWorkers();
            }
          }
          , this.keepAliveTime, this.keepAliveTime, TimeUnit.NANOSECONDS);
        }
        this.evictorService = localScheduledExecutorService;
        this.evictorTask = localScheduledFuture;
        return;
        l = 0L;
      }
    }

    void evictExpiredWorkers()
    {
      if (!this.expiringWorkerQueue.isEmpty())
      {
        long l = now();
        Iterator localIterator = this.expiringWorkerQueue.iterator();
        while (localIterator.hasNext())
        {
          CachedThreadScheduler.ThreadWorker localThreadWorker = (CachedThreadScheduler.ThreadWorker)localIterator.next();
          if (localThreadWorker.getExpirationTime() > l)
            break;
          if (!this.expiringWorkerQueue.remove(localThreadWorker))
            continue;
          this.allWorkers.remove(localThreadWorker);
        }
      }
    }

    CachedThreadScheduler.ThreadWorker get()
    {
      Object localObject;
      if (this.allWorkers.isUnsubscribed())
        localObject = CachedThreadScheduler.SHUTDOWN_THREADWORKER;
      while (true)
      {
        return localObject;
        while (true)
          if (!this.expiringWorkerQueue.isEmpty())
          {
            localObject = (CachedThreadScheduler.ThreadWorker)this.expiringWorkerQueue.poll();
            if (localObject == null)
              continue;
            break;
          }
        CachedThreadScheduler.ThreadWorker localThreadWorker = new CachedThreadScheduler.ThreadWorker(CachedThreadScheduler.WORKER_THREAD_FACTORY);
        this.allWorkers.add(localThreadWorker);
        localObject = localThreadWorker;
      }
    }

    long now()
    {
      return System.nanoTime();
    }

    void release(CachedThreadScheduler.ThreadWorker paramThreadWorker)
    {
      paramThreadWorker.setExpirationTime(now() + this.keepAliveTime);
      this.expiringWorkerQueue.offer(paramThreadWorker);
    }

    void shutdown()
    {
      try
      {
        if (this.evictorTask != null)
          this.evictorTask.cancel(true);
        if (this.evictorService != null)
          this.evictorService.shutdownNow();
        return;
      }
      finally
      {
        this.allWorkers.unsubscribe();
      }
      throw localObject;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.schedulers.CachedThreadScheduler
 * JD-Core Version:    0.6.0
 */