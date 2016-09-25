package com.miui.tsmclient.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DisplayUtils
{
  public static float pxToSp(Context paramContext, float paramFloat)
  {
    return 0.5F + paramFloat / paramContext.getResources().getDisplayMetrics().scaledDensity;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.DisplayUtils
 * JD-Core Version:    0.6.0
 */