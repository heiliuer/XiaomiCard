package rx.internal.util.unsafe;

import java.util.Iterator;
import sun.misc.Unsafe;

public class SpscUnboundedArrayQueue<E> extends SpscUnboundedArrayQueueConsumerField<E>
  implements QueueProgressIndicators
{
  private static final long C_INDEX_OFFSET;
  private static final Object HAS_NEXT;
  static final int MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
  private static final long P_INDEX_OFFSET;
  private static final long REF_ARRAY_BASE;
  private static final int REF_ELEMENT_SHIFT;

  // ERROR //
  static
  {
    // Byte code:
    //   0: ldc 22
    //   2: sipush 4096
    //   5: invokestatic 28	java/lang/Integer:getInteger	(Ljava/lang/String;I)Ljava/lang/Integer;
    //   8: invokevirtual 32	java/lang/Integer:intValue	()I
    //   11: putstatic 34	rx/internal/util/unsafe/SpscUnboundedArrayQueue:MAX_LOOK_AHEAD_STEP	I
    //   14: new 36	java/lang/Object
    //   17: dup
    //   18: invokespecial 39	java/lang/Object:<init>	()V
    //   21: putstatic 41	rx/internal/util/unsafe/SpscUnboundedArrayQueue:HAS_NEXT	Ljava/lang/Object;
    //   24: getstatic 47	rx/internal/util/unsafe/UnsafeAccess:UNSAFE	Lsun/misc/Unsafe;
    //   27: ldc 49
    //   29: invokevirtual 55	sun/misc/Unsafe:arrayIndexScale	(Ljava/lang/Class;)I
    //   32: istore_0
    //   33: iconst_4
    //   34: iload_0
    //   35: if_icmpne +58 -> 93
    //   38: iconst_2
    //   39: putstatic 57	rx/internal/util/unsafe/SpscUnboundedArrayQueue:REF_ELEMENT_SHIFT	I
    //   42: getstatic 47	rx/internal/util/unsafe/UnsafeAccess:UNSAFE	Lsun/misc/Unsafe;
    //   45: ldc 49
    //   47: invokevirtual 60	sun/misc/Unsafe:arrayBaseOffset	(Ljava/lang/Class;)I
    //   50: i2l
    //   51: putstatic 62	rx/internal/util/unsafe/SpscUnboundedArrayQueue:REF_ARRAY_BASE	J
    //   54: ldc 64
    //   56: ldc 66
    //   58: invokevirtual 72	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   61: astore_2
    //   62: getstatic 47	rx/internal/util/unsafe/UnsafeAccess:UNSAFE	Lsun/misc/Unsafe;
    //   65: aload_2
    //   66: invokevirtual 76	sun/misc/Unsafe:objectFieldOffset	(Ljava/lang/reflect/Field;)J
    //   69: putstatic 78	rx/internal/util/unsafe/SpscUnboundedArrayQueue:P_INDEX_OFFSET	J
    //   72: ldc 5
    //   74: ldc 80
    //   76: invokevirtual 72	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   79: astore 4
    //   81: getstatic 47	rx/internal/util/unsafe/UnsafeAccess:UNSAFE	Lsun/misc/Unsafe;
    //   84: aload 4
    //   86: invokevirtual 76	sun/misc/Unsafe:objectFieldOffset	(Ljava/lang/reflect/Field;)J
    //   89: putstatic 82	rx/internal/util/unsafe/SpscUnboundedArrayQueue:C_INDEX_OFFSET	J
    //   92: return
    //   93: bipush 8
    //   95: iload_0
    //   96: if_icmpne +10 -> 106
    //   99: iconst_3
    //   100: putstatic 57	rx/internal/util/unsafe/SpscUnboundedArrayQueue:REF_ELEMENT_SHIFT	I
    //   103: goto -61 -> 42
    //   106: new 84	java/lang/IllegalStateException
    //   109: dup
    //   110: ldc 86
    //   112: invokespecial 89	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   115: athrow
    //   116: astore_1
    //   117: new 91	java/lang/RuntimeException
    //   120: dup
    //   121: aload_1
    //   122: invokespecial 94	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   125: athrow
    //   126: astore_3
    //   127: new 91	java/lang/RuntimeException
    //   130: dup
    //   131: aload_3
    //   132: invokespecial 94	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   135: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   54	72	116	java/lang/NoSuchFieldException
    //   72	92	126	java/lang/NoSuchFieldException
  }

  public SpscUnboundedArrayQueue(int paramInt)
  {
    int i = Pow2.roundToPowerOfTwo(paramInt);
    long l = i - 1;
    Object[] arrayOfObject = (Object[])new Object[i + 1];
    this.producerBuffer = arrayOfObject;
    this.producerMask = l;
    adjustLookAheadStep(i);
    this.consumerBuffer = arrayOfObject;
    this.consumerMask = l;
    this.producerLookAhead = (l - 1L);
    soProducerIndex(0L);
  }

  private void adjustLookAheadStep(int paramInt)
  {
    this.producerLookAheadStep = Math.min(paramInt / 4, MAX_LOOK_AHEAD_STEP);
  }

  private static final long calcDirectOffset(long paramLong)
  {
    return REF_ARRAY_BASE + (paramLong << REF_ELEMENT_SHIFT);
  }

  private static final long calcWrappedOffset(long paramLong1, long paramLong2)
  {
    return calcDirectOffset(paramLong1 & paramLong2);
  }

  private long lvConsumerIndex()
  {
    return UnsafeAccess.UNSAFE.getLongVolatile(this, C_INDEX_OFFSET);
  }

  private static final <E> Object lvElement(E[] paramArrayOfE, long paramLong)
  {
    return UnsafeAccess.UNSAFE.getObjectVolatile(paramArrayOfE, paramLong);
  }

  private E[] lvNext(E[] paramArrayOfE)
  {
    return (Object[])(Object[])lvElement(paramArrayOfE, calcDirectOffset(-1 + paramArrayOfE.length));
  }

  private long lvProducerIndex()
  {
    return UnsafeAccess.UNSAFE.getLongVolatile(this, P_INDEX_OFFSET);
  }

  private E newBufferPeek(E[] paramArrayOfE, long paramLong1, long paramLong2)
  {
    this.consumerBuffer = paramArrayOfE;
    return lvElement(paramArrayOfE, calcWrappedOffset(paramLong1, paramLong2));
  }

  private E newBufferPoll(E[] paramArrayOfE, long paramLong1, long paramLong2)
  {
    this.consumerBuffer = paramArrayOfE;
    long l = calcWrappedOffset(paramLong1, paramLong2);
    Object localObject = lvElement(paramArrayOfE, l);
    if (localObject == null)
      localObject = null;
    while (true)
    {
      return localObject;
      soConsumerIndex(1L + paramLong1);
      soElement(paramArrayOfE, l, null);
    }
  }

  private void resize(E[] paramArrayOfE, long paramLong1, long paramLong2, E paramE, long paramLong3)
  {
    Object[] arrayOfObject = (Object[])new Object[paramArrayOfE.length];
    this.producerBuffer = arrayOfObject;
    this.producerLookAhead = (paramLong1 + paramLong3 - 1L);
    soProducerIndex(paramLong1 + 1L);
    soElement(arrayOfObject, paramLong2, paramE);
    soNext(paramArrayOfE, arrayOfObject);
    soElement(paramArrayOfE, paramLong2, HAS_NEXT);
  }

  private void soConsumerIndex(long paramLong)
  {
    UnsafeAccess.UNSAFE.putOrderedLong(this, C_INDEX_OFFSET, paramLong);
  }

  private static final void soElement(Object[] paramArrayOfObject, long paramLong, Object paramObject)
  {
    UnsafeAccess.UNSAFE.putOrderedObject(paramArrayOfObject, paramLong, paramObject);
  }

  private void soNext(E[] paramArrayOfE1, E[] paramArrayOfE2)
  {
    soElement(paramArrayOfE1, calcDirectOffset(-1 + paramArrayOfE1.length), paramArrayOfE2);
  }

  private void soProducerIndex(long paramLong)
  {
    UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, paramLong);
  }

  private boolean writeToQueue(E[] paramArrayOfE, E paramE, long paramLong1, long paramLong2)
  {
    soProducerIndex(1L + paramLong1);
    soElement(paramArrayOfE, paramLong2, paramE);
    return true;
  }

  public long currentConsumerIndex()
  {
    return lvConsumerIndex();
  }

  public long currentProducerIndex()
  {
    return lvProducerIndex();
  }

  public final Iterator<E> iterator()
  {
    throw new UnsupportedOperationException();
  }

  public final boolean offer(E paramE)
  {
    if (paramE == null)
      throw new NullPointerException("Null is not a valid element");
    Object[] arrayOfObject = this.producerBuffer;
    long l1 = this.producerIndex;
    long l2 = this.producerMask;
    long l3 = calcWrappedOffset(l1, l2);
    boolean bool;
    if (l1 < this.producerLookAhead)
      bool = writeToQueue(arrayOfObject, paramE, l1, l3);
    while (true)
    {
      return bool;
      int i = this.producerLookAheadStep;
      if (lvElement(arrayOfObject, calcWrappedOffset(l1 + i, l2)) == null)
      {
        this.producerLookAhead = (l1 + i - 1L);
        bool = writeToQueue(arrayOfObject, paramE, l1, l3);
        continue;
      }
      if (lvElement(arrayOfObject, calcWrappedOffset(1L + l1, l2)) != null)
      {
        bool = writeToQueue(arrayOfObject, paramE, l1, l3);
        continue;
      }
      resize(arrayOfObject, l1, l3, paramE, l2);
      bool = true;
    }
  }

  public final E peek()
  {
    Object[] arrayOfObject = this.consumerBuffer;
    long l1 = this.consumerIndex;
    long l2 = this.consumerMask;
    Object localObject = lvElement(arrayOfObject, calcWrappedOffset(l1, l2));
    if (localObject == HAS_NEXT)
      localObject = newBufferPeek(lvNext(arrayOfObject), l1, l2);
    return localObject;
  }

  public final E poll()
  {
    Object[] arrayOfObject = this.consumerBuffer;
    long l1 = this.consumerIndex;
    long l2 = this.consumerMask;
    long l3 = calcWrappedOffset(l1, l2);
    Object localObject = lvElement(arrayOfObject, l3);
    int i;
    if (localObject == HAS_NEXT)
    {
      i = 1;
      if ((localObject == null) || (i != 0))
        break label76;
      soConsumerIndex(1L + l1);
      soElement(arrayOfObject, l3, null);
    }
    while (true)
    {
      return localObject;
      i = 0;
      break;
      label76: if (i != 0)
      {
        localObject = newBufferPoll(lvNext(arrayOfObject), l1, l2);
        continue;
      }
      localObject = null;
    }
  }

  public final int size()
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
 * Qualified Name:     rx.internal.util.unsafe.SpscUnboundedArrayQueue
 * JD-Core Version:    0.6.0
 */