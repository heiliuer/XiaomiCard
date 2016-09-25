package rx.internal.operators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.producers.SingleDelayedProducer;

public final class OperatorToObservableList<T>
  implements Observable.Operator<List<T>, T>
{
  public static <T> OperatorToObservableList<T> instance()
  {
    return Holder.INSTANCE;
  }

  public Subscriber<? super T> call(Subscriber<? super List<T>> paramSubscriber)
  {
    SingleDelayedProducer localSingleDelayedProducer = new SingleDelayedProducer(paramSubscriber);
    1 local1 = new Subscriber(localSingleDelayedProducer, paramSubscriber)
    {
      boolean completed = false;
      List<T> list = new LinkedList();

      public void onCompleted()
      {
        if (!this.completed)
          this.completed = true;
        try
        {
          ArrayList localArrayList = new ArrayList(this.list);
          this.list = null;
          this.val$producer.setValue(localArrayList);
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
        this.val$o.onError(paramThrowable);
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
    paramSubscriber.add(local1);
    paramSubscriber.setProducer(localSingleDelayedProducer);
    return local1;
  }

  private static final class Holder
  {
    static final OperatorToObservableList<Object> INSTANCE = new OperatorToObservableList(null);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorToObservableList
 * JD-Core Version:    0.6.0
 */