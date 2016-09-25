package rx.internal.util.unsafe;

abstract class SpscArrayQueueProducerFields<E> extends SpscArrayQueueL1Pad<E>
{
  protected static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueProducerFields.class, "producerIndex");
  protected long producerIndex;
  protected long producerLookAhead;

  public SpscArrayQueueProducerFields(int paramInt)
  {
    super(paramInt);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.SpscArrayQueueProducerFields
 * JD-Core Version:    0.6.0
 */