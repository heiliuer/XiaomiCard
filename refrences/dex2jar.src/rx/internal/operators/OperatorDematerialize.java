package rx.internal.operators;

import rx.Notification;
import rx.Observable.Operator;
import rx.Subscriber;

public final class OperatorDematerialize<T>
  implements Observable.Operator<T, Notification<T>>
{
  public static OperatorDematerialize instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super Notification<T>> call(Subscriber<? super T> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      boolean terminated;

      public void onCompleted()
      {
        if (!this.terminated)
        {
          this.terminated = true;
          this.val$child.onCompleted();
        }
      }

      public void onError(Throwable paramThrowable)
      {
        if (!this.terminated)
        {
          this.terminated = true;
          this.val$child.onError(paramThrowable);
        }
      }

      public void onNext(Notification<T> paramNotification)
      {
        switch (OperatorDematerialize.2.$SwitchMap$rx$Notification$Kind[paramNotification.getKind().ordinal()])
        {
        default:
        case 1:
        case 2:
        case 3:
        }
        while (true)
        {
          return;
          if (this.terminated)
            continue;
          this.val$child.onNext(paramNotification.getValue());
          continue;
          onError(paramNotification.getThrowable());
          continue;
          onCompleted();
        }
      }
    };
  }

  private static final class Holder
  {
    static final OperatorDematerialize<Object> INSTANCE = new OperatorDematerialize(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorDematerialize
 * JD-Core Version:    0.6.0
 */