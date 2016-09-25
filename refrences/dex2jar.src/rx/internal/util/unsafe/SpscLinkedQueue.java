package rx.internal.util.unsafe;

import rx.internal.util.atomic.LinkedQueueNode;

public final class SpscLinkedQueue<E> extends BaseLinkedQueue<E>
{
  public SpscLinkedQueue()
  {
    spProducerNode(new LinkedQueueNode());
    spConsumerNode(this.producerNode);
    this.consumerNode.soNext(null);
  }

  public boolean offer(E paramE)
  {
    if (paramE == null)
      throw new NullPointerException("null elements not allowed");
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode(paramE);
    this.producerNode.soNext(localLinkedQueueNode);
    this.producerNode = localLinkedQueueNode;
    return true;
  }

  public E peek()
  {
    LinkedQueueNode localLinkedQueueNode = this.consumerNode.lvNext();
    if (localLinkedQueueNode != null);
    for (Object localObject = localLinkedQueueNode.lpValue(); ; localObject = null)
      return localObject;
  }

  public E poll()
  {
    LinkedQueueNode localLinkedQueueNode = this.consumerNode.lvNext();
    Object localObject;
    if (localLinkedQueueNode != null)
    {
      localObject = localLinkedQueueNode.getAndNullValue();
      this.consumerNode = localLinkedQueueNode;
    }
    while (true)
    {
      return localObject;
      localObject = null;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.unsafe.SpscLinkedQueue
 * JD-Core Version:    0.6.0
 */