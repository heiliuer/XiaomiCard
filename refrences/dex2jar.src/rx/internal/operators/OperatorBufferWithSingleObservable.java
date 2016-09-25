package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.observers.SerializedSubscriber;
import rx.observers.Subscribers;

public final class OperatorBufferWithSingleObservable<T, TClosing>
  implements Observable.Operator<List<T>, T>
{
  final Func0<? extends Observable<? extends TClosing>> bufferClosingSelector;
  final int initialCapacity;

  public OperatorBufferWithSingleObservable(Observable<? extends TClosing> paramObservable, int paramInt)
  {
    this.bufferClosingSelector = new Func0(paramObservable)
    {
      public Observable<? extends TClosing> call()
      {
        return this.val$bufferClosing;
      }
    };
    this.initialCapacity = paramInt;
  }

  public OperatorBufferWithSingleObservable(Func0<? extends Observable<? extends TClosing>> paramFunc0, int paramInt)
  {
    this.bufferClosingSelector = paramFunc0;
    this.initialCapacity = paramInt;
  }

  public Subscriber<? super T> call(Subscriber<? super List<T>> paramSubscriber)
  {
    try
    {
      Observable localObservable = (Observable)this.bufferClosingSelector.call();
      localObject = new BufferingSubscriber(new SerializedSubscriber(paramSubscriber));
      2 local2 = new Subscriber((BufferingSubscriber)localObject)
      {
        public void onCompleted()
        {
          this.val$bsub.onCompleted();
        }

        public void onError(Throwable paramThrowable)
        {
          this.val$bsub.onError(paramThrowable);
        }

        public void onNext(TClosing paramTClosing)
        {
          this.val$bsub.emit();
        }
      };
      paramSubscriber.add(local2);
      paramSubscriber.add((Subscription)localObject);
      localObservable.unsafeSubscribe(local2);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        Exceptions.throwOrReport(localThrowable, paramSubscriber);
        Object localObject = Subscribers.empty();
      }
    }
  }

  final class BufferingSubscriber extends Subscriber<T>
  {
    final Subscriber<? super List<T>> child;
    List<T> chunk;
    boolean done;

    public BufferingSubscriber()
    {
      Object localObject;
      this.child = localObject;
      this.chunk = new ArrayList(OperatorBufferWithSingleObservable.this.initialCapacity);
    }

    // ERROR //
    void emit()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 43	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:done	Z
      //   6: ifeq +8 -> 14
      //   9: aload_0
      //   10: monitorexit
      //   11: goto +85 -> 96
      //   14: aload_0
      //   15: getfield 38	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:chunk	Ljava/util/List;
      //   18: astore_2
      //   19: aload_0
      //   20: new 29	java/util/ArrayList
      //   23: dup
      //   24: aload_0
      //   25: getfield 22	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:this$0	Lrx/internal/operators/OperatorBufferWithSingleObservable;
      //   28: getfield 33	rx/internal/operators/OperatorBufferWithSingleObservable:initialCapacity	I
      //   31: invokespecial 36	java/util/ArrayList:<init>	(I)V
      //   34: putfield 38	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:chunk	Ljava/util/List;
      //   37: aload_0
      //   38: monitorexit
      //   39: aload_0
      //   40: getfield 27	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:child	Lrx/Subscriber;
      //   43: aload_2
      //   44: invokevirtual 47	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   47: goto +49 -> 96
      //   50: astore_3
      //   51: aload_0
      //   52: invokevirtual 50	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:unsubscribe	()V
      //   55: aload_0
      //   56: monitorenter
      //   57: aload_0
      //   58: getfield 43	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:done	Z
      //   61: ifeq +20 -> 81
      //   64: aload_0
      //   65: monitorexit
      //   66: goto +30 -> 96
      //   69: astore 4
      //   71: aload_0
      //   72: monitorexit
      //   73: aload 4
      //   75: athrow
      //   76: astore_1
      //   77: aload_0
      //   78: monitorexit
      //   79: aload_1
      //   80: athrow
      //   81: aload_0
      //   82: iconst_1
      //   83: putfield 43	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:done	Z
      //   86: aload_0
      //   87: monitorexit
      //   88: aload_3
      //   89: aload_0
      //   90: getfield 27	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:child	Lrx/Subscriber;
      //   93: invokestatic 56	rx/exceptions/Exceptions:throwOrReport	(Ljava/lang/Throwable;Lrx/Observer;)V
      //   96: return
      //
      // Exception table:
      //   from	to	target	type
      //   39	47	50	java/lang/Throwable
      //   57	73	69	finally
      //   81	88	69	finally
      //   2	39	76	finally
      //   77	79	76	finally
    }

    public void onCompleted()
    {
      try
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
          monitorexit;
        }
      }
      finally
      {
        localObject = finally;
        monitorexit;
        throw localObject;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorBufferWithSingleObservable
 * JD-Core Version:    0.6.0
 */