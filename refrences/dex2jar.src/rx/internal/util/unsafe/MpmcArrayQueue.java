package rx.internal.util.unsafe;

public class MpmcArrayQueue<E> extends MpmcArrayQueueConsumerField<E>
{
  long p30;
  long p31;
  long p32;
  long p33;
  long p34;
  long p35;
  long p36;
  long p37;
  long p40;
  long p41;
  long p42;
  long p43;
  long p44;
  long p45;
  long p46;

  public MpmcArrayQueue(int paramInt)
  {
    super(Math.max(2, paramInt));
  }

  public boolean isEmpty()
  {
    if (lvConsumerIndex() == lvProducerIndex());
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean offer(E paramE)
  {
    if (paramE == null)
      throw new NullPointerException("Null is not a valid element");
    long l1 = 1L + this.mask;
    long[] arrayOfLong = this.sequenceBuffer;
    long l2 = 9223372036854775807L;
    long l3;
    long l5;
    while (true)
    {
      l3 = lvProducerIndex();
      long l4 = calcSequenceOffset(l3);
      l5 = lvSequence(arrayOfLong, l4) - l3;
      if (l5 != 0L)
        break;
      if (!casProducerIndex(l3, 1L + l3))
        continue;
      spElement(calcElementOffset(l3), paramE);
      soSequence(arrayOfLong, l4, 1L + l3);
    }
    for (int i = 1; ; i = 0)
    {
      return i;
      if ((l5 >= 0L) || (l3 - l1 > l2))
        break;
      long l6 = l3 - l1;
      l2 = lvConsumerIndex();
      if (l6 > l2)
        break;
    }
  }

  public E peek()
  {
    long l;
    Object localObject;
    do
    {
      l = lvConsumerIndex();
      localObject = lpElement(calcElementOffset(l));
    }
    while ((localObject == null) && (l != lvProducerIndex()));
    return localObject;
  }

  public E poll()
  {
    long[] arrayOfLong = this.sequenceBuffer;
    long l1 = -1L;
    long l2;
    long l4;
    Object localObject;
    while (true)
    {
      l2 = lvConsumerIndex();
      long l3 = calcSequenceOffset(l2);
      l4 = lvSequence(arrayOfLong, l3) - (1L + l2);
      if (l4 != 0L)
        break;
      if (!casConsumerIndex(l2, 1L + l2))
        continue;
      long l5 = calcElementOffset(l2);
      localObject = lpElement(l5);
      spElement(l5, null);
      soSequence(arrayOfLong, l3, 1L + (l2 + this.mask));
    }
    while (true)
    {
      return localObject;
      if ((l4 >= 0L) || (l2 < l1))
        break;
      l1 = lvProducerIndex();
      if (l2 != l1)
        break;
      localObject = null;
    }
  }

  public int size()
  {
    long l1 = lvConsumerIndex();
    long l2;
    long l3;
    do
    {
      l2 = l1;
      l3 = lvProducerIndex();
      l1 = lvConsumerIndex();
    }
    while (l2 != l1);
    return (int)(l3 - l1);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.MpmcArrayQueue
 * JD-Core Version:    0.6.0
 */