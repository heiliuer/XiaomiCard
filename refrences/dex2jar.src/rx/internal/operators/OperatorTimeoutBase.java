package rx.internal.operators;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

class OperatorTimeoutBase<T>
  implements Observable.Operator<T, T>
{
  private final FirstTimeoutStub<T> firstTimeoutStub;
  private final Observable<? extends T> other;
  private final Scheduler scheduler;
  private final TimeoutStub<T> timeoutStub;

  OperatorTimeoutBase(FirstTimeoutStub<T> paramFirstTimeoutStub, TimeoutStub<T> paramTimeoutStub, Observable<? extends T> paramObservable, Scheduler paramScheduler)
  {
    this.firstTimeoutStub = paramFirstTimeoutStub;
    this.timeoutStub = paramTimeoutStub;
    this.other = paramObservable;
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    SerialSubscription localSerialSubscription = new SerialSubscription();
    paramSubscriber.add(localSerialSubscription);
    TimeoutSubscriber localTimeoutSubscriber = new TimeoutSubscriber(new SerializedSubscriber(paramSubscriber), this.timeoutStub, localSerialSubscription, this.other, localWorker, null);
    localSerialSubscription.set((Subscription)this.firstTimeoutStub.call(localTimeoutSubscriber, Long.valueOf(0L), localWorker));
    return localTimeoutSubscriber;
  }

  static final class TimeoutSubscriber<T> extends Subscriber<T>
  {
    final AtomicLong actual = new AtomicLong();
    private final Object gate = new Object();
    private final Scheduler.Worker inner;
    private final Observable<? extends T> other;
    private final SerialSubscription serial;
    private final SerializedSubscriber<T> serializedSubscriber;
    final AtomicInteger terminated = new AtomicInteger();
    private final OperatorTimeoutBase.TimeoutStub<T> timeoutStub;

    private TimeoutSubscriber(SerializedSubscriber<T> paramSerializedSubscriber, OperatorTimeoutBase.TimeoutStub<T> paramTimeoutStub, SerialSubscription paramSerialSubscription, Observable<? extends T> paramObservable, Scheduler.Worker paramWorker)
    {
      super();
      this.serializedSubscriber = paramSerializedSubscriber;
      this.timeoutStub = paramTimeoutStub;
      this.serial = paramSerialSubscription;
      this.other = paramObservable;
      this.inner = paramWorker;
    }

    public void onCompleted()
    {
      int i = 0;
      synchronized (this.gate)
      {
        if (this.terminated.getAndSet(1) == 0)
          i = 1;
        if (i != 0)
        {
          this.serial.unsubscribe();
          this.serializedSubscriber.onCompleted();
        }
        return;
      }
    }

    public void onError(Throwable paramThrowable)
    {
      int i = 0;
      synchronized (this.gate)
      {
        if (this.terminated.getAndSet(1) == 0)
          i = 1;
        if (i != 0)
        {
          this.serial.unsubscribe();
          this.serializedSubscriber.onError(paramThrowable);
        }
        return;
      }
    }

    public void onNext(T paramT)
    {
      int i = 0;
      synchronized (this.gate)
      {
        if (this.terminated.get() == 0)
        {
          this.actual.incrementAndGet();
          i = 1;
        }
        if (i != 0)
        {
          this.serializedSubscriber.onNext(paramT);
          this.serial.set((Subscription)this.timeoutStub.call(this, Long.valueOf(this.actual.get()), paramT, this.inner));
        }
        return;
      }
    }

    public void onTimeout(long paramLong)
    {
      int i = 0;
      while (true)
      {
        synchronized (this.gate)
        {
          if ((paramLong != this.actual.get()) || (this.terminated.getAndSet(1) != 0))
            continue;
          i = 1;
          if (i == 0)
            continue;
          if (this.other == null)
          {
            this.serializedSubscriber.onError(new TimeoutException());
            return;
          }
        }
        this.other.unsafeSubscribe(this.serializedSubscriber);
        this.serial.set(this.serializedSubscriber);
      }
    }
  }

  static abstract interface TimeoutStub<T> extends Func4<OperatorTimeoutBase.TimeoutSubscriber<T>, Long, T, Scheduler.Worker, Subscription>
  {
  }

  static abstract interface FirstTimeoutStub<T> extends Func3<OperatorTimeoutBase.TimeoutSubscriber<T>, Long, Scheduler.Worker, Subscription>
  {
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTimeoutBase
 * JD-Core Version:    0.6.0
 */