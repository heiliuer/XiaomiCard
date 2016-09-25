package rx.internal.util;

import java.util.ArrayList;
import java.util.List;

public class LinkedArrayList
{
  final int capacityHint;
  Object[] head;
  int indexInTail;
  volatile int size;
  Object[] tail;

  public LinkedArrayList(int paramInt)
  {
    this.capacityHint = paramInt;
  }

  public void add(Object paramObject)
  {
    if (this.size == 0)
    {
      this.head = new Object[1 + this.capacityHint];
      this.tail = this.head;
      this.head[0] = paramObject;
      this.indexInTail = 1;
      this.size = 1;
    }
    while (true)
    {
      return;
      if (this.indexInTail == this.capacityHint)
      {
        Object[] arrayOfObject = new Object[1 + this.capacityHint];
        arrayOfObject[0] = paramObject;
        this.tail[this.capacityHint] = arrayOfObject;
        this.tail = arrayOfObject;
        this.indexInTail = 1;
        this.size = (1 + this.size);
        continue;
      }
      this.tail[this.indexInTail] = paramObject;
      this.indexInTail = (1 + this.indexInTail);
      this.size = (1 + this.size);
    }
  }

  public int capacityHint()
  {
    return this.capacityHint;
  }

  public Object[] head()
  {
    return this.head;
  }

  public int indexInTail()
  {
    return this.indexInTail;
  }

  public int size()
  {
    return this.size;
  }

  public Object[] tail()
  {
    return this.tail;
  }

  List<Object> toList()
  {
    int i = this.capacityHint;
    int j = this.size;
    ArrayList localArrayList = new ArrayList(j + 1);
    Object[] arrayOfObject = head();
    int k = 0;
    int m = 0;
    while (k < j)
    {
      localArrayList.add(arrayOfObject[m]);
      k++;
      m++;
      if (m != i)
        continue;
      m = 0;
      arrayOfObject = (Object[])(Object[])arrayOfObject[i];
    }
    return localArrayList;
  }

  public String toString()
  {
    return toList().toString();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.LinkedArrayList
 * JD-Core Version:    0.6.0
 */