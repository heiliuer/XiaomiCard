package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;

public final class OperatorMerge<T>
  implements Observable.Operator<T, Observable<? extends T>>
{
  final boolean delayErrors;
  final int maxConcurrent;

  private OperatorMerge(boolean paramBoolean, int paramInt)
  {
    this.delayErrors = paramBoolean;
    this.maxConcurrent = paramInt;
  }

  public static <T> OperatorMerge<T> instance(boolean paramBoolean)
  {
    if (paramBoolean);
    for (OperatorMerge localOperatorMerge = HolderDelayErrors.INSTANCE; ; localOperatorMerge = HolderNoDelay.INSTANCE)
      return localOperatorMerge;
  }

  public static <T> OperatorMerge<T> instance(boolean paramBoolean, int paramInt)
  {
    if (paramInt == 2147483647);
    for (OperatorMerge localOperatorMerge = instance(paramBoolean); ; localOperatorMerge = new OperatorMerge(paramBoolean, paramInt))
      return localOperatorMerge;
  }

  public Subscriber<Observable<? extends T>> call(Subscriber<? super T> paramSubscriber)
  {
    MergeSubscriber localMergeSubscriber = new MergeSubscriber(paramSubscriber, this.delayErrors, this.maxConcurrent);
    MergeProducer localMergeProducer = new MergeProducer(localMergeSubscriber);
    localMergeSubscriber.producer = localMergeProducer;
    paramSubscriber.add(localMergeSubscriber);
    paramSubscriber.setProducer(localMergeProducer);
    return localMergeSubscriber;
  }

  static final class InnerSubscriber<T> extends Subscriber<T>
  {
    static final int limit = RxRingBuffer.SIZE / 4;
    volatile boolean done;
    final long id;
    int outstanding;
    final OperatorMerge.MergeSubscriber<T> parent;
    volatile RxRingBuffer queue;

    public InnerSubscriber(OperatorMerge.MergeSubscriber<T> paramMergeSubscriber, long paramLong)
    {
      this.parent = paramMergeSubscriber;
      this.id = paramLong;
    }

    public void onCompleted()
    {
      this.done = true;
      this.parent.emit();
    }

    public void onError(Throwable paramThrowable)
    {
      this.done = true;
      this.parent.getOrCreateErrorQueue().offer(paramThrowable);
      this.parent.emit();
    }

    public void onNext(T paramT)
    {
      this.parent.tryEmit(this, paramT);
    }

    public void onStart()
    {
      this.outstanding = RxRingBuffer.SIZE;
      request(RxRingBuffer.SIZE);
    }

    public void requestMore(long paramLong)
    {
      int i = this.outstanding - (int)paramLong;
      if (i > limit)
        this.outstanding = i;
      while (true)
      {
        return;
        this.outstanding = RxRingBuffer.SIZE;
        int j = RxRingBuffer.SIZE - i;
        if (j <= 0)
          continue;
        request(j);
      }
    }
  }

  static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>>
  {
    static final OperatorMerge.InnerSubscriber<?>[] EMPTY = new OperatorMerge.InnerSubscriber[0];
    final Subscriber<? super T> child;
    final boolean delayErrors;
    volatile boolean done;
    boolean emitting;
    volatile ConcurrentLinkedQueue<Throwable> errors;
    final Object innerGuard;
    volatile OperatorMerge.InnerSubscriber<?>[] innerSubscribers;
    long lastId;
    int lastIndex;
    final int maxConcurrent;
    boolean missed;
    final NotificationLite<T> nl;
    OperatorMerge.MergeProducer<T> producer;
    volatile Queue<Object> queue;
    volatile CompositeSubscription subscriptions;
    long uniqueId;

    public MergeSubscriber(Subscriber<? super T> paramSubscriber, boolean paramBoolean, int paramInt)
    {
      this.child = paramSubscriber;
      this.delayErrors = paramBoolean;
      this.maxConcurrent = paramInt;
      this.nl = NotificationLite.instance();
      this.innerGuard = new Object();
      this.innerSubscribers = EMPTY;
      long l;
      if (paramInt == 2147483647)
        l = 9223372036854775807L;
      while (true)
      {
        request(l);
        return;
        l = paramInt;
      }
    }

    private void reportError()
    {
      ArrayList localArrayList = new ArrayList(this.errors);
      if (localArrayList.size() == 1)
        this.child.onError((Throwable)localArrayList.get(0));
      while (true)
      {
        return;
        this.child.onError(new CompositeException(localArrayList));
      }
    }

    void addInner(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber)
    {
      getOrCreateComposite().add(paramInnerSubscriber);
      synchronized (this.innerGuard)
      {
        OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber1 = this.innerSubscribers;
        int i = arrayOfInnerSubscriber1.length;
        OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber2 = new OperatorMerge.InnerSubscriber[i + 1];
        System.arraycopy(arrayOfInnerSubscriber1, 0, arrayOfInnerSubscriber2, 0, i);
        arrayOfInnerSubscriber2[i] = paramInnerSubscriber;
        this.innerSubscribers = arrayOfInnerSubscriber2;
        return;
      }
    }

    boolean checkTerminate()
    {
      int i = 1;
      if (this.child.isUnsubscribed());
      while (true)
      {
        return i;
        ConcurrentLinkedQueue localConcurrentLinkedQueue = this.errors;
        if ((!this.delayErrors) && (localConcurrentLinkedQueue != null) && (!localConcurrentLinkedQueue.isEmpty()));
        try
        {
          reportError();
          unsubscribe();
        }
        finally
        {
          unsubscribe();
        }
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
      //   0: iconst_0
      //   1: istore_1
      //   2: aload_0
      //   3: getfield 54	rx/internal/operators/OperatorMerge$MergeSubscriber:child	Lrx/Subscriber;
      //   6: astore 4
      //   8: aload_0
      //   9: invokevirtual 150	rx/internal/operators/OperatorMerge$MergeSubscriber:checkTerminate	()Z
      //   12: istore 5
      //   14: iload 5
      //   16: ifeq +26 -> 42
      //   19: iconst_1
      //   20: ifne +903 -> 923
      //   23: aload_0
      //   24: monitorenter
      //   25: aload_0
      //   26: iconst_0
      //   27: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   30: aload_0
      //   31: monitorexit
      //   32: goto +891 -> 923
      //   35: astore 51
      //   37: aload_0
      //   38: monitorexit
      //   39: aload 51
      //   41: athrow
      //   42: aload_0
      //   43: getfield 152	rx/internal/operators/OperatorMerge$MergeSubscriber:queue	Ljava/util/Queue;
      //   46: astore 6
      //   48: aload_0
      //   49: getfield 154	rx/internal/operators/OperatorMerge$MergeSubscriber:producer	Lrx/internal/operators/OperatorMerge$MergeProducer;
      //   52: invokevirtual 159	rx/internal/operators/OperatorMerge$MergeProducer:get	()J
      //   55: lstore 7
      //   57: lload 7
      //   59: ldc2_w 75
      //   62: lcmp
      //   63: ifne +59 -> 122
      //   66: iconst_1
      //   67: istore 9
      //   69: goto +855 -> 924
      //   72: lload 7
      //   74: lconst_0
      //   75: lcmp
      //   76: ifle +57 -> 133
      //   79: aload 6
      //   81: invokeinterface 163 1 0
      //   86: astore 44
      //   88: aload_0
      //   89: invokevirtual 150	rx/internal/operators/OperatorMerge$MergeSubscriber:checkTerminate	()Z
      //   92: istore 45
      //   94: iload 45
      //   96: ifeq +32 -> 128
      //   99: iconst_1
      //   100: ifne +823 -> 923
      //   103: aload_0
      //   104: monitorenter
      //   105: aload_0
      //   106: iconst_0
      //   107: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   110: aload_0
      //   111: monitorexit
      //   112: goto +811 -> 923
      //   115: astore 50
      //   117: aload_0
      //   118: monitorexit
      //   119: aload 50
      //   121: athrow
      //   122: iconst_0
      //   123: istore 9
      //   125: goto +799 -> 924
      //   128: aload 44
      //   130: ifnonnull +127 -> 257
      //   133: iload 43
      //   135: ifle +13 -> 148
      //   138: iload 9
      //   140: ifeq +231 -> 371
      //   143: ldc2_w 75
      //   146: lstore 7
      //   148: lload 7
      //   150: lconst_0
      //   151: lcmp
      //   152: ifeq +8 -> 160
      //   155: aload 44
      //   157: ifnonnull +775 -> 932
      //   160: aload_0
      //   161: getfield 165	rx/internal/operators/OperatorMerge$MergeSubscriber:done	Z
      //   164: istore 11
      //   166: aload_0
      //   167: getfield 152	rx/internal/operators/OperatorMerge$MergeSubscriber:queue	Ljava/util/Queue;
      //   170: astore 12
      //   172: aload_0
      //   173: getfield 73	rx/internal/operators/OperatorMerge$MergeSubscriber:innerSubscribers	[Lrx/internal/operators/OperatorMerge$InnerSubscriber;
      //   176: astore 13
      //   178: aload 13
      //   180: arraylength
      //   181: istore 14
      //   183: iload 11
      //   185: ifeq +207 -> 392
      //   188: aload 12
      //   190: ifnull +13 -> 203
      //   193: aload 12
      //   195: invokeinterface 135 1 0
      //   200: ifeq +192 -> 392
      //   203: iload 14
      //   205: ifne +187 -> 392
      //   208: aload_0
      //   209: getfield 85	rx/internal/operators/OperatorMerge$MergeSubscriber:errors	Ljava/util/concurrent/ConcurrentLinkedQueue;
      //   212: astore 41
      //   214: aload 41
      //   216: ifnull +13 -> 229
      //   219: aload 41
      //   221: invokeinterface 135 1 0
      //   226: ifeq +159 -> 385
      //   229: aload 4
      //   231: invokevirtual 168	rx/Subscriber:onCompleted	()V
      //   234: iconst_1
      //   235: ifne +688 -> 923
      //   238: aload_0
      //   239: monitorenter
      //   240: aload_0
      //   241: iconst_0
      //   242: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   245: aload_0
      //   246: monitorexit
      //   247: goto +676 -> 923
      //   250: astore 42
      //   252: aload_0
      //   253: monitorexit
      //   254: aload 42
      //   256: athrow
      //   257: aload_0
      //   258: getfield 66	rx/internal/operators/OperatorMerge$MergeSubscriber:nl	Lrx/internal/operators/NotificationLite;
      //   261: aload 44
      //   263: invokevirtual 172	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   266: astore 46
      //   268: aload 4
      //   270: aload 46
      //   272: invokevirtual 176	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   275: iinc 10 1
      //   278: iinc 43 1
      //   281: lload 7
      //   283: lconst_1
      //   284: lsub
      //   285: lstore 7
      //   287: goto -215 -> 72
      //   290: astore 47
      //   292: aload_0
      //   293: getfield 56	rx/internal/operators/OperatorMerge$MergeSubscriber:delayErrors	Z
      //   296: ifne +44 -> 340
      //   299: aload 47
      //   301: invokestatic 181	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   304: iconst_1
      //   305: istore_1
      //   306: aload_0
      //   307: invokevirtual 140	rx/internal/operators/OperatorMerge$MergeSubscriber:unsubscribe	()V
      //   310: aload 4
      //   312: aload 47
      //   314: invokevirtual 104	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   317: iload_1
      //   318: ifne +605 -> 923
      //   321: aload_0
      //   322: monitorenter
      //   323: aload_0
      //   324: iconst_0
      //   325: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   328: aload_0
      //   329: monitorexit
      //   330: goto +593 -> 923
      //   333: astore 49
      //   335: aload_0
      //   336: monitorexit
      //   337: aload 49
      //   339: athrow
      //   340: aload_0
      //   341: invokevirtual 185	rx/internal/operators/OperatorMerge$MergeSubscriber:getOrCreateErrorQueue	()Ljava/util/Queue;
      //   344: aload 47
      //   346: invokeinterface 189 2 0
      //   351: pop
      //   352: goto -77 -> 275
      //   355: astore_2
      //   356: iload_1
      //   357: ifne +12 -> 369
      //   360: aload_0
      //   361: monitorenter
      //   362: aload_0
      //   363: iconst_0
      //   364: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   367: aload_0
      //   368: monitorexit
      //   369: aload_2
      //   370: athrow
      //   371: aload_0
      //   372: getfield 154	rx/internal/operators/OperatorMerge$MergeSubscriber:producer	Lrx/internal/operators/OperatorMerge$MergeProducer;
      //   375: iload 43
      //   377: invokevirtual 193	rx/internal/operators/OperatorMerge$MergeProducer:produced	(I)J
      //   380: lstore 7
      //   382: goto -234 -> 148
      //   385: aload_0
      //   386: invokespecial 137	rx/internal/operators/OperatorMerge$MergeSubscriber:reportError	()V
      //   389: goto -155 -> 234
      //   392: iconst_0
      //   393: istore 15
      //   395: iload 14
      //   397: ifle +427 -> 824
      //   400: aload_0
      //   401: getfield 195	rx/internal/operators/OperatorMerge$MergeSubscriber:lastId	J
      //   404: lstore 18
      //   406: aload_0
      //   407: getfield 197	rx/internal/operators/OperatorMerge$MergeSubscriber:lastIndex	I
      //   410: istore 20
      //   412: iload 14
      //   414: iload 20
      //   416: if_icmple +525 -> 941
      //   419: aload 13
      //   421: iload 20
      //   423: aaload
      //   424: getfield 200	rx/internal/operators/OperatorMerge$InnerSubscriber:id	J
      //   427: lload 18
      //   429: lcmp
      //   430: ifeq +531 -> 961
      //   433: goto +508 -> 941
      //   436: iload 22
      //   438: iload 14
      //   440: if_icmpge +17 -> 457
      //   443: aload 13
      //   445: iload 21
      //   447: aaload
      //   448: getfield 200	rx/internal/operators/OperatorMerge$InnerSubscriber:id	J
      //   451: lload 18
      //   453: lcmp
      //   454: ifne +69 -> 523
      //   457: iload 21
      //   459: istore 20
      //   461: aload_0
      //   462: iload 21
      //   464: putfield 197	rx/internal/operators/OperatorMerge$MergeSubscriber:lastIndex	I
      //   467: aload_0
      //   468: aload 13
      //   470: iload 21
      //   472: aaload
      //   473: getfield 200	rx/internal/operators/OperatorMerge$InnerSubscriber:id	J
      //   476: putfield 195	rx/internal/operators/OperatorMerge$MergeSubscriber:lastId	J
      //   479: goto +482 -> 961
      //   482: iload 24
      //   484: iload 14
      //   486: if_icmpge +320 -> 806
      //   489: aload_0
      //   490: invokevirtual 150	rx/internal/operators/OperatorMerge$MergeSubscriber:checkTerminate	()Z
      //   493: istore 25
      //   495: iload 25
      //   497: ifeq +45 -> 542
      //   500: iconst_1
      //   501: ifne +422 -> 923
      //   504: aload_0
      //   505: monitorenter
      //   506: aload_0
      //   507: iconst_0
      //   508: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   511: aload_0
      //   512: monitorexit
      //   513: goto +410 -> 923
      //   516: astore 40
      //   518: aload_0
      //   519: monitorexit
      //   520: aload 40
      //   522: athrow
      //   523: iinc 21 1
      //   526: iload 21
      //   528: iload 14
      //   530: if_icmpne +6 -> 536
      //   533: iconst_0
      //   534: istore 21
      //   536: iinc 22 1
      //   539: goto -103 -> 436
      //   542: aload 13
      //   544: iload 23
      //   546: aaload
      //   547: astore 26
      //   549: aconst_null
      //   550: astore 27
      //   552: goto +419 -> 971
      //   555: lload 7
      //   557: lconst_0
      //   558: lcmp
      //   559: ifle +49 -> 608
      //   562: aload_0
      //   563: invokevirtual 150	rx/internal/operators/OperatorMerge$MergeSubscriber:checkTerminate	()Z
      //   566: istore 33
      //   568: iload 33
      //   570: ifeq +26 -> 596
      //   573: iconst_1
      //   574: ifne +349 -> 923
      //   577: aload_0
      //   578: monitorenter
      //   579: aload_0
      //   580: iconst_0
      //   581: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   584: aload_0
      //   585: monitorexit
      //   586: goto +337 -> 923
      //   589: astore 39
      //   591: aload_0
      //   592: monitorexit
      //   593: aload 39
      //   595: athrow
      //   596: aload 26
      //   598: getfield 203	rx/internal/operators/OperatorMerge$InnerSubscriber:queue	Lrx/internal/util/RxRingBuffer;
      //   601: astore 34
      //   603: aload 34
      //   605: ifnonnull +107 -> 712
      //   608: iload 28
      //   610: ifle +367 -> 977
      //   613: iload 9
      //   615: ifne +377 -> 992
      //   618: aload_0
      //   619: getfield 154	rx/internal/operators/OperatorMerge$MergeSubscriber:producer	Lrx/internal/operators/OperatorMerge$MergeProducer;
      //   622: iload 28
      //   624: invokevirtual 193	rx/internal/operators/OperatorMerge$MergeProducer:produced	(I)J
      //   627: lstore 7
      //   629: aload 26
      //   631: iload 28
      //   633: i2l
      //   634: invokevirtual 206	rx/internal/operators/OperatorMerge$InnerSubscriber:requestMore	(J)V
      //   637: goto +340 -> 977
      //   640: aload 26
      //   642: getfield 207	rx/internal/operators/OperatorMerge$InnerSubscriber:done	Z
      //   645: istore 29
      //   647: aload 26
      //   649: getfield 203	rx/internal/operators/OperatorMerge$InnerSubscriber:queue	Lrx/internal/util/RxRingBuffer;
      //   652: astore 30
      //   654: iload 29
      //   656: ifeq +350 -> 1006
      //   659: aload 30
      //   661: ifnull +11 -> 672
      //   664: aload 30
      //   666: invokevirtual 210	rx/internal/util/RxRingBuffer:isEmpty	()Z
      //   669: ifeq +337 -> 1006
      //   672: aload_0
      //   673: aload 26
      //   675: invokevirtual 213	rx/internal/operators/OperatorMerge$MergeSubscriber:removeInner	(Lrx/internal/operators/OperatorMerge$InnerSubscriber;)V
      //   678: aload_0
      //   679: invokevirtual 150	rx/internal/operators/OperatorMerge$MergeSubscriber:checkTerminate	()Z
      //   682: istore 31
      //   684: iload 31
      //   686: ifeq +314 -> 1000
      //   689: iconst_1
      //   690: ifne +233 -> 923
      //   693: aload_0
      //   694: monitorenter
      //   695: aload_0
      //   696: iconst_0
      //   697: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   700: aload_0
      //   701: monitorexit
      //   702: goto +221 -> 923
      //   705: astore 32
      //   707: aload_0
      //   708: monitorexit
      //   709: aload 32
      //   711: athrow
      //   712: aload 34
      //   714: invokevirtual 214	rx/internal/util/RxRingBuffer:poll	()Ljava/lang/Object;
      //   717: astore 27
      //   719: aload 27
      //   721: ifnull -113 -> 608
      //   724: aload_0
      //   725: getfield 66	rx/internal/operators/OperatorMerge$MergeSubscriber:nl	Lrx/internal/operators/NotificationLite;
      //   728: aload 27
      //   730: invokevirtual 172	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   733: astore 35
      //   735: aload 4
      //   737: aload 35
      //   739: invokevirtual 176	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   742: lload 7
      //   744: lconst_1
      //   745: lsub
      //   746: lstore 7
      //   748: iinc 28 1
      //   751: goto -196 -> 555
      //   754: astore 36
      //   756: iconst_1
      //   757: istore_1
      //   758: aload 36
      //   760: invokestatic 181	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   763: aload 4
      //   765: aload 36
      //   767: invokevirtual 104	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   770: aload_0
      //   771: invokevirtual 140	rx/internal/operators/OperatorMerge$MergeSubscriber:unsubscribe	()V
      //   774: iload_1
      //   775: ifne +148 -> 923
      //   778: aload_0
      //   779: monitorenter
      //   780: aload_0
      //   781: iconst_0
      //   782: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   785: aload_0
      //   786: monitorexit
      //   787: goto +136 -> 923
      //   790: astore 38
      //   792: aload_0
      //   793: monitorexit
      //   794: aload 38
      //   796: athrow
      //   797: astore 37
      //   799: aload_0
      //   800: invokevirtual 140	rx/internal/operators/OperatorMerge$MergeSubscriber:unsubscribe	()V
      //   803: aload 37
      //   805: athrow
      //   806: aload_0
      //   807: iload 23
      //   809: putfield 197	rx/internal/operators/OperatorMerge$MergeSubscriber:lastIndex	I
      //   812: aload_0
      //   813: aload 13
      //   815: iload 23
      //   817: aaload
      //   818: getfield 200	rx/internal/operators/OperatorMerge$InnerSubscriber:id	J
      //   821: putfield 195	rx/internal/operators/OperatorMerge$MergeSubscriber:lastId	J
      //   824: iload 10
      //   826: ifle +10 -> 836
      //   829: aload_0
      //   830: iload 10
      //   832: i2l
      //   833: invokevirtual 80	rx/internal/operators/OperatorMerge$MergeSubscriber:request	(J)V
      //   836: iload 15
      //   838: ifne -830 -> 8
      //   841: aload_0
      //   842: monitorenter
      //   843: aload_0
      //   844: getfield 145	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   847: ifne +54 -> 901
      //   850: iconst_1
      //   851: istore_1
      //   852: aload_0
      //   853: iconst_0
      //   854: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   857: aload_0
      //   858: monitorexit
      //   859: iload_1
      //   860: ifne +63 -> 923
      //   863: aload_0
      //   864: monitorenter
      //   865: aload_0
      //   866: iconst_0
      //   867: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   870: aload_0
      //   871: monitorexit
      //   872: goto +51 -> 923
      //   875: astore 17
      //   877: aload_0
      //   878: monitorexit
      //   879: aload 17
      //   881: athrow
      //   882: iinc 23 1
      //   885: iload 23
      //   887: iload 14
      //   889: if_icmpne +6 -> 895
      //   892: iconst_0
      //   893: istore 23
      //   895: iinc 24 1
      //   898: goto -416 -> 482
      //   901: aload_0
      //   902: iconst_0
      //   903: putfield 145	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   906: aload_0
      //   907: monitorexit
      //   908: goto -900 -> 8
      //   911: astore 16
      //   913: aload_0
      //   914: monitorexit
      //   915: aload 16
      //   917: athrow
      //   918: astore_3
      //   919: aload_0
      //   920: monitorexit
      //   921: aload_3
      //   922: athrow
      //   923: return
      //   924: iconst_0
      //   925: istore 10
      //   927: aload 6
      //   929: ifnull -769 -> 160
      //   932: iconst_0
      //   933: istore 43
      //   935: aconst_null
      //   936: astore 44
      //   938: goto -866 -> 72
      //   941: iload 14
      //   943: iload 20
      //   945: if_icmpgt +6 -> 951
      //   948: iconst_0
      //   949: istore 20
      //   951: iload 20
      //   953: istore 21
      //   955: iconst_0
      //   956: istore 22
      //   958: goto -522 -> 436
      //   961: iload 20
      //   963: istore 23
      //   965: iconst_0
      //   966: istore 24
      //   968: goto -486 -> 482
      //   971: iconst_0
      //   972: istore 28
      //   974: goto -419 -> 555
      //   977: lload 7
      //   979: lconst_0
      //   980: lcmp
      //   981: ifeq -341 -> 640
      //   984: aload 27
      //   986: ifnonnull -15 -> 971
      //   989: goto -349 -> 640
      //   992: ldc2_w 75
      //   995: lstore 7
      //   997: goto -368 -> 629
      //   1000: iinc 10 1
      //   1003: iconst_1
      //   1004: istore 15
      //   1006: lload 7
      //   1008: lconst_0
      //   1009: lcmp
      //   1010: ifne -128 -> 882
      //   1013: goto -207 -> 806
      //
      // Exception table:
      //   from	to	target	type
      //   25	39	35	finally
      //   105	119	115	finally
      //   240	254	250	finally
      //   268	275	290	java/lang/Throwable
      //   323	337	333	finally
      //   2	14	355	finally
      //   42	94	355	finally
      //   160	234	355	finally
      //   257	268	355	finally
      //   268	275	355	finally
      //   292	317	355	finally
      //   340	352	355	finally
      //   371	495	355	finally
      //   542	568	355	finally
      //   596	684	355	finally
      //   712	735	355	finally
      //   735	742	355	finally
      //   758	763	355	finally
      //   770	774	355	finally
      //   799	843	355	finally
      //   915	918	355	finally
      //   506	520	516	finally
      //   579	593	589	finally
      //   695	709	705	finally
      //   735	742	754	java/lang/Throwable
      //   780	794	790	finally
      //   763	770	797	finally
      //   865	879	875	finally
      //   843	859	911	finally
      //   901	915	911	finally
      //   362	369	918	finally
      //   919	921	918	finally
    }

    // ERROR //
    protected void emitScalar(T paramT, long paramLong)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 4
      //   3: aload_0
      //   4: getfield 54	rx/internal/operators/OperatorMerge$MergeSubscriber:child	Lrx/Subscriber;
      //   7: aload_1
      //   8: invokevirtual 176	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   11: lload_2
      //   12: ldc2_w 75
      //   15: lcmp
      //   16: ifeq +12 -> 28
      //   19: aload_0
      //   20: getfield 154	rx/internal/operators/OperatorMerge$MergeSubscriber:producer	Lrx/internal/operators/OperatorMerge$MergeProducer;
      //   23: iconst_1
      //   24: invokevirtual 193	rx/internal/operators/OperatorMerge$MergeProducer:produced	(I)J
      //   27: pop2
      //   28: aload_0
      //   29: lconst_1
      //   30: invokevirtual 217	rx/internal/operators/OperatorMerge$MergeSubscriber:requestMore	(J)V
      //   33: aload_0
      //   34: monitorenter
      //   35: iconst_1
      //   36: istore 4
      //   38: aload_0
      //   39: getfield 145	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   42: ifne +117 -> 159
      //   45: aload_0
      //   46: iconst_0
      //   47: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   50: aload_0
      //   51: monitorexit
      //   52: iload 4
      //   54: ifne +12 -> 66
      //   57: aload_0
      //   58: monitorenter
      //   59: aload_0
      //   60: iconst_0
      //   61: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   64: aload_0
      //   65: monitorexit
      //   66: return
      //   67: astore 7
      //   69: aload_0
      //   70: getfield 56	rx/internal/operators/OperatorMerge$MergeSubscriber:delayErrors	Z
      //   73: ifne +45 -> 118
      //   76: aload 7
      //   78: invokestatic 181	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   81: iconst_1
      //   82: istore 4
      //   84: aload_0
      //   85: invokevirtual 140	rx/internal/operators/OperatorMerge$MergeSubscriber:unsubscribe	()V
      //   88: aload_0
      //   89: aload 7
      //   91: invokevirtual 218	rx/internal/operators/OperatorMerge$MergeSubscriber:onError	(Ljava/lang/Throwable;)V
      //   94: iload 4
      //   96: ifne -30 -> 66
      //   99: aload_0
      //   100: monitorenter
      //   101: aload_0
      //   102: iconst_0
      //   103: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   106: aload_0
      //   107: monitorexit
      //   108: goto -42 -> 66
      //   111: astore 14
      //   113: aload_0
      //   114: monitorexit
      //   115: aload 14
      //   117: athrow
      //   118: aload_0
      //   119: invokevirtual 185	rx/internal/operators/OperatorMerge$MergeSubscriber:getOrCreateErrorQueue	()Ljava/util/Queue;
      //   122: aload 7
      //   124: invokeinterface 189 2 0
      //   129: pop
      //   130: goto -119 -> 11
      //   133: astore 5
      //   135: iload 4
      //   137: ifne +12 -> 149
      //   140: aload_0
      //   141: monitorenter
      //   142: aload_0
      //   143: iconst_0
      //   144: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   147: aload_0
      //   148: monitorexit
      //   149: aload 5
      //   151: athrow
      //   152: astore 11
      //   154: aload_0
      //   155: monitorexit
      //   156: aload 11
      //   158: athrow
      //   159: aload_0
      //   160: iconst_0
      //   161: putfield 145	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   164: aload_0
      //   165: monitorexit
      //   166: iload 4
      //   168: ifne +12 -> 180
      //   171: aload_0
      //   172: monitorenter
      //   173: aload_0
      //   174: iconst_0
      //   175: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   178: aload_0
      //   179: monitorexit
      //   180: aload_0
      //   181: invokevirtual 148	rx/internal/operators/OperatorMerge$MergeSubscriber:emitLoop	()V
      //   184: goto -118 -> 66
      //   187: astore 9
      //   189: aload_0
      //   190: monitorexit
      //   191: aload 9
      //   193: athrow
      //   194: astore 10
      //   196: aload_0
      //   197: monitorexit
      //   198: aload 10
      //   200: athrow
      //   201: astore 6
      //   203: aload_0
      //   204: monitorexit
      //   205: aload 6
      //   207: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   3	11	67	java/lang/Throwable
      //   101	115	111	finally
      //   3	11	133	finally
      //   19	35	133	finally
      //   69	94	133	finally
      //   118	130	133	finally
      //   191	194	133	finally
      //   59	66	152	finally
      //   154	156	152	finally
      //   38	52	187	finally
      //   159	166	187	finally
      //   189	191	187	finally
      //   173	180	194	finally
      //   196	198	194	finally
      //   142	149	201	finally
      //   203	205	201	finally
    }

    // ERROR //
    protected void emitScalar(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber, T paramT, long paramLong)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 5
      //   3: aload_0
      //   4: getfield 54	rx/internal/operators/OperatorMerge$MergeSubscriber:child	Lrx/Subscriber;
      //   7: aload_2
      //   8: invokevirtual 176	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   11: lload_3
      //   12: ldc2_w 75
      //   15: lcmp
      //   16: ifeq +12 -> 28
      //   19: aload_0
      //   20: getfield 154	rx/internal/operators/OperatorMerge$MergeSubscriber:producer	Lrx/internal/operators/OperatorMerge$MergeProducer;
      //   23: iconst_1
      //   24: invokevirtual 193	rx/internal/operators/OperatorMerge$MergeProducer:produced	(I)J
      //   27: pop2
      //   28: aload_1
      //   29: lconst_1
      //   30: invokevirtual 206	rx/internal/operators/OperatorMerge$InnerSubscriber:requestMore	(J)V
      //   33: aload_0
      //   34: monitorenter
      //   35: iconst_1
      //   36: istore 5
      //   38: aload_0
      //   39: getfield 145	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   42: ifne +117 -> 159
      //   45: aload_0
      //   46: iconst_0
      //   47: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   50: aload_0
      //   51: monitorexit
      //   52: iload 5
      //   54: ifne +12 -> 66
      //   57: aload_0
      //   58: monitorenter
      //   59: aload_0
      //   60: iconst_0
      //   61: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   64: aload_0
      //   65: monitorexit
      //   66: return
      //   67: astore 8
      //   69: aload_0
      //   70: getfield 56	rx/internal/operators/OperatorMerge$MergeSubscriber:delayErrors	Z
      //   73: ifne +45 -> 118
      //   76: aload 8
      //   78: invokestatic 181	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   81: iconst_1
      //   82: istore 5
      //   84: aload_1
      //   85: invokevirtual 220	rx/internal/operators/OperatorMerge$InnerSubscriber:unsubscribe	()V
      //   88: aload_1
      //   89: aload 8
      //   91: invokevirtual 221	rx/internal/operators/OperatorMerge$InnerSubscriber:onError	(Ljava/lang/Throwable;)V
      //   94: iload 5
      //   96: ifne -30 -> 66
      //   99: aload_0
      //   100: monitorenter
      //   101: aload_0
      //   102: iconst_0
      //   103: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   106: aload_0
      //   107: monitorexit
      //   108: goto -42 -> 66
      //   111: astore 15
      //   113: aload_0
      //   114: monitorexit
      //   115: aload 15
      //   117: athrow
      //   118: aload_0
      //   119: invokevirtual 185	rx/internal/operators/OperatorMerge$MergeSubscriber:getOrCreateErrorQueue	()Ljava/util/Queue;
      //   122: aload 8
      //   124: invokeinterface 189 2 0
      //   129: pop
      //   130: goto -119 -> 11
      //   133: astore 6
      //   135: iload 5
      //   137: ifne +12 -> 149
      //   140: aload_0
      //   141: monitorenter
      //   142: aload_0
      //   143: iconst_0
      //   144: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   147: aload_0
      //   148: monitorexit
      //   149: aload 6
      //   151: athrow
      //   152: astore 12
      //   154: aload_0
      //   155: monitorexit
      //   156: aload 12
      //   158: athrow
      //   159: aload_0
      //   160: iconst_0
      //   161: putfield 145	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   164: aload_0
      //   165: monitorexit
      //   166: iload 5
      //   168: ifne +12 -> 180
      //   171: aload_0
      //   172: monitorenter
      //   173: aload_0
      //   174: iconst_0
      //   175: putfield 143	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   178: aload_0
      //   179: monitorexit
      //   180: aload_0
      //   181: invokevirtual 148	rx/internal/operators/OperatorMerge$MergeSubscriber:emitLoop	()V
      //   184: goto -118 -> 66
      //   187: astore 10
      //   189: aload_0
      //   190: monitorexit
      //   191: aload 10
      //   193: athrow
      //   194: astore 11
      //   196: aload_0
      //   197: monitorexit
      //   198: aload 11
      //   200: athrow
      //   201: astore 7
      //   203: aload_0
      //   204: monitorexit
      //   205: aload 7
      //   207: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   3	11	67	java/lang/Throwable
      //   101	115	111	finally
      //   3	11	133	finally
      //   19	35	133	finally
      //   69	94	133	finally
      //   118	130	133	finally
      //   191	194	133	finally
      //   59	66	152	finally
      //   154	156	152	finally
      //   38	52	187	finally
      //   159	166	187	finally
      //   189	191	187	finally
      //   173	180	194	finally
      //   196	198	194	finally
      //   142	149	201	finally
      //   203	205	201	finally
    }

    // ERROR //
    CompositeSubscription getOrCreateComposite()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 223	rx/internal/operators/OperatorMerge$MergeSubscriber:subscriptions	Lrx/subscriptions/CompositeSubscription;
      //   4: astore_1
      //   5: aload_1
      //   6: ifnonnull +47 -> 53
      //   9: iconst_0
      //   10: istore_2
      //   11: aload_0
      //   12: monitorenter
      //   13: aload_0
      //   14: getfield 223	rx/internal/operators/OperatorMerge$MergeSubscriber:subscriptions	Lrx/subscriptions/CompositeSubscription;
      //   17: astore_1
      //   18: aload_1
      //   19: ifnonnull +23 -> 42
      //   22: new 115	rx/subscriptions/CompositeSubscription
      //   25: dup
      //   26: invokespecial 224	rx/subscriptions/CompositeSubscription:<init>	()V
      //   29: astore 4
      //   31: aload_0
      //   32: aload 4
      //   34: putfield 223	rx/internal/operators/OperatorMerge$MergeSubscriber:subscriptions	Lrx/subscriptions/CompositeSubscription;
      //   37: iconst_1
      //   38: istore_2
      //   39: aload 4
      //   41: astore_1
      //   42: aload_0
      //   43: monitorexit
      //   44: iload_2
      //   45: ifeq +8 -> 53
      //   48: aload_0
      //   49: aload_1
      //   50: invokevirtual 225	rx/internal/operators/OperatorMerge$MergeSubscriber:add	(Lrx/Subscription;)V
      //   53: aload_1
      //   54: areturn
      //   55: astore_3
      //   56: aload_0
      //   57: monitorexit
      //   58: aload_3
      //   59: athrow
      //   60: astore_3
      //   61: goto -5 -> 56
      //
      // Exception table:
      //   from	to	target	type
      //   13	31	55	finally
      //   42	44	55	finally
      //   56	58	55	finally
      //   31	37	60	finally
    }

    Queue<Throwable> getOrCreateErrorQueue()
    {
      Object localObject1 = this.errors;
      if (localObject1 == null)
        monitorenter;
      try
      {
        localObject1 = this.errors;
        ConcurrentLinkedQueue localConcurrentLinkedQueue;
        if (localObject1 == null)
          localConcurrentLinkedQueue = new ConcurrentLinkedQueue();
        try
        {
          this.errors = localConcurrentLinkedQueue;
          localObject1 = localConcurrentLinkedQueue;
          monitorexit;
          break label48;
          label40: monitorexit;
          Object localObject2;
          throw localObject2;
        }
        finally
        {
        }
        label48: return localObject1;
      }
      finally
      {
        break label40;
      }
    }

    public void onCompleted()
    {
      this.done = true;
      emit();
    }

    public void onError(Throwable paramThrowable)
    {
      getOrCreateErrorQueue().offer(paramThrowable);
      this.done = true;
      emit();
    }

    public void onNext(Observable<? extends T> paramObservable)
    {
      if (paramObservable == null);
      while (true)
      {
        return;
        if ((paramObservable instanceof ScalarSynchronousObservable))
        {
          tryEmit(((ScalarSynchronousObservable)paramObservable).get());
          continue;
        }
        long l = this.uniqueId;
        this.uniqueId = (1L + l);
        OperatorMerge.InnerSubscriber localInnerSubscriber = new OperatorMerge.InnerSubscriber(this, l);
        addInner(localInnerSubscriber);
        paramObservable.unsafeSubscribe(localInnerSubscriber);
        emit();
      }
    }

    protected void queueScalar(T paramT)
    {
      Object localObject = this.queue;
      int i;
      if (localObject == null)
      {
        i = this.maxConcurrent;
        if (i == 2147483647)
        {
          localObject = new SpscUnboundedAtomicArrayQueue(RxRingBuffer.SIZE);
          this.queue = ((Queue)localObject);
        }
      }
      else
      {
        if (((Queue)localObject).offer(paramT))
          break label115;
        unsubscribe();
        onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), paramT));
      }
      while (true)
      {
        return;
        if (Pow2.isPowerOfTwo(i))
        {
          if (UnsafeAccess.isUnsafeAvailable())
          {
            localObject = new SpscArrayQueue(i);
            break;
          }
          localObject = new SpscAtomicArrayQueue(i);
          break;
        }
        localObject = new SpscExactAtomicArrayQueue(i);
        break;
        label115: emit();
      }
    }

    protected void queueScalar(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber, T paramT)
    {
      RxRingBuffer localRxRingBuffer = paramInnerSubscriber.queue;
      if (localRxRingBuffer == null)
      {
        localRxRingBuffer = RxRingBuffer.getSpscInstance();
        paramInnerSubscriber.add(localRxRingBuffer);
        paramInnerSubscriber.queue = localRxRingBuffer;
      }
      try
      {
        localRxRingBuffer.onNext(this.nl.next(paramT));
        emit();
        return;
      }
      catch (MissingBackpressureException localMissingBackpressureException)
      {
        while (true)
        {
          paramInnerSubscriber.unsubscribe();
          paramInnerSubscriber.onError(localMissingBackpressureException);
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        while (true)
        {
          if (paramInnerSubscriber.isUnsubscribed())
            continue;
          paramInnerSubscriber.unsubscribe();
          paramInnerSubscriber.onError(localIllegalStateException);
        }
      }
    }

    void removeInner(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber)
    {
      RxRingBuffer localRxRingBuffer = paramInnerSubscriber.queue;
      if (localRxRingBuffer != null)
        localRxRingBuffer.release();
      this.subscriptions.remove(paramInnerSubscriber);
      while (true)
      {
        OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber1;
        int i;
        int j;
        int k;
        int m;
        OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber2;
        synchronized (this.innerGuard)
        {
          arrayOfInnerSubscriber1 = this.innerSubscribers;
          i = arrayOfInnerSubscriber1.length;
          j = -1;
          k = 0;
          if (k >= i)
            continue;
          if (paramInnerSubscriber.equals(arrayOfInnerSubscriber1[k]))
          {
            j = k;
            if (j < 0)
              continue;
            if (i != 1)
              continue;
            this.innerSubscribers = EMPTY;
          }
        }
        k++;
      }
    }

    public void requestMore(long paramLong)
    {
      request(paramLong);
    }

    void tryEmit(T paramT)
    {
      int i = 0;
      long l = this.producer.get();
      if (l != 0L)
        monitorenter;
      while (true)
      {
        try
        {
          l = this.producer.get();
          if ((this.emitting) || (l == 0L))
            continue;
          this.emitting = true;
          i = 1;
          monitorexit;
          if (i != 0)
          {
            emitScalar(paramT, l);
            return;
          }
        }
        finally
        {
          monitorexit;
        }
        queueScalar(paramT);
      }
    }

    void tryEmit(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber, T paramT)
    {
      int i = 0;
      long l = this.producer.get();
      if (l != 0L)
        monitorenter;
      while (true)
      {
        try
        {
          l = this.producer.get();
          if ((this.emitting) || (l == 0L))
            continue;
          this.emitting = true;
          i = 1;
          monitorexit;
          if (i != 0)
          {
            emitScalar(paramInnerSubscriber, paramT, l);
            return;
          }
        }
        finally
        {
          monitorexit;
        }
        queueScalar(paramInnerSubscriber, paramT);
      }
    }
  }

  static final class MergeProducer<T> extends AtomicLong
    implements Producer
  {
    private static final long serialVersionUID = -1214379189873595503L;
    final OperatorMerge.MergeSubscriber<T> subscriber;

    public MergeProducer(OperatorMerge.MergeSubscriber<T> paramMergeSubscriber)
    {
      this.subscriber = paramMergeSubscriber;
    }

    public long produced(int paramInt)
    {
      return addAndGet(-paramInt);
    }

    public void request(long paramLong)
    {
      if (paramLong > 0L)
        if (get() != 9223372036854775807L);
      do
        while (true)
        {
          return;
          BackpressureUtils.getAndAddRequest(this, paramLong);
          this.subscriber.emit();
        }
      while (paramLong >= 0L);
      throw new IllegalArgumentException("n >= 0 required");
    }
  }

  private static final class HolderDelayErrors
  {
    static final OperatorMerge<Object> INSTANCE = new OperatorMerge(true, 2147483647, null);
  }

  private static final class HolderNoDelay
  {
    static final OperatorMerge<Object> INSTANCE = new OperatorMerge(false, 2147483647, null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorMerge
 * JD-Core Version:    0.6.0
 */