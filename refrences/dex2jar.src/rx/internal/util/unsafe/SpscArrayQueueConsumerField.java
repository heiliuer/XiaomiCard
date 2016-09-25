package rx.internal.util.unsafe;

abstract class SpscArrayQueueConsumerField<E> extends SpscArrayQueueL2Pad<E>
{
  protected static final long C_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueConsumerField.class, "consumerIndex");
  protected long consumerIndex;

  public SpscArrayQueueConsumerField(int paramInt)
  {
    super(paramInt);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.SpscArrayQueueConsumerField
 * JD-Core Version:    0.6.0
 */