package rx.internal.util.unsafe;

abstract class SpscUnboundedArrayQueueProducerColdFields<E> extends SpscUnboundedArrayQueueProducerFields<E>
{
  protected E[] producerBuffer;
  protected long producerLookAhead;
  protected int producerLookAheadStep;
  protected long producerMask;
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.SpscUnboundedArrayQueueProducerColdFields
 * JD-Core Version:    0.6.0
 */