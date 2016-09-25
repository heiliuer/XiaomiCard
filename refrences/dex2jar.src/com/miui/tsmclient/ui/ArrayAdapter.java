package com.miui.tsmclient.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public abstract class ArrayAdapter<T> extends BaseAdapter
{
  protected Context mContext;
  protected List<T> mData;

  public ArrayAdapter(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public abstract void bindData(View paramView, int paramInt, T paramT);

  public int getCount()
  {
    if (this.mData != null);
    for (int i = this.mData.size(); ; i = 0)
      return i;
  }

  public ArrayList<T> getData()
  {
    ArrayList localArrayList = Lists.newArrayList();
    if (this.mData != null)
      localArrayList.addAll(this.mData);
    return localArrayList;
  }

  public Object getItem(int paramInt)
  {
    if (this.mData != null);
    for (Object localObject = this.mData.get(paramInt); ; localObject = null)
      return localObject;
  }

  public long getItemId(int paramInt)
  {
    long l;
    if (this.mData != null)
      l = paramInt;
    while (true)
    {
      return l;
      l = 0L;
    }
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (this.mData == null)
      throw new IllegalStateException("this should only be called when the data is valid");
    if ((paramInt < 0) || (paramInt >= this.mData.size()))
      throw new IllegalStateException("couldn't get view at this position " + paramInt);
    Object localObject = this.mData.get(paramInt);
    if (paramView == null);
    for (View localView = newView(this.mContext, localObject, paramViewGroup); ; localView = paramView)
    {
      bindData(localView, paramInt, localObject);
      return localView;
    }
  }

  public abstract View newView(Context paramContext, T paramT, ViewGroup paramViewGroup);

  public void updateData(List<T> paramList)
  {
    if (paramList != null)
    {
      this.mData = paramList;
      notifyDataSetChanged();
    }
    while (true)
    {
      return;
      notifyDataSetInvalidated();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.ArrayAdapter
 * JD-Core Version:    0.6.0
 */