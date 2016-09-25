package rx.internal.operators;

import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;

public final class OperatorSkip<T>
  implements Observable.Operator<T, T>
{
  final int toSkip;

  public OperatorSkip(int paramInt)
  {
    this.toSkip = paramInt;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      int skipped = 0;

      public void onCompleted()
      {
        this.val$child.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$child.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        if (this.skipped >= OperatorSkip.this.toSkip)
          this.val$child.onNext(paramT);
        while (true)
        {
          return;
          this.skipped = (1 + this.skipped);
        }
      }

      public void setProducer(Producer paramProducer)
      {
        this.val$child.setProducer(paramProducer);
        paramProducer.request(OperatorSkip.this.toSkip);
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSkip
 * JD-Core Version:    0.6.0
 */