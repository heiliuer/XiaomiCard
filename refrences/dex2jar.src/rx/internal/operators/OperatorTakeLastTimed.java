package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;

public final class OperatorTakeLastTimed<T>
  implements Observable.Operator<T, T>
{
  private final long ageMillis;
  private final int count;
  private final Scheduler scheduler;

  public OperatorTakeLastTimed(int paramInt, long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    if (paramInt < 0)
      throw new IndexOutOfBoundsException("count could not be negative");
    this.ageMillis = paramTimeUnit.toMillis(paramLong);
    this.scheduler = paramScheduler;
    this.count = paramInt;
  }

  public OperatorTakeLastTimed(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.ageMillis = paramTimeUnit.toMillis(paramLong);
    this.scheduler = paramScheduler;
    this.count = -1;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    ArrayDeque localArrayDeque1 = new ArrayDeque();
    ArrayDeque localArrayDeque2 = new ArrayDeque();
    NotificationLite localNotificationLite = NotificationLite.instance();
    TakeLastQueueProducer localTakeLastQueueProducer = new TakeLastQueueProducer(localNotificationLite, localArrayDeque1, paramSubscriber);
    paramSubscriber.setProducer(localTakeLastQueueProducer);
    return new Subscriber(paramSubscriber, localArrayDeque1, localArrayDeque2, localNotificationLite, paramSubscriber, localTakeLastQueueProducer)
    {
      public void onCompleted()
      {
        runEvictionPolicy(OperatorTakeLastTimed.this.scheduler.now());
        this.val$timestampBuffer.clear();
        this.val$buffer.offer(this.val$notification.completed());
        this.val$producer.startEmitting();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$timestampBuffer.clear();
        this.val$buffer.clear();
        this.val$subscriber.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        long l = OperatorTakeLastTimed.this.scheduler.now();
        this.val$timestampBuffer.add(Long.valueOf(l));
        this.val$buffer.add(this.val$notification.next(paramT));
        runEvictionPolicy(l);
      }

      public void onStart()
      {
        request(9223372036854775807L);
      }

      protected void runEvictionPolicy(long paramLong)
      {
        while ((OperatorTakeLastTimed.this.count >= 0) && (this.val$buffer.size() > OperatorTakeLastTimed.this.count))
        {
          this.val$timestampBuffer.pollFirst();
          this.val$buffer.pollFirst();
        }
        while ((!this.val$buffer.isEmpty()) && (((Long)this.val$timestampBuffer.peekFirst()).longValue() < paramLong - OperatorTakeLastTimed.this.ageMillis))
        {
          this.val$timestampBuffer.pollFirst();
          this.val$buffer.pollFirst();
        }
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTakeLastTimed
 * JD-Core Version:    0.6.0
 */