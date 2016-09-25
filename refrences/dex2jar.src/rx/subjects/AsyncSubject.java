package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import rx.Observable.OnSubscribe;
import rx.annotations.Beta;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;

public final class AsyncSubject<T> extends Subject<T, T>
{
  volatile Object lastValue;
  private final NotificationLite<T> nl = NotificationLite.instance();
  final SubjectSubscriptionManager<T> state;

  protected AsyncSubject(Observable.OnSubscribe<T> paramOnSubscribe, SubjectSubscriptionManager<T> paramSubjectSubscriptionManager)
  {
    super(paramOnSubscribe);
    this.state = paramSubjectSubscriptionManager;
  }

  public static <T> AsyncSubject<T> create()
  {
    SubjectSubscriptionManager localSubjectSubscriptionManager = new SubjectSubscriptionManager();
    localSubjectSubscriptionManager.onTerminated = new Action1(localSubjectSubscriptionManager)
    {
      public void call(SubjectSubscriptionManager.SubjectObserver<T> paramSubjectObserver)
      {
        Object localObject = AsyncSubject.this.getLatest();
        NotificationLite localNotificationLite = AsyncSubject.this.nl;
        paramSubjectObserver.accept(localObject, localNotificationLite);
        if ((localObject == null) || ((!localNotificationLite.isCompleted(localObject)) && (!localNotificationLite.isError(localObject))))
          paramSubjectObserver.onCompleted();
      }
    };
    return new AsyncSubject(localSubjectSubscriptionManager, localSubjectSubscriptionManager);
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
    Object localObject1 = this.lastValue;
    Object localObject2 = this.state.getLatest();
    if ((!this.nl.isError(localObject2)) && (this.nl.isNext(localObject1)));
    for (Object localObject3 = this.nl.getValue(localObject1); ; localObject3 = null)
      return localObject3;
  }

  @Beta
  public boolean hasCompleted()
  {
    Object localObject = this.state.getLatest();
    if ((localObject != null) && (!this.nl.isError(localObject)));
    for (int i = 1; ; i = 0)
      return i;
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
    Object localObject1 = this.lastValue;
    Object localObject2 = this.state.getLatest();
    if ((!this.nl.isError(localObject2)) && (this.nl.isNext(localObject1)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void onCompleted()
  {
    if (this.state.active)
    {
      Object localObject = this.lastValue;
      if (localObject == null)
        localObject = this.nl.completed();
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.terminate(localObject);
      int i = arrayOfSubjectObserver.length;
      int j = 0;
      if (j < i)
      {
        SubjectSubscriptionManager.SubjectObserver localSubjectObserver = arrayOfSubjectObserver[j];
        if (localObject == this.nl.completed())
          localSubjectObserver.onCompleted();
        while (true)
        {
          j++;
          break;
          localSubjectObserver.onNext(this.nl.getValue(localObject));
          localSubjectObserver.onCompleted();
        }
      }
    }
  }

  public void onError(Throwable paramThrowable)
  {
    if (this.state.active)
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
            localSubjectObserver.onError(paramThrowable);
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
    this.lastValue = this.nl.next(paramT);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.subjects.AsyncSubject
 * JD-Core Version:    0.6.0
 */