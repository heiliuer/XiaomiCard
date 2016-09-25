package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.Operator;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.SynchronizedQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.schedulers.ImmediateScheduler;
import rx.schedulers.TrampolineScheduler;

public final class OperatorObserveOn<T>
  implements Observable.Operator<T, T>
{
  private final Scheduler scheduler;

  public OperatorObserveOn(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    if ((this.scheduler instanceof ImmediateScheduler));
    while (true)
    {
      return paramSubscriber;
      if ((this.scheduler instanceof TrampolineScheduler))
        continue;
      ObserveOnSubscriber localObserveOnSubscriber = new ObserveOnSubscriber(this.scheduler, paramSubscriber);
      localObserveOnSubscriber.init();
      paramSubscriber = localObserveOnSubscriber;
    }
  }

  static final class ScheduledUnsubscribe extends AtomicInteger
    implements Subscription
  {
    volatile boolean unsubscribed = false;
    final Scheduler.Worker worker;

    public ScheduledUnsubscribe(Scheduler.Worker paramWorker)
    {
      this.worker = paramWorker;
    }

    public boolean isUnsubscribed()
    {
      return this.unsubscribed;
    }

    public void unsubscribe()
    {
      if (getAndSet(1) == 0)
        this.worker.schedule(new Action0()
        {
          public void call()
          {
            OperatorObserveOn.ScheduledUnsubscribe.this.worker.unsubscribe();
            OperatorObserveOn.ScheduledUnsubscribe.this.unsubscribed = true;
          }
        });
    }
  }

  private static final class ObserveOnSubscriber<T> extends Subscriber<T>
  {
    final Action0 action = new Action0()
    {
      public void call()
      {
        OperatorObserveOn.ObserveOnSubscriber.this.pollQueue();
      }
    };
    final Subscriber<? super T> child;
    final AtomicLong counter = new AtomicLong();
    volatile Throwable error;
    volatile boolean finished = false;
    final NotificationLite<T> on = NotificationLite.instance();
    final Queue<Object> queue;
    final Scheduler.Worker recursiveScheduler;
    final AtomicLong requested = new AtomicLong();
    final OperatorObserveOn.ScheduledUnsubscribe scheduledUnsubscribe;

    public ObserveOnSubscriber(Scheduler paramScheduler, Subscriber<? super T> paramSubscriber)
    {
      this.child = paramSubscriber;
      this.recursiveScheduler = paramScheduler.createWorker();
      if (UnsafeAccess.isUnsafeAvailable());
      for (this.queue = new SpscArrayQueue(RxRingBuffer.SIZE); ; this.queue = new SynchronizedQueue(RxRingBuffer.SIZE))
      {
        this.scheduledUnsubscribe = new OperatorObserveOn.ScheduledUnsubscribe(this.recursiveScheduler);
        return;
      }
    }

    void init()
    {
      this.child.add(this.scheduledUnsubscribe);
      this.child.setProducer(new Producer()
      {
        public void request(long paramLong)
        {
          BackpressureUtils.getAndAddRequest(OperatorObserveOn.ObserveOnSubscriber.this.requested, paramLong);
          OperatorObserveOn.ObserveOnSubscriber.this.schedule();
        }
      });
      this.child.add(this.recursiveScheduler);
      this.child.add(this);
    }

    public void onCompleted()
    {
      if ((isUnsubscribed()) || (this.finished));
      while (true)
      {
        return;
        this.finished = true;
        schedule();
      }
    }

    public void onError(Throwable paramThrowable)
    {
      if ((isUnsubscribed()) || (this.finished));
      while (true)
      {
        return;
        this.error = paramThrowable;
        unsubscribe();
        this.finished = true;
        schedule();
      }
    }

    public void onNext(T paramT)
    {
      if (isUnsubscribed());
      while (true)
      {
        return;
        if (!this.queue.offer(this.on.next(paramT)))
        {
          onError(new MissingBackpressureException());
          continue;
        }
        schedule();
      }
    }

    public void onStart()
    {
      request(RxRingBuffer.SIZE);
    }

    void pollQueue()
    {
      int i = 0;
      AtomicLong localAtomicLong1 = this.requested;
      AtomicLong localAtomicLong2 = this.counter;
      localAtomicLong2.set(1L);
      long l1 = 0L;
      long l2 = localAtomicLong1.get();
      label26: if (this.child.isUnsubscribed());
      while (true)
      {
        return;
        if (this.finished)
        {
          Throwable localThrowable = this.error;
          if (localThrowable != null)
          {
            this.queue.clear();
            this.child.onError(localThrowable);
            continue;
          }
          if (this.queue.isEmpty())
          {
            this.child.onCompleted();
            continue;
          }
        }
        if (l2 > 0L)
        {
          Object localObject = this.queue.poll();
          if (localObject != null)
          {
            this.child.onNext(this.on.getValue(localObject));
            l2 -= 1L;
            i++;
            l1 += 1L;
            break label26;
          }
        }
        if ((l1 > 0L) && (localAtomicLong1.get() != 9223372036854775807L))
          localAtomicLong1.addAndGet(-l1);
        if (localAtomicLong2.decrementAndGet() > 0L)
          break;
        if (i <= 0)
          continue;
        request(i);
      }
    }

    protected void schedule()
    {
      if (this.counter.getAndIncrement() == 0L)
        this.recursiveScheduler.schedule(this.action);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorObserveOn
 * JD-Core Version:    0.6.0
 */