package rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.internal.util.unsafe.Pow2;

public final class SpscLinkedArrayQueue<T>
  implements Queue<T>
{
  static final AtomicLongFieldUpdater<SpscLinkedArrayQueue> CONSUMER_INDEX;
  private static final Object HAS_NEXT;
  static final int MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
  static final AtomicLongFieldUpdater<SpscLinkedArrayQueue> PRODUCER_INDEX = AtomicLongFieldUpdater.newUpdater(SpscLinkedArrayQueue.class, "producerIndex");
  protected AtomicReferenceArray<Object> consumerBuffer;
  protected volatile long consumerIndex;
  protected int consumerMask;
  protected AtomicReferenceArray<Object> producerBuffer;
  protected volatile long producerIndex;
  protected long producerLookAhead;
  protected int producerLookAheadStep;
  protected int producerMask;

  static
  {
    CONSUMER_INDEX = AtomicLongFieldUpdater.newUpdater(SpscLinkedArrayQueue.class, "consumerIndex");
    HAS_NEXT = new Object();
  }

  public SpscLinkedArrayQueue(int paramInt)
  {
    int i = Pow2.roundToPowerOfTwo(paramInt);
    int j = i - 1;
    AtomicReferenceArray localAtomicReferenceArray = new AtomicReferenceArray(i + 1);
    this.producerBuffer = localAtomicReferenceArray;
    this.producerMask = j;
    adjustLookAheadStep(i);
    this.consumerBuffer = localAtomicReferenceArray;
    this.consumerMask = j;
    this.producerLookAhead = (j - 1);
    soProducerIndex(0L);
  }

  private void adjustLookAheadStep(int paramInt)
  {
    this.producerLookAheadStep = Math.min(paramInt / 4, MAX_LOOK_AHEAD_STEP);
  }

  private static final int calcDirectOffset(int paramInt)
  {
    return paramInt;
  }

  private static final int calcWrappedOffset(long paramLong, int paramInt)
  {
    return calcDirectOffset(paramInt & (int)paramLong);
  }

  private long lpConsumerIndex()
  {
    return this.consumerIndex;
  }

  private long lpProducerIndex()
  {
    return this.producerIndex;
  }

  private long lvConsumerIndex()
  {
    return this.consumerIndex;
  }

  private static final <E> Object lvElement(AtomicReferenceArray<Object> paramAtomicReferenceArray, int paramInt)
  {
    return paramAtomicReferenceArray.get(paramInt);
  }

  private AtomicReferenceArray<Object> lvNext(AtomicReferenceArray<Object> paramAtomicReferenceArray)
  {
    return (AtomicReferenceArray)lvElement(paramAtomicReferenceArray, calcDirectOffset(-1 + paramAtomicReferenceArray.length()));
  }

  private long lvProducerIndex()
  {
    return this.producerIndex;
  }

  private T newBufferPeek(AtomicReferenceArray<Object> paramAtomicReferenceArray, long paramLong, int paramInt)
  {
    this.consumerBuffer = paramAtomicReferenceArray;
    return lvElement(paramAtomicReferenceArray, calcWrappedOffset(paramLong, paramInt));
  }

  private T newBufferPoll(AtomicReferenceArray<Object> paramAtomicReferenceArray, long paramLong, int paramInt)
  {
    this.consumerBuffer = paramAtomicReferenceArray;
    int i = calcWrappedOffset(paramLong, paramInt);
    Object localObject = lvElement(paramAtomicReferenceArray, i);
    if (localObject == null)
      localObject = null;
    while (true)
    {
      return localObject;
      soConsumerIndex(1L + paramLong);
      soElement(paramAtomicReferenceArray, i, null);
    }
  }

  private void resize(AtomicReferenceArray<Object> paramAtomicReferenceArray, long paramLong1, int paramInt, T paramT, long paramLong2)
  {
    AtomicReferenceArray localAtomicReferenceArray = new AtomicReferenceArray(paramAtomicReferenceArray.length());
    this.producerBuffer = localAtomicReferenceArray;
    this.producerLookAhead = (paramLong1 + paramLong2 - 1L);
    soProducerIndex(paramLong1 + 1L);
    soElement(localAtomicReferenceArray, paramInt, paramT);
    soNext(paramAtomicReferenceArray, localAtomicReferenceArray);
    soElement(paramAtomicReferenceArray, paramInt, HAS_NEXT);
  }

  private void soConsumerIndex(long paramLong)
  {
    CONSUMER_INDEX.lazySet(this, paramLong);
  }

  private static final void soElement(AtomicReferenceArray<Object> paramAtomicReferenceArray, int paramInt, Object paramObject)
  {
    paramAtomicReferenceArray.lazySet(paramInt, paramObject);
  }

  private void soNext(AtomicReferenceArray<Object> paramAtomicReferenceArray1, AtomicReferenceArray<Object> paramAtomicReferenceArray2)
  {
    soElement(paramAtomicReferenceArray1, calcDirectOffset(-1 + paramAtomicReferenceArray1.length()), paramAtomicReferenceArray2);
  }

  private void soProducerIndex(long paramLong)
  {
    PRODUCER_INDEX.lazySet(this, paramLong);
  }

  private boolean writeToQueue(AtomicReferenceArray<Object> paramAtomicReferenceArray, T paramT, long paramLong, int paramInt)
  {
    soProducerIndex(1L + paramLong);
    soElement(paramAtomicReferenceArray, paramInt, paramT);
    return true;
  }

  public boolean add(T paramT)
  {
    throw new UnsupportedOperationException();
  }

  public boolean addAll(Collection<? extends T> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public void clear()
  {
    while ((poll() != null) || (!isEmpty()));
  }

  public boolean contains(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public boolean containsAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public T element()
  {
    throw new UnsupportedOperationException();
  }

  public boolean isEmpty()
  {
    if (lvProducerIndex() == lvConsumerIndex());
    for (int i = 1; ; i = 0)
      return i;
  }

  public final Iterator<T> iterator()
  {
    throw new UnsupportedOperationException();
  }

  public final boolean offer(T paramT)
  {
    AtomicReferenceArray localAtomicReferenceArray = this.producerBuffer;
    long l = lpProducerIndex();
    int i = this.producerMask;
    int j = calcWrappedOffset(l, i);
    boolean bool;
    if (l < this.producerLookAhead)
      bool = writeToQueue(localAtomicReferenceArray, paramT, l, j);
    while (true)
    {
      return bool;
      int k = this.producerLookAheadStep;
      if (lvElement(localAtomicReferenceArray, calcWrappedOffset(l + k, i)) == null)
      {
        this.producerLookAhead = (l + k - 1L);
        bool = writeToQueue(localAtomicReferenceArray, paramT, l, j);
        continue;
      }
      if (lvElement(localAtomicReferenceArray, calcWrappedOffset(1L + l, i)) == null)
      {
        bool = writeToQueue(localAtomicReferenceArray, paramT, l, j);
        continue;
      }
      resize(localAtomicReferenceArray, l, j, paramT, i);
      bool = true;
    }
  }

  public boolean offer(T paramT1, T paramT2)
  {
    AtomicReferenceArray localAtomicReferenceArray1 = this.producerBuffer;
    long l = this.producerIndex;
    int i = this.producerMask;
    if (lvElement(localAtomicReferenceArray1, calcWrappedOffset(l + 2L, i)) == null)
    {
      int k = calcWrappedOffset(l, i);
      soElement(localAtomicReferenceArray1, k + 1, paramT2);
      soProducerIndex(l + 2L);
      soElement(localAtomicReferenceArray1, k, paramT1);
    }
    while (true)
    {
      return true;
      AtomicReferenceArray localAtomicReferenceArray2 = new AtomicReferenceArray(localAtomicReferenceArray1.length());
      this.producerBuffer = localAtomicReferenceArray2;
      int j = calcWrappedOffset(l, i);
      soElement(localAtomicReferenceArray2, j + 1, paramT2);
      soElement(localAtomicReferenceArray2, j, paramT1);
      soNext(localAtomicReferenceArray1, localAtomicReferenceArray2);
      soProducerIndex(l + 2L);
      soElement(localAtomicReferenceArray1, j, HAS_NEXT);
    }
  }

  public final T peek()
  {
    AtomicReferenceArray localAtomicReferenceArray = this.consumerBuffer;
    long l = lpConsumerIndex();
    int i = this.consumerMask;
    Object localObject = lvElement(localAtomicReferenceArray, calcWrappedOffset(l, i));
    if (localObject == HAS_NEXT)
      localObject = newBufferPeek(lvNext(localAtomicReferenceArray), l, i);
    return localObject;
  }

  public final T poll()
  {
    AtomicReferenceArray localAtomicReferenceArray = this.consumerBuffer;
    long l = lpConsumerIndex();
    int i = this.consumerMask;
    int j = calcWrappedOffset(l, i);
    Object localObject = lvElement(localAtomicReferenceArray, j);
    int k;
    if (localObject == HAS_NEXT)
    {
      k = 1;
      if ((localObject == null) || (k != 0))
        break label76;
      soConsumerIndex(1L + l);
      soElement(localAtomicReferenceArray, j, null);
    }
    while (true)
    {
      return localObject;
      k = 0;
      break;
      label76: if (k != 0)
      {
        localObject = newBufferPoll(lvNext(localAtomicReferenceArray), l, i);
        continue;
      }
      localObject = null;
    }
  }

  public T remove()
  {
    throw new UnsupportedOperationException();
  }

  public boolean remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public boolean removeAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public boolean retainAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public final int size()
  {
    long l1 = lvConsumerIndex();
    long l2;
    long l3;
    do
    {
      l2 = l1;
      l3 = lvProducerIndex();
      l1 = lvConsumerIndex();
    }
    while (l2 != l1);
    return (int)(l3 - l1);
  }

  public Object[] toArray()
  {
    throw new UnsupportedOperationException();
  }

  public <E> E[] toArray(E[] paramArrayOfE)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.atomic.SpscLinkedArrayQueue
 * JD-Core Version:    0.6.0
 */