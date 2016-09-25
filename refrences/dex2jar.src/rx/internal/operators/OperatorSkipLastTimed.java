package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Timestamped;

public class OperatorSkipLastTimed<T>
  implements Observable.Operator<T, T>
{
  private final Scheduler scheduler;
  private final long timeInMillis;

  public OperatorSkipLastTimed(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.timeInMillis = paramTimeUnit.toMillis(paramLong);
    this.scheduler = paramScheduler;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      private Deque<Timestamped<T>> buffer = new ArrayDeque();

      private void emitItemsOutOfWindow(long paramLong)
      {
        long l = paramLong - OperatorSkipLastTimed.this.timeInMillis;
        while (!this.buffer.isEmpty())
        {
          Timestamped localTimestamped = (Timestamped)this.buffer.getFirst();
          if (localTimestamped.getTimestampMillis() >= l)
            break;
          this.buffer.removeFirst();
          this.val$subscriber.onNext(localTimestamped.getValue());
        }
      }

      public void onCompleted()
      {
        emitItemsOutOfWindow(OperatorSkipLastTimed.this.scheduler.now());
        this.val$subscriber.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.val$subscriber.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        long l = OperatorSkipLastTimed.this.scheduler.now();
        emitItemsOutOfWindow(l);
        this.buffer.offerLast(new Timestamped(l, paramT));
      }
    };
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSkipLastTimed
 * JD-Core Version:    0.6.0
 */