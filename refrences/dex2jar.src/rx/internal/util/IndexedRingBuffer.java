package rx.internal.util;

import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.Subscription;
import rx.functions.Func1;

public final class IndexedRingBuffer<E>
  implements Subscription
{
  private static final ObjectPool<IndexedRingBuffer<?>> POOL = new ObjectPool()
  {
    protected IndexedRingBuffer<?> createObject()
    {
      return new IndexedRingBuffer(null);
    }
  };
  static final int SIZE;
  static int _size = 256;
  private final ElementSection<E> elements = new ElementSection(null);
  final AtomicInteger index = new AtomicInteger();
  private final IndexSection removed = new IndexSection(null);
  final AtomicInteger removedIndex = new AtomicInteger();

  static
  {
    if (PlatformDependent.isAndroid())
      _size = 8;
    String str = System.getProperty("rx.indexed-ring-buffer.size");
    if (str != null);
    try
    {
      _size = Integer.parseInt(str);
      SIZE = _size;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        System.err.println("Failed to set 'rx.indexed-ring-buffer.size' with value " + str + " => " + localException.getMessage());
    }
  }

  private int forEach(Func1<? super E, Boolean> paramFunc1, int paramInt1, int paramInt2)
  {
    int i = this.index.get();
    int j = paramInt1;
    ElementSection localElementSection = this.elements;
    if (paramInt1 >= SIZE)
    {
      localElementSection = getElementSection(paramInt1);
      paramInt1 %= SIZE;
    }
    while (true)
    {
      int m;
      if (localElementSection != null)
      {
        m = paramInt1;
        if (m >= SIZE)
          break label130;
        if ((j < i) && (j < paramInt2))
          break label76;
      }
      label76: int n;
      for (int k = j; ; k = n)
      {
        return k;
        Object localObject = localElementSection.array.get(m);
        if (localObject == null);
        do
        {
          m++;
          j++;
          break;
          n = j;
        }
        while (((Boolean)paramFunc1.call(localObject)).booleanValue());
      }
      label130: localElementSection = (ElementSection)localElementSection.next.get();
      paramInt1 = 0;
    }
  }

  private ElementSection<E> getElementSection(int paramInt)
  {
    ElementSection localElementSection;
    if (paramInt < SIZE)
      localElementSection = this.elements;
    while (true)
    {
      return localElementSection;
      int i = paramInt / SIZE;
      localElementSection = this.elements;
      for (int j = 0; j < i; j++)
        localElementSection = localElementSection.getNext();
    }
  }

  /** @deprecated */
  private int getIndexForAdd()
  {
    monitorenter;
    try
    {
      int i = getIndexFromPreviouslyRemoved();
      int k;
      if (i >= 0)
        if (i < SIZE)
        {
          k = this.removed.getAndSet(i, -1);
          if (k == this.index.get())
            this.index.getAndIncrement();
        }
      while (true)
      {
        return k;
        int m = i % SIZE;
        k = getIndexSection(i).getAndSet(m, -1);
        break;
        int j = this.index.getAndIncrement();
        k = j;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  private int getIndexFromPreviouslyRemoved()
  {
    monitorenter;
    try
    {
      while (true)
      {
        int i = this.removedIndex.get();
        if (i <= 0)
          break;
        boolean bool = this.removedIndex.compareAndSet(i, i - 1);
        if (!bool)
          continue;
        j = i - 1;
        return j;
      }
      int j = -1;
    }
    finally
    {
      monitorexit;
    }
  }

  private IndexSection getIndexSection(int paramInt)
  {
    IndexSection localIndexSection;
    if (paramInt < SIZE)
      localIndexSection = this.removed;
    while (true)
    {
      return localIndexSection;
      int i = paramInt / SIZE;
      localIndexSection = this.removed;
      for (int j = 0; j < i; j++)
        localIndexSection = localIndexSection.getNext();
    }
  }

  public static final <T> IndexedRingBuffer<T> getInstance()
  {
    return (IndexedRingBuffer)POOL.borrowObject();
  }

  /** @deprecated */
  private void pushRemovedIndex(int paramInt)
  {
    monitorenter;
    try
    {
      int i = this.removedIndex.getAndIncrement();
      if (i < SIZE)
        this.removed.set(i, paramInt);
      while (true)
      {
        return;
        int j = i % SIZE;
        getIndexSection(i).set(j, paramInt);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int add(E paramE)
  {
    int i = getIndexForAdd();
    if (i < SIZE)
      this.elements.array.set(i, paramE);
    while (true)
    {
      return i;
      int j = i % SIZE;
      getElementSection(i).array.set(j, paramE);
    }
  }

  public int forEach(Func1<? super E, Boolean> paramFunc1)
  {
    return forEach(paramFunc1, 0);
  }

  public int forEach(Func1<? super E, Boolean> paramFunc1, int paramInt)
  {
    int i = forEach(paramFunc1, paramInt, this.index.get());
    if ((paramInt > 0) && (i == this.index.get()))
      i = forEach(paramFunc1, 0, paramInt);
    while (true)
    {
      return i;
      if (i != this.index.get())
        continue;
      i = 0;
    }
  }

  public boolean isUnsubscribed()
  {
    return false;
  }

  public void releaseToPool()
  {
    int i = this.index.get();
    int j = 0;
    for (ElementSection localElementSection = this.elements; ; localElementSection = (ElementSection)localElementSection.next.get())
    {
      int k;
      if (localElementSection != null)
        k = 0;
      while (k < SIZE)
      {
        if (j >= i)
        {
          this.index.set(0);
          this.removedIndex.set(0);
          POOL.returnObject(this);
          return;
        }
        localElementSection.array.set(k, null);
        k++;
        j++;
      }
    }
  }

  public E remove(int paramInt)
  {
    if (paramInt < SIZE);
    int i;
    for (Object localObject = this.elements.array.getAndSet(paramInt, null); ; localObject = getElementSection(paramInt).array.getAndSet(i, null))
    {
      pushRemovedIndex(paramInt);
      return localObject;
      i = paramInt % SIZE;
    }
  }

  public void unsubscribe()
  {
    releaseToPool();
  }

  private static class IndexSection
  {
    private final AtomicReference<IndexSection> _next = new AtomicReference();
    private final AtomicIntegerArray unsafeArray = new AtomicIntegerArray(IndexedRingBuffer.SIZE);

    public int getAndSet(int paramInt1, int paramInt2)
    {
      return this.unsafeArray.getAndSet(paramInt1, paramInt2);
    }

    IndexSection getNext()
    {
      Object localObject;
      if (this._next.get() != null)
        localObject = (IndexSection)this._next.get();
      while (true)
      {
        return localObject;
        IndexSection localIndexSection = new IndexSection();
        if (this._next.compareAndSet(null, localIndexSection))
        {
          localObject = localIndexSection;
          continue;
        }
        localObject = (IndexSection)this._next.get();
      }
    }

    public void set(int paramInt1, int paramInt2)
    {
      this.unsafeArray.set(paramInt1, paramInt2);
    }
  }

  private static class ElementSection<E>
  {
    private final AtomicReferenceArray<E> array = new AtomicReferenceArray(IndexedRingBuffer.SIZE);
    private final AtomicReference<ElementSection<E>> next = new AtomicReference();

    ElementSection<E> getNext()
    {
      Object localObject;
      if (this.next.get() != null)
        localObject = (ElementSection)this.next.get();
      while (true)
      {
        return localObject;
        ElementSection localElementSection = new ElementSection();
        if (this.next.compareAndSet(null, localElementSection))
        {
          localObject = localElementSection;
          continue;
        }
        localObject = (ElementSection)this.next.get();
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.IndexedRingBuffer
 * JD-Core Version:    0.6.0
 */