package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

public final class OperatorMapNotification<T, R>
  implements Observable.Operator<R, T>
{
  private final Func0<? extends R> onCompleted;
  private final Func1<? super Throwable, ? extends R> onError;
  private final Func1<? super T, ? extends R> onNext;

  public OperatorMapNotification(Func1<? super T, ? extends R> paramFunc1, Func1<? super Throwable, ? extends R> paramFunc11, Func0<? extends R> paramFunc0)
  {
    this.onNext = paramFunc1;
    this.onError = paramFunc11;
    this.onCompleted = paramFunc0;
  }

  public Subscriber<? super T> call(Subscriber<? super R> paramSubscriber)
  {
    MapNotificationSubscriber localMapNotificationSubscriber = new MapNotificationSubscriber(new ProducerArbiter(), paramSubscriber, null);
    paramSubscriber.add(localMapNotificationSubscriber);
    localMapNotificationSubscriber.init();
    return localMapNotificationSubscriber;
  }

  static final class SingleEmitter<T> extends AtomicLong
    implements Producer, Subscription
  {
    private static final long serialVersionUID = -249869671366010660L;
    final Subscription cancel;
    final Subscriber<? super T> child;
    volatile boolean complete;
    boolean emitting;
    boolean missed;
    final NotificationLite<T> nl;
    final Producer producer;
    final Queue<Object> queue;

    public SingleEmitter(Subscriber<? super T> paramSubscriber, Producer paramProducer, Subscription paramSubscription)
    {
      this.child = paramSubscriber;
      this.producer = paramProducer;
      this.cancel = paramSubscription;
      if (UnsafeAccess.isUnsafeAvailable());
      for (Object localObject = new SpscArrayQueue(2); ; localObject = new ConcurrentLinkedQueue())
      {
        this.queue = ((Queue)localObject);
        this.nl = NotificationLite.instance();
        return;
      }
    }

    // ERROR //
    void drain()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 71	rx/internal/operators/OperatorMapNotification$SingleEmitter:emitting	Z
      //   6: ifeq +13 -> 19
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 73	rx/internal/operators/OperatorMapNotification$SingleEmitter:missed	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: goto +245 -> 261
      //   19: aload_0
      //   20: iconst_1
      //   21: putfield 71	rx/internal/operators/OperatorMapNotification$SingleEmitter:emitting	Z
      //   24: aload_0
      //   25: iconst_0
      //   26: putfield 73	rx/internal/operators/OperatorMapNotification$SingleEmitter:missed	Z
      //   29: aload_0
      //   30: monitorexit
      //   31: iconst_0
      //   32: istore_2
      //   33: aload_0
      //   34: invokevirtual 77	rx/internal/operators/OperatorMapNotification$SingleEmitter:get	()J
      //   37: lstore 5
      //   39: aload_0
      //   40: getfield 79	rx/internal/operators/OperatorMapNotification$SingleEmitter:complete	Z
      //   43: istore 7
      //   45: aload_0
      //   46: getfield 57	rx/internal/operators/OperatorMapNotification$SingleEmitter:queue	Ljava/util/Queue;
      //   49: invokeinterface 84 1 0
      //   54: istore 8
      //   56: iload 7
      //   58: ifeq +43 -> 101
      //   61: iload 8
      //   63: ifeq +38 -> 101
      //   66: aload_0
      //   67: getfield 40	rx/internal/operators/OperatorMapNotification$SingleEmitter:child	Lrx/Subscriber;
      //   70: invokevirtual 89	rx/Subscriber:onCompleted	()V
      //   73: iconst_1
      //   74: ifne +187 -> 261
      //   77: aload_0
      //   78: monitorenter
      //   79: aload_0
      //   80: iconst_0
      //   81: putfield 71	rx/internal/operators/OperatorMapNotification$SingleEmitter:emitting	Z
      //   84: aload_0
      //   85: monitorexit
      //   86: goto +175 -> 261
      //   89: astore 13
      //   91: aload_0
      //   92: monitorexit
      //   93: aload 13
      //   95: athrow
      //   96: astore_1
      //   97: aload_0
      //   98: monitorexit
      //   99: aload_1
      //   100: athrow
      //   101: lload 5
      //   103: lconst_0
      //   104: lcmp
      //   105: ifle +40 -> 145
      //   108: aload_0
      //   109: getfield 57	rx/internal/operators/OperatorMapNotification$SingleEmitter:queue	Ljava/util/Queue;
      //   112: invokeinterface 93 1 0
      //   117: astore 11
      //   119: aload 11
      //   121: ifnull +65 -> 186
      //   124: aload_0
      //   125: getfield 40	rx/internal/operators/OperatorMapNotification$SingleEmitter:child	Lrx/Subscriber;
      //   128: aload_0
      //   129: getfield 65	rx/internal/operators/OperatorMapNotification$SingleEmitter:nl	Lrx/internal/operators/NotificationLite;
      //   132: aload 11
      //   134: invokevirtual 97	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   137: invokevirtual 101	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   140: aload_0
      //   141: lconst_1
      //   142: invokevirtual 105	rx/internal/operators/OperatorMapNotification$SingleEmitter:produced	(J)V
      //   145: aload_0
      //   146: monitorenter
      //   147: aload_0
      //   148: getfield 73	rx/internal/operators/OperatorMapNotification$SingleEmitter:missed	Z
      //   151: ifne +70 -> 221
      //   154: iconst_1
      //   155: istore_2
      //   156: aload_0
      //   157: iconst_0
      //   158: putfield 71	rx/internal/operators/OperatorMapNotification$SingleEmitter:emitting	Z
      //   161: aload_0
      //   162: monitorexit
      //   163: iload_2
      //   164: ifne +97 -> 261
      //   167: aload_0
      //   168: monitorenter
      //   169: aload_0
      //   170: iconst_0
      //   171: putfield 71	rx/internal/operators/OperatorMapNotification$SingleEmitter:emitting	Z
      //   174: aload_0
      //   175: monitorexit
      //   176: goto +85 -> 261
      //   179: astore 10
      //   181: aload_0
      //   182: monitorexit
      //   183: aload 10
      //   185: athrow
      //   186: iload 7
      //   188: ifeq -43 -> 145
      //   191: aload_0
      //   192: getfield 40	rx/internal/operators/OperatorMapNotification$SingleEmitter:child	Lrx/Subscriber;
      //   195: invokevirtual 89	rx/Subscriber:onCompleted	()V
      //   198: iconst_1
      //   199: ifne +62 -> 261
      //   202: aload_0
      //   203: monitorenter
      //   204: aload_0
      //   205: iconst_0
      //   206: putfield 71	rx/internal/operators/OperatorMapNotification$SingleEmitter:emitting	Z
      //   209: aload_0
      //   210: monitorexit
      //   211: goto +50 -> 261
      //   214: astore 12
      //   216: aload_0
      //   217: monitorexit
      //   218: aload 12
      //   220: athrow
      //   221: aload_0
      //   222: iconst_0
      //   223: putfield 73	rx/internal/operators/OperatorMapNotification$SingleEmitter:missed	Z
      //   226: aload_0
      //   227: monitorexit
      //   228: goto -195 -> 33
      //   231: astore 9
      //   233: aload_0
      //   234: monitorexit
      //   235: aload 9
      //   237: athrow
      //   238: astore_3
      //   239: iload_2
      //   240: ifne +12 -> 252
      //   243: aload_0
      //   244: monitorenter
      //   245: aload_0
      //   246: iconst_0
      //   247: putfield 71	rx/internal/operators/OperatorMapNotification$SingleEmitter:emitting	Z
      //   250: aload_0
      //   251: monitorexit
      //   252: aload_3
      //   253: athrow
      //   254: astore 4
      //   256: aload_0
      //   257: monitorexit
      //   258: aload 4
      //   260: athrow
      //   261: return
      //
      // Exception table:
      //   from	to	target	type
      //   79	93	89	finally
      //   2	31	96	finally
      //   97	99	96	finally
      //   169	183	179	finally
      //   204	218	214	finally
      //   147	163	231	finally
      //   221	235	231	finally
      //   33	73	238	finally
      //   108	147	238	finally
      //   191	198	238	finally
      //   235	238	238	finally
      //   245	252	254	finally
      //   256	258	254	finally
    }

    public boolean isUnsubscribed()
    {
      if (get() < 0L);
      for (int i = 1; ; i = 0)
        return i;
    }

    public void offer(T paramT)
    {
      if (!this.queue.offer(paramT))
      {
        this.child.onError(new MissingBackpressureException());
        unsubscribe();
      }
      while (true)
      {
        return;
        drain();
      }
    }

    public void offerAndComplete(T paramT)
    {
      if (!this.queue.offer(paramT))
      {
        this.child.onError(new MissingBackpressureException());
        unsubscribe();
      }
      while (true)
      {
        return;
        this.complete = true;
        drain();
      }
    }

    void produced(long paramLong)
    {
      long l1 = get();
      if (l1 < 0L);
      while (true)
      {
        return;
        long l2 = l1 - paramLong;
        if (l2 < 0L)
          throw new IllegalStateException("More produced (" + paramLong + ") than requested (" + l1 + ")");
        if (!compareAndSet(l1, l2))
          break;
      }
    }

    public void request(long paramLong)
    {
      long l1 = get();
      if (l1 < 0L);
      while (true)
      {
        return;
        long l2 = l1 + paramLong;
        if (l2 < 0L)
          l2 = 9223372036854775807L;
        if (!compareAndSet(l1, l2))
          break;
        this.producer.request(paramLong);
        drain();
      }
    }

    public void unsubscribe()
    {
      if ((get() != -9223372036854775808L) && (getAndSet(-9223372036854775808L) != -9223372036854775808L))
        this.cancel.unsubscribe();
    }
  }

  final class MapNotificationSubscriber extends Subscriber<T>
  {
    final OperatorMapNotification.SingleEmitter<R> emitter;
    private final Subscriber<? super R> o;
    private final ProducerArbiter pa;

    private MapNotificationSubscriber(Subscriber<? super R> arg2)
    {
      Producer localProducer;
      this.pa = localProducer;
      Subscriber localSubscriber;
      this.o = localSubscriber;
      this.emitter = new OperatorMapNotification.SingleEmitter(localSubscriber, localProducer, this);
    }

    void init()
    {
      this.o.setProducer(this.emitter);
    }

    public void onCompleted()
    {
      try
      {
        this.emitter.offerAndComplete(OperatorMapNotification.this.onCompleted.call());
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          Exceptions.throwOrReport(localThrowable, this.o);
      }
    }

    public void onError(Throwable paramThrowable)
    {
      try
      {
        this.emitter.offerAndComplete(OperatorMapNotification.this.onError.call(paramThrowable));
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          Exceptions.throwOrReport(localThrowable, this.o);
      }
    }

    public void onNext(T paramT)
    {
      try
      {
        this.emitter.offer(OperatorMapNotification.this.onNext.call(paramT));
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          Exceptions.throwOrReport(localThrowable, this.o, paramT);
      }
    }

    public void setProducer(Producer paramProducer)
    {
      this.pa.setProducer(paramProducer);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorMapNotification
 * JD-Core Version:    0.6.0
 */