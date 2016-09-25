package rx.observables;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func1;
import rx.internal.operators.BlockingOperatorLatest;
import rx.internal.operators.BlockingOperatorMostRecent;
import rx.internal.operators.BlockingOperatorNext;
import rx.internal.operators.BlockingOperatorToFuture;
import rx.internal.operators.BlockingOperatorToIterator;
import rx.internal.operators.NotificationLite;
import rx.internal.util.BlockingUtils;
import rx.internal.util.UtilityFunctions;
import rx.subscriptions.Subscriptions;

public final class BlockingObservable<T>
{
  private static final Object ON_START = new Object();
  private static final Object SET_PRODUCER = new Object();
  private static final Object UNSUBSCRIBE = new Object();
  private final Observable<? extends T> o;

  private BlockingObservable(Observable<? extends T> paramObservable)
  {
    this.o = paramObservable;
  }

  private T blockForSingle(Observable<? extends T> paramObservable)
  {
    AtomicReference localAtomicReference1 = new AtomicReference();
    AtomicReference localAtomicReference2 = new AtomicReference();
    CountDownLatch localCountDownLatch = new CountDownLatch(1);
    BlockingUtils.awaitForComplete(localCountDownLatch, paramObservable.subscribe(new Subscriber(localCountDownLatch, localAtomicReference2, localAtomicReference1)
    {
      public void onCompleted()
      {
        this.val$latch.countDown();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$returnException.set(paramThrowable);
        this.val$latch.countDown();
      }

      public void onNext(T paramT)
      {
        this.val$returnItem.set(paramT);
      }
    }));
    if (localAtomicReference2.get() != null)
    {
      if ((localAtomicReference2.get() instanceof RuntimeException))
        throw ((RuntimeException)localAtomicReference2.get());
      throw new RuntimeException((Throwable)localAtomicReference2.get());
    }
    return localAtomicReference1.get();
  }

  public static <T> BlockingObservable<T> from(Observable<? extends T> paramObservable)
  {
    return new BlockingObservable(paramObservable);
  }

  public T first()
  {
    return blockForSingle(this.o.first());
  }

  public T first(Func1<? super T, Boolean> paramFunc1)
  {
    return blockForSingle(this.o.first(paramFunc1));
  }

  public T firstOrDefault(T paramT)
  {
    return blockForSingle(this.o.map(UtilityFunctions.identity()).firstOrDefault(paramT));
  }

  public T firstOrDefault(T paramT, Func1<? super T, Boolean> paramFunc1)
  {
    return blockForSingle(this.o.filter(paramFunc1).map(UtilityFunctions.identity()).firstOrDefault(paramT));
  }

  public void forEach(Action1<? super T> paramAction1)
  {
    CountDownLatch localCountDownLatch = new CountDownLatch(1);
    AtomicReference localAtomicReference = new AtomicReference();
    BlockingUtils.awaitForComplete(localCountDownLatch, this.o.subscribe(new Subscriber(localCountDownLatch, localAtomicReference, paramAction1)
    {
      public void onCompleted()
      {
        this.val$latch.countDown();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$exceptionFromOnError.set(paramThrowable);
        this.val$latch.countDown();
      }

      public void onNext(T paramT)
      {
        this.val$onNext.call(paramT);
      }
    }));
    if (localAtomicReference.get() != null)
    {
      if ((localAtomicReference.get() instanceof RuntimeException))
        throw ((RuntimeException)localAtomicReference.get());
      throw new RuntimeException((Throwable)localAtomicReference.get());
    }
  }

  public Iterator<T> getIterator()
  {
    return BlockingOperatorToIterator.toIterator(this.o);
  }

  public T last()
  {
    return blockForSingle(this.o.last());
  }

  public T last(Func1<? super T, Boolean> paramFunc1)
  {
    return blockForSingle(this.o.last(paramFunc1));
  }

  public T lastOrDefault(T paramT)
  {
    return blockForSingle(this.o.map(UtilityFunctions.identity()).lastOrDefault(paramT));
  }

  public T lastOrDefault(T paramT, Func1<? super T, Boolean> paramFunc1)
  {
    return blockForSingle(this.o.filter(paramFunc1).map(UtilityFunctions.identity()).lastOrDefault(paramT));
  }

  public Iterable<T> latest()
  {
    return BlockingOperatorLatest.latest(this.o);
  }

  public Iterable<T> mostRecent(T paramT)
  {
    return BlockingOperatorMostRecent.mostRecent(this.o, paramT);
  }

  public Iterable<T> next()
  {
    return BlockingOperatorNext.next(this.o);
  }

  public T single()
  {
    return blockForSingle(this.o.single());
  }

  public T single(Func1<? super T, Boolean> paramFunc1)
  {
    return blockForSingle(this.o.single(paramFunc1));
  }

  public T singleOrDefault(T paramT)
  {
    return blockForSingle(this.o.map(UtilityFunctions.identity()).singleOrDefault(paramT));
  }

  public T singleOrDefault(T paramT, Func1<? super T, Boolean> paramFunc1)
  {
    return blockForSingle(this.o.filter(paramFunc1).map(UtilityFunctions.identity()).singleOrDefault(paramT));
  }

  @Experimental
  public void subscribe()
  {
    CountDownLatch localCountDownLatch = new CountDownLatch(1);
    Throwable[] arrayOfThrowable = new Throwable[1];
    arrayOfThrowable[0] = null;
    BlockingUtils.awaitForComplete(localCountDownLatch, this.o.subscribe(new Subscriber(arrayOfThrowable, localCountDownLatch)
    {
      public void onCompleted()
      {
        this.val$cdl.countDown();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$error[0] = paramThrowable;
        this.val$cdl.countDown();
      }

      public void onNext(T paramT)
      {
      }
    }));
    Throwable localThrowable = arrayOfThrowable[0];
    if (localThrowable != null)
    {
      if ((localThrowable instanceof RuntimeException))
        throw ((RuntimeException)localThrowable);
      throw new RuntimeException(localThrowable);
    }
  }

  @Experimental
  public void subscribe(Observer<? super T> paramObserver)
  {
    NotificationLite localNotificationLite = NotificationLite.instance();
    LinkedBlockingQueue localLinkedBlockingQueue = new LinkedBlockingQueue();
    Subscription localSubscription = this.o.subscribe(new Subscriber(localLinkedBlockingQueue, localNotificationLite)
    {
      public void onCompleted()
      {
        this.val$queue.offer(this.val$nl.completed());
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$queue.offer(this.val$nl.error(paramThrowable));
      }

      public void onNext(T paramT)
      {
        this.val$queue.offer(this.val$nl.next(paramT));
      }
    });
    try
    {
      boolean bool;
      do
      {
        Object localObject2 = localLinkedBlockingQueue.poll();
        if (localObject2 == null)
          localObject2 = localLinkedBlockingQueue.take();
        bool = localNotificationLite.accept(paramObserver, localObject2);
      }
      while (!bool);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
      {
        Thread.currentThread().interrupt();
        paramObserver.onError(localInterruptedException);
        localSubscription.unsubscribe();
      }
    }
    finally
    {
      localSubscription.unsubscribe();
    }
    throw localObject1;
  }

  @Experimental
  public void subscribe(Subscriber<? super T> paramSubscriber)
  {
    NotificationLite localNotificationLite = NotificationLite.instance();
    LinkedBlockingQueue localLinkedBlockingQueue = new LinkedBlockingQueue();
    Producer[] arrayOfProducer = new Producer[1];
    arrayOfProducer[0] = null;
    6 local6 = new Subscriber(localLinkedBlockingQueue, localNotificationLite, arrayOfProducer)
    {
      public void onCompleted()
      {
        this.val$queue.offer(this.val$nl.completed());
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$queue.offer(this.val$nl.error(paramThrowable));
      }

      public void onNext(T paramT)
      {
        this.val$queue.offer(this.val$nl.next(paramT));
      }

      public void onStart()
      {
        this.val$queue.offer(BlockingObservable.ON_START);
      }

      public void setProducer(Producer paramProducer)
      {
        this.val$theProducer[0] = paramProducer;
        this.val$queue.offer(BlockingObservable.SET_PRODUCER);
      }
    };
    paramSubscriber.add(local6);
    paramSubscriber.add(Subscriptions.create(new Action0(localLinkedBlockingQueue)
    {
      public void call()
      {
        this.val$queue.offer(BlockingObservable.UNSUBSCRIBE);
      }
    }));
    this.o.subscribe(local6);
    while (true)
    {
      Object localObject2;
      try
      {
        boolean bool1 = paramSubscriber.isUnsubscribed();
        if (bool1)
          return;
        localObject2 = localLinkedBlockingQueue.poll();
        if (localObject2 != null)
          continue;
        localObject2 = localLinkedBlockingQueue.take();
        if ((paramSubscriber.isUnsubscribed()) || (localObject2 == UNSUBSCRIBE))
          continue;
        if (localObject2 != ON_START)
          continue;
        paramSubscriber.onStart();
        continue;
      }
      catch (InterruptedException localInterruptedException)
      {
        Thread.currentThread().interrupt();
        paramSubscriber.onError(localInterruptedException);
        local6.unsubscribe();
        continue;
        if (localObject2 == SET_PRODUCER)
        {
          paramSubscriber.setProducer(arrayOfProducer[0]);
          continue;
        }
      }
      finally
      {
        local6.unsubscribe();
      }
      boolean bool2 = localNotificationLite.accept(paramSubscriber, localObject2);
      if (!bool2)
        continue;
      local6.unsubscribe();
    }
  }

  @Experimental
  public void subscribe(Action1<? super T> paramAction1)
  {
    subscribe(paramAction1, new Action1()
    {
      public void call(Throwable paramThrowable)
      {
        throw new OnErrorNotImplementedException(paramThrowable);
      }
    }
    , Actions.empty());
  }

  @Experimental
  public void subscribe(Action1<? super T> paramAction1, Action1<? super Throwable> paramAction11)
  {
    subscribe(paramAction1, paramAction11, Actions.empty());
  }

  @Experimental
  public void subscribe(Action1<? super T> paramAction1, Action1<? super Throwable> paramAction11, Action0 paramAction0)
  {
    subscribe(new Observer(paramAction1, paramAction11, paramAction0)
    {
      public void onCompleted()
      {
        this.val$onCompleted.call();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$onError.call(paramThrowable);
      }

      public void onNext(T paramT)
      {
        this.val$onNext.call(paramT);
      }
    });
  }

  public Future<T> toFuture()
  {
    return BlockingOperatorToFuture.toFuture(this.o);
  }

  public Iterable<T> toIterable()
  {
    return new Iterable()
    {
      public Iterator<T> iterator()
      {
        return BlockingObservable.this.getIterator();
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.observables.BlockingObservable
 * JD-Core Version:    0.6.0
 */