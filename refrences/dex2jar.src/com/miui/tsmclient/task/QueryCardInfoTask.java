package com.miui.tsmclient.task;

import android.content.Context;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.bankcard.BankCardOperation;
import com.miui.tsmclient.net.TSMAuthManager;
import java.util.List;

public class QueryCardInfoTask extends BaseRxTask<Result>
{
  private BankCardOperation mBankCardOperation = new BankCardOperation();
  private TSMAuthManager mTSMAuthManager = new TSMAuthManager();

  public QueryCardInfoTask(Context paramContext)
  {
    super(paramContext, Result.class);
  }

  // ERROR //
  protected void doLoad(Result paramResult)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 24	com/miui/tsmclient/task/QueryCardInfoTask:mTSMAuthManager	Lcom/miui/tsmclient/net/TSMAuthManager;
    //   6: aload_0
    //   7: invokevirtual 39	com/miui/tsmclient/task/QueryCardInfoTask:getContext	()Landroid/content/Context;
    //   10: getstatic 45	com/miui/tsmclient/entity/CardGroupType:TRANSCARD	Lcom/miui/tsmclient/entity/CardGroupType;
    //   13: invokevirtual 49	com/miui/tsmclient/entity/CardGroupType:getId	()I
    //   16: invokestatic 53	com/miui/tsmclient/entity/CardGroupType:newInstance	(I)Lcom/miui/tsmclient/entity/CardGroupType;
    //   19: invokevirtual 57	com/miui/tsmclient/net/TSMAuthManager:queryCardProduct	(Landroid/content/Context;Lcom/miui/tsmclient/entity/CardGroupType;)Lorg/json/JSONArray;
    //   22: astore 6
    //   24: aload 6
    //   26: ifnull +156 -> 182
    //   29: new 59	java/util/LinkedList
    //   32: dup
    //   33: invokespecial 60	java/util/LinkedList:<init>	()V
    //   36: astore 7
    //   38: iconst_0
    //   39: istore 8
    //   41: iload 8
    //   43: aload 6
    //   45: invokevirtual 65	org/json/JSONArray:length	()I
    //   48: if_icmpge +49 -> 97
    //   51: aload 6
    //   53: iload 8
    //   55: invokevirtual 69	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   58: astore 12
    //   60: aload 12
    //   62: ldc 71
    //   64: invokevirtual 77	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   67: aconst_null
    //   68: invokestatic 83	com/miui/tsmclient/entity/CardInfoFactory:makeCardInfo	(Ljava/lang/String;Lorg/json/JSONObject;)Lcom/miui/tsmclient/entity/CardInfo;
    //   71: astore 13
    //   73: aload 13
    //   75: aload 12
    //   77: invokevirtual 89	com/miui/tsmclient/entity/CardInfo:parse	(Lorg/json/JSONObject;)Lcom/miui/tsmclient/entity/CardInfo;
    //   80: pop
    //   81: aload 7
    //   83: aload 13
    //   85: invokeinterface 95 2 0
    //   90: pop
    //   91: iinc 8 1
    //   94: goto -53 -> 41
    //   97: aload_0
    //   98: invokevirtual 39	com/miui/tsmclient/task/QueryCardInfoTask:getContext	()Landroid/content/Context;
    //   101: invokestatic 101	com/miui/tsmclient/util/SysUtils:getDefaultTransCard	(Landroid/content/Context;)Ljava/lang/String;
    //   104: astore 9
    //   106: new 103	java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   113: ldc 106
    //   115: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: aload 9
    //   120: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: invokevirtual 114	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   126: invokestatic 120	com/miui/tsmclient/util/LogUtils:d	(Ljava/lang/String;)V
    //   129: aload 7
    //   131: invokeinterface 124 1 0
    //   136: astore 10
    //   138: aload 10
    //   140: invokeinterface 130 1 0
    //   145: ifeq +109 -> 254
    //   148: aload 10
    //   150: invokeinterface 134 1 0
    //   155: checkcast 85	com/miui/tsmclient/entity/CardInfo
    //   158: astore 11
    //   160: aload_0
    //   161: invokevirtual 39	com/miui/tsmclient/task/QueryCardInfoTask:getContext	()Landroid/content/Context;
    //   164: aload 11
    //   166: invokestatic 140	com/miui/tsmclient/database/CardDataUtil:saveCardInfo	(Landroid/content/Context;Lcom/miui/tsmclient/entity/CardInfo;)V
    //   169: goto -31 -> 138
    //   172: astore_3
    //   173: aload 7
    //   175: astore_2
    //   176: ldc 142
    //   178: aload_3
    //   179: invokestatic 146	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   182: aload_1
    //   183: aload_2
    //   184: putfield 150	com/miui/tsmclient/task/QueryCardInfoTask$Result:mTransCardInfos	Ljava/util/List;
    //   187: aload_0
    //   188: getfield 29	com/miui/tsmclient/task/QueryCardInfoTask:mBankCardOperation	Lcom/miui/tsmclient/model/bankcard/BankCardOperation;
    //   191: aload_0
    //   192: invokevirtual 39	com/miui/tsmclient/task/QueryCardInfoTask:getContext	()Landroid/content/Context;
    //   195: new 152	com/miui/tsmclient/entity/BankCardInfo
    //   198: dup
    //   199: invokespecial 153	com/miui/tsmclient/entity/BankCardInfo:<init>	()V
    //   202: invokevirtual 157	com/miui/tsmclient/model/bankcard/BankCardOperation:updateCardInfo	(Landroid/content/Context;Lcom/miui/tsmclient/entity/CardInfo;)Lcom/miui/tsmclient/model/BaseResponse;
    //   205: astore 4
    //   207: aload 4
    //   209: getfield 163	com/miui/tsmclient/model/BaseResponse:mResultCode	I
    //   212: ifne +17 -> 229
    //   215: aload_1
    //   216: aload 4
    //   218: getfield 167	com/miui/tsmclient/model/BaseResponse:mDatas	[Ljava/lang/Object;
    //   221: iconst_0
    //   222: aaload
    //   223: checkcast 91	java/util/List
    //   226: putfield 170	com/miui/tsmclient/task/QueryCardInfoTask$Result:mBankCardInfos	Ljava/util/List;
    //   229: return
    //   230: astore 5
    //   232: ldc 142
    //   234: aload 5
    //   236: invokestatic 146	com/miui/tsmclient/util/LogUtils:e	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   239: goto -57 -> 182
    //   242: astore 5
    //   244: aload 7
    //   246: astore_2
    //   247: goto -15 -> 232
    //   250: astore_3
    //   251: goto -75 -> 176
    //   254: aload 7
    //   256: astore_2
    //   257: goto -75 -> 182
    //
    // Exception table:
    //   from	to	target	type
    //   41	169	172	java/io/IOException
    //   2	38	230	org/json/JSONException
    //   41	169	242	org/json/JSONException
    //   2	38	250	java/io/IOException
  }

  public static class Result
  {
    public List<BankCardInfo> mBankCardInfos = null;
    public List<CardInfo> mTransCardInfos = null;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.task.QueryCardInfoTask
 * JD-Core Version:    0.6.0
 */