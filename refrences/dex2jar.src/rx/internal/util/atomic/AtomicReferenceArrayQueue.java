package rx.internal.util.atomic;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.internal.util.unsafe.Pow2;

abstract class AtomicReferenceArrayQueue<E> extends AbstractQueue<E>
{
  protected final AtomicReferenceArray<E> buffer;
  protected final int mask;

  public AtomicReferenceArrayQueue(int paramInt)
  {
    int i = Pow2.roundToPowerOfTwo(paramInt);
    this.mask = (i - 1);
    this.buffer = new AtomicReferenceArray(i);
  }

  protected final int calcElementOffset(long paramLong)
  {
    return (int)paramLong & this.mask;
  }

  protected final int calcElementOffset(long paramLong, int paramInt)
  {
    return paramInt & (int)paramLong;
  }

  public void clear()
  {
    while ((poll() != null) || (!isEmpty()));
  }

  public Iterator<E> iterator()
  {
    throw new UnsupportedOperationException();
  }

  protected final E lpElement(int paramInt)
  {
    return this.buffer.get(paramInt);
  }

  protected final E lpElement(AtomicReferenceArray<E> paramAtomicReferenceArray, int paramInt)
  {
    return paramAtomicReferenceArray.get(paramInt);
  }

  protected final E lvElement(int paramInt)
  {
    return lvElement(this.buffer, paramInt);
  }

  protected final E lvElement(AtomicReferenceArray<E> paramAtomicReferenceArray, int paramInt)
  {
    return paramAtomicReferenceArray.get(paramInt);
  }

  protected final void soElement(int paramInt, E paramE)
  {
    this.buffer.lazySet(paramInt, paramE);
  }

  protected final void soElement(AtomicReferenceArray<E> paramAtomicReferenceArray, int paramInt, E paramE)
  {
    paramAtomicReferenceArray.lazySet(paramInt, paramE);
  }

  protected final void spElement(int paramInt, E paramE)
  {
    this.buffer.lazySet(paramInt, paramE);
  }

  protected final void spElement(AtomicReferenceArray<E> paramAtomicReferenceArray, int paramInt, E paramE)
  {
    paramAtomicReferenceArray.lazySet(paramInt, paramE);
  }

  protected final void svElement(AtomicReferenceArray<E> paramAtomicReferenceArray, int paramInt, E paramE)
  {
    paramAtomicReferenceArray.set(paramInt, paramE);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.atomic.AtomicReferenceArrayQueue
 * JD-Core Version:    0.6.0
 */