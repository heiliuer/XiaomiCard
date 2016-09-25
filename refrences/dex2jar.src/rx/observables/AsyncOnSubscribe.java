package rx.observables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Action3;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.internal.operators.BufferUntilSubscriber;
import rx.observers.SerializedObserver;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.CompositeSubscription;

@Experimental
public abstract class AsyncOnSubscribe<S, T>
  implements Observable.OnSubscribe<T>
{
  @Experimental
  public static <S, T> Observable.OnSubscribe<T> createSingleState(Func0<? extends S> paramFunc0, Action3<? super S, Long, ? super Observer<Observable<? extends T>>> paramAction3)
  {
    return new AsyncOnSubscribeImpl(paramFunc0, new Func3(paramAction3)
    {
      public S call(S paramS, Long paramLong, Observer<Observable<? extends T>> paramObserver)
      {
        AsyncOnSubscribe.this.call(paramS, paramLong, paramObserver);
        return paramS;
      }
    });
  }

  @Experimental
  public static <S, T> Observable.OnSubscribe<T> createSingleState(Func0<? extends S> paramFunc0, Action3<? super S, Long, ? super Observer<Observable<? extends T>>> paramAction3, Action1<? super S> paramAction1)
  {
    return new AsyncOnSubscribeImpl(paramFunc0, new Func3(paramAction3)
    {
      public S call(S paramS, Long paramLong, Observer<Observable<? extends T>> paramObserver)
      {
        AsyncOnSubscribe.this.call(paramS, paramLong, paramObserver);
        return paramS;
      }
    }
    , paramAction1, null);
  }

  @Experimental
  public static <S, T> Observable.OnSubscribe<T> createStateful(Func0<? extends S> paramFunc0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> paramFunc3)
  {
    return new AsyncOnSubscribeImpl(paramFunc0, paramFunc3);
  }

  @Experimental
  public static <S, T> Observable.OnSubscribe<T> createStateful(Func0<? extends S> paramFunc0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> paramFunc3, Action1<? super S> paramAction1)
  {
    return new AsyncOnSubscribeImpl(paramFunc0, paramFunc3, paramAction1, null);
  }

  @Experimental
  public static <T> Observable.OnSubscribe<T> createStateless(Action2<Long, ? super Observer<Observable<? extends T>>> paramAction2)
  {
    return new AsyncOnSubscribeImpl(new Func3(paramAction2)
    {
      public Void call(Void paramVoid, Long paramLong, Observer<Observable<? extends T>> paramObserver)
      {
        AsyncOnSubscribe.this.call(paramLong, paramObserver);
        return paramVoid;
      }
    });
  }

  @Experimental
  public static <T> Observable.OnSubscribe<T> createStateless(Action2<Long, ? super Observer<Observable<? extends T>>> paramAction2, Action0 paramAction0)
  {
    return new AsyncOnSubscribeImpl(new Func3(paramAction2)
    {
      public Void call(Void paramVoid, Long paramLong, Observer<Observable<? extends T>> paramObserver)
      {
        AsyncOnSubscribe.this.call(paramLong, paramObserver);
        return null;
      }
    }
    , new Action1(paramAction0)
    {
      public void call(Void paramVoid)
      {
        AsyncOnSubscribe.this.call();
      }
    });
  }

  public final void call(Subscriber<? super T> paramSubscriber)
  {
    try
    {
      Object localObject = generateState();
      UnicastSubject localUnicastSubject = UnicastSubject.create();
      AsyncOuterManager localAsyncOuterManager = new AsyncOuterManager(this, localObject, localUnicastSubject);
      6 local6 = new Subscriber(paramSubscriber, localAsyncOuterManager)
      {
        public void onCompleted()
        {
          this.val$actualSubscriber.onCompleted();
        }

        public void onError(Throwable paramThrowable)
        {
          this.val$actualSubscriber.onError(paramThrowable);
        }

        public void onNext(T paramT)
        {
          this.val$actualSubscriber.onNext(paramT);
        }

        public void setProducer(Producer paramProducer)
        {
          this.val$outerProducer.setConcatProducer(paramProducer);
        }
      };
      localUnicastSubject.onBackpressureBuffer().concatMap(new Func1()
      {
        public Observable<T> call(Observable<T> paramObservable)
        {
          return paramObservable.onBackpressureBuffer();
        }
      }).unsafeSubscribe(local6);
      paramSubscriber.add(local6);
      paramSubscriber.add(localAsyncOuterManager);
      paramSubscriber.setProducer(localAsyncOuterManager);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        paramSubscriber.onError(localThrowable);
    }
  }

  protected abstract S generateState();

  protected abstract S next(S paramS, long paramLong, Observer<Observable<? extends T>> paramObserver);

  protected void onUnsubscribe(S paramS)
  {
  }

  static final class UnicastSubject<T> extends Observable<T>
    implements Observer<T>
  {
    private State<T> state;

    protected UnicastSubject(State<T> paramState)
    {
      super();
      this.state = paramState;
    }

    public static <T> UnicastSubject<T> create()
    {
      return new UnicastSubject(new State());
    }

    public void onCompleted()
    {
      this.state.subscriber.onCompleted();
    }

    public void onError(Throwable paramThrowable)
    {
      this.state.subscriber.onError(paramThrowable);
    }

    public void onNext(T paramT)
    {
      this.state.subscriber.onNext(paramT);
    }

    static final class State<T>
      implements Observable.OnSubscribe<T>
    {
      private Subscriber<? super T> subscriber;

      public void call(Subscriber<? super T> paramSubscriber)
      {
        monitorenter;
        try
        {
          if (this.subscriber == null)
          {
            this.subscriber = paramSubscriber;
            monitorexit;
          }
          else
          {
            monitorexit;
            paramSubscriber.onError(new IllegalStateException("There can be only one subscriber"));
          }
        }
        finally
        {
          monitorexit;
        }
      }
    }
  }

  static final class AsyncOuterManager<S, T>
    implements Producer, Subscription, Observer<Observable<? extends T>>
  {
    private static final AtomicIntegerFieldUpdater<AsyncOuterManager> IS_UNSUBSCRIBED = AtomicIntegerFieldUpdater.newUpdater(AsyncOuterManager.class, "isUnsubscribed");
    Producer concatProducer;
    boolean emitting;
    long expectedDelivery;
    private boolean hasTerminated;
    private volatile int isUnsubscribed;
    private final AsyncOnSubscribe.UnicastSubject<Observable<T>> merger;
    private boolean onNextCalled;
    private final AsyncOnSubscribe<S, T> parent;
    List<Long> requests;
    private final SerializedObserver<Observable<? extends T>> serializedSubscriber;
    private S state;
    private final CompositeSubscription subscriptions = new CompositeSubscription();

    public AsyncOuterManager(AsyncOnSubscribe<S, T> paramAsyncOnSubscribe, S paramS, AsyncOnSubscribe.UnicastSubject<Observable<T>> paramUnicastSubject)
    {
      this.parent = paramAsyncOnSubscribe;
      this.serializedSubscriber = new SerializedObserver(this);
      this.state = paramS;
      this.merger = paramUnicastSubject;
    }

    private void handleThrownError(Throwable paramThrowable)
    {
      if (this.hasTerminated)
        RxJavaPlugins.getInstance().getErrorHandler().handleError(paramThrowable);
      while (true)
      {
        return;
        this.hasTerminated = true;
        this.merger.onError(paramThrowable);
        cleanup();
      }
    }

    private void subscribeBufferToObservable(Observable<? extends T> paramObservable)
    {
      BufferUntilSubscriber localBufferUntilSubscriber = BufferUntilSubscriber.create();
      1 local1 = new Subscriber(this.expectedDelivery, localBufferUntilSubscriber)
      {
        long remaining = this.val$expected;

        public void onCompleted()
        {
          this.val$buffer.onCompleted();
          long l = this.remaining;
          if (l > 0L)
            AsyncOnSubscribe.AsyncOuterManager.this.requestRemaining(l);
        }

        public void onError(Throwable paramThrowable)
        {
          this.val$buffer.onError(paramThrowable);
        }

        public void onNext(T paramT)
        {
          this.remaining -= 1L;
          this.val$buffer.onNext(paramT);
        }
      };
      this.subscriptions.add(local1);
      paramObservable.doOnTerminate(new Action0(local1)
      {
        public void call()
        {
          AsyncOnSubscribe.AsyncOuterManager.this.subscriptions.remove(this.val$s);
        }
      }).subscribe(local1);
      this.merger.onNext(localBufferUntilSubscriber);
    }

    void cleanup()
    {
      this.subscriptions.unsubscribe();
      try
      {
        this.parent.onUnsubscribe(this.state);
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          handleThrownError(localThrowable);
      }
    }

    public boolean isUnsubscribed()
    {
      if (this.isUnsubscribed != 0);
      for (int i = 1; ; i = 0)
        return i;
    }

    public void nextIteration(long paramLong)
    {
      this.state = this.parent.next(this.state, paramLong, this.serializedSubscriber);
    }

    public void onCompleted()
    {
      if (this.hasTerminated)
        throw new IllegalStateException("Terminal event already emitted.");
      this.hasTerminated = true;
      this.merger.onCompleted();
    }

    public void onError(Throwable paramThrowable)
    {
      if (this.hasTerminated)
        throw new IllegalStateException("Terminal event already emitted.");
      this.hasTerminated = true;
      this.merger.onError(paramThrowable);
    }

    public void onNext(Observable<? extends T> paramObservable)
    {
      if (this.onNextCalled)
        throw new IllegalStateException("onNext called multiple times!");
      this.onNextCalled = true;
      if (this.hasTerminated);
      while (true)
      {
        return;
        subscribeBufferToObservable(paramObservable);
      }
    }

    // ERROR //
    public void request(long paramLong)
    {
      // Byte code:
      //   0: lload_1
      //   1: lconst_0
      //   2: lcmp
      //   3: ifne +4 -> 7
      //   6: return
      //   7: lload_1
      //   8: lconst_0
      //   9: lcmp
      //   10: ifge +30 -> 40
      //   13: new 166	java/lang/IllegalStateException
      //   16: dup
      //   17: new 184	java/lang/StringBuilder
      //   20: dup
      //   21: invokespecial 185	java/lang/StringBuilder:<init>	()V
      //   24: ldc 187
      //   26: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   29: lload_1
      //   30: invokevirtual 194	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   33: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   36: invokespecial 171	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
      //   39: athrow
      //   40: iconst_0
      //   41: istore_3
      //   42: aload_0
      //   43: monitorenter
      //   44: aload_0
      //   45: getfield 200	rx/observables/AsyncOnSubscribe$AsyncOuterManager:emitting	Z
      //   48: ifeq +97 -> 145
      //   51: aload_0
      //   52: getfield 202	rx/observables/AsyncOnSubscribe$AsyncOuterManager:requests	Ljava/util/List;
      //   55: astore 8
      //   57: aload 8
      //   59: ifnonnull +18 -> 77
      //   62: new 204	java/util/ArrayList
      //   65: dup
      //   66: invokespecial 205	java/util/ArrayList:<init>	()V
      //   69: astore 8
      //   71: aload_0
      //   72: aload 8
      //   74: putfield 202	rx/observables/AsyncOnSubscribe$AsyncOuterManager:requests	Ljava/util/List;
      //   77: aload 8
      //   79: lload_1
      //   80: invokestatic 211	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   83: invokeinterface 216 2 0
      //   88: pop
      //   89: iconst_1
      //   90: istore_3
      //   91: aload_0
      //   92: monitorexit
      //   93: aload_0
      //   94: getfield 218	rx/observables/AsyncOnSubscribe$AsyncOuterManager:concatProducer	Lrx/Producer;
      //   97: lload_1
      //   98: invokeinterface 220 3 0
      //   103: iload_3
      //   104: ifne -98 -> 6
      //   107: aload_0
      //   108: lload_1
      //   109: invokevirtual 224	rx/observables/AsyncOnSubscribe$AsyncOuterManager:tryEmit	(J)Z
      //   112: ifne -106 -> 6
      //   115: aload_0
      //   116: monitorenter
      //   117: aload_0
      //   118: getfield 202	rx/observables/AsyncOnSubscribe$AsyncOuterManager:requests	Ljava/util/List;
      //   121: astore 6
      //   123: aload 6
      //   125: ifnonnull +35 -> 160
      //   128: aload_0
      //   129: iconst_0
      //   130: putfield 200	rx/observables/AsyncOnSubscribe$AsyncOuterManager:emitting	Z
      //   133: aload_0
      //   134: monitorexit
      //   135: goto -129 -> 6
      //   138: astore 5
      //   140: aload_0
      //   141: monitorexit
      //   142: aload 5
      //   144: athrow
      //   145: aload_0
      //   146: iconst_1
      //   147: putfield 200	rx/observables/AsyncOnSubscribe$AsyncOuterManager:emitting	Z
      //   150: goto -59 -> 91
      //   153: astore 4
      //   155: aload_0
      //   156: monitorexit
      //   157: aload 4
      //   159: athrow
      //   160: aload_0
      //   161: aconst_null
      //   162: putfield 202	rx/observables/AsyncOnSubscribe$AsyncOuterManager:requests	Ljava/util/List;
      //   165: aload_0
      //   166: monitorexit
      //   167: aload 6
      //   169: invokeinterface 228 1 0
      //   174: astore 7
      //   176: aload 7
      //   178: invokeinterface 233 1 0
      //   183: ifeq -68 -> 115
      //   186: aload_0
      //   187: aload 7
      //   189: invokeinterface 236 1 0
      //   194: checkcast 207	java/lang/Long
      //   197: invokevirtual 240	java/lang/Long:longValue	()J
      //   200: invokevirtual 224	rx/observables/AsyncOnSubscribe$AsyncOuterManager:tryEmit	(J)Z
      //   203: ifeq -27 -> 176
      //   206: goto -200 -> 6
      //
      // Exception table:
      //   from	to	target	type
      //   117	142	138	finally
      //   160	167	138	finally
      //   44	93	153	finally
      //   145	157	153	finally
    }

    // ERROR //
    public void requestRemaining(long paramLong)
    {
      // Byte code:
      //   0: lload_1
      //   1: lconst_0
      //   2: lcmp
      //   3: ifne +4 -> 7
      //   6: return
      //   7: lload_1
      //   8: lconst_0
      //   9: lcmp
      //   10: ifge +30 -> 40
      //   13: new 166	java/lang/IllegalStateException
      //   16: dup
      //   17: new 184	java/lang/StringBuilder
      //   20: dup
      //   21: invokespecial 185	java/lang/StringBuilder:<init>	()V
      //   24: ldc 187
      //   26: invokevirtual 191	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   29: lload_1
      //   30: invokevirtual 194	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
      //   33: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   36: invokespecial 171	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
      //   39: athrow
      //   40: aload_0
      //   41: monitorenter
      //   42: aload_0
      //   43: getfield 200	rx/observables/AsyncOnSubscribe$AsyncOuterManager:emitting	Z
      //   46: ifeq +51 -> 97
      //   49: aload_0
      //   50: getfield 202	rx/observables/AsyncOnSubscribe$AsyncOuterManager:requests	Ljava/util/List;
      //   53: astore 7
      //   55: aload 7
      //   57: ifnonnull +18 -> 75
      //   60: new 204	java/util/ArrayList
      //   63: dup
      //   64: invokespecial 205	java/util/ArrayList:<init>	()V
      //   67: astore 7
      //   69: aload_0
      //   70: aload 7
      //   72: putfield 202	rx/observables/AsyncOnSubscribe$AsyncOuterManager:requests	Ljava/util/List;
      //   75: aload 7
      //   77: lload_1
      //   78: invokestatic 211	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   81: invokeinterface 216 2 0
      //   86: pop
      //   87: aload_0
      //   88: monitorexit
      //   89: goto -83 -> 6
      //   92: astore_3
      //   93: aload_0
      //   94: monitorexit
      //   95: aload_3
      //   96: athrow
      //   97: aload_0
      //   98: iconst_1
      //   99: putfield 200	rx/observables/AsyncOnSubscribe$AsyncOuterManager:emitting	Z
      //   102: aload_0
      //   103: monitorexit
      //   104: aload_0
      //   105: lload_1
      //   106: invokevirtual 224	rx/observables/AsyncOnSubscribe$AsyncOuterManager:tryEmit	(J)Z
      //   109: ifne -103 -> 6
      //   112: aload_0
      //   113: monitorenter
      //   114: aload_0
      //   115: getfield 202	rx/observables/AsyncOnSubscribe$AsyncOuterManager:requests	Ljava/util/List;
      //   118: astore 5
      //   120: aload 5
      //   122: ifnonnull +20 -> 142
      //   125: aload_0
      //   126: iconst_0
      //   127: putfield 200	rx/observables/AsyncOnSubscribe$AsyncOuterManager:emitting	Z
      //   130: aload_0
      //   131: monitorexit
      //   132: goto -126 -> 6
      //   135: astore 4
      //   137: aload_0
      //   138: monitorexit
      //   139: aload 4
      //   141: athrow
      //   142: aload_0
      //   143: aconst_null
      //   144: putfield 202	rx/observables/AsyncOnSubscribe$AsyncOuterManager:requests	Ljava/util/List;
      //   147: aload_0
      //   148: monitorexit
      //   149: aload 5
      //   151: invokeinterface 228 1 0
      //   156: astore 6
      //   158: aload 6
      //   160: invokeinterface 233 1 0
      //   165: ifeq -53 -> 112
      //   168: aload_0
      //   169: aload 6
      //   171: invokeinterface 236 1 0
      //   176: checkcast 207	java/lang/Long
      //   179: invokevirtual 240	java/lang/Long:longValue	()J
      //   182: invokevirtual 224	rx/observables/AsyncOnSubscribe$AsyncOuterManager:tryEmit	(J)Z
      //   185: ifeq -27 -> 158
      //   188: goto -182 -> 6
      //
      // Exception table:
      //   from	to	target	type
      //   42	95	92	finally
      //   97	104	92	finally
      //   114	139	135	finally
      //   142	149	135	finally
    }

    void setConcatProducer(Producer paramProducer)
    {
      if (this.concatProducer != null)
        throw new IllegalStateException("setConcatProducer may be called at most once!");
      this.concatProducer = paramProducer;
    }

    boolean tryEmit(long paramLong)
    {
      int i = 1;
      if (isUnsubscribed())
        cleanup();
      while (true)
      {
        return i;
        try
        {
          this.onNextCalled = false;
          this.expectedDelivery = paramLong;
          nextIteration(paramLong);
          if ((!this.hasTerminated) && (!isUnsubscribed()))
            break label62;
          cleanup();
        }
        catch (Throwable localThrowable)
        {
          handleThrownError(localThrowable);
        }
        continue;
        label62: if (!this.onNextCalled)
        {
          handleThrownError(new IllegalStateException("No events emitted!"));
          continue;
        }
        i = 0;
      }
    }

    public void unsubscribe()
    {
      if (IS_UNSUBSCRIBED.compareAndSet(this, 0, 1))
      {
        monitorenter;
        try
        {
          if (this.emitting)
          {
            this.requests = new ArrayList();
            this.requests.add(Long.valueOf(0L));
            monitorexit;
          }
          else
          {
            this.emitting = true;
            monitorexit;
            cleanup();
          }
        }
        finally
        {
          monitorexit;
        }
      }
    }
  }

  private static final class AsyncOnSubscribeImpl<S, T> extends AsyncOnSubscribe<S, T>
  {
    private final Func0<? extends S> generator;
    private final Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> next;
    private final Action1<? super S> onUnsubscribe;

    public AsyncOnSubscribeImpl(Func0<? extends S> paramFunc0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> paramFunc3)
    {
      this(paramFunc0, paramFunc3, null);
    }

    private AsyncOnSubscribeImpl(Func0<? extends S> paramFunc0, Func3<? super S, Long, ? super Observer<Observable<? extends T>>, ? extends S> paramFunc3, Action1<? super S> paramAction1)
    {
      this.generator = paramFunc0;
      this.next = paramFunc3;
      this.onUnsubscribe = paramAction1;
    }

    public AsyncOnSubscribeImpl(Func3<S, Long, Observer<Observable<? extends T>>, S> paramFunc3)
    {
      this(null, paramFunc3, null);
    }

    public AsyncOnSubscribeImpl(Func3<S, Long, Observer<Observable<? extends T>>, S> paramFunc3, Action1<? super S> paramAction1)
    {
      this(null, paramFunc3, paramAction1);
    }

    protected S generateState()
    {
      if (this.generator == null);
      for (Object localObject = null; ; localObject = this.generator.call())
        return localObject;
    }

    protected S next(S paramS, long paramLong, Observer<Observable<? extends T>> paramObserver)
    {
      return this.next.call(paramS, Long.valueOf(paramLong), paramObserver);
    }

    protected void onUnsubscribe(S paramS)
    {
      if (this.onUnsubscribe != null)
        this.onUnsubscribe.call(paramS);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.observables.AsyncOnSubscribe
 * JD-Core Version:    0.6.0
 */