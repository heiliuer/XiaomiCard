package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.SynchronizedQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;

public final class OperatorPublish<T> extends ConnectableObservable<T>
{
  final AtomicReference<PublishSubscriber<T>> current;
  final Observable<? extends T> source;

  private OperatorPublish(Observable.OnSubscribe<T> paramOnSubscribe, Observable<? extends T> paramObservable, AtomicReference<PublishSubscriber<T>> paramAtomicReference)
  {
    super(paramOnSubscribe);
    this.source = paramObservable;
    this.current = paramAtomicReference;
  }

  public static <T, R> Observable<R> create(Observable<? extends T> paramObservable, Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1)
  {
    return create(new Observable.OnSubscribe(paramObservable, paramFunc1)
    {
      public void call(Subscriber<? super R> paramSubscriber)
      {
        ConnectableObservable localConnectableObservable = OperatorPublish.create(OperatorPublish.this);
        ((Observable)this.val$selector.call(localConnectableObservable)).unsafeSubscribe(paramSubscriber);
        localConnectableObservable.connect(new Action1(paramSubscriber)
        {
          public void call(Subscription paramSubscription)
          {
            this.val$child.add(paramSubscription);
          }
        });
      }
    });
  }

  public static <T> ConnectableObservable<T> create(Observable<? extends T> paramObservable)
  {
    AtomicReference localAtomicReference = new AtomicReference();
    return new OperatorPublish(new Observable.OnSubscribe(localAtomicReference)
    {
      public void call(Subscriber<? super T> paramSubscriber)
      {
        Object localObject;
        OperatorPublish.InnerProducer localInnerProducer;
        do
        {
          while (true)
          {
            localObject = (OperatorPublish.PublishSubscriber)OperatorPublish.this.get();
            if ((localObject != null) && (!((OperatorPublish.PublishSubscriber)localObject).isUnsubscribed()))
              break;
            OperatorPublish.PublishSubscriber localPublishSubscriber = new OperatorPublish.PublishSubscriber(OperatorPublish.this);
            localPublishSubscriber.init();
            if (!OperatorPublish.this.compareAndSet(localObject, localPublishSubscriber))
              continue;
            localObject = localPublishSubscriber;
          }
          localInnerProducer = new OperatorPublish.InnerProducer((OperatorPublish.PublishSubscriber)localObject, paramSubscriber);
        }
        while (!((OperatorPublish.PublishSubscriber)localObject).add(localInnerProducer));
        paramSubscriber.add(localInnerProducer);
        paramSubscriber.setProducer(localInnerProducer);
      }
    }
    , paramObservable, localAtomicReference);
  }

  public void connect(Action1<? super Subscription> paramAction1)
  {
    Object localObject;
    while (true)
    {
      localObject = (PublishSubscriber)this.current.get();
      if ((localObject != null) && (!((PublishSubscriber)localObject).isUnsubscribed()))
        break;
      PublishSubscriber localPublishSubscriber = new PublishSubscriber(this.current);
      localPublishSubscriber.init();
      if (!this.current.compareAndSet(localObject, localPublishSubscriber))
        continue;
      localObject = localPublishSubscriber;
    }
    if ((!((PublishSubscriber)localObject).shouldConnect.get()) && (((PublishSubscriber)localObject).shouldConnect.compareAndSet(false, true)));
    for (int i = 1; ; i = 0)
    {
      paramAction1.call(localObject);
      if (i != 0)
        this.source.unsafeSubscribe((Subscriber)localObject);
      return;
    }
  }

  static final class InnerProducer<T> extends AtomicLong
    implements Producer, Subscription
  {
    static final long NOT_REQUESTED = -4611686018427387904L;
    static final long UNSUBSCRIBED = -9223372036854775808L;
    private static final long serialVersionUID = -4453897557930727610L;
    final Subscriber<? super T> child;
    final OperatorPublish.PublishSubscriber<T> parent;

    public InnerProducer(OperatorPublish.PublishSubscriber<T> paramPublishSubscriber, Subscriber<? super T> paramSubscriber)
    {
      this.parent = paramPublishSubscriber;
      this.child = paramSubscriber;
      lazySet(-4611686018427387904L);
    }

    public boolean isUnsubscribed()
    {
      if (get() == -9223372036854775808L);
      for (int i = 1; ; i = 0)
        return i;
    }

    public long produced(long paramLong)
    {
      if (paramLong <= 0L)
        throw new IllegalArgumentException("Cant produce zero or less");
      long l1 = get();
      if (l1 == -4611686018427387904L)
        throw new IllegalStateException("Produced without request");
      long l2;
      if (l1 == -9223372036854775808L)
        l2 = -9223372036854775808L;
      while (true)
      {
        return l2;
        l2 = l1 - paramLong;
        if (l2 < 0L)
          throw new IllegalStateException("More produced (" + paramLong + ") than requested (" + l1 + ")");
        if (!compareAndSet(l1, l2))
          break;
      }
    }

    public void request(long paramLong)
    {
      if (paramLong < 0L)
        return;
      label81: 
      while (true)
      {
        long l1 = get();
        if ((l1 == -9223372036854775808L) || ((l1 >= 0L) && (paramLong == 0L)))
          break;
        long l2;
        if (l1 == -4611686018427387904L)
          l2 = paramLong;
        while (true)
        {
          if (!compareAndSet(l1, l2))
            break label81;
          this.parent.dispatch();
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
      if ((get() != -9223372036854775808L) && (getAndSet(-9223372036854775808L) != -9223372036854775808L))
      {
        this.parent.remove(this);
        this.parent.dispatch();
      }
    }
  }

  static final class PublishSubscriber<T> extends Subscriber<T>
    implements Subscription
  {
    static final OperatorPublish.InnerProducer[] EMPTY = new OperatorPublish.InnerProducer[0];
    static final OperatorPublish.InnerProducer[] TERMINATED = new OperatorPublish.InnerProducer[0];
    final AtomicReference<PublishSubscriber<T>> current;
    boolean emitting;
    boolean missed;
    final NotificationLite<T> nl;
    final AtomicReference<OperatorPublish.InnerProducer[]> producers;
    final Queue<Object> queue;
    final AtomicBoolean shouldConnect;
    volatile Object terminalEvent;

    public PublishSubscriber(AtomicReference<PublishSubscriber<T>> paramAtomicReference)
    {
      if (UnsafeAccess.isUnsafeAvailable());
      for (Object localObject = new SpscArrayQueue(RxRingBuffer.SIZE); ; localObject = new SynchronizedQueue(RxRingBuffer.SIZE))
      {
        this.queue = ((Queue)localObject);
        this.nl = NotificationLite.instance();
        this.producers = new AtomicReference(EMPTY);
        this.current = paramAtomicReference;
        this.shouldConnect = new AtomicBoolean();
        return;
      }
    }

    boolean add(OperatorPublish.InnerProducer<T> paramInnerProducer)
    {
      int i = 0;
      if (paramInnerProducer == null)
        throw new NullPointerException();
      OperatorPublish.InnerProducer[] arrayOfInnerProducer1 = (OperatorPublish.InnerProducer[])this.producers.get();
      if (arrayOfInnerProducer1 == TERMINATED);
      while (true)
      {
        return i;
        int j = arrayOfInnerProducer1.length;
        OperatorPublish.InnerProducer[] arrayOfInnerProducer2 = new OperatorPublish.InnerProducer[j + 1];
        System.arraycopy(arrayOfInnerProducer1, 0, arrayOfInnerProducer2, 0, j);
        arrayOfInnerProducer2[j] = paramInnerProducer;
        if (!this.producers.compareAndSet(arrayOfInnerProducer1, arrayOfInnerProducer2))
          break;
        i = 1;
      }
    }

    boolean checkTerminated(Object paramObject, boolean paramBoolean)
    {
      int i = 1;
      if (paramObject != null)
        if (this.nl.isCompleted(paramObject))
        {
          if (!paramBoolean)
            break label178;
          this.current.compareAndSet(this, null);
        }
      while (true)
      {
        try
        {
          OperatorPublish.InnerProducer[] arrayOfInnerProducer2 = (OperatorPublish.InnerProducer[])this.producers.getAndSet(TERMINATED);
          int m = arrayOfInnerProducer2.length;
          int n = 0;
          if (n >= m)
            continue;
          arrayOfInnerProducer2[n].child.onCompleted();
          n++;
          continue;
          return i;
        }
        finally
        {
          unsubscribe();
        }
        Throwable localThrowable = this.nl.getError(paramObject);
        this.current.compareAndSet(this, null);
        label178: 
        try
        {
          OperatorPublish.InnerProducer[] arrayOfInnerProducer1 = (OperatorPublish.InnerProducer[])this.producers.getAndSet(TERMINATED);
          int j = arrayOfInnerProducer1.length;
          for (int k = 0; k < j; k++)
            arrayOfInnerProducer1[k].child.onError(localThrowable);
          unsubscribe();
        }
        finally
        {
          unsubscribe();
        }
      }
    }

    // ERROR //
    void dispatch()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   6: ifeq +13 -> 19
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 144	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: goto +512 -> 528
      //   19: aload_0
      //   20: iconst_1
      //   21: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   24: aload_0
      //   25: iconst_0
      //   26: putfield 144	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   29: aload_0
      //   30: monitorexit
      //   31: iconst_0
      //   32: istore_2
      //   33: aload_0
      //   34: getfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:terminalEvent	Ljava/lang/Object;
      //   37: astore 5
      //   39: aload_0
      //   40: getfield 64	rx/internal/operators/OperatorPublish$PublishSubscriber:queue	Ljava/util/Queue;
      //   43: invokeinterface 151 1 0
      //   48: istore 6
      //   50: aload_0
      //   51: aload 5
      //   53: iload 6
      //   55: invokevirtual 153	rx/internal/operators/OperatorPublish$PublishSubscriber:checkTerminated	(Ljava/lang/Object;Z)Z
      //   58: istore 7
      //   60: iload 7
      //   62: ifeq +31 -> 93
      //   65: iconst_1
      //   66: ifne +462 -> 528
      //   69: aload_0
      //   70: monitorenter
      //   71: aload_0
      //   72: iconst_0
      //   73: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   76: aload_0
      //   77: monitorexit
      //   78: goto +450 -> 528
      //   81: astore 39
      //   83: aload_0
      //   84: monitorexit
      //   85: aload 39
      //   87: athrow
      //   88: astore_1
      //   89: aload_0
      //   90: monitorexit
      //   91: aload_1
      //   92: athrow
      //   93: iload 6
      //   95: ifne +273 -> 368
      //   98: aload_0
      //   99: getfield 79	rx/internal/operators/OperatorPublish$PublishSubscriber:producers	Ljava/util/concurrent/atomic/AtomicReference;
      //   102: invokevirtual 98	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
      //   105: checkcast 99	[Lrx/internal/operators/OperatorPublish$InnerProducer;
      //   108: astore 10
      //   110: aload 10
      //   112: arraylength
      //   113: istore 11
      //   115: ldc2_w 154
      //   118: lstore 12
      //   120: iconst_0
      //   121: istore 14
      //   123: aload 10
      //   125: arraylength
      //   126: istore 15
      //   128: iconst_0
      //   129: istore 16
      //   131: iload 16
      //   133: iload 15
      //   135: if_icmpge +32 -> 167
      //   138: aload 10
      //   140: iload 16
      //   142: aaload
      //   143: invokevirtual 158	rx/internal/operators/OperatorPublish$InnerProducer:get	()J
      //   146: lstore 37
      //   148: lload 37
      //   150: lconst_0
      //   151: lcmp
      //   152: iflt +383 -> 535
      //   155: lload 12
      //   157: lload 37
      //   159: invokestatic 164	java/lang/Math:min	(JJ)J
      //   162: lstore 12
      //   164: goto +365 -> 529
      //   167: iload 11
      //   169: iload 14
      //   171: if_icmpne +92 -> 263
      //   174: aload_0
      //   175: getfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:terminalEvent	Ljava/lang/Object;
      //   178: astore 33
      //   180: aload_0
      //   181: getfield 64	rx/internal/operators/OperatorPublish$PublishSubscriber:queue	Ljava/util/Queue;
      //   184: invokeinterface 167 1 0
      //   189: ifnonnull +44 -> 233
      //   192: iconst_1
      //   193: istore 34
      //   195: aload_0
      //   196: aload 33
      //   198: iload 34
      //   200: invokevirtual 153	rx/internal/operators/OperatorPublish$PublishSubscriber:checkTerminated	(Ljava/lang/Object;Z)Z
      //   203: istore 35
      //   205: iload 35
      //   207: ifeq +32 -> 239
      //   210: iconst_1
      //   211: ifne +317 -> 528
      //   214: aload_0
      //   215: monitorenter
      //   216: aload_0
      //   217: iconst_0
      //   218: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   221: aload_0
      //   222: monitorexit
      //   223: goto +305 -> 528
      //   226: astore 36
      //   228: aload_0
      //   229: monitorexit
      //   230: aload 36
      //   232: athrow
      //   233: iconst_0
      //   234: istore 34
      //   236: goto -41 -> 195
      //   239: aload_0
      //   240: lconst_1
      //   241: invokevirtual 171	rx/internal/operators/OperatorPublish$PublishSubscriber:request	(J)V
      //   244: goto -211 -> 33
      //   247: astore_3
      //   248: iload_2
      //   249: ifne +12 -> 261
      //   252: aload_0
      //   253: monitorenter
      //   254: aload_0
      //   255: iconst_0
      //   256: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   259: aload_0
      //   260: monitorexit
      //   261: aload_3
      //   262: athrow
      //   263: iconst_0
      //   264: istore 17
      //   266: iload 17
      //   268: i2l
      //   269: lload 12
      //   271: lcmp
      //   272: ifge +77 -> 349
      //   275: aload_0
      //   276: getfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:terminalEvent	Ljava/lang/Object;
      //   279: astore 20
      //   281: aload_0
      //   282: getfield 64	rx/internal/operators/OperatorPublish$PublishSubscriber:queue	Ljava/util/Queue;
      //   285: invokeinterface 167 1 0
      //   290: astore 21
      //   292: aload 21
      //   294: ifnonnull +44 -> 338
      //   297: iconst_1
      //   298: istore 6
      //   300: aload_0
      //   301: aload 20
      //   303: iload 6
      //   305: invokevirtual 153	rx/internal/operators/OperatorPublish$PublishSubscriber:checkTerminated	(Ljava/lang/Object;Z)Z
      //   308: istore 22
      //   310: iload 22
      //   312: ifeq +32 -> 344
      //   315: iconst_1
      //   316: ifne +212 -> 528
      //   319: aload_0
      //   320: monitorenter
      //   321: aload_0
      //   322: iconst_0
      //   323: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   326: aload_0
      //   327: monitorexit
      //   328: goto +200 -> 528
      //   331: astore 32
      //   333: aload_0
      //   334: monitorexit
      //   335: aload 32
      //   337: athrow
      //   338: iconst_0
      //   339: istore 6
      //   341: goto -41 -> 300
      //   344: iload 6
      //   346: ifeq +63 -> 409
      //   349: iload 17
      //   351: ifle +199 -> 550
      //   354: iload 17
      //   356: i2l
      //   357: lstore 18
      //   359: aload_0
      //   360: lload 18
      //   362: invokevirtual 171	rx/internal/operators/OperatorPublish$PublishSubscriber:request	(J)V
      //   365: goto +185 -> 550
      //   368: aload_0
      //   369: monitorenter
      //   370: aload_0
      //   371: getfield 144	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   374: ifne +130 -> 504
      //   377: aload_0
      //   378: iconst_0
      //   379: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   382: iconst_1
      //   383: istore_2
      //   384: aload_0
      //   385: monitorexit
      //   386: iload_2
      //   387: ifne +141 -> 528
      //   390: aload_0
      //   391: monitorenter
      //   392: aload_0
      //   393: iconst_0
      //   394: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   397: aload_0
      //   398: monitorexit
      //   399: goto +129 -> 528
      //   402: astore 9
      //   404: aload_0
      //   405: monitorexit
      //   406: aload 9
      //   408: athrow
      //   409: aload_0
      //   410: getfield 72	rx/internal/operators/OperatorPublish$PublishSubscriber:nl	Lrx/internal/operators/NotificationLite;
      //   413: aload 21
      //   415: invokevirtual 174	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   418: astore 23
      //   420: aload 10
      //   422: arraylength
      //   423: istore 24
      //   425: iconst_0
      //   426: istore 25
      //   428: iload 25
      //   430: iload 24
      //   432: if_icmpge +66 -> 498
      //   435: aload 10
      //   437: iload 25
      //   439: aaload
      //   440: astore 26
      //   442: aload 26
      //   444: invokevirtual 158	rx/internal/operators/OperatorPublish$InnerProducer:get	()J
      //   447: lstore 27
      //   449: lload 27
      //   451: lconst_0
      //   452: lcmp
      //   453: ifle +112 -> 565
      //   456: aload 26
      //   458: getfield 123	rx/internal/operators/OperatorPublish$InnerProducer:child	Lrx/Subscriber;
      //   461: aload 23
      //   463: invokevirtual 177	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   466: aload 26
      //   468: lconst_1
      //   469: invokevirtual 181	rx/internal/operators/OperatorPublish$InnerProducer:produced	(J)J
      //   472: pop2
      //   473: goto +92 -> 565
      //   476: astore 29
      //   478: aload 26
      //   480: invokevirtual 182	rx/internal/operators/OperatorPublish$InnerProducer:unsubscribe	()V
      //   483: aload 29
      //   485: aload 26
      //   487: getfield 123	rx/internal/operators/OperatorPublish$InnerProducer:child	Lrx/Subscriber;
      //   490: aload 23
      //   492: invokestatic 188	rx/exceptions/Exceptions:throwOrReport	(Ljava/lang/Throwable;Lrx/Observer;Ljava/lang/Object;)V
      //   495: goto +70 -> 565
      //   498: iinc 17 1
      //   501: goto -235 -> 266
      //   504: aload_0
      //   505: iconst_0
      //   506: putfield 144	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   509: aload_0
      //   510: monitorexit
      //   511: goto -478 -> 33
      //   514: astore 8
      //   516: aload_0
      //   517: monitorexit
      //   518: aload 8
      //   520: athrow
      //   521: astore 4
      //   523: aload_0
      //   524: monitorexit
      //   525: aload 4
      //   527: athrow
      //   528: return
      //   529: iinc 16 1
      //   532: goto -401 -> 131
      //   535: lload 37
      //   537: ldc2_w 189
      //   540: lcmp
      //   541: ifne -12 -> 529
      //   544: iinc 14 1
      //   547: goto -18 -> 529
      //   550: lload 12
      //   552: lconst_0
      //   553: lcmp
      //   554: ifeq -186 -> 368
      //   557: iload 6
      //   559: ifeq -526 -> 33
      //   562: goto -194 -> 368
      //   565: iinc 25 1
      //   568: goto -140 -> 428
      //
      // Exception table:
      //   from	to	target	type
      //   71	85	81	finally
      //   2	31	88	finally
      //   89	91	88	finally
      //   216	230	226	finally
      //   33	60	247	finally
      //   98	205	247	finally
      //   239	244	247	finally
      //   275	310	247	finally
      //   359	370	247	finally
      //   409	449	247	finally
      //   456	466	247	finally
      //   466	495	247	finally
      //   518	521	247	finally
      //   321	335	331	finally
      //   392	406	402	finally
      //   456	466	476	java/lang/Throwable
      //   370	386	514	finally
      //   504	518	514	finally
      //   254	261	521	finally
      //   523	525	521	finally
    }

    void init()
    {
      add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          OperatorPublish.PublishSubscriber.this.producers.getAndSet(OperatorPublish.PublishSubscriber.TERMINATED);
          OperatorPublish.PublishSubscriber.this.current.compareAndSet(OperatorPublish.PublishSubscriber.this, null);
        }
      }));
    }

    public void onCompleted()
    {
      if (this.terminalEvent == null)
      {
        this.terminalEvent = this.nl.completed();
        dispatch();
      }
    }

    public void onError(Throwable paramThrowable)
    {
      if (this.terminalEvent == null)
      {
        this.terminalEvent = this.nl.error(paramThrowable);
        dispatch();
      }
    }

    public void onNext(T paramT)
    {
      if (!this.queue.offer(this.nl.next(paramT)))
        onError(new MissingBackpressureException());
      while (true)
      {
        return;
        dispatch();
      }
    }

    public void onStart()
    {
      request(RxRingBuffer.SIZE);
    }

    void remove(OperatorPublish.InnerProducer<T> paramInnerProducer)
    {
      OperatorPublish.InnerProducer[] arrayOfInnerProducer1 = (OperatorPublish.InnerProducer[])this.producers.get();
      if ((arrayOfInnerProducer1 == EMPTY) || (arrayOfInnerProducer1 == TERMINATED));
      label25: int i;
      int j;
      int k;
      label36: OperatorPublish.InnerProducer[] arrayOfInnerProducer2;
      while (true)
      {
        return;
        i = -1;
        j = arrayOfInnerProducer1.length;
        k = 0;
        if (k < j)
        {
          if (!arrayOfInnerProducer1[k].equals(paramInnerProducer))
            break;
          i = k;
        }
        else
        {
          if (i < 0)
            continue;
          if (j != 1)
            break label94;
          arrayOfInnerProducer2 = EMPTY;
        }
      }
      while (this.producers.compareAndSet(arrayOfInnerProducer1, arrayOfInnerProducer2))
      {
        break label25;
        k++;
        break label36;
        label94: arrayOfInnerProducer2 = new OperatorPublish.InnerProducer[j - 1];
        System.arraycopy(arrayOfInnerProducer1, 0, arrayOfInnerProducer2, 0, i);
        System.arraycopy(arrayOfInnerProducer1, i + 1, arrayOfInnerProducer2, i, -1 + (j - i));
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorPublish
 * JD-Core Version:    0.6.0
 */