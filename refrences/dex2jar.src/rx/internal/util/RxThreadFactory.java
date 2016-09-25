package rx.internal.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class RxThreadFactory extends AtomicLong
  implements ThreadFactory
{
  final String prefix;

  public RxThreadFactory(String paramString)
  {
    this.prefix = paramString;
  }

  public Thread newThread(Runnable paramRunnable)
  {
    Thread localThread = new Thread(paramRunnable, this.prefix + incrementAndGet());
    localThread.setDaemon(true);
    return localThread;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.RxThreadFactory
 * JD-Core Version:    0.6.0
 */