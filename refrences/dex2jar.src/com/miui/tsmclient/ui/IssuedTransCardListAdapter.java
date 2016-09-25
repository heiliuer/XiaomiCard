package com.miui.tsmclient.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardUIInfo;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IssuedTransCardListAdapter extends RecyclerView.Adapter<ItemViewHolder>
{
  private Context mContext;
  private List<CardInfo> mDatas;
  private String mDefaultCardAId;
  private OnItemClickListener mOnItemClickListener;

  public IssuedTransCardListAdapter(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void addData(CardInfo paramCardInfo)
  {
    if (this.mDatas == null)
      this.mDatas = new ArrayList();
    Iterator localIterator = this.mDatas.iterator();
    do
      if (!localIterator.hasNext())
        break;
    while (!TextUtils.equals(((CardInfo)localIterator.next()).mCardType, paramCardInfo.mCardType));
    while (true)
    {
      return;
      this.mDatas.add(paramCardInfo);
      notifyItemInserted(-1 + this.mDatas.size());
    }
  }

  public int getItemCount()
  {
    if (this.mDatas == null);
    for (int i = 0; ; i = this.mDatas.size())
      return i;
  }

  public CardInfo getItemData(int paramInt)
  {
    CardInfo localCardInfo = null;
    if ((paramInt > -1) && (paramInt < getItemCount()))
      localCardInfo = (CardInfo)this.mDatas.get(paramInt);
    return localCardInfo;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public void onBindViewHolder(ItemViewHolder paramItemViewHolder, int paramInt)
  {
    CardInfo localCardInfo = (CardInfo)this.mDatas.get(paramInt);
    if (paramItemViewHolder.mCardBackground.getDrawable() == null)
      paramItemViewHolder.mCardBackground.setImageResource(2130837605);
    if ((localCardInfo.mCardUIInfo != null) && (!TextUtils.isEmpty(localCardInfo.mCardUIInfo.mCardIssuedListBgHdUrl)))
      ImageLoader.getInstance().displayImage(localCardInfo.mCardUIInfo.mCardIssuedListBgHdUrl, paramItemViewHolder.mCardBackground);
    String str = localCardInfo.mCardName;
    if ((this.mDefaultCardAId != null) && (localCardInfo.mAid.equals(this.mDefaultCardAId)))
      str = str + " | " + this.mContext.getString(2131296505);
    paramItemViewHolder.mCardName.setText(str);
    paramItemViewHolder.itemView.setOnClickListener(new View.OnClickListener(paramItemViewHolder, paramInt)
    {
      public void onClick(View paramView)
      {
        if (IssuedTransCardListAdapter.this.mOnItemClickListener != null)
          IssuedTransCardListAdapter.this.mOnItemClickListener.onItemClick(IssuedTransCardListAdapter.this, this.val$holder.itemView, this.val$position);
      }
    });
  }

  public ItemViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
  {
    return new ItemViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(2130903070, paramViewGroup, false));
  }

  public void setData(List<CardInfo> paramList)
  {
    this.mDatas = paramList;
    notifyDataSetChanged();
  }

  public void setDefaultCard(String paramString)
  {
    if (this.mDatas == null)
    {
      this.mDefaultCardAId = paramString;
      return;
    }
    while (true)
    {
      Iterator localIterator = this.mDatas.iterator();
      if (!localIterator.hasNext())
        continue;
      if (!TextUtils.equals(((CardInfo)localIterator.next()).mAid, paramString))
        break;
      this.mDefaultCardAId = paramString;
      notifyDataSetChanged();
    }
  }

  public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener)
  {
    this.mOnItemClickListener = paramOnItemClickListener;
  }

  public void updateData(CardInfo paramCardInfo)
  {
    if (this.mDatas == null)
      this.mDatas = new ArrayList();
    int i;
    if (this.mDatas.contains(paramCardInfo))
    {
      i = this.mDatas.indexOf(paramCardInfo);
      if ((paramCardInfo.mStatus != null) && (!paramCardInfo.mHasIssue))
      {
        this.mDatas.remove(i);
        notifyItemRemoved(i);
      }
    }
    while (true)
    {
      return;
      if (paramCardInfo.mHasIssue)
      {
        this.mDatas.remove(i);
        this.mDatas.add(i, paramCardInfo);
        notifyItemChanged(i);
        continue;
        if (!paramCardInfo.mHasIssue)
          continue;
        this.mDatas.add(paramCardInfo);
        notifyItemChanged(-1 + this.mDatas.size());
        continue;
      }
    }
  }

  public static class ItemViewHolder extends RecyclerView.ViewHolder
  {
    public ImageView mCardBackground;
    public TextView mCardName;
    public ViewGroup mCardNameContainer;

    public ItemViewHolder(View paramView)
    {
      super();
      this.mCardBackground = ((ImageView)paramView.findViewById(2131493025));
      this.mCardNameContainer = ((ViewGroup)paramView.findViewById(2131493026));
      this.mCardName = ((TextView)paramView.findViewById(2131493027));
      paramView.setTag(285212672, this.mCardNameContainer);
    }
  }

  public static abstract interface OnItemClickListener
  {
    public abstract void onItemClick(IssuedTransCardListAdapter paramIssuedTransCardListAdapter, View paramView, int paramInt);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.IssuedTransCardListAdapter
 * JD-Core Version:    0.6.0
 */