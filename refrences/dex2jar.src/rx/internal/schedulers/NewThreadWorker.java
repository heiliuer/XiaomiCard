package rx.internal.schedulers;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.util.PlatformDependent;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.SubscriptionList;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public class NewThreadWorker extends Scheduler.Worker
  implements Subscription
{
  private static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> EXECUTORS = new ConcurrentHashMap();
  private static final String FREQUENCY_KEY = "rx.scheduler.jdk6.purge-frequency-millis";
  private static final AtomicReference<ScheduledExecutorService> PURGE = new AtomicReference();
  private static final String PURGE_FORCE_KEY = "rx.scheduler.jdk6.purge-force";
  public static final int PURGE_FREQUENCY = 0;
  private static final String PURGE_THREAD_PREFIX = "RxSchedulerPurge-";
  private static final Object SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED;
  private static final boolean SHOULD_TRY_ENABLE_CANCEL_POLICY;
  private static volatile Object cachedSetRemoveOnCancelPolicyMethod;
  private final ScheduledExecutorService executor;
  volatile boolean isUnsubscribed;
  private final RxJavaSchedulersHook schedulersHook;

  static
  {
    boolean bool1 = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
    int i = PlatformDependent.getAndroidApiVersion();
    if ((!bool1) && ((i == 0) || (i >= 21)));
    for (boolean bool2 = true; ; bool2 = false)
    {
      SHOULD_TRY_ENABLE_CANCEL_POLICY = bool2;
      SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED = new Object();
      return;
    }
  }

  public NewThreadWorker(ThreadFactory paramThreadFactory)
  {
    ScheduledExecutorService localScheduledExecutorService = Executors.newScheduledThreadPool(1, paramThreadFactory);
    if ((!tryEnableCancelPolicy(localScheduledExecutorService)) && ((localScheduledExecutorService instanceof ScheduledThreadPoolExecutor)))
      registerExecutor((ScheduledThreadPoolExecutor)localScheduledExecutorService);
    this.schedulersHook = RxJavaPlugins.getInstance().getSchedulersHook();
    this.executor = localScheduledExecutorService;
  }

  public static void deregisterExecutor(ScheduledExecutorService paramScheduledExecutorService)
  {
    EXECUTORS.remove(paramScheduledExecutorService);
  }

  static Method findSetRemoveOnCancelPolicyMethod(ScheduledExecutorService paramScheduledExecutorService)
  {
    Method[] arrayOfMethod = paramScheduledExecutorService.getClass().getMethods();
    int i = arrayOfMethod.length;
    int j = 0;
    Method localMethod;
    if (j < i)
    {
      localMethod = arrayOfMethod[j];
      if (localMethod.getName().equals("setRemoveOnCancelPolicy"))
      {
        Class[] arrayOfClass = localMethod.getParameterTypes();
        if ((arrayOfClass.length != 1) || (arrayOfClass[0] != Boolean.TYPE));
      }
    }
    while (true)
    {
      return localMethod;
      j++;
      break;
      localMethod = null;
    }
  }

  static void purgeExecutors()
  {
    while (true)
    {
      Iterator localIterator;
      try
      {
        localIterator = EXECUTORS.keySet().iterator();
        if (localIterator.hasNext())
        {
          ScheduledThreadPoolExecutor localScheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor)localIterator.next();
          if (localScheduledThreadPoolExecutor.isShutdown())
            break label61;
          localScheduledThreadPoolExecutor.purge();
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        Exceptions.throwIfFatal(localThrowable);
        RxJavaPlugins.getInstance().getErrorHandler().handleError(localThrowable);
      }
      return;
      label61: localIterator.remove();
    }
  }

  public static void registerExecutor(ScheduledThreadPoolExecutor paramScheduledThreadPoolExecutor)
  {
    while (true)
    {
      if ((ScheduledExecutorService)PURGE.get() != null);
      ScheduledExecutorService localScheduledExecutorService;
      while (true)
      {
        EXECUTORS.putIfAbsent(paramScheduledThreadPoolExecutor, paramScheduledThreadPoolExecutor);
        return;
        localScheduledExecutorService = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge-"));
        if (!PURGE.compareAndSet(null, localScheduledExecutorService))
          break;
        localScheduledExecutorService.scheduleAtFixedRate(new Runnable()
        {
          public void run()
          {
            NewThreadWorker.purgeExecutors();
          }
        }
        , PURGE_FREQUENCY, PURGE_FREQUENCY, TimeUnit.MILLISECONDS);
      }
      localScheduledExecutorService.shutdownNow();
    }
  }

  public static boolean tryEnableCancelPolicy(ScheduledExecutorService paramScheduledExecutorService)
  {
    Object localObject1;
    if (SHOULD_TRY_ENABLE_CANCEL_POLICY)
      if ((paramScheduledExecutorService instanceof ScheduledThreadPoolExecutor))
      {
        localObject1 = cachedSetRemoveOnCancelPolicyMethod;
        if (localObject1 != SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED);
      }
    label50: label58: label125: for (int i = 0; ; i = 0)
      while (true)
      {
        return i;
        Object localObject2;
        Method localMethod1;
        if (localObject1 == null)
        {
          Method localMethod2 = findSetRemoveOnCancelPolicyMethod(paramScheduledExecutorService);
          if (localMethod2 != null)
          {
            localObject2 = localMethod2;
            cachedSetRemoveOnCancelPolicyMethod = localObject2;
            localMethod1 = localMethod2;
            if (localMethod1 == null)
              break label125;
          }
        }
        try
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Boolean.valueOf(true);
          localMethod1.invoke(paramScheduledExecutorService, arrayOfObject);
          i = 1;
          continue;
          localObject2 = SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED;
          break label50;
          localMethod1 = (Method)localObject1;
          break label58;
          localMethod1 = findSetRemoveOnCancelPolicyMethod(paramScheduledExecutorService);
        }
        catch (Exception localException)
        {
          RxJavaPlugins.getInstance().getErrorHandler().handleError(localException);
        }
      }
  }

  public boolean isUnsubscribed()
  {
    return this.isUnsubscribed;
  }

  public Subscription schedule(Action0 paramAction0)
  {
    return schedule(paramAction0, 0L, null);
  }

  public Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
  {
    if (this.isUnsubscribed);
    for (Object localObject = Subscriptions.unsubscribed(); ; localObject = scheduleActual(paramAction0, paramLong, paramTimeUnit))
      return localObject;
  }

  public ScheduledAction scheduleActual(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
  {
    ScheduledAction localScheduledAction = new ScheduledAction(this.schedulersHook.onSchedule(paramAction0));
    if (paramLong <= 0L);
    for (Object localObject = this.executor.submit(localScheduledAction); ; localObject = this.executor.schedule(localScheduledAction, paramLong, paramTimeUnit))
    {
      localScheduledAction.add((Future)localObject);
      return localScheduledAction;
    }
  }

  public ScheduledAction scheduleActual(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit, SubscriptionList paramSubscriptionList)
  {
    ScheduledAction localScheduledAction = new ScheduledAction(this.schedulersHook.onSchedule(paramAction0), paramSubscriptionList);
    paramSubscriptionList.add(localScheduledAction);
    if (paramLong <= 0L);
    for (Object localObject = this.executor.submit(localScheduledAction); ; localObject = this.executor.schedule(localScheduledAction, paramLong, paramTimeUnit))
    {
      localScheduledAction.add((Future)localObject);
      return localScheduledAction;
    }
  }

  public ScheduledAction scheduleActual(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit, CompositeSubscription paramCompositeSubscription)
  {
    ScheduledAction localScheduledAction = new ScheduledAction(this.schedulersHook.onSchedule(paramAction0), paramCompositeSubscription);
    paramCompositeSubscription.add(localScheduledAction);
    if (paramLong <= 0L);
    for (Object localObject = this.executor.submit(localScheduledAction); ; localObject = this.executor.schedule(localScheduledAction, paramLong, paramTimeUnit))
    {
      localScheduledAction.add((Future)localObject);
      return localScheduledAction;
    }
  }

  public void unsubscribe()
  {
    this.isUnsubscribed = true;
    this.executor.shutdownNow();
    deregisterExecutor(this.executor);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.schedulers.NewThreadWorker
 * JD-Core Version:    0.6.0
 */