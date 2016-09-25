package rx.singles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import rx.Single;
import rx.SingleSubscriber;
import rx.annotations.Experimental;
import rx.internal.operators.BlockingOperatorToFuture;
import rx.internal.util.BlockingUtils;

@Experimental
public class BlockingSingle<T>
{
  private final Single<? extends T> single;

  private BlockingSingle(Single<? extends T> paramSingle)
  {
    this.single = paramSingle;
  }

  @Experimental
  public static <T> BlockingSingle<T> from(Single<? extends T> paramSingle)
  {
    return new BlockingSingle(paramSingle);
  }

  @Experimental
  public Future<T> toFuture()
  {
    return BlockingOperatorToFuture.toFuture(this.single.toObservable());
  }

  @Experimental
  public T value()
  {
    AtomicReference localAtomicReference1 = new AtomicReference();
    AtomicReference localAtomicReference2 = new AtomicReference();
    CountDownLatch localCountDownLatch = new CountDownLatch(1);
    BlockingUtils.awaitForComplete(localCountDownLatch, this.single.subscribe(new SingleSubscriber(localAtomicReference1, localCountDownLatch, localAtomicReference2)
    {
      public void onError(Throwable paramThrowable)
      {
        this.val$returnException.set(paramThrowable);
        this.val$latch.countDown();
      }

      public void onSuccess(T paramT)
      {
        this.val$returnItem.set(paramT);
        this.val$latch.countDown();
      }
    }));
    Throwable localThrowable = (Throwable)localAtomicReference2.get();
    if (localThrowable != null)
    {
      if ((localThrowable instanceof RuntimeException))
        throw ((RuntimeException)localThrowable);
      throw new RuntimeException(localThrowable);
    }
    return localAtomicReference1.get();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.singles.BlockingSingle
 * JD-Core Version:    0.6.0
 */