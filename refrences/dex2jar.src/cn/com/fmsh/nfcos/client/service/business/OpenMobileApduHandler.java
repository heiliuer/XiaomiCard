package cn.com.fmsh.nfcos.client.service.business;

import a2;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.ApduHandler.ApduHandlerType;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import org.simalliance.openmobileapi.Channel;
import org.simalliance.openmobileapi.SEService;
import org.simalliance.openmobileapi.Session;

public class OpenMobileApduHandler
  implements ApduHandler
{
  public OpenMobileApduHandler(SEService paramSEService, SeServiceHandler paramSeServiceHandler, byte[] paramArrayOfByte, int paramInt)
  {
    this.b = paramArrayOfByte;
    this.g = paramSEService;
    this.f = paramSeServiceHandler;
    this.d = paramInt;
  }

  public void close()
  {
    this.k = false;
    if (this.c != null)
      this.c.debug(this.a, FM_Exception.getChars(4, 108, "\02290/@vg81,Tqi,\r0smyd?y&}rz0"));
    if (this.j != null)
      this.j.close();
    if (this.i != null)
      this.i.close();
    this.j = null;
    this.i = null;
  }

  public boolean connect()
  {
    if (this.c != null)
      this.c.debug(this.a, FM_Int.lastIndexOf(188, "@`t|^{w{}XjiUq$-'1d&))&,)?bc`"));
    return open(null);
  }

  public ApduHandler.ApduHandlerType getApduHandlerType()
  {
    return ApduHandler.ApduHandlerType.OPEN_MOBILE;
  }

  public byte[] getLastOpenAid()
  {
    return this.e;
  }

  public boolean isConnect()
  {
    int m = 1;
    if (this.k)
      break label11;
    while (true)
    {
      return m;
      label11: if (!this.j.isClosed())
        continue;
      m = 0;
      continue;
      if (this.j != null)
        break;
      m = 0;
    }
  }

  public boolean open(byte[] paramArrayOfByte)
  {
    int m = 1;
    if (this.c == null)
      this.c = LogFactory.getInstance().getLog();
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < m))
      paramArrayOfByte = this.b;
    if (this.c != null)
      this.c.debug(this.a, FM_CN.subSequence("}quqn<%?Q", 4) + FM_Bytes.bytesToHexString(paramArrayOfByte) + "]");
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > m))
      if ((this.j != null) && (!this.j.isClosed()))
      {
        this.j.close();
        if (this.c != null)
          this.c.debug(this.a, FM_Bytes.startsWith("釉旸戟弐斤盜=)`＄叓儘扇弘盘!-l兿閽扄劇", 3, 100));
      }
    while (true)
    {
      try
      {
        Thread.sleep(200L);
        this.e = paramArrayOfByte;
        if (((paramArrayOfByte == null) || (paramArrayOfByte.length < m)) && ((this.i != null) && (!this.i.isClosed())))
          continue;
        if (openSession(this.d) == 0)
          continue;
        if (this.c == null)
          continue;
        this.c.debug(this.a, FM_Utils.copyValueOf(2, 87, "L4g捎交扰蠂斳０<:d6\002i?=ggyw;{, rp&qs!奵贾"));
        m = 0;
        return m;
        if (this.c == null)
          continue;
        this.c.debug(this.a, FM_Int.lastIndexOf(246, ";/\004<( o#4! =:8w+,9>/."));
        this.k = m;
        continue;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
        continue;
      }
      paramArrayOfByte = this.e;
    }
  }

  // ERROR //
  public int openSession(int paramInt)
  {
    // Byte code:
    //   0: sipush 9102
    //   3: istore_2
    //   4: aload_0
    //   5: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   8: ifnonnull +13 -> 21
    //   11: aload_0
    //   12: invokestatic 118	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   15: invokevirtual 122	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   18: putfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   21: aload_0
    //   22: iload_1
    //   23: putfield 56	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:d	I
    //   26: aload_0
    //   27: getfield 52	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:g	Lorg/simalliance/openmobileapi/SEService;
    //   30: invokevirtual 188	org/simalliance/openmobileapi/SEService:getReaders	()[Lorg/simalliance/openmobileapi/Reader;
    //   33: astore 7
    //   35: aload 7
    //   37: astore 5
    //   39: aload 5
    //   41: ifnonnull +33 -> 74
    //   44: aload_0
    //   45: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   48: ifnull +24 -> 72
    //   51: aload_0
    //   52: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   55: aload_0
    //   56: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   59: ldc 190
    //   61: sipush 202
    //   64: invokestatic 132	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   67: invokeinterface 193 3 0
    //   72: iload_2
    //   73: ireturn
    //   74: aload 5
    //   76: arraylength
    //   77: iconst_1
    //   78: if_icmpge +238 -> 316
    //   81: aload_0
    //   82: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   85: ifnull -13 -> 72
    //   88: aload_0
    //   89: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   92: aload_0
    //   93: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   96: ldc 195
    //   98: sipush 316
    //   101: invokestatic 132	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   104: invokeinterface 193 3 0
    //   109: goto -37 -> 72
    //   112: astore_3
    //   113: ldc2_w 196
    //   116: invokestatic 163	java/lang/Thread:sleep	(J)V
    //   119: aload_0
    //   120: aload_0
    //   121: getfield 54	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:f	Lcn/com/fmsh/nfcos/client/service/business/SeServiceHandler;
    //   124: invokeinterface 203 1 0
    //   129: putfield 52	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:g	Lorg/simalliance/openmobileapi/SEService;
    //   132: aload_0
    //   133: getfield 52	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:g	Lorg/simalliance/openmobileapi/SEService;
    //   136: ifnonnull +168 -> 304
    //   139: aload_0
    //   140: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   143: ifnull +24 -> 167
    //   146: aload_0
    //   147: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   150: aload_0
    //   151: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   154: sipush 152
    //   157: ldc 205
    //   159: invokestatic 210	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   162: invokeinterface 193 3 0
    //   167: sipush 9101
    //   170: istore_2
    //   171: goto -99 -> 72
    //   174: astore 6
    //   176: aload_0
    //   177: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   180: ifnull +42 -> 222
    //   183: aload_0
    //   184: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   187: aload_0
    //   188: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   191: new 124	java/lang/StringBuilder
    //   194: dup
    //   195: iconst_1
    //   196: bipush 25
    //   198: ldc 212
    //   200: invokestatic 175	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   203: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   206: aload 6
    //   208: invokestatic 216	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   211: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   217: invokeinterface 193 3 0
    //   222: sipush 9103
    //   225: istore_2
    //   226: goto -154 -> 72
    //   229: aload_0
    //   230: aload 5
    //   232: iload_1
    //   233: aaload
    //   234: putfield 218	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:h	Lorg/simalliance/openmobileapi/Reader;
    //   237: aload_0
    //   238: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   241: aload_0
    //   242: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   245: new 124	java/lang/StringBuilder
    //   248: dup
    //   249: ldc 220
    //   251: sipush 196
    //   254: invokestatic 132	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   257: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   260: aload_0
    //   261: getfield 218	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:h	Lorg/simalliance/openmobileapi/Reader;
    //   264: invokevirtual 225	org/simalliance/openmobileapi/Reader:getName	()Ljava/lang/String;
    //   267: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   273: invokeinterface 71 3 0
    //   278: aload_0
    //   279: aload_0
    //   280: getfield 218	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:h	Lorg/simalliance/openmobileapi/Reader;
    //   283: invokevirtual 228	org/simalliance/openmobileapi/Reader:openSession	()Lorg/simalliance/openmobileapi/Session;
    //   286: putfield 79	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:i	Lorg/simalliance/openmobileapi/Session;
    //   289: iconst_0
    //   290: istore_2
    //   291: goto -219 -> 72
    //   294: astore 4
    //   296: aload 4
    //   298: invokevirtual 180	java/lang/InterruptedException:printStackTrace	()V
    //   301: goto -182 -> 119
    //   304: aload_0
    //   305: getfield 52	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:g	Lorg/simalliance/openmobileapi/SEService;
    //   308: invokevirtual 188	org/simalliance/openmobileapi/SEService:getReaders	()[Lorg/simalliance/openmobileapi/Reader;
    //   311: astore 5
    //   313: goto -274 -> 39
    //   316: iload_1
    //   317: aload 5
    //   319: arraylength
    //   320: if_icmple -91 -> 229
    //   323: aload_0
    //   324: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   327: ifnull +26 -> 353
    //   330: aload_0
    //   331: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   334: aload_0
    //   335: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   338: ldc 230
    //   340: sipush 152
    //   343: bipush 101
    //   345: invokestatic 40	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   348: invokeinterface 193 3 0
    //   353: sipush 9100
    //   356: istore_2
    //   357: goto -285 -> 72
    //
    // Exception table:
    //   from	to	target	type
    //   26	35	112	java/lang/Exception
    //   278	289	174	java/lang/Exception
    //   113	119	294	java/lang/InterruptedException
  }

  public void setAid(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null);
    try
    {
      if (paramArrayOfByte.length > 0)
        this.b = paramArrayOfByte;
      label14: return;
    }
    catch (a2 locala2)
    {
      break label14;
    }
  }

  // ERROR //
  public byte[] transceive(byte[] paramArrayOfByte)
    throws cn.com.fmsh.script.exception.FMScriptHandleException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   4: ifnonnull +13 -> 17
    //   7: aload_0
    //   8: invokestatic 118	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   11: invokevirtual 122	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   14: putfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   17: aload_0
    //   18: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   21: ifnull +39 -> 60
    //   24: aload_0
    //   25: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   28: aload_0
    //   29: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   32: new 124	java/lang/StringBuilder
    //   35: dup
    //   36: ldc 246
    //   38: iconst_1
    //   39: invokestatic 251	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   42: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   45: aload_1
    //   46: invokestatic 141	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   49: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: invokeinterface 71 3 0
    //   60: aload_0
    //   61: getfield 73	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:j	Lorg/simalliance/openmobileapi/Channel;
    //   64: ifnull +13 -> 77
    //   67: aload_0
    //   68: getfield 73	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:j	Lorg/simalliance/openmobileapi/Channel;
    //   71: invokevirtual 110	org/simalliance/openmobileapi/Channel:isClosed	()Z
    //   74: ifeq +458 -> 532
    //   77: aload_0
    //   78: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   81: aload_0
    //   82: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   85: ldc 253
    //   87: iconst_2
    //   88: invokestatic 132	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   91: invokeinterface 71 3 0
    //   96: aload_0
    //   97: getfield 48	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:e	[B
    //   100: ifnull +12 -> 112
    //   103: aload_0
    //   104: getfield 48	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:e	[B
    //   107: arraylength
    //   108: iconst_1
    //   109: if_icmpge +11 -> 120
    //   112: aload_0
    //   113: aload_0
    //   114: getfield 44	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:b	[B
    //   117: putfield 48	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:e	[B
    //   120: aload_0
    //   121: getfield 79	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:i	Lorg/simalliance/openmobileapi/Session;
    //   124: ifnull +13 -> 137
    //   127: aload_0
    //   128: getfield 79	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:i	Lorg/simalliance/openmobileapi/Session;
    //   131: invokevirtual 164	org/simalliance/openmobileapi/Session:isClosed	()Z
    //   134: ifeq +197 -> 331
    //   137: aload_0
    //   138: aload_0
    //   139: getfield 56	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:d	I
    //   142: invokevirtual 168	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:openSession	(I)I
    //   145: istore_2
    //   146: iload_2
    //   147: ifeq +626 -> 773
    //   150: aload_0
    //   151: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   154: ifnull +24 -> 178
    //   157: aload_0
    //   158: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   161: aload_0
    //   162: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   165: sipush 170
    //   168: ldc 255
    //   170: invokestatic 210	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   173: invokeinterface 193 3 0
    //   178: new 238	cn/com/fmsh/script/exception/FMScriptHandleException
    //   181: dup
    //   182: new 124	java/lang/StringBuilder
    //   185: dup
    //   186: ldc_w 257
    //   189: iconst_3
    //   190: bipush 31
    //   192: invokestatic 40	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   195: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   198: iload_2
    //   199: invokevirtual 260	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   202: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   205: invokespecial 261	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   208: athrow
    //   209: astore 8
    //   211: aload_0
    //   212: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   215: ifnull +41 -> 256
    //   218: aload_0
    //   219: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   222: aload_0
    //   223: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   226: new 124	java/lang/StringBuilder
    //   229: dup
    //   230: iconst_4
    //   231: ldc_w 263
    //   234: invokestatic 210	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   237: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   240: aload 8
    //   242: invokestatic 216	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   245: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   251: invokeinterface 193 3 0
    //   256: new 238	cn/com/fmsh/script/exception/FMScriptHandleException
    //   259: dup
    //   260: aload 8
    //   262: invokevirtual 266	java/lang/IllegalStateException:getMessage	()Ljava/lang/String;
    //   265: invokespecial 261	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   268: athrow
    //   269: astore 7
    //   271: aload_0
    //   272: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   275: ifnull +43 -> 318
    //   278: aload_0
    //   279: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   282: aload_0
    //   283: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   286: new 124	java/lang/StringBuilder
    //   289: dup
    //   290: iconst_4
    //   291: bipush 14
    //   293: ldc_w 268
    //   296: invokestatic 65	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   299: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   302: aload 7
    //   304: invokestatic 216	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   307: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   313: invokeinterface 193 3 0
    //   318: new 238	cn/com/fmsh/script/exception/FMScriptHandleException
    //   321: dup
    //   322: aload 7
    //   324: invokevirtual 269	java/lang/IllegalArgumentException:getMessage	()Ljava/lang/String;
    //   327: invokespecial 261	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   330: athrow
    //   331: aload_0
    //   332: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   335: aload_0
    //   336: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   339: iconst_2
    //   340: bipush 74
    //   342: ldc_w 271
    //   345: invokestatic 175	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   348: invokeinterface 71 3 0
    //   353: aload_0
    //   354: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   357: ifnull +59 -> 416
    //   360: aload_0
    //   361: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   364: aload_0
    //   365: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   368: new 124	java/lang/StringBuilder
    //   371: dup
    //   372: sipush 238
    //   375: bipush 119
    //   377: ldc_w 273
    //   380: invokestatic 65	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   383: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   386: aload_0
    //   387: getfield 48	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:e	[B
    //   390: invokestatic 141	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   393: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: ldc_w 275
    //   399: sipush 310
    //   402: invokestatic 251	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   405: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   411: invokeinterface 71 3 0
    //   416: aload_0
    //   417: aload_0
    //   418: getfield 79	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:i	Lorg/simalliance/openmobileapi/Session;
    //   421: aload_0
    //   422: getfield 48	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:e	[B
    //   425: invokevirtual 279	org/simalliance/openmobileapi/Session:openBasicChannel	([B)Lorg/simalliance/openmobileapi/Channel;
    //   428: putfield 73	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:j	Lorg/simalliance/openmobileapi/Channel;
    //   431: aload_0
    //   432: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   435: ifnull +43 -> 478
    //   438: aload_0
    //   439: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   442: aload_0
    //   443: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   446: new 124	java/lang/StringBuilder
    //   449: dup
    //   450: ldc_w 281
    //   453: iconst_5
    //   454: invokestatic 251	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   457: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   460: aload_0
    //   461: getfield 48	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:e	[B
    //   464: invokestatic 141	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   467: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   470: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   473: invokeinterface 71 3 0
    //   478: aload_0
    //   479: getfield 73	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:j	Lorg/simalliance/openmobileapi/Channel;
    //   482: ifnonnull +169 -> 651
    //   485: new 238	cn/com/fmsh/script/exception/FMScriptHandleException
    //   488: dup
    //   489: new 124	java/lang/StringBuilder
    //   492: dup
    //   493: ldc_w 283
    //   496: bipush 6
    //   498: invokestatic 132	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   501: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   504: aload_0
    //   505: getfield 48	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:e	[B
    //   508: invokestatic 141	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   511: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   514: bipush 70
    //   516: ldc_w 285
    //   519: invokestatic 92	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   522: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   528: invokespecial 261	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   531: athrow
    //   532: aload_0
    //   533: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   536: aload_0
    //   537: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   540: bipush 56
    //   542: ldc_w 287
    //   545: invokestatic 92	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   548: invokeinterface 71 3 0
    //   553: goto -75 -> 478
    //   556: astore 6
    //   558: aload_0
    //   559: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   562: ifnull +43 -> 605
    //   565: aload_0
    //   566: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   569: aload_0
    //   570: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   573: new 124	java/lang/StringBuilder
    //   576: dup
    //   577: sipush 358
    //   580: ldc_w 289
    //   583: invokestatic 92	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   586: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   589: aload 6
    //   591: invokestatic 216	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   594: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   597: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   600: invokeinterface 193 3 0
    //   605: new 238	cn/com/fmsh/script/exception/FMScriptHandleException
    //   608: dup
    //   609: aload 6
    //   611: invokevirtual 290	java/io/IOException:getMessage	()Ljava/lang/String;
    //   614: invokespecial 261	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   617: athrow
    //   618: astore_3
    //   619: new 238	cn/com/fmsh/script/exception/FMScriptHandleException
    //   622: dup
    //   623: new 124	java/lang/StringBuilder
    //   626: dup
    //   627: ldc_w 292
    //   630: iconst_4
    //   631: invokestatic 251	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   634: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   637: aload_3
    //   638: invokestatic 216	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   641: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   644: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   647: invokespecial 261	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   650: athrow
    //   651: aload_0
    //   652: getfield 73	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:j	Lorg/simalliance/openmobileapi/Channel;
    //   655: aload_1
    //   656: invokevirtual 295	org/simalliance/openmobileapi/Channel:transmit	([B)[B
    //   659: astore 5
    //   661: aload_0
    //   662: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   665: ifnull +43 -> 708
    //   668: aload_0
    //   669: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   672: aload_0
    //   673: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   676: new 124	java/lang/StringBuilder
    //   679: dup
    //   680: ldc_w 297
    //   683: iconst_4
    //   684: bipush 114
    //   686: invokestatic 302	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   689: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   692: aload 5
    //   694: invokestatic 141	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   697: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   703: invokeinterface 71 3 0
    //   708: aload 5
    //   710: areturn
    //   711: astore 4
    //   713: aload_0
    //   714: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   717: ifnull +43 -> 760
    //   720: aload_0
    //   721: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   724: aload_0
    //   725: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   728: new 124	java/lang/StringBuilder
    //   731: dup
    //   732: ldc_w 304
    //   735: sipush 150
    //   738: invokestatic 251	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   741: invokespecial 135	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   744: aload 4
    //   746: invokestatic 216	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   749: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   752: invokevirtual 150	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   755: invokeinterface 193 3 0
    //   760: new 238	cn/com/fmsh/script/exception/FMScriptHandleException
    //   763: dup
    //   764: aload 4
    //   766: invokevirtual 305	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   769: invokespecial 261	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   772: athrow
    //   773: aload_0
    //   774: getfield 46	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:c	Lcn/com/fmsh/util/log/FMLog;
    //   777: aload_0
    //   778: getfield 42	cn/com/fmsh/nfcos/client/service/business/OpenMobileApduHandler:a	Ljava/lang/String;
    //   781: ldc_w 307
    //   784: iconst_1
    //   785: invokestatic 132	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   788: invokeinterface 71 3 0
    //   793: goto -440 -> 353
    //
    // Exception table:
    //   from	to	target	type
    //   651	661	209	java/lang/IllegalStateException
    //   651	661	269	java/lang/IllegalArgumentException
    //   651	661	556	java/io/IOException
    //   416	478	618	java/lang/Exception
    //   651	661	711	java/lang/Exception
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.business.OpenMobileApduHandler
 * JD-Core Version:    0.6.0
 */