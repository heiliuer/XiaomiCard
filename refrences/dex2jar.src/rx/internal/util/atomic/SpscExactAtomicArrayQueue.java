package rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.internal.util.unsafe.Pow2;

public final class SpscExactAtomicArrayQueue<T> extends AtomicReferenceArray<T>
  implements Queue<T>
{
  static final AtomicLongFieldUpdater<SpscExactAtomicArrayQueue> CONSUMER_INDEX;
  static final AtomicLongFieldUpdater<SpscExactAtomicArrayQueue> PRODUCER_INDEX = AtomicLongFieldUpdater.newUpdater(SpscExactAtomicArrayQueue.class, "producerIndex");
  private static final long serialVersionUID = 6210984603741293445L;
  final int capacitySkip;
  volatile long consumerIndex;
  final int mask;
  volatile long producerIndex;

  static
  {
    CONSUMER_INDEX = AtomicLongFieldUpdater.newUpdater(SpscExactAtomicArrayQueue.class, "consumerIndex");
  }

  public SpscExactAtomicArrayQueue(int paramInt)
  {
    super(Pow2.roundToPowerOfTwo(paramInt));
    int i = length();
    this.mask = (i - 1);
    this.capacitySkip = (i - paramInt);
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
    if (this.producerIndex == this.consumerIndex);
    for (int i = 1; ; i = 0)
      return i;
  }

  public Iterator<T> iterator()
  {
    throw new UnsupportedOperationException();
  }

  public boolean offer(T paramT)
  {
    if (paramT == null)
      throw new NullPointerException();
    long l = this.producerIndex;
    int i = this.mask;
    if (get(i & (int)(l + this.capacitySkip)) != null);
    for (int k = 0; ; k = 1)
    {
      return k;
      int j = i & (int)l;
      PRODUCER_INDEX.lazySet(this, 1L + l);
      lazySet(j, paramT);
    }
  }

  public T peek()
  {
    return get((int)this.consumerIndex & this.mask);
  }

  public T poll()
  {
    long l = this.consumerIndex;
    int i = (int)l & this.mask;
    Object localObject = get(i);
    if (localObject == null)
      localObject = null;
    while (true)
    {
      return localObject;
      CONSUMER_INDEX.lazySet(this, 1L + l);
      lazySet(i, null);
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

  public int size()
  {
    long l1 = this.consumerIndex;
    while (true)
    {
      long l2 = this.producerIndex;
      long l3 = this.consumerIndex;
      if (l1 == l3)
        return (int)(l2 - l3);
      l1 = l3;
    }
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
 * Qualified Name:     rx.internal.util.atomic.SpscExactAtomicArrayQueue
 * JD-Core Version:    0.6.0
 */