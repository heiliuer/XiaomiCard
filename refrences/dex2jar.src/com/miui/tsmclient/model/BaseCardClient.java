package com.miui.tsmclient.model;

import android.content.Context;
import com.miui.tsmclient.net.TSMAuthManager;
import com.miui.tsmclient.util.LogUtils;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public abstract class BaseCardClient
{
  protected String mBussinessType = "";
  protected TSMAuthManager mTSMAuthManager = new TSMAuthManager();
  private List<BaseAppTask> mTaskList = new Vector();

  protected BaseResponse executeTask(BaseAppTask paramBaseAppTask)
  {
    this.mTaskList.add(paramBaseAppTask);
    return paramBaseAppTask.execute();
  }

  protected String getPass(Context paramContext, String paramString)
  {
    try
    {
      String str2 = this.mTSMAuthManager.genPassRequest(paramContext.getApplicationContext(), this.mBussinessType, paramString);
      str1 = str2;
      return str1;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        LogUtils.e("IOException occured when get auth pass", localIOException);
        String str1 = null;
      }
    }
  }

  public void shutDown()
  {
    List localList = this.mTaskList;
    monitorenter;
    int i = 0;
    try
    {
      while (i < this.mTaskList.size())
      {
        ((BaseAppTask)this.mTaskList.get(i)).terminate();
        i++;
      }
      this.mTaskList.clear();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.BaseCardClient
 * JD-Core Version:    0.6.0
 */