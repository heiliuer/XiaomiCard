package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import rx.Observable.Operator;
import rx.Subscriber;

public final class OperatorTakeLast<T>
  implements Observable.Operator<T, T>
{
  private final int count;

  public OperatorTakeLast(int paramInt)
  {
    if (paramInt < 0)
      throw new IndexOutOfBoundsException("count cannot be negative");
    this.count = paramInt;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    ArrayDeque localArrayDeque = new ArrayDeque();
    NotificationLite localNotificationLite = NotificationLite.instance();
    TakeLastQueueProducer localTakeLastQueueProducer = new TakeLastQueueProducer(localNotificationLite, localArrayDeque, paramSubscriber);
    paramSubscriber.setProducer(localTakeLastQueueProducer);
    return new Subscriber(paramSubscriber, localArrayDeque, localNotificationLite, localTakeLastQueueProducer, paramSubscriber)
    {
      public void onCompleted()
      {
        this.val$deque.offer(this.val$notification.completed());
        this.val$producer.startEmitting();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$deque.clear();
        this.val$subscriber.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        if (OperatorTakeLast.this.count == 0);
        while (true)
        {
          return;
          if (this.val$deque.size() == OperatorTakeLast.this.count)
            this.val$deque.removeFirst();
          this.val$deque.offerLast(this.val$notification.next(paramT));
        }
      }

      public void onStart()
      {
        request(9223372036854775807L);
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTakeLast
 * JD-Core Version:    0.6.0
 */