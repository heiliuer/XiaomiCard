package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

public final class OperatorBufferWithSize<T>
  implements Observable.Operator<List<T>, T>
{
  final int count;
  final int skip;

  public OperatorBufferWithSize(int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 0)
      throw new IllegalArgumentException("count must be greater than 0");
    if (paramInt2 <= 0)
      throw new IllegalArgumentException("skip must be greater than 0");
    this.count = paramInt1;
    this.skip = paramInt2;
  }

  public Subscriber<? super T> call(Subscriber<? super List<T>> paramSubscriber)
  {
    if (this.count == this.skip);
    for (Object localObject = new Subscriber(paramSubscriber, paramSubscriber)
    {
      List<T> buffer;

      public void onCompleted()
      {
        List localList = this.buffer;
        this.buffer = null;
        if (localList != null);
        try
        {
          this.val$child.onNext(localList);
          this.val$child.onCompleted();
          return;
        }
        catch (Throwable localThrowable)
        {
          while (true)
            Exceptions.throwOrReport(localThrowable, this);
        }
      }

      public void onError(Throwable paramThrowable)
      {
        this.buffer = null;
        this.val$child.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        if (this.buffer == null)
          this.buffer = new ArrayList(OperatorBufferWithSize.this.count);
        this.buffer.add(paramT);
        if (this.buffer.size() == OperatorBufferWithSize.this.count)
        {
          List localList = this.buffer;
          this.buffer = null;
          this.val$child.onNext(localList);
        }
      }

      public void setProducer(Producer paramProducer)
      {
        this.val$child.setProducer(new Producer(paramProducer)
        {
          private volatile boolean infinite = false;

          public void request(long paramLong)
          {
            if (this.infinite);
            while (true)
            {
              return;
              if (paramLong >= 9223372036854775807L / OperatorBufferWithSize.this.count)
              {
                this.infinite = true;
                this.val$producer.request(9223372036854775807L);
                continue;
              }
              this.val$producer.request(paramLong * OperatorBufferWithSize.this.count);
            }
          }
        });
      }
    }
    ; ; localObject = new Subscriber(paramSubscriber, paramSubscriber)
    {
      final List<List<T>> chunks = new LinkedList();
      int index;

      public void onCompleted()
      {
        try
        {
          Iterator localIterator = this.chunks.iterator();
          while (true)
            if (localIterator.hasNext())
            {
              List localList = (List)localIterator.next();
              try
              {
                this.val$child.onNext(localList);
              }
              catch (Throwable localThrowable)
              {
                Exceptions.throwOrReport(localThrowable, this);
                this.chunks.clear();
              }
            }
          while (true)
          {
            return;
            this.val$child.onCompleted();
            this.chunks.clear();
          }
        }
        finally
        {
          this.chunks.clear();
        }
        throw localObject;
      }

      public void onError(Throwable paramThrowable)
      {
        this.chunks.clear();
        this.val$child.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        int i = this.index;
        this.index = (i + 1);
        if (i % OperatorBufferWithSize.this.skip == 0)
          this.chunks.add(new ArrayList(OperatorBufferWithSize.this.count));
        Iterator localIterator = this.chunks.iterator();
        while (localIterator.hasNext())
        {
          List localList = (List)localIterator.next();
          localList.add(paramT);
          if (localList.size() != OperatorBufferWithSize.this.count)
            continue;
          localIterator.remove();
          this.val$child.onNext(localList);
        }
      }

      public void setProducer(Producer paramProducer)
      {
        this.val$child.setProducer(new Producer(paramProducer)
        {
          private volatile boolean firstRequest = true;
          private volatile boolean infinite = false;

          private void requestInfinite()
          {
            this.infinite = true;
            this.val$producer.request(9223372036854775807L);
          }

          public void request(long paramLong)
          {
            if (paramLong == 0L);
            while (true)
            {
              return;
              if (paramLong < 0L)
                throw new IllegalArgumentException("request a negative number: " + paramLong);
              if (this.infinite)
                continue;
              if (paramLong == 9223372036854775807L)
              {
                requestInfinite();
                continue;
              }
              if (this.firstRequest)
              {
                this.firstRequest = false;
                if (paramLong - 1L >= (9223372036854775807L - OperatorBufferWithSize.this.count) / OperatorBufferWithSize.this.skip)
                {
                  requestInfinite();
                  continue;
                }
                this.val$producer.request(OperatorBufferWithSize.this.count + OperatorBufferWithSize.this.skip * (paramLong - 1L));
                continue;
              }
              if (paramLong >= 9223372036854775807L / OperatorBufferWithSize.this.skip)
              {
                requestInfinite();
                continue;
              }
              this.val$producer.request(paramLong * OperatorBufferWithSize.this.skip);
            }
          }
        });
      }
    })
      return localObject;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorBufferWithSize
 * JD-Core Version:    0.6.0
 */