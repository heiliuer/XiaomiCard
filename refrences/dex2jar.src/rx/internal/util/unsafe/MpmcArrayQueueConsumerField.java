package rx.internal.util.unsafe;

import sun.misc.Unsafe;

abstract class MpmcArrayQueueConsumerField<E> extends MpmcArrayQueueL2Pad<E>
{
  private static final long C_INDEX_OFFSET = UnsafeAccess.addressOf(MpmcArrayQueueConsumerField.class, "consumerIndex");
  private volatile long consumerIndex;

  public MpmcArrayQueueConsumerField(int paramInt)
  {
    super(paramInt);
  }

  protected final boolean casConsumerIndex(long paramLong1, long paramLong2)
  {
    return UnsafeAccess.UNSAFE.compareAndSwapLong(this, C_INDEX_OFFSET, paramLong1, paramLong2);
  }

  protected final long lvConsumerIndex()
  {
    return this.consumerIndex;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.MpmcArrayQueueConsumerField
 * JD-Core Version:    0.6.0
 */