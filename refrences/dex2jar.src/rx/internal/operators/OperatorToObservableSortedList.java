package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func2;
import rx.internal.producers.SingleDelayedProducer;

public final class OperatorToObservableSortedList<T>
  implements Observable.Operator<List<T>, T>
{
  private static Comparator DEFAULT_SORT_FUNCTION = new DefaultComparableFunction(null);
  private final int initialCapacity;
  private final Comparator<? super T> sortFunction;

  public OperatorToObservableSortedList(int paramInt)
  {
    this.sortFunction = DEFAULT_SORT_FUNCTION;
    this.initialCapacity = paramInt;
  }

  public OperatorToObservableSortedList(Func2<? super T, ? super T, Integer> paramFunc2, int paramInt)
  {
    this.initialCapacity = paramInt;
    this.sortFunction = new Comparator(paramFunc2)
    {
      public int compare(T paramT1, T paramT2)
      {
        return ((Integer)this.val$sortFunction.call(paramT1, paramT2)).intValue();
      }
    };
  }

  public Subscriber<? super T> call(Subscriber<? super List<T>> paramSubscriber)
  {
    SingleDelayedProducer localSingleDelayedProducer = new SingleDelayedProducer(paramSubscriber);
    2 local2 = new Subscriber(localSingleDelayedProducer, paramSubscriber)
    {
      boolean completed;
      List<T> list = new ArrayList(OperatorToObservableSortedList.this.initialCapacity);

      public void onCompleted()
      {
        List localList;
        if (!this.completed)
        {
          this.completed = true;
          localList = this.list;
          this.list = null;
        }
        try
        {
          Collections.sort(localList, OperatorToObservableSortedList.this.sortFunction);
          this.val$producer.setValue(localList);
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
        this.val$child.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        if (!this.completed)
          this.list.add(paramT);
      }

      public void onStart()
      {
        request(9223372036854775807L);
      }
    };
    paramSubscriber.add(local2);
    paramSubscriber.setProducer(localSingleDelayedProducer);
    return local2;
  }

  private static class DefaultComparableFunction
    implements Comparator<Object>
  {
    public int compare(Object paramObject1, Object paramObject2)
    {
      return ((Comparable)paramObject1).compareTo((Comparable)paramObject2);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorToObservableSortedList
 * JD-Core Version:    0.6.0
 */