package cn.com.fmsh.util;

import dj;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FM_Collection<T, E>
{
  public E get(T paramT)
  {
    try
    {
      Object localObject2 = this.a.get(paramT);
      localObject1 = localObject2;
      return localObject1;
    }
    catch (dj localdj)
    {
      while (true)
        Object localObject1 = null;
    }
  }

  public Iterator<T> iterator()
  {
    try
    {
      Iterator localIterator2 = this.a.keySet().iterator();
      localIterator1 = localIterator2;
      return localIterator1;
    }
    catch (dj localdj)
    {
      while (true)
        Iterator localIterator1 = null;
    }
  }

  public void put(T paramT, E paramE)
  {
    try
    {
      this.a.put(paramT, paramE);
      label12: return;
    }
    catch (dj localdj)
    {
      break label12;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.util.FM_Collection
 * JD-Core Version:    0.6.0
 */