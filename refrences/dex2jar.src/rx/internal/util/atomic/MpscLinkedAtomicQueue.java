package rx.internal.util.atomic;

public final class MpscLinkedAtomicQueue<E> extends BaseLinkedAtomicQueue<E>
{
  public MpscLinkedAtomicQueue()
  {
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode();
    spConsumerNode(localLinkedQueueNode);
    xchgProducerNode(localLinkedQueueNode);
  }

  public final boolean offer(E paramE)
  {
    if (paramE == null)
      throw new NullPointerException("null elements not allowed");
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode(paramE);
    xchgProducerNode(localLinkedQueueNode).soNext(localLinkedQueueNode);
    return true;
  }

  public final E peek()
  {
    LinkedQueueNode localLinkedQueueNode1 = lpConsumerNode();
    LinkedQueueNode localLinkedQueueNode2 = localLinkedQueueNode1.lvNext();
    Object localObject;
    if (localLinkedQueueNode2 != null)
      localObject = localLinkedQueueNode2.lpValue();
    while (true)
    {
      return localObject;
      if (localLinkedQueueNode1 != lvProducerNode())
      {
        LinkedQueueNode localLinkedQueueNode3;
        do
          localLinkedQueueNode3 = localLinkedQueueNode1.lvNext();
        while (localLinkedQueueNode3 == null);
        localObject = localLinkedQueueNode3.lpValue();
        continue;
      }
      localObject = null;
    }
  }

  public final E poll()
  {
    LinkedQueueNode localLinkedQueueNode1 = lpConsumerNode();
    LinkedQueueNode localLinkedQueueNode2 = localLinkedQueueNode1.lvNext();
    Object localObject;
    if (localLinkedQueueNode2 != null)
    {
      localObject = localLinkedQueueNode2.getAndNullValue();
      spConsumerNode(localLinkedQueueNode2);
    }
    while (true)
    {
      return localObject;
      if (localLinkedQueueNode1 != lvProducerNode())
      {
        LinkedQueueNode localLinkedQueueNode3;
        do
          localLinkedQueueNode3 = localLinkedQueueNode1.lvNext();
        while (localLinkedQueueNode3 == null);
        localObject = localLinkedQueueNode3.getAndNullValue();
        spConsumerNode(localLinkedQueueNode3);
        continue;
      }
      localObject = null;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.atomic.MpscLinkedAtomicQueue
 * JD-Core Version:    0.6.0
 */