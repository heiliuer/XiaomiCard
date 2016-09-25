package rx.internal.util.unsafe;

public abstract interface QueueProgressIndicators
{
  public abstract long currentConsumerIndex();

  public abstract long currentProducerIndex();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.QueueProgressIndicators
 * JD-Core Version:    0.6.0
 */