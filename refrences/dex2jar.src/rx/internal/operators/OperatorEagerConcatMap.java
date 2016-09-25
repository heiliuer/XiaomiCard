package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.Subscriptions;

public final class OperatorEagerConcatMap<T, R>
  implements Observable.Operator<R, T>
{
  final int bufferSize;
  final Func1<? super T, ? extends Observable<? extends R>> mapper;

  public OperatorEagerConcatMap(Func1<? super T, ? extends Observable<? extends R>> paramFunc1, int paramInt)
  {
    this.mapper = paramFunc1;
    this.bufferSize = paramInt;
  }

  public Subscriber<? super T> call(Subscriber<? super R> paramSubscriber)
  {
    EagerOuterSubscriber localEagerOuterSubscriber = new EagerOuterSubscriber(this.mapper, this.bufferSize, paramSubscriber);
    localEagerOuterSubscriber.init();
    return localEagerOuterSubscriber;
  }

  static final class EagerInnerSubscriber<T> extends Subscriber<T>
  {
    volatile boolean done;
    Throwable error;
    final OperatorEagerConcatMap.EagerOuterSubscriber<?, T> parent;
    final Queue<T> queue;

    public EagerInnerSubscriber(OperatorEagerConcatMap.EagerOuterSubscriber<?, T> paramEagerOuterSubscriber, int paramInt)
    {
      this.parent = paramEagerOuterSubscriber;
      if (UnsafeAccess.isUnsafeAvailable());
      for (Object localObject = new SpscArrayQueue(paramInt); ; localObject = new SpscAtomicArrayQueue(paramInt))
      {
        this.queue = ((Queue)localObject);
        request(paramInt);
        return;
      }
    }

    public void onCompleted()
    {
      this.done = true;
      this.parent.drain();
    }

    public void onError(Throwable paramThrowable)
    {
      this.error = paramThrowable;
      this.done = true;
      this.parent.drain();
    }

    public void onNext(T paramT)
    {
      this.queue.offer(paramT);
      this.parent.drain();
    }

    void requestMore(long paramLong)
    {
      request(paramLong);
    }
  }

  static final class EagerOuterSubscriber<T, R> extends Subscriber<T>
  {
    final Subscriber<? super R> actual;
    final int bufferSize;
    volatile boolean cancelled;
    volatile boolean done;
    Throwable error;
    final Func1<? super T, ? extends Observable<? extends R>> mapper;
    private OperatorEagerConcatMap.EagerOuterProducer sharedProducer;
    final LinkedList<OperatorEagerConcatMap.EagerInnerSubscriber<R>> subscribers;
    final AtomicInteger wip;

    public EagerOuterSubscriber(Func1<? super T, ? extends Observable<? extends R>> paramFunc1, int paramInt, Subscriber<? super R> paramSubscriber)
    {
      this.mapper = paramFunc1;
      this.bufferSize = paramInt;
      this.actual = paramSubscriber;
      this.subscribers = new LinkedList();
      this.wip = new AtomicInteger();
    }

    void cleanup()
    {
      synchronized (this.subscribers)
      {
        ArrayList localArrayList = new ArrayList(this.subscribers);
        this.subscribers.clear();
        Iterator localIterator = localArrayList.iterator();
        if (localIterator.hasNext())
          ((Subscription)localIterator.next()).unsubscribe();
      }
    }

    void drain()
    {
      if (this.wip.getAndIncrement() != 0);
      while (true)
      {
        return;
        int i = 1;
        OperatorEagerConcatMap.EagerOuterProducer localEagerOuterProducer = this.sharedProducer;
        Subscriber localSubscriber = this.actual;
        if (this.cancelled)
        {
          cleanup();
          continue;
        }
        boolean bool1 = this.done;
        OperatorEagerConcatMap.EagerInnerSubscriber localEagerInnerSubscriber;
        int j;
        while (true)
        {
          synchronized (this.subscribers)
          {
            localEagerInnerSubscriber = (OperatorEagerConcatMap.EagerInnerSubscriber)this.subscribers.peek();
            if (localEagerInnerSubscriber == null)
            {
              j = 1;
              if (!bool1)
                break label130;
              Throwable localThrowable3 = this.error;
              if (localThrowable3 == null)
                break label118;
              cleanup();
              localSubscriber.onError(localThrowable3);
            }
          }
          j = 0;
          continue;
          label118: if (j != 0)
          {
            localSubscriber.onCompleted();
            break;
          }
        }
        label130: long l1;
        long l2;
        int k;
        label156: Queue localQueue;
        int m;
        if (j == 0)
        {
          l1 = localEagerOuterProducer.get();
          l2 = 0L;
          if (l1 == 9223372036854775807L)
          {
            k = 1;
            localQueue = localEagerInnerSubscriber.queue;
            m = 0;
          }
        }
        while (true)
        {
          boolean bool2 = localEagerInnerSubscriber.done;
          Object localObject2 = localQueue.peek();
          if (localObject2 == null);
          for (int n = 1; ; n = 0)
          {
            if (!bool2)
              break label327;
            Throwable localThrowable2 = localEagerInnerSubscriber.error;
            if (localThrowable2 == null)
              break label232;
            cleanup();
            localSubscriber.onError(localThrowable2);
            break;
            k = 0;
            break label156;
          }
          label232: if (n != 0);
          label327: 
          do
            synchronized (this.subscribers)
            {
              this.subscribers.poll();
              localEagerInnerSubscriber.unsubscribe();
              m = 1;
              if (l2 != 0L)
              {
                if (k == 0)
                  localEagerOuterProducer.addAndGet(l2);
                if (m == 0)
                  localEagerInnerSubscriber.requestMore(-l2);
              }
              if (m != 0)
                break;
              i = this.wip.addAndGet(-i);
              if (i != 0)
                break;
            }
          while ((n != 0) || (l1 == 0L));
          localQueue.poll();
          try
          {
            localSubscriber.onNext(localObject2);
            l1 -= 1L;
            l2 -= 1L;
          }
          catch (Throwable localThrowable1)
          {
            Exceptions.throwOrReport(localThrowable1, localSubscriber, localObject2);
          }
        }
      }
    }

    void init()
    {
      this.sharedProducer = new OperatorEagerConcatMap.EagerOuterProducer(this);
      add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          OperatorEagerConcatMap.EagerOuterSubscriber.this.cancelled = true;
          if (OperatorEagerConcatMap.EagerOuterSubscriber.this.wip.getAndIncrement() == 0)
            OperatorEagerConcatMap.EagerOuterSubscriber.this.cleanup();
        }
      }));
      this.actual.add(this);
      this.actual.setProducer(this.sharedProducer);
    }

    public void onCompleted()
    {
      this.done = true;
      drain();
    }

    public void onError(Throwable paramThrowable)
    {
      this.error = paramThrowable;
      this.done = true;
      drain();
    }

    // ERROR //
    public void onNext(T paramT)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 37	rx/internal/operators/OperatorEagerConcatMap$EagerOuterSubscriber:mapper	Lrx/functions/Func1;
      //   4: aload_1
      //   5: invokeinterface 183 2 0
      //   10: checkcast 185	rx/Observable
      //   13: astore_3
      //   14: new 101	rx/internal/operators/OperatorEagerConcatMap$EagerInnerSubscriber
      //   17: dup
      //   18: aload_0
      //   19: aload_0
      //   20: getfield 39	rx/internal/operators/OperatorEagerConcatMap$EagerOuterSubscriber:bufferSize	I
      //   23: invokespecial 188	rx/internal/operators/OperatorEagerConcatMap$EagerInnerSubscriber:<init>	(Lrx/internal/operators/OperatorEagerConcatMap$EagerOuterSubscriber;I)V
      //   26: astore 4
      //   28: aload_0
      //   29: getfield 92	rx/internal/operators/OperatorEagerConcatMap$EagerOuterSubscriber:cancelled	Z
      //   32: ifeq +17 -> 49
      //   35: return
      //   36: astore_2
      //   37: aload_2
      //   38: aload_0
      //   39: getfield 41	rx/internal/operators/OperatorEagerConcatMap$EagerOuterSubscriber:actual	Lrx/Subscriber;
      //   42: aload_1
      //   43: invokestatic 153	rx/exceptions/Exceptions:throwOrReport	(Ljava/lang/Throwable;Lrx/Observer;Ljava/lang/Object;)V
      //   46: goto -11 -> 35
      //   49: aload_0
      //   50: getfield 46	rx/internal/operators/OperatorEagerConcatMap$EagerOuterSubscriber:subscribers	Ljava/util/LinkedList;
      //   53: astore 5
      //   55: aload 5
      //   57: monitorenter
      //   58: aload_0
      //   59: getfield 92	rx/internal/operators/OperatorEagerConcatMap$EagerOuterSubscriber:cancelled	Z
      //   62: ifeq +17 -> 79
      //   65: aload 5
      //   67: monitorexit
      //   68: goto -33 -> 35
      //   71: astore 6
      //   73: aload 5
      //   75: monitorexit
      //   76: aload 6
      //   78: athrow
      //   79: aload_0
      //   80: getfield 46	rx/internal/operators/OperatorEagerConcatMap$EagerOuterSubscriber:subscribers	Ljava/util/LinkedList;
      //   83: aload 4
      //   85: invokevirtual 191	java/util/LinkedList:add	(Ljava/lang/Object;)Z
      //   88: pop
      //   89: aload 5
      //   91: monitorexit
      //   92: aload_0
      //   93: getfield 92	rx/internal/operators/OperatorEagerConcatMap$EagerOuterSubscriber:cancelled	Z
      //   96: ifne -61 -> 35
      //   99: aload_3
      //   100: aload 4
      //   102: invokevirtual 195	rx/Observable:unsafeSubscribe	(Lrx/Subscriber;)Lrx/Subscription;
      //   105: pop
      //   106: aload_0
      //   107: invokevirtual 177	rx/internal/operators/OperatorEagerConcatMap$EagerOuterSubscriber:drain	()V
      //   110: goto -75 -> 35
      //
      // Exception table:
      //   from	to	target	type
      //   0	14	36	java/lang/Throwable
      //   58	76	71	finally
      //   79	92	71	finally
    }
  }

  static final class EagerOuterProducer extends AtomicLong
    implements Producer
  {
    private static final long serialVersionUID = -657299606803478389L;
    final OperatorEagerConcatMap.EagerOuterSubscriber<?, ?> parent;

    public EagerOuterProducer(OperatorEagerConcatMap.EagerOuterSubscriber<?, ?> paramEagerOuterSubscriber)
    {
      this.parent = paramEagerOuterSubscriber;
    }

    public void request(long paramLong)
    {
      if (paramLong < 0L)
        throw new IllegalStateException("n >= 0 required but it was " + paramLong);
      if (paramLong > 0L)
      {
        BackpressureUtils.getAndAddRequest(this, paramLong);
        this.parent.drain();
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorEagerConcatMap
 * JD-Core Version:    0.6.0
 */