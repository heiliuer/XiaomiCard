package com.miui.tsmclient.ui.quick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.UiUtils;
import miui.app.Activity;

public class DoubleClickActivity extends Activity
{
  private static final String ACTION_DOUBLE_CLICK = "com.miui.intent.action.DOUBLE_CLICK";
  public static final String EVENT_SOURCE_KEY_RF_ON = "key_rf_on";
  public static final String EVENT_SOURCE_KEY_VOLUME_DOWN = "key_volume_down";
  public static final String KEY_EVENT_SOURCE = "event_source";
  public static final int REQUEST_OPEN_BANKCARD = 1;
  private SwitchCardFragment mSwitchCardFragment;
  private Bitmap mWindowBg;

  private String getCalledSource(Intent paramIntent)
  {
    String str;
    if (paramIntent != null)
    {
      str = paramIntent.getStringExtra("event_source");
      LogUtils.i("event_source: " + str);
    }
    while (true)
    {
      return str;
      str = null;
    }
  }

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
      localPaint.setAlpha(220);
      localCanvas.drawRect(0.0F, 0.0F, paramBitmap.getWidth(), paramBitmap.getHeight(), localPaint);
      localCanvas.save(31);
      localCanvas.restore();
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((1 == paramInt1) && (paramInt2 == -1))
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setPackage("com.mipay.wallet");
      localIntent.setData(Uri.parse("https://app.mipay.com?id=mipay.home"));
      startActivity(localIntent);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(9);
    super.onCreate(paramBundle);
    getWindow().addFlags(2621568);
    Bitmap localBitmap = ((BitmapDrawable)getWallpaper()).getBitmap().copy(Bitmap.Config.ARGB_4444, true);
    this.mWindowBg = maskBitmap(localBitmap);
    getWindow().setBackgroundDrawable(new BitmapDrawable(getResources(), this.mWindowBg));
    localBitmap.recycle();
    this.mSwitchCardFragment = new SwitchCardFragment();
    this.mSwitchCardFragment.setAttachedActivitySource(getCalledSource(getIntent()));
    UiUtils.replaceFragment(this, this.mSwitchCardFragment);
  }

  protected void onDestroy()
  {
    if (this.mWindowBg != null)
      this.mWindowBg.recycle();
    super.onDestroy();
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    this.mSwitchCardFragment.setAttachedActivitySource(getCalledSource(paramIntent));
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.quick.DoubleClickActivity
 * JD-Core Version:    0.6.0
 */