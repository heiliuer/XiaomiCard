package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action0;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

public final class UnicastSubject<T> extends Subject<T, T>
{
  final State<T> state;

  private UnicastSubject(State<T> paramState)
  {
    super(paramState);
    this.state = paramState;
  }

  public static <T> UnicastSubject<T> create()
  {
    return create(16);
  }

  public static <T> UnicastSubject<T> create(int paramInt)
  {
    return new UnicastSubject(new State(paramInt));
  }

  public boolean hasObservers()
  {
    if (this.state.subscriber.get() != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onCompleted()
  {
    this.state.onCompleted();
  }

  public void onError(Throwable paramThrowable)
  {
    this.state.onError(paramThrowable);
  }

  public void onNext(T paramT)
  {
    this.state.onNext(paramT);
  }

  static final class State<T> extends AtomicLong
    implements Producer, Observer<T>, Action0, Observable.OnSubscribe<T>
  {
    private static final long serialVersionUID = -9044104859202255786L;
    volatile boolean caughtUp;
    volatile boolean done;
    boolean emitting;
    Throwable error;
    boolean missed;
    final NotificationLite<T> nl = NotificationLite.instance();
    final Queue<Object> queue;
    final AtomicReference<Subscriber<? super T>> subscriber = new AtomicReference();

    public State(int paramInt)
    {
      if (paramInt > 1)
      {
        if (UnsafeAccess.isUnsafeAvailable());
        for (localObject = new SpscUnboundedArrayQueue(paramInt); ; localObject = new SpscUnboundedAtomicArrayQueue(paramInt))
        {
          this.queue = ((Queue)localObject);
          return;
        }
      }
      if (UnsafeAccess.isUnsafeAvailable());
      for (Object localObject = new SpscLinkedQueue(); ; localObject = new SpscLinkedAtomicQueue())
        break;
    }

    public void call()
    {
      this.done = true;
      monitorenter;
      try
      {
        if (this.emitting)
        {
          monitorexit;
        }
        else
        {
          this.emitting = true;
          monitorexit;
          this.queue.clear();
        }
      }
      finally
      {
        monitorexit;
      }
    }

    public void call(Subscriber<? super T> paramSubscriber)
    {
      if (this.subscriber.compareAndSet(null, paramSubscriber))
      {
        paramSubscriber.add(Subscriptions.create(this));
        paramSubscriber.setProducer(this);
      }
      while (true)
      {
        return;
        paramSubscriber.onError(new IllegalStateException("Only a single subscriber is allowed"));
      }
    }

    boolean checkTerminated(boolean paramBoolean1, boolean paramBoolean2, Subscriber<? super T> paramSubscriber)
    {
      int i = 1;
      if (paramSubscriber.isUnsubscribed())
        this.queue.clear();
      while (true)
      {
        return i;
        if (paramBoolean1)
        {
          Throwable localThrowable = this.error;
          if (localThrowable != null)
          {
            this.queue.clear();
            paramSubscriber.onError(localThrowable);
            continue;
          }
          if (paramBoolean2)
          {
            paramSubscriber.onCompleted();
            continue;
          }
        }
        i = 0;
      }
    }

    public void onCompleted()
    {
      if (!this.done)
      {
        this.done = true;
        if (this.caughtUp)
          break label51;
        monitorenter;
      }
      while (true)
      {
        try
        {
          if (this.caughtUp)
            continue;
          int i = 1;
          monitorexit;
          if (i != 0)
          {
            replay();
            return;
            i = 0;
            continue;
          }
        }
        finally
        {
          monitorexit;
        }
        label51: ((Subscriber)this.subscriber.get()).onCompleted();
      }
    }

    public void onError(Throwable paramThrowable)
    {
      if (!this.done)
      {
        this.error = paramThrowable;
        this.done = true;
        if (this.caughtUp)
          break label56;
        monitorenter;
      }
      while (true)
      {
        try
        {
          if (this.caughtUp)
            continue;
          int i = 1;
          monitorexit;
          if (i != 0)
          {
            replay();
            return;
            i = 0;
            continue;
          }
        }
        finally
        {
          monitorexit;
        }
        label56: ((Subscriber)this.subscriber.get()).onError(paramThrowable);
      }
    }

    public void onNext(T paramT)
    {
      int i;
      if (!this.done)
      {
        if (this.caughtUp)
          break label66;
        i = 0;
        monitorenter;
      }
      while (true)
      {
        try
        {
          if (this.caughtUp)
            continue;
          this.queue.offer(this.nl.next(paramT));
          i = 1;
          monitorexit;
          if (i != 0)
          {
            replay();
            return;
          }
        }
        finally
        {
          monitorexit;
        }
        label66: Subscriber localSubscriber = (Subscriber)this.subscriber.get();
        try
        {
          localSubscriber.onNext(paramT);
        }
        catch (Throwable localThrowable)
        {
          Exceptions.throwIfFatal(localThrowable);
          localSubscriber.onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramT));
        }
      }
    }

    // ERROR //
    void replay()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 80	rx/internal/operators/UnicastSubject$State:emitting	Z
      //   6: ifeq +13 -> 19
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 165	rx/internal/operators/UnicastSubject$State:missed	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: goto +269 -> 285
      //   19: aload_0
      //   20: iconst_1
      //   21: putfield 80	rx/internal/operators/UnicastSubject$State:emitting	Z
      //   24: aload_0
      //   25: monitorexit
      //   26: aload_0
      //   27: getfield 66	rx/internal/operators/UnicastSubject$State:queue	Ljava/util/Queue;
      //   30: astore_2
      //   31: aload_0
      //   32: getfield 54	rx/internal/operators/UnicastSubject$State:subscriber	Ljava/util/concurrent/atomic/AtomicReference;
      //   35: invokevirtual 139	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
      //   38: checkcast 88	rx/Subscriber
      //   41: astore_3
      //   42: iconst_0
      //   43: istore 4
      //   45: aload_3
      //   46: ifnull +108 -> 154
      //   49: aload_0
      //   50: aload_0
      //   51: getfield 78	rx/internal/operators/UnicastSubject$State:done	Z
      //   54: aload_2
      //   55: invokeinterface 168 1 0
      //   60: aload_3
      //   61: invokevirtual 170	rx/internal/operators/UnicastSubject$State:checkTerminated	(ZZLrx/Subscriber;)Z
      //   64: ifne +221 -> 285
      //   67: aload_0
      //   68: invokevirtual 173	rx/internal/operators/UnicastSubject$State:get	()J
      //   71: lstore 6
      //   73: lload 6
      //   75: ldc2_w 174
      //   78: lcmp
      //   79: ifne +125 -> 204
      //   82: iconst_1
      //   83: istore 4
      //   85: lconst_0
      //   86: lstore 8
      //   88: lload 6
      //   90: lconst_0
      //   91: lcmp
      //   92: ifeq +42 -> 134
      //   95: aload_0
      //   96: getfield 78	rx/internal/operators/UnicastSubject$State:done	Z
      //   99: istore 12
      //   101: aload_2
      //   102: invokeinterface 178 1 0
      //   107: astore 13
      //   109: aload 13
      //   111: ifnonnull +99 -> 210
      //   114: iconst_1
      //   115: istore 14
      //   117: aload_0
      //   118: iload 12
      //   120: iload 14
      //   122: aload_3
      //   123: invokevirtual 170	rx/internal/operators/UnicastSubject$State:checkTerminated	(ZZLrx/Subscriber;)Z
      //   126: ifne +159 -> 285
      //   129: iload 14
      //   131: ifeq +85 -> 216
      //   134: iload 4
      //   136: ifne +18 -> 154
      //   139: lload 8
      //   141: lconst_0
      //   142: lcmp
      //   143: ifeq +11 -> 154
      //   146: aload_0
      //   147: lload 8
      //   149: lneg
      //   150: invokevirtual 182	rx/internal/operators/UnicastSubject$State:addAndGet	(J)J
      //   153: pop2
      //   154: aload_0
      //   155: monitorenter
      //   156: aload_0
      //   157: getfield 165	rx/internal/operators/UnicastSubject$State:missed	Z
      //   160: ifne +115 -> 275
      //   163: iload 4
      //   165: ifeq +17 -> 182
      //   168: aload_2
      //   169: invokeinterface 168 1 0
      //   174: ifeq +8 -> 182
      //   177: aload_0
      //   178: iconst_1
      //   179: putfield 132	rx/internal/operators/UnicastSubject$State:caughtUp	Z
      //   182: aload_0
      //   183: iconst_0
      //   184: putfield 80	rx/internal/operators/UnicastSubject$State:emitting	Z
      //   187: aload_0
      //   188: monitorexit
      //   189: goto +96 -> 285
      //   192: astore 5
      //   194: aload_0
      //   195: monitorexit
      //   196: aload 5
      //   198: athrow
      //   199: astore_1
      //   200: aload_0
      //   201: monitorexit
      //   202: aload_1
      //   203: athrow
      //   204: iconst_0
      //   205: istore 4
      //   207: goto -122 -> 85
      //   210: iconst_0
      //   211: istore 14
      //   213: goto -96 -> 117
      //   216: aload_0
      //   217: getfield 49	rx/internal/operators/UnicastSubject$State:nl	Lrx/internal/operators/NotificationLite;
      //   220: aload 13
      //   222: invokevirtual 185	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   225: astore 15
      //   227: aload_3
      //   228: aload 15
      //   230: invokevirtual 152	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   233: lload 6
      //   235: lconst_1
      //   236: lsub
      //   237: lstore 6
      //   239: lload 8
      //   241: lconst_1
      //   242: ladd
      //   243: lstore 8
      //   245: goto -157 -> 88
      //   248: astore 16
      //   250: aload_2
      //   251: invokeinterface 85 1 0
      //   256: aload 16
      //   258: invokestatic 157	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   261: aload_3
      //   262: aload 16
      //   264: aload 15
      //   266: invokestatic 163	rx/exceptions/OnErrorThrowable:addValueAsLastCause	(Ljava/lang/Throwable;Ljava/lang/Object;)Ljava/lang/Throwable;
      //   269: invokevirtual 120	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   272: goto +13 -> 285
      //   275: aload_0
      //   276: iconst_0
      //   277: putfield 165	rx/internal/operators/UnicastSubject$State:missed	Z
      //   280: aload_0
      //   281: monitorexit
      //   282: goto -251 -> 31
      //   285: return
      //
      // Exception table:
      //   from	to	target	type
      //   156	196	192	finally
      //   275	282	192	finally
      //   2	26	199	finally
      //   200	202	199	finally
      //   227	233	248	java/lang/Throwable
    }

    public void request(long paramLong)
    {
      if (paramLong < 0L)
        throw new IllegalArgumentException("n >= 0 required");
      if (paramLong > 0L)
      {
        BackpressureUtils.getAndAddRequest(this, paramLong);
        replay();
      }
      while (true)
      {
        return;
        if (this.done)
        {
          replay();
          continue;
        }
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.UnicastSubject
 * JD-Core Version:    0.6.0
 */