package cn.com.fmsh.tsm.business.card.core;

import cj;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.tsm.business.bean.CardAppRecord;
import cn.com.fmsh.tsm.business.card.base.CardManager;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LntAppManager
  implements CardManager
{
  public FMLog a = LogFactory.getInstance().getLog();

  public LntAppManager()
  {
    byte[] arrayOfByte = new byte[8];
    arrayOfByte[0] = 89;
    arrayOfByte[1] = 67;
    arrayOfByte[2] = 84;
    arrayOfByte[3] = 46;
    arrayOfByte[4] = 85;
    arrayOfByte[5] = 83;
    arrayOfByte[6] = 69;
    arrayOfByte[7] = 82;
    this.d = arrayOfByte;
    this.e = 23;
    this.f = 2;
  }

  public byte[] getAid()
  {
    return this.d;
  }

  public byte[] getAppNo()
    throws BusinessException
  {
    Object localObject = null;
    try
    {
      if (this.a == null)
        this.a = LogFactory.getInstance().getLog();
      if (this.a != null)
        this.a.debug(this.b, Util4Java.toString("\003\027W-pdTo9\0352iu", 112, 10));
      if (this.g == null)
      {
        if (this.a != null)
          this.a.warn(this.b, FM_Long.concat("乐浲亴送卧皕庈畯廝削县｟\037ypj夎琓嘨乱稬", 2));
        throw new BusinessException(BCCUtil.endsWith("三洤产遉匢皗廗画庌剄叴｟\002c'f备瑕噫乩稹", 260, 80), BusinessException.ErrorMessage.local_business_apdu_handler_null);
      }
      byte[] arrayOfByte1 = new byte[5];
      arrayOfByte1[0] = -60;
      arrayOfByte1[1] = -2;
      byte[] arrayOfByte2 = a(arrayOfByte1);
      if (FM_Bytes.isEnd9000(arrayOfByte2))
      {
        byte[] arrayOfByte3 = Arrays.copyOf(arrayOfByte2, -2 + arrayOfByte2.length);
        localObject = arrayOfByte3;
      }
    }
    catch (cj localcj)
    {
    }
    return localObject;
  }

  public CardAppRecord getAppRecord4bytes(byte[] paramArrayOfByte)
  {
    try
    {
      localCardAppRecord = new CardAppRecord();
      byte[] arrayOfByte1 = new byte[2];
      arrayOfByte1[0] = paramArrayOfByte[0];
      arrayOfByte1[1] = paramArrayOfByte[1];
      localCardAppRecord.setTradeNo(FM_Bytes.bytesToInt(arrayOfByte1));
      byte[] arrayOfByte2 = new byte[4];
      arrayOfByte2[0] = paramArrayOfByte[5];
      arrayOfByte2[1] = paramArrayOfByte[6];
      arrayOfByte2[2] = paramArrayOfByte[7];
      arrayOfByte2[3] = paramArrayOfByte[8];
      localCardAppRecord.setAmount(Integer.parseInt(FM_Bytes.bytesToHexString(arrayOfByte2), 16));
      localCardAppRecord.setTradeType(a(paramArrayOfByte[9]));
      byte[] arrayOfByte3 = new byte[6];
      arrayOfByte3[0] = paramArrayOfByte[10];
      arrayOfByte3[1] = paramArrayOfByte[11];
      arrayOfByte3[2] = paramArrayOfByte[12];
      arrayOfByte3[3] = paramArrayOfByte[13];
      arrayOfByte3[4] = paramArrayOfByte[14];
      arrayOfByte3[5] = paramArrayOfByte[15];
      localCardAppRecord.setTradeDevice(FM_Bytes.bytesToHexString(arrayOfByte3));
      byte[] arrayOfByte4 = new byte[2];
      arrayOfByte4[0] = paramArrayOfByte[18];
      arrayOfByte4[1] = paramArrayOfByte[19];
      localCardAppRecord.setTradeDate(FM_Bytes.bytesToHexString(arrayOfByte4));
      byte[] arrayOfByte5 = new byte[2];
      arrayOfByte5[0] = paramArrayOfByte[20];
      arrayOfByte5[1] = paramArrayOfByte[21];
      localCardAppRecord.setTradeTime(FM_Bytes.bytesToHexString(arrayOfByte5));
      return localCardAppRecord;
    }
    catch (cj localcj)
    {
      while (true)
        CardAppRecord localCardAppRecord = null;
    }
  }

  public String getFaceID()
    throws BusinessException
  {
    try
    {
      String str2 = FM_Bytes.bytesToHexString(getAppNo());
      str1 = str2;
      return str1;
    }
    catch (cj localcj)
    {
      while (true)
        String str1 = null;
    }
  }

  public String getMOC()
    throws BusinessException
  {
    return null;
  }

  // ERROR //
  public cn.com.fmsh.tsm.business.enums.EnumCardAppStatus getStatus()
    throws BusinessException
  {
    // Byte code:
    //   0: getstatic 240	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_INSTALL	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   3: astore_1
    //   4: aload_0
    //   5: getfield 79	cn/com/fmsh/tsm/business/card/core/LntAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   8: ifnonnull +86 -> 94
    //   11: aload_0
    //   12: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   15: ifnull +26 -> 41
    //   18: aload_0
    //   19: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   22: aload_0
    //   23: getfield 43	cn/com/fmsh/tsm/business/card/core/LntAppManager:b	Ljava/lang/String;
    //   26: ldc 242
    //   28: sipush 148
    //   31: bipush 69
    //   33: invokestatic 109	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   36: invokeinterface 98 3 0
    //   41: new 75	cn/com/fmsh/tsm/business/exception/BusinessException
    //   44: dup
    //   45: ldc 244
    //   47: iconst_2
    //   48: bipush 122
    //   50: invokestatic 155	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   53: getstatic 175	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_null	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   56: invokespecial 118	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   59: athrow
    //   60: iconst_5
    //   61: newarray byte
    //   63: astore 8
    //   65: aload 8
    //   67: iconst_1
    //   68: bipush 10
    //   70: bastore
    //   71: aload_0
    //   72: getfield 79	cn/com/fmsh/tsm/business/card/core/LntAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   75: aload 8
    //   77: invokeinterface 84 2 0
    //   82: astore 10
    //   84: aload 10
    //   86: invokestatic 181	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   89: ifne +232 -> 321
    //   92: aload_1
    //   93: areturn
    //   94: bipush 7
    //   96: newarray byte
    //   98: astore_2
    //   99: aload_2
    //   100: iconst_1
    //   101: bipush 164
    //   103: bastore
    //   104: aload_2
    //   105: iconst_4
    //   106: iconst_2
    //   107: bastore
    //   108: aload_2
    //   109: iconst_5
    //   110: bipush 221
    //   112: bastore
    //   113: aload_2
    //   114: bipush 6
    //   116: bipush 241
    //   118: bastore
    //   119: aload_0
    //   120: getfield 79	cn/com/fmsh/tsm/business/card/core/LntAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   123: aload_2
    //   124: invokeinterface 84 2 0
    //   129: astore 4
    //   131: aload 4
    //   133: invokestatic 181	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   136: ifeq -44 -> 92
    //   139: bipush 7
    //   141: newarray byte
    //   143: astore 5
    //   145: aload 5
    //   147: iconst_1
    //   148: bipush 164
    //   150: bastore
    //   151: aload 5
    //   153: iconst_4
    //   154: iconst_2
    //   155: bastore
    //   156: aload 5
    //   158: iconst_5
    //   159: bipush 173
    //   161: bastore
    //   162: aload 5
    //   164: bipush 6
    //   166: bipush 243
    //   168: bastore
    //   169: aload_0
    //   170: getfield 79	cn/com/fmsh/tsm/business/card/core/LntAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   173: aload 5
    //   175: invokeinterface 84 2 0
    //   180: astore 7
    //   182: aload 7
    //   184: invokestatic 181	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   187: ifne -127 -> 60
    //   190: goto -98 -> 92
    //   193: iconst_2
    //   194: aload 10
    //   196: iconst_2
    //   197: baload
    //   198: if_icmpne -106 -> 92
    //   201: getstatic 247	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_PERSONALIZED	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   204: astore_1
    //   205: bipush 16
    //   207: newarray byte
    //   209: astore 11
    //   211: aload 11
    //   213: iconst_0
    //   214: bipush 128
    //   216: bastore
    //   217: aload 11
    //   219: iconst_1
    //   220: bipush 80
    //   222: bastore
    //   223: aload 11
    //   225: iconst_2
    //   226: iconst_1
    //   227: bastore
    //   228: aload 11
    //   230: iconst_3
    //   231: iconst_2
    //   232: bastore
    //   233: aload 11
    //   235: iconst_4
    //   236: bipush 11
    //   238: bastore
    //   239: aload 11
    //   241: iconst_5
    //   242: iconst_2
    //   243: bastore
    //   244: aload_0
    //   245: getfield 79	cn/com/fmsh/tsm/business/card/core/LntAppManager:g	Lcn/com/fmsh/script/ApduHandler;
    //   248: aload 11
    //   250: invokeinterface 84 2 0
    //   255: astore 13
    //   257: aload 13
    //   259: invokestatic 181	cn/com/fmsh/util/FM_Bytes:isEnd9000	([B)Z
    //   262: ifeq -170 -> 92
    //   265: getstatic 250	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:STATUS_ACTIVATE	Lcn/com/fmsh/tsm/business/enums/EnumCardAppStatus;
    //   268: astore_1
    //   269: goto -177 -> 92
    //   272: astore 9
    //   274: aload_0
    //   275: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   278: ifnull -186 -> 92
    //   281: aload_0
    //   282: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   285: aload_0
    //   286: getfield 43	cn/com/fmsh/tsm/business/card/core/LntAppManager:b	Ljava/lang/String;
    //   289: new 120	java/lang/StringBuilder
    //   292: dup
    //   293: ldc 252
    //   295: iconst_4
    //   296: invokestatic 92	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   299: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   302: aload 9
    //   304: invokestatic 131	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   307: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: invokevirtual 138	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   313: invokeinterface 255 3 0
    //   318: goto -226 -> 92
    //   321: aload 10
    //   323: arraylength
    //   324: iconst_5
    //   325: if_icmpge -132 -> 193
    //   328: aload_0
    //   329: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   332: ifnull -240 -> 92
    //   335: aload_0
    //   336: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   339: aload_0
    //   340: getfield 43	cn/com/fmsh/tsm/business/card/core/LntAppManager:b	Ljava/lang/String;
    //   343: ldc_w 257
    //   346: sipush 208
    //   349: bipush 48
    //   351: invokestatic 172	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   354: invokeinterface 255 3 0
    //   359: goto -267 -> 92
    //   362: astore_3
    //   363: aload_0
    //   364: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   367: ifnull -275 -> 92
    //   370: aload_0
    //   371: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   374: aload_0
    //   375: getfield 43	cn/com/fmsh/tsm/business/card/core/LntAppManager:b	Ljava/lang/String;
    //   378: new 120	java/lang/StringBuilder
    //   381: dup
    //   382: ldc_w 259
    //   385: sipush 262
    //   388: invokestatic 165	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   391: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   394: aload_3
    //   395: invokestatic 131	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   398: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: invokevirtual 138	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   404: invokeinterface 255 3 0
    //   409: goto -317 -> 92
    //   412: astore 6
    //   414: aload_0
    //   415: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   418: ifnull -326 -> 92
    //   421: aload_0
    //   422: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   425: aload_0
    //   426: getfield 43	cn/com/fmsh/tsm/business/card/core/LntAppManager:b	Ljava/lang/String;
    //   429: new 120	java/lang/StringBuilder
    //   432: dup
    //   433: iconst_2
    //   434: bipush 56
    //   436: ldc_w 261
    //   439: invokestatic 267	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   442: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   445: aload 6
    //   447: invokestatic 131	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   450: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: invokevirtual 138	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: invokeinterface 255 3 0
    //   461: goto -369 -> 92
    //   464: astore 12
    //   466: aload_0
    //   467: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   470: ifnull -378 -> 92
    //   473: aload_0
    //   474: getfield 35	cn/com/fmsh/tsm/business/card/core/LntAppManager:a	Lcn/com/fmsh/util/log/FMLog;
    //   477: aload_0
    //   478: getfield 43	cn/com/fmsh/tsm/business/card/core/LntAppManager:b	Ljava/lang/String;
    //   481: new 120	java/lang/StringBuilder
    //   484: dup
    //   485: bipush 6
    //   487: bipush 73
    //   489: ldc_w 269
    //   492: invokestatic 274	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   495: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   498: aload 12
    //   500: invokestatic 131	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   503: invokevirtual 135	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: invokevirtual 138	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   509: invokeinterface 255 3 0
    //   514: goto -422 -> 92
    //
    // Exception table:
    //   from	to	target	type
    //   71	84	272	cn/com/fmsh/script/exception/FMScriptHandleException
    //   119	131	362	cn/com/fmsh/script/exception/FMScriptHandleException
    //   169	182	412	cn/com/fmsh/script/exception/FMScriptHandleException
    //   244	257	464	cn/com/fmsh/script/exception/FMScriptHandleException
  }

  public String getTime4Validity()
    throws BusinessException
  {
    return null;
  }

  public boolean isLock4Consume()
    throws BusinessException
  {
    return false;
  }

  public boolean isLock4Load()
    throws BusinessException
  {
    return false;
  }

  public int queryBalance()
    throws BusinessException
  {
    int i = 0;
    try
    {
      if (this.a == null)
        this.a = LogFactory.getInstance().getLog();
      if (this.a != null)
        this.a.debug(this.b, FM_Utils.copyValueOf(3, 90, "B\006\026<'%ov'Zs 'n91 &l"));
      if (this.g == null)
      {
        if (this.a != null)
          this.a.warn(this.b, Util4Java.toString("莢叄仫遖卨佟飞斶ｑ[g0$夊琍嘠乿穸", 150, 29));
        throw new BusinessException(BCCUtil.endsWith("获厛亾遝卵佘飓旭ｄTr+i奍琐噫乪穧", 1, 77), BusinessException.ErrorMessage.local_business_apdu_handler_null);
      }
      byte[] arrayOfByte1 = new byte[7];
      arrayOfByte1[1] = -92;
      arrayOfByte1[4] = 2;
      arrayOfByte1[5] = -35;
      arrayOfByte1[6] = -15;
      a(arrayOfByte1);
      byte[] arrayOfByte2 = new byte[7];
      arrayOfByte2[1] = -92;
      arrayOfByte2[4] = 2;
      arrayOfByte2[5] = -83;
      arrayOfByte2[6] = -13;
      a(arrayOfByte2);
      byte[] arrayOfByte3 = new byte[17];
      arrayOfByte3[0] = -128;
      arrayOfByte3[1] = 80;
      arrayOfByte3[2] = 3;
      arrayOfByte3[3] = 2;
      arrayOfByte3[4] = 11;
      arrayOfByte3[5] = 1;
      arrayOfByte3[16] = 15;
      byte[] arrayOfByte4 = a(arrayOfByte3);
      if (arrayOfByte4.length < 9)
      {
        if (this.a != null)
          this.a.warn(this.b, FM_Utils.copyValueOf(1, 123, "莻发亦遇匹伊飓斿ｈ\036\n\021\005咆廒盅敬捹旲故"));
        throw new BusinessException(FM_Exception.getChars(2, 120, "菬厅仯遙区伊飖斵７哞废敳捵旳敃"), BusinessException.ErrorMessage.local_get_app_info_fail);
      }
      int j = FM_Bytes.bytesToInt(Arrays.copyOf(arrayOfByte4, 4));
      byte[] arrayOfByte5 = new byte[3];
      arrayOfByte5[0] = arrayOfByte4[6];
      arrayOfByte5[1] = arrayOfByte4[7];
      arrayOfByte5[2] = arrayOfByte4[8];
      int k = FM_Bytes.bytesToInt(arrayOfByte5);
      i = j - k;
    }
    catch (cj localcj)
    {
    }
    return i;
  }

  public List<CardAppRecord> readAppRecords()
    throws BusinessException
  {
    if (this.a == null)
      this.a = LogFactory.getInstance().getLog();
    ArrayList localArrayList = new ArrayList();
    if (this.a != null)
      this.a.debug(this.b, FM_Exception.getChars(4, 99, "\021\016\027&{).vT(+Ldg(8ic}x7"));
    if (this.g == null)
    {
      if (this.a != null)
        this.a.warn(this.b, CRCUtil.valueOf(3, "菮厜仿晟训彛早＜@\"'!奁琐噯丢穳"));
      throw new BusinessException(FM_Bytes.startsWith("莱厃仠晀诲彄时＃\037=8>夞琏噰丽稬", 5, 111), BusinessException.ErrorMessage.local_business_apdu_handler_null);
    }
    label182: label322: 
    while (true)
    {
      byte[] arrayOfByte4 = new byte[5];
      arrayOfByte4[1] = -78;
      int i;
      arrayOfByte4[2] = (byte)i;
      arrayOfByte4[3] = 4;
      byte[] arrayOfByte5 = a(arrayOfByte4);
      byte[] arrayOfByte6 = new byte[2];
      arrayOfByte6[0] = 106;
      arrayOfByte6[1] = -125;
      if (Arrays.equals(arrayOfByte6, Arrays.copyOfRange(arrayOfByte5, -2 + arrayOfByte5.length, arrayOfByte5.length)))
      {
        return localArrayList;
        localArrayList.add(getAppRecord4bytes(arrayOfByte5));
      }
      while (true)
      {
        i++;
        while (true)
        {
          if (i <= 10)
            break label322;
          break;
          byte[] arrayOfByte1 = new byte[7];
          arrayOfByte1[1] = -92;
          arrayOfByte1[4] = 2;
          arrayOfByte1[5] = -35;
          arrayOfByte1[6] = -15;
          a(arrayOfByte1);
          byte[] arrayOfByte2 = new byte[7];
          arrayOfByte2[1] = -92;
          arrayOfByte2[4] = 2;
          arrayOfByte2[5] = -83;
          arrayOfByte2[6] = -13;
          a(arrayOfByte2);
          byte[] arrayOfByte3 = new byte[7];
          arrayOfByte3[1] = -92;
          arrayOfByte3[4] = 2;
          arrayOfByte3[6] = 24;
          a(arrayOfByte3);
          i = 1;
        }
        if (arrayOfByte5.length >= 23)
          break label182;
      }
    }
  }

  public void setApduHandler(ApduHandler paramApduHandler)
  {
    try
    {
      this.g = paramApduHandler;
      label5: return;
    }
    catch (cj localcj)
    {
      break label5;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.card.core.LntAppManager
 * JD-Core Version:    0.6.0
 */