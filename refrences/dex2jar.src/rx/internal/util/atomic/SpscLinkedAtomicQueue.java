package rx.internal.util.atomic;

public final class SpscLinkedAtomicQueue<E> extends BaseLinkedAtomicQueue<E>
{
  public SpscLinkedAtomicQueue()
  {
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode();
    spProducerNode(localLinkedQueueNode);
    spConsumerNode(localLinkedQueueNode);
    localLinkedQueueNode.soNext(null);
  }

  public boolean offer(E paramE)
  {
    if (paramE == null)
      throw new NullPointerException("null elements not allowed");
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode(paramE);
    lpProducerNode().soNext(localLinkedQueueNode);
    spProducerNode(localLinkedQueueNode);
    return true;
  }

  public E peek()
  {
    LinkedQueueNode localLinkedQueueNode = lpConsumerNode().lvNext();
    if (localLinkedQueueNode != null);
    for (Object localObject = localLinkedQueueNode.lpValue(); ; localObject = null)
      return localObject;
  }

  public E poll()
  {
    LinkedQueueNode localLinkedQueueNode = lpConsumerNode().lvNext();
    Object localObject;
    if (localLinkedQueueNode != null)
    {
      localObject = localLinkedQueueNode.getAndNullValue();
      spConsumerNode(localLinkedQueueNode);
    }
    while (true)
    {
      return localObject;
      localObject = null;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.atomic.SpscLinkedAtomicQueue
 * JD-Core Version:    0.6.0
 */