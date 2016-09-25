package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;

public final class OnSubscribeFromIterable<T>
  implements Observable.OnSubscribe<T>
{
  final Iterable<? extends T> is;

  public OnSubscribeFromIterable(Iterable<? extends T> paramIterable)
  {
    if (paramIterable == null)
      throw new NullPointerException("iterable must not be null");
    this.is = paramIterable;
  }

  public void call(Subscriber<? super T> paramSubscriber)
  {
    Iterator localIterator = this.is.iterator();
    if ((!localIterator.hasNext()) && (!paramSubscriber.isUnsubscribed()))
      paramSubscriber.onCompleted();
    while (true)
    {
      return;
      paramSubscriber.setProducer(new IterableProducer(paramSubscriber, localIterator, null));
    }
  }

  private static final class IterableProducer<T> extends AtomicLong
    implements Producer
  {
    private static final long serialVersionUID = -8730475647105475802L;
    private final Iterator<? extends T> it;
    private final Subscriber<? super T> o;

    private IterableProducer(Subscriber<? super T> paramSubscriber, Iterator<? extends T> paramIterator)
    {
      this.o = paramSubscriber;
      this.it = paramIterator;
    }

    void fastpath()
    {
      Subscriber localSubscriber = this.o;
      Iterator localIterator = this.it;
      if (localSubscriber.isUnsubscribed());
      while (true)
      {
        return;
        if (localIterator.hasNext())
        {
          localSubscriber.onNext(localIterator.next());
          break;
        }
        if (localSubscriber.isUnsubscribed())
          continue;
        localSubscriber.onCompleted();
      }
    }

    public void request(long paramLong)
    {
      if (get() == 9223372036854775807L);
      while (true)
      {
        return;
        if ((paramLong == 9223372036854775807L) && (compareAndSet(0L, 9223372036854775807L)))
        {
          fastpath();
          continue;
        }
        if ((paramLong <= 0L) || (BackpressureUtils.getAndAddRequest(this, paramLong) != 0L))
          continue;
        slowpath(paramLong);
      }
    }

    void slowpath(long paramLong)
    {
      Subscriber localSubscriber = this.o;
      Iterator localIterator = this.it;
      long l1 = paramLong;
      long l2 = l1;
      label18: if (localSubscriber.isUnsubscribed());
      while (true)
      {
        return;
        if (localIterator.hasNext())
        {
          l2 -= 1L;
          if (l2 >= 0L)
          {
            localSubscriber.onNext(localIterator.next());
            break label18;
          }
        }
        else
        {
          if (localSubscriber.isUnsubscribed())
            continue;
          localSubscriber.onCompleted();
          continue;
        }
        l1 = addAndGet(-l1);
        if (l1 != 0L)
          break;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OnSubscribeFromIterable
 * JD-Core Version:    0.6.0
 */