package com.miui.tsmclient.ui;

import android.view.Menu;
import android.view.MenuItem;

public abstract interface IDetailImmersionMenu
{
  public abstract boolean onCreateOptionsMenu(Menu paramMenu);

  public abstract boolean onOptionsItemSelected(MenuItem paramMenuItem);

  public abstract void release();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.IDetailImmersionMenu
 * JD-Core Version:    0.6.0
 */