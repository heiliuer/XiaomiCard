package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.SerialSubscription;

public final class OperatorOnErrorResumeNextViaFunction<T>
  implements Observable.Operator<T, T>
{
  private final Func1<Throwable, ? extends Observable<? extends T>> resumeFunction;

  public OperatorOnErrorResumeNextViaFunction(Func1<Throwable, ? extends Observable<? extends T>> paramFunc1)
  {
    this.resumeFunction = paramFunc1;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    ProducerArbiter localProducerArbiter = new ProducerArbiter();
    SerialSubscription localSerialSubscription = new SerialSubscription();
    1 local1 = new Subscriber(paramSubscriber, localProducerArbiter, localSerialSubscription)
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
          try
          {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(paramThrowable);
            unsubscribe();
            1 local1 = new Subscriber()
            {
              public void onCompleted()
              {
                OperatorOnErrorResumeNextViaFunction.1.this.val$child.onCompleted();
              }

              public void onError(Throwable paramThrowable)
              {
                OperatorOnErrorResumeNextViaFunction.1.this.val$child.onError(paramThrowable);
              }

              public void onNext(T paramT)
              {
                OperatorOnErrorResumeNextViaFunction.1.this.val$child.onNext(paramT);
              }

              public void setProducer(Producer paramProducer)
              {
                OperatorOnErrorResumeNextViaFunction.1.this.val$pa.setProducer(paramProducer);
              }
            };
            this.val$ssub.set(local1);
            ((Observable)OperatorOnErrorResumeNextViaFunction.this.resumeFunction.call(paramThrowable)).unsafeSubscribe(local1);
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwOrReport(localThrowable, this.val$child);
          }
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
        this.val$pa.setProducer(paramProducer);
      }
    };
    paramSubscriber.add(localSerialSubscription);
    localSerialSubscription.set(local1);
    paramSubscriber.setProducer(localProducerArbiter);
    return local1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorOnErrorResumeNextViaFunction
 * JD-Core Version:    0.6.0
 */