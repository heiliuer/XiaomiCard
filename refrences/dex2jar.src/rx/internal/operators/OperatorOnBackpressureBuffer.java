package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.internal.util.BackpressureDrainManager;
import rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback;

public class OperatorOnBackpressureBuffer<T>
  implements Observable.Operator<T, T>
{
  private final Long capacity;
  private final Action0 onOverflow;

  private OperatorOnBackpressureBuffer()
  {
    this.capacity = null;
    this.onOverflow = null;
  }

  public OperatorOnBackpressureBuffer(long paramLong)
  {
    this(paramLong, null);
  }

  public OperatorOnBackpressureBuffer(long paramLong, Action0 paramAction0)
  {
    if (paramLong <= 0L)
      throw new IllegalArgumentException("Buffer capacity must be > 0");
    this.capacity = Long.valueOf(paramLong);
    this.onOverflow = paramAction0;
  }

  public static <T> OperatorOnBackpressureBuffer<T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    BufferSubscriber localBufferSubscriber = new BufferSubscriber(paramSubscriber, this.capacity, this.onOverflow);
    paramSubscriber.add(localBufferSubscriber);
    paramSubscriber.setProducer(localBufferSubscriber.manager());
    return localBufferSubscriber;
  }

  private static final class BufferSubscriber<T> extends Subscriber<T>
    implements BackpressureDrainManager.BackpressureQueueCallback
  {
    private final Long baseCapacity;
    private final AtomicLong capacity;
    private final Subscriber<? super T> child;
    private final BackpressureDrainManager manager;
    private final NotificationLite<T> on = NotificationLite.instance();
    private final Action0 onOverflow;
    private final ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue();
    private final AtomicBoolean saturated = new AtomicBoolean(false);

    public BufferSubscriber(Subscriber<? super T> paramSubscriber, Long paramLong, Action0 paramAction0)
    {
      this.child = paramSubscriber;
      this.baseCapacity = paramLong;
      if (paramLong != null);
      for (AtomicLong localAtomicLong = new AtomicLong(paramLong.longValue()); ; localAtomicLong = null)
      {
        this.capacity = localAtomicLong;
        this.onOverflow = paramAction0;
        this.manager = new BackpressureDrainManager(this);
        return;
      }
    }

    private boolean assertCapacity()
    {
      boolean bool = true;
      if (this.capacity == null);
      while (true)
      {
        return bool;
        long l;
        do
        {
          l = this.capacity.get();
          if (l > 0L)
            continue;
          if (this.saturated.compareAndSet(false, bool))
          {
            unsubscribe();
            this.child.onError(new MissingBackpressureException("Overflowed buffer of " + this.baseCapacity));
            if (this.onOverflow != null)
              this.onOverflow.call();
          }
          bool = false;
          break;
        }
        while (!this.capacity.compareAndSet(l, l - 1L));
      }
    }

    public boolean accept(Object paramObject)
    {
      return this.on.accept(this.child, paramObject);
    }

    public void complete(Throwable paramThrowable)
    {
      if (paramThrowable != null)
        this.child.onError(paramThrowable);
      while (true)
      {
        return;
        this.child.onCompleted();
      }
    }

    protected Producer manager()
    {
      return this.manager;
    }

    public void onCompleted()
    {
      if (!this.saturated.get())
        this.manager.terminateAndDrain();
    }

    public void onError(Throwable paramThrowable)
    {
      if (!this.saturated.get())
        this.manager.terminateAndDrain(paramThrowable);
    }

    public void onNext(T paramT)
    {
      if (!assertCapacity());
      while (true)
      {
        return;
        this.queue.offer(this.on.next(paramT));
        this.manager.drain();
      }
    }

    public void onStart()
    {
      request(9223372036854775807L);
    }

    public Object peek()
    {
      return this.queue.peek();
    }

    public Object poll()
    {
      Object localObject = this.queue.poll();
      if ((this.capacity != null) && (localObject != null))
        this.capacity.incrementAndGet();
      return localObject;
    }
  }

  private static class Holder
  {
    static final OperatorOnBackpressureBuffer<?> INSTANCE = new OperatorOnBackpressureBuffer(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorOnBackpressureBuffer
 * JD-Core Version:    0.6.0
 */