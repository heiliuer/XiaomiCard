package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

public final class OperatorSerialize<T>
  implements Observable.Operator<T, T>
{
  public static <T> OperatorSerialize<T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new SerializedSubscriber(new Subscriber(paramSubscriber, paramSubscriber)
    {
      public void onCompleted()
      {
        this.val$s.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$s.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        this.val$s.onNext(paramT);
      }
    });
  }

  private static final class Holder
  {
    static final OperatorSerialize<Object> INSTANCE = new OperatorSerialize(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSerialize
 * JD-Core Version:    0.6.0
 */