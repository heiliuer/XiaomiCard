package rx.subjects;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;

public abstract class Subject<T, R> extends Observable<R>
  implements Observer<T>
{
  protected Subject(Observable.OnSubscribe<R> paramOnSubscribe)
  {
    super(paramOnSubscribe);
  }

  public abstract boolean hasObservers();

  public final SerializedSubject<T, R> toSerialized()
  {
    if (getClass() == SerializedSubject.class);
    for (SerializedSubject localSerializedSubject = (SerializedSubject)this; ; localSerializedSubject = new SerializedSubject(this))
      return localSerializedSubject;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.subjects.Subject
 * JD-Core Version:    0.6.0
 */