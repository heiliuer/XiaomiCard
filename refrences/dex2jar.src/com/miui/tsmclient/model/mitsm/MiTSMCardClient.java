package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseCardClient;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.SeiTsmAuthManager;
import com.miui.tsmclient.seitsm.TsmRpcModels.CommonResponse;
import com.miui.tsmclient.seitsm.TsmRpcModels.SeAPDUResponseItem;
import com.miui.tsmclient.seitsm.TsmRpcModels.SeAPDUResponseItem.Builder;
import com.miui.tsmclient.seitsm.TsmRpcModels.SeOperationType;
import com.miui.tsmclient.seitsm.TsmRpcModels.TsmAPDUCommand;
import com.miui.tsmclient.seitsm.TsmRpcModels.TsmCAPDU;
import com.miui.tsmclient.seitsm.TsmRpcModels.TsmSessionInfo;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.PrefUtils;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import com.tsmclient.smartcard.terminal.AbstractTerminal;
import com.tsmclient.smartcard.terminal.I2CSmartMxTerminal;
import com.tsmclient.smartcard.terminal.SPISmartMxTerminal;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiTSMCardClient extends BaseCardClient
{
  public static final String EXTRAS_KEY_SESSION_NOT_FINISH = "extras_key_session_not_finish";
  public static final String EXTRAS_KEY_SE_OPERATION = "extras_key_se_operation";
  protected static AtomicBoolean mNeedSync;
  protected SeiTsmAuthManager mSeiTsmAuthManager = new SeiTsmAuthManager();

  public MiTSMCardClient()
  {
    mNeedSync = new AtomicBoolean(true);
  }

  private BaseResponse startSeOperation(Context paramContext, CardInfo paramCardInfo, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, TsmRpcModels.SeOperationType paramSeOperationType, Bundle paramBundle)
    throws IOException, SeiTSMApiException
  {
    Object localObject1 = null;
    if (mNeedSync.get())
      localObject1 = syncEse(paramContext, paramCardInfo, paramTsmSessionInfo, paramBundle);
    TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand;
    if (!mNeedSync.get())
    {
      localTsmAPDUCommand = this.mSeiTsmAuthManager.startSeOperation(paramContext, paramTsmSessionInfo, paramSeOperationType, paramCardInfo, paramBundle);
      if (localTsmAPDUCommand != null)
        break label78;
      mNeedSync.set(true);
      localObject1 = new BaseResponse(8, new Object[0]);
    }
    while (true)
    {
      return localObject1;
      label78: int i = localTsmAPDUCommand.getResult();
      Object localObject2;
      if (i == 0)
      {
        if ((localTsmAPDUCommand.getApdusList() == null) || (localTsmAPDUCommand.getApdusList().isEmpty()))
        {
          localObject1 = new BaseResponse(0, new Object[0]);
          continue;
        }
        if (TextUtils.equals(paramCardInfo.mCardType, "BANKCARD"))
          localObject2 = new SPISmartMxTerminal(paramContext);
      }
      try
      {
        ((AbstractTerminal)localObject2).connect();
        BaseResponse localBaseResponse = executeCapdu(paramContext, (AbstractTerminal)localObject2, paramTsmSessionInfo, localTsmAPDUCommand);
        localObject1 = localBaseResponse;
        ((AbstractTerminal)localObject2).close();
        continue;
        localObject2 = new I2CSmartMxTerminal(paramContext);
      }
      finally
      {
        ((AbstractTerminal)localObject2).close();
      }
    }
  }

  public BaseResponse delete(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    LogUtils.d("Delete aid is : " + paramCardInfo.mAid);
    TsmRpcModels.SeOperationType localSeOperationType = TsmRpcModels.SeOperationType.DELETE;
    if (paramBundle != null)
      localSeOperationType = TsmRpcModels.SeOperationType.valueOf(paramBundle.getInt("extras_key_se_operation", 4));
    try
    {
      BaseResponse localBaseResponse2 = startSeOperation(paramContext, paramCardInfo, getSession(paramContext, paramCardInfo, null), localSeOperationType, paramBundle);
      localBaseResponse1 = localBaseResponse2;
      return localBaseResponse1;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        i = 2;
        LogUtils.e("delete failed with an io exception.", localIOException);
        if ((localSeOperationType == TsmRpcModels.SeOperationType.DELETE) && (i == 0))
        {
          if (TextUtils.equals(paramCardInfo.mCardType, "BANKCARD"))
            break;
          bool = true;
          if (TextUtils.equals(paramCardInfo.mAid, PrefUtils.getDefaultCard(paramContext, bool)))
            PrefUtils.setDefaultCard(paramContext, "", bool);
        }
        BaseResponse localBaseResponse1 = new BaseResponse(i, new Object[0]);
      }
    }
    catch (SeiTSMApiException localSeiTSMApiException)
    {
      while (true)
      {
        int i = MiTsmErrorCode.format(localSeiTSMApiException.getErrorCode());
        LogUtils.e("delete failed with an tsmapi exception.", localSeiTSMApiException);
        continue;
        boolean bool = false;
      }
    }
  }

  protected BaseResponse executeCapdu(Context paramContext, AbstractTerminal paramAbstractTerminal, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, TsmRpcModels.TsmAPDUCommand paramTsmAPDUCommand)
    throws IOException, SeiTSMApiException
  {
    return executeCapdu(paramContext, paramAbstractTerminal, paramTsmSessionInfo, paramTsmAPDUCommand, null, true);
  }

  protected BaseResponse executeCapdu(Context paramContext, AbstractTerminal paramAbstractTerminal, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, TsmRpcModels.TsmAPDUCommand paramTsmAPDUCommand, TsmRpcModels.SeOperationType paramSeOperationType, boolean paramBoolean)
    throws IOException, SeiTSMApiException
  {
    List localList = paramTsmAPDUCommand.getApdusList();
    if ((localList == null) || (localList.isEmpty()))
      throw new SeiTSMApiException(paramTsmAPDUCommand.getResult(), "capdu list is null,　errorCode:" + paramTsmAPDUCommand.getResult());
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    boolean bool = true;
    Iterator localIterator = localList.iterator();
    TsmRpcModels.TsmCAPDU localTsmCAPDU;
    ScResponse localScResponse;
    label197: TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand;
    BaseResponse localBaseResponse;
    if (localIterator.hasNext())
    {
      localTsmCAPDU = (TsmRpcModels.TsmCAPDU)localIterator.next();
      localScResponse = paramAbstractTerminal.transmit(localTsmCAPDU.getApdu().toByteArray());
      i++;
      byte[] arrayOfByte1 = localScResponse.getData().toBytes();
      byte[] arrayOfByte2 = localScResponse.getStatus().toBytes();
      localArrayList.add(TsmRpcModels.SeAPDUResponseItem.newBuilder().setIndex(i).setResponseData(ByteString.copyFrom(arrayOfByte1)).setResponseSw(ByteString.copyFrom(arrayOfByte2)).build());
      if (TextUtils.isEmpty(localTsmCAPDU.getExpectSwRegex()))
        LogUtils.d("no expected sw.");
    }
    else
    {
      if (!paramBoolean)
        break label280;
      localTsmAPDUCommand = this.mSeiTsmAuthManager.processSeResponse(paramContext, paramTsmSessionInfo, localArrayList);
      if (localTsmAPDUCommand != null)
        break label400;
      LogUtils.d("cannnot get nextApducommand,processSeResponse failed.");
      localBaseResponse = new BaseResponse(8, new Object[0]);
    }
    while (true)
    {
      return localBaseResponse;
      if (Pattern.compile(localTsmCAPDU.getExpectSwRegex()).matcher(Coder.bytesToHexString(localScResponse.getStatus().toBytes())).matches())
        break;
      bool = false;
      break label197;
      label280: TsmRpcModels.CommonResponse localCommonResponse = this.mSeiTsmAuthManager.processFinishNotify(paramContext, paramTsmSessionInfo, bool, paramSeOperationType, localArrayList);
      if ((localCommonResponse != null) && (localCommonResponse.getResult() == 0))
      {
        if (bool);
        for (int k = 0; ; k = 9999)
        {
          Object[] arrayOfObject = new Object[0];
          localBaseResponse = new BaseResponse(k, arrayOfObject);
          break;
        }
      }
      int j = 9999;
      if (localCommonResponse != null)
        j = localCommonResponse.getResult();
      String str = "processFinishNotify api returned an error, errorCode:" + j;
      throw new SeiTSMApiException(j, str);
      label400: int m = localTsmAPDUCommand.getResult();
      if (m != 0)
        throw new SeiTSMApiException(m, "processSeResponse api returned an error, errorCode:" + m);
      if ((localTsmAPDUCommand.getApdusList() != null) && (!localTsmAPDUCommand.getApdusList().isEmpty()))
      {
        localBaseResponse = executeCapdu(paramContext, paramAbstractTerminal, paramTsmSessionInfo, localTsmAPDUCommand);
        continue;
      }
      LogUtils.d("no more apdu, execte finished!");
      localBaseResponse = new BaseResponse(0, new Object[0]);
    }
  }

  public TsmRpcModels.TsmSessionInfo getSession(Context paramContext, CardInfo paramCardInfo, TSMSessionManager.BusinessType paramBusinessType)
    throws IOException, SeiTSMApiException
  {
    return getSession(paramContext, paramCardInfo, paramBusinessType, false);
  }

  public TsmRpcModels.TsmSessionInfo getSession(Context paramContext, CardInfo paramCardInfo, TSMSessionManager.BusinessType paramBusinessType, boolean paramBoolean)
    throws IOException, SeiTSMApiException
  {
    TSMSessionManager localTSMSessionManager = TSMSessionManager.getInstance();
    if (paramBusinessType == null);
    for (TsmRpcModels.TsmSessionInfo localTsmSessionInfo = localTSMSessionManager.getSession(paramContext, paramCardInfo); localTsmSessionInfo == null; localTsmSessionInfo = localTSMSessionManager.getSession(paramContext, paramCardInfo, paramBusinessType, paramBoolean))
      throw new SeiTSMApiException("cannot get a session to start operation.");
    if (localTsmSessionInfo.getResult() != 0)
      throw new SeiTSMApiException(localTsmSessionInfo.getResult(), "getSession failed, errorCode:" + localTsmSessionInfo.getResult());
    LogUtils.d("sessionInfo : " + localTsmSessionInfo.getSessionId());
    return localTsmSessionInfo;
  }

  public BaseResponse issue(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    TSMSessionManager.BusinessType localBusinessType = TSMSessionManager.BusinessType.INSTALL;
    try
    {
      TsmRpcModels.TsmSessionInfo localTsmSessionInfo = getSession(paramContext, paramCardInfo, localBusinessType);
      boolean bool = false;
      if (paramBundle != null)
        bool = paramBundle.getBoolean("pre_load");
      TsmRpcModels.SeOperationType localSeOperationType = TsmRpcModels.SeOperationType.INSTALL;
      if (bool)
        localSeOperationType = TsmRpcModels.SeOperationType.LOAD;
      BaseResponse localBaseResponse = startSeOperation(paramContext, paramCardInfo, localTsmSessionInfo, localSeOperationType, paramBundle);
      if (localBaseResponse.mResultCode != 0)
      {
        i = localBaseResponse.mResultCode;
        if ((paramBundle == null) || (!paramBundle.getBoolean("extras_key_session_not_finish")))
          TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
        localBaseResponse = new BaseResponse(i, new Object[0]);
      }
      while (true)
      {
        return localBaseResponse;
        if ((paramBundle != null) && (paramBundle.getBoolean("extras_key_session_not_finish")))
          continue;
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
      }
    }
    catch (NfcEeIOException localNfcEeIOException)
    {
      while (true)
      {
        LogUtils.e("issue failed with an nfc exception. errorCode:" + localNfcEeIOException.getErrorCode(), localNfcEeIOException);
        i = 10;
        if ((paramBundle != null) && (paramBundle.getBoolean("extras_key_session_not_finish")))
          continue;
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        i = 2;
        LogUtils.e("issue failed with an io exception.", localIOException);
        if ((paramBundle != null) && (paramBundle.getBoolean("extras_key_session_not_finish")))
          continue;
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
      }
    }
    catch (SeiTSMApiException localSeiTSMApiException)
    {
      while (true)
      {
        int i = MiTsmErrorCode.format(localSeiTSMApiException.getErrorCode());
        LogUtils.e("issue failed with an tsmapi exception.", localSeiTSMApiException);
        if ((paramBundle != null) && (paramBundle.getBoolean("extras_key_session_not_finish")))
          continue;
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
      }
    }
    finally
    {
      if ((paramBundle == null) || (!paramBundle.getBoolean("extras_key_session_not_finish")))
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
    }
    throw localObject;
  }

  public BaseResponse lock(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    TSMSessionManager.BusinessType localBusinessType = TSMSessionManager.BusinessType.LOCK;
    try
    {
      BaseResponse localBaseResponse2 = startSeOperation(paramContext, paramCardInfo, getSession(paramContext, paramCardInfo, localBusinessType), TsmRpcModels.SeOperationType.LOCK, paramBundle);
      localBaseResponse1 = localBaseResponse2;
      return localBaseResponse1;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        i = 2;
        LogUtils.e("lock failed with an io exception.", localIOException);
        TSMSessionManager.getInstance().removeSession(paramCardInfo, localBusinessType);
        BaseResponse localBaseResponse1 = new BaseResponse(i, new Object[0]);
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
  public BaseResponse saveAppKey(Context paramContext)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 399	com/miui/tsmclient/util/SysUtils:getSignedSpiPK	(Landroid/content/Context;)Landroid/os/Bundle;
    //   4: astore_2
    //   5: aload_2
    //   6: ifnonnull +27 -> 33
    //   9: ldc_w 401
    //   12: invokestatic 404	com/miui/tsmclient/util/LogUtils:w	(Ljava/lang/String;)V
    //   15: new 53	com/miui/tsmclient/model/BaseResponse
    //   18: dup
    //   19: bipush 255
    //   21: iconst_0
    //   22: anewarray 55	java/lang/Object
    //   25: invokespecial 58	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
    //   28: astore 9
    //   30: aload 9
    //   32: areturn
    //   33: getstatic 407	com/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType:SAVEKEY	Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;
    //   36: astore_3
    //   37: aload_0
    //   38: aload_1
    //   39: new 409	com/miui/tsmclient/entity/BankCardInfo
    //   42: dup
    //   43: invokespecial 410	com/miui/tsmclient/entity/BankCardInfo:<init>	()V
    //   46: aload_3
    //   47: invokevirtual 158	com/miui/tsmclient/model/mitsm/MiTSMCardClient:getSession	(Landroid/content/Context;Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   50: astore 12
    //   52: aload_0
    //   53: getfield 24	com/miui/tsmclient/model/mitsm/MiTSMCardClient:mSeiTsmAuthManager	Lcom/miui/tsmclient/seitsm/SeiTsmAuthManager;
    //   56: aload_1
    //   57: aload_2
    //   58: aload 12
    //   60: invokevirtual 413	com/miui/tsmclient/seitsm/SeiTsmAuthManager:saveAppKey	(Landroid/content/Context;Landroid/os/Bundle;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand;
    //   63: astore 13
    //   65: aload 13
    //   67: ifnonnull +42 -> 109
    //   70: ldc_w 415
    //   73: invokestatic 138	com/miui/tsmclient/util/LogUtils:d	(Ljava/lang/String;)V
    //   76: new 53	com/miui/tsmclient/model/BaseResponse
    //   79: dup
    //   80: bipush 8
    //   82: iconst_0
    //   83: anewarray 55	java/lang/Object
    //   86: invokespecial 58	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
    //   89: astore 9
    //   91: invokestatic 328	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   94: new 409	com/miui/tsmclient/entity/BankCardInfo
    //   97: dup
    //   98: invokespecial 410	com/miui/tsmclient/entity/BankCardInfo:<init>	()V
    //   101: aload_3
    //   102: invokevirtual 374	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   105: pop
    //   106: goto -76 -> 30
    //   109: aload 13
    //   111: invokevirtual 64	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getResult	()I
    //   114: istore 14
    //   116: iload 14
    //   118: ifne +167 -> 285
    //   121: aload 13
    //   123: invokevirtual 68	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   126: ifnull +16 -> 142
    //   129: aload 13
    //   131: invokevirtual 68	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   134: invokeinterface 73 1 0
    //   139: ifeq +35 -> 174
    //   142: new 53	com/miui/tsmclient/model/BaseResponse
    //   145: dup
    //   146: iconst_0
    //   147: iconst_0
    //   148: anewarray 55	java/lang/Object
    //   151: invokespecial 58	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
    //   154: astore 9
    //   156: invokestatic 328	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   159: new 409	com/miui/tsmclient/entity/BankCardInfo
    //   162: dup
    //   163: invokespecial 410	com/miui/tsmclient/entity/BankCardInfo:<init>	()V
    //   166: aload_3
    //   167: invokevirtual 374	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   170: pop
    //   171: goto -141 -> 30
    //   174: new 88	com/tsmclient/smartcard/terminal/SPISmartMxTerminal
    //   177: dup
    //   178: aload_1
    //   179: invokespecial 91	com/tsmclient/smartcard/terminal/SPISmartMxTerminal:<init>	(Landroid/content/Context;)V
    //   182: astore 16
    //   184: aload 16
    //   186: invokevirtual 96	com/tsmclient/smartcard/terminal/AbstractTerminal:connect	()V
    //   189: aload_0
    //   190: aload_1
    //   191: aload 16
    //   193: aload 12
    //   195: aload 13
    //   197: invokevirtual 100	com/miui/tsmclient/model/mitsm/MiTSMCardClient:executeCapdu	(Landroid/content/Context;Lcom/tsmclient/smartcard/terminal/AbstractTerminal;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand;)Lcom/miui/tsmclient/model/BaseResponse;
    //   200: astore 18
    //   202: aload 18
    //   204: astore 9
    //   206: aload 16
    //   208: invokevirtual 103	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   211: invokestatic 328	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   214: new 409	com/miui/tsmclient/entity/BankCardInfo
    //   217: dup
    //   218: invokespecial 410	com/miui/tsmclient/entity/BankCardInfo:<init>	()V
    //   221: aload_3
    //   222: invokevirtual 374	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   225: pop
    //   226: goto -196 -> 30
    //   229: astore 17
    //   231: aload 16
    //   233: invokevirtual 103	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   236: aload 17
    //   238: athrow
    //   239: astore 10
    //   241: iconst_2
    //   242: istore 7
    //   244: ldc_w 417
    //   247: aload 10
    //   249: invokestatic 166	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   252: invokestatic 328	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   255: new 409	com/miui/tsmclient/entity/BankCardInfo
    //   258: dup
    //   259: invokespecial 410	com/miui/tsmclient/entity/BankCardInfo:<init>	()V
    //   262: aload_3
    //   263: invokevirtual 374	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   266: pop
    //   267: new 53	com/miui/tsmclient/model/BaseResponse
    //   270: dup
    //   271: iload 7
    //   273: iconst_0
    //   274: anewarray 55	java/lang/Object
    //   277: invokespecial 58	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
    //   280: astore 9
    //   282: goto -252 -> 30
    //   285: new 37	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   288: dup
    //   289: iload 14
    //   291: new 108	java/lang/StringBuilder
    //   294: dup
    //   295: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   298: ldc_w 419
    //   301: invokevirtual 115	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: iload 14
    //   306: invokevirtual 118	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   309: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   312: invokespecial 125	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException:<init>	(ILjava/lang/String;)V
    //   315: athrow
    //   316: astore 6
    //   318: aload 6
    //   320: invokevirtual 181	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException:getErrorCode	()I
    //   323: istore 7
    //   325: ldc_w 421
    //   328: aload 6
    //   330: invokestatic 166	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   333: invokestatic 328	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   336: new 409	com/miui/tsmclient/entity/BankCardInfo
    //   339: dup
    //   340: invokespecial 410	com/miui/tsmclient/entity/BankCardInfo:<init>	()V
    //   343: aload_3
    //   344: invokevirtual 374	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   347: pop
    //   348: goto -81 -> 267
    //   351: astore 4
    //   353: invokestatic 328	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   356: new 409	com/miui/tsmclient/entity/BankCardInfo
    //   359: dup
    //   360: invokespecial 410	com/miui/tsmclient/entity/BankCardInfo:<init>	()V
    //   363: aload_3
    //   364: invokevirtual 374	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   367: pop
    //   368: aload 4
    //   370: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   184	202	229	finally
    //   37	91	239	java/io/IOException
    //   109	156	239	java/io/IOException
    //   174	184	239	java/io/IOException
    //   206	211	239	java/io/IOException
    //   231	239	239	java/io/IOException
    //   285	316	239	java/io/IOException
    //   37	91	316	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   109	156	316	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   174	184	316	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   206	211	316	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   231	239	316	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   285	316	316	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   37	91	351	finally
    //   109	156	351	finally
    //   174	184	351	finally
    //   206	211	351	finally
    //   231	239	351	finally
    //   244	252	351	finally
    //   285	316	351	finally
    //   318	333	351	finally
  }

  public BaseResponse syncEse(Context paramContext, CardInfo paramCardInfo, TsmRpcModels.TsmSessionInfo paramTsmSessionInfo, Bundle paramBundle)
    throws IOException, SeiTSMApiException
  {
    BaseResponse localBaseResponse;
    TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand;
    if (!PrefUtils.getBoolean(paramContext, "key_spi_pk_state", false))
    {
      localBaseResponse = saveAppKey(paramContext);
      if (localBaseResponse.mResultCode == 0)
        PrefUtils.putBoolean(paramContext, "key_spi_pk_state", true);
    }
    else
    {
      localTsmAPDUCommand = this.mSeiTsmAuthManager.startSeOperation(paramContext, paramTsmSessionInfo, TsmRpcModels.SeOperationType.SYNC, paramCardInfo, paramBundle);
      if (localTsmAPDUCommand != null)
        break label78;
      throw new SeiTSMApiException(8, "syncEse failed, no apdu commands");
    }
    LogUtils.w("failed to save spi pk and syncing se has been interruptted");
    while (true)
    {
      return localBaseResponse;
      label78: int i = localTsmAPDUCommand.getResult();
      SPISmartMxTerminal localSPISmartMxTerminal;
      if (i == 0)
        localSPISmartMxTerminal = new SPISmartMxTerminal(paramContext);
      try
      {
        localSPISmartMxTerminal.connect();
        localBaseResponse = executeCapdu(paramContext, localSPISmartMxTerminal, paramTsmSessionInfo, localTsmAPDUCommand);
        LogUtils.d("sync ese finished, result:" + localBaseResponse.mResultCode);
        mNeedSync.set(false);
        localSPISmartMxTerminal.close();
      }
      finally
      {
        localSPISmartMxTerminal.close();
      }
    }
  }

  public BaseResponse unrestrictEse(Context paramContext, Bundle paramBundle)
    throws IOException, SeiTSMApiException
  {
    TsmRpcModels.TsmSessionInfo localTsmSessionInfo = getSession(paramContext, null, null);
    TsmRpcModels.TsmAPDUCommand localTsmAPDUCommand = this.mSeiTsmAuthManager.startSeOperation(paramContext, localTsmSessionInfo, TsmRpcModels.SeOperationType.UNRESTRICT, null, paramBundle);
    if (localTsmAPDUCommand == null)
      throw new SeiTSMApiException(8, "unrestrict ese failed, no apdu commands");
    int i = localTsmAPDUCommand.getResult();
    if (i == 0)
    {
      I2CSmartMxTerminal localI2CSmartMxTerminal = new I2CSmartMxTerminal(paramContext);
      try
      {
        localI2CSmartMxTerminal.connect();
        LogUtils.d("don't need to unrestict ese");
        localBaseResponse = new BaseResponse(0, new Object[0]);
        return localBaseResponse;
      }
      catch (NfcEeIOException localNfcEeIOException)
      {
        BaseResponse localBaseResponse;
        while (localNfcEeIOException.getErrorCode() == 5)
        {
          localBaseResponse = executeCapdu(paramContext, localI2CSmartMxTerminal, localTsmSessionInfo, localTsmAPDUCommand);
          LogUtils.d("unrestricted ese finished, result: " + localBaseResponse.mResultCode);
          localI2CSmartMxTerminal.close();
        }
        throw localNfcEeIOException;
      }
      finally
      {
        localI2CSmartMxTerminal.close();
      }
    }
    throw new SeiTSMApiException(i, "unrestrict ese failed, errorCode: " + i);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.mitsm.MiTSMCardClient
 * JD-Core Version:    0.6.0
 */