package rx.internal.operators;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;

public final class OperatorSingle<T>
  implements Observable.Operator<T, T>
{
  private final T defaultValue;
  private final boolean hasDefaultValue;

  private OperatorSingle()
  {
    this(false, null);
  }

  public OperatorSingle(T paramT)
  {
    this(true, paramT);
  }

  private OperatorSingle(boolean paramBoolean, T paramT)
  {
    this.hasDefaultValue = paramBoolean;
    this.defaultValue = paramT;
  }

  public static <T> OperatorSingle<T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    ParentSubscriber localParentSubscriber = new ParentSubscriber(paramSubscriber, this.hasDefaultValue, this.defaultValue);
    paramSubscriber.setProducer(new Producer(localParentSubscriber)
    {
      private final AtomicBoolean requestedTwo = new AtomicBoolean(false);

      public void request(long paramLong)
      {
        if ((paramLong > 0L) && (this.requestedTwo.compareAndSet(false, true)))
          this.val$parent.requestMore(2L);
      }
    });
    paramSubscriber.add(localParentSubscriber);
    return localParentSubscriber;
  }

  private static final class ParentSubscriber<T> extends Subscriber<T>
  {
    private final Subscriber<? super T> child;
    private final T defaultValue;
    private final boolean hasDefaultValue;
    private boolean hasTooManyElements = false;
    private boolean isNonEmpty = false;
    private T value;

    ParentSubscriber(Subscriber<? super T> paramSubscriber, boolean paramBoolean, T paramT)
    {
      this.child = paramSubscriber;
      this.hasDefaultValue = paramBoolean;
      this.defaultValue = paramT;
    }

    public void onCompleted()
    {
      if (this.hasTooManyElements);
      while (true)
      {
        return;
        if (this.isNonEmpty)
        {
          this.child.onNext(this.value);
          this.child.onCompleted();
          continue;
        }
        if (this.hasDefaultValue)
        {
          this.child.onNext(this.defaultValue);
          this.child.onCompleted();
          continue;
        }
        this.child.onError(new NoSuchElementException("Sequence contains no elements"));
      }
    }

    public void onError(Throwable paramThrowable)
    {
      this.child.onError(paramThrowable);
    }

    public void onNext(T paramT)
    {
      if (this.isNonEmpty)
      {
        this.hasTooManyElements = true;
        this.child.onError(new IllegalArgumentException("Sequence contains too many elements"));
        unsubscribe();
      }
      while (true)
      {
        return;
        this.value = paramT;
        this.isNonEmpty = true;
      }
    }

    void requestMore(long paramLong)
    {
      request(paramLong);
    }
  }

  private static class Holder
  {
    static final OperatorSingle<?> INSTANCE = new OperatorSingle(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorSingle
 * JD-Core Version:    0.6.0
 */