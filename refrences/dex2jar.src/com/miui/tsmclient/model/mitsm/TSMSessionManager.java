package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.seitsm.SeiTsmAuthManager;
import com.miui.tsmclient.seitsm.TsmRpcModels.TsmSessionInfo;
import com.miui.tsmclient.util.LogUtils;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TSMSessionManager
{
  private static TSMSessionManager sInstance = new TSMSessionManager();
  private Map<String, TsmRpcModels.TsmSessionInfo> mBusiness2SessionMap = new ConcurrentHashMap();
  private SeiTsmAuthManager mSeiTsmAuthManager = new SeiTsmAuthManager();

  public static TSMSessionManager getInstance()
  {
    return sInstance;
  }

  private String getRealBusinessId(CardInfo paramCardInfo, BusinessType paramBusinessType)
  {
    if ((paramCardInfo == null) || (paramBusinessType == null));
    for (String str = null; ; str = paramCardInfo.mCardType + "_" + paramBusinessType.toString())
      return str;
  }

  public TsmRpcModels.TsmSessionInfo getSession(Context paramContext, CardInfo paramCardInfo)
    throws IOException
  {
    return this.mSeiTsmAuthManager.createSession(paramContext, paramCardInfo);
  }

  public TsmRpcModels.TsmSessionInfo getSession(Context paramContext, CardInfo paramCardInfo, BusinessType paramBusinessType, boolean paramBoolean)
    throws IOException
  {
    String str = getRealBusinessId(paramCardInfo, paramBusinessType);
    TsmRpcModels.TsmSessionInfo localTsmSessionInfo2;
    synchronized (this.mBusiness2SessionMap)
    {
      if ((!this.mBusiness2SessionMap.containsKey(str)) || (paramBoolean))
      {
        TsmRpcModels.TsmSessionInfo localTsmSessionInfo1 = this.mSeiTsmAuthManager.createSession(paramContext, paramCardInfo);
        if (localTsmSessionInfo1 == null)
        {
          LogUtils.d("cannot get session info.createSession failed.");
          localTsmSessionInfo2 = null;
        }
        else
        {
          this.mBusiness2SessionMap.put(str, localTsmSessionInfo1);
        }
      }
      else
      {
        localTsmSessionInfo2 = (TsmRpcModels.TsmSessionInfo)this.mBusiness2SessionMap.get(str);
      }
    }
    return localTsmSessionInfo2;
  }

  public TsmRpcModels.TsmSessionInfo removeSession(CardInfo paramCardInfo, BusinessType paramBusinessType)
  {
    return (TsmRpcModels.TsmSessionInfo)this.mBusiness2SessionMap.remove(getRealBusinessId(paramCardInfo, paramBusinessType));
  }

  public static enum BusinessType
  {
    static
    {
      DELETE = new BusinessType("DELETE", 1);
      RECHARGE = new BusinessType("RECHARGE", 2);
      LOCK = new BusinessType("LOCK", 3);
      QUERY = new BusinessType("QUERY", 4);
      PREPARE_PAY = new BusinessType("PREPARE_PAY", 5);
      PERSO = new BusinessType("PERSO", 6);
      SAVEKEY = new BusinessType("SAVEKEY", 7);
      BusinessType[] arrayOfBusinessType = new BusinessType[8];
      arrayOfBusinessType[0] = INSTALL;
      arrayOfBusinessType[1] = DELETE;
      arrayOfBusinessType[2] = RECHARGE;
      arrayOfBusinessType[3] = LOCK;
      arrayOfBusinessType[4] = QUERY;
      arrayOfBusinessType[5] = PREPARE_PAY;
      arrayOfBusinessType[6] = PERSO;
      arrayOfBusinessType[7] = SAVEKEY;
      $VALUES = arrayOfBusinessType;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.mitsm.TSMSessionManager
 * JD-Core Version:    0.6.0
 */