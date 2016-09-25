package rx.internal.schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.util.RxThreadFactory;

public final class GenericScheduledExecutorService
  implements SchedulerLifecycle
{
  public static final GenericScheduledExecutorService INSTANCE;
  static final ScheduledExecutorService NONE;
  private static final RxThreadFactory THREAD_FACTORY = new RxThreadFactory("RxScheduledExecutorPool-");
  private static final String THREAD_NAME_PREFIX = "RxScheduledExecutorPool-";
  private final AtomicReference<ScheduledExecutorService> executor = new AtomicReference(NONE);

  static
  {
    INSTANCE = new GenericScheduledExecutorService();
    NONE = Executors.newScheduledThreadPool(0);
    NONE.shutdownNow();
  }

  private GenericScheduledExecutorService()
  {
    start();
  }

  public static ScheduledExecutorService getInstance()
  {
    return (ScheduledExecutorService)INSTANCE.executor.get();
  }

  public void shutdown()
  {
    ScheduledExecutorService localScheduledExecutorService = (ScheduledExecutorService)this.executor.get();
    if (localScheduledExecutorService == NONE);
    while (true)
    {
      return;
      if (!this.executor.compareAndSet(localScheduledExecutorService, NONE))
        break;
      NewThreadWorker.deregisterExecutor(localScheduledExecutorService);
      localScheduledExecutorService.shutdownNow();
    }
  }

  public void start()
  {
    int i = Runtime.getRuntime().availableProcessors();
    if (i > 4)
      i /= 2;
    if (i > 8)
      i = 8;
    ScheduledExecutorService localScheduledExecutorService = Executors.newScheduledThreadPool(i, THREAD_FACTORY);
    if (this.executor.compareAndSet(NONE, localScheduledExecutorService))
      if ((!NewThreadWorker.tryEnableCancelPolicy(localScheduledExecutorService)) && ((localScheduledExecutorService instanceof ScheduledThreadPoolExecutor)))
        NewThreadWorker.registerExecutor((ScheduledThreadPoolExecutor)localScheduledExecutorService);
    while (true)
    {
      return;
      localScheduledExecutorService.shutdownNow();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.schedulers.GenericScheduledExecutorService
 * JD-Core Version:    0.6.0
 */