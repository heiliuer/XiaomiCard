package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import rx.Observable.OnSubscribe;
import rx.annotations.Beta;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;

public final class BehaviorSubject<T> extends Subject<T, T>
{
  private static final Object[] EMPTY_ARRAY = new Object[0];
  private final NotificationLite<T> nl = NotificationLite.instance();
  private final SubjectSubscriptionManager<T> state;

  protected BehaviorSubject(Observable.OnSubscribe<T> paramOnSubscribe, SubjectSubscriptionManager<T> paramSubjectSubscriptionManager)
  {
    super(paramOnSubscribe);
    this.state = paramSubjectSubscriptionManager;
  }

  public static <T> BehaviorSubject<T> create()
  {
    return create(null, false);
  }

  public static <T> BehaviorSubject<T> create(T paramT)
  {
    return create(paramT, true);
  }

  private static <T> BehaviorSubject<T> create(T paramT, boolean paramBoolean)
  {
    SubjectSubscriptionManager localSubjectSubscriptionManager = new SubjectSubscriptionManager();
    if (paramBoolean)
      localSubjectSubscriptionManager.setLatest(NotificationLite.instance().next(paramT));
    localSubjectSubscriptionManager.onAdded = new Action1(localSubjectSubscriptionManager)
    {
      public void call(SubjectSubscriptionManager.SubjectObserver<T> paramSubjectObserver)
      {
        paramSubjectObserver.emitFirst(BehaviorSubject.this.getLatest(), BehaviorSubject.this.nl);
      }
    };
    localSubjectSubscriptionManager.onTerminated = localSubjectSubscriptionManager.onAdded;
    return new BehaviorSubject(localSubjectSubscriptionManager, localSubjectSubscriptionManager);
  }

  @Beta
  public Throwable getThrowable()
  {
    Object localObject = this.state.getLatest();
    if (this.nl.isError(localObject));
    for (Throwable localThrowable = this.nl.getError(localObject); ; localThrowable = null)
      return localThrowable;
  }

  @Beta
  public T getValue()
  {
    Object localObject1 = this.state.getLatest();
    if (this.nl.isNext(localObject1));
    for (Object localObject2 = this.nl.getValue(localObject1); ; localObject2 = null)
      return localObject2;
  }

  @Beta
  public Object[] getValues()
  {
    Object[] arrayOfObject = getValues((Object[])EMPTY_ARRAY);
    if (arrayOfObject == EMPTY_ARRAY)
      arrayOfObject = new Object[0];
    return arrayOfObject;
  }

  @Beta
  public T[] getValues(T[] paramArrayOfT)
  {
    Object localObject = this.state.getLatest();
    if (this.nl.isNext(localObject))
    {
      if (paramArrayOfT.length == 0)
        paramArrayOfT = (Object[])(Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), 1);
      paramArrayOfT[0] = this.nl.getValue(localObject);
      if (paramArrayOfT.length > 1)
        paramArrayOfT[1] = null;
    }
    while (true)
    {
      return paramArrayOfT;
      if (paramArrayOfT.length <= 0)
        continue;
      paramArrayOfT[0] = null;
    }
  }

  @Beta
  public boolean hasCompleted()
  {
    Object localObject = this.state.getLatest();
    return this.nl.isCompleted(localObject);
  }

  public boolean hasObservers()
  {
    if (this.state.observers().length > 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  @Beta
  public boolean hasThrowable()
  {
    Object localObject = this.state.getLatest();
    return this.nl.isError(localObject);
  }

  @Beta
  public boolean hasValue()
  {
    Object localObject = this.state.getLatest();
    return this.nl.isNext(localObject);
  }

  public void onCompleted()
  {
    if ((this.state.getLatest() == null) || (this.state.active))
    {
      Object localObject = this.nl.completed();
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.terminate(localObject);
      int i = arrayOfSubjectObserver.length;
      for (int j = 0; j < i; j++)
        arrayOfSubjectObserver[j].emitNext(localObject, this.state.nl);
    }
  }

  public void onError(Throwable paramThrowable)
  {
    if ((this.state.getLatest() == null) || (this.state.active))
    {
      Object localObject = this.nl.error(paramThrowable);
      ArrayList localArrayList = null;
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.terminate(localObject);
      int i = arrayOfSubjectObserver.length;
      int j = 0;
      while (true)
        if (j < i)
        {
          SubjectSubscriptionManager.SubjectObserver localSubjectObserver = arrayOfSubjectObserver[j];
          try
          {
            localSubjectObserver.emitNext(localObject, this.state.nl);
            j++;
          }
          catch (Throwable localThrowable)
          {
            while (true)
            {
              if (localArrayList == null)
                localArrayList = new ArrayList();
              localArrayList.add(localThrowable);
            }
          }
        }
      Exceptions.throwIfAny(localArrayList);
    }
  }

  public void onNext(T paramT)
  {
    if ((this.state.getLatest() == null) || (this.state.active))
    {
      Object localObject = this.nl.next(paramT);
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.next(localObject);
      int i = arrayOfSubjectObserver.length;
      for (int j = 0; j < i; j++)
        arrayOfSubjectObserver[j].emitNext(localObject, this.state.nl);
    }
  }

  int subscriberCount()
  {
    return this.state.observers().length;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.subjects.BehaviorSubject
 * JD-Core Version:    0.6.0
 */