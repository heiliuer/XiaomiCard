package com.miui.tsmclient.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardDetailMoreItem extends LinearLayout
{
  private TextView mTvTitle;

  public CardDetailMoreItem(Context paramContext)
  {
    super(paramContext);
  }

  public CardDetailMoreItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public CardDetailMoreItem(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void bindData(String paramString)
  {
    this.mTvTitle.setText(paramString);
  }

  public void init()
  {
    this.mTvTitle = ((TextView)findViewById(2131492923));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardDetailMoreItem
 * JD-Core Version:    0.6.0
 */