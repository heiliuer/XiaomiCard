package rx.internal.util;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.schedulers.EventLoopsScheduler;

public final class ScalarSynchronousObservable<T> extends Observable<T>
{
  private final T t;

  protected ScalarSynchronousObservable(T paramT)
  {
    super(new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super T> paramSubscriber)
      {
        paramSubscriber.onNext(ScalarSynchronousObservable.this);
        paramSubscriber.onCompleted();
      }
    });
    this.t = paramT;
  }

  public static final <T> ScalarSynchronousObservable<T> create(T paramT)
  {
    return new ScalarSynchronousObservable(paramT);
  }

  public T get()
  {
    return this.t;
  }

  public <R> Observable<R> scalarFlatMap(Func1<? super T, ? extends Observable<? extends R>> paramFunc1)
  {
    return create(new Observable.OnSubscribe(paramFunc1)
    {
      public void call(Subscriber<? super R> paramSubscriber)
      {
        Observable localObservable = (Observable)this.val$func.call(ScalarSynchronousObservable.this.t);
        if (localObservable.getClass() == ScalarSynchronousObservable.class)
        {
          paramSubscriber.onNext(((ScalarSynchronousObservable)localObservable).t);
          paramSubscriber.onCompleted();
        }
        while (true)
        {
          return;
          localObservable.unsafeSubscribe(new Subscriber(paramSubscriber, paramSubscriber)
          {
            public void onCompleted()
            {
              this.val$child.onCompleted();
            }

            public void onError(Throwable paramThrowable)
            {
              this.val$child.onError(paramThrowable);
            }

            public void onNext(R paramR)
            {
              this.val$child.onNext(paramR);
            }
          });
        }
      }
    });
  }

  public Observable<T> scalarScheduleOn(Scheduler paramScheduler)
  {
    if ((paramScheduler instanceof EventLoopsScheduler));
    for (Observable localObservable = create(new DirectScheduledEmission((EventLoopsScheduler)paramScheduler, this.t)); ; localObservable = create(new NormalScheduledEmission(paramScheduler, this.t)))
      return localObservable;
  }

  static final class ScalarSynchronousAction<T>
    implements Action0
  {
    private final Subscriber<? super T> subscriber;
    private final T value;

    private ScalarSynchronousAction(Subscriber<? super T> paramSubscriber, T paramT)
    {
      this.subscriber = paramSubscriber;
      this.value = paramT;
    }

    public void call()
    {
      try
      {
        this.subscriber.onNext(this.value);
        this.subscriber.onCompleted();
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          this.subscriber.onError(localThrowable);
      }
    }
  }

  static final class NormalScheduledEmission<T>
    implements Observable.OnSubscribe<T>
  {
    private final Scheduler scheduler;
    private final T value;

    NormalScheduledEmission(Scheduler paramScheduler, T paramT)
    {
      this.scheduler = paramScheduler;
      this.value = paramT;
    }

    public void call(Subscriber<? super T> paramSubscriber)
    {
      Scheduler.Worker localWorker = this.scheduler.createWorker();
      paramSubscriber.add(localWorker);
      localWorker.schedule(new ScalarSynchronousObservable.ScalarSynchronousAction(paramSubscriber, this.value, null));
    }
  }

  static final class DirectScheduledEmission<T>
    implements Observable.OnSubscribe<T>
  {
    private final EventLoopsScheduler es;
    private final T value;

    DirectScheduledEmission(EventLoopsScheduler paramEventLoopsScheduler, T paramT)
    {
      this.es = paramEventLoopsScheduler;
      this.value = paramT;
    }

    public void call(Subscriber<? super T> paramSubscriber)
    {
      paramSubscriber.add(this.es.scheduleDirect(new ScalarSynchronousObservable.ScalarSynchronousAction(paramSubscriber, this.value, null)));
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.util.ScalarSynchronousObservable
 * JD-Core Version:    0.6.0
 */