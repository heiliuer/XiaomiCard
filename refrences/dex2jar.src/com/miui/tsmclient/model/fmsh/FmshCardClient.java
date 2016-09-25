package com.miui.tsmclient.model.fmsh;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import cn.com.fmsh.nfcos.client.service.xm.CardAppInfo;
import cn.com.fmsh.nfcos.client.service.xm.CardAppManager;
import cn.com.fmsh.nfcos.client.service.xm.LoginInfo;
import cn.com.fmsh.nfcos.client.service.xm.NfcosBusinessOrder;
import cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder;
import cn.com.fmsh.nfcos.client.service.xm.UserInfo;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumCardIoType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import com.miui.tsmclient.account.TSMAccountManager;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.FmshCardInfo;
import com.miui.tsmclient.model.BaseCardClient;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.net.TSMAuthContants.BussinessType;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.tsmclient.smartcard.Coder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class FmshCardClient extends BaseCardClient
{
  public static final int GET_INFO_REQUEST_TYPE_CARD_NO = 1;
  public static final int GET_INFO_REQUEST_TYPE_OVERAGE = 2;
  public static final int GET_INFO_REQUEST_TYPE_PURCHASE_RECORD = 4;
  protected static final byte ISSUE_HANDLE_FLAG_LNT = 3;
  protected static final byte ISSUE_HANDLE_FLAG_SPTC = 1;
  protected static final int OMA_CHANNEL_TYPE_ESE = 1;
  private static final int PAGE_SIZE_QUERY_ORDERS = 10;
  private static final String TAG = "FmshCardClient";
  private static final Semaphore sSemaphore = new Semaphore(1);
  protected TSMAccountManager mAccountManager = new TSMAccountManager();

  public FmshCardClient()
  {
    this.mBussinessType = TSMAuthContants.BussinessType.sptc.toString();
  }

  static int getCardAppType(String paramString)
  {
    if (TextUtils.equals(paramString, "SPTC"));
    for (int i = EnumCardAppType.CARD_APP_TYPE_SH.getId(); ; i = 0)
      return i;
  }

  private NfcosBusinessOrder getIssueOrder(CardAppManager paramCardAppManager, int paramInt)
    throws RemoteException
  {
    ArrayList localArrayList = new ArrayList();
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = EnumOrderStatus.hasPaid.getId();
    paramCardAppManager.queryBusinessOrders(0, 10, paramInt, arrayOfInt, EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId(), localArrayList);
    if (!localArrayList.isEmpty());
    for (NfcosBusinessOrder localNfcosBusinessOrder = (NfcosBusinessOrder)localArrayList.get(0); ; localNfcosBusinessOrder = null)
      return localNfcosBusinessOrder;
  }

  private boolean handleCardNotActivate(Context paramContext, CardAppManager paramCardAppManager, NfcosBusinessOrder paramNfcosBusinessOrder, int paramInt)
    throws RemoteException
  {
    int i = 0;
    if ((paramNfcosBusinessOrder == null) || (paramNfcosBusinessOrder.businessOrderType != EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId()))
      paramNfcosBusinessOrder = getIssueOrder(paramCardAppManager, paramInt);
    byte b;
    if (paramNfcosBusinessOrder != null)
    {
      b = 0;
      if (EnumCardAppType.CARD_APP_TYPE_SH.getId() != paramInt)
        break label119;
      b = 1;
    }
    while (true)
    {
      Object localObject = null;
      try
      {
        byte[] arrayOfByte = getSeid(paramContext);
        localObject = arrayOfByte;
        if (localObject != null)
        {
          int j = paramCardAppManager.doIssue(paramNfcosBusinessOrder.order, b, localObject, null);
          LogUtils.d("continue do issue,result:" + j);
          if (j == 0)
            i = 1;
        }
        return i;
        label119: if (EnumCardAppType.CARD_APP_TYPE_LNT.getId() != paramInt)
          continue;
        b = 3;
      }
      catch (IOException localIOException)
      {
        while (true)
          LogUtils.e("get seid failed!", localIOException);
      }
    }
  }

  private int handleUnsolvedOrder(Context paramContext, CardAppManager paramCardAppManager, int paramInt, NfcosBusinessOrder paramNfcosBusinessOrder, FmshCardInfo paramFmshCardInfo)
    throws RemoteException
  {
    int j;
    if (paramNfcosBusinessOrder.businessOrderType == EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId())
    {
      if (handleCardNotActivate(paramContext, paramCardAppManager, paramNfcosBusinessOrder, paramInt));
      for (j = 0; ; j = 9999)
        return j;
    }
    int i;
    if ((paramNfcosBusinessOrder.tradeState == EnumOrderStatus.hasPaid.getId()) || (paramNfcosBusinessOrder.tradeState == EnumOrderStatus.failure.getId()))
    {
      LogUtils.d("order has applyRecharge success,so recharge directly");
      i = paramCardAppManager.recharge(paramNfcosBusinessOrder.order, paramFmshCardInfo.mAppNo);
      if ((i == 1035) || (i == 1031))
      {
        LogUtils.d("card has not activated!");
        if (handleCardNotActivate(paramContext, paramCardAppManager, paramNfcosBusinessOrder, paramInt))
          i = paramCardAppManager.recharge(paramNfcosBusinessOrder.order, paramFmshCardInfo.mAppNo);
      }
    }
    while (true)
    {
      if (i == 1104)
        i = 0;
      j = i;
      break;
      if (paramNfcosBusinessOrder.tradeState == EnumOrderStatus.unsettled.getId())
      {
        LogUtils.d("order status is unkown!");
        i = paramCardAppManager.doUnsolvedOrder(paramNfcosBusinessOrder.order);
        if (i != 0)
          continue;
        NfcosBusinessOrder localNfcosBusinessOrder = new NfcosBusinessOrder();
        paramCardAppManager.queryBusinessOrder(paramNfcosBusinessOrder.order, localNfcosBusinessOrder);
        LogUtils.d("doUnsolvedOrder success,now the order state is:" + localNfcosBusinessOrder.tradeState);
        handleUnsolvedOrder(paramContext, paramCardAppManager, paramInt, localNfcosBusinessOrder, paramFmshCardInfo);
        continue;
      }
      i = 9999;
    }
  }

  private boolean isSameCardIOType(Tag paramTag, NfcosBusinessOrder paramNfcosBusinessOrder)
  {
    if (((paramTag != null) && (paramNfcosBusinessOrder.cardIoType == EnumCardIoType.CARD_IO_TYPE_OUT.getId())) || ((paramTag == null) && (paramNfcosBusinessOrder.cardIoType == EnumCardIoType.CARD_IO_TYPE_IN.getId())));
    for (int i = 1; ; i = 0)
      return i;
  }

  private void lock()
  {
    LogUtils.d("Thread id: " + Thread.currentThread().getId() + " is Acquiring a permit");
    sSemaphore.acquireUninterruptibly();
    LogUtils.d("Acquire success!");
  }

  private void unLock()
  {
    LogUtils.d("Thread id: " + Thread.currentThread().getId() + " is release a permit");
    sSemaphore.release();
  }

  BaseResponse applyIssue(Context paramContext, int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    if (paramContext == null);
    for (BaseResponse localBaseResponse = new BaseResponse(1, "applyIssue() called,but the param is invalid!", new Object[0]); ; localBaseResponse = executeTask(new FmshAppTask(paramContext, paramContext, paramInt1, paramInt2, paramInt3, paramArrayOfByte, paramString1, paramString2, new NfcosMainOrder())
    {
      protected BaseResponse doInBackgroud()
      {
        LogUtils.d("applyIssue() called!");
        CardAppManager localCardAppManager = getService();
        if (localCardAppManager != null);
        while (true)
        {
          try
          {
            if (!FmshCardClient.this.login(localCardAppManager, this.val$context))
              continue;
            i = localCardAppManager.applyIssue(this.val$cardAppType, this.val$amount, this.val$mode, this.val$seid, this.val$deviceModel, Coder.hexStringToBytes(this.val$actCode), this.val$mainOrder);
            if (i != 1103)
              break label152;
            i = 1000;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = this.val$mainOrder;
            localBaseResponse = new BaseResponse(i, arrayOfObject);
            break label150;
            localBaseResponse = new BaseResponse(5, "login failed!", new Object[0]);
          }
          catch (RemoteException localRemoteException)
          {
            LogUtils.e("exception occured when apply4OrderEx() running", localRemoteException);
          }
          BaseResponse localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]);
          label150: return localBaseResponse;
          label152: if (i != 1041)
            continue;
          int i = 2001;
        }
      }
    }))
      return localBaseResponse;
  }

  BaseResponse applyIssueByProduct(Context paramContext, int paramInt1, String paramString1, int paramInt2, byte[] paramArrayOfByte, String paramString2, String paramString3)
  {
    if (paramContext == null);
    for (BaseResponse localBaseResponse = new BaseResponse(1, "applyIssueByProduct() called,but the param is invalid!", new Object[0]); ; localBaseResponse = executeTask(new FmshAppTask(paramContext, paramContext, paramInt1, paramString1, paramInt2, paramArrayOfByte, paramString2, paramString3, new NfcosMainOrder())
    {
      protected BaseResponse doInBackgroud()
      {
        LogUtils.d("applyIssueByProduct() called!");
        CardAppManager localCardAppManager = getService();
        if (localCardAppManager != null);
        while (true)
        {
          try
          {
            if (!FmshCardClient.this.login(localCardAppManager, this.val$context))
              continue;
            i = localCardAppManager.applyIssueByProduct(this.val$cardAppType, this.val$productCode, this.val$mode, this.val$seid, this.val$deviceModel, Coder.hexStringToBytes(this.val$actCode), this.val$mainOrder);
            if (i != 1103)
              break label152;
            i = 1000;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = this.val$mainOrder;
            localBaseResponse = new BaseResponse(i, arrayOfObject);
            break label150;
            localBaseResponse = new BaseResponse(5, "login failed!", new Object[0]);
          }
          catch (RemoteException localRemoteException)
          {
            LogUtils.e("exception occured when apply4OrderEx() running", localRemoteException);
          }
          BaseResponse localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]);
          label150: return localBaseResponse;
          label152: if (i != 1041)
            continue;
          int i = 2001;
        }
      }
    }))
      return localBaseResponse;
  }

  BaseResponse applyRecharge(Context paramContext, int paramInt1, int paramInt2, ActionToken paramActionToken, byte[] paramArrayOfByte, Tag paramTag)
  {
    Object localObject1;
    if (paramContext == null)
      localObject1 = new BaseResponse(1, "applyRecharge() called,but the param is invalid! context = null!", new Object[0]);
    while (true)
    {
      return localObject1;
      7 local7 = new FmshAppTask(paramContext, paramTag, paramInt1, paramContext, paramActionToken, paramInt2, paramArrayOfByte, new NfcosMainOrder())
      {
        protected BaseResponse doInBackgroud()
        {
          LogUtils.d("applyRecharge() called!");
          CardAppManager localCardAppManager = getService();
          if (localCardAppManager != null)
          {
            try
            {
              if (this.val$tag != null)
                localCardAppManager.switchMode2NFC(this.val$tag);
              while (FmshCardClient.this.login(localCardAppManager, this.val$context))
              {
                int i = localCardAppManager.applyRecharge(this.val$cardAppType, this.val$actionToken.mRechargeAmount, this.val$rechargeMode, this.val$cardAppNo, null, this.val$mainOrder);
                if ((i == 1035) || (i == 1031))
                {
                  LogUtils.d("card has not activated!");
                  if (FmshCardClient.this.handleCardNotActivate(this.val$context, localCardAppManager, null, this.val$cardAppType))
                    i = localCardAppManager.applyRecharge(this.val$cardAppType, this.val$actionToken.mRechargeAmount, this.val$rechargeMode, this.val$cardAppNo, null, this.val$mainOrder);
                }
                Object[] arrayOfObject = new Object[1];
                arrayOfObject[0] = this.val$mainOrder;
                localBaseResponse = new BaseResponse(i, arrayOfObject);
                break label236;
                localCardAppManager.switchMode2OMA(1, this.val$cardAppType);
              }
            }
            catch (RemoteException localRemoteException)
            {
              LogUtils.e("exception occured when applyRecharge() running", localRemoteException);
            }
          }
          else
          {
            localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]);
            break label236;
          }
          BaseResponse localBaseResponse = new BaseResponse(5, "login failed!", new Object[0]);
          label236: return localBaseResponse;
        }
      };
      lock();
      try
      {
        BaseResponse localBaseResponse = executeTask(local7);
        localObject1 = localBaseResponse;
        unLock();
      }
      finally
      {
        unLock();
      }
    }
  }

  BaseResponse cancelIssue(Context paramContext, int paramInt1, int paramInt2)
  {
    if (paramContext == null);
    for (BaseResponse localBaseResponse = new BaseResponse(1, "cancelIssue() called,but the param  is invalid, context = null, stop: " + paramInt2, new Object[0]); ; localBaseResponse = executeTask(new FmshAppTask(paramContext, paramInt2)
    {
      protected BaseResponse doInBackgroud()
      {
        LogUtils.d("cancelIssue() called!");
        CardAppManager localCardAppManager = getService();
        if (localCardAppManager != null);
        while (true)
        {
          try
          {
            localBaseResponse = new BaseResponse(localCardAppManager.cancelIssue(this.val$stop), new Object[0]);
            return localBaseResponse;
          }
          catch (RemoteException localRemoteException)
          {
            LogUtils.e("exception occured when cancelIssue() running", localRemoteException);
          }
          BaseResponse localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]);
        }
      }
    }))
      return localBaseResponse;
  }

  BaseResponse doIssue(Context paramContext, int paramInt, byte[] paramArrayOfByte1, byte paramByte, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, Bundle paramBundle)
  {
    if (paramContext == null);
    for (BaseResponse localBaseResponse = new BaseResponse(1, "doIssue() called,but the param is invalid!", new Object[0]); ; localBaseResponse = executeTask(new FmshAppTask(paramContext, paramInt, paramContext, paramArrayOfByte1, paramByte, paramArrayOfByte2, paramArrayOfByte3)
    {
      protected BaseResponse doInBackgroud()
      {
        LogUtils.d("doIssue() called!");
        CardAppManager localCardAppManager = getService();
        BaseResponse localBaseResponse;
        if (localCardAppManager != null)
          try
          {
            localCardAppManager.switchMode2OMA(1, this.val$cardAppType);
            if (FmshCardClient.this.login(localCardAppManager, this.val$context))
            {
              int i = localCardAppManager.doIssue(this.val$order, this.val$handlerFlag, this.val$seid, this.val$patch);
              if ((i == 1104) || (i == 0))
              {
                localBaseResponse = new BaseResponse(0, new Object[0]);
                break label133;
              }
              localBaseResponse = new BaseResponse(i, new Object[0]);
            }
          }
          catch (RemoteException localRemoteException)
          {
            LogUtils.e("exception occured when doIssue() running", localRemoteException);
          }
        else
          localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]);
        label133: return localBaseResponse;
      }
    }))
      return localBaseResponse;
  }

  BaseResponse doIssueEx(Context paramContext, int paramInt1, int paramInt2, String paramString, byte[] paramArrayOfByte, ActionToken paramActionToken, int paramInt3, Bundle paramBundle)
  {
    if ((paramContext == null) || (paramActionToken == null));
    for (BaseResponse localBaseResponse = new BaseResponse(1, "doIssue() called,but the param is invalid!", new Object[0]); ; localBaseResponse = executeTask(new FmshAppTask(paramContext, paramInt1, paramContext, paramInt3, paramInt2, paramArrayOfByte, paramString, paramActionToken)
    {
      protected BaseResponse doInBackgroud()
      {
        LogUtils.d("doIssue() called!");
        CardAppManager localCardAppManager = getService();
        BaseResponse localBaseResponse;
        if (localCardAppManager != null)
          try
          {
            localCardAppManager.switchMode2OMA(1, this.val$cardAppType);
            if (FmshCardClient.this.login(localCardAppManager, this.val$context))
            {
              int i = localCardAppManager.doIssueEx(this.val$cardAppType, this.val$amount, this.val$rechargeMode, this.val$seid, this.val$model, Coder.hexStringToBytes(this.val$actionToken.mToken));
              if ((i == 1104) || (i == 0))
              {
                localBaseResponse = new BaseResponse(0, new Object[0]);
                break label147;
              }
              localBaseResponse = new BaseResponse(i, new Object[0]);
            }
          }
          catch (RemoteException localRemoteException)
          {
            LogUtils.e("exception occured when doIssue() running", localRemoteException);
          }
        else
          localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]);
        label147: return localBaseResponse;
      }
    }))
      return localBaseResponse;
  }

  BaseResponse downloadApplet(Context paramContext, int paramInt, byte[] paramArrayOfByte, String paramString)
  {
    if (paramContext == null);
    for (BaseResponse localBaseResponse = new BaseResponse(1, "downloadApplet() called,but the param is invalid!", new Object[0]); ; localBaseResponse = executeTask(new FmshAppTask(paramContext, paramInt, paramContext, paramArrayOfByte, paramString)
    {
      protected BaseResponse doInBackgroud()
      {
        LogUtils.d("downloadApplet() called!");
        CardAppManager localCardAppManager = getService();
        if (localCardAppManager != null);
        while (true)
        {
          try
          {
            localCardAppManager.switchMode2OMA(1, this.val$cardAppType);
            if (FmshCardClient.this.login(localCardAppManager, this.val$context))
            {
              localBaseResponse = new BaseResponse(localCardAppManager.downloadApplet(this.val$cardAppType, this.val$seid, this.val$deviceModel), new Object[0]);
              return localBaseResponse;
            }
          }
          catch (RemoteException localRemoteException)
          {
            LogUtils.e("exception occured when doIssue() running", localRemoteException);
          }
          BaseResponse localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]);
        }
      }
    }))
      return localBaseResponse;
  }

  BaseResponse getInfo(Context paramContext, int paramInt1, int paramInt2)
  {
    boolean bool;
    Object localObject1;
    if ((paramContext == null) || (paramInt2 < 0))
    {
      StringBuilder localStringBuilder = new StringBuilder().append("getInfo() called,but the param is invalid! context == null:");
      if (paramContext == null)
      {
        bool = true;
        localObject1 = new BaseResponse(1, bool + ",type:" + paramInt2, new Object[0]);
      }
    }
    while (true)
    {
      return localObject1;
      bool = false;
      break;
      12 local12 = new FmshAppTask(paramContext, paramInt1, paramInt2, new CardAppInfo())
      {
        protected BaseResponse doInBackgroud()
        {
          LogUtils.d("getInfo() called!");
          CardAppManager localCardAppManager = getService();
          if (localCardAppManager != null);
          while (true)
          {
            try
            {
              localCardAppManager.switchMode2OMA(1, this.val$cardAppType);
              int i = localCardAppManager.getInfo(this.val$type, this.val$cardAppType, this.val$info);
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = this.val$info;
              localBaseResponse = new BaseResponse(i, arrayOfObject);
              return localBaseResponse;
            }
            catch (RemoteException localRemoteException)
            {
              LogUtils.e("exception occured when getInfo() running", localRemoteException);
            }
            BaseResponse localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]);
          }
        }
      };
      lock();
      try
      {
        BaseResponse localBaseResponse = executeTask(local12);
        localObject1 = localBaseResponse;
        unLock();
      }
      finally
      {
        unLock();
      }
    }
  }

  byte[] getSeid(Context paramContext)
    throws IOException
  {
    String str = SysUtils.getCPLC(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Coder.encodeMD5(Coder.hexStringToBytes(str)).substring(0, 20)).append(str.substring(0, 4)).append(str.substring(20, 36));
    LogUtils.d("seid:" + localStringBuilder.toString());
    return Coder.hexStringToBytes(localStringBuilder.toString());
  }

  String getUserName(Context paramContext)
    throws IOException
  {
    String str = SysUtils.getCPLC(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str.substring(0, 4)).append(str.substring(20, 36));
    return localStringBuilder.toString();
  }

  BaseResponse handleUnsolvedOrder(Context paramContext, int paramInt, FmshCardInfo paramFmshCardInfo, Tag paramTag, NfcosBusinessOrder paramNfcosBusinessOrder)
  {
    if ((paramContext == null) || (paramNfcosBusinessOrder == null));
    for (BaseResponse localBaseResponse = new BaseResponse(1, "handleUnsolvedOrder() called,but the param is invalid!", new Object[0]); ; localBaseResponse = executeTask(new FmshAppTask(paramContext, paramTag, paramInt, paramContext, paramNfcosBusinessOrder, paramFmshCardInfo)
    {
      // ERROR //
      protected BaseResponse doInBackgroud()
      {
        // Byte code:
        //   0: ldc 44
        //   2: invokestatic 50	com/miui/tsmclient/util/LogUtils:d	(Ljava/lang/String;)V
        //   5: aload_0
        //   6: invokevirtual 54	com/miui/tsmclient/model/fmsh/FmshCardClient$11:getService	()Lcn/com/fmsh/nfcos/client/service/xm/CardAppManager;
        //   9: astore_1
        //   10: aload_1
        //   11: ifnull +144 -> 155
        //   14: aload_0
        //   15: getfield 27	com/miui/tsmclient/model/fmsh/FmshCardClient$11:val$tag	Landroid/nfc/Tag;
        //   18: ifnull +115 -> 133
        //   21: aload_1
        //   22: aload_0
        //   23: getfield 27	com/miui/tsmclient/model/fmsh/FmshCardClient$11:val$tag	Landroid/nfc/Tag;
        //   26: invokeinterface 60 2 0
        //   31: pop
        //   32: aload_0
        //   33: getfield 25	com/miui/tsmclient/model/fmsh/FmshCardClient$11:this$0	Lcom/miui/tsmclient/model/fmsh/FmshCardClient;
        //   36: aload_1
        //   37: aload_0
        //   38: getfield 31	com/miui/tsmclient/model/fmsh/FmshCardClient$11:val$context	Landroid/content/Context;
        //   41: invokevirtual 64	com/miui/tsmclient/model/fmsh/FmshCardClient:login	(Lcn/com/fmsh/nfcos/client/service/xm/CardAppManager;Landroid/content/Context;)Z
        //   44: ifeq +111 -> 155
        //   47: aload_0
        //   48: getfield 25	com/miui/tsmclient/model/fmsh/FmshCardClient$11:this$0	Lcom/miui/tsmclient/model/fmsh/FmshCardClient;
        //   51: invokestatic 68	com/miui/tsmclient/model/fmsh/FmshCardClient:access$200	(Lcom/miui/tsmclient/model/fmsh/FmshCardClient;)V
        //   54: aload_0
        //   55: getfield 25	com/miui/tsmclient/model/fmsh/FmshCardClient$11:this$0	Lcom/miui/tsmclient/model/fmsh/FmshCardClient;
        //   58: aload_0
        //   59: getfield 31	com/miui/tsmclient/model/fmsh/FmshCardClient$11:val$context	Landroid/content/Context;
        //   62: aload_1
        //   63: aload_0
        //   64: getfield 29	com/miui/tsmclient/model/fmsh/FmshCardClient$11:val$cardAppType	I
        //   67: aload_0
        //   68: getfield 33	com/miui/tsmclient/model/fmsh/FmshCardClient$11:val$businessOrder	Lcn/com/fmsh/nfcos/client/service/xm/NfcosBusinessOrder;
        //   71: aload_0
        //   72: getfield 35	com/miui/tsmclient/model/fmsh/FmshCardClient$11:val$info	Lcom/miui/tsmclient/entity/FmshCardInfo;
        //   75: invokestatic 72	com/miui/tsmclient/model/fmsh/FmshCardClient:access$300	(Lcom/miui/tsmclient/model/fmsh/FmshCardClient;Landroid/content/Context;Lcn/com/fmsh/nfcos/client/service/xm/CardAppManager;ILcn/com/fmsh/nfcos/client/service/xm/NfcosBusinessOrder;Lcom/miui/tsmclient/entity/FmshCardInfo;)I
        //   78: istore 6
        //   80: iload 6
        //   82: istore 7
        //   84: aload_0
        //   85: getfield 25	com/miui/tsmclient/model/fmsh/FmshCardClient$11:this$0	Lcom/miui/tsmclient/model/fmsh/FmshCardClient;
        //   88: invokestatic 75	com/miui/tsmclient/model/fmsh/FmshCardClient:access$400	(Lcom/miui/tsmclient/model/fmsh/FmshCardClient;)V
        //   91: iload 7
        //   93: sipush 1030
        //   96: if_icmpne +8 -> 104
        //   99: sipush 2002
        //   102: istore 7
        //   104: iconst_1
        //   105: anewarray 77	java/lang/Object
        //   108: astore 8
        //   110: aload 8
        //   112: iconst_0
        //   113: aload_0
        //   114: getfield 33	com/miui/tsmclient/model/fmsh/FmshCardClient$11:val$businessOrder	Lcn/com/fmsh/nfcos/client/service/xm/NfcosBusinessOrder;
        //   117: aastore
        //   118: new 79	com/miui/tsmclient/model/BaseResponse
        //   121: dup
        //   122: iload 7
        //   124: aload 8
        //   126: invokespecial 82	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
        //   129: astore_2
        //   130: goto +55 -> 185
        //   133: aload_1
        //   134: iconst_1
        //   135: aload_0
        //   136: getfield 29	com/miui/tsmclient/model/fmsh/FmshCardClient$11:val$cardAppType	I
        //   139: invokeinterface 86 3 0
        //   144: pop
        //   145: goto -113 -> 32
        //   148: astore_3
        //   149: ldc 88
        //   151: aload_3
        //   152: invokestatic 92	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
        //   155: new 79	com/miui/tsmclient/model/BaseResponse
        //   158: dup
        //   159: iconst_3
        //   160: ldc 94
        //   162: iconst_0
        //   163: anewarray 77	java/lang/Object
        //   166: invokespecial 97	com/miui/tsmclient/model/BaseResponse:<init>	(ILjava/lang/String;[Ljava/lang/Object;)V
        //   169: astore_2
        //   170: goto +15 -> 185
        //   173: astore 5
        //   175: aload_0
        //   176: getfield 25	com/miui/tsmclient/model/fmsh/FmshCardClient$11:this$0	Lcom/miui/tsmclient/model/fmsh/FmshCardClient;
        //   179: invokestatic 75	com/miui/tsmclient/model/fmsh/FmshCardClient:access$400	(Lcom/miui/tsmclient/model/fmsh/FmshCardClient;)V
        //   182: aload 5
        //   184: athrow
        //   185: aload_2
        //   186: areturn
        //
        // Exception table:
        //   from	to	target	type
        //   14	54	148	android/os/RemoteException
        //   84	145	148	android/os/RemoteException
        //   175	185	148	android/os/RemoteException
        //   54	80	173	finally
      }
    }))
      return localBaseResponse;
  }

  protected boolean login(CardAppManager paramCardAppManager, Context paramContext)
  {
    int i = 0;
    try
    {
      str1 = getUserName(paramContext);
      str2 = getPass(paramContext, str1);
      if (TextUtils.isEmpty(str2))
        return i;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        String str1;
        String str2;
        LogUtils.e("get fmsh user name failed.", localIOException);
        continue;
        LoginInfo localLoginInfo = new LoginInfo();
        if (this.mAccountManager.loadAccountInfo(paramContext) == null)
          continue;
        try
        {
          int j = paramCardAppManager.login(str1, str2, localLoginInfo);
          LogUtils.d("login api execute result is: " + j);
          if (localLoginInfo.loginResult == 1000)
          {
            LogUtils.d("User name：" + str1 + " has not been registed!, so registed an new account!");
            UserInfo localUserInfo = new UserInfo();
            localUserInfo.password = str2;
            localUserInfo.username = str1;
            if (paramCardAppManager.register(localUserInfo) == 0)
            {
              LogUtils.d("account " + str1 + " registed success! Then login...");
              j = paramCardAppManager.login(str1, str2, localLoginInfo);
            }
            LogUtils.d("Account:" + str1 + " login result:" + localLoginInfo.loginResult);
          }
          LogUtils.d("login result is: " + localLoginInfo.loginResult);
          if (j != 0)
            continue;
          int k = localLoginInfo.loginResult;
          if (k != 0)
            continue;
          i = 1;
        }
        catch (RemoteException localRemoteException)
        {
          LogUtils.e("failed to login sptc account", localRemoteException);
        }
      }
    }
  }

  BaseResponse queryIssueOrder(Context paramContext, int paramInt)
  {
    if (paramContext == null);
    for (BaseResponse localBaseResponse = new BaseResponse(1, "queryIssueOrder() called,but the param is invalid! context = null", new Object[0]); ; localBaseResponse = executeTask(new FmshAppTask(paramContext, paramInt)
    {
      protected BaseResponse doInBackgroud()
      {
        LogUtils.d("queryIssueOrder() called!");
        CardAppManager localCardAppManager = getService();
        if (localCardAppManager != null);
        while (true)
        {
          try
          {
            ArrayList localArrayList = new ArrayList();
            int i = this.val$cardAppType;
            int[] arrayOfInt = new int[1];
            arrayOfInt[0] = EnumOrderStatus.success.getId();
            int j = localCardAppManager.queryBusinessOrders(0, 10, i, arrayOfInt, EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId(), localArrayList);
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = localArrayList;
            localBaseResponse = new BaseResponse(j, arrayOfObject);
            return localBaseResponse;
          }
          catch (RemoteException localRemoteException)
          {
            LogUtils.e("exception occured when queryOrder() running", localRemoteException);
          }
          BaseResponse localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]);
        }
      }
    }))
      return localBaseResponse;
  }

  BaseResponse queryUnsolvedOrder(Context paramContext, int paramInt, Tag paramTag)
  {
    if (paramContext == null);
    for (BaseResponse localBaseResponse = new BaseResponse(1, "queryUnsolvedOrder() called,but the param is invalid! context is null!", new Object[0]); ; localBaseResponse = executeTask(new FmshAppTask(paramContext, paramContext, paramInt, new ArrayList(), paramTag)
    {
      protected BaseResponse doInBackgroud()
      {
        LogUtils.d("queryUnsolvedOrder() called!");
        CardAppManager localCardAppManager = getService();
        ArrayList localArrayList;
        int j;
        if (localCardAppManager != null)
          try
          {
            localArrayList = new ArrayList();
            if (FmshCardClient.this.login(localCardAppManager, this.val$context))
            {
              int i = this.val$cardAppType;
              int[] arrayOfInt = new int[4];
              arrayOfInt[0] = EnumOrderStatus.hasPaid.getId();
              arrayOfInt[1] = EnumOrderStatus.failure.getId();
              arrayOfInt[2] = EnumOrderStatus.unsettled.getId();
              arrayOfInt[3] = EnumOrderStatus.dubious.getId();
              j = localCardAppManager.queryBusinessOrders(0, 10, i, arrayOfInt, EnumBusinessOrderType.UNKNOW.getId(), this.val$businessOrders);
              if (this.val$businessOrders.isEmpty())
                break label240;
              Iterator localIterator = this.val$businessOrders.iterator();
              while (localIterator.hasNext())
              {
                NfcosBusinessOrder localNfcosBusinessOrder = (NfcosBusinessOrder)localIterator.next();
                if ((!FmshCardClient.this.isSameCardIOType(this.val$tag, localNfcosBusinessOrder)) || ((localNfcosBusinessOrder.businessOrderType != EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId()) && (localNfcosBusinessOrder.businessOrderType != EnumBusinessOrderType.ORDER_TYPE_RECHARGE.getId())))
                  continue;
                localArrayList.add(localNfcosBusinessOrder);
              }
            }
          }
          catch (RemoteException localRemoteException)
          {
            LogUtils.e("exception occured when queryUnsolvedOrder() running", localRemoteException);
          }
        label240: Object[] arrayOfObject;
        for (BaseResponse localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]); ; localBaseResponse = new BaseResponse(j, arrayOfObject))
        {
          return localBaseResponse;
          arrayOfObject = new Object[1];
          arrayOfObject[0] = localArrayList;
        }
      }
    }))
      return localBaseResponse;
  }

  BaseResponse recharge(Context paramContext, int paramInt1, ActionToken paramActionToken, byte[] paramArrayOfByte, int paramInt2, Tag paramTag)
  {
    boolean bool1;
    boolean bool2;
    label52: Object localObject1;
    if ((paramContext == null) || (paramActionToken == null))
    {
      StringBuilder localStringBuilder1 = new StringBuilder().append("recharge() called,but the param is invalid! context == null:");
      if (paramContext == null)
      {
        bool1 = true;
        StringBuilder localStringBuilder2 = localStringBuilder1.append(bool1).append(",order == null:");
        if (paramActionToken != null)
          break label85;
        bool2 = true;
        localObject1 = new BaseResponse(1, bool2, new Object[0]);
      }
    }
    while (true)
    {
      return localObject1;
      bool1 = false;
      break;
      label85: bool2 = false;
      break label52;
      9 local9 = new FmshAppTask(paramContext, paramTag, paramInt1, paramArrayOfByte, paramActionToken, paramInt2, paramContext)
      {
        protected BaseResponse doInBackgroud()
        {
          LogUtils.d("recharge() called!");
          CardAppManager localCardAppManager = getService();
          if (localCardAppManager != null);
          BaseResponse localBaseResponse;
          while (true)
          {
            int i;
            try
            {
              if (this.val$tag == null)
                continue;
              localCardAppManager.switchMode2NFC(this.val$tag);
              byte[] arrayOfByte = this.val$appNo;
              if (arrayOfByte != null)
                continue;
              CardAppInfo localCardAppInfo = new CardAppInfo();
              localCardAppManager.getInfo(1, this.val$cardAppType, localCardAppInfo);
              arrayOfByte = localCardAppInfo.appNo;
              i = localCardAppManager.applyRechargeEx(this.val$cardAppType, this.val$actionToken.mRechargeAmount, this.val$rechargeMode, arrayOfByte, Coder.hexStringToBytes(this.val$actionToken.mToken));
              if (((i != 1035) && (i != 1031)) || (!FmshCardClient.this.handleCardNotActivate(this.val$context, localCardAppManager, null, this.val$cardAppType)))
                break label255;
              i = localCardAppManager.applyRechargeEx(this.val$cardAppType, this.val$actionToken.mRechargeAmount, this.val$rechargeMode, arrayOfByte, Coder.hexStringToBytes(this.val$actionToken.mToken));
              break label255;
              localBaseResponse = new BaseResponse(0, new Object[0]);
              break;
              localCardAppManager.switchMode2OMA(1, this.val$cardAppType);
              continue;
            }
            catch (RemoteException localRemoteException)
            {
              LogUtils.e("exception occured when recharge() running", localRemoteException);
            }
            localBaseResponse = new BaseResponse(3, "service unvailible", new Object[0]);
            break;
            label255: 
            do
            {
              localBaseResponse = new BaseResponse(i, new Object[0]);
              break label271;
              if (i == 1104)
                break;
            }
            while (i != 0);
          }
          label271: return localBaseResponse;
        }
      };
      lock();
      try
      {
        BaseResponse localBaseResponse = executeTask(local9);
        localObject1 = localBaseResponse;
        unLock();
      }
      finally
      {
        unLock();
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.fmsh.FmshCardClient
 * JD-Core Version:    0.6.0
 */