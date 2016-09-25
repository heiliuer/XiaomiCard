package rx.schedulers;

import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.internal.schedulers.NewThreadWorker;
import rx.internal.util.RxThreadFactory;

public final class NewThreadScheduler extends Scheduler
{
  private static final NewThreadScheduler INSTANCE;
  private static final RxThreadFactory THREAD_FACTORY = new RxThreadFactory("RxNewThreadScheduler-");
  private static final String THREAD_NAME_PREFIX = "RxNewThreadScheduler-";

  static
  {
    INSTANCE = new NewThreadScheduler();
  }

  static NewThreadScheduler instance()
  {
    return INSTANCE;
  }

  public Scheduler.Worker createWorker()
  {
    return new NewThreadWorker(THREAD_FACTORY);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.schedulers.NewThreadScheduler
 * JD-Core Version:    0.6.0
 */