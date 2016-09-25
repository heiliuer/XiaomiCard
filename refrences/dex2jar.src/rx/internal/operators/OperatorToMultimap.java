package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;

public final class OperatorToMultimap<T, K, V>
  implements Observable.Operator<Map<K, Collection<V>>, T>
{
  private final Func1<? super K, ? extends Collection<V>> collectionFactory;
  private final Func1<? super T, ? extends K> keySelector;
  private final Func0<? extends Map<K, Collection<V>>> mapFactory;
  private final Func1<? super T, ? extends V> valueSelector;

  public OperatorToMultimap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11)
  {
    this(paramFunc1, paramFunc11, new DefaultToMultimapFactory(), new DefaultMultimapCollectionFactory());
  }

  public OperatorToMultimap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11, Func0<? extends Map<K, Collection<V>>> paramFunc0)
  {
    this(paramFunc1, paramFunc11, paramFunc0, new DefaultMultimapCollectionFactory());
  }

  public OperatorToMultimap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11, Func0<? extends Map<K, Collection<V>>> paramFunc0, Func1<? super K, ? extends Collection<V>> paramFunc12)
  {
    this.keySelector = paramFunc1;
    this.valueSelector = paramFunc11;
    this.mapFactory = paramFunc0;
    this.collectionFactory = paramFunc12;
  }

  public Subscriber<? super T> call(Subscriber<? super Map<K, Collection<V>>> paramSubscriber)
  {
    return new Subscriber(paramSubscriber, paramSubscriber)
    {
      private Map<K, Collection<V>> map = (Map)OperatorToMultimap.this.mapFactory.call();

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
        Object localObject1 = OperatorToMultimap.this.keySelector.call(paramT);
        Object localObject2 = OperatorToMultimap.this.valueSelector.call(paramT);
        Collection localCollection = (Collection)this.map.get(localObject1);
        if (localCollection == null)
        {
          localCollection = (Collection)OperatorToMultimap.this.collectionFactory.call(localObject1);
          this.map.put(localObject1, localCollection);
        }
        localCollection.add(localObject2);
      }

      public void onStart()
      {
        request(9223372036854775807L);
      }
    };
  }

  public static final class DefaultMultimapCollectionFactory<K, V>
    implements Func1<K, Collection<V>>
  {
    public Collection<V> call(K paramK)
    {
      return new ArrayList();
    }
  }

  public static final class DefaultToMultimapFactory<K, V>
    implements Func0<Map<K, Collection<V>>>
  {
    public Map<K, Collection<V>> call()
    {
      return new HashMap();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OperatorToMultimap
 * JD-Core Version:    0.6.0
 */