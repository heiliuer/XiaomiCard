package rx.internal.util.unsafe;

abstract interface MessagePassingQueue<M>
{
  public abstract boolean isEmpty();

  public abstract boolean offer(M paramM);

  public abstract M peek();

  public abstract M poll();

  public abstract int size();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.MessagePassingQueue
 * JD-Core Version:    0.6.0
 */