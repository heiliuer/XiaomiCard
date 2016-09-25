package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;

public final class OnSubscribeRange
  implements Observable.OnSubscribe<Integer>
{
  private final int end;
  private final int start;

  public OnSubscribeRange(int paramInt1, int paramInt2)
  {
    this.start = paramInt1;
    this.end = paramInt2;
  }

  public void call(Subscriber<? super Integer> paramSubscriber)
  {
    paramSubscriber.setProducer(new RangeProducer(paramSubscriber, this.start, this.end, null));
  }

  private static final class RangeProducer extends AtomicLong
    implements Producer
  {
    private static final long serialVersionUID = 4114392207069098388L;
    private final int end;
    private long index;
    private final Subscriber<? super Integer> o;

    private RangeProducer(Subscriber<? super Integer> paramSubscriber, int paramInt1, int paramInt2)
    {
      this.o = paramSubscriber;
      this.index = paramInt1;
      this.end = paramInt2;
    }

    void fastpath()
    {
      long l1 = 1L + this.end;
      Subscriber localSubscriber = this.o;
      long l2 = this.index;
      if (l2 != l1)
        if (!localSubscriber.isUnsubscribed());
      while (true)
      {
        return;
        localSubscriber.onNext(Integer.valueOf((int)l2));
        l2 += 1L;
        break;
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
      long l1 = this.index;
      long l2 = 1L + (this.end - l1);
      long l3 = Math.min(l2, paramLong);
      int i;
      label34: long l4;
      Subscriber localSubscriber;
      long l5;
      if (l2 <= paramLong)
      {
        i = 1;
        l4 = l3 + l1;
        localSubscriber = this.o;
        l5 = l1;
        label49: if (l5 == l4)
          break label92;
        if (!localSubscriber.isUnsubscribed())
          break label72;
      }
      while (true)
      {
        return;
        i = 0;
        break label34;
        label72: localSubscriber.onNext(Integer.valueOf((int)l5));
        l5 += 1L;
        break label49;
        label92: if (i != 0)
        {
          if (localSubscriber.isUnsubscribed())
            continue;
          localSubscriber.onCompleted();
          continue;
        }
        l1 = l4;
        this.index = l4;
        paramLong = addAndGet(-l3);
        if (paramLong != 0L)
          break;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OnSubscribeRange
 * JD-Core Version:    0.6.0
 */