package rx.internal.operators;

import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.CompositeSubscription;

public final class OnSubscribeJoin<TLeft, TRight, TLeftDuration, TRightDuration, R>
  implements Observable.OnSubscribe<R>
{
  final Observable<TLeft> left;
  final Func1<TLeft, Observable<TLeftDuration>> leftDurationSelector;
  final Func2<TLeft, TRight, R> resultSelector;
  final Observable<TRight> right;
  final Func1<TRight, Observable<TRightDuration>> rightDurationSelector;

  public OnSubscribeJoin(Observable<TLeft> paramObservable, Observable<TRight> paramObservable1, Func1<TLeft, Observable<TLeftDuration>> paramFunc1, Func1<TRight, Observable<TRightDuration>> paramFunc11, Func2<TLeft, TRight, R> paramFunc2)
  {
    this.left = paramObservable;
    this.right = paramObservable1;
    this.leftDurationSelector = paramFunc1;
    this.rightDurationSelector = paramFunc11;
    this.resultSelector = paramFunc2;
  }

  public void call(Subscriber<? super R> paramSubscriber)
  {
    new ResultSink(new SerializedSubscriber(paramSubscriber)).run();
  }

  final class ResultSink
  {
    final CompositeSubscription group;
    final Object guard = new Object();
    boolean leftDone;
    int leftId;
    final Map<Integer, TLeft> leftMap;
    boolean rightDone;
    int rightId;
    final Map<Integer, TRight> rightMap;
    final Subscriber<? super R> subscriber;

    public ResultSink()
    {
      Object localObject;
      this.subscriber = localObject;
      this.group = new CompositeSubscription();
      this.leftMap = new HashMap();
      this.rightMap = new HashMap();
    }

    public void run()
    {
      this.subscriber.add(this.group);
      LeftSubscriber localLeftSubscriber = new LeftSubscriber();
      RightSubscriber localRightSubscriber = new RightSubscriber();
      this.group.add(localLeftSubscriber);
      this.group.add(localRightSubscriber);
      OnSubscribeJoin.this.left.unsafeSubscribe(localLeftSubscriber);
      OnSubscribeJoin.this.right.unsafeSubscribe(localRightSubscriber);
    }

    final class RightSubscriber extends Subscriber<TRight>
    {
      RightSubscriber()
      {
      }

      void expire(int paramInt, Subscription paramSubscription)
      {
        int i = 0;
        while (true)
        {
          synchronized (OnSubscribeJoin.ResultSink.this.guard)
          {
            if ((OnSubscribeJoin.ResultSink.this.rightMap.remove(Integer.valueOf(paramInt)) == null) || (!OnSubscribeJoin.ResultSink.this.rightMap.isEmpty()) || (!OnSubscribeJoin.ResultSink.this.rightDone))
              continue;
            i = 1;
            if (i != 0)
            {
              OnSubscribeJoin.ResultSink.this.subscriber.onCompleted();
              OnSubscribeJoin.ResultSink.this.subscriber.unsubscribe();
              return;
            }
          }
          OnSubscribeJoin.ResultSink.this.group.remove(paramSubscription);
        }
      }

      public void onCompleted()
      {
        for (int i = 0; ; i = 1)
          while (true)
          {
            synchronized (OnSubscribeJoin.ResultSink.this.guard)
            {
              OnSubscribeJoin.ResultSink.this.rightDone = true;
              if ((OnSubscribeJoin.ResultSink.this.leftDone) || (OnSubscribeJoin.ResultSink.this.rightMap.isEmpty()))
                break;
              if (i != 0)
              {
                OnSubscribeJoin.ResultSink.this.subscriber.onCompleted();
                OnSubscribeJoin.ResultSink.this.subscriber.unsubscribe();
                return;
              }
            }
            OnSubscribeJoin.ResultSink.this.group.remove(this);
          }
      }

      public void onError(Throwable paramThrowable)
      {
        OnSubscribeJoin.ResultSink.this.subscriber.onError(paramThrowable);
        OnSubscribeJoin.ResultSink.this.subscriber.unsubscribe();
      }

      // ERROR //
      public void onNext(TRight paramTRight)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   4: getfield 26	rx/internal/operators/OnSubscribeJoin$ResultSink:guard	Ljava/lang/Object;
        //   7: astore_2
        //   8: aload_2
        //   9: monitorenter
        //   10: aload_0
        //   11: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   14: astore 4
        //   16: aload 4
        //   18: getfield 84	rx/internal/operators/OnSubscribeJoin$ResultSink:rightId	I
        //   21: istore 5
        //   23: aload 4
        //   25: iload 5
        //   27: iconst_1
        //   28: iadd
        //   29: putfield 84	rx/internal/operators/OnSubscribeJoin$ResultSink:rightId	I
        //   32: aload_0
        //   33: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   36: getfield 30	rx/internal/operators/OnSubscribeJoin$ResultSink:rightMap	Ljava/util/Map;
        //   39: iload 5
        //   41: invokestatic 36	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   44: aload_1
        //   45: invokeinterface 88 3 0
        //   50: pop
        //   51: aload_0
        //   52: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   55: getfield 91	rx/internal/operators/OnSubscribeJoin$ResultSink:leftId	I
        //   58: istore 7
        //   60: aload_2
        //   61: monitorexit
        //   62: new 93	rx/subscriptions/SerialSubscription
        //   65: dup
        //   66: invokespecial 94	rx/subscriptions/SerialSubscription:<init>	()V
        //   69: astore 8
        //   71: aload_0
        //   72: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   75: getfield 64	rx/internal/operators/OnSubscribeJoin$ResultSink:group	Lrx/subscriptions/CompositeSubscription;
        //   78: aload 8
        //   80: invokevirtual 97	rx/subscriptions/CompositeSubscription:add	(Lrx/Subscription;)V
        //   83: aload_0
        //   84: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   87: getfield 101	rx/internal/operators/OnSubscribeJoin$ResultSink:this$0	Lrx/internal/operators/OnSubscribeJoin;
        //   90: getfield 107	rx/internal/operators/OnSubscribeJoin:rightDurationSelector	Lrx/functions/Func1;
        //   93: aload_1
        //   94: invokeinterface 112 2 0
        //   99: checkcast 114	rx/Observable
        //   102: astore 10
        //   104: new 10	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber$RightDurationSubscriber
        //   107: dup
        //   108: aload_0
        //   109: iload 5
        //   111: invokespecial 117	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber$RightDurationSubscriber:<init>	(Lrx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber;I)V
        //   114: astore 11
        //   116: aload_0
        //   117: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   120: getfield 64	rx/internal/operators/OnSubscribeJoin$ResultSink:group	Lrx/subscriptions/CompositeSubscription;
        //   123: aload 11
        //   125: invokevirtual 97	rx/subscriptions/CompositeSubscription:add	(Lrx/Subscription;)V
        //   128: aload 10
        //   130: aload 11
        //   132: invokevirtual 121	rx/Observable:unsafeSubscribe	(Lrx/Subscriber;)Lrx/Subscription;
        //   135: pop
        //   136: new 123	java/util/ArrayList
        //   139: dup
        //   140: invokespecial 124	java/util/ArrayList:<init>	()V
        //   143: astore 13
        //   145: aload_0
        //   146: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   149: getfield 26	rx/internal/operators/OnSubscribeJoin$ResultSink:guard	Ljava/lang/Object;
        //   152: astore 14
        //   154: aload 14
        //   156: monitorenter
        //   157: aload_0
        //   158: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   161: getfield 127	rx/internal/operators/OnSubscribeJoin$ResultSink:leftMap	Ljava/util/Map;
        //   164: invokeinterface 131 1 0
        //   169: invokeinterface 137 1 0
        //   174: astore 16
        //   176: aload 16
        //   178: invokeinterface 142 1 0
        //   183: ifeq +73 -> 256
        //   186: aload 16
        //   188: invokeinterface 146 1 0
        //   193: checkcast 148	java/util/Map$Entry
        //   196: astore 20
        //   198: aload 20
        //   200: invokeinterface 151 1 0
        //   205: checkcast 32	java/lang/Integer
        //   208: invokevirtual 155	java/lang/Integer:intValue	()I
        //   211: iload 7
        //   213: if_icmpge -37 -> 176
        //   216: aload 13
        //   218: aload 20
        //   220: invokeinterface 158 1 0
        //   225: invokeinterface 163 2 0
        //   230: pop
        //   231: goto -55 -> 176
        //   234: astore 15
        //   236: aload 14
        //   238: monitorexit
        //   239: aload 15
        //   241: athrow
        //   242: astore 9
        //   244: aload 9
        //   246: aload_0
        //   247: invokestatic 169	rx/exceptions/Exceptions:throwOrReport	(Ljava/lang/Throwable;Lrx/Observer;)V
        //   250: return
        //   251: astore_3
        //   252: aload_2
        //   253: monitorexit
        //   254: aload_3
        //   255: athrow
        //   256: aload 14
        //   258: monitorexit
        //   259: aload 13
        //   261: invokeinterface 170 1 0
        //   266: astore 17
        //   268: aload 17
        //   270: invokeinterface 142 1 0
        //   275: ifeq -25 -> 250
        //   278: aload 17
        //   280: invokeinterface 146 1 0
        //   285: astore 18
        //   287: aload_0
        //   288: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   291: getfield 101	rx/internal/operators/OnSubscribeJoin$ResultSink:this$0	Lrx/internal/operators/OnSubscribeJoin;
        //   294: getfield 174	rx/internal/operators/OnSubscribeJoin:resultSelector	Lrx/functions/Func2;
        //   297: aload 18
        //   299: aload_1
        //   300: invokeinterface 178 3 0
        //   305: astore 19
        //   307: aload_0
        //   308: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$RightSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   311: getfield 54	rx/internal/operators/OnSubscribeJoin$ResultSink:subscriber	Lrx/Subscriber;
        //   314: aload 19
        //   316: invokevirtual 180	rx/Subscriber:onNext	(Ljava/lang/Object;)V
        //   319: goto -51 -> 268
        //
        // Exception table:
        //   from	to	target	type
        //   157	239	234	finally
        //   256	259	234	finally
        //   83	157	242	java/lang/Throwable
        //   239	242	242	java/lang/Throwable
        //   259	319	242	java/lang/Throwable
        //   10	62	251	finally
        //   252	254	251	finally
      }

      final class RightDurationSubscriber extends Subscriber<TRightDuration>
      {
        final int id;
        boolean once = true;

        public RightDurationSubscriber(int arg2)
        {
          int i;
          this.id = i;
        }

        public void onCompleted()
        {
          if (this.once)
          {
            this.once = false;
            OnSubscribeJoin.ResultSink.RightSubscriber.this.expire(this.id, this);
          }
        }

        public void onError(Throwable paramThrowable)
        {
          OnSubscribeJoin.ResultSink.RightSubscriber.this.onError(paramThrowable);
        }

        public void onNext(TRightDuration paramTRightDuration)
        {
          onCompleted();
        }
      }
    }

    final class LeftSubscriber extends Subscriber<TLeft>
    {
      LeftSubscriber()
      {
      }

      protected void expire(int paramInt, Subscription paramSubscription)
      {
        int i = 0;
        while (true)
        {
          synchronized (OnSubscribeJoin.ResultSink.this.guard)
          {
            if ((OnSubscribeJoin.ResultSink.this.leftMap.remove(Integer.valueOf(paramInt)) == null) || (!OnSubscribeJoin.ResultSink.this.leftMap.isEmpty()) || (!OnSubscribeJoin.ResultSink.this.leftDone))
              continue;
            i = 1;
            if (i != 0)
            {
              OnSubscribeJoin.ResultSink.this.subscriber.onCompleted();
              OnSubscribeJoin.ResultSink.this.subscriber.unsubscribe();
              return;
            }
          }
          OnSubscribeJoin.ResultSink.this.group.remove(paramSubscription);
        }
      }

      public void onCompleted()
      {
        for (int i = 0; ; i = 1)
          while (true)
          {
            synchronized (OnSubscribeJoin.ResultSink.this.guard)
            {
              OnSubscribeJoin.ResultSink.this.leftDone = true;
              if ((OnSubscribeJoin.ResultSink.this.rightDone) || (OnSubscribeJoin.ResultSink.this.leftMap.isEmpty()))
                break;
              if (i != 0)
              {
                OnSubscribeJoin.ResultSink.this.subscriber.onCompleted();
                OnSubscribeJoin.ResultSink.this.subscriber.unsubscribe();
                return;
              }
            }
            OnSubscribeJoin.ResultSink.this.group.remove(this);
          }
      }

      public void onError(Throwable paramThrowable)
      {
        OnSubscribeJoin.ResultSink.this.subscriber.onError(paramThrowable);
        OnSubscribeJoin.ResultSink.this.subscriber.unsubscribe();
      }

      // ERROR //
      public void onNext(TLeft paramTLeft)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   4: getfield 26	rx/internal/operators/OnSubscribeJoin$ResultSink:guard	Ljava/lang/Object;
        //   7: astore_2
        //   8: aload_2
        //   9: monitorenter
        //   10: aload_0
        //   11: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   14: astore 4
        //   16: aload 4
        //   18: getfield 84	rx/internal/operators/OnSubscribeJoin$ResultSink:leftId	I
        //   21: istore 5
        //   23: aload 4
        //   25: iload 5
        //   27: iconst_1
        //   28: iadd
        //   29: putfield 84	rx/internal/operators/OnSubscribeJoin$ResultSink:leftId	I
        //   32: aload_0
        //   33: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   36: getfield 30	rx/internal/operators/OnSubscribeJoin$ResultSink:leftMap	Ljava/util/Map;
        //   39: iload 5
        //   41: invokestatic 36	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   44: aload_1
        //   45: invokeinterface 88 3 0
        //   50: pop
        //   51: aload_0
        //   52: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   55: getfield 91	rx/internal/operators/OnSubscribeJoin$ResultSink:rightId	I
        //   58: istore 7
        //   60: aload_2
        //   61: monitorexit
        //   62: aload_0
        //   63: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   66: getfield 95	rx/internal/operators/OnSubscribeJoin$ResultSink:this$0	Lrx/internal/operators/OnSubscribeJoin;
        //   69: getfield 101	rx/internal/operators/OnSubscribeJoin:leftDurationSelector	Lrx/functions/Func1;
        //   72: aload_1
        //   73: invokeinterface 106 2 0
        //   78: checkcast 108	rx/Observable
        //   81: astore 9
        //   83: new 10	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber$LeftDurationSubscriber
        //   86: dup
        //   87: aload_0
        //   88: iload 5
        //   90: invokespecial 111	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber$LeftDurationSubscriber:<init>	(Lrx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber;I)V
        //   93: astore 10
        //   95: aload_0
        //   96: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   99: getfield 64	rx/internal/operators/OnSubscribeJoin$ResultSink:group	Lrx/subscriptions/CompositeSubscription;
        //   102: aload 10
        //   104: invokevirtual 114	rx/subscriptions/CompositeSubscription:add	(Lrx/Subscription;)V
        //   107: aload 9
        //   109: aload 10
        //   111: invokevirtual 118	rx/Observable:unsafeSubscribe	(Lrx/Subscriber;)Lrx/Subscription;
        //   114: pop
        //   115: new 120	java/util/ArrayList
        //   118: dup
        //   119: invokespecial 121	java/util/ArrayList:<init>	()V
        //   122: astore 12
        //   124: aload_0
        //   125: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   128: getfield 26	rx/internal/operators/OnSubscribeJoin$ResultSink:guard	Ljava/lang/Object;
        //   131: astore 13
        //   133: aload 13
        //   135: monitorenter
        //   136: aload_0
        //   137: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   140: getfield 124	rx/internal/operators/OnSubscribeJoin$ResultSink:rightMap	Ljava/util/Map;
        //   143: invokeinterface 128 1 0
        //   148: invokeinterface 134 1 0
        //   153: astore 15
        //   155: aload 15
        //   157: invokeinterface 139 1 0
        //   162: ifeq +73 -> 235
        //   165: aload 15
        //   167: invokeinterface 143 1 0
        //   172: checkcast 145	java/util/Map$Entry
        //   175: astore 19
        //   177: aload 19
        //   179: invokeinterface 148 1 0
        //   184: checkcast 32	java/lang/Integer
        //   187: invokevirtual 152	java/lang/Integer:intValue	()I
        //   190: iload 7
        //   192: if_icmpge -37 -> 155
        //   195: aload 12
        //   197: aload 19
        //   199: invokeinterface 155 1 0
        //   204: invokeinterface 160 2 0
        //   209: pop
        //   210: goto -55 -> 155
        //   213: astore 14
        //   215: aload 13
        //   217: monitorexit
        //   218: aload 14
        //   220: athrow
        //   221: astore 8
        //   223: aload 8
        //   225: aload_0
        //   226: invokestatic 166	rx/exceptions/Exceptions:throwOrReport	(Ljava/lang/Throwable;Lrx/Observer;)V
        //   229: return
        //   230: astore_3
        //   231: aload_2
        //   232: monitorexit
        //   233: aload_3
        //   234: athrow
        //   235: aload 13
        //   237: monitorexit
        //   238: aload 12
        //   240: invokeinterface 167 1 0
        //   245: astore 16
        //   247: aload 16
        //   249: invokeinterface 139 1 0
        //   254: ifeq -25 -> 229
        //   257: aload 16
        //   259: invokeinterface 143 1 0
        //   264: astore 17
        //   266: aload_0
        //   267: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   270: getfield 95	rx/internal/operators/OnSubscribeJoin$ResultSink:this$0	Lrx/internal/operators/OnSubscribeJoin;
        //   273: getfield 171	rx/internal/operators/OnSubscribeJoin:resultSelector	Lrx/functions/Func2;
        //   276: aload_1
        //   277: aload 17
        //   279: invokeinterface 175 3 0
        //   284: astore 18
        //   286: aload_0
        //   287: getfield 17	rx/internal/operators/OnSubscribeJoin$ResultSink$LeftSubscriber:this$1	Lrx/internal/operators/OnSubscribeJoin$ResultSink;
        //   290: getfield 54	rx/internal/operators/OnSubscribeJoin$ResultSink:subscriber	Lrx/Subscriber;
        //   293: aload 18
        //   295: invokevirtual 177	rx/Subscriber:onNext	(Ljava/lang/Object;)V
        //   298: goto -51 -> 247
        //
        // Exception table:
        //   from	to	target	type
        //   136	218	213	finally
        //   235	238	213	finally
        //   62	136	221	java/lang/Throwable
        //   218	221	221	java/lang/Throwable
        //   238	298	221	java/lang/Throwable
        //   10	62	230	finally
        //   231	233	230	finally
      }

      final class LeftDurationSubscriber extends Subscriber<TLeftDuration>
      {
        final int id;
        boolean once = true;

        public LeftDurationSubscriber(int arg2)
        {
          int i;
          this.id = i;
        }

        public void onCompleted()
        {
          if (this.once)
          {
            this.once = false;
            OnSubscribeJoin.ResultSink.LeftSubscriber.this.expire(this.id, this);
          }
        }

        public void onError(Throwable paramThrowable)
        {
          OnSubscribeJoin.ResultSink.LeftSubscriber.this.onError(paramThrowable);
        }

        public void onNext(TLeftDuration paramTLeftDuration)
        {
          onCompleted();
        }
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OnSubscribeJoin
 * JD-Core Version:    0.6.0
 */