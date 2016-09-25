package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;

public class AsyncMiTSMClient extends DecoratorMiTSMClient
{
  private static final int MAX_RETRY_COUNT = 10;
  private int mRetryCount;

  public AsyncMiTSMClient(MiTSMCardClient paramMiTSMCardClient)
  {
    super(paramMiTSMCardClient);
  }

  // ERROR //
  private BaseResponse pullBusCardPersoData(Context paramContext, CardInfo paramCardInfo)
  {
    // Byte code:
    //   0: getstatic 28	com/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType:INSTALL	Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;
    //   3: astore_3
    //   4: sipush 9999
    //   7: istore 4
    //   9: new 30	com/tsmclient/smartcard/terminal/I2CSmartMxTerminal
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 33	com/tsmclient/smartcard/terminal/I2CSmartMxTerminal:<init>	(Landroid/content/Context;)V
    //   17: astore 5
    //   19: aload_0
    //   20: aload_1
    //   21: aload_2
    //   22: aload_3
    //   23: invokevirtual 37	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:getSession	(Landroid/content/Context;Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   26: astore 15
    //   28: new 39	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   35: ldc 44
    //   37: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_0
    //   41: getfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   44: invokevirtual 53	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   47: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: invokestatic 63	com/miui/tsmclient/util/LogUtils:d	(Ljava/lang/String;)V
    //   53: aload_0
    //   54: getfield 67	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mSeiTsmAuthManager	Lcom/miui/tsmclient/seitsm/SeiTsmAuthManager;
    //   57: aload_1
    //   58: aload 15
    //   60: invokevirtual 72	com/miui/tsmclient/seitsm/SeiTsmAuthManager:pullBusCardPersoData	(Landroid/content/Context;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand;
    //   63: astore 16
    //   65: aload 16
    //   67: ifnonnull +61 -> 128
    //   70: aload_0
    //   71: getfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   74: istore 18
    //   76: iload 18
    //   78: bipush 10
    //   80: if_icmplt -52 -> 28
    //   83: iload 4
    //   85: sipush 10048
    //   88: if_icmpeq +17 -> 105
    //   91: aload_0
    //   92: iconst_0
    //   93: putfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   96: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   99: aload_2
    //   100: aload_3
    //   101: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   104: pop
    //   105: aload 5
    //   107: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   110: new 89	com/miui/tsmclient/model/BaseResponse
    //   113: dup
    //   114: iload 4
    //   116: iconst_0
    //   117: anewarray 91	java/lang/Object
    //   120: invokespecial 94	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
    //   123: astore 9
    //   125: aload 9
    //   127: areturn
    //   128: aload 16
    //   130: invokevirtual 100	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getResult	()I
    //   133: istore 4
    //   135: iload 4
    //   137: sipush 10048
    //   140: if_icmpne +144 -> 284
    //   143: ldc 102
    //   145: invokestatic 63	com/miui/tsmclient/util/LogUtils:d	(Ljava/lang/String;)V
    //   148: ldc2_w 103
    //   151: invokestatic 110	java/lang/Thread:sleep	(J)V
    //   154: aload_0
    //   155: iconst_1
    //   156: aload_0
    //   157: getfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   160: iadd
    //   161: putfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   164: goto -94 -> 70
    //   167: astore 13
    //   169: new 39	java/lang/StringBuilder
    //   172: dup
    //   173: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   176: ldc 112
    //   178: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: aload 13
    //   183: invokevirtual 115	com/tsmclient/smartcard/exception/NfcEeIOException:getErrorCode	()I
    //   186: invokevirtual 53	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   189: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: aload 13
    //   194: invokestatic 119	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   197: bipush 10
    //   199: istore 4
    //   201: iload 4
    //   203: sipush 10048
    //   206: if_icmpeq +17 -> 223
    //   209: aload_0
    //   210: iconst_0
    //   211: putfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   214: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   217: aload_2
    //   218: aload_3
    //   219: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   222: pop
    //   223: aload 5
    //   225: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   228: goto -118 -> 110
    //   231: astore 21
    //   233: invokestatic 123	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   236: invokevirtual 126	java/lang/Thread:interrupt	()V
    //   239: goto -85 -> 154
    //   242: astore 11
    //   244: iconst_2
    //   245: istore 4
    //   247: ldc 128
    //   249: aload 11
    //   251: invokestatic 119	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   254: iload 4
    //   256: sipush 10048
    //   259: if_icmpeq +17 -> 276
    //   262: aload_0
    //   263: iconst_0
    //   264: putfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   267: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   270: aload_2
    //   271: aload_3
    //   272: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   275: pop
    //   276: aload 5
    //   278: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   281: goto -171 -> 110
    //   284: iload 4
    //   286: ifne +151 -> 437
    //   289: aload 16
    //   291: invokevirtual 132	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   294: ifnull +143 -> 437
    //   297: aload 16
    //   299: invokevirtual 132	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   302: invokeinterface 138 1 0
    //   307: ifne +130 -> 437
    //   310: aload 5
    //   312: invokevirtual 141	com/tsmclient/smartcard/terminal/AbstractTerminal:isNfcChannelOpen	()Z
    //   315: ifne +8 -> 323
    //   318: aload 5
    //   320: invokevirtual 144	com/tsmclient/smartcard/terminal/AbstractTerminal:connect	()V
    //   323: aload_0
    //   324: aload_1
    //   325: aload 5
    //   327: aload 15
    //   329: aload 16
    //   331: getstatic 149	com/miui/tsmclient/seitsm/TsmRpcModels$SeOperationType:INSTALL	Lcom/miui/tsmclient/seitsm/TsmRpcModels$SeOperationType;
    //   334: iconst_0
    //   335: invokevirtual 153	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:executeCapdu	(Landroid/content/Context;Lcom/tsmclient/smartcard/terminal/AbstractTerminal;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand;Lcom/miui/tsmclient/seitsm/TsmRpcModels$SeOperationType;Z)Lcom/miui/tsmclient/model/BaseResponse;
    //   338: astore 9
    //   340: aload 9
    //   342: ifnull +65 -> 407
    //   345: aload 9
    //   347: getfield 156	com/miui/tsmclient/model/BaseResponse:mResultCode	I
    //   350: ifne +57 -> 407
    //   353: aload_0
    //   354: iconst_0
    //   355: putfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   358: goto -288 -> 70
    //   361: astore 8
    //   363: aload 8
    //   365: invokevirtual 157	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException:getErrorCode	()I
    //   368: istore 4
    //   370: ldc 159
    //   372: aload 8
    //   374: invokestatic 119	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   377: iload 4
    //   379: sipush 10048
    //   382: if_icmpeq +17 -> 399
    //   385: aload_0
    //   386: iconst_0
    //   387: putfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   390: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   393: aload_2
    //   394: aload_3
    //   395: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   398: pop
    //   399: aload 5
    //   401: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   404: goto -294 -> 110
    //   407: iload 4
    //   409: sipush 10048
    //   412: if_icmpeq +17 -> 429
    //   415: aload_0
    //   416: iconst_0
    //   417: putfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   420: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   423: aload_2
    //   424: aload_3
    //   425: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   428: pop
    //   429: aload 5
    //   431: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   434: goto -309 -> 125
    //   437: iload 4
    //   439: ifne -285 -> 154
    //   442: aload 16
    //   444: invokevirtual 132	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   447: ifnull +16 -> 463
    //   450: aload 16
    //   452: invokevirtual 132	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   455: invokeinterface 138 1 0
    //   460: ifeq -306 -> 154
    //   463: new 89	com/miui/tsmclient/model/BaseResponse
    //   466: dup
    //   467: iconst_0
    //   468: iconst_0
    //   469: anewarray 91	java/lang/Object
    //   472: invokespecial 94	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
    //   475: astore 9
    //   477: iload 4
    //   479: sipush 10048
    //   482: if_icmpeq +17 -> 499
    //   485: aload_0
    //   486: iconst_0
    //   487: putfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   490: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   493: aload_2
    //   494: aload_3
    //   495: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   498: pop
    //   499: aload 5
    //   501: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   504: goto -379 -> 125
    //   507: astore 6
    //   509: iload 4
    //   511: sipush 10048
    //   514: if_icmpeq +17 -> 531
    //   517: aload_0
    //   518: iconst_0
    //   519: putfield 50	com/miui/tsmclient/model/mitsm/AsyncMiTSMClient:mRetryCount	I
    //   522: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   525: aload_2
    //   526: aload_3
    //   527: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   530: pop
    //   531: aload 5
    //   533: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   536: aload 6
    //   538: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   19	76	167	com/tsmclient/smartcard/exception/NfcEeIOException
    //   128	148	167	com/tsmclient/smartcard/exception/NfcEeIOException
    //   148	154	167	com/tsmclient/smartcard/exception/NfcEeIOException
    //   154	164	167	com/tsmclient/smartcard/exception/NfcEeIOException
    //   233	239	167	com/tsmclient/smartcard/exception/NfcEeIOException
    //   289	358	167	com/tsmclient/smartcard/exception/NfcEeIOException
    //   442	477	167	com/tsmclient/smartcard/exception/NfcEeIOException
    //   148	154	231	java/lang/InterruptedException
    //   19	76	242	java/io/IOException
    //   128	148	242	java/io/IOException
    //   148	154	242	java/io/IOException
    //   154	164	242	java/io/IOException
    //   233	239	242	java/io/IOException
    //   289	358	242	java/io/IOException
    //   442	477	242	java/io/IOException
    //   19	76	361	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   128	148	361	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   148	154	361	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   154	164	361	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   233	239	361	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   289	358	361	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   442	477	361	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   19	76	507	finally
    //   128	148	507	finally
    //   148	154	507	finally
    //   154	164	507	finally
    //   169	197	507	finally
    //   233	239	507	finally
    //   247	254	507	finally
    //   289	358	507	finally
    //   363	377	507	finally
    //   442	477	507	finally
  }

  public BaseResponse issue(Context paramContext, CardInfo paramCardInfo, Bundle paramBundle)
  {
    if (paramBundle == null)
      paramBundle = new Bundle();
    paramBundle.putBoolean("extras_key_session_not_finish", true);
    BaseResponse localBaseResponse = null;
    if (this.mRetryCount == 0)
    {
      localBaseResponse = super.issue(paramContext, paramCardInfo, paramBundle);
      boolean bool = false;
      if (paramBundle != null)
        bool = paramBundle.getBoolean("pre_load");
      if ((bool) || ((localBaseResponse != null) && (localBaseResponse.mResultCode != 0)))
        break label99;
      localBaseResponse = pullBusCardPersoData(paramContext, paramCardInfo);
    }
    while (true)
    {
      return localBaseResponse;
      if (this.mRetryCount != 10)
        break;
      this.mRetryCount = 0;
      break;
      label99: TSMSessionManager.getInstance().removeSession(paramCardInfo, TSMSessionManager.BusinessType.INSTALL);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.mitsm.AsyncMiTSMClient
 * JD-Core Version:    0.6.0
 */