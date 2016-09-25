package cn.com.fmsh.tsm.business.card.core;

import cl;
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
import cn.com.fmsh.util.FM_Long;
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

public class StpcManager
  implements CardManager
{
  public FMLog a = LogFactory.getInstance().getLog();

  public StpcManager()
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
    byte[] arrayOfByte1 = new byte[8];
    if (this.a != null)
      this.a.info(this.b, FM_Utils.copyValueOf(6, 87, "B\030KUm\005K\002iN\030n+r}"));
    byte[] arrayOfByte5;
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, FM_Utils.copyValueOf(2, 107, "万洯仧達卸皀庛畲廊則召＊P,#'夙琎噻乤稳"));
      throw new BusinessException(FM_Int.lastIndexOf(3, "乜洠仼遃医盟廈畵廑剈号－Cs`p夂琁噠丳穰"), BusinessException.ErrorMessage.local_business_apdu_handler_null);
      byte[] arrayOfByte4 = new byte[5];
      arrayOfByte4[1] = -80;
      arrayOfByte4[2] = -107;
      arrayOfByte5 = a(arrayOfByte4);
      if (FM_Bytes.isEnd9000(arrayOfByte5))
      {
        if (arrayOfByte5.length < 20)
          break label325;
        System.arraycopy(arrayOfByte5, 12, arrayOfByte1, 0, arrayOfByte1.length);
      }
    }
    while (true)
    {
      return arrayOfByte1;
      byte[] arrayOfByte3;
      do
      {
        if (this.a != null)
          this.a.warn(this.b, BCCUtil.endsWith("莵叏乚浰儲亱匭皇卻厦旾ｓ速护E_\024扮蠌引并?", 3, 55) + FM_Bytes.bytesToHexString(arrayOfByte3));
        throw new BusinessException(FM_Bytes.startsWith("莲友也浺儩亹匴皉匤厪旣ａ逌抴T\tC扺蠙式幽", 4, 56), BusinessException.ErrorMessage.local_message_apdu_execute_exception);
        if (this.a != null)
          this.a.warn(this.b, FM_CN.subSequence("莸又万浫儧仾匨盜卦叡旳８遊抻pe旘仸哐庘弙干c", 1) + FM_Bytes.bytesToHexString(arrayOfByte5));
        throw new BusinessException(Util4Java.toString("莲厑乃浼兡仫匰皗却厠斯７途抶pv斂仱咄廟式帷", 6, 98), BusinessException.ErrorMessage.local_message_apdu_execute_exception);
        byte[] arrayOfByte2 = new byte[7];
        arrayOfByte2[1] = -92;
        arrayOfByte2[4] = 2;
        arrayOfByte2[5] = 63;
        arrayOfByte2[6] = 1;
        arrayOfByte3 = a(arrayOfByte2);
      }
      while (!FM_Bytes.isEnd9000(arrayOfByte3));
      if (arrayOfByte3.length < 42)
        break;
      System.arraycopy(arrayOfByte3, 34, arrayOfByte1, 0, arrayOfByte1.length);
      continue;
      label325: arrayOfByte1 = null;
    }
  }

  public CardAppRecord getAppRecord4bytes(byte[] paramArrayOfByte, Map<String, EnumTradeType> paramMap)
  {
    CardAppRecord localCardAppRecord2;
    try
    {
      CardAppRecord localCardAppRecord1 = new CardAppRecord();
      byte[] arrayOfByte1 = new byte[2];
      arrayOfByte1[0] = paramArrayOfByte[0];
      arrayOfByte1[1] = paramArrayOfByte[1];
      localCardAppRecord1.setTradeNo(FM_Bytes.bytesToInt(arrayOfByte1));
      byte[] arrayOfByte2 = new byte[4];
      arrayOfByte2[0] = paramArrayOfByte[16];
      arrayOfByte2[1] = paramArrayOfByte[17];
      arrayOfByte2[2] = paramArrayOfByte[18];
      arrayOfByte2[3] = paramArrayOfByte[19];
      localCardAppRecord1.setTradeDate(FM_Bytes.bytesToHexString(arrayOfByte2));
      byte[] arrayOfByte3 = new byte[3];
      arrayOfByte3[0] = paramArrayOfByte[20];
      arrayOfByte3[1] = paramArrayOfByte[21];
      arrayOfByte3[2] = paramArrayOfByte[22];
      localCardAppRecord1.setTradeTime(FM_Bytes.bytesToHexString(arrayOfByte3));
      byte[] arrayOfByte4 = new byte[4];
      arrayOfByte4[0] = paramArrayOfByte[5];
      arrayOfByte4[1] = paramArrayOfByte[6];
      arrayOfByte4[2] = paramArrayOfByte[7];
      arrayOfByte4[3] = paramArrayOfByte[8];
      localCardAppRecord1.setAmount(Integer.parseInt(FM_Bytes.bytesToHexString(arrayOfByte4), 16));
      byte[] arrayOfByte5 = new byte[3];
      arrayOfByte5[0] = paramArrayOfByte[2];
      arrayOfByte5[1] = paramArrayOfByte[3];
      arrayOfByte5[2] = paramArrayOfByte[4];
      int i = FM_Bytes.bytesToInt(arrayOfByte5);
      localCardAppRecord1.setBalance(i);
      System.out.println(FM_Bytes.startsWith(";:ut76qp32ml/.ih住颊b", 5, 33) + i);
      localCardAppRecord1.setOriTradeType(paramArrayOfByte[12]);
      localCardAppRecord1.setOriTradeType(paramArrayOfByte[9]);
      EnumTradeType localEnumTradeType1 = (EnumTradeType)paramMap.get(FM_Bytes.bytesToHexString(arrayOfByte2) + FM_Bytes.bytesToHexString(arrayOfByte3));
      EnumTradeType localEnumTradeType2 = a(paramArrayOfByte[12], paramArrayOfByte[9]);
      if (localEnumTradeType2 != null)
        localCardAppRecord1.setTradeType(localEnumTradeType2);
      while (true)
      {
        System.out.println(FM_Int.lastIndexOf(3, "kjedgfa`cb=<?>98亢昔米垂0") + localCardAppRecord1.getTradeType());
        byte[] arrayOfByte6 = new byte[6];
        arrayOfByte6[0] = paramArrayOfByte[10];
        arrayOfByte6[1] = paramArrayOfByte[11];
        arrayOfByte6[2] = paramArrayOfByte[12];
        arrayOfByte6[3] = paramArrayOfByte[13];
        arrayOfByte6[4] = paramArrayOfByte[14];
        arrayOfByte6[5] = paramArrayOfByte[15];
        localCardAppRecord1.setTradeDevice(FM_Bytes.bytesToHexString(arrayOfByte6));
        localCardAppRecord2 = localCardAppRecord1;
        break;
        localCardAppRecord1.setTradeType(localEnumTradeType1);
      }
    }
    catch (cl localcl)
    {
      localCardAppRecord2 = null;
    }
    return localCardAppRecord2;
  }

  public String getDateTime4File07(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = paramArrayOfByte[0];
    arrayOfByte1[1] = paramArrayOfByte[1];
    localStringBuffer.append(String.valueOf(FM_Bytes.bytesToInt(arrayOfByte1) >> 4));
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
      this.a.info(this.b, FM_Long.concat("\017WF^(u*1$: %8/", 4));
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, FM_Utils.copyValueOf(156, 109, "莰参占霬厬斾９C8<奒瑅噸丧穰"));
      throw new BusinessException(FM_Int.lastIndexOf(80, "菴厒匤霤厰斾ｅ\013;(8奊瑉嘸乫稨"), BusinessException.ErrorMessage.local_business_apdu_handler_null);
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
        this.a.warn(this.b, BCCUtil.endsWith("莳収佃庺邼诬讝砑旲４\r\020\020]挛仴辐嚆纟果夥购０\"!(cng-ｆ", 5, 20) + FM_Bytes.bytesToHexString(arrayOfByte2));
      throw new BusinessException(FM_Utils.copyValueOf(2, 2, "莺叙圙孋劊胪锘宁犫怞旷／DWM^挊仫夕琕夤贲"), BusinessException.ErrorMessage.local_business_execute_fail);
    }
    return FM_Bytes.bytesToHexString(Arrays.copyOf(arrayOfByte2, -2 + arrayOfByte2.length));
  }

  // ERROR //
  public cn.com.fmsh.tsm.business.enums.EnumCardAppStatus getStatus()
    throws BusinessException
  {
    // Byte code:
    //   0: getstatic 366	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_INSTALL	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   3: astore_1
    //   4: aload_0
    //   5: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   8: ifnull +23 -> 31
    //   11: aload_0
    //   12: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   15: aload_0
    //   16: getfield 43	cn/com/fmsh/tsm/business/card/core/StpcManager:b	Ljava/lang/String;
    //   19: ldc_w 368
    //   22: iconst_2
    //   23: invokestatic 167	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   26: invokeinterface 181 3 0
    //   31: aload_0
    //   32: getfield 104	cn/com/fmsh/tsm/business/card/core/StpcManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   35: ifnonnull +575 -> 610
    //   38: aload_0
    //   39: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   42: ifnull +23 -> 65
    //   45: aload_0
    //   46: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   49: aload_0
    //   50: getfield 43	cn/com/fmsh/tsm/business/card/core/StpcManager:b	Ljava/lang/String;
    //   53: ldc_w 370
    //   56: iconst_5
    //   57: invokestatic 330	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   60: invokeinterface 123 3 0
    //   65: new 100	cn/com/fmsh/tsm/business/exception/BusinessException
    //   68: dup
    //   69: ldc_w 372
    //   72: bipush 102
    //   74: invokestatic 330	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   77: getstatic 194	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_null	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   80: invokespecial 142	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   83: athrow
    //   84: sipush 230
    //   87: bipush 39
    //   89: ldc_w 374
    //   92: invokestatic 379	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   95: invokestatic 383	cn/com/fmsh/util/FM_Bytes:hexStringToBytes	(Ljava/lang/String;)[B
    //   98: astore 16
    //   100: aload_0
    //   101: getfield 104	cn/com/fmsh/tsm/business/card/core/StpcManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   104: aload 16
    //   106: invokeinterface 109 2 0
    //   111: astore 18
    //   113: iconst_2
    //   114: newarray byte
    //   116: astore 19
    //   118: aload 19
    //   120: iconst_0
    //   121: ldc_w 384
    //   124: bastore
    //   125: aload 19
    //   127: iconst_1
    //   128: ldc_w 385
    //   131: bastore
    //   132: aload 18
    //   134: aload 19
    //   136: invokestatic 389	cn/com/fmsh/util/FM_Bytes:isEnd	([B[B)Z
    //   139: ifeq +392 -> 531
    //   142: getstatic 392	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_ACTIVATE	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   145: astore_1
    //   146: aload_1
    //   147: areturn
    //   148: aload 4
    //   150: iconst_0
    //   151: baload
    //   152: ifeq +14 -> 166
    //   155: aload 24
    //   157: invokevirtual 398	java/util/Date:getTime	()J
    //   160: lload 21
    //   162: lcmp
    //   163: ifge +383 -> 546
    //   166: iconst_0
    //   167: istore 12
    //   169: iload 12
    //   171: ifeq -25 -> 146
    //   174: bipush 16
    //   176: newarray byte
    //   178: astore 13
    //   180: aload 13
    //   182: iconst_0
    //   183: bipush 128
    //   185: bastore
    //   186: aload 13
    //   188: iconst_1
    //   189: bipush 80
    //   191: bastore
    //   192: aload 13
    //   194: iconst_3
    //   195: iconst_2
    //   196: bastore
    //   197: aload 13
    //   199: iconst_4
    //   200: bipush 11
    //   202: bastore
    //   203: aload 13
    //   205: iconst_5
    //   206: iconst_1
    //   207: bastore
    //   208: aload 13
    //   210: bipush 8
    //   212: bipush 7
    //   214: bastore
    //   215: aload 13
    //   217: bipush 9
    //   219: bipush 208
    //   221: bastore
    //   222: aload_0
    //   223: getfield 104	cn/com/fmsh/tsm/business/card/core/StpcManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   226: aload 13
    //   228: invokeinterface 109 2 0
    //   233: astore 15
    //   235: aload 15
    //   237: invokestatic 200	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   240: ifne -156 -> 84
    //   243: goto -97 -> 146
    //   246: astore 28
    //   248: aload_0
    //   249: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   252: ifnull -106 -> 146
    //   255: aload_0
    //   256: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   259: aload_0
    //   260: getfield 43	cn/com/fmsh/tsm/business/card/core/StpcManager:b	Ljava/lang/String;
    //   263: new 144	java/lang/StringBuilder
    //   266: dup
    //   267: ldc_w 400
    //   270: iconst_5
    //   271: bipush 42
    //   273: invokestatic 213	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   276: invokespecial 149	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   279: aload 28
    //   281: invokestatic 153	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   284: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   290: invokeinterface 403 3 0
    //   295: goto -149 -> 146
    //   298: aload 4
    //   300: arraylength
    //   301: iconst_5
    //   302: if_icmpge +361 -> 663
    //   305: iconst_1
    //   306: istore 12
    //   308: goto -139 -> 169
    //   311: bipush 32
    //   313: newarray byte
    //   315: astore 5
    //   317: aload 5
    //   319: bipush 30
    //   321: bipush 144
    //   323: bastore
    //   324: aload 5
    //   326: aload 4
    //   328: invokestatic 406	java/util/Arrays:equals	([B[B)Z
    //   331: ifne -185 -> 146
    //   334: getstatic 409	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_PERSONALIZED	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   337: astore_1
    //   338: new 411	java/util/Random
    //   341: dup
    //   342: invokespecial 412	java/util/Random:<init>	()V
    //   345: astore 6
    //   347: bipush 8
    //   349: newarray byte
    //   351: astore 7
    //   353: aload 6
    //   355: aload 7
    //   357: invokevirtual 416	java/util/Random:nextBytes	([B)V
    //   360: iconst_5
    //   361: newarray byte
    //   363: astore 8
    //   365: aload 8
    //   367: iconst_1
    //   368: bipush 10
    //   370: bastore
    //   371: aload 8
    //   373: iconst_2
    //   374: bipush 131
    //   376: bastore
    //   377: aload 8
    //   379: iconst_3
    //   380: iconst_4
    //   381: bastore
    //   382: aload 8
    //   384: iconst_4
    //   385: bipush 8
    //   387: bastore
    //   388: aload 8
    //   390: aload 7
    //   392: invokestatic 420	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
    //   395: astore 9
    //   397: iconst_0
    //   398: istore 10
    //   400: aload_0
    //   401: getfield 104	cn/com/fmsh/tsm/business/card/core/StpcManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   404: aload 9
    //   406: invokeinterface 109 2 0
    //   411: astore 27
    //   413: aload 27
    //   415: astore 4
    //   417: iload 10
    //   419: ifeq +230 -> 649
    //   422: iconst_1
    //   423: istore 12
    //   425: goto -256 -> 169
    //   428: astore 14
    //   430: aload_0
    //   431: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   434: ifnull -288 -> 146
    //   437: aload_0
    //   438: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   441: aload_0
    //   442: getfield 43	cn/com/fmsh/tsm/business/card/core/StpcManager:b	Ljava/lang/String;
    //   445: new 144	java/lang/StringBuilder
    //   448: dup
    //   449: iconst_4
    //   450: iconst_2
    //   451: ldc_w 422
    //   454: invokestatic 379	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   457: invokespecial 149	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   460: aload 14
    //   462: invokestatic 153	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   465: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   468: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   471: invokeinterface 403 3 0
    //   476: goto -330 -> 146
    //   479: astore 17
    //   481: aload_0
    //   482: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   485: ifnull -339 -> 146
    //   488: aload_0
    //   489: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   492: aload_0
    //   493: getfield 43	cn/com/fmsh/tsm/business/card/core/StpcManager:b	Ljava/lang/String;
    //   496: new 144	java/lang/StringBuilder
    //   499: dup
    //   500: ldc_w 424
    //   503: sipush 216
    //   506: invokestatic 330	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   509: invokespecial 149	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   512: aload 17
    //   514: invokestatic 153	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   517: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   520: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   523: invokeinterface 403 3 0
    //   528: goto -382 -> 146
    //   531: aload 18
    //   533: invokestatic 200	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   536: ifeq -390 -> 146
    //   539: getstatic 392	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_ACTIVATE	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   542: astore_1
    //   543: goto -397 -> 146
    //   546: getstatic 392	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_ACTIVATE	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   549: astore_1
    //   550: goto -404 -> 146
    //   553: astore 26
    //   555: aload_0
    //   556: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   559: ifnull +40 -> 599
    //   562: aload_0
    //   563: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   566: aload_0
    //   567: getfield 43	cn/com/fmsh/tsm/business/card/core/StpcManager:b	Ljava/lang/String;
    //   570: new 144	java/lang/StringBuilder
    //   573: dup
    //   574: sipush 230
    //   577: ldc_w 426
    //   580: invokestatic 191	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   583: invokespecial 149	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   586: aload 20
    //   588: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   591: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   594: invokeinterface 123 3 0
    //   599: aload 24
    //   601: ifnonnull -453 -> 148
    //   604: iconst_1
    //   605: istore 12
    //   607: goto -438 -> 169
    //   610: iconst_5
    //   611: newarray byte
    //   613: astore_2
    //   614: aload_2
    //   615: iconst_1
    //   616: bipush 176
    //   618: bastore
    //   619: aload_2
    //   620: iconst_2
    //   621: bipush 149
    //   623: bastore
    //   624: aload_0
    //   625: getfield 104	cn/com/fmsh/tsm/business/card/core/StpcManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   628: aload_2
    //   629: invokeinterface 109 2 0
    //   634: astore_3
    //   635: aload_3
    //   636: astore 4
    //   638: aload 4
    //   640: invokestatic 200	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   643: ifne -332 -> 311
    //   646: goto -500 -> 146
    //   649: aload 4
    //   651: invokestatic 200	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   654: ifne -356 -> 298
    //   657: iconst_1
    //   658: istore 12
    //   660: goto -491 -> 169
    //   663: aload 4
    //   665: iconst_1
    //   666: iconst_5
    //   667: invokestatic 342	java/util/Arrays:copyOfRange	([BII)[B
    //   670: invokestatic 217	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   673: astore 20
    //   675: invokestatic 429	java/lang/System:currentTimeMillis	()J
    //   678: lstore 21
    //   680: new 431	java/text/SimpleDateFormat
    //   683: dup
    //   684: iconst_3
    //   685: ldc_w 433
    //   688: invokestatic 437	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   691: invokespecial 438	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   694: astore 23
    //   696: aconst_null
    //   697: astore 24
    //   699: aload 23
    //   701: aload 20
    //   703: invokevirtual 442	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   706: astore 25
    //   708: aload 25
    //   710: astore 24
    //   712: goto -113 -> 599
    //   715: astore 11
    //   717: aload_0
    //   718: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   721: ifnull +45 -> 766
    //   724: aload_0
    //   725: getfield 35	cn/com/fmsh/tsm/business/card/core/StpcManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   728: aload_0
    //   729: getfield 43	cn/com/fmsh/tsm/business/card/core/StpcManager:b	Ljava/lang/String;
    //   732: new 144	java/lang/StringBuilder
    //   735: dup
    //   736: ldc_w 444
    //   739: sipush 278
    //   742: bipush 17
    //   744: invokestatic 213	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   747: invokespecial 149	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   750: aload 11
    //   752: invokestatic 153	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   755: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   758: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   761: invokeinterface 403 3 0
    //   766: iconst_1
    //   767: istore 10
    //   769: goto -352 -> 417
    //
    // Exception table:
    //   from	to	target	type
    //   624	635	246	cn/com/fmsh/script/exception/FMScriptHandleException
    //   222	235	428	cn/com/fmsh/script/exception/FMScriptHandleException
    //   100	113	479	cn/com/fmsh/script/exception/FMScriptHandleException
    //   699	708	553	java/text/ParseException
    //   400	413	715	cn/com/fmsh/script/exception/FMScriptHandleException
  }

  public String getTime4Validity()
    throws BusinessException
  {
    String str1;
    try
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
          this.a.error(this.b, FM_CN.subSequence("莣叕即之廄畷杇攕朓旭＆逐抡廃畮皻彑夢货＋", 6) + FM_Bytes.bytesToHexString(arrayOfByte2));
        throw new BusinessException(FM_Exception.getChars(184, 82, "莦叕匴不廍畣朔攇杞日ｉ逞拠廏甥皱弄挄亱奃琟奺贸"), BusinessException.ErrorMessage.local_business_execute_fail);
      }
      byte[] arrayOfByte3 = new byte[5];
      arrayOfByte3[1] = -80;
      arrayOfByte3[2] = -107;
      byte[] arrayOfByte4 = a(arrayOfByte3);
      if (!FM_Bytes.isEnd9000(arrayOfByte4))
      {
        if (this.a != null)
          this.a.error(this.b, BCCUtil.endsWith("莶厘区乂庁甪朆攔杖斠ｏ诫友;b旃产夯贮＂", 2, 109) + FM_Bytes.bytesToHexString(arrayOfByte4));
        throw new BusinessException(FM_Exception.getChars(5, 62, "菩及医丒廂甼杛敘村旺ｆ诳厐5w文亨夭赿"), BusinessException.ErrorMessage.local_business_execute_fail);
      }
      if (arrayOfByte4.length < 29)
      {
        if (this.a != null)
          this.a.error(this.b, FM_Utils.copyValueOf(2, 6, "莺叅卸丕庑産朘敟朂斵ｅ讴厃jt旀亻奢赼ｅ") + FM_Bytes.bytesToHexString(arrayOfByte4));
        throw new BusinessException(CRCUtil.valueOf(3, "菮厜区乆庉甦朖敘朞斤ｏ讯厓'2斟仿奫赮"), BusinessException.ErrorMessage.local_business_execute_fail);
      }
      byte[] arrayOfByte5 = new byte[4];
      arrayOfByte5[0] = arrayOfByte4[24];
      arrayOfByte5[1] = arrayOfByte4[25];
      arrayOfByte5[2] = arrayOfByte4[26];
      arrayOfByte5[3] = arrayOfByte4[27];
      String str2 = FM_Bytes.bytesToHexString(arrayOfByte5);
      str1 = str2;
    }
    catch (cl localcl)
    {
      str1 = null;
    }
    return str1;
  }

  public boolean isLock4Consume()
    throws BusinessException
  {
    int i = 0;
    if (this.a == null)
      this.a = LogFactory.getInstance().getLog();
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, CRCUtil.valueOf(1, "菠厞坑嬒劄胱锜宔狩恑斷～\002dac夃瑞嘡习稱"));
      throw new BusinessException(FM_Exception.getChars(1, 19, "菭叛交选匧伀飑斩＞Dh/+奕琂噿丰穧"), BusinessException.ErrorMessage.local_business_apdu_handler_null);
    }
    while (true)
    {
      byte[] arrayOfByte3;
      if (arrayOfByte3.length < 5)
      {
        if (this.a != null)
          this.a.warn(this.b, CRCUtil.valueOf(5, "菬厚淕起劀胭销守犵恕斳ｚ\006HMO挌亸奉瑘奾赥"));
        throw new BusinessException(FM_Utils.copyValueOf(190, 54, "菾厉圝嬓办肪锌寙狯怎斳７\020WY\006挎亻夑瑍夰贲"), BusinessException.ErrorMessage.local_business_execute_fail);
      }
      String str = FM_Bytes.bytesToHexString(Arrays.copyOfRange(arrayOfByte3, 1, 5));
      long l = System.currentTimeMillis();
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(FM_Int.lastIndexOf(1, "-,/.\025\024>?"));
      try
      {
        Date localDate = localSimpleDateFormat.parse(str);
        if ((arrayOfByte3[0] == 0) || (localDate.getTime() < l))
          i = 1;
        return i;
        Random localRandom = new Random();
        byte[] arrayOfByte1 = new byte[8];
        localRandom.nextBytes(arrayOfByte1);
        byte[] arrayOfByte2 = new byte[5];
        arrayOfByte2[1] = 10;
        arrayOfByte2[2] = 67;
        arrayOfByte2[3] = 4;
        arrayOfByte2[4] = 8;
        arrayOfByte3 = a(FM_Bytes.join(arrayOfByte2, arrayOfByte1));
        if (FM_Bytes.isEnd9000(arrayOfByte3))
          continue;
        if (this.a != null)
          this.a.warn(this.b, FM_Exception.getChars(334, 49, "菰厎涁赣劔胡镌宄狹态旧ｎR\024\021S捐们迍嚔终某奬贫"));
        throw new BusinessException(FM_Utils.copyValueOf(5, 34, "莧叄坜嬎劇胧镝寄状恃斲＊I\032\b[挗亶奐琐天赿"), BusinessException.ErrorMessage.local_business_execute_fail);
      }
      catch (ParseException localParseException)
      {
        if (this.a != null)
          this.a.warn(this.b, FM_CN.subSequence("莤叔涙赹勐肣镌密犽怛旿４菰厀盁斱朜栮弎弒帧t", 5) + str);
      }
    }
    throw new BusinessException(FM_Utils.copyValueOf(264, 70, "莤厏圗嬝劔肬锖寇狵怈方９\032Q\003X捔份奛瑃夺赴"), BusinessException.ErrorMessage.local_business_execute_fail);
  }

  public boolean isLock4Load()
    throws BusinessException
  {
    int i = 1;
    if (this.a == null)
      this.a = LogFactory.getInstance().getLog();
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, FM_Int.lastIndexOf(6, "菮厌坓嬄勂肣镞定犷怃旵（Dvc}复琌噣丶穷"));
      throw new BusinessException(CRCUtil.valueOf(2, "華原仾遑卽佔颃旹，\020271夑琀噿串稣"), BusinessException.ErrorMessage.local_business_apdu_handler_null);
    }
    byte[] arrayOfByte3;
    while (arrayOfByte3.length < 5)
    {
      if (this.a != null)
        this.a.warn(this.b, FM_Int.lastIndexOf(156, "莸叆圙孊劌胩锔完犡怙旯６ZLYK挘令夅琄夲贡"));
      throw new BusinessException(FM_Utils.copyValueOf(5, 53, "莧厓坒字勛胤镏宙犮恌旴｛M\021\022^捇仱奎瑙夥赬"), BusinessException.ErrorMessage.local_business_execute_fail);
      Random localRandom = new Random();
      byte[] arrayOfByte1 = new byte[8];
      localRandom.nextBytes(arrayOfByte1);
      byte[] arrayOfByte2 = new byte[5];
      arrayOfByte2[i] = 10;
      arrayOfByte2[2] = -125;
      arrayOfByte2[3] = 4;
      arrayOfByte2[4] = 8;
      arrayOfByte3 = a(FM_Bytes.join(arrayOfByte2, arrayOfByte1));
      if (FM_Bytes.isEnd9000(arrayOfByte3))
        continue;
      if (this.a != null)
        this.a.warn(this.b, FM_Exception.getChars(3, 73, "菫厓圆嬏功肴锓寁狲怌斠３\tA\036\026挋亱迊嚙练柅女贮"));
      throw new BusinessException(Util4Java.toString("莠厑土孟勈胺镖寝犡恆旡＋\026W\023\022挐亣夓琁奦财", 280, 80), BusinessException.ErrorMessage.local_business_execute_fail);
    }
    String str = FM_Bytes.bytesToHexString(Arrays.copyOfRange(arrayOfByte3, i, 5));
    long l = System.currentTimeMillis();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(FM_Utils.copyValueOf(i, 111, "u\"3 \005Zbq"));
    while (true)
    {
      try
      {
        Date localDate = localSimpleDateFormat.parse(str);
        if ((arrayOfByte3[0] == 0) || (localDate.getTime() < l))
          return i;
      }
      catch (ParseException localParseException)
      {
        if (this.a == null)
          continue;
        this.a.warn(this.b, FM_Int.lastIndexOf(310, "莾叜涃贵劒胳锎宊犧怓日８莢叀皓旽朆栦弔弞帥$") + str);
        throw new BusinessException(FM_Exception.getChars(182, 51, "莸厔圝嬐勄胳镀宎狱恛旻ｌR\026\035Y捘件奁瑞夺赻"), BusinessException.ErrorMessage.local_business_execute_fail);
      }
      i = 0;
    }
  }

  public int queryBalance()
    throws BusinessException
  {
    if (this.a == null)
      this.a = LogFactory.getInstance().getLog();
    if (this.a != null)
      this.a.info(this.b, FM_Int.lastIndexOf(5, "\013\t\016\030|?2>nbg#*+("));
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, CRCUtil.valueOf(4, "菭厝仸遗卿佖额旧．\022413夓琎噱丰稡"));
      throw new BusinessException(FM_Long.concat("菬叐亵逆卦佋颀斾｟\03790*夎琓器丱穬", 3), BusinessException.ErrorMessage.local_business_apdu_handler_null);
    }
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
    byte[] arrayOfByte3 = a(arrayOfByte2);
    if (arrayOfByte3.length < 9)
    {
      if (this.a != null)
        this.a.warn(this.b, FM_CN.subSequence("莦取享逄匬伅飖斬％YWR\020咙廗盖敱捾旿敆", 3));
      throw new BusinessException(CRCUtil.valueOf(2, "華原仾遑卽佔颃旹，咜廖攣挪旵敎"), BusinessException.ErrorMessage.local_get_app_info_fail);
    }
    int i = FM_Bytes.bytesToInt(Arrays.copyOf(arrayOfByte3, 4));
    byte[] arrayOfByte4 = new byte[3];
    arrayOfByte4[0] = arrayOfByte3[6];
    arrayOfByte4[1] = arrayOfByte3[7];
    arrayOfByte4[2] = arrayOfByte3[8];
    return i - FM_Bytes.bytesToInt(arrayOfByte4);
  }

  public List<CardAppRecord> readAppRecords()
    throws BusinessException
  {
    ArrayList localArrayList = new ArrayList();
    if (this.a != null)
      this.a.info(this.b, FM_Utils.copyValueOf(5, 38, "CF\bA(/$j`4iq7,`9p82l"));
    byte[] arrayOfByte8;
    label103: int j;
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, BCCUtil.endsWith("莦參份晎诱彐旿ａ\020e}(奅瑃噡丷稫", 178, 100));
      throw new BusinessException(BCCUtil.endsWith("莶厍亱昜诹弖旫｛\020{a*夝琕嘥乽穻", 2, 90), BusinessException.ErrorMessage.local_business_apdu_handler_null);
      if (arrayOfByte8.length < 23)
      {
        j++;
        if (j <= 10)
          break label390;
      }
    }
    while (true)
    {
      return localArrayList;
      Object localObject;
      localArrayList.add(getAppRecord4bytes(arrayOfByte8, (Map)localObject));
      break label103;
      label133: byte[] arrayOfByte4;
      String str = getDateTime4File07(Arrays.copyOfRange(arrayOfByte4, 10, 15));
      EnumTradeType localEnumTradeType = a(arrayOfByte4[0]);
      if ((str != null) && (localEnumTradeType != null))
        ((Map)localObject).put(str, localEnumTradeType);
      while (true)
      {
        int i;
        i++;
        label183: if (i > 10);
        byte[] arrayOfByte5;
        do
        {
          byte[] arrayOfByte6 = new byte[7];
          arrayOfByte6[1] = -92;
          arrayOfByte6[4] = 2;
          arrayOfByte6[6] = 24;
          a(arrayOfByte6);
          j = 1;
          break;
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
          break label183;
          byte[] arrayOfByte3 = new byte[5];
          arrayOfByte3[1] = -78;
          arrayOfByte3[2] = (byte)i;
          arrayOfByte3[3] = 4;
          arrayOfByte4 = a(arrayOfByte3);
          arrayOfByte5 = new byte[2];
          arrayOfByte5[0] = 106;
          arrayOfByte5[1] = -125;
        }
        while (Arrays.equals(arrayOfByte5, Arrays.copyOfRange(arrayOfByte4, -2 + arrayOfByte4.length, arrayOfByte4.length)));
        if (arrayOfByte4.length >= 16)
          break label133;
      }
      label390: byte[] arrayOfByte7 = new byte[5];
      arrayOfByte7[1] = -78;
      arrayOfByte7[2] = (byte)j;
      arrayOfByte7[3] = 4;
      arrayOfByte8 = a(arrayOfByte7);
      byte[] arrayOfByte9 = new byte[2];
      arrayOfByte9[0] = 106;
      arrayOfByte9[1] = -125;
      if (!Arrays.equals(arrayOfByte9, Arrays.copyOfRange(arrayOfByte8, -2 + arrayOfByte8.length, arrayOfByte8.length)))
        break;
    }
  }

  public void setApduHandler(ApduHandler paramApduHandler)
  {
    try
    {
      this.g = paramApduHandler;
      label5: return;
    }
    catch (cl localcl)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.card.core.StpcManager
 * JD-Core Version:    0.6.0
 */