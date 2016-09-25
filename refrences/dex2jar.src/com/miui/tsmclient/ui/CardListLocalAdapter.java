package com.miui.tsmclient.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardUIInfo;
import com.miui.tsmclient.ui.widget.LayoutAware;
import com.miui.tsmclient.ui.widget.MiuiDigitFontTextView;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class CardListLocalAdapter extends BaseAdapter
{
  private Context mContext;
  private List<CardInfo> mDatas = new ArrayList();
  private CardInfo mDefaultCard;
  private final LayoutInflater mInflater;
  private List<String> mLoadingCards = new Vector();

  public CardListLocalAdapter(Context paramContext)
  {
    this.mContext = paramContext;
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  private void fillContent(CardItemHolder paramCardItemHolder, CardInfo paramCardInfo)
  {
    String str1 = null;
    String str3;
    if (paramCardInfo.mCardUIInfo != null)
    {
      if (paramCardInfo.mHasIssue)
        str1 = paramCardInfo.mCardUIInfo.mCardIssuedListBgUrl;
    }
    else
    {
      paramCardItemHolder.mLayoutRoot.setBackgroundResource(2130837550);
      if (!TextUtils.isEmpty(str1))
        ImageLoader.getInstance().displayImage(str1, new LayoutAware(this.mContext, paramCardItemHolder.mLayoutRoot));
      String str2 = paramCardInfo.mCardName;
      if (!TextUtils.isEmpty(paramCardInfo.mCardSubName))
        str2 = str2 + "·" + paramCardInfo.mCardSubName;
      paramCardItemHolder.mTvCardName.setText(str2);
      if (!paramCardInfo.mHasIssue)
        break label368;
      paramCardItemHolder.mLayoutBalance.setVisibility(0);
      paramCardItemHolder.mTvInstall.setVisibility(8);
      paramCardItemHolder.mTvCardBalance.setText(StringUtils.covertFloatToString(paramCardInfo.mCardBalance / 100.0F));
      TextView localTextView = paramCardItemHolder.mTvCardDesc;
      Context localContext = this.mContext.getApplicationContext();
      Object[] arrayOfObject = new Object[1];
      if (paramCardInfo.mCardNo != null)
        break label359;
      str3 = "";
      label190: arrayOfObject[0] = str3;
      localTextView.setText(localContext.getString(2131296354, arrayOfObject));
      paramCardItemHolder.mTvCardUnit.setText(this.mContext.getString(2131296369));
      label226: paramCardItemHolder.mIcLogo.setVisibility(0);
      if (getDefaultLogoRes(paramCardInfo) > 0)
        paramCardItemHolder.mIcLogo.setImageResource(getDefaultLogoRes(paramCardInfo));
      if ((paramCardInfo.mCardUIInfo != null) && (!TextUtils.isEmpty(paramCardInfo.mCardUIInfo.mCardLogoUrl)))
        ImageLoader.getInstance().displayImage(paramCardInfo.mCardUIInfo.mCardLogoUrl, paramCardItemHolder.mIcLogo);
      if (!this.mLoadingCards.contains(paramCardInfo.mCardType))
        break label409;
      paramCardItemHolder.mLoading.setVisibility(0);
      label315: if ((this.mDefaultCard == null) || (!TextUtils.equals(paramCardInfo.mAid, this.mDefaultCard.mAid)))
        break label421;
      paramCardItemHolder.mTvDefaultCard.setVisibility(0);
    }
    while (true)
    {
      return;
      str1 = paramCardInfo.mCardUIInfo.mCardPreIssuedListBgUrl;
      break;
      label359: str3 = paramCardInfo.mCardNo;
      break label190;
      label368: paramCardItemHolder.mLayoutBalance.setVisibility(8);
      paramCardItemHolder.mTvInstall.setVisibility(0);
      if (paramCardInfo.mCardUIInfo == null)
        break label226;
      paramCardItemHolder.mTvCardDesc.setText(paramCardInfo.mCardUIInfo.mCardDesc);
      break label226;
      label409: paramCardItemHolder.mLoading.setVisibility(8);
      break label315;
      label421: paramCardItemHolder.mTvDefaultCard.setVisibility(8);
    }
  }

  private int getDefaultLogoRes(CardInfo paramCardInfo)
  {
    int i;
    if (TextUtils.equals(paramCardInfo.mCardType, "SPTC"))
      i = 2130837620;
    while (true)
    {
      return i;
      if (TextUtils.equals(paramCardInfo.mCardType, "LNT"))
      {
        i = 2130837619;
        continue;
      }
      if (TextUtils.equals(paramCardInfo.mCardType, "SZT"))
      {
        i = 2130837621;
        continue;
      }
      i = -1;
    }
  }

  public int findDataPosition(CardInfo paramCardInfo)
  {
    int i = 0;
    if (i < this.mDatas.size())
      if (!TextUtils.equals(((CardInfo)this.mDatas.get(i)).mCardType, paramCardInfo.mCardType));
    while (true)
    {
      return i;
      i++;
      break;
      i = -1;
    }
  }

  public int getCount()
  {
    return this.mDatas.size();
  }

  public CardInfo getItem(int paramInt)
  {
    Object[] arrayOfObject = this.mDatas.toArray();
    if ((paramInt > -1) && (paramInt < arrayOfObject.length));
    for (CardInfo localCardInfo = (CardInfo)arrayOfObject[paramInt]; ; localCardInfo = null)
      return localCardInfo;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView;
    if ((paramInt < 0) || (paramInt >= this.mDatas.size()))
    {
      LogUtils.w("CardListLocalAdapter#getView() called, but the param position is invalid with the value :" + paramInt + ",so return the convertView without fill data!");
      localView = paramView;
      return localView;
    }
    CardItemHolder localCardItemHolder;
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(2130903061, null);
      localCardItemHolder = new CardItemHolder(null);
      localCardItemHolder.mTvCardName = ((TextView)paramView.findViewById(2131492915));
      localCardItemHolder.mTvCardBalance = ((MiuiDigitFontTextView)paramView.findViewById(2131492969));
      localCardItemHolder.mTvCardDesc = ((TextView)paramView.findViewById(2131492967));
      localCardItemHolder.mTvCardUnit = ((TextView)paramView.findViewById(2131492970));
      localCardItemHolder.mIcLogo = ((ImageView)paramView.findViewById(2131492966));
      localCardItemHolder.mLoading = paramView.findViewById(2131492972);
      localCardItemHolder.mTvInstall = ((TextView)paramView.findViewById(2131492971));
      localCardItemHolder.mLayoutBalance = ((RelativeLayout)paramView.findViewById(2131492968));
      localCardItemHolder.mLayoutRoot = ((RelativeLayout)paramView.findViewById(2131492910));
      localCardItemHolder.mTvDefaultCard = ((TextView)paramView.findViewById(2131492973));
      paramView.setTag(localCardItemHolder);
    }
    while (true)
    {
      CardInfo localCardInfo = getItem(paramInt);
      if ((localCardInfo != null) && (localCardItemHolder != null))
        fillContent(localCardItemHolder, localCardInfo);
      localView = paramView;
      break;
      localCardItemHolder = (CardItemHolder)paramView.getTag();
    }
  }

  public void release()
  {
  }

  public void setData(List<CardInfo> paramList)
  {
    this.mDatas.clear();
    if (paramList != null)
      this.mDatas.addAll(paramList);
    notifyDataSetChanged();
  }

  public void setDefaultCard(String paramString)
  {
    Iterator localIterator = this.mDatas.iterator();
    while (localIterator.hasNext())
    {
      CardInfo localCardInfo = (CardInfo)localIterator.next();
      if (!TextUtils.equals(localCardInfo.mAid, paramString))
        continue;
      this.mDefaultCard = localCardInfo;
      notifyDataSetChanged();
    }
  }

  public void startLoading(String paramString)
  {
    if (!this.mLoadingCards.contains(paramString))
      this.mLoadingCards.add(paramString);
  }

  public void stopLoading(String paramString)
  {
    this.mLoadingCards.remove(paramString);
  }

  private static class CardItemHolder
  {
    ImageView mIcLogo;
    RelativeLayout mLayoutBalance;
    RelativeLayout mLayoutRoot;
    View mLoading;
    MiuiDigitFontTextView mTvCardBalance;
    TextView mTvCardDesc;
    TextView mTvCardName;
    TextView mTvCardUnit;
    TextView mTvDefaultCard;
    TextView mTvInstall;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardListLocalAdapter
 * JD-Core Version:    0.6.0
 */