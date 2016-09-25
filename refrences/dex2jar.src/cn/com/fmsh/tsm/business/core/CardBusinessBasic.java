package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.CommunicationNotify;
import cn.com.fmsh.communication.TerminalCommunication;
import cn.com.fmsh.communication.TerminalCommunicationList;
import cn.com.fmsh.communication.core.LinkInfo;
import cn.com.fmsh.communication.exception.CommunicationException;
import cn.com.fmsh.communication.exception.CommunicationException.CommunicationExceptionType;
import cn.com.fmsh.communication.exception.SocketException;
import cn.com.fmsh.communication.exception.session.CloseSessionException;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.MessageHandleFactory;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.exception.InvalidParameterException;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.ScriptHandler;
import cn.com.fmsh.script.ScriptHandlerFactory;
import cn.com.fmsh.tsm.business.LocalDataHandler;
import cn.com.fmsh.tsm.business.SocketExceptionHandler;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import ct;
import java.io.InputStream;

public class CardBusinessBasic
{
  public FMLog a = LogFactory.getInstance().getLog();

  public final boolean a(TerminalCommunication paramTerminalCommunication)
  {
    try
    {
      boolean bool2 = paramTerminalCommunication.isOpenSession();
      bool1 = bool2;
      return bool1;
    }
    catch (ct localct)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  // ERROR //
  public final boolean a(String paramString)
    throws BusinessException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: getfield 79	cn/com/fmsh/tsm/business/core/CardBusinessBasic:d	Lcn/com/fmsh/communication/TerminalCommunicationList;
    //   6: aload_1
    //   7: invokeinterface 168 2 0
    //   12: astore_3
    //   13: aload_3
    //   14: ifnonnull +682 -> 696
    //   17: aload_0
    //   18: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   21: ifnull +25 -> 46
    //   24: aload_0
    //   25: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   28: aload_0
    //   29: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   32: iconst_4
    //   33: bipush 79
    //   35: ldc_w 271
    //   38: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   41: invokeinterface 202 3 0
    //   46: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   49: dup
    //   50: sipush 186
    //   53: bipush 11
    //   55: ldc_w 278
    //   58: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   61: getstatic 281	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_connect_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   64: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   67: athrow
    //   68: astore 10
    //   70: aload_0
    //   71: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   74: ifnonnull +13 -> 87
    //   77: aload_0
    //   78: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   81: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   84: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   87: aload_0
    //   88: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   91: ifnull +41 -> 132
    //   94: aload_0
    //   95: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   98: aload_0
    //   99: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   102: new 170	java/lang/StringBuilder
    //   105: dup
    //   106: iconst_4
    //   107: ldc_w 283
    //   110: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   113: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   116: aload 10
    //   118: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   121: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: invokeinterface 202 3 0
    //   132: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   135: dup
    //   136: iconst_2
    //   137: ldc_w 285
    //   140: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   143: getstatic 281	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_connect_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   146: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   149: athrow
    //   150: aload_0
    //   151: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   154: ifnull +43 -> 197
    //   157: aload_0
    //   158: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   161: aload_0
    //   162: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   165: new 170	java/lang/StringBuilder
    //   168: dup
    //   169: iconst_1
    //   170: bipush 14
    //   172: ldc_w 287
    //   175: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   178: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   181: aload 7
    //   183: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   186: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: invokeinterface 202 3 0
    //   197: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   200: dup
    //   201: bipush 6
    //   203: ldc_w 289
    //   206: invokestatic 178	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   209: getstatic 292	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_sign_in_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   212: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   215: athrow
    //   216: aload 5
    //   218: arraylength
    //   219: iconst_1
    //   220: if_icmple +17 -> 237
    //   223: new 294	java/util/Random
    //   226: dup
    //   227: invokespecial 295	java/util/Random:<init>	()V
    //   230: aload 5
    //   232: arraylength
    //   233: invokevirtual 299	java/util/Random:nextInt	(I)I
    //   236: istore_2
    //   237: aload 4
    //   239: aload 5
    //   241: iload_2
    //   242: aaload
    //   243: getfield 304	cn/com/fmsh/tsm/business/core/Configration$Key:index	I
    //   246: i2b
    //   247: invokevirtual 310	cn/com/fmsh/communication/core/TerminalInfo:setKeyIndex	(B)V
    //   250: aload 4
    //   252: aload 5
    //   254: iload_2
    //   255: aaload
    //   256: getfield 313	cn/com/fmsh/tsm/business/core/Configration$Key:exponent	[B
    //   259: invokevirtual 317	cn/com/fmsh/communication/core/TerminalInfo:setExponent	([B)V
    //   262: aload 4
    //   264: aload 5
    //   266: iload_2
    //   267: aaload
    //   268: getfield 320	cn/com/fmsh/tsm/business/core/Configration$Key:modulus	[B
    //   271: invokevirtual 323	cn/com/fmsh/communication/core/TerminalInfo:setModulus	([B)V
    //   274: aload 4
    //   276: aload_0
    //   277: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   280: invokevirtual 329	cn/com/fmsh/tsm/business/core/Configration:getBusinessVersion	()B
    //   283: invokevirtual 332	cn/com/fmsh/communication/core/TerminalInfo:setBusinessVersion	(B)V
    //   286: aload 4
    //   288: aload_0
    //   289: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   292: invokevirtual 335	cn/com/fmsh/tsm/business/core/Configration:getTerminalType	()[B
    //   295: invokevirtual 338	cn/com/fmsh/communication/core/TerminalInfo:setTerminalType	([B)V
    //   298: aload 4
    //   300: aload_0
    //   301: getfield 340	cn/com/fmsh/tsm/business/core/CardBusinessBasic:l	[B
    //   304: invokevirtual 343	cn/com/fmsh/communication/core/TerminalInfo:setSecurityCode	([B)V
    //   307: aload_0
    //   308: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   311: invokevirtual 346	cn/com/fmsh/tsm/business/core/Configration:getTerminalNumber	()[B
    //   314: ifnull +12 -> 326
    //   317: aload_0
    //   318: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   321: invokevirtual 346	cn/com/fmsh/tsm/business/core/Configration:getTerminalNumber	()[B
    //   324: arraylength
    //   325: pop
    //   326: aload_0
    //   327: getfield 348	cn/com/fmsh/tsm/business/core/CardBusinessBasic:j	[B
    //   330: ifnull +12 -> 342
    //   333: aload 4
    //   335: aload_0
    //   336: getfield 348	cn/com/fmsh/tsm/business/core/CardBusinessBasic:j	[B
    //   339: invokevirtual 351	cn/com/fmsh/communication/core/TerminalInfo:setAppend	([B)V
    //   342: aload_0
    //   343: getfield 353	cn/com/fmsh/tsm/business/core/CardBusinessBasic:k	[B
    //   346: ifnull +421 -> 767
    //   349: aload 4
    //   351: aload_0
    //   352: getfield 353	cn/com/fmsh/tsm/business/core/CardBusinessBasic:k	[B
    //   355: invokevirtual 356	cn/com/fmsh/communication/core/TerminalInfo:setTerminalNumber	([B)V
    //   358: aload_3
    //   359: aload 4
    //   361: iconst_0
    //   362: invokeinterface 360 3 0
    //   367: istore 9
    //   369: iload 9
    //   371: ireturn
    //   372: astore 8
    //   374: aload_0
    //   375: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   378: ifnonnull +13 -> 391
    //   381: aload_0
    //   382: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   385: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   388: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   391: aload_0
    //   392: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   395: ifnull +41 -> 436
    //   398: aload_0
    //   399: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   402: aload_0
    //   403: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   406: new 170	java/lang/StringBuilder
    //   409: dup
    //   410: ldc_w 362
    //   413: iconst_4
    //   414: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   417: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   420: aload 8
    //   422: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   425: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   431: invokeinterface 202 3 0
    //   436: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   439: dup
    //   440: iconst_1
    //   441: ldc_w 364
    //   444: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   447: getstatic 292	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_sign_in_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   450: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   453: athrow
    //   454: new 306	cn/com/fmsh/communication/core/TerminalInfo
    //   457: dup
    //   458: invokespecial 365	cn/com/fmsh/communication/core/TerminalInfo:<init>	()V
    //   461: astore 4
    //   463: aload_0
    //   464: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   467: aload_1
    //   468: invokevirtual 369	cn/com/fmsh/tsm/business/core/Configration:getKeys	(Ljava/lang/String;)[Lcn/com/fmsh/tsm/business/core/Configration$Key;
    //   471: astore 5
    //   473: aload 5
    //   475: ifnull +10 -> 485
    //   478: aload 5
    //   480: arraylength
    //   481: iconst_1
    //   482: if_icmpge -266 -> 216
    //   485: aload_0
    //   486: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   489: ifnull +23 -> 512
    //   492: aload_0
    //   493: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   496: aload_0
    //   497: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   500: iconst_2
    //   501: ldc_w 371
    //   504: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   507: invokeinterface 202 3 0
    //   512: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   515: dup
    //   516: ldc_w 373
    //   519: iconst_3
    //   520: bipush 85
    //   522: invokestatic 378	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   525: getstatic 381	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_no_key	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   528: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   531: athrow
    //   532: astore 7
    //   534: aload_0
    //   535: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   538: ifnonnull +13 -> 551
    //   541: aload_0
    //   542: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   545: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   548: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   551: aload 7
    //   553: invokevirtual 384	cn/com/fmsh/communication/exception/session/OpenSessionException:getExceptionType	()Lcn/com/fmsh/communication/exception/session/OpenSessionException$OpenSessionExceptionType;
    //   556: ifnull -406 -> 150
    //   559: aload_0
    //   560: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   563: ifnull -366 -> 197
    //   566: aload_0
    //   567: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   570: aload_0
    //   571: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   574: new 170	java/lang/StringBuilder
    //   577: dup
    //   578: iconst_1
    //   579: ldc_w 386
    //   582: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   585: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   588: aload 7
    //   590: invokevirtual 384	cn/com/fmsh/communication/exception/session/OpenSessionException:getExceptionType	()Lcn/com/fmsh/communication/exception/session/OpenSessionException$OpenSessionExceptionType;
    //   593: invokevirtual 389	cn/com/fmsh/communication/exception/session/OpenSessionException$OpenSessionExceptionType:getDescription	()Ljava/lang/String;
    //   596: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   599: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   602: invokeinterface 202 3 0
    //   607: goto -410 -> 197
    //   610: astore 6
    //   612: aload_0
    //   613: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   616: ifnonnull +13 -> 629
    //   619: aload_0
    //   620: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   623: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   626: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   629: aload_0
    //   630: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   633: ifnull +43 -> 676
    //   636: aload_0
    //   637: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   640: aload_0
    //   641: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   644: new 170	java/lang/StringBuilder
    //   647: dup
    //   648: ldc_w 391
    //   651: iconst_3
    //   652: bipush 57
    //   654: invokestatic 94	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   657: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   660: aload 6
    //   662: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   665: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   668: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   671: invokeinterface 202 3 0
    //   676: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   679: dup
    //   680: sipush 274
    //   683: ldc_w 393
    //   686: invokestatic 178	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   689: getstatic 292	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_sign_in_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   692: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   695: athrow
    //   696: aload_3
    //   697: invokeinterface 396 1 0
    //   702: ifne +9 -> 711
    //   705: aload_0
    //   706: aload_1
    //   707: invokevirtual 245	cn/com/fmsh/tsm/business/core/CardBusinessBasic:connect	(Ljava/lang/String;)Lcn/com/fmsh/communication/TerminalCommunication;
    //   710: astore_3
    //   711: aload_3
    //   712: invokeinterface 396 1 0
    //   717: ifne -263 -> 454
    //   720: aload_0
    //   721: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   724: ifnull +25 -> 749
    //   727: aload_0
    //   728: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   731: aload_0
    //   732: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   735: iconst_4
    //   736: bipush 52
    //   738: ldc_w 398
    //   741: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   744: invokeinterface 202 3 0
    //   749: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   752: dup
    //   753: ldc_w 400
    //   756: iconst_3
    //   757: invokestatic 252	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   760: getstatic 281	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_connect_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   763: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   766: athrow
    //   767: aload 4
    //   769: bipush 32
    //   771: newarray byte
    //   773: invokevirtual 356	cn/com/fmsh/communication/core/TerminalInfo:setTerminalNumber	([B)V
    //   776: goto -418 -> 358
    //
    // Exception table:
    //   from	to	target	type
    //   358	369	68	cn/com/fmsh/communication/exception/SocketException
    //   358	369	372	cn/com/fmsh/communication/exception/CommunicationException
    //   358	369	532	cn/com/fmsh/communication/exception/session/OpenSessionException
    //   358	369	610	cn/com/fmsh/exception/InvalidParameterException
  }

  // ERROR //
  public final byte[] a(CommunicationException paramCommunicationException, boolean paramBoolean, String paramString)
    throws BusinessException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 86	cn/com/fmsh/communication/exception/CommunicationException:getExceptionType	()Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   4: getstatic 123	cn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType:NO_REPONSE	Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   7: if_acmpne +14 -> 21
    //   10: aload_0
    //   11: iload_2
    //   12: aload_3
    //   13: invokevirtual 234	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	(ZLjava/lang/String;)[B
    //   16: astore 4
    //   18: aload 4
    //   20: areturn
    //   21: aload_1
    //   22: invokevirtual 86	cn/com/fmsh/communication/exception/CommunicationException:getExceptionType	()Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   25: getstatic 141	cn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType:INVALID_REPONSE	Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   28: if_acmpne +117 -> 145
    //   31: aload_0
    //   32: iload_2
    //   33: aload_3
    //   34: invokespecial 403	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	(ZLjava/lang/String;)[B
    //   37: astore 4
    //   39: goto -21 -> 18
    //   42: aload_1
    //   43: invokevirtual 86	cn/com/fmsh/communication/exception/CommunicationException:getExceptionType	()Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   46: getstatic 117	cn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType:CHECK_FAILED	Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   49: if_acmpne +126 -> 175
    //   52: aload_0
    //   53: sipush 262
    //   56: bipush 41
    //   58: ldc_w 405
    //   61: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   64: getstatic 153	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_invalid_format	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   67: iload_2
    //   68: invokevirtual 409	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   71: aconst_null
    //   72: astore 4
    //   74: goto -56 -> 18
    //   77: aload_1
    //   78: invokevirtual 86	cn/com/fmsh/communication/exception/CommunicationException:getExceptionType	()Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   81: getstatic 129	cn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType:INVALID_SESSION_NUMBER	Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   84: if_acmpne -42 -> 42
    //   87: aload_0
    //   88: ldc_w 411
    //   91: iconst_4
    //   92: invokestatic 252	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   95: getstatic 132	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_invalid_session_serial	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   98: iload_2
    //   99: invokevirtual 409	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   102: goto -31 -> 71
    //   105: astore 6
    //   107: aload_0
    //   108: bipush 106
    //   110: ldc_w 413
    //   113: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   116: getstatic 225	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_disconnect_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   119: iload_2
    //   120: invokevirtual 409	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   123: aload_0
    //   124: sipush 286
    //   127: bipush 37
    //   129: ldc_w 415
    //   132: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   135: getstatic 281	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_connect_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   138: iload_2
    //   139: invokevirtual 409	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   142: goto -71 -> 71
    //   145: aload_1
    //   146: invokevirtual 86	cn/com/fmsh/communication/exception/CommunicationException:getExceptionType	()Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   149: getstatic 156	cn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType:INVALID_SESSION	Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   152: if_acmpne -75 -> 77
    //   155: aload_0
    //   156: ldc_w 417
    //   159: iconst_5
    //   160: bipush 89
    //   162: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   165: getstatic 159	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_invalid_session	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   168: iload_2
    //   169: invokevirtual 409	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   172: goto -101 -> 71
    //   175: aload_1
    //   176: invokevirtual 86	cn/com/fmsh/communication/exception/CommunicationException:getExceptionType	()Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   179: getstatic 150	cn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType:INVALID_FORMAT	Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   182: if_acmpne +14 -> 196
    //   185: aload_0
    //   186: iload_2
    //   187: aload_3
    //   188: invokespecial 403	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	(ZLjava/lang/String;)[B
    //   191: astore 4
    //   193: goto -175 -> 18
    //   196: aload_0
    //   197: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   200: ifnull +47 -> 247
    //   203: aload_0
    //   204: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   207: aload_0
    //   208: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   211: new 170	java/lang/StringBuilder
    //   214: dup
    //   215: ldc_w 421
    //   218: sipush 138
    //   221: bipush 77
    //   223: invokestatic 378	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   226: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   229: aload_1
    //   230: invokevirtual 86	cn/com/fmsh/communication/exception/CommunicationException:getExceptionType	()Lcn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType;
    //   233: invokevirtual 111	cn/com/fmsh/communication/exception/CommunicationException$CommunicationExceptionType:getDescription	()Ljava/lang/String;
    //   236: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: invokeinterface 202 3 0
    //   247: aload_0
    //   248: aload_3
    //   249: invokevirtual 205	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	(Ljava/lang/String;)Z
    //   252: pop
    //   253: aload_0
    //   254: aload_3
    //   255: invokevirtual 208	cn/com/fmsh/tsm/business/core/CardBusinessBasic:disconnect	(Ljava/lang/String;)V
    //   258: goto -135 -> 123
    //
    // Exception table:
    //   from	to	target	type
    //   247	258	105	cn/com/fmsh/tsm/business/exception/BusinessException
  }

  public final byte[] a(boolean paramBoolean, String paramString)
    throws BusinessException
  {
    boolean bool = c(paramString);
    if (!bool)
    {
      if (this.a != null)
        this.a.warn(this.b, BCCUtil.endsWith("丁劥変琐早ｄ钯揿彁年ｙ醓斷铮掼夳贮", 156, 9));
      if (this.g != null)
        if (this.g.isReconnect4tException())
          break label327;
      label60: if (!bool)
      {
        if (this.a != null)
          this.a.debug(this.b, FM_Utils.copyValueOf(5, 66, "上勳夐瑐旮ｖ铢揻异幺（醋斸钴掩奿贵"));
        throwExceptionAndClose(CRCUtil.valueOf(2, "乂勨奞瑍旪！铠措异幩ｎ醞旴铫掣夦购"), BusinessException.ErrorMessage.local_communication_connect_fail, paramBoolean);
      }
    }
    while (true)
    {
      try
      {
        byte[] arrayOfByte2 = this.d.getTerminalCommunication(paramString).repeat();
        arrayOfByte1 = arrayOfByte2;
        return arrayOfByte1;
      }
      catch (CommunicationException localCommunicationException)
      {
        if (this.a == null)
          continue;
        FMLog localFMLog = this.a;
        String str2 = this.b;
        StringBuilder localStringBuilder2 = new StringBuilder(Util4Java.toString("丛勸奕瑏斷ｕ钯揬彃帡＝专加夝琗冄欠奨赴ｓ", 2, 120));
        if (localCommunicationException.getExceptionType() != null)
          break label340;
        str3 = "";
        localFMLog.warn(str2, str3);
        StringBuilder localStringBuilder1 = new StringBuilder(CRCUtil.valueOf(2, "乂勨奞瑍旪！铠措异幩ｎ义勥夑琀冚欩奨赯ａ"));
        if (localCommunicationException.getExceptionType() != null)
          break label353;
        str1 = "";
        throwExceptionAndClose(str1, BusinessException.ErrorMessage.local_communication_connect_fail, paramBoolean);
        arrayOfByte1 = null;
        continue;
      }
      catch (SocketException localSocketException)
      {
        if (this.a == null)
          continue;
        this.a.warn(this.b, FM_Exception.getChars(2, 2, "乁勼奛琇旵）铹掬弉帵＃下劲夑琑冔欺夬贺；") + Util4Java.getExceptionInfo(localSocketException));
        throwExceptionAndClose(BCCUtil.endsWith("丛劥奃瑌旻＜钭揳弛帤ｓ丘劤奌瑍冃欰奥赲", 2, 35), BusinessException.ErrorMessage.local_communication_connect_fail, paramBoolean);
        byte[] arrayOfByte1 = null;
        continue;
      }
      label327: bool = c(paramString);
      if (!bool)
        break;
      break label60;
      label340: String str3 = localCommunicationException.getExceptionType().getDescription();
      continue;
      label353: String str1 = localCommunicationException.getExceptionType().getDescription();
    }
  }

  public final boolean b(String paramString)
    throws BusinessException
  {
    int i1 = 0;
    TerminalCommunication localTerminalCommunication = this.d.getTerminalCommunication(paramString);
    if (localTerminalCommunication != null);
    try
    {
      boolean bool = localTerminalCommunication.closeSession(null);
      i1 = bool;
      return i1;
    }
    catch (InvalidParameterException localInvalidParameterException)
    {
      if (this.a != null)
        this.a.error(this.b, FM_Bytes.startsWith("绌竡吉幱叼筨遀讽氖斨ｄ佲儹皂叒敪旤敆8", 3, 10) + Util4Java.getExceptionInfo(localInvalidParameterException));
      throw new BusinessException(CRCUtil.valueOf(3, "纑窥告帿叭筰速诧汃斤ｏ佴儠皒叅敨早攒"), BusinessException.ErrorMessage.local_communication_sign_out_fail);
    }
    catch (SocketException localSocketException)
    {
      if (this.a != null)
        this.a.error(this.b, FM_Utils.copyValueOf(5, 45, "绘竲呛幤厴笯逞讼氚夁瑔斩＀缈绚凩珰式帢g") + Util4Java.getExceptionInfo(localSocketException));
      throw new BusinessException(FM_Bytes.startsWith("绎竬向帮厪笩達讦氌施ｄ缔纞冥珬彛幮", 5, 125), BusinessException.ErrorMessage.local_communication_sign_out_fail);
    }
    catch (CloseSessionException localCloseSessionException)
    {
      if (this.a != null)
        this.a.error(this.b, Util4Java.toString("绌童呁帥召笼遈诹汖奞理夷赩r", 5, 38) + Util4Java.getExceptionInfo(localCloseSessionException));
      throw new BusinessException(FM_CN.subSequence("纔窤呋幺叨筹逖讲氖奇瑔夰贵", 206), BusinessException.ErrorMessage.local_communication_sign_out_fail);
    }
    catch (CommunicationException localCommunicationException)
    {
      if (this.a != null)
        this.a.error(this.b, CRCUtil.valueOf(3, "纑窥告帿叭筰速诧汃奖瑅斢ｉ逌俦敨捧奞瑍彞幵>") + Util4Java.getExceptionInfo(localCommunicationException));
    }
    throw new BusinessException(CRCUtil.valueOf(118, "纄窲呟幬叠筿递说氖奁瑐斱４逓俻敻挲奉瑘彍幸"), BusinessException.ErrorMessage.local_communication_sign_out_fail);
  }

  public void businessFinish(boolean paramBoolean)
    throws BusinessException
  {
    if ((paramBoolean) && (this.f != null))
      this.f.close();
  }

  public void businessReady(String paramString1, String paramString2)
    throws BusinessException
  {
    TerminalCommunication localTerminalCommunication = this.d.getTerminalCommunication(paramString2);
    if (localTerminalCommunication == null)
    {
      if (this.a != null)
        this.a.warn(this.b, paramString1 + FM_Bytes.startsWith("\"纕窷辍揫剹帷厯奫走", 1, 123));
      throw new BusinessException(paramString1 + CRCUtil.valueOf(222, "t纍竹这掽刹帩去奭赨"), BusinessException.ErrorMessage.local_communication_connect_fail);
    }
    if (!localTerminalCommunication.isConnect())
      localTerminalCommunication = connect(paramString2);
    if (!localTerminalCommunication.isConnect())
    {
      if (this.a != null)
        this.a.warn(this.b, paramString1 + FM_Bytes.startsWith("#练竼迅掦刻幠叫奲赮", 2, 8));
      throw new BusinessException(paramString1 + FM_Exception.getChars(2, 75, "{纎竾辂探剢幮司奢贻"), BusinessException.ErrorMessage.local_communication_connect_fail);
    }
    if ((!a(localTerminalCommunication)) && (!a(paramString2)))
    {
      if (this.a != null)
        this.a.warn(this.b, paramString1 + Util4Java.toString("$纁竡杹筦剭", 5, 69));
      throw new BusinessException(paramString1 + CRCUtil.valueOf(218, "p纉竽筽判头赳"), BusinessException.ErrorMessage.local_communication_sign_in_fail);
    }
  }

  // ERROR //
  public TerminalCommunication connect(String paramString)
    throws BusinessException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   6: ifnonnull +13 -> 19
    //   9: aload_0
    //   10: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   13: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   16: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   19: aload_0
    //   20: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   23: ifnull +51 -> 74
    //   26: aload_0
    //   27: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   30: aload_0
    //   31: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   34: new 170	java/lang/StringBuilder
    //   37: dup
    //   38: iconst_3
    //   39: bipush 12
    //   41: ldc_w 510
    //   44: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   47: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   50: aload_1
    //   51: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: iconst_4
    //   55: bipush 36
    //   57: ldc_w 512
    //   60: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   63: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   69: invokeinterface 515 3 0
    //   74: aload_0
    //   75: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   78: ifnonnull +265 -> 343
    //   81: aload_0
    //   82: aload_0
    //   83: invokevirtual 519	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getConfigration	()Lcn/com/fmsh/tsm/business/core/Configration;
    //   86: putfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   89: aload_0
    //   90: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   93: ifnonnull +250 -> 343
    //   96: aload_0
    //   97: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   100: ifnull +25 -> 125
    //   103: aload_0
    //   104: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   107: aload_0
    //   108: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   111: ldc_w 521
    //   114: iconst_1
    //   115: bipush 50
    //   117: invokestatic 378	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   120: invokeinterface 202 3 0
    //   125: aload_2
    //   126: areturn
    //   127: aload_0
    //   128: getfield 79	cn/com/fmsh/tsm/business/core/CardBusinessBasic:d	Lcn/com/fmsh/communication/TerminalCommunicationList;
    //   131: aload_1
    //   132: invokeinterface 168 2 0
    //   137: astore 4
    //   139: aload 4
    //   141: ifnonnull +175 -> 316
    //   144: aload_0
    //   145: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   148: ifnull -23 -> 125
    //   151: aload_0
    //   152: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   155: aload_0
    //   156: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   159: sipush 276
    //   162: ldc_w 523
    //   165: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   168: invokeinterface 202 3 0
    //   173: goto -48 -> 125
    //   176: astore 7
    //   178: aload_0
    //   179: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   182: ifnull +25 -> 207
    //   185: aload_0
    //   186: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   189: aload_0
    //   190: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   193: iconst_3
    //   194: bipush 60
    //   196: ldc_w 525
    //   199: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   202: invokeinterface 202 3 0
    //   207: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   210: dup
    //   211: ldc_w 527
    //   214: iconst_3
    //   215: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   218: getstatic 530	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_connect_param_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   221: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   224: athrow
    //   225: astore 6
    //   227: aload_0
    //   228: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   231: ifnull +67 -> 298
    //   234: aload_0
    //   235: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   238: aload_0
    //   239: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   242: new 170	java/lang/StringBuilder
    //   245: dup
    //   246: iconst_4
    //   247: bipush 25
    //   249: ldc_w 532
    //   252: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   255: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   258: aload_3
    //   259: invokevirtual 537	cn/com/fmsh/communication/core/LinkInfo:getAddress	()Ljava/lang/String;
    //   262: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: ldc_w 539
    //   268: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: aload_3
    //   272: invokevirtual 543	cn/com/fmsh/communication/core/LinkInfo:getPort	()I
    //   275: invokevirtual 546	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   278: ldc_w 548
    //   281: iconst_4
    //   282: bipush 124
    //   284: invokestatic 94	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   287: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   293: invokeinterface 202 3 0
    //   298: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   301: dup
    //   302: iconst_5
    //   303: ldc_w 550
    //   306: invokestatic 178	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   309: getstatic 281	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_connect_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   312: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   315: athrow
    //   316: aload 4
    //   318: aload_0
    //   319: getfield 552	cn/com/fmsh/tsm/business/core/CardBusinessBasic:e	Lcn/com/fmsh/communication/CommunicationNotify;
    //   322: invokeinterface 556 2 0
    //   327: aload 4
    //   329: invokeinterface 396 1 0
    //   334: ifeq +56 -> 390
    //   337: aload 4
    //   339: astore_2
    //   340: goto -215 -> 125
    //   343: aload_0
    //   344: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   347: aload_1
    //   348: invokevirtual 560	cn/com/fmsh/tsm/business/core/Configration:getLinkInfo	(Ljava/lang/String;)Lcn/com/fmsh/communication/core/LinkInfo;
    //   351: astore_3
    //   352: aload_3
    //   353: ifnonnull -226 -> 127
    //   356: aload_0
    //   357: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   360: ifnull -235 -> 125
    //   363: aload_0
    //   364: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   367: aload_0
    //   368: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   371: sipush 238
    //   374: bipush 39
    //   376: ldc_w 562
    //   379: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   382: invokeinterface 202 3 0
    //   387: goto -262 -> 125
    //   390: aload 4
    //   392: aload_3
    //   393: invokeinterface 565 2 0
    //   398: istore 5
    //   400: iload 5
    //   402: ifne +92 -> 494
    //   405: aload_0
    //   406: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   409: ifnull +67 -> 476
    //   412: aload_0
    //   413: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   416: aload_0
    //   417: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   420: new 170	java/lang/StringBuilder
    //   423: dup
    //   424: ldc_w 567
    //   427: sipush 238
    //   430: bipush 21
    //   432: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   435: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   438: aload_3
    //   439: invokevirtual 537	cn/com/fmsh/communication/core/LinkInfo:getAddress	()Ljava/lang/String;
    //   442: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: ldc_w 539
    //   448: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   451: aload_3
    //   452: invokevirtual 543	cn/com/fmsh/communication/core/LinkInfo:getPort	()I
    //   455: invokevirtual 546	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   458: ldc_w 569
    //   461: iconst_2
    //   462: invokestatic 252	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   465: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   468: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   471: invokeinterface 202 3 0
    //   476: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   479: dup
    //   480: ldc_w 571
    //   483: iconst_5
    //   484: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   487: getstatic 281	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_connect_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   490: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   493: athrow
    //   494: aload 4
    //   496: astore_2
    //   497: goto -372 -> 125
    //
    // Exception table:
    //   from	to	target	type
    //   390	400	176	cn/com/fmsh/exception/InvalidParameterException
    //   390	400	225	cn/com/fmsh/communication/exception/SocketException
  }

  // ERROR //
  public InputStream decryptFile(String paramString)
    throws BusinessException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 581	java/lang/Object:getClass	()Ljava/lang/Class;
    //   4: aload_1
    //   5: invokevirtual 584	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   8: astore_2
    //   9: sipush 128
    //   12: newarray byte
    //   14: astore_3
    //   15: sipush 512
    //   18: newarray byte
    //   20: astore 4
    //   22: new 586	java/io/ByteArrayOutputStream
    //   25: dup
    //   26: invokespecial 587	java/io/ByteArrayOutputStream:<init>	()V
    //   29: astore 5
    //   31: iconst_1
    //   32: newarray byte
    //   34: astore 6
    //   36: aload_2
    //   37: aload 6
    //   39: invokevirtual 593	java/io/InputStream:read	([B)I
    //   42: pop
    //   43: aload_2
    //   44: aload_3
    //   45: invokevirtual 593	java/io/InputStream:read	([B)I
    //   48: sipush 128
    //   51: if_icmpge +164 -> 215
    //   54: aload_0
    //   55: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   58: ifnonnull +13 -> 71
    //   61: aload_0
    //   62: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   65: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   68: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   71: aload_0
    //   72: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   75: ifnull +25 -> 100
    //   78: aload_0
    //   79: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   82: aload_0
    //   83: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   86: ldc_w 595
    //   89: iconst_2
    //   90: bipush 111
    //   92: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   95: invokeinterface 202 3 0
    //   100: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   103: dup
    //   104: ldc_w 597
    //   107: sipush 350
    //   110: bipush 34
    //   112: invokestatic 378	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   115: getstatic 600	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_config_invaild_content	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   118: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   121: athrow
    //   122: astore 10
    //   124: aload_0
    //   125: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   128: ifnonnull +13 -> 141
    //   131: aload_0
    //   132: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   135: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   138: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   141: aload_0
    //   142: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   145: ifnull +23 -> 168
    //   148: aload_0
    //   149: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   152: aload_0
    //   153: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   156: iconst_1
    //   157: ldc_w 602
    //   160: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   163: invokeinterface 202 3 0
    //   168: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   171: dup
    //   172: ldc_w 604
    //   175: sipush 224
    //   178: bipush 36
    //   180: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   183: getstatic 607	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   186: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   189: athrow
    //   190: astore 8
    //   192: aload_2
    //   193: ifnull +7 -> 200
    //   196: aload_2
    //   197: invokevirtual 608	java/io/InputStream:close	()V
    //   200: aload 8
    //   202: athrow
    //   203: aload 5
    //   205: aload 4
    //   207: iload 12
    //   209: invokestatic 614	java/util/Arrays:copyOf	([BI)[B
    //   212: invokevirtual 617	java/io/ByteArrayOutputStream:write	([B)V
    //   215: aload_2
    //   216: aload 4
    //   218: invokevirtual 593	java/io/InputStream:read	([B)I
    //   221: istore 12
    //   223: iload 12
    //   225: ifge -22 -> 203
    //   228: aload_2
    //   229: ifnull +7 -> 236
    //   232: aload_2
    //   233: invokevirtual 608	java/io/InputStream:close	()V
    //   236: aload 6
    //   238: iconst_0
    //   239: baload
    //   240: istore 13
    //   242: aload_0
    //   243: getfield 71	cn/com/fmsh/tsm/business/core/CardBusinessBasic:o	Lcn/com/fmsh/tsm/business/core/ConfigKeyManager;
    //   246: iload 13
    //   248: invokevirtual 621	cn/com/fmsh/tsm/business/core/ConfigKeyManager:getConfigKey	(I)Lcn/com/fmsh/tsm/business/core/ConfigKey;
    //   251: astore 14
    //   253: aload 14
    //   255: ifnonnull +159 -> 414
    //   258: aload_0
    //   259: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   262: ifnonnull +13 -> 275
    //   265: aload_0
    //   266: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   269: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   272: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   275: aload_0
    //   276: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   279: ifnull +25 -> 304
    //   282: aload_0
    //   283: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   286: aload_0
    //   287: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   290: iconst_5
    //   291: bipush 62
    //   293: ldc_w 623
    //   296: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   299: invokeinterface 202 3 0
    //   304: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   307: dup
    //   308: iconst_3
    //   309: ldc_w 625
    //   312: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   315: getstatic 600	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_config_invaild_content	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   318: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   321: athrow
    //   322: astore 7
    //   324: aload_0
    //   325: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   328: ifnonnull +13 -> 341
    //   331: aload_0
    //   332: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   335: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   338: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   341: aload_0
    //   342: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   345: ifnull +41 -> 386
    //   348: aload_0
    //   349: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   352: aload_0
    //   353: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   356: new 170	java/lang/StringBuilder
    //   359: dup
    //   360: iconst_3
    //   361: ldc_w 627
    //   364: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   367: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   370: aload 7
    //   372: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   375: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   381: invokeinterface 202 3 0
    //   386: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   389: dup
    //   390: iconst_4
    //   391: ldc_w 629
    //   394: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   397: getstatic 607	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   400: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   403: athrow
    //   404: astore 9
    //   406: aload 9
    //   408: invokevirtual 632	java/io/IOException:printStackTrace	()V
    //   411: goto -211 -> 200
    //   414: aload 14
    //   416: invokevirtual 637	cn/com/fmsh/tsm/business/core/ConfigKey:getPublicKey	()[B
    //   419: aload 14
    //   421: invokevirtual 640	cn/com/fmsh/tsm/business/core/ConfigKey:getPrivateKey	()[B
    //   424: aload_3
    //   425: iconst_0
    //   426: invokestatic 646	cn/com/fmsh/util/algorithm/RSA:decrtyByPrivate	([B[B[BZ)[B
    //   429: astore 15
    //   431: aload 15
    //   433: arraylength
    //   434: bipush 36
    //   436: if_icmpge +83 -> 519
    //   439: aload_0
    //   440: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   443: ifnonnull +13 -> 456
    //   446: aload_0
    //   447: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   450: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   453: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   456: aload_0
    //   457: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   460: ifnull +27 -> 487
    //   463: aload_0
    //   464: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   467: aload_0
    //   468: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   471: sipush 250
    //   474: bipush 7
    //   476: ldc_w 648
    //   479: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   482: invokeinterface 202 3 0
    //   487: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   490: dup
    //   491: sipush 172
    //   494: bipush 75
    //   496: ldc_w 650
    //   499: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   502: getstatic 600	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_config_invaild_content	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   505: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   508: athrow
    //   509: astore 17
    //   511: aload 17
    //   513: invokevirtual 632	java/io/IOException:printStackTrace	()V
    //   516: goto -280 -> 236
    //   519: aload 15
    //   521: bipush 20
    //   523: invokestatic 614	java/util/Arrays:copyOf	([BI)[B
    //   526: pop
    //   527: new 652	java/io/ByteArrayInputStream
    //   530: dup
    //   531: aload 15
    //   533: bipush 20
    //   535: bipush 36
    //   537: invokestatic 656	java/util/Arrays:copyOfRange	([BII)[B
    //   540: aload 5
    //   542: invokevirtual 659	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   545: invokestatic 665	cn/com/fmsh/util/algorithm/DES:decrypt4des3	([B[B)[B
    //   548: invokestatic 669	cn/com/fmsh/util/FM_Bytes:byteRemovePatch4Des	([B)[B
    //   551: invokespecial 671	java/io/ByteArrayInputStream:<init>	([B)V
    //   554: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   36	122	122	java/io/FileNotFoundException
    //   203	223	122	java/io/FileNotFoundException
    //   36	122	190	finally
    //   124	190	190	finally
    //   203	223	190	finally
    //   324	404	190	finally
    //   36	122	322	java/io/IOException
    //   203	223	322	java/io/IOException
    //   196	200	404	java/io/IOException
    //   232	236	509	java/io/IOException
  }

  public void disconnect(String paramString)
    throws BusinessException
  {
    TerminalCommunication localTerminalCommunication = this.d.getTerminalCommunication(paramString);
    if (localTerminalCommunication != null);
    try
    {
      localTerminalCommunication.disconnect();
      return;
    }
    catch (SocketException localSocketException)
    {
      if (this.a == null)
        this.a = LogFactory.getInstance().getLog();
      if (this.a != null)
        this.a.warn(this.b, FM_Int.lastIndexOf(2, "儦閻纟窷哕帩厫盘钣揻冥现弃帺９") + Util4Java.getExceptionInfo(localSocketException));
    }
    throw new BusinessException(FM_CN.subSequence("兣闲细竲哀帨厺盝银掲凼玥彆幫", 2));
  }

  public void disconnectAll()
    throws BusinessException
  {
    try
    {
      this.d.disConnect();
      return;
    }
    catch (SocketException localSocketException)
    {
      if (this.a == null)
        this.a = LogFactory.getInstance().getLog();
      if (this.a != null)
        this.a.warn(this.b, FM_Bytes.startsWith("優閮绅竸咍常厥皛铷掶冧珷当帣？", 88, 106) + Util4Java.getExceptionInfo(localSocketException));
    }
    throw new BusinessException(FM_Bytes.startsWith("其闠绝竲咉幾句皙钻揨冯班彇幵", 4, 8));
  }

  public ApduHandler getApduHandler()
  {
    return this.f;
  }

  public Configration getConfigration()
  {
    Configration localConfigration = null;
    int i1 = 0;
    if (this.i == null);
    while (true)
    {
      try
      {
        InputStream localInputStream = decryptFile(BCCUtil.endsWith(".06',xbkzt31!", 2, 113));
        if (localInputStream == null)
          continue;
        boolean bool = loadDefine(localInputStream);
        i1 = bool;
        if (i1 == 0)
        {
          if (this.a == null)
            continue;
          this.a.warn(this.b, FM_Long.concat("钣掭刣幭叹既３勪輨廔畣鄛罯斋仡夳质", 5));
          return localConfigration;
        }
      }
      catch (BusinessException localBusinessException)
      {
        if (this.a == null)
          continue;
        this.a.warn(this.b, Util4Java.toString("脚杣觽柝噴刖妑匟斮ｋ劶輸庀甫鄟罯旗仩夿赸", 1, 79));
        continue;
      }
      localConfigration = this.i;
    }
  }

  public ErrorCodeHandler getErrorCodeHandler()
  {
    if (this.q == null)
      this.q = new ErrorCodeHandler();
    try
    {
      InputStream localInputStream2 = decryptFile(FM_CN.subSequence("y&;gw/`m!-))~pmd", 328));
      localInputStream1 = localInputStream2;
      if (!this.q.init(localInputStream1))
        this.q = null;
      return this.q;
    }
    catch (BusinessException localBusinessException)
    {
      while (true)
      {
        if (this.a != null)
        {
          this.a.warn(this.b, Util4Java.toString("勥輲幪右哀廃辆溯噽凥玹彑幥}", 230, 106) + Util4Java.getExceptionInfo(localBusinessException));
          localInputStream1 = null;
          continue;
        }
        InputStream localInputStream1 = null;
      }
    }
  }

  public LocalDataHandler getLocalDataHandler()
  {
    return this.p;
  }

  public IMessageHandler getMessageHandler()
  {
    IMessageHandler localIMessageHandler = null;
    boolean bool = false;
    if (this.h == null);
    while (true)
    {
      try
      {
        InputStream localInputStream2 = decryptFile(CRCUtil.valueOf(5, "t!8=lqfw-,(:"));
        localInputStream1 = localInputStream2;
        if (localInputStream1 == null)
          continue;
        bool = messageConfigLoad(localInputStream1);
        if (bool)
          break label144;
        if (this.a != null)
          continue;
        this.a = LogFactory.getInstance().getLog();
        if (this.a == null)
          continue;
        this.a.warn(this.b, FM_Utils.copyValueOf(92, 69, "菰叚淙恹奟瑆旳ｆ劯輩涑怱酎缦斊交夦赹"));
        return localIMessageHandler;
      }
      catch (BusinessException localBusinessException)
      {
        if (this.a != null)
          continue;
        this.a = LogFactory.getInstance().getLog();
        if (this.a == null)
          break label152;
      }
      this.a.warn(this.b, CRCUtil.valueOf(1, "菠厞淑急够琊旫＂勿輭淉怽鄎罺斂仠夶赽"));
      InputStream localInputStream1 = null;
      continue;
      label144: localIMessageHandler = this.h;
      continue;
      label152: localInputStream1 = null;
    }
  }

  public ScriptHandler getScriptHandler()
  {
    ScriptHandler localScriptHandler;
    if (this.i == null)
    {
      this.i = getConfigration();
      if (this.i == null)
      {
        if (this.a != null)
          this.a.warn(this.b, FM_Bytes.startsWith("铼掲剼干厦旽ｌ勵轷廋甼鄄缰斔亾夬起", 1, 53));
        localScriptHandler = null;
        return localScriptHandler;
      }
    }
    if (this.m == null)
    {
      this.m = ScriptHandlerFactory.getInstance().getScriptHandler(getApduHandler());
      ApduFilterDataInitImpl localApduFilterDataInitImpl = new ApduFilterDataInitImpl(this.i.getAids());
      this.m.setApduFilterDataInit(localApduFilterDataInitImpl);
    }
    while (true)
    {
      localScriptHandler = this.m;
      break;
      this.m.setApduHandler(getApduHandler());
    }
  }

  public byte[] getSecurityCode()
  {
    return this.l;
  }

  public String getServer4Business(int paramInt)
  {
    try
    {
      String str2 = getConfigration().getServer4Business(paramInt);
      str1 = str2;
      return str1;
    }
    catch (ct localct)
    {
      while (true)
        String str1 = null;
    }
  }

  public SocketExceptionHandler getSocketExceptionHandler()
  {
    return this.g;
  }

  public TerminalCommunication getTerminalCommunication(String paramString)
  {
    try
    {
      TerminalCommunication localTerminalCommunication2 = this.d.getTerminalCommunication(paramString);
      localTerminalCommunication1 = localTerminalCommunication2;
      return localTerminalCommunication1;
    }
    catch (ct localct)
    {
      while (true)
        TerminalCommunication localTerminalCommunication1 = null;
    }
  }

  public byte[] getTerminalNumber()
  {
    return this.k;
  }

  // ERROR //
  public byte[] interaction(byte[] paramArrayOfByte, String paramString1, boolean paramBoolean, String paramString2)
    throws BusinessException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: getfield 79	cn/com/fmsh/tsm/business/core/CardBusinessBasic:d	Lcn/com/fmsh/communication/TerminalCommunicationList;
    //   7: aload 4
    //   9: invokeinterface 168 2 0
    //   14: astore 6
    //   16: aload 6
    //   18: ifnonnull +385 -> 403
    //   21: aload_0
    //   22: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   25: ifnull +50 -> 75
    //   28: aload_0
    //   29: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   32: aload_0
    //   33: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   36: new 170	java/lang/StringBuilder
    //   39: dup
    //   40: ldc_w 775
    //   43: iconst_2
    //   44: bipush 82
    //   46: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   49: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   52: aload 4
    //   54: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc_w 777
    //   60: iconst_5
    //   61: invokestatic 252	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   64: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokeinterface 202 3 0
    //   75: aload 5
    //   77: areturn
    //   78: astore 10
    //   80: aload_0
    //   81: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   84: ifnonnull +13 -> 97
    //   87: aload_0
    //   88: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   91: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   94: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   97: aload_0
    //   98: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   101: ifnull +48 -> 149
    //   104: aload_0
    //   105: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   108: aload_0
    //   109: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   112: new 170	java/lang/StringBuilder
    //   115: dup
    //   116: aload_2
    //   117: invokestatic 492	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   120: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   123: iconst_4
    //   124: ldc_w 779
    //   127: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   130: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: aload 10
    //   135: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   138: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   144: invokeinterface 460 3 0
    //   149: aload_0
    //   150: sipush 210
    //   153: bipush 32
    //   155: ldc_w 781
    //   158: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   161: getstatic 784	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_request_param_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   164: iload_3
    //   165: invokevirtual 409	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   168: aload 5
    //   170: ifnull +10 -> 180
    //   173: aload 5
    //   175: arraylength
    //   176: iconst_2
    //   177: if_icmpge -102 -> 75
    //   180: aload_0
    //   181: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   184: ifnonnull +13 -> 197
    //   187: aload_0
    //   188: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   191: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   194: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   197: aload_0
    //   198: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   201: ifnull +25 -> 226
    //   204: aload_0
    //   205: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   208: aload_0
    //   209: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   212: ldc_w 786
    //   215: iconst_5
    //   216: bipush 76
    //   218: invokestatic 94	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   221: invokeinterface 202 3 0
    //   226: aload_0
    //   227: sipush 300
    //   230: bipush 119
    //   232: ldc_w 788
    //   235: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   238: getstatic 791	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_no_response	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   241: iload_3
    //   242: invokevirtual 409	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   245: goto -170 -> 75
    //   248: astore 9
    //   250: aload_0
    //   251: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   254: ifnull +52 -> 306
    //   257: aload_0
    //   258: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   261: aload_0
    //   262: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   265: new 170	java/lang/StringBuilder
    //   268: dup
    //   269: aload_2
    //   270: invokestatic 492	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   273: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   276: ldc_w 793
    //   279: sipush 146
    //   282: bipush 79
    //   284: invokestatic 94	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   287: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: aload 9
    //   292: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   295: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   301: invokeinterface 460 3 0
    //   306: aload_0
    //   307: aload 9
    //   309: iload_3
    //   310: aload 4
    //   312: invokevirtual 795	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	(Lcn/com/fmsh/communication/exception/CommunicationException;ZLjava/lang/String;)[B
    //   315: astore 5
    //   317: goto -149 -> 168
    //   320: astore 8
    //   322: aload_0
    //   323: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   326: ifnonnull +13 -> 339
    //   329: aload_0
    //   330: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   333: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   336: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   339: aload_0
    //   340: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   343: ifnull +48 -> 391
    //   346: aload_0
    //   347: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   350: aload_0
    //   351: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   354: new 170	java/lang/StringBuilder
    //   357: dup
    //   358: aload_2
    //   359: invokestatic 492	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   362: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   365: ldc_w 797
    //   368: iconst_3
    //   369: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   372: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: aload 8
    //   377: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   380: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   386: invokeinterface 460 3 0
    //   391: aload_0
    //   392: iload_3
    //   393: aload 4
    //   395: invokevirtual 234	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	(ZLjava/lang/String;)[B
    //   398: astore 5
    //   400: goto -232 -> 168
    //   403: aload 6
    //   405: aload_1
    //   406: invokeinterface 800 2 0
    //   411: astore 7
    //   413: aload 7
    //   415: astore 5
    //   417: goto -249 -> 168
    //
    // Exception table:
    //   from	to	target	type
    //   403	413	78	cn/com/fmsh/exception/InvalidParameterException
    //   403	413	248	cn/com/fmsh/communication/exception/CommunicationException
    //   403	413	320	cn/com/fmsh/communication/exception/SocketException
  }

  // ERROR //
  public boolean loadDefine(InputStream paramInputStream)
    throws BusinessException
  {
    // Byte code:
    //   0: aload_0
    //   1: new 325	cn/com/fmsh/tsm/business/core/Configration
    //   4: dup
    //   5: invokespecial 805	cn/com/fmsh/tsm/business/core/Configration:<init>	()V
    //   8: putfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   11: aload_1
    //   12: ifnonnull +363 -> 375
    //   15: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   18: dup
    //   19: ldc_w 807
    //   22: sipush 340
    //   25: bipush 86
    //   27: invokestatic 94	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   30: invokespecial 678	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;)V
    //   33: athrow
    //   34: astore 17
    //   36: aload_0
    //   37: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   40: ifnonnull +13 -> 53
    //   43: aload_0
    //   44: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   47: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   50: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   53: aload_0
    //   54: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   57: ifnull +21 -> 78
    //   60: aload_0
    //   61: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   64: aload_0
    //   65: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   68: aload 17
    //   70: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   73: invokeinterface 460 3 0
    //   78: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   81: dup
    //   82: iconst_4
    //   83: bipush 11
    //   85: ldc_w 809
    //   88: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   91: getstatic 600	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_config_invaild_content	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   94: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   97: athrow
    //   98: aload 38
    //   100: iload 41
    //   102: invokeinterface 815 2 0
    //   107: invokeinterface 821 1 0
    //   112: astore 43
    //   114: aload 43
    //   116: ldc_w 823
    //   119: sipush 270
    //   122: bipush 23
    //   124: invokestatic 378	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   127: invokeinterface 829 2 0
    //   132: astore 44
    //   134: aload 44
    //   136: ifnull +12 -> 148
    //   139: aload 44
    //   141: invokeinterface 832 1 0
    //   146: astore 39
    //   148: aload 43
    //   150: ldc_w 834
    //   153: iconst_2
    //   154: bipush 85
    //   156: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   159: invokeinterface 829 2 0
    //   164: astore 45
    //   166: aload 45
    //   168: ifnull +1527 -> 1695
    //   171: aload 45
    //   173: invokeinterface 832 1 0
    //   178: bipush 255
    //   180: invokestatic 838	cn/com/fmsh/util/Util4Java:String2Int	(Ljava/lang/String;I)I
    //   183: istore 40
    //   185: goto +1510 -> 1695
    //   188: aload_0
    //   189: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   192: iload 40
    //   194: aload 39
    //   196: invokevirtual 842	cn/com/fmsh/tsm/business/core/Configration:addBusinessAndServer	(ILjava/lang/String;)V
    //   199: goto +1511 -> 1710
    //   202: aload 38
    //   204: invokeinterface 845 1 0
    //   209: istore 42
    //   211: iload 41
    //   213: iload 42
    //   215: if_icmplt -117 -> 98
    //   218: aload_0
    //   219: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   222: ifnull +23 -> 245
    //   225: aload_0
    //   226: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   229: aload_0
    //   230: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   233: iconst_4
    //   234: ldc_w 847
    //   237: invokestatic 178	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   240: invokeinterface 515 3 0
    //   245: iconst_1
    //   246: ireturn
    //   247: astore 16
    //   249: aload_0
    //   250: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   253: ifnonnull +13 -> 266
    //   256: aload_0
    //   257: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   260: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   263: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   266: aload_0
    //   267: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   270: ifnull +21 -> 291
    //   273: aload_0
    //   274: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   277: aload_0
    //   278: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   281: aload 16
    //   283: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   286: invokeinterface 460 3 0
    //   291: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   294: dup
    //   295: iconst_3
    //   296: bipush 40
    //   298: ldc_w 849
    //   301: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   304: getstatic 600	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_config_invaild_content	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   307: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   310: athrow
    //   311: astore 15
    //   313: aload_0
    //   314: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   317: ifnonnull +13 -> 330
    //   320: aload_0
    //   321: invokestatic 46	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   324: invokevirtual 50	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   327: putfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   330: aload_0
    //   331: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   334: ifnull +21 -> 355
    //   337: aload_0
    //   338: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   341: aload_0
    //   342: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   345: aload 15
    //   347: invokestatic 258	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   350: invokeinterface 460 3 0
    //   355: new 108	cn/com/fmsh/tsm/business/exception/BusinessException
    //   358: dup
    //   359: ldc_w 851
    //   362: iconst_5
    //   363: bipush 90
    //   365: invokestatic 378	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   368: getstatic 600	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_config_invaild_content	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   371: invokespecial 114	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   374: athrow
    //   375: invokestatic 857	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   378: astore_2
    //   379: aload_2
    //   380: invokevirtual 861	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   383: aload_1
    //   384: invokevirtual 867	javax/xml/parsers/DocumentBuilder:parse	(Ljava/io/InputStream;)Lorg/w3c/dom/Document;
    //   387: invokeinterface 873 1 0
    //   392: astore_3
    //   393: aload_3
    //   394: sipush 180
    //   397: bipush 121
    //   399: ldc_w 875
    //   402: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   405: invokeinterface 881 2 0
    //   410: astore 4
    //   412: iconst_0
    //   413: istore 5
    //   415: iload 5
    //   417: aload 4
    //   419: invokeinterface 845 1 0
    //   424: if_icmplt +480 -> 904
    //   427: aload_3
    //   428: ldc_w 883
    //   431: sipush 146
    //   434: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   437: invokeinterface 881 2 0
    //   442: iconst_0
    //   443: invokeinterface 815 2 0
    //   448: invokeinterface 821 1 0
    //   453: astore 18
    //   455: aload 18
    //   457: iconst_4
    //   458: ldc_w 885
    //   461: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   464: invokeinterface 829 2 0
    //   469: astore 19
    //   471: aload 19
    //   473: ifnull +20 -> 493
    //   476: aload_0
    //   477: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   480: aload 19
    //   482: invokeinterface 832 1 0
    //   487: invokestatic 889	cn/com/fmsh/util/FM_Bytes:hexStringToBytes	(Ljava/lang/String;)[B
    //   490: invokevirtual 890	cn/com/fmsh/tsm/business/core/Configration:setTerminalType	([B)V
    //   493: aload 18
    //   495: ldc_w 892
    //   498: iconst_4
    //   499: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   502: invokeinterface 829 2 0
    //   507: astore 20
    //   509: aload 20
    //   511: ifnull +21 -> 532
    //   514: aload_0
    //   515: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   518: aload 20
    //   520: invokeinterface 832 1 0
    //   525: invokestatic 898	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   528: i2b
    //   529: invokevirtual 899	cn/com/fmsh/tsm/business/core/Configration:setBusinessVersion	(B)V
    //   532: aload_3
    //   533: iconst_2
    //   534: bipush 116
    //   536: ldc_w 901
    //   539: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   542: invokeinterface 881 2 0
    //   547: astore 21
    //   549: aload 21
    //   551: ifnull +768 -> 1319
    //   554: aload 21
    //   556: iconst_0
    //   557: invokeinterface 815 2 0
    //   562: astore 51
    //   564: aload 51
    //   566: ifnull +529 -> 1095
    //   569: aload 51
    //   571: invokeinterface 821 1 0
    //   576: ldc_w 903
    //   579: iconst_5
    //   580: bipush 63
    //   582: invokestatic 94	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   585: invokeinterface 829 2 0
    //   590: astore 52
    //   592: aload 52
    //   594: ifnull +822 -> 1416
    //   597: aload_0
    //   598: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   601: aload 52
    //   603: invokeinterface 832 1 0
    //   608: iconst_0
    //   609: invokestatic 907	cn/com/fmsh/util/Util4Java:String2Byte	(Ljava/lang/String;B)B
    //   612: invokevirtual 910	cn/com/fmsh/tsm/business/core/Configration:setOrderSource	(B)V
    //   615: aload_3
    //   616: sipush 204
    //   619: bipush 75
    //   621: ldc_w 912
    //   624: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   627: invokeinterface 881 2 0
    //   632: astore 22
    //   634: aload 22
    //   636: ifnull +941 -> 1577
    //   639: aload 22
    //   641: iconst_0
    //   642: invokeinterface 815 2 0
    //   647: astore 49
    //   649: aload 49
    //   651: ifnull +862 -> 1513
    //   654: aload 49
    //   656: invokeinterface 821 1 0
    //   661: ldc_w 914
    //   664: iconst_5
    //   665: bipush 11
    //   667: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   670: invokeinterface 829 2 0
    //   675: astore 50
    //   677: aload 50
    //   679: ifnull +768 -> 1447
    //   682: aload_0
    //   683: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   686: aload 50
    //   688: invokeinterface 832 1 0
    //   693: invokevirtual 917	cn/com/fmsh/tsm/business/core/Configration:setCompanyCode	(Ljava/lang/String;)V
    //   696: aload_3
    //   697: ldc_w 919
    //   700: sipush 188
    //   703: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   706: invokeinterface 881 2 0
    //   711: astore 23
    //   713: aload 23
    //   715: ifnull +830 -> 1545
    //   718: aload 23
    //   720: iconst_0
    //   721: invokeinterface 815 2 0
    //   726: astore 46
    //   728: aload 46
    //   730: ifnull +879 -> 1609
    //   733: aload 46
    //   735: invokeinterface 821 1 0
    //   740: sipush 226
    //   743: bipush 114
    //   745: ldc_w 921
    //   748: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   751: invokeinterface 829 2 0
    //   756: astore 47
    //   758: aload 47
    //   760: ifnull +721 -> 1481
    //   763: aload 47
    //   765: invokeinterface 832 1 0
    //   770: invokestatic 898	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   773: invokestatic 927	cn/com/fmsh/util/log/Level:instance	(I)Lcn/com/fmsh/util/log/Level;
    //   776: astore 48
    //   778: aload 48
    //   780: ifnull +572 -> 1352
    //   783: aload_0
    //   784: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   787: aload 48
    //   789: invokevirtual 931	cn/com/fmsh/tsm/business/core/Configration:setLogLevel	(Lcn/com/fmsh/util/log/Level;)V
    //   792: aload_0
    //   793: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   796: ifnull +14 -> 810
    //   799: aload_0
    //   800: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   803: aload 48
    //   805: invokeinterface 934 2 0
    //   810: aload_3
    //   811: sipush 200
    //   814: bipush 48
    //   816: ldc_w 936
    //   819: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   822: invokeinterface 881 2 0
    //   827: astore 24
    //   829: iconst_0
    //   830: istore 25
    //   832: iload 25
    //   834: aload 24
    //   836: invokeinterface 845 1 0
    //   841: if_icmplt +288 -> 1129
    //   844: aload_3
    //   845: ldc_w 938
    //   848: iconst_4
    //   849: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   852: invokeinterface 881 2 0
    //   857: astore 35
    //   859: iconst_0
    //   860: istore 36
    //   862: iload 36
    //   864: aload 35
    //   866: invokeinterface 845 1 0
    //   871: if_icmplt +768 -> 1639
    //   874: aload_3
    //   875: ldc_w 940
    //   878: sipush 206
    //   881: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   884: invokeinterface 881 2 0
    //   889: astore 38
    //   891: aconst_null
    //   892: astore 39
    //   894: bipush 255
    //   896: istore 40
    //   898: iconst_0
    //   899: istore 41
    //   901: goto -699 -> 202
    //   904: aconst_null
    //   905: astore 6
    //   907: bipush 255
    //   909: istore 7
    //   911: iconst_0
    //   912: istore 8
    //   914: aconst_null
    //   915: astore 9
    //   917: aload 4
    //   919: iload 5
    //   921: invokeinterface 815 2 0
    //   926: invokeinterface 821 1 0
    //   931: astore 10
    //   933: aload 10
    //   935: ldc_w 942
    //   938: iconst_5
    //   939: bipush 19
    //   941: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   944: invokeinterface 829 2 0
    //   949: astore 11
    //   951: aload 11
    //   953: ifnull +12 -> 965
    //   956: aload 11
    //   958: invokeinterface 832 1 0
    //   963: astore 6
    //   965: aload 10
    //   967: ldc_w 944
    //   970: sipush 288
    //   973: bipush 106
    //   975: invokestatic 94	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   978: invokeinterface 829 2 0
    //   983: astore 12
    //   985: aload 12
    //   987: ifnull +12 -> 999
    //   990: aload 12
    //   992: invokeinterface 832 1 0
    //   997: astore 9
    //   999: aload 10
    //   1001: ldc_w 946
    //   1004: sipush 266
    //   1007: bipush 103
    //   1009: invokestatic 94	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   1012: invokeinterface 829 2 0
    //   1017: astore 13
    //   1019: aload 13
    //   1021: ifnull +17 -> 1038
    //   1024: aload 13
    //   1026: invokeinterface 832 1 0
    //   1031: bipush 255
    //   1033: invokestatic 838	cn/com/fmsh/util/Util4Java:String2Int	(Ljava/lang/String;I)I
    //   1036: istore 7
    //   1038: aload 10
    //   1040: sipush 340
    //   1043: ldc_w 948
    //   1046: invokestatic 178	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   1049: invokeinterface 829 2 0
    //   1054: astore 14
    //   1056: aload 14
    //   1058: ifnull +16 -> 1074
    //   1061: aload 14
    //   1063: invokeinterface 832 1 0
    //   1068: iconst_0
    //   1069: invokestatic 838	cn/com/fmsh/util/Util4Java:String2Int	(Ljava/lang/String;I)I
    //   1072: istore 8
    //   1074: aload_0
    //   1075: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   1078: aload 6
    //   1080: iload 7
    //   1082: iload 8
    //   1084: aload 9
    //   1086: invokevirtual 952	cn/com/fmsh/tsm/business/core/Configration:addServers	(Ljava/lang/String;IILjava/lang/String;)V
    //   1089: iinc 5 1
    //   1092: goto -677 -> 415
    //   1095: aload_0
    //   1096: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1099: ifnull -484 -> 615
    //   1102: aload_0
    //   1103: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1106: aload_0
    //   1107: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   1110: sipush 294
    //   1113: bipush 67
    //   1115: ldc_w 954
    //   1118: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   1121: invokeinterface 202 3 0
    //   1126: goto -511 -> 615
    //   1129: aload 24
    //   1131: iload 25
    //   1133: invokeinterface 815 2 0
    //   1138: invokeinterface 821 1 0
    //   1143: astore 26
    //   1145: bipush 255
    //   1147: istore 27
    //   1149: aconst_null
    //   1150: astore 28
    //   1152: aconst_null
    //   1153: astore 29
    //   1155: ldc_w 443
    //   1158: astore 30
    //   1160: aload 26
    //   1162: ldc_w 956
    //   1165: iconst_4
    //   1166: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   1169: invokeinterface 829 2 0
    //   1174: astore 31
    //   1176: aload 31
    //   1178: ifnull +15 -> 1193
    //   1181: aload 31
    //   1183: invokeinterface 832 1 0
    //   1188: invokestatic 898	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1191: istore 27
    //   1193: aload 26
    //   1195: ldc_w 958
    //   1198: sipush 252
    //   1201: bipush 118
    //   1203: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   1206: invokeinterface 829 2 0
    //   1211: astore 32
    //   1213: aload 32
    //   1215: ifnull +15 -> 1230
    //   1218: aload 32
    //   1220: invokeinterface 832 1 0
    //   1225: invokestatic 889	cn/com/fmsh/util/FM_Bytes:hexStringToBytes	(Ljava/lang/String;)[B
    //   1228: astore 28
    //   1230: aload 26
    //   1232: ldc_w 960
    //   1235: iconst_4
    //   1236: bipush 104
    //   1238: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   1241: invokeinterface 829 2 0
    //   1246: astore 33
    //   1248: aload 33
    //   1250: ifnull +15 -> 1265
    //   1253: aload 33
    //   1255: invokeinterface 832 1 0
    //   1260: invokestatic 889	cn/com/fmsh/util/FM_Bytes:hexStringToBytes	(Ljava/lang/String;)[B
    //   1263: astore 29
    //   1265: aload 26
    //   1267: bipush 90
    //   1269: bipush 101
    //   1271: ldc_w 962
    //   1274: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   1277: invokeinterface 829 2 0
    //   1282: astore 34
    //   1284: aload 34
    //   1286: ifnull +430 -> 1716
    //   1289: aload 34
    //   1291: invokeinterface 832 1 0
    //   1296: astore 30
    //   1298: goto +418 -> 1716
    //   1301: aload_0
    //   1302: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   1305: aload 30
    //   1307: iload 27
    //   1309: aload 28
    //   1311: aload 29
    //   1313: invokevirtual 966	cn/com/fmsh/tsm/business/core/Configration:addKey	(Ljava/lang/String;I[B[B)V
    //   1316: goto +425 -> 1741
    //   1319: aload_0
    //   1320: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1323: ifnull -708 -> 615
    //   1326: aload_0
    //   1327: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1330: aload_0
    //   1331: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   1334: bipush 118
    //   1336: bipush 28
    //   1338: ldc_w 968
    //   1341: invokestatic 276	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   1344: invokeinterface 202 3 0
    //   1349: goto -734 -> 615
    //   1352: aload_0
    //   1353: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1356: ifnull +45 -> 1401
    //   1359: aload_0
    //   1360: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1363: aload_0
    //   1364: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   1367: new 170	java/lang/StringBuilder
    //   1370: dup
    //   1371: ldc_w 970
    //   1374: sipush 296
    //   1377: invokestatic 252	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   1380: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1383: aload 47
    //   1385: invokeinterface 832 1 0
    //   1390: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1393: invokevirtual 196	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1396: invokeinterface 202 3 0
    //   1401: aload_0
    //   1402: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1405: getstatic 974	cn/com/fmsh/util/log/Level:WARNING	Lcn/com/fmsh/util/log/Level;
    //   1408: invokeinterface 934 2 0
    //   1413: goto -603 -> 810
    //   1416: aload_0
    //   1417: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1420: ifnull -805 -> 615
    //   1423: aload_0
    //   1424: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1427: aload_0
    //   1428: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   1431: ldc_w 976
    //   1434: bipush 46
    //   1436: invokestatic 193	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   1439: invokeinterface 202 3 0
    //   1444: goto -829 -> 615
    //   1447: aload_0
    //   1448: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1451: ifnull -755 -> 696
    //   1454: aload_0
    //   1455: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1458: aload_0
    //   1459: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   1462: sipush 228
    //   1465: bipush 67
    //   1467: ldc_w 978
    //   1470: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   1473: invokeinterface 202 3 0
    //   1478: goto -782 -> 696
    //   1481: aload_0
    //   1482: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1485: ifnull -675 -> 810
    //   1488: aload_0
    //   1489: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1492: aload_0
    //   1493: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   1496: iconst_1
    //   1497: bipush 18
    //   1499: ldc_w 980
    //   1502: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   1505: invokeinterface 202 3 0
    //   1510: goto -700 -> 810
    //   1513: aload_0
    //   1514: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1517: ifnull -821 -> 696
    //   1520: aload_0
    //   1521: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1524: aload_0
    //   1525: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   1528: ldc_w 982
    //   1531: iconst_4
    //   1532: bipush 109
    //   1534: invokestatic 94	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   1537: invokeinterface 202 3 0
    //   1542: goto -846 -> 696
    //   1545: aload_0
    //   1546: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1549: ifnull -739 -> 810
    //   1552: aload_0
    //   1553: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1556: aload_0
    //   1557: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   1560: ldc_w 984
    //   1563: iconst_3
    //   1564: bipush 23
    //   1566: invokestatic 419	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   1569: invokeinterface 202 3 0
    //   1574: goto -764 -> 810
    //   1577: aload_0
    //   1578: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1581: ifnull -885 -> 696
    //   1584: aload_0
    //   1585: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1588: aload_0
    //   1589: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   1592: iconst_3
    //   1593: bipush 61
    //   1595: ldc_w 986
    //   1598: invokestatic 222	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   1601: invokeinterface 202 3 0
    //   1606: goto -910 -> 696
    //   1609: aload_0
    //   1610: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1613: ifnull -803 -> 810
    //   1616: aload_0
    //   1617: getfield 52	cn/com/fmsh/tsm/business/core/CardBusinessBasic:a	Lcn/com/fmsh/util/log/FMLog;
    //   1620: aload_0
    //   1621: getfield 60	cn/com/fmsh/tsm/business/core/CardBusinessBasic:b	Ljava/lang/String;
    //   1624: iconst_3
    //   1625: ldc_w 988
    //   1628: invokestatic 232	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   1631: invokeinterface 202 3 0
    //   1636: goto -826 -> 810
    //   1639: aload 35
    //   1641: iload 36
    //   1643: invokeinterface 815 2 0
    //   1648: invokeinterface 821 1 0
    //   1653: ldc_w 990
    //   1656: iconst_1
    //   1657: invokestatic 252	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   1660: invokeinterface 829 2 0
    //   1665: astore 37
    //   1667: aload 37
    //   1669: ifnull +20 -> 1689
    //   1672: aload_0
    //   1673: getfield 64	cn/com/fmsh/tsm/business/core/CardBusinessBasic:i	Lcn/com/fmsh/tsm/business/core/Configration;
    //   1676: aload 37
    //   1678: invokeinterface 832 1 0
    //   1683: invokestatic 889	cn/com/fmsh/util/FM_Bytes:hexStringToBytes	(Ljava/lang/String;)[B
    //   1686: invokevirtual 993	cn/com/fmsh/tsm/business/core/Configration:addAid	([B)V
    //   1689: iinc 36 1
    //   1692: goto -830 -> 862
    //   1695: iload 40
    //   1697: bipush 255
    //   1699: if_icmpne -1511 -> 188
    //   1702: aload 39
    //   1704: ifnull +6 -> 1710
    //   1707: goto -1519 -> 188
    //   1710: iinc 41 1
    //   1713: goto -1511 -> 202
    //   1716: aload 30
    //   1718: ifnonnull -417 -> 1301
    //   1721: aload 28
    //   1723: ifnull +18 -> 1741
    //   1726: aload 29
    //   1728: ifnull +13 -> 1741
    //   1731: iload 27
    //   1733: bipush 255
    //   1735: if_icmpeq +6 -> 1741
    //   1738: goto -437 -> 1301
    //   1741: iinc 25 1
    //   1744: goto -912 -> 832
    //
    // Exception table:
    //   from	to	target	type
    //   98	211	34	org/xml/sax/SAXException
    //   379	1689	34	org/xml/sax/SAXException
    //   98	211	247	javax/xml/parsers/ParserConfigurationException
    //   379	1689	247	javax/xml/parsers/ParserConfigurationException
    //   98	211	311	java/io/IOException
    //   379	1689	311	java/io/IOException
  }

  public boolean messageConfigLoad(InputStream paramInputStream)
  {
    int i1 = 0;
    if (this.a == null)
      this.a = LogFactory.getInstance().getLog();
    this.h = MessageHandleFactory.getMessageHandler();
    try
    {
      int i2 = this.h.loadDefine(paramInputStream);
      if (i2 != 0)
      {
        if (this.a != null)
          this.a.warn(this.b, Util4Java.toString("万勥奏琔旯ｌ涏恡覶柌噫杠勱轥酒缨斊仢奪货", 286, 39));
        this.h = null;
        return i1;
      }
    }
    catch (FMCommunicationMessageException localFMCommunicationMessageException)
    {
      while (true)
      {
        if (this.a == null)
          continue;
        this.a.error(this.b, FM_CN.subSequence("上劾夊琛斺ｗ淂怶觫枇噮朿勤輮鄏缿出玿弜帵＆", 2) + Util4Java.getExceptionInfo(localFMCommunicationMessageException));
        continue;
        i1 = 1;
      }
    }
  }

  public void registerCommunicationNotify(CommunicationNotify paramCommunicationNotify)
  {
    try
    {
      this.e = paramCommunicationNotify;
      label5: return;
    }
    catch (ct localct)
    {
      break label5;
    }
  }

  public void registerLocalDataHandler(LocalDataHandler paramLocalDataHandler)
  {
    try
    {
      this.p = paramLocalDataHandler;
      label5: return;
    }
    catch (ct localct)
    {
      break label5;
    }
  }

  public void setApduHandler(ApduHandler paramApduHandler)
  {
    try
    {
      this.f = paramApduHandler;
      label5: return;
    }
    catch (ct localct)
    {
      break label5;
    }
  }

  public void setLinkInfo(LinkInfo paramLinkInfo)
  {
    try
    {
      this.n = paramLinkInfo;
      label5: return;
    }
    catch (ct localct)
    {
      break label5;
    }
  }

  public void setMobileInfo(byte[] paramArrayOfByte)
  {
    try
    {
      this.j = paramArrayOfByte;
      label5: return;
    }
    catch (ct localct)
    {
      break label5;
    }
  }

  public void setSecurityCode(byte[] paramArrayOfByte)
  {
    try
    {
      this.l = paramArrayOfByte;
      label5: return;
    }
    catch (ct localct)
    {
      break label5;
    }
  }

  public void setSocketExceptionHandle(SocketExceptionHandler paramSocketExceptionHandler)
  {
    try
    {
      this.g = paramSocketExceptionHandler;
      label5: return;
    }
    catch (ct localct)
    {
      break label5;
    }
  }

  public void setTerminalNumber(byte[] paramArrayOfByte)
  {
    try
    {
      this.k = paramArrayOfByte;
      label5: return;
    }
    catch (ct localct)
    {
      break label5;
    }
  }

  public void throwExceptionAndClose(BusinessException paramBusinessException, boolean paramBoolean, String paramString)
    throws BusinessException
  {
    if ((paramBoolean) && (this.f != null))
      this.f.close();
    disconnect(paramString);
  }

  public void throwExceptionAndClose(String paramString, BusinessException.ErrorMessage paramErrorMessage, boolean paramBoolean)
    throws BusinessException
  {
    if ((paramBoolean) && (this.f != null))
      this.f.close();
    throw new BusinessException(paramString, paramErrorMessage);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.core.CardBusinessBasic
 * JD-Core Version:    0.6.0
 */