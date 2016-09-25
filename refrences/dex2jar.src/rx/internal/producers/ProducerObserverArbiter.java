package rx.internal.producers;

import java.util.Iterator;
import java.util.List;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;

public final class ProducerObserverArbiter<T>
  implements Producer, Observer<T>
{
  static final Producer NULL_PRODUCER = new Producer()
  {
    public void request(long paramLong)
    {
    }
  };
  final Subscriber<? super T> child;
  Producer currentProducer;
  boolean emitting;
  volatile boolean hasError;
  Producer missedProducer;
  long missedRequested;
  Object missedTerminal;
  List<T> queue;
  long requested;

  public ProducerObserverArbiter(Subscriber<? super T> paramSubscriber)
  {
    this.child = paramSubscriber;
  }

  void emitLoop()
  {
    Subscriber localSubscriber = this.child;
    long l1 = 0L;
    Object localObject1 = null;
    while (true)
    {
      int i = 0;
      monitorenter;
      long l2;
      Producer localProducer1;
      List localList;
      label95: long l3;
      while (true)
      {
        Object localObject3;
        try
        {
          l2 = this.missedRequested;
          localProducer1 = this.missedProducer;
          localObject3 = this.missedTerminal;
          localList = this.queue;
          if ((l2 != 0L) || (localProducer1 != null) || (localList != null) || (localObject3 != null))
            continue;
          this.emitting = false;
          i = 1;
          monitorexit;
          if (i != 0)
          {
            if ((l1 == 0L) || (localObject1 == null))
              continue;
            localObject1.request(l1);
            return;
            this.missedRequested = 0L;
            this.missedProducer = null;
            this.queue = null;
            this.missedTerminal = null;
            continue;
          }
        }
        finally
        {
          monitorexit;
        }
        if ((localList == null) || (localList.isEmpty()));
        for (int j = 1; ; j = 0)
        {
          if (localObject3 == null)
            break label187;
          if (localObject3 == Boolean.TRUE)
            break label175;
          localSubscriber.onError((Throwable)localObject3);
          break;
        }
        label175: if (j != 0)
        {
          localSubscriber.onCompleted();
          continue;
        }
        label187: l3 = 0L;
        if (localList == null)
          break label272;
        Iterator localIterator = localList.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
            break label259;
          Object localObject4 = localIterator.next();
          if (localSubscriber.isUnsubscribed())
            break label95;
          if (this.hasError)
            break;
          try
          {
            localSubscriber.onNext(localObject4);
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwOrReport(localThrowable, localSubscriber, localObject4);
          }
        }
      }
      label259: l3 += localList.size();
      label272: long l4 = this.requested;
      if (l4 != 9223372036854775807L)
      {
        if (l2 != 0L)
        {
          long l6 = l4 + l2;
          if (l6 < 0L)
            l6 = 9223372036854775807L;
          l4 = l6;
        }
        if ((l3 != 0L) && (l4 != 9223372036854775807L))
        {
          long l5 = l4 - l3;
          if (l5 < 0L)
            throw new IllegalStateException("More produced than requested");
          l4 = l5;
        }
        this.requested = l4;
      }
      if (localProducer1 != null)
      {
        if (localProducer1 == NULL_PRODUCER)
        {
          this.currentProducer = null;
          continue;
        }
        this.currentProducer = localProducer1;
        if (l4 == 0L)
          continue;
        l1 = BackpressureUtils.addCap(l1, l4);
        localObject1 = localProducer1;
        continue;
      }
      Producer localProducer2 = this.currentProducer;
      if ((localProducer2 == null) || (l2 == 0L))
        continue;
      l1 = BackpressureUtils.addCap(l1, l2);
      localObject1 = localProducer2;
    }
  }

  public void onCompleted()
  {
    monitorenter;
    try
    {
      if (this.emitting)
      {
        this.missedTerminal = Boolean.valueOf(true);
        monitorexit;
      }
      else
      {
        this.emitting = true;
        monitorexit;
        this.child.onCompleted();
      }
    }
    finally
    {
      monitorexit;
    }
  }

  public void onError(Throwable paramThrowable)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (!this.emitting)
          continue;
        this.missedTerminal = paramThrowable;
        int i = 0;
        monitorexit;
        if (i != 0)
        {
          this.child.onError(paramThrowable);
          return;
          this.emitting = true;
          i = 1;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      this.hasError = true;
    }
  }

  // ERROR //
  public void onNext(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   6: ifeq +44 -> 50
    //   9: aload_0
    //   10: getfield 51	rx/internal/producers/ProducerObserverArbiter:queue	Ljava/util/List;
    //   13: astore 8
    //   15: aload 8
    //   17: ifnonnull +19 -> 36
    //   20: new 135	java/util/ArrayList
    //   23: dup
    //   24: iconst_4
    //   25: invokespecial 138	java/util/ArrayList:<init>	(I)V
    //   28: astore 8
    //   30: aload_0
    //   31: aload 8
    //   33: putfield 51	rx/internal/producers/ProducerObserverArbiter:queue	Ljava/util/List;
    //   36: aload 8
    //   38: aload_1
    //   39: invokeinterface 142 2 0
    //   44: pop
    //   45: aload_0
    //   46: monitorexit
    //   47: goto +91 -> 138
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_0
    //   53: getfield 40	rx/internal/producers/ProducerObserverArbiter:child	Lrx/Subscriber;
    //   56: aload_1
    //   57: invokevirtual 100	rx/Subscriber:onNext	(Ljava/lang/Object;)V
    //   60: aload_0
    //   61: getfield 112	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   64: lstore 5
    //   66: lload 5
    //   68: ldc2_w 113
    //   71: lcmp
    //   72: ifeq +11 -> 83
    //   75: aload_0
    //   76: lload 5
    //   78: lconst_1
    //   79: lsub
    //   80: putfield 112	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   83: aload_0
    //   84: invokevirtual 144	rx/internal/producers/ProducerObserverArbiter:emitLoop	()V
    //   87: iconst_1
    //   88: ifne +50 -> 138
    //   91: aload_0
    //   92: monitorenter
    //   93: aload_0
    //   94: iconst_0
    //   95: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   98: aload_0
    //   99: monitorexit
    //   100: goto +38 -> 138
    //   103: astore 7
    //   105: aload_0
    //   106: monitorexit
    //   107: aload 7
    //   109: athrow
    //   110: astore_2
    //   111: aload_0
    //   112: monitorexit
    //   113: aload_2
    //   114: athrow
    //   115: astore_3
    //   116: iconst_0
    //   117: ifne +12 -> 129
    //   120: aload_0
    //   121: monitorenter
    //   122: aload_0
    //   123: iconst_0
    //   124: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   127: aload_0
    //   128: monitorexit
    //   129: aload_3
    //   130: athrow
    //   131: astore 4
    //   133: aload_0
    //   134: monitorexit
    //   135: aload 4
    //   137: athrow
    //   138: return
    //
    // Exception table:
    //   from	to	target	type
    //   93	107	103	finally
    //   2	52	110	finally
    //   111	113	110	finally
    //   52	87	115	finally
    //   122	129	131	finally
    //   133	135	131	finally
  }

  // ERROR //
  public void request(long paramLong)
  {
    // Byte code:
    //   0: lload_1
    //   1: lconst_0
    //   2: lcmp
    //   3: ifge +13 -> 16
    //   6: new 146	java/lang/IllegalArgumentException
    //   9: dup
    //   10: ldc 148
    //   12: invokespecial 149	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   15: athrow
    //   16: lload_1
    //   17: lconst_0
    //   18: lcmp
    //   19: ifne +4 -> 23
    //   22: return
    //   23: aload_0
    //   24: monitorenter
    //   25: aload_0
    //   26: getfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   29: ifeq +23 -> 52
    //   32: aload_0
    //   33: lload_1
    //   34: aload_0
    //   35: getfield 45	rx/internal/producers/ProducerObserverArbiter:missedRequested	J
    //   38: ladd
    //   39: putfield 45	rx/internal/producers/ProducerObserverArbiter:missedRequested	J
    //   42: aload_0
    //   43: monitorexit
    //   44: goto -22 -> 22
    //   47: astore_3
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_3
    //   51: athrow
    //   52: aload_0
    //   53: iconst_1
    //   54: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_0
    //   60: getfield 123	rx/internal/producers/ProducerObserverArbiter:currentProducer	Lrx/Producer;
    //   63: astore 4
    //   65: lload_1
    //   66: aload_0
    //   67: getfield 112	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   70: ladd
    //   71: lstore 7
    //   73: lload 7
    //   75: lconst_0
    //   76: lcmp
    //   77: ifge +8 -> 85
    //   80: ldc2_w 113
    //   83: lstore 7
    //   85: aload_0
    //   86: lload 7
    //   88: putfield 112	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   91: aload_0
    //   92: invokevirtual 144	rx/internal/producers/ProducerObserverArbiter:emitLoop	()V
    //   95: iconst_1
    //   96: ifne +12 -> 108
    //   99: aload_0
    //   100: monitorenter
    //   101: aload_0
    //   102: iconst_0
    //   103: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   106: aload_0
    //   107: monitorexit
    //   108: aload 4
    //   110: ifnull -88 -> 22
    //   113: aload 4
    //   115: lload_1
    //   116: invokeinterface 57 3 0
    //   121: goto -99 -> 22
    //   124: astore 9
    //   126: aload_0
    //   127: monitorexit
    //   128: aload 9
    //   130: athrow
    //   131: astore 5
    //   133: iconst_0
    //   134: ifne +12 -> 146
    //   137: aload_0
    //   138: monitorenter
    //   139: aload_0
    //   140: iconst_0
    //   141: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   144: aload_0
    //   145: monitorexit
    //   146: aload 5
    //   148: athrow
    //   149: astore 6
    //   151: aload_0
    //   152: monitorexit
    //   153: aload 6
    //   155: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   25	50	47	finally
    //   52	59	47	finally
    //   101	108	124	finally
    //   126	128	124	finally
    //   65	95	131	finally
    //   139	146	149	finally
    //   151	153	149	finally
  }

  // ERROR //
  public void setProducer(Producer paramProducer)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   6: ifeq +24 -> 30
    //   9: aload_1
    //   10: ifnull +13 -> 23
    //   13: aload_0
    //   14: aload_1
    //   15: putfield 47	rx/internal/producers/ProducerObserverArbiter:missedProducer	Lrx/Producer;
    //   18: aload_0
    //   19: monitorexit
    //   20: goto +101 -> 121
    //   23: getstatic 36	rx/internal/producers/ProducerObserverArbiter:NULL_PRODUCER	Lrx/Producer;
    //   26: astore_1
    //   27: goto -14 -> 13
    //   30: aload_0
    //   31: iconst_1
    //   32: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_0
    //   38: aload_1
    //   39: putfield 123	rx/internal/producers/ProducerObserverArbiter:currentProducer	Lrx/Producer;
    //   42: aload_0
    //   43: getfield 112	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   46: lstore_3
    //   47: aload_0
    //   48: invokevirtual 144	rx/internal/producers/ProducerObserverArbiter:emitLoop	()V
    //   51: iconst_1
    //   52: ifne +12 -> 64
    //   55: aload_0
    //   56: monitorenter
    //   57: aload_0
    //   58: iconst_0
    //   59: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: ifnull +56 -> 121
    //   68: lload_3
    //   69: lconst_0
    //   70: lcmp
    //   71: ifeq +50 -> 121
    //   74: aload_1
    //   75: lload_3
    //   76: invokeinterface 57 3 0
    //   81: goto +40 -> 121
    //   84: astore_2
    //   85: aload_0
    //   86: monitorexit
    //   87: aload_2
    //   88: athrow
    //   89: astore 7
    //   91: aload_0
    //   92: monitorexit
    //   93: aload 7
    //   95: athrow
    //   96: astore 5
    //   98: iconst_0
    //   99: ifne +12 -> 111
    //   102: aload_0
    //   103: monitorenter
    //   104: aload_0
    //   105: iconst_0
    //   106: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   109: aload_0
    //   110: monitorexit
    //   111: aload 5
    //   113: athrow
    //   114: astore 6
    //   116: aload_0
    //   117: monitorexit
    //   118: aload 6
    //   120: athrow
    //   121: return
    //
    // Exception table:
    //   from	to	target	type
    //   2	37	84	finally
    //   85	87	84	finally
    //   57	64	89	finally
    //   91	93	89	finally
    //   47	51	96	finally
    //   104	111	114	finally
    //   116	118	114	finally
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.producers.ProducerObserverArbiter
 * JD-Core Version:    0.6.0
 */