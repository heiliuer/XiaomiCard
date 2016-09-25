package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.Operator;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;

public final class OperatorScan<R, T>
  implements Observable.Operator<R, T>
{
  private static final Object NO_INITIAL_VALUE = new Object();
  private final Func2<R, ? super T, R> accumulator;
  private final Func0<R> initialValueFactory;

  public OperatorScan(R paramR, Func2<R, ? super T, R> paramFunc2)
  {
    this(new Func0()
    {
      public R call()
      {
        return OperatorScan.this;
      }
    }
    , paramFunc2);
  }

  public OperatorScan(Func0<R> paramFunc0, Func2<R, ? super T, R> paramFunc2)
  {
    this.initialValueFactory = paramFunc0;
    this.accumulator = paramFunc2;
  }

  public OperatorScan(Func2<R, ? super T, R> paramFunc2)
  {
    this(NO_INITIAL_VALUE, paramFunc2);
  }

  public Subscriber<? super T> call(Subscriber<? super R> paramSubscriber)
  {
    Object localObject1 = this.initialValueFactory.call();
    Object localObject2;
    if (localObject1 == NO_INITIAL_VALUE)
      localObject2 = new Subscriber(paramSubscriber, paramSubscriber)
      {
        boolean once;
        R value;

        public void onCompleted()
        {
          this.val$child.onCompleted();
        }

        public void onError(Throwable paramThrowable)
        {
          this.val$child.onError(paramThrowable);
        }

        public void onNext(T paramT)
        {
          Object localObject3;
          if (!this.once)
          {
            this.once = true;
            localObject3 = paramT;
            this.value = localObject3;
            this.val$child.onNext(localObject3);
          }
          while (true)
            while (true)
            {
              return;
              Object localObject1 = this.value;
              try
              {
                Object localObject2 = OperatorScan.this.accumulator.call(localObject1, paramT);
                localObject3 = localObject2;
              }
              catch (Throwable localThrowable)
              {
                Exceptions.throwIfFatal(localThrowable);
                this.val$child.onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramT));
              }
            }
        }
      };
    while (true)
    {
      return localObject2;
      InitialProducer localInitialProducer = new InitialProducer(localObject1, paramSubscriber);
      localObject2 = new Subscriber(localObject1, localInitialProducer)
      {
        private R value = this.val$initialValue;

        public void onCompleted()
        {
          this.val$ip.onCompleted();
        }

        public void onError(Throwable paramThrowable)
        {
          this.val$ip.onError(paramThrowable);
        }

        public void onNext(T paramT)
        {
          Object localObject1 = this.value;
          try
          {
            Object localObject2 = OperatorScan.this.accumulator.call(localObject1, paramT);
            this.value = localObject2;
            this.val$ip.onNext(localObject2);
            return;
          }
          catch (Throwable localThrowable)
          {
            while (true)
            {
              Exceptions.throwIfFatal(localThrowable);
              onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramT));
            }
          }
        }

        public void setProducer(Producer paramProducer)
        {
          this.val$ip.setProducer(paramProducer);
        }
      };
      paramSubscriber.add((Subscription)localObject2);
      paramSubscriber.setProducer(localInitialProducer);
    }
  }

  static final class InitialProducer<R>
    implements Producer, Observer<R>
  {
    final Subscriber<? super R> child;
    volatile boolean done;
    boolean emitting;
    Throwable error;
    boolean missed;
    long missedRequested;
    volatile Producer producer;
    final Queue<Object> queue;
    final AtomicLong requested;

    public InitialProducer(R paramR, Subscriber<? super R> paramSubscriber)
    {
      this.child = paramSubscriber;
      if (UnsafeAccess.isUnsafeAvailable());
      for (Object localObject = new SpscLinkedQueue(); ; localObject = new SpscLinkedAtomicQueue())
      {
        this.queue = ((Queue)localObject);
        ((Queue)localObject).offer(NotificationLite.instance().next(paramR));
        this.requested = new AtomicLong();
        return;
      }
    }

    boolean checkTerminated(boolean paramBoolean1, boolean paramBoolean2, Subscriber<? super R> paramSubscriber)
    {
      int i = 1;
      if (paramSubscriber.isUnsubscribed());
      while (true)
      {
        return i;
        if (paramBoolean1)
        {
          Throwable localThrowable = this.error;
          if (localThrowable != null)
          {
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

    void emit()
    {
      monitorenter;
      try
      {
        if (this.emitting)
        {
          this.missed = true;
          monitorexit;
        }
        else
        {
          this.emitting = true;
          monitorexit;
          emitLoop();
        }
      }
      finally
      {
        monitorexit;
      }
    }

    // ERROR //
    void emitLoop()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 37	rx/internal/operators/OperatorScan$InitialProducer:child	Lrx/Subscriber;
      //   4: astore_1
      //   5: aload_0
      //   6: getfield 48	rx/internal/operators/OperatorScan$InitialProducer:queue	Ljava/util/Queue;
      //   9: astore_2
      //   10: invokestatic 54	rx/internal/operators/NotificationLite:instance	()Lrx/internal/operators/NotificationLite;
      //   13: astore_3
      //   14: aload_0
      //   15: getfield 69	rx/internal/operators/OperatorScan$InitialProducer:requested	Ljava/util/concurrent/atomic/AtomicLong;
      //   18: astore 4
      //   20: aload 4
      //   22: invokevirtual 102	java/util/concurrent/atomic/AtomicLong:get	()J
      //   25: lstore 5
      //   27: lload 5
      //   29: ldc2_w 103
      //   32: lcmp
      //   33: ifne +25 -> 58
      //   36: iconst_1
      //   37: istore 7
      //   39: aload_0
      //   40: aload_0
      //   41: getfield 106	rx/internal/operators/OperatorScan$InitialProducer:done	Z
      //   44: aload_2
      //   45: invokeinterface 109 1 0
      //   50: aload_1
      //   51: invokevirtual 111	rx/internal/operators/OperatorScan$InitialProducer:checkTerminated	(ZZLrx/Subscriber;)Z
      //   54: ifeq +10 -> 64
      //   57: return
      //   58: iconst_0
      //   59: istore 7
      //   61: goto -22 -> 39
      //   64: lconst_0
      //   65: lstore 8
      //   67: lload 5
      //   69: lconst_0
      //   70: lcmp
      //   71: ifeq +42 -> 113
      //   74: aload_0
      //   75: getfield 106	rx/internal/operators/OperatorScan$InitialProducer:done	Z
      //   78: istore 11
      //   80: aload_2
      //   81: invokeinterface 115 1 0
      //   86: astore 12
      //   88: aload 12
      //   90: ifnonnull +70 -> 160
      //   93: iconst_1
      //   94: istore 13
      //   96: aload_0
      //   97: iload 11
      //   99: iload 13
      //   101: aload_1
      //   102: invokevirtual 111	rx/internal/operators/OperatorScan$InitialProducer:checkTerminated	(ZZLrx/Subscriber;)Z
      //   105: ifne -48 -> 57
      //   108: iload 13
      //   110: ifeq +56 -> 166
      //   113: lload 8
      //   115: lconst_0
      //   116: lcmp
      //   117: ifeq +17 -> 134
      //   120: iload 7
      //   122: ifne +12 -> 134
      //   125: aload 4
      //   127: lload 8
      //   129: invokevirtual 119	java/util/concurrent/atomic/AtomicLong:addAndGet	(J)J
      //   132: lstore 5
      //   134: aload_0
      //   135: monitorenter
      //   136: aload_0
      //   137: getfield 93	rx/internal/operators/OperatorScan$InitialProducer:missed	Z
      //   140: ifne +76 -> 216
      //   143: aload_0
      //   144: iconst_0
      //   145: putfield 91	rx/internal/operators/OperatorScan$InitialProducer:emitting	Z
      //   148: aload_0
      //   149: monitorexit
      //   150: goto -93 -> 57
      //   153: astore 10
      //   155: aload_0
      //   156: monitorexit
      //   157: aload 10
      //   159: athrow
      //   160: iconst_0
      //   161: istore 13
      //   163: goto -67 -> 96
      //   166: aload_3
      //   167: aload 12
      //   169: invokevirtual 122	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   172: astore 14
      //   174: aload_1
      //   175: aload 14
      //   177: invokevirtual 126	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   180: lload 5
      //   182: lconst_1
      //   183: lsub
      //   184: lstore 5
      //   186: lload 8
      //   188: lconst_1
      //   189: lsub
      //   190: lstore 8
      //   192: goto -125 -> 67
      //   195: astore 15
      //   197: aload 15
      //   199: invokestatic 131	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   202: aload_1
      //   203: aload 15
      //   205: aload 14
      //   207: invokestatic 137	rx/exceptions/OnErrorThrowable:addValueAsLastCause	(Ljava/lang/Throwable;Ljava/lang/Object;)Ljava/lang/Throwable;
      //   210: invokevirtual 85	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   213: goto -156 -> 57
      //   216: aload_0
      //   217: iconst_0
      //   218: putfield 93	rx/internal/operators/OperatorScan$InitialProducer:missed	Z
      //   221: aload_0
      //   222: monitorexit
      //   223: goto -196 -> 27
      //
      // Exception table:
      //   from	to	target	type
      //   136	157	153	finally
      //   216	223	153	finally
      //   174	180	195	java/lang/Throwable
    }

    public void onCompleted()
    {
      this.done = true;
      emit();
    }

    public void onError(Throwable paramThrowable)
    {
      this.error = paramThrowable;
      this.done = true;
      emit();
    }

    public void onNext(R paramR)
    {
      this.queue.offer(NotificationLite.instance().next(paramR));
      emit();
    }

    public void request(long paramLong)
    {
      if (paramLong < 0L)
        throw new IllegalArgumentException("n >= required but it was " + paramLong);
      Producer localProducer;
      if (paramLong != 0L)
      {
        BackpressureUtils.getAndAddRequest(this.requested, paramLong);
        localProducer = this.producer;
        if (localProducer != null);
      }
      synchronized (this.requested)
      {
        localProducer = this.producer;
        if (localProducer == null)
          this.missedRequested = BackpressureUtils.addCap(this.missedRequested, paramLong);
        if (localProducer != null)
          localProducer.request(paramLong);
        emit();
        return;
      }
    }

    // ERROR //
    public void setProducer(Producer paramProducer)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnonnull +11 -> 12
      //   4: new 182	java/lang/NullPointerException
      //   7: dup
      //   8: invokespecial 183	java/lang/NullPointerException:<init>	()V
      //   11: athrow
      //   12: aload_0
      //   13: getfield 69	rx/internal/operators/OperatorScan$InitialProducer:requested	Ljava/util/concurrent/atomic/AtomicLong;
      //   16: astore_2
      //   17: aload_2
      //   18: monitorenter
      //   19: aload_0
      //   20: getfield 170	rx/internal/operators/OperatorScan$InitialProducer:producer	Lrx/Producer;
      //   23: ifnull +18 -> 41
      //   26: new 185	java/lang/IllegalStateException
      //   29: dup
      //   30: ldc 187
      //   32: invokespecial 188	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
      //   35: athrow
      //   36: astore_3
      //   37: aload_2
      //   38: monitorexit
      //   39: aload_3
      //   40: athrow
      //   41: aload_0
      //   42: getfield 172	rx/internal/operators/OperatorScan$InitialProducer:missedRequested	J
      //   45: lconst_1
      //   46: lsub
      //   47: lstore 4
      //   49: aload_0
      //   50: lconst_0
      //   51: putfield 172	rx/internal/operators/OperatorScan$InitialProducer:missedRequested	J
      //   54: aload_0
      //   55: aload_1
      //   56: putfield 170	rx/internal/operators/OperatorScan$InitialProducer:producer	Lrx/Producer;
      //   59: aload_2
      //   60: monitorexit
      //   61: lload 4
      //   63: lconst_0
      //   64: lcmp
      //   65: ifle +11 -> 76
      //   68: aload_1
      //   69: lload 4
      //   71: invokeinterface 178 3 0
      //   76: aload_0
      //   77: invokevirtual 139	rx/internal/operators/OperatorScan$InitialProducer:emit	()V
      //   80: return
      //
      // Exception table:
      //   from	to	target	type
      //   19	39	36	finally
      //   41	61	36	finally
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorScan
 * JD-Core Version:    0.6.0
 */