package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;

public final class OperatorBufferWithTime<T>
  implements Observable.Operator<List<T>, T>
{
  final int count;
  final Scheduler scheduler;
  final long timeshift;
  final long timespan;
  final TimeUnit unit;

  public OperatorBufferWithTime(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, int paramInt, Scheduler paramScheduler)
  {
    this.timespan = paramLong1;
    this.timeshift = paramLong2;
    this.unit = paramTimeUnit;
    this.count = paramInt;
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super List<T>> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    Object localObject;
    if (this.timespan == this.timeshift)
    {
      localObject = new ExactSubscriber(localSerializedSubscriber, localWorker);
      ((ExactSubscriber)localObject).add(localWorker);
      paramSubscriber.add((Subscription)localObject);
      ((ExactSubscriber)localObject).scheduleExact();
    }
    while (true)
    {
      return localObject;
      InexactSubscriber localInexactSubscriber = new InexactSubscriber(localSerializedSubscriber, localWorker);
      localInexactSubscriber.add(localWorker);
      paramSubscriber.add(localInexactSubscriber);
      localInexactSubscriber.startNewChunk();
      localInexactSubscriber.scheduleChunk();
      localObject = localInexactSubscriber;
    }
  }

  final class ExactSubscriber extends Subscriber<T>
  {
    final Subscriber<? super List<T>> child;
    List<T> chunk;
    boolean done;
    final Scheduler.Worker inner;

    public ExactSubscriber(Scheduler.Worker arg2)
    {
      Object localObject1;
      this.child = localObject1;
      Object localObject2;
      this.inner = localObject2;
      this.chunk = new ArrayList();
    }

    void emit()
    {
      monitorenter;
      try
      {
        if (this.done)
        {
          monitorexit;
        }
        else
        {
          List localList = this.chunk;
          this.chunk = new ArrayList();
          monitorexit;
          try
          {
            this.child.onNext(localList);
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwOrReport(localThrowable, this);
          }
        }
      }
      finally
      {
        monitorexit;
      }
    }

    public void onCompleted()
    {
      try
      {
        this.inner.unsubscribe();
        monitorenter;
        try
        {
          if (this.done)
          {
            monitorexit;
          }
          else
          {
            this.done = true;
            List localList = this.chunk;
            this.chunk = null;
            monitorexit;
            this.child.onNext(localList);
            this.child.onCompleted();
            unsubscribe();
          }
        }
        finally
        {
          monitorexit;
        }
      }
      catch (Throwable localThrowable)
      {
        Exceptions.throwOrReport(localThrowable, this.child);
      }
    }

    public void onError(Throwable paramThrowable)
    {
      monitorenter;
      try
      {
        if (this.done)
        {
          monitorexit;
        }
        else
        {
          this.done = true;
          this.chunk = null;
          monitorexit;
          this.child.onError(paramThrowable);
          unsubscribe();
        }
      }
      finally
      {
        monitorexit;
      }
    }

    public void onNext(T paramT)
    {
      List localList = null;
      monitorenter;
      try
      {
        if (this.done)
        {
          monitorexit;
        }
        else
        {
          this.chunk.add(paramT);
          if (this.chunk.size() == OperatorBufferWithTime.this.count)
          {
            localList = this.chunk;
            this.chunk = new ArrayList();
          }
          monitorexit;
          if (localList != null)
            this.child.onNext(localList);
        }
      }
      finally
      {
        monitorexit;
      }
    }

    void scheduleExact()
    {
      this.inner.schedulePeriodically(new Action0()
      {
        public void call()
        {
          OperatorBufferWithTime.ExactSubscriber.this.emit();
        }
      }
      , OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.unit);
    }
  }

  final class InexactSubscriber extends Subscriber<T>
  {
    final Subscriber<? super List<T>> child;
    final List<List<T>> chunks;
    boolean done;
    final Scheduler.Worker inner;

    public InexactSubscriber(Scheduler.Worker arg2)
    {
      Object localObject1;
      this.child = localObject1;
      Object localObject2;
      this.inner = localObject2;
      this.chunks = new LinkedList();
    }

    void emitChunk(List<T> paramList)
    {
      int i = 0;
      monitorenter;
      try
      {
        if (this.done)
        {
          monitorexit;
        }
        else
        {
          Iterator localIterator = this.chunks.iterator();
          while (localIterator.hasNext())
          {
            if ((List)localIterator.next() != paramList)
              continue;
            localIterator.remove();
            i = 1;
          }
          monitorexit;
          if (i != 0)
            try
            {
              this.child.onNext(paramList);
            }
            catch (Throwable localThrowable)
            {
              Exceptions.throwOrReport(localThrowable, this);
            }
        }
      }
      finally
      {
        monitorexit;
      }
    }

    public void onCompleted()
    {
      try
      {
        monitorenter;
      }
      catch (Throwable localThrowable)
      {
        try
        {
          if (this.done)
          {
            monitorexit;
            return;
          }
          this.done = true;
          LinkedList localLinkedList = new LinkedList(this.chunks);
          this.chunks.clear();
          monitorexit;
          Iterator localIterator = localLinkedList.iterator();
          while (localIterator.hasNext())
          {
            List localList = (List)localIterator.next();
            this.child.onNext(localList);
            continue;
            localThrowable = localThrowable;
            Exceptions.throwOrReport(localThrowable, this.child);
          }
        }
        finally
        {
          monitorexit;
        }
        this.child.onCompleted();
        unsubscribe();
      }
    }

    public void onError(Throwable paramThrowable)
    {
      monitorenter;
      try
      {
        if (this.done)
        {
          monitorexit;
        }
        else
        {
          this.done = true;
          this.chunks.clear();
          monitorexit;
          this.child.onError(paramThrowable);
          unsubscribe();
        }
      }
      finally
      {
        monitorexit;
      }
    }

    // ERROR //
    public void onNext(T paramT)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 46	rx/internal/operators/OperatorBufferWithTime$InexactSubscriber:done	Z
      //   6: ifeq +8 -> 14
      //   9: aload_0
      //   10: monitorexit
      //   11: goto +181 -> 192
      //   14: aload_0
      //   15: getfield 40	rx/internal/operators/OperatorBufferWithTime$InexactSubscriber:chunks	Ljava/util/List;
      //   18: invokeinterface 52 1 0
      //   23: astore_3
      //   24: aconst_null
      //   25: astore 4
      //   27: aload_3
      //   28: invokeinterface 58 1 0
      //   33: ifeq +77 -> 110
      //   36: aload_3
      //   37: invokeinterface 62 1 0
      //   42: checkcast 48	java/util/List
      //   45: astore 9
      //   47: aload 9
      //   49: aload_1
      //   50: invokeinterface 95 2 0
      //   55: pop
      //   56: aload 9
      //   58: invokeinterface 99 1 0
      //   63: aload_0
      //   64: getfield 28	rx/internal/operators/OperatorBufferWithTime$InexactSubscriber:this$0	Lrx/internal/operators/OperatorBufferWithTime;
      //   67: getfield 103	rx/internal/operators/OperatorBufferWithTime:count	I
      //   70: if_icmpne +115 -> 185
      //   73: aload_3
      //   74: invokeinterface 65 1 0
      //   79: aload 4
      //   81: ifnonnull +97 -> 178
      //   84: new 37	java/util/LinkedList
      //   87: dup
      //   88: invokespecial 38	java/util/LinkedList:<init>	()V
      //   91: astore 11
      //   93: aload 11
      //   95: aload 9
      //   97: invokeinterface 95 2 0
      //   102: pop
      //   103: aload 11
      //   105: astore 4
      //   107: goto -80 -> 27
      //   110: aload_0
      //   111: monitorexit
      //   112: aload 4
      //   114: ifnull +51 -> 165
      //   117: aload 4
      //   119: invokeinterface 52 1 0
      //   124: astore 7
      //   126: aload 7
      //   128: invokeinterface 58 1 0
      //   133: ifeq +32 -> 165
      //   136: aload 7
      //   138: invokeinterface 62 1 0
      //   143: checkcast 48	java/util/List
      //   146: astore 8
      //   148: aload_0
      //   149: getfield 33	rx/internal/operators/OperatorBufferWithTime$InexactSubscriber:child	Lrx/Subscriber;
      //   152: aload 8
      //   154: invokevirtual 69	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   157: goto -31 -> 126
      //   160: astore_2
      //   161: aload_0
      //   162: monitorexit
      //   163: aload_2
      //   164: athrow
      //   165: aload 4
      //   167: pop
      //   168: goto +24 -> 192
      //   171: astore_2
      //   172: aload 4
      //   174: pop
      //   175: goto -14 -> 161
      //   178: aload 4
      //   180: astore 11
      //   182: goto -89 -> 93
      //   185: aload 4
      //   187: astore 11
      //   189: goto -86 -> 103
      //   192: return
      //
      // Exception table:
      //   from	to	target	type
      //   2	24	160	finally
      //   93	103	160	finally
      //   161	163	160	finally
      //   27	93	171	finally
      //   110	112	171	finally
    }

    void scheduleChunk()
    {
      this.inner.schedulePeriodically(new Action0()
      {
        public void call()
        {
          OperatorBufferWithTime.InexactSubscriber.this.startNewChunk();
        }
      }
      , OperatorBufferWithTime.this.timeshift, OperatorBufferWithTime.this.timeshift, OperatorBufferWithTime.this.unit);
    }

    void startNewChunk()
    {
      ArrayList localArrayList = new ArrayList();
      monitorenter;
      try
      {
        if (this.done)
        {
          monitorexit;
        }
        else
        {
          this.chunks.add(localArrayList);
          monitorexit;
          this.inner.schedule(new Action0(localArrayList)
          {
            public void call()
            {
              OperatorBufferWithTime.InexactSubscriber.this.emitChunk(this.val$chunk);
            }
          }
          , OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.unit);
        }
      }
      finally
      {
        monitorexit;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorBufferWithTime
 * JD-Core Version:    0.6.0
 */