package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.SingleDelayedProducer;

public final class OperatorAny<T>
  implements Observable.Operator<Boolean, T>
{
  private final Func1<? super T, Boolean> predicate;
  private final boolean returnOnEmpty;

  public OperatorAny(Func1<? super T, Boolean> paramFunc1, boolean paramBoolean)
  {
    this.predicate = paramFunc1;
    this.returnOnEmpty = paramBoolean;
  }

  public Subscriber<? super T> call(Subscriber<? super Boolean> paramSubscriber)
  {
    SingleDelayedProducer localSingleDelayedProducer = new SingleDelayedProducer(paramSubscriber);
    1 local1 = new Subscriber(localSingleDelayedProducer, paramSubscriber)
    {
      boolean done;
      boolean hasElements;

      public void onCompleted()
      {
        if (!this.done)
        {
          this.done = true;
          if (!this.hasElements)
            break label31;
          this.val$producer.setValue(Boolean.valueOf(false));
        }
        while (true)
        {
          return;
          label31: this.val$producer.setValue(Boolean.valueOf(OperatorAny.this.returnOnEmpty));
        }
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$child.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        this.hasElements = true;
        try
        {
          boolean bool1 = ((Boolean)OperatorAny.this.predicate.call(paramT)).booleanValue();
          if ((bool1) && (!this.done))
          {
            this.done = true;
            SingleDelayedProducer localSingleDelayedProducer = this.val$producer;
            if (!OperatorAny.this.returnOnEmpty)
            {
              bool2 = true;
              localSingleDelayedProducer.setValue(Boolean.valueOf(bool2));
              unsubscribe();
            }
          }
          else
          {
            return;
          }
        }
        catch (Throwable localThrowable)
        {
          while (true)
          {
            Exceptions.throwOrReport(localThrowable, this, paramT);
            continue;
            boolean bool2 = false;
          }
        }
      }
    };
    paramSubscriber.add(local1);
    paramSubscriber.setProducer(localSingleDelayedProducer);
    return local1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorAny
 * JD-Core Version:    0.6.0
 */