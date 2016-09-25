package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public final class OnSubscribeAmb<T>
  implements Observable.OnSubscribe<T>
{
  final AtomicReference<AmbSubscriber<T>> choice = this.selection.choice;
  final Selection<T> selection = new Selection(null);
  final Iterable<? extends Observable<? extends T>> sources;

  private OnSubscribeAmb(Iterable<? extends Observable<? extends T>> paramIterable)
  {
    this.sources = paramIterable;
  }

  public static <T> Observable.OnSubscribe<T> amb(Iterable<? extends Observable<? extends T>> paramIterable)
  {
    return new OnSubscribeAmb(paramIterable);
  }

  public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramObservable1);
    localArrayList.add(paramObservable2);
    return amb(localArrayList);
  }

  public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramObservable1);
    localArrayList.add(paramObservable2);
    localArrayList.add(paramObservable3);
    return amb(localArrayList);
  }

  public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramObservable1);
    localArrayList.add(paramObservable2);
    localArrayList.add(paramObservable3);
    localArrayList.add(paramObservable4);
    return amb(localArrayList);
  }

  public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramObservable1);
    localArrayList.add(paramObservable2);
    localArrayList.add(paramObservable3);
    localArrayList.add(paramObservable4);
    localArrayList.add(paramObservable5);
    return amb(localArrayList);
  }

  public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramObservable1);
    localArrayList.add(paramObservable2);
    localArrayList.add(paramObservable3);
    localArrayList.add(paramObservable4);
    localArrayList.add(paramObservable5);
    localArrayList.add(paramObservable6);
    return amb(localArrayList);
  }

  public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramObservable1);
    localArrayList.add(paramObservable2);
    localArrayList.add(paramObservable3);
    localArrayList.add(paramObservable4);
    localArrayList.add(paramObservable5);
    localArrayList.add(paramObservable6);
    localArrayList.add(paramObservable7);
    return amb(localArrayList);
  }

  public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7, Observable<? extends T> paramObservable8)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramObservable1);
    localArrayList.add(paramObservable2);
    localArrayList.add(paramObservable3);
    localArrayList.add(paramObservable4);
    localArrayList.add(paramObservable5);
    localArrayList.add(paramObservable6);
    localArrayList.add(paramObservable7);
    localArrayList.add(paramObservable8);
    return amb(localArrayList);
  }

  public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Observable<? extends T> paramObservable3, Observable<? extends T> paramObservable4, Observable<? extends T> paramObservable5, Observable<? extends T> paramObservable6, Observable<? extends T> paramObservable7, Observable<? extends T> paramObservable8, Observable<? extends T> paramObservable9)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramObservable1);
    localArrayList.add(paramObservable2);
    localArrayList.add(paramObservable3);
    localArrayList.add(paramObservable4);
    localArrayList.add(paramObservable5);
    localArrayList.add(paramObservable6);
    localArrayList.add(paramObservable7);
    localArrayList.add(paramObservable8);
    localArrayList.add(paramObservable9);
    return amb(localArrayList);
  }

  private static <T> void unsubscribeAmbSubscribers(Collection<AmbSubscriber<T>> paramCollection)
  {
    if (!paramCollection.isEmpty())
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
        ((AmbSubscriber)localIterator.next()).unsubscribe();
      paramCollection.clear();
    }
  }

  public void call(Subscriber<? super T> paramSubscriber)
  {
    paramSubscriber.add(Subscriptions.create(new Action0()
    {
      public void call()
      {
        OnSubscribeAmb.AmbSubscriber localAmbSubscriber = (OnSubscribeAmb.AmbSubscriber)OnSubscribeAmb.this.choice.get();
        if (localAmbSubscriber != null)
          localAmbSubscriber.unsubscribe();
        OnSubscribeAmb.access$100(OnSubscribeAmb.this.selection.ambSubscribers);
      }
    }));
    Iterator localIterator = this.sources.iterator();
    while (true)
    {
      Observable localObservable;
      if (localIterator.hasNext())
      {
        localObservable = (Observable)localIterator.next();
        if (!paramSubscriber.isUnsubscribed());
      }
      else
      {
        if (paramSubscriber.isUnsubscribed())
          unsubscribeAmbSubscribers(this.selection.ambSubscribers);
        paramSubscriber.setProducer(new Producer()
        {
          public void request(long paramLong)
          {
            OnSubscribeAmb.AmbSubscriber localAmbSubscriber1 = (OnSubscribeAmb.AmbSubscriber)OnSubscribeAmb.this.choice.get();
            if (localAmbSubscriber1 != null)
              OnSubscribeAmb.AmbSubscriber.access$300(localAmbSubscriber1, paramLong);
            label102: 
            while (true)
            {
              return;
              Iterator localIterator = OnSubscribeAmb.this.selection.ambSubscribers.iterator();
              while (true)
              {
                if (!localIterator.hasNext())
                  break label102;
                OnSubscribeAmb.AmbSubscriber localAmbSubscriber2 = (OnSubscribeAmb.AmbSubscriber)localIterator.next();
                if (localAmbSubscriber2.isUnsubscribed())
                  continue;
                if (OnSubscribeAmb.this.choice.get() == localAmbSubscriber2)
                {
                  OnSubscribeAmb.AmbSubscriber.access$300(localAmbSubscriber2, paramLong);
                  break;
                }
                OnSubscribeAmb.AmbSubscriber.access$300(localAmbSubscriber2, paramLong);
              }
            }
          }
        });
      }
      AmbSubscriber localAmbSubscriber1;
      while (true)
      {
        return;
        localAmbSubscriber1 = new AmbSubscriber(0L, paramSubscriber, this.selection, null);
        this.selection.ambSubscribers.add(localAmbSubscriber1);
        AmbSubscriber localAmbSubscriber2 = (AmbSubscriber)this.choice.get();
        if (localAmbSubscriber2 == null)
          break;
        this.selection.unsubscribeOthers(localAmbSubscriber2);
      }
      localObservable.unsafeSubscribe(localAmbSubscriber1);
    }
  }

  private static class Selection<T>
  {
    final Collection<OnSubscribeAmb.AmbSubscriber<T>> ambSubscribers = new ConcurrentLinkedQueue();
    final AtomicReference<OnSubscribeAmb.AmbSubscriber<T>> choice = new AtomicReference();

    public void unsubscribeLosers()
    {
      OnSubscribeAmb.AmbSubscriber localAmbSubscriber = (OnSubscribeAmb.AmbSubscriber)this.choice.get();
      if (localAmbSubscriber != null)
        unsubscribeOthers(localAmbSubscriber);
    }

    public void unsubscribeOthers(OnSubscribeAmb.AmbSubscriber<T> paramAmbSubscriber)
    {
      Iterator localIterator = this.ambSubscribers.iterator();
      while (localIterator.hasNext())
      {
        OnSubscribeAmb.AmbSubscriber localAmbSubscriber = (OnSubscribeAmb.AmbSubscriber)localIterator.next();
        if (localAmbSubscriber == paramAmbSubscriber)
          continue;
        localAmbSubscriber.unsubscribe();
      }
      this.ambSubscribers.clear();
    }
  }

  private static final class AmbSubscriber<T> extends Subscriber<T>
  {
    private boolean chosen;
    private final OnSubscribeAmb.Selection<T> selection;
    private final Subscriber<? super T> subscriber;

    private AmbSubscriber(long paramLong, Subscriber<? super T> paramSubscriber, OnSubscribeAmb.Selection<T> paramSelection)
    {
      this.subscriber = paramSubscriber;
      this.selection = paramSelection;
      request(paramLong);
    }

    private boolean isSelected()
    {
      boolean bool = true;
      if (this.chosen);
      while (true)
      {
        return bool;
        if (this.selection.choice.get() == this)
        {
          this.chosen = bool;
          continue;
        }
        if (this.selection.choice.compareAndSet(null, this))
        {
          this.selection.unsubscribeOthers(this);
          this.chosen = bool;
          continue;
        }
        this.selection.unsubscribeLosers();
        bool = false;
      }
    }

    private final void requestMore(long paramLong)
    {
      request(paramLong);
    }

    public void onCompleted()
    {
      if (!isSelected());
      while (true)
      {
        return;
        this.subscriber.onCompleted();
      }
    }

    public void onError(Throwable paramThrowable)
    {
      if (!isSelected());
      while (true)
      {
        return;
        this.subscriber.onError(paramThrowable);
      }
    }

    public void onNext(T paramT)
    {
      if (!isSelected());
      while (true)
      {
        return;
        this.subscriber.onNext(paramT);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.internal.operators.OnSubscribeAmb
 * JD-Core Version:    0.6.0
 */