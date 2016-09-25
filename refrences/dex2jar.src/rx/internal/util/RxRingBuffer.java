package rx.internal.util;

import java.io.PrintStream;
import java.util.Queue;
import rx.Observer;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.NotificationLite;
import rx.internal.util.unsafe.SpmcArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

public class RxRingBuffer
  implements Subscription
{
  public static final int SIZE;
  public static ObjectPool<Queue<Object>> SPMC_POOL;
  public static ObjectPool<Queue<Object>> SPSC_POOL;
  static int _size;
  private static final NotificationLite<Object> on = NotificationLite.instance();
  private final ObjectPool<Queue<Object>> pool;
  private Queue<Object> queue;
  private final int size;
  public volatile Object terminalState;

  static
  {
    _size = 128;
    if (PlatformDependent.isAndroid())
      _size = 16;
    String str = System.getProperty("rx.ring-buffer.size");
    if (str != null);
    try
    {
      _size = Integer.parseInt(str);
      SIZE = _size;
      SPSC_POOL = new ObjectPool()
      {
        protected SpscArrayQueue<Object> createObject()
        {
          return new SpscArrayQueue(RxRingBuffer.SIZE);
        }
      };
      SPMC_POOL = new ObjectPool()
      {
        protected SpmcArrayQueue<Object> createObject()
        {
          return new SpmcArrayQueue(RxRingBuffer.SIZE);
        }
      };
      return;
    }
    catch (Exception localException)
    {
      while (true)
        System.err.println("Failed to set 'rx.buffer.size' with value " + str + " => " + localException.getMessage());
    }
  }

  RxRingBuffer()
  {
    this(new SynchronizedQueue(SIZE), SIZE);
  }

  private RxRingBuffer(Queue<Object> paramQueue, int paramInt)
  {
    this.queue = paramQueue;
    this.pool = null;
    this.size = paramInt;
  }

  private RxRingBuffer(ObjectPool<Queue<Object>> paramObjectPool, int paramInt)
  {
    this.pool = paramObjectPool;
    this.queue = ((Queue)paramObjectPool.borrowObject());
    this.size = paramInt;
  }

  public static RxRingBuffer getSpmcInstance()
  {
    if (UnsafeAccess.isUnsafeAvailable());
    for (RxRingBuffer localRxRingBuffer = new RxRingBuffer(SPMC_POOL, SIZE); ; localRxRingBuffer = new RxRingBuffer())
      return localRxRingBuffer;
  }

  public static RxRingBuffer getSpscInstance()
  {
    if (UnsafeAccess.isUnsafeAvailable());
    for (RxRingBuffer localRxRingBuffer = new RxRingBuffer(SPSC_POOL, SIZE); ; localRxRingBuffer = new RxRingBuffer())
      return localRxRingBuffer;
  }

  public boolean accept(Object paramObject, Observer paramObserver)
  {
    return on.accept(paramObserver, paramObject);
  }

  public Throwable asError(Object paramObject)
  {
    return on.getError(paramObject);
  }

  public int available()
  {
    return this.size - count();
  }

  public int capacity()
  {
    return this.size;
  }

  public int count()
  {
    Queue localQueue = this.queue;
    if (localQueue == null);
    for (int i = 0; ; i = localQueue.size())
      return i;
  }

  public Object getValue(Object paramObject)
  {
    return on.getValue(paramObject);
  }

  public boolean isCompleted(Object paramObject)
  {
    return on.isCompleted(paramObject);
  }

  public boolean isEmpty()
  {
    Queue localQueue = this.queue;
    if (localQueue == null);
    for (boolean bool = true; ; bool = localQueue.isEmpty())
      return bool;
  }

  public boolean isError(Object paramObject)
  {
    return on.isError(paramObject);
  }

  public boolean isUnsubscribed()
  {
    if (this.queue == null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onCompleted()
  {
    if (this.terminalState == null)
      this.terminalState = on.completed();
  }

  public void onError(Throwable paramThrowable)
  {
    if (this.terminalState == null)
      this.terminalState = on.error(paramThrowable);
  }

  public void onNext(Object paramObject)
    throws MissingBackpressureException
  {
    int i = 0;
    int j = 0;
    monitorenter;
    try
    {
      Queue localQueue = this.queue;
      if (localQueue != null)
        if (!localQueue.offer(on.next(paramObject)))
          j = 1;
      while (true)
      {
        monitorexit;
        if (i == 0)
          break;
        throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        j = 0;
        continue;
        i = 1;
      }
    }
    finally
    {
      monitorexit;
    }
    if (j != 0)
      throw new MissingBackpressureException();
  }

  public Object peek()
  {
    monitorenter;
    Object localObject2;
    try
    {
      Queue localQueue = this.queue;
      if (localQueue == null)
      {
        localObject2 = null;
        monitorexit;
      }
      else
      {
        localObject2 = localQueue.peek();
        Object localObject3 = this.terminalState;
        if ((localObject2 == null) && (localObject3 != null) && (localQueue.peek() == null))
          localObject2 = localObject3;
        monitorexit;
      }
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
      throw localObject1;
    }
    return localObject2;
  }

  public Object poll()
  {
    Object localObject1 = null;
    monitorenter;
    try
    {
      Queue localQueue = this.queue;
      if (localQueue == null)
      {
        monitorexit;
      }
      else
      {
        localObject1 = localQueue.poll();
        Object localObject3 = this.terminalState;
        if ((localObject1 == null) && (localObject3 != null) && (localQueue.peek() == null))
        {
          localObject1 = localObject3;
          this.terminalState = null;
        }
        monitorexit;
      }
    }
    finally
    {
      localObject2 = finally;
      monitorexit;
      throw localObject2;
    }
    return localObject1;
  }

  /** @deprecated */
  public void release()
  {
    monitorenter;
    try
    {
      Queue localQueue = this.queue;
      ObjectPool localObjectPool = this.pool;
      if ((localObjectPool != null) && (localQueue != null))
      {
        localQueue.clear();
        this.queue = null;
        localObjectPool.returnObject(localQueue);
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void unsubscribe()
  {
    release();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.RxRingBuffer
 * JD-Core Version:    0.6.0
 */