package com.miui.tsmclient.task;

import rx.Subscriber;

public abstract class RxBaseTaskListener<R> extends Subscriber<R>
{
  protected void handleResult(R paramR)
  {
  }

  public void onCompleted()
  {
  }

  public void onError(Throwable paramThrowable)
  {
  }

  public void onNext(R paramR)
  {
    handleResult(paramR);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.task.RxBaseTaskListener
 * JD-Core Version:    0.6.0
 */