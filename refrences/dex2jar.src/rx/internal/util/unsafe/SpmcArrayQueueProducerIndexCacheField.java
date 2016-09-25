package rx.internal.util.unsafe;

abstract class SpmcArrayQueueProducerIndexCacheField<E> extends SpmcArrayQueueMidPad<E>
{
  private volatile long producerIndexCache;

  public SpmcArrayQueueProducerIndexCacheField(int paramInt)
  {
    super(paramInt);
  }

  protected final long lvProducerIndexCache()
  {
    return this.producerIndexCache;
  }

  protected final void svProducerIndexCache(long paramLong)
  {
    this.producerIndexCache = paramLong;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.SpmcArrayQueueProducerIndexCacheField
 * JD-Core Version:    0.6.0
 */