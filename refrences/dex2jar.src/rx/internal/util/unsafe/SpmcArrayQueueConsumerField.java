package rx.internal.util.unsafe;

import sun.misc.Unsafe;

abstract class SpmcArrayQueueConsumerField<E> extends SpmcArrayQueueL2Pad<E>
{
  protected static final long C_INDEX_OFFSET = UnsafeAccess.addressOf(SpmcArrayQueueConsumerField.class, "consumerIndex");
  private volatile long consumerIndex;

  public SpmcArrayQueueConsumerField(int paramInt)
  {
    super(paramInt);
  }

  protected final boolean casHead(long paramLong1, long paramLong2)
  {
    return UnsafeAccess.UNSAFE.compareAndSwapLong(this, C_INDEX_OFFSET, paramLong1, paramLong2);
  }

  protected final long lvConsumerIndex()
  {
    return this.consumerIndex;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.SpmcArrayQueueConsumerField
 * JD-Core Version:    0.6.0
 */