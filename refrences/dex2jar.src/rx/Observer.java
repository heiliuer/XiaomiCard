package rx;

public abstract interface Observer<T>
{
  public abstract void onCompleted();

  public abstract void onError(Throwable paramThrowable);

  public abstract void onNext(T paramT);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.Observer
 * JD-Core Version:    0.6.0
 */