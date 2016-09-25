package rx.internal.operators;

import java.util.Arrays;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public final class OperatorOnErrorReturn<T>
  implements Observable.Operator<T, T>
{
  final Func1<Throwable, ? extends T> resultFunction;

  public OperatorOnErrorReturn(Func1<Throwable, ? extends T> paramFunc1)
  {
    this.resultFunction = paramFunc1;
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
          try
          {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(paramThrowable);
            unsubscribe();
            Object localObject = OperatorOnErrorReturn.this.resultFunction.call(paramThrowable);
            this.val$child.onNext(localObject);
            this.val$child.onCompleted();
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwIfFatal(localThrowable);
            Subscriber localSubscriber = this.val$child;
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
 * Qualified Name:     rx.internal.operators.OperatorOnErrorReturn
 * JD-Core Version:    0.6.0
 */