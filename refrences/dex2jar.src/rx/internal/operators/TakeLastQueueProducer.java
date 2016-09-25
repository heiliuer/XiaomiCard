package rx.internal.operators;

import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

final class TakeLastQueueProducer<T> extends AtomicLong
  implements Producer
{
  private final Deque<Object> deque;
  private volatile boolean emittingStarted = false;
  private final NotificationLite<T> notification;
  private final Subscriber<? super T> subscriber;

  public TakeLastQueueProducer(NotificationLite<T> paramNotificationLite, Deque<Object> paramDeque, Subscriber<? super T> paramSubscriber)
  {
    this.notification = paramNotificationLite;
    this.deque = paramDeque;
    this.subscriber = paramSubscriber;
  }

  void emit(long paramLong)
  {
    if (get() == 9223372036854775807L)
      if (paramLong != 0L);
    while (true)
    {
      try
      {
        Iterator localIterator = this.deque.iterator();
        if (!localIterator.hasNext())
          continue;
        Object localObject3 = localIterator.next();
        boolean bool = this.subscriber.isUnsubscribed();
        if (bool)
          return;
        this.notification.accept(this.subscriber, localObject3);
        continue;
      }
      catch (Throwable localThrowable)
      {
        Exceptions.throwOrReport(localThrowable, this.subscriber);
        this.deque.clear();
        continue;
        this.deque.clear();
        continue;
      }
      finally
      {
        this.deque.clear();
      }
      if (paramLong != 0L)
        continue;
      label209: long l3;
      do
      {
        long l1 = get();
        for (int i = 0; ; i++)
        {
          l1 -= 1L;
          if (l1 < 0L)
            break label209;
          Object localObject1 = this.deque.poll();
          if (localObject1 == null)
            break label209;
          if ((this.subscriber.isUnsubscribed()) || (this.notification.accept(this.subscriber, localObject1)))
            break;
        }
        long l2;
        do
        {
          l2 = get();
          l3 = l2 - i;
          if (l2 == 9223372036854775807L)
            break;
        }
        while (!compareAndSet(l2, l3));
      }
      while (l3 != 0L);
    }
  }

  public void request(long paramLong)
  {
    if (get() == 9223372036854775807L);
    label50: 
    while (true)
    {
      return;
      long l;
      if (paramLong == 9223372036854775807L)
        l = getAndSet(9223372036854775807L);
      while (true)
      {
        if (!this.emittingStarted)
          break label50;
        emit(l);
        break;
        l = BackpressureUtils.getAndAddRequest(this, paramLong);
      }
    }
  }

  void startEmitting()
  {
    if (!this.emittingStarted)
    {
      this.emittingStarted = true;
      emit(0L);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.TakeLastQueueProducer
 * JD-Core Version:    0.6.0
 */