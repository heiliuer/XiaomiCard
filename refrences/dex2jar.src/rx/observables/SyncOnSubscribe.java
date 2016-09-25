package rx.observables;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.internal.operators.BackpressureUtils;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

@Experimental
public abstract class SyncOnSubscribe<S, T>
  implements Observable.OnSubscribe<T>
{
  @Experimental
  public static <S, T> Observable.OnSubscribe<T> createSingleState(Func0<? extends S> paramFunc0, Action2<? super S, ? super Observer<? super T>> paramAction2)
  {
    return new SyncOnSubscribeImpl(paramFunc0, new Func2(paramAction2)
    {
      public S call(S paramS, Observer<? super T> paramObserver)
      {
        SyncOnSubscribe.this.call(paramS, paramObserver);
        return paramS;
      }
    });
  }

  @Experimental
  public static <S, T> Observable.OnSubscribe<T> createSingleState(Func0<? extends S> paramFunc0, Action2<? super S, ? super Observer<? super T>> paramAction2, Action1<? super S> paramAction1)
  {
    return new SyncOnSubscribeImpl(paramFunc0, new Func2(paramAction2)
    {
      public S call(S paramS, Observer<? super T> paramObserver)
      {
        SyncOnSubscribe.this.call(paramS, paramObserver);
        return paramS;
      }
    }
    , paramAction1, null);
  }

  @Experimental
  public static <S, T> Observable.OnSubscribe<T> createStateful(Func0<? extends S> paramFunc0, Func2<? super S, ? super Observer<? super T>, ? extends S> paramFunc2)
  {
    return new SyncOnSubscribeImpl(paramFunc0, paramFunc2);
  }

  @Experimental
  public static <S, T> Observable.OnSubscribe<T> createStateful(Func0<? extends S> paramFunc0, Func2<? super S, ? super Observer<? super T>, ? extends S> paramFunc2, Action1<? super S> paramAction1)
  {
    return new SyncOnSubscribeImpl(paramFunc0, paramFunc2, paramAction1, null);
  }

  @Experimental
  public static <T> Observable.OnSubscribe<T> createStateless(Action1<? super Observer<? super T>> paramAction1)
  {
    return new SyncOnSubscribeImpl(new Func2(paramAction1)
    {
      public Void call(Void paramVoid, Observer<? super T> paramObserver)
      {
        SyncOnSubscribe.this.call(paramObserver);
        return paramVoid;
      }
    });
  }

  @Experimental
  public static <T> Observable.OnSubscribe<T> createStateless(Action1<? super Observer<? super T>> paramAction1, Action0 paramAction0)
  {
    return new SyncOnSubscribeImpl(new Func2(paramAction1)
    {
      public Void call(Void paramVoid, Observer<? super T> paramObserver)
      {
        SyncOnSubscribe.this.call(paramObserver);
        return null;
      }
    }
    , new Action1(paramAction0)
    {
      public void call(Void paramVoid)
      {
        SyncOnSubscribe.this.call();
      }
    });
  }

  public final void call(Subscriber<? super T> paramSubscriber)
  {
    SubscriptionProducer localSubscriptionProducer = new SubscriptionProducer(paramSubscriber, this, generateState(), null);
    paramSubscriber.add(localSubscriptionProducer);
    paramSubscriber.setProducer(localSubscriptionProducer);
  }

  protected abstract S generateState();

  protected abstract S next(S paramS, Observer<? super T> paramObserver);

  protected void onUnsubscribe(S paramS)
  {
  }

  private static class SubscriptionProducer<S, T> extends AtomicLong
    implements Producer, Subscription, Observer<T>
  {
    private static final long serialVersionUID = -3736864024352728072L;
    private final Subscriber<? super T> actualSubscriber;
    private boolean hasTerminated;
    private boolean onNextCalled;
    private final SyncOnSubscribe<S, T> parent;
    private S state;

    private SubscriptionProducer(Subscriber<? super T> paramSubscriber, SyncOnSubscribe<S, T> paramSyncOnSubscribe, S paramS)
    {
      this.actualSubscriber = paramSubscriber;
      this.parent = paramSyncOnSubscribe;
      this.state = paramS;
    }

    private void doUnsubscribe()
    {
      this.parent.onUnsubscribe(this.state);
    }

    private void fastpath()
    {
      SyncOnSubscribe localSyncOnSubscribe = this.parent;
      Subscriber localSubscriber = this.actualSubscriber;
      try
      {
        do
        {
          this.onNextCalled = false;
          nextIteration(localSyncOnSubscribe);
        }
        while (!tryUnsubscribe());
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          handleThrownError(localSubscriber, localThrowable);
      }
    }

    private void handleThrownError(Subscriber<? super T> paramSubscriber, Throwable paramThrowable)
    {
      if (this.hasTerminated)
        RxJavaPlugins.getInstance().getErrorHandler().handleError(paramThrowable);
      while (true)
      {
        return;
        this.hasTerminated = true;
        paramSubscriber.onError(paramThrowable);
        unsubscribe();
      }
    }

    private void nextIteration(SyncOnSubscribe<S, T> paramSyncOnSubscribe)
    {
      this.state = paramSyncOnSubscribe.next(this.state, this);
    }

    private void slowPath(long paramLong)
    {
      SyncOnSubscribe localSyncOnSubscribe = this.parent;
      Subscriber localSubscriber = this.actualSubscriber;
      long l1 = paramLong;
      long l2 = l1;
      while (true)
      {
        try
        {
          this.onNextCalled = false;
          nextIteration(localSyncOnSubscribe);
          if (tryUnsubscribe())
            return;
        }
        catch (Throwable localThrowable)
        {
          handleThrownError(localSubscriber, localThrowable);
          continue;
          if (this.onNextCalled)
            l2 -= 1L;
        }
        if (l2 != 0L)
          continue;
        l1 = addAndGet(-l1);
        if (l1 > 0L)
          break;
        tryUnsubscribe();
      }
    }

    private boolean tryUnsubscribe()
    {
      if ((this.hasTerminated) || (get() < -1L))
      {
        set(-1L);
        doUnsubscribe();
      }
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean isUnsubscribed()
    {
      if (get() < 0L);
      for (int i = 1; ; i = 0)
        return i;
    }

    public void onCompleted()
    {
      if (this.hasTerminated)
        throw new IllegalStateException("Terminal event already emitted.");
      this.hasTerminated = true;
      if (!this.actualSubscriber.isUnsubscribed())
        this.actualSubscriber.onCompleted();
    }

    public void onError(Throwable paramThrowable)
    {
      if (this.hasTerminated)
        throw new IllegalStateException("Terminal event already emitted.");
      this.hasTerminated = true;
      if (!this.actualSubscriber.isUnsubscribed())
        this.actualSubscriber.onError(paramThrowable);
    }

    public void onNext(T paramT)
    {
      if (this.onNextCalled)
        throw new IllegalStateException("onNext called multiple times!");
      this.onNextCalled = true;
      this.actualSubscriber.onNext(paramT);
    }

    public void request(long paramLong)
    {
      if ((paramLong > 0L) && (BackpressureUtils.getAndAddRequest(this, paramLong) == 0L))
      {
        if (paramLong != 9223372036854775807L)
          break label29;
        fastpath();
      }
      while (true)
      {
        return;
        label29: slowPath(paramLong);
      }
    }

    public void unsubscribe()
    {
      long l = get();
      if (compareAndSet(0L, -1L))
        doUnsubscribe();
      while (true)
      {
        return;
        if (!compareAndSet(l, -2L))
          break;
      }
    }
  }

  private static final class SyncOnSubscribeImpl<S, T> extends SyncOnSubscribe<S, T>
  {
    private final Func0<? extends S> generator;
    private final Func2<? super S, ? super Observer<? super T>, ? extends S> next;
    private final Action1<? super S> onUnsubscribe;

    public SyncOnSubscribeImpl(Func0<? extends S> paramFunc0, Func2<? super S, ? super Observer<? super T>, ? extends S> paramFunc2)
    {
      this(paramFunc0, paramFunc2, null);
    }

    private SyncOnSubscribeImpl(Func0<? extends S> paramFunc0, Func2<? super S, ? super Observer<? super T>, ? extends S> paramFunc2, Action1<? super S> paramAction1)
    {
      this.generator = paramFunc0;
      this.next = paramFunc2;
      this.onUnsubscribe = paramAction1;
    }

    public SyncOnSubscribeImpl(Func2<S, Observer<? super T>, S> paramFunc2)
    {
      this(null, paramFunc2, null);
    }

    public SyncOnSubscribeImpl(Func2<S, Observer<? super T>, S> paramFunc2, Action1<? super S> paramAction1)
    {
      this(null, paramFunc2, paramAction1);
    }

    protected S generateState()
    {
      if (this.generator == null);
      for (Object localObject = null; ; localObject = this.generator.call())
        return localObject;
    }

    protected S next(S paramS, Observer<? super T> paramObserver)
    {
      return this.next.call(paramS, paramObserver);
    }

    protected void onUnsubscribe(S paramS)
    {
      if (this.onUnsubscribe != null)
        this.onUnsubscribe.call(paramS);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.observables.SyncOnSubscribe
 * JD-Core Version:    0.6.0
 */