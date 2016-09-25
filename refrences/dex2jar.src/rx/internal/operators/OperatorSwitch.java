package rx.internal.operators;

import java.util.Iterator;
import java.util.List;
import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.internal.producers.ProducerArbiter;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

public final class OperatorSwitch<T>
  implements Observable.Operator<T, Observable<? extends T>>
{
  public static <T> OperatorSwitch<T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> paramSubscriber)
  {
    SwitchSubscriber localSwitchSubscriber = new SwitchSubscriber(paramSubscriber);
    paramSubscriber.add(localSwitchSubscriber);
    return localSwitchSubscriber;
  }

  private static final class InnerSubscriber<T> extends Subscriber<T>
  {
    private final ProducerArbiter arbiter;
    private final int id;
    private final OperatorSwitch.SwitchSubscriber<T> parent;

    InnerSubscriber(int paramInt, ProducerArbiter paramProducerArbiter, OperatorSwitch.SwitchSubscriber<T> paramSwitchSubscriber)
    {
      this.id = paramInt;
      this.arbiter = paramProducerArbiter;
      this.parent = paramSwitchSubscriber;
    }

    public void onCompleted()
    {
      this.parent.complete(this.id);
    }

    public void onError(Throwable paramThrowable)
    {
      this.parent.error(paramThrowable, this.id);
    }

    public void onNext(T paramT)
    {
      this.parent.emit(paramT, this.id, this);
    }

    public void setProducer(Producer paramProducer)
    {
      this.arbiter.setProducer(paramProducer);
    }
  }

  private static final class SwitchSubscriber<T> extends Subscriber<Observable<? extends T>>
  {
    boolean active;
    final ProducerArbiter arbiter;
    OperatorSwitch.InnerSubscriber<T> currentSubscriber;
    boolean emitting;
    final Object guard = new Object();
    int index;
    boolean mainDone;
    final NotificationLite<?> nl = NotificationLite.instance();
    List<Object> queue;
    final SerializedSubscriber<T> serializedChild;
    final SerialSubscription ssub;

    SwitchSubscriber(Subscriber<? super T> paramSubscriber)
    {
      this.serializedChild = new SerializedSubscriber(paramSubscriber);
      this.arbiter = new ProducerArbiter();
      this.ssub = new SerialSubscription();
      paramSubscriber.add(this.ssub);
      paramSubscriber.setProducer(new Producer()
      {
        public void request(long paramLong)
        {
          if (paramLong > 0L)
            OperatorSwitch.SwitchSubscriber.this.arbiter.request(paramLong);
        }
      });
    }

    // ERROR //
    void complete(int paramInt)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 44	rx/internal/operators/OperatorSwitch$SwitchSubscriber:guard	Ljava/lang/Object;
      //   4: astore_2
      //   5: aload_2
      //   6: monitorenter
      //   7: iload_1
      //   8: aload_0
      //   9: getfield 83	rx/internal/operators/OperatorSwitch$SwitchSubscriber:index	I
      //   12: if_icmpeq +8 -> 20
      //   15: aload_2
      //   16: monitorexit
      //   17: goto +107 -> 124
      //   20: aload_0
      //   21: iconst_0
      //   22: putfield 85	rx/internal/operators/OperatorSwitch$SwitchSubscriber:active	Z
      //   25: aload_0
      //   26: getfield 87	rx/internal/operators/OperatorSwitch$SwitchSubscriber:mainDone	Z
      //   29: ifne +13 -> 42
      //   32: aload_2
      //   33: monitorexit
      //   34: goto +90 -> 124
      //   37: astore_3
      //   38: aload_2
      //   39: monitorexit
      //   40: aload_3
      //   41: athrow
      //   42: aload_0
      //   43: getfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   46: ifeq +43 -> 89
      //   49: aload_0
      //   50: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   53: ifnonnull +14 -> 67
      //   56: aload_0
      //   57: new 93	java/util/ArrayList
      //   60: dup
      //   61: invokespecial 94	java/util/ArrayList:<init>	()V
      //   64: putfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   67: aload_0
      //   68: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   71: aload_0
      //   72: getfield 52	rx/internal/operators/OperatorSwitch$SwitchSubscriber:nl	Lrx/internal/operators/NotificationLite;
      //   75: invokevirtual 98	rx/internal/operators/NotificationLite:completed	()Ljava/lang/Object;
      //   78: invokeinterface 103 2 0
      //   83: pop
      //   84: aload_2
      //   85: monitorexit
      //   86: goto +38 -> 124
      //   89: aload_0
      //   90: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   93: astore 4
      //   95: aload_0
      //   96: aconst_null
      //   97: putfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   100: aload_0
      //   101: iconst_1
      //   102: putfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   105: aload_2
      //   106: monitorexit
      //   107: aload_0
      //   108: aload 4
      //   110: invokevirtual 107	rx/internal/operators/OperatorSwitch$SwitchSubscriber:drain	(Ljava/util/List;)V
      //   113: aload_0
      //   114: getfield 58	rx/internal/operators/OperatorSwitch$SwitchSubscriber:serializedChild	Lrx/observers/SerializedSubscriber;
      //   117: invokevirtual 110	rx/observers/SerializedSubscriber:onCompleted	()V
      //   120: aload_0
      //   121: invokevirtual 113	rx/internal/operators/OperatorSwitch$SwitchSubscriber:unsubscribe	()V
      //   124: return
      //
      // Exception table:
      //   from	to	target	type
      //   7	40	37	finally
      //   42	107	37	finally
    }

    void drain(List<Object> paramList)
    {
      if (paramList == null);
      label95: 
      while (true)
      {
        return;
        Iterator localIterator = paramList.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
            break label95;
          Object localObject = localIterator.next();
          if (this.nl.isCompleted(localObject))
          {
            this.serializedChild.onCompleted();
            break;
          }
          if (this.nl.isError(localObject))
          {
            this.serializedChild.onError(this.nl.getError(localObject));
            break;
          }
          this.serializedChild.onNext(localObject);
          this.arbiter.produced(1L);
        }
      }
    }

    // ERROR //
    void emit(T paramT, int paramInt, OperatorSwitch.InnerSubscriber<T> paramInnerSubscriber)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 44	rx/internal/operators/OperatorSwitch$SwitchSubscriber:guard	Ljava/lang/Object;
      //   4: astore 4
      //   6: aload 4
      //   8: monitorenter
      //   9: iload_2
      //   10: aload_0
      //   11: getfield 83	rx/internal/operators/OperatorSwitch$SwitchSubscriber:index	I
      //   14: if_icmpeq +9 -> 23
      //   17: aload 4
      //   19: monitorexit
      //   20: goto +240 -> 260
      //   23: aload_0
      //   24: getfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   27: ifeq +46 -> 73
      //   30: aload_0
      //   31: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   34: ifnonnull +14 -> 48
      //   37: aload_0
      //   38: new 93	java/util/ArrayList
      //   41: dup
      //   42: invokespecial 94	java/util/ArrayList:<init>	()V
      //   45: putfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   48: aload_0
      //   49: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   52: aload_1
      //   53: invokeinterface 103 2 0
      //   58: pop
      //   59: aload 4
      //   61: monitorexit
      //   62: goto +198 -> 260
      //   65: astore 5
      //   67: aload 4
      //   69: monitorexit
      //   70: aload 5
      //   72: athrow
      //   73: aload_0
      //   74: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   77: astore 6
      //   79: aload_0
      //   80: aconst_null
      //   81: putfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   84: aload_0
      //   85: iconst_1
      //   86: putfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   89: aload 4
      //   91: monitorexit
      //   92: iconst_1
      //   93: istore 7
      //   95: iconst_0
      //   96: istore 8
      //   98: aload_0
      //   99: aload 6
      //   101: invokevirtual 107	rx/internal/operators/OperatorSwitch$SwitchSubscriber:drain	(Ljava/util/List;)V
      //   104: iload 7
      //   106: ifeq +22 -> 128
      //   109: iconst_0
      //   110: istore 7
      //   112: aload_0
      //   113: getfield 58	rx/internal/operators/OperatorSwitch$SwitchSubscriber:serializedChild	Lrx/observers/SerializedSubscriber;
      //   116: aload_1
      //   117: invokevirtual 144	rx/observers/SerializedSubscriber:onNext	(Ljava/lang/Object;)V
      //   120: aload_0
      //   121: getfield 63	rx/internal/operators/OperatorSwitch$SwitchSubscriber:arbiter	Lrx/internal/producers/ProducerArbiter;
      //   124: lconst_1
      //   125: invokevirtual 148	rx/internal/producers/ProducerArbiter:produced	(J)V
      //   128: aload_0
      //   129: getfield 44	rx/internal/operators/OperatorSwitch$SwitchSubscriber:guard	Ljava/lang/Object;
      //   132: astore 12
      //   134: aload 12
      //   136: monitorenter
      //   137: aload_0
      //   138: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   141: astore 6
      //   143: aload_0
      //   144: aconst_null
      //   145: putfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   148: aload 6
      //   150: ifnonnull +47 -> 197
      //   153: aload_0
      //   154: iconst_0
      //   155: putfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   158: iconst_1
      //   159: istore 8
      //   161: aload 12
      //   163: monitorexit
      //   164: iload 8
      //   166: ifne +94 -> 260
      //   169: aload_0
      //   170: getfield 44	rx/internal/operators/OperatorSwitch$SwitchSubscriber:guard	Ljava/lang/Object;
      //   173: astore 15
      //   175: aload 15
      //   177: monitorenter
      //   178: aload_0
      //   179: iconst_0
      //   180: putfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   183: aload 15
      //   185: monitorexit
      //   186: goto +74 -> 260
      //   189: astore 16
      //   191: aload 15
      //   193: monitorexit
      //   194: aload 16
      //   196: athrow
      //   197: aload 12
      //   199: monitorexit
      //   200: aload_0
      //   201: getfield 58	rx/internal/operators/OperatorSwitch$SwitchSubscriber:serializedChild	Lrx/observers/SerializedSubscriber;
      //   204: invokevirtual 153	rx/observers/SerializedSubscriber:isUnsubscribed	()Z
      //   207: istore 14
      //   209: iload 14
      //   211: ifeq -113 -> 98
      //   214: goto -50 -> 164
      //   217: astore 13
      //   219: aload 12
      //   221: monitorexit
      //   222: aload 13
      //   224: athrow
      //   225: astore 9
      //   227: iload 8
      //   229: ifne +20 -> 249
      //   232: aload_0
      //   233: getfield 44	rx/internal/operators/OperatorSwitch$SwitchSubscriber:guard	Ljava/lang/Object;
      //   236: astore 10
      //   238: aload 10
      //   240: monitorenter
      //   241: aload_0
      //   242: iconst_0
      //   243: putfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   246: aload 10
      //   248: monitorexit
      //   249: aload 9
      //   251: athrow
      //   252: astore 11
      //   254: aload 10
      //   256: monitorexit
      //   257: aload 11
      //   259: athrow
      //   260: return
      //
      // Exception table:
      //   from	to	target	type
      //   9	70	65	finally
      //   73	92	65	finally
      //   178	194	189	finally
      //   137	164	217	finally
      //   197	200	217	finally
      //   219	222	217	finally
      //   98	137	225	finally
      //   200	209	225	finally
      //   222	225	225	finally
      //   241	249	252	finally
      //   254	257	252	finally
    }

    // ERROR //
    void error(Throwable paramThrowable, int paramInt)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 44	rx/internal/operators/OperatorSwitch$SwitchSubscriber:guard	Ljava/lang/Object;
      //   4: astore_3
      //   5: aload_3
      //   6: monitorenter
      //   7: iload_2
      //   8: aload_0
      //   9: getfield 83	rx/internal/operators/OperatorSwitch$SwitchSubscriber:index	I
      //   12: if_icmpeq +8 -> 20
      //   15: aload_3
      //   16: monitorexit
      //   17: goto +94 -> 111
      //   20: aload_0
      //   21: getfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   24: ifeq +51 -> 75
      //   27: aload_0
      //   28: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   31: ifnonnull +14 -> 45
      //   34: aload_0
      //   35: new 93	java/util/ArrayList
      //   38: dup
      //   39: invokespecial 94	java/util/ArrayList:<init>	()V
      //   42: putfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   45: aload_0
      //   46: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   49: aload_0
      //   50: getfield 52	rx/internal/operators/OperatorSwitch$SwitchSubscriber:nl	Lrx/internal/operators/NotificationLite;
      //   53: aload_1
      //   54: invokevirtual 158	rx/internal/operators/NotificationLite:error	(Ljava/lang/Throwable;)Ljava/lang/Object;
      //   57: invokeinterface 103 2 0
      //   62: pop
      //   63: aload_3
      //   64: monitorexit
      //   65: goto +46 -> 111
      //   68: astore 4
      //   70: aload_3
      //   71: monitorexit
      //   72: aload 4
      //   74: athrow
      //   75: aload_0
      //   76: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   79: astore 5
      //   81: aload_0
      //   82: aconst_null
      //   83: putfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   86: aload_0
      //   87: iconst_1
      //   88: putfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   91: aload_3
      //   92: monitorexit
      //   93: aload_0
      //   94: aload 5
      //   96: invokevirtual 107	rx/internal/operators/OperatorSwitch$SwitchSubscriber:drain	(Ljava/util/List;)V
      //   99: aload_0
      //   100: getfield 58	rx/internal/operators/OperatorSwitch$SwitchSubscriber:serializedChild	Lrx/observers/SerializedSubscriber;
      //   103: aload_1
      //   104: invokevirtual 140	rx/observers/SerializedSubscriber:onError	(Ljava/lang/Throwable;)V
      //   107: aload_0
      //   108: invokevirtual 113	rx/internal/operators/OperatorSwitch$SwitchSubscriber:unsubscribe	()V
      //   111: return
      //
      // Exception table:
      //   from	to	target	type
      //   7	72	68	finally
      //   75	93	68	finally
    }

    // ERROR //
    public void onCompleted()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 44	rx/internal/operators/OperatorSwitch$SwitchSubscriber:guard	Ljava/lang/Object;
      //   4: astore_1
      //   5: aload_1
      //   6: monitorenter
      //   7: aload_0
      //   8: iconst_1
      //   9: putfield 87	rx/internal/operators/OperatorSwitch$SwitchSubscriber:mainDone	Z
      //   12: aload_0
      //   13: getfield 85	rx/internal/operators/OperatorSwitch$SwitchSubscriber:active	Z
      //   16: ifeq +8 -> 24
      //   19: aload_1
      //   20: monitorexit
      //   21: goto +88 -> 109
      //   24: aload_0
      //   25: getfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   28: ifeq +48 -> 76
      //   31: aload_0
      //   32: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   35: ifnonnull +14 -> 49
      //   38: aload_0
      //   39: new 93	java/util/ArrayList
      //   42: dup
      //   43: invokespecial 94	java/util/ArrayList:<init>	()V
      //   46: putfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   49: aload_0
      //   50: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   53: aload_0
      //   54: getfield 52	rx/internal/operators/OperatorSwitch$SwitchSubscriber:nl	Lrx/internal/operators/NotificationLite;
      //   57: invokevirtual 98	rx/internal/operators/NotificationLite:completed	()Ljava/lang/Object;
      //   60: invokeinterface 103 2 0
      //   65: pop
      //   66: aload_1
      //   67: monitorexit
      //   68: goto +41 -> 109
      //   71: astore_2
      //   72: aload_1
      //   73: monitorexit
      //   74: aload_2
      //   75: athrow
      //   76: aload_0
      //   77: getfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   80: astore_3
      //   81: aload_0
      //   82: aconst_null
      //   83: putfield 91	rx/internal/operators/OperatorSwitch$SwitchSubscriber:queue	Ljava/util/List;
      //   86: aload_0
      //   87: iconst_1
      //   88: putfield 89	rx/internal/operators/OperatorSwitch$SwitchSubscriber:emitting	Z
      //   91: aload_1
      //   92: monitorexit
      //   93: aload_0
      //   94: aload_3
      //   95: invokevirtual 107	rx/internal/operators/OperatorSwitch$SwitchSubscriber:drain	(Ljava/util/List;)V
      //   98: aload_0
      //   99: getfield 58	rx/internal/operators/OperatorSwitch$SwitchSubscriber:serializedChild	Lrx/observers/SerializedSubscriber;
      //   102: invokevirtual 110	rx/observers/SerializedSubscriber:onCompleted	()V
      //   105: aload_0
      //   106: invokevirtual 113	rx/internal/operators/OperatorSwitch$SwitchSubscriber:unsubscribe	()V
      //   109: return
      //
      // Exception table:
      //   from	to	target	type
      //   7	74	71	finally
      //   76	93	71	finally
    }

    public void onError(Throwable paramThrowable)
    {
      this.serializedChild.onError(paramThrowable);
      unsubscribe();
    }

    public void onNext(Observable<? extends T> paramObservable)
    {
      synchronized (this.guard)
      {
        int i = 1 + this.index;
        this.index = i;
        this.active = true;
        this.currentSubscriber = new OperatorSwitch.InnerSubscriber(i, this.arbiter, this);
        this.ssub.set(this.currentSubscriber);
        paramObservable.unsafeSubscribe(this.currentSubscriber);
        return;
      }
    }
  }

  private static final class Holder
  {
    static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSwitch
 * JD-Core Version:    0.6.0
 */