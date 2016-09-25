package rx;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import rx.annotations.Beta;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;
import rx.internal.operators.OnSubscribeToObservableFuture;
import rx.internal.operators.OperatorDelay;
import rx.internal.operators.OperatorDoOnEach;
import rx.internal.operators.OperatorMap;
import rx.internal.operators.OperatorObserveOn;
import rx.internal.operators.OperatorOnErrorReturn;
import rx.internal.operators.OperatorSubscribeOn;
import rx.internal.operators.OperatorTimeout;
import rx.internal.operators.OperatorZip;
import rx.internal.producers.SingleDelayedProducer;
import rx.observers.SafeSubscriber;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;
import rx.singles.BlockingSingle;
import rx.subscriptions.Subscriptions;

@Beta
public class Single<T>
{
  private static final RxJavaObservableExecutionHook hook = RxJavaPlugins.getInstance().getObservableExecutionHook();
  final Observable.OnSubscribe<T> onSubscribe;

  private Single(Observable.OnSubscribe<T> paramOnSubscribe)
  {
    this.onSubscribe = paramOnSubscribe;
  }

  protected Single(OnSubscribe<T> paramOnSubscribe)
  {
    this.onSubscribe = new Observable.OnSubscribe(paramOnSubscribe)
    {
      public void call(Subscriber<? super T> paramSubscriber)
      {
        SingleDelayedProducer localSingleDelayedProducer = new SingleDelayedProducer(paramSubscriber);
        paramSubscriber.setProducer(localSingleDelayedProducer);
        1 local1 = new SingleSubscriber(localSingleDelayedProducer, paramSubscriber)
        {
          public void onError(Throwable paramThrowable)
          {
            this.val$child.onError(paramThrowable);
          }

          public void onSuccess(T paramT)
          {
            this.val$producer.setValue(paramT);
          }
        };
        paramSubscriber.add(local1);
        this.val$f.call(local1);
      }
    };
  }

  private static <T> Observable<T> asObservable(Single<T> paramSingle)
  {
    return Observable.create(paramSingle.onSubscribe);
  }

  public static final <T> Observable<T> concat(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2)
  {
    return Observable.concat(asObservable(paramSingle1), asObservable(paramSingle2));
  }

  public static final <T> Observable<T> concat(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3)
  {
    return Observable.concat(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3));
  }

  public static final <T> Observable<T> concat(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4)
  {
    return Observable.concat(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4));
  }

  public static final <T> Observable<T> concat(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4, Single<? extends T> paramSingle5)
  {
    return Observable.concat(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4), asObservable(paramSingle5));
  }

  public static final <T> Observable<T> concat(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4, Single<? extends T> paramSingle5, Single<? extends T> paramSingle6)
  {
    return Observable.concat(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4), asObservable(paramSingle5), asObservable(paramSingle6));
  }

  public static final <T> Observable<T> concat(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4, Single<? extends T> paramSingle5, Single<? extends T> paramSingle6, Single<? extends T> paramSingle7)
  {
    return Observable.concat(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4), asObservable(paramSingle5), asObservable(paramSingle6), asObservable(paramSingle7));
  }

  public static final <T> Observable<T> concat(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4, Single<? extends T> paramSingle5, Single<? extends T> paramSingle6, Single<? extends T> paramSingle7, Single<? extends T> paramSingle8)
  {
    return Observable.concat(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4), asObservable(paramSingle5), asObservable(paramSingle6), asObservable(paramSingle7), asObservable(paramSingle8));
  }

  public static final <T> Observable<T> concat(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4, Single<? extends T> paramSingle5, Single<? extends T> paramSingle6, Single<? extends T> paramSingle7, Single<? extends T> paramSingle8, Single<? extends T> paramSingle9)
  {
    return Observable.concat(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4), asObservable(paramSingle5), asObservable(paramSingle6), asObservable(paramSingle7), asObservable(paramSingle8), asObservable(paramSingle9));
  }

  public static final <T> Single<T> create(OnSubscribe<T> paramOnSubscribe)
  {
    return new Single(paramOnSubscribe);
  }

  @Experimental
  public static <T> Single<T> defer(Callable<Single<T>> paramCallable)
  {
    return create(new OnSubscribe(paramCallable)
    {
      public void call(SingleSubscriber<? super T> paramSingleSubscriber)
      {
        try
        {
          Single localSingle = (Single)Single.this.call();
          localSingle.subscribe(paramSingleSubscriber);
          return;
        }
        catch (Throwable localThrowable)
        {
          while (true)
          {
            Exceptions.throwIfFatal(localThrowable);
            paramSingleSubscriber.onError(localThrowable);
          }
        }
      }
    });
  }

  public static final <T> Single<T> error(Throwable paramThrowable)
  {
    return create(new OnSubscribe(paramThrowable)
    {
      public void call(SingleSubscriber<? super T> paramSingleSubscriber)
      {
        paramSingleSubscriber.onError(Single.this);
      }
    });
  }

  public static final <T> Single<T> from(Future<? extends T> paramFuture)
  {
    return new Single(OnSubscribeToObservableFuture.toObservableFuture(paramFuture));
  }

  public static final <T> Single<T> from(Future<? extends T> paramFuture, long paramLong, TimeUnit paramTimeUnit)
  {
    return new Single(OnSubscribeToObservableFuture.toObservableFuture(paramFuture, paramLong, paramTimeUnit));
  }

  public static final <T> Single<T> from(Future<? extends T> paramFuture, Scheduler paramScheduler)
  {
    return new Single(OnSubscribeToObservableFuture.toObservableFuture(paramFuture)).subscribeOn(paramScheduler);
  }

  @Experimental
  public static <T> Single<T> fromCallable(Callable<? extends T> paramCallable)
  {
    return create(new OnSubscribe(paramCallable)
    {
      public void call(SingleSubscriber<? super T> paramSingleSubscriber)
      {
        try
        {
          Object localObject = Single.this.call();
          paramSingleSubscriber.onSuccess(localObject);
          return;
        }
        catch (Throwable localThrowable)
        {
          while (true)
          {
            Exceptions.throwIfFatal(localThrowable);
            paramSingleSubscriber.onError(localThrowable);
          }
        }
      }
    });
  }

  public static final <T> Single<T> just(T paramT)
  {
    return create(new OnSubscribe(paramT)
    {
      public void call(SingleSubscriber<? super T> paramSingleSubscriber)
      {
        paramSingleSubscriber.onSuccess(Single.this);
      }
    });
  }

  private final <R> Single<R> lift(Observable.Operator<? extends R, ? super T> paramOperator)
  {
    return new Single(new Observable.OnSubscribe(paramOperator)
    {
      public void call(Subscriber<? super R> paramSubscriber)
      {
        try
        {
          Subscriber localSubscriber = (Subscriber)Single.hook.onLift(this.val$lift).call(paramSubscriber);
          try
          {
            localSubscriber.onStart();
            Single.this.onSubscribe.call(localSubscriber);
            return;
          }
          catch (Throwable localThrowable2)
          {
            while (true)
            {
              Exceptions.throwIfFatal(localThrowable2);
              localSubscriber.onError(localThrowable2);
            }
          }
        }
        catch (Throwable localThrowable1)
        {
          while (true)
          {
            Exceptions.throwIfFatal(localThrowable1);
            paramSubscriber.onError(localThrowable1);
          }
        }
      }
    });
  }

  public static final <T> Observable<T> merge(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2)
  {
    return Observable.merge(asObservable(paramSingle1), asObservable(paramSingle2));
  }

  public static final <T> Observable<T> merge(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3)
  {
    return Observable.merge(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3));
  }

  public static final <T> Observable<T> merge(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4)
  {
    return Observable.merge(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4));
  }

  public static final <T> Observable<T> merge(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4, Single<? extends T> paramSingle5)
  {
    return Observable.merge(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4), asObservable(paramSingle5));
  }

  public static final <T> Observable<T> merge(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4, Single<? extends T> paramSingle5, Single<? extends T> paramSingle6)
  {
    return Observable.merge(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4), asObservable(paramSingle5), asObservable(paramSingle6));
  }

  public static final <T> Observable<T> merge(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4, Single<? extends T> paramSingle5, Single<? extends T> paramSingle6, Single<? extends T> paramSingle7)
  {
    return Observable.merge(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4), asObservable(paramSingle5), asObservable(paramSingle6), asObservable(paramSingle7));
  }

  public static final <T> Observable<T> merge(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4, Single<? extends T> paramSingle5, Single<? extends T> paramSingle6, Single<? extends T> paramSingle7, Single<? extends T> paramSingle8)
  {
    return Observable.merge(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4), asObservable(paramSingle5), asObservable(paramSingle6), asObservable(paramSingle7), asObservable(paramSingle8));
  }

  public static final <T> Observable<T> merge(Single<? extends T> paramSingle1, Single<? extends T> paramSingle2, Single<? extends T> paramSingle3, Single<? extends T> paramSingle4, Single<? extends T> paramSingle5, Single<? extends T> paramSingle6, Single<? extends T> paramSingle7, Single<? extends T> paramSingle8, Single<? extends T> paramSingle9)
  {
    return Observable.merge(asObservable(paramSingle1), asObservable(paramSingle2), asObservable(paramSingle3), asObservable(paramSingle4), asObservable(paramSingle5), asObservable(paramSingle6), asObservable(paramSingle7), asObservable(paramSingle8), asObservable(paramSingle9));
  }

  public static final <T> Single<T> merge(Single<? extends Single<? extends T>> paramSingle)
  {
    return create(new OnSubscribe(paramSingle)
    {
      public void call(SingleSubscriber<? super T> paramSingleSubscriber)
      {
        Single.this.subscribe(new SingleSubscriber(paramSingleSubscriber)
        {
          public void onError(Throwable paramThrowable)
          {
            this.val$child.onError(paramThrowable);
          }

          public void onSuccess(Single<? extends T> paramSingle)
          {
            paramSingle.subscribe(this.val$child);
          }
        });
      }
    });
  }

  private final Single<Observable<T>> nest()
  {
    return just(asObservable(this));
  }

  public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Single<R> zip(Single<? extends T1> paramSingle, Single<? extends T2> paramSingle1, Single<? extends T3> paramSingle2, Single<? extends T4> paramSingle3, Single<? extends T5> paramSingle4, Single<? extends T6> paramSingle5, Single<? extends T7> paramSingle6, Single<? extends T8> paramSingle7, Single<? extends T9> paramSingle8, Func9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> paramFunc9)
  {
    Observable[] arrayOfObservable = new Observable[9];
    arrayOfObservable[0] = asObservable(paramSingle);
    arrayOfObservable[1] = asObservable(paramSingle1);
    arrayOfObservable[2] = asObservable(paramSingle2);
    arrayOfObservable[3] = asObservable(paramSingle3);
    arrayOfObservable[4] = asObservable(paramSingle4);
    arrayOfObservable[5] = asObservable(paramSingle5);
    arrayOfObservable[6] = asObservable(paramSingle6);
    arrayOfObservable[7] = asObservable(paramSingle7);
    arrayOfObservable[8] = asObservable(paramSingle8);
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc9));
  }

  public static final <T1, T2, T3, T4, T5, T6, T7, T8, R> Single<R> zip(Single<? extends T1> paramSingle, Single<? extends T2> paramSingle1, Single<? extends T3> paramSingle2, Single<? extends T4> paramSingle3, Single<? extends T5> paramSingle4, Single<? extends T6> paramSingle5, Single<? extends T7> paramSingle6, Single<? extends T8> paramSingle7, Func8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> paramFunc8)
  {
    Observable[] arrayOfObservable = new Observable[8];
    arrayOfObservable[0] = asObservable(paramSingle);
    arrayOfObservable[1] = asObservable(paramSingle1);
    arrayOfObservable[2] = asObservable(paramSingle2);
    arrayOfObservable[3] = asObservable(paramSingle3);
    arrayOfObservable[4] = asObservable(paramSingle4);
    arrayOfObservable[5] = asObservable(paramSingle5);
    arrayOfObservable[6] = asObservable(paramSingle6);
    arrayOfObservable[7] = asObservable(paramSingle7);
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc8));
  }

  public static final <T1, T2, T3, T4, T5, T6, T7, R> Single<R> zip(Single<? extends T1> paramSingle, Single<? extends T2> paramSingle1, Single<? extends T3> paramSingle2, Single<? extends T4> paramSingle3, Single<? extends T5> paramSingle4, Single<? extends T6> paramSingle5, Single<? extends T7> paramSingle6, Func7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> paramFunc7)
  {
    Observable[] arrayOfObservable = new Observable[7];
    arrayOfObservable[0] = asObservable(paramSingle);
    arrayOfObservable[1] = asObservable(paramSingle1);
    arrayOfObservable[2] = asObservable(paramSingle2);
    arrayOfObservable[3] = asObservable(paramSingle3);
    arrayOfObservable[4] = asObservable(paramSingle4);
    arrayOfObservable[5] = asObservable(paramSingle5);
    arrayOfObservable[6] = asObservable(paramSingle6);
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc7));
  }

  public static final <T1, T2, T3, T4, T5, T6, R> Single<R> zip(Single<? extends T1> paramSingle, Single<? extends T2> paramSingle1, Single<? extends T3> paramSingle2, Single<? extends T4> paramSingle3, Single<? extends T5> paramSingle4, Single<? extends T6> paramSingle5, Func6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> paramFunc6)
  {
    Observable[] arrayOfObservable = new Observable[6];
    arrayOfObservable[0] = asObservable(paramSingle);
    arrayOfObservable[1] = asObservable(paramSingle1);
    arrayOfObservable[2] = asObservable(paramSingle2);
    arrayOfObservable[3] = asObservable(paramSingle3);
    arrayOfObservable[4] = asObservable(paramSingle4);
    arrayOfObservable[5] = asObservable(paramSingle5);
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc6));
  }

  public static final <T1, T2, T3, T4, T5, R> Single<R> zip(Single<? extends T1> paramSingle, Single<? extends T2> paramSingle1, Single<? extends T3> paramSingle2, Single<? extends T4> paramSingle3, Single<? extends T5> paramSingle4, Func5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> paramFunc5)
  {
    Observable[] arrayOfObservable = new Observable[5];
    arrayOfObservable[0] = asObservable(paramSingle);
    arrayOfObservable[1] = asObservable(paramSingle1);
    arrayOfObservable[2] = asObservable(paramSingle2);
    arrayOfObservable[3] = asObservable(paramSingle3);
    arrayOfObservable[4] = asObservable(paramSingle4);
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc5));
  }

  public static final <T1, T2, T3, T4, R> Single<R> zip(Single<? extends T1> paramSingle, Single<? extends T2> paramSingle1, Single<? extends T3> paramSingle2, Single<? extends T4> paramSingle3, Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> paramFunc4)
  {
    Observable[] arrayOfObservable = new Observable[4];
    arrayOfObservable[0] = asObservable(paramSingle);
    arrayOfObservable[1] = asObservable(paramSingle1);
    arrayOfObservable[2] = asObservable(paramSingle2);
    arrayOfObservable[3] = asObservable(paramSingle3);
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc4));
  }

  public static final <T1, T2, T3, R> Single<R> zip(Single<? extends T1> paramSingle, Single<? extends T2> paramSingle1, Single<? extends T3> paramSingle2, Func3<? super T1, ? super T2, ? super T3, ? extends R> paramFunc3)
  {
    Observable[] arrayOfObservable = new Observable[3];
    arrayOfObservable[0] = asObservable(paramSingle);
    arrayOfObservable[1] = asObservable(paramSingle1);
    arrayOfObservable[2] = asObservable(paramSingle2);
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc3));
  }

  public static final <T1, T2, R> Single<R> zip(Single<? extends T1> paramSingle, Single<? extends T2> paramSingle1, Func2<? super T1, ? super T2, ? extends R> paramFunc2)
  {
    Observable[] arrayOfObservable = new Observable[2];
    arrayOfObservable[0] = asObservable(paramSingle);
    arrayOfObservable[1] = asObservable(paramSingle1);
    return just(arrayOfObservable).lift(new OperatorZip(paramFunc2));
  }

  public <R> Single<R> compose(Transformer<? super T, ? extends R> paramTransformer)
  {
    return (Single)paramTransformer.call(this);
  }

  public final Observable<T> concatWith(Single<? extends T> paramSingle)
  {
    return concat(this, paramSingle);
  }

  @Experimental
  public final Single<T> delay(long paramLong, TimeUnit paramTimeUnit)
  {
    return delay(paramLong, paramTimeUnit, Schedulers.computation());
  }

  @Experimental
  public final Single<T> delay(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return lift(new OperatorDelay(paramLong, paramTimeUnit, paramScheduler));
  }

  @Experimental
  public final Single<T> doOnError(Action1<Throwable> paramAction1)
  {
    return lift(new OperatorDoOnEach(new Observer(paramAction1)
    {
      public void onCompleted()
      {
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$onError.call(paramThrowable);
      }

      public void onNext(T paramT)
      {
      }
    }));
  }

  @Experimental
  public final Single<T> doOnSuccess(Action1<? super T> paramAction1)
  {
    return lift(new OperatorDoOnEach(new Observer(paramAction1)
    {
      public void onCompleted()
      {
      }

      public void onError(Throwable paramThrowable)
      {
      }

      public void onNext(T paramT)
      {
        this.val$onSuccess.call(paramT);
      }
    }));
  }

  public final <R> Single<R> flatMap(Func1<? super T, ? extends Single<? extends R>> paramFunc1)
  {
    return merge(map(paramFunc1));
  }

  public final <R> Observable<R> flatMapObservable(Func1<? super T, ? extends Observable<? extends R>> paramFunc1)
  {
    return Observable.merge(asObservable(map(paramFunc1)));
  }

  public final <R> Single<R> map(Func1<? super T, ? extends R> paramFunc1)
  {
    return lift(new OperatorMap(paramFunc1));
  }

  public final Observable<T> mergeWith(Single<? extends T> paramSingle)
  {
    return merge(this, paramSingle);
  }

  public final Single<T> observeOn(Scheduler paramScheduler)
  {
    return lift(new OperatorObserveOn(paramScheduler));
  }

  public final Single<T> onErrorReturn(Func1<Throwable, ? extends T> paramFunc1)
  {
    return lift(new OperatorOnErrorReturn(paramFunc1));
  }

  public final Subscription subscribe()
  {
    return subscribe(new Subscriber()
    {
      public final void onCompleted()
      {
      }

      public final void onError(Throwable paramThrowable)
      {
        throw new OnErrorNotImplementedException(paramThrowable);
      }

      public final void onNext(T paramT)
      {
      }
    });
  }

  public final Subscription subscribe(SingleSubscriber<? super T> paramSingleSubscriber)
  {
    10 local10 = new Subscriber(paramSingleSubscriber)
    {
      public void onCompleted()
      {
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$te.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        this.val$te.onSuccess(paramT);
      }
    };
    paramSingleSubscriber.add(local10);
    subscribe(local10);
    return local10;
  }

  public final Subscription subscribe(Subscriber<? super T> paramSubscriber)
  {
    if (paramSubscriber == null)
      throw new IllegalArgumentException("observer can not be null");
    if (this.onSubscribe == null)
      throw new IllegalStateException("onSubscribe function can not be null.");
    paramSubscriber.onStart();
    if (!(paramSubscriber instanceof SafeSubscriber))
      paramSubscriber = new SafeSubscriber(paramSubscriber);
    RuntimeException localRuntimeException;
    try
    {
      this.onSubscribe.call(paramSubscriber);
      Subscription localSubscription2 = hook.onSubscribeReturn(paramSubscriber);
      localSubscription1 = localSubscription2;
      return localSubscription1;
    }
    catch (Throwable localThrowable1)
    {
      while (true)
      {
        Subscription localSubscription1;
        Exceptions.throwIfFatal(localThrowable1);
        try
        {
          paramSubscriber.onError(hook.onSubscribeError(localThrowable1));
          localSubscription1 = Subscriptions.empty();
        }
        catch (Throwable localThrowable2)
        {
          Exceptions.throwIfFatal(localThrowable2);
          localRuntimeException = new RuntimeException("Error occurred attempting to subscribe [" + localThrowable1.getMessage() + "] and then again while trying to pass to onError.", localThrowable2);
          hook.onSubscribeError(localRuntimeException);
        }
      }
    }
    throw localRuntimeException;
  }

  public final Subscription subscribe(Action1<? super T> paramAction1)
  {
    if (paramAction1 == null)
      throw new IllegalArgumentException("onSuccess can not be null");
    return subscribe(new Subscriber(paramAction1)
    {
      public final void onCompleted()
      {
      }

      public final void onError(Throwable paramThrowable)
      {
        throw new OnErrorNotImplementedException(paramThrowable);
      }

      public final void onNext(T paramT)
      {
        this.val$onSuccess.call(paramT);
      }
    });
  }

  public final Subscription subscribe(Action1<? super T> paramAction1, Action1<Throwable> paramAction11)
  {
    if (paramAction1 == null)
      throw new IllegalArgumentException("onSuccess can not be null");
    if (paramAction11 == null)
      throw new IllegalArgumentException("onError can not be null");
    return subscribe(new Subscriber(paramAction11, paramAction1)
    {
      public final void onCompleted()
      {
      }

      public final void onError(Throwable paramThrowable)
      {
        this.val$onError.call(paramThrowable);
      }

      public final void onNext(T paramT)
      {
        this.val$onSuccess.call(paramT);
      }
    });
  }

  public final Single<T> subscribeOn(Scheduler paramScheduler)
  {
    return nest().lift(new OperatorSubscribeOn(paramScheduler));
  }

  public final Single<T> timeout(long paramLong, TimeUnit paramTimeUnit)
  {
    return timeout(paramLong, paramTimeUnit, null, Schedulers.computation());
  }

  public final Single<T> timeout(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return timeout(paramLong, paramTimeUnit, null, paramScheduler);
  }

  public final Single<T> timeout(long paramLong, TimeUnit paramTimeUnit, Single<? extends T> paramSingle)
  {
    return timeout(paramLong, paramTimeUnit, paramSingle, Schedulers.computation());
  }

  public final Single<T> timeout(long paramLong, TimeUnit paramTimeUnit, Single<? extends T> paramSingle, Scheduler paramScheduler)
  {
    if (paramSingle == null)
      paramSingle = error(new TimeoutException());
    return lift(new OperatorTimeout(paramLong, paramTimeUnit, asObservable(paramSingle), paramScheduler));
  }

  @Experimental
  public final BlockingSingle<T> toBlocking()
  {
    return BlockingSingle.from(this);
  }

  public final Observable<T> toObservable()
  {
    return asObservable(this);
  }

  public final void unsafeSubscribe(Subscriber<? super T> paramSubscriber)
  {
    RuntimeException localRuntimeException;
    try
    {
      paramSubscriber.onStart();
      this.onSubscribe.call(paramSubscriber);
      hook.onSubscribeReturn(paramSubscriber);
      return;
    }
    catch (Throwable localThrowable1)
    {
      while (true)
      {
        Exceptions.throwIfFatal(localThrowable1);
        try
        {
          paramSubscriber.onError(hook.onSubscribeError(localThrowable1));
        }
        catch (Throwable localThrowable2)
        {
          Exceptions.throwIfFatal(localThrowable2);
          localRuntimeException = new RuntimeException("Error occurred attempting to subscribe [" + localThrowable1.getMessage() + "] and then again while trying to pass to onError.", localThrowable2);
          hook.onSubscribeError(localRuntimeException);
        }
      }
    }
    throw localRuntimeException;
  }

  public final <T2, R> Single<R> zipWith(Single<? extends T2> paramSingle, Func2<? super T, ? super T2, ? extends R> paramFunc2)
  {
    return zip(this, paramSingle, paramFunc2);
  }

  public static abstract interface Transformer<T, R> extends Func1<Single<T>, Single<R>>
  {
  }

  public static abstract interface OnSubscribe<T> extends Action1<SingleSubscriber<? super T>>
  {
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.Single
 * JD-Core Version:    0.6.0
 */