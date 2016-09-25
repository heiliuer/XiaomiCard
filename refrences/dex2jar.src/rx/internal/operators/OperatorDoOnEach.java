package rx.internal.operators;

import java.util.Arrays;
import rx.Observable.Operator;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;

public class OperatorDoOnEach<T>
  implements Observable.Operator<T, T>
{
  private final Observer<? super T> doOnEachObserver;

  public OperatorDoOnEach(Observer<? super T> paramObserver)
  {
    this.doOnEachObserver = paramObserver;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      private boolean done = false;

      public void onCompleted()
      {
        if (this.done);
        while (true)
        {
          return;
          try
          {
            OperatorDoOnEach.this.doOnEachObserver.onCompleted();
            this.done = true;
            this.val$observer.onCompleted();
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwOrReport(localThrowable, this);
          }
        }
      }

      public void onError(Throwable paramThrowable)
      {
        Exceptions.throwIfFatal(paramThrowable);
        if (this.done);
        while (true)
        {
          return;
          this.done = true;
          try
          {
            OperatorDoOnEach.this.doOnEachObserver.onError(paramThrowable);
            this.val$observer.onError(paramThrowable);
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwIfFatal(localThrowable);
            Subscriber localSubscriber = this.val$observer;
            Throwable[] arrayOfThrowable = new Throwable[2];
            arrayOfThrowable[0] = paramThrowable;
            arrayOfThrowable[1] = localThrowable;
            localSubscriber.onError(new CompositeException(Arrays.asList(arrayOfThrowable)));
          }
        }
      }

      public void onNext(T paramT)
      {
        if (this.done);
        while (true)
        {
          return;
          try
          {
            OperatorDoOnEach.this.doOnEachObserver.onNext(paramT);
            this.val$observer.onNext(paramT);
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwOrReport(localThrowable, this, paramT);
          }
        }
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorDoOnEach
 * JD-Core Version:    0.6.0
 */