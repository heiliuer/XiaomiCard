package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.message.IMessage;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.tsm.business.CardAppTrade;
import cn.com.fmsh.tsm.business.bean.Activity;
import cn.com.fmsh.tsm.business.bean.BusinessOrder;
import cn.com.fmsh.tsm.business.bean.CardAppInfo;
import cn.com.fmsh.tsm.business.bean.CardAppRecord;
import cn.com.fmsh.tsm.business.bean.LoginInfo;
import cn.com.fmsh.tsm.business.bean.MainOrder;
import cn.com.fmsh.tsm.business.bean.PayOrder;
import cn.com.fmsh.tsm.business.bean.PreDepositInfo;
import cn.com.fmsh.tsm.business.bean.TerminalBackInfo;
import cn.com.fmsh.tsm.business.card.CardManagerFactory;
import cn.com.fmsh.tsm.business.card.base.CardManager;
import cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform;
import cn.com.fmsh.tsm.business.enums.EnumBackInfoType;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
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
import cs;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CardAppTradeImpl
  implements CardAppTrade
{
  public FMLog b = null;

  public CardAppTradeImpl(CardBusinessBasic paramCardBusinessBasic)
  {
    this.d = paramCardBusinessBasic;
    this.b = LogFactory.getInstance().getLog();
  }

  public MainOrder apply4Pay(int paramInt1, int paramInt2, byte[] paramArrayOfByte, EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    int i = 0;
    label619: MainOrder localMainOrder;
    try
    {
      String str1 = FM_Utils.copyValueOf(204, 22, "诵匘田诮");
      if (this.b == null)
        this.b = LogFactory.getInstance().getLog();
      if (this.b != null)
        this.b.debug(this.c, FM_Bytes.startsWith("讵匝番讽u\"3", 182, 17));
      while (true)
      {
        if (this.b != null)
          this.b.warn(this.c, str1 + FM_Utils.copyValueOf(114, 123, "斫ｔ佳儫盍历支彘幭"));
        throw new BusinessException(str1 + Util4Java.toString("旷＋伭其皝叝敵弉帩", 2, 6), BusinessException.ErrorMessage.local_business_para_error);
        label669: 
        do
        {
          if (this.d == null)
          {
            if (this.b != null)
              this.b.warn(this.c, str1 + FM_Bytes.startsWith("旫５丏劰変琏导豠丧穣", 188, 124));
            throw new BusinessException(str1 + FM_Exception.getChars(4, 50, "斫＃乛劲奁瑑噡剆姆匉奠账"), BusinessException.ErrorMessage.local_business_init_fail);
            Configration localConfigration = this.d.getConfigration();
            if (localConfigration == null)
              break label619;
            i = localConfigration.getOrderSource();
            if ((i == 0) && (this.b != null))
              this.b.warn(this.c, str1 + FM_Exception.getChars(2, 111, "断ｆ读匝杲準圽酉缽旅产乭朥宄乄"));
          }
          String str2;
          ApduHandler localApduHandler;
          IMessageHandler localIMessageHandler;
          while (true)
          {
            str2 = this.d.getServer4Business(1112);
            if (str2 != null)
              break label669;
            if (this.b != null)
              this.b.warn(this.c, str1 + FM_Utils.copyValueOf(2, 78, "旻ｗ莾厁奁琕盅幼厭奺贼"));
            throw new BusinessException(str1 + CRCUtil.valueOf(170, "时＝莵厅奀瑓盂幤司夨贯"), BusinessException.ErrorMessage.local_app_query_server_fail);
            localApduHandler = this.d.getApduHandler();
            if (localApduHandler == null)
            {
              if (this.b != null)
                this.b.error(this.c, str1 + FM_Exception.getChars(3, 89, "斪ｙOW\004L外瑍嘬丧稬"));
              this.d.throwExceptionAndClose(str1 + Util4Java.toString("旹／诠元剘挱匦盟诰閭斮弄7\\J\032`\r\021\b6", 48, 116), BusinessException.ErrorMessage.local_business_apdu_handler_null, false);
            }
            localIMessageHandler = this.d.getMessageHandler();
            if (localIMessageHandler != null)
              break;
            if (this.b != null)
              this.b.warn(this.c, str1 + FM_Utils.copyValueOf(2, 56, "旻ｉ淕恺奉球嘵丯稷"));
            throw new BusinessException(str1 + FM_Long.concat("斪＋涚恲夌琕噶乳種", 4), BusinessException.ErrorMessage.local_message_load_config_fail);
            if (this.b == null)
              continue;
            this.b.warn(this.c, str1 + FM_Exception.getChars(3, 126, "斪ｖ杲戨剤鄟缾旉人"));
          }
          this.d.businessReady(str1, str2);
          localMainOrder = a(i, paramInt1, paramInt2, paramEnumCardAppType.getId(), paramArrayOfByte, localIMessageHandler, localApduHandler, str2);
          this.d.businessFinish(false);
          break label734;
          if ((paramInt1 < 0) || (paramInt2 < 0))
            break;
        }
        while (paramEnumCardAppType != null);
      }
    }
    catch (cs localcs)
    {
      localMainOrder = null;
    }
    label734: return localMainOrder;
  }

  public MainOrder applyAct4Pay(byte[] paramArrayOfByte1, EnumCardAppType paramEnumCardAppType, byte[] paramArrayOfByte2)
    throws BusinessException
  {
    String str1 = FM_Utils.copyValueOf(2, 43, "洶劰诡卛甪讳");
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Exception.getChars(4, 97, "浦勶讽单甲讵m*+"));
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length < 1))
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Utils.copyValueOf(2, 37, "旻＞杽佼兤浽勣缆研"));
      throw new BusinessException(str1 + FM_Bytes.startsWith("斫．佧兩盕叔攫彂帽", 92, 69), BusinessException.ErrorMessage.local_business_para_error);
    }
    int i;
    String str2;
    ApduHandler localApduHandler;
    IMessageHandler localIMessageHandler;
    while (true)
    {
      if (this.d == null)
      {
        if (this.b != null)
          this.b.warn(this.c, str1 + CRCUtil.valueOf(5, "断｀乇勯夛琖寸豳丹種"));
        throw new BusinessException(str1 + FM_Int.lastIndexOf(5, "斮ｕ乀勺奘瑛嘶剂始北夳账"), BusinessException.ErrorMessage.local_business_init_fail);
      }
      while (true)
      {
        Configration localConfigration = this.d.getConfigration();
        if (localConfigration == null)
        {
          if (this.b != null)
            this.b.warn(this.c, str1 + FM_Utils.copyValueOf(160, 12, "旽；鄎缡侺怨宪谾丱穭"));
          throw new BusinessException(str1 + Util4Java.toString("旷｜酒缠俼恣客豫乣稲", 2, 79), BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        i = localConfigration.getOrderSource();
        if ((i == 0) && (this.b != null))
          this.b.warn(this.c, str1 + FM_CN.subSequence("旴＝订博杻滝坴鄆缴斎仮个朼察丝", 244));
        str2 = this.d.getServer4Business(1142);
        if (str2 == null)
        {
          if (this.b != null)
            this.b.warn(this.c, str1 + FM_Exception.getChars(188, 89, "旣＂菰厖夝瑔盏幷厭奧贪"));
          throw new BusinessException(str1 + FM_Int.lastIndexOf(6, "斯ｖ菬厊奙瑘盛平叱夳账"), BusinessException.ErrorMessage.local_app_query_server_fail);
          localApduHandler = this.d.getApduHandler();
          if (localApduHandler == null)
          {
            if (this.b != null)
              this.b.error(this.c, str1 + FM_Bytes.startsWith("旲ｄM��P\r夘瑆噬乲究", 3, 68));
            this.d.throwExceptionAndClose(str1 + Util4Java.toString("旵＊设億刈捰匴盜认闰旸弋/\005��Q<\030\037_6", 4, 35), BusinessException.ErrorMessage.local_business_apdu_handler_null, false);
          }
          localIMessageHandler = this.d.getMessageHandler();
          if (localIMessageHandler != null)
            continue;
          if (this.b != null)
            this.b.warn(this.c, str1 + FM_Bytes.startsWith("旿＜涟恱夁琊噻丠稻", 136, 7));
          throw new BusinessException(str1 + FM_Int.lastIndexOf(3, "斠｛淐怶奞瑝嘴乧稤"), BusinessException.ErrorMessage.local_message_load_config_fail);
          if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length >= 1))
            break;
          if (this.b != null)
            this.b.warn(this.c, str1 + BCCUtil.endsWith("旲ｒ杲佲儩浽勨阞勴放挦", 5, 122));
          throw new BusinessException(str1 + FM_Exception.getChars(5, 40, "斨＊佮儳皚厄放弔带"), BusinessException.ErrorMessage.local_business_para_error);
        }
      }
    }
    this.d.businessReady(str1, str2);
    MainOrder localMainOrder = a(i, paramEnumCardAppType.getId(), paramArrayOfByte1, paramArrayOfByte2, localIMessageHandler, localApduHandler, str2);
    this.d.businessFinish(false);
    return localMainOrder;
  }

  // ERROR //
  public int deleteTerminalInfoBack(byte[] paramArrayOfByte)
    throws BusinessException
  {
    // Byte code:
    //   0: iconst_5
    //   1: bipush 40
    //   3: ldc_w 949
    //   6: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   9: astore_2
    //   10: aload_0
    //   11: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   14: ifnonnull +13 -> 27
    //   17: aload_0
    //   18: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   21: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   24: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   27: aload_0
    //   28: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   31: ifnull +17 -> 48
    //   34: aload_0
    //   35: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   38: aload_0
    //   39: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   42: aload_2
    //   43: invokeinterface 914 3 0
    //   48: aload_1
    //   49: ifnonnull +281 -> 330
    //   52: aload_0
    //   53: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   56: ifnull +42 -> 98
    //   59: aload_0
    //   60: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   63: aload_0
    //   64: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   67: new 143	java/lang/StringBuilder
    //   70: dup
    //   71: aload_2
    //   72: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   75: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   78: ldc_w 951
    //   81: sipush 224
    //   84: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   87: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: invokeinterface 223 3 0
    //   98: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   101: dup
    //   102: new 143	java/lang/StringBuilder
    //   105: dup
    //   106: aload_2
    //   107: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   110: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   113: iconst_2
    //   114: bipush 61
    //   116: ldc_w 953
    //   119: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   122: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   131: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   134: athrow
    //   135: astore 14
    //   137: aload_0
    //   138: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   141: ifnull +50 -> 191
    //   144: aload_0
    //   145: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   148: aload_0
    //   149: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   152: new 143	java/lang/StringBuilder
    //   155: dup
    //   156: aload_2
    //   157: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   160: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   163: ldc_w 955
    //   166: iconst_3
    //   167: bipush 57
    //   169: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   172: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: aload 14
    //   177: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   180: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: invokeinterface 223 3 0
    //   191: aload_0
    //   192: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   195: new 143	java/lang/StringBuilder
    //   198: dup
    //   199: aload_2
    //   200: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   203: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   206: iconst_4
    //   207: ldc_w 957
    //   210: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   213: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   219: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   222: iconst_0
    //   223: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   226: aload_0
    //   227: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   230: aload 5
    //   232: aload_2
    //   233: iconst_0
    //   234: aload 4
    //   236: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   239: astore 11
    //   241: aload 11
    //   243: iconst_2
    //   244: invokestatic 278	java/util/Arrays:copyOf	([BI)[B
    //   247: astore 12
    //   249: aload_0
    //   250: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   253: iconst_0
    //   254: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   257: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   260: aload 12
    //   262: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   265: ifne +435 -> 700
    //   268: aload_0
    //   269: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   272: ifnull +48 -> 320
    //   275: aload_0
    //   276: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   279: aload_0
    //   280: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   283: new 143	java/lang/StringBuilder
    //   286: dup
    //   287: aload_2
    //   288: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   291: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   294: iconst_2
    //   295: ldc_w 959
    //   298: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   301: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: aload 11
    //   306: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   309: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   312: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   315: invokeinterface 172 3 0
    //   320: aload 12
    //   322: invokestatic 962	cn/com/fmsh/util/FM_CN:bcdBytesToInt	([B)I
    //   325: istore 13
    //   327: iload 13
    //   329: ireturn
    //   330: aload_0
    //   331: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   334: ifnonnull +202 -> 536
    //   337: aload_0
    //   338: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   341: ifnonnull +13 -> 354
    //   344: aload_0
    //   345: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   348: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   351: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   354: aload_0
    //   355: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   358: ifnull +40 -> 398
    //   361: aload_0
    //   362: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   365: aload_0
    //   366: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   369: new 143	java/lang/StringBuilder
    //   372: dup
    //   373: aload_2
    //   374: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   377: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   380: iconst_1
    //   381: ldc_w 964
    //   384: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   387: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   393: invokeinterface 223 3 0
    //   398: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   401: dup
    //   402: new 143	java/lang/StringBuilder
    //   405: dup
    //   406: aload_2
    //   407: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   410: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   413: bipush 102
    //   415: ldc_w 966
    //   418: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   421: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   427: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   430: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   433: athrow
    //   434: aload_0
    //   435: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   438: sipush 2122
    //   441: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   444: astore 4
    //   446: aload 4
    //   448: ifnonnull +183 -> 631
    //   451: aload_0
    //   452: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   455: ifnull +42 -> 497
    //   458: aload_0
    //   459: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   462: aload_0
    //   463: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   466: new 143	java/lang/StringBuilder
    //   469: dup
    //   470: aload_2
    //   471: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   474: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   477: sipush 232
    //   480: ldc_w 968
    //   483: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   486: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   489: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   492: invokeinterface 223 3 0
    //   497: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   500: dup
    //   501: new 143	java/lang/StringBuilder
    //   504: dup
    //   505: aload_2
    //   506: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   509: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   512: ldc_w 970
    //   515: sipush 162
    //   518: bipush 110
    //   520: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   523: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   526: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   529: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   532: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   535: athrow
    //   536: aload_0
    //   537: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   540: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   543: astore_3
    //   544: aload_3
    //   545: ifnonnull -111 -> 434
    //   548: aload_0
    //   549: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   552: ifnull +43 -> 595
    //   555: aload_0
    //   556: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   559: aload_0
    //   560: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   563: new 143	java/lang/StringBuilder
    //   566: dup
    //   567: aload_2
    //   568: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   571: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   574: bipush 6
    //   576: bipush 72
    //   578: ldc_w 972
    //   581: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   584: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   587: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   590: invokeinterface 223 3 0
    //   595: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   598: dup
    //   599: new 143	java/lang/StringBuilder
    //   602: dup
    //   603: aload_2
    //   604: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   607: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   610: iconst_4
    //   611: iconst_4
    //   612: ldc_w 974
    //   615: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   618: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   624: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   627: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   630: athrow
    //   631: aload_0
    //   632: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   635: aload_2
    //   636: aload 4
    //   638: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   641: aconst_null
    //   642: astore 5
    //   644: aload_3
    //   645: sipush 4021
    //   648: invokeinterface 59 2 0
    //   653: astore 6
    //   655: aload_3
    //   656: bipush 81
    //   658: invokeinterface 63 2 0
    //   663: astore 7
    //   665: aload 7
    //   667: aload_1
    //   668: invokeinterface 91 2 0
    //   673: pop
    //   674: aload 6
    //   676: aload 7
    //   678: invokeinterface 75 2 0
    //   683: pop
    //   684: aload 6
    //   686: invokeinterface 117 1 0
    //   691: astore 10
    //   693: aload 10
    //   695: astore 5
    //   697: goto -471 -> 226
    //   700: iconst_0
    //   701: istore 13
    //   703: goto -376 -> 327
    //
    // Exception table:
    //   from	to	target	type
    //   655	693	135	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public int doRefund(byte[] paramArrayOfByte)
    throws BusinessException
  {
    // Byte code:
    //   0: ldc_w 977
    //   3: iconst_2
    //   4: bipush 97
    //   6: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   9: astore_2
    //   10: aload_0
    //   11: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   14: ifnonnull +13 -> 27
    //   17: aload_0
    //   18: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   21: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   24: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   27: aload_0
    //   28: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   31: ifnull +25 -> 56
    //   34: aload_0
    //   35: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   38: aload_0
    //   39: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   42: iconst_2
    //   43: bipush 58
    //   45: ldc_w 979
    //   48: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   51: invokeinterface 914 3 0
    //   56: aload_0
    //   57: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   60: ifnonnull +69 -> 129
    //   63: aload_0
    //   64: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   67: ifnonnull +13 -> 80
    //   70: aload_0
    //   71: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   74: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   77: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   80: aload_0
    //   81: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   84: ifnull +25 -> 109
    //   87: aload_0
    //   88: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   91: aload_0
    //   92: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   95: iconst_4
    //   96: bipush 10
    //   98: ldc_w 981
    //   101: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   104: invokeinterface 223 3 0
    //   109: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   112: dup
    //   113: ldc_w 983
    //   116: iconst_4
    //   117: bipush 112
    //   119: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   122: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   125: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   128: athrow
    //   129: aload_0
    //   130: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   133: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   136: astore_3
    //   137: aload_3
    //   138: ifnonnull +235 -> 373
    //   141: aload_0
    //   142: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   145: ifnull +25 -> 170
    //   148: aload_0
    //   149: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   152: aload_0
    //   153: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   156: iconst_4
    //   157: bipush 112
    //   159: ldc_w 985
    //   162: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   165: invokeinterface 223 3 0
    //   170: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   173: dup
    //   174: ldc_w 987
    //   177: iconst_5
    //   178: bipush 42
    //   180: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   183: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   186: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   189: athrow
    //   190: iconst_0
    //   191: istore 13
    //   193: iload 13
    //   195: ireturn
    //   196: astore 14
    //   198: aload_0
    //   199: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   202: ifnull +45 -> 247
    //   205: aload_0
    //   206: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   209: aload_0
    //   210: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   213: new 143	java/lang/StringBuilder
    //   216: dup
    //   217: ldc_w 989
    //   220: sipush 300
    //   223: bipush 72
    //   225: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   228: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   231: aload 14
    //   233: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   236: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: invokeinterface 223 3 0
    //   247: aload_0
    //   248: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   251: ldc_w 991
    //   254: iconst_5
    //   255: bipush 107
    //   257: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   260: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   263: iconst_0
    //   264: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   267: aload_0
    //   268: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   271: aload 5
    //   273: ldc_w 993
    //   276: sipush 250
    //   279: iconst_4
    //   280: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   283: iconst_0
    //   284: aload 4
    //   286: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   289: astore 11
    //   291: aload 11
    //   293: iconst_2
    //   294: invokestatic 278	java/util/Arrays:copyOf	([BI)[B
    //   297: astore 12
    //   299: aload_0
    //   300: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   303: iconst_0
    //   304: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   307: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   310: aload 12
    //   312: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   315: ifne -125 -> 190
    //   318: aload_0
    //   319: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   322: ifnull +41 -> 363
    //   325: aload_0
    //   326: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   329: aload_0
    //   330: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   333: new 143	java/lang/StringBuilder
    //   336: dup
    //   337: ldc_w 995
    //   340: iconst_4
    //   341: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   344: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   347: aload 11
    //   349: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   352: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   358: invokeinterface 172 3 0
    //   363: aload 12
    //   365: invokestatic 962	cn/com/fmsh/util/FM_CN:bcdBytesToInt	([B)I
    //   368: istore 13
    //   370: goto -177 -> 193
    //   373: aload_0
    //   374: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   377: sipush 2122
    //   380: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   383: astore 4
    //   385: aload 4
    //   387: ifnonnull +84 -> 471
    //   390: aload_0
    //   391: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   394: ifnull +40 -> 434
    //   397: aload_0
    //   398: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   401: aload_0
    //   402: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   405: new 143	java/lang/StringBuilder
    //   408: dup
    //   409: aload_2
    //   410: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   413: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   416: iconst_3
    //   417: ldc_w 997
    //   420: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   423: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   429: invokeinterface 223 3 0
    //   434: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   437: dup
    //   438: new 143	java/lang/StringBuilder
    //   441: dup
    //   442: aload_2
    //   443: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   446: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   449: ldc_w 999
    //   452: iconst_2
    //   453: bipush 96
    //   455: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   458: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   461: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   464: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   467: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   470: athrow
    //   471: aload_0
    //   472: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   475: aload_2
    //   476: aload 4
    //   478: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   481: aconst_null
    //   482: astore 5
    //   484: aload_3
    //   485: sipush 2122
    //   488: invokeinterface 59 2 0
    //   493: astore 6
    //   495: aload_3
    //   496: bipush 105
    //   498: invokeinterface 63 2 0
    //   503: astore 7
    //   505: aload 7
    //   507: aload_1
    //   508: invokeinterface 91 2 0
    //   513: pop
    //   514: aload 6
    //   516: aload 7
    //   518: invokeinterface 75 2 0
    //   523: pop
    //   524: aload 6
    //   526: invokeinterface 117 1 0
    //   531: astore 10
    //   533: aload 10
    //   535: astore 5
    //   537: goto -270 -> 267
    //
    // Exception table:
    //   from	to	target	type
    //   495	533	196	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public int doUnsolvedOrder(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws BusinessException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   4: ifnull +27 -> 31
    //   7: aload_0
    //   8: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   11: aload_0
    //   12: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   15: ldc_w 1003
    //   18: sipush 152
    //   21: bipush 95
    //   23: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   26: invokeinterface 223 3 0
    //   31: ldc_w 1005
    //   34: sipush 164
    //   37: bipush 87
    //   39: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   42: astore_3
    //   43: aload_0
    //   44: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   47: ifnonnull +13 -> 60
    //   50: aload_0
    //   51: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   54: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   57: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   60: aload_0
    //   61: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   64: ifnull +25 -> 89
    //   67: aload_0
    //   68: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   71: aload_0
    //   72: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   75: iconst_1
    //   76: bipush 121
    //   78: ldc_w 1007
    //   81: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   84: invokeinterface 914 3 0
    //   89: aload_0
    //   90: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   93: ifnonnull +520 -> 613
    //   96: aload_0
    //   97: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   100: ifnull +25 -> 125
    //   103: aload_0
    //   104: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   107: aload_0
    //   108: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   111: iconst_4
    //   112: bipush 56
    //   114: ldc_w 1009
    //   117: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   120: invokeinterface 223 3 0
    //   125: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   128: dup
    //   129: ldc_w 1011
    //   132: iconst_2
    //   133: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   136: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   139: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   142: athrow
    //   143: aload 5
    //   145: invokeinterface 658 1 0
    //   150: ifne +48 -> 198
    //   153: aload_0
    //   154: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   157: ifnull +23 -> 180
    //   160: aload_0
    //   161: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   164: aload_0
    //   165: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   168: ldc_w 1013
    //   171: iconst_1
    //   172: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   175: invokeinterface 172 3 0
    //   180: aload_0
    //   181: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   184: ldc_w 1013
    //   187: iconst_1
    //   188: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   191: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   194: iconst_1
    //   195: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   198: aload_0
    //   199: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   202: sipush 3021
    //   205: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   208: astore 6
    //   210: aload 6
    //   212: ifnonnull +650 -> 862
    //   215: aload_0
    //   216: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   219: ifnull +42 -> 261
    //   222: aload_0
    //   223: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   226: aload_0
    //   227: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   230: new 143	java/lang/StringBuilder
    //   233: dup
    //   234: aload_3
    //   235: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   238: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   241: iconst_5
    //   242: bipush 26
    //   244: ldc_w 1015
    //   247: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   250: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   256: invokeinterface 223 3 0
    //   261: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   264: dup
    //   265: new 143	java/lang/StringBuilder
    //   268: dup
    //   269: aload_3
    //   270: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   273: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   276: bipush 118
    //   278: bipush 106
    //   280: ldc_w 1017
    //   283: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   286: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   292: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   295: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   298: athrow
    //   299: astore 35
    //   301: aload_0
    //   302: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   305: ifnull +25 -> 330
    //   308: aload_0
    //   309: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   312: aload_0
    //   313: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   316: ldc_w 1019
    //   319: iconst_3
    //   320: bipush 17
    //   322: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   325: invokeinterface 223 3 0
    //   330: aload_0
    //   331: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   334: ldc_w 1021
    //   337: iconst_3
    //   338: bipush 52
    //   340: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   343: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   346: iconst_1
    //   347: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   350: aload_0
    //   351: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   354: ifnull +45 -> 399
    //   357: aload_0
    //   358: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   361: aload_0
    //   362: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   365: new 143	java/lang/StringBuilder
    //   368: dup
    //   369: ldc_w 1023
    //   372: sipush 222
    //   375: bipush 82
    //   377: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   380: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   383: aload 30
    //   385: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   388: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   391: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   394: invokeinterface 361 3 0
    //   399: aload_0
    //   400: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   403: ifnull +25 -> 428
    //   406: aload_0
    //   407: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   410: aload_0
    //   411: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   414: ldc_w 1025
    //   417: iconst_2
    //   418: bipush 78
    //   420: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   423: invokeinterface 361 3 0
    //   428: aload_0
    //   429: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   432: aload 30
    //   434: aload_3
    //   435: iconst_1
    //   436: aload 6
    //   438: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   441: astore 10
    //   443: aload 10
    //   445: iconst_2
    //   446: invokestatic 278	java/util/Arrays:copyOf	([BI)[B
    //   449: astore 33
    //   451: aload 25
    //   453: astore 34
    //   455: aload 33
    //   457: astore 13
    //   459: aload 34
    //   461: astore 12
    //   463: getstatic 606	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:CARD_REQUEST	[B
    //   466: aload 13
    //   468: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   471: ifne +710 -> 1181
    //   474: aload_0
    //   475: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   478: iconst_1
    //   479: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   482: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   485: aload 13
    //   487: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   490: ifne +1021 -> 1511
    //   493: aload_0
    //   494: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   497: ifnull +45 -> 542
    //   500: aload_0
    //   501: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   504: aload_0
    //   505: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   508: new 143	java/lang/StringBuilder
    //   511: dup
    //   512: sipush 232
    //   515: bipush 98
    //   517: ldc_w 1027
    //   520: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   523: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   526: aload 10
    //   528: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   531: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   534: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   537: invokeinterface 223 3 0
    //   542: aload 13
    //   544: invokestatic 962	cn/com/fmsh/util/FM_CN:bcdBytesToInt	([B)I
    //   547: istore 48
    //   549: iload 48
    //   551: ireturn
    //   552: aload_0
    //   553: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   556: invokevirtual 514	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getApduHandler	()Lcn/com/fmsh/script/ApduHandler;
    //   559: astore 5
    //   561: aload 5
    //   563: ifnonnull +888 -> 1451
    //   566: aload_0
    //   567: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   570: ifnull +25 -> 595
    //   573: aload_0
    //   574: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   577: aload_0
    //   578: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   581: ldc_w 1029
    //   584: iconst_2
    //   585: bipush 123
    //   587: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   590: invokeinterface 223 3 0
    //   595: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   598: dup
    //   599: ldc_w 1031
    //   602: iconst_5
    //   603: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   606: getstatic 521	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_null	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   609: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   612: athrow
    //   613: aload_0
    //   614: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   617: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   620: astore 4
    //   622: aload 4
    //   624: ifnonnull -72 -> 552
    //   627: aload_0
    //   628: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   631: ifnull +25 -> 656
    //   634: aload_0
    //   635: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   638: aload_0
    //   639: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   642: iconst_4
    //   643: bipush 70
    //   645: ldc_w 1033
    //   648: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   651: invokeinterface 223 3 0
    //   656: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   659: dup
    //   660: iconst_2
    //   661: ldc_w 1035
    //   664: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   667: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   670: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   673: athrow
    //   674: astore 38
    //   676: aload_0
    //   677: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   680: ifnull +43 -> 723
    //   683: aload_0
    //   684: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   687: aload_0
    //   688: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   691: new 143	java/lang/StringBuilder
    //   694: dup
    //   695: iconst_1
    //   696: bipush 120
    //   698: ldc_w 1037
    //   701: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   704: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   707: aload 38
    //   709: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   712: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   715: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   718: invokeinterface 223 3 0
    //   723: aload_0
    //   724: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   727: iconst_3
    //   728: iconst_5
    //   729: ldc_w 1039
    //   732: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   735: getstatic 1042	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_invalid_response	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   738: iconst_1
    //   739: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   742: aload_0
    //   743: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   746: invokevirtual 546	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getScriptHandler	()Lcn/com/fmsh/script/ScriptHandler;
    //   749: aload 16
    //   751: invokeinterface 552 2 0
    //   756: astore 37
    //   758: aload 37
    //   760: astore 14
    //   762: aload 14
    //   764: ifnull +12 -> 776
    //   767: aload 14
    //   769: invokevirtual 555	cn/com/fmsh/script/bean/ApduReponseList:size	()I
    //   772: iconst_1
    //   773: if_icmpge +52 -> 825
    //   776: aload_0
    //   777: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   780: ifnull +25 -> 805
    //   783: aload_0
    //   784: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   787: aload_0
    //   788: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   791: ldc_w 1044
    //   794: iconst_3
    //   795: bipush 27
    //   797: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   800: invokeinterface 223 3 0
    //   805: aload_0
    //   806: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   809: ldc_w 1046
    //   812: iconst_5
    //   813: bipush 29
    //   815: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   818: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   821: iconst_1
    //   822: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   825: aconst_null
    //   826: astore 30
    //   828: iload 15
    //   830: ifne +687 -> 1517
    //   833: aload 25
    //   835: aload 14
    //   837: invokevirtual 1049	cn/com/fmsh/script/bean/ApduReponseList:toTag4A2	()Lcn/com/fmsh/communication/message/ITag;
    //   840: invokeinterface 75 2 0
    //   845: pop
    //   846: aload 25
    //   848: invokeinterface 117 1 0
    //   853: astore 32
    //   855: aload 32
    //   857: astore 30
    //   859: goto -509 -> 350
    //   862: aload_0
    //   863: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   866: aload_3
    //   867: aload 6
    //   869: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   872: aconst_null
    //   873: astore 7
    //   875: aload 4
    //   877: sipush 3021
    //   880: invokeinterface 59 2 0
    //   885: astore 8
    //   887: aload 4
    //   889: bipush 17
    //   891: invokeinterface 63 2 0
    //   896: astore 49
    //   898: aload 49
    //   900: aload_1
    //   901: invokeinterface 91 2 0
    //   906: pop
    //   907: aload 8
    //   909: aload 49
    //   911: invokeinterface 75 2 0
    //   916: pop
    //   917: aload 8
    //   919: invokeinterface 117 1 0
    //   924: astore 52
    //   926: aload 52
    //   928: astore 7
    //   930: aload_0
    //   931: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   934: aload 7
    //   936: iconst_1
    //   937: ldc_w 1051
    //   940: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   943: iconst_1
    //   944: aload 6
    //   946: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   949: astore 10
    //   951: aload 10
    //   953: iconst_2
    //   954: invokestatic 278	java/util/Arrays:copyOf	([BI)[B
    //   957: astore 11
    //   959: aconst_null
    //   960: astore 12
    //   962: aload 11
    //   964: astore 13
    //   966: aconst_null
    //   967: astore 14
    //   969: iconst_0
    //   970: istore 15
    //   972: goto -509 -> 463
    //   975: astore 47
    //   977: aconst_null
    //   978: astore 20
    //   980: aload 12
    //   982: astore 21
    //   984: aload 47
    //   986: astore 22
    //   988: aconst_null
    //   989: astore 23
    //   991: aload_0
    //   992: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   995: ifnull +54 -> 1049
    //   998: aload_0
    //   999: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1002: ldc_w 1053
    //   1005: sipush 146
    //   1008: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   1011: new 143	java/lang/StringBuilder
    //   1014: dup
    //   1015: aload_3
    //   1016: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1019: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1022: iconst_4
    //   1023: iconst_2
    //   1024: ldc_w 1055
    //   1027: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   1030: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1033: aload 22
    //   1035: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   1038: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1041: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1044: invokeinterface 223 3 0
    //   1049: aload_0
    //   1050: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   1053: sipush 214
    //   1056: ldc_w 1057
    //   1059: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   1062: getstatic 302	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_command_data_invaild	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   1065: iconst_1
    //   1066: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   1069: aload 23
    //   1071: astore 24
    //   1073: aload 21
    //   1075: astore 25
    //   1077: aload 20
    //   1079: ifnull +523 -> 1602
    //   1082: aload 20
    //   1084: astore 39
    //   1086: iconst_0
    //   1087: istore 26
    //   1089: aload 39
    //   1091: astore 27
    //   1093: aload 24
    //   1095: ifnull +496 -> 1591
    //   1098: iconst_1
    //   1099: istore 15
    //   1101: aload 16
    //   1103: aload 24
    //   1105: invokevirtual 542	cn/com/fmsh/script/bean/ApduRequestList:fromTag	(Lcn/com/fmsh/communication/message/ITag;)V
    //   1108: goto -366 -> 742
    //   1111: astore 28
    //   1113: aload_0
    //   1114: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1117: ifnull +41 -> 1158
    //   1120: aload_0
    //   1121: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1124: aload_0
    //   1125: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   1128: new 143	java/lang/StringBuilder
    //   1131: dup
    //   1132: ldc_w 1059
    //   1135: iconst_5
    //   1136: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   1139: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1142: aload 28
    //   1144: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   1147: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1150: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1153: invokeinterface 223 3 0
    //   1158: aload_0
    //   1159: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   1162: iconst_4
    //   1163: bipush 15
    //   1165: ldc_w 1061
    //   1168: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   1171: getstatic 1042	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_communication_invalid_response	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   1174: iconst_1
    //   1175: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   1178: goto -436 -> 742
    //   1181: new 538	cn/com/fmsh/script/bean/ApduRequestList
    //   1184: dup
    //   1185: invokespecial 539	cn/com/fmsh/script/bean/ApduRequestList:<init>	()V
    //   1188: astore 16
    //   1190: aload 4
    //   1192: aload 10
    //   1194: invokeinterface 613 2 0
    //   1199: astore 17
    //   1201: aload 17
    //   1203: bipush 160
    //   1205: invokeinterface 195 2 0
    //   1210: astore 18
    //   1212: aload 17
    //   1214: bipush 161
    //   1216: invokeinterface 195 2 0
    //   1221: astore 40
    //   1223: aload 17
    //   1225: bipush 166
    //   1227: invokeinterface 195 2 0
    //   1232: astore 42
    //   1234: aload 17
    //   1236: bipush 167
    //   1238: invokeinterface 195 2 0
    //   1243: astore 43
    //   1245: aload 4
    //   1247: sipush 9001
    //   1250: invokeinterface 59 2 0
    //   1255: astore 44
    //   1257: aload 44
    //   1259: astore 25
    //   1261: aload 25
    //   1263: aload 42
    //   1265: invokeinterface 75 2 0
    //   1270: pop
    //   1271: aload 25
    //   1273: aload 43
    //   1275: invokeinterface 75 2 0
    //   1280: pop
    //   1281: aload 40
    //   1283: astore 24
    //   1285: aload 18
    //   1287: astore 20
    //   1289: goto -212 -> 1077
    //   1292: astore 29
    //   1294: aload_0
    //   1295: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1298: ifnull +23 -> 1321
    //   1301: aload_0
    //   1302: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1305: aload_0
    //   1306: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   1309: iconst_3
    //   1310: ldc_w 1063
    //   1313: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   1316: invokeinterface 223 3 0
    //   1321: aload 29
    //   1323: ifnull +107 -> 1430
    //   1326: getstatic 1069	cn/com/fmsh/script/exception/FMScriptHandleException$ScriptHandleExceptionType:STOPED	Lcn/com/fmsh/script/exception/FMScriptHandleException$ScriptHandleExceptionType;
    //   1329: aload 29
    //   1331: invokevirtual 1073	cn/com/fmsh/script/exception/FMScriptHandleException:getType	()Lcn/com/fmsh/script/exception/FMScriptHandleException$ScriptHandleExceptionType;
    //   1334: if_acmpne +96 -> 1430
    //   1337: aload_0
    //   1338: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   1341: iconst_5
    //   1342: bipush 115
    //   1344: ldc_w 1075
    //   1347: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   1350: getstatic 1078	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_cancel	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   1353: iconst_1
    //   1354: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   1357: goto -595 -> 762
    //   1360: astore 9
    //   1362: aload_0
    //   1363: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1366: ifnull +43 -> 1409
    //   1369: aload_0
    //   1370: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1373: aload_0
    //   1374: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   1377: new 143	java/lang/StringBuilder
    //   1380: dup
    //   1381: ldc_w 1080
    //   1384: iconst_4
    //   1385: bipush 91
    //   1387: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   1390: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1393: aload 9
    //   1395: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   1398: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1401: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1404: invokeinterface 223 3 0
    //   1409: aload_0
    //   1410: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   1413: ldc_w 1082
    //   1416: iconst_5
    //   1417: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   1420: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   1423: iconst_1
    //   1424: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   1427: goto -497 -> 930
    //   1430: aload_0
    //   1431: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   1434: ldc_w 1084
    //   1437: iconst_4
    //   1438: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   1441: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   1444: iconst_1
    //   1445: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   1448: goto -686 -> 762
    //   1451: aload 5
    //   1453: invokeinterface 525 1 0
    //   1458: ifeq -1315 -> 143
    //   1461: aload_0
    //   1462: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1465: ifnull +23 -> 1488
    //   1468: aload_0
    //   1469: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1472: aload_0
    //   1473: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   1476: ldc_w 1086
    //   1479: iconst_2
    //   1480: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   1483: invokeinterface 172 3 0
    //   1488: aload_0
    //   1489: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   1492: iconst_3
    //   1493: bipush 66
    //   1495: ldc_w 1088
    //   1498: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   1501: getstatic 532	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_busying	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   1504: iconst_0
    //   1505: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   1508: goto -1310 -> 198
    //   1511: iconst_0
    //   1512: istore 48
    //   1514: goto -965 -> 549
    //   1517: aload 25
    //   1519: aload 14
    //   1521: invokevirtual 580	cn/com/fmsh/script/bean/ApduReponseList:toTag4A3	()Lcn/com/fmsh/communication/message/ITag;
    //   1524: invokeinterface 75 2 0
    //   1529: pop
    //   1530: goto -684 -> 846
    //   1533: astore 19
    //   1535: aload 18
    //   1537: astore 20
    //   1539: aload 12
    //   1541: astore 21
    //   1543: aload 19
    //   1545: astore 22
    //   1547: aconst_null
    //   1548: astore 23
    //   1550: goto -559 -> 991
    //   1553: astore 41
    //   1555: aload 12
    //   1557: astore 21
    //   1559: aload 41
    //   1561: astore 22
    //   1563: aload 40
    //   1565: astore 23
    //   1567: aload 18
    //   1569: astore 20
    //   1571: goto -580 -> 991
    //   1574: astore 22
    //   1576: aload 25
    //   1578: astore 21
    //   1580: aload 40
    //   1582: astore 23
    //   1584: aload 18
    //   1586: astore 20
    //   1588: goto -597 -> 991
    //   1591: aload 27
    //   1593: astore 24
    //   1595: iload 26
    //   1597: istore 15
    //   1599: goto -498 -> 1101
    //   1602: iload 15
    //   1604: istore 26
    //   1606: aconst_null
    //   1607: astore 27
    //   1609: goto -516 -> 1093
    //
    // Exception table:
    //   from	to	target	type
    //   833	855	299	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   1517	1530	299	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   1101	1108	674	cn/com/fmsh/script/exception/FMScriptHandleException
    //   1190	1212	975	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   1101	1108	1111	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   742	758	1292	cn/com/fmsh/script/exception/FMScriptHandleException
    //   887	926	1360	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   1212	1223	1533	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   1223	1257	1553	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   1261	1281	1574	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public byte[] getAppNo(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   4: ifnonnull +13 -> 17
    //   7: aload_0
    //   8: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   11: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   14: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   17: aload_0
    //   18: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   21: ifnull +26 -> 47
    //   24: aload_0
    //   25: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   28: aload_0
    //   29: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   32: bipush 88
    //   34: bipush 52
    //   36: ldc_w 1092
    //   39: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   42: invokeinterface 914 3 0
    //   47: invokestatic 1097	cn/com/fmsh/tsm/business/card/CardManagerFactory:instance	()Lcn/com/fmsh/tsm/business/card/CardManagerFactory;
    //   50: aload_1
    //   51: invokevirtual 1101	cn/com/fmsh/tsm/business/card/CardManagerFactory:getCardManager	(Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;)Lcn/com/fmsh/tsm/business/card/base/CardManager;
    //   54: astore_2
    //   55: aload_2
    //   56: ifnonnull +98 -> 154
    //   59: aload_0
    //   60: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   63: ifnull +25 -> 88
    //   66: aload_0
    //   67: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   70: aload_0
    //   71: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   74: ldc_w 1103
    //   77: iconst_5
    //   78: bipush 125
    //   80: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   83: invokeinterface 223 3 0
    //   88: aconst_null
    //   89: astore 4
    //   91: aload 4
    //   93: areturn
    //   94: astore 6
    //   96: aload_0
    //   97: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   100: ifnull +25 -> 125
    //   103: aload_0
    //   104: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   107: aload_0
    //   108: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   111: ldc_w 1105
    //   114: iconst_4
    //   115: bipush 20
    //   117: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   120: invokeinterface 172 3 0
    //   125: aload_0
    //   126: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   129: ldc_w 1107
    //   132: iconst_4
    //   133: bipush 105
    //   135: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   138: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   141: iconst_1
    //   142: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   145: aload_3
    //   146: invokeinterface 1110 1 0
    //   151: goto -60 -> 91
    //   154: aload_0
    //   155: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   158: invokevirtual 514	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getApduHandler	()Lcn/com/fmsh/script/ApduHandler;
    //   161: astore_3
    //   162: aload_3
    //   163: ifnonnull +50 -> 213
    //   166: aload_0
    //   167: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   170: ifnull +25 -> 195
    //   173: aload_0
    //   174: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   177: aload_0
    //   178: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   181: ldc_w 1112
    //   184: iconst_3
    //   185: bipush 71
    //   187: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   190: invokeinterface 172 3 0
    //   195: aload_0
    //   196: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   199: iconst_5
    //   200: ldc_w 1114
    //   203: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   206: getstatic 521	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_null	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   209: iconst_0
    //   210: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   213: aload_3
    //   214: invokeinterface 525 1 0
    //   219: ifeq +75 -> 294
    //   222: aload_0
    //   223: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   226: ifnull +25 -> 251
    //   229: aload_0
    //   230: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   233: aload_0
    //   234: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   237: iconst_4
    //   238: bipush 52
    //   240: ldc_w 1116
    //   243: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   246: invokeinterface 172 3 0
    //   251: aload_0
    //   252: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   255: iconst_1
    //   256: ldc_w 1118
    //   259: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   262: getstatic 532	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_busying	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   265: iconst_0
    //   266: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   269: aload_2
    //   270: aload_3
    //   271: invokeinterface 1124 2 0
    //   276: aconst_null
    //   277: astore 4
    //   279: aload_2
    //   280: invokeinterface 1126 1 0
    //   285: astore 5
    //   287: aload 5
    //   289: astore 4
    //   291: goto -146 -> 145
    //   294: aload_3
    //   295: invokeinterface 658 1 0
    //   300: ifne -31 -> 269
    //   303: aload_0
    //   304: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   307: ifnull +26 -> 333
    //   310: aload_0
    //   311: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   314: aload_0
    //   315: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   318: ldc_w 1128
    //   321: bipush 60
    //   323: bipush 74
    //   325: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   328: invokeinterface 172 3 0
    //   333: aload_0
    //   334: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   337: iconst_3
    //   338: bipush 42
    //   340: ldc_w 1130
    //   343: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   346: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   349: iconst_1
    //   350: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   353: goto -84 -> 269
    //
    // Exception table:
    //   from	to	target	type
    //   279	287	94	cn/com/fmsh/tsm/business/exception/BusinessException
  }

  public Integer getBalance(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    Object localObject = null;
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Exception.getChars(5, 7, "菩叓亨选卻皅佑颒83j"));
    String str = FM_CN.subSequence("莡叓亰遙匳盅伉飂", 264);
    CardManager localCardManager = CardManagerFactory.instance().getCardManager(paramEnumCardAppType);
    if (localCardManager == null)
      if (this.b != null)
        this.b.warn(this.c, str + FM_Long.concat("ｖ莲叆挜宜印籧埌盖奙瑎嘻奯贬", 2));
    while (true)
    {
      return localObject;
      ApduHandler localApduHandler = this.d.getApduHandler();
      if (localApduHandler == null)
      {
        if (this.b != null)
          this.b.error(this.c, str + FM_Int.lastIndexOf(5, "斮ｕ\033\013\030\b奚瑙器主穸"));
        this.d.throwExceptionAndClose(str + FM_Exception.getChars(5, 29, "斨７误儝剕捭卭盍诹闭方弒r\030YPa\005NFk"), BusinessException.ErrorMessage.local_business_apdu_handler_null, false);
      }
      if (localApduHandler.isConnect())
      {
        if (this.b != null)
          this.b.error(this.c, str + FM_Utils.copyValueOf(1, 55, "旺ｏ\033A\fJ奒琋嘬欸忋"));
        this.d.throwExceptionAndClose(str + FM_Exception.getChars(214, 62, "方！\nY\003P奇琇噷款忂"), BusinessException.ErrorMessage.local_business_apdu_handler_busying, false);
        label308: localCardManager.setApduHandler(localApduHandler);
      }
      try
      {
        Integer localInteger = Integer.valueOf(localCardManager.queryBalance());
        localObject = localInteger;
        localApduHandler.close();
        continue;
        if (localApduHandler.connect())
          break label308;
        if (this.b != null)
          this.b.error(this.c, str + FM_Int.lastIndexOf(312, "＇迒推卯夾贵"));
        this.d.throwExceptionAndClose(str + FM_CN.subSequence("旦３运掸匭奪赯", 2), BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
      }
      catch (BusinessException localBusinessException)
      {
        while (true)
        {
          if (this.b != null)
            this.b.error(this.c, str + FM_Long.concat("斪函玢弟帰", 4));
          this.d.throwExceptionAndClose(str + FM_Int.lastIndexOf(212, "斱冲珹彈平"), BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
        }
      }
    }
  }

  public CardAppInfo getCardAppInfo(int paramInt, EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Long.concat("\027\033\n\006p8gcx'{s.<pgz", 204));
    CardAppInfo localCardAppInfo1 = new CardAppInfo();
    localCardAppInfo1.setTitle(BCCUtil.endsWith("莳厁卫盙伉颞哚仭昏诿弗", 5, 83));
    CardManager localCardManager = CardManagerFactory.instance().getCardManager(paramEnumCardAppType);
    CardAppInfo localCardAppInfo2;
    if (localCardManager == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_CN.subSequence("莫厝医盍企颚咚亡昇诳弇斷｜菨变挚宖卺簱埒盌奓琀噽夵贶", 174));
      localCardAppInfo2 = localCardAppInfo1;
      return localCardAppInfo2;
    }
    ApduHandler localApduHandler = this.d.getApduHandler();
    if (localApduHandler == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_Int.lastIndexOf(240, "菴厒匤盂伞飕哅仮晘诼弘ｂ\016��\025\007套瑒嘽乬稭"));
      this.d.throwExceptionAndClose(BCCUtil.endsWith("莵受匡霽厩旫０讬儒刞捺匶盒讪闺旪彝9_\002\017\"B\r\t ", 3, 31), BusinessException.ErrorMessage.local_business_apdu_handler_null, false);
    }
    if (localApduHandler.isConnect())
    {
      if (this.b != null)
        this.b.warn(this.c, CRCUtil.valueOf(3, "菮厜区盈佄颓咓亴昒询弖斢ｉWW\\\\奞瑍嘴欮忇"));
      this.d.throwExceptionAndClose(FM_Exception.getChars(254, 114, "菠原区盉但颌咏亱昔诩弞斫ｃ��CAB复瑝嘥欼很"), BusinessException.ErrorMessage.local_business_apdu_handler_busying, false);
    }
    while (true)
    {
      localCardManager.setApduHandler(localApduHandler);
      if ((paramInt & 0x1) != 0)
      {
        localCardAppInfo1.setFaceId(localCardManager.getFaceID());
        localCardAppInfo1.setCardAppNo(localCardManager.getAppNo());
      }
      if ((paramInt & 0x2) != 0)
        localCardAppInfo1.setBalance(Integer.valueOf(localCardManager.queryBalance()));
      Iterator localIterator;
      if ((paramInt & 0x4) != 0)
        localIterator = localCardManager.readAppRecords().iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          if ((paramInt & 0x8) != 0)
            localCardAppInfo1.setAppClose(localCardManager.isLock4Consume());
          if ((paramInt & 0x10) != 0)
            localCardAppInfo1.setMoc(localCardManager.getMOC());
          if ((paramInt & 0x20) != 0)
            localCardAppInfo1.setTime4Validity(localCardManager.getTime4Validity());
          localApduHandler.close();
          if (this.b != null)
            this.b.info(this.c, FM_Long.concat("菪叞卲皚佐颉咓仮晆诰弞寚我", 5));
          localCardAppInfo2 = localCardAppInfo1;
          break;
        }
        localCardAppInfo1.addRecord((CardAppRecord)localIterator.next());
      }
      localApduHandler.connect();
    }
  }

  // ERROR //
  public List<CardAppRecord> getCardAppRecords(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   4: ifnonnull +13 -> 17
    //   7: aload_0
    //   8: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   11: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   14: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   17: aload_0
    //   18: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   21: ifnull +26 -> 47
    //   24: aload_0
    //   25: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   28: aload_0
    //   29: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   32: ldc_w 1244
    //   35: bipush 122
    //   37: bipush 121
    //   39: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   42: invokeinterface 914 3 0
    //   47: iconst_3
    //   48: bipush 29
    //   50: ldc_w 1246
    //   53: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   56: astore_2
    //   57: invokestatic 1097	cn/com/fmsh/tsm/business/card/CardManagerFactory:instance	()Lcn/com/fmsh/tsm/business/card/CardManagerFactory;
    //   60: aload_1
    //   61: invokevirtual 1101	cn/com/fmsh/tsm/business/card/CardManagerFactory:getCardManager	(Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;)Lcn/com/fmsh/tsm/business/card/base/CardManager;
    //   64: astore_3
    //   65: aload_3
    //   66: ifnonnull +150 -> 216
    //   69: aload_0
    //   70: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   73: ifnull +42 -> 115
    //   76: aload_0
    //   77: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   80: aload_0
    //   81: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   84: new 143	java/lang/StringBuilder
    //   87: dup
    //   88: aload_2
    //   89: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   92: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   95: ldc_w 1248
    //   98: iconst_1
    //   99: bipush 125
    //   101: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   104: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokeinterface 223 3 0
    //   115: aconst_null
    //   116: astore 5
    //   118: aload 5
    //   120: areturn
    //   121: astore 7
    //   123: aload_0
    //   124: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   127: ifnull +40 -> 167
    //   130: aload_0
    //   131: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   134: aload_0
    //   135: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   138: new 143	java/lang/StringBuilder
    //   141: dup
    //   142: aload_2
    //   143: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   146: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   149: ldc_w 1250
    //   152: iconst_2
    //   153: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   156: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: invokeinterface 172 3 0
    //   167: aload_0
    //   168: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   171: new 143	java/lang/StringBuilder
    //   174: dup
    //   175: aload_2
    //   176: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   179: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   182: ldc_w 1252
    //   185: sipush 336
    //   188: bipush 46
    //   190: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   193: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   202: iconst_1
    //   203: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   206: aload 4
    //   208: invokeinterface 1110 1 0
    //   213: goto -95 -> 118
    //   216: aload_0
    //   217: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   220: invokevirtual 514	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getApduHandler	()Lcn/com/fmsh/script/ApduHandler;
    //   223: astore 4
    //   225: aload 4
    //   227: ifnonnull +82 -> 309
    //   230: aload_0
    //   231: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   234: ifnull +40 -> 274
    //   237: aload_0
    //   238: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   241: aload_0
    //   242: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   245: new 143	java/lang/StringBuilder
    //   248: dup
    //   249: aload_2
    //   250: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   253: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   256: ldc_w 1254
    //   259: iconst_5
    //   260: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   263: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   269: invokeinterface 172 3 0
    //   274: aload_0
    //   275: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   278: new 143	java/lang/StringBuilder
    //   281: dup
    //   282: aload_2
    //   283: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   286: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   289: iconst_3
    //   290: ldc_w 1256
    //   293: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   296: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   302: getstatic 521	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_null	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   305: iconst_0
    //   306: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   309: aload 4
    //   311: invokeinterface 525 1 0
    //   316: ifeq +111 -> 427
    //   319: aload_0
    //   320: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   323: ifnull +43 -> 366
    //   326: aload_0
    //   327: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   330: aload_0
    //   331: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   334: new 143	java/lang/StringBuilder
    //   337: dup
    //   338: aload_2
    //   339: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   342: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   345: ldc_w 1258
    //   348: bipush 106
    //   350: bipush 123
    //   352: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   355: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   358: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   361: invokeinterface 172 3 0
    //   366: aload_0
    //   367: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   370: new 143	java/lang/StringBuilder
    //   373: dup
    //   374: aload_2
    //   375: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   378: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   381: ldc_w 1260
    //   384: iconst_4
    //   385: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   388: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   391: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   394: getstatic 532	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_busying	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   397: iconst_0
    //   398: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   401: aload_3
    //   402: aload 4
    //   404: invokeinterface 1124 2 0
    //   409: aconst_null
    //   410: astore 5
    //   412: aload_3
    //   413: invokeinterface 1201 1 0
    //   418: astore 6
    //   420: aload 6
    //   422: astore 5
    //   424: goto -218 -> 206
    //   427: aload 4
    //   429: invokeinterface 658 1 0
    //   434: ifne -33 -> 401
    //   437: aload_0
    //   438: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   441: ifnull +40 -> 481
    //   444: aload_0
    //   445: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   448: aload_0
    //   449: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   452: new 143	java/lang/StringBuilder
    //   455: dup
    //   456: aload_2
    //   457: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   460: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   463: iconst_2
    //   464: ldc_w 1262
    //   467: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   470: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   476: invokeinterface 172 3 0
    //   481: aload_0
    //   482: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   485: new 143	java/lang/StringBuilder
    //   488: dup
    //   489: aload_2
    //   490: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   493: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   496: iconst_5
    //   497: bipush 69
    //   499: ldc_w 1264
    //   502: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   505: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   508: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   511: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   514: iconst_1
    //   515: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   518: goto -117 -> 401
    //
    // Exception table:
    //   from	to	target	type
    //   412	420	121	cn/com/fmsh/tsm/business/exception/BusinessException
  }

  public EnumCardAppType getCardAppType()
    throws BusinessException
  {
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, BCCUtil.endsWith("莳厐匩皎籷埅~<:", 5, 98));
    String str = FM_CN.subSequence("莤叔印盄簴埕斻", 5);
    ApduHandler localApduHandler = this.d.getApduHandler();
    if (localApduHandler == null)
    {
      if (this.b != null)
        this.b.error(this.c, str + FM_CN.subSequence("？CA\004\032奚瑋嘴丱穠", 5));
      this.d.throwExceptionAndClose(str + BCCUtil.endsWith("＋说儗剜挵匲盋说閩旺彐s\030\036\016d\t\005\\2", 136, 124), BusinessException.ErrorMessage.local_business_apdu_handler_null, false);
    }
    if (localApduHandler.isConnect())
    {
      if (this.b != null)
        this.b.error(this.c, str + FM_Exception.getChars(3, 50, "ｐO\020V\021奒琎嘲歯忇"));
      this.d.throwExceptionAndClose(str + FM_Utils.copyValueOf(3, 65, "＂\016@\025G套琒嘽歵徎"), BusinessException.ErrorMessage.local_business_apdu_handler_busying, false);
    }
    while (true)
    {
      CardManagerFactory.instance().setApduHandler(localApduHandler);
      Object localObject = null;
      try
      {
        EnumCardAppType localEnumCardAppType = CardManagerFactory.instance().getCardAppType();
        localObject = localEnumCardAppType;
        localApduHandler.close();
        return localObject;
      }
      catch (BusinessException localBusinessException)
      {
        while (true)
        {
          if (this.b != null)
            this.b.error(this.c, str + FM_Exception.getChars(4, 57, "冧玦彍帰"));
          this.d.throwExceptionAndClose(str + FM_Exception.getChars(2, 73, "冡珴式幮"), BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
        }
      }
      if (localApduHandler.connect())
        continue;
      if (this.b != null)
        this.b.error(this.c, str + Util4Java.toString("（辆揩匡奥赭", 5, 116));
      this.d.throwExceptionAndClose(str + BCCUtil.endsWith("－迃揼却奠质", 2, 60), BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
    }
  }

  // ERROR //
  public String getFaceID(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   6: ifnonnull +13 -> 19
    //   9: aload_0
    //   10: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   13: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   16: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   19: aload_0
    //   20: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   23: ifnull +23 -> 46
    //   26: aload_0
    //   27: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   30: aload_0
    //   31: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   34: iconst_4
    //   35: ldc_w 1292
    //   38: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   41: invokeinterface 914 3 0
    //   46: invokestatic 1097	cn/com/fmsh/tsm/business/card/CardManagerFactory:instance	()Lcn/com/fmsh/tsm/business/card/CardManagerFactory;
    //   49: aload_1
    //   50: invokevirtual 1101	cn/com/fmsh/tsm/business/card/CardManagerFactory:getCardManager	(Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;)Lcn/com/fmsh/tsm/business/card/base/CardManager;
    //   53: astore_3
    //   54: aload_3
    //   55: ifnonnull +177 -> 232
    //   58: aload_0
    //   59: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   62: ifnull +27 -> 89
    //   65: aload_0
    //   66: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   69: aload_0
    //   70: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   73: ldc_w 1294
    //   76: sipush 174
    //   79: bipush 95
    //   81: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   84: invokeinterface 223 3 0
    //   89: aload_2
    //   90: areturn
    //   91: astore 6
    //   93: aload_0
    //   94: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   97: ifnull +24 -> 121
    //   100: aload_0
    //   101: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   104: aload_0
    //   105: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   108: ldc_w 1296
    //   111: bipush 80
    //   113: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   116: invokeinterface 172 3 0
    //   121: aload_0
    //   122: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   125: sipush 272
    //   128: ldc_w 1298
    //   131: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   134: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   137: iconst_1
    //   138: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   141: aload 4
    //   143: invokeinterface 1110 1 0
    //   148: goto -59 -> 89
    //   151: aload 4
    //   153: invokeinterface 658 1 0
    //   158: ifne +52 -> 210
    //   161: aload_0
    //   162: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   165: ifnull +25 -> 190
    //   168: aload_0
    //   169: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   172: aload_0
    //   173: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   176: ldc_w 1300
    //   179: iconst_2
    //   180: bipush 30
    //   182: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   185: invokeinterface 172 3 0
    //   190: aload_0
    //   191: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   194: iconst_4
    //   195: bipush 75
    //   197: ldc_w 1302
    //   200: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   203: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   206: iconst_1
    //   207: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   210: aload_3
    //   211: aload 4
    //   213: invokeinterface 1124 2 0
    //   218: aload_3
    //   219: invokeinterface 1187 1 0
    //   224: astore 5
    //   226: aload 5
    //   228: astore_2
    //   229: goto -88 -> 141
    //   232: aload_0
    //   233: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   236: invokevirtual 514	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getApduHandler	()Lcn/com/fmsh/script/ApduHandler;
    //   239: astore 4
    //   241: aload 4
    //   243: ifnonnull +50 -> 293
    //   246: aload_0
    //   247: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   250: ifnull +25 -> 275
    //   253: aload_0
    //   254: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   257: aload_0
    //   258: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   261: ldc_w 1304
    //   264: iconst_4
    //   265: bipush 73
    //   267: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   270: invokeinterface 172 3 0
    //   275: aload_0
    //   276: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   279: ldc_w 1306
    //   282: iconst_5
    //   283: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   286: getstatic 521	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_null	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   289: iconst_0
    //   290: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   293: aload 4
    //   295: invokeinterface 525 1 0
    //   300: ifeq -149 -> 151
    //   303: aload_0
    //   304: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   307: ifnull +27 -> 334
    //   310: aload_0
    //   311: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   314: aload_0
    //   315: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   318: ldc_w 1308
    //   321: sipush 186
    //   324: bipush 93
    //   326: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   329: invokeinterface 172 3 0
    //   334: aload_0
    //   335: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   338: ldc_w 1310
    //   341: iconst_2
    //   342: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   345: getstatic 532	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_busying	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   348: iconst_0
    //   349: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   352: goto -142 -> 210
    //
    // Exception table:
    //   from	to	target	type
    //   218	226	91	cn/com/fmsh/tsm/business/exception/BusinessException
  }

  // ERROR //
  public String getInvoiceToken(byte[] paramArrayOfByte)
    throws BusinessException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   4: ifnonnull +13 -> 17
    //   7: aload_0
    //   8: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   11: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   14: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   17: aload_0
    //   18: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   21: ifnull +27 -> 48
    //   24: aload_0
    //   25: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   28: aload_0
    //   29: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   32: ldc_w 1313
    //   35: sipush 274
    //   38: bipush 116
    //   40: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   43: invokeinterface 914 3 0
    //   48: iconst_2
    //   49: ldc_w 1315
    //   52: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   55: astore_2
    //   56: aload_1
    //   57: ifnull +9 -> 66
    //   60: aload_1
    //   61: arraylength
    //   62: iconst_1
    //   63: if_icmpge +457 -> 520
    //   66: aload_0
    //   67: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   70: ifnull +23 -> 93
    //   73: aload_0
    //   74: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   77: aload_0
    //   78: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   81: ldc_w 1317
    //   84: iconst_3
    //   85: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   88: invokeinterface 223 3 0
    //   93: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   96: dup
    //   97: iconst_3
    //   98: ldc_w 1319
    //   101: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   104: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   107: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   110: athrow
    //   111: astore 17
    //   113: aload_0
    //   114: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   117: ifnull +45 -> 162
    //   120: aload_0
    //   121: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   124: aload_0
    //   125: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   128: new 143	java/lang/StringBuilder
    //   131: dup
    //   132: ldc_w 1321
    //   135: sipush 276
    //   138: bipush 91
    //   140: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   143: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   146: aload 17
    //   148: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   151: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   157: invokeinterface 223 3 0
    //   162: aload_0
    //   163: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   166: ldc_w 1323
    //   169: iconst_3
    //   170: bipush 22
    //   172: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   175: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   178: iconst_0
    //   179: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   182: aload_0
    //   183: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   186: aload 5
    //   188: aload_2
    //   189: iconst_0
    //   190: aload 4
    //   192: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   195: astore 11
    //   197: aload 11
    //   199: ifnull +10 -> 209
    //   202: aload 11
    //   204: arraylength
    //   205: iconst_2
    //   206: if_icmpge +54 -> 260
    //   209: aload_0
    //   210: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   213: ifnull +27 -> 240
    //   216: aload_0
    //   217: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   220: aload_0
    //   221: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   224: sipush 148
    //   227: bipush 118
    //   229: ldc_w 1325
    //   232: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   235: invokeinterface 223 3 0
    //   240: aload_0
    //   241: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   244: sipush 172
    //   247: ldc_w 1327
    //   250: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   253: getstatic 302	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_command_data_invaild	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   256: iconst_0
    //   257: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   260: iconst_2
    //   261: newarray byte
    //   263: astore 12
    //   265: aload 11
    //   267: iconst_0
    //   268: aload 12
    //   270: iconst_0
    //   271: aload 12
    //   273: arraylength
    //   274: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   277: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   280: aload 12
    //   282: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   285: ifne +73 -> 358
    //   288: aload_0
    //   289: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   292: ifnull +41 -> 333
    //   295: aload_0
    //   296: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   299: aload_0
    //   300: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   303: new 143	java/lang/StringBuilder
    //   306: dup
    //   307: ldc_w 1329
    //   310: iconst_2
    //   311: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   314: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   317: aload 11
    //   319: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   322: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   325: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   328: invokeinterface 223 3 0
    //   333: aload_0
    //   334: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   337: ldc_w 1331
    //   340: iconst_2
    //   341: bipush 101
    //   343: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   346: aload 12
    //   348: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   351: invokestatic 180	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:instance	(Ljava/lang/String;)Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   354: iconst_0
    //   355: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   358: aconst_null
    //   359: astore 13
    //   361: aload_3
    //   362: sipush 3062
    //   365: aload 11
    //   367: iconst_2
    //   368: aload 11
    //   370: arraylength
    //   371: invokestatic 188	java/util/Arrays:copyOfRange	([BII)[B
    //   374: invokeinterface 191 3 0
    //   379: bipush 66
    //   381: invokeinterface 195 2 0
    //   386: astore 15
    //   388: aload 15
    //   390: ifnull +19 -> 409
    //   393: aload 15
    //   395: invokeinterface 697 1 0
    //   400: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   403: astore 16
    //   405: aload 16
    //   407: astore 13
    //   409: aload_0
    //   410: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   413: iconst_0
    //   414: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   417: aload 13
    //   419: areturn
    //   420: aload_0
    //   421: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   424: sipush 3062
    //   427: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   430: astore 4
    //   432: aload 4
    //   434: ifnonnull +270 -> 704
    //   437: aload_0
    //   438: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   441: ifnull +40 -> 481
    //   444: aload_0
    //   445: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   448: aload_0
    //   449: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   452: new 143	java/lang/StringBuilder
    //   455: dup
    //   456: aload_2
    //   457: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   460: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   463: iconst_1
    //   464: ldc_w 1333
    //   467: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   470: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   476: invokeinterface 223 3 0
    //   481: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   484: dup
    //   485: new 143	java/lang/StringBuilder
    //   488: dup
    //   489: aload_2
    //   490: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   493: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   496: ldc_w 1335
    //   499: sipush 264
    //   502: bipush 122
    //   504: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   507: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   513: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   516: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   519: athrow
    //   520: aload_0
    //   521: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   524: ifnonnull +50 -> 574
    //   527: aload_0
    //   528: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   531: ifnull +25 -> 556
    //   534: aload_0
    //   535: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   538: aload_0
    //   539: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   542: ldc_w 1337
    //   545: iconst_5
    //   546: bipush 93
    //   548: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   551: invokeinterface 223 3 0
    //   556: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   559: dup
    //   560: iconst_5
    //   561: ldc_w 1339
    //   564: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   567: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   570: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   573: athrow
    //   574: aload_0
    //   575: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   578: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   581: astore_3
    //   582: aload_3
    //   583: ifnonnull -163 -> 420
    //   586: aload_0
    //   587: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   590: ifnull +25 -> 615
    //   593: aload_0
    //   594: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   597: aload_0
    //   598: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   601: iconst_3
    //   602: bipush 61
    //   604: ldc_w 1341
    //   607: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   610: invokeinterface 223 3 0
    //   615: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   618: dup
    //   619: iconst_3
    //   620: ldc_w 1343
    //   623: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   626: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   629: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   632: athrow
    //   633: astore 14
    //   635: aload_0
    //   636: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   639: ifnull +42 -> 681
    //   642: aload_0
    //   643: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   646: aload_0
    //   647: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   650: new 143	java/lang/StringBuilder
    //   653: dup
    //   654: bipush 76
    //   656: ldc_w 1345
    //   659: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   662: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   665: aload 11
    //   667: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   670: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   673: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   676: invokeinterface 223 3 0
    //   681: aload_0
    //   682: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   685: ldc_w 1347
    //   688: iconst_5
    //   689: bipush 19
    //   691: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   694: getstatic 302	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_command_data_invaild	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   697: iconst_0
    //   698: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   701: goto -292 -> 409
    //   704: aload_0
    //   705: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   708: aload_2
    //   709: aload 4
    //   711: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   714: aconst_null
    //   715: astore 5
    //   717: aload_3
    //   718: sipush 3062
    //   721: invokeinterface 59 2 0
    //   726: astore 6
    //   728: aload_3
    //   729: bipush 105
    //   731: invokeinterface 63 2 0
    //   736: astore 7
    //   738: aload 7
    //   740: aload_1
    //   741: invokeinterface 91 2 0
    //   746: pop
    //   747: aload 6
    //   749: aload 7
    //   751: invokeinterface 75 2 0
    //   756: pop
    //   757: aload 6
    //   759: invokeinterface 117 1 0
    //   764: astore 10
    //   766: aload 10
    //   768: astore 5
    //   770: goto -588 -> 182
    //
    // Exception table:
    //   from	to	target	type
    //   728	766	111	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   361	405	633	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public String getMOC(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   6: ifnonnull +13 -> 19
    //   9: aload_0
    //   10: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   13: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   16: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   19: aload_0
    //   20: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   23: ifnull +26 -> 49
    //   26: aload_0
    //   27: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   30: aload_0
    //   31: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   34: ldc_w 1349
    //   37: bipush 80
    //   39: bipush 62
    //   41: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   44: invokeinterface 914 3 0
    //   49: invokestatic 1097	cn/com/fmsh/tsm/business/card/CardManagerFactory:instance	()Lcn/com/fmsh/tsm/business/card/CardManagerFactory;
    //   52: aload_1
    //   53: invokevirtual 1101	cn/com/fmsh/tsm/business/card/CardManagerFactory:getCardManager	(Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;)Lcn/com/fmsh/tsm/business/card/base/CardManager;
    //   56: astore_3
    //   57: aload_3
    //   58: ifnonnull +91 -> 149
    //   61: aload_0
    //   62: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   65: ifnull +23 -> 88
    //   68: aload_0
    //   69: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   72: aload_0
    //   73: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   76: ldc_w 1351
    //   79: iconst_5
    //   80: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   83: invokeinterface 223 3 0
    //   88: aload_2
    //   89: areturn
    //   90: astore 6
    //   92: aload_0
    //   93: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   96: ifnull +23 -> 119
    //   99: aload_0
    //   100: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   103: aload_0
    //   104: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   107: iconst_4
    //   108: ldc_w 1353
    //   111: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   114: invokeinterface 172 3 0
    //   119: aload_0
    //   120: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   123: iconst_2
    //   124: bipush 18
    //   126: ldc_w 1355
    //   129: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   132: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   135: iconst_1
    //   136: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   139: aload 4
    //   141: invokeinterface 1110 1 0
    //   146: goto -58 -> 88
    //   149: aload_0
    //   150: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   153: invokevirtual 514	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getApduHandler	()Lcn/com/fmsh/script/ApduHandler;
    //   156: astore 4
    //   158: aload 4
    //   160: ifnonnull +53 -> 213
    //   163: aload_0
    //   164: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   167: ifnull +25 -> 192
    //   170: aload_0
    //   171: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   174: aload_0
    //   175: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   178: ldc_w 1357
    //   181: sipush 210
    //   184: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   187: invokeinterface 172 3 0
    //   192: aload_0
    //   193: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   196: ldc_w 1359
    //   199: bipush 114
    //   201: bipush 30
    //   203: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   206: getstatic 521	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_null	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   209: iconst_0
    //   210: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   213: aload 4
    //   215: invokeinterface 525 1 0
    //   220: ifeq +74 -> 294
    //   223: aload_0
    //   224: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   227: ifnull +25 -> 252
    //   230: aload_0
    //   231: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   234: aload_0
    //   235: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   238: sipush 188
    //   241: ldc_w 1361
    //   244: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   247: invokeinterface 172 3 0
    //   252: aload_0
    //   253: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   256: ldc_w 1363
    //   259: iconst_4
    //   260: bipush 112
    //   262: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   265: getstatic 532	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_busying	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   268: iconst_0
    //   269: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   272: aload_3
    //   273: aload 4
    //   275: invokeinterface 1124 2 0
    //   280: aload_3
    //   281: invokeinterface 1219 1 0
    //   286: astore 5
    //   288: aload 5
    //   290: astore_2
    //   291: goto -152 -> 139
    //   294: aload 4
    //   296: invokeinterface 658 1 0
    //   301: ifne -29 -> 272
    //   304: aload_0
    //   305: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   308: ifnull +23 -> 331
    //   311: aload_0
    //   312: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   315: aload_0
    //   316: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   319: iconst_5
    //   320: ldc_w 1365
    //   323: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   326: invokeinterface 172 3 0
    //   331: aload_0
    //   332: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   335: iconst_3
    //   336: ldc_w 1367
    //   339: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   342: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   345: iconst_1
    //   346: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   349: goto -77 -> 272
    //
    // Exception table:
    //   from	to	target	type
    //   280	288	90	cn/com/fmsh/tsm/business/exception/BusinessException
  }

  // ERROR //
  public List<cn.com.fmsh.tsm.business.bean.Notice> getNotices(int paramInt)
    throws BusinessException
  {
    // Byte code:
    //   0: iconst_4
    //   1: ldc_w 1371
    //   4: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   7: astore_2
    //   8: aload_0
    //   9: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   12: ifnonnull +71 -> 83
    //   15: aload_0
    //   16: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   19: ifnonnull +13 -> 32
    //   22: aload_0
    //   23: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   26: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   29: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   32: aload_0
    //   33: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   36: ifnull +27 -> 63
    //   39: aload_0
    //   40: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   43: aload_0
    //   44: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   47: sipush 234
    //   50: bipush 70
    //   52: ldc_w 1373
    //   55: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   58: invokeinterface 223 3 0
    //   63: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   66: dup
    //   67: ldc_w 1375
    //   70: iconst_3
    //   71: bipush 12
    //   73: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   76: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   79: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   82: athrow
    //   83: aload_0
    //   84: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   87: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   90: astore_3
    //   91: aload_3
    //   92: ifnonnull +540 -> 632
    //   95: aload_0
    //   96: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   99: ifnull +26 -> 125
    //   102: aload_0
    //   103: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   106: aload_0
    //   107: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   110: bipush 64
    //   112: bipush 89
    //   114: ldc_w 1377
    //   117: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   120: invokeinterface 223 3 0
    //   125: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   128: dup
    //   129: iconst_2
    //   130: bipush 77
    //   132: ldc_w 1379
    //   135: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   138: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   141: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   144: athrow
    //   145: astore 24
    //   147: aload_0
    //   148: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   151: ifnull +41 -> 192
    //   154: aload_0
    //   155: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   158: aload_0
    //   159: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   162: new 143	java/lang/StringBuilder
    //   165: dup
    //   166: ldc_w 1381
    //   169: iconst_3
    //   170: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   173: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   176: aload 11
    //   178: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   181: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   187: invokeinterface 223 3 0
    //   192: aload_0
    //   193: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   196: iconst_3
    //   197: bipush 108
    //   199: ldc_w 1383
    //   202: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   205: getstatic 302	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_command_data_invaild	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   208: iconst_0
    //   209: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   212: aload_0
    //   213: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   216: iconst_0
    //   217: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   220: aload 13
    //   222: areturn
    //   223: astore 30
    //   225: aload_0
    //   226: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   229: ifnull +43 -> 272
    //   232: aload_0
    //   233: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   236: aload_0
    //   237: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   240: new 143	java/lang/StringBuilder
    //   243: dup
    //   244: ldc_w 1385
    //   247: iconst_4
    //   248: bipush 116
    //   250: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   253: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   256: aload 30
    //   258: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   261: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   267: invokeinterface 223 3 0
    //   272: aload_0
    //   273: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   276: sipush 210
    //   279: iconst_2
    //   280: ldc_w 1387
    //   283: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   286: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   289: iconst_0
    //   290: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   293: aload_0
    //   294: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   297: aload 5
    //   299: aload_2
    //   300: iconst_0
    //   301: aload 4
    //   303: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   306: astore 11
    //   308: aload 11
    //   310: ifnull +10 -> 320
    //   313: aload 11
    //   315: arraylength
    //   316: iconst_2
    //   317: if_icmpge +53 -> 370
    //   320: aload_0
    //   321: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   324: ifnull +27 -> 351
    //   327: aload_0
    //   328: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   331: aload_0
    //   332: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   335: ldc_w 1389
    //   338: sipush 140
    //   341: bipush 25
    //   343: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   346: invokeinterface 223 3 0
    //   351: aload_0
    //   352: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   355: ldc_w 1391
    //   358: bipush 6
    //   360: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   363: getstatic 302	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_command_data_invaild	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   366: iconst_0
    //   367: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   370: iconst_2
    //   371: newarray byte
    //   373: astore 12
    //   375: aload 11
    //   377: iconst_0
    //   378: aload 12
    //   380: iconst_0
    //   381: aload 12
    //   383: arraylength
    //   384: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   387: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   390: aload 12
    //   392: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   395: ifne +75 -> 470
    //   398: aload_0
    //   399: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   402: ifnull +41 -> 443
    //   405: aload_0
    //   406: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   409: aload_0
    //   410: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   413: new 143	java/lang/StringBuilder
    //   416: dup
    //   417: ldc_w 1393
    //   420: iconst_4
    //   421: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   424: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   427: aload 11
    //   429: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   432: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   438: invokeinterface 223 3 0
    //   443: aload_0
    //   444: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   447: sipush 284
    //   450: bipush 18
    //   452: ldc_w 1395
    //   455: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   458: aload 12
    //   460: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   463: invokestatic 180	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:instance	(Ljava/lang/String;)Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   466: iconst_0
    //   467: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   470: new 321	java/util/ArrayList
    //   473: dup
    //   474: invokespecial 322	java/util/ArrayList:<init>	()V
    //   477: astore 13
    //   479: aload_3
    //   480: sipush 1311
    //   483: aload 11
    //   485: iconst_2
    //   486: aload 11
    //   488: arraylength
    //   489: invokestatic 188	java/util/Arrays:copyOfRange	([BII)[B
    //   492: invokeinterface 191 3 0
    //   497: bipush 147
    //   499: invokeinterface 195 2 0
    //   504: astore 14
    //   506: aload 14
    //   508: ifnull -296 -> 212
    //   511: aload 14
    //   513: invokeinterface 336 1 0
    //   518: astore 15
    //   520: aload 15
    //   522: ifnull -310 -> 212
    //   525: aload 15
    //   527: arraylength
    //   528: istore 16
    //   530: iconst_0
    //   531: istore 17
    //   533: iload 17
    //   535: iload 16
    //   537: if_icmpge -325 -> 212
    //   540: aload 15
    //   542: iload 17
    //   544: aaload
    //   545: astore 18
    //   547: aload 18
    //   549: ifnonnull +317 -> 866
    //   552: iinc 17 1
    //   555: goto -22 -> 533
    //   558: aload_0
    //   559: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   562: aload_2
    //   563: aload 4
    //   565: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   568: aconst_null
    //   569: astore 5
    //   571: aload_3
    //   572: sipush 1311
    //   575: invokeinterface 59 2 0
    //   580: astore 6
    //   582: aload_3
    //   583: bipush 49
    //   585: invokeinterface 63 2 0
    //   590: astore 7
    //   592: aload 7
    //   594: iload_1
    //   595: bipush 8
    //   597: invokestatic 88	cn/com/fmsh/util/FM_Bytes:intToBytes	(II)[B
    //   600: invokeinterface 91 2 0
    //   605: pop
    //   606: aload 6
    //   608: aload 7
    //   610: invokeinterface 75 2 0
    //   615: pop
    //   616: aload 6
    //   618: invokeinterface 117 1 0
    //   623: astore 10
    //   625: aload 10
    //   627: astore 5
    //   629: goto -336 -> 293
    //   632: aload_0
    //   633: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   636: sipush 1311
    //   639: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   642: astore 4
    //   644: aload 4
    //   646: ifnonnull -88 -> 558
    //   649: aload_0
    //   650: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   653: ifnull +42 -> 695
    //   656: aload_0
    //   657: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   660: aload_0
    //   661: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   664: new 143	java/lang/StringBuilder
    //   667: dup
    //   668: aload_2
    //   669: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   672: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   675: ldc_w 1397
    //   678: iconst_4
    //   679: bipush 20
    //   681: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   684: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   687: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   690: invokeinterface 223 3 0
    //   695: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   698: dup
    //   699: new 143	java/lang/StringBuilder
    //   702: dup
    //   703: aload_2
    //   704: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   707: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   710: ldc_w 1399
    //   713: iconst_4
    //   714: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   717: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   720: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   723: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   726: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   729: athrow
    //   730: aload 25
    //   732: invokeinterface 809 1 0
    //   737: tableswitch	default:+290 -> 1027, 17:+248->985
    //   757: fconst_2
    //   758: aload 19
    //   760: invokeinterface 349 2 0
    //   765: pop
    //   766: goto -214 -> 552
    //   769: aload 19
    //   771: aload 29
    //   773: invokeinterface 780 1 0
    //   778: invokevirtual 1402	cn/com/fmsh/tsm/business/bean/Notice:setTitle	(Ljava/lang/String;)V
    //   781: goto +259 -> 1040
    //   784: iload 28
    //   786: iload 27
    //   788: if_icmpge -32 -> 756
    //   791: aload 26
    //   793: iload 28
    //   795: aaload
    //   796: astore 29
    //   798: aload 29
    //   800: invokeinterface 809 1 0
    //   805: tableswitch	default:+241 -> 1046, 49:+43->848, 50:+-36->769, 51:+241->1046, 52:+195->1000, 53:+241->1046, 54:+110->915, 55:+125->930
    //   849: ldc_w 6429
    //   852: invokeinterface 697 1 0
    //   857: invokestatic 700	cn/com/fmsh/util/FM_Bytes:bytesToInt	([B)I
    //   860: invokevirtual 1405	cn/com/fmsh/tsm/business/bean/Notice:setNo	(I)V
    //   863: goto +177 -> 1040
    //   866: new 1401	cn/com/fmsh/tsm/business/bean/Notice
    //   869: dup
    //   870: invokespecial 1406	cn/com/fmsh/tsm/business/bean/Notice:<init>	()V
    //   873: astore 19
    //   875: aload 18
    //   877: invokeinterface 809 1 0
    //   882: bipush 146
    //   884: if_icmpne +61 -> 945
    //   887: aload 18
    //   889: invokeinterface 336 1 0
    //   894: astore 26
    //   896: aload 19
    //   898: getstatic 1410	cn/com/fmsh/tsm/business/bean/Notice:NOTICE_TXT	I
    //   901: invokevirtual 1413	cn/com/fmsh/tsm/business/bean/Notice:setType	(I)V
    //   904: aload 26
    //   906: arraylength
    //   907: istore 27
    //   909: iconst_0
    //   910: istore 28
    //   912: goto -128 -> 784
    //   915: aload 19
    //   917: aload 29
    //   919: invokeinterface 780 1 0
    //   924: invokevirtual 1416	cn/com/fmsh/tsm/business/bean/Notice:setStartDate	(Ljava/lang/String;)V
    //   927: goto +113 -> 1040
    //   930: aload 19
    //   932: aload 29
    //   934: invokeinterface 780 1 0
    //   939: invokevirtual 1419	cn/com/fmsh/tsm/business/bean/Notice:setEndDate	(Ljava/lang/String;)V
    //   942: goto +98 -> 1040
    //   945: aload 18
    //   947: invokeinterface 809 1 0
    //   952: bipush 148
    //   954: if_icmpne -402 -> 552
    //   957: aload 18
    //   959: invokeinterface 336 1 0
    //   964: astore 20
    //   966: aload 19
    //   968: getstatic 1422	cn/com/fmsh/tsm/business/bean/Notice:NOTICE_UNSOLVED	I
    //   971: invokevirtual 1413	cn/com/fmsh/tsm/business/bean/Notice:setType	(I)V
    //   974: aload 20
    //   976: arraylength
    //   977: istore 21
    //   979: iconst_0
    //   980: istore 22
    //   982: goto +48 -> 1030
    //   985: aload 19
    //   987: aload 25
    //   989: invokeinterface 697 1 0
    //   994: invokevirtual 1423	cn/com/fmsh/tsm/business/bean/Notice:setOrder	([B)V
    //   997: goto +30 -> 1027
    //   1000: aload 19
    //   1002: aload 29
    //   1004: invokeinterface 780 1 0
    //   1009: invokevirtual 1426	cn/com/fmsh/tsm/business/bean/Notice:setContent	(Ljava/lang/String;)V
    //   1012: goto +28 -> 1040
    //   1015: aload 20
    //   1017: iload 22
    //   1019: aaload
    //   1020: astore 25
    //   1022: aload 25
    //   1024: ifnonnull -294 -> 730
    //   1027: iinc 22 1
    //   1030: iload 22
    //   1032: iload 21
    //   1034: if_icmplt -19 -> 1015
    //   1037: goto -281 -> 756
    //   1040: iinc 28 1
    //   1043: goto -259 -> 784
    //   1046: goto -6 -> 1040
    //
    // Exception table:
    //   from	to	target	type
    //   479	547	145	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   730	1022	145	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   582	625	223	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public String getTime4Validity(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   6: ifnonnull +13 -> 19
    //   9: aload_0
    //   10: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   13: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   16: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   19: aload_0
    //   20: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   23: ifnull +27 -> 50
    //   26: aload_0
    //   27: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   30: aload_0
    //   31: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   34: ldc_w 1428
    //   37: sipush 342
    //   40: bipush 101
    //   42: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   45: invokeinterface 914 3 0
    //   50: invokestatic 1097	cn/com/fmsh/tsm/business/card/CardManagerFactory:instance	()Lcn/com/fmsh/tsm/business/card/CardManagerFactory;
    //   53: aload_1
    //   54: invokevirtual 1101	cn/com/fmsh/tsm/business/card/CardManagerFactory:getCardManager	(Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;)Lcn/com/fmsh/tsm/business/card/base/CardManager;
    //   57: astore_3
    //   58: aload_3
    //   59: ifnonnull +66 -> 125
    //   62: aload_2
    //   63: areturn
    //   64: astore 6
    //   66: aload_0
    //   67: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   70: ifnull +25 -> 95
    //   73: aload_0
    //   74: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   77: aload_0
    //   78: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   81: ldc_w 1430
    //   84: iconst_1
    //   85: bipush 78
    //   87: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   90: invokeinterface 172 3 0
    //   95: aload_0
    //   96: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   99: sipush 284
    //   102: ldc_w 1432
    //   105: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   108: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   111: iconst_1
    //   112: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   115: aload 4
    //   117: invokeinterface 1110 1 0
    //   122: goto -60 -> 62
    //   125: aload_0
    //   126: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   129: invokevirtual 514	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getApduHandler	()Lcn/com/fmsh/script/ApduHandler;
    //   132: astore 4
    //   134: aload 4
    //   136: ifnonnull +50 -> 186
    //   139: aload_0
    //   140: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   143: ifnull +23 -> 166
    //   146: aload_0
    //   147: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   150: aload_0
    //   151: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   154: ldc_w 1434
    //   157: iconst_5
    //   158: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   161: invokeinterface 172 3 0
    //   166: aload_0
    //   167: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   170: iconst_4
    //   171: bipush 66
    //   173: ldc_w 1436
    //   176: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   179: getstatic 521	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_null	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   182: iconst_0
    //   183: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   186: aload 4
    //   188: invokeinterface 525 1 0
    //   193: ifeq +72 -> 265
    //   196: aload_0
    //   197: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   200: ifnull +23 -> 223
    //   203: aload_0
    //   204: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   207: aload_0
    //   208: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   211: ldc_w 1438
    //   214: iconst_1
    //   215: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   218: invokeinterface 172 3 0
    //   223: aload_0
    //   224: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   227: ldc_w 1440
    //   230: iconst_4
    //   231: bipush 21
    //   233: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   236: getstatic 532	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_apdu_handler_busying	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   239: iconst_0
    //   240: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   243: aload_3
    //   244: aload 4
    //   246: invokeinterface 1124 2 0
    //   251: aload_3
    //   252: invokeinterface 1225 1 0
    //   257: astore 5
    //   259: aload 5
    //   261: astore_2
    //   262: goto -147 -> 115
    //   265: aload 4
    //   267: invokeinterface 658 1 0
    //   272: ifne -29 -> 243
    //   275: aload_0
    //   276: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   279: ifnull +23 -> 302
    //   282: aload_0
    //   283: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   286: aload_0
    //   287: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   290: iconst_3
    //   291: ldc_w 1442
    //   294: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   297: invokeinterface 172 3 0
    //   302: aload_0
    //   303: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   306: iconst_2
    //   307: bipush 71
    //   309: ldc_w 1444
    //   312: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   315: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_apdu_execute_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   318: iconst_1
    //   319: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   322: goto -79 -> 243
    //
    // Exception table:
    //   from	to	target	type
    //   251	259	64	cn/com/fmsh/tsm/business/exception/BusinessException
  }

  public boolean isLock4Consume(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    int i = 0;
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Bytes.startsWith("莳厓仢逝卩淁赳劔胱晢镏宕犦恐|=:", 3, 97));
    String str = FM_Int.lastIndexOf(2, "菢厀仳遂匸坒嬃勃肠镟寅状怀");
    CardManager localCardManager = CardManagerFactory.instance().getCardManager(paramEnumCardAppType);
    if (localCardManager == null)
    {
      if (this.b != null)
        this.b.error(this.c, str + FM_Long.concat("斫＄伳妜皍卵籤埁斵攈", 5));
      this.d.throwExceptionAndClose(str + FM_Bytes.startsWith("旵ｌ诪兒剐挶印皊说閦於弍wST\027|^K\tn", 2, 93), BusinessException.ErrorMessage.local_business_no_card_app_type, false);
    }
    ApduHandler localApduHandler = this.d.getApduHandler();
    if (localApduHandler == null)
    {
      if (this.b != null)
        this.b.error(this.c, str + FM_Bytes.startsWith("旰ｐSX\032A夎瑆噾丶稸", 5, 86));
      this.d.throwExceptionAndClose(str + FM_Int.lastIndexOf(4, "斡ｔ议儒剜挾匼盚诠问斸弍+KHG(FOI\""), BusinessException.ErrorMessage.local_business_apdu_handler_null, false);
    }
    if (localApduHandler.isConnect())
    {
      if (this.b != null)
        this.b.error(this.c, str + FM_Long.concat("断＊PLCG夙瑎嘻欽徐", 3));
      this.d.throwExceptionAndClose(str + CRCUtil.valueOf(5, "断｀\034\036[E夅琔噫欷徜"), BusinessException.ErrorMessage.local_business_apdu_handler_busying, false);
    }
    while (true)
    {
      localCardManager.setApduHandler(localApduHandler);
      try
      {
        boolean bool = localCardManager.isLock4Consume();
        i = bool;
        localApduHandler.close();
        return i;
        if (localApduHandler.connect())
          continue;
        if (this.b != null)
          this.b.error(this.c, str + FM_Int.lastIndexOf(2, "斣ｚ\026\b\035\017奟瑚嘵辀揺卡夰货"));
        this.d.throwExceptionAndClose(str + FM_Int.lastIndexOf(112, "斵ｈ\004\026\003\035奍瑌嘣辒揨匯奾赵"), BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
      }
      catch (BusinessException localBusinessException)
      {
        while (true)
        {
          if (this.b != null)
            this.b.error(this.c, str + CRCUtil.valueOf(2, "斮决珪彉帤"));
          this.d.throwExceptionAndClose(str + BCCUtil.endsWith("斱冬玵彖帻", 200, 47), BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
        }
      }
    }
  }

  public boolean isLock4Load(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    int i = 0;
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, Util4Java.toString("莵厘仾遜即圖孒勉肿镏宀犰怓pdx", 3, 108));
    String str = FM_Utils.copyValueOf(3, 70, "莹厂亾遚卧坄孊勇胣锅寐犦恗");
    CardManager localCardManager = CardManagerFactory.instance().getCardManager(paramEnumCardAppType);
    if (localCardManager == null)
    {
      if (this.b != null)
        this.b.warn(this.c, str + CRCUtil.valueOf(2, "斮ｅ佺姉皘卬籥垄无攙"));
      this.d.throwExceptionAndClose(str + FM_Utils.copyValueOf(356, 11, "方ｖ诲兘刜捤印皘诸閼旤彇{\021DU0DS\003b"), BusinessException.ErrorMessage.local_business_no_card_app_type, false);
    }
    ApduHandler localApduHandler = this.d.getApduHandler();
    if (localApduHandler == null)
    {
      if (this.b != null)
        this.b.error(this.c, str + FM_Exception.getChars(154, 102, "日ｕ\036\025OD奓瑛嘫丳穵"));
      this.d.throwExceptionAndClose(str + FM_Int.lastIndexOf(162, "旣：诠児刞捸卺皘订闰斦式)MNE*HAK "), BusinessException.ErrorMessage.local_business_apdu_handler_null, false);
    }
    if (localApduHandler.isConnect())
    {
      if (this.b != null)
        this.b.error(this.c, str + Util4Java.toString("时：M\022\034\033夀琜噸欥待", 1, 22));
      this.d.throwExceptionAndClose(str + FM_Utils.copyValueOf(260, 118, "旹）\032\001\003\b套瑏噷歶忒"), BusinessException.ErrorMessage.local_business_apdu_handler_busying, false);
    }
    while (true)
    {
      localCardManager.setApduHandler(localApduHandler);
      try
      {
        boolean bool = localCardManager.isLock4Load();
        i = bool;
        localApduHandler.close();
        return i;
      }
      catch (BusinessException localBusinessException)
      {
        while (true)
        {
          if (this.b != null)
            this.b.error(this.c, str + BCCUtil.endsWith("斡击珻弗幧", 88, 74));
          this.d.throwExceptionAndClose(str + FM_Bytes.startsWith("旵凣珿弇幣", 2, 54), BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
        }
      }
      if (localApduHandler.connect())
        continue;
      if (this.b != null)
        this.b.error(this.c, str + Util4Java.toString("旴｀辈揥卫夥赻", 3, 106));
      this.d.throwExceptionAndClose(str + FM_Long.concat("斮ｏ辐揼卥夾贿", 224), BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
    }
  }

  public boolean isRun4plateform()
    throws BusinessException
  {
    int i = 0;
    String str1 = CRCUtil.valueOf(44, "糹绌含畽狰恖枭读");
    String str2;
    IMessageHandler localIMessageHandler;
    int j;
    if (this.d == null)
    {
      if (this.b == null)
        this.b = LogFactory.getInstance().getLog();
      if (this.b != null)
        this.b.warn(this.c, str1 + Util4Java.toString("旯～丑勥奙琐家豩乻穠", 154, 57));
      throw new BusinessException(str1 + FM_Int.lastIndexOf(140, "斩，丛劣备琂噭创姌匞夸贯"), BusinessException.ErrorMessage.local_business_init_fail);
      this.d.businessReady(str1, str2);
      IMessage localIMessage = localIMessageHandler.createMessage(1221);
      try
      {
        ITag localITag = localIMessageHandler.createTag(30);
        localITag.addValue(j);
        localIMessage.addTag(localITag);
        byte[] arrayOfByte4 = localIMessage.toBytes();
        arrayOfByte1 = arrayOfByte4;
        byte[] arrayOfByte2 = this.d.interaction(arrayOfByte1, str1, false, str2);
        byte[] arrayOfByte3 = new byte[2];
        System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 0, arrayOfByte3.length);
        if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, arrayOfByte3))
          break label696;
        if (this.b != null)
          this.b.warn(this.c, str1 + CRCUtil.valueOf(2, "斮ｅ帩去夘琋夯贪:") + FM_Bytes.bytesToHexString(arrayOfByte2));
        label279: return i;
      }
      catch (FMCommunicationMessageException localFMCommunicationMessageException)
      {
        while (true)
        {
          if (this.b != null)
            this.b.warn(this.c, str1 + FM_Bytes.startsWith("斿｟枙逧幢厫讲氍敩捭凷珧彃平／", 232, 106) + Util4Java.getExceptionInfo(localFMCommunicationMessageException));
          this.d.throwExceptionAndClose(str1 + FM_Bytes.startsWith("旳？柅逯幮去诮氅攥捭冫珯式幣", 4, 46), BusinessException.ErrorMessage.local_message_message_handle_exception, false);
          byte[] arrayOfByte1 = null;
        }
      }
    }
    else
    {
      localIMessageHandler = this.d.getMessageHandler();
      if (localIMessageHandler == null)
      {
        if (this.b != null)
          this.b.warn(this.c, str1 + FM_Long.concat("断＊涙恳夃琔噵乲稩", 3));
        throw new BusinessException(str1 + CRCUtil.valueOf(2, "斮ｅ淒怤夘琋噶丵空"), BusinessException.ErrorMessage.local_message_load_config_fail);
        label473: if (this.b == null)
          break label709;
        this.b.warn(this.c, str1 + FM_Utils.copyValueOf(4, 51, "旹ｎ板扶剫酃缯旓仱"));
        j = 0;
      }
    }
    while (true)
    {
      str2 = this.d.getServer4Business(1211);
      if (str2 != null)
        break;
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Utils.copyValueOf(3, 109, "旸ｗ菿厃奆琉皘幺厦奲赵"));
      throw new BusinessException(str1 + CRCUtil.valueOf(216, "斸３莧受外琅盐帶厦奶贽"), BusinessException.ErrorMessage.local_app_query_server_fail);
      Configration localConfigration = this.d.getConfigration();
      if (localConfigration == null)
        break label473;
      j = localConfigration.getOrderSource();
      if ((j != 0) || (this.b == null))
        continue;
      this.b.warn(this.c, str1 + FM_CN.subSequence("斠）讶卖杷滑坸鄒缠旚仺丶朠它丁", 104));
      continue;
      label696: this.d.businessFinish(false);
      i = 1;
      break label279;
      label709: j = 0;
    }
  }

  public LoginInfo login(String paramString1, String paramString2)
    throws BusinessException
  {
    String str1 = FM_Long.concat("甦戮瘿弚", 182);
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Utils.copyValueOf(6, 39, "甹戯瘤当#zu"));
    String str2;
    Object localObject;
    IMessageHandler localIMessageHandler;
    IMessage localIMessage1;
    if ((paramString1 == null) || ("".equals(paramString1)) || (paramString2 == null) || ("".equals(paramString2)))
    {
      throw new BusinessException(FM_Bytes.startsWith("甮戵瘥式斠～佮儯盂厀攮彘幮", 5, 124), BusinessException.ErrorMessage.local_business_para_error);
      this.d.businessReady(str1, str2);
      localObject = null;
      localIMessage1 = localIMessageHandler.createMessage(1021);
    }
    while (true)
    {
      byte[] arrayOfByte1;
      byte[] arrayOfByte2;
      LoginInfo localLoginInfo;
      try
      {
        ITag localITag3 = localIMessageHandler.createTag(2);
        localITag3.addValue(paramString1);
        localIMessage1.addTag(localITag3);
        ITag localITag4 = localIMessageHandler.createTag(3);
        localITag4.addValue(paramString2);
        localIMessage1.addTag(localITag4);
        byte[] arrayOfByte3 = localIMessage1.toBytes();
        localObject = arrayOfByte3;
        arrayOfByte1 = this.d.interaction(localObject, FM_CN.subSequence("甶戺癧弞", 272), false, str2);
        arrayOfByte2 = new byte[2];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte2.length);
        localLoginInfo = new LoginInfo();
        if (arrayOfByte1.length == 2)
        {
          if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, arrayOfByte2))
            break label722;
          this.a = paramString1;
          localLoginInfo.setResult(0);
          label279: this.d.businessFinish(false);
          return localLoginInfo;
        }
      }
      catch (FMCommunicationMessageException localFMCommunicationMessageException1)
      {
        if (this.b == null)
          continue;
        this.b.warn(this.c, FM_Long.concat("畵房癨彋旿８枛遪带厰讼气敱换凭玲式帠ｙ", 5) + Util4Java.getExceptionInfo(localFMCommunicationMessageException1));
        this.d.throwExceptionAndClose(FM_Int.lastIndexOf(1, "畼扢瘭异斮ｕ柞遻帯厭让氝数捯凸玳弆帽"), BusinessException.ErrorMessage.local_message_message_handle_exception, false);
        continue;
      }
      do
      {
        while (true)
        {
          localIMessageHandler = this.d.getMessageHandler();
          if (localIMessageHandler == null)
          {
            if (this.b != null)
              this.b.warn(this.c, FM_Int.lastIndexOf(3, "畾扠瘣弌斬ｗ淔怲奚瑙器主穸"));
            throw new BusinessException(FM_Long.concat("畴戰癩彈旾？涖怦奐瑙嘢乯空", 4), BusinessException.ErrorMessage.local_message_load_config_fail);
          }
          str2 = this.d.getServer4Business(1021);
          if (str2 != null)
            break;
          if (this.b != null)
            this.b.warn(this.c, str1 + FM_Exception.getChars(210, 6, "施｝菠压奇瑏盋带厫夰财"));
          throw new BusinessException(str1 + FM_Long.concat("斢ｓ莽參处琍皒帲厼奦赧", 252), BusinessException.ErrorMessage.local_app_query_server_fail);
          try
          {
            IMessage localIMessage2 = localIMessageHandler.createMessage(1021, Arrays.copyOfRange(arrayOfByte1, 2, arrayOfByte1.length));
            localLoginInfo.setResult(Util4Java.String2Int(FM_CN.bcdBytesToString(arrayOfByte2), 1001));
            ITag localITag1 = localIMessage2.getTag4Id(36);
            if (localITag1 != null)
              localLoginInfo.setFailureNum(localITag1.getIntVal());
            ITag localITag2 = localIMessage2.getTag4Id(43);
            if (localITag2 == null)
              break label279;
            localLoginInfo.setUserLockTime(localITag2.getIntVal());
          }
          catch (FMCommunicationMessageException localFMCommunicationMessageException2)
          {
          }
        }
        if (this.b == null)
          break label279;
        this.b.warn(this.c, CRCUtil.valueOf(4, "畲扼瘧弘旨＃解极幱厣咉廁收捹凲玩弈幣v") + Util4Java.getExceptionInfo(localFMCommunicationMessageException2));
        break label279;
      }
      while (this.d != null);
      if (this.b != null)
        this.b.warn(this.c, FM_Bytes.startsWith("甭戤瘺彚旫ｇ七勦契琅宨谾丷稡", 4, 46));
      throw new BusinessException(FM_Exception.getChars(124, 88, "畽扺百弈斣！丟勼夑琋嘭刀姞卛奴贸"), BusinessException.ErrorMessage.local_business_init_fail);
      label722: localLoginInfo.setResult(FM_CN.bcdBytesToInt(arrayOfByte2));
    }
  }

  // ERROR //
  public LoginInfo loginVer2(String paramString1, String paramString2)
    throws BusinessException
  {
    // Byte code:
    //   0: ldc_w 1580
    //   3: sipush 162
    //   6: bipush 82
    //   8: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   11: astore_3
    //   12: aload_0
    //   13: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   16: ifnonnull +13 -> 29
    //   19: aload_0
    //   20: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   23: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   26: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   29: aload_0
    //   30: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   33: ifnull +25 -> 58
    //   36: aload_0
    //   37: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   40: aload_0
    //   41: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   44: sipush 260
    //   47: ldc_w 1582
    //   50: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   53: invokeinterface 914 3 0
    //   58: aload_1
    //   59: ifnull +27 -> 86
    //   62: ldc_w 1532
    //   65: aload_1
    //   66: invokevirtual 1534	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   69: ifne +17 -> 86
    //   72: aload_2
    //   73: ifnull +13 -> 86
    //   76: ldc_w 1532
    //   79: aload_2
    //   80: invokevirtual 1534	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   83: ifeq +607 -> 690
    //   86: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   89: dup
    //   90: ldc_w 1584
    //   93: iconst_1
    //   94: bipush 115
    //   96: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   99: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   102: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   105: athrow
    //   106: astore 26
    //   108: aload_0
    //   109: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   112: ifnull +41 -> 153
    //   115: aload_0
    //   116: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   119: aload_0
    //   120: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   123: new 143	java/lang/StringBuilder
    //   126: dup
    //   127: ldc_w 1586
    //   130: iconst_5
    //   131: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   134: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   137: aload 26
    //   139: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   142: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: invokeinterface 223 3 0
    //   153: aload_0
    //   154: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   157: iconst_0
    //   158: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   161: aload 22
    //   163: areturn
    //   164: astore 27
    //   166: aload_0
    //   167: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   170: ifnull +42 -> 212
    //   173: aload_0
    //   174: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   177: aload_0
    //   178: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   181: new 143	java/lang/StringBuilder
    //   184: dup
    //   185: bipush 6
    //   187: ldc_w 1588
    //   190: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   193: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   196: aload 27
    //   198: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   201: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: invokeinterface 223 3 0
    //   212: aload_0
    //   213: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   216: ldc_w 1590
    //   219: bipush 62
    //   221: bipush 82
    //   223: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   226: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   229: iconst_0
    //   230: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   233: aload_0
    //   234: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   237: aload 8
    //   239: iconst_2
    //   240: ldc_w 1592
    //   243: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   246: iconst_0
    //   247: aload 7
    //   249: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   252: astore 20
    //   254: iconst_2
    //   255: newarray byte
    //   257: astore 21
    //   259: aload 20
    //   261: iconst_0
    //   262: aload 21
    //   264: iconst_0
    //   265: aload 21
    //   267: arraylength
    //   268: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   271: new 1540	cn/com/fmsh/tsm/business/bean/LoginInfo
    //   274: dup
    //   275: invokespecial 1541	cn/com/fmsh/tsm/business/bean/LoginInfo:<init>	()V
    //   278: astore 22
    //   280: aload 20
    //   282: arraylength
    //   283: iconst_2
    //   284: if_icmpne +224 -> 508
    //   287: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   290: aload 21
    //   292: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   295: ifeq +101 -> 396
    //   298: aload_0
    //   299: aload_1
    //   300: putfield 1543	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:a	Ljava/lang/String;
    //   303: aload 22
    //   305: iconst_0
    //   306: invokevirtual 1546	cn/com/fmsh/tsm/business/bean/LoginInfo:setResult	(I)V
    //   309: goto -156 -> 153
    //   312: aload_0
    //   313: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   316: ifnull +559 -> 875
    //   319: aload_0
    //   320: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   323: aload_0
    //   324: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   327: iconst_4
    //   328: bipush 11
    //   330: ldc_w 1594
    //   333: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   336: invokeinterface 223 3 0
    //   341: aconst_null
    //   342: astore 6
    //   344: aload 6
    //   346: ifnonnull +63 -> 409
    //   349: aload_0
    //   350: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   353: ifnull +25 -> 378
    //   356: aload_0
    //   357: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   360: aload_0
    //   361: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   364: ldc_w 1596
    //   367: sipush 182
    //   370: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   373: invokeinterface 223 3 0
    //   378: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   381: dup
    //   382: ldc_w 1598
    //   385: iconst_2
    //   386: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   389: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   392: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   395: athrow
    //   396: aload 22
    //   398: aload 21
    //   400: invokestatic 962	cn/com/fmsh/util/FM_CN:bcdBytesToInt	([B)I
    //   403: invokevirtual 1546	cn/com/fmsh/tsm/business/bean/LoginInfo:setResult	(I)V
    //   406: goto -253 -> 153
    //   409: aload_0
    //   410: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   413: sipush 1022
    //   416: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   419: astore 7
    //   421: aload 7
    //   423: ifnonnull +322 -> 745
    //   426: aload_0
    //   427: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   430: ifnull +40 -> 470
    //   433: aload_0
    //   434: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   437: aload_0
    //   438: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   441: new 143	java/lang/StringBuilder
    //   444: dup
    //   445: aload_3
    //   446: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   449: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   452: iconst_3
    //   453: ldc_w 1600
    //   456: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   459: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   462: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   465: invokeinterface 223 3 0
    //   470: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   473: dup
    //   474: new 143	java/lang/StringBuilder
    //   477: dup
    //   478: aload_3
    //   479: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   482: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   485: bipush 104
    //   487: bipush 9
    //   489: ldc_w 1602
    //   492: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   495: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   501: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   504: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   507: athrow
    //   508: aload 4
    //   510: sipush 1021
    //   513: aload 20
    //   515: iconst_2
    //   516: aload 20
    //   518: arraylength
    //   519: invokestatic 188	java/util/Arrays:copyOfRange	([BII)[B
    //   522: invokeinterface 191 3 0
    //   527: astore 23
    //   529: aload 22
    //   531: aload 21
    //   533: invokestatic 1561	cn/com/fmsh/util/FM_CN:bcdBytesToString	([B)Ljava/lang/String;
    //   536: sipush 1001
    //   539: invokestatic 1565	cn/com/fmsh/util/Util4Java:String2Int	(Ljava/lang/String;I)I
    //   542: invokevirtual 1546	cn/com/fmsh/tsm/business/bean/LoginInfo:setResult	(I)V
    //   545: aload 23
    //   547: bipush 36
    //   549: invokeinterface 195 2 0
    //   554: astore 24
    //   556: aload 24
    //   558: ifnull +15 -> 573
    //   561: aload 22
    //   563: aload 24
    //   565: invokeinterface 774 1 0
    //   570: invokevirtual 1568	cn/com/fmsh/tsm/business/bean/LoginInfo:setFailureNum	(I)V
    //   573: aload 23
    //   575: bipush 43
    //   577: invokeinterface 195 2 0
    //   582: astore 25
    //   584: aload 25
    //   586: ifnull -433 -> 153
    //   589: aload 22
    //   591: aload 25
    //   593: invokeinterface 774 1 0
    //   598: invokevirtual 1571	cn/com/fmsh/tsm/business/bean/LoginInfo:setUserLockTime	(I)V
    //   601: goto -448 -> 153
    //   604: aload_0
    //   605: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   608: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   611: astore 4
    //   613: aload 4
    //   615: ifnonnull +51 -> 666
    //   618: aload_0
    //   619: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   622: ifnull +23 -> 645
    //   625: aload_0
    //   626: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   629: aload_0
    //   630: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   633: iconst_5
    //   634: ldc_w 1604
    //   637: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   640: invokeinterface 223 3 0
    //   645: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   648: dup
    //   649: ldc_w 1606
    //   652: bipush 6
    //   654: bipush 72
    //   656: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   659: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   662: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   665: athrow
    //   666: aload_0
    //   667: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   670: invokevirtual 865	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getConfigration	()Lcn/com/fmsh/tsm/business/core/Configration;
    //   673: astore 5
    //   675: aload 5
    //   677: ifnull -365 -> 312
    //   680: aload 5
    //   682: invokevirtual 1609	cn/com/fmsh/tsm/business/core/Configration:getCompanyCode	()Ljava/lang/String;
    //   685: astore 6
    //   687: goto -343 -> 344
    //   690: aload_0
    //   691: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   694: ifnonnull -90 -> 604
    //   697: aload_0
    //   698: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   701: ifnull +23 -> 724
    //   704: aload_0
    //   705: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   708: aload_0
    //   709: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   712: iconst_2
    //   713: ldc_w 1611
    //   716: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   719: invokeinterface 223 3 0
    //   724: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   727: dup
    //   728: sipush 248
    //   731: iconst_3
    //   732: ldc_w 1613
    //   735: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   738: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   741: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   744: athrow
    //   745: aload_0
    //   746: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   749: aload_3
    //   750: aload 7
    //   752: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   755: aconst_null
    //   756: astore 8
    //   758: aload 4
    //   760: sipush 1022
    //   763: invokeinterface 59 2 0
    //   768: astore 9
    //   770: aload 4
    //   772: iconst_2
    //   773: invokeinterface 63 2 0
    //   778: astore 10
    //   780: aload 10
    //   782: aload_1
    //   783: invokeinterface 84 2 0
    //   788: pop
    //   789: aload 9
    //   791: aload 10
    //   793: invokeinterface 75 2 0
    //   798: pop
    //   799: aload 4
    //   801: iconst_3
    //   802: invokeinterface 63 2 0
    //   807: astore 13
    //   809: aload 13
    //   811: aload_2
    //   812: invokeinterface 84 2 0
    //   817: pop
    //   818: aload 9
    //   820: aload 13
    //   822: invokeinterface 75 2 0
    //   827: pop
    //   828: aload 4
    //   830: bipush 137
    //   832: invokeinterface 63 2 0
    //   837: astore 16
    //   839: aload 16
    //   841: aload 6
    //   843: invokeinterface 84 2 0
    //   848: pop
    //   849: aload 9
    //   851: aload 16
    //   853: invokeinterface 75 2 0
    //   858: pop
    //   859: aload 9
    //   861: invokeinterface 117 1 0
    //   866: astore 19
    //   868: aload 19
    //   870: astore 8
    //   872: goto -639 -> 233
    //   875: aconst_null
    //   876: astore 6
    //   878: goto -534 -> 344
    //
    // Exception table:
    //   from	to	target	type
    //   508	601	106	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   770	868	164	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  public int logout()
    throws BusinessException
  {
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, Util4Java.toString("甯戡瘾冮m|/", 40, 15));
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_Bytes.startsWith("甭扰遉凱旻ｃ之劲夑瑑宠豺丧稥", 4, 98));
      throw new BusinessException(BCCUtil.endsWith("畧戾逃冧旡＝乑勤夛瑟嘻刐姌南太贰", 208, 90), BusinessException.ErrorMessage.local_business_init_fail);
    }
    if (this.d.getApduHandler() != null)
      this.d.getApduHandler().close();
    int i = -1;
    String str = this.d.getServer4Business(1022);
    if (str == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_Bytes.startsWith("畵扶逅决旻｝莢厏幮厱俤怦夼赴", 124, 68));
      throw new BusinessException(FM_Exception.getChars(4, 63, "畵戫遛几斯４菠叀带古侲恽奠贵"), BusinessException.ErrorMessage.local_app_query_server_fail);
      if (this.b != null)
        this.b.warn(this.c, Util4Java.toString("甩扮逑凳斷夝琗奸赤", 2, 88));
      this.d.disconnect(str);
    }
    while (true)
    {
      return i;
      if (!this.d.b(str))
        break;
      i = 0;
    }
  }

  // ERROR //
  public int modifyPassword(String paramString1, String paramString2)
    throws BusinessException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   4: ifnonnull +13 -> 17
    //   7: aload_0
    //   8: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   11: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   14: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   17: aload_0
    //   18: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   21: ifnull +25 -> 46
    //   24: aload_0
    //   25: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   28: aload_0
    //   29: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   32: ldc_w 1636
    //   35: sipush 150
    //   38: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   41: invokeinterface 914 3 0
    //   46: bipush 78
    //   48: ldc_w 1638
    //   51: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   54: astore_3
    //   55: aload_1
    //   56: ifnull +23 -> 79
    //   59: aload_1
    //   60: invokevirtual 1641	java/lang/String:length	()I
    //   63: iconst_1
    //   64: if_icmplt +15 -> 79
    //   67: aload_2
    //   68: ifnull +11 -> 79
    //   71: aload_2
    //   72: invokevirtual 1641	java/lang/String:length	()I
    //   75: iconst_1
    //   76: if_icmpge +360 -> 436
    //   79: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   82: dup
    //   83: iconst_4
    //   84: ldc_w 1643
    //   87: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   90: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   93: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   96: athrow
    //   97: aload_0
    //   98: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   101: ifnull +41 -> 142
    //   104: aload_0
    //   105: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   108: aload_0
    //   109: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   112: new 143	java/lang/StringBuilder
    //   115: dup
    //   116: ldc_w 1645
    //   119: iconst_2
    //   120: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   123: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   126: aload 20
    //   128: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   131: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: invokeinterface 223 3 0
    //   142: aload 21
    //   144: invokestatic 962	cn/com/fmsh/util/FM_CN:bcdBytesToInt	([B)I
    //   147: istore 22
    //   149: iload 22
    //   151: ireturn
    //   152: aload_0
    //   153: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   156: ifnonnull +338 -> 494
    //   159: aload_0
    //   160: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   163: ifnull +25 -> 188
    //   166: aload_0
    //   167: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   170: aload_0
    //   171: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   174: sipush 248
    //   177: ldc_w 1647
    //   180: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   183: invokeinterface 223 3 0
    //   188: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   191: dup
    //   192: iconst_4
    //   193: bipush 103
    //   195: ldc_w 1649
    //   198: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   201: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   204: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   207: athrow
    //   208: astore 23
    //   210: aload_0
    //   211: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   214: ifnull +43 -> 257
    //   217: aload_0
    //   218: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   221: aload_0
    //   222: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   225: new 143	java/lang/StringBuilder
    //   228: dup
    //   229: iconst_3
    //   230: bipush 105
    //   232: ldc_w 1651
    //   235: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   238: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   241: aload 23
    //   243: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   246: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   252: invokeinterface 223 3 0
    //   257: aload_0
    //   258: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   261: ldc_w 1653
    //   264: iconst_1
    //   265: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   268: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   271: iconst_0
    //   272: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   275: aload_0
    //   276: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   279: aload 6
    //   281: ldc_w 1655
    //   284: iconst_2
    //   285: bipush 98
    //   287: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   290: iconst_0
    //   291: aload 5
    //   293: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   296: astore 20
    //   298: iconst_2
    //   299: newarray byte
    //   301: astore 21
    //   303: aload 20
    //   305: iconst_0
    //   306: aload 21
    //   308: iconst_0
    //   309: aload 21
    //   311: arraylength
    //   312: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   315: aload_0
    //   316: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   319: iconst_0
    //   320: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   323: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   326: aload 21
    //   328: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   331: ifeq -234 -> 97
    //   334: iconst_0
    //   335: istore 22
    //   337: goto -188 -> 149
    //   340: aload_0
    //   341: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   344: sipush 1031
    //   347: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   350: astore 5
    //   352: aload 5
    //   354: ifnonnull +203 -> 557
    //   357: aload_0
    //   358: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   361: ifnull +40 -> 401
    //   364: aload_0
    //   365: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   368: aload_0
    //   369: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   372: new 143	java/lang/StringBuilder
    //   375: dup
    //   376: aload_3
    //   377: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   380: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   383: iconst_2
    //   384: ldc_w 1657
    //   387: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   390: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   393: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   396: invokeinterface 223 3 0
    //   401: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   404: dup
    //   405: new 143	java/lang/StringBuilder
    //   408: dup
    //   409: aload_3
    //   410: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   413: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   416: iconst_4
    //   417: ldc_w 1659
    //   420: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   423: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   429: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   432: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   435: athrow
    //   436: aload_1
    //   437: invokevirtual 1641	java/lang/String:length	()I
    //   440: bipush 32
    //   442: if_icmpeq +21 -> 463
    //   445: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   448: dup
    //   449: ldc_w 1661
    //   452: iconst_1
    //   453: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   456: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   459: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   462: athrow
    //   463: aload_2
    //   464: invokevirtual 1641	java/lang/String:length	()I
    //   467: bipush 32
    //   469: if_icmpeq -317 -> 152
    //   472: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   475: dup
    //   476: ldc_w 1663
    //   479: sipush 154
    //   482: bipush 21
    //   484: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   487: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   490: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   493: athrow
    //   494: aload_0
    //   495: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   498: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   501: astore 4
    //   503: aload 4
    //   505: ifnonnull -165 -> 340
    //   508: aload_0
    //   509: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   512: ifnull +23 -> 535
    //   515: aload_0
    //   516: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   519: aload_0
    //   520: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   523: ldc_w 1665
    //   526: iconst_4
    //   527: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   530: invokeinterface 223 3 0
    //   535: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   538: dup
    //   539: ldc_w 1667
    //   542: sipush 154
    //   545: bipush 116
    //   547: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   550: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   553: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   556: athrow
    //   557: aload_0
    //   558: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   561: aload_3
    //   562: aload 5
    //   564: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   567: aconst_null
    //   568: astore 6
    //   570: aload 4
    //   572: sipush 1031
    //   575: invokeinterface 59 2 0
    //   580: astore 7
    //   582: aload 4
    //   584: iconst_2
    //   585: invokeinterface 63 2 0
    //   590: astore 8
    //   592: aload 8
    //   594: aload_0
    //   595: getfield 1543	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:a	Ljava/lang/String;
    //   598: invokeinterface 84 2 0
    //   603: pop
    //   604: aload 7
    //   606: aload 8
    //   608: invokeinterface 75 2 0
    //   613: pop
    //   614: aload 4
    //   616: bipush 9
    //   618: invokeinterface 63 2 0
    //   623: astore 11
    //   625: aload 4
    //   627: iconst_3
    //   628: invokeinterface 63 2 0
    //   633: astore 12
    //   635: aload 12
    //   637: aload_1
    //   638: invokeinterface 84 2 0
    //   643: pop
    //   644: aload 11
    //   646: aload 12
    //   648: invokeinterface 438 2 0
    //   653: pop
    //   654: aload 4
    //   656: iconst_3
    //   657: invokeinterface 63 2 0
    //   662: astore 15
    //   664: aload 15
    //   666: aload_2
    //   667: invokeinterface 84 2 0
    //   672: pop
    //   673: aload 11
    //   675: aload 15
    //   677: invokeinterface 438 2 0
    //   682: pop
    //   683: aload 7
    //   685: aload 11
    //   687: invokeinterface 75 2 0
    //   692: pop
    //   693: aload 7
    //   695: invokeinterface 117 1 0
    //   700: astore 19
    //   702: aload 19
    //   704: astore 6
    //   706: goto -431 -> 275
    //
    // Exception table:
    //   from	to	target	type
    //   582	702	208	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public int modifyUserInfo(cn.com.fmsh.tsm.business.bean.UserInfo paramUserInfo)
    throws BusinessException
  {
    // Byte code:
    //   0: ldc_w 1671
    //   3: iconst_5
    //   4: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   7: astore_2
    //   8: aload_0
    //   9: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   12: ifnonnull +13 -> 25
    //   15: aload_0
    //   16: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   19: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   22: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   25: aload_0
    //   26: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   29: ifnull +23 -> 52
    //   32: aload_0
    //   33: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   36: aload_0
    //   37: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   40: ldc_w 1673
    //   43: iconst_2
    //   44: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   47: invokeinterface 914 3 0
    //   52: aload_1
    //   53: ifnonnull +23 -> 76
    //   56: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   59: dup
    //   60: ldc_w 1675
    //   63: iconst_1
    //   64: bipush 122
    //   66: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   69: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   72: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   75: athrow
    //   76: aload_0
    //   77: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   80: ifnonnull +145 -> 225
    //   83: aload_0
    //   84: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   87: ifnull +23 -> 110
    //   90: aload_0
    //   91: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   94: aload_0
    //   95: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   98: ldc_w 1677
    //   101: iconst_5
    //   102: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   105: invokeinterface 223 3 0
    //   110: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   113: dup
    //   114: ldc_w 1679
    //   117: iconst_2
    //   118: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   121: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   124: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   127: athrow
    //   128: aload_0
    //   129: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   132: sipush 1011
    //   135: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   138: astore 4
    //   140: aload 4
    //   142: ifnonnull +315 -> 457
    //   145: aload_0
    //   146: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   149: ifnull +41 -> 190
    //   152: aload_0
    //   153: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   156: aload_0
    //   157: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   160: new 143	java/lang/StringBuilder
    //   163: dup
    //   164: aload_2
    //   165: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   168: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   171: ldc_w 1681
    //   174: iconst_4
    //   175: iconst_4
    //   176: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   179: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokeinterface 223 3 0
    //   190: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   193: dup
    //   194: new 143	java/lang/StringBuilder
    //   197: dup
    //   198: aload_2
    //   199: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   202: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   205: iconst_5
    //   206: ldc_w 1683
    //   209: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   212: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   218: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   221: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   224: athrow
    //   225: aload_0
    //   226: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   229: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   232: astore_3
    //   233: aload_3
    //   234: ifnonnull -106 -> 128
    //   237: aload_0
    //   238: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   241: ifnull +25 -> 266
    //   244: aload_0
    //   245: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   248: aload_0
    //   249: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   252: ldc_w 1685
    //   255: iconst_5
    //   256: bipush 119
    //   258: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   261: invokeinterface 223 3 0
    //   266: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   269: dup
    //   270: ldc_w 1687
    //   273: iconst_5
    //   274: bipush 100
    //   276: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   279: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   282: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   285: athrow
    //   286: aload_0
    //   287: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   290: ifnull +41 -> 331
    //   293: aload_0
    //   294: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   297: aload_0
    //   298: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   301: new 143	java/lang/StringBuilder
    //   304: dup
    //   305: iconst_4
    //   306: ldc_w 1689
    //   309: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   312: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   315: aload 17
    //   317: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   320: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   326: invokeinterface 223 3 0
    //   331: aload 18
    //   333: invokestatic 962	cn/com/fmsh/util/FM_CN:bcdBytesToInt	([B)I
    //   336: istore 19
    //   338: iload 19
    //   340: ireturn
    //   341: astore 20
    //   343: aload_0
    //   344: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   347: ifnull +41 -> 388
    //   350: aload_0
    //   351: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   354: aload_0
    //   355: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   358: new 143	java/lang/StringBuilder
    //   361: dup
    //   362: iconst_2
    //   363: ldc_w 1691
    //   366: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   369: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   372: aload 20
    //   374: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   377: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   383: invokeinterface 223 3 0
    //   388: aload_0
    //   389: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   392: sipush 174
    //   395: ldc_w 1693
    //   398: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   401: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   404: iconst_0
    //   405: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   408: aload_0
    //   409: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   412: aload 5
    //   414: aload_2
    //   415: iconst_0
    //   416: aload 4
    //   418: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   421: astore 17
    //   423: iconst_2
    //   424: newarray byte
    //   426: astore 18
    //   428: aload 17
    //   430: iconst_0
    //   431: aload 18
    //   433: iconst_0
    //   434: aload 18
    //   436: arraylength
    //   437: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   440: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   443: aload 18
    //   445: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   448: ifeq -162 -> 286
    //   451: iconst_0
    //   452: istore 19
    //   454: goto -116 -> 338
    //   457: aload_0
    //   458: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   461: aload_2
    //   462: aload 4
    //   464: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   467: aconst_null
    //   468: astore 5
    //   470: aload_3
    //   471: sipush 1011
    //   474: invokeinterface 59 2 0
    //   479: astore 6
    //   481: aload_3
    //   482: iconst_1
    //   483: invokeinterface 63 2 0
    //   488: astore 7
    //   490: aload 7
    //   492: aload_1
    //   493: invokevirtual 1698	cn/com/fmsh/tsm/business/bean/UserInfo:getUserType	()I
    //   496: i2b
    //   497: invokeinterface 69 2 0
    //   502: pop
    //   503: aload 6
    //   505: aload 7
    //   507: invokeinterface 75 2 0
    //   512: pop
    //   513: aload_3
    //   514: iconst_2
    //   515: invokeinterface 63 2 0
    //   520: astore 10
    //   522: aload 10
    //   524: aload_1
    //   525: invokevirtual 1701	cn/com/fmsh/tsm/business/bean/UserInfo:getUserName	()Ljava/lang/String;
    //   528: invokeinterface 84 2 0
    //   533: pop
    //   534: aload 6
    //   536: aload 10
    //   538: invokeinterface 75 2 0
    //   543: pop
    //   544: aload_3
    //   545: iconst_3
    //   546: invokeinterface 63 2 0
    //   551: astore 13
    //   553: aload 13
    //   555: aload_1
    //   556: invokevirtual 1704	cn/com/fmsh/tsm/business/bean/UserInfo:getPassword	()Ljava/lang/String;
    //   559: invokeinterface 84 2 0
    //   564: pop
    //   565: aload 6
    //   567: aload 13
    //   569: invokeinterface 75 2 0
    //   574: pop
    //   575: aload_1
    //   576: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   579: ifnull +45 -> 624
    //   582: aload_1
    //   583: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   586: invokevirtual 1641	java/lang/String:length	()I
    //   589: iconst_1
    //   590: if_icmple +34 -> 624
    //   593: aload_3
    //   594: iconst_4
    //   595: invokeinterface 63 2 0
    //   600: astore 33
    //   602: aload 33
    //   604: aload_1
    //   605: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   608: invokeinterface 84 2 0
    //   613: pop
    //   614: aload 6
    //   616: aload 33
    //   618: invokeinterface 75 2 0
    //   623: pop
    //   624: aload_1
    //   625: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   628: ifnull +45 -> 673
    //   631: aload_1
    //   632: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   635: invokevirtual 1641	java/lang/String:length	()I
    //   638: iconst_1
    //   639: if_icmple +34 -> 673
    //   642: aload_3
    //   643: iconst_5
    //   644: invokeinterface 63 2 0
    //   649: astore 30
    //   651: aload 30
    //   653: aload_1
    //   654: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   657: invokeinterface 84 2 0
    //   662: pop
    //   663: aload 6
    //   665: aload 30
    //   667: invokeinterface 75 2 0
    //   672: pop
    //   673: aload_1
    //   674: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   677: ifnull +46 -> 723
    //   680: aload_1
    //   681: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   684: invokevirtual 1641	java/lang/String:length	()I
    //   687: iconst_1
    //   688: if_icmple +35 -> 723
    //   691: aload_3
    //   692: bipush 6
    //   694: invokeinterface 63 2 0
    //   699: astore 27
    //   701: aload 27
    //   703: aload_1
    //   704: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   707: invokeinterface 84 2 0
    //   712: pop
    //   713: aload 6
    //   715: aload 27
    //   717: invokeinterface 75 2 0
    //   722: pop
    //   723: aload_1
    //   724: invokevirtual 1716	cn/com/fmsh/tsm/business/bean/UserInfo:getCertType	()I
    //   727: bipush 255
    //   729: if_icmpeq +35 -> 764
    //   732: aload_3
    //   733: bipush 7
    //   735: invokeinterface 63 2 0
    //   740: astore 24
    //   742: aload 24
    //   744: aload_1
    //   745: invokevirtual 1716	cn/com/fmsh/tsm/business/bean/UserInfo:getCertType	()I
    //   748: invokeinterface 69 2 0
    //   753: pop
    //   754: aload 6
    //   756: aload 24
    //   758: invokeinterface 75 2 0
    //   763: pop
    //   764: aload_1
    //   765: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   768: ifnull +46 -> 814
    //   771: aload_1
    //   772: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   775: invokevirtual 1641	java/lang/String:length	()I
    //   778: iconst_1
    //   779: if_icmple +35 -> 814
    //   782: aload_3
    //   783: bipush 8
    //   785: invokeinterface 63 2 0
    //   790: astore 21
    //   792: aload 21
    //   794: aload_1
    //   795: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   798: invokeinterface 84 2 0
    //   803: pop
    //   804: aload 6
    //   806: aload 21
    //   808: invokeinterface 75 2 0
    //   813: pop
    //   814: aload 6
    //   816: invokeinterface 117 1 0
    //   821: astore 16
    //   823: aload 16
    //   825: astore 5
    //   827: goto -419 -> 408
    //
    // Exception table:
    //   from	to	target	type
    //   481	823	341	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public int modifyUserInfoVer2(cn.com.fmsh.tsm.business.bean.UserInfo paramUserInfo)
    throws BusinessException
  {
    // Byte code:
    //   0: iconst_3
    //   1: ldc_w 1722
    //   4: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   7: astore_2
    //   8: aload_0
    //   9: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   12: ifnonnull +13 -> 25
    //   15: aload_0
    //   16: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   19: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   22: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   25: aload_0
    //   26: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   29: ifnull +27 -> 56
    //   32: aload_0
    //   33: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   36: aload_0
    //   37: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   40: sipush 138
    //   43: bipush 71
    //   45: ldc_w 1724
    //   48: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   51: invokeinterface 914 3 0
    //   56: aload_1
    //   57: ifnonnull +280 -> 337
    //   60: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   63: dup
    //   64: new 143	java/lang/StringBuilder
    //   67: dup
    //   68: aload_2
    //   69: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   72: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   75: iconst_1
    //   76: bipush 82
    //   78: ldc_w 1726
    //   81: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   84: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   90: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   93: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   96: athrow
    //   97: aload_0
    //   98: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   101: sipush 1011
    //   104: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   107: astore 4
    //   109: aload 4
    //   111: ifnonnull +465 -> 576
    //   114: aload_0
    //   115: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   118: ifnull +42 -> 160
    //   121: aload_0
    //   122: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   125: aload_0
    //   126: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   129: new 143	java/lang/StringBuilder
    //   132: dup
    //   133: aload_2
    //   134: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   137: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   140: ldc_w 1728
    //   143: iconst_5
    //   144: bipush 77
    //   146: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   149: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   155: invokeinterface 223 3 0
    //   160: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   163: dup
    //   164: new 143	java/lang/StringBuilder
    //   167: dup
    //   168: aload_2
    //   169: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   172: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   175: iconst_5
    //   176: ldc_w 1730
    //   179: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   182: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   188: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   191: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   194: athrow
    //   195: astore 20
    //   197: aload_0
    //   198: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   201: ifnull +50 -> 251
    //   204: aload_0
    //   205: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   208: aload_0
    //   209: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   212: new 143	java/lang/StringBuilder
    //   215: dup
    //   216: aload_2
    //   217: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   220: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   223: ldc_w 1732
    //   226: iconst_3
    //   227: bipush 25
    //   229: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   232: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aload 20
    //   237: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   240: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   246: invokeinterface 223 3 0
    //   251: aload_0
    //   252: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   255: new 143	java/lang/StringBuilder
    //   258: dup
    //   259: aload_2
    //   260: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   263: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   266: iconst_1
    //   267: bipush 16
    //   269: ldc_w 1734
    //   272: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   275: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   281: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   284: iconst_0
    //   285: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   288: aload_0
    //   289: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   292: aload 5
    //   294: aload_2
    //   295: iconst_0
    //   296: aload 4
    //   298: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   301: astore 17
    //   303: iconst_2
    //   304: newarray byte
    //   306: astore 18
    //   308: aload 17
    //   310: iconst_0
    //   311: aload 18
    //   313: iconst_0
    //   314: aload 18
    //   316: arraylength
    //   317: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   320: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   323: aload 18
    //   325: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   328: ifeq +190 -> 518
    //   331: iconst_0
    //   332: istore 19
    //   334: iload 19
    //   336: ireturn
    //   337: aload_0
    //   338: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   341: ifnonnull +84 -> 425
    //   344: aload_0
    //   345: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   348: ifnull +42 -> 390
    //   351: aload_0
    //   352: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   355: aload_0
    //   356: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   359: new 143	java/lang/StringBuilder
    //   362: dup
    //   363: aload_2
    //   364: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   367: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   370: ldc_w 1736
    //   373: iconst_4
    //   374: bipush 45
    //   376: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   379: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   385: invokeinterface 223 3 0
    //   390: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   393: dup
    //   394: new 143	java/lang/StringBuilder
    //   397: dup
    //   398: aload_2
    //   399: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   402: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   405: iconst_3
    //   406: ldc_w 1738
    //   409: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   412: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   418: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   421: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   424: athrow
    //   425: aload_0
    //   426: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   429: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   432: astore_3
    //   433: aload_3
    //   434: ifnonnull -337 -> 97
    //   437: aload_0
    //   438: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   441: ifnull +40 -> 481
    //   444: aload_0
    //   445: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   448: aload_0
    //   449: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   452: new 143	java/lang/StringBuilder
    //   455: dup
    //   456: aload_2
    //   457: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   460: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   463: iconst_5
    //   464: ldc_w 1740
    //   467: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   470: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   476: invokeinterface 223 3 0
    //   481: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   484: dup
    //   485: new 143	java/lang/StringBuilder
    //   488: dup
    //   489: aload_2
    //   490: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   493: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   496: ldc_w 1742
    //   499: iconst_3
    //   500: bipush 101
    //   502: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   505: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   508: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   511: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   514: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   517: athrow
    //   518: aload_0
    //   519: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   522: ifnull +44 -> 566
    //   525: aload_0
    //   526: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   529: aload_0
    //   530: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   533: new 143	java/lang/StringBuilder
    //   536: dup
    //   537: aload_2
    //   538: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   541: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   544: ldc_w 1744
    //   547: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   550: aload 17
    //   552: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   555: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   558: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   561: invokeinterface 223 3 0
    //   566: aload 18
    //   568: invokestatic 962	cn/com/fmsh/util/FM_CN:bcdBytesToInt	([B)I
    //   571: istore 19
    //   573: goto -239 -> 334
    //   576: aload_0
    //   577: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   580: aload_2
    //   581: aload 4
    //   583: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   586: aconst_null
    //   587: astore 5
    //   589: aload_3
    //   590: sipush 1012
    //   593: invokeinterface 59 2 0
    //   598: astore 6
    //   600: aload_3
    //   601: iconst_1
    //   602: invokeinterface 63 2 0
    //   607: astore 7
    //   609: aload 7
    //   611: aload_1
    //   612: invokevirtual 1698	cn/com/fmsh/tsm/business/bean/UserInfo:getUserType	()I
    //   615: i2b
    //   616: invokeinterface 69 2 0
    //   621: pop
    //   622: aload 6
    //   624: aload 7
    //   626: invokeinterface 75 2 0
    //   631: pop
    //   632: aload_3
    //   633: iconst_2
    //   634: invokeinterface 63 2 0
    //   639: astore 10
    //   641: aload 10
    //   643: aload_1
    //   644: invokevirtual 1701	cn/com/fmsh/tsm/business/bean/UserInfo:getUserName	()Ljava/lang/String;
    //   647: invokeinterface 84 2 0
    //   652: pop
    //   653: aload 6
    //   655: aload 10
    //   657: invokeinterface 75 2 0
    //   662: pop
    //   663: aload_3
    //   664: iconst_3
    //   665: invokeinterface 63 2 0
    //   670: astore 13
    //   672: aload 13
    //   674: aload_1
    //   675: invokevirtual 1704	cn/com/fmsh/tsm/business/bean/UserInfo:getPassword	()Ljava/lang/String;
    //   678: invokeinterface 84 2 0
    //   683: pop
    //   684: aload 6
    //   686: aload 13
    //   688: invokeinterface 75 2 0
    //   693: pop
    //   694: aload_1
    //   695: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   698: ifnull +45 -> 743
    //   701: aload_1
    //   702: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   705: invokevirtual 1641	java/lang/String:length	()I
    //   708: iconst_1
    //   709: if_icmple +34 -> 743
    //   712: aload_3
    //   713: iconst_4
    //   714: invokeinterface 63 2 0
    //   719: astore 33
    //   721: aload 33
    //   723: aload_1
    //   724: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   727: invokeinterface 84 2 0
    //   732: pop
    //   733: aload 6
    //   735: aload 33
    //   737: invokeinterface 75 2 0
    //   742: pop
    //   743: aload_1
    //   744: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   747: ifnull +45 -> 792
    //   750: aload_1
    //   751: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   754: invokevirtual 1641	java/lang/String:length	()I
    //   757: iconst_1
    //   758: if_icmple +34 -> 792
    //   761: aload_3
    //   762: iconst_5
    //   763: invokeinterface 63 2 0
    //   768: astore 30
    //   770: aload 30
    //   772: aload_1
    //   773: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   776: invokeinterface 84 2 0
    //   781: pop
    //   782: aload 6
    //   784: aload 30
    //   786: invokeinterface 75 2 0
    //   791: pop
    //   792: aload_1
    //   793: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   796: ifnull +46 -> 842
    //   799: aload_1
    //   800: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   803: invokevirtual 1641	java/lang/String:length	()I
    //   806: iconst_1
    //   807: if_icmple +35 -> 842
    //   810: aload_3
    //   811: bipush 6
    //   813: invokeinterface 63 2 0
    //   818: astore 27
    //   820: aload 27
    //   822: aload_1
    //   823: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   826: invokeinterface 84 2 0
    //   831: pop
    //   832: aload 6
    //   834: aload 27
    //   836: invokeinterface 75 2 0
    //   841: pop
    //   842: aload_1
    //   843: invokevirtual 1716	cn/com/fmsh/tsm/business/bean/UserInfo:getCertType	()I
    //   846: bipush 255
    //   848: if_icmpeq +35 -> 883
    //   851: aload_3
    //   852: bipush 7
    //   854: invokeinterface 63 2 0
    //   859: astore 24
    //   861: aload 24
    //   863: aload_1
    //   864: invokevirtual 1716	cn/com/fmsh/tsm/business/bean/UserInfo:getCertType	()I
    //   867: invokeinterface 69 2 0
    //   872: pop
    //   873: aload 6
    //   875: aload 24
    //   877: invokeinterface 75 2 0
    //   882: pop
    //   883: aload_1
    //   884: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   887: ifnull +46 -> 933
    //   890: aload_1
    //   891: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   894: invokevirtual 1641	java/lang/String:length	()I
    //   897: iconst_1
    //   898: if_icmple +35 -> 933
    //   901: aload_3
    //   902: bipush 8
    //   904: invokeinterface 63 2 0
    //   909: astore 21
    //   911: aload 21
    //   913: aload_1
    //   914: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   917: invokeinterface 84 2 0
    //   922: pop
    //   923: aload 6
    //   925: aload 21
    //   927: invokeinterface 75 2 0
    //   932: pop
    //   933: aload 6
    //   935: invokeinterface 117 1 0
    //   940: astore 16
    //   942: aload 16
    //   944: astore 5
    //   946: goto -658 -> 288
    //
    // Exception table:
    //   from	to	target	type
    //   600	942	195	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  public List<Activity> queryActivities(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    String str1 = FM_CN.subSequence("洯劫俳怮枵讽", 6);
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Bytes.startsWith("派勫侠恰枸讹79{", 4, 94));
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + BCCUtil.endsWith("５三勬奃琇客谴丵穳", 186, 90));
      throw new BusinessException(str1 + FM_Int.lastIndexOf(210, "ｉ乜勦奌瑏嘢剖妇卛奿赪"), BusinessException.ErrorMessage.local_business_init_fail);
      if (paramEnumCardAppType != null)
        break label471;
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Long.concat("ｑ沩朚伾公卵丕廞畽盄簰埝", 5));
      throw new BusinessException(str1 + CRCUtil.valueOf(3, "ｕ泫杒佬典卯丕庄甩盖簸域"), BusinessException.ErrorMessage.local_message_load_config_fail);
      label230: if (this.b != null)
        this.b.warn(this.c, str1 + BCCUtil.endsWith("｟\r&*932\"*2(sy2丷穲", 244, 123));
    }
    String str3;
    IMessageHandler localIMessageHandler;
    label471: String str2;
    while (true)
    {
      str3 = this.d.getServer4Business(1151);
      if (str3 != null)
        break label498;
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_CN.subSequence("无ｉ菣厕奖琇皔幬叾夬赩", 168));
      throw new BusinessException(str1 + FM_CN.subSequence("旤－莧叉奊瑛盈帨叺夨购", 4), BusinessException.ErrorMessage.local_app_query_server_fail);
      localIMessageHandler = this.d.getMessageHandler();
      if (localIMessageHandler != null)
        break;
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Exception.getChars(2, 122, "ｗ淝怠奍瑅嘵乭稫"));
      throw new BusinessException(str1 + FM_Long.concat("ｖ涍恿够琀噹並稽", 258), BusinessException.ErrorMessage.local_message_load_config_fail);
      str2 = null;
      Configration localConfigration = this.d.getConfigration();
      if (localConfigration == null)
        break label230;
      str2 = localConfigration.getCompanyCode();
    }
    label498: this.d.businessReady(str1, str3);
    List localList = a(paramEnumCardAppType, str2, localIMessageHandler, str3);
    this.d.businessFinish(false);
    return localList;
  }

  public BusinessOrder queryBusinessOrder(byte[] paramArrayOfByte)
    throws BusinessException
  {
    String str1 = FM_CN.subSequence("上劾讬午讪纝枯讻旾", 2);
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Exception.getChars(3, 107, "乆勦诰午S") + FM_Bytes.bytesToHexString(paramArrayOfByte) + FM_Int.lastIndexOf(4, "\n设纟侻怴枹访pq."));
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, Util4Java.toString("丛勷诩匕讳續柺诶旿２三助奙瑔宾谽乫稼", 2, 117));
      throw new BusinessException(FM_Exception.getChars(110, 86, "九劼讱匜诹结枮讣旡ａ乙劸奋瑃噳剌妌匋奢赬"), BusinessException.ErrorMessage.local_business_init_fail);
    }
    IMessageHandler localIMessageHandler = this.d.getMessageHandler();
    if (localIMessageHandler == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_CN.subSequence("乎勢诰協诶给柫诿斺ｗ淂怶夌琑噮丯稾", 326));
      throw new BusinessException(FM_Long.concat("乆劦记午柭诱旨ｅ淜怰奎瑓器丱穬", 4), BusinessException.ErrorMessage.local_message_load_config_fail);
    }
    String str2 = this.d.getServer4Business(1122);
    if (str2 == null)
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + Util4Java.toString("旴ｘ菱厎奎琚皊平叢夵赳", 3, 114));
      throw new BusinessException(str1 + CRCUtil.valueOf(3, "斯ｆ菬厚夙琈皛幣叱奣赦"), BusinessException.ErrorMessage.local_app_query_server_fail);
    }
    this.d.businessReady(str1, str2);
    BusinessOrder localBusinessOrder = b(paramArrayOfByte, localIMessageHandler, str2);
    this.d.businessFinish(false);
    return localBusinessOrder;
  }

  public List<BusinessOrder> queryBusinessOrders(int paramInt1, int paramInt2, EnumCardAppType paramEnumCardAppType, EnumBusinessOrderType paramEnumBusinessOrderType, EnumOrderStatus paramEnumOrderStatus)
    throws BusinessException
  {
    String str1 = FM_Exception.getChars(5, 61, "乄劺诺區枷语");
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Bytes.startsWith("丞勽讶卙枡课:bj", 3, 88));
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, BCCUtil.endsWith("丘勯诸匓柷诼＆乌勣奊琜寿豳乤稰", 3, 108));
      throw new BusinessException(FM_Exception.getChars(5, 114, "乄勱诠匁枣诺＆丆劯处瑔嘬剋妃匌夽贻"), BusinessException.ErrorMessage.local_business_init_fail);
    }
    IMessageHandler localIMessageHandler;
    do
    {
      String str2 = this.d.getServer4Business(1132);
      if (str2 == null)
      {
        if (this.b != null)
          this.b.warn(this.c, str1 + CRCUtil.valueOf(5, "断｀菪厘夛琖皅幡右奥赠"));
        throw new BusinessException(str1 + FM_Utils.copyValueOf(1, 116, "旺，菣厞奘瑖盀幫叼失贱"), BusinessException.ErrorMessage.local_app_query_server_fail);
      }
      this.d.businessReady(str1, str2);
      List localList = a(paramEnumOrderStatus, paramInt1 + paramInt2, paramEnumCardAppType, paramEnumBusinessOrderType, localIMessageHandler, str2);
      this.d.businessFinish(false);
      return localList;
      localIMessageHandler = this.d.getMessageHandler();
    }
    while (localIMessageHandler != null);
    if (this.b != null)
      this.b.warn(this.c, FM_Bytes.startsWith("丞勿论升枩认，淒总夊琎嘪並穬", 3, 90));
    throw new BusinessException(BCCUtil.endsWith("丘勶诮匔枳让ｌ涝恥夛琒噡乤稩", 3, 117), BusinessException.ErrorMessage.local_message_load_config_fail);
  }

  public List<BusinessOrder> queryBusinessOrdersVer3(int paramInt1, int paramInt2, EnumCardAppType paramEnumCardAppType, EnumBusinessOrderType paramEnumBusinessOrderType, EnumOrderStatus paramEnumOrderStatus, byte[] paramArrayOfByte)
    throws BusinessException
  {
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, CRCUtil.valueOf(4, "乀勪课匘査语(Gg!w|h9&"));
    String str1 = FM_Int.lastIndexOf(5, "乂勸诸匎枹访");
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_Bytes.startsWith("丅劾诽匊柺诽ｓ久劾夛瑙宦豾严稥", 190, 96));
      throw new BusinessException(FM_Long.concat("世劶讠単柽计ｂ乃勥奋瑜噭刍姐匐夠费", 148), BusinessException.ErrorMessage.local_business_init_fail);
    }
    String str2;
    IMessageHandler localIMessageHandler;
    while (true)
    {
      str2 = this.d.getServer4Business(1133);
      if (str2 != null)
        break;
      if (this.b != null)
        this.b.warn(this.c, str1 + Util4Java.toString("旴）菿厝夊琗盐帤只夬赥", 3, 35));
      throw new BusinessException(str1 + FM_Bytes.startsWith("旴ｏ菳叓夂瑁盌幺叺奺赩", 1, 97), BusinessException.ErrorMessage.local_app_query_server_fail);
      localIMessageHandler = this.d.getMessageHandler();
      if (localIMessageHandler != null)
        continue;
      if (this.b != null)
        this.b.warn(this.c, FM_Bytes.startsWith("东勻讬南枳诨ｒ淚恩奞瑈噪乬稰", 5, 84));
      throw new BusinessException(CRCUtil.valueOf(148, "丐勺诮匈枫诽＜涉恽备瑒嘭乬稽"), BusinessException.ErrorMessage.local_message_load_config_fail);
    }
    this.d.businessReady(str1, str2);
    List localList = a(paramEnumOrderStatus, paramInt1 + paramInt2, paramEnumCardAppType, paramEnumBusinessOrderType, localIMessageHandler, paramArrayOfByte, str2);
    this.d.businessFinish(false);
    return localList;
  }

  public List<BusinessOrder> queryBusinessOrdersVer4(int paramInt1, int paramInt2, EnumCardAppType paramEnumCardAppType, EnumBusinessOrderType paramEnumBusinessOrderType, List<EnumOrderStatus> paramList, byte[] paramArrayOfByte)
    throws BusinessException
  {
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, BCCUtil.endsWith("也勪讳卂枸诡!\0310iun#=w", 198, 38));
    String str1 = FM_Bytes.startsWith("丅劤诩匄柲诿", 286, 102);
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_Long.concat("乁劧讳卉柢诰１乒勲奚瑏宭谾丰穯", 3));
      throw new BusinessException(FM_Int.lastIndexOf(230, "乃勻诹匉枸讼ｓ乚勠奆瑅嘬剘妍卑她赬"), BusinessException.ErrorMessage.local_business_init_fail);
    }
    String str2;
    IMessageHandler localIMessageHandler;
    while (true)
    {
      str2 = this.d.getServer4Business(1134);
      if (str2 != null)
        break;
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Bytes.startsWith("斻＞菠及奅琀盏幣厥夫赺", 332, 69));
      throw new BusinessException(str1 + FM_Long.concat("斬）莧反夂琗皘帴厢奬赭", 2), BusinessException.ErrorMessage.local_app_query_server_fail);
      localIMessageHandler = this.d.getMessageHandler();
      if (localIMessageHandler != null)
        continue;
      if (this.b != null)
        this.b.warn(this.c, FM_Utils.copyValueOf(94, 22, "乓勾讷卞枤讵ａ涋恶夋瑃嘳乫穽"));
      throw new BusinessException(FM_Utils.copyValueOf(180, 24, "丅勶语卒柺诵ｃ淏怰夓琉嘯乥稭"), BusinessException.ErrorMessage.local_message_load_config_fail);
    }
    this.d.businessReady(str1, str2);
    List localList = a(paramList, paramInt1 + paramInt2, paramEnumCardAppType, paramEnumBusinessOrderType, localIMessageHandler, paramArrayOfByte, str2);
    this.d.businessFinish(false);
    return localList;
  }

  // ERROR //
  public cn.com.fmsh.tsm.business.bean.CardBusinessStatus queryCardBusinessStatus(String paramString)
    throws BusinessException
  {
    // Byte code:
    //   0: iconst_5
    //   1: bipush 30
    //   3: ldc_w 1851
    //   6: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   9: astore_2
    //   10: aload_0
    //   11: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   14: ifnonnull +13 -> 27
    //   17: aload_0
    //   18: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   21: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   24: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   27: aload_0
    //   28: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   31: ifnull +26 -> 57
    //   34: aload_0
    //   35: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   38: aload_0
    //   39: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   42: bipush 92
    //   44: bipush 116
    //   46: ldc_w 1853
    //   49: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   52: invokeinterface 914 3 0
    //   57: aload_0
    //   58: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   61: ifnonnull +453 -> 514
    //   64: aload_0
    //   65: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   68: ifnonnull +13 -> 81
    //   71: aload_0
    //   72: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   75: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   78: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   81: aload_0
    //   82: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   85: ifnull +42 -> 127
    //   88: aload_0
    //   89: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   92: aload_0
    //   93: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   96: new 143	java/lang/StringBuilder
    //   99: dup
    //   100: aload_2
    //   101: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   104: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   107: ldc_w 1855
    //   110: iconst_5
    //   111: bipush 103
    //   113: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   116: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   122: invokeinterface 223 3 0
    //   127: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   130: dup
    //   131: new 143	java/lang/StringBuilder
    //   134: dup
    //   135: aload_2
    //   136: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   139: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   142: ldc_w 1857
    //   145: iconst_2
    //   146: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   149: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   155: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   158: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   161: athrow
    //   162: astore 20
    //   164: aload_0
    //   165: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   168: ifnull +50 -> 218
    //   171: aload_0
    //   172: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   175: aload_0
    //   176: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   179: new 143	java/lang/StringBuilder
    //   182: dup
    //   183: aload_2
    //   184: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   187: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   190: ldc_w 1859
    //   193: iconst_1
    //   194: bipush 104
    //   196: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   199: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: aload 20
    //   204: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   207: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   213: invokeinterface 223 3 0
    //   218: aload_0
    //   219: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   222: new 143	java/lang/StringBuilder
    //   225: dup
    //   226: aload_2
    //   227: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   230: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   233: iconst_4
    //   234: ldc_w 1861
    //   237: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   240: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   246: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   249: iconst_0
    //   250: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   253: aload_0
    //   254: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   257: aload 5
    //   259: aload_2
    //   260: iconst_0
    //   261: aload 4
    //   263: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   266: astore 11
    //   268: iconst_2
    //   269: newarray byte
    //   271: astore 12
    //   273: aload 11
    //   275: iconst_0
    //   276: aload 12
    //   278: iconst_0
    //   279: aload 12
    //   281: arraylength
    //   282: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   285: new 1863	cn/com/fmsh/tsm/business/bean/CardBusinessStatus
    //   288: dup
    //   289: invokespecial 1864	cn/com/fmsh/tsm/business/bean/CardBusinessStatus:<init>	()V
    //   292: astore 13
    //   294: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   297: aload 12
    //   299: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   302: ifne +97 -> 399
    //   305: aload_0
    //   306: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   309: ifnull +50 -> 359
    //   312: aload_0
    //   313: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   316: aload_0
    //   317: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   320: new 143	java/lang/StringBuilder
    //   323: dup
    //   324: aload_2
    //   325: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   328: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   331: ldc_w 1866
    //   334: iconst_2
    //   335: bipush 34
    //   337: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   340: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: aload 11
    //   345: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   348: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   354: invokeinterface 223 3 0
    //   359: aload_0
    //   360: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   363: new 143	java/lang/StringBuilder
    //   366: dup
    //   367: aload_2
    //   368: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   371: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   374: iconst_5
    //   375: ldc_w 1868
    //   378: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   381: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   387: aload 12
    //   389: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   392: invokestatic 180	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:instance	(Ljava/lang/String;)Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   395: iconst_0
    //   396: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   399: aload_3
    //   400: sipush 3051
    //   403: aload 11
    //   405: iconst_2
    //   406: aload 11
    //   408: arraylength
    //   409: invokestatic 188	java/util/Arrays:copyOfRange	([BII)[B
    //   412: invokeinterface 191 3 0
    //   417: astore 15
    //   419: aload 15
    //   421: bipush 62
    //   423: invokeinterface 195 2 0
    //   428: astore 16
    //   430: aload 16
    //   432: ifnull +29 -> 461
    //   435: aload 16
    //   437: invokeinterface 697 1 0
    //   442: iconst_0
    //   443: baload
    //   444: invokestatic 1874	cn/com/fmsh/tsm/business/enums/EnumBusinessOrderStatus:getBusinessOrderStatus4ID	(I)Lcn/com/fmsh/tsm/business/enums/EnumBusinessOrderStatus;
    //   447: astore 19
    //   449: aload 19
    //   451: ifnull +10 -> 461
    //   454: aload 13
    //   456: aload 19
    //   458: invokevirtual 1878	cn/com/fmsh/tsm/business/bean/CardBusinessStatus:setBusinessOrderStatus	(Lcn/com/fmsh/tsm/business/enums/EnumBusinessOrderStatus;)V
    //   461: aload 15
    //   463: bipush 63
    //   465: invokeinterface 195 2 0
    //   470: astore 17
    //   472: aload 17
    //   474: ifnull +29 -> 503
    //   477: aload 17
    //   479: invokeinterface 697 1 0
    //   484: iconst_0
    //   485: baload
    //   486: invokestatic 1884	cn/com/fmsh/tsm/business/enums/EnumAppActivationStatus:getActivationStatus4ID	(I)Lcn/com/fmsh/tsm/business/enums/EnumAppActivationStatus;
    //   489: astore 18
    //   491: aload 18
    //   493: ifnull +10 -> 503
    //   496: aload 13
    //   498: aload 18
    //   500: invokevirtual 1888	cn/com/fmsh/tsm/business/bean/CardBusinessStatus:setActivationStatus	(Lcn/com/fmsh/tsm/business/enums/EnumAppActivationStatus;)V
    //   503: aload_0
    //   504: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   507: iconst_0
    //   508: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   511: aload 13
    //   513: areturn
    //   514: aload_0
    //   515: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   518: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   521: astore_3
    //   522: aload_3
    //   523: ifnonnull +227 -> 750
    //   526: aload_0
    //   527: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   530: ifnull +44 -> 574
    //   533: aload_0
    //   534: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   537: aload_0
    //   538: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   541: new 143	java/lang/StringBuilder
    //   544: dup
    //   545: aload_2
    //   546: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   549: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   552: sipush 296
    //   555: bipush 93
    //   557: ldc_w 1890
    //   560: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   563: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   569: invokeinterface 223 3 0
    //   574: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   577: dup
    //   578: new 143	java/lang/StringBuilder
    //   581: dup
    //   582: aload_2
    //   583: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   586: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   589: iconst_3
    //   590: ldc_w 1892
    //   593: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   596: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   599: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   602: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   605: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   608: athrow
    //   609: astore 14
    //   611: aload_0
    //   612: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   615: ifnull +45 -> 660
    //   618: aload_0
    //   619: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   622: aload_0
    //   623: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   626: new 143	java/lang/StringBuilder
    //   629: dup
    //   630: ldc_w 1894
    //   633: sipush 142
    //   636: bipush 46
    //   638: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   641: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   644: aload 11
    //   646: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   649: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   652: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   655: invokeinterface 223 3 0
    //   660: aload_0
    //   661: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   664: iconst_4
    //   665: ldc_w 1896
    //   668: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   671: getstatic 302	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_command_data_invaild	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   674: iconst_0
    //   675: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   678: goto -175 -> 503
    //   681: aload_0
    //   682: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   685: aload_2
    //   686: aload 4
    //   688: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   691: aconst_null
    //   692: astore 5
    //   694: aload_3
    //   695: sipush 3051
    //   698: invokeinterface 59 2 0
    //   703: astore 6
    //   705: aload_3
    //   706: bipush 15
    //   708: invokeinterface 63 2 0
    //   713: astore 7
    //   715: aload 7
    //   717: aload_1
    //   718: invokeinterface 84 2 0
    //   723: pop
    //   724: aload 6
    //   726: aload 7
    //   728: invokeinterface 75 2 0
    //   733: pop
    //   734: aload 6
    //   736: invokeinterface 117 1 0
    //   741: astore 10
    //   743: aload 10
    //   745: astore 5
    //   747: goto -494 -> 253
    //   750: aload_0
    //   751: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   754: sipush 3051
    //   757: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   760: astore 4
    //   762: aload 4
    //   764: ifnonnull -83 -> 681
    //   767: aload_0
    //   768: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   771: ifnull +42 -> 813
    //   774: aload_0
    //   775: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   778: aload_0
    //   779: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   782: new 143	java/lang/StringBuilder
    //   785: dup
    //   786: aload_2
    //   787: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   790: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   793: iconst_3
    //   794: bipush 84
    //   796: ldc_w 1898
    //   799: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   802: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   805: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   808: invokeinterface 223 3 0
    //   813: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   816: dup
    //   817: new 143	java/lang/StringBuilder
    //   820: dup
    //   821: aload_2
    //   822: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   825: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   828: iconst_5
    //   829: ldc_w 1730
    //   832: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   835: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   838: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   841: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   844: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   847: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   705	743	162	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   399	503	609	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  public List<BusinessOrder> queryConfirmDoubtOrder(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    List localList;
    try
    {
      String str1 = CRCUtil.valueOf(4, "z砥诸厢疏训单柴诠");
      if (this.d == null)
      {
        if (this.b == null)
          this.b = LogFactory.getInstance().getLog();
        if (this.b != null)
          this.b.warn(this.c, FM_Long.concat("砰训叻疎讨區枥让ｚ乛勭奓琄寴豹丹穴", 6));
        throw new BusinessException(FM_Int.lastIndexOf(4, "砹诼厶痋诹匉枸讼ｓ业加夆琅噬刘姍匑夹贬"), BusinessException.ErrorMessage.local_business_init_fail);
      }
      while (true)
      {
        this.d.businessReady(str1, str2);
        localList = a(EnumOrderStatus.dubious, 10, paramEnumCardAppType, EnumBusinessOrderType.ORDER_TYPE_RECHARGE, localIMessageHandler, str2);
        this.d.businessFinish(false);
        break;
        IMessageHandler localIMessageHandler = this.d.getMessageHandler();
        if (localIMessageHandler == null)
        {
          if (this.b != null)
            this.b.warn(this.c, Util4Java.toString("硡许厦疇诡包柸讨｛涌怾奚琍嘰丿穨", 272, 45));
          throw new BusinessException(FM_Exception.getChars(5, 66, "砰诤叭痕认匝柯议＂淘恽奐琐嘰丠稦"), BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String str2 = this.d.getServer4Business(1132);
        if (str2 != null)
          continue;
        if (this.b != null)
          this.b.warn(this.c, str1 + BCCUtil.endsWith("旵０菢厘夃理皝帡去夵贸", 292, 25));
        throw new BusinessException(str1 + Util4Java.toString("旷＀莠叔変琞盇帽厩奵赪", 2, 11), BusinessException.ErrorMessage.local_app_query_server_fail);
      }
    }
    catch (cs localcs)
    {
      localList = null;
    }
    return localList;
  }

  public MainOrder queryMainOrder(byte[] paramArrayOfByte)
    throws BusinessException
  {
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, CRCUtil.valueOf(4, "乡诩匉讫绘柪询?,}"));
    String str1 = FM_Int.lastIndexOf(178, "举认卒俩恦柯诩");
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Long.concat("ｗ东劰夘琁寫豼乲稩", 3));
      throw new BusinessException(str1 + FM_Int.lastIndexOf(3, "ｚ乍勹奝瑜嘳剁妖午奮败"), BusinessException.ErrorMessage.local_business_init_fail);
    }
    String str2;
    IMessageHandler localIMessageHandler;
    while (true)
    {
      str2 = this.d.getServer4Business(1122);
      if (str2 != null)
        break;
      if (this.b != null)
        this.b.warn(this.c, str1 + BCCUtil.endsWith("斱ｄ菾厜奏瑊盉帽县奡赴", 72, 1));
      throw new BusinessException(str1 + FM_Bytes.startsWith("斳＀莤厌夅琎盋帥叭奵赮", 228, 39), BusinessException.ErrorMessage.local_app_query_server_fail);
      localIMessageHandler = this.d.getMessageHandler();
      if (localIMessageHandler != null)
        continue;
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Exception.getChars(3, 52, "ｐ涘怫夜瑊器丮稲"));
      throw new BusinessException(str1 + FM_CN.subSequence("？涊恾奄瑉嘶乷稦", 5), BusinessException.ErrorMessage.local_message_load_config_fail);
    }
    this.d.businessReady(str1, str2);
    MainOrder localMainOrder = a(paramArrayOfByte, localIMessageHandler, str2);
    this.d.businessFinish(false);
    return localMainOrder;
  }

  public List<MainOrder> queryMainOrders(int paramInt1, int paramInt2, EnumOrderStatus paramEnumOrderStatus, EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    List localList;
    try
    {
      if (this.b == null)
        this.b = LogFactory.getInstance().getLog();
      if (this.b != null)
        this.b.info(this.c, FM_Utils.copyValueOf(320, 77, "买论匐俳怰枩读h=."));
      if (paramEnumOrderStatus == null);
      for (EnumOrderStatus localEnumOrderStatus = EnumOrderStatus.unknown; ; localEnumOrderStatus = paramEnumOrderStatus)
      {
        String str1 = BCCUtil.endsWith("为诠化俥恪枣讥", 2, 97);
        if (this.d == null)
        {
          if (this.b != null)
            this.b.warn(this.c, str1 + BCCUtil.endsWith("（下勿夏琞宼豳严稶", 5, 45));
          throw new BusinessException(str1 + FM_Bytes.startsWith("／乇劶夕瑍嘭刂妒卅夼财", 2, 90), BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler localIMessageHandler = this.d.getMessageHandler();
        if (localIMessageHandler == null)
        {
          if (this.b != null)
            this.b.warn(this.c, str1 + FM_Long.concat("ｑ涀恼多琏噼严稰", 5));
          throw new BusinessException(str1 + FM_Int.lastIndexOf(3, "ｚ淟怷奝瑜嘳书稧"), BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String str2 = this.d.getServer4Business(1132);
        if (str2 == null)
        {
          if (this.b != null)
            this.b.warn(this.c, str1 + CRCUtil.valueOf(2, "斮ｅ菭厝夘琋皚幼台奠赧"));
          throw new BusinessException(str1 + Util4Java.toString("旲ｊ菿叜夈瑈盔幡古奧赽", 5, 98), BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.d.businessReady(str1, str2);
        localList = a(localEnumOrderStatus, paramInt2 + paramInt1, paramEnumCardAppType, localIMessageHandler, str2);
        this.d.businessFinish(false);
        break;
      }
    }
    catch (cs localcs)
    {
      localList = null;
    }
    return localList;
  }

  public List<MainOrder> queryMainOrdersVer4(int paramInt1, int paramInt2, List<EnumOrderStatus> paramList, EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, CRCUtil.valueOf(116, "乱诹匙俼恡柺诲)\004&&q?)6'"));
    String str1 = FM_Int.lastIndexOf(5, "乣读匏侺怳枸讼");
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_CN.subSequence("～乛勱奛琈寤豭両稰", 196));
      throw new BusinessException(str1 + FM_CN.subSequence("＞丛励夛瑈嘵剑妐匜夨购", 4), BusinessException.ErrorMessage.local_business_init_fail);
    }
    String str2;
    IMessageHandler localIMessageHandler;
    while (true)
    {
      str2 = this.d.getServer4Business(1134);
      if (str2 != null)
        break;
      if (this.b != null)
        this.b.warn(this.c, str1 + Util4Java.toString("斣＊菠叞夝瑌皟帿厭夿赺", 118, 49));
      throw new BusinessException(str1 + FM_Exception.getChars(118, 119, "方ｊ菪厂奏瑄皝幣号夯贰"), BusinessException.ErrorMessage.local_app_query_server_fail);
      localIMessageHandler = this.d.getMessageHandler();
      if (localIMessageHandler != null)
        continue;
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Bytes.startsWith("．涄怹处琌嘼乤穲", 1, 42));
      throw new BusinessException(str1 + FM_Exception.getChars(3, 6, "ｐ涊恧夊琒噲为穼"), BusinessException.ErrorMessage.local_message_load_config_fail);
    }
    this.d.businessReady(str1, str2);
    List localList = a(paramList, paramInt2 + paramInt1, paramEnumCardAppType, localIMessageHandler, str2);
    this.d.businessFinish(false);
    return localList;
  }

  public BusinessOrder queryOrder(byte[] paramArrayOfByte)
    throws BusinessException
  {
    String str1 = CRCUtil.valueOf(2, "匍筝诸匞柹误");
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.debug(this.c, FM_CN.subSequence("丕势讯卉训纜侨怷柢说+:m", 1));
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_Long.concat("匉笓记午柭诱旨ｅ乎勾奎瑓对豪丬穻", 4));
      throw new BusinessException(FM_Bytes.startsWith("卓笃诪卌枯讹旺ｑ且劾奔琇嘺刞姟卓大赢", 5, 49), BusinessException.ErrorMessage.local_business_init_fail);
    }
    IMessageHandler localIMessageHandler = this.d.getMessageHandler();
    if (localIMessageHandler == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_CN.subSequence("卅笋讬午枩讹於ｕ涀恸夂琓嘬乩稸", 2));
      throw new BusinessException(CRCUtil.valueOf(240, "匓筃诪卌柯诹旺ｑ淆怰奔瑇噺丹穮"), BusinessException.ErrorMessage.local_message_load_config_fail);
    }
    String str2 = this.d.getServer4Business(1121);
    if (str2 == null)
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Exception.getChars(3, 42, "斪＊菧厌夀瑈盜幱厼奧败"));
      throw new BusinessException(str1 + FM_Exception.getChars(2, 53, "断＜菲双奋琂皝帽右奩质"), BusinessException.ErrorMessage.local_app_query_server_fail);
    }
    this.d.businessReady(str1, str2);
    BusinessOrder localBusinessOrder = d(paramArrayOfByte, localIMessageHandler, str2);
    this.d.businessFinish(false);
    return localBusinessOrder;
  }

  public PayOrder queryPayOrder(byte[] paramArrayOfByte)
    throws BusinessException
  {
    String str1 = FM_Long.concat("整仞讳卉诡绔柸讪斥", 3);
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Utils.copyValueOf(4, 113, "攠付诳北\b") + FM_Bytes.bytesToHexString(paramArrayOfByte) + BCCUtil.endsWith("_说纄俳怭枷诠|,<", 3, 48));
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_Bytes.startsWith("敶亊让卑讻绐柪讪斷６义勭夁瑘宮豱丳稸", 248, 89));
      throw new BusinessException(FM_CN.subSequence("政仇讬午讪纝枯讻旾；东労奀瑕嘪剌始匙夯质", 2), BusinessException.ErrorMessage.local_business_init_fail);
    }
    String str2;
    do
    {
      this.d.businessReady(str1, str2);
      PayOrder localPayOrder = c(paramArrayOfByte, localIMessageHandler, str2);
      this.d.businessFinish(false);
      return localPayOrder;
      IMessageHandler localIMessageHandler = this.d.getMessageHandler();
      if (localIMessageHandler == null)
      {
        if (this.b != null)
          this.b.warn(this.c, FM_CN.subSequence("攼仚讳匕让纘枨设旽６涁恷奃瑐嘭乮穹", 5));
        throw new BusinessException(CRCUtil.valueOf(298, "支仉讠匆枡讷新；涀恶夎瑝嘤乧稴"), BusinessException.ErrorMessage.local_message_load_config_fail);
      }
      str2 = this.d.getServer4Business(1122);
    }
    while (str2 != null);
    if (this.b != null)
      this.b.warn(this.c, str1 + CRCUtil.valueOf(5, "断｀菪厘夛琖皅幡右奥赠"));
    throw new BusinessException(str1 + FM_CN.subSequence("斴｝菷厙奚琋皘幸只奸赽", 308), BusinessException.ErrorMessage.local_app_query_server_fail);
  }

  public List<PayOrder> queryPayOrders(int paramInt1, int paramInt2, EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    String str1 = CRCUtil.valueOf(2, "敷云诸匞柹误");
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.debug(this.c, BCCUtil.endsWith("放争诳卄柴讳??", 274, 96));
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, CRCUtil.valueOf(5, "整五诿匛柺诲－丈劢奐瑃宯谦丢穳"));
      throw new BusinessException(Util4Java.toString("攬亊讣包柺诬ｑ世勺奎琟嘠刊姍千夵赶", 4, 79), BusinessException.ErrorMessage.local_business_init_fail);
    }
    IMessageHandler localIMessageHandler = this.d.getMessageHandler();
    if (localIMessageHandler == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_Bytes.startsWith("攪于该匍枬诸＇涔恢奚瑉嘨乫稸", 4, 113));
      throw new BusinessException(CRCUtil.valueOf(3, "敶互诹匙柸诬３涘恮奖瑅嘼乿穬"), BusinessException.ErrorMessage.local_message_load_config_fail);
    }
    String str2 = this.d.getServer4Business(1132);
    if (str2 == null)
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + BCCUtil.endsWith("斿ｅ莾叟奍瑏皍幺厹奸贬", 202, 32));
      throw new BusinessException(str1 + CRCUtil.valueOf(190, "既）莡发奜瑏盞常召夼贻"), BusinessException.ErrorMessage.local_app_query_server_fail);
    }
    this.d.businessReady(str1, str2);
    List localList = a(EnumOrderStatus.unknown.getId(), paramInt1 + paramInt2, paramEnumCardAppType, localIMessageHandler, str2);
    this.d.businessFinish(false);
    return localList;
  }

  public List<PayOrder> queryPayOrdersVer4(int paramInt1, int paramInt2, EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    String str1 = BCCUtil.endsWith("攬亟诩博柶讵", 4, 100);
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, BCCUtil.endsWith("攬亗诹匒柶诽%ym", 4, 108));
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, FM_CN.subSequence("攱井课匞枿诫４丝劷奁瑒宺谳主穪", 176));
      throw new BusinessException(FM_Int.lastIndexOf(72, "攴仄访卋柺订ｍ乘勢奀瑃嘮剚妃卟奻赮"), BusinessException.ErrorMessage.local_business_init_fail);
    }
    String str2;
    IMessageHandler localIMessageHandler;
    while (true)
    {
      str2 = this.d.getServer4Business(1134);
      if (str2 != null)
        break;
      if (this.b != null)
        this.b.warn(this.c, str1 + BCCUtil.endsWith("旵｝莨厛够琏盓并厣奰贪", 4, 78));
      throw new BusinessException(str1 + FM_Exception.getChars(3, 7, "斪／莽叇夜琙皂幾古太赧"), BusinessException.ErrorMessage.local_app_query_server_fail);
      localIMessageHandler = this.d.getMessageHandler();
      if (localIMessageHandler != null)
        continue;
      if (this.b != null)
        this.b.warn(this.c, CRCUtil.valueOf(3, "敶互诹匙柸诬３涘恮奖瑅嘼乿穬"));
      throw new BusinessException(FM_Bytes.startsWith("攪亂训卑枼诬／淐恢夆瑑噤主稬", 4, 85), BusinessException.ErrorMessage.local_message_load_config_fail);
    }
    this.d.businessReady(str1, str2);
    List localList = b(null, paramInt1 + paramInt2, paramEnumCardAppType, localIMessageHandler, str2);
    this.d.businessFinish(false);
    return localList;
  }

  public PreDepositInfo queryPreDeposit(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    String str1 = FM_Utils.copyValueOf(5, 52, "颔缪醉俭怯柱讪");
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Utils.copyValueOf(2, 67, "颉缾釂侷恶枹诽l+f"));
    if (this.d == null)
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + CRCUtil.valueOf(3, "ｕ乐勺奈琛寷豾个穻"));
      throw new BusinessException(str1 + FM_Exception.getChars(286, 105, "；业勨奖琝噬刐妝卉她贴"), BusinessException.ErrorMessage.local_business_init_fail);
    }
    IMessageHandler localIMessageHandler = this.d.getMessageHandler();
    label283: String str3;
    String str2;
    if (localIMessageHandler == null)
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Long.concat("ｗ涎恾夘琁噺丧稲", 3));
      throw new BusinessException(str1 + FM_CN.subSequence("＝消恠多瑋嘴乱稠", 3), BusinessException.ErrorMessage.local_message_load_config_fail);
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Exception.getChars(5, 81, "ｒ\fo?$z#gg#a6$;乶穧"));
      str3 = this.d.getServer4Business(1161);
      if (str3 == null)
      {
        if (this.b != null)
          this.b.warn(this.c, str1 + CRCUtil.valueOf(4, "斬ｇ菫厛多琉的幢史奢赡"));
        throw new BusinessException(str1 + FM_Int.lastIndexOf(3, "斠｛華厏奞瑝盘帮厮奮败"), BusinessException.ErrorMessage.local_app_query_server_fail);
      }
    }
    else
    {
      do
      {
        str2 = null;
        Configration localConfigration = this.d.getConfigration();
        if (localConfigration == null)
          break;
        str2 = localConfigration.getCompanyCode();
        break label283;
      }
      while (paramEnumCardAppType != null);
      if (this.b != null)
        this.b.warn(this.c, str1 + Util4Java.toString("７油朜伲兪卭七庒甫的籦垑", 316, 125));
      throw new BusinessException(str1 + FM_Exception.getChars(2, 91, "ｗ泷朘伬儢匣丗廌畻皊籲埏"), BusinessException.ErrorMessage.local_message_load_config_fail);
    }
    this.d.businessReady(str1, str3);
    PreDepositInfo localPreDepositInfo = b(paramEnumCardAppType, str2, localIMessageHandler, str3);
    this.d.businessFinish(false);
    return localPreDepositInfo;
  }

  // ERROR //
  public List<TerminalBackInfo> queryTerminalInfoBack(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt, cn.com.fmsh.tsm.business.enums.EnumResultsSortType paramEnumResultsSortType)
    throws BusinessException
  {
    // Byte code:
    //   0: ldc_w 2076
    //   3: iconst_2
    //   4: bipush 92
    //   6: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   9: astore 5
    //   11: aload_0
    //   12: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   15: ifnonnull +13 -> 28
    //   18: aload_0
    //   19: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   22: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   25: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   28: aload_0
    //   29: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   32: ifnull +18 -> 50
    //   35: aload_0
    //   36: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   39: aload_0
    //   40: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   43: aload 5
    //   45: invokeinterface 914 3 0
    //   50: iload_3
    //   51: ifge +251 -> 302
    //   54: aload_0
    //   55: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   58: ifnull +44 -> 102
    //   61: aload_0
    //   62: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   65: aload_0
    //   66: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   69: new 143	java/lang/StringBuilder
    //   72: dup
    //   73: aload 5
    //   75: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   78: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   81: ldc_w 2078
    //   84: sipush 228
    //   87: iconst_5
    //   88: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   91: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: invokeinterface 223 3 0
    //   102: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   105: dup
    //   106: new 143	java/lang/StringBuilder
    //   109: dup
    //   110: aload 5
    //   112: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   115: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   118: ldc_w 2080
    //   121: iconst_5
    //   122: bipush 35
    //   124: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   127: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   136: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   139: athrow
    //   140: aload_0
    //   141: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   144: ifnonnull +743 -> 887
    //   147: aload_0
    //   148: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   151: ifnonnull +13 -> 164
    //   154: aload_0
    //   155: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   158: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   161: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   164: aload_0
    //   165: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   168: ifnull +43 -> 211
    //   171: aload_0
    //   172: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   175: aload_0
    //   176: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   179: new 143	java/lang/StringBuilder
    //   182: dup
    //   183: aload 5
    //   185: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   188: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   191: ldc_w 2082
    //   194: sipush 310
    //   197: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   200: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokeinterface 223 3 0
    //   211: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   214: dup
    //   215: new 143	java/lang/StringBuilder
    //   218: dup
    //   219: aload 5
    //   221: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   224: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   227: ldc_w 2084
    //   230: sipush 370
    //   233: bipush 109
    //   235: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   238: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   241: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   244: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   247: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   250: athrow
    //   251: aload 15
    //   253: arraylength
    //   254: istore 16
    //   256: iconst_0
    //   257: istore 17
    //   259: goto +893 -> 1152
    //   262: aload 15
    //   264: iload 17
    //   266: aaload
    //   267: astore 18
    //   269: aload 18
    //   271: ifnull +25 -> 296
    //   274: aload 18
    //   276: invokestatic 2089	cn/com/fmsh/tsm/business/bean/TerminalBackInfo:fromTag	(Lcn/com/fmsh/communication/message/ITag;)Lcn/com/fmsh/tsm/business/bean/TerminalBackInfo;
    //   279: astore 19
    //   281: aload 19
    //   283: ifnull +13 -> 296
    //   286: aload 12
    //   288: aload 19
    //   290: invokeinterface 349 2 0
    //   295: pop
    //   296: iinc 17 1
    //   299: goto +853 -> 1152
    //   302: aload 4
    //   304: ifnonnull -164 -> 140
    //   307: aload_0
    //   308: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   311: ifnull +41 -> 352
    //   314: aload_0
    //   315: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   318: aload_0
    //   319: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   322: new 143	java/lang/StringBuilder
    //   325: dup
    //   326: aload 5
    //   328: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   331: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   334: iconst_4
    //   335: ldc_w 2091
    //   338: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   341: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   347: invokeinterface 223 3 0
    //   352: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   355: dup
    //   356: new 143	java/lang/StringBuilder
    //   359: dup
    //   360: aload 5
    //   362: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   365: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   368: sipush 224
    //   371: ldc_w 2093
    //   374: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   377: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   383: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   386: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   389: athrow
    //   390: aload_0
    //   391: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   394: aload 5
    //   396: aload 7
    //   398: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   401: aconst_null
    //   402: astore 8
    //   404: aload 6
    //   406: sipush 4011
    //   409: invokeinterface 59 2 0
    //   414: astore 9
    //   416: aload_1
    //   417: ifnull +33 -> 450
    //   420: aload 6
    //   422: bipush 81
    //   424: invokeinterface 63 2 0
    //   429: astore 32
    //   431: aload 32
    //   433: aload_1
    //   434: invokeinterface 91 2 0
    //   439: pop
    //   440: aload 9
    //   442: aload 32
    //   444: invokeinterface 75 2 0
    //   449: pop
    //   450: aload_2
    //   451: ifnull +33 -> 484
    //   454: aload 6
    //   456: bipush 85
    //   458: invokeinterface 63 2 0
    //   463: astore 29
    //   465: aload 29
    //   467: aload_2
    //   468: invokeinterface 91 2 0
    //   473: pop
    //   474: aload 9
    //   476: aload 29
    //   478: invokeinterface 75 2 0
    //   483: pop
    //   484: aload 6
    //   486: bipush 38
    //   488: invokeinterface 63 2 0
    //   493: astore 21
    //   495: aload 21
    //   497: iload_3
    //   498: invokeinterface 69 2 0
    //   503: pop
    //   504: aload 9
    //   506: aload 21
    //   508: invokeinterface 75 2 0
    //   513: pop
    //   514: aload 6
    //   516: bipush 86
    //   518: invokeinterface 63 2 0
    //   523: astore 24
    //   525: iconst_1
    //   526: newarray byte
    //   528: astore 25
    //   530: aload 25
    //   532: iconst_0
    //   533: aload 4
    //   535: invokevirtual 2096	cn/com/fmsh/tsm/business/enums/EnumResultsSortType:getId	()I
    //   538: i2b
    //   539: bastore
    //   540: aload 24
    //   542: aload 25
    //   544: invokeinterface 91 2 0
    //   549: pop
    //   550: aload 9
    //   552: aload 24
    //   554: invokeinterface 75 2 0
    //   559: pop
    //   560: aload 9
    //   562: invokeinterface 117 1 0
    //   567: astore 28
    //   569: aload 28
    //   571: astore 8
    //   573: aload_0
    //   574: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   577: aload 8
    //   579: aload 5
    //   581: iconst_0
    //   582: aload 7
    //   584: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   587: astore 11
    //   589: aload_0
    //   590: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   593: iconst_0
    //   594: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   597: new 321	java/util/ArrayList
    //   600: dup
    //   601: invokespecial 322	java/util/ArrayList:<init>	()V
    //   604: astore 12
    //   606: aload 6
    //   608: sipush 4011
    //   611: aload 11
    //   613: iconst_2
    //   614: aload 11
    //   616: arraylength
    //   617: invokestatic 188	java/util/Arrays:copyOfRange	([BII)[B
    //   620: invokeinterface 191 3 0
    //   625: bipush 84
    //   627: invokeinterface 195 2 0
    //   632: astore 14
    //   634: aload 14
    //   636: ifnonnull +447 -> 1083
    //   639: aload_0
    //   640: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   643: ifnull +516 -> 1159
    //   646: aload_0
    //   647: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   650: aload_0
    //   651: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   654: new 143	java/lang/StringBuilder
    //   657: dup
    //   658: aload 5
    //   660: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   663: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   666: iconst_2
    //   667: ldc_w 2098
    //   670: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   673: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   676: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   679: invokeinterface 361 3 0
    //   684: goto +475 -> 1159
    //   687: astore 13
    //   689: aload_0
    //   690: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   693: ifnull +51 -> 744
    //   696: aload_0
    //   697: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   700: aload_0
    //   701: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   704: new 143	java/lang/StringBuilder
    //   707: dup
    //   708: aload 5
    //   710: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   713: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   716: iconst_5
    //   717: bipush 57
    //   719: ldc_w 2100
    //   722: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   725: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   728: aload 11
    //   730: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   733: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   736: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   739: invokeinterface 223 3 0
    //   744: aload_0
    //   745: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   748: new 143	java/lang/StringBuilder
    //   751: dup
    //   752: aload 5
    //   754: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   757: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   760: ldc_w 2102
    //   763: iconst_2
    //   764: bipush 20
    //   766: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   769: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   772: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   775: getstatic 302	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_command_data_invaild	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   778: iconst_0
    //   779: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   782: goto +377 -> 1159
    //   785: aload_0
    //   786: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   789: sipush 4001
    //   792: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   795: astore 7
    //   797: aload 7
    //   799: ifnonnull -409 -> 390
    //   802: aload_0
    //   803: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   806: ifnull +43 -> 849
    //   809: aload_0
    //   810: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   813: aload_0
    //   814: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   817: new 143	java/lang/StringBuilder
    //   820: dup
    //   821: aload 5
    //   823: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   826: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   829: sipush 308
    //   832: ldc_w 2104
    //   835: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   838: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   841: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   844: invokeinterface 223 3 0
    //   849: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   852: dup
    //   853: new 143	java/lang/StringBuilder
    //   856: dup
    //   857: aload 5
    //   859: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   862: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   865: iconst_5
    //   866: bipush 104
    //   868: ldc_w 2106
    //   871: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   874: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   877: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   880: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   883: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   886: athrow
    //   887: aload_0
    //   888: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   891: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   894: astore 6
    //   896: aload 6
    //   898: ifnonnull -113 -> 785
    //   901: aload_0
    //   902: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   905: ifnull +43 -> 948
    //   908: aload_0
    //   909: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   912: aload_0
    //   913: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   916: new 143	java/lang/StringBuilder
    //   919: dup
    //   920: aload 5
    //   922: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   925: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   928: ldc_w 2108
    //   931: iconst_1
    //   932: bipush 106
    //   934: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   937: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   940: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   943: invokeinterface 223 3 0
    //   948: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   951: dup
    //   952: new 143	java/lang/StringBuilder
    //   955: dup
    //   956: aload 5
    //   958: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   961: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   964: ldc_w 2110
    //   967: iconst_2
    //   968: bipush 123
    //   970: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   973: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   976: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   979: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   982: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   985: athrow
    //   986: astore 10
    //   988: aload_0
    //   989: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   992: ifnull +50 -> 1042
    //   995: aload_0
    //   996: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   999: aload_0
    //   1000: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   1003: new 143	java/lang/StringBuilder
    //   1006: dup
    //   1007: aload 5
    //   1009: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1012: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1015: bipush 120
    //   1017: ldc_w 2112
    //   1020: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   1023: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1026: aload 10
    //   1028: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   1031: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1034: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1037: invokeinterface 223 3 0
    //   1042: aload_0
    //   1043: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   1046: new 143	java/lang/StringBuilder
    //   1049: dup
    //   1050: aload 5
    //   1052: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1055: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1058: sipush 360
    //   1061: ldc_w 2114
    //   1064: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   1067: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1070: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1073: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   1076: iconst_0
    //   1077: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   1080: goto -507 -> 573
    //   1083: aload 14
    //   1085: invokeinterface 336 1 0
    //   1090: astore 15
    //   1092: aload 15
    //   1094: ifnull +10 -> 1104
    //   1097: aload 15
    //   1099: arraylength
    //   1100: iconst_1
    //   1101: if_icmpge -850 -> 251
    //   1104: aload_0
    //   1105: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1108: ifnull +51 -> 1159
    //   1111: aload_0
    //   1112: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   1115: aload_0
    //   1116: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   1119: new 143	java/lang/StringBuilder
    //   1122: dup
    //   1123: aload 5
    //   1125: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1128: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1131: ldc_w 2116
    //   1134: iconst_4
    //   1135: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   1138: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1141: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1144: invokeinterface 361 3 0
    //   1149: goto +10 -> 1159
    //   1152: iload 17
    //   1154: iload 16
    //   1156: if_icmplt -894 -> 262
    //   1159: aload 12
    //   1161: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   251	296	687	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   606	684	687	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   1083	1149	687	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   420	569	986	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  public List<BusinessOrder> queryUnsolvedOrder(EnumCardAppType paramEnumCardAppType)
    throws BusinessException
  {
    String str1 = FM_Int.lastIndexOf(2, "u杼凤仼晊枿讹");
    if (this.d == null)
    {
      if (this.b == null)
        this.b = LogFactory.getInstance().getLog();
      if (this.b != null)
        this.b.warn(this.c, Util4Java.toString("末冧代昉枨询日ｊ七勭奛琔宼豹乱稤", 258, 51));
      throw new BusinessException(BCCUtil.endsWith("朩况亭星柪诰旣４丁势奅瑂嘯剗妆卆奢赳", 164, 3), BusinessException.ErrorMessage.local_business_init_fail);
    }
    String str2;
    List localList1;
    Iterator localIterator2;
    label204: IMessageHandler localIMessageHandler;
    while (true)
    {
      str2 = this.d.getServer4Business(1132);
      if (str2 != null)
        break label322;
      if (this.b != null)
        this.b.warn(this.c, str1 + Util4Java.toString("斣＃莾厕夙琑盕常叵奮赼", 214, 90));
      throw new BusinessException(str1 + BCCUtil.endsWith("旵ｘ菲厀奃琞皍幩叻奭赨", 4, 113), BusinessException.ErrorMessage.local_app_query_server_fail);
      localList1.add((BusinessOrder)localIterator2.next());
      if (localIterator2.hasNext())
        break;
      this.d.businessFinish(false);
      return localList1;
      localIMessageHandler = this.d.getMessageHandler();
      if (localIMessageHandler != null)
        continue;
      if (this.b != null)
        this.b.warn(this.c, CRCUtil.valueOf(5, "東凿仹晝柺诲旷＞涋总奁瑐嘯丢穳"));
      throw new BusinessException(CRCUtil.valueOf(3, "杳凹仿晟柸诬早＜涉怽奇瑒嘭丬穽"), BusinessException.ErrorMessage.local_message_load_config_fail);
    }
    label396: 
    while (true)
    {
      Iterator localIterator1;
      localList1.add((BusinessOrder)localIterator1.next());
      while (true)
      {
        if (localIterator1.hasNext())
          break label396;
        localIterator2 = localList3.iterator();
        break label204;
        break;
        label322: this.d.businessReady(str1, str2);
        localList1 = a(EnumOrderStatus.unsettled, 10, paramEnumCardAppType, EnumBusinessOrderType.ORDER_TYPE_RECHARGE, localIMessageHandler, str2);
        List localList2 = a(EnumOrderStatus.hasPaid, 10, paramEnumCardAppType, EnumBusinessOrderType.ORDER_TYPE_RECHARGE, localIMessageHandler, str2);
        List localList3 = a(EnumOrderStatus.failure, 10, paramEnumCardAppType, EnumBusinessOrderType.ORDER_TYPE_RECHARGE, localIMessageHandler, str2);
        localIterator1 = localList2.iterator();
      }
    }
  }

  // ERROR //
  public cn.com.fmsh.tsm.business.bean.UserInfo queryUserInfo(String paramString)
    throws BusinessException
  {
    // Byte code:
    //   0: ldc_w 2144
    //   3: sipush 264
    //   6: bipush 90
    //   8: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   11: astore_2
    //   12: aload_1
    //   13: ifnull +13 -> 26
    //   16: ldc_w 1532
    //   19: aload_1
    //   20: invokevirtual 1534	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   23: ifeq +555 -> 578
    //   26: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   29: dup
    //   30: ldc_w 2146
    //   33: bipush 106
    //   35: iconst_5
    //   36: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   39: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   42: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   45: athrow
    //   46: astore 21
    //   48: aload_0
    //   49: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   52: ifnull +45 -> 97
    //   55: aload_0
    //   56: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   59: aload_0
    //   60: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   63: new 143	java/lang/StringBuilder
    //   66: dup
    //   67: ldc_w 2148
    //   70: sipush 206
    //   73: bipush 43
    //   75: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   78: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   81: aload 21
    //   83: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   86: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: invokeinterface 223 3 0
    //   97: aload_0
    //   98: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   101: iconst_4
    //   102: bipush 115
    //   104: ldc_w 2150
    //   107: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   110: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   113: iconst_0
    //   114: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   117: aload_0
    //   118: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   121: iconst_0
    //   122: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   125: aload 13
    //   127: areturn
    //   128: astore 22
    //   130: aload_0
    //   131: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   134: ifnull +43 -> 177
    //   137: aload_0
    //   138: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   141: aload_0
    //   142: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   145: new 143	java/lang/StringBuilder
    //   148: dup
    //   149: ldc_w 2152
    //   152: iconst_1
    //   153: bipush 17
    //   155: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   158: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   161: aload 22
    //   163: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   166: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   172: invokeinterface 223 3 0
    //   177: aload_0
    //   178: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   181: iconst_5
    //   182: bipush 100
    //   184: ldc_w 2154
    //   187: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   190: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   193: iconst_0
    //   194: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   197: aload_0
    //   198: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   201: aload 5
    //   203: aload_2
    //   204: iconst_0
    //   205: aload 4
    //   207: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   210: astore 11
    //   212: iconst_2
    //   213: newarray byte
    //   215: astore 12
    //   217: aload 11
    //   219: iconst_0
    //   220: aload 12
    //   222: iconst_0
    //   223: aload 12
    //   225: arraylength
    //   226: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   229: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   232: aload 12
    //   234: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   237: ifne +76 -> 313
    //   240: aload_0
    //   241: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   244: ifnull +44 -> 288
    //   247: aload_0
    //   248: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   251: aload_0
    //   252: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   255: new 143	java/lang/StringBuilder
    //   258: dup
    //   259: ldc_w 2156
    //   262: bipush 6
    //   264: bipush 9
    //   266: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   269: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   272: aload 11
    //   274: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   277: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   283: invokeinterface 223 3 0
    //   288: aload_0
    //   289: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   292: ldc_w 2158
    //   295: sipush 208
    //   298: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   301: aload 12
    //   303: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   306: invokestatic 180	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:instance	(Ljava/lang/String;)Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   309: iconst_0
    //   310: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   313: new 1695	cn/com/fmsh/tsm/business/bean/UserInfo
    //   316: dup
    //   317: invokespecial 2159	cn/com/fmsh/tsm/business/bean/UserInfo:<init>	()V
    //   320: astore 13
    //   322: aload_3
    //   323: sipush 1051
    //   326: aload 11
    //   328: iconst_2
    //   329: aload 11
    //   331: arraylength
    //   332: invokestatic 188	java/util/Arrays:copyOfRange	([BII)[B
    //   335: invokeinterface 191 3 0
    //   340: astore 14
    //   342: aload 14
    //   344: iconst_1
    //   345: invokeinterface 195 2 0
    //   350: astore 15
    //   352: aload 15
    //   354: ifnull +15 -> 369
    //   357: aload 13
    //   359: aload 15
    //   361: invokeinterface 774 1 0
    //   366: invokevirtual 2162	cn/com/fmsh/tsm/business/bean/UserInfo:setUserType	(I)V
    //   369: aload 14
    //   371: iconst_4
    //   372: invokeinterface 195 2 0
    //   377: astore 16
    //   379: aload 16
    //   381: ifnull +15 -> 396
    //   384: aload 13
    //   386: aload 16
    //   388: invokeinterface 780 1 0
    //   393: invokevirtual 2165	cn/com/fmsh/tsm/business/bean/UserInfo:setMail	(Ljava/lang/String;)V
    //   396: aload 14
    //   398: iconst_5
    //   399: invokeinterface 195 2 0
    //   404: astore 17
    //   406: aload 17
    //   408: ifnull +15 -> 423
    //   411: aload 13
    //   413: aload 17
    //   415: invokeinterface 780 1 0
    //   420: invokevirtual 2168	cn/com/fmsh/tsm/business/bean/UserInfo:setPhone	(Ljava/lang/String;)V
    //   423: aload 14
    //   425: bipush 6
    //   427: invokeinterface 195 2 0
    //   432: astore 18
    //   434: aload 18
    //   436: ifnull +15 -> 451
    //   439: aload 13
    //   441: aload 18
    //   443: invokeinterface 780 1 0
    //   448: invokevirtual 2171	cn/com/fmsh/tsm/business/bean/UserInfo:setRealName	(Ljava/lang/String;)V
    //   451: aload 14
    //   453: bipush 7
    //   455: invokeinterface 195 2 0
    //   460: astore 19
    //   462: aload 19
    //   464: ifnull +15 -> 479
    //   467: aload 13
    //   469: aload 19
    //   471: invokeinterface 774 1 0
    //   476: invokevirtual 2174	cn/com/fmsh/tsm/business/bean/UserInfo:setCertType	(I)V
    //   479: aload 14
    //   481: bipush 8
    //   483: invokeinterface 195 2 0
    //   488: astore 20
    //   490: aload 20
    //   492: ifnull -375 -> 117
    //   495: aload 13
    //   497: aload 20
    //   499: invokeinterface 780 1 0
    //   504: invokevirtual 2177	cn/com/fmsh/tsm/business/bean/UserInfo:setCertNo	(Ljava/lang/String;)V
    //   507: goto -390 -> 117
    //   510: aload_0
    //   511: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   514: aload_2
    //   515: aload 4
    //   517: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   520: aconst_null
    //   521: astore 5
    //   523: aload_3
    //   524: sipush 1051
    //   527: invokeinterface 59 2 0
    //   532: astore 6
    //   534: aload_3
    //   535: iconst_2
    //   536: invokeinterface 63 2 0
    //   541: astore 7
    //   543: aload 7
    //   545: aload_1
    //   546: invokeinterface 84 2 0
    //   551: pop
    //   552: aload 6
    //   554: aload 7
    //   556: invokeinterface 75 2 0
    //   561: pop
    //   562: aload 6
    //   564: invokeinterface 117 1 0
    //   569: astore 10
    //   571: aload 10
    //   573: astore 5
    //   575: goto -378 -> 197
    //   578: aload_0
    //   579: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   582: ifnonnull +67 -> 649
    //   585: aload_0
    //   586: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   589: ifnonnull +13 -> 602
    //   592: aload_0
    //   593: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   596: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   599: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   602: aload_0
    //   603: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   606: ifnull +25 -> 631
    //   609: aload_0
    //   610: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   613: aload_0
    //   614: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   617: sipush 132
    //   620: ldc_w 2179
    //   623: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   626: invokeinterface 223 3 0
    //   631: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   634: dup
    //   635: iconst_3
    //   636: ldc_w 2181
    //   639: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   642: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   645: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   648: athrow
    //   649: aload_0
    //   650: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   653: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   656: astore_3
    //   657: aload_3
    //   658: ifnonnull +52 -> 710
    //   661: aload_0
    //   662: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   665: ifnull +27 -> 692
    //   668: aload_0
    //   669: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   672: aload_0
    //   673: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   676: ldc_w 2183
    //   679: sipush 146
    //   682: bipush 65
    //   684: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   687: invokeinterface 223 3 0
    //   692: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   695: dup
    //   696: ldc_w 2185
    //   699: iconst_3
    //   700: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   703: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   706: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   709: athrow
    //   710: aload_0
    //   711: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   714: sipush 1051
    //   717: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   720: astore 4
    //   722: aload 4
    //   724: ifnonnull -214 -> 510
    //   727: aload_0
    //   728: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   731: ifnull +44 -> 775
    //   734: aload_0
    //   735: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   738: aload_0
    //   739: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   742: new 143	java/lang/StringBuilder
    //   745: dup
    //   746: aload_2
    //   747: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   750: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   753: ldc_w 2187
    //   756: sipush 158
    //   759: bipush 55
    //   761: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   764: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   767: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   770: invokeinterface 223 3 0
    //   775: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   778: dup
    //   779: new 143	java/lang/StringBuilder
    //   782: dup
    //   783: aload_2
    //   784: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   787: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   790: ldc_w 2189
    //   793: bipush 116
    //   795: bipush 15
    //   797: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   800: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   803: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   806: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   809: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   812: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   322	507	46	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   534	571	128	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public cn.com.fmsh.tsm.business.bean.UserInfo queryUserInfoVer2(String paramString)
    throws BusinessException
  {
    // Byte code:
    //   0: sipush 312
    //   3: bipush 35
    //   5: ldc_w 2192
    //   8: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   11: astore_2
    //   12: aload_1
    //   13: ifnull +13 -> 26
    //   16: ldc_w 1532
    //   19: aload_1
    //   20: invokevirtual 1534	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   23: ifeq +269 -> 292
    //   26: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   29: dup
    //   30: ldc_w 2194
    //   33: sipush 328
    //   36: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   39: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   42: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   45: athrow
    //   46: astore 20
    //   48: aload_0
    //   49: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   52: ifnull +43 -> 95
    //   55: aload_0
    //   56: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   59: aload_0
    //   60: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   63: new 143	java/lang/StringBuilder
    //   66: dup
    //   67: ldc_w 2196
    //   70: sipush 298
    //   73: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   76: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   79: aload 20
    //   81: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   84: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   90: invokeinterface 223 3 0
    //   95: aload_0
    //   96: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   99: ldc_w 2198
    //   102: iconst_1
    //   103: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   106: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   109: iconst_0
    //   110: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   113: aload_0
    //   114: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   117: iconst_0
    //   118: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   121: aload 12
    //   123: areturn
    //   124: aload_0
    //   125: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   128: sipush 1052
    //   131: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   134: astore 6
    //   136: aload 6
    //   138: ifnonnull +229 -> 367
    //   141: aload_0
    //   142: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   145: ifnull +42 -> 187
    //   148: aload_0
    //   149: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   152: aload_0
    //   153: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   156: new 143	java/lang/StringBuilder
    //   159: dup
    //   160: aload_2
    //   161: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   164: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   167: sipush 272
    //   170: ldc_w 2200
    //   173: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   176: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: invokeinterface 223 3 0
    //   187: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   190: dup
    //   191: new 143	java/lang/StringBuilder
    //   194: dup
    //   195: aload_2
    //   196: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   199: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   202: iconst_1
    //   203: ldc_w 2202
    //   206: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   209: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   215: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   218: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   221: athrow
    //   222: aload_0
    //   223: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   226: invokevirtual 865	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getConfigration	()Lcn/com/fmsh/tsm/business/core/Configration;
    //   229: astore_3
    //   230: aload_3
    //   231: ifnull +679 -> 910
    //   234: aload_3
    //   235: invokevirtual 1609	cn/com/fmsh/tsm/business/core/Configration:getCompanyCode	()Ljava/lang/String;
    //   238: astore 4
    //   240: aload 4
    //   242: ifnonnull +606 -> 848
    //   245: aload_0
    //   246: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   249: ifnull +25 -> 274
    //   252: aload_0
    //   253: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   256: aload_0
    //   257: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   260: ldc_w 2204
    //   263: iconst_1
    //   264: bipush 71
    //   266: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   269: invokeinterface 223 3 0
    //   274: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   277: dup
    //   278: ldc_w 2206
    //   281: iconst_3
    //   282: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   285: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   288: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   291: athrow
    //   292: aload_0
    //   293: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   296: ifnonnull -74 -> 222
    //   299: aload_0
    //   300: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   303: ifnonnull +13 -> 316
    //   306: aload_0
    //   307: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   310: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   313: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   316: aload_0
    //   317: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   320: ifnull +27 -> 347
    //   323: aload_0
    //   324: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   327: aload_0
    //   328: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   331: ldc_w 2208
    //   334: sipush 280
    //   337: bipush 106
    //   339: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   342: invokeinterface 223 3 0
    //   347: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   350: dup
    //   351: iconst_5
    //   352: bipush 31
    //   354: ldc_w 2210
    //   357: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   360: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   363: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   366: athrow
    //   367: aload_0
    //   368: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   371: aload_2
    //   372: aload 6
    //   374: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   377: aconst_null
    //   378: astore 7
    //   380: aload 5
    //   382: sipush 1052
    //   385: invokeinterface 59 2 0
    //   390: astore 8
    //   392: aload 5
    //   394: iconst_2
    //   395: invokeinterface 63 2 0
    //   400: astore 21
    //   402: aload 21
    //   404: aload_1
    //   405: invokeinterface 84 2 0
    //   410: pop
    //   411: aload 8
    //   413: aload 21
    //   415: invokeinterface 75 2 0
    //   420: pop
    //   421: aload 5
    //   423: bipush 137
    //   425: invokeinterface 63 2 0
    //   430: astore 24
    //   432: aload 24
    //   434: aload 4
    //   436: invokeinterface 84 2 0
    //   441: pop
    //   442: aload 8
    //   444: aload 24
    //   446: invokeinterface 75 2 0
    //   451: pop
    //   452: aload 8
    //   454: invokeinterface 117 1 0
    //   459: astore 27
    //   461: aload 27
    //   463: astore 7
    //   465: aload_0
    //   466: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   469: aload 7
    //   471: aload_2
    //   472: iconst_0
    //   473: aload 6
    //   475: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   478: astore 10
    //   480: iconst_2
    //   481: newarray byte
    //   483: astore 11
    //   485: aload 10
    //   487: iconst_0
    //   488: aload 11
    //   490: iconst_0
    //   491: aload 11
    //   493: arraylength
    //   494: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   497: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   500: aload 11
    //   502: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   505: ifne +75 -> 580
    //   508: aload_0
    //   509: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   512: ifnull +43 -> 555
    //   515: aload_0
    //   516: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   519: aload_0
    //   520: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   523: new 143	java/lang/StringBuilder
    //   526: dup
    //   527: iconst_5
    //   528: bipush 37
    //   530: ldc_w 2212
    //   533: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   536: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   539: aload 10
    //   541: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   544: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   547: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   550: invokeinterface 223 3 0
    //   555: aload_0
    //   556: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   559: ldc_w 2214
    //   562: iconst_3
    //   563: bipush 43
    //   565: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   568: aload 11
    //   570: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   573: invokestatic 180	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:instance	(Ljava/lang/String;)Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   576: iconst_0
    //   577: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   580: new 1695	cn/com/fmsh/tsm/business/bean/UserInfo
    //   583: dup
    //   584: invokespecial 2159	cn/com/fmsh/tsm/business/bean/UserInfo:<init>	()V
    //   587: astore 12
    //   589: aload 5
    //   591: sipush 1051
    //   594: aload 10
    //   596: iconst_2
    //   597: aload 10
    //   599: arraylength
    //   600: invokestatic 188	java/util/Arrays:copyOfRange	([BII)[B
    //   603: invokeinterface 191 3 0
    //   608: astore 13
    //   610: aload 13
    //   612: iconst_1
    //   613: invokeinterface 195 2 0
    //   618: astore 14
    //   620: aload 14
    //   622: ifnull +15 -> 637
    //   625: aload 12
    //   627: aload 14
    //   629: invokeinterface 774 1 0
    //   634: invokevirtual 2162	cn/com/fmsh/tsm/business/bean/UserInfo:setUserType	(I)V
    //   637: aload 13
    //   639: iconst_4
    //   640: invokeinterface 195 2 0
    //   645: astore 15
    //   647: aload 15
    //   649: ifnull +15 -> 664
    //   652: aload 12
    //   654: aload 15
    //   656: invokeinterface 780 1 0
    //   661: invokevirtual 2165	cn/com/fmsh/tsm/business/bean/UserInfo:setMail	(Ljava/lang/String;)V
    //   664: aload 13
    //   666: iconst_5
    //   667: invokeinterface 195 2 0
    //   672: astore 16
    //   674: aload 16
    //   676: ifnull +15 -> 691
    //   679: aload 12
    //   681: aload 16
    //   683: invokeinterface 780 1 0
    //   688: invokevirtual 2168	cn/com/fmsh/tsm/business/bean/UserInfo:setPhone	(Ljava/lang/String;)V
    //   691: aload 13
    //   693: bipush 6
    //   695: invokeinterface 195 2 0
    //   700: astore 17
    //   702: aload 17
    //   704: ifnull +15 -> 719
    //   707: aload 12
    //   709: aload 17
    //   711: invokeinterface 780 1 0
    //   716: invokevirtual 2171	cn/com/fmsh/tsm/business/bean/UserInfo:setRealName	(Ljava/lang/String;)V
    //   719: aload 13
    //   721: bipush 7
    //   723: invokeinterface 195 2 0
    //   728: astore 18
    //   730: aload 18
    //   732: ifnull +15 -> 747
    //   735: aload 12
    //   737: aload 18
    //   739: invokeinterface 774 1 0
    //   744: invokevirtual 2174	cn/com/fmsh/tsm/business/bean/UserInfo:setCertType	(I)V
    //   747: aload 13
    //   749: bipush 8
    //   751: invokeinterface 195 2 0
    //   756: astore 19
    //   758: aload 19
    //   760: ifnull -647 -> 113
    //   763: aload 12
    //   765: aload 19
    //   767: invokeinterface 780 1 0
    //   772: invokevirtual 2177	cn/com/fmsh/tsm/business/bean/UserInfo:setCertNo	(Ljava/lang/String;)V
    //   775: goto -662 -> 113
    //   778: astore 9
    //   780: aload_0
    //   781: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   784: ifnull +43 -> 827
    //   787: aload_0
    //   788: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   791: aload_0
    //   792: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   795: new 143	java/lang/StringBuilder
    //   798: dup
    //   799: sipush 136
    //   802: ldc_w 2216
    //   805: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   808: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   811: aload 9
    //   813: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   816: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   819: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   822: invokeinterface 223 3 0
    //   827: aload_0
    //   828: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   831: ldc_w 2218
    //   834: iconst_5
    //   835: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   838: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   841: iconst_0
    //   842: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   845: goto -380 -> 465
    //   848: aload_0
    //   849: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   852: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   855: astore 5
    //   857: aload 5
    //   859: ifnonnull -735 -> 124
    //   862: aload_0
    //   863: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   866: ifnull +24 -> 890
    //   869: aload_0
    //   870: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   873: aload_0
    //   874: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   877: iconst_3
    //   878: iconst_5
    //   879: ldc_w 2220
    //   882: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   885: invokeinterface 223 3 0
    //   890: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   893: dup
    //   894: ldc_w 2222
    //   897: iconst_2
    //   898: bipush 85
    //   900: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   903: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   906: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   909: athrow
    //   910: aload_0
    //   911: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   914: ifnull +31 -> 945
    //   917: aload_0
    //   918: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   921: aload_0
    //   922: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   925: iconst_2
    //   926: bipush 42
    //   928: ldc_w 2224
    //   931: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   934: invokeinterface 223 3 0
    //   939: aconst_null
    //   940: astore 4
    //   942: goto -702 -> 240
    //   945: aconst_null
    //   946: astore 4
    //   948: goto -708 -> 240
    //
    // Exception table:
    //   from	to	target	type
    //   589	775	46	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   392	461	778	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public cn.com.fmsh.tsm.business.bean.VersionInfo queryVersion()
    throws BusinessException
  {
    // Byte code:
    //   0: ldc_w 2228
    //   3: iconst_5
    //   4: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   7: astore_1
    //   8: aload_0
    //   9: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   12: ifnonnull +391 -> 403
    //   15: aload_0
    //   16: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   19: ifnonnull +13 -> 32
    //   22: aload_0
    //   23: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   26: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   29: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   32: aload_0
    //   33: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   36: ifnull +25 -> 61
    //   39: aload_0
    //   40: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   43: aload_0
    //   44: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   47: sipush 220
    //   50: ldc_w 2230
    //   53: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   56: invokeinterface 223 3 0
    //   61: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   64: dup
    //   65: iconst_4
    //   66: bipush 68
    //   68: ldc_w 2232
    //   71: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   74: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   77: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   80: athrow
    //   81: astore 14
    //   83: aload_0
    //   84: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   87: ifnull +45 -> 132
    //   90: aload_0
    //   91: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   94: aload_0
    //   95: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   98: new 143	java/lang/StringBuilder
    //   101: dup
    //   102: ldc_w 2234
    //   105: sipush 316
    //   108: bipush 100
    //   110: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   113: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   116: aload 7
    //   118: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   121: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: invokeinterface 223 3 0
    //   132: aload_0
    //   133: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   136: iconst_4
    //   137: ldc_w 2236
    //   140: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   143: getstatic 302	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_command_data_invaild	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   146: iconst_0
    //   147: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   150: aload_0
    //   151: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   154: iconst_0
    //   155: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   158: aload 9
    //   160: areturn
    //   161: astore 16
    //   163: aload_0
    //   164: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   167: ifnull +43 -> 210
    //   170: aload_0
    //   171: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   174: aload_0
    //   175: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   178: new 143	java/lang/StringBuilder
    //   181: dup
    //   182: iconst_5
    //   183: bipush 86
    //   185: ldc_w 2238
    //   188: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   191: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   194: aload 16
    //   196: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   199: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   205: invokeinterface 223 3 0
    //   210: aload_0
    //   211: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   214: ldc_w 2240
    //   217: sipush 150
    //   220: bipush 64
    //   222: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   225: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   228: iconst_0
    //   229: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   232: aload_0
    //   233: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   236: aload 4
    //   238: aload_1
    //   239: iconst_0
    //   240: aload_3
    //   241: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   244: astore 7
    //   246: iconst_2
    //   247: newarray byte
    //   249: astore 8
    //   251: aload 7
    //   253: iconst_0
    //   254: aload 8
    //   256: iconst_0
    //   257: aload 8
    //   259: arraylength
    //   260: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   263: new 2242	cn/com/fmsh/tsm/business/bean/VersionInfo
    //   266: dup
    //   267: invokespecial 2243	cn/com/fmsh/tsm/business/bean/VersionInfo:<init>	()V
    //   270: astore 9
    //   272: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   275: aload 8
    //   277: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   280: ifne +73 -> 353
    //   283: aload_0
    //   284: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   287: ifnull +41 -> 328
    //   290: aload_0
    //   291: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   294: aload_0
    //   295: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   298: new 143	java/lang/StringBuilder
    //   301: dup
    //   302: ldc_w 2245
    //   305: iconst_2
    //   306: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   309: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   312: aload 7
    //   314: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   317: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: invokeinterface 223 3 0
    //   328: aload_0
    //   329: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   332: ldc_w 2247
    //   335: iconst_5
    //   336: bipush 44
    //   338: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   341: aload 8
    //   343: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   346: invokestatic 180	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:instance	(Ljava/lang/String;)Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   349: iconst_0
    //   350: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   353: aload_2
    //   354: sipush 1211
    //   357: aload 7
    //   359: iconst_2
    //   360: aload 7
    //   362: arraylength
    //   363: invokestatic 188	java/util/Arrays:copyOfRange	([BII)[B
    //   366: invokeinterface 191 3 0
    //   371: bipush 144
    //   373: invokeinterface 195 2 0
    //   378: invokeinterface 336 1 0
    //   383: astore 10
    //   385: aload 10
    //   387: arraylength
    //   388: istore 11
    //   390: iconst_0
    //   391: istore 12
    //   393: iload 12
    //   395: iload 11
    //   397: if_icmplt +243 -> 640
    //   400: goto -250 -> 150
    //   403: aload_0
    //   404: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   407: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   410: astore_2
    //   411: aload_2
    //   412: ifnonnull +50 -> 462
    //   415: aload_0
    //   416: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   419: ifnull +25 -> 444
    //   422: aload_0
    //   423: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   426: aload_0
    //   427: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   430: iconst_5
    //   431: bipush 97
    //   433: ldc_w 2249
    //   436: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   439: invokeinterface 223 3 0
    //   444: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   447: dup
    //   448: iconst_5
    //   449: ldc_w 2251
    //   452: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   455: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   458: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   461: athrow
    //   462: aload_0
    //   463: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   466: sipush 1211
    //   469: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   472: astore_3
    //   473: aload_3
    //   474: ifnonnull +86 -> 560
    //   477: aload_0
    //   478: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   481: ifnull +42 -> 523
    //   484: aload_0
    //   485: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   488: aload_0
    //   489: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   492: new 143	java/lang/StringBuilder
    //   495: dup
    //   496: aload_1
    //   497: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   500: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   503: ldc_w 2253
    //   506: iconst_4
    //   507: bipush 119
    //   509: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   512: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   515: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   518: invokeinterface 223 3 0
    //   523: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   526: dup
    //   527: new 143	java/lang/StringBuilder
    //   530: dup
    //   531: aload_1
    //   532: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   535: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   538: iconst_1
    //   539: bipush 48
    //   541: ldc_w 2255
    //   544: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   547: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   550: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   553: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   556: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   559: athrow
    //   560: aload_0
    //   561: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   564: aload_1
    //   565: aload_3
    //   566: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   569: aconst_null
    //   570: astore 4
    //   572: aload_2
    //   573: sipush 1211
    //   576: invokeinterface 59 2 0
    //   581: astore 5
    //   583: aload 5
    //   585: invokeinterface 117 1 0
    //   590: astore 6
    //   592: aload 6
    //   594: astore 4
    //   596: goto -364 -> 232
    //   599: aload 13
    //   601: invokeinterface 809 1 0
    //   606: lookupswitch	default:+109->715, 28:+49->655, 44:+88->694, 45:+64->670
    //   641: lconst_1
    //   642: iload 12
    //   644: aaload
    //   645: astore 13
    //   647: aload 13
    //   649: ifnonnull -50 -> 599
    //   652: goto +63 -> 715
    //   655: aload 9
    //   657: aload 13
    //   659: invokeinterface 780 1 0
    //   664: invokevirtual 2258	cn/com/fmsh/tsm/business/bean/VersionInfo:setUrl	(Ljava/lang/String;)V
    //   667: goto +48 -> 715
    //   670: aload 13
    //   672: invokeinterface 774 1 0
    //   677: iconst_1
    //   678: if_icmpne +31 -> 709
    //   681: iconst_1
    //   682: istore 15
    //   684: aload 9
    //   686: iload 15
    //   688: invokevirtual 2261	cn/com/fmsh/tsm/business/bean/VersionInfo:setUpdate	(Z)V
    //   691: goto +24 -> 715
    //   694: aload 9
    //   696: aload 13
    //   698: invokeinterface 780 1 0
    //   703: invokevirtual 2264	cn/com/fmsh/tsm/business/bean/VersionInfo:setViersion	(Ljava/lang/String;)V
    //   706: goto +9 -> 715
    //   709: iconst_0
    //   710: istore 15
    //   712: goto -28 -> 684
    //   715: iinc 12 1
    //   718: goto -325 -> 393
    //
    // Exception table:
    //   from	to	target	type
    //   353	390	81	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   599	706	81	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   583	592	161	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public int register(cn.com.fmsh.tsm.business.bean.UserInfo paramUserInfo)
    throws BusinessException
  {
    // Byte code:
    //   0: iconst_3
    //   1: bipush 7
    //   3: ldc_w 2267
    //   6: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   9: astore_2
    //   10: aload_0
    //   11: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   14: ifnonnull +13 -> 27
    //   17: aload_0
    //   18: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   21: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   24: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   27: aload_0
    //   28: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   31: ifnull +23 -> 54
    //   34: aload_0
    //   35: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   38: aload_0
    //   39: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   42: iconst_2
    //   43: ldc_w 2269
    //   46: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   49: invokeinterface 914 3 0
    //   54: aload_0
    //   55: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   58: ifnonnull +233 -> 291
    //   61: aload_0
    //   62: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   65: ifnull +26 -> 91
    //   68: aload_0
    //   69: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   72: aload_0
    //   73: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   76: bipush 100
    //   78: bipush 10
    //   80: ldc_w 2271
    //   83: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   86: invokeinterface 223 3 0
    //   91: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   94: dup
    //   95: iconst_2
    //   96: ldc_w 2273
    //   99: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   102: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   105: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   108: athrow
    //   109: astore 20
    //   111: aload_0
    //   112: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   115: ifnull +43 -> 158
    //   118: aload_0
    //   119: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   122: aload_0
    //   123: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   126: new 143	java/lang/StringBuilder
    //   129: dup
    //   130: iconst_2
    //   131: bipush 69
    //   133: ldc_w 2275
    //   136: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   139: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   142: aload 20
    //   144: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   147: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokeinterface 223 3 0
    //   158: aload_0
    //   159: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   162: ldc_w 2277
    //   165: sipush 138
    //   168: bipush 52
    //   170: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   173: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   176: iconst_0
    //   177: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   180: aload_0
    //   181: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   184: aload 5
    //   186: iconst_5
    //   187: ldc_w 2279
    //   190: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   193: iconst_0
    //   194: aload 4
    //   196: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   199: astore 17
    //   201: aload 17
    //   203: iconst_2
    //   204: invokestatic 278	java/util/Arrays:copyOf	([BI)[B
    //   207: astore 18
    //   209: aload_0
    //   210: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   213: iconst_0
    //   214: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   217: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   220: aload 18
    //   222: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   225: ifne +60 -> 285
    //   228: aload_0
    //   229: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   232: ifnull +43 -> 275
    //   235: aload_0
    //   236: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   239: aload_0
    //   240: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   243: new 143	java/lang/StringBuilder
    //   246: dup
    //   247: iconst_2
    //   248: bipush 17
    //   250: ldc_w 2281
    //   253: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   256: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   259: aload 17
    //   261: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   264: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   270: invokeinterface 172 3 0
    //   275: aload 18
    //   277: invokestatic 962	cn/com/fmsh/util/FM_CN:bcdBytesToInt	([B)I
    //   280: istore 19
    //   282: iload 19
    //   284: ireturn
    //   285: iconst_0
    //   286: istore 19
    //   288: goto -6 -> 282
    //   291: aload_0
    //   292: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   295: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   298: astore_3
    //   299: aload_3
    //   300: ifnonnull +53 -> 353
    //   303: aload_0
    //   304: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   307: ifnull +25 -> 332
    //   310: aload_0
    //   311: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   314: aload_0
    //   315: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   318: ldc_w 2283
    //   321: sipush 130
    //   324: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   327: invokeinterface 223 3 0
    //   332: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   335: dup
    //   336: bipush 122
    //   338: bipush 80
    //   340: ldc_w 2285
    //   343: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   346: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   349: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   352: athrow
    //   353: aload_0
    //   354: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   357: sipush 1001
    //   360: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   363: astore 4
    //   365: aload 4
    //   367: ifnonnull +84 -> 451
    //   370: aload_0
    //   371: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   374: ifnull +42 -> 416
    //   377: aload_0
    //   378: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   381: aload_0
    //   382: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   385: new 143	java/lang/StringBuilder
    //   388: dup
    //   389: aload_2
    //   390: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   393: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   396: ldc_w 2287
    //   399: sipush 220
    //   402: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   405: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   411: invokeinterface 223 3 0
    //   416: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   419: dup
    //   420: new 143	java/lang/StringBuilder
    //   423: dup
    //   424: aload_2
    //   425: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   428: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   431: iconst_1
    //   432: ldc_w 1333
    //   435: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   438: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   444: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   447: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   450: athrow
    //   451: aload_0
    //   452: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   455: aload_2
    //   456: aload 4
    //   458: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   461: aconst_null
    //   462: astore 5
    //   464: aload_3
    //   465: sipush 1001
    //   468: invokeinterface 59 2 0
    //   473: astore 6
    //   475: aload_3
    //   476: iconst_1
    //   477: invokeinterface 63 2 0
    //   482: astore 7
    //   484: aload 7
    //   486: aload_1
    //   487: invokevirtual 1698	cn/com/fmsh/tsm/business/bean/UserInfo:getUserType	()I
    //   490: invokeinterface 69 2 0
    //   495: pop
    //   496: aload 6
    //   498: aload 7
    //   500: invokeinterface 75 2 0
    //   505: pop
    //   506: aload_3
    //   507: iconst_2
    //   508: invokeinterface 63 2 0
    //   513: astore 10
    //   515: aload 10
    //   517: aload_1
    //   518: invokevirtual 1701	cn/com/fmsh/tsm/business/bean/UserInfo:getUserName	()Ljava/lang/String;
    //   521: invokeinterface 84 2 0
    //   526: pop
    //   527: aload 6
    //   529: aload 10
    //   531: invokeinterface 75 2 0
    //   536: pop
    //   537: aload_3
    //   538: iconst_3
    //   539: invokeinterface 63 2 0
    //   544: astore 13
    //   546: aload 13
    //   548: aload_1
    //   549: invokevirtual 1704	cn/com/fmsh/tsm/business/bean/UserInfo:getPassword	()Ljava/lang/String;
    //   552: invokeinterface 84 2 0
    //   557: pop
    //   558: aload 6
    //   560: aload 13
    //   562: invokeinterface 75 2 0
    //   567: pop
    //   568: aload_1
    //   569: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   572: ifnull +45 -> 617
    //   575: aload_1
    //   576: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   579: invokevirtual 1641	java/lang/String:length	()I
    //   582: iconst_1
    //   583: if_icmple +34 -> 617
    //   586: aload_3
    //   587: iconst_4
    //   588: invokeinterface 63 2 0
    //   593: astore 33
    //   595: aload 33
    //   597: aload_1
    //   598: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   601: invokeinterface 84 2 0
    //   606: pop
    //   607: aload 6
    //   609: aload 33
    //   611: invokeinterface 75 2 0
    //   616: pop
    //   617: aload_1
    //   618: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   621: ifnull +45 -> 666
    //   624: aload_1
    //   625: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   628: invokevirtual 1641	java/lang/String:length	()I
    //   631: iconst_1
    //   632: if_icmple +34 -> 666
    //   635: aload_3
    //   636: iconst_5
    //   637: invokeinterface 63 2 0
    //   642: astore 30
    //   644: aload 30
    //   646: aload_1
    //   647: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   650: invokeinterface 84 2 0
    //   655: pop
    //   656: aload 6
    //   658: aload 30
    //   660: invokeinterface 75 2 0
    //   665: pop
    //   666: aload_1
    //   667: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   670: ifnull +46 -> 716
    //   673: aload_1
    //   674: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   677: invokevirtual 1641	java/lang/String:length	()I
    //   680: iconst_1
    //   681: if_icmple +35 -> 716
    //   684: aload_3
    //   685: bipush 6
    //   687: invokeinterface 63 2 0
    //   692: astore 27
    //   694: aload 27
    //   696: aload_1
    //   697: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   700: invokeinterface 84 2 0
    //   705: pop
    //   706: aload 6
    //   708: aload 27
    //   710: invokeinterface 75 2 0
    //   715: pop
    //   716: aload_1
    //   717: invokevirtual 1716	cn/com/fmsh/tsm/business/bean/UserInfo:getCertType	()I
    //   720: bipush 255
    //   722: if_icmpeq +35 -> 757
    //   725: aload_3
    //   726: bipush 7
    //   728: invokeinterface 63 2 0
    //   733: astore 24
    //   735: aload 24
    //   737: aload_1
    //   738: invokevirtual 1716	cn/com/fmsh/tsm/business/bean/UserInfo:getCertType	()I
    //   741: invokeinterface 69 2 0
    //   746: pop
    //   747: aload 6
    //   749: aload 24
    //   751: invokeinterface 75 2 0
    //   756: pop
    //   757: aload_1
    //   758: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   761: ifnull +46 -> 807
    //   764: aload_1
    //   765: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   768: invokevirtual 1641	java/lang/String:length	()I
    //   771: iconst_1
    //   772: if_icmple +35 -> 807
    //   775: aload_3
    //   776: bipush 8
    //   778: invokeinterface 63 2 0
    //   783: astore 21
    //   785: aload 21
    //   787: aload_1
    //   788: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   791: invokeinterface 84 2 0
    //   796: pop
    //   797: aload 6
    //   799: aload 21
    //   801: invokeinterface 75 2 0
    //   806: pop
    //   807: aload 6
    //   809: invokeinterface 117 1 0
    //   814: astore 16
    //   816: aload 16
    //   818: astore 5
    //   820: goto -640 -> 180
    //
    // Exception table:
    //   from	to	target	type
    //   475	816	109	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public int registerVer2(cn.com.fmsh.tsm.business.bean.UserInfo paramUserInfo)
    throws BusinessException
  {
    // Byte code:
    //   0: ldc_w 2290
    //   3: sipush 248
    //   6: bipush 42
    //   8: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   11: astore_2
    //   12: aload_0
    //   13: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   16: ifnonnull +13 -> 29
    //   19: aload_0
    //   20: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   23: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   26: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   29: aload_0
    //   30: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   33: ifnull +25 -> 58
    //   36: aload_0
    //   37: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   40: aload_0
    //   41: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   44: ldc_w 2292
    //   47: iconst_4
    //   48: bipush 18
    //   50: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   53: invokeinterface 914 3 0
    //   58: aload_1
    //   59: ifnonnull +219 -> 278
    //   62: aload_0
    //   63: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   66: ifnull +23 -> 89
    //   69: aload_0
    //   70: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   73: aload_0
    //   74: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   77: iconst_2
    //   78: ldc_w 2294
    //   81: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   84: invokeinterface 223 3 0
    //   89: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   92: dup
    //   93: iconst_2
    //   94: ldc_w 2296
    //   97: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   100: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   103: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   106: athrow
    //   107: astore 25
    //   109: aload_0
    //   110: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   113: ifnull +41 -> 154
    //   116: aload_0
    //   117: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   120: aload_0
    //   121: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   124: new 143	java/lang/StringBuilder
    //   127: dup
    //   128: iconst_5
    //   129: ldc_w 2298
    //   132: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   135: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   138: aload 25
    //   140: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   143: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   149: invokeinterface 223 3 0
    //   154: aload_0
    //   155: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   158: bipush 74
    //   160: ldc_w 2300
    //   163: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   166: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   169: iconst_0
    //   170: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   173: aload_0
    //   174: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   177: aload 7
    //   179: iconst_2
    //   180: ldc_w 2302
    //   183: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   186: iconst_0
    //   187: aload 6
    //   189: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   192: astore 22
    //   194: aload 22
    //   196: iconst_2
    //   197: invokestatic 278	java/util/Arrays:copyOf	([BI)[B
    //   200: astore 23
    //   202: aload_0
    //   203: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   206: iconst_0
    //   207: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   210: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   213: aload 23
    //   215: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   218: ifne +540 -> 758
    //   221: aload_0
    //   222: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   225: ifnull +43 -> 268
    //   228: aload_0
    //   229: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   232: aload_0
    //   233: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   236: new 143	java/lang/StringBuilder
    //   239: dup
    //   240: ldc_w 2304
    //   243: iconst_3
    //   244: bipush 97
    //   246: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   249: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   252: aload 22
    //   254: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   257: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   263: invokeinterface 172 3 0
    //   268: aload 23
    //   270: invokestatic 962	cn/com/fmsh/util/FM_CN:bcdBytesToInt	([B)I
    //   273: istore 24
    //   275: iload 24
    //   277: ireturn
    //   278: aload_1
    //   279: invokevirtual 1701	cn/com/fmsh/tsm/business/bean/UserInfo:getUserName	()Ljava/lang/String;
    //   282: ifnull +14 -> 296
    //   285: aload_1
    //   286: invokevirtual 1701	cn/com/fmsh/tsm/business/bean/UserInfo:getUserName	()Ljava/lang/String;
    //   289: invokevirtual 1641	java/lang/String:length	()I
    //   292: iconst_1
    //   293: if_icmpge +50 -> 343
    //   296: aload_0
    //   297: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   300: ifnull +25 -> 325
    //   303: aload_0
    //   304: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   307: aload_0
    //   308: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   311: ldc_w 2306
    //   314: iconst_2
    //   315: bipush 19
    //   317: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   320: invokeinterface 223 3 0
    //   325: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   328: dup
    //   329: ldc_w 2308
    //   332: iconst_3
    //   333: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   336: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   339: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   342: athrow
    //   343: aload_1
    //   344: invokevirtual 1704	cn/com/fmsh/tsm/business/bean/UserInfo:getPassword	()Ljava/lang/String;
    //   347: ifnull +14 -> 361
    //   350: aload_1
    //   351: invokevirtual 1704	cn/com/fmsh/tsm/business/bean/UserInfo:getPassword	()Ljava/lang/String;
    //   354: invokevirtual 1641	java/lang/String:length	()I
    //   357: iconst_1
    //   358: if_icmpge +217 -> 575
    //   361: aload_0
    //   362: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   365: ifnull +25 -> 390
    //   368: aload_0
    //   369: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   372: aload_0
    //   373: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   376: ldc_w 2310
    //   379: sipush 262
    //   382: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   385: invokeinterface 223 3 0
    //   390: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   393: dup
    //   394: ldc_w 2312
    //   397: sipush 154
    //   400: bipush 124
    //   402: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   405: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   408: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   411: athrow
    //   412: aload_0
    //   413: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   416: sipush 1002
    //   419: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   422: astore 6
    //   424: aload 6
    //   426: ifnonnull +338 -> 764
    //   429: aload_0
    //   430: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   433: ifnull +42 -> 475
    //   436: aload_0
    //   437: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   440: aload_0
    //   441: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   444: new 143	java/lang/StringBuilder
    //   447: dup
    //   448: aload_2
    //   449: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   452: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   455: sipush 246
    //   458: ldc_w 2314
    //   461: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   464: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   467: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   470: invokeinterface 223 3 0
    //   475: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   478: dup
    //   479: new 143	java/lang/StringBuilder
    //   482: dup
    //   483: aload_2
    //   484: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   487: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   490: iconst_3
    //   491: bipush 60
    //   493: ldc_w 2316
    //   496: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   499: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   505: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   508: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   511: athrow
    //   512: aload_0
    //   513: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   516: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   519: astore 5
    //   521: aload 5
    //   523: ifnonnull -111 -> 412
    //   526: aload_0
    //   527: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   530: ifnull +25 -> 555
    //   533: aload_0
    //   534: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   537: aload_0
    //   538: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   541: ldc_w 2318
    //   544: iconst_4
    //   545: bipush 40
    //   547: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   550: invokeinterface 223 3 0
    //   555: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   558: dup
    //   559: sipush 176
    //   562: ldc_w 2320
    //   565: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   568: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   571: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   574: athrow
    //   575: aload_0
    //   576: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   579: ifnonnull +158 -> 737
    //   582: aload_0
    //   583: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   586: ifnonnull +13 -> 599
    //   589: aload_0
    //   590: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   593: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   596: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   599: aload_0
    //   600: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   603: ifnull +27 -> 630
    //   606: aload_0
    //   607: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   610: aload_0
    //   611: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   614: ldc_w 2322
    //   617: sipush 252
    //   620: bipush 48
    //   622: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   625: invokeinterface 223 3 0
    //   630: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   633: dup
    //   634: ldc_w 2324
    //   637: sipush 352
    //   640: iconst_2
    //   641: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   644: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   647: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   650: athrow
    //   651: aload_0
    //   652: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   655: ifnull +25 -> 680
    //   658: aload_0
    //   659: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   662: aload_0
    //   663: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   666: iconst_2
    //   667: bipush 84
    //   669: ldc_w 2326
    //   672: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   675: invokeinterface 223 3 0
    //   680: aconst_null
    //   681: astore 4
    //   683: aload 4
    //   685: ifnonnull -173 -> 512
    //   688: aload_0
    //   689: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   692: ifnull +25 -> 717
    //   695: aload_0
    //   696: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   699: aload_0
    //   700: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   703: iconst_5
    //   704: bipush 78
    //   706: ldc_w 2328
    //   709: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   712: invokeinterface 223 3 0
    //   717: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   720: dup
    //   721: sipush 208
    //   724: ldc_w 2330
    //   727: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   730: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   733: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   736: athrow
    //   737: aload_0
    //   738: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   741: invokevirtual 865	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getConfigration	()Lcn/com/fmsh/tsm/business/core/Configration;
    //   744: astore_3
    //   745: aload_3
    //   746: ifnull -95 -> 651
    //   749: aload_3
    //   750: invokevirtual 1609	cn/com/fmsh/tsm/business/core/Configration:getCompanyCode	()Ljava/lang/String;
    //   753: astore 4
    //   755: goto -72 -> 683
    //   758: iconst_0
    //   759: istore 24
    //   761: goto -486 -> 275
    //   764: aload_0
    //   765: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   768: aload_2
    //   769: aload 6
    //   771: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   774: aconst_null
    //   775: astore 7
    //   777: aload 5
    //   779: sipush 1002
    //   782: invokeinterface 59 2 0
    //   787: astore 8
    //   789: aload 5
    //   791: iconst_1
    //   792: invokeinterface 63 2 0
    //   797: astore 9
    //   799: aload 9
    //   801: aload_1
    //   802: invokevirtual 1698	cn/com/fmsh/tsm/business/bean/UserInfo:getUserType	()I
    //   805: invokeinterface 69 2 0
    //   810: pop
    //   811: aload 8
    //   813: aload 9
    //   815: invokeinterface 75 2 0
    //   820: pop
    //   821: aload 5
    //   823: iconst_2
    //   824: invokeinterface 63 2 0
    //   829: astore 12
    //   831: aload 12
    //   833: aload_1
    //   834: invokevirtual 1701	cn/com/fmsh/tsm/business/bean/UserInfo:getUserName	()Ljava/lang/String;
    //   837: invokeinterface 84 2 0
    //   842: pop
    //   843: aload 8
    //   845: aload 12
    //   847: invokeinterface 75 2 0
    //   852: pop
    //   853: aload 5
    //   855: iconst_3
    //   856: invokeinterface 63 2 0
    //   861: astore 15
    //   863: aload 15
    //   865: aload_1
    //   866: invokevirtual 1704	cn/com/fmsh/tsm/business/bean/UserInfo:getPassword	()Ljava/lang/String;
    //   869: invokeinterface 84 2 0
    //   874: pop
    //   875: aload 8
    //   877: aload 15
    //   879: invokeinterface 75 2 0
    //   884: pop
    //   885: aload_1
    //   886: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   889: ifnull +46 -> 935
    //   892: aload_1
    //   893: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   896: invokevirtual 1641	java/lang/String:length	()I
    //   899: iconst_1
    //   900: if_icmple +35 -> 935
    //   903: aload 5
    //   905: iconst_4
    //   906: invokeinterface 63 2 0
    //   911: astore 38
    //   913: aload 38
    //   915: aload_1
    //   916: invokevirtual 1707	cn/com/fmsh/tsm/business/bean/UserInfo:getMail	()Ljava/lang/String;
    //   919: invokeinterface 84 2 0
    //   924: pop
    //   925: aload 8
    //   927: aload 38
    //   929: invokeinterface 75 2 0
    //   934: pop
    //   935: aload_1
    //   936: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   939: ifnull +46 -> 985
    //   942: aload_1
    //   943: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   946: invokevirtual 1641	java/lang/String:length	()I
    //   949: iconst_1
    //   950: if_icmple +35 -> 985
    //   953: aload 5
    //   955: iconst_5
    //   956: invokeinterface 63 2 0
    //   961: astore 35
    //   963: aload 35
    //   965: aload_1
    //   966: invokevirtual 1710	cn/com/fmsh/tsm/business/bean/UserInfo:getPhone	()Ljava/lang/String;
    //   969: invokeinterface 84 2 0
    //   974: pop
    //   975: aload 8
    //   977: aload 35
    //   979: invokeinterface 75 2 0
    //   984: pop
    //   985: aload_1
    //   986: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   989: ifnull +47 -> 1036
    //   992: aload_1
    //   993: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   996: invokevirtual 1641	java/lang/String:length	()I
    //   999: iconst_1
    //   1000: if_icmple +36 -> 1036
    //   1003: aload 5
    //   1005: bipush 6
    //   1007: invokeinterface 63 2 0
    //   1012: astore 32
    //   1014: aload 32
    //   1016: aload_1
    //   1017: invokevirtual 1713	cn/com/fmsh/tsm/business/bean/UserInfo:getRealName	()Ljava/lang/String;
    //   1020: invokeinterface 84 2 0
    //   1025: pop
    //   1026: aload 8
    //   1028: aload 32
    //   1030: invokeinterface 75 2 0
    //   1035: pop
    //   1036: aload_1
    //   1037: invokevirtual 1716	cn/com/fmsh/tsm/business/bean/UserInfo:getCertType	()I
    //   1040: bipush 255
    //   1042: if_icmpeq +36 -> 1078
    //   1045: aload 5
    //   1047: bipush 7
    //   1049: invokeinterface 63 2 0
    //   1054: astore 29
    //   1056: aload 29
    //   1058: aload_1
    //   1059: invokevirtual 1716	cn/com/fmsh/tsm/business/bean/UserInfo:getCertType	()I
    //   1062: invokeinterface 69 2 0
    //   1067: pop
    //   1068: aload 8
    //   1070: aload 29
    //   1072: invokeinterface 75 2 0
    //   1077: pop
    //   1078: aload_1
    //   1079: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   1082: ifnull +47 -> 1129
    //   1085: aload_1
    //   1086: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   1089: invokevirtual 1641	java/lang/String:length	()I
    //   1092: iconst_1
    //   1093: if_icmple +36 -> 1129
    //   1096: aload 5
    //   1098: bipush 8
    //   1100: invokeinterface 63 2 0
    //   1105: astore 26
    //   1107: aload 26
    //   1109: aload_1
    //   1110: invokevirtual 1719	cn/com/fmsh/tsm/business/bean/UserInfo:getCertNo	()Ljava/lang/String;
    //   1113: invokeinterface 84 2 0
    //   1118: pop
    //   1119: aload 8
    //   1121: aload 26
    //   1123: invokeinterface 75 2 0
    //   1128: pop
    //   1129: aload 5
    //   1131: bipush 137
    //   1133: invokeinterface 63 2 0
    //   1138: astore 18
    //   1140: aload 18
    //   1142: aload 4
    //   1144: invokeinterface 84 2 0
    //   1149: pop
    //   1150: aload 8
    //   1152: aload 18
    //   1154: invokeinterface 75 2 0
    //   1159: pop
    //   1160: aload 8
    //   1162: invokeinterface 117 1 0
    //   1167: astore 21
    //   1169: aload 21
    //   1171: astore 7
    //   1173: goto -1000 -> 173
    //
    // Exception table:
    //   from	to	target	type
    //   789	1169	107	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  public boolean remoteRecharge(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws BusinessException
  {
    String str1 = FM_Utils.copyValueOf(2, 48, "卬丗廙电坅嬅");
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Utils.copyValueOf(1, 41, "卭丟廊甯團嬁,%z"));
    String str2;
    if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length < 1))
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_CN.subSequence("旧，伯养盉课匞罌叾丢穽", 3));
      throw new BusinessException(str1 + FM_Int.lastIndexOf(1, "斢ｙ佶儲盜读匏罍厫乧稤"), BusinessException.ErrorMessage.local_business_para_error);
      str2 = this.d.getServer4Business(3011);
      if (str2 == null)
      {
        if (this.b != null)
          this.b.warn(this.c, str1 + FM_Utils.copyValueOf(3, 115, "旸－菣厑奞瑋盄幠叶夨贩"));
        throw new BusinessException(str1 + FM_Exception.getChars(4, 45, "斫＆菠叒夕瑘皏幫厵夣贺"), BusinessException.ErrorMessage.local_app_query_server_fail);
      }
    }
    else
    {
      if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length >= 1))
        break label458;
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Bytes.startsWith("旲）伦兢皌卨庞産広刚叹丵穪", 3, 1));
      throw new BusinessException(str1 + FM_Exception.getChars(98, 36, "断ｓ伣儢盏卮庇畿廔刈厴乽穱"), BusinessException.ErrorMessage.local_business_para_error);
    }
    label458: 
    do
    {
      IMessageHandler localIMessageHandler = this.d.getMessageHandler();
      if (localIMessageHandler != null)
        break;
      if (this.b != null)
        this.b.warn(this.c, str1 + BCCUtil.endsWith("旴＇涜恲夂琉噰乻稰", 3, 9));
      throw new BusinessException(str1 + FM_Bytes.startsWith("施６涁恷夃瑐嘭乮稹", 202, 111), BusinessException.ErrorMessage.local_message_load_config_fail);
      this.d.businessReady(str1, str2);
      return a(paramArrayOfByte1, paramArrayOfByte2, localIMessageHandler, str2);
    }
    while (this.d != null);
    if (this.b != null)
      this.b.warn(this.c, str1 + BCCUtil.endsWith("旵～乛勱夛琈寤豭乡稰", 4, 111));
    throw new BusinessException(str1 + FM_Bytes.startsWith("旳：九効奍瑜噣剁姆匈奾败", 4, 49), BusinessException.ErrorMessage.local_business_init_fail);
  }

  // ERROR //
  public cn.com.fmsh.tsm.business.bean.PasswordPrompt retrievePassword(String paramString)
    throws BusinessException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   4: ifnonnull +13 -> 17
    //   7: aload_0
    //   8: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   11: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   14: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   17: aload_0
    //   18: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   21: ifnull +23 -> 44
    //   24: aload_0
    //   25: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   28: aload_0
    //   29: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   32: iconst_5
    //   33: ldc_w 2361
    //   36: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   39: invokeinterface 914 3 0
    //   44: ldc_w 2363
    //   47: bipush 102
    //   49: bipush 121
    //   51: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   54: astore_2
    //   55: aload_1
    //   56: ifnull +11 -> 67
    //   59: aload_1
    //   60: invokevirtual 1641	java/lang/String:length	()I
    //   63: iconst_1
    //   64: if_icmpge +143 -> 207
    //   67: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   70: dup
    //   71: new 143	java/lang/StringBuilder
    //   74: dup
    //   75: aload_2
    //   76: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   79: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   82: iconst_3
    //   83: bipush 87
    //   85: ldc_w 2365
    //   88: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   91: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: getstatic 851	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_para_error	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   100: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   103: athrow
    //   104: aload_0
    //   105: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   108: ifnull +51 -> 159
    //   111: aload_0
    //   112: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   115: aload_0
    //   116: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   119: new 143	java/lang/StringBuilder
    //   122: dup
    //   123: aload_2
    //   124: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   127: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   130: ldc_w 2367
    //   133: bipush 74
    //   135: bipush 84
    //   137: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   140: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload 11
    //   145: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   148: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: invokeinterface 223 3 0
    //   159: aload_0
    //   160: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   163: new 143	java/lang/StringBuilder
    //   166: dup
    //   167: aload_2
    //   168: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   171: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   174: iconst_4
    //   175: bipush 10
    //   177: ldc_w 2369
    //   180: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   183: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   189: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   192: iconst_0
    //   193: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   196: aload_0
    //   197: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   200: iconst_0
    //   201: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   204: aload 13
    //   206: areturn
    //   207: aload_0
    //   208: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   211: ifnonnull +271 -> 482
    //   214: aload_0
    //   215: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   218: ifnull +44 -> 262
    //   221: aload_0
    //   222: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   225: aload_0
    //   226: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   229: new 143	java/lang/StringBuilder
    //   232: dup
    //   233: aload_2
    //   234: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   237: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   240: sipush 232
    //   243: bipush 7
    //   245: ldc_w 2371
    //   248: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   251: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   257: invokeinterface 223 3 0
    //   262: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   265: dup
    //   266: new 143	java/lang/StringBuilder
    //   269: dup
    //   270: aload_2
    //   271: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   274: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   277: sipush 204
    //   280: ldc_w 2373
    //   283: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   286: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   292: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   295: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   298: athrow
    //   299: astore 17
    //   301: aload_0
    //   302: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   305: ifnull +50 -> 355
    //   308: aload_0
    //   309: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   312: aload_0
    //   313: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   316: new 143	java/lang/StringBuilder
    //   319: dup
    //   320: aload_2
    //   321: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   324: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   327: iconst_2
    //   328: bipush 121
    //   330: ldc_w 2375
    //   333: invokestatic 53	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   336: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: aload 17
    //   341: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   344: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   350: invokeinterface 223 3 0
    //   355: aload_0
    //   356: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   359: new 143	java/lang/StringBuilder
    //   362: dup
    //   363: aload_2
    //   364: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   367: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   370: iconst_5
    //   371: ldc_w 2377
    //   374: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   377: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   383: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   386: iconst_0
    //   387: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   390: aload_0
    //   391: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   394: aload 5
    //   396: aload_2
    //   397: iconst_0
    //   398: aload 4
    //   400: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   403: astore 11
    //   405: iconst_2
    //   406: newarray byte
    //   408: astore 12
    //   410: aload 11
    //   412: iconst_0
    //   413: aload 12
    //   415: iconst_0
    //   416: aload 12
    //   418: arraylength
    //   419: invokestatic 129	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   422: aconst_null
    //   423: astore 13
    //   425: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   428: aload 12
    //   430: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   433: ifeq -329 -> 104
    //   436: aload_3
    //   437: sipush 1041
    //   440: aload 11
    //   442: iconst_2
    //   443: aload 11
    //   445: arraylength
    //   446: invokestatic 188	java/util/Arrays:copyOfRange	([BII)[B
    //   449: invokeinterface 191 3 0
    //   454: bipush 10
    //   456: invokeinterface 195 2 0
    //   461: astore 15
    //   463: aload 15
    //   465: ifnull -269 -> 196
    //   468: aload 15
    //   470: invokestatic 2382	cn/com/fmsh/tsm/business/bean/PasswordPrompt:fromTag	(Lcn/com/fmsh/communication/message/ITag;)Lcn/com/fmsh/tsm/business/bean/PasswordPrompt;
    //   473: astore 16
    //   475: aload 16
    //   477: astore 13
    //   479: goto -283 -> 196
    //   482: aload_0
    //   483: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   486: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   489: astore_3
    //   490: aload_3
    //   491: ifnonnull +208 -> 699
    //   494: aload_0
    //   495: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   498: ifnull +41 -> 539
    //   501: aload_0
    //   502: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   505: aload_0
    //   506: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   509: new 143	java/lang/StringBuilder
    //   512: dup
    //   513: aload_2
    //   514: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   517: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   520: bipush 64
    //   522: ldc_w 2384
    //   525: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   528: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   534: invokeinterface 223 3 0
    //   539: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   542: dup
    //   543: new 143	java/lang/StringBuilder
    //   546: dup
    //   547: aload_2
    //   548: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   551: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   554: ldc_w 2386
    //   557: sipush 154
    //   560: bipush 98
    //   562: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   565: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   568: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   571: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   574: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   577: athrow
    //   578: aload_0
    //   579: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   582: aload_2
    //   583: aload 4
    //   585: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   588: aconst_null
    //   589: astore 5
    //   591: aload_3
    //   592: sipush 1041
    //   595: invokeinterface 59 2 0
    //   600: astore 6
    //   602: aload_3
    //   603: iconst_2
    //   604: invokeinterface 63 2 0
    //   609: astore 7
    //   611: aload 7
    //   613: aload_0
    //   614: getfield 1543	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:a	Ljava/lang/String;
    //   617: invokeinterface 84 2 0
    //   622: pop
    //   623: aload 6
    //   625: aload 7
    //   627: invokeinterface 75 2 0
    //   632: pop
    //   633: aload 6
    //   635: invokeinterface 117 1 0
    //   640: astore 10
    //   642: aload 10
    //   644: astore 5
    //   646: goto -256 -> 390
    //   649: astore 14
    //   651: aload_0
    //   652: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   655: ifnull -459 -> 196
    //   658: aload_0
    //   659: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   662: aload_0
    //   663: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   666: new 143	java/lang/StringBuilder
    //   669: dup
    //   670: iconst_3
    //   671: ldc_w 2388
    //   674: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   677: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   680: aload 14
    //   682: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   685: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   688: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   691: invokeinterface 223 3 0
    //   696: goto -500 -> 196
    //   699: aload_0
    //   700: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   703: sipush 1041
    //   706: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   709: astore 4
    //   711: aload 4
    //   713: ifnonnull -135 -> 578
    //   716: aload_0
    //   717: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   720: ifnull +44 -> 764
    //   723: aload_0
    //   724: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   727: aload_0
    //   728: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   731: new 143	java/lang/StringBuilder
    //   734: dup
    //   735: aload_2
    //   736: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   739: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   742: ldc_w 2390
    //   745: sipush 196
    //   748: bipush 101
    //   750: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   753: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   756: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   759: invokeinterface 223 3 0
    //   764: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   767: dup
    //   768: new 143	java/lang/StringBuilder
    //   771: dup
    //   772: aload_2
    //   773: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   776: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   779: ldc_w 2392
    //   782: iconst_3
    //   783: bipush 94
    //   785: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   788: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   791: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   794: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   797: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   800: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   602	642	299	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   436	475	649	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  // ERROR //
  public int setOrderStatus(byte[] paramArrayOfByte1, cn.com.fmsh.tsm.business.enums.EnumOrderType paramEnumOrderType, byte[] paramArrayOfByte2, EnumOrderStatus paramEnumOrderStatus)
    throws BusinessException
  {
    // Byte code:
    //   0: ldc_w 2396
    //   3: iconst_3
    //   4: bipush 14
    //   6: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   9: astore 5
    //   11: aload_0
    //   12: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   15: ifnonnull +13 -> 28
    //   18: aload_0
    //   19: invokestatic 36	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   22: invokevirtual 40	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   25: putfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   28: aload_0
    //   29: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   32: ifnull +25 -> 57
    //   35: aload_0
    //   36: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   39: aload_0
    //   40: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   43: ldc_w 2398
    //   46: sipush 230
    //   49: invokestatic 290	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   52: invokeinterface 914 3 0
    //   57: aload_0
    //   58: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   61: ifnonnull +50 -> 111
    //   64: aload_0
    //   65: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   68: ifnull +23 -> 91
    //   71: aload_0
    //   72: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   75: aload_0
    //   76: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   79: iconst_5
    //   80: ldc_w 2400
    //   83: invokestatic 244	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   86: invokeinterface 223 3 0
    //   91: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   94: dup
    //   95: iconst_5
    //   96: bipush 90
    //   98: ldc_w 2402
    //   101: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   104: getstatic 861	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_business_init_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   107: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   110: athrow
    //   111: aload_0
    //   112: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   115: invokevirtual 891	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getMessageHandler	()Lcn/com/fmsh/communication/message/IMessageHandler;
    //   118: astore 6
    //   120: aload 6
    //   122: ifnonnull +52 -> 174
    //   125: aload_0
    //   126: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   129: ifnull +25 -> 154
    //   132: aload_0
    //   133: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   136: aload_0
    //   137: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   140: ldc_w 2404
    //   143: iconst_4
    //   144: bipush 46
    //   146: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   149: invokeinterface 223 3 0
    //   154: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   157: dup
    //   158: sipush 194
    //   161: ldc_w 2406
    //   164: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   167: getstatic 898	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_load_config_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   170: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   173: athrow
    //   174: aload_0
    //   175: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   178: sipush 1171
    //   181: invokevirtual 876	cn/com/fmsh/tsm/business/core/CardBusinessBasic:getServer4Business	(I)Ljava/lang/String;
    //   184: astore 7
    //   186: aload 7
    //   188: ifnonnull +260 -> 448
    //   191: aload_0
    //   192: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   195: ifnull +43 -> 238
    //   198: aload_0
    //   199: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   202: aload_0
    //   203: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   206: new 143	java/lang/StringBuilder
    //   209: dup
    //   210: aload 5
    //   212: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   215: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   218: iconst_2
    //   219: bipush 22
    //   221: ldc_w 2408
    //   224: invokestatic 214	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   227: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: invokeinterface 223 3 0
    //   238: new 43	cn/com/fmsh/tsm/business/exception/BusinessException
    //   241: dup
    //   242: new 143	java/lang/StringBuilder
    //   245: dup
    //   246: aload 5
    //   248: invokestatic 149	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   251: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   254: ldc_w 2410
    //   257: iconst_5
    //   258: bipush 64
    //   260: invokestatic 251	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   263: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   269: getstatic 883	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_app_query_server_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   272: invokespecial 854	cn/com/fmsh/tsm/business/exception/BusinessException:<init>	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;)V
    //   275: athrow
    //   276: astore 14
    //   278: aload_0
    //   279: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   282: ifnull +41 -> 323
    //   285: aload_0
    //   286: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   289: aload_0
    //   290: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   293: new 143	java/lang/StringBuilder
    //   296: dup
    //   297: ldc_w 2412
    //   300: iconst_2
    //   301: invokestatic 297	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   304: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   307: aload 14
    //   309: invokestatic 207	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   312: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   318: invokeinterface 223 3 0
    //   323: aload_0
    //   324: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   327: ldc_w 2414
    //   330: iconst_4
    //   331: bipush 100
    //   333: invokestatic 258	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   336: getstatic 218	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:local_message_message_handle_exception	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
    //   339: iconst_0
    //   340: invokevirtual 184	cn/com/fmsh/tsm/business/core/CardBusinessBasic:throwExceptionAndClose	(Ljava/lang/String;Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;Z)V
    //   343: aload_0
    //   344: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   347: aload 8
    //   349: ldc_w 2416
    //   352: iconst_4
    //   353: bipush 95
    //   355: invokestatic 160	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   358: iconst_0
    //   359: aload 7
    //   361: invokevirtual 123	cn/com/fmsh/tsm/business/core/CardBusinessBasic:interaction	([BLjava/lang/String;ZLjava/lang/String;)[B
    //   364: astore 11
    //   366: aload 11
    //   368: iconst_2
    //   369: invokestatic 278	java/util/Arrays:copyOf	([BI)[B
    //   372: astore 12
    //   374: aload_0
    //   375: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   378: iconst_0
    //   379: invokevirtual 561	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessFinish	(Z)V
    //   382: getstatic 135	cn/com/fmsh/tsm/business/constants/Constants$RespCodeonse4Platform:SUCESS	[B
    //   385: aload 12
    //   387: invokestatic 141	java/util/Arrays:equals	([B[B)Z
    //   390: ifne +297 -> 687
    //   393: aload_0
    //   394: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   397: ifnull +41 -> 438
    //   400: aload_0
    //   401: getfield 20	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:b	Lcn/com/fmsh/util/log/FMLog;
    //   404: aload_0
    //   405: getfield 28	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:c	Ljava/lang/String;
    //   408: new 143	java/lang/StringBuilder
    //   411: dup
    //   412: iconst_4
    //   413: ldc_w 2418
    //   416: invokestatic 231	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   419: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   422: aload 11
    //   424: invokestatic 81	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   427: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   433: invokeinterface 172 3 0
    //   438: aload 12
    //   440: invokestatic 962	cn/com/fmsh/util/FM_CN:bcdBytesToInt	([B)I
    //   443: istore 13
    //   445: iload 13
    //   447: ireturn
    //   448: aload_0
    //   449: getfield 30	cn/com/fmsh/tsm/business/core/CardAppTradeImpl:d	Lcn/com/fmsh/tsm/business/core/CardBusinessBasic;
    //   452: aload 5
    //   454: aload 7
    //   456: invokevirtual 903	cn/com/fmsh/tsm/business/core/CardBusinessBasic:businessReady	(Ljava/lang/String;Ljava/lang/String;)V
    //   459: aconst_null
    //   460: astore 8
    //   462: aload 6
    //   464: sipush 1171
    //   467: invokeinterface 59 2 0
    //   472: astore 9
    //   474: aload_1
    //   475: ifnull +38 -> 513
    //   478: aload_1
    //   479: arraylength
    //   480: ifle +33 -> 513
    //   483: aload 6
    //   485: bipush 71
    //   487: invokeinterface 63 2 0
    //   492: astore 23
    //   494: aload 23
    //   496: aload_1
    //   497: invokeinterface 91 2 0
    //   502: pop
    //   503: aload 9
    //   505: aload 23
    //   507: invokeinterface 75 2 0
    //   512: pop
    //   513: aload_2
    //   514: ifnull +157 -> 671
    //   517: aload 6
    //   519: bipush 101
    //   521: invokeinterface 63 2 0
    //   526: astore 15
    //   528: aload 15
    //   530: aload_2
    //   531: invokevirtual 272	cn/com/fmsh/tsm/business/enums/EnumOrderType:getId	()I
    //   534: invokeinterface 69 2 0
    //   539: pop
    //   540: aload 9
    //   542: aload 15
    //   544: invokeinterface 75 2 0
    //   549: pop
    //   550: aload_3
    //   551: ifnull +81 -> 632
    //   554: aload_3
    //   555: arraylength
    //   556: ifle +76 -> 632
    //   559: getstatic 271	cn/com/fmsh/tsm/business/enums/EnumOrderType:MAIN	Lcn/com/fmsh/tsm/business/enums/EnumOrderType;
    //   562: aload_2
    //   563: if_acmpne +14 -> 577
    //   566: aload 6
    //   568: bipush 105
    //   570: invokeinterface 63 2 0
    //   575: astore 15
    //   577: getstatic 402	cn/com/fmsh/tsm/business/enums/EnumOrderType:BUSINESS	Lcn/com/fmsh/tsm/business/enums/EnumOrderType;
    //   580: aload_2
    //   581: if_acmpne +14 -> 595
    //   584: aload 6
    //   586: bipush 17
    //   588: invokeinterface 63 2 0
    //   593: astore 15
    //   595: getstatic 313	cn/com/fmsh/tsm/business/enums/EnumOrderType:PAY	Lcn/com/fmsh/tsm/business/enums/EnumOrderType;
    //   598: aload_2
    //   599: if_acmpne +14 -> 613
    //   602: aload 6
    //   604: bipush 106
    //   606: invokeinterface 63 2 0
    //   611: astore 15
    //   613: aload 15
    //   615: aload_3
    //   616: invokeinterface 91 2 0
    //   621: pop
    //   622: aload 9
    //   624: aload 15
    //   626: invokeinterface 75 2 0
    //   631: pop
    //   632: aload 4
    //   634: ifnull +37 -> 671
    //   637: aload 6
    //   639: bipush 21
    //   641: invokeinterface 63 2 0
    //   646: astore 18
    //   648: aload 18
    //   650: aload 4
    //   652: invokevirtual 382	cn/com/fmsh/tsm/business/enums/EnumOrderStatus:getId	()I
    //   655: invokeinterface 69 2 0
    //   660: pop
    //   661: aload 9
    //   663: aload 18
    //   665: invokeinterface 75 2 0
    //   670: pop
    //   671: aload 9
    //   673: invokeinterface 117 1 0
    //   678: astore 10
    //   680: aload 10
    //   682: astore 8
    //   684: goto -341 -> 343
    //   687: iconst_0
    //   688: istore 13
    //   690: goto -245 -> 445
    //
    // Exception table:
    //   from	to	target	type
    //   478	680	276	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  public int terminalInfoBack(TerminalBackInfo paramTerminalBackInfo)
    throws BusinessException
  {
    String str1 = FM_Bytes.startsWith("绋窵俰恧厒馞", 2, 87);
    if (this.b == null)
      this.b = LogFactory.getInstance().getLog();
    if (this.b != null)
      this.b.info(this.c, FM_Long.concat("绌章俻怪厝駓hr", 172));
    String str2;
    IMessageHandler localIMessageHandler;
    if (paramTerminalBackInfo == null)
    {
      if (this.b != null)
        this.b.warn(this.c, str1 + FM_Utils.copyValueOf(4, 126, "＃伭兮皍叅敵弁帹"));
      throw new BusinessException(str1 + FM_Exception.getChars(330, 86, "ｏ伹優盁叙攡彅帥"), BusinessException.ErrorMessage.local_business_para_error);
      while (true)
      {
        str2 = this.d.getServer4Business(4001);
        if (str2 != null)
          break;
        if (this.b != null)
          this.b.warn(this.c, str1 + FM_Int.lastIndexOf(142, "旷．莴叒夁琀皃幻叹夻贮"));
        throw new BusinessException(str1 + FM_Long.concat("旬ｉ菧厍奂瑗盘年叢夬购", 66), BusinessException.ErrorMessage.local_app_query_server_fail);
        localIMessageHandler = this.d.getMessageHandler();
        if (localIMessageHandler != null)
          continue;
        if (this.b != null)
          this.b.warn(this.c, str1 + FM_Exception.getChars(2, 91, "ｗ淞恾夈瑁嘪丧稢"));
        throw new BusinessException(str1 + Util4Java.toString("．涅怷备琈嘱举穵", 3, 43), BusinessException.ErrorMessage.local_message_load_config_fail);
      }
    }
    while (this.d == null)
    {
      if (this.b == null)
        this.b = LogFactory.getInstance().getLog();
      if (this.b != null)
        this.b.warn(this.c, str1 + BCCUtil.endsWith("－乔勺奌琓寻豮书稳", 2, 109));
      throw new BusinessException(str1 + CRCUtil.valueOf(160, "：丝勹奍瑜嘣刁姆匈夾败"), BusinessException.ErrorMessage.local_business_init_fail);
      this.d.businessReady(str1, str2);
      Object localObject = null;
      IMessage localIMessage = localIMessageHandler.createMessage(4001);
      try
      {
        ITag localITag1 = localIMessageHandler.createTag(65);
        localITag1.addValue(paramTerminalBackInfo.getTitle());
        localIMessage.addTag(localITag1);
        ITag localITag2 = localIMessageHandler.createTag(67);
        localITag2.addValue(paramTerminalBackInfo.getInfoType().getId());
        localIMessage.addTag(localITag2);
        if ((paramTerminalBackInfo.getOsVersion() != null) && (paramTerminalBackInfo.getOsVersion().length() > 0))
        {
          ITag localITag7 = localIMessageHandler.createTag(68);
          localITag7.addValue(paramTerminalBackInfo.getOsVersion());
          localIMessage.addTag(localITag7);
        }
        if ((paramTerminalBackInfo.getModelNumber() != null) && (paramTerminalBackInfo.getModelNumber().length() > 1))
        {
          ITag localITag6 = localIMessageHandler.createTag(69);
          localITag6.addValue(paramTerminalBackInfo.getModelNumber());
          localIMessage.addTag(localITag6);
        }
        if ((paramTerminalBackInfo.getBasebandVersion() != null) && (paramTerminalBackInfo.getBasebandVersion().length() > 1))
        {
          ITag localITag5 = localIMessageHandler.createTag(70);
          localITag5.addValue(paramTerminalBackInfo.getBasebandVersion());
          localIMessage.addTag(localITag5);
        }
        if ((paramTerminalBackInfo.getAppVersion() != null) && (paramTerminalBackInfo.getAppVersion().length() > 1))
        {
          ITag localITag4 = localIMessageHandler.createTag(44);
          localITag4.addValue(paramTerminalBackInfo.getAppVersion());
          localIMessage.addTag(localITag4);
        }
        if ((paramTerminalBackInfo.getId() != null) && (paramTerminalBackInfo.getId().length > 1))
        {
          ITag localITag3 = localIMessageHandler.createTag(81);
          localITag3.addValue(paramTerminalBackInfo.getId());
          localIMessage.addTag(localITag3);
        }
        byte[] arrayOfByte3 = localIMessage.toBytes();
        localObject = arrayOfByte3;
        byte[] arrayOfByte1 = this.d.interaction(localObject, str1, false, str2);
        byte[] arrayOfByte2 = Arrays.copyOf(arrayOfByte1, 2);
        this.d.businessFinish(false);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, arrayOfByte2))
        {
          if (this.b != null)
            this.b.error(this.c, str1 + FM_Utils.copyValueOf(2, 112, "!ｑ帾厭咀庉锔诲哀廉硌gm") + FM_Bytes.bytesToHexString(arrayOfByte1));
          i = FM_CN.bcdBytesToInt(arrayOfByte2);
          return i;
          if (paramTerminalBackInfo.getTitle() != null)
            continue;
          if (this.b != null)
            this.b.warn(this.c, str1 + FM_CN.subSequence("＜伿八皙厎攫彈幡", 2));
          throw new BusinessException(str1 + FM_Utils.copyValueOf(5, 112, "＜传儵盄厒攰弒常"), BusinessException.ErrorMessage.local_business_para_error);
        }
      }
      catch (FMCommunicationMessageException localFMCommunicationMessageException)
      {
        while (true)
        {
          if (this.b != null)
            this.b.warn(this.c, str1 + FM_Utils.copyValueOf(5, 105, "<柝遢常古诪汄政挶彃帲"));
          this.d.throwExceptionAndClose(str1 + CRCUtil.valueOf(3, "u柎遻帿叭诹汝敠捯奣赦"), BusinessException.ErrorMessage.local_message_message_handle_exception, false);
          continue;
          int i = 0;
        }
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.core.CardAppTradeImpl
 * JD-Core Version:    0.6.0
 */