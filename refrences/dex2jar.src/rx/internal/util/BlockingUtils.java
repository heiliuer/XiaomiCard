package rx.internal.util;

import java.util.concurrent.CountDownLatch;
import rx.Subscription;
import rx.annotations.Experimental;

@Experimental
public final class BlockingUtils
{
  @Experimental
  public static void awaitForComplete(CountDownLatch paramCountDownLatch, Subscription paramSubscription)
  {
    if (paramCountDownLatch.getCount() == 0L);
    while (true)
    {
      return;
      try
      {
        paramCountDownLatch.await();
      }
      catch (InterruptedException localInterruptedException)
      {
        paramSubscription.unsubscribe();
        Thread.currentThread().interrupt();
      }
    }
    throw new RuntimeException("Interrupted while waiting for subscription to complete.", localInterruptedException);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.BlockingUtils
 * JD-Core Version:    0.6.0
 */