package com.miui.tsmclient.model;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import com.miui.tsmclient.entity.PayableCardInfo;
import com.miui.tsmclient.model.mitsm.MiTSMCardClient;
import com.miui.tsmclient.pay.OrderInfo;

public class AsyncPayableCardClient extends PayableCardClient
{
  private static final int MAX_RETRY_COUNT = 10;
  private int mRetryCount;

  public AsyncPayableCardClient(MiTSMCardClient paramMiTSMCardClient)
  {
    super(paramMiTSMCardClient);
  }

  // ERROR //
  private BaseResponse pullBusCardTopUpData(Context paramContext, com.miui.tsmclient.entity.CardInfo paramCardInfo)
  {
    // Byte code:
    //   0: getstatic 28	com/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType:RECHARGE	Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;
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
    //   23: invokevirtual 37	com/miui/tsmclient/model/AsyncPayableCardClient:getSession	(Landroid/content/Context;Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   26: astore 15
    //   28: new 39	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   35: ldc 44
    //   37: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_0
    //   41: getfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   44: invokevirtual 53	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   47: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: invokestatic 63	com/miui/tsmclient/util/LogUtils:d	(Ljava/lang/String;)V
    //   53: aload_0
    //   54: getfield 67	com/miui/tsmclient/model/AsyncPayableCardClient:mSeiTsmAuthManager	Lcom/miui/tsmclient/seitsm/SeiTsmAuthManager;
    //   57: aload_1
    //   58: aload 15
    //   60: invokevirtual 72	com/miui/tsmclient/seitsm/SeiTsmAuthManager:pullBusCardTopUpData	(Landroid/content/Context;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand;
    //   63: astore 16
    //   65: aload 16
    //   67: ifnonnull +61 -> 128
    //   70: aload_0
    //   71: getfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   74: istore 19
    //   76: iload 19
    //   78: bipush 10
    //   80: if_icmplt -52 -> 28
    //   83: iload 4
    //   85: sipush 10050
    //   88: if_icmpeq +17 -> 105
    //   91: aload_0
    //   92: iconst_0
    //   93: putfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
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
    //   133: istore 17
    //   135: iload 17
    //   137: istore 4
    //   139: iload 4
    //   141: sipush 10050
    //   144: if_icmpne +139 -> 283
    //   147: ldc2_w 101
    //   150: invokestatic 108	java/lang/Thread:sleep	(J)V
    //   153: aload_0
    //   154: iconst_1
    //   155: aload_0
    //   156: getfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   159: iadd
    //   160: putfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   163: goto -93 -> 70
    //   166: astore 13
    //   168: new 39	java/lang/StringBuilder
    //   171: dup
    //   172: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   175: ldc 110
    //   177: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: aload 13
    //   182: invokevirtual 113	com/tsmclient/smartcard/exception/NfcEeIOException:getErrorCode	()I
    //   185: invokevirtual 53	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   188: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   191: aload 13
    //   193: invokestatic 117	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   196: bipush 10
    //   198: istore 4
    //   200: iload 4
    //   202: sipush 10050
    //   205: if_icmpeq +17 -> 222
    //   208: aload_0
    //   209: iconst_0
    //   210: putfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   213: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   216: aload_2
    //   217: aload_3
    //   218: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   221: pop
    //   222: aload 5
    //   224: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   227: goto -117 -> 110
    //   230: astore 22
    //   232: invokestatic 121	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   235: invokevirtual 124	java/lang/Thread:interrupt	()V
    //   238: goto -85 -> 153
    //   241: astore 11
    //   243: iconst_2
    //   244: istore 4
    //   246: ldc 126
    //   248: aload 11
    //   250: invokestatic 117	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   253: iload 4
    //   255: sipush 10050
    //   258: if_icmpeq +17 -> 275
    //   261: aload_0
    //   262: iconst_0
    //   263: putfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   266: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   269: aload_2
    //   270: aload_3
    //   271: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   274: pop
    //   275: aload 5
    //   277: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   280: goto -170 -> 110
    //   283: iload 4
    //   285: ifne +151 -> 436
    //   288: aload 16
    //   290: invokevirtual 130	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   293: ifnull +143 -> 436
    //   296: aload 16
    //   298: invokevirtual 130	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   301: invokeinterface 136 1 0
    //   306: ifne +130 -> 436
    //   309: aload 5
    //   311: invokevirtual 139	com/tsmclient/smartcard/terminal/AbstractTerminal:isNfcChannelOpen	()Z
    //   314: ifne +8 -> 322
    //   317: aload 5
    //   319: invokevirtual 142	com/tsmclient/smartcard/terminal/AbstractTerminal:connect	()V
    //   322: aload_0
    //   323: aload_1
    //   324: aload 5
    //   326: aload 15
    //   328: aload 16
    //   330: getstatic 148	com/miui/tsmclient/seitsm/TsmRpcModels$SeOperationType:TOPUP	Lcom/miui/tsmclient/seitsm/TsmRpcModels$SeOperationType;
    //   333: iconst_0
    //   334: invokevirtual 152	com/miui/tsmclient/model/AsyncPayableCardClient:executeCapdu	(Landroid/content/Context;Lcom/tsmclient/smartcard/terminal/AbstractTerminal;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand;Lcom/miui/tsmclient/seitsm/TsmRpcModels$SeOperationType;Z)Lcom/miui/tsmclient/model/BaseResponse;
    //   337: astore 9
    //   339: aload 9
    //   341: ifnull +65 -> 406
    //   344: aload 9
    //   346: getfield 155	com/miui/tsmclient/model/BaseResponse:mResultCode	I
    //   349: ifne +57 -> 406
    //   352: aload_0
    //   353: iconst_0
    //   354: putfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   357: goto -287 -> 70
    //   360: astore 8
    //   362: aload 8
    //   364: invokevirtual 156	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException:getErrorCode	()I
    //   367: istore 4
    //   369: ldc 158
    //   371: aload 8
    //   373: invokestatic 117	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   376: iload 4
    //   378: sipush 10050
    //   381: if_icmpeq +17 -> 398
    //   384: aload_0
    //   385: iconst_0
    //   386: putfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   389: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   392: aload_2
    //   393: aload_3
    //   394: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   397: pop
    //   398: aload 5
    //   400: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   403: goto -293 -> 110
    //   406: iload 4
    //   408: sipush 10050
    //   411: if_icmpeq +17 -> 428
    //   414: aload_0
    //   415: iconst_0
    //   416: putfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   419: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   422: aload_2
    //   423: aload_3
    //   424: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   427: pop
    //   428: aload 5
    //   430: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   433: goto -308 -> 125
    //   436: iload 4
    //   438: ifne -285 -> 153
    //   441: aload 16
    //   443: invokevirtual 130	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   446: ifnull +16 -> 462
    //   449: aload 16
    //   451: invokevirtual 130	com/miui/tsmclient/seitsm/TsmRpcModels$TsmAPDUCommand:getApdusList	()Ljava/util/List;
    //   454: invokeinterface 136 1 0
    //   459: ifeq -306 -> 153
    //   462: new 89	com/miui/tsmclient/model/BaseResponse
    //   465: dup
    //   466: iconst_0
    //   467: iconst_0
    //   468: anewarray 91	java/lang/Object
    //   471: invokespecial 94	com/miui/tsmclient/model/BaseResponse:<init>	(I[Ljava/lang/Object;)V
    //   474: astore 9
    //   476: iload 4
    //   478: sipush 10050
    //   481: if_icmpeq +17 -> 498
    //   484: aload_0
    //   485: iconst_0
    //   486: putfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   489: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   492: aload_2
    //   493: aload_3
    //   494: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   497: pop
    //   498: aload 5
    //   500: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   503: goto -378 -> 125
    //   506: astore 6
    //   508: iload 4
    //   510: sipush 10050
    //   513: if_icmpeq +17 -> 530
    //   516: aload_0
    //   517: iconst_0
    //   518: putfield 50	com/miui/tsmclient/model/AsyncPayableCardClient:mRetryCount	I
    //   521: invokestatic 78	com/miui/tsmclient/model/mitsm/TSMSessionManager:getInstance	()Lcom/miui/tsmclient/model/mitsm/TSMSessionManager;
    //   524: aload_2
    //   525: aload_3
    //   526: invokevirtual 82	com/miui/tsmclient/model/mitsm/TSMSessionManager:removeSession	(Lcom/miui/tsmclient/entity/CardInfo;Lcom/miui/tsmclient/model/mitsm/TSMSessionManager$BusinessType;)Lcom/miui/tsmclient/seitsm/TsmRpcModels$TsmSessionInfo;
    //   529: pop
    //   530: aload 5
    //   532: invokevirtual 87	com/tsmclient/smartcard/terminal/AbstractTerminal:close	()V
    //   535: aload 6
    //   537: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   19	76	166	com/tsmclient/smartcard/exception/NfcEeIOException
    //   128	135	166	com/tsmclient/smartcard/exception/NfcEeIOException
    //   147	153	166	com/tsmclient/smartcard/exception/NfcEeIOException
    //   153	163	166	com/tsmclient/smartcard/exception/NfcEeIOException
    //   232	238	166	com/tsmclient/smartcard/exception/NfcEeIOException
    //   288	357	166	com/tsmclient/smartcard/exception/NfcEeIOException
    //   441	476	166	com/tsmclient/smartcard/exception/NfcEeIOException
    //   147	153	230	java/lang/InterruptedException
    //   19	76	241	java/io/IOException
    //   128	135	241	java/io/IOException
    //   147	153	241	java/io/IOException
    //   153	163	241	java/io/IOException
    //   232	238	241	java/io/IOException
    //   288	357	241	java/io/IOException
    //   441	476	241	java/io/IOException
    //   19	76	360	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   128	135	360	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   147	153	360	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   153	163	360	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   232	238	360	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   288	357	360	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   441	476	360	com/miui/tsmclient/seitsm/Exception/SeiTSMApiException
    //   19	76	506	finally
    //   128	135	506	finally
    //   147	153	506	finally
    //   153	163	506	finally
    //   168	196	506	finally
    //   232	238	506	finally
    //   246	253	506	finally
    //   288	357	506	finally
    //   362	376	506	finally
    //   441	476	506	finally
  }

  protected BaseResponse recharge(Context paramContext, PayableCardInfo paramPayableCardInfo, OrderInfo paramOrderInfo, Tag paramTag, Bundle paramBundle)
  {
    if (paramBundle == null)
      paramBundle = new Bundle();
    paramBundle.putBoolean("extras_key_session_not_finish", true);
    BaseResponse localBaseResponse = null;
    if (this.mRetryCount == 0)
      localBaseResponse = super.recharge(paramContext, paramPayableCardInfo, paramOrderInfo, paramTag, paramBundle);
    while (true)
    {
      if ((localBaseResponse == null) || (localBaseResponse.mResultCode == 0))
        localBaseResponse = pullBusCardTopUpData(paramContext, paramPayableCardInfo);
      return localBaseResponse;
      if (this.mRetryCount != 10)
        continue;
      this.mRetryCount = 0;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.model.AsyncPayableCardClient
 * JD-Core Version:    0.6.0
 */