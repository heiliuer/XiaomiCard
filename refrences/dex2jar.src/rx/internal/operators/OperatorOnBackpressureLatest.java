package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable.Operator;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;

public final class OperatorOnBackpressureLatest<T>
  implements Observable.Operator<T, T>
{
  public static <T> OperatorOnBackpressureLatest<T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    LatestEmitter localLatestEmitter = new LatestEmitter(paramSubscriber);
    LatestSubscriber localLatestSubscriber = new LatestSubscriber(localLatestEmitter, null);
    localLatestEmitter.parent = localLatestSubscriber;
    paramSubscriber.add(localLatestSubscriber);
    paramSubscriber.add(localLatestEmitter);
    paramSubscriber.setProducer(localLatestEmitter);
    return localLatestSubscriber;
  }

  static final class LatestSubscriber<T> extends Subscriber<T>
  {
    private final OperatorOnBackpressureLatest.LatestEmitter<T> producer;

    private LatestSubscriber(OperatorOnBackpressureLatest.LatestEmitter<T> paramLatestEmitter)
    {
      this.producer = paramLatestEmitter;
    }

    public void onCompleted()
    {
      this.producer.onCompleted();
    }

    public void onError(Throwable paramThrowable)
    {
      this.producer.onError(paramThrowable);
    }

    public void onNext(T paramT)
    {
      this.producer.onNext(paramT);
    }

    public void onStart()
    {
      request(0L);
    }

    void requestMore(long paramLong)
    {
      request(paramLong);
    }
  }

  static final class LatestEmitter<T> extends AtomicLong
    implements Producer, Subscription, Observer<T>
  {
    static final Object EMPTY = new Object();
    static final long NOT_REQUESTED = -4611686018427387904L;
    private static final long serialVersionUID = -1364393685005146274L;
    final Subscriber<? super T> child;
    volatile boolean done;
    boolean emitting;
    boolean missed;
    OperatorOnBackpressureLatest.LatestSubscriber<? super T> parent;
    Throwable terminal;
    final AtomicReference<Object> value;

    public LatestEmitter(Subscriber<? super T> paramSubscriber)
    {
      this.child = paramSubscriber;
      this.value = new AtomicReference(EMPTY);
      lazySet(-4611686018427387904L);
    }

    // ERROR //
    void emit()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 65	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:emitting	Z
      //   6: ifeq +13 -> 19
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 67	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:missed	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: goto +229 -> 245
      //   19: aload_0
      //   20: iconst_1
      //   21: putfield 65	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:emitting	Z
      //   24: aload_0
      //   25: iconst_0
      //   26: putfield 67	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:missed	Z
      //   29: aload_0
      //   30: monitorexit
      //   31: iconst_0
      //   32: istore_2
      //   33: aload_0
      //   34: invokevirtual 71	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:get	()J
      //   37: lstore 5
      //   39: lload 5
      //   41: ldc2_w 72
      //   44: lcmp
      //   45: ifne +33 -> 78
      //   48: iconst_1
      //   49: istore_2
      //   50: iload_2
      //   51: ifne +194 -> 245
      //   54: aload_0
      //   55: monitorenter
      //   56: aload_0
      //   57: iconst_0
      //   58: putfield 65	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:emitting	Z
      //   61: aload_0
      //   62: monitorexit
      //   63: goto +182 -> 245
      //   66: astore 9
      //   68: aload_0
      //   69: monitorexit
      //   70: aload 9
      //   72: athrow
      //   73: astore_1
      //   74: aload_0
      //   75: monitorexit
      //   76: aload_1
      //   77: athrow
      //   78: aload_0
      //   79: getfield 58	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:value	Ljava/util/concurrent/atomic/AtomicReference;
      //   82: invokevirtual 76	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
      //   85: astore 7
      //   87: lload 5
      //   89: lconst_0
      //   90: lcmp
      //   91: ifle +48 -> 139
      //   94: aload 7
      //   96: getstatic 47	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:EMPTY	Ljava/lang/Object;
      //   99: if_acmpeq +40 -> 139
      //   102: aload 7
      //   104: astore 11
      //   106: aload_0
      //   107: getfield 51	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:child	Lrx/Subscriber;
      //   110: aload 11
      //   112: invokevirtual 81	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   115: aload_0
      //   116: getfield 58	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:value	Ljava/util/concurrent/atomic/AtomicReference;
      //   119: aload 7
      //   121: getstatic 47	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:EMPTY	Ljava/lang/Object;
      //   124: invokevirtual 85	java/util/concurrent/atomic/AtomicReference:compareAndSet	(Ljava/lang/Object;Ljava/lang/Object;)Z
      //   127: pop
      //   128: aload_0
      //   129: lconst_1
      //   130: invokevirtual 89	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:produced	(J)J
      //   133: pop2
      //   134: getstatic 47	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:EMPTY	Ljava/lang/Object;
      //   137: astore 7
      //   139: aload 7
      //   141: getstatic 47	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:EMPTY	Ljava/lang/Object;
      //   144: if_acmpne +30 -> 174
      //   147: aload_0
      //   148: getfield 91	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:done	Z
      //   151: ifeq +23 -> 174
      //   154: aload_0
      //   155: getfield 93	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:terminal	Ljava/lang/Throwable;
      //   158: astore 10
      //   160: aload 10
      //   162: ifnull +56 -> 218
      //   165: aload_0
      //   166: getfield 51	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:child	Lrx/Subscriber;
      //   169: aload 10
      //   171: invokevirtual 97	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   174: aload_0
      //   175: monitorenter
      //   176: aload_0
      //   177: getfield 67	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:missed	Z
      //   180: ifne +48 -> 228
      //   183: aload_0
      //   184: iconst_0
      //   185: putfield 65	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:emitting	Z
      //   188: iconst_1
      //   189: istore_2
      //   190: aload_0
      //   191: monitorexit
      //   192: goto -142 -> 50
      //   195: astore 8
      //   197: aload_0
      //   198: monitorexit
      //   199: aload 8
      //   201: athrow
      //   202: astore_3
      //   203: iload_2
      //   204: ifne +12 -> 216
      //   207: aload_0
      //   208: monitorenter
      //   209: aload_0
      //   210: iconst_0
      //   211: putfield 65	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:emitting	Z
      //   214: aload_0
      //   215: monitorexit
      //   216: aload_3
      //   217: athrow
      //   218: aload_0
      //   219: getfield 51	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:child	Lrx/Subscriber;
      //   222: invokevirtual 100	rx/Subscriber:onCompleted	()V
      //   225: goto -51 -> 174
      //   228: aload_0
      //   229: iconst_0
      //   230: putfield 67	rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter:missed	Z
      //   233: aload_0
      //   234: monitorexit
      //   235: goto -202 -> 33
      //   238: astore 4
      //   240: aload_0
      //   241: monitorexit
      //   242: aload 4
      //   244: athrow
      //   245: return
      //
      // Exception table:
      //   from	to	target	type
      //   56	70	66	finally
      //   2	31	73	finally
      //   74	76	73	finally
      //   176	199	195	finally
      //   228	235	195	finally
      //   33	39	202	finally
      //   78	176	202	finally
      //   199	202	202	finally
      //   218	225	202	finally
      //   209	216	238	finally
      //   240	242	238	finally
    }

    public boolean isUnsubscribed()
    {
      if (get() == -9223372036854775808L);
      for (int i = 1; ; i = 0)
        return i;
    }

    public void onCompleted()
    {
      this.done = true;
      emit();
    }

    public void onError(Throwable paramThrowable)
    {
      this.terminal = paramThrowable;
      this.done = true;
      emit();
    }

    public void onNext(T paramT)
    {
      this.value.lazySet(paramT);
      emit();
    }

    long produced(long paramLong)
    {
      long l1 = get();
      if (l1 < 0L);
      while (true)
      {
        return l1;
        long l2 = l1 - paramLong;
        if (!compareAndSet(l1, l2))
          break;
        l1 = l2;
      }
    }

    public void request(long paramLong)
    {
      if (paramLong >= 0L);
      label84: 
      while (true)
      {
        long l1 = get();
        if (l1 == -9223372036854775808L)
          return;
        long l2;
        if (l1 == -4611686018427387904L)
          l2 = paramLong;
        while (true)
        {
          if (!compareAndSet(l1, l2))
            break label84;
          if (l1 == -4611686018427387904L)
            this.parent.requestMore(9223372036854775807L);
          emit();
          break;
          l2 = l1 + paramLong;
          if (l2 >= 0L)
            continue;
          l2 = 9223372036854775807L;
        }
      }
    }

    public void unsubscribe()
    {
      if (get() >= 0L)
        getAndSet(-9223372036854775808L);
    }
  }

  static final class Holder
  {
    static final OperatorOnBackpressureLatest<Object> INSTANCE = new OperatorOnBackpressureLatest();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorOnBackpressureLatest
 * JD-Core Version:    0.6.0
 */