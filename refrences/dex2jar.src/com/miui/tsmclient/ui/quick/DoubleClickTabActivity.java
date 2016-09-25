package com.miui.tsmclient.ui.quick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import com.miui.tsmclient.ui.BaseTabActivity;
import com.miui.tsmclient.ui.BaseTabActivity.FragmentInfo;

public class DoubleClickTabActivity extends BaseTabActivity
{
  private static final String ACTION_DOUBLE_CLICK = "com.miui.intent.action.DOUBLE_CLICK";

  private Bitmap maskBitmap(Bitmap paramBitmap)
  {
    Bitmap localBitmap;
    if (paramBitmap == null)
      localBitmap = null;
    while (true)
    {
      return localBitmap;
      localBitmap = Bitmap.createBitmap(paramBitmap);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      localPaint.setColor(-16777216);
      localPaint.setAlpha(179);
      localCanvas.drawRect(0.0F, 0.0F, paramBitmap.getWidth(), paramBitmap.getHeight(), localPaint);
      localCanvas.save(31);
      localCanvas.restore();
    }
  }

  protected BaseTabActivity.FragmentInfo getFragment(int paramInt)
  {
    Bundle localBundle = null;
    Intent localIntent = getIntent();
    if (localIntent != null)
    {
      String str = localIntent.getAction();
      localBundle = new Bundle();
      if (!TextUtils.equals(str, "com.miui.intent.action.DOUBLE_CLICK"));
    }
    switch (1.$SwitchMap$com$miui$tsmclient$ui$quick$DoubleClickTabActivity$TabState[TabState.fromInt(paramInt).ordinal()])
    {
    default:
    case 1:
    }
    for (BaseTabActivity.FragmentInfo localFragmentInfo = null; ; localFragmentInfo = new BaseTabActivity.FragmentInfo(SwitchCardFragment.class, localBundle, false))
      return localFragmentInfo;
  }

  protected int getTabCounts()
  {
    return TabState.values().length;
  }

  protected int getTabText(int paramInt)
  {
    return TabState.fromInt(paramInt).getTabText();
  }

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(9);
    super.onCreate(paramBundle);
    getWindow().addFlags(2621440);
    Bitmap localBitmap = maskBitmap(((BitmapDrawable)getWallpaper()).getBitmap().copy(Bitmap.Config.ARGB_8888, true));
    getWindow().setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
  }

  public static enum TabState
  {
    private int mTabText;

    static
    {
      TabState[] arrayOfTabState = new TabState[1];
      arrayOfTabState[0] = SMARTCARD;
      $VALUES = arrayOfTabState;
    }

    private TabState(int paramInt)
    {
      this.mTabText = paramInt;
    }

    public static TabState fromInt(int paramInt)
    {
      if (paramInt == SMARTCARD.ordinal());
      for (TabState localTabState = SMARTCARD; ; localTabState = null)
        return localTabState;
    }

    public int getTabText()
    {
      return this.mTabText;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.quick.DoubleClickTabActivity
 * JD-Core Version:    0.6.0
 */