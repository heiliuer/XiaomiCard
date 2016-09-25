package rx.internal.operators;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.CompositeSubscription;

public final class OperatorBufferWithStartEndObservable<T, TOpening, TClosing>
  implements Observable.Operator<List<T>, T>
{
  final Func1<? super TOpening, ? extends Observable<? extends TClosing>> bufferClosing;
  final Observable<? extends TOpening> bufferOpening;

  public OperatorBufferWithStartEndObservable(Observable<? extends TOpening> paramObservable, Func1<? super TOpening, ? extends Observable<? extends TClosing>> paramFunc1)
  {
    this.bufferOpening = paramObservable;
    this.bufferClosing = paramFunc1;
  }

  public Subscriber<? super T> call(Subscriber<? super List<T>> paramSubscriber)
  {
    BufferingSubscriber localBufferingSubscriber = new BufferingSubscriber(new SerializedSubscriber(paramSubscriber));
    1 local1 = new Subscriber(localBufferingSubscriber)
    {
      public void onCompleted()
      {
        this.val$bsub.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$bsub.onError(paramThrowable);
      }

      public void onNext(TOpening paramTOpening)
      {
        this.val$bsub.startBuffer(paramTOpening);
      }
    };
    paramSubscriber.add(local1);
    paramSubscriber.add(localBufferingSubscriber);
    this.bufferOpening.unsafeSubscribe(local1);
    return localBufferingSubscriber;
  }

  final class BufferingSubscriber extends Subscriber<T>
  {
    final Subscriber<? super List<T>> child;
    final List<List<T>> chunks;
    final CompositeSubscription closingSubscriptions;
    boolean done;

    public BufferingSubscriber()
    {
      Object localObject;
      this.child = localObject;
      this.chunks = new LinkedList();
      this.closingSubscriptions = new CompositeSubscription();
      add(this.closingSubscriptions);
    }

    void endBuffer(List<T> paramList)
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
            i = 1;
            localIterator.remove();
          }
          monitorexit;
          if (i != 0)
            this.child.onNext(paramList);
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
      //   3: getfield 36	rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber:chunks	Ljava/util/List;
      //   6: invokeinterface 55 1 0
      //   11: astore_3
      //   12: aload_3
      //   13: invokeinterface 61 1 0
      //   18: ifeq +27 -> 45
      //   21: aload_3
      //   22: invokeinterface 65 1 0
      //   27: checkcast 51	java/util/List
      //   30: aload_1
      //   31: invokeinterface 99 2 0
      //   36: pop
      //   37: goto -25 -> 12
      //   40: astore_2
      //   41: aload_0
      //   42: monitorexit
      //   43: aload_2
      //   44: athrow
      //   45: aload_0
      //   46: monitorexit
      //   47: return
      //
      // Exception table:
      //   from	to	target	type
      //   2	43	40	finally
      //   45	47	40	finally
    }

    // ERROR //
    void startBuffer(TOpening paramTOpening)
    {
      // Byte code:
      //   0: new 102	java/util/ArrayList
      //   3: dup
      //   4: invokespecial 103	java/util/ArrayList:<init>	()V
      //   7: astore_2
      //   8: aload_0
      //   9: monitorenter
      //   10: aload_0
      //   11: getfield 49	rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber:done	Z
      //   14: ifeq +8 -> 22
      //   17: aload_0
      //   18: monitorexit
      //   19: goto +78 -> 97
      //   22: aload_0
      //   23: getfield 36	rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber:chunks	Ljava/util/List;
      //   26: aload_2
      //   27: invokeinterface 99 2 0
      //   32: pop
      //   33: aload_0
      //   34: monitorexit
      //   35: aload_0
      //   36: getfield 26	rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber:this$0	Lrx/internal/operators/OperatorBufferWithStartEndObservable;
      //   39: getfield 107	rx/internal/operators/OperatorBufferWithStartEndObservable:bufferClosing	Lrx/functions/Func1;
      //   42: aload_1
      //   43: invokeinterface 113 2 0
      //   48: checkcast 115	rx/Observable
      //   51: astore 6
      //   53: new 7	rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber$1
      //   56: dup
      //   57: aload_0
      //   58: aload_2
      //   59: invokespecial 118	rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber$1:<init>	(Lrx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber;Ljava/util/List;)V
      //   62: astore 7
      //   64: aload_0
      //   65: getfield 41	rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber:closingSubscriptions	Lrx/subscriptions/CompositeSubscription;
      //   68: aload 7
      //   70: invokevirtual 119	rx/subscriptions/CompositeSubscription:add	(Lrx/Subscription;)V
      //   73: aload 6
      //   75: aload 7
      //   77: invokevirtual 123	rx/Observable:unsafeSubscribe	(Lrx/Subscriber;)Lrx/Subscription;
      //   80: pop
      //   81: goto +16 -> 97
      //   84: astore_3
      //   85: aload_0
      //   86: monitorexit
      //   87: aload_3
      //   88: athrow
      //   89: astore 5
      //   91: aload 5
      //   93: aload_0
      //   94: invokestatic 87	rx/exceptions/Exceptions:throwOrReport	(Ljava/lang/Throwable;Lrx/Observer;)V
      //   97: return
      //
      // Exception table:
      //   from	to	target	type
      //   10	35	84	finally
      //   85	87	84	finally
      //   35	53	89	java/lang/Throwable
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorBufferWithStartEndObservable
 * JD-Core Version:    0.6.0
 */