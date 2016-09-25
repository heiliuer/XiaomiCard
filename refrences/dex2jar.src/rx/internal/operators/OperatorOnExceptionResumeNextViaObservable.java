package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public final class OperatorOnExceptionResumeNextViaObservable<T>
  implements Observable.Operator<T, T>
{
  final Observable<? extends T> resumeSequence;

  public OperatorOnExceptionResumeNextViaObservable(Observable<? extends T> paramObservable)
  {
    this.resumeSequence = paramObservable;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    1 local1 = new Subscriber(paramSubscriber)
    {
      private boolean done = false;

      public void onCompleted()
      {
        if (this.done);
        while (true)
        {
          return;
          this.done = true;
          this.val$child.onCompleted();
        }
      }

      public void onError(Throwable paramThrowable)
      {
        if (this.done)
          Exceptions.throwIfFatal(paramThrowable);
        while (true)
        {
          return;
          this.done = true;
          if ((paramThrowable instanceof Exception))
          {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(paramThrowable);
            unsubscribe();
            OperatorOnExceptionResumeNextViaObservable.this.resumeSequence.unsafeSubscribe(this.val$child);
            continue;
          }
          this.val$child.onError(paramThrowable);
        }
      }

      public void onNext(T paramT)
      {
        if (this.done);
        while (true)
        {
          return;
          this.val$child.onNext(paramT);
        }
      }

      public void setProducer(Producer paramProducer)
      {
        this.val$child.setProducer(new Producer(paramProducer)
        {
          public void request(long paramLong)
          {
            this.val$producer.request(paramLong);
          }
        });
      }
    };
    paramSubscriber.add(local1);
    return local1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorOnExceptionResumeNextViaObservable
 * JD-Core Version:    0.6.0
 */