package rx.internal.operators;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observers.Subscribers;

public final class OnSubscribeUsing<T, Resource>
  implements Observable.OnSubscribe<T>
{
  private final Action1<? super Resource> dispose;
  private final boolean disposeEagerly;
  private final Func1<? super Resource, ? extends Observable<? extends T>> observableFactory;
  private final Func0<Resource> resourceFactory;

  public OnSubscribeUsing(Func0<Resource> paramFunc0, Func1<? super Resource, ? extends Observable<? extends T>> paramFunc1, Action1<? super Resource> paramAction1, boolean paramBoolean)
  {
    this.resourceFactory = paramFunc0;
    this.observableFactory = paramFunc1;
    this.dispose = paramAction1;
    this.disposeEagerly = paramBoolean;
  }

  private Throwable disposeEagerlyIfRequested(Action0 paramAction0)
  {
    Object localObject = null;
    if (this.disposeEagerly);
    try
    {
      paramAction0.call();
      label15: return localObject;
    }
    catch (Throwable localThrowable)
    {
      break label15;
    }
  }

  public void call(Subscriber<? super T> paramSubscriber)
  {
    try
    {
      Object localObject = this.resourceFactory.call();
      DisposeAction localDisposeAction = new DisposeAction(this.dispose, localObject, null);
      paramSubscriber.add(localDisposeAction);
      Observable localObservable1 = (Observable)this.observableFactory.call(localObject);
      Observable localObservable2;
      if (this.disposeEagerly)
      {
        Observable localObservable3 = localObservable1.doOnTerminate(localDisposeAction);
        localObservable2 = localObservable3;
      }
      while (true)
      {
        Throwable localThrowable3;
        try
        {
          localObservable2.unsafeSubscribe(Subscribers.wrap(paramSubscriber));
          return;
          localObservable2 = localObservable1;
          continue;
        }
        catch (Throwable localThrowable2)
        {
          localThrowable3 = disposeEagerlyIfRequested(localDisposeAction);
          Exceptions.throwIfFatal(localThrowable2);
          Exceptions.throwIfFatal(localThrowable3);
          if (localThrowable3 == null)
            break;
        }
        Throwable[] arrayOfThrowable = new Throwable[2];
        arrayOfThrowable[0] = localThrowable2;
        arrayOfThrowable[1] = localThrowable3;
        paramSubscriber.onError(new CompositeException(Arrays.asList(arrayOfThrowable)));
      }
    }
    catch (Throwable localThrowable1)
    {
      while (true)
      {
        Exceptions.throwOrReport(localThrowable1, paramSubscriber);
        continue;
        paramSubscriber.onError(localThrowable2);
      }
    }
  }

  private static final class DisposeAction<Resource> extends AtomicBoolean
    implements Action0, Subscription
  {
    private static final long serialVersionUID = 4262875056400218316L;
    private Action1<? super Resource> dispose;
    private Resource resource;

    private DisposeAction(Action1<? super Resource> paramAction1, Resource paramResource)
    {
      this.dispose = paramAction1;
      this.resource = paramResource;
      lazySet(false);
    }

    public void call()
    {
      if (compareAndSet(false, true));
      try
      {
        this.dispose.call(this.resource);
        return;
      }
      finally
      {
        this.resource = null;
        this.dispose = null;
      }
      throw localObject;
    }

    public boolean isUnsubscribed()
    {
      return get();
    }

    public void unsubscribe()
    {
      call();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OnSubscribeUsing
 * JD-Core Version:    0.6.0
 */