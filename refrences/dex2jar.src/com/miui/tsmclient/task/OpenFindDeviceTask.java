package com.miui.tsmclient.task;

import android.content.Context;
import com.miui.tsmclient.net.AuthRequest;
import com.miui.tsmclient.util.SysUtils;
import miui.util.async.Task;

public class OpenFindDeviceTask extends Task<Boolean>
{
  private Context mContext;

  public OpenFindDeviceTask(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public Boolean doLoad()
    throws Exception
  {
    if (AuthRequest.STAGING);
    for (Boolean localBoolean = Boolean.valueOf(true); ; localBoolean = Boolean.valueOf(SysUtils.checkFindMyDeviceStatus(this.mContext)))
      return localBoolean;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.task.OpenFindDeviceTask
 * JD-Core Version:    0.6.0
 */