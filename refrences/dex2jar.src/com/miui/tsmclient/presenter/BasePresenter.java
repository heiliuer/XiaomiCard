package com.miui.tsmclient.presenter;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.model.IModel;
import com.miui.tsmclient.model.OnModelChangedListener;

public class BasePresenter<T extends IView>
  implements IPresenter<T>
{
  private Bundle mArgs;
  private Context mContext;
  private final transient OnModelChangedListener mInternalModelChangedListener = new OnModelChangedListener()
  {
    public void onModelChanged(int paramInt, Bundle paramBundle)
    {
      BasePresenter.this.onChildModelChanged(paramInt, paramBundle);
    }
  };
  private T mView;

  public void attach(T paramT)
  {
    this.mView = paramT;
    onAttach();
  }

  public void detach()
  {
    onDetach();
  }

  public Bundle getArgs()
  {
    return this.mArgs;
  }

  public Context getContext()
  {
    return this.mContext;
  }

  public T getView()
  {
    return this.mView;
  }

  public void init(Context paramContext, Bundle paramBundle)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mArgs = paramBundle;
    onInit();
  }

  protected void onAttach()
  {
  }

  protected void onChildModelChanged(int paramInt, Bundle paramBundle)
  {
    if (paramInt == 2147483647)
    {
      int i = paramBundle.getInt("error_code");
      String str = paramBundle.getString("error_desc");
      ((IHandleError)getView()).handleError(i, str);
    }
  }

  protected void onDetach()
  {
  }

  protected void onInit()
  {
  }

  public void release()
  {
  }

  public void subscribe(IModel paramIModel)
  {
    paramIModel.init(this.mContext, this.mInternalModelChangedListener);
  }

  public void unsubscribe(IModel paramIModel)
  {
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.presenter.BasePresenter
 * JD-Core Version:    0.6.0
 */