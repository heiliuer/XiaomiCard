package rx.internal.util;

import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.annotations.Experimental;

@Experimental
public final class BackpressureDrainManager extends AtomicLong
  implements Producer
{
  protected final BackpressureQueueCallback actual;
  protected boolean emitting;
  protected Throwable exception;
  protected volatile boolean terminated;

  public BackpressureDrainManager(BackpressureQueueCallback paramBackpressureQueueCallback)
  {
    this.actual = paramBackpressureQueueCallback;
  }

  // ERROR //
  public final void drain()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 27	rx/internal/util/BackpressureDrainManager:emitting	Z
    //   6: ifeq +8 -> 14
    //   9: aload_0
    //   10: monitorexit
    //   11: goto +341 -> 352
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 27	rx/internal/util/BackpressureDrainManager:emitting	Z
    //   19: aload_0
    //   20: getfield 29	rx/internal/util/BackpressureDrainManager:terminated	Z
    //   23: istore_2
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_0
    //   27: invokevirtual 33	rx/internal/util/BackpressureDrainManager:get	()J
    //   30: lstore_3
    //   31: iconst_0
    //   32: istore 5
    //   34: aload_0
    //   35: getfield 24	rx/internal/util/BackpressureDrainManager:actual	Lrx/internal/util/BackpressureDrainManager$BackpressureQueueCallback;
    //   38: astore 8
    //   40: goto +313 -> 353
    //   43: iload_2
    //   44: ifeq +136 -> 180
    //   47: aload 8
    //   49: invokeinterface 37 1 0
    //   54: ifnonnull +46 -> 100
    //   57: iconst_1
    //   58: istore 5
    //   60: aload 8
    //   62: aload_0
    //   63: getfield 39	rx/internal/util/BackpressureDrainManager:exception	Ljava/lang/Throwable;
    //   66: invokeinterface 43 2 0
    //   71: iload 5
    //   73: ifne +279 -> 352
    //   76: aload_0
    //   77: monitorenter
    //   78: aload_0
    //   79: iconst_0
    //   80: putfield 27	rx/internal/util/BackpressureDrainManager:emitting	Z
    //   83: aload_0
    //   84: monitorexit
    //   85: goto +267 -> 352
    //   88: astore 19
    //   90: aload_0
    //   91: monitorexit
    //   92: aload 19
    //   94: athrow
    //   95: astore_1
    //   96: aload_0
    //   97: monitorexit
    //   98: aload_1
    //   99: athrow
    //   100: lload_3
    //   101: lconst_0
    //   102: lcmp
    //   103: ifne +77 -> 180
    //   106: aload_0
    //   107: monitorenter
    //   108: aload_0
    //   109: getfield 29	rx/internal/util/BackpressureDrainManager:terminated	Z
    //   112: istore_2
    //   113: aload 8
    //   115: invokeinterface 37 1 0
    //   120: ifnull +123 -> 243
    //   123: iconst_1
    //   124: istore 14
    //   126: aload_0
    //   127: invokevirtual 33	rx/internal/util/BackpressureDrainManager:get	()J
    //   130: ldc2_w 44
    //   133: lcmp
    //   134: ifne +150 -> 284
    //   137: iload 14
    //   139: ifne +110 -> 249
    //   142: iload_2
    //   143: ifne +106 -> 249
    //   146: iconst_1
    //   147: istore 5
    //   149: aload_0
    //   150: iconst_0
    //   151: putfield 27	rx/internal/util/BackpressureDrainManager:emitting	Z
    //   154: aload_0
    //   155: monitorexit
    //   156: iload 5
    //   158: ifne +194 -> 352
    //   161: aload_0
    //   162: monitorenter
    //   163: aload_0
    //   164: iconst_0
    //   165: putfield 27	rx/internal/util/BackpressureDrainManager:emitting	Z
    //   168: aload_0
    //   169: monitorexit
    //   170: goto +182 -> 352
    //   173: astore 18
    //   175: aload_0
    //   176: monitorexit
    //   177: aload 18
    //   179: athrow
    //   180: aload 8
    //   182: invokeinterface 48 1 0
    //   187: astore 10
    //   189: aload 10
    //   191: ifnull -85 -> 106
    //   194: aload 8
    //   196: aload 10
    //   198: invokeinterface 52 2 0
    //   203: istore 11
    //   205: iload 11
    //   207: ifeq +26 -> 233
    //   210: iconst_1
    //   211: ifne +141 -> 352
    //   214: aload_0
    //   215: monitorenter
    //   216: aload_0
    //   217: iconst_0
    //   218: putfield 27	rx/internal/util/BackpressureDrainManager:emitting	Z
    //   221: aload_0
    //   222: monitorexit
    //   223: goto +129 -> 352
    //   226: astore 12
    //   228: aload_0
    //   229: monitorexit
    //   230: aload 12
    //   232: athrow
    //   233: lload_3
    //   234: lconst_1
    //   235: lsub
    //   236: lstore_3
    //   237: iinc 9 1
    //   240: goto +116 -> 356
    //   243: iconst_0
    //   244: istore 14
    //   246: goto -120 -> 126
    //   249: ldc2_w 44
    //   252: lstore_3
    //   253: aload_0
    //   254: monitorexit
    //   255: goto +98 -> 353
    //   258: astore 13
    //   260: aload_0
    //   261: monitorexit
    //   262: aload 13
    //   264: athrow
    //   265: astore 6
    //   267: iload 5
    //   269: ifne +12 -> 281
    //   272: aload_0
    //   273: monitorenter
    //   274: aload_0
    //   275: iconst_0
    //   276: putfield 27	rx/internal/util/BackpressureDrainManager:emitting	Z
    //   279: aload_0
    //   280: monitorexit
    //   281: aload 6
    //   283: athrow
    //   284: iload 9
    //   286: ineg
    //   287: i2l
    //   288: lstore 15
    //   290: aload_0
    //   291: lload 15
    //   293: invokevirtual 56	rx/internal/util/BackpressureDrainManager:addAndGet	(J)J
    //   296: lstore_3
    //   297: lload_3
    //   298: lconst_0
    //   299: lcmp
    //   300: ifeq +69 -> 369
    //   303: iload 14
    //   305: ifne -52 -> 253
    //   308: goto +61 -> 369
    //   311: iconst_1
    //   312: istore 5
    //   314: aload_0
    //   315: iconst_0
    //   316: putfield 27	rx/internal/util/BackpressureDrainManager:emitting	Z
    //   319: aload_0
    //   320: monitorexit
    //   321: iload 5
    //   323: ifne +29 -> 352
    //   326: aload_0
    //   327: monitorenter
    //   328: aload_0
    //   329: iconst_0
    //   330: putfield 27	rx/internal/util/BackpressureDrainManager:emitting	Z
    //   333: aload_0
    //   334: monitorexit
    //   335: goto +17 -> 352
    //   338: astore 17
    //   340: aload_0
    //   341: monitorexit
    //   342: aload 17
    //   344: athrow
    //   345: astore 7
    //   347: aload_0
    //   348: monitorexit
    //   349: aload 7
    //   351: athrow
    //   352: return
    //   353: iconst_0
    //   354: istore 9
    //   356: lload_3
    //   357: lconst_0
    //   358: lcmp
    //   359: ifgt -316 -> 43
    //   362: iload_2
    //   363: ifeq -257 -> 106
    //   366: goto -323 -> 43
    //   369: iload_2
    //   370: ifeq -59 -> 311
    //   373: iload 14
    //   375: ifeq -122 -> 253
    //   378: goto -67 -> 311
    //
    // Exception table:
    //   from	to	target	type
    //   78	92	88	finally
    //   2	26	95	finally
    //   96	98	95	finally
    //   163	177	173	finally
    //   216	230	226	finally
    //   108	156	258	finally
    //   253	262	258	finally
    //   290	321	258	finally
    //   34	71	265	finally
    //   106	108	265	finally
    //   180	205	265	finally
    //   262	265	265	finally
    //   328	342	338	finally
    //   274	281	345	finally
    //   347	349	345	finally
  }

  public final boolean isTerminated()
  {
    return this.terminated;
  }

  public final void request(long paramLong)
  {
    if (paramLong == 0L)
      return;
    label29: label47: label72: label98: 
    while (true)
    {
      long l1 = get();
      if (l1 == 0L);
      for (int i = 1; ; i = 0)
      {
        if (l1 != 9223372036854775807L)
          break label47;
        if (i == 0)
          break label72;
        drain();
        break;
      }
      long l2;
      if (paramLong == 9223372036854775807L)
      {
        l2 = paramLong;
        i = 1;
      }
      while (true)
      {
        if (!compareAndSet(l1, l2))
          break label98;
        break label29;
        break;
        if (l1 > 9223372036854775807L - paramLong)
        {
          l2 = 9223372036854775807L;
          continue;
        }
        l2 = l1 + paramLong;
      }
    }
  }

  public final void terminate()
  {
    this.terminated = true;
  }

  public final void terminate(Throwable paramThrowable)
  {
    if (!this.terminated)
    {
      this.exception = paramThrowable;
      this.terminated = true;
    }
  }

  public final void terminateAndDrain()
  {
    this.terminated = true;
    drain();
  }

  public final void terminateAndDrain(Throwable paramThrowable)
  {
    if (!this.terminated)
    {
      this.exception = paramThrowable;
      this.terminated = true;
      drain();
    }
  }

  public static abstract interface BackpressureQueueCallback
  {
    public abstract boolean accept(Object paramObject);

    public abstract void complete(Throwable paramThrowable);

    public abstract Object peek();

    public abstract Object poll();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.BackpressureDrainManager
 * JD-Core Version:    0.6.0
 */