package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.NotificationLite;
import rx.subscriptions.Subscriptions;

final class SubjectSubscriptionManager<T> extends AtomicReference<State<T>>
  implements Observable.OnSubscribe<T>
{
  boolean active = true;
  volatile Object latest;
  public final NotificationLite<T> nl = NotificationLite.instance();
  Action1<SubjectObserver<T>> onAdded = Actions.empty();
  Action1<SubjectObserver<T>> onStart = Actions.empty();
  Action1<SubjectObserver<T>> onTerminated = Actions.empty();

  public SubjectSubscriptionManager()
  {
    super(State.EMPTY);
  }

  boolean add(SubjectObserver<T> paramSubjectObserver)
  {
    State localState = (State)get();
    if (localState.terminated)
      this.onTerminated.call(paramSubjectObserver);
    for (int i = 0; ; i = 1)
    {
      return i;
      if (!compareAndSet(localState, localState.add(paramSubjectObserver)))
        break;
      this.onAdded.call(paramSubjectObserver);
    }
  }

  void addUnsubscriber(Subscriber<? super T> paramSubscriber, SubjectObserver<T> paramSubjectObserver)
  {
    paramSubscriber.add(Subscriptions.create(new Action0(paramSubjectObserver)
    {
      public void call()
      {
        SubjectSubscriptionManager.this.remove(this.val$bo);
      }
    }));
  }

  public void call(Subscriber<? super T> paramSubscriber)
  {
    SubjectObserver localSubjectObserver = new SubjectObserver(paramSubscriber);
    addUnsubscriber(paramSubscriber, localSubjectObserver);
    this.onStart.call(localSubjectObserver);
    if ((!paramSubscriber.isUnsubscribed()) && (add(localSubjectObserver)) && (paramSubscriber.isUnsubscribed()))
      remove(localSubjectObserver);
  }

  Object getLatest()
  {
    return this.latest;
  }

  SubjectObserver<T>[] next(Object paramObject)
  {
    setLatest(paramObject);
    return ((State)get()).observers;
  }

  SubjectObserver<T>[] observers()
  {
    return ((State)get()).observers;
  }

  void remove(SubjectObserver<T> paramSubjectObserver)
  {
    State localState1 = (State)get();
    if (localState1.terminated);
    while (true)
    {
      return;
      State localState2 = localState1.remove(paramSubjectObserver);
      if (localState2 == localState1)
        continue;
      if (!compareAndSet(localState1, localState2))
        break;
    }
  }

  void setLatest(Object paramObject)
  {
    this.latest = paramObject;
  }

  SubjectObserver<T>[] terminate(Object paramObject)
  {
    setLatest(paramObject);
    this.active = false;
    if (((State)get()).terminated);
    for (SubjectObserver[] arrayOfSubjectObserver = State.NO_OBSERVERS; ; arrayOfSubjectObserver = ((State)getAndSet(State.TERMINATED)).observers)
      return arrayOfSubjectObserver;
  }

  protected static final class SubjectObserver<T>
    implements Observer<T>
  {
    final Observer<? super T> actual;
    protected volatile boolean caughtUp;
    boolean emitting;
    boolean fastPath;
    boolean first = true;
    private volatile Object index;
    List<Object> queue;

    public SubjectObserver(Observer<? super T> paramObserver)
    {
      this.actual = paramObserver;
    }

    protected void accept(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      if (paramObject != null)
        paramNotificationLite.accept(this.actual, paramObject);
    }

    protected void emitFirst(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      boolean bool = false;
      monitorenter;
      try
      {
        if ((!this.first) || (this.emitting))
        {
          monitorexit;
        }
        else
        {
          this.first = false;
          if (paramObject != null)
            bool = true;
          this.emitting = bool;
          monitorexit;
          if (paramObject != null)
            emitLoop(null, paramObject, paramNotificationLite);
        }
      }
      finally
      {
        monitorexit;
      }
    }

    // ERROR //
    protected void emitLoop(List<Object> paramList, Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      // Byte code:
      //   0: iconst_1
      //   1: istore 4
      //   3: iconst_0
      //   4: istore 5
      //   6: aload_1
      //   7: ifnull +55 -> 62
      //   10: aload_1
      //   11: invokeinterface 52 1 0
      //   16: astore 10
      //   18: aload 10
      //   20: invokeinterface 58 1 0
      //   25: ifeq +37 -> 62
      //   28: aload_0
      //   29: aload 10
      //   31: invokeinterface 62 1 0
      //   36: aload_3
      //   37: invokevirtual 64	rx/subjects/SubjectSubscriptionManager$SubjectObserver:accept	(Ljava/lang/Object;Lrx/internal/operators/NotificationLite;)V
      //   40: goto -22 -> 18
      //   43: astore 7
      //   45: iload 5
      //   47: ifne +12 -> 59
      //   50: aload_0
      //   51: monitorenter
      //   52: aload_0
      //   53: iconst_0
      //   54: putfield 42	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
      //   57: aload_0
      //   58: monitorexit
      //   59: aload 7
      //   61: athrow
      //   62: iload 4
      //   64: ifeq +12 -> 76
      //   67: iconst_0
      //   68: istore 4
      //   70: aload_0
      //   71: aload_2
      //   72: aload_3
      //   73: invokevirtual 64	rx/subjects/SubjectSubscriptionManager$SubjectObserver:accept	(Ljava/lang/Object;Lrx/internal/operators/NotificationLite;)V
      //   76: aload_0
      //   77: monitorenter
      //   78: aload_0
      //   79: getfield 66	rx/subjects/SubjectSubscriptionManager$SubjectObserver:queue	Ljava/util/List;
      //   82: astore_1
      //   83: aload_0
      //   84: aconst_null
      //   85: putfield 66	rx/subjects/SubjectSubscriptionManager$SubjectObserver:queue	Ljava/util/List;
      //   88: aload_1
      //   89: ifnonnull +28 -> 117
      //   92: aload_0
      //   93: iconst_0
      //   94: putfield 42	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
      //   97: iconst_1
      //   98: istore 5
      //   100: aload_0
      //   101: monitorexit
      //   102: iload 5
      //   104: ifne +12 -> 116
      //   107: aload_0
      //   108: monitorenter
      //   109: aload_0
      //   110: iconst_0
      //   111: putfield 42	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
      //   114: aload_0
      //   115: monitorexit
      //   116: return
      //   117: aload_0
      //   118: monitorexit
      //   119: goto -113 -> 6
      //   122: astore 6
      //   124: aload_0
      //   125: monitorexit
      //   126: aload 6
      //   128: athrow
      //   129: astore 9
      //   131: aload_0
      //   132: monitorexit
      //   133: aload 9
      //   135: athrow
      //   136: astore 8
      //   138: aload_0
      //   139: monitorexit
      //   140: aload 8
      //   142: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   10	40	43	finally
      //   70	78	43	finally
      //   126	129	43	finally
      //   78	102	122	finally
      //   117	126	122	finally
      //   109	116	129	finally
      //   131	133	129	finally
      //   52	59	136	finally
      //   138	140	136	finally
    }

    protected void emitNext(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      if (!this.fastPath)
        monitorenter;
      try
      {
        this.first = false;
        if (this.emitting)
        {
          if (this.queue == null)
            this.queue = new ArrayList();
          this.queue.add(paramObject);
          monitorexit;
        }
        else
        {
          monitorexit;
          this.fastPath = true;
          paramNotificationLite.accept(this.actual, paramObject);
        }
      }
      finally
      {
        monitorexit;
      }
    }

    protected Observer<? super T> getActual()
    {
      return this.actual;
    }

    public <I> I index()
    {
      return this.index;
    }

    public void index(Object paramObject)
    {
      this.index = paramObject;
    }

    public void onCompleted()
    {
      this.actual.onCompleted();
    }

    public void onError(Throwable paramThrowable)
    {
      this.actual.onError(paramThrowable);
    }

    public void onNext(T paramT)
    {
      this.actual.onNext(paramT);
    }
  }

  protected static final class State<T>
  {
    static final State EMPTY;
    static final SubjectSubscriptionManager.SubjectObserver[] NO_OBSERVERS = new SubjectSubscriptionManager.SubjectObserver[0];
    static final State TERMINATED = new State(true, NO_OBSERVERS);
    final SubjectSubscriptionManager.SubjectObserver[] observers;
    final boolean terminated;

    static
    {
      EMPTY = new State(false, NO_OBSERVERS);
    }

    public State(boolean paramBoolean, SubjectSubscriptionManager.SubjectObserver[] paramArrayOfSubjectObserver)
    {
      this.terminated = paramBoolean;
      this.observers = paramArrayOfSubjectObserver;
    }

    public State add(SubjectSubscriptionManager.SubjectObserver paramSubjectObserver)
    {
      int i = this.observers.length;
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = new SubjectSubscriptionManager.SubjectObserver[i + 1];
      System.arraycopy(this.observers, 0, arrayOfSubjectObserver, 0, i);
      arrayOfSubjectObserver[i] = paramSubjectObserver;
      return new State(this.terminated, arrayOfSubjectObserver);
    }

    public State remove(SubjectSubscriptionManager.SubjectObserver paramSubjectObserver)
    {
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver1 = this.observers;
      int i = arrayOfSubjectObserver1.length;
      if ((i == 1) && (arrayOfSubjectObserver1[0] == paramSubjectObserver))
        this = EMPTY;
      Object localObject;
      int j;
      int k;
      label44: int m;
      while (true)
      {
        return this;
        if (i == 0)
          continue;
        localObject = new SubjectSubscriptionManager.SubjectObserver[i - 1];
        j = 0;
        k = 0;
        if (j >= i)
          break;
        SubjectSubscriptionManager.SubjectObserver localSubjectObserver = arrayOfSubjectObserver1[j];
        if (localSubjectObserver == paramSubjectObserver)
          break label152;
        if (k == i - 1)
          continue;
        m = k + 1;
        localObject[k] = localSubjectObserver;
      }
      while (true)
      {
        j++;
        k = m;
        break label44;
        if (k == 0)
        {
          this = EMPTY;
          break;
        }
        if (k < i - 1)
        {
          SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver2 = new SubjectSubscriptionManager.SubjectObserver[k];
          System.arraycopy(localObject, 0, arrayOfSubjectObserver2, 0, k);
          localObject = arrayOfSubjectObserver2;
        }
        this = new State(this.terminated, localObject);
        break;
        label152: m = k;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.subjects.SubjectSubscriptionManager
 * JD-Core Version:    0.6.0
 */