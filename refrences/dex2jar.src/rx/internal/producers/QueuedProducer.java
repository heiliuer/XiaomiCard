package rx.internal.producers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.BackpressureUtils;

public final class QueuedProducer<T> extends AtomicLong
  implements Producer, Observer<T>
{
  static final Object NULL_SENTINEL = new Object();
  private static final long serialVersionUID = 7277121710709137047L;
  final Subscriber<? super T> child;
  volatile boolean done;
  Throwable error;
  final Queue<Object> queue;
  final AtomicInteger wip;

  public QueuedProducer(Subscriber<? super T> paramSubscriber)
  {
  }

  public QueuedProducer(Subscriber<? super T> paramSubscriber, Queue<Object> paramQueue)
  {
    this.child = paramSubscriber;
    this.queue = paramQueue;
    this.wip = new AtomicInteger();
  }

  private boolean checkTerminated(boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = 1;
    if (this.child.isUnsubscribed());
    while (true)
    {
      return i;
      if (paramBoolean1)
      {
        Throwable localThrowable = this.error;
        if (localThrowable != null)
        {
          this.queue.clear();
          this.child.onError(localThrowable);
          continue;
        }
        if (paramBoolean2)
        {
          this.child.onCompleted();
          continue;
        }
      }
      i = 0;
    }
  }

  private void drain()
  {
    Subscriber localSubscriber;
    Queue localQueue;
    if (this.wip.getAndIncrement() == 0)
    {
      localSubscriber = this.child;
      localQueue = this.queue;
      if (!checkTerminated(this.done, localQueue.isEmpty()));
    }
    else
    {
      label37: return;
    }
    this.wip.lazySet(1);
    long l1 = get();
    long l2 = 0L;
    while (true)
    {
      boolean bool1;
      Object localObject;
      if (l1 != 0L)
      {
        bool1 = this.done;
        localObject = localQueue.poll();
        if (localObject != null)
          break label137;
      }
      label137: for (boolean bool2 = true; ; bool2 = false)
      {
        if (checkTerminated(bool1, bool2))
          break label141;
        if (localObject != null)
          break label143;
        if ((l2 != 0L) && (get() != 9223372036854775807L))
          addAndGet(-l2);
        if (this.wip.decrementAndGet() != 0)
          break;
        break label37;
      }
      label141: break label37;
      try
      {
        label143: if (localObject == NULL_SENTINEL)
        {
          localSubscriber.onNext(null);
          break label195;
        }
        localSubscriber.onNext(localObject);
      }
      catch (Throwable localThrowable)
      {
        if (localObject == NULL_SENTINEL);
      }
      while (true)
      {
        Exceptions.throwOrReport(localThrowable, localSubscriber, localObject);
        break;
        localObject = null;
      }
      label195: l1 -= 1L;
      l2 += 1L;
    }
  }

  public boolean offer(T paramT)
  {
    int i = 0;
    if (paramT == null)
      if (this.queue.offer(NULL_SENTINEL))
        break label36;
    while (true)
    {
      return i;
      if (!this.queue.offer(paramT))
        continue;
      label36: drain();
      i = 1;
    }
  }

  public void onCompleted()
  {
    this.done = true;
    drain();
  }

  public void onError(Throwable paramThrowable)
  {
    this.error = paramThrowable;
    this.done = true;
    drain();
  }

  public void onNext(T paramT)
  {
    if (!offer(paramT))
      onError(new MissingBackpressureException());
  }

  public void request(long paramLong)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("n >= 0 required");
    if (paramLong > 0L)
    {
      BackpressureUtils.getAndAddRequest(this, paramLong);
      drain();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.producers.QueuedProducer
 * JD-Core Version:    0.6.0
 */