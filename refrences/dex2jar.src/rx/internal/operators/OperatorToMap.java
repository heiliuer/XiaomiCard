package rx.internal.operators;

import java.util.HashMap;
import java.util.Map;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;

public final class OperatorToMap<T, K, V>
  implements Observable.Operator<Map<K, V>, T>
{
  private final Func1<? super T, ? extends K> keySelector;
  private final Func0<? extends Map<K, V>> mapFactory;
  private final Func1<? super T, ? extends V> valueSelector;

  public OperatorToMap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11)
  {
    this(paramFunc1, paramFunc11, new DefaultToMapFactory());
  }

  public OperatorToMap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11, Func0<? extends Map<K, V>> paramFunc0)
  {
    this.keySelector = paramFunc1;
    this.valueSelector = paramFunc11;
    this.mapFactory = paramFunc0;
  }

  public Subscriber<? super T> call(Subscriber<? super Map<K, V>> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      private Map<K, V> map = (Map)OperatorToMap.this.mapFactory.call();

      public void onCompleted()
      {
        Map localMap = this.map;
        this.map = null;
        this.val$subscriber.onNext(localMap);
        this.val$subscriber.onCompleted();
      }

      public void onError(Throwable paramThrowable)
      {
        this.map = null;
        this.val$subscriber.onError(paramThrowable);
      }

      public void onNext(T paramT)
      {
        Object localObject1 = OperatorToMap.this.keySelector.call(paramT);
        Object localObject2 = OperatorToMap.this.valueSelector.call(paramT);
        this.map.put(localObject1, localObject2);
      }

      public void onStart()
      {
        request(9223372036854775807L);
      }
    };
  }

  public static final class DefaultToMapFactory<K, V>
    implements Func0<Map<K, V>>
  {
    public Map<K, V> call()
    {
      return new HashMap();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorToMap
 * JD-Core Version:    0.6.0
 */