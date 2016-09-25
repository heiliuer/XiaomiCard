package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Notification;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public final class OperatorMaterialize<T>
  implements Observable.Operator<Notification<T>, T>
{
  public static <T> OperatorMaterialize<T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super T> call(Subscriber<? super Notification<T>> paramSubscriber)
  {
    ParentSubscriber localParentSubscriber = new ParentSubscriber(paramSubscriber);
    paramSubscriber.add(localParentSubscriber);
    paramSubscriber.setProducer(new Producer(localParentSubscriber)
    {
      public void request(long paramLong)
      {
        if (paramLong > 0L)
          this.val$parent.requestMore(paramLong);
      }
    });
    return localParentSubscriber;
  }

  private static class ParentSubscriber<T> extends Subscriber<T>
  {
    private boolean busy = false;
    private final Subscriber<? super Notification<T>> child;
    private boolean missed = false;
    private final AtomicLong requested = new AtomicLong();
    private volatile Notification<T> terminalNotification;

    ParentSubscriber(Subscriber<? super Notification<T>> paramSubscriber)
    {
      this.child = paramSubscriber;
    }

    private void decrementRequested()
    {
      AtomicLong localAtomicLong = this.requested;
      while (true)
      {
        long l = localAtomicLong.get();
        if (l == 9223372036854775807L);
        while (true)
        {
          return;
          if (!localAtomicLong.compareAndSet(l, l - 1L))
            break;
        }
      }
    }

    // ERROR //
    private void drain()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 26	rx/internal/operators/OperatorMaterialize$ParentSubscriber:busy	Z
      //   6: ifeq +13 -> 19
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 28	rx/internal/operators/OperatorMaterialize$ParentSubscriber:missed	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: goto +107 -> 123
      //   19: aload_0
      //   20: monitorexit
      //   21: aload_0
      //   22: getfield 33	rx/internal/operators/OperatorMaterialize$ParentSubscriber:requested	Ljava/util/concurrent/atomic/AtomicLong;
      //   25: astore_2
      //   26: aload_0
      //   27: getfield 35	rx/internal/operators/OperatorMaterialize$ParentSubscriber:child	Lrx/Subscriber;
      //   30: invokevirtual 51	rx/Subscriber:isUnsubscribed	()Z
      //   33: ifne +90 -> 123
      //   36: aload_0
      //   37: getfield 53	rx/internal/operators/OperatorMaterialize$ParentSubscriber:terminalNotification	Lrx/Notification;
      //   40: astore_3
      //   41: aload_3
      //   42: ifnull +50 -> 92
      //   45: aload_2
      //   46: invokevirtual 40	java/util/concurrent/atomic/AtomicLong:get	()J
      //   49: lconst_0
      //   50: lcmp
      //   51: ifle +41 -> 92
      //   54: aload_0
      //   55: aconst_null
      //   56: putfield 53	rx/internal/operators/OperatorMaterialize$ParentSubscriber:terminalNotification	Lrx/Notification;
      //   59: aload_0
      //   60: getfield 35	rx/internal/operators/OperatorMaterialize$ParentSubscriber:child	Lrx/Subscriber;
      //   63: aload_3
      //   64: invokevirtual 57	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   67: aload_0
      //   68: getfield 35	rx/internal/operators/OperatorMaterialize$ParentSubscriber:child	Lrx/Subscriber;
      //   71: invokevirtual 51	rx/Subscriber:isUnsubscribed	()Z
      //   74: ifne +49 -> 123
      //   77: aload_0
      //   78: getfield 35	rx/internal/operators/OperatorMaterialize$ParentSubscriber:child	Lrx/Subscriber;
      //   81: invokevirtual 60	rx/Subscriber:onCompleted	()V
      //   84: goto +39 -> 123
      //   87: astore_1
      //   88: aload_0
      //   89: monitorexit
      //   90: aload_1
      //   91: athrow
      //   92: aload_0
      //   93: monitorenter
      //   94: aload_0
      //   95: getfield 28	rx/internal/operators/OperatorMaterialize$ParentSubscriber:missed	Z
      //   98: ifne +20 -> 118
      //   101: aload_0
      //   102: iconst_0
      //   103: putfield 26	rx/internal/operators/OperatorMaterialize$ParentSubscriber:busy	Z
      //   106: aload_0
      //   107: monitorexit
      //   108: goto +15 -> 123
      //   111: astore 4
      //   113: aload_0
      //   114: monitorexit
      //   115: aload 4
      //   117: athrow
      //   118: aload_0
      //   119: monitorexit
      //   120: goto -94 -> 26
      //   123: return
      //
      // Exception table:
      //   from	to	target	type
      //   2	21	87	finally
      //   88	90	87	finally
      //   94	115	111	finally
      //   118	120	111	finally
    }

    public void onCompleted()
    {
      this.terminalNotification = Notification.createOnCompleted();
      drain();
    }

    public void onError(Throwable paramThrowable)
    {
      this.terminalNotification = Notification.createOnError(paramThrowable);
      RxJavaPlugins.getInstance().getErrorHandler().handleError(paramThrowable);
      drain();
    }

    public void onNext(T paramT)
    {
      this.child.onNext(Notification.createOnNext(paramT));
      decrementRequested();
    }

    public void onStart()
    {
      request(0L);
    }

    void requestMore(long paramLong)
    {
      BackpressureUtils.getAndAddRequest(this.requested, paramLong);
      request(paramLong);
      drain();
    }
  }

  private static final class Holder
  {
    static final OperatorMaterialize<Object> INSTANCE = new OperatorMaterialize(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorMaterialize
 * JD-Core Version:    0.6.0
 */