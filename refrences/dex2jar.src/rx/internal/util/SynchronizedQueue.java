package rx.internal.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedQueue<T>
  implements Queue<T>
{
  private final LinkedList<T> list = new LinkedList();
  private final int size;

  public SynchronizedQueue()
  {
    this.size = -1;
  }

  public SynchronizedQueue(int paramInt)
  {
    this.size = paramInt;
  }

  /** @deprecated */
  public boolean add(T paramT)
  {
    monitorenter;
    try
    {
      boolean bool = this.list.add(paramT);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public boolean addAll(Collection<? extends T> paramCollection)
  {
    monitorenter;
    try
    {
      boolean bool = this.list.addAll(paramCollection);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void clear()
  {
    monitorenter;
    try
    {
      this.list.clear();
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

  /** @deprecated */
  public Object clone()
  {
    monitorenter;
    try
    {
      SynchronizedQueue localSynchronizedQueue = new SynchronizedQueue(this.size);
      localSynchronizedQueue.addAll(this.list);
      monitorexit;
      return localSynchronizedQueue;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public boolean contains(Object paramObject)
  {
    monitorenter;
    try
    {
      boolean bool = this.list.contains(paramObject);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public boolean containsAll(Collection<?> paramCollection)
  {
    monitorenter;
    try
    {
      boolean bool = this.list.containsAll(paramCollection);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public T element()
  {
    monitorenter;
    try
    {
      Object localObject2 = this.list.element();
      monitorexit;
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
    }
    throw localObject1;
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (this == paramObject);
    while (true)
    {
      return i;
      if (paramObject == null)
      {
        i = 0;
        continue;
      }
      if (getClass() != paramObject.getClass())
      {
        i = 0;
        continue;
      }
      SynchronizedQueue localSynchronizedQueue = (SynchronizedQueue)paramObject;
      if (this.list == null)
      {
        if (localSynchronizedQueue.list == null)
          continue;
        i = 0;
        continue;
      }
      if (this.list.equals(localSynchronizedQueue.list))
        continue;
      i = 0;
    }
  }

  public int hashCode()
  {
    return this.list.hashCode();
  }

  /** @deprecated */
  public boolean isEmpty()
  {
    monitorenter;
    try
    {
      boolean bool = this.list.isEmpty();
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public Iterator<T> iterator()
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.list.iterator();
      monitorexit;
      return localIterator;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public boolean offer(T paramT)
  {
    monitorenter;
    try
    {
      if (this.size > -1)
      {
        int j = 1 + this.list.size();
        int k = this.size;
        if (j <= k);
      }
      boolean bool;
      for (int i = 0; ; i = bool)
      {
        return i;
        bool = this.list.offer(paramT);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public T peek()
  {
    monitorenter;
    try
    {
      Object localObject2 = this.list.peek();
      monitorexit;
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
    }
    throw localObject1;
  }

  /** @deprecated */
  public T poll()
  {
    monitorenter;
    try
    {
      Object localObject2 = this.list.poll();
      monitorexit;
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
    }
    throw localObject1;
  }

  /** @deprecated */
  public T remove()
  {
    monitorenter;
    try
    {
      Object localObject2 = this.list.remove();
      monitorexit;
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
    }
    throw localObject1;
  }

  /** @deprecated */
  public boolean remove(Object paramObject)
  {
    monitorenter;
    try
    {
      boolean bool = this.list.remove(paramObject);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public boolean removeAll(Collection<?> paramCollection)
  {
    monitorenter;
    try
    {
      boolean bool = this.list.removeAll(paramCollection);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public boolean retainAll(Collection<?> paramCollection)
  {
    monitorenter;
    try
    {
      boolean bool = this.list.retainAll(paramCollection);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public int size()
  {
    monitorenter;
    try
    {
      int i = this.list.size();
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public Object[] toArray()
  {
    monitorenter;
    try
    {
      Object[] arrayOfObject = this.list.toArray();
      monitorexit;
      return arrayOfObject;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public <R> R[] toArray(R[] paramArrayOfR)
  {
    monitorenter;
    try
    {
      Object[] arrayOfObject = this.list.toArray(paramArrayOfR);
      monitorexit;
      return arrayOfObject;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public String toString()
  {
    monitorenter;
    try
    {
      String str = this.list.toString();
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.SynchronizedQueue
 * JD-Core Version:    0.6.0
 */