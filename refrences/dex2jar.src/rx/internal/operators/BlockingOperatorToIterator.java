package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import rx.Notification;
import rx.Notification<+TT;>;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.util.RxRingBuffer;

public final class BlockingOperatorToIterator
{
  private BlockingOperatorToIterator()
  {
    throw new IllegalStateException("No instances!");
  }

  public static <T> Iterator<T> toIterator(Observable<? extends T> paramObservable)
  {
    SubscriberIterator localSubscriberIterator = new SubscriberIterator();
    paramObservable.materialize().subscribe(localSubscriberIterator);
    return localSubscriberIterator;
  }

  public static final class SubscriberIterator<T> extends Subscriber<Notification<? extends T>>
    implements Iterator<T>
  {
    static final int LIMIT = 3 * RxRingBuffer.SIZE / 4;
    private Notification<? extends T> buf;
    private final BlockingQueue<Notification<? extends T>> notifications = new LinkedBlockingQueue();
    private int received;

    private Notification<? extends T> take()
    {
      Object localObject;
      try
      {
        localObject = (Notification)this.notifications.poll();
        if (localObject == null)
        {
          Notification localNotification = (Notification)this.notifications.take();
          localObject = localNotification;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        unsubscribe();
        throw Exceptions.propagate(localInterruptedException);
      }
      return (Notification<? extends T>)localObject;
    }

    public boolean hasNext()
    {
      int i = 0;
      if (this.buf == null)
      {
        this.buf = take();
        this.received = (1 + this.received);
        if (this.received >= LIMIT)
        {
          request(this.received);
          this.received = 0;
        }
      }
      if (this.buf.isOnError())
        throw Exceptions.propagate(this.buf.getThrowable());
      if (!this.buf.isOnCompleted())
        i = 1;
      return i;
    }

    public T next()
    {
      if (hasNext())
      {
        Object localObject = this.buf.getValue();
        this.buf = null;
        return localObject;
      }
      throw new NoSuchElementException();
    }

    public void onCompleted()
    {
    }

    public void onError(Throwable paramThrowable)
    {
      this.notifications.offer(Notification.createOnError(paramThrowable));
    }

    public void onNext(Notification<? extends T> paramNotification)
    {
      this.notifications.offer(paramNotification);
    }

    public void onStart()
    {
      request(RxRingBuffer.SIZE);
    }

    public void remove()
    {
      throw new UnsupportedOperationException("Read-only iterator");
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.BlockingOperatorToIterator
 * JD-Core Version:    0.6.0
 */