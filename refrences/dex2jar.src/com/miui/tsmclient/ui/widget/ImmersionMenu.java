package com.miui.tsmclient.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import miui.app.Activity;

public class ImmersionMenu extends FrameLayout
{
  private Button mBtnMore;
  private ImageView mIcNew;

  public ImmersionMenu(Context paramContext)
  {
    super(paramContext);
  }

  public ImmersionMenu(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ImmersionMenu(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean isEnable()
  {
    if (getVisibility() == 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mBtnMore = ((Button)findViewById(2131492874));
    this.mBtnMore.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Context localContext = ImmersionMenu.this.getContext();
        if ((localContext instanceof Activity))
          ((Activity)localContext).showImmersionMenu(paramView, ImmersionMenu.this);
      }
    });
    this.mIcNew = ((ImageView)findViewById(2131492875));
  }

  public void setNewIconVisibile(boolean paramBoolean)
  {
    ImageView localImageView = this.mIcNew;
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      localImageView.setVisibility(i);
      return;
    }
  }

  public void showOptionsMenu()
  {
    Context localContext = getContext();
    if ((localContext instanceof Activity))
      ((Activity)localContext).showImmersionMenu(this.mBtnMore, this);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.widget.ImmersionMenu
 * JD-Core Version:    0.6.0
 */