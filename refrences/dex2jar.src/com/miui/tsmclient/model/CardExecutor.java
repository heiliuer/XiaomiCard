package com.miui.tsmclient.model;

import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclientsdk.MiTsmCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CardExecutor
{
  private static final int POOL_SIZE = 2;
  private static CardExecutor sInstance;
  private static int sReferenceCount;
  private Map<String, ICardOperation> mCardType2OperationMap = new HashMap();
  public ExecutorService mExecutor = Executors.newFixedThreadPool(2);
  private Map<String, List<ICardOperation>> mTag2RunningClientsMap = new HashMap();
  private Map<String, List<Future>> mTag2RunningResultMap = new HashMap();

  /** @deprecated */
  public static CardExecutor getInstance()
  {
    monitorenter;
    try
    {
      if (sInstance == null)
        sInstance = new CardExecutor();
      CardExecutor localCardExecutor = sInstance;
      monitorexit;
      return localCardExecutor;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public ICardOperation createCardOperation(String paramString1, String paramString2)
  {
    monitorenter;
    try
    {
      ICardOperation localICardOperation;
      if (this.mCardType2OperationMap.containsKey(paramString2))
        localICardOperation = (ICardOperation)this.mCardType2OperationMap.get(paramString2);
      while (true)
      {
        Object localObject2 = (List)this.mTag2RunningClientsMap.get(paramString1);
        if (localObject2 == null)
        {
          localObject2 = new ArrayList();
          this.mTag2RunningClientsMap.put(paramString1, localObject2);
          sReferenceCount = 1 + sReferenceCount;
        }
        ((List)localObject2).add(localICardOperation);
        return localICardOperation;
        localICardOperation = CardOperationFactory.createCardOperation(paramString2);
        this.mCardType2OperationMap.put(paramString2, localICardOperation);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  /** @deprecated */
  public void execute(Runnable paramRunnable)
  {
    monitorenter;
    try
    {
      this.mExecutor.execute(paramRunnable);
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

  /** @deprecated */
  public void notifyResult(BaseResponse paramBaseResponse, MiTsmCallback paramMiTsmCallback)
  {
    monitorenter;
    while (true)
    {
      try
      {
        boolean bool = this.mExecutor.isShutdown();
        if ((bool) || (paramBaseResponse == null) || (paramMiTsmCallback == null))
          return;
        if (paramBaseResponse.mResultCode == 0)
        {
          paramMiTsmCallback.onSuccess(paramBaseResponse.mResultCode, paramBaseResponse.mDatas);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      LogUtils.d("api execute failed,errorcode is:" + paramBaseResponse.mResultCode + ",errormsg is:" + paramBaseResponse.mMsg);
      paramMiTsmCallback.onFail(paramBaseResponse.mResultCode, paramBaseResponse.mMsg, paramBaseResponse.mDatas);
    }
  }

  /** @deprecated */
  public void release(String paramString)
  {
    monitorenter;
    try
    {
      if (!this.mTag2RunningResultMap.containsKey(paramString))
        break label90;
      List localList2 = (List)this.mTag2RunningResultMap.get(paramString);
      if (localList2 != null)
      {
        Iterator localIterator2 = localList2.iterator();
        while (localIterator2.hasNext())
          ((Future)localIterator2.next()).cancel(true);
      }
    }
    finally
    {
      monitorexit;
    }
    this.mTag2RunningResultMap.remove(paramString);
    label90: if (!this.mTag2RunningClientsMap.containsKey(paramString))
      LogUtils.d("tag:" + paramString + " never used,so return.");
    while (true)
    {
      monitorexit;
      return;
      List localList1 = (List)this.mTag2RunningClientsMap.get(paramString);
      if ((localList1 != null) && (!localList1.isEmpty()))
      {
        Iterator localIterator1 = localList1.iterator();
        while (localIterator1.hasNext())
          ((ICardOperation)localIterator1.next()).terminate();
      }
      this.mTag2RunningClientsMap.remove(paramString);
      sReferenceCount = -1 + sReferenceCount;
      if (sReferenceCount != 0)
        continue;
      LogUtils.d("card executor has no reference,so shutdown");
      this.mExecutor.shutdownNow();
      this.mCardType2OperationMap.clear();
      this.mTag2RunningClientsMap.clear();
      sInstance = null;
    }
  }

  /** @deprecated */
  public void submit(String paramString, Runnable paramRunnable)
  {
    monitorenter;
    try
    {
      Object localObject2 = (List)this.mTag2RunningResultMap.get(paramString);
      if (localObject2 == null)
        localObject2 = new ArrayList();
      ((List)localObject2).add(this.mExecutor.submit(paramRunnable));
      this.mTag2RunningResultMap.put(paramString, localObject2);
      monitorexit;
      return;
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
    }
    throw localObject1;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.CardExecutor
 * JD-Core Version:    0.6.0
 */