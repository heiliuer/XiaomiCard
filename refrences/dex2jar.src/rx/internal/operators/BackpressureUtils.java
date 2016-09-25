package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public final class BackpressureUtils
{
  private BackpressureUtils()
  {
    throw new IllegalStateException("No instances!");
  }

  public static long addCap(long paramLong1, long paramLong2)
  {
    long l = paramLong1 + paramLong2;
    if (l < 0L)
      l = 9223372036854775807L;
    return l;
  }

  public static long getAndAddRequest(AtomicLong paramAtomicLong, long paramLong)
  {
    long l;
    do
      l = paramAtomicLong.get();
    while (!paramAtomicLong.compareAndSet(l, addCap(l, paramLong)));
    return l;
  }

  public static <T> long getAndAddRequest(AtomicLongFieldUpdater<T> paramAtomicLongFieldUpdater, T paramT, long paramLong)
  {
    long l;
    do
      l = paramAtomicLongFieldUpdater.get(paramT);
    while (!paramAtomicLongFieldUpdater.compareAndSet(paramT, l, addCap(l, paramLong)));
    return l;
  }

  public static long multiplyCap(long paramLong1, long paramLong2)
  {
    long l = paramLong1 * paramLong2;
    if (((paramLong1 | paramLong2) >>> 31 != 0L) && (paramLong2 != 0L) && (l / paramLong2 != paramLong1))
      l = 9223372036854775807L;
    return l;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.BackpressureUtils
 * JD-Core Version:    0.6.0
 */