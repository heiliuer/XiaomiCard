package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import rx.Observable;
import rx.Observable.Operator;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

public final class OperatorWindowWithObservableFactory<T, U>
  implements Observable.Operator<Observable<T>, T>
{
  static final Object NEXT_SUBJECT = new Object();
  static final NotificationLite<Object> nl = NotificationLite.instance();
  final Func0<? extends Observable<? extends U>> otherFactory;

  public OperatorWindowWithObservableFactory(Func0<? extends Observable<? extends U>> paramFunc0)
  {
    this.otherFactory = paramFunc0;
  }

  public Subscriber<? super T> call(Subscriber<? super Observable<T>> paramSubscriber)
  {
    SourceSubscriber localSourceSubscriber = new SourceSubscriber(paramSubscriber, this.otherFactory);
    paramSubscriber.add(localSourceSubscriber);
    localSourceSubscriber.replaceWindow();
    return localSourceSubscriber;
  }

  static final class BoundarySubscriber<T, U> extends Subscriber<U>
  {
    boolean done;
    final OperatorWindowWithObservableFactory.SourceSubscriber<T, U> sub;

    public BoundarySubscriber(Subscriber<?> paramSubscriber, OperatorWindowWithObservableFactory.SourceSubscriber<T, U> paramSourceSubscriber)
    {
      this.sub = paramSourceSubscriber;
    }

    public void onCompleted()
    {
      if (!this.done)
      {
        this.done = true;
        this.sub.onCompleted();
      }
    }

    public void onError(Throwable paramThrowable)
    {
      this.sub.onError(paramThrowable);
    }

    public void onNext(U paramU)
    {
      if (!this.done)
      {
        this.done = true;
        this.sub.replaceWindow();
      }
    }

    public void onStart()
    {
      request(9223372036854775807L);
    }
  }

  static final class SourceSubscriber<T, U> extends Subscriber<T>
  {
    final Subscriber<? super Observable<T>> child;
    Observer<T> consumer;
    boolean emitting;
    final Object guard;
    final Func0<? extends Observable<? extends U>> otherFactory;
    Observable<T> producer;
    List<Object> queue;
    final SerialSubscription ssub;

    public SourceSubscriber(Subscriber<? super Observable<T>> paramSubscriber, Func0<? extends Observable<? extends U>> paramFunc0)
    {
      this.child = new SerializedSubscriber(paramSubscriber);
      this.guard = new Object();
      this.ssub = new SerialSubscription();
      this.otherFactory = paramFunc0;
      add(this.ssub);
    }

    void complete()
    {
      Observer localObserver = this.consumer;
      this.consumer = null;
      this.producer = null;
      if (localObserver != null)
        localObserver.onCompleted();
      this.child.onCompleted();
      unsubscribe();
    }

    void createNewWindow()
    {
      UnicastSubject localUnicastSubject = UnicastSubject.create();
      this.consumer = localUnicastSubject;
      this.producer = localUnicastSubject;
      try
      {
        Observable localObservable = (Observable)this.otherFactory.call();
        OperatorWindowWithObservableFactory.BoundarySubscriber localBoundarySubscriber = new OperatorWindowWithObservableFactory.BoundarySubscriber(this.child, this);
        this.ssub.set(localBoundarySubscriber);
        localObservable.unsafeSubscribe(localBoundarySubscriber);
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          this.child.onError(localThrowable);
          unsubscribe();
        }
      }
    }

    void drain(List<Object> paramList)
    {
      if (paramList == null);
      label89: 
      while (true)
      {
        return;
        Iterator localIterator = paramList.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
            break label89;
          Object localObject = localIterator.next();
          if (localObject == OperatorWindowWithObservableFactory.NEXT_SUBJECT)
          {
            replaceSubject();
            continue;
          }
          if (OperatorWindowWithObservableFactory.nl.isError(localObject))
          {
            error(OperatorWindowWithObservableFactory.nl.getError(localObject));
            break;
          }
          if (OperatorWindowWithObservableFactory.nl.isCompleted(localObject))
          {
            complete();
            break;
          }
          emitValue(localObject);
        }
      }
    }

    void emitValue(T paramT)
    {
      Observer localObserver = this.consumer;
      if (localObserver != null)
        localObserver.onNext(paramT);
    }

    void error(Throwable paramThrowable)
    {
      Observer localObserver = this.consumer;
      this.consumer = null;
      this.producer = null;
      if (localObserver != null)
        localObserver.onError(paramThrowable);
      this.child.onError(paramThrowable);
      unsubscribe();
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
          this.queue.add(OperatorWindowWithObservableFactory.nl.completed());
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
          this.queue = Collections.singletonList(OperatorWindowWithObservableFactory.nl.error(paramThrowable));
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
      //   1: getfield 46	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:guard	Ljava/lang/Object;
      //   4: astore_2
      //   5: aload_2
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   11: ifeq +37 -> 48
      //   14: aload_0
      //   15: getfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   18: ifnonnull +14 -> 32
      //   21: aload_0
      //   22: new 163	java/util/ArrayList
      //   25: dup
      //   26: invokespecial 164	java/util/ArrayList:<init>	()V
      //   29: putfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   32: aload_0
      //   33: getfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   36: aload_1
      //   37: invokeinterface 169 2 0
      //   42: pop
      //   43: aload_2
      //   44: monitorexit
      //   45: goto +212 -> 257
      //   48: aload_0
      //   49: getfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   52: astore 4
      //   54: aload_0
      //   55: aconst_null
      //   56: putfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   59: aload_0
      //   60: iconst_1
      //   61: putfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   64: aload_2
      //   65: monitorexit
      //   66: iconst_1
      //   67: istore 5
      //   69: iconst_0
      //   70: istore 6
      //   72: aload_0
      //   73: aload 4
      //   75: invokevirtual 171	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:drain	(Ljava/util/List;)V
      //   78: iload 5
      //   80: ifeq +11 -> 91
      //   83: iconst_0
      //   84: istore 5
      //   86: aload_0
      //   87: aload_1
      //   88: invokevirtual 153	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitValue	(Ljava/lang/Object;)V
      //   91: aload_0
      //   92: getfield 46	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:guard	Ljava/lang/Object;
      //   95: astore 10
      //   97: aload 10
      //   99: monitorenter
      //   100: aload_0
      //   101: getfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   104: astore 4
      //   106: aload_0
      //   107: aconst_null
      //   108: putfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   111: aload 4
      //   113: ifnonnull +52 -> 165
      //   116: aload_0
      //   117: iconst_0
      //   118: putfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   121: iconst_1
      //   122: istore 6
      //   124: aload 10
      //   126: monitorexit
      //   127: iload 6
      //   129: ifne +128 -> 257
      //   132: aload_0
      //   133: getfield 46	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:guard	Ljava/lang/Object;
      //   136: astore 15
      //   138: aload 15
      //   140: monitorenter
      //   141: aload_0
      //   142: iconst_0
      //   143: putfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   146: aload 15
      //   148: monitorexit
      //   149: goto +108 -> 257
      //   152: astore 16
      //   154: aload 15
      //   156: monitorexit
      //   157: aload 16
      //   159: athrow
      //   160: astore_3
      //   161: aload_2
      //   162: monitorexit
      //   163: aload_3
      //   164: athrow
      //   165: aload 10
      //   167: monitorexit
      //   168: aload_0
      //   169: getfield 41	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:child	Lrx/Subscriber;
      //   172: invokevirtual 183	rx/Subscriber:isUnsubscribed	()Z
      //   175: istore 12
      //   177: iload 12
      //   179: ifeq -107 -> 72
      //   182: iconst_0
      //   183: ifne +74 -> 257
      //   186: aload_0
      //   187: getfield 46	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:guard	Ljava/lang/Object;
      //   190: astore 13
      //   192: aload 13
      //   194: monitorenter
      //   195: aload_0
      //   196: iconst_0
      //   197: putfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   200: aload 13
      //   202: monitorexit
      //   203: goto +54 -> 257
      //   206: astore 14
      //   208: aload 13
      //   210: monitorexit
      //   211: aload 14
      //   213: athrow
      //   214: astore 11
      //   216: aload 10
      //   218: monitorexit
      //   219: aload 11
      //   221: athrow
      //   222: astore 7
      //   224: iload 6
      //   226: ifne +20 -> 246
      //   229: aload_0
      //   230: getfield 46	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:guard	Ljava/lang/Object;
      //   233: astore 8
      //   235: aload 8
      //   237: monitorenter
      //   238: aload_0
      //   239: iconst_0
      //   240: putfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   243: aload 8
      //   245: monitorexit
      //   246: aload 7
      //   248: athrow
      //   249: astore 9
      //   251: aload 8
      //   253: monitorexit
      //   254: aload 9
      //   256: athrow
      //   257: return
      //
      // Exception table:
      //   from	to	target	type
      //   141	157	152	finally
      //   7	66	160	finally
      //   161	163	160	finally
      //   195	211	206	finally
      //   100	127	214	finally
      //   165	168	214	finally
      //   216	219	214	finally
      //   72	100	222	finally
      //   168	177	222	finally
      //   219	222	222	finally
      //   238	246	249	finally
      //   251	254	249	finally
    }

    public void onStart()
    {
      request(9223372036854775807L);
    }

    void replaceSubject()
    {
      Observer localObserver = this.consumer;
      if (localObserver != null)
        localObserver.onCompleted();
      createNewWindow();
      this.child.onNext(this.producer);
    }

    // ERROR //
    void replaceWindow()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 46	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:guard	Ljava/lang/Object;
      //   4: astore_1
      //   5: aload_1
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   11: ifeq +39 -> 50
      //   14: aload_0
      //   15: getfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   18: ifnonnull +14 -> 32
      //   21: aload_0
      //   22: new 163	java/util/ArrayList
      //   25: dup
      //   26: invokespecial 164	java/util/ArrayList:<init>	()V
      //   29: putfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   32: aload_0
      //   33: getfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   36: getstatic 124	rx/internal/operators/OperatorWindowWithObservableFactory:NEXT_SUBJECT	Ljava/lang/Object;
      //   39: invokeinterface 169 2 0
      //   44: pop
      //   45: aload_1
      //   46: monitorexit
      //   47: goto +207 -> 254
      //   50: aload_0
      //   51: getfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   54: astore_3
      //   55: aload_0
      //   56: aconst_null
      //   57: putfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   60: aload_0
      //   61: iconst_1
      //   62: putfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   65: aload_1
      //   66: monitorexit
      //   67: iconst_1
      //   68: istore 4
      //   70: iconst_0
      //   71: istore 5
      //   73: aload_0
      //   74: aload_3
      //   75: invokevirtual 171	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:drain	(Ljava/util/List;)V
      //   78: iload 4
      //   80: ifeq +10 -> 90
      //   83: iconst_0
      //   84: istore 4
      //   86: aload_0
      //   87: invokevirtual 127	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:replaceSubject	()V
      //   90: aload_0
      //   91: getfield 46	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:guard	Ljava/lang/Object;
      //   94: astore 9
      //   96: aload 9
      //   98: monitorenter
      //   99: aload_0
      //   100: getfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   103: astore_3
      //   104: aload_0
      //   105: aconst_null
      //   106: putfield 161	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:queue	Ljava/util/List;
      //   109: aload_3
      //   110: ifnonnull +52 -> 162
      //   113: aload_0
      //   114: iconst_0
      //   115: putfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   118: iconst_1
      //   119: istore 5
      //   121: aload 9
      //   123: monitorexit
      //   124: iload 5
      //   126: ifne +128 -> 254
      //   129: aload_0
      //   130: getfield 46	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:guard	Ljava/lang/Object;
      //   133: astore 14
      //   135: aload 14
      //   137: monitorenter
      //   138: aload_0
      //   139: iconst_0
      //   140: putfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   143: aload 14
      //   145: monitorexit
      //   146: goto +108 -> 254
      //   149: astore 15
      //   151: aload 14
      //   153: monitorexit
      //   154: aload 15
      //   156: athrow
      //   157: astore_2
      //   158: aload_1
      //   159: monitorexit
      //   160: aload_2
      //   161: athrow
      //   162: aload 9
      //   164: monitorexit
      //   165: aload_0
      //   166: getfield 41	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:child	Lrx/Subscriber;
      //   169: invokevirtual 183	rx/Subscriber:isUnsubscribed	()Z
      //   172: istore 11
      //   174: iload 11
      //   176: ifeq -103 -> 73
      //   179: iconst_0
      //   180: ifne +74 -> 254
      //   183: aload_0
      //   184: getfield 46	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:guard	Ljava/lang/Object;
      //   187: astore 12
      //   189: aload 12
      //   191: monitorenter
      //   192: aload_0
      //   193: iconst_0
      //   194: putfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   197: aload 12
      //   199: monitorexit
      //   200: goto +54 -> 254
      //   203: astore 13
      //   205: aload 12
      //   207: monitorexit
      //   208: aload 13
      //   210: athrow
      //   211: astore 10
      //   213: aload 9
      //   215: monitorexit
      //   216: aload 10
      //   218: athrow
      //   219: astore 6
      //   221: iload 5
      //   223: ifne +20 -> 243
      //   226: aload_0
      //   227: getfield 46	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:guard	Ljava/lang/Object;
      //   230: astore 7
      //   232: aload 7
      //   234: monitorenter
      //   235: aload_0
      //   236: iconst_0
      //   237: putfield 159	rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber:emitting	Z
      //   240: aload 7
      //   242: monitorexit
      //   243: aload 6
      //   245: athrow
      //   246: astore 8
      //   248: aload 7
      //   250: monitorexit
      //   251: aload 8
      //   253: athrow
      //   254: return
      //
      // Exception table:
      //   from	to	target	type
      //   138	154	149	finally
      //   7	67	157	finally
      //   158	160	157	finally
      //   192	208	203	finally
      //   99	124	211	finally
      //   162	165	211	finally
      //   213	216	211	finally
      //   73	99	219	finally
      //   165	174	219	finally
      //   216	219	219	finally
      //   235	243	246	finally
      //   248	251	246	finally
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorWindowWithObservableFactory
 * JD-Core Version:    0.6.0
 */