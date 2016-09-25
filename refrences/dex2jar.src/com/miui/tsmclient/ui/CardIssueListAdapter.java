package com.miui.tsmclient.ui;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.IChildCity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardIssueListAdapter extends BaseExpandableListAdapter
{
  private List<CardInfo> mCardInfoList = new ArrayList();
  private Context mContext;
  private final LayoutInflater mInflater;
  private Map<String, List<? extends IChildCity>> mSubListMap;

  public CardIssueListAdapter(Context paramContext)
  {
    this.mContext = paramContext;
    this.mSubListMap = new HashMap();
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  private void refreshIssueState(ItemViewHolder paramItemViewHolder, CardInfo paramCardInfo)
  {
    if (paramCardInfo.mHasIssue)
    {
      paramItemViewHolder.mTvDescription.setTextColor(this.mContext.getResources().getColor(2131165246));
      paramItemViewHolder.mTvDescription.setText(2131296352);
    }
    while (true)
    {
      return;
      paramItemViewHolder.mTvDescription.setTextColor(this.mContext.getResources().getColor(2131165284));
      paramItemViewHolder.mTvDescription.setText(2131296351);
    }
  }

  public IChildCity getChild(int paramInt1, int paramInt2)
  {
    return (IChildCity)((List)this.mSubListMap.get(((CardInfo)this.mCardInfoList.get(paramInt1)).mAid)).get(paramInt2);
  }

  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2;
  }

  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    ItemViewHolder localItemViewHolder;
    if (localView == null)
    {
      localView = this.mInflater.inflate(2130903060, paramViewGroup, false);
      localItemViewHolder = new ItemViewHolder(null);
      localItemViewHolder.mTvCardName = ((TextView)localView.findViewById(2131492963));
      localItemViewHolder.mTvDescription = ((TextView)localView.findViewById(2131492964));
      localView.setTag(localItemViewHolder);
    }
    while (true)
    {
      CardInfo localCardInfo = getGroup(paramInt1);
      IChildCity localIChildCity = getChild(paramInt1, paramInt2);
      localItemViewHolder.mTvCardName.setText(localIChildCity.getCityNameRes());
      refreshIssueState(localItemViewHolder, localCardInfo);
      return localView;
      localItemViewHolder = (ItemViewHolder)localView.getTag();
    }
  }

  public int getChildrenCount(int paramInt)
  {
    int i = 0;
    CardInfo localCardInfo = (CardInfo)this.mCardInfoList.get(paramInt);
    if (TextUtils.isEmpty(localCardInfo.mAid));
    while (true)
    {
      return i;
      List localList = (List)this.mSubListMap.get(localCardInfo.mAid);
      if (localList == null)
        continue;
      i = localList.size();
    }
  }

  public CardInfo getGroup(int paramInt)
  {
    return (CardInfo)this.mCardInfoList.get(paramInt);
  }

  public int getGroupCount()
  {
    return this.mCardInfoList.size();
  }

  public long getGroupId(int paramInt)
  {
    return paramInt;
  }

  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    GroupViewHolder localGroupViewHolder;
    CardInfo localCardInfo;
    int j;
    if (localView == null)
    {
      localView = this.mInflater.inflate(2130903059, paramViewGroup, false);
      localGroupViewHolder = new GroupViewHolder(null);
      localGroupViewHolder.mTvCardName = ((TextView)localView.findViewById(2131492963));
      localGroupViewHolder.mTvDescription = ((TextView)localView.findViewById(2131492964));
      localGroupViewHolder.mIvArrow = ((ImageView)localView.findViewById(2131492965));
      localView.setTag(localGroupViewHolder);
      localCardInfo = getGroup(paramInt);
      localGroupViewHolder.mTvCardName.setText(localCardInfo.mCardName);
      int i = getChildrenCount(paramInt);
      if (i <= 0)
        break label226;
      localGroupViewHolder.mTvDescription.setTextColor(this.mContext.getResources().getColor(2131165284));
      TextView localTextView = localGroupViewHolder.mTvDescription;
      String str = this.mContext.getString(2131296353);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(i);
      localTextView.setText(String.format(str, arrayOfObject));
      ImageView localImageView = localGroupViewHolder.mIvArrow;
      if (!paramBoolean)
        break label219;
      j = 2130837519;
      label196: localImageView.setImageResource(j);
    }
    while (true)
    {
      return localView;
      localGroupViewHolder = (GroupViewHolder)localView.getTag();
      break;
      label219: j = 2130837511;
      break label196;
      label226: localGroupViewHolder.mIvArrow.setImageResource(2130837512);
      refreshIssueState(localGroupViewHolder, localCardInfo);
    }
  }

  public boolean hasStableIds()
  {
    return false;
  }

  public boolean isChildSelectable(int paramInt1, int paramInt2)
  {
    return true;
  }

  public void setData(List<CardInfo> paramList)
  {
    this.mCardInfoList.clear();
    if (paramList != null)
      this.mCardInfoList.addAll(paramList);
    notifyDataSetChanged();
  }

  public void setSubData(String paramString, List<? extends IChildCity> paramList)
  {
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return;
      this.mSubListMap.put(paramString, paramList);
      notifyDataSetChanged();
    }
  }

  private static class GroupViewHolder extends CardIssueListAdapter.ItemViewHolder
  {
    ImageView mIvArrow;

    private GroupViewHolder()
    {
      super();
    }
  }

  private static class ItemViewHolder
  {
    TextView mTvCardName;
    TextView mTvDescription;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardIssueListAdapter
 * JD-Core Version:    0.6.0
 */