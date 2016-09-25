package rx.internal.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.functions.Action0;
import rx.internal.schedulers.SchedulerLifecycle;
import rx.internal.util.unsafe.MpmcArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.schedulers.Schedulers;

public abstract class ObjectPool<T>
  implements SchedulerLifecycle
{
  private final int maxSize;
  private final int minSize;
  private Queue<T> pool;
  private final AtomicReference<Scheduler.Worker> schedulerWorker;
  private final long validationInterval;

  public ObjectPool()
  {
    this(0, 0, 67L);
  }

  private ObjectPool(int paramInt1, int paramInt2, long paramLong)
  {
    this.minSize = paramInt1;
    this.maxSize = paramInt2;
    this.validationInterval = paramLong;
    this.schedulerWorker = new AtomicReference();
    initialize(paramInt1);
    start();
  }

  private void initialize(int paramInt)
  {
    if (UnsafeAccess.isUnsafeAvailable());
    for (this.pool = new MpmcArrayQueue(Math.max(this.maxSize, 1024)); ; this.pool = new ConcurrentLinkedQueue())
      for (int i = 0; i < paramInt; i++)
        this.pool.add(createObject());
  }

  public T borrowObject()
  {
    Object localObject = this.pool.poll();
    if (localObject == null)
      localObject = createObject();
    return localObject;
  }

  protected abstract T createObject();

  public void returnObject(T paramT)
  {
    if (paramT == null);
    while (true)
    {
      return;
      this.pool.offer(paramT);
    }
  }

  public void shutdown()
  {
    Scheduler.Worker localWorker = (Scheduler.Worker)this.schedulerWorker.getAndSet(null);
    if (localWorker != null)
      localWorker.unsubscribe();
  }

  public void start()
  {
    Scheduler.Worker localWorker = Schedulers.computation().createWorker();
    if (this.schedulerWorker.compareAndSet(null, localWorker))
      localWorker.schedulePeriodically(new Action0()
      {
        public void call()
        {
          int i = ObjectPool.this.pool.size();
          if (i < ObjectPool.this.minSize)
          {
            int m = ObjectPool.this.maxSize - i;
            for (int n = 0; n < m; n++)
              ObjectPool.this.pool.add(ObjectPool.this.createObject());
          }
          if (i > ObjectPool.this.maxSize)
          {
            int j = i - ObjectPool.this.maxSize;
            for (int k = 0; k < j; k++)
              ObjectPool.this.pool.poll();
          }
        }
      }
      , this.validationInterval, this.validationInterval, TimeUnit.SECONDS);
    while (true)
    {
      return;
      localWorker.unsubscribe();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.ObjectPool
 * JD-Core Version:    0.6.0
 */