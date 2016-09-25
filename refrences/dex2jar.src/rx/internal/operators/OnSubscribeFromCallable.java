package rx.internal.operators;

import java.util.concurrent.Callable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.producers.SingleDelayedProducer;

public final class OnSubscribeFromCallable<T>
  implements Observable.OnSubscribe<T>
{
  private final Callable<? extends T> resultFactory;

  public OnSubscribeFromCallable(Callable<? extends T> paramCallable)
  {
    this.resultFactory = paramCallable;
  }

  public void call(Subscriber<? super T> paramSubscriber)
  {
    SingleDelayedProducer localSingleDelayedProducer = new SingleDelayedProducer(paramSubscriber);
    paramSubscriber.setProducer(localSingleDelayedProducer);
    try
    {
      localSingleDelayedProducer.setValue(this.resultFactory.call());
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        Exceptions.throwIfFatal(localThrowable);
        paramSubscriber.onError(localThrowable);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OnSubscribeFromCallable
 * JD-Core Version:    0.6.0
 */