package com.miui.tsmclient.ui;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import com.miui.tsmclient.entity.CardInfo;

public class CardDetailMenuManager
{
  private IDetailImmersionMenu mDetailImmersionMenu;

  public CardDetailMenuManager(Activity paramActivity, CardInfo paramCardInfo)
  {
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if ((this.mDetailImmersionMenu != null) && (this.mDetailImmersionMenu.onCreateOptionsMenu(paramMenu)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if ((this.mDetailImmersionMenu != null) && (this.mDetailImmersionMenu.onOptionsItemSelected(paramMenuItem)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public void release()
  {
    if (this.mDetailImmersionMenu != null)
      this.mDetailImmersionMenu.release();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.ui.CardDetailMenuManager
 * JD-Core Version:    0.6.0
 */