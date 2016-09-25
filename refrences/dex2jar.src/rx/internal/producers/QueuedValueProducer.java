package rx.internal.producers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;

public final class QueuedValueProducer<T> extends AtomicLong
  implements Producer
{
  static final Object NULL_SENTINEL = new Object();
  private static final long serialVersionUID = 7277121710709137047L;
  final Subscriber<? super T> child;
  final Queue<Object> queue;
  final AtomicInteger wip;

  public QueuedValueProducer(Subscriber<? super T> paramSubscriber)
  {
  }

  public QueuedValueProducer(Subscriber<? super T> paramSubscriber, Queue<Object> paramQueue)
  {
    this.child = paramSubscriber;
    this.queue = paramQueue;
    this.wip = new AtomicInteger();
  }

  private void drain()
  {
    Subscriber localSubscriber;
    Queue localQueue;
    if (this.wip.getAndIncrement() == 0)
    {
      localSubscriber = this.child;
      localQueue = this.queue;
      if (!localSubscriber.isUnsubscribed())
        break label28;
    }
    while (true)
    {
      return;
      label28: this.wip.lazySet(1);
      long l1 = get();
      long l2 = 0L;
      label44: if (l1 != 0L)
      {
        Object localObject1 = localQueue.poll();
        if (localObject1 != null)
        {
          try
          {
            if (localObject1 == NULL_SENTINEL)
              localSubscriber.onNext(null);
            while (!localSubscriber.isUnsubscribed())
            {
              l1 -= 1L;
              l2 += 1L;
              break label44;
              Object localObject2 = localObject1;
              localSubscriber.onNext(localObject2);
            }
          }
          catch (Throwable localThrowable)
          {
            if (localObject1 == NULL_SENTINEL);
          }
          while (true)
          {
            Exceptions.throwOrReport(localThrowable, localSubscriber, localObject1);
            break;
            localObject1 = null;
          }
        }
      }
      if ((l2 != 0L) && (get() != 9223372036854775807L))
        addAndGet(-l2);
      if (this.wip.decrementAndGet() != 0)
        break;
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
 * Qualified Name:     rx.internal.producers.QueuedValueProducer
 * JD-Core Version:    0.6.0
 */