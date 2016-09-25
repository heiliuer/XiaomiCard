package rx.android.lifecycle;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

public class LifecycleObservable
{
  private static final Func1<LifecycleEvent, LifecycleEvent> ACTIVITY_LIFECYCLE = new Func1()
  {
    public LifecycleEvent call(LifecycleEvent paramLifecycleEvent)
    {
      if (paramLifecycleEvent == null)
        throw new NullPointerException("Cannot bind to null LifecycleEvent.");
      LifecycleEvent localLifecycleEvent;
      switch (LifecycleObservable.6.$SwitchMap$rx$android$lifecycle$LifecycleEvent[paramLifecycleEvent.ordinal()])
      {
      default:
        throw new UnsupportedOperationException("Binding to LifecycleEvent " + paramLifecycleEvent + " not yet implemented");
      case 1:
        localLifecycleEvent = LifecycleEvent.DESTROY;
      case 2:
      case 3:
      case 4:
      case 5:
        while (true)
        {
          return localLifecycleEvent;
          localLifecycleEvent = LifecycleEvent.STOP;
          continue;
          localLifecycleEvent = LifecycleEvent.PAUSE;
          continue;
          localLifecycleEvent = LifecycleEvent.STOP;
          continue;
          localLifecycleEvent = LifecycleEvent.DESTROY;
        }
      case 6:
        throw new IllegalStateException("Cannot bind to Activity lifecycle when outside of it.");
      case 7:
      case 8:
      case 9:
      case 10:
      }
      throw new IllegalStateException("Cannot bind to " + paramLifecycleEvent + " for an Activity.");
    }
  };
  private static final Func1<LifecycleEvent, LifecycleEvent> FRAGMENT_LIFECYCLE = new Func1()
  {
    public LifecycleEvent call(LifecycleEvent paramLifecycleEvent)
    {
      if (paramLifecycleEvent == null)
        throw new NullPointerException("Cannot bind to null LifecycleEvent.");
      LifecycleEvent localLifecycleEvent;
      switch (LifecycleObservable.6.$SwitchMap$rx$android$lifecycle$LifecycleEvent[paramLifecycleEvent.ordinal()])
      {
      default:
        throw new UnsupportedOperationException("Binding to LifecycleEvent " + paramLifecycleEvent + " not yet implemented");
      case 7:
        localLifecycleEvent = LifecycleEvent.DETACH;
      case 1:
      case 8:
      case 2:
      case 3:
      case 4:
      case 5:
      case 9:
      case 6:
        while (true)
        {
          return localLifecycleEvent;
          localLifecycleEvent = LifecycleEvent.DESTROY;
          continue;
          localLifecycleEvent = LifecycleEvent.DESTROY_VIEW;
          continue;
          localLifecycleEvent = LifecycleEvent.STOP;
          continue;
          localLifecycleEvent = LifecycleEvent.PAUSE;
          continue;
          localLifecycleEvent = LifecycleEvent.STOP;
          continue;
          localLifecycleEvent = LifecycleEvent.DESTROY_VIEW;
          continue;
          localLifecycleEvent = LifecycleEvent.DESTROY;
          continue;
          localLifecycleEvent = LifecycleEvent.DETACH;
        }
      case 10:
      }
      throw new IllegalStateException("Cannot bind to Fragment lifecycle when outside of it.");
    }
  };

  private LifecycleObservable()
  {
    throw new AssertionError("No instances");
  }

  public static <T> Observable<T> bindActivityLifecycle(Observable<LifecycleEvent> paramObservable, Observable<T> paramObservable1)
  {
    return bindLifecycle(paramObservable, paramObservable1, ACTIVITY_LIFECYCLE);
  }

  public static <T> Observable<T> bindFragmentLifecycle(Observable<LifecycleEvent> paramObservable, Observable<T> paramObservable1)
  {
    return bindLifecycle(paramObservable, paramObservable1, FRAGMENT_LIFECYCLE);
  }

  private static <T> Observable<T> bindLifecycle(Observable<LifecycleEvent> paramObservable, Observable<T> paramObservable1, Func1<LifecycleEvent, LifecycleEvent> paramFunc1)
  {
    if ((paramObservable == null) || (paramObservable1 == null))
      throw new IllegalArgumentException("Lifecycle and Observable must be given");
    Observable localObservable = paramObservable.share();
    return paramObservable1.lift(new OperatorSubscribeUntil(Observable.combineLatest(localObservable.take(1).map(paramFunc1), localObservable.skip(1), new Func2()
    {
      public Boolean call(LifecycleEvent paramLifecycleEvent1, LifecycleEvent paramLifecycleEvent2)
      {
        if (paramLifecycleEvent2 == paramLifecycleEvent1);
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }
    }).takeFirst(new Func1()
    {
      public Boolean call(Boolean paramBoolean)
      {
        return paramBoolean;
      }
    })));
  }

  public static <T> Observable<T> bindUntilLifecycleEvent(Observable<LifecycleEvent> paramObservable, Observable<T> paramObservable1, LifecycleEvent paramLifecycleEvent)
  {
    if ((paramObservable == null) || (paramObservable1 == null))
      throw new IllegalArgumentException("Lifecycle and Observable must be given");
    return paramObservable1.lift(new OperatorSubscribeUntil(paramObservable.takeFirst(new Func1(paramLifecycleEvent)
    {
      public Boolean call(LifecycleEvent paramLifecycleEvent)
      {
        if (paramLifecycleEvent == LifecycleObservable.this);
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }
    })));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.lifecycle.LifecycleObservable
 * JD-Core Version:    0.6.0
 */