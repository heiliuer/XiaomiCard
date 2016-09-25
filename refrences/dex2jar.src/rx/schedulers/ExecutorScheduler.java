package rx.schedulers;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.schedulers.GenericScheduledExecutorService;
import rx.internal.schedulers.ScheduledAction;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.MultipleAssignmentSubscription;
import rx.subscriptions.Subscriptions;

final class ExecutorScheduler extends Scheduler
{
  final Executor executor;

  public ExecutorScheduler(Executor paramExecutor)
  {
    this.executor = paramExecutor;
  }

  public Scheduler.Worker createWorker()
  {
    return new ExecutorSchedulerWorker(this.executor);
  }

  static final class ExecutorSchedulerWorker extends Scheduler.Worker
    implements Runnable
  {
    final Executor executor;
    final ConcurrentLinkedQueue<ScheduledAction> queue;
    final CompositeSubscription tasks;
    final AtomicInteger wip;

    public ExecutorSchedulerWorker(Executor paramExecutor)
    {
      this.executor = paramExecutor;
      this.queue = new ConcurrentLinkedQueue();
      this.wip = new AtomicInteger();
      this.tasks = new CompositeSubscription();
    }

    public boolean isUnsubscribed()
    {
      return this.tasks.isUnsubscribed();
    }

    public void run()
    {
      do
      {
        ScheduledAction localScheduledAction = (ScheduledAction)this.queue.poll();
        if (localScheduledAction.isUnsubscribed())
          continue;
        localScheduledAction.run();
      }
      while (this.wip.decrementAndGet() > 0);
    }

    public Subscription schedule(Action0 paramAction0)
    {
      Object localObject;
      if (isUnsubscribed())
        localObject = Subscriptions.unsubscribed();
      while (true)
      {
        return localObject;
        localObject = new ScheduledAction(paramAction0, this.tasks);
        this.tasks.add((Subscription)localObject);
        this.queue.offer(localObject);
        if (this.wip.getAndIncrement() != 0)
          continue;
        try
        {
          this.executor.execute(this);
        }
        catch (RejectedExecutionException localRejectedExecutionException)
        {
          this.tasks.remove((Subscription)localObject);
          this.wip.decrementAndGet();
          RxJavaPlugins.getInstance().getErrorHandler().handleError(localRejectedExecutionException);
        }
      }
      throw localRejectedExecutionException;
    }

    public Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
    {
      if (paramLong <= 0L);
      for (Subscription localSubscription = schedule(paramAction0); ; localSubscription = Subscriptions.unsubscribed())
      {
        return localSubscription;
        if (!isUnsubscribed())
          break;
      }
      if ((this.executor instanceof ScheduledExecutorService));
      for (ScheduledExecutorService localScheduledExecutorService = (ScheduledExecutorService)this.executor; ; localScheduledExecutorService = GenericScheduledExecutorService.getInstance())
        while (true)
        {
          MultipleAssignmentSubscription localMultipleAssignmentSubscription1 = new MultipleAssignmentSubscription();
          MultipleAssignmentSubscription localMultipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
          localMultipleAssignmentSubscription2.set(localMultipleAssignmentSubscription1);
          this.tasks.add(localMultipleAssignmentSubscription2);
          localSubscription = Subscriptions.create(new Action0(localMultipleAssignmentSubscription2)
          {
            public void call()
            {
              ExecutorScheduler.ExecutorSchedulerWorker.this.tasks.remove(this.val$mas);
            }
          });
          ScheduledAction localScheduledAction = new ScheduledAction(new Action0(localMultipleAssignmentSubscription2, paramAction0, localSubscription)
          {
            public void call()
            {
              if (this.val$mas.isUnsubscribed());
              while (true)
              {
                return;
                Subscription localSubscription = ExecutorScheduler.ExecutorSchedulerWorker.this.schedule(this.val$action);
                this.val$mas.set(localSubscription);
                if (localSubscription.getClass() != ScheduledAction.class)
                  continue;
                ((ScheduledAction)localSubscription).add(this.val$removeMas);
              }
            }
          });
          localMultipleAssignmentSubscription1.set(localScheduledAction);
          try
          {
            localScheduledAction.add(localScheduledExecutorService.schedule(localScheduledAction, paramLong, paramTimeUnit));
          }
          catch (RejectedExecutionException localRejectedExecutionException)
          {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(localRejectedExecutionException);
            throw localRejectedExecutionException;
          }
        }
    }

    public void unsubscribe()
    {
      this.tasks.unsubscribe();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.schedulers.ExecutorScheduler
 * JD-Core Version:    0.6.0
 */