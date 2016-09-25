package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Observable.Operator;
import rx.Observer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.SerializedObserver;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.Subscriptions;

public final class OperatorWindowWithTime<T>
  implements Observable.Operator<Observable<T>, T>
{
  static final Object NEXT_SUBJECT = new Object();
  static final NotificationLite<Object> nl = NotificationLite.instance();
  final Scheduler scheduler;
  final int size;
  final long timeshift;
  final long timespan;
  final TimeUnit unit;

  public OperatorWindowWithTime(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, int paramInt, Scheduler paramScheduler)
  {
    this.timespan = paramLong1;
    this.timeshift = paramLong2;
    this.unit = paramTimeUnit;
    this.size = paramInt;
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super Observable<T>> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    Object localObject;
    if (this.timespan == this.timeshift)
    {
      localObject = new ExactSubscriber(paramSubscriber, localWorker);
      ((ExactSubscriber)localObject).add(localWorker);
      ((ExactSubscriber)localObject).scheduleExact();
    }
    while (true)
    {
      return localObject;
      InexactSubscriber localInexactSubscriber = new InexactSubscriber(paramSubscriber, localWorker);
      localInexactSubscriber.add(localWorker);
      localInexactSubscriber.startNewChunk();
      localInexactSubscriber.scheduleChunk();
      localObject = localInexactSubscriber;
    }
  }

  final class InexactSubscriber extends Subscriber<T>
  {
    final Subscriber<? super Observable<T>> child;
    final List<OperatorWindowWithTime.CountedSerializedSubject<T>> chunks;
    boolean done;
    final Object guard;
    final Scheduler.Worker worker;

    public InexactSubscriber(Scheduler.Worker arg2)
    {
      super();
      this.child = localSubscriber;
      Object localObject;
      this.worker = localObject;
      this.guard = new Object();
      this.chunks = new LinkedList();
    }

    OperatorWindowWithTime.CountedSerializedSubject<T> createCountedSerializedSubject()
    {
      UnicastSubject localUnicastSubject = UnicastSubject.create();
      return new OperatorWindowWithTime.CountedSerializedSubject(localUnicastSubject, localUnicastSubject);
    }

    public void onCompleted()
    {
      synchronized (this.guard)
      {
        if (this.done)
          return;
        this.done = true;
        ArrayList localArrayList = new ArrayList(this.chunks);
        this.chunks.clear();
        Iterator localIterator = localArrayList.iterator();
        if (localIterator.hasNext())
          ((OperatorWindowWithTime.CountedSerializedSubject)localIterator.next()).consumer.onCompleted();
      }
      this.child.onCompleted();
    }

    public void onError(Throwable paramThrowable)
    {
      synchronized (this.guard)
      {
        if (this.done)
          return;
        this.done = true;
        ArrayList localArrayList = new ArrayList(this.chunks);
        this.chunks.clear();
        Iterator localIterator = localArrayList.iterator();
        if (localIterator.hasNext())
          ((OperatorWindowWithTime.CountedSerializedSubject)localIterator.next()).consumer.onError(paramThrowable);
      }
      this.child.onError(paramThrowable);
    }

    // ERROR //
    public void onNext(T paramT)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 44	rx/internal/operators/OperatorWindowWithTime$InexactSubscriber:guard	Ljava/lang/Object;
      //   4: astore_2
      //   5: aload_2
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 65	rx/internal/operators/OperatorWindowWithTime$InexactSubscriber:done	Z
      //   11: ifeq +8 -> 19
      //   14: aload_2
      //   15: monitorexit
      //   16: goto +164 -> 180
      //   19: new 67	java/util/ArrayList
      //   22: dup
      //   23: aload_0
      //   24: getfield 49	rx/internal/operators/OperatorWindowWithTime$InexactSubscriber:chunks	Ljava/util/List;
      //   27: invokespecial 70	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
      //   30: astore 4
      //   32: aload_0
      //   33: getfield 49	rx/internal/operators/OperatorWindowWithTime$InexactSubscriber:chunks	Ljava/util/List;
      //   36: invokeinterface 79 1 0
      //   41: astore 5
      //   43: aload 5
      //   45: invokeinterface 85 1 0
      //   50: ifeq +58 -> 108
      //   53: aload 5
      //   55: invokeinterface 89 1 0
      //   60: checkcast 59	rx/internal/operators/OperatorWindowWithTime$CountedSerializedSubject
      //   63: astore 8
      //   65: iconst_1
      //   66: aload 8
      //   68: getfield 109	rx/internal/operators/OperatorWindowWithTime$CountedSerializedSubject:count	I
      //   71: iadd
      //   72: istore 9
      //   74: aload 8
      //   76: iload 9
      //   78: putfield 109	rx/internal/operators/OperatorWindowWithTime$CountedSerializedSubject:count	I
      //   81: iload 9
      //   83: aload_0
      //   84: getfield 30	rx/internal/operators/OperatorWindowWithTime$InexactSubscriber:this$0	Lrx/internal/operators/OperatorWindowWithTime;
      //   87: getfield 112	rx/internal/operators/OperatorWindowWithTime:size	I
      //   90: if_icmpne -47 -> 43
      //   93: aload 5
      //   95: invokeinterface 115 1 0
      //   100: goto -57 -> 43
      //   103: astore_3
      //   104: aload_2
      //   105: monitorexit
      //   106: aload_3
      //   107: athrow
      //   108: aload_2
      //   109: monitorexit
      //   110: aload 4
      //   112: invokeinterface 79 1 0
      //   117: astore 6
      //   119: aload 6
      //   121: invokeinterface 85 1 0
      //   126: ifeq +54 -> 180
      //   129: aload 6
      //   131: invokeinterface 89 1 0
      //   136: checkcast 59	rx/internal/operators/OperatorWindowWithTime$CountedSerializedSubject
      //   139: astore 7
      //   141: aload 7
      //   143: getfield 93	rx/internal/operators/OperatorWindowWithTime$CountedSerializedSubject:consumer	Lrx/Observer;
      //   146: aload_1
      //   147: invokeinterface 117 2 0
      //   152: aload 7
      //   154: getfield 109	rx/internal/operators/OperatorWindowWithTime$CountedSerializedSubject:count	I
      //   157: aload_0
      //   158: getfield 30	rx/internal/operators/OperatorWindowWithTime$InexactSubscriber:this$0	Lrx/internal/operators/OperatorWindowWithTime;
      //   161: getfield 112	rx/internal/operators/OperatorWindowWithTime:size	I
      //   164: if_icmpne -45 -> 119
      //   167: aload 7
      //   169: getfield 93	rx/internal/operators/OperatorWindowWithTime$CountedSerializedSubject:consumer	Lrx/Observer;
      //   172: invokeinterface 97 1 0
      //   177: goto -58 -> 119
      //   180: return
      //
      // Exception table:
      //   from	to	target	type
      //   7	106	103	finally
      //   108	110	103	finally
    }

    public void onStart()
    {
      request(9223372036854775807L);
    }

    void scheduleChunk()
    {
      this.worker.schedulePeriodically(new Action0()
      {
        public void call()
        {
          OperatorWindowWithTime.InexactSubscriber.this.startNewChunk();
        }
      }
      , OperatorWindowWithTime.this.timeshift, OperatorWindowWithTime.this.timeshift, OperatorWindowWithTime.this.unit);
    }

    void startNewChunk()
    {
      OperatorWindowWithTime.CountedSerializedSubject localCountedSerializedSubject = createCountedSerializedSubject();
      synchronized (this.guard)
      {
        if (this.done)
          return;
        this.chunks.add(localCountedSerializedSubject);
      }
      try
      {
        this.child.onNext(localCountedSerializedSubject.producer);
        this.worker.schedule(new Action0(localCountedSerializedSubject)
        {
          public void call()
          {
            OperatorWindowWithTime.InexactSubscriber.this.terminateChunk(this.val$chunk);
          }
        }
        , OperatorWindowWithTime.this.timespan, OperatorWindowWithTime.this.unit);
        return;
        localObject2 = finally;
        monitorexit;
        throw localObject2;
      }
      catch (Throwable localThrowable)
      {
        onError(localThrowable);
      }
    }

    void terminateChunk(OperatorWindowWithTime.CountedSerializedSubject<T> paramCountedSerializedSubject)
    {
      int i = 0;
      synchronized (this.guard)
      {
        if (!this.done)
        {
          Iterator localIterator = this.chunks.iterator();
          while (localIterator.hasNext())
          {
            if ((OperatorWindowWithTime.CountedSerializedSubject)localIterator.next() != paramCountedSerializedSubject)
              continue;
            i = 1;
            localIterator.remove();
          }
          if (i != 0)
            paramCountedSerializedSubject.consumer.onCompleted();
        }
      }
    }
  }

  static final class CountedSerializedSubject<T>
  {
    final Observer<T> consumer;
    int count;
    final Observable<T> producer;

    public CountedSerializedSubject(Observer<T> paramObserver, Observable<T> paramObservable)
    {
      this.consumer = new SerializedObserver(paramObserver);
      this.producer = paramObservable;
    }
  }

  final class ExactSubscriber extends Subscriber<T>
  {
    final Subscriber<? super Observable<T>> child;
    boolean emitting;
    final Object guard;
    List<Object> queue;
    volatile OperatorWindowWithTime.State<T> state;
    final Scheduler.Worker worker;

    public ExactSubscriber(Scheduler.Worker arg2)
    {
      Subscriber localSubscriber;
      this.child = new SerializedSubscriber(localSubscriber);
      Object localObject;
      this.worker = localObject;
      this.guard = new Object();
      this.state = OperatorWindowWithTime.State.empty();
      localSubscriber.add(Subscriptions.create(new Action0(OperatorWindowWithTime.this)
      {
        public void call()
        {
          if (OperatorWindowWithTime.ExactSubscriber.this.state.consumer == null)
            OperatorWindowWithTime.ExactSubscriber.this.unsubscribe();
        }
      }));
    }

    void complete()
    {
      Observer localObserver = this.state.consumer;
      this.state = this.state.clear();
      if (localObserver != null)
        localObserver.onCompleted();
      this.child.onCompleted();
      unsubscribe();
    }

    boolean drain(List<Object> paramList)
    {
      int i = 1;
      if (paramList == null)
        break label15;
      while (true)
      {
        return i;
        Iterator localIterator = paramList.iterator();
        label15: if (!localIterator.hasNext())
          continue;
        Object localObject = localIterator.next();
        if (localObject == OperatorWindowWithTime.NEXT_SUBJECT)
        {
          if (replaceSubject())
            break;
          i = 0;
          continue;
        }
        if (OperatorWindowWithTime.nl.isError(localObject))
        {
          error(OperatorWindowWithTime.nl.getError(localObject));
          continue;
        }
        if (OperatorWindowWithTime.nl.isCompleted(localObject))
        {
          complete();
          continue;
        }
        if (emitValue(localObject))
          break;
        i = 0;
      }
    }

    boolean emitValue(T paramT)
    {
      OperatorWindowWithTime.State localState1 = this.state;
      int i;
      if (localState1.consumer == null)
      {
        if (!replaceSubject())
        {
          i = 0;
          return i;
        }
        localState1 = this.state;
      }
      localState1.consumer.onNext(paramT);
      if (localState1.count == -1 + OperatorWindowWithTime.this.size)
        localState1.consumer.onCompleted();
      for (OperatorWindowWithTime.State localState2 = localState1.clear(); ; localState2 = localState1.next())
      {
        this.state = localState2;
        i = 1;
        break;
      }
    }

    void error(Throwable paramThrowable)
    {
      Observer localObserver = this.state.consumer;
      this.state = this.state.clear();
      if (localObserver != null)
        localObserver.onError(paramThrowable);
      this.child.onError(paramThrowable);
      unsubscribe();
    }

    // ERROR //
    void nextWindow()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   4: astore_1
      //   5: aload_1
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   11: ifeq +39 -> 50
      //   14: aload_0
      //   15: getfield 160	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:queue	Ljava/util/List;
      //   18: ifnonnull +14 -> 32
      //   21: aload_0
      //   22: new 162	java/util/ArrayList
      //   25: dup
      //   26: invokespecial 163	java/util/ArrayList:<init>	()V
      //   29: putfield 160	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:queue	Ljava/util/List;
      //   32: aload_0
      //   33: getfield 160	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:queue	Ljava/util/List;
      //   36: getstatic 109	rx/internal/operators/OperatorWindowWithTime:NEXT_SUBJECT	Ljava/lang/Object;
      //   39: invokeinterface 165 2 0
      //   44: pop
      //   45: aload_1
      //   46: monitorexit
      //   47: goto +217 -> 264
      //   50: aload_0
      //   51: iconst_1
      //   52: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   55: aload_1
      //   56: monitorexit
      //   57: iconst_0
      //   58: istore_3
      //   59: aload_0
      //   60: invokevirtual 112	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:replaceSubject	()Z
      //   63: istore 7
      //   65: iload 7
      //   67: ifne +40 -> 107
      //   70: iconst_0
      //   71: ifne +193 -> 264
      //   74: aload_0
      //   75: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   78: astore 16
      //   80: aload 16
      //   82: monitorenter
      //   83: aload_0
      //   84: iconst_0
      //   85: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   88: aload 16
      //   90: monitorexit
      //   91: goto +173 -> 264
      //   94: astore 17
      //   96: aload 16
      //   98: monitorexit
      //   99: aload 17
      //   101: athrow
      //   102: astore_2
      //   103: aload_1
      //   104: monitorexit
      //   105: aload_2
      //   106: athrow
      //   107: aload_0
      //   108: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   111: astore 8
      //   113: aload 8
      //   115: monitorenter
      //   116: aload_0
      //   117: getfield 160	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:queue	Ljava/util/List;
      //   120: astore 10
      //   122: aload 10
      //   124: ifnonnull +45 -> 169
      //   127: aload_0
      //   128: iconst_0
      //   129: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   132: iconst_1
      //   133: istore_3
      //   134: aload 8
      //   136: monitorexit
      //   137: iload_3
      //   138: ifne +126 -> 264
      //   141: aload_0
      //   142: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   145: astore 14
      //   147: aload 14
      //   149: monitorenter
      //   150: aload_0
      //   151: iconst_0
      //   152: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   155: aload 14
      //   157: monitorexit
      //   158: goto +106 -> 264
      //   161: astore 15
      //   163: aload 14
      //   165: monitorexit
      //   166: aload 15
      //   168: athrow
      //   169: aload_0
      //   170: aconst_null
      //   171: putfield 160	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:queue	Ljava/util/List;
      //   174: aload 8
      //   176: monitorexit
      //   177: aload_0
      //   178: aload 10
      //   180: invokevirtual 167	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:drain	(Ljava/util/List;)Z
      //   183: istore 11
      //   185: iload 11
      //   187: ifne -80 -> 107
      //   190: iconst_0
      //   191: ifne +73 -> 264
      //   194: aload_0
      //   195: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   198: astore 12
      //   200: aload 12
      //   202: monitorenter
      //   203: aload_0
      //   204: iconst_0
      //   205: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   208: aload 12
      //   210: monitorexit
      //   211: goto +53 -> 264
      //   214: astore 13
      //   216: aload 12
      //   218: monitorexit
      //   219: aload 13
      //   221: athrow
      //   222: astore 9
      //   224: aload 8
      //   226: monitorexit
      //   227: aload 9
      //   229: athrow
      //   230: astore 4
      //   232: iload_3
      //   233: ifne +20 -> 253
      //   236: aload_0
      //   237: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   240: astore 5
      //   242: aload 5
      //   244: monitorenter
      //   245: aload_0
      //   246: iconst_0
      //   247: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   250: aload 5
      //   252: monitorexit
      //   253: aload 4
      //   255: athrow
      //   256: astore 6
      //   258: aload 5
      //   260: monitorexit
      //   261: aload 6
      //   263: athrow
      //   264: return
      //
      // Exception table:
      //   from	to	target	type
      //   83	99	94	finally
      //   7	57	102	finally
      //   103	105	102	finally
      //   150	166	161	finally
      //   203	219	214	finally
      //   116	137	222	finally
      //   169	177	222	finally
      //   224	227	222	finally
      //   59	65	230	finally
      //   107	116	230	finally
      //   177	185	230	finally
      //   227	230	230	finally
      //   245	253	256	finally
      //   258	261	256	finally
    }

    public void onCompleted()
    {
      List localList;
      synchronized (this.guard)
      {
        if (this.emitting)
        {
          if (this.queue == null)
            this.queue = new ArrayList();
          this.queue.add(OperatorWindowWithTime.nl.completed());
          return;
        }
        localList = this.queue;
        this.queue = null;
        this.emitting = true;
      }
      try
      {
        drain(localList);
        complete();
        return;
        localObject2 = finally;
        monitorexit;
        throw localObject2;
      }
      catch (Throwable localThrowable)
      {
        error(localThrowable);
      }
    }

    public void onError(Throwable paramThrowable)
    {
      synchronized (this.guard)
      {
        if (this.emitting)
        {
          this.queue = Collections.singletonList(OperatorWindowWithTime.nl.error(paramThrowable));
        }
        else
        {
          this.queue = null;
          this.emitting = true;
          error(paramThrowable);
        }
      }
    }

    // ERROR //
    public void onNext(T paramT)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   4: astore_2
      //   5: aload_2
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   11: ifeq +37 -> 48
      //   14: aload_0
      //   15: getfield 160	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:queue	Ljava/util/List;
      //   18: ifnonnull +14 -> 32
      //   21: aload_0
      //   22: new 162	java/util/ArrayList
      //   25: dup
      //   26: invokespecial 163	java/util/ArrayList:<init>	()V
      //   29: putfield 160	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:queue	Ljava/util/List;
      //   32: aload_0
      //   33: getfield 160	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:queue	Ljava/util/List;
      //   36: aload_1
      //   37: invokeinterface 165 2 0
      //   42: pop
      //   43: aload_2
      //   44: monitorexit
      //   45: goto +222 -> 267
      //   48: aload_0
      //   49: iconst_1
      //   50: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   53: aload_2
      //   54: monitorexit
      //   55: iconst_0
      //   56: istore 4
      //   58: aload_0
      //   59: aload_1
      //   60: invokevirtual 138	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitValue	(Ljava/lang/Object;)Z
      //   63: istore 8
      //   65: iload 8
      //   67: ifne +40 -> 107
      //   70: iconst_0
      //   71: ifne +196 -> 267
      //   74: aload_0
      //   75: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   78: astore 17
      //   80: aload 17
      //   82: monitorenter
      //   83: aload_0
      //   84: iconst_0
      //   85: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   88: aload 17
      //   90: monitorexit
      //   91: goto +176 -> 267
      //   94: astore 18
      //   96: aload 17
      //   98: monitorexit
      //   99: aload 18
      //   101: athrow
      //   102: astore_3
      //   103: aload_2
      //   104: monitorexit
      //   105: aload_3
      //   106: athrow
      //   107: aload_0
      //   108: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   111: astore 9
      //   113: aload 9
      //   115: monitorenter
      //   116: aload_0
      //   117: getfield 160	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:queue	Ljava/util/List;
      //   120: astore 11
      //   122: aload 11
      //   124: ifnonnull +47 -> 171
      //   127: aload_0
      //   128: iconst_0
      //   129: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   132: iconst_1
      //   133: istore 4
      //   135: aload 9
      //   137: monitorexit
      //   138: iload 4
      //   140: ifne +127 -> 267
      //   143: aload_0
      //   144: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   147: astore 15
      //   149: aload 15
      //   151: monitorenter
      //   152: aload_0
      //   153: iconst_0
      //   154: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   157: aload 15
      //   159: monitorexit
      //   160: goto +107 -> 267
      //   163: astore 16
      //   165: aload 15
      //   167: monitorexit
      //   168: aload 16
      //   170: athrow
      //   171: aload_0
      //   172: aconst_null
      //   173: putfield 160	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:queue	Ljava/util/List;
      //   176: aload 9
      //   178: monitorexit
      //   179: aload_0
      //   180: aload 11
      //   182: invokevirtual 167	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:drain	(Ljava/util/List;)Z
      //   185: istore 12
      //   187: iload 12
      //   189: ifne -82 -> 107
      //   192: iconst_0
      //   193: ifne +74 -> 267
      //   196: aload_0
      //   197: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   200: astore 13
      //   202: aload 13
      //   204: monitorenter
      //   205: aload_0
      //   206: iconst_0
      //   207: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   210: aload 13
      //   212: monitorexit
      //   213: goto +54 -> 267
      //   216: astore 14
      //   218: aload 13
      //   220: monitorexit
      //   221: aload 14
      //   223: athrow
      //   224: astore 10
      //   226: aload 9
      //   228: monitorexit
      //   229: aload 10
      //   231: athrow
      //   232: astore 5
      //   234: iload 4
      //   236: ifne +20 -> 256
      //   239: aload_0
      //   240: getfield 50	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:guard	Ljava/lang/Object;
      //   243: astore 6
      //   245: aload 6
      //   247: monitorenter
      //   248: aload_0
      //   249: iconst_0
      //   250: putfield 158	rx/internal/operators/OperatorWindowWithTime$ExactSubscriber:emitting	Z
      //   253: aload 6
      //   255: monitorexit
      //   256: aload 5
      //   258: athrow
      //   259: astore 7
      //   261: aload 6
      //   263: monitorexit
      //   264: aload 7
      //   266: athrow
      //   267: return
      //
      // Exception table:
      //   from	to	target	type
      //   83	99	94	finally
      //   7	55	102	finally
      //   103	105	102	finally
      //   152	168	163	finally
      //   205	221	216	finally
      //   116	138	224	finally
      //   171	179	224	finally
      //   226	229	224	finally
      //   58	65	232	finally
      //   107	116	232	finally
      //   179	187	232	finally
      //   229	232	232	finally
      //   248	256	259	finally
      //   261	264	259	finally
    }

    public void onStart()
    {
      request(9223372036854775807L);
    }

    boolean replaceSubject()
    {
      Observer localObserver = this.state.consumer;
      if (localObserver != null)
        localObserver.onCompleted();
      if (this.child.isUnsubscribed())
      {
        this.state = this.state.clear();
        unsubscribe();
      }
      for (int i = 0; ; i = 1)
      {
        return i;
        UnicastSubject localUnicastSubject = UnicastSubject.create();
        this.state = this.state.create(localUnicastSubject, localUnicastSubject);
        this.child.onNext(localUnicastSubject);
      }
    }

    void scheduleExact()
    {
      this.worker.schedulePeriodically(new Action0()
      {
        public void call()
        {
          OperatorWindowWithTime.ExactSubscriber.this.nextWindow();
        }
      }
      , 0L, OperatorWindowWithTime.this.timespan, OperatorWindowWithTime.this.unit);
    }
  }

  static final class State<T>
  {
    static final State<Object> EMPTY = new State(null, null, 0);
    final Observer<T> consumer;
    final int count;
    final Observable<T> producer;

    public State(Observer<T> paramObserver, Observable<T> paramObservable, int paramInt)
    {
      this.consumer = paramObserver;
      this.producer = paramObservable;
      this.count = paramInt;
    }

    public static <T> State<T> empty()
    {
      return EMPTY;
    }

    public State<T> clear()
    {
      return empty();
    }

    public State<T> create(Observer<T> paramObserver, Observable<T> paramObservable)
    {
      return new State(paramObserver, paramObservable, 0);
    }

    public State<T> next()
    {
      return new State(this.consumer, this.producer, 1 + this.count);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorWindowWithTime
 * JD-Core Version:    0.6.0
 */