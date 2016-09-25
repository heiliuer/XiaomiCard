package com.miui.tsmclient.presenter;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.model.IModel;
import java.io.Serializable;

public abstract interface IPresenter<T extends IView> extends Serializable
{
  public abstract void attach(T paramT);

  public abstract void detach();

  public abstract void init(Context paramContext, Bundle paramBundle);

  public abstract void release();

  public abstract void subscribe(IModel paramIModel);

  public abstract void unsubscribe(IModel paramIModel);
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.presenter.IPresenter
 * JD-Core Version:    0.6.0
 */