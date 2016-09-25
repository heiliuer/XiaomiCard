package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Timestamped;
import rx.subscriptions.Subscriptions;

public final class OperatorReplay<T> extends ConnectableObservable<T>
{
  static final Func0 DEFAULT_UNBOUNDED_FACTORY = new Func0()
  {
    public Object call()
    {
      return new OperatorReplay.UnboundedReplayBuffer(16);
    }
  };
  final Func0<? extends ReplayBuffer<T>> bufferFactory;
  final AtomicReference<ReplaySubscriber<T>> current;
  final Observable<? extends T> source;

  private OperatorReplay(Observable.OnSubscribe<T> paramOnSubscribe, Observable<? extends T> paramObservable, AtomicReference<ReplaySubscriber<T>> paramAtomicReference, Func0<? extends ReplayBuffer<T>> paramFunc0)
  {
    super(paramOnSubscribe);
    this.source = paramObservable;
    this.current = paramAtomicReference;
    this.bufferFactory = paramFunc0;
  }

  public static <T> ConnectableObservable<T> create(Observable<? extends T> paramObservable)
  {
    return create(paramObservable, DEFAULT_UNBOUNDED_FACTORY);
  }

  public static <T> ConnectableObservable<T> create(Observable<? extends T> paramObservable, int paramInt)
  {
    if (paramInt == 2147483647);
    for (ConnectableObservable localConnectableObservable = create(paramObservable); ; localConnectableObservable = create(paramObservable, new Func0(paramInt)
    {
      public OperatorReplay.ReplayBuffer<T> call()
      {
        return new OperatorReplay.SizeBoundReplayBuffer(this.val$bufferSize);
      }
    }))
      return localConnectableObservable;
  }

  public static <T> ConnectableObservable<T> create(Observable<? extends T> paramObservable, long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    return create(paramObservable, paramLong, paramTimeUnit, paramScheduler, 2147483647);
  }

  public static <T> ConnectableObservable<T> create(Observable<? extends T> paramObservable, long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler, int paramInt)
  {
    return create(paramObservable, new Func0(paramInt, paramTimeUnit.toMillis(paramLong), paramScheduler)
    {
      public OperatorReplay.ReplayBuffer<T> call()
      {
        return new OperatorReplay.SizeAndTimeBoundReplayBuffer(this.val$bufferSize, this.val$maxAgeInMillis, this.val$scheduler);
      }
    });
  }

  static <T> ConnectableObservable<T> create(Observable<? extends T> paramObservable, Func0<? extends ReplayBuffer<T>> paramFunc0)
  {
    AtomicReference localAtomicReference = new AtomicReference();
    return new OperatorReplay(new Observable.OnSubscribe(localAtomicReference, paramFunc0)
    {
      public void call(Subscriber<? super T> paramSubscriber)
      {
        Object localObject;
        while (true)
        {
          localObject = (OperatorReplay.ReplaySubscriber)OperatorReplay.this.get();
          if (localObject != null)
            break;
          OperatorReplay.ReplaySubscriber localReplaySubscriber = new OperatorReplay.ReplaySubscriber(OperatorReplay.this, (OperatorReplay.ReplayBuffer)this.val$bufferFactory.call());
          localReplaySubscriber.init();
          if (!OperatorReplay.this.compareAndSet(localObject, localReplaySubscriber))
            continue;
          localObject = localReplaySubscriber;
        }
        OperatorReplay.InnerProducer localInnerProducer = new OperatorReplay.InnerProducer((OperatorReplay.ReplaySubscriber)localObject, paramSubscriber);
        ((OperatorReplay.ReplaySubscriber)localObject).add(localInnerProducer);
        paramSubscriber.add(localInnerProducer);
        paramSubscriber.setProducer(localInnerProducer);
      }
    }
    , paramObservable, localAtomicReference, paramFunc0);
  }

  public static <T, U, R> Observable<R> multicastSelector(Func0<? extends ConnectableObservable<U>> paramFunc0, Func1<? super Observable<U>, ? extends Observable<R>> paramFunc1)
  {
    return Observable.create(new Observable.OnSubscribe(paramFunc0, paramFunc1)
    {
      public void call(Subscriber<? super R> paramSubscriber)
      {
        try
        {
          ConnectableObservable localConnectableObservable = (ConnectableObservable)OperatorReplay.this.call();
          Observable localObservable = (Observable)this.val$selector.call(localConnectableObservable);
          localObservable.subscribe(paramSubscriber);
          localConnectableObservable.connect(new Action1(paramSubscriber)
          {
            public void call(Subscription paramSubscription)
            {
              this.val$child.add(paramSubscription);
            }
          });
          return;
        }
        catch (Throwable localThrowable)
        {
          while (true)
            Exceptions.throwOrReport(localThrowable, paramSubscriber);
        }
      }
    });
  }

  public static <T> ConnectableObservable<T> observeOn(ConnectableObservable<T> paramConnectableObservable, Scheduler paramScheduler)
  {
    return new ConnectableObservable(new Observable.OnSubscribe(paramConnectableObservable.observeOn(paramScheduler))
    {
      public void call(Subscriber<? super T> paramSubscriber)
      {
        OperatorReplay.this.unsafeSubscribe(new Subscriber(paramSubscriber, paramSubscriber)
        {
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
            this.val$child.onNext(paramT);
          }
        });
      }
    }
    , paramConnectableObservable)
    {
      public void connect(Action1<? super Subscription> paramAction1)
      {
        this.val$co.connect(paramAction1);
      }
    };
  }

  public void connect(Action1<? super Subscription> paramAction1)
  {
    Object localObject;
    while (true)
    {
      localObject = (ReplaySubscriber)this.current.get();
      if ((localObject != null) && (!((ReplaySubscriber)localObject).isUnsubscribed()))
        break;
      ReplaySubscriber localReplaySubscriber = new ReplaySubscriber(this.current, (ReplayBuffer)this.bufferFactory.call());
      localReplaySubscriber.init();
      if (!this.current.compareAndSet(localObject, localReplaySubscriber))
        continue;
      localObject = localReplaySubscriber;
    }
    if ((!((ReplaySubscriber)localObject).shouldConnect.get()) && (((ReplaySubscriber)localObject).shouldConnect.compareAndSet(false, true)));
    for (int i = 1; ; i = 0)
    {
      paramAction1.call(localObject);
      if (i != 0)
        this.source.unsafeSubscribe((Subscriber)localObject);
      return;
    }
  }

  static final class SizeAndTimeBoundReplayBuffer<T> extends OperatorReplay.BoundedReplayBuffer<T>
  {
    private static final long serialVersionUID = 3457957419649567404L;
    final int limit;
    final long maxAgeInMillis;
    final Scheduler scheduler;

    public SizeAndTimeBoundReplayBuffer(int paramInt, long paramLong, Scheduler paramScheduler)
    {
      this.scheduler = paramScheduler;
      this.limit = paramInt;
      this.maxAgeInMillis = paramLong;
    }

    Object enterTransform(Object paramObject)
    {
      return new Timestamped(this.scheduler.now(), paramObject);
    }

    Object leaveTransform(Object paramObject)
    {
      return ((Timestamped)paramObject).getValue();
    }

    void truncate()
    {
      long l = this.scheduler.now() - this.maxAgeInMillis;
      Object localObject = (OperatorReplay.Node)get();
      OperatorReplay.Node localNode = (OperatorReplay.Node)((OperatorReplay.Node)localObject).get();
      int i = 0;
      while (localNode != null)
      {
        if (this.size > this.limit)
        {
          i++;
          this.size = (-1 + this.size);
          localObject = localNode;
          localNode = (OperatorReplay.Node)localNode.get();
          continue;
        }
        if (((Timestamped)localNode.value).getTimestampMillis() > l)
          break;
        i++;
        this.size = (-1 + this.size);
        localObject = localNode;
        localNode = (OperatorReplay.Node)localNode.get();
      }
      if (i != 0)
        setFirst((OperatorReplay.Node)localObject);
    }

    void truncateFinal()
    {
      long l = this.scheduler.now() - this.maxAgeInMillis;
      Object localObject = (OperatorReplay.Node)get();
      OperatorReplay.Node localNode = (OperatorReplay.Node)((OperatorReplay.Node)localObject).get();
      int i = 0;
      while ((localNode != null) && (this.size > 1) && (((Timestamped)localNode.value).getTimestampMillis() <= l))
      {
        i++;
        this.size = (-1 + this.size);
        localObject = localNode;
        localNode = (OperatorReplay.Node)localNode.get();
      }
      if (i != 0)
        setFirst((OperatorReplay.Node)localObject);
    }
  }

  static final class SizeBoundReplayBuffer<T> extends OperatorReplay.BoundedReplayBuffer<T>
  {
    private static final long serialVersionUID = -5898283885385201806L;
    final int limit;

    public SizeBoundReplayBuffer(int paramInt)
    {
      this.limit = paramInt;
    }

    void truncate()
    {
      if (this.size > this.limit)
        removeFirst();
    }
  }

  static class BoundedReplayBuffer<T> extends AtomicReference<OperatorReplay.Node>
    implements OperatorReplay.ReplayBuffer<T>
  {
    private static final long serialVersionUID = 2346567790059478686L;
    final NotificationLite<T> nl = NotificationLite.instance();
    int size;
    OperatorReplay.Node tail;

    public BoundedReplayBuffer()
    {
      OperatorReplay.Node localNode = new OperatorReplay.Node(null);
      this.tail = localNode;
      set(localNode);
    }

    final void addLast(OperatorReplay.Node paramNode)
    {
      this.tail.set(paramNode);
      this.tail = paramNode;
      this.size = (1 + this.size);
    }

    final void collect(Collection<? super T> paramCollection)
    {
      OperatorReplay.Node localNode;
      for (Object localObject1 = (OperatorReplay.Node)get(); ; localObject1 = localNode)
      {
        localNode = (OperatorReplay.Node)((OperatorReplay.Node)localObject1).get();
        Object localObject2;
        if (localNode != null)
        {
          localObject2 = leaveTransform(localNode.value);
          if ((!this.nl.isCompleted(localObject2)) && (!this.nl.isError(localObject2)));
        }
        else
        {
          return;
        }
        paramCollection.add(this.nl.getValue(localObject2));
      }
    }

    public final void complete()
    {
      addLast(new OperatorReplay.Node(enterTransform(this.nl.completed())));
      truncateFinal();
    }

    Object enterTransform(Object paramObject)
    {
      return paramObject;
    }

    public final void error(Throwable paramThrowable)
    {
      addLast(new OperatorReplay.Node(enterTransform(this.nl.error(paramThrowable))));
      truncateFinal();
    }

    boolean hasCompleted()
    {
      if ((this.tail.value != null) && (this.nl.isCompleted(leaveTransform(this.tail.value))));
      for (int i = 1; ; i = 0)
        return i;
    }

    boolean hasError()
    {
      if ((this.tail.value != null) && (this.nl.isError(leaveTransform(this.tail.value))));
      for (int i = 1; ; i = 0)
        return i;
    }

    Object leaveTransform(Object paramObject)
    {
      return paramObject;
    }

    public final void next(T paramT)
    {
      addLast(new OperatorReplay.Node(enterTransform(this.nl.next(paramT))));
      truncate();
    }

    final void removeFirst()
    {
      OperatorReplay.Node localNode = (OperatorReplay.Node)((OperatorReplay.Node)get()).get();
      if (localNode == null)
        throw new IllegalStateException("Empty list!");
      this.size = (-1 + this.size);
      setFirst(localNode);
    }

    final void removeSome(int paramInt)
    {
      OperatorReplay.Node localNode = (OperatorReplay.Node)get();
      while (paramInt > 0)
      {
        localNode = (OperatorReplay.Node)localNode.get();
        paramInt--;
        this.size = (-1 + this.size);
      }
      setFirst(localNode);
    }

    // ERROR //
    public final void replay(OperatorReplay.InnerProducer<T> paramInnerProducer)
    {
      // Byte code:
      //   0: aload_1
      //   1: monitorenter
      //   2: aload_1
      //   3: getfield 127	rx/internal/operators/OperatorReplay$InnerProducer:emitting	Z
      //   6: ifeq +13 -> 19
      //   9: aload_1
      //   10: iconst_1
      //   11: putfield 130	rx/internal/operators/OperatorReplay$InnerProducer:missed	Z
      //   14: aload_1
      //   15: monitorexit
      //   16: goto +263 -> 279
      //   19: aload_1
      //   20: iconst_1
      //   21: putfield 127	rx/internal/operators/OperatorReplay$InnerProducer:emitting	Z
      //   24: aload_1
      //   25: monitorexit
      //   26: aload_1
      //   27: invokevirtual 133	rx/internal/operators/OperatorReplay$InnerProducer:isUnsubscribed	()Z
      //   30: ifne +249 -> 279
      //   33: aload_1
      //   34: invokevirtual 136	rx/internal/operators/OperatorReplay$InnerProducer:get	()J
      //   37: lstore_3
      //   38: lconst_0
      //   39: lstore 5
      //   41: aload_1
      //   42: invokevirtual 139	rx/internal/operators/OperatorReplay$InnerProducer:index	()Ljava/lang/Object;
      //   45: checkcast 35	rx/internal/operators/OperatorReplay$Node
      //   48: astore 7
      //   50: aload 7
      //   52: ifnonnull +18 -> 70
      //   55: aload_0
      //   56: invokevirtual 54	rx/internal/operators/OperatorReplay$BoundedReplayBuffer:get	()Ljava/lang/Object;
      //   59: checkcast 35	rx/internal/operators/OperatorReplay$Node
      //   62: astore 7
      //   64: aload_1
      //   65: aload 7
      //   67: putfield 141	rx/internal/operators/OperatorReplay$InnerProducer:index	Ljava/lang/Object;
      //   70: lload_3
      //   71: lconst_0
      //   72: lcmp
      //   73: ifeq +142 -> 215
      //   76: aload 7
      //   78: invokevirtual 55	rx/internal/operators/OperatorReplay$Node:get	()Ljava/lang/Object;
      //   81: checkcast 35	rx/internal/operators/OperatorReplay$Node
      //   84: astore 11
      //   86: aload 11
      //   88: ifnull +127 -> 215
      //   91: aload_0
      //   92: aload 11
      //   94: getfield 59	rx/internal/operators/OperatorReplay$Node:value	Ljava/lang/Object;
      //   97: invokevirtual 63	rx/internal/operators/OperatorReplay$BoundedReplayBuffer:leaveTransform	(Ljava/lang/Object;)Ljava/lang/Object;
      //   100: astore 12
      //   102: aload_0
      //   103: getfield 33	rx/internal/operators/OperatorReplay$BoundedReplayBuffer:nl	Lrx/internal/operators/NotificationLite;
      //   106: aload_1
      //   107: getfield 145	rx/internal/operators/OperatorReplay$InnerProducer:child	Lrx/Subscriber;
      //   110: aload 12
      //   112: invokevirtual 149	rx/internal/operators/NotificationLite:accept	(Lrx/Observer;Ljava/lang/Object;)Z
      //   115: ifeq +80 -> 195
      //   118: aload_1
      //   119: aconst_null
      //   120: putfield 141	rx/internal/operators/OperatorReplay$InnerProducer:index	Ljava/lang/Object;
      //   123: goto +156 -> 279
      //   126: astore 13
      //   128: aload_1
      //   129: aconst_null
      //   130: putfield 141	rx/internal/operators/OperatorReplay$InnerProducer:index	Ljava/lang/Object;
      //   133: aload 13
      //   135: invokestatic 154	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   138: aload_1
      //   139: invokevirtual 157	rx/internal/operators/OperatorReplay$InnerProducer:unsubscribe	()V
      //   142: aload_0
      //   143: getfield 33	rx/internal/operators/OperatorReplay$BoundedReplayBuffer:nl	Lrx/internal/operators/NotificationLite;
      //   146: aload 12
      //   148: invokevirtual 70	rx/internal/operators/NotificationLite:isError	(Ljava/lang/Object;)Z
      //   151: ifne +128 -> 279
      //   154: aload_0
      //   155: getfield 33	rx/internal/operators/OperatorReplay$BoundedReplayBuffer:nl	Lrx/internal/operators/NotificationLite;
      //   158: aload 12
      //   160: invokevirtual 67	rx/internal/operators/NotificationLite:isCompleted	(Ljava/lang/Object;)Z
      //   163: ifne +116 -> 279
      //   166: aload_1
      //   167: getfield 145	rx/internal/operators/OperatorReplay$InnerProducer:child	Lrx/Subscriber;
      //   170: aload 13
      //   172: aload_0
      //   173: getfield 33	rx/internal/operators/OperatorReplay$BoundedReplayBuffer:nl	Lrx/internal/operators/NotificationLite;
      //   176: aload 12
      //   178: invokevirtual 73	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   181: invokestatic 163	rx/exceptions/OnErrorThrowable:addValueAsLastCause	(Ljava/lang/Throwable;Ljava/lang/Object;)Ljava/lang/Throwable;
      //   184: invokevirtual 168	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   187: goto +92 -> 279
      //   190: astore_2
      //   191: aload_1
      //   192: monitorexit
      //   193: aload_2
      //   194: athrow
      //   195: lload 5
      //   197: lconst_1
      //   198: ladd
      //   199: lstore 5
      //   201: aload 11
      //   203: astore 7
      //   205: aload_1
      //   206: invokevirtual 133	rx/internal/operators/OperatorReplay$InnerProducer:isUnsubscribed	()Z
      //   209: ifeq -139 -> 70
      //   212: goto +67 -> 279
      //   215: lload 5
      //   217: lconst_0
      //   218: lcmp
      //   219: ifeq +24 -> 243
      //   222: aload_1
      //   223: aload 7
      //   225: putfield 141	rx/internal/operators/OperatorReplay$InnerProducer:index	Ljava/lang/Object;
      //   228: lload_3
      //   229: ldc2_w 169
      //   232: lcmp
      //   233: ifeq +10 -> 243
      //   236: aload_1
      //   237: lload 5
      //   239: invokevirtual 174	rx/internal/operators/OperatorReplay$InnerProducer:produced	(J)J
      //   242: pop2
      //   243: aload_1
      //   244: monitorenter
      //   245: aload_1
      //   246: getfield 130	rx/internal/operators/OperatorReplay$InnerProducer:missed	Z
      //   249: ifne +20 -> 269
      //   252: aload_1
      //   253: iconst_0
      //   254: putfield 127	rx/internal/operators/OperatorReplay$InnerProducer:emitting	Z
      //   257: aload_1
      //   258: monitorexit
      //   259: goto +20 -> 279
      //   262: astore 8
      //   264: aload_1
      //   265: monitorexit
      //   266: aload 8
      //   268: athrow
      //   269: aload_1
      //   270: iconst_0
      //   271: putfield 130	rx/internal/operators/OperatorReplay$InnerProducer:missed	Z
      //   274: aload_1
      //   275: monitorexit
      //   276: goto -250 -> 26
      //   279: return
      //
      // Exception table:
      //   from	to	target	type
      //   102	123	126	java/lang/Throwable
      //   2	26	190	finally
      //   191	193	190	finally
      //   245	266	262	finally
      //   269	276	262	finally
    }

    final void setFirst(OperatorReplay.Node paramNode)
    {
      set(paramNode);
    }

    void truncate()
    {
    }

    void truncateFinal()
    {
    }
  }

  static final class Node extends AtomicReference<Node>
  {
    private static final long serialVersionUID = 245354315435971818L;
    final Object value;

    public Node(Object paramObject)
    {
      this.value = paramObject;
    }
  }

  static final class UnboundedReplayBuffer<T> extends ArrayList<Object>
    implements OperatorReplay.ReplayBuffer<T>
  {
    private static final long serialVersionUID = 7063189396499112664L;
    final NotificationLite<T> nl = NotificationLite.instance();
    volatile int size;

    public UnboundedReplayBuffer(int paramInt)
    {
      super();
    }

    public void complete()
    {
      add(this.nl.completed());
      this.size = (1 + this.size);
    }

    public void error(Throwable paramThrowable)
    {
      add(this.nl.error(paramThrowable));
      this.size = (1 + this.size);
    }

    public void next(T paramT)
    {
      add(this.nl.next(paramT));
      this.size = (1 + this.size);
    }

    // ERROR //
    public void replay(OperatorReplay.InnerProducer<T> paramInnerProducer)
    {
      // Byte code:
      //   0: aload_1
      //   1: monitorenter
      //   2: aload_1
      //   3: getfield 63	rx/internal/operators/OperatorReplay$InnerProducer:emitting	Z
      //   6: ifeq +13 -> 19
      //   9: aload_1
      //   10: iconst_1
      //   11: putfield 66	rx/internal/operators/OperatorReplay$InnerProducer:missed	Z
      //   14: aload_1
      //   15: monitorexit
      //   16: goto +260 -> 276
      //   19: aload_1
      //   20: iconst_1
      //   21: putfield 63	rx/internal/operators/OperatorReplay$InnerProducer:emitting	Z
      //   24: aload_1
      //   25: monitorexit
      //   26: aload_1
      //   27: invokevirtual 70	rx/internal/operators/OperatorReplay$InnerProducer:isUnsubscribed	()Z
      //   30: ifne +246 -> 276
      //   33: aload_0
      //   34: getfield 43	rx/internal/operators/OperatorReplay$UnboundedReplayBuffer:size	I
      //   37: istore_3
      //   38: aload_1
      //   39: invokevirtual 73	rx/internal/operators/OperatorReplay$InnerProducer:index	()Ljava/lang/Object;
      //   42: checkcast 75	java/lang/Integer
      //   45: astore 4
      //   47: aload 4
      //   49: ifnull +94 -> 143
      //   52: aload 4
      //   54: invokevirtual 79	java/lang/Integer:intValue	()I
      //   57: istore 5
      //   59: aload_1
      //   60: invokevirtual 83	rx/internal/operators/OperatorReplay$InnerProducer:get	()J
      //   63: lstore 6
      //   65: lload 6
      //   67: lstore 8
      //   69: lconst_0
      //   70: lstore 10
      //   72: lload 6
      //   74: lconst_0
      //   75: lcmp
      //   76: ifeq +132 -> 208
      //   79: iload 5
      //   81: iload_3
      //   82: if_icmpge +126 -> 208
      //   85: aload_0
      //   86: iload 5
      //   88: invokevirtual 86	rx/internal/operators/OperatorReplay$UnboundedReplayBuffer:get	(I)Ljava/lang/Object;
      //   91: astore 15
      //   93: aload_0
      //   94: getfield 31	rx/internal/operators/OperatorReplay$UnboundedReplayBuffer:nl	Lrx/internal/operators/NotificationLite;
      //   97: aload_1
      //   98: getfield 90	rx/internal/operators/OperatorReplay$InnerProducer:child	Lrx/Subscriber;
      //   101: aload 15
      //   103: invokevirtual 94	rx/internal/operators/NotificationLite:accept	(Lrx/Observer;Ljava/lang/Object;)Z
      //   106: istore 17
      //   108: iload 17
      //   110: ifne +166 -> 276
      //   113: aload_1
      //   114: invokevirtual 70	rx/internal/operators/OperatorReplay$InnerProducer:isUnsubscribed	()Z
      //   117: ifne +159 -> 276
      //   120: iinc 5 1
      //   123: lload 6
      //   125: lconst_1
      //   126: lsub
      //   127: lstore 6
      //   129: lload 10
      //   131: lconst_1
      //   132: ladd
      //   133: lstore 10
      //   135: goto -63 -> 72
      //   138: astore_2
      //   139: aload_1
      //   140: monitorexit
      //   141: aload_2
      //   142: athrow
      //   143: iconst_0
      //   144: istore 5
      //   146: goto -87 -> 59
      //   149: astore 16
      //   151: aload 16
      //   153: invokestatic 99	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   156: aload_1
      //   157: invokevirtual 102	rx/internal/operators/OperatorReplay$InnerProducer:unsubscribe	()V
      //   160: aload_0
      //   161: getfield 31	rx/internal/operators/OperatorReplay$UnboundedReplayBuffer:nl	Lrx/internal/operators/NotificationLite;
      //   164: aload 15
      //   166: invokevirtual 105	rx/internal/operators/NotificationLite:isError	(Ljava/lang/Object;)Z
      //   169: ifne +107 -> 276
      //   172: aload_0
      //   173: getfield 31	rx/internal/operators/OperatorReplay$UnboundedReplayBuffer:nl	Lrx/internal/operators/NotificationLite;
      //   176: aload 15
      //   178: invokevirtual 108	rx/internal/operators/NotificationLite:isCompleted	(Ljava/lang/Object;)Z
      //   181: ifne +95 -> 276
      //   184: aload_1
      //   185: getfield 90	rx/internal/operators/OperatorReplay$InnerProducer:child	Lrx/Subscriber;
      //   188: aload 16
      //   190: aload_0
      //   191: getfield 31	rx/internal/operators/OperatorReplay$UnboundedReplayBuffer:nl	Lrx/internal/operators/NotificationLite;
      //   194: aload 15
      //   196: invokevirtual 111	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   199: invokestatic 117	rx/exceptions/OnErrorThrowable:addValueAsLastCause	(Ljava/lang/Throwable;Ljava/lang/Object;)Ljava/lang/Throwable;
      //   202: invokevirtual 122	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   205: goto +71 -> 276
      //   208: lload 10
      //   210: lconst_0
      //   211: lcmp
      //   212: ifeq +28 -> 240
      //   215: aload_1
      //   216: iload 5
      //   218: invokestatic 126	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   221: putfield 129	rx/internal/operators/OperatorReplay$InnerProducer:index	Ljava/lang/Object;
      //   224: lload 8
      //   226: ldc2_w 130
      //   229: lcmp
      //   230: ifeq +10 -> 240
      //   233: aload_1
      //   234: lload 10
      //   236: invokevirtual 135	rx/internal/operators/OperatorReplay$InnerProducer:produced	(J)J
      //   239: pop2
      //   240: aload_1
      //   241: monitorenter
      //   242: aload_1
      //   243: getfield 66	rx/internal/operators/OperatorReplay$InnerProducer:missed	Z
      //   246: ifne +20 -> 266
      //   249: aload_1
      //   250: iconst_0
      //   251: putfield 63	rx/internal/operators/OperatorReplay$InnerProducer:emitting	Z
      //   254: aload_1
      //   255: monitorexit
      //   256: goto +20 -> 276
      //   259: astore 12
      //   261: aload_1
      //   262: monitorexit
      //   263: aload 12
      //   265: athrow
      //   266: aload_1
      //   267: iconst_0
      //   268: putfield 66	rx/internal/operators/OperatorReplay$InnerProducer:missed	Z
      //   271: aload_1
      //   272: monitorexit
      //   273: goto -247 -> 26
      //   276: return
      //
      // Exception table:
      //   from	to	target	type
      //   2	26	138	finally
      //   139	141	138	finally
      //   93	108	149	java/lang/Throwable
      //   242	263	259	finally
      //   266	273	259	finally
    }
  }

  static abstract interface ReplayBuffer<T>
  {
    public abstract void complete();

    public abstract void error(Throwable paramThrowable);

    public abstract void next(T paramT);

    public abstract void replay(OperatorReplay.InnerProducer<T> paramInnerProducer);
  }

  static final class InnerProducer<T> extends AtomicLong
    implements Producer, Subscription
  {
    static final long UNSUBSCRIBED = -9223372036854775808L;
    private static final long serialVersionUID = -4453897557930727610L;
    final Subscriber<? super T> child;
    boolean emitting;
    Object index;
    boolean missed;
    final OperatorReplay.ReplaySubscriber<T> parent;
    final AtomicLong totalRequested;

    public InnerProducer(OperatorReplay.ReplaySubscriber<T> paramReplaySubscriber, Subscriber<? super T> paramSubscriber)
    {
      this.parent = paramReplaySubscriber;
      this.child = paramSubscriber;
      this.totalRequested = new AtomicLong();
    }

    void addTotalRequested(long paramLong)
    {
      long l1;
      long l2;
      do
      {
        l1 = this.totalRequested.get();
        l2 = l1 + paramLong;
        if (l2 >= 0L)
          continue;
        l2 = 9223372036854775807L;
      }
      while (!this.totalRequested.compareAndSet(l1, l2));
    }

    <U> U index()
    {
      return this.index;
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
      if (paramLong < 0L);
      while (true)
      {
        return;
        long l1;
        long l2;
        do
        {
          l1 = get();
          if ((l1 == -9223372036854775808L) || ((l1 >= 0L) && (paramLong == 0L)))
            break;
          l2 = l1 + paramLong;
          if (l2 >= 0L)
            continue;
          l2 = 9223372036854775807L;
        }
        while (!compareAndSet(l1, l2));
        addTotalRequested(paramLong);
        this.parent.manageRequests();
        this.parent.buffer.replay(this);
      }
    }

    public void unsubscribe()
    {
      if ((get() != -9223372036854775808L) && (getAndSet(-9223372036854775808L) != -9223372036854775808L))
      {
        this.parent.remove(this);
        this.parent.manageRequests();
      }
    }
  }

  static final class ReplaySubscriber<T> extends Subscriber<T>
    implements Subscription
  {
    static final OperatorReplay.InnerProducer[] EMPTY = new OperatorReplay.InnerProducer[0];
    static final OperatorReplay.InnerProducer[] TERMINATED = new OperatorReplay.InnerProducer[0];
    final OperatorReplay.ReplayBuffer<T> buffer;
    boolean done;
    boolean emitting;
    long maxChildRequested;
    long maxUpstreamRequested;
    boolean missed;
    final NotificationLite<T> nl;
    volatile Producer producer;
    final AtomicReference<OperatorReplay.InnerProducer[]> producers;
    final AtomicBoolean shouldConnect;

    public ReplaySubscriber(AtomicReference<ReplaySubscriber<T>> paramAtomicReference, OperatorReplay.ReplayBuffer<T> paramReplayBuffer)
    {
      this.buffer = paramReplayBuffer;
      this.nl = NotificationLite.instance();
      this.producers = new AtomicReference(EMPTY);
      this.shouldConnect = new AtomicBoolean();
      request(0L);
    }

    boolean add(OperatorReplay.InnerProducer<T> paramInnerProducer)
    {
      int i = 0;
      if (paramInnerProducer == null)
        throw new NullPointerException();
      OperatorReplay.InnerProducer[] arrayOfInnerProducer1 = (OperatorReplay.InnerProducer[])this.producers.get();
      if (arrayOfInnerProducer1 == TERMINATED);
      while (true)
      {
        return i;
        int j = arrayOfInnerProducer1.length;
        OperatorReplay.InnerProducer[] arrayOfInnerProducer2 = new OperatorReplay.InnerProducer[j + 1];
        System.arraycopy(arrayOfInnerProducer1, 0, arrayOfInnerProducer2, 0, j);
        arrayOfInnerProducer2[j] = paramInnerProducer;
        if (!this.producers.compareAndSet(arrayOfInnerProducer1, arrayOfInnerProducer2))
          break;
        i = 1;
      }
    }

    void init()
    {
      add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          OperatorReplay.ReplaySubscriber.this.producers.getAndSet(OperatorReplay.ReplaySubscriber.TERMINATED);
        }
      }));
    }

    // ERROR //
    void manageRequests()
    {
      // Byte code:
      //   0: aload_0
      //   1: invokevirtual 111	rx/internal/operators/OperatorReplay$ReplaySubscriber:isUnsubscribed	()Z
      //   4: ifeq +4 -> 8
      //   7: return
      //   8: aload_0
      //   9: monitorenter
      //   10: aload_0
      //   11: getfield 113	rx/internal/operators/OperatorReplay$ReplaySubscriber:emitting	Z
      //   14: ifeq +18 -> 32
      //   17: aload_0
      //   18: iconst_1
      //   19: putfield 115	rx/internal/operators/OperatorReplay$ReplaySubscriber:missed	Z
      //   22: aload_0
      //   23: monitorexit
      //   24: goto -17 -> 7
      //   27: astore_1
      //   28: aload_0
      //   29: monitorexit
      //   30: aload_1
      //   31: athrow
      //   32: aload_0
      //   33: iconst_1
      //   34: putfield 113	rx/internal/operators/OperatorReplay$ReplaySubscriber:emitting	Z
      //   37: aload_0
      //   38: monitorexit
      //   39: aload_0
      //   40: invokevirtual 111	rx/internal/operators/OperatorReplay$ReplaySubscriber:isUnsubscribed	()Z
      //   43: ifne -36 -> 7
      //   46: aload_0
      //   47: getfield 64	rx/internal/operators/OperatorReplay$ReplaySubscriber:producers	Ljava/util/concurrent/atomic/AtomicReference;
      //   50: invokevirtual 82	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
      //   53: checkcast 83	[Lrx/internal/operators/OperatorReplay$InnerProducer;
      //   56: astore_2
      //   57: aload_0
      //   58: getfield 117	rx/internal/operators/OperatorReplay$ReplaySubscriber:maxChildRequested	J
      //   61: lstore_3
      //   62: lload_3
      //   63: lstore 5
      //   65: aload_2
      //   66: arraylength
      //   67: istore 7
      //   69: iconst_0
      //   70: istore 8
      //   72: iload 8
      //   74: iload 7
      //   76: if_icmpge +26 -> 102
      //   79: lload 5
      //   81: aload_2
      //   82: iload 8
      //   84: aaload
      //   85: getfield 121	rx/internal/operators/OperatorReplay$InnerProducer:totalRequested	Ljava/util/concurrent/atomic/AtomicLong;
      //   88: invokevirtual 126	java/util/concurrent/atomic/AtomicLong:get	()J
      //   91: invokestatic 132	java/lang/Math:max	(JJ)J
      //   94: lstore 5
      //   96: iinc 8 1
      //   99: goto -27 -> 72
      //   102: aload_0
      //   103: getfield 134	rx/internal/operators/OperatorReplay$ReplaySubscriber:maxUpstreamRequested	J
      //   106: lstore 9
      //   108: aload_0
      //   109: getfield 136	rx/internal/operators/OperatorReplay$ReplaySubscriber:producer	Lrx/Producer;
      //   112: astore 11
      //   114: lload 5
      //   116: lload_3
      //   117: lsub
      //   118: lstore 12
      //   120: lload 12
      //   122: lconst_0
      //   123: lcmp
      //   124: ifeq +104 -> 228
      //   127: aload_0
      //   128: lload 5
      //   130: putfield 117	rx/internal/operators/OperatorReplay$ReplaySubscriber:maxChildRequested	J
      //   133: aload 11
      //   135: ifnull +65 -> 200
      //   138: lload 9
      //   140: lconst_0
      //   141: lcmp
      //   142: ifeq +46 -> 188
      //   145: aload_0
      //   146: lconst_0
      //   147: putfield 134	rx/internal/operators/OperatorReplay$ReplaySubscriber:maxUpstreamRequested	J
      //   150: aload 11
      //   152: lload 9
      //   154: lload 12
      //   156: ladd
      //   157: invokeinterface 139 3 0
      //   162: aload_0
      //   163: monitorenter
      //   164: aload_0
      //   165: getfield 115	rx/internal/operators/OperatorReplay$ReplaySubscriber:missed	Z
      //   168: ifne +89 -> 257
      //   171: aload_0
      //   172: iconst_0
      //   173: putfield 113	rx/internal/operators/OperatorReplay$ReplaySubscriber:emitting	Z
      //   176: aload_0
      //   177: monitorexit
      //   178: goto -171 -> 7
      //   181: astore 14
      //   183: aload_0
      //   184: monitorexit
      //   185: aload 14
      //   187: athrow
      //   188: aload 11
      //   190: lload 12
      //   192: invokeinterface 139 3 0
      //   197: goto -35 -> 162
      //   200: lload 9
      //   202: lload 12
      //   204: ladd
      //   205: lstore 15
      //   207: lload 15
      //   209: lconst_0
      //   210: lcmp
      //   211: ifge +8 -> 219
      //   214: ldc2_w 140
      //   217: lstore 15
      //   219: aload_0
      //   220: lload 15
      //   222: putfield 134	rx/internal/operators/OperatorReplay$ReplaySubscriber:maxUpstreamRequested	J
      //   225: goto -63 -> 162
      //   228: lload 9
      //   230: lconst_0
      //   231: lcmp
      //   232: ifeq -70 -> 162
      //   235: aload 11
      //   237: ifnull -75 -> 162
      //   240: aload_0
      //   241: lconst_0
      //   242: putfield 134	rx/internal/operators/OperatorReplay$ReplaySubscriber:maxUpstreamRequested	J
      //   245: aload 11
      //   247: lload 9
      //   249: invokeinterface 139 3 0
      //   254: goto -92 -> 162
      //   257: aload_0
      //   258: iconst_0
      //   259: putfield 115	rx/internal/operators/OperatorReplay$ReplaySubscriber:missed	Z
      //   262: aload_0
      //   263: monitorexit
      //   264: goto -225 -> 39
      //
      // Exception table:
      //   from	to	target	type
      //   10	30	27	finally
      //   32	39	27	finally
      //   164	185	181	finally
      //   257	264	181	finally
    }

    public void onCompleted()
    {
      if (!this.done)
        this.done = true;
      try
      {
        this.buffer.complete();
        replay();
        return;
      }
      finally
      {
        unsubscribe();
      }
      throw localObject;
    }

    public void onError(Throwable paramThrowable)
    {
      if (!this.done)
        this.done = true;
      try
      {
        this.buffer.error(paramThrowable);
        replay();
        return;
      }
      finally
      {
        unsubscribe();
      }
      throw localObject;
    }

    public void onNext(T paramT)
    {
      if (!this.done)
      {
        this.buffer.next(paramT);
        replay();
      }
    }

    void remove(OperatorReplay.InnerProducer<T> paramInnerProducer)
    {
      OperatorReplay.InnerProducer[] arrayOfInnerProducer1 = (OperatorReplay.InnerProducer[])this.producers.get();
      if ((arrayOfInnerProducer1 == EMPTY) || (arrayOfInnerProducer1 == TERMINATED));
      label25: int i;
      int j;
      int k;
      label36: OperatorReplay.InnerProducer[] arrayOfInnerProducer2;
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
        label94: arrayOfInnerProducer2 = new OperatorReplay.InnerProducer[j - 1];
        System.arraycopy(arrayOfInnerProducer1, 0, arrayOfInnerProducer2, 0, i);
        System.arraycopy(arrayOfInnerProducer1, i + 1, arrayOfInnerProducer2, i, -1 + (j - i));
      }
    }

    void replay()
    {
      for (OperatorReplay.InnerProducer localInnerProducer : (OperatorReplay.InnerProducer[])this.producers.get())
        this.buffer.replay(localInnerProducer);
    }

    public void setProducer(Producer paramProducer)
    {
      if (this.producer != null)
        throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
      this.producer = paramProducer;
      manageRequests();
      replay();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorReplay
 * JD-Core Version:    0.6.0
 */