package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.internal.util.LinkedArrayList;
import rx.subscriptions.SerialSubscription;

public final class CachedObservable<T> extends Observable<T>
{
  private final CacheState<T> state;

  private CachedObservable(Observable.OnSubscribe<T> paramOnSubscribe, CacheState<T> paramCacheState)
  {
    super(paramOnSubscribe);
    this.state = paramCacheState;
  }

  public static <T> CachedObservable<T> from(Observable<? extends T> paramObservable)
  {
    return from(paramObservable, 16);
  }

  public static <T> CachedObservable<T> from(Observable<? extends T> paramObservable, int paramInt)
  {
    if (paramInt < 1)
      throw new IllegalArgumentException("capacityHint > 0 required");
    CacheState localCacheState = new CacheState(paramObservable, paramInt);
    return new CachedObservable(new CachedSubscribe(localCacheState), localCacheState);
  }

  boolean hasObservers()
  {
    if (this.state.producers.length != 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  boolean isConnected()
  {
    return this.state.isConnected;
  }

  static final class ReplayProducer<T> extends AtomicLong
    implements Producer, Subscription
  {
    private static final long serialVersionUID = -2557562030197141021L;
    final Subscriber<? super T> child;
    Object[] currentBuffer;
    int currentIndexInBuffer;
    boolean emitting;
    int index;
    boolean missed;
    final CachedObservable.CacheState<T> state;

    public ReplayProducer(Subscriber<? super T> paramSubscriber, CachedObservable.CacheState<T> paramCacheState)
    {
      this.child = paramSubscriber;
      this.state = paramCacheState;
    }

    public boolean isUnsubscribed()
    {
      if (get() < 0L);
      for (int i = 1; ; i = 0)
        return i;
    }

    public long produced(long paramLong)
    {
      return addAndGet(-paramLong);
    }

    // ERROR //
    public void replay()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   6: ifeq +13 -> 19
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 57	rx/internal/operators/CachedObservable$ReplayProducer:missed	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: goto +604 -> 620
      //   19: aload_0
      //   20: iconst_1
      //   21: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   24: aload_0
      //   25: monitorexit
      //   26: iconst_0
      //   27: istore_2
      //   28: aload_0
      //   29: getfield 39	rx/internal/operators/CachedObservable$ReplayProducer:state	Lrx/internal/operators/CachedObservable$CacheState;
      //   32: getfield 63	rx/internal/operators/CachedObservable$CacheState:nl	Lrx/internal/operators/NotificationLite;
      //   35: astore 5
      //   37: aload_0
      //   38: getfield 37	rx/internal/operators/CachedObservable$ReplayProducer:child	Lrx/Subscriber;
      //   41: astore 6
      //   43: aload_0
      //   44: invokevirtual 45	rx/internal/operators/CachedObservable$ReplayProducer:get	()J
      //   47: lstore 7
      //   49: lload 7
      //   51: lstore 9
      //   53: lload 9
      //   55: lconst_0
      //   56: lcmp
      //   57: ifge +31 -> 88
      //   60: iconst_1
      //   61: ifne +559 -> 620
      //   64: aload_0
      //   65: monitorenter
      //   66: aload_0
      //   67: iconst_0
      //   68: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   71: aload_0
      //   72: monitorexit
      //   73: goto +547 -> 620
      //   76: astore 32
      //   78: aload_0
      //   79: monitorexit
      //   80: aload 32
      //   82: athrow
      //   83: astore_1
      //   84: aload_0
      //   85: monitorexit
      //   86: aload_1
      //   87: athrow
      //   88: aload_0
      //   89: getfield 39	rx/internal/operators/CachedObservable$ReplayProducer:state	Lrx/internal/operators/CachedObservable$CacheState;
      //   92: invokevirtual 67	rx/internal/operators/CachedObservable$CacheState:size	()I
      //   95: istore 11
      //   97: iload 11
      //   99: ifeq +440 -> 539
      //   102: aload_0
      //   103: getfield 69	rx/internal/operators/CachedObservable$ReplayProducer:currentBuffer	[Ljava/lang/Object;
      //   106: astore 14
      //   108: aload 14
      //   110: ifnonnull +18 -> 128
      //   113: aload_0
      //   114: getfield 39	rx/internal/operators/CachedObservable$ReplayProducer:state	Lrx/internal/operators/CachedObservable$CacheState;
      //   117: invokevirtual 73	rx/internal/operators/CachedObservable$CacheState:head	()[Ljava/lang/Object;
      //   120: astore 14
      //   122: aload_0
      //   123: aload 14
      //   125: putfield 69	rx/internal/operators/CachedObservable$ReplayProducer:currentBuffer	[Ljava/lang/Object;
      //   128: bipush 255
      //   130: aload 14
      //   132: arraylength
      //   133: iadd
      //   134: istore 15
      //   136: aload_0
      //   137: getfield 75	rx/internal/operators/CachedObservable$ReplayProducer:index	I
      //   140: istore 16
      //   142: aload_0
      //   143: getfield 77	rx/internal/operators/CachedObservable$ReplayProducer:currentIndexInBuffer	I
      //   146: istore 17
      //   148: lload 9
      //   150: lconst_0
      //   151: lcmp
      //   152: ifne +105 -> 257
      //   155: aload 14
      //   157: iload 17
      //   159: aaload
      //   160: astore 29
      //   162: aload 5
      //   164: aload 29
      //   166: invokevirtual 83	rx/internal/operators/NotificationLite:isCompleted	(Ljava/lang/Object;)Z
      //   169: ifeq +37 -> 206
      //   172: aload 6
      //   174: invokevirtual 88	rx/Subscriber:onCompleted	()V
      //   177: iconst_1
      //   178: istore_2
      //   179: aload_0
      //   180: invokevirtual 91	rx/internal/operators/CachedObservable$ReplayProducer:unsubscribe	()V
      //   183: iload_2
      //   184: ifne +436 -> 620
      //   187: aload_0
      //   188: monitorenter
      //   189: aload_0
      //   190: iconst_0
      //   191: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   194: aload_0
      //   195: monitorexit
      //   196: goto +424 -> 620
      //   199: astore 31
      //   201: aload_0
      //   202: monitorexit
      //   203: aload 31
      //   205: athrow
      //   206: aload 5
      //   208: aload 29
      //   210: invokevirtual 94	rx/internal/operators/NotificationLite:isError	(Ljava/lang/Object;)Z
      //   213: ifeq +326 -> 539
      //   216: aload 6
      //   218: aload 5
      //   220: aload 29
      //   222: invokevirtual 98	rx/internal/operators/NotificationLite:getError	(Ljava/lang/Object;)Ljava/lang/Throwable;
      //   225: invokevirtual 102	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   228: iconst_1
      //   229: istore_2
      //   230: aload_0
      //   231: invokevirtual 91	rx/internal/operators/CachedObservable$ReplayProducer:unsubscribe	()V
      //   234: iload_2
      //   235: ifne +385 -> 620
      //   238: aload_0
      //   239: monitorenter
      //   240: aload_0
      //   241: iconst_0
      //   242: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   245: aload_0
      //   246: monitorexit
      //   247: goto +373 -> 620
      //   250: astore 30
      //   252: aload_0
      //   253: monitorexit
      //   254: aload 30
      //   256: athrow
      //   257: lload 9
      //   259: lconst_0
      //   260: lcmp
      //   261: ifle +278 -> 539
      //   264: iconst_0
      //   265: istore 18
      //   267: iload 16
      //   269: iload 11
      //   271: if_icmpge +207 -> 478
      //   274: lload 9
      //   276: lconst_0
      //   277: lcmp
      //   278: ifle +200 -> 478
      //   281: aload 6
      //   283: invokevirtual 104	rx/Subscriber:isUnsubscribed	()Z
      //   286: istore 23
      //   288: iload 23
      //   290: ifeq +26 -> 316
      //   293: iconst_1
      //   294: ifne +326 -> 620
      //   297: aload_0
      //   298: monitorenter
      //   299: aload_0
      //   300: iconst_0
      //   301: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   304: aload_0
      //   305: monitorexit
      //   306: goto +314 -> 620
      //   309: astore 28
      //   311: aload_0
      //   312: monitorexit
      //   313: aload 28
      //   315: athrow
      //   316: iload 17
      //   318: iload 15
      //   320: if_icmpne +19 -> 339
      //   323: aload 14
      //   325: iload 15
      //   327: aaload
      //   328: checkcast 105	[Ljava/lang/Object;
      //   331: checkcast 105	[Ljava/lang/Object;
      //   334: astore 14
      //   336: iconst_0
      //   337: istore 17
      //   339: aload 14
      //   341: iload 17
      //   343: aaload
      //   344: astore 24
      //   346: aload 5
      //   348: aload 6
      //   350: aload 24
      //   352: invokevirtual 109	rx/internal/operators/NotificationLite:accept	(Lrx/Observer;Ljava/lang/Object;)Z
      //   355: ifeq +105 -> 460
      //   358: iconst_1
      //   359: istore_2
      //   360: aload_0
      //   361: invokevirtual 91	rx/internal/operators/CachedObservable$ReplayProducer:unsubscribe	()V
      //   364: iload_2
      //   365: ifne +255 -> 620
      //   368: aload_0
      //   369: monitorenter
      //   370: aload_0
      //   371: iconst_0
      //   372: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   375: aload_0
      //   376: monitorexit
      //   377: goto +243 -> 620
      //   380: astore 27
      //   382: aload_0
      //   383: monitorexit
      //   384: aload 27
      //   386: athrow
      //   387: astore 25
      //   389: aload 25
      //   391: invokestatic 114	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   394: iconst_1
      //   395: istore_2
      //   396: aload_0
      //   397: invokevirtual 91	rx/internal/operators/CachedObservable$ReplayProducer:unsubscribe	()V
      //   400: aload 5
      //   402: aload 24
      //   404: invokevirtual 94	rx/internal/operators/NotificationLite:isError	(Ljava/lang/Object;)Z
      //   407: ifne +30 -> 437
      //   410: aload 5
      //   412: aload 24
      //   414: invokevirtual 83	rx/internal/operators/NotificationLite:isCompleted	(Ljava/lang/Object;)Z
      //   417: ifne +20 -> 437
      //   420: aload 6
      //   422: aload 25
      //   424: aload 5
      //   426: aload 24
      //   428: invokevirtual 118	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   431: invokestatic 124	rx/exceptions/OnErrorThrowable:addValueAsLastCause	(Ljava/lang/Throwable;Ljava/lang/Object;)Ljava/lang/Throwable;
      //   434: invokevirtual 102	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   437: iload_2
      //   438: ifne +182 -> 620
      //   441: aload_0
      //   442: monitorenter
      //   443: aload_0
      //   444: iconst_0
      //   445: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   448: aload_0
      //   449: monitorexit
      //   450: goto +170 -> 620
      //   453: astore 26
      //   455: aload_0
      //   456: monitorexit
      //   457: aload 26
      //   459: athrow
      //   460: iinc 17 1
      //   463: iinc 16 1
      //   466: lload 9
      //   468: lconst_1
      //   469: lsub
      //   470: lstore 9
      //   472: iinc 18 1
      //   475: goto -208 -> 267
      //   478: aload 6
      //   480: invokevirtual 104	rx/Subscriber:isUnsubscribed	()Z
      //   483: istore 19
      //   485: iload 19
      //   487: ifeq +26 -> 513
      //   490: iconst_1
      //   491: ifne +129 -> 620
      //   494: aload_0
      //   495: monitorenter
      //   496: aload_0
      //   497: iconst_0
      //   498: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   501: aload_0
      //   502: monitorexit
      //   503: goto +117 -> 620
      //   506: astore 22
      //   508: aload_0
      //   509: monitorexit
      //   510: aload 22
      //   512: athrow
      //   513: aload_0
      //   514: iload 16
      //   516: putfield 75	rx/internal/operators/CachedObservable$ReplayProducer:index	I
      //   519: aload_0
      //   520: iload 17
      //   522: putfield 77	rx/internal/operators/CachedObservable$ReplayProducer:currentIndexInBuffer	I
      //   525: aload_0
      //   526: aload 14
      //   528: putfield 69	rx/internal/operators/CachedObservable$ReplayProducer:currentBuffer	[Ljava/lang/Object;
      //   531: aload_0
      //   532: iload 18
      //   534: i2l
      //   535: invokevirtual 126	rx/internal/operators/CachedObservable$ReplayProducer:produced	(J)J
      //   538: pop2
      //   539: aload_0
      //   540: monitorenter
      //   541: aload_0
      //   542: getfield 57	rx/internal/operators/CachedObservable$ReplayProducer:missed	Z
      //   545: ifne +35 -> 580
      //   548: aload_0
      //   549: iconst_0
      //   550: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   553: iconst_1
      //   554: istore_2
      //   555: aload_0
      //   556: monitorexit
      //   557: iload_2
      //   558: ifne +62 -> 620
      //   561: aload_0
      //   562: monitorenter
      //   563: aload_0
      //   564: iconst_0
      //   565: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   568: aload_0
      //   569: monitorexit
      //   570: goto +50 -> 620
      //   573: astore 13
      //   575: aload_0
      //   576: monitorexit
      //   577: aload 13
      //   579: athrow
      //   580: aload_0
      //   581: iconst_0
      //   582: putfield 57	rx/internal/operators/CachedObservable$ReplayProducer:missed	Z
      //   585: aload_0
      //   586: monitorexit
      //   587: goto -544 -> 43
      //   590: astore 12
      //   592: aload_0
      //   593: monitorexit
      //   594: aload 12
      //   596: athrow
      //   597: astore_3
      //   598: iload_2
      //   599: ifne +12 -> 611
      //   602: aload_0
      //   603: monitorenter
      //   604: aload_0
      //   605: iconst_0
      //   606: putfield 55	rx/internal/operators/CachedObservable$ReplayProducer:emitting	Z
      //   609: aload_0
      //   610: monitorexit
      //   611: aload_3
      //   612: athrow
      //   613: astore 4
      //   615: aload_0
      //   616: monitorexit
      //   617: aload 4
      //   619: athrow
      //   620: return
      //
      // Exception table:
      //   from	to	target	type
      //   66	80	76	finally
      //   2	26	83	finally
      //   84	86	83	finally
      //   189	203	199	finally
      //   240	254	250	finally
      //   299	313	309	finally
      //   370	384	380	finally
      //   346	364	387	java/lang/Throwable
      //   443	457	453	finally
      //   496	510	506	finally
      //   563	577	573	finally
      //   541	557	590	finally
      //   580	594	590	finally
      //   28	49	597	finally
      //   88	183	597	finally
      //   206	234	597	finally
      //   281	288	597	finally
      //   323	346	597	finally
      //   346	364	597	finally
      //   389	437	597	finally
      //   478	485	597	finally
      //   513	541	597	finally
      //   594	597	597	finally
      //   604	611	613	finally
      //   615	617	613	finally
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
        replay();
      }
    }

    public void unsubscribe()
    {
      if ((get() >= 0L) && (getAndSet(-1L) >= 0L))
        this.state.removeProducer(this);
    }
  }

  static final class CachedSubscribe<T> extends AtomicBoolean
    implements Observable.OnSubscribe<T>
  {
    private static final long serialVersionUID = -2817751667698696782L;
    final CachedObservable.CacheState<T> state;

    public CachedSubscribe(CachedObservable.CacheState<T> paramCacheState)
    {
      this.state = paramCacheState;
    }

    public void call(Subscriber<? super T> paramSubscriber)
    {
      CachedObservable.ReplayProducer localReplayProducer = new CachedObservable.ReplayProducer(paramSubscriber, this.state);
      this.state.addProducer(localReplayProducer);
      paramSubscriber.add(localReplayProducer);
      paramSubscriber.setProducer(localReplayProducer);
      if ((!get()) && (compareAndSet(false, true)))
        this.state.connect();
    }
  }

  static final class CacheState<T> extends LinkedArrayList
    implements Observer<T>
  {
    static final CachedObservable.ReplayProducer<?>[] EMPTY = new CachedObservable.ReplayProducer[0];
    final SerialSubscription connection;
    volatile boolean isConnected;
    final NotificationLite<T> nl;
    volatile CachedObservable.ReplayProducer<?>[] producers;
    final Observable<? extends T> source;
    boolean sourceDone;

    public CacheState(Observable<? extends T> paramObservable, int paramInt)
    {
      super();
      this.source = paramObservable;
      this.producers = EMPTY;
      this.nl = NotificationLite.instance();
      this.connection = new SerialSubscription();
    }

    public void addProducer(CachedObservable.ReplayProducer<T> paramReplayProducer)
    {
      synchronized (this.connection)
      {
        CachedObservable.ReplayProducer[] arrayOfReplayProducer1 = this.producers;
        int i = arrayOfReplayProducer1.length;
        CachedObservable.ReplayProducer[] arrayOfReplayProducer2 = new CachedObservable.ReplayProducer[i + 1];
        System.arraycopy(arrayOfReplayProducer1, 0, arrayOfReplayProducer2, 0, i);
        arrayOfReplayProducer2[i] = paramReplayProducer;
        this.producers = arrayOfReplayProducer2;
        return;
      }
    }

    public void connect()
    {
      1 local1 = new Subscriber()
      {
        public void onCompleted()
        {
          CachedObservable.CacheState.this.onCompleted();
        }

        public void onError(Throwable paramThrowable)
        {
          CachedObservable.CacheState.this.onError(paramThrowable);
        }

        public void onNext(T paramT)
        {
          CachedObservable.CacheState.this.onNext(paramT);
        }
      };
      this.connection.set(local1);
      this.source.unsafeSubscribe(local1);
      this.isConnected = true;
    }

    void dispatch()
    {
      CachedObservable.ReplayProducer[] arrayOfReplayProducer = this.producers;
      int i = arrayOfReplayProducer.length;
      for (int j = 0; j < i; j++)
        arrayOfReplayProducer[j].replay();
    }

    public void onCompleted()
    {
      if (!this.sourceDone)
      {
        this.sourceDone = true;
        add(this.nl.completed());
        this.connection.unsubscribe();
        dispatch();
      }
    }

    public void onError(Throwable paramThrowable)
    {
      if (!this.sourceDone)
      {
        this.sourceDone = true;
        add(this.nl.error(paramThrowable));
        this.connection.unsubscribe();
        dispatch();
      }
    }

    public void onNext(T paramT)
    {
      if (!this.sourceDone)
      {
        add(this.nl.next(paramT));
        dispatch();
      }
    }

    public void removeProducer(CachedObservable.ReplayProducer<T> paramReplayProducer)
    {
      while (true)
      {
        CachedObservable.ReplayProducer[] arrayOfReplayProducer1;
        int i;
        int j;
        int k;
        int m;
        CachedObservable.ReplayProducer[] arrayOfReplayProducer2;
        synchronized (this.connection)
        {
          arrayOfReplayProducer1 = this.producers;
          i = arrayOfReplayProducer1.length;
          j = -1;
          k = 0;
          if (k >= i)
            continue;
          if (arrayOfReplayProducer1[k].equals(paramReplayProducer))
          {
            j = k;
            if (j < 0)
              continue;
            if (i != 1)
              continue;
            this.producers = EMPTY;
          }
        }
        k++;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.CachedObservable
 * JD-Core Version:    0.6.0
 */