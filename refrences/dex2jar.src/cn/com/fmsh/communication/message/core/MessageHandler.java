package cn.com.fmsh.communication.message.core;

import ai;
import ak;
import an;
import cn.com.fmsh.communication.message.IMessage;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import java.util.Map;

public class MessageHandler
  implements IMessageHandler
{
  public Message createMessage(int paramInt)
  {
    try
    {
      localMessage = new Message(this, paramInt);
      return localMessage;
    }
    catch (ak localak)
    {
      while (true)
        Message localMessage = null;
    }
  }

  public Message createMessage(int paramInt, byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    try
    {
      localMessage = new Message(this);
      int i = localMessage.fromPackageBody(paramInt, paramArrayOfByte);
      if (i == 0);
      while (true)
      {
        return localMessage;
        localMessage = null;
      }
    }
    catch (ak localak)
    {
      while (true)
        Message localMessage = null;
    }
  }

  public Message createMessage(byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    try
    {
      localMessage = new Message(this);
      int i = localMessage.fromPackageBody(paramArrayOfByte);
      if (i == 0);
      while (true)
      {
        return localMessage;
        localMessage = null;
      }
    }
    catch (ak localak)
    {
      while (true)
        Message localMessage = null;
    }
  }

  public IMessage createMessageAndRetCode(int paramInt, byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    try
    {
      localMessage = new Message(this);
      int i = localMessage.fromPackageBodyAndRetCode(paramInt, paramArrayOfByte);
      if (i == 0);
      while (true)
      {
        return localMessage;
        localMessage = null;
      }
    }
    catch (ak localak)
    {
      while (true)
        Message localMessage = null;
    }
  }

  public IMessage createMessageAndRetCode(byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    try
    {
      localMessage = new Message(this);
      int i = localMessage.fromPackageBodyAndRetCode(paramArrayOfByte);
      if (i == 0);
      while (true)
      {
        return localMessage;
        localMessage = null;
      }
    }
    catch (ak localak)
    {
      while (true)
        Message localMessage = null;
    }
  }

  public Tag createTag(byte paramByte)
  {
    try
    {
      localTag = new Tag(this, paramByte);
      return localTag;
    }
    catch (ak localak)
    {
      while (true)
        Tag localTag = null;
    }
  }

  public Tag createTag(byte paramByte, byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    try
    {
      localTag = new Tag(this);
      localTag.fromPackageBody(paramByte, paramArrayOfByte);
      return localTag;
    }
    catch (ak localak)
    {
      while (true)
        Tag localTag = null;
    }
  }

  public Tag createTag(byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    try
    {
      localTag = new Tag(this);
      localTag.fromPackageBody(paramArrayOfByte);
      return localTag;
    }
    catch (ak localak)
    {
      while (true)
        Tag localTag = null;
    }
  }

  public ai getMessageDefine(int paramInt)
  {
    try
    {
      localai = (ai)this.d.get(Integer.valueOf(paramInt));
      return localai;
    }
    catch (ak localak)
    {
      while (true)
        ai localai = null;
    }
  }

  public ai getMessageRetDefine(int paramInt)
  {
    try
    {
      localai = (ai)this.e.get(Integer.valueOf(paramInt));
      return localai;
    }
    catch (ak localak)
    {
      while (true)
        ai localai = null;
    }
  }

  public an getTagDefine(byte paramByte)
  {
    try
    {
      localan = (an)this.c.get(Byte.valueOf(paramByte));
      return localan;
    }
    catch (ak localak)
    {
      while (true)
        an localan = null;
    }
  }

  public boolean isLoad()
  {
    return this.f;
  }

  // ERROR //
  public int loadDefine(java.io.InputStream paramInputStream)
    throws FMCommunicationMessageException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +213 -> 214
    //   4: new 63	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   7: dup
    //   8: ldc 149
    //   10: iconst_2
    //   11: bipush 111
    //   13: invokestatic 155	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   16: invokespecial 158	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException:<init>	(Ljava/lang/String;)V
    //   19: athrow
    //   20: astore 18
    //   22: aload_0
    //   23: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   26: ifnull +21 -> 47
    //   29: aload_0
    //   30: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   33: aload_0
    //   34: getfield 42	cn/com/fmsh/communication/message/core/MessageHandler:b	Ljava/lang/String;
    //   37: aload 18
    //   39: invokestatic 164	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   42: invokeinterface 170 3 0
    //   47: new 63	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   50: dup
    //   51: new 172	java/lang/StringBuilder
    //   54: dup
    //   55: iconst_2
    //   56: ldc 174
    //   58: invokestatic 179	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   61: invokespecial 180	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   64: aload 18
    //   66: invokestatic 164	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   69: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokespecial 158	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException:<init>	(Ljava/lang/String;)V
    //   78: athrow
    //   79: astore 17
    //   81: aload_0
    //   82: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   85: ifnull +21 -> 106
    //   88: aload_0
    //   89: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   92: aload_0
    //   93: getfield 42	cn/com/fmsh/communication/message/core/MessageHandler:b	Ljava/lang/String;
    //   96: aload 17
    //   98: invokestatic 164	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   101: invokeinterface 170 3 0
    //   106: new 63	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   109: dup
    //   110: new 172	java/lang/StringBuilder
    //   113: dup
    //   114: sipush 296
    //   117: ldc 189
    //   119: invokestatic 179	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   122: invokespecial 180	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   125: aload 17
    //   127: invokestatic 164	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   130: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: invokespecial 158	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException:<init>	(Ljava/lang/String;)V
    //   139: athrow
    //   140: aload_0
    //   141: getfield 53	cn/com/fmsh/communication/message/core/MessageHandler:e	Ljava/util/Map;
    //   144: aload 34
    //   146: invokevirtual 193	ai:getMessageCode	()I
    //   149: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   152: aload 34
    //   154: invokeinterface 197 3 0
    //   159: pop
    //   160: goto +1121 -> 1281
    //   163: aload 27
    //   165: invokeinterface 202 1 0
    //   170: istore 29
    //   172: iload 28
    //   174: iload 29
    //   176: if_icmplt +993 -> 1169
    //   179: aload_0
    //   180: iconst_1
    //   181: putfield 44	cn/com/fmsh/communication/message/core/MessageHandler:f	Z
    //   184: aload_0
    //   185: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   188: ifnull +24 -> 212
    //   191: aload_0
    //   192: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   195: aload_0
    //   196: getfield 42	cn/com/fmsh/communication/message/core/MessageHandler:b	Ljava/lang/String;
    //   199: iconst_5
    //   200: bipush 77
    //   202: ldc 204
    //   204: invokestatic 210	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   207: invokeinterface 213 3 0
    //   212: iconst_0
    //   213: ireturn
    //   214: invokestatic 219	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   217: astore_2
    //   218: aload_2
    //   219: invokevirtual 223	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   222: aload_1
    //   223: invokevirtual 229	javax/xml/parsers/DocumentBuilder:parse	(Ljava/io/InputStream;)Lorg/w3c/dom/Document;
    //   226: invokeinterface 235 1 0
    //   231: astore 4
    //   233: aload 4
    //   235: iconst_3
    //   236: ldc 237
    //   238: invokestatic 179	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   241: invokeinterface 243 2 0
    //   246: astore 5
    //   248: iconst_0
    //   249: istore 6
    //   251: iload 6
    //   253: aload 5
    //   255: invokeinterface 202 1 0
    //   260: if_icmplt +160 -> 420
    //   263: aload 4
    //   265: ldc 245
    //   267: iconst_5
    //   268: bipush 43
    //   270: invokestatic 250	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   273: invokeinterface 243 2 0
    //   278: astore 27
    //   280: iconst_0
    //   281: istore 28
    //   283: goto -120 -> 163
    //   286: astore_3
    //   287: aload_0
    //   288: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   291: ifnull +20 -> 311
    //   294: aload_0
    //   295: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   298: aload_0
    //   299: getfield 42	cn/com/fmsh/communication/message/core/MessageHandler:b	Ljava/lang/String;
    //   302: aload_3
    //   303: invokestatic 164	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   306: invokeinterface 170 3 0
    //   311: new 63	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   314: dup
    //   315: new 172	java/lang/StringBuilder
    //   318: dup
    //   319: sipush 138
    //   322: bipush 64
    //   324: ldc 252
    //   326: invokestatic 210	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   329: invokespecial 180	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   332: aload_3
    //   333: invokestatic 164	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   336: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   342: invokespecial 158	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException:<init>	(Ljava/lang/String;)V
    //   345: athrow
    //   346: aload_0
    //   347: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   350: ifnull +937 -> 1287
    //   353: aload_0
    //   354: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   357: aload_0
    //   358: getfield 42	cn/com/fmsh/communication/message/core/MessageHandler:b	Ljava/lang/String;
    //   361: ldc 254
    //   363: iconst_3
    //   364: bipush 98
    //   366: invokestatic 155	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   369: invokeinterface 257 3 0
    //   374: goto +913 -> 1287
    //   377: iload 36
    //   379: aload 35
    //   381: invokeinterface 202 1 0
    //   386: if_icmplt +609 -> 995
    //   389: aload 34
    //   391: invokevirtual 260	ai:getRetCode	()Ljava/lang/String;
    //   394: ifnonnull -254 -> 140
    //   397: aload_0
    //   398: getfield 51	cn/com/fmsh/communication/message/core/MessageHandler:d	Ljava/util/Map;
    //   401: aload 34
    //   403: invokevirtual 193	ai:getMessageCode	()I
    //   406: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   409: aload 34
    //   411: invokeinterface 197 3 0
    //   416: pop
    //   417: goto +864 -> 1281
    //   420: new 137	an
    //   423: dup
    //   424: invokespecial 261	an:<init>	()V
    //   427: astore 7
    //   429: aload 5
    //   431: iload 6
    //   433: invokeinterface 265 2 0
    //   438: astore 8
    //   440: aload 8
    //   442: invokeinterface 271 1 0
    //   447: astore 9
    //   449: aload 9
    //   451: iconst_3
    //   452: bipush 89
    //   454: ldc_w 273
    //   457: invokestatic 278	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   460: invokeinterface 284 2 0
    //   465: astore 10
    //   467: aload 10
    //   469: ifnull +21 -> 490
    //   472: aload 7
    //   474: aload 10
    //   476: invokeinterface 287 1 0
    //   481: bipush 16
    //   483: invokestatic 291	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   486: i2b
    //   487: invokevirtual 295	an:setId	(B)V
    //   490: aload 9
    //   492: iconst_2
    //   493: bipush 69
    //   495: ldc_w 297
    //   498: invokestatic 210	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   501: invokeinterface 284 2 0
    //   506: astore 11
    //   508: aload 11
    //   510: ifnull +18 -> 528
    //   513: aload 7
    //   515: aload 11
    //   517: invokeinterface 287 1 0
    //   522: invokestatic 300	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   525: invokevirtual 304	an:setLength	(I)V
    //   528: aload 9
    //   530: ldc_w 306
    //   533: bipush 6
    //   535: invokestatic 312	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   538: invokeinterface 284 2 0
    //   543: astore 12
    //   545: aload 12
    //   547: ifnull +18 -> 565
    //   550: aload 7
    //   552: aload 12
    //   554: invokeinterface 287 1 0
    //   559: invokestatic 317	cn/com/fmsh/communication/message/enumerate/ETagType:valueOf	(Ljava/lang/String;)Lcn/com/fmsh/communication/message/enumerate/ETagType;
    //   562: invokevirtual 321	an:setType	(Lcn/com/fmsh/communication/message/enumerate/ETagType;)V
    //   565: aload 9
    //   567: ldc_w 323
    //   570: iconst_5
    //   571: bipush 37
    //   573: invokestatic 250	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   576: invokeinterface 284 2 0
    //   581: astore 13
    //   583: aload 13
    //   585: ifnull +15 -> 600
    //   588: aload 7
    //   590: aload 13
    //   592: invokeinterface 287 1 0
    //   597: invokevirtual 326	an:setDesc	(Ljava/lang/String;)V
    //   600: aload 8
    //   602: invokeinterface 330 1 0
    //   607: astore 14
    //   609: iconst_0
    //   610: istore 15
    //   612: iload 15
    //   614: aload 14
    //   616: invokeinterface 202 1 0
    //   621: if_icmplt +85 -> 706
    //   624: aload_0
    //   625: getfield 49	cn/com/fmsh/communication/message/core/MessageHandler:c	Ljava/util/Map;
    //   628: aload 7
    //   630: invokevirtual 334	an:getId	()B
    //   633: invokestatic 135	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   636: aload 7
    //   638: invokeinterface 197 3 0
    //   643: pop
    //   644: iinc 6 1
    //   647: goto -396 -> 251
    //   650: aload 39
    //   652: iconst_1
    //   653: invokevirtual 339	cn/com/fmsh/communication/message/core/MessageTagDefine:setExist	(I)V
    //   656: aload 38
    //   658: ldc_w 341
    //   661: sipush 134
    //   664: bipush 91
    //   666: invokestatic 250	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   669: invokeinterface 284 2 0
    //   674: astore 43
    //   676: aload 43
    //   678: ifnull +18 -> 696
    //   681: aload 39
    //   683: aload 43
    //   685: invokeinterface 287 1 0
    //   690: invokestatic 300	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   693: invokevirtual 344	cn/com/fmsh/communication/message/core/MessageTagDefine:setOrder	(I)V
    //   696: aload 34
    //   698: aload 39
    //   700: invokevirtual 348	ai:addMessageData	(Lcn/com/fmsh/communication/message/core/MessageTagDefine;)V
    //   703: goto +584 -> 1287
    //   706: aload 14
    //   708: iload 15
    //   710: invokeinterface 265 2 0
    //   715: astore 16
    //   717: aload 16
    //   719: ifnull +246 -> 965
    //   722: sipush 192
    //   725: bipush 80
    //   727: ldc_w 350
    //   730: invokestatic 210	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   733: aload 16
    //   735: invokeinterface 353 1 0
    //   740: invokevirtual 359	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   743: ifeq +550 -> 1293
    //   746: aload 16
    //   748: invokeinterface 271 1 0
    //   753: astore 19
    //   755: new 361	ap
    //   758: dup
    //   759: invokespecial 362	ap:<init>	()V
    //   762: astore 20
    //   764: aload 19
    //   766: ldc_w 364
    //   769: iconst_3
    //   770: bipush 89
    //   772: invokestatic 366	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   775: invokeinterface 284 2 0
    //   780: astore 21
    //   782: aload 21
    //   784: ifnull +21 -> 805
    //   787: aload 20
    //   789: aload 21
    //   791: invokeinterface 287 1 0
    //   796: bipush 16
    //   798: invokestatic 291	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   801: i2b
    //   802: invokevirtual 369	ap:setTag	(B)V
    //   805: aload 19
    //   807: iconst_3
    //   808: bipush 18
    //   810: ldc_w 371
    //   813: invokestatic 210	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   816: invokeinterface 284 2 0
    //   821: astore 22
    //   823: aload 22
    //   825: ifnull +15 -> 840
    //   828: aload 20
    //   830: aload 22
    //   832: invokeinterface 287 1 0
    //   837: invokevirtual 372	ap:setDesc	(Ljava/lang/String;)V
    //   840: aload 19
    //   842: ldc_w 374
    //   845: bipush 76
    //   847: bipush 121
    //   849: invokestatic 155	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   852: invokeinterface 284 2 0
    //   857: astore 23
    //   859: aload 23
    //   861: ifnull +18 -> 879
    //   864: aload 20
    //   866: aload 23
    //   868: invokeinterface 287 1 0
    //   873: invokestatic 300	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   876: invokevirtual 377	ap:setMultiple	(I)V
    //   879: aload 19
    //   881: ldc_w 379
    //   884: sipush 210
    //   887: bipush 72
    //   889: invokestatic 366	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   892: invokeinterface 284 2 0
    //   897: astore 24
    //   899: aload 24
    //   901: ifnull +18 -> 919
    //   904: aload 20
    //   906: aload 24
    //   908: invokeinterface 287 1 0
    //   913: invokestatic 300	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   916: invokevirtual 380	ap:setExist	(I)V
    //   919: aload 19
    //   921: ldc_w 382
    //   924: iconst_2
    //   925: invokestatic 312	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   928: invokeinterface 284 2 0
    //   933: astore 25
    //   935: aload 25
    //   937: ifnull +18 -> 955
    //   940: aload 20
    //   942: aload 25
    //   944: invokeinterface 287 1 0
    //   949: invokestatic 300	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   952: invokevirtual 383	ap:setOrder	(I)V
    //   955: aload 7
    //   957: aload 20
    //   959: invokevirtual 387	an:addTagItem	(Lap;)V
    //   962: goto +331 -> 1293
    //   965: aload_0
    //   966: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   969: ifnull +324 -> 1293
    //   972: aload_0
    //   973: getfield 34	cn/com/fmsh/communication/message/core/MessageHandler:a	Lcn/com/fmsh/util/log/FMLog;
    //   976: aload_0
    //   977: getfield 42	cn/com/fmsh/communication/message/core/MessageHandler:b	Ljava/lang/String;
    //   980: ldc_w 389
    //   983: iconst_2
    //   984: invokestatic 312	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   987: invokeinterface 257 3 0
    //   992: goto +301 -> 1293
    //   995: aload 35
    //   997: iload 36
    //   999: invokeinterface 265 2 0
    //   1004: astore 37
    //   1006: aload 37
    //   1008: ifnull -662 -> 346
    //   1011: ldc_w 391
    //   1014: bipush 40
    //   1016: invokestatic 396	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   1019: aload 37
    //   1021: invokeinterface 353 1 0
    //   1026: invokevirtual 359	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1029: ifeq +258 -> 1287
    //   1032: aload 37
    //   1034: invokeinterface 271 1 0
    //   1039: astore 38
    //   1041: new 336	cn/com/fmsh/communication/message/core/MessageTagDefine
    //   1044: dup
    //   1045: invokespecial 397	cn/com/fmsh/communication/message/core/MessageTagDefine:<init>	()V
    //   1048: astore 39
    //   1050: aload 38
    //   1052: ldc_w 399
    //   1055: iconst_5
    //   1056: bipush 31
    //   1058: invokestatic 250	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   1061: invokeinterface 284 2 0
    //   1066: astore 40
    //   1068: aload 40
    //   1070: ifnull +21 -> 1091
    //   1073: aload 39
    //   1075: aload 40
    //   1077: invokeinterface 287 1 0
    //   1082: bipush 16
    //   1084: invokestatic 291	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   1087: i2b
    //   1088: invokevirtual 400	cn/com/fmsh/communication/message/core/MessageTagDefine:setTag	(B)V
    //   1091: aload 38
    //   1093: bipush 6
    //   1095: ldc_w 402
    //   1098: invokestatic 179	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   1101: invokeinterface 284 2 0
    //   1106: astore 41
    //   1108: aload 41
    //   1110: ifnull +18 -> 1128
    //   1113: aload 39
    //   1115: aload 41
    //   1117: invokeinterface 287 1 0
    //   1122: invokestatic 300	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1125: invokevirtual 403	cn/com/fmsh/communication/message/core/MessageTagDefine:setMultiple	(I)V
    //   1128: aload 38
    //   1130: ldc_w 405
    //   1133: sipush 272
    //   1136: invokestatic 396	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   1139: invokeinterface 284 2 0
    //   1144: astore 42
    //   1146: aload 42
    //   1148: ifnull -498 -> 650
    //   1151: aload 39
    //   1153: aload 42
    //   1155: invokeinterface 287 1 0
    //   1160: invokestatic 300	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1163: invokevirtual 339	cn/com/fmsh/communication/message/core/MessageTagDefine:setExist	(I)V
    //   1166: goto -510 -> 656
    //   1169: aload 27
    //   1171: iload 28
    //   1173: invokeinterface 265 2 0
    //   1178: astore 30
    //   1180: aload 30
    //   1182: invokeinterface 271 1 0
    //   1187: astore 31
    //   1189: aload 31
    //   1191: ldc_w 407
    //   1194: sipush 192
    //   1197: invokestatic 312	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   1200: invokeinterface 284 2 0
    //   1205: invokeinterface 287 1 0
    //   1210: astore 32
    //   1212: aload 31
    //   1214: ldc_w 409
    //   1217: iconst_2
    //   1218: bipush 25
    //   1220: invokestatic 250	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   1223: invokeinterface 284 2 0
    //   1228: astore 33
    //   1230: new 127	ai
    //   1233: dup
    //   1234: invokespecial 410	ai:<init>	()V
    //   1237: astore 34
    //   1239: aload 34
    //   1241: aload 32
    //   1243: invokestatic 300	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1246: invokevirtual 413	ai:setMessageCode	(I)V
    //   1249: aload 33
    //   1251: ifnull +15 -> 1266
    //   1254: aload 34
    //   1256: aload 33
    //   1258: invokeinterface 287 1 0
    //   1263: invokevirtual 416	ai:setRetCode	(Ljava/lang/String;)V
    //   1266: aload 30
    //   1268: invokeinterface 330 1 0
    //   1273: astore 35
    //   1275: iconst_0
    //   1276: istore 36
    //   1278: goto -901 -> 377
    //   1281: iinc 28 1
    //   1284: goto -1121 -> 163
    //   1287: iinc 36 1
    //   1290: goto -913 -> 377
    //   1293: iinc 15 1
    //   1296: goto -684 -> 612
    //
    // Exception table:
    //   from	to	target	type
    //   140	172	20	java/io/IOException
    //   218	280	20	java/io/IOException
    //   346	1275	20	java/io/IOException
    //   140	172	79	org/xml/sax/SAXException
    //   218	280	79	org/xml/sax/SAXException
    //   346	1275	79	org/xml/sax/SAXException
    //   140	172	286	javax/xml/parsers/ParserConfigurationException
    //   218	280	286	javax/xml/parsers/ParserConfigurationException
    //   346	1275	286	javax/xml/parsers/ParserConfigurationException
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.core.MessageHandler
 * JD-Core Version:    0.6.0
 */