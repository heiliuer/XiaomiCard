package rx.internal.util.atomic;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

abstract class BaseLinkedAtomicQueue<E> extends AbstractQueue<E>
{
  private final AtomicReference<LinkedQueueNode<E>> consumerNode = new AtomicReference();
  private final AtomicReference<LinkedQueueNode<E>> producerNode = new AtomicReference();

  public final boolean isEmpty()
  {
    if (lvConsumerNode() == lvProducerNode());
    for (int i = 1; ; i = 0)
      return i;
  }

  public final Iterator<E> iterator()
  {
    throw new UnsupportedOperationException();
  }

  protected final LinkedQueueNode<E> lpConsumerNode()
  {
    return (LinkedQueueNode)this.consumerNode.get();
  }

  protected final LinkedQueueNode<E> lpProducerNode()
  {
    return (LinkedQueueNode)this.producerNode.get();
  }

  protected final LinkedQueueNode<E> lvConsumerNode()
  {
    return (LinkedQueueNode)this.consumerNode.get();
  }

  protected final LinkedQueueNode<E> lvProducerNode()
  {
    return (LinkedQueueNode)this.producerNode.get();
  }

  public final int size()
  {
    Object localObject = lvConsumerNode();
    LinkedQueueNode localLinkedQueueNode1 = lvProducerNode();
    for (int i = 0; (localObject != localLinkedQueueNode1) && (i < 2147483647); i++)
    {
      LinkedQueueNode localLinkedQueueNode2;
      do
        localLinkedQueueNode2 = ((LinkedQueueNode)localObject).lvNext();
      while (localLinkedQueueNode2 == null);
      localObject = localLinkedQueueNode2;
    }
    return i;
  }

  protected final void spConsumerNode(LinkedQueueNode<E> paramLinkedQueueNode)
  {
    this.consumerNode.lazySet(paramLinkedQueueNode);
  }

  protected final void spProducerNode(LinkedQueueNode<E> paramLinkedQueueNode)
  {
    this.producerNode.lazySet(paramLinkedQueueNode);
  }

  protected final LinkedQueueNode<E> xchgProducerNode(LinkedQueueNode<E> paramLinkedQueueNode)
  {
    return (LinkedQueueNode)this.producerNode.getAndSet(paramLinkedQueueNode);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.atomic.BaseLinkedAtomicQueue
 * JD-Core Version:    0.6.0
 */