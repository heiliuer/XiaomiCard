package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.internal.producers.ProducerArbiter;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

public final class OperatorConcat<T>
  implements Observable.Operator<T, Observable<? extends T>>
{
  public static <T> OperatorConcat<T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    SerialSubscription localSerialSubscription = new SerialSubscription();
    paramSubscriber.add(localSerialSubscription);
    ConcatSubscriber localConcatSubscriber = new ConcatSubscriber(localSerializedSubscriber, localSerialSubscription);
    paramSubscriber.setProducer(new ConcatProducer(localConcatSubscriber));
    return localConcatSubscriber;
  }

  static class ConcatInnerSubscriber<T> extends Subscriber<T>
  {
    private final ProducerArbiter arbiter;
    private final Subscriber<T> child;
    private final AtomicInteger once = new AtomicInteger();
    private final OperatorConcat.ConcatSubscriber<T> parent;

    public ConcatInnerSubscriber(OperatorConcat.ConcatSubscriber<T> paramConcatSubscriber, Subscriber<T> paramSubscriber, ProducerArbiter paramProducerArbiter)
    {
      this.parent = paramConcatSubscriber;
      this.child = paramSubscriber;
      this.arbiter = paramProducerArbiter;
    }

    public void onCompleted()
    {
      if (this.once.compareAndSet(0, 1))
        this.parent.completeInner();
    }

    public void onError(Throwable paramThrowable)
    {
      if (this.once.compareAndSet(0, 1))
        this.parent.onError(paramThrowable);
    }

    public void onNext(T paramT)
    {
      this.child.onNext(paramT);
      OperatorConcat.ConcatSubscriber.access$200(this.parent);
      this.arbiter.produced(1L);
    }

    public void setProducer(Producer paramProducer)
    {
      this.arbiter.setProducer(paramProducer);
    }
  }

  static final class ConcatSubscriber<T> extends Subscriber<Observable<? extends T>>
  {
    private final ProducerArbiter arbiter;
    private final Subscriber<T> child;
    private final SerialSubscription current;
    volatile OperatorConcat.ConcatInnerSubscriber<T> currentSubscriber;
    final NotificationLite<Observable<? extends T>> nl = NotificationLite.instance();
    final ConcurrentLinkedQueue<Object> queue;
    private final AtomicLong requested = new AtomicLong();
    final AtomicInteger wip = new AtomicInteger();

    public ConcatSubscriber(Subscriber<T> paramSubscriber, SerialSubscription paramSerialSubscription)
    {
      super();
      this.child = paramSubscriber;
      this.current = paramSerialSubscription;
      this.arbiter = new ProducerArbiter();
      this.queue = new ConcurrentLinkedQueue();
      add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          OperatorConcat.ConcatSubscriber.this.queue.clear();
        }
      }));
    }

    private void decrementRequested()
    {
      this.requested.decrementAndGet();
    }

    private void requestFromChild(long paramLong)
    {
      if (paramLong <= 0L);
      while (true)
      {
        return;
        long l = BackpressureUtils.getAndAddRequest(this.requested, paramLong);
        this.arbiter.request(paramLong);
        if ((l != 0L) || (this.currentSubscriber != null) || (this.wip.get() <= 0))
          continue;
        subscribeNext();
      }
    }

    void completeInner()
    {
      this.currentSubscriber = null;
      if (this.wip.decrementAndGet() > 0)
        subscribeNext();
      request(1L);
    }

    public void onCompleted()
    {
      this.queue.add(this.nl.completed());
      if (this.wip.getAndIncrement() == 0)
        subscribeNext();
    }

    public void onError(Throwable paramThrowable)
    {
      this.child.onError(paramThrowable);
      unsubscribe();
    }

    public void onNext(Observable<? extends T> paramObservable)
    {
      this.queue.add(this.nl.next(paramObservable));
      if (this.wip.getAndIncrement() == 0)
        subscribeNext();
    }

    public void onStart()
    {
      request(2L);
    }

    void subscribeNext()
    {
      Object localObject2;
      if (this.requested.get() > 0L)
      {
        localObject2 = this.queue.poll();
        if (this.nl.isCompleted(localObject2))
          this.child.onCompleted();
      }
      while (true)
      {
        return;
        if (localObject2 != null)
        {
          Observable localObservable = (Observable)this.nl.getValue(localObject2);
          this.currentSubscriber = new OperatorConcat.ConcatInnerSubscriber(this, this.child, this.arbiter);
          this.current.set(this.currentSubscriber);
          localObservable.unsafeSubscribe(this.currentSubscriber);
          continue;
          Object localObject1 = this.queue.peek();
          if (!this.nl.isCompleted(localObject1))
            continue;
          this.child.onCompleted();
          continue;
        }
      }
    }
  }

  static final class ConcatProducer<T>
    implements Producer
  {
    final OperatorConcat.ConcatSubscriber<T> cs;

    ConcatProducer(OperatorConcat.ConcatSubscriber<T> paramConcatSubscriber)
    {
      this.cs = paramConcatSubscriber;
    }

    public void request(long paramLong)
    {
      this.cs.requestFromChild(paramLong);
    }
  }

  private static final class Holder
  {
    static final OperatorConcat<Object> INSTANCE = new OperatorConcat(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorConcat
 * JD-Core Version:    0.6.0
 */