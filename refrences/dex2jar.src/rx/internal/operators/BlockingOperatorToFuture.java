package rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public final class BlockingOperatorToFuture
{
  private BlockingOperatorToFuture()
  {
    throw new IllegalStateException("No instances!");
  }

  public static <T> Future<T> toFuture(Observable<? extends T> paramObservable)
  {
    CountDownLatch localCountDownLatch = new CountDownLatch(1);
    AtomicReference localAtomicReference1 = new AtomicReference();
    AtomicReference localAtomicReference2 = new AtomicReference();
    return new Future(localCountDownLatch, paramObservable.single().subscribe(new Subscriber(localCountDownLatch, localAtomicReference2, localAtomicReference1)
    {
      public void onCompleted()
      {
        BlockingOperatorToFuture.this.countDown();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$error.compareAndSet(null, paramThrowable);
        BlockingOperatorToFuture.this.countDown();
      }

      public void onNext(T paramT)
      {
        this.val$value.set(paramT);
      }
    }), localAtomicReference2, localAtomicReference1)
    {
      private volatile boolean cancelled = false;

      private T getValue()
        throws ExecutionException
      {
        Throwable localThrowable = (Throwable)this.val$error.get();
        if (localThrowable != null)
          throw new ExecutionException("Observable onError", localThrowable);
        if (this.cancelled)
          throw new CancellationException("Subscription unsubscribed");
        return this.val$value.get();
      }

      public boolean cancel(boolean paramBoolean)
      {
        boolean bool = true;
        if (BlockingOperatorToFuture.this.getCount() > 0L)
        {
          this.cancelled = bool;
          this.val$s.unsubscribe();
          BlockingOperatorToFuture.this.countDown();
        }
        while (true)
        {
          return bool;
          bool = false;
        }
      }

      public T get()
        throws InterruptedException, ExecutionException
      {
        BlockingOperatorToFuture.this.await();
        return getValue();
      }

      public T get(long paramLong, TimeUnit paramTimeUnit)
        throws InterruptedException, ExecutionException, TimeoutException
      {
        if (BlockingOperatorToFuture.this.await(paramLong, paramTimeUnit))
          return getValue();
        throw new TimeoutException("Timed out after " + paramTimeUnit.toMillis(paramLong) + "ms waiting for underlying Observable.");
      }

      public boolean isCancelled()
      {
        return this.cancelled;
      }

      public boolean isDone()
      {
        if (BlockingOperatorToFuture.this.getCount() == 0L);
        for (int i = 1; ; i = 0)
          return i;
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.BlockingOperatorToFuture
 * JD-Core Version:    0.6.0
 */