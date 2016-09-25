package rx.observers;

import rx.Observer;
import rx.internal.operators.NotificationLite;

public class SerializedObserver<T>
  implements Observer<T>
{
  private static final int MAX_DRAIN_ITERATION = 1024;
  private final Observer<? super T> actual;
  private boolean emitting;
  private final NotificationLite<T> nl = NotificationLite.instance();
  private FastList queue;
  private volatile boolean terminated;

  public SerializedObserver(Observer<? super T> paramObserver)
  {
    this.actual = paramObserver;
  }

  // ERROR //
  public void onCompleted()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 42	rx/observers/SerializedObserver:terminated	Z
    //   4: ifeq +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield 42	rx/observers/SerializedObserver:terminated	Z
    //   14: ifeq +13 -> 27
    //   17: aload_0
    //   18: monitorexit
    //   19: goto -12 -> 7
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    //   27: aload_0
    //   28: iconst_1
    //   29: putfield 42	rx/observers/SerializedObserver:terminated	Z
    //   32: aload_0
    //   33: getfield 44	rx/observers/SerializedObserver:emitting	Z
    //   36: ifeq +41 -> 77
    //   39: aload_0
    //   40: getfield 46	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   43: astore_2
    //   44: aload_2
    //   45: ifnonnull +16 -> 61
    //   48: new 9	rx/observers/SerializedObserver$FastList
    //   51: dup
    //   52: invokespecial 47	rx/observers/SerializedObserver$FastList:<init>	()V
    //   55: astore_2
    //   56: aload_0
    //   57: aload_2
    //   58: putfield 46	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   61: aload_2
    //   62: aload_0
    //   63: getfield 37	rx/observers/SerializedObserver:nl	Lrx/internal/operators/NotificationLite;
    //   66: invokevirtual 51	rx/internal/operators/NotificationLite:completed	()Ljava/lang/Object;
    //   69: invokevirtual 55	rx/observers/SerializedObserver$FastList:add	(Ljava/lang/Object;)V
    //   72: aload_0
    //   73: monitorexit
    //   74: goto -67 -> 7
    //   77: aload_0
    //   78: iconst_1
    //   79: putfield 44	rx/observers/SerializedObserver:emitting	Z
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_0
    //   85: getfield 39	rx/observers/SerializedObserver:actual	Lrx/Observer;
    //   88: invokeinterface 57 1 0
    //   93: goto -86 -> 7
    //
    // Exception table:
    //   from	to	target	type
    //   10	25	22	finally
    //   27	84	22	finally
  }

  // ERROR //
  public void onError(java.lang.Throwable paramThrowable)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 64	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
    //   4: aload_0
    //   5: getfield 42	rx/observers/SerializedObserver:terminated	Z
    //   8: ifeq +4 -> 12
    //   11: return
    //   12: aload_0
    //   13: monitorenter
    //   14: aload_0
    //   15: getfield 42	rx/observers/SerializedObserver:terminated	Z
    //   18: ifeq +13 -> 31
    //   21: aload_0
    //   22: monitorexit
    //   23: goto -12 -> 11
    //   26: astore_2
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_2
    //   30: athrow
    //   31: aload_0
    //   32: iconst_1
    //   33: putfield 42	rx/observers/SerializedObserver:terminated	Z
    //   36: aload_0
    //   37: getfield 44	rx/observers/SerializedObserver:emitting	Z
    //   40: ifeq +42 -> 82
    //   43: aload_0
    //   44: getfield 46	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   47: astore_3
    //   48: aload_3
    //   49: ifnonnull +16 -> 65
    //   52: new 9	rx/observers/SerializedObserver$FastList
    //   55: dup
    //   56: invokespecial 47	rx/observers/SerializedObserver$FastList:<init>	()V
    //   59: astore_3
    //   60: aload_0
    //   61: aload_3
    //   62: putfield 46	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   65: aload_3
    //   66: aload_0
    //   67: getfield 37	rx/observers/SerializedObserver:nl	Lrx/internal/operators/NotificationLite;
    //   70: aload_1
    //   71: invokevirtual 68	rx/internal/operators/NotificationLite:error	(Ljava/lang/Throwable;)Ljava/lang/Object;
    //   74: invokevirtual 55	rx/observers/SerializedObserver$FastList:add	(Ljava/lang/Object;)V
    //   77: aload_0
    //   78: monitorexit
    //   79: goto -68 -> 11
    //   82: aload_0
    //   83: iconst_1
    //   84: putfield 44	rx/observers/SerializedObserver:emitting	Z
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_0
    //   90: getfield 39	rx/observers/SerializedObserver:actual	Lrx/Observer;
    //   93: aload_1
    //   94: invokeinterface 70 2 0
    //   99: goto -88 -> 11
    //
    // Exception table:
    //   from	to	target	type
    //   14	29	26	finally
    //   31	89	26	finally
  }

  // ERROR //
  public void onNext(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 42	rx/observers/SerializedObserver:terminated	Z
    //   4: ifeq +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield 42	rx/observers/SerializedObserver:terminated	Z
    //   14: ifeq +13 -> 27
    //   17: aload_0
    //   18: monitorexit
    //   19: goto -12 -> 7
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    //   27: aload_0
    //   28: getfield 44	rx/observers/SerializedObserver:emitting	Z
    //   31: ifeq +47 -> 78
    //   34: aload_0
    //   35: getfield 46	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   38: astore 12
    //   40: aload 12
    //   42: ifnonnull +18 -> 60
    //   45: new 9	rx/observers/SerializedObserver$FastList
    //   48: dup
    //   49: invokespecial 47	rx/observers/SerializedObserver$FastList:<init>	()V
    //   52: astore 12
    //   54: aload_0
    //   55: aload 12
    //   57: putfield 46	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   60: aload 12
    //   62: aload_0
    //   63: getfield 37	rx/observers/SerializedObserver:nl	Lrx/internal/operators/NotificationLite;
    //   66: aload_1
    //   67: invokevirtual 77	rx/internal/operators/NotificationLite:next	(Ljava/lang/Object;)Ljava/lang/Object;
    //   70: invokevirtual 55	rx/observers/SerializedObserver$FastList:add	(Ljava/lang/Object;)V
    //   73: aload_0
    //   74: monitorexit
    //   75: goto -68 -> 7
    //   78: aload_0
    //   79: iconst_1
    //   80: putfield 44	rx/observers/SerializedObserver:emitting	Z
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_0
    //   86: getfield 39	rx/observers/SerializedObserver:actual	Lrx/Observer;
    //   89: aload_1
    //   90: invokeinterface 79 2 0
    //   95: iconst_0
    //   96: istore 4
    //   98: iload 4
    //   100: sipush 1024
    //   103: if_icmpge -8 -> 95
    //   106: aload_0
    //   107: monitorenter
    //   108: aload_0
    //   109: getfield 46	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   112: astore 6
    //   114: aload 6
    //   116: ifnonnull +47 -> 163
    //   119: aload_0
    //   120: iconst_0
    //   121: putfield 44	rx/observers/SerializedObserver:emitting	Z
    //   124: aload_0
    //   125: monitorexit
    //   126: goto -119 -> 7
    //   129: astore 5
    //   131: aload_0
    //   132: monitorexit
    //   133: aload 5
    //   135: athrow
    //   136: astore_3
    //   137: aload_0
    //   138: iconst_1
    //   139: putfield 42	rx/observers/SerializedObserver:terminated	Z
    //   142: aload_3
    //   143: invokestatic 64	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
    //   146: aload_0
    //   147: getfield 39	rx/observers/SerializedObserver:actual	Lrx/Observer;
    //   150: aload_3
    //   151: aload_1
    //   152: invokestatic 85	rx/exceptions/OnErrorThrowable:addValueAsLastCause	(Ljava/lang/Throwable;Ljava/lang/Object;)Ljava/lang/Throwable;
    //   155: invokeinterface 70 2 0
    //   160: goto -153 -> 7
    //   163: aload_0
    //   164: aconst_null
    //   165: putfield 46	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   168: aload_0
    //   169: monitorexit
    //   170: aload 6
    //   172: getfield 89	rx/observers/SerializedObserver$FastList:array	[Ljava/lang/Object;
    //   175: astore 7
    //   177: aload 7
    //   179: arraylength
    //   180: istore 8
    //   182: iconst_0
    //   183: istore 9
    //   185: iload 9
    //   187: iload 8
    //   189: if_icmpge +15 -> 204
    //   192: aload 7
    //   194: iload 9
    //   196: aaload
    //   197: astore 10
    //   199: aload 10
    //   201: ifnonnull +9 -> 210
    //   204: iinc 4 1
    //   207: goto -109 -> 98
    //   210: aload_0
    //   211: getfield 37	rx/observers/SerializedObserver:nl	Lrx/internal/operators/NotificationLite;
    //   214: aload_0
    //   215: getfield 39	rx/observers/SerializedObserver:actual	Lrx/Observer;
    //   218: aload 10
    //   220: invokevirtual 93	rx/internal/operators/NotificationLite:accept	(Lrx/Observer;Ljava/lang/Object;)Z
    //   223: ifeq +41 -> 264
    //   226: aload_0
    //   227: iconst_1
    //   228: putfield 42	rx/observers/SerializedObserver:terminated	Z
    //   231: goto -224 -> 7
    //   234: astore 11
    //   236: aload_0
    //   237: iconst_1
    //   238: putfield 42	rx/observers/SerializedObserver:terminated	Z
    //   241: aload 11
    //   243: invokestatic 64	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
    //   246: aload_0
    //   247: getfield 39	rx/observers/SerializedObserver:actual	Lrx/Observer;
    //   250: aload 11
    //   252: aload_1
    //   253: invokestatic 85	rx/exceptions/OnErrorThrowable:addValueAsLastCause	(Ljava/lang/Throwable;Ljava/lang/Object;)Ljava/lang/Throwable;
    //   256: invokeinterface 70 2 0
    //   261: goto -254 -> 7
    //   264: iinc 9 1
    //   267: goto -82 -> 185
    //
    // Exception table:
    //   from	to	target	type
    //   10	25	22	finally
    //   27	85	22	finally
    //   108	133	129	finally
    //   163	170	129	finally
    //   85	95	136	java/lang/Throwable
    //   210	231	234	java/lang/Throwable
  }

  static final class FastList
  {
    Object[] array;
    int size;

    public void add(Object paramObject)
    {
      int i = this.size;
      Object localObject = this.array;
      if (localObject == null)
      {
        localObject = new Object[16];
        this.array = ((Object)localObject);
      }
      while (true)
      {
        localObject[i] = paramObject;
        this.size = (i + 1);
        return;
        if (i != localObject.length)
          continue;
        Object[] arrayOfObject = new Object[i + (i >> 2)];
        System.arraycopy(localObject, 0, arrayOfObject, 0, i);
        localObject = arrayOfObject;
        this.array = ((Object)localObject);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.observers.SerializedObserver
 * JD-Core Version:    0.6.0
 */