package rx.internal.producers;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

public final class SingleDelayedProducer<T> extends AtomicInteger
  implements Producer
{
  static final int HAS_REQUEST_HAS_VALUE = 3;
  static final int HAS_REQUEST_NO_VALUE = 2;
  static final int NO_REQUEST_HAS_VALUE = 1;
  static final int NO_REQUEST_NO_VALUE = 0;
  private static final long serialVersionUID = -2873467947112093874L;
  final Subscriber<? super T> child;
  T value;

  public SingleDelayedProducer(Subscriber<? super T> paramSubscriber)
  {
    this.child = paramSubscriber;
  }

  private static <T> void emit(Subscriber<? super T> paramSubscriber, T paramT)
  {
    if (paramSubscriber.isUnsubscribed());
    while (true)
    {
      return;
      try
      {
        paramSubscriber.onNext(paramT);
        if (paramSubscriber.isUnsubscribed())
          continue;
        paramSubscriber.onCompleted();
      }
      catch (Throwable localThrowable)
      {
        Exceptions.throwOrReport(localThrowable, paramSubscriber, paramT);
      }
    }
  }

  public void request(long paramLong)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("n >= 0 required");
    if (paramLong == 0L);
    while (true)
    {
      return;
      int i;
      while (true)
      {
        i = get();
        if (i != 0)
          break label44;
        if (compareAndSet(0, 2))
          break;
      }
      label44: if ((i != 1) || (!compareAndSet(1, 3)))
        continue;
      emit(this.child, this.value);
    }
  }

  public void setValue(T paramT)
  {
    int i;
    do
    {
      i = get();
      if (i != 0)
        break;
      this.value = paramT;
    }
    while (!compareAndSet(0, 1));
    while (true)
    {
      return;
      if ((i == 2) && (compareAndSet(2, 3)))
      {
        emit(this.child, paramT);
        continue;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.producers.SingleDelayedProducer
 * JD-Core Version:    0.6.0
 */