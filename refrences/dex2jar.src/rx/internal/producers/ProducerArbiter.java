package rx.internal.producers;

import rx.Producer;

public final class ProducerArbiter
  implements Producer
{
  static final Producer NULL_PRODUCER = new Producer()
  {
    public void request(long paramLong)
    {
    }
  };
  Producer currentProducer;
  boolean emitting;
  long missedProduced;
  Producer missedProducer;
  long missedRequested;
  long requested;

  public void emitLoop()
  {
    while (true)
    {
      monitorenter;
      long l1;
      Producer localProducer1;
      long l3;
      while (true)
      {
        long l2;
        long l4;
        try
        {
          l1 = this.missedRequested;
          l2 = this.missedProduced;
          localProducer1 = this.missedProducer;
          if ((l1 != 0L) || (l2 != 0L) || (localProducer1 != null))
            continue;
          this.emitting = false;
          return;
          this.missedRequested = 0L;
          this.missedProduced = 0L;
          this.missedProducer = null;
          monitorexit;
          l3 = this.requested;
          if (l3 == 9223372036854775807L)
            continue;
          l4 = l3 + l1;
          if ((l4 < 0L) || (l4 == 9223372036854775807L))
          {
            l3 = 9223372036854775807L;
            this.requested = l3;
            if (localProducer1 == null)
              break label191;
            if (localProducer1 != NULL_PRODUCER)
              break label173;
            this.currentProducer = null;
            break;
          }
        }
        finally
        {
          monitorexit;
        }
        long l5 = l4 - l2;
        if (l5 < 0L)
          throw new IllegalStateException("more produced than requested");
        l3 = l5;
        this.requested = l5;
      }
      label173: this.currentProducer = localProducer1;
      localProducer1.request(l3);
      continue;
      label191: Producer localProducer2 = this.currentProducer;
      if ((localProducer2 == null) || (l1 == 0L))
        continue;
      localProducer2.request(l1);
    }
  }

  // ERROR //
  public void produced(long paramLong)
  {
    // Byte code:
    //   0: lload_1
    //   1: lconst_0
    //   2: lcmp
    //   3: ifgt +13 -> 16
    //   6: new 55	java/lang/IllegalArgumentException
    //   9: dup
    //   10: ldc 57
    //   12: invokespecial 58	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   15: athrow
    //   16: aload_0
    //   17: monitorenter
    //   18: aload_0
    //   19: getfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   22: ifeq +18 -> 40
    //   25: aload_0
    //   26: lload_1
    //   27: aload_0
    //   28: getfield 31	rx/internal/producers/ProducerArbiter:missedProduced	J
    //   31: ladd
    //   32: putfield 31	rx/internal/producers/ProducerArbiter:missedProduced	J
    //   35: aload_0
    //   36: monitorexit
    //   37: goto +111 -> 148
    //   40: aload_0
    //   41: iconst_1
    //   42: putfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_0
    //   48: getfield 37	rx/internal/producers/ProducerArbiter:requested	J
    //   51: lstore 6
    //   53: lload 6
    //   55: ldc2_w 38
    //   58: lcmp
    //   59: ifeq +55 -> 114
    //   62: lload 6
    //   64: lload_1
    //   65: lsub
    //   66: lstore 9
    //   68: lload 9
    //   70: lconst_0
    //   71: lcmp
    //   72: ifge +36 -> 108
    //   75: new 43	java/lang/IllegalStateException
    //   78: dup
    //   79: ldc 60
    //   81: invokespecial 48	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   84: athrow
    //   85: astore 4
    //   87: iconst_0
    //   88: ifne +12 -> 100
    //   91: aload_0
    //   92: monitorenter
    //   93: aload_0
    //   94: iconst_0
    //   95: putfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   98: aload_0
    //   99: monitorexit
    //   100: aload 4
    //   102: athrow
    //   103: astore_3
    //   104: aload_0
    //   105: monitorexit
    //   106: aload_3
    //   107: athrow
    //   108: aload_0
    //   109: lload 9
    //   111: putfield 37	rx/internal/producers/ProducerArbiter:requested	J
    //   114: aload_0
    //   115: invokevirtual 62	rx/internal/producers/ProducerArbiter:emitLoop	()V
    //   118: iconst_1
    //   119: ifne +29 -> 148
    //   122: aload_0
    //   123: monitorenter
    //   124: aload_0
    //   125: iconst_0
    //   126: putfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   129: aload_0
    //   130: monitorexit
    //   131: goto +17 -> 148
    //   134: astore 8
    //   136: aload_0
    //   137: monitorexit
    //   138: aload 8
    //   140: athrow
    //   141: astore 5
    //   143: aload_0
    //   144: monitorexit
    //   145: aload 5
    //   147: athrow
    //   148: return
    //
    // Exception table:
    //   from	to	target	type
    //   47	85	85	finally
    //   108	118	85	finally
    //   18	47	103	finally
    //   104	106	103	finally
    //   124	138	134	finally
    //   93	100	141	finally
    //   143	145	141	finally
  }

  // ERROR //
  public void request(long paramLong)
  {
    // Byte code:
    //   0: lload_1
    //   1: lconst_0
    //   2: lcmp
    //   3: ifge +13 -> 16
    //   6: new 55	java/lang/IllegalArgumentException
    //   9: dup
    //   10: ldc 64
    //   12: invokespecial 58	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   15: athrow
    //   16: lload_1
    //   17: lconst_0
    //   18: lcmp
    //   19: ifne +4 -> 23
    //   22: return
    //   23: aload_0
    //   24: monitorenter
    //   25: aload_0
    //   26: getfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   29: ifeq +23 -> 52
    //   32: aload_0
    //   33: lload_1
    //   34: aload_0
    //   35: getfield 29	rx/internal/producers/ProducerArbiter:missedRequested	J
    //   38: ladd
    //   39: putfield 29	rx/internal/producers/ProducerArbiter:missedRequested	J
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
    //   54: putfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   57: aload_0
    //   58: monitorexit
    //   59: lload_1
    //   60: aload_0
    //   61: getfield 37	rx/internal/producers/ProducerArbiter:requested	J
    //   64: ladd
    //   65: lstore 6
    //   67: lload 6
    //   69: lconst_0
    //   70: lcmp
    //   71: ifge +8 -> 79
    //   74: ldc2_w 38
    //   77: lstore 6
    //   79: aload_0
    //   80: lload 6
    //   82: putfield 37	rx/internal/producers/ProducerArbiter:requested	J
    //   85: aload_0
    //   86: getfield 41	rx/internal/producers/ProducerArbiter:currentProducer	Lrx/Producer;
    //   89: astore 8
    //   91: aload 8
    //   93: ifnull +11 -> 104
    //   96: aload 8
    //   98: lload_1
    //   99: invokeinterface 52 3 0
    //   104: aload_0
    //   105: invokevirtual 62	rx/internal/producers/ProducerArbiter:emitLoop	()V
    //   108: iconst_1
    //   109: ifne -87 -> 22
    //   112: aload_0
    //   113: monitorenter
    //   114: aload_0
    //   115: iconst_0
    //   116: putfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   119: aload_0
    //   120: monitorexit
    //   121: goto -99 -> 22
    //   124: astore 9
    //   126: aload_0
    //   127: monitorexit
    //   128: aload 9
    //   130: athrow
    //   131: astore 4
    //   133: iconst_0
    //   134: ifne +12 -> 146
    //   137: aload_0
    //   138: monitorenter
    //   139: aload_0
    //   140: iconst_0
    //   141: putfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   144: aload_0
    //   145: monitorexit
    //   146: aload 4
    //   148: athrow
    //   149: astore 5
    //   151: aload_0
    //   152: monitorexit
    //   153: aload 5
    //   155: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   25	50	47	finally
    //   52	59	47	finally
    //   114	128	124	finally
    //   59	108	131	finally
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
    //   3: getfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   6: ifeq +21 -> 27
    //   9: aload_1
    //   10: ifnonnull +7 -> 17
    //   13: getstatic 25	rx/internal/producers/ProducerArbiter:NULL_PRODUCER	Lrx/Producer;
    //   16: astore_1
    //   17: aload_0
    //   18: aload_1
    //   19: putfield 33	rx/internal/producers/ProducerArbiter:missedProducer	Lrx/Producer;
    //   22: aload_0
    //   23: monitorexit
    //   24: goto +84 -> 108
    //   27: aload_0
    //   28: iconst_1
    //   29: putfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_0
    //   35: aload_1
    //   36: putfield 41	rx/internal/producers/ProducerArbiter:currentProducer	Lrx/Producer;
    //   39: aload_1
    //   40: ifnull +13 -> 53
    //   43: aload_1
    //   44: aload_0
    //   45: getfield 37	rx/internal/producers/ProducerArbiter:requested	J
    //   48: invokeinterface 52 3 0
    //   53: aload_0
    //   54: invokevirtual 62	rx/internal/producers/ProducerArbiter:emitLoop	()V
    //   57: iconst_1
    //   58: ifne +50 -> 108
    //   61: aload_0
    //   62: monitorenter
    //   63: aload_0
    //   64: iconst_0
    //   65: putfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   68: aload_0
    //   69: monitorexit
    //   70: goto +38 -> 108
    //   73: astore 5
    //   75: aload_0
    //   76: monitorexit
    //   77: aload 5
    //   79: athrow
    //   80: astore_2
    //   81: aload_0
    //   82: monitorexit
    //   83: aload_2
    //   84: athrow
    //   85: astore_3
    //   86: iconst_0
    //   87: ifne +12 -> 99
    //   90: aload_0
    //   91: monitorenter
    //   92: aload_0
    //   93: iconst_0
    //   94: putfield 35	rx/internal/producers/ProducerArbiter:emitting	Z
    //   97: aload_0
    //   98: monitorexit
    //   99: aload_3
    //   100: athrow
    //   101: astore 4
    //   103: aload_0
    //   104: monitorexit
    //   105: aload 4
    //   107: athrow
    //   108: return
    //
    // Exception table:
    //   from	to	target	type
    //   63	77	73	finally
    //   2	34	80	finally
    //   81	83	80	finally
    //   34	57	85	finally
    //   92	99	101	finally
    //   103	105	101	finally
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.producers.ProducerArbiter
 * JD-Core Version:    0.6.0
 */