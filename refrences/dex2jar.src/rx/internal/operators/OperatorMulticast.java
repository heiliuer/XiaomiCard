package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func0;
import rx.observables.ConnectableObservable;
import rx.subjects.Subject;

public final class OperatorMulticast<T, R> extends ConnectableObservable<R>
{
  final AtomicReference<Subject<? super T, ? extends R>> connectedSubject;
  final Object guard;
  private Subscription guardedSubscription;
  final Observable<? extends T> source;
  final Func0<? extends Subject<? super T, ? extends R>> subjectFactory;
  private Subscriber<T> subscription;
  final List<Subscriber<? super R>> waitingForConnect;

  private OperatorMulticast(Object paramObject, AtomicReference<Subject<? super T, ? extends R>> paramAtomicReference, List<Subscriber<? super R>> paramList, Observable<? extends T> paramObservable, Func0<? extends Subject<? super T, ? extends R>> paramFunc0)
  {
    super(new Observable.OnSubscribe(paramAtomicReference, paramList)
    {
      public void call(Subscriber<? super R> paramSubscriber)
      {
        synchronized (OperatorMulticast.this)
        {
          if (this.val$connectedSubject.get() == null)
          {
            this.val$waitingForConnect.add(paramSubscriber);
            return;
          }
          ((Subject)this.val$connectedSubject.get()).unsafeSubscribe(paramSubscriber);
        }
      }
    });
    this.guard = paramObject;
    this.connectedSubject = paramAtomicReference;
    this.waitingForConnect = paramList;
    this.source = paramObservable;
    this.subjectFactory = paramFunc0;
  }

  public OperatorMulticast(Observable<? extends T> paramObservable, Func0<? extends Subject<? super T, ? extends R>> paramFunc0)
  {
    this(new Object(), new AtomicReference(), new ArrayList(), paramObservable, paramFunc0);
  }

  // ERROR //
  public void connect(rx.functions.Action1<? super Subscription> paramAction1)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 40	rx/internal/operators/OperatorMulticast:guard	Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 72	rx/internal/operators/OperatorMulticast:subscription	Lrx/Subscriber;
    //   11: ifnull +18 -> 29
    //   14: aload_1
    //   15: aload_0
    //   16: getfield 66	rx/internal/operators/OperatorMulticast:guardedSubscription	Lrx/Subscription;
    //   19: invokeinterface 82 2 0
    //   24: aload_2
    //   25: monitorexit
    //   26: goto +198 -> 224
    //   29: aload_0
    //   30: getfield 48	rx/internal/operators/OperatorMulticast:subjectFactory	Lrx/functions/Func0;
    //   33: invokeinterface 87 1 0
    //   38: checkcast 89	rx/subjects/Subject
    //   41: astore 4
    //   43: aload_0
    //   44: aload 4
    //   46: invokestatic 95	rx/observers/Subscribers:from	(Lrx/Observer;)Lrx/Subscriber;
    //   49: putfield 72	rx/internal/operators/OperatorMulticast:subscription	Lrx/Subscriber;
    //   52: new 56	java/util/concurrent/atomic/AtomicReference
    //   55: dup
    //   56: invokespecial 57	java/util/concurrent/atomic/AtomicReference:<init>	()V
    //   59: astore 5
    //   61: aload 5
    //   63: new 9	rx/internal/operators/OperatorMulticast$2
    //   66: dup
    //   67: aload_0
    //   68: aload 5
    //   70: invokespecial 98	rx/internal/operators/OperatorMulticast$2:<init>	(Lrx/internal/operators/OperatorMulticast;Ljava/util/concurrent/atomic/AtomicReference;)V
    //   73: invokestatic 104	rx/subscriptions/Subscriptions:create	(Lrx/functions/Action0;)Lrx/Subscription;
    //   76: invokevirtual 107	java/util/concurrent/atomic/AtomicReference:set	(Ljava/lang/Object;)V
    //   79: aload_0
    //   80: aload 5
    //   82: invokevirtual 110	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   85: checkcast 112	rx/Subscription
    //   88: putfield 66	rx/internal/operators/OperatorMulticast:guardedSubscription	Lrx/Subscription;
    //   91: aload_0
    //   92: getfield 44	rx/internal/operators/OperatorMulticast:waitingForConnect	Ljava/util/List;
    //   95: invokeinterface 118 1 0
    //   100: astore 6
    //   102: aload 6
    //   104: invokeinterface 124 1 0
    //   109: ifeq +41 -> 150
    //   112: aload 6
    //   114: invokeinterface 127 1 0
    //   119: checkcast 129	rx/Subscriber
    //   122: astore 11
    //   124: aload 4
    //   126: new 11	rx/internal/operators/OperatorMulticast$3
    //   129: dup
    //   130: aload_0
    //   131: aload 11
    //   133: aload 11
    //   135: invokespecial 132	rx/internal/operators/OperatorMulticast$3:<init>	(Lrx/internal/operators/OperatorMulticast;Lrx/Subscriber;Lrx/Subscriber;)V
    //   138: invokevirtual 136	rx/subjects/Subject:unsafeSubscribe	(Lrx/Subscriber;)Lrx/Subscription;
    //   141: pop
    //   142: goto -40 -> 102
    //   145: astore_3
    //   146: aload_2
    //   147: monitorexit
    //   148: aload_3
    //   149: athrow
    //   150: aload_0
    //   151: getfield 44	rx/internal/operators/OperatorMulticast:waitingForConnect	Ljava/util/List;
    //   154: invokeinterface 139 1 0
    //   159: aload_0
    //   160: getfield 42	rx/internal/operators/OperatorMulticast:connectedSubject	Ljava/util/concurrent/atomic/AtomicReference;
    //   163: aload 4
    //   165: invokevirtual 107	java/util/concurrent/atomic/AtomicReference:set	(Ljava/lang/Object;)V
    //   168: aload_2
    //   169: monitorexit
    //   170: aload_1
    //   171: aload_0
    //   172: getfield 66	rx/internal/operators/OperatorMulticast:guardedSubscription	Lrx/Subscription;
    //   175: invokeinterface 82 2 0
    //   180: aload_0
    //   181: getfield 40	rx/internal/operators/OperatorMulticast:guard	Ljava/lang/Object;
    //   184: astore 7
    //   186: aload 7
    //   188: monitorenter
    //   189: aload_0
    //   190: getfield 72	rx/internal/operators/OperatorMulticast:subscription	Lrx/Subscriber;
    //   193: astore 9
    //   195: aload 7
    //   197: monitorexit
    //   198: aload 9
    //   200: ifnull +24 -> 224
    //   203: aload_0
    //   204: getfield 46	rx/internal/operators/OperatorMulticast:source	Lrx/Observable;
    //   207: aload 9
    //   209: invokevirtual 144	rx/Observable:subscribe	(Lrx/Subscriber;)Lrx/Subscription;
    //   212: pop
    //   213: goto +11 -> 224
    //   216: astore 8
    //   218: aload 7
    //   220: monitorexit
    //   221: aload 8
    //   223: athrow
    //   224: return
    //
    // Exception table:
    //   from	to	target	type
    //   7	148	145	finally
    //   150	170	145	finally
    //   189	198	216	finally
    //   218	221	216	finally
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorMulticast
 * JD-Core Version:    0.6.0
 */