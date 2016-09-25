package com.nostra13.universalimageloader.core.assist.deque;

public class LIFOLinkedBlockingDeque<T> extends LinkedBlockingDeque<T>
{
  private static final long serialVersionUID = -4114786347960826192L;

  public boolean offer(T paramT)
  {
    return super.offerFirst(paramT);
  }

  public T remove()
  {
    return super.removeFirst();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.assist.deque.LIFOLinkedBlockingDeque
 * JD-Core Version:    0.6.0
 */