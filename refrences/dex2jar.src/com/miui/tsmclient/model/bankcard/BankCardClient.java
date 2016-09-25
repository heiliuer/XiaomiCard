package com.miui.tsmclient.model.bankcard;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.model.mitsm.MiTsmErrorCode;
import com.miui.tsmclient.model.mitsm.TSMSessionManager;
import com.miui.tsmclient.model.mitsm.TSMSessionManager.BusinessType;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.SeiTsmAuthManager;
import com.miui.tsmclient.seitsm.TsmRpcModels.CommonResponse;
import com.miui.tsmclient.seitsm.TsmRpcModels.QueryBankCardInfoResponse;
import com.miui.tsmclient.seitsm.TsmRpcModels.QueryPanResponse;
import com.miui.tsmclient.seitsm.TsmRpcModels.SeAPDUResponseItem;
import com.miui.tsmclient.seitsm.TsmRpcModels.SeAPDUResponseItem.Builder;
import com.miui.tsmclient.seitsm.TsmRpcModels.SeOperationType;
import com.miui.tsmclient.seitsm.TsmRpcModels.TsmAPDUCommand;
import com.miui.tsmclient.seitsm.TsmRpcModels.TsmCAPDU;
import com.miui.tsmclient.seitsm.TsmRpcModels.TsmSessionInfo;
import com.miui.tsmclient.seitsm.TsmRpcModels.VirtualCardInfoResponse;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import com.tsmclient.smartcard.terminal.AbstractTerminal;
import com.tsmclient.smartcard.terminal.SPISmartMxTerminal;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankCardClient extends MiTSMCardClient
{
  private BaseResponse processUPCmd(Context paramContext, CardInfo paramCardInfo, AbstractTerminal paramAbstractTerminal, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, TsmRpcModels.TsmAPDUCommand paramTsmAPDUCommand)
    throws IOException, SeiTSMApiException
  {
    List localList = paramTsmAPDUCommand.getApdusList();
    if ((localList == null) || (localList.isEmpty()))
      throw new SeiTSMApiException(paramTsmAPDUCommand.getResult(), "processUPCmd ： capdulist is null,errorCode:" + paramTsmAPDUCommand.getResult());
    boolean bool = true;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    try
    {
      Iterator localIterator = localList.iterator();
      TsmRpcModels.TsmCAPDU localTsmCAPDU;
      ScResponse localScResponse;
      label210: TsmRpcModels.CommonResponse localCommonResponse;
      BaseResponse localBaseResponse;
      if (localIterator.hasNext())
      {
        localTsmCAPDU = (TsmRpcModels.TsmCAPDU)localIterator.next();
        if (!paramAbstractTerminal.isNfcChannelOpen())
          paramAbstractTerminal.connect();
        localScResponse = paramAbstractTerminal.transmit(localTsmCAPDU.getApdu().toByteArray());
        i++;
        byte[] arrayOfByte1 = localScResponse.getData().toBytes();
        byte[] arrayOfByte2 = localScResponse.getStatus().toBytes();
        localArrayList.add(TsmRpcModels.SeAPDUResponseItem.newBuilder().setIndex(i).setResponseData(ByteString.copyFrom(arrayOfByte1)).setResponseSw(ByteString.copyFrom(arrayOfByte2)).build());
        if (TextUtils.isEmpty(localTsmCAPDU.getExpectSwRegex()))
        {
          LogUtils.d("processUPCmd ； no expected sw.");
          bool = false;
        }
      }
      else
      {
        localCommonResponse = this.mSeiTsmAuthManager.persoFinishNotify(paramContext, paramCardInfo, bool, localArrayList, paramTsmSessionInfo);
        if (bool)
          break label290;
        localBaseResponse = new BaseResponse(8, new Object[0]);
      }
      while (true)
      {
        return localBaseResponse;
        if (Pattern.compile(localTsmCAPDU.getExpectSwRegex()).matcher(Coder.bytesToHexString(localScResponse.getStatus().toBytes())).matches())
          break;
        bool = false;
        break label210;
        label290: if (localCommonResponse.getResult() == 0)
        {
          localBaseResponse = new BaseResponse(0, new Object[0]);
          paramAbstractTerminal.close();
          continue;
        }
        LogUtils.e("persoFinishNotify error : " + localCommonResponse.getResult());
        localBaseResponse = new BaseResponse(MiTsmErrorCode.format(localCommonResponse.getResult()), new Object[0]);
        paramAbstractTerminal.close();
      }
    }
    finally
    {
      paramAbstractTerminal.close();
    }
    throw localObject;
  }

  public BaseResponse deleteCards(Context paramContext)
  {
    LogUtils.v("-------------- start deleteCards ---------------");
    try
    {
      TsmRpcModels.CommonResponse localCommonResponse = this.mSeiTsmAuthManager.deleteAllBankCards(paramContext);
      if (localCommonResponse != null);
      for (i = localCommonResponse.getResult(); ; i = 9999)
      {
        LogUtils.d("deletecards result: " + i);
        return new BaseResponse(MiTsmErrorCode.format(i), new Object[0]);
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        int i = 2;
        LogUtils.e("deleteAllBankCard failed with an io exception.", localIOException);
      }
    }
  }

  public BaseResponse enrollUPCard(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    LogUtils.v("-------------- start enrollUPCard ---------------");
    TSMSessionManager.BusinessType localBusinessType = TSMSessionManager.BusinessType.INSTALL;
    try
    {
      TsmRpcModels.TsmSessionInfo localTsmSessionInfo = getSession(paramContext, paramCardInfo, localBusinessType);
      TsmRpcModels.VirtualCardInfoResponse localVirtualCardInfoResponse = this.mSeiTsmAuthManager.enrollUPCard(paramContext, localTsmSessionInfo, paramCardInfo, paramBundle);
      if (localVirtualCardInfoResponse == null)
      {
        i = -1;
        LogUtils.i("enrollUPCard failed,errorCode: " + i);
      }
      Object[] arrayOfObject;
      for (BaseResponse localBaseResponse = new BaseResponse(i, new Object[0]); ; localBaseResponse = new BaseResponse(0, arrayOfObject))
      {
        return localBaseResponse;
        j = localVirtualCardInfoResponse.getResult();
        if (j != 0)
          break;
        LogUtils.v("-------------- enrollUPCard end ---------------");
        arrayOfObject = new Object[1];
        arrayOfObject[0] = localVirtualCardInfoResponse;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        int j;
        i = 2;
        LogUtils.e("enrollUPCard failed with an io exception.", localIOException);
        continue;
        int k = MiTsmErrorCode.format(j);
        i = k;
      }
    }
    catch (SeiTSMApiException localSeiTSMApiException)
    {
      while (true)
      {
        int i = MiTsmErrorCode.format(localSeiTSMApiException.getErrorCode());
        LogUtils.e("enrollUPCard failed with an tsmapi exception.", localSeiTSMApiException);
      }
    }
  }

  public BaseResponse isBankCardServiceAvailable(Context paramContext)
  {
    try
    {
      TsmRpcModels.CommonResponse localCommonResponse = this.mSeiTsmAuthManager.isBankCardServiceAvailable(paramContext);
      if (localCommonResponse == null)
      {
        i = -1;
        LogUtils.d("isBankCardServiceAvailable result: " + i);
      }
      Object[] arrayOfObject;
      for (BaseResponse localBaseResponse = new BaseResponse(i, new Object[0]); ; localBaseResponse = new BaseResponse(0, arrayOfObject))
      {
        return localBaseResponse;
        j = localCommonResponse.getResult();
        if (j != 0)
          break;
        arrayOfObject = new Object[1];
        arrayOfObject[0] = localCommonResponse;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        int j;
        int i = 2;
        LogUtils.e("isBankCardServiceAvailable failed with an io exception.", localIOException);
        continue;
        int k = MiTsmErrorCode.format(j);
        i = k;
      }
    }
  }

  public BaseResponse lock(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    LogUtils.v("-------------- start lock ---------------");
    TSMSessionManager.BusinessType localBusinessType = TSMSessionManager.BusinessType.LOCK;
    try
    {
      TsmRpcModels.TsmSessionInfo localTsmSessionInfo = getSession(paramContext, paramCardInfo, localBusinessType);
      TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand = this.mSeiTsmAuthManager.startSeOperation(paramContext, localTsmSessionInfo, TsmRpcModels.SeOperationType.LOCK, paramCardInfo, paramBundle);
      if (localTsmAPDUCommand == null)
      {
        LogUtils.d("cannnot get apduCommand,startSeOperation failed.");
        localBaseResponse = new BaseResponse(8, new Object[0]);
      }
      while (true)
      {
        return localBaseResponse;
        int j = localTsmAPDUCommand.getResult();
        if (j != 0)
        {
          LogUtils.v("-------------- lock end---------------");
          localBaseResponse = new BaseResponse(MiTsmErrorCode.format(j), new Object[0]);
          TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
          continue;
        }
        localBaseResponse = new BaseResponse(0, new Object[0]);
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        i = 2;
        LogUtils.e("lock failed with an io exception.", localIOException);
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
        BaseResponse localBaseResponse = new BaseResponse(MiTsmErrorCode.format(i), new Object[0]);
      }
    }
    catch (SeiTSMApiException localSeiTSMApiException)
    {
      while (true)
      {
        int i = MiTsmErrorCode.format(localSeiTSMApiException.getErrorCode());
        LogUtils.e("lock failed with an tsmapi exception.", localSeiTSMApiException);
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
      }
    }
    finally
    {
      TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
    }
    throw localObject;
  }

  // ERROR //
  public BaseResponse preparePayApplet(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    // Byte code:
    //   0: ldc_w 300
    //   3: invokestatic 212	com/miui/tsmclient/util/LogUtils:v	(Ljava/lang/String;)V
    //   6: getstatic 233	com/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType:INSTALL	Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;
    //   9: astore 4
    //   11: aload_0
    //   12: aload_1
    //   13: aload_2
    //   14: aload 4
    //   16: invokevirtual 237	com/miui/tsmclient/model/bankcard/BankCardClient:getSession	(Landroid/content/Context;Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   19: astore 9
    //   21: aconst_null
    //   22: astore 7
    //   24: getstatic 304	com/miui/tsmclient/model/bankcard/BankCardClient:mNeedSync	Ljava/util/concurrent/atomic/AtomicBoolean;
    //   27: invokevirtual 309	java/util/concurrent/atomic/AtomicBoolean:get	()Z
    //   30: ifeq +14 -> 44
    //   33: aload_0
    //   34: aload_1
    //   35: aload_2
    //   36: aload 9
    //   38: aload_3
    //   39: invokevirtual 313	com/miui/tsmclient/model/bankcard/BankCardClient:syncEse	(Landroid/content/Context;Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;Landroid/os/Bundle;)Lcom/miui/tsmclient/model/BaseResponse;
    //   42: astore 7
    //   44: getstatic 304	com/miui/tsmclient/model/bankcard/BankCardClient:mNeedSync	Ljava/util/concurrent/atomic/AtomicBoolean;
    //   47: invokevirtual 309	java/util/concurrent/atomic/AtomicBoolean:get	()Z
    //   50: ifne +229 -> 279
    //   53: aload_0
    //   54: getfield 154	com/miui/tsmclient/model/bankcard/BankCardClient:mSeiTsmAuthManager	Lcom/miui/tsmclient/seitsm/SeiTsmAuthManager;
    //   57: aload_1
    //   58: aload 9
    //   60: aload_2
    //   61: aload_3
    //   62: invokevirtual 316	com/miui/tsmclient/seitsm/SeiTsmAuthManager:preparePayApplet	(Landroid/content/Context;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;Lcom/miui/tsmclient/entity/CardInfo;Landroid/os/Bundle;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand;
    //   65: astore 10
    //   67: aload 10
    //   69: ifnonnull +34 -> 103
    //   72: getstatic 304	com/miui/tsmclient/model/bankcard/BankCardClient:mNeedSync	Ljava/util/concurrent/atomic/AtomicBoolean;
    //   75: iconst_1
    //   76: invokevirtual 320	java/util/concurrent/atomic/AtomicBoolean:set	(Z)V
    //   79: ldc_w 322
    //   82: invokestatic 150	com/miui/tsmclient/util/LogUtils:d	(Ljava/lang/String;)V
    //   85: new 162	com/miui/tsmclient/model/BaseResponse
    //   88: dup
    //   89: bipush 8
    //   91: iconst_0
    //   92: anewarray 164	java/lang/Object
    //   95: invokespecial 167	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
    //   98: astore 7
    //   100: goto +179 -> 279
    //   103: aload 10
    //   105: invokevirtual 30	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getResult	()I
    //   108: istore 11
    //   110: iload 11
    //   112: ifne +151 -> 263
    //   115: aload 10
    //   117: invokevirtual 20	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   120: ifnull +16 -> 136
    //   123: aload 10
    //   125: invokevirtual 20	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   128: invokeinterface 26 1 0
    //   133: ifeq +57 -> 190
    //   136: ldc_w 324
    //   139: invokestatic 212	com/miui/tsmclient/util/LogUtils:v	(Ljava/lang/String;)V
    //   142: new 162	com/miui/tsmclient/model/BaseResponse
    //   145: dup
    //   146: iconst_0
    //   147: iconst_0
    //   148: anewarray 164	java/lang/Object
    //   151: invokespecial 167	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
    //   154: astore 7
    //   156: goto +123 -> 279
    //   159: astore 8
    //   161: iconst_2
    //   162: istore 6
    //   164: ldc_w 326
    //   167: aload 8
    //   169: invokestatic 223	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   172: new 162	com/miui/tsmclient/model/BaseResponse
    //   175: dup
    //   176: iload 6
    //   178: iconst_0
    //   179: anewarray 164	java/lang/Object
    //   182: invokespecial 167	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
    //   185: astore 7
    //   187: goto +92 -> 279
    //   190: new 328	com/tsmclient/smartcard/terminal/SPISmartMxTerminal
    //   193: dup
    //   194: aload_1
    //   195: invokespecial 331	com/tsmclient/smartcard/terminal/SPISmartMxTerminal:<init>	(Landroid/content/Context;)V
    //   198: astore 12
    //   200: aload 12
    //   202: invokevirtual 75	com/tsmclient/smartcard/terminal/AbstractTerminal:connect	()V
    //   205: aload_0
    //   206: aload_1
    //   207: aload 12
    //   209: aload 9
    //   211: aload 10
    //   213: invokevirtual 335	com/miui/tsmclient/model/bankcard/BankCardClient:executeCapdu	(Landroid/content/Context;Lcom/tsmclient/smartcard/terminal/AbstractTerminal;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand;)Lcom/miui/tsmclient/model/BaseResponse;
    //   216: astore 14
    //   218: aload 14
    //   220: astore 7
    //   222: aload 12
    //   224: invokevirtual 170	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   227: goto +52 -> 279
    //   230: astore 5
    //   232: aload 5
    //   234: invokevirtual 255	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException:getErrorCode	()I
    //   237: invokestatic 205	com/miui/tsmclient/model/mitsm/MiTsmErrorCode:format	(I)I
    //   240: istore 6
    //   242: ldc_w 337
    //   245: aload 5
    //   247: invokestatic 223	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   250: goto -78 -> 172
    //   253: astore 13
    //   255: aload 12
    //   257: invokevirtual 170	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   260: aload 13
    //   262: athrow
    //   263: iload 11
    //   265: invokestatic 205	com/miui/tsmclient/model/mitsm/MiTsmErrorCode:format	(I)I
    //   268: istore 6
    //   270: ldc_w 337
    //   273: invokestatic 199	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;)V
    //   276: goto -104 -> 172
    //   279: aload 7
    //   281: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   11	156	159	java/io/IOException
    //   190	200	159	java/io/IOException
    //   222	227	159	java/io/IOException
    //   255	276	159	java/io/IOException
    //   11	156	230	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   190	200	230	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   222	227	230	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   255	276	230	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   200	218	253	finally
  }

  public BaseResponse pullPersoData(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    LogUtils.v("-------------- start pullPersoData ---------------");
    TSMSessionManager.BusinessType localBusinessType = TSMSessionManager.BusinessType.INSTALL;
    BaseResponse localBaseResponse;
    try
    {
      localTsmSessionInfo = getSession(paramContext, paramCardInfo, localBusinessType);
      localBaseResponse = null;
      if (mNeedSync.get())
        localBaseResponse = syncEse(paramContext, paramCardInfo, localTsmSessionInfo, paramBundle);
      if (!mNeedSync.get())
      {
        localTsmAPDUCommand = this.mSeiTsmAuthManager.pullPersoData(paramContext, localTsmSessionInfo, paramCardInfo, paramBundle);
        if (localTsmAPDUCommand == null)
        {
          LogUtils.d("cannnot get apduCommand,pullPersoData failed.");
          localBaseResponse = new BaseResponse(8, new Object[0]);
        }
        else
        {
          i = localTsmAPDUCommand.getResult();
          if (i == 0)
            if ((localTsmAPDUCommand.getApdusList() == null) || (localTsmAPDUCommand.getApdusList().isEmpty()))
            {
              LogUtils.v("-------------- pullPersoData end ---------------");
              localBaseResponse = new BaseResponse(0, new Object[0]);
            }
        }
      }
    }
    catch (IOException localIOException)
    {
      TsmRpcModels.TsmSessionInfo localTsmSessionInfo;
      TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand;
      i = 2;
      LogUtils.e("pullPersoData failed with an io exception.", localIOException);
      while (true)
      {
        localBaseResponse = new BaseResponse(i, new Object[0]);
        break;
        localBaseResponse = processUPCmd(paramContext, paramCardInfo, new SPISmartMxTerminal(paramContext), localTsmSessionInfo, localTsmAPDUCommand);
        break;
        LogUtils.e("pullPersoData failed with an tsmapi exception.");
      }
    }
    catch (SeiTSMApiException localSeiTSMApiException)
    {
      while (true)
      {
        int i = MiTsmErrorCode.format(localSeiTSMApiException.getErrorCode());
        LogUtils.e("pullPersoData failed with an tsmapi exception.", localSeiTSMApiException);
      }
    }
    return localBaseResponse;
  }

  public BaseResponse queryBankCardInfo(Context paramContext, CardInfo paramCardInfo, String paramString)
  {
    try
    {
      TsmRpcModels.QueryBankCardInfoResponse localQueryBankCardInfoResponse = this.mSeiTsmAuthManager.queryBankCardInfo(paramContext, paramString);
      if (localQueryBankCardInfoResponse == null)
      {
        i = -1;
        LogUtils.d("queryBankCardInfo result: " + i);
      }
      Object[] arrayOfObject;
      for (BaseResponse localBaseResponse = new BaseResponse(i, new Object[0]); ; localBaseResponse = new BaseResponse(0, arrayOfObject))
      {
        return localBaseResponse;
        j = localQueryBankCardInfoResponse.getResult();
        if (j != 0)
          break;
        arrayOfObject = new Object[1];
        arrayOfObject[0] = localQueryBankCardInfoResponse;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        int j;
        int i = 2;
        LogUtils.e("queryBankCardInfo failed with an io exception.", localIOException);
        continue;
        int k = MiTsmErrorCode.format(j);
        i = k;
      }
    }
  }

  public BaseResponse queryPan(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    LogUtils.v("-------------- start queryPan ---------------");
    TSMSessionManager.BusinessType localBusinessType = TSMSessionManager.BusinessType.INSTALL;
    try
    {
      TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
      TsmRpcModels.TsmSessionInfo localTsmSessionInfo = getSession(paramContext, paramCardInfo, localBusinessType);
      TsmRpcModels.QueryPanResponse localQueryPanResponse = this.mSeiTsmAuthManager.queryPan(paramContext, localTsmSessionInfo, paramBundle);
      if (localQueryPanResponse == null)
      {
        i = -1;
        LogUtils.i("queryPan failed,errorCode: " + i);
      }
      Object[] arrayOfObject;
      for (BaseResponse localBaseResponse = new BaseResponse(i, new Object[0]); ; localBaseResponse = new BaseResponse(0, arrayOfObject))
      {
        return localBaseResponse;
        j = localQueryPanResponse.getResult();
        if (j != 0)
          break;
        LogUtils.v("-------------- queryPan end ---------------");
        arrayOfObject = new Object[1];
        arrayOfObject[0] = localQueryPanResponse;
      }
    }
    catch (NfcEeIOException localNfcEeIOException)
    {
      while (true)
      {
        int j;
        LogUtils.e("queryPan failed with an nfc exception. errorCode:" + localNfcEeIOException.getErrorCode(), localNfcEeIOException);
        i = 10;
        continue;
        int k = MiTsmErrorCode.format(j);
        i = k;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        i = 2;
        LogUtils.e("queryPan failed with an io exception.", localIOException);
      }
    }
    catch (SeiTSMApiException localSeiTSMApiException)
    {
      while (true)
      {
        int i = MiTsmErrorCode.format(localSeiTSMApiException.getErrorCode());
        LogUtils.e("queryPan failed with an tsmapi exception.", localSeiTSMApiException);
      }
    }
  }

  public BaseResponse requestVerificationCode(Context paramContext, CardInfo paramCardInfo, String paramString)
  {
    TSMSessionManager.BusinessType localBusinessType = TSMSessionManager.BusinessType.INSTALL;
    try
    {
      TsmRpcModels.TsmSessionInfo localTsmSessionInfo = getSession(paramContext, paramCardInfo, localBusinessType);
      TsmRpcModels.CommonResponse localCommonResponse = this.mSeiTsmAuthManager.requestVerificationCode(paramContext, localTsmSessionInfo, paramString);
      if (localCommonResponse == null)
      {
        i = -1;
        LogUtils.d("requestVerificationCode result: " + i);
      }
      Object[] arrayOfObject;
      for (BaseResponse localBaseResponse = new BaseResponse(i, new Object[0]); ; localBaseResponse = new BaseResponse(0, arrayOfObject))
      {
        return localBaseResponse;
        j = localCommonResponse.getResult();
        if (j != 0)
          break;
        arrayOfObject = new Object[1];
        arrayOfObject[0] = localCommonResponse;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        int j;
        i = 2;
        LogUtils.e("requestVerificationCode failed with an io exception.", localIOException);
        continue;
        int k = MiTsmErrorCode.format(j);
        i = k;
      }
    }
    catch (SeiTSMApiException localSeiTSMApiException)
    {
      while (true)
      {
        int i = localSeiTSMApiException.getErrorCode();
        LogUtils.e("requestVerificationCode failed with an tsmapi exception.", localSeiTSMApiException);
      }
    }
  }

  public BaseResponse verifyCardInfo(Context paramContext, CardInfo paramCardInfo, String paramString, byte[] paramArrayOfByte, Bundle paramBundle)
  {
    LogUtils.v("-------------- start verifyCardInfo ---------------");
    TSMSessionManager.BusinessType localBusinessType = TSMSessionManager.BusinessType.INSTALL;
    try
    {
      TsmRpcModels.TsmSessionInfo localTsmSessionInfo = getSession(paramContext, paramCardInfo, localBusinessType);
      TsmRpcModels.VirtualCardInfoResponse localVirtualCardInfoResponse = this.mSeiTsmAuthManager.verifyCardInfo(paramContext, localTsmSessionInfo, paramString, paramArrayOfByte, paramBundle);
      if (localVirtualCardInfoResponse == null)
      {
        i = -1;
        LogUtils.d("verifyCardInfo result: " + i);
      }
      Object[] arrayOfObject;
      for (BaseResponse localBaseResponse = new BaseResponse(i, new Object[0]); ; localBaseResponse = new BaseResponse(0, arrayOfObject))
      {
        return localBaseResponse;
        j = localVirtualCardInfoResponse.getResult();
        if (j != 0)
          break;
        LogUtils.v("-------------- verifyCardInfo end ---------------");
        arrayOfObject = new Object[1];
        arrayOfObject[0] = localVirtualCardInfoResponse;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        int j;
        i = 2;
        LogUtils.e("verifyCardInfo failed with an io exception.", localIOException);
        continue;
        int k = MiTsmErrorCode.format(j);
        i = k;
      }
    }
    catch (SeiTSMApiException localSeiTSMApiException)
    {
      while (true)
      {
        int i = localSeiTSMApiException.getErrorCode();
        LogUtils.e("verifyCardInfo failed with an tsmapi exception.", localSeiTSMApiException);
      }
    }
  }

  public BaseResponse verifyVerificationCode(Context paramContext, CardInfo paramCardInfo, String paramString1, String paramString2)
  {
    TSMSessionManager.BusinessType localBusinessType = TSMSessionManager.BusinessType.INSTALL;
    int i = -1;
    try
    {
      TsmRpcModels.TsmSessionInfo localTsmSessionInfo = getSession(paramContext, paramCardInfo, localBusinessType);
      TsmRpcModels.CommonResponse localCommonResponse = this.mSeiTsmAuthManager.verifyVerificationCode(paramContext, localTsmSessionInfo, paramString1, paramString2);
      if (localCommonResponse == null);
      int j;
      for (i = -1; ; i = j)
      {
        LogUtils.d("verifyVerificationCode result: " + i);
        if ((i == 0) || (i == 3011))
          TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
        BaseResponse localBaseResponse = new BaseResponse(i, new Object[0]);
        while (true)
        {
          return localBaseResponse;
          i = localCommonResponse.getResult();
          if (i != 0)
            break;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localCommonResponse;
          localBaseResponse = new BaseResponse(0, arrayOfObject);
          if ((i != 0) && (i != 3011))
            continue;
          TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
        }
        j = MiTsmErrorCode.format(i);
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        i = 2;
        LogUtils.e("verifyVerificationCode failed with an io exception.", localIOException);
        if ((i != 0) && (i != 3011))
          continue;
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
      }
    }
    catch (SeiTSMApiException localSeiTSMApiException)
    {
      while (true)
      {
        i = localSeiTSMApiException.getErrorCode();
        LogUtils.e("verifyVerificationCode failed with an tsmapi exception.", localSeiTSMApiException);
        if ((i != 0) && (i != 3011))
          continue;
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
      }
    }
    finally
    {
      if ((i == 0) || (i == 3011))
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
    }
    throw localObject;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.bankcard.BankCardClient
 * JD-Core Version:    0.6.0
 */