package com.miui.tsmclient;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.bankcard.BankCardManager;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclientsdk.IMiTsmResponse;
import com.miui.tsmclientsdk.IMiTsmService;
import com.miui.tsmclientsdk.IMiTsmService.Stub;
import com.miui.tsmclientsdk.MiTsmCallback;
import com.miui.tsmclientsdk.MiTsmConstants.OperationType;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.exception.InvalidTLVException;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import com.tsmclient.smartcard.terminal.APDUConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;

public class MiTsmService extends Service
{
  private IMiTsmService mService;

  public IBinder onBind(Intent paramIntent)
  {
    if (this.mService == null)
      this.mService = new ServiceImpl(this);
    return this.mService.asBinder();
  }

  private class ServiceImpl extends IMiTsmService.Stub
  {
    private Context mContext;

    public ServiceImpl(Context arg2)
    {
      Object localObject;
      this.mContext = localObject;
    }

    private void updateBankCards(Context paramContext, BankCardManager paramBankCardManager)
    {
      updateBankCards(paramContext, paramBankCardManager, null);
    }

    private void updateBankCards(Context paramContext, BankCardManager paramBankCardManager, MiTsmCallback paramMiTsmCallback)
    {
      paramBankCardManager.updateCardInfo(paramContext, new BankCardInfo(), paramMiTsmCallback);
    }

    @Deprecated
    public void createSSD(IMiTsmResponse paramIMiTsmResponse, int paramInt)
      throws RemoteException
    {
    }

    public void deleteAllBankCard(IMiTsmResponse paramIMiTsmResponse)
      throws RemoteException
    {
      BankCardManager localBankCardManager = new BankCardManager(this.mContext);
      try
      {
        int i = localBankCardManager.deleteAllBankCard(this.mContext);
        if (i == 0)
        {
          Bundle localBundle = new Bundle();
          localBundle.putInt("key_result_code", i);
          updateBankCards(this.mContext, localBankCardManager);
          paramIMiTsmResponse.onResult(localBundle);
        }
        while (true)
        {
          return;
          paramIMiTsmResponse.onError(i, "");
        }
      }
      finally
      {
        localBankCardManager.release();
      }
      throw localObject;
    }

    @Deprecated
    public void deleteBankCard(IMiTsmResponse paramIMiTsmResponse, String paramString)
      throws RemoteException
    {
    }

    public void getActiveCards(IMiTsmResponse paramIMiTsmResponse, String paramString)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = CardInfo.getActivedCards().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (paramString != null)
          switch (MiTsmService.1.$SwitchMap$com$miui$tsmclientsdk$MiTsmConstants$CardType[com.miui.tsmclientsdk.MiTsmConstants.CardType.valueOf(paramString).ordinal()])
          {
          default:
            break;
          case 1:
            if (!TextUtils.equals(str, "BANKCARD"))
              continue;
            localArrayList.add(str);
            break;
          case 2:
            if (TextUtils.equals(str, "BANKCARD"))
              continue;
            localArrayList.add(str);
            break;
          }
        localArrayList.add(str);
      }
      localBundle.putStringArrayList("key_active_card", localArrayList);
      paramIMiTsmResponse.onResult(localBundle);
    }

    // ERROR //
    public void getCPLC(IMiTsmResponse paramIMiTsmResponse)
      throws RemoteException
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_2
      //   2: aconst_null
      //   3: astore_3
      //   4: new 63	android/os/Bundle
      //   7: dup
      //   8: invokespecial 64	android/os/Bundle:<init>	()V
      //   11: astore 4
      //   13: aload 4
      //   15: ldc 154
      //   17: aload_0
      //   18: getfield 28	com/miui/tsmclient/MiTsmService$ServiceImpl:mContext	Landroid/content/Context;
      //   21: invokestatic 159	com/miui/tsmclient/util/SysUtils:getCPLC	(Landroid/content/Context;)Ljava/lang/String;
      //   24: invokevirtual 163	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
      //   27: iconst_0
      //   28: istore 6
      //   30: aload 4
      //   32: astore_3
      //   33: iload 6
      //   35: ifne +30 -> 65
      //   38: aload_1
      //   39: aload_3
      //   40: invokeinterface 76 2 0
      //   45: return
      //   46: astore 5
      //   48: iconst_1
      //   49: istore 6
      //   51: aload 5
      //   53: invokevirtual 167	java/io/IOException:getMessage	()Ljava/lang/String;
      //   56: astore_2
      //   57: ldc 169
      //   59: invokestatic 175	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;)V
      //   62: goto -29 -> 33
      //   65: aload_1
      //   66: iload 6
      //   68: aload_2
      //   69: invokeinterface 85 3 0
      //   74: goto -29 -> 45
      //   77: astore 5
      //   79: aload 4
      //   81: astore_3
      //   82: goto -34 -> 48
      //
      // Exception table:
      //   from	to	target	type
      //   4	13	46	java/io/IOException
      //   13	27	77	java/io/IOException
    }

    // ERROR //
    public void getCardInfo(IMiTsmResponse paramIMiTsmResponse, List<String> paramList)
      throws RemoteException
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: new 181	com/miui/tsmclient/model/CardManager
      //   5: dup
      //   6: aload_0
      //   7: getfield 28	com/miui/tsmclient/MiTsmService$ServiceImpl:mContext	Landroid/content/Context;
      //   10: invokespecial 182	com/miui/tsmclient/model/CardManager:<init>	(Landroid/content/Context;)V
      //   13: astore 4
      //   15: new 63	android/os/Bundle
      //   18: dup
      //   19: invokespecial 64	android/os/Bundle:<init>	()V
      //   22: astore 5
      //   24: aload_2
      //   25: ifnull +153 -> 178
      //   28: new 184	org/json/JSONArray
      //   31: dup
      //   32: invokespecial 185	org/json/JSONArray:<init>	()V
      //   35: astore 6
      //   37: new 187	java/util/concurrent/CountDownLatch
      //   40: dup
      //   41: aload_2
      //   42: invokeinterface 190 1 0
      //   47: invokespecial 193	java/util/concurrent/CountDownLatch:<init>	(I)V
      //   50: astore 7
      //   52: aload_2
      //   53: invokeinterface 103 1 0
      //   58: astore 10
      //   60: aload 10
      //   62: invokeinterface 109 1 0
      //   67: ifeq +73 -> 140
      //   70: aload 10
      //   72: invokeinterface 113 1 0
      //   77: checkcast 115	java/lang/String
      //   80: astore 11
      //   82: aload 4
      //   84: aload_0
      //   85: getfield 28	com/miui/tsmclient/MiTsmService$ServiceImpl:mContext	Landroid/content/Context;
      //   88: aload 11
      //   90: aconst_null
      //   91: invokestatic 199	com/miui/tsmclient/entity/CardInfoFactory:makeCardInfo	(Ljava/lang/String;Lorg/json/JSONObject;)Lcom/miui/tsmclient/entity/CardInfo;
      //   94: aconst_null
      //   95: new 6	com/miui/tsmclient/MiTsmService$ServiceImpl$1
      //   98: dup
      //   99: aload_0
      //   100: aload 6
      //   102: aload 7
      //   104: invokespecial 202	com/miui/tsmclient/MiTsmService$ServiceImpl$1:<init>	(Lcom/miui/tsmclient/MiTsmService$ServiceImpl;Lorg/json/JSONArray;Ljava/util/concurrent/CountDownLatch;)V
      //   107: invokevirtual 206	com/miui/tsmclient/model/CardManager:queryCardInfo	(Landroid/content/Context;Lcom/miui/tsmclient/entity/CardInfo;Landroid/os/Bundle;Lcom/miui/tsmclientsdk/MiTsmCallback;)V
      //   110: goto -50 -> 60
      //   113: astore 9
      //   115: aload 4
      //   117: astore_3
      //   118: invokestatic 212	java/lang/Thread:currentThread	()Ljava/lang/Thread;
      //   121: invokevirtual 215	java/lang/Thread:interrupt	()V
      //   124: ldc 217
      //   126: aload 9
      //   128: invokestatic 220	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
      //   131: aload_3
      //   132: ifnull +7 -> 139
      //   135: aload_3
      //   136: invokevirtual 221	com/miui/tsmclient/model/CardManager:release	()V
      //   139: return
      //   140: aload 7
      //   142: invokevirtual 224	java/util/concurrent/CountDownLatch:await	()V
      //   145: aload 5
      //   147: ldc 226
      //   149: aload 6
      //   151: invokevirtual 229	org/json/JSONArray:toString	()Ljava/lang/String;
      //   154: invokevirtual 163	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
      //   157: aload_1
      //   158: aload 5
      //   160: invokeinterface 76 2 0
      //   165: aload 4
      //   167: ifnull +49 -> 216
      //   170: aload 4
      //   172: invokevirtual 221	com/miui/tsmclient/model/CardManager:release	()V
      //   175: goto -36 -> 139
      //   178: aload_1
      //   179: iconst_5
      //   180: ldc 231
      //   182: invokeinterface 85 3 0
      //   187: goto -22 -> 165
      //   190: astore 8
      //   192: aload 4
      //   194: astore_3
      //   195: aload_3
      //   196: ifnull +7 -> 203
      //   199: aload_3
      //   200: invokevirtual 221	com/miui/tsmclient/model/CardManager:release	()V
      //   203: aload 8
      //   205: athrow
      //   206: astore 8
      //   208: goto -13 -> 195
      //   211: astore 9
      //   213: goto -95 -> 118
      //   216: goto -77 -> 139
      //
      // Exception table:
      //   from	to	target	type
      //   15	110	113	java/lang/InterruptedException
      //   140	165	113	java/lang/InterruptedException
      //   178	187	113	java/lang/InterruptedException
      //   15	110	190	finally
      //   140	165	190	finally
      //   178	187	190	finally
      //   2	15	206	finally
      //   118	131	206	finally
      //   2	15	211	java/lang/InterruptedException
    }

    public void getCardsQuantity(IMiTsmResponse paramIMiTsmResponse, String paramString)
      throws RemoteException
    {
      int i = -1;
      String str = null;
      Bundle localBundle = new Bundle();
      try
      {
        int j = MiTsmService.1.$SwitchMap$com$miui$tsmclientsdk$MiTsmConstants$CardType[com.miui.tsmclientsdk.MiTsmConstants.CardType.valueOf(paramString).ordinal()];
        switch (j)
        {
        default:
        case 1:
        case 2:
        }
        while (i == 0)
        {
          paramIMiTsmResponse.onResult(localBundle);
          return;
          localBundle.putInt("key_card_quantity", SysUtils.getBankCardCounts(this.mContext));
          i = 0;
          continue;
          localBundle.putInt("key_card_quantity", SysUtils.getTransCardCounts(this.mContext));
          i = 0;
        }
      }
      catch (InvalidTLVException localInvalidTLVException)
      {
        while (true)
        {
          i = 103;
          str = localInvalidTLVException.getMessage();
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          i = 1;
          str = localIOException.getMessage();
        }
      }
      catch (TagNotFoundException localTagNotFoundException)
      {
        while (true)
        {
          i = 102;
          str = localTagNotFoundException.getMessage();
          continue;
          paramIMiTsmResponse.onError(i, str);
        }
      }
    }

    @Deprecated
    public void getDefaultCard(IMiTsmResponse paramIMiTsmResponse, String paramString)
      throws RemoteException
    {
    }

    public void getSeBankCards(IMiTsmResponse paramIMiTsmResponse)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      HashMap localHashMap = new HashMap();
      Map localMap = SysUtils.getCardActivationState(this.mContext, Coder.bytesToHexString(APDUConstants.PBOC_CARD_AID_PREFFIX));
      if (localMap != null)
      {
        Iterator localIterator = localMap.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          localHashMap.put(localEntry.getKey(), ((ByteArray)localEntry.getValue()).toString());
        }
      }
      localBundle.putSerializable("se_bank_card", localHashMap);
      paramIMiTsmResponse.onResult(localBundle);
    }

    public void getTransCardState(IMiTsmResponse paramIMiTsmResponse)
      throws RemoteException
    {
      String str = null;
      Object localObject = null;
      try
      {
        Bundle localBundle = SysUtils.getTransCardState(this.mContext, false);
        localObject = localBundle;
        i = 0;
        if (i == 0)
        {
          paramIMiTsmResponse.onResult(localObject);
          return;
        }
      }
      catch (InvalidTLVException localInvalidTLVException)
      {
        while (true)
        {
          i = 1;
          str = localInvalidTLVException.getMessage();
        }
      }
      catch (TagNotFoundException localTagNotFoundException)
      {
        while (true)
        {
          i = 1;
          str = localTagNotFoundException.getMessage();
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          int i = 1;
          str = localIOException.getMessage();
          continue;
          paramIMiTsmResponse.onError(i, str);
        }
      }
    }

    public void isBankCardAvailable(IMiTsmResponse paramIMiTsmResponse)
      throws RemoteException
    {
      BankCardManager localBankCardManager = new BankCardManager(this.mContext);
      CountDownLatch localCountDownLatch = new CountDownLatch(1);
      try
      {
        localBankCardManager.isBankCardServiceAvailable(this.mContext, new MiTsmCallback(paramIMiTsmResponse, localCountDownLatch)
        {
          public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
          {
            try
            {
              this.val$response.onError(paramInt, "");
              this.val$countDownLatch.countDown();
              return;
            }
            catch (RemoteException localRemoteException)
            {
              while (true)
                LogUtils.e("Notify result error in isBankCardAvailable", localRemoteException);
            }
          }

          public void onSuccess(int paramInt, Object[] paramArrayOfObject)
          {
            Bundle localBundle = new Bundle();
            localBundle.putInt("key_result_code", paramInt);
            try
            {
              this.val$response.onResult(localBundle);
              this.val$countDownLatch.countDown();
              return;
            }
            catch (RemoteException localRemoteException)
            {
              while (true)
                LogUtils.e("Notify result error in isBankCardAvailable", localRemoteException);
            }
          }
        });
        localCountDownLatch.await();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
        {
          Thread.currentThread().interrupt();
          LogUtils.e("isBankCardAvailable is interrupted", localInterruptedException);
          localBankCardManager.release();
        }
      }
      finally
      {
        localBankCardManager.release();
      }
      throw localObject;
    }

    public void manageBankCard(IMiTsmResponse paramIMiTsmResponse, String paramString, int paramInt)
      throws RemoteException
    {
      BankCardManager localBankCardManager = new BankCardManager(this.mContext);
      BankCardInfo localBankCardInfo = new BankCardInfo();
      localBankCardInfo.mAid = paramString;
      try
      {
        if (paramInt == MiTsmConstants.OperationType.DELETE.ordinal())
        {
          int i = localBankCardManager.deleteCard(this.mContext, localBankCardInfo, null);
          if (i == 0)
          {
            updateBankCards(this.mContext, localBankCardManager);
            Bundle localBundle = new Bundle();
            localBundle.putInt("key_result_code", i);
            paramIMiTsmResponse.onResult(localBundle);
          }
          while (true)
          {
            return;
            paramIMiTsmResponse.onError(i, "delete from external error, error code : " + i);
          }
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
        {
          Thread.currentThread().interrupt();
          LogUtils.e("manageBankCard is interrupted", localInterruptedException);
          localBankCardManager.release();
          continue;
          if (paramInt != MiTsmConstants.OperationType.LOCK.ordinal())
            continue;
          CountDownLatch localCountDownLatch = new CountDownLatch(1);
          localBankCardManager.lock(this.mContext, localBankCardInfo, null, new MiTsmCallback(localBankCardManager, paramIMiTsmResponse, localCountDownLatch)
          {
            public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
            {
              try
              {
                this.val$response.onError(paramInt, "");
                this.val$countDownLatch.countDown();
                return;
              }
              catch (RemoteException localRemoteException)
              {
                while (true)
                  LogUtils.e("lock card from external throw remoteException", localRemoteException);
              }
            }

            public void onSuccess(int paramInt, Object[] paramArrayOfObject)
            {
              MiTsmService.ServiceImpl.this.updateBankCards(MiTsmService.ServiceImpl.this.mContext, this.val$cardManager);
              Bundle localBundle = new Bundle();
              localBundle.putInt("key_result_code", paramInt);
              try
              {
                this.val$response.onResult(localBundle);
                this.val$countDownLatch.countDown();
                return;
              }
              catch (RemoteException localRemoteException)
              {
                while (true)
                  LogUtils.e("lock card from external throw remoteException", localRemoteException);
              }
            }
          });
          localCountDownLatch.await();
        }
      }
      finally
      {
        localBankCardManager.release();
      }
      throw localObject;
    }

    @Deprecated
    public void setDefaultCard(IMiTsmResponse paramIMiTsmResponse, String paramString, Bundle paramBundle)
      throws RemoteException
    {
    }

    public void syncBankCardStatus(IMiTsmResponse paramIMiTsmResponse)
    {
      BankCardManager localBankCardManager = new BankCardManager(this.mContext);
      updateBankCards(this.mContext, localBankCardManager, new MiTsmCallback(paramIMiTsmResponse)
      {
        public void onFail(int paramInt, String paramString, Object[] paramArrayOfObject)
        {
          LogUtils.i("update card info faled, errorCode = " + paramInt);
          try
          {
            this.val$miTsmResponse.onError(paramInt, paramString);
            return;
          }
          catch (RemoteException localRemoteException)
          {
            while (true)
              LogUtils.e("Notify result error when syncBankCardStatus", localRemoteException);
          }
        }

        public void onSuccess(int paramInt, Object[] paramArrayOfObject)
        {
          Bundle localBundle = new Bundle();
          localBundle.putInt("key_result_code", 0);
          try
          {
            this.val$miTsmResponse.onResult(localBundle);
            return;
          }
          catch (RemoteException localRemoteException)
          {
            while (true)
              LogUtils.e("Notify result error when syncBankCardStatus", localRemoteException);
          }
        }
      });
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.MiTsmService
 * JD-Core Version:    0.6.0
 */