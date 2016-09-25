package cn.com.fmsh.tsm.business.card.core;

import ck;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.tsm.business.bean.CardAppRecord;
import cn.com.fmsh.tsm.business.card.CardTools;
import cn.com.fmsh.tsm.business.card.base.CardManager;
import cn.com.fmsh.tsm.business.enums.EnumTradeType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ShTourAppManager
  implements CardManager
{
  public FMLog a = LogFactory.getInstance().getLog();

  public ShTourAppManager()
  {
    byte[] arrayOfByte = new byte[9];
    arrayOfByte[0] = -96;
    arrayOfByte[4] = 3;
    arrayOfByte[5] = -122;
    arrayOfByte[6] = -104;
    arrayOfByte[7] = 7;
    arrayOfByte[8] = 1;
    this.c = arrayOfByte;
    this.d = 10;
    this.e = 23;
    this.f = 0;
  }

  public byte[] getAid()
  {
    return this.c;
  }

  public byte[] getAppNo()
    throws BusinessException
  {
    byte[] arrayOfByte1;
    try
    {
      arrayOfByte1 = new byte[8];
      if (this.a != null)
        this.a.info(this.b, CRCUtil.valueOf(238, "菳厃乌浠旍渡卫廏畤廒剙叨0/<-"));
      String str = FM_Utils.copyValueOf(4, 32, "莸叙久洸旊渷匮廛甧庀剘厸/");
      if (this.g == null)
      {
        if (this.a != null)
          this.a.warn(this.b, str + FM_Bytes.startsWith("＊Z`!/奋琂噱临稹", 5, 21));
        throw new BusinessException(str + FM_Bytes.startsWith("＊Fxm夏琊噥临穵", 5, 1), BusinessException.ErrorMessage.local_business_apdu_handler_null);
      }
      byte[] arrayOfByte5;
      byte[] arrayOfByte3;
      while (true)
      {
        byte[] arrayOfByte4 = new byte[5];
        arrayOfByte4[1] = -80;
        arrayOfByte4[2] = -107;
        arrayOfByte5 = a(arrayOfByte4);
        if (!FM_Bytes.isEnd9000(arrayOfByte5))
          break;
        if (arrayOfByte5.length < 20)
          break label435;
        System.arraycopy(arrayOfByte5, 12, arrayOfByte1, 0, arrayOfByte1.length);
        break label433;
        byte[] arrayOfByte2 = new byte[7];
        arrayOfByte2[1] = -92;
        arrayOfByte2[4] = 2;
        arrayOfByte2[5] = 63;
        arrayOfByte2[6] = 1;
        arrayOfByte3 = a(arrayOfByte2);
        if (!FM_Bytes.isEnd9000(arrayOfByte3))
          break label341;
        if (arrayOfByte3.length < 42)
          continue;
        System.arraycopy(arrayOfByte3, 34, arrayOfByte1, 0, arrayOfByte1.length);
        break label433;
      }
      if (this.a != null)
        this.a.warn(this.b, str + Util4Java.toString("５遟抺!8旍亱哉底弜幣b", 186, 29) + FM_Bytes.bytesToHexString(arrayOfByte5));
      throw new BusinessException(str + FM_Utils.copyValueOf(5, 5, "＜逜拳.1斎仸哞庌弟幺"), BusinessException.ErrorMessage.local_message_apdu_execute_exception);
      label341: if (this.a != null)
        this.a.warn(this.b, str + FM_Utils.copyValueOf(5, 68, "＜遝拱\035D\002扯蠀弒幬\"") + FM_Bytes.bytesToHexString(arrayOfByte3));
      throw new BusinessException(str + Util4Java.toString("．遘择\016ZK戻衇彘幱", 3, 79), BusinessException.ErrorMessage.local_message_apdu_execute_exception);
    }
    catch (ck localck)
    {
      arrayOfByte1 = null;
    }
    while (true)
    {
      label433: return arrayOfByte1;
      label435: arrayOfByte1 = null;
    }
  }

  public CardAppRecord getAppRecord4bytes(byte[] paramArrayOfByte, Map<String, EnumTradeType> paramMap)
  {
    CardAppRecord localCardAppRecord = new CardAppRecord();
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = paramArrayOfByte[0];
    arrayOfByte1[1] = paramArrayOfByte[1];
    localCardAppRecord.setTradeNo(FM_Bytes.bytesToInt(arrayOfByte1));
    byte[] arrayOfByte2 = new byte[4];
    arrayOfByte2[0] = paramArrayOfByte[16];
    arrayOfByte2[1] = paramArrayOfByte[17];
    arrayOfByte2[2] = paramArrayOfByte[18];
    arrayOfByte2[3] = paramArrayOfByte[19];
    localCardAppRecord.setTradeDate(FM_Bytes.bytesToHexString(arrayOfByte2));
    byte[] arrayOfByte3 = new byte[3];
    arrayOfByte3[0] = paramArrayOfByte[20];
    arrayOfByte3[1] = paramArrayOfByte[21];
    arrayOfByte3[2] = paramArrayOfByte[22];
    localCardAppRecord.setTradeTime(FM_Bytes.bytesToHexString(arrayOfByte3));
    EnumTradeType localEnumTradeType = (EnumTradeType)paramMap.get(FM_Bytes.bytesToHexString(arrayOfByte2) + FM_Bytes.bytesToHexString(arrayOfByte3));
    byte[] arrayOfByte4 = new byte[4];
    arrayOfByte4[0] = paramArrayOfByte[5];
    arrayOfByte4[1] = paramArrayOfByte[6];
    arrayOfByte4[2] = paramArrayOfByte[7];
    arrayOfByte4[3] = paramArrayOfByte[8];
    localCardAppRecord.setAmount(Integer.parseInt(FM_Bytes.bytesToHexString(arrayOfByte4), 16));
    byte[] arrayOfByte5 = new byte[3];
    arrayOfByte5[0] = paramArrayOfByte[2];
    arrayOfByte5[1] = paramArrayOfByte[3];
    arrayOfByte5[2] = paramArrayOfByte[4];
    localCardAppRecord.setBalance(FM_Bytes.bytesToInt(arrayOfByte5));
    localCardAppRecord.setOriTradeType(paramArrayOfByte[12]);
    localCardAppRecord.setOriTradeType(paramArrayOfByte[9]);
    if (localEnumTradeType != null)
      localCardAppRecord.setTradeType(localEnumTradeType);
    while (true)
    {
      byte[] arrayOfByte6 = new byte[6];
      arrayOfByte6[0] = paramArrayOfByte[10];
      arrayOfByte6[1] = paramArrayOfByte[11];
      arrayOfByte6[2] = paramArrayOfByte[12];
      arrayOfByte6[3] = paramArrayOfByte[13];
      arrayOfByte6[4] = paramArrayOfByte[14];
      arrayOfByte6[5] = paramArrayOfByte[15];
      localCardAppRecord.setTradeDevice(FM_Bytes.bytesToHexString(arrayOfByte6));
      return localCardAppRecord;
      localCardAppRecord.setTradeType(a(paramArrayOfByte[12], paramArrayOfByte[9]));
    }
  }

  public String getDateTime4File07(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = paramArrayOfByte[0];
    arrayOfByte1[1] = paramArrayOfByte[1];
    int i = FM_Bytes.bytesToInt(arrayOfByte1) >> 4;
    localStringBuffer.append(String.valueOf(i));
    System.out.println(FM_Int.lastIndexOf(5, "!<;)\n<2*e;") + i);
    String str1 = String.valueOf(0xF & paramArrayOfByte[1]);
    if (str1.length() == 1)
      str1 = "0" + str1;
    localStringBuffer.append(str1);
    String str2 = String.valueOf((0xF8 & paramArrayOfByte[2]) >> 3);
    if (str2.length() == 1)
      str2 = "0" + str2;
    localStringBuffer.append(str2);
    byte[] arrayOfByte2 = new byte[2];
    arrayOfByte2[0] = (byte)(0x7 & paramArrayOfByte[2]);
    arrayOfByte2[1] = (byte)(0xC0 & paramArrayOfByte[3]);
    String str3 = String.valueOf(FM_Bytes.bytesToInt(arrayOfByte2) >> 6);
    if (str3.length() == 1)
      str3 = "0" + str3;
    localStringBuffer.append(str3);
    String str4 = String.valueOf((byte)(0x3F & paramArrayOfByte[3]));
    if (str4.length() == 1)
      str4 = "0" + str4;
    localStringBuffer.append(str4);
    String str5 = String.valueOf((0xFC & paramArrayOfByte[4]) >> 2);
    if (str5.length() == 1)
      str5 = "0" + str5;
    localStringBuffer.append(str5);
    return localStringBuffer.toString();
  }

  public String getFaceID()
    throws BusinessException
  {
    if (this.a == null)
      this.a = LogFactory.getInstance().getLog();
    if (this.a != null)
      this.a.info(this.b, BCCUtil.endsWith("莳历世洿旑游匭卹霦叧|f:n", 5, 76));
    String str = Util4Java.toString("莴原丅洢旞湹卦匬靱厮", 4, 70);
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, str + FM_CN.subSequence("旮ｋ\02750v外琇噸严稴", 42));
      throw new BusinessException(str + FM_Int.lastIndexOf(254, "斧～\022$1#奓瑞嘱习稡"), BusinessException.ErrorMessage.local_business_apdu_handler_null);
    }
    return CardTools.getFaceID4UID(Arrays.copyOfRange(getAppNo(), 4, 8));
  }

  public String getMOC()
    throws BusinessException
  {
    byte[] arrayOfByte1 = new byte[5];
    arrayOfByte1[0] = -128;
    arrayOfByte1[1] = -54;
    arrayOfByte1[4] = 9;
    byte[] arrayOfByte2 = a(arrayOfByte1);
    if (!FM_Bytes.isEnd9000(arrayOfByte2))
    {
      if (this.a != null)
        this.a.warn(this.b, CRCUtil.valueOf(68, "莭叝体廷邶诫讁硐斴？EEB\002捏亽辞囅统极夿赺｜373{kebｂ") + FM_Bytes.bytesToHexString(arrayOfByte2));
      throw new BusinessException(FM_Exception.getChars(5, 92, "菩厌圞孊勑肷锇官狨怛无～\017ZB\027挙仾奒瑔夿贯"), BusinessException.ErrorMessage.local_business_execute_fail);
    }
    return FM_Bytes.bytesToHexString(Arrays.copyOf(arrayOfByte2, -2 + arrayOfByte2.length));
  }

  // ERROR //
  public cn.com.fmsh.tsm.business.enums.EnumCardAppStatus getStatus()
    throws BusinessException
  {
    // Byte code:
    //   0: getstatic 365	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_INSTALL	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   3: astore_1
    //   4: aload_0
    //   5: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   8: ifnull +25 -> 33
    //   11: aload_0
    //   12: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   15: aload_0
    //   16: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   19: iconst_2
    //   20: bipush 76
    //   22: ldc_w 367
    //   25: invokestatic 176	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   28: invokeinterface 189 3 0
    //   33: iconst_1
    //   34: ldc_w 369
    //   37: invokestatic 134	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   40: astore_2
    //   41: aload_0
    //   42: getfield 104	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   45: ifnonnull +759 -> 804
    //   48: aload_0
    //   49: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   52: ifnull +44 -> 96
    //   55: aload_0
    //   56: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   59: aload_0
    //   60: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   63: new 145	java/lang/StringBuilder
    //   66: dup
    //   67: aload_2
    //   68: invokestatic 201	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   71: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   74: ldc_w 371
    //   77: sipush 158
    //   80: bipush 10
    //   82: invokestatic 152	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   85: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   91: invokeinterface 123 3 0
    //   96: new 100	cn/com/fmsh/tsm/business/exception/BusinessException
    //   99: dup
    //   100: new 145	java/lang/StringBuilder
    //   103: dup
    //   104: aload_2
    //   105: invokestatic 201	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   108: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   111: iconst_5
    //   112: bipush 100
    //   114: ldc_w 373
    //   117: invokestatic 176	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   120: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   126: getstatic 208	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_null	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   129: invokespecial 143	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   132: athrow
    //   133: astore 29
    //   135: aload_0
    //   136: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   139: ifnull +52 -> 191
    //   142: aload_0
    //   143: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   146: aload_0
    //   147: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   150: new 145	java/lang/StringBuilder
    //   153: dup
    //   154: aload_2
    //   155: invokestatic 201	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   158: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   161: ldc_w 375
    //   164: sipush 198
    //   167: bipush 37
    //   169: invokestatic 224	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   172: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: aload 29
    //   177: invokestatic 161	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   180: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: invokeinterface 378 3 0
    //   191: aload_1
    //   192: areturn
    //   193: astore 28
    //   195: aload_0
    //   196: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   199: ifnull +50 -> 249
    //   202: aload_0
    //   203: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   206: aload_0
    //   207: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   210: new 145	java/lang/StringBuilder
    //   213: dup
    //   214: aload_2
    //   215: invokestatic 201	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   218: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   221: ldc_w 380
    //   224: iconst_4
    //   225: bipush 10
    //   227: invokestatic 152	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   230: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: aload 28
    //   235: invokestatic 161	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   238: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   241: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   244: invokeinterface 378 3 0
    //   249: iconst_1
    //   250: istore 11
    //   252: iload 11
    //   254: ifeq +311 -> 565
    //   257: iconst_1
    //   258: istore 13
    //   260: iload 13
    //   262: ifeq -71 -> 191
    //   265: bipush 16
    //   267: newarray byte
    //   269: astore 14
    //   271: aload 14
    //   273: iconst_0
    //   274: bipush 128
    //   276: bastore
    //   277: aload 14
    //   279: iconst_1
    //   280: bipush 80
    //   282: bastore
    //   283: aload 14
    //   285: iconst_3
    //   286: iconst_2
    //   287: bastore
    //   288: aload 14
    //   290: iconst_4
    //   291: bipush 11
    //   293: bastore
    //   294: aload 14
    //   296: iconst_5
    //   297: iconst_1
    //   298: bastore
    //   299: aload 14
    //   301: bipush 8
    //   303: bipush 7
    //   305: bastore
    //   306: aload 14
    //   308: bipush 9
    //   310: bipush 208
    //   312: bastore
    //   313: aload_0
    //   314: getfield 104	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   317: aload 14
    //   319: invokeinterface 109 2 0
    //   324: astore 16
    //   326: aload 16
    //   328: invokestatic 214	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   331: ifeq -140 -> 191
    //   334: sipush 228
    //   337: ldc_w 382
    //   340: invokestatic 134	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   343: invokestatic 386	cn/com/fmsh/util/FM_Bytes:hexStringToBytes	(Ljava/lang/String;)[B
    //   346: astore 17
    //   348: aload_0
    //   349: getfield 104	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   352: aload 17
    //   354: invokeinterface 109 2 0
    //   359: astore 19
    //   361: iconst_2
    //   362: newarray byte
    //   364: astore 20
    //   366: aload 20
    //   368: iconst_0
    //   369: ldc_w 387
    //   372: bastore
    //   373: aload 20
    //   375: iconst_1
    //   376: ldc_w 388
    //   379: bastore
    //   380: aload 19
    //   382: aload 20
    //   384: invokestatic 392	cn/com/fmsh/util/FM_Bytes:isEnd	([B[B)Z
    //   387: ifeq +348 -> 735
    //   390: getstatic 395	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_ACTIVATE	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   393: astore_1
    //   394: goto -203 -> 191
    //   397: astore 15
    //   399: aload_0
    //   400: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   403: ifnull -212 -> 191
    //   406: aload_0
    //   407: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   410: aload_0
    //   411: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   414: new 145	java/lang/StringBuilder
    //   417: dup
    //   418: aload_2
    //   419: invokestatic 201	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   422: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   425: ldc_w 397
    //   428: iconst_3
    //   429: bipush 87
    //   431: invokestatic 224	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   434: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: aload 15
    //   439: invokestatic 161	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   442: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   448: invokeinterface 378 3 0
    //   453: goto -262 -> 191
    //   456: bipush 32
    //   458: newarray byte
    //   460: astore 6
    //   462: aload 6
    //   464: bipush 30
    //   466: bipush 144
    //   468: bastore
    //   469: aload 6
    //   471: aload 5
    //   473: invokestatic 400	java/util/Arrays:equals	([B[B)Z
    //   476: ifne -285 -> 191
    //   479: getstatic 403	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_PERSONALIZED	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   482: astore_1
    //   483: new 405	java/util/Random
    //   486: dup
    //   487: invokespecial 406	java/util/Random:<init>	()V
    //   490: astore 7
    //   492: bipush 8
    //   494: newarray byte
    //   496: astore 8
    //   498: aload 7
    //   500: aload 8
    //   502: invokevirtual 410	java/util/Random:nextBytes	([B)V
    //   505: iconst_5
    //   506: newarray byte
    //   508: astore 9
    //   510: aload 9
    //   512: iconst_1
    //   513: bipush 10
    //   515: bastore
    //   516: aload 9
    //   518: iconst_2
    //   519: bipush 131
    //   521: bastore
    //   522: aload 9
    //   524: iconst_3
    //   525: iconst_4
    //   526: bastore
    //   527: aload 9
    //   529: iconst_4
    //   530: bipush 8
    //   532: bastore
    //   533: aload 9
    //   535: aload 8
    //   537: invokestatic 414	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
    //   540: astore 10
    //   542: iconst_0
    //   543: istore 11
    //   545: aload_0
    //   546: getfield 104	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   549: aload 10
    //   551: invokeinterface 109 2 0
    //   556: astore 12
    //   558: aload 12
    //   560: astore 5
    //   562: goto -310 -> 252
    //   565: aload 5
    //   567: invokestatic 214	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   570: ifne +152 -> 722
    //   573: iconst_1
    //   574: istore 13
    //   576: goto -316 -> 260
    //   579: aload 5
    //   581: iconst_0
    //   582: baload
    //   583: ifeq +14 -> 597
    //   586: aload 25
    //   588: invokevirtual 420	java/util/Date:getTime	()J
    //   591: lload 22
    //   593: lcmp
    //   594: ifge +251 -> 845
    //   597: iconst_0
    //   598: istore 13
    //   600: goto -340 -> 260
    //   603: aload 5
    //   605: iconst_1
    //   606: iconst_5
    //   607: invokestatic 341	java/util/Arrays:copyOfRange	([BII)[B
    //   610: invokestatic 228	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   613: astore 21
    //   615: invokestatic 423	java/lang/System:currentTimeMillis	()J
    //   618: lstore 22
    //   620: new 425	java/text/SimpleDateFormat
    //   623: dup
    //   624: ldc_w 427
    //   627: iconst_3
    //   628: bipush 86
    //   630: invokestatic 117	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   633: invokespecial 428	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   636: astore 24
    //   638: aconst_null
    //   639: astore 25
    //   641: aload 24
    //   643: aload 21
    //   645: invokevirtual 432	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   648: astore 27
    //   650: aload 27
    //   652: astore 25
    //   654: aload 25
    //   656: ifnonnull -77 -> 579
    //   659: iconst_1
    //   660: istore 13
    //   662: goto -402 -> 260
    //   665: astore 18
    //   667: aload_0
    //   668: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   671: ifnull -480 -> 191
    //   674: aload_0
    //   675: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   678: aload_0
    //   679: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   682: new 145	java/lang/StringBuilder
    //   685: dup
    //   686: aload_2
    //   687: invokestatic 201	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   690: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   693: iconst_5
    //   694: ldc_w 434
    //   697: invokestatic 186	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   700: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   703: aload 18
    //   705: invokestatic 161	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   708: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   711: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   714: invokeinterface 378 3 0
    //   719: goto -528 -> 191
    //   722: aload 5
    //   724: arraylength
    //   725: iconst_5
    //   726: if_icmpge -123 -> 603
    //   729: iconst_1
    //   730: istore 13
    //   732: goto -472 -> 260
    //   735: aload 19
    //   737: invokestatic 214	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   740: ifeq -549 -> 191
    //   743: getstatic 395	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_ACTIVATE	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   746: astore_1
    //   747: goto -556 -> 191
    //   750: astore 26
    //   752: aload_0
    //   753: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   756: ifnull -102 -> 654
    //   759: aload_0
    //   760: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   763: aload_0
    //   764: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   767: new 145	java/lang/StringBuilder
    //   770: dup
    //   771: aload_2
    //   772: invokestatic 201	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   775: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   778: ldc_w 436
    //   781: iconst_4
    //   782: invokestatic 441	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   785: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   788: aload 21
    //   790: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   793: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   796: invokeinterface 123 3 0
    //   801: goto -147 -> 654
    //   804: iconst_5
    //   805: newarray byte
    //   807: astore_3
    //   808: aload_3
    //   809: iconst_1
    //   810: bipush 176
    //   812: bastore
    //   813: aload_3
    //   814: iconst_2
    //   815: bipush 149
    //   817: bastore
    //   818: aload_0
    //   819: getfield 104	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   822: aload_3
    //   823: invokeinterface 109 2 0
    //   828: astore 4
    //   830: aload 4
    //   832: astore 5
    //   834: aload 5
    //   836: invokestatic 214	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   839: ifne -383 -> 456
    //   842: goto -651 -> 191
    //   845: getstatic 395	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_ACTIVATE	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   848: astore_1
    //   849: goto -658 -> 191
    //
    // Exception table:
    //   from	to	target	type
    //   818	830	133	cn/com/fmsh/script/exception/FMScriptHandleException
    //   545	558	193	cn/com/fmsh/script/exception/FMScriptHandleException
    //   313	326	397	cn/com/fmsh/script/exception/FMScriptHandleException
    //   348	361	665	cn/com/fmsh/script/exception/FMScriptHandleException
    //   641	650	750	java/text/ParseException
  }

  public String getTime4Validity()
    throws BusinessException
  {
    byte[] arrayOfByte1 = new byte[7];
    arrayOfByte1[1] = -92;
    arrayOfByte1[4] = 2;
    arrayOfByte1[5] = 63;
    arrayOfByte1[6] = 1;
    byte[] arrayOfByte2 = a(arrayOfByte1);
    if (!FM_Bytes.isEnd9000(arrayOfByte2))
    {
      if (this.a != null)
        this.a.error(this.b, CRCUtil.valueOf(172, "莵叅卥也廒畿杁救朕旭＀達抧廋畸皯彇夲贱？") + FM_Bytes.bytesToHexString(arrayOfByte2));
      throw new BusinessException(Util4Java.toString("菰叆卸丈廟畼朔敎朐斮ｍ遃拺庈甭皠异捇仭外瑝奵赨", 200, 105), BusinessException.ErrorMessage.local_business_execute_fail);
    }
    byte[] arrayOfByte4;
    do
    {
      byte[] arrayOfByte5 = new byte[4];
      arrayOfByte5[0] = arrayOfByte4[24];
      arrayOfByte5[1] = arrayOfByte4[25];
      arrayOfByte5[2] = arrayOfByte4[26];
      arrayOfByte5[3] = arrayOfByte4[27];
      return FM_Bytes.bytesToHexString(arrayOfByte5);
      byte[] arrayOfByte3 = new byte[5];
      arrayOfByte3[1] = -80;
      arrayOfByte3[2] = -107;
      arrayOfByte4 = a(arrayOfByte3);
      if (FM_Bytes.isEnd9000(arrayOfByte4))
        continue;
      if (this.a != null)
        this.a.error(this.b, CRCUtil.valueOf(5, "菬厚匼乄庋甸月敚朜斢ｉ训厑)<斝份奭赨ｄ") + FM_Bytes.bytesToHexString(arrayOfByte4));
      throw new BusinessException(FM_Int.lastIndexOf(304, "莴叒卤丌库甠最敂朔旺！诵叙!$斕以夥贰"), BusinessException.ErrorMessage.local_business_execute_fail);
    }
    while (arrayOfByte4.length >= 29);
    if (this.a != null)
      this.a.error(this.b, FM_Utils.copyValueOf(224, 42, "菼參卾乃庇电李教朄斳＃询厕<\"旆份夤赺３") + FM_Bytes.bytesToHexString(arrayOfByte4));
    throw new BusinessException(BCCUtil.endsWith("莬参卬丌廋異杘攂杜斪ｙ讵厑q,斕份夵贸", 156, 121), BusinessException.ErrorMessage.local_business_execute_fail);
  }

  // ERROR //
  public boolean isLock4Consume()
    throws BusinessException
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: aload_0
    //   3: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   6: ifnonnull +13 -> 19
    //   9: aload_0
    //   10: invokestatic 29	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   13: invokevirtual 33	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   16: putfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   19: aload_0
    //   20: getfield 104	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   23: ifnonnull +52 -> 75
    //   26: aload_0
    //   27: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   30: ifnull +25 -> 55
    //   33: aload_0
    //   34: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   37: aload_0
    //   38: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   41: iconst_3
    //   42: bipush 90
    //   44: ldc_w 458
    //   47: invokestatic 176	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   50: invokeinterface 123 3 0
    //   55: new 100	cn/com/fmsh/tsm/business/exception/BusinessException
    //   58: dup
    //   59: ldc_w 460
    //   62: iconst_5
    //   63: bipush 10
    //   65: invokestatic 117	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   68: getstatic 208	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_null	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   71: invokespecial 143	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   74: athrow
    //   75: new 405	java/util/Random
    //   78: dup
    //   79: invokespecial 406	java/util/Random:<init>	()V
    //   82: astore_2
    //   83: bipush 8
    //   85: newarray byte
    //   87: astore_3
    //   88: aload_2
    //   89: aload_3
    //   90: invokevirtual 410	java/util/Random:nextBytes	([B)V
    //   93: iconst_5
    //   94: newarray byte
    //   96: astore 4
    //   98: aload 4
    //   100: iload_1
    //   101: bipush 10
    //   103: bastore
    //   104: aload 4
    //   106: iconst_2
    //   107: bipush 67
    //   109: bastore
    //   110: aload 4
    //   112: iconst_3
    //   113: iconst_4
    //   114: bastore
    //   115: aload 4
    //   117: iconst_4
    //   118: bipush 8
    //   120: bastore
    //   121: aload_0
    //   122: aload 4
    //   124: aload_3
    //   125: invokestatic 414	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
    //   128: invokespecial 210	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	([B)[B
    //   131: astore 5
    //   133: aload 5
    //   135: invokestatic 214	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   138: ifne +114 -> 252
    //   141: aload_0
    //   142: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   145: ifnull +25 -> 170
    //   148: aload_0
    //   149: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   152: aload_0
    //   153: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   156: iconst_5
    //   157: bipush 41
    //   159: ldc_w 462
    //   162: invokestatic 176	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   165: invokeinterface 123 3 0
    //   170: new 100	cn/com/fmsh/tsm/business/exception/BusinessException
    //   173: dup
    //   174: iconst_5
    //   175: bipush 122
    //   177: ldc_w 464
    //   180: invokestatic 176	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   183: getstatic 140	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_execute_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   186: invokespecial 143	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   189: athrow
    //   190: astore 11
    //   192: aload_0
    //   193: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   196: ifnull +38 -> 234
    //   199: aload_0
    //   200: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   203: aload_0
    //   204: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   207: new 145	java/lang/StringBuilder
    //   210: dup
    //   211: iconst_4
    //   212: ldc_w 466
    //   215: invokestatic 134	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   218: invokespecial 155	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   221: aload 6
    //   223: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   229: invokeinterface 123 3 0
    //   234: new 100	cn/com/fmsh/tsm/business/exception/BusinessException
    //   237: dup
    //   238: iconst_3
    //   239: ldc_w 468
    //   242: invokestatic 186	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   245: getstatic 140	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_execute_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   248: invokespecial 143	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   251: athrow
    //   252: aload 5
    //   254: arraylength
    //   255: iconst_5
    //   256: if_icmpge +49 -> 305
    //   259: aload_0
    //   260: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   263: ifnull +23 -> 286
    //   266: aload_0
    //   267: getfield 35	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   270: aload_0
    //   271: getfield 43	cn/com/fmsh/tsm/business/card/core/ShTourAppManager:b	Ljava/lang/String;
    //   274: ldc_w 470
    //   277: iconst_3
    //   278: invokestatic 331	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   281: invokeinterface 123 3 0
    //   286: new 100	cn/com/fmsh/tsm/business/exception/BusinessException
    //   289: dup
    //   290: bipush 6
    //   292: ldc_w 472
    //   295: invokestatic 186	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   298: getstatic 140	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_execute_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   301: invokespecial 143	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   304: athrow
    //   305: aload 5
    //   307: iload_1
    //   308: iconst_5
    //   309: invokestatic 341	java/util/Arrays:copyOfRange	([BII)[B
    //   312: invokestatic 228	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   315: astore 6
    //   317: invokestatic 423	java/lang/System:currentTimeMillis	()J
    //   320: lstore 7
    //   322: new 425	java/text/SimpleDateFormat
    //   325: dup
    //   326: ldc_w 474
    //   329: iconst_4
    //   330: bipush 11
    //   332: invokestatic 224	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   335: invokespecial 428	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   338: astore 9
    //   340: aload 9
    //   342: aload 6
    //   344: invokevirtual 432	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   347: astore 10
    //   349: aload 5
    //   351: iconst_0
    //   352: baload
    //   353: ifeq +14 -> 367
    //   356: aload 10
    //   358: invokevirtual 420	java/util/Date:getTime	()J
    //   361: lload 7
    //   363: lcmp
    //   364: ifge +5 -> 369
    //   367: iload_1
    //   368: ireturn
    //   369: iconst_0
    //   370: istore_1
    //   371: goto -4 -> 367
    //
    // Exception table:
    //   from	to	target	type
    //   340	349	190	java/text/ParseException
  }

  public boolean isLock4Load()
    throws BusinessException
  {
    int i = 0;
    if (this.a == null)
      this.a = LogFactory.getInstance().getLog();
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, Util4Java.toString("菼参圕孞勐肥镀宐犥恝斳ｂVpm'奟瑂噥丬稥", 204, 105));
      throw new BusinessException(BCCUtil.endsWith("莴厖丗浭斒湬印佗飖斾）C/xl奒瑕噸丷稰", 4, 93), BusinessException.ErrorMessage.local_business_apdu_handler_null);
    }
    Random localRandom = new Random();
    byte[] arrayOfByte1 = new byte[8];
    localRandom.nextBytes(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[5];
    arrayOfByte2[1] = 10;
    arrayOfByte2[2] = -125;
    arrayOfByte2[3] = 4;
    arrayOfByte2[4] = 8;
    byte[] arrayOfByte3 = a(FM_Bytes.join(arrayOfByte2, arrayOfByte1));
    if (!FM_Bytes.isEnd9000(arrayOfByte3))
    {
      if (this.a != null)
        this.a.warn(this.b, FM_Exception.getChars(54, 95, "莸变坅嬔劔胷镈寒犱怇斳ｈBR\005\025挘人辉囂终柆奨贽"));
      throw new BusinessException(FM_Int.lastIndexOf(6, "菮厌坓嬄勂肣镞定犷怃旵（DVC]挎仮夏琊夼贫"), BusinessException.ErrorMessage.local_business_execute_fail);
    }
    do
    {
      String str = FM_Bytes.bytesToHexString(Arrays.copyOfRange(arrayOfByte3, 1, 5));
      long l = System.currentTimeMillis();
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(FM_CN.subSequence("=*;(MBzi", 310));
      try
      {
        Date localDate = localSimpleDateFormat.parse(str);
        if ((arrayOfByte3[0] == 0) || (localDate.getTime() < l))
          i = 1;
        return i;
      }
      catch (ParseException localParseException)
      {
        if (this.a != null)
          this.a.warn(this.b, FM_Exception.getChars(3, 119, "菫厅淂赸勇育镇宇犢怊旴５莧发盚新杓桿录当幰%") + str);
        throw new BusinessException(FM_Exception.getChars(4, 63, "菪及坓孂勆胥镖完狣怕斥＞\020@\013[捊仨奏琌奸购"), BusinessException.ErrorMessage.local_business_execute_fail);
      }
    }
    while (arrayOfByte3.length >= 5);
    if (this.a != null)
      this.a.warn(this.b, FM_Bytes.startsWith("莳叛圞孇劗胬锛寙狺恔斨ｋ\021\tF^挓仹夂琉天赤", 3, 9));
    throw new BusinessException(FM_Exception.getChars(60, 3, "莢収圓孆勞肹镆寐狻恑斥ｚ\030\f\033\027捂京奏瑈奠赱"), BusinessException.ErrorMessage.local_business_execute_fail);
  }

  public int queryBalance()
    throws BusinessException
  {
    if (this.a == null)
      this.a = LogFactory.getInstance().getLog();
    if (this.a != null)
      this.a.info(this.b, FM_Int.lastIndexOf(4, "菠厎乓洭斞湤匼伇飂 /,-"));
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, FM_CN.subSequence("莧叉丄浪斉湣匫伀颕；Ge &奆瑗器丵穤", 2));
      throw new BusinessException(CRCUtil.valueOf(206, "菳參丌浠旍湡匫伂飑旫＂^`e'奇瑒嘭丬穽"), BusinessException.ErrorMessage.local_business_apdu_handler_null);
    }
    byte[] arrayOfByte3;
    do
    {
      int i = FM_Bytes.bytesToInt(Arrays.copyOf(arrayOfByte3, 4));
      byte[] arrayOfByte4 = new byte[3];
      arrayOfByte4[0] = arrayOfByte3[6];
      arrayOfByte4[1] = arrayOfByte3[7];
      arrayOfByte4[2] = arrayOfByte3[8];
      return i - FM_Bytes.bytesToInt(arrayOfByte4);
      byte[] arrayOfByte1 = new byte[7];
      arrayOfByte1[1] = -92;
      arrayOfByte1[4] = 2;
      arrayOfByte1[5] = 63;
      arrayOfByte1[6] = 1;
      a(arrayOfByte1);
      byte[] arrayOfByte2 = new byte[17];
      arrayOfByte2[0] = -128;
      arrayOfByte2[1] = 80;
      arrayOfByte2[2] = 3;
      arrayOfByte2[3] = 2;
      arrayOfByte2[4] = 11;
      arrayOfByte2[5] = 1;
      arrayOfByte2[16] = 15;
      arrayOfByte3 = a(arrayOfByte2);
    }
    while (arrayOfByte3.length >= 9);
    if (this.a != null)
      this.a.warn(this.b, FM_Int.lastIndexOf(176, "莴叒丏浱旂渰卨体颖旺！O_TD哟庇皐敥捸旷敐"));
    throw new BusinessException(FM_Int.lastIndexOf(5, "華厏乐洬料湥匿伆额旷．哎庐敵捨旧敀"), BusinessException.ErrorMessage.local_get_app_info_fail);
  }

  public List<CardAppRecord> readAppRecords()
    throws BusinessException
  {
    ArrayList localArrayList = new ArrayList();
    if (this.a != null)
      this.a.info(this.b, FM_Utils.copyValueOf(2, 74, "莺厁下洼旐湧匨亷晎讷弄;+a7"));
    String str1 = FM_CN.subSequence("莥受业浨斋湥匭仿昙让彝", 4);
    byte[] arrayOfByte4;
    Object localObject;
    label184: int i;
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, str1 + FM_Exception.getChars(194, 99, "旭２@4#変瑖嘻丬穣"));
      throw new BusinessException(str1 + Util4Java.toString("旲ｋK=t&夒瑟噴乥穸", 5, 67), BusinessException.ErrorMessage.local_business_apdu_handler_null);
      String str2 = getDateTime4File07(Arrays.copyOfRange(arrayOfByte4, 10, 15));
      EnumTradeType localEnumTradeType = a(arrayOfByte4[0]);
      if ((str2 != null) && (localEnumTradeType != null))
        ((Map)localObject).put(str2, localEnumTradeType);
      i++;
    }
    while (true)
    {
      label194: int j;
      label228: label235: label237: byte[] arrayOfByte8;
      if (i > 10)
      {
        byte[] arrayOfByte6 = new byte[7];
        arrayOfByte6[1] = -92;
        arrayOfByte6[4] = 2;
        arrayOfByte6[6] = 24;
        a(arrayOfByte6);
        j = 1;
        if (j <= 10)
          break label334;
        return localArrayList;
        if (arrayOfByte8.length >= 23)
          break label409;
      }
      while (true)
      {
        j++;
        break label228;
        byte[] arrayOfByte3 = new byte[5];
        arrayOfByte3[1] = -78;
        arrayOfByte3[2] = (byte)i;
        arrayOfByte3[3] = 4;
        arrayOfByte4 = a(arrayOfByte3);
        byte[] arrayOfByte5 = new byte[2];
        arrayOfByte5[0] = 106;
        arrayOfByte5[1] = -125;
        if (Arrays.equals(arrayOfByte5, Arrays.copyOfRange(arrayOfByte4, -2 + arrayOfByte4.length, arrayOfByte4.length)))
          break label194;
        if (arrayOfByte4.length >= 16)
          break;
        break label184;
        label334: byte[] arrayOfByte7 = new byte[5];
        arrayOfByte7[1] = -78;
        arrayOfByte7[2] = (byte)j;
        arrayOfByte7[3] = 4;
        arrayOfByte8 = a(arrayOfByte7);
        byte[] arrayOfByte9 = new byte[2];
        arrayOfByte9[0] = 106;
        arrayOfByte9[1] = -125;
        if (!Arrays.equals(arrayOfByte9, Arrays.copyOfRange(arrayOfByte8, -2 + arrayOfByte8.length, arrayOfByte8.length)))
          break label237;
        break label235;
        label409: localArrayList.add(getAppRecord4bytes(arrayOfByte8, (Map)localObject));
      }
      byte[] arrayOfByte1 = new byte[7];
      arrayOfByte1[1] = -92;
      arrayOfByte1[4] = 2;
      arrayOfByte1[5] = 63;
      arrayOfByte1[6] = 1;
      a(arrayOfByte1);
      localObject = new HashMap();
      byte[] arrayOfByte2 = new byte[7];
      arrayOfByte2[1] = -92;
      arrayOfByte2[4] = 2;
      arrayOfByte2[6] = 7;
      a(arrayOfByte2);
      new byte[5];
      i = 1;
    }
  }

  public void setApduHandler(ApduHandler paramApduHandler)
  {
    try
    {
      this.g = paramApduHandler;
      label5: return;
    }
    catch (ck localck)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.card.core.ShTourAppManager
 * JD-Core Version:    0.6.0
 */