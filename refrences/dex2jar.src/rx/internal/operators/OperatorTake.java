package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;

public final class OperatorTake<T>
  implements Observable.Operator<T, T>
{
  final int limit;

  public OperatorTake(int paramInt)
  {
    this.limit = paramInt;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    1 local1 = new Subscriber(paramSubscriber)
    {
      boolean completed;
      int count;

      public void onCompleted()
      {
        if (!this.completed)
        {
          this.completed = true;
          this.val$child.onCompleted();
        }
      }

      public void onError(Throwable paramThrowable)
      {
        if (!this.completed)
          this.completed = true;
        try
        {
          this.val$child.onError(paramThrowable);
          return;
        }
        finally
        {
          unsubscribe();
        }
        throw localObject;
      }

      public void onNext(T paramT)
      {
        int j;
        if (!isUnsubscribed())
        {
          int i = this.count;
          this.count = (i + 1);
          if (i < OperatorTake.this.limit)
          {
            if (this.count != OperatorTake.this.limit)
              break label82;
            j = 1;
          }
        }
        while (true)
        {
          this.val$child.onNext(paramT);
          if ((j != 0) && (!this.completed))
            this.completed = true;
          try
          {
            this.val$child.onCompleted();
            return;
            label82: j = 0;
          }
          finally
          {
            unsubscribe();
          }
        }
      }

      public void setProducer(Producer paramProducer)
      {
        this.val$child.setProducer(new Producer(paramProducer)
        {
          final AtomicLong requested = new AtomicLong(0L);

          public void request(long paramLong)
          {
            if ((paramLong > 0L) && (!OperatorTake.1.this.completed));
            while (true)
            {
              long l1 = this.requested.get();
              long l2 = Math.min(paramLong, OperatorTake.this.limit - l1);
              if (l2 == 0L);
              while (true)
              {
                return;
                if (!this.requested.compareAndSet(l1, l1 + l2))
                  break;
                this.val$producer.request(l2);
              }
            }
          }
        });
      }
    };
    if (this.limit == 0)
    {
      paramSubscriber.onCompleted();
      local1.unsubscribe();
    }
    paramSubscriber.add(local1);
    return local1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorTake
 * JD-Core Version:    0.6.0
 */