package com.miui.tsmclient.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LntSelectLocationAdapter extends ArrayAdapter<String>
{
  public LntSelectLocationAdapter(Context paramContext)
  {
    super(paramContext);
  }

  public void bindData(View paramView, int paramInt, String paramString)
  {
    ((ViewHolder)paramView.getTag()).mLocationName.setText(paramString);
  }

  public View newView(Context paramContext, String paramString, ViewGroup paramViewGroup)
  {
    View localView = LayoutInflater.from(this.mContext).inflate(2130903084, null);
    ViewHolder localViewHolder = new ViewHolder();
    localViewHolder.mLocationName = ((TextView)localView.findViewById(2131493082));
    localView.setTag(localViewHolder);
    return localView;
  }

  static final class ViewHolder
  {
    TextView mLocationName;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.LntSelectLocationAdapter
 * JD-Core Version:    0.6.0
 */