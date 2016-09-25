package com.miui.tsmclient.model;

import android.content.Context;
import android.os.Bundle;

public class BaseModel
  implements IModel
{
  public static final int DATA_ID_ERROR = 2147483647;
  private Context mContext;
  private OnModelChangedListener mListener;

  public Context getContext()
  {
    return this.mContext;
  }

  public void init(Context paramContext, OnModelChangedListener paramOnModelChangedListener)
  {
    this.mContext = paramContext;
    this.mListener = paramOnModelChangedListener;
    onInit();
  }

  protected final void notifyChanged(int paramInt)
  {
    notifyChanged(paramInt, null);
  }

  protected final void notifyChanged(int paramInt, Bundle paramBundle)
  {
    if (paramBundle == null)
      paramBundle = new Bundle();
    if (this.mListener != null)
      this.mListener.onModelChanged(paramInt, paramBundle);
  }

  protected void onInit()
  {
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.BaseModel
 * JD-Core Version:    0.6.0
 */