package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import rx.Observable.Operator;
import rx.Subscriber;

public class OperatorSkipLast<T>
  implements Observable.Operator<T, T>
{
  private final int count;

  public OperatorSkipLast(int paramInt)
  {
    if (paramInt < 0)
      throw new IndexOutOfBoundsException("count could not be negative");
    this.count = paramInt;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      private final Deque<Object> deque = new ArrayDeque();
      private final NotificationLite<T> on = NotificationLite.instance();

      public void onCompleted()
      {
        this.val$subscriber.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$subscriber.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        if (OperatorSkipLast.this.count == 0)
        {
          this.val$subscriber.onNext(paramT);
          return;
        }
        if (this.deque.size() == OperatorSkipLast.this.count)
          this.val$subscriber.onNext(this.on.getValue(this.deque.removeFirst()));
        while (true)
        {
          this.deque.offerLast(this.on.next(paramT));
          break;
          request(1L);
        }
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSkipLast
 * JD-Core Version:    0.6.0
 */