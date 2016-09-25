package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

public final class BufferUntilSubscriber<T> extends Subject<T, T>
{
  private static final Observer EMPTY_OBSERVER = new Observer()
  {
    public void onCompleted()
    {
    }

    public void onError(Throwable paramThrowable)
    {
    }

    public void onNext(Object paramObject)
    {
    }
  };
  private boolean forward = false;
  final State<T> state;

  private BufferUntilSubscriber(State<T> paramState)
  {
    super(new OnSubscribeAction(paramState));
    this.state = paramState;
  }

  public static <T> BufferUntilSubscriber<T> create()
  {
    return new BufferUntilSubscriber(new State());
  }

  private void emit(Object paramObject)
  {
    synchronized (this.state.guard)
    {
      this.state.buffer.add(paramObject);
      if ((this.state.get() != null) && (!this.state.emitting))
      {
        this.forward = true;
        this.state.emitting = true;
      }
      if (this.forward)
      {
        Object localObject3 = this.state.buffer.poll();
        if (localObject3 != null)
          this.state.nl.accept((Observer)this.state.get(), localObject3);
      }
    }
  }

  public boolean hasObservers()
  {
    while (true)
    {
      synchronized (this.state.guard)
      {
        if (this.state.get() != null)
        {
          i = 1;
          return i;
        }
      }
      int i = 0;
    }
  }

  public void onCompleted()
  {
    if (this.forward)
      ((Observer)this.state.get()).onCompleted();
    while (true)
    {
      return;
      emit(this.state.nl.completed());
    }
  }

  public void onError(Throwable paramThrowable)
  {
    if (this.forward)
      ((Observer)this.state.get()).onError(paramThrowable);
    while (true)
    {
      return;
      emit(this.state.nl.error(paramThrowable));
    }
  }

  public void onNext(T paramT)
  {
    if (this.forward)
      ((Observer)this.state.get()).onNext(paramT);
    while (true)
    {
      return;
      emit(this.state.nl.next(paramT));
    }
  }

  static final class OnSubscribeAction<T>
    implements Observable.OnSubscribe<T>
  {
    final BufferUntilSubscriber.State<T> state;

    public OnSubscribeAction(BufferUntilSubscriber.State<T> paramState)
    {
      this.state = paramState;
    }

    public void call(Subscriber<? super T> paramSubscriber)
    {
      if (this.state.casObserverRef(null, paramSubscriber))
      {
        paramSubscriber.add(Subscriptions.create(new Action0()
        {
          public void call()
          {
            BufferUntilSubscriber.OnSubscribeAction.this.state.set(BufferUntilSubscriber.EMPTY_OBSERVER);
          }
        }));
        int i = 0;
        while (true)
        {
          synchronized (this.state.guard)
          {
            if (this.state.emitting)
              continue;
            this.state.emitting = true;
            i = 1;
            if (i == 0)
              break;
            NotificationLite localNotificationLite = NotificationLite.instance();
            Object localObject3 = this.state.buffer.poll();
            if (localObject3 != null)
              localNotificationLite.accept((Observer)this.state.get(), localObject3);
          }
          synchronized (this.state.guard)
          {
            if (this.state.buffer.isEmpty())
            {
              this.state.emitting = false;
              break;
            }
          }
        }
      }
      paramSubscriber.onError(new IllegalStateException("Only one subscriber allowed!"));
    }
  }

  static final class State<T> extends AtomicReference<Observer<? super T>>
  {
    final ConcurrentLinkedQueue<Object> buffer = new ConcurrentLinkedQueue();
    boolean emitting = false;
    final Object guard = new Object();
    final NotificationLite<T> nl = NotificationLite.instance();

    boolean casObserverRef(Observer<? super T> paramObserver1, Observer<? super T> paramObserver2)
    {
      return compareAndSet(paramObserver1, paramObserver2);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.BufferUntilSubscriber
 * JD-Core Version:    0.6.0
 */