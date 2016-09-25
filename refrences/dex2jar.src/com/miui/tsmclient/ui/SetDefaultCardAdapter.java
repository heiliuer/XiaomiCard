package com.miui.tsmclient.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.miui.tsmclient.entity.CardInfo;
import java.util.Iterator;
import java.util.List;

public class SetDefaultCardAdapter extends ArrayAdapter<CardInfo>
{
  private ColorStateList mCommonColor;
  private String mDefaultCardAId;
  private int mItemSelectedColor;

  public SetDefaultCardAdapter(Context paramContext)
  {
    super(paramContext);
    this.mItemSelectedColor = paramContext.getResources().getColor(2131165245);
  }

  public void bindData(View paramView, int paramInt, CardInfo paramCardInfo)
  {
    ViewHolder localViewHolder = (ViewHolder)paramView.getTag();
    localViewHolder.mCardName.setText(paramCardInfo.mCardName);
    if (TextUtils.equals(paramCardInfo.mAid, this.mDefaultCardAId))
    {
      localViewHolder.mDefaultText.setVisibility(0);
      localViewHolder.mCardName.setTextColor(this.mItemSelectedColor);
    }
    while (true)
    {
      return;
      localViewHolder.mDefaultText.setVisibility(4);
      localViewHolder.mCardName.setTextColor(this.mCommonColor);
    }
  }

  public View newView(Context paramContext, CardInfo paramCardInfo, ViewGroup paramViewGroup)
  {
    View localView = LayoutInflater.from(this.mContext).inflate(2130903078, null);
    ViewHolder localViewHolder = new ViewHolder(null);
    localViewHolder.mCardName = ((TextView)localView.findViewById(2131493061));
    this.mCommonColor = localViewHolder.mCardName.getTextColors();
    localViewHolder.mDefaultText = ((TextView)localView.findViewById(2131493062));
    localViewHolder.mDefaultText.setTextColor(this.mItemSelectedColor);
    localView.setTag(localViewHolder);
    return localView;
  }

  public void setDefaultCard(String paramString)
  {
    if (this.mData == null)
    {
      this.mDefaultCardAId = paramString;
      return;
    }
    while (true)
    {
      Iterator localIterator = this.mData.iterator();
      if (!localIterator.hasNext())
        continue;
      if (!TextUtils.equals(((CardInfo)localIterator.next()).mAid, paramString))
        break;
      this.mDefaultCardAId = paramString;
      notifyDataSetChanged();
    }
  }

  private static final class ViewHolder
  {
    TextView mCardName;
    TextView mDefaultText;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.SetDefaultCardAdapter
 * JD-Core Version:    0.6.0
 */