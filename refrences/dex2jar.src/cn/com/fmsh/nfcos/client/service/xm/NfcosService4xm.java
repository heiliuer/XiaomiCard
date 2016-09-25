package cn.com.fmsh.nfcos.client.service.xm;

import a9;
import android.app.Service;
import android.content.Intent;
import android.content.pm.Signature;
import android.nfc.Tag;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.nfcos.client.service.business.NFCApduHandler;
import cn.com.fmsh.nfcos.client.service.business.OpenMobileApduHandler;
import cn.com.fmsh.nfcos.client.service.business.SeServiceHandler;
import cn.com.fmsh.nfcos.client.service.enums.BroadCastType;
import cn.com.fmsh.nfcos.client.service.log.FMLog4Android;
import cn.com.fmsh.tsm.business.BusinessManager;
import cn.com.fmsh.tsm.business.BusinessManagerFactory;
import cn.com.fmsh.tsm.business.CardAppInstall;
import cn.com.fmsh.tsm.business.CardAppTrade;
import cn.com.fmsh.tsm.business.IssuerProcessHandler;
import cn.com.fmsh.tsm.business.bean.BusinessOrder;
import cn.com.fmsh.tsm.business.bean.MainOrder;
import cn.com.fmsh.tsm.business.card.CardTools;
import cn.com.fmsh.tsm.business.core.ErrorCodeHandler;
import cn.com.fmsh.tsm.business.enums.EnumAppManageOperateType;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppStatus;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumCardIoType;
import cn.com.fmsh.tsm.business.enums.EnumIssueProcess;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.tsm.business.enums.EnumTerminalOpType;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import org.simalliance.openmobileapi.SEService;
import org.simalliance.openmobileapi.SEService.CallBack;

public class NfcosService4xm extends Service
  implements SeServiceHandler
{
  public static byte[] LINGNAN_PASS_AID;
  public static byte[] STPC_AID;
  public static final Signature[] h;

  static
  {
    try
    {
      byte[] arrayOfByte1 = new byte[9];
      arrayOfByte1[0] = -96;
      arrayOfByte1[4] = 3;
      arrayOfByte1[5] = -122;
      arrayOfByte1[6] = -104;
      arrayOfByte1[7] = 7;
      arrayOfByte1[8] = 1;
      STPC_AID = arrayOfByte1;
      byte[] arrayOfByte2 = new byte[9];
      arrayOfByte2[0] = -96;
      arrayOfByte2[4] = 3;
      arrayOfByte2[5] = -122;
      arrayOfByte2[6] = -104;
      arrayOfByte2[7] = 7;
      arrayOfByte2[8] = 1;
      LINGNAN_PASS_AID = arrayOfByte2;
      Signature[] arrayOfSignature = new Signature[1];
      byte[] arrayOfByte3 = new byte[8];
      arrayOfByte3[0] = 48;
      arrayOfByte3[1] = -126;
      arrayOfByte3[2] = 4;
      arrayOfByte3[3] = 108;
      arrayOfByte3[4] = -104;
      arrayOfByte3[5] = -10;
      arrayOfByte3[6] = 75;
      arrayOfByte3[7] = -39;
      arrayOfSignature[0] = new Signature(arrayOfByte3);
      h = arrayOfSignature;
      label157: return;
    }
    catch (a9 locala9)
    {
      break label157;
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    try
    {
      localAppManger = new AppManger();
      return localAppManger;
    }
    catch (a9 locala9)
    {
      while (true)
        AppManger localAppManger = null;
    }
  }

  public void onCreate()
  {
    this.c = new FMLog4Android();
    LogFactory.getInstance().setLog(this.c);
    this.c.info(this.b, FM_Bytes.startsWith("mj61tp:n\"1s2ok$8eu&k7#{w:phxq&", 2, 41));
    try
    {
      this.j = new SEService(this, new a(null));
      Random localRandom = new Random();
      byte[] arrayOfByte1 = new byte[32];
      localRandom.nextBytes(arrayOfByte1);
      this.k = new HashMap();
      this.n = BusinessManagerFactory.getBusinessManager();
      this.n.getCardAppInstall().registerIssuerProcessHandler(new IssuerProcessHandlerImpl());
      this.n.setTerminalNumber(arrayOfByte1);
      byte[] arrayOfByte2 = new byte[3];
      arrayOfByte2[0] = 2;
      arrayOfByte2[1] = 1;
      arrayOfByte2[2] = (byte)EnumTerminalOpType.ANDROID.getId();
      this.n.setMobileInfo(arrayOfByte2);
      this.l = this.n.getErrorCodeHandler();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e(this.b, FM_Long.concat("\036~rywft'=di", 3) + Util4Java.getExceptionInfo(localException));
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
      {
        Log.e(this.b, FM_CN.subSequence("Rv`y%5-yfxr5%?.>wjz!<>),+*f`f.;2#6as,t8>f$oxe.8!!=h2dj,6*9g}ow  6`\016AZXM\013\026\024\021", 2), localSecurityException);
        Log.e(this.b, Util4Java.getExceptionInfo(localSecurityException));
      }
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
    this.c.info(this.b, FM_Exception.getChars(274, 17, "xyh&#4\"|jQ#$<+eb\"3`q~/<-:k"));
    if ((this.j != null) && (this.j.isConnected()))
      this.j.shutdown();
  }

  public boolean onUnbind(Intent paramIntent)
  {
    try
    {
      this.c.info(this.b, FM_Utils.copyValueOf(1, 72, "1n2e7y$#z\tj.}2 \"z2j\"z2*b:"));
      boolean bool2 = super.onUnbind(paramIntent);
      bool1 = bool2;
      return bool1;
    }
    catch (a9 locala9)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public SEService seServiceReopen()
  {
    SEService localSEService = null;
    try
    {
      if ((this.j == null) || (!this.j.isConnected()))
        this.j = new SEService(this, new a(null));
      localSEService = this.j;
      return localSEService;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e(this.b, CRCUtil.valueOf(252, "\027;7 fsqf4q|") + localException.getMessage());
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
        Log.e(this.b, FM_Utils.copyValueOf(2, 63, "O%e.`&`fk+wb`,3q*y?6ym$s&9#w#}&}>e$dig5akw*o l3w<r8ww's;y<p8|*o=eeY\004I\025R\006E\021F~"));
    }
  }

  public void sendBroadCast(BroadCastParameter paramBroadCastParameter)
  {
    try
    {
      Intent localIntent = new Intent(BCCUtil.endsWith("c:f??)6jmg`r>\";#3:kpya68n'-nfm{i.<=='!q\"bfg=4'9?4:idm7\"", 1, 116));
      localIntent.putExtra(FM_Int.lastIndexOf(2, "68y;67u:0-7.od`kv(dd`oex#}jbg{pq;~bynr2lpaeabwq(jmzyjkh duh"), paramBroadCastParameter);
      sendBroadcast(localIntent, BCCUtil.endsWith("g-,\"orp{1h276q5z'=1}9j ybx/{>n%`jy*t!h;t(~ti=e;|'`;~>! b8b)l", 5, 63));
      label44: return;
    }
    catch (a9 locala9)
    {
      break label44;
    }
  }

  public void serviceConnected(SEService paramSEService)
  {
    try
    {
      this.c.info(this.b, FM_Exception.getChars(162, 93, "h=#{l)\nim.xy#1u&b"));
      label24: return;
    }
    catch (a9 locala9)
    {
      break label24;
    }
  }

  public class AppManger extends CardAppManager.Stub
  {
    public AppManger()
    {
    }

    public int applyIssue(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, String paramString, byte[] paramArrayOfByte2, NfcosMainOrder paramNfcosMainOrder)
      throws RemoteException
    {
      int i;
      if (paramNfcosMainOrder == null)
      {
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Utils.copyValueOf(3, 5, "叟卲论午由诰仔欯无７你儠盎淇总輤伍宺谩乷稨"));
        i = 9112;
      }
      while (true)
      {
        return i;
        label88: 
        do
        {
          try
          {
            if (NfcosService4xm.b(NfcosService4xm.this) != null)
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("洹势议匔略讼时９连囁纇柕乤穩", 1, 21));
            int j;
            if (j == 0)
              break label650;
            i = 0;
            break;
            EnumCardAppType localEnumCardAppType;
            do
            {
              if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length >= 1))
                break label536;
              if (NfcosService4xm.b(NfcosService4xm.this) != null)
                NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), BCCUtil.endsWith("叐卯诹卝甦讵仗欢斿：佣儵N\017桐诂斱攖", 2, 45));
              i = 9112;
              break;
              localEnumCardAppType = EnumCardAppType.instance(paramInt1);
            }
            while (localEnumCardAppType != null);
            if (NfcosService4xm.b(NfcosService4xm.this) != null)
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("叒却该卌畸讪仗歿日ｉ佷公盟卬丕廅甫簮垌旹攃", 2, 50));
            i = 9112;
            break;
            do
            {
              j = 0;
              byte[] arrayOfByte1 = new byte[3];
              arrayOfByte1[0] = 2;
              arrayOfByte1[1] = 1;
              arrayOfByte1[2] = (byte)paramInt3;
              byte[] arrayOfByte2 = FM_Bytes.intToBytes(paramInt2, 4);
              byte[] arrayOfByte3 = new byte[2];
              arrayOfByte3[0] = 1;
              arrayOfByte3[1] = (byte)arrayOfByte2.length;
              byte[] arrayOfByte4 = FM_Bytes.join(FM_Bytes.join(arrayOfByte1, arrayOfByte3), arrayOfByte2);
              byte[] arrayOfByte5 = new byte[2];
              arrayOfByte5[0] = 3;
              arrayOfByte5[1] = (byte)paramArrayOfByte1.length;
              byte[] arrayOfByte6 = FM_Bytes.join(FM_Bytes.join(arrayOfByte4, arrayOfByte5), paramArrayOfByte1);
              byte[] arrayOfByte7 = paramString.getBytes();
              byte[] arrayOfByte8 = new byte[2];
              arrayOfByte8[0] = 5;
              arrayOfByte8[1] = (byte)arrayOfByte7.length;
              byte[] arrayOfByte9 = FM_Bytes.join(FM_Bytes.join(arrayOfByte6, arrayOfByte8), arrayOfByte7);
              MainOrder localMainOrder = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().applyAct4Pay(paramArrayOfByte2, localEnumCardAppType, arrayOfByte9);
              if (localMainOrder == null)
                break;
              a(paramNfcosMainOrder, localMainOrder);
              j = 1;
              break label88;
            }
            while (paramInt2 >= 0);
            if (NfcosService4xm.b(NfcosService4xm.this) != null)
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), CRCUtil.valueOf(156, "參卢诶匐略记什欷旬＇佼儨盚敠亘釀颟旳敌"));
            i = 9112;
          }
          catch (BusinessException localBusinessException)
          {
            if (NfcosService4xm.b(NfcosService4xm.this) != null)
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Long.concat("课卒甡诪仐欭旨决珤彝干o", 4) + Util4Java.getExceptionInfo(localBusinessException));
            if (localBusinessException.getErrorMsg() != null)
            {
              i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
              break;
            }
            i = 99;
          }
          break;
          if ((paramString != null) && (paramString.length() >= 1))
            continue;
          if (NfcosService4xm.b(NfcosService4xm.this) != null)
            NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Int.lastIndexOf(2, "厄匷诵匍番训亃止斫ｒ使入绉竭垈右日敎"));
          i = 9112;
          break;
        }
        while ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length >= 1));
        label536: if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Long.concat("厌卩讱卋町诣仇歴斣ｌ佫儳洺劤缁砃旭敐", 5));
        i = 9112;
        continue;
        label650: i = 99;
      }
    }

    // ERROR //
    public int applyIssueByProduct(int paramInt1, String paramString1, int paramInt2, byte[] paramArrayOfByte1, String paramString2, byte[] paramArrayOfByte2, NfcosMainOrder paramNfcosMainOrder)
      throws RemoteException
    {
      // Byte code:
      //   0: aload 7
      //   2: ifnonnull +167 -> 169
      //   5: aload_0
      //   6: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   9: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   12: ifnull +32 -> 44
      //   15: aload_0
      //   16: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   19: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   22: aload_0
      //   23: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   26: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   29: bipush 120
      //   31: bipush 43
      //   33: ldc_w 442
      //   36: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   39: invokeinterface 323 3 0
      //   44: sipush 9112
      //   47: istore 9
      //   49: iload 9
      //   51: ireturn
      //   52: aload 5
      //   54: ifnull +12 -> 66
      //   57: aload 5
      //   59: invokevirtual 429	java/lang/String:length	()I
      //   62: iconst_1
      //   63: if_icmpge +252 -> 315
      //   66: aload_0
      //   67: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   70: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   73: ifnull +30 -> 103
      //   76: aload_0
      //   77: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   80: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   83: aload_0
      //   84: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   87: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   90: ldc_w 444
      //   93: bipush 50
      //   95: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   98: invokeinterface 323 3 0
      //   103: sipush 9112
      //   106: istore 9
      //   108: goto -59 -> 49
      //   111: aload_2
      //   112: ifnull +11 -> 123
      //   115: aload_2
      //   116: invokevirtual 429	java/lang/String:length	()I
      //   119: iconst_1
      //   120: if_icmpge +251 -> 371
      //   123: aload_0
      //   124: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   127: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   130: ifnull +31 -> 161
      //   133: aload_0
      //   134: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   137: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   140: aload_0
      //   141: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   144: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   147: ldc_w 446
      //   150: iconst_4
      //   151: bipush 12
      //   153: invokestatic 331	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   156: invokeinterface 323 3 0
      //   161: sipush 9112
      //   164: istore 9
      //   166: goto -117 -> 49
      //   169: iload_1
      //   170: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   173: astore 8
      //   175: aload 8
      //   177: ifnonnull -66 -> 111
      //   180: aload_0
      //   181: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   184: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   187: ifnull +29 -> 216
      //   190: aload_0
      //   191: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   194: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   197: aload_0
      //   198: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   201: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   204: iconst_5
      //   205: ldc_w 448
      //   208: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   211: invokeinterface 323 3 0
      //   216: sipush 9112
      //   219: istore 9
      //   221: goto -172 -> 49
      //   224: astore 21
      //   226: aload_0
      //   227: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   230: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   233: ifnull +51 -> 284
      //   236: aload_0
      //   237: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   240: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   243: aload_0
      //   244: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   247: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   250: new 385	java/lang/StringBuilder
      //   253: dup
      //   254: sipush 344
      //   257: bipush 28
      //   259: ldc_w 450
      //   262: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   265: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   268: aload 21
      //   270: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   273: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   276: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   279: invokeinterface 323 3 0
      //   284: aload 21
      //   286: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   289: ifnull +363 -> 652
      //   292: aload_0
      //   293: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   296: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   299: aload 21
      //   301: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   304: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   307: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   310: istore 9
      //   312: goto -263 -> 49
      //   315: aload 6
      //   317: ifnull +10 -> 327
      //   320: aload 6
      //   322: arraylength
      //   323: iconst_1
      //   324: if_icmpge +151 -> 475
      //   327: aload_0
      //   328: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   331: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   334: ifnull +29 -> 363
      //   337: aload_0
      //   338: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   341: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   344: aload_0
      //   345: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   348: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   351: iconst_2
      //   352: ldc_w 457
      //   355: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   358: invokeinterface 323 3 0
      //   363: sipush 9112
      //   366: istore 9
      //   368: goto -319 -> 49
      //   371: aload 4
      //   373: ifnull +10 -> 383
      //   376: aload 4
      //   378: arraylength
      //   379: iconst_1
      //   380: if_icmpge -328 -> 52
      //   383: aload_0
      //   384: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   387: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   390: ifnull +29 -> 419
      //   393: aload_0
      //   394: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   397: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   400: aload_0
      //   401: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   404: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   407: ldc_w 459
      //   410: iconst_5
      //   411: invokestatic 464	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   414: invokeinterface 323 3 0
      //   419: sipush 9112
      //   422: istore 9
      //   424: goto -375 -> 49
      //   427: aload_0
      //   428: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   431: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   434: ifnull +30 -> 464
      //   437: aload_0
      //   438: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   441: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   444: aload_0
      //   445: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   448: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   451: bipush 108
      //   453: ldc_w 466
      //   456: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   459: invokeinterface 323 3 0
      //   464: iload 10
      //   466: ifeq +193 -> 659
      //   469: iconst_0
      //   470: istore 9
      //   472: goto -423 -> 49
      //   475: iconst_0
      //   476: istore 10
      //   478: iconst_3
      //   479: newarray byte
      //   481: astore 11
      //   483: aload 11
      //   485: iconst_0
      //   486: iconst_2
      //   487: bastore
      //   488: aload 11
      //   490: iconst_1
      //   491: iconst_1
      //   492: bastore
      //   493: aload 11
      //   495: iconst_2
      //   496: iload_3
      //   497: i2b
      //   498: bastore
      //   499: aload_2
      //   500: invokevirtual 357	java/lang/String:getBytes	()[B
      //   503: astore 12
      //   505: iconst_2
      //   506: newarray byte
      //   508: astore 13
      //   510: aload 13
      //   512: iconst_0
      //   513: iconst_1
      //   514: bastore
      //   515: aload 13
      //   517: iconst_1
      //   518: aload 12
      //   520: arraylength
      //   521: i2b
      //   522: bastore
      //   523: aload 11
      //   525: aload 13
      //   527: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   530: aload 12
      //   532: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   535: astore 14
      //   537: iconst_2
      //   538: newarray byte
      //   540: astore 15
      //   542: aload 15
      //   544: iconst_0
      //   545: iconst_3
      //   546: bastore
      //   547: aload 15
      //   549: iconst_1
      //   550: aload 4
      //   552: arraylength
      //   553: i2b
      //   554: bastore
      //   555: aload 14
      //   557: aload 15
      //   559: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   562: aload 4
      //   564: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   567: astore 16
      //   569: aload 5
      //   571: invokevirtual 357	java/lang/String:getBytes	()[B
      //   574: astore 17
      //   576: iconst_2
      //   577: newarray byte
      //   579: astore 18
      //   581: aload 18
      //   583: iconst_0
      //   584: iconst_5
      //   585: bastore
      //   586: aload 18
      //   588: iconst_1
      //   589: aload 17
      //   591: arraylength
      //   592: i2b
      //   593: bastore
      //   594: aload 16
      //   596: aload 18
      //   598: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   601: aload 17
      //   603: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   606: astore 19
      //   608: aload_0
      //   609: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   612: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   615: invokeinterface 367 1 0
      //   620: aload 6
      //   622: aload 8
      //   624: aload 19
      //   626: invokeinterface 373 4 0
      //   631: astore 20
      //   633: aload 20
      //   635: ifnull -208 -> 427
      //   638: aload_0
      //   639: aload 7
      //   641: aload 20
      //   643: invokespecial 375	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosMainOrder;Lcn/com/fmsh/tsm/business/bean/MainOrder;)V
      //   646: iconst_1
      //   647: istore 10
      //   649: goto -185 -> 464
      //   652: bipush 99
      //   654: istore 9
      //   656: goto -607 -> 49
      //   659: bipush 99
      //   661: istore 9
      //   663: goto -614 -> 49
      //
      // Exception table:
      //   from	to	target	type
      //   427	464	224	cn/com/fmsh/tsm/business/exception/BusinessException
      //   608	646	224	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    // ERROR //
    public int applyRecharge(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, NfcosMainOrder paramNfcosMainOrder)
      throws RemoteException
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 7
      //   3: iconst_1
      //   4: istore 8
      //   6: aload 6
      //   8: ifnonnull +188 -> 196
      //   11: aload_0
      //   12: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   15: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   18: ifnull +31 -> 49
      //   21: aload_0
      //   22: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   25: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   28: aload_0
      //   29: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   32: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   35: ldc_w 470
      //   38: iconst_4
      //   39: bipush 88
      //   41: invokestatic 472	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
      //   44: invokeinterface 323 3 0
      //   49: sipush 9112
      //   52: istore 7
      //   54: iload 7
      //   56: ireturn
      //   57: iload_2
      //   58: ifge +195 -> 253
      //   61: aload_0
      //   62: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   65: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   68: ifnull +31 -> 99
      //   71: aload_0
      //   72: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   75: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   78: aload_0
      //   79: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   82: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   85: ldc_w 474
      //   88: iconst_4
      //   89: bipush 12
      //   91: invokestatic 331	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   94: invokeinterface 323 3 0
      //   99: sipush 9112
      //   102: istore 7
      //   104: goto -50 -> 54
      //   107: astore 17
      //   109: aload_0
      //   110: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   113: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   116: ifnull +49 -> 165
      //   119: aload_0
      //   120: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   123: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   126: aload_0
      //   127: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   130: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   133: new 385	java/lang/StringBuilder
      //   136: dup
      //   137: iconst_3
      //   138: bipush 73
      //   140: ldc_w 476
      //   143: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   146: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   149: aload 17
      //   151: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   154: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   157: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   160: invokeinterface 323 3 0
      //   165: aload 17
      //   167: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   170: ifnull +269 -> 439
      //   173: aload_0
      //   174: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   177: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   180: aload 17
      //   182: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   185: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   188: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   191: istore 7
      //   193: goto -139 -> 54
      //   196: iload_1
      //   197: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   200: astore 9
      //   202: aload 9
      //   204: ifnonnull -147 -> 57
      //   207: aload_0
      //   208: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   211: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   214: ifnull +31 -> 245
      //   217: aload_0
      //   218: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   221: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   224: aload_0
      //   225: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   228: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   231: ldc_w 478
      //   234: iconst_5
      //   235: bipush 68
      //   237: invokestatic 472	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
      //   240: invokeinterface 323 3 0
      //   245: sipush 9112
      //   248: istore 7
      //   250: goto -196 -> 54
      //   253: iconst_3
      //   254: newarray byte
      //   256: astore 10
      //   258: aload 10
      //   260: iload 7
      //   262: iconst_2
      //   263: bastore
      //   264: aload 10
      //   266: iload 8
      //   268: iload 8
      //   270: bastore
      //   271: aload 10
      //   273: iconst_2
      //   274: iload_3
      //   275: i2b
      //   276: bastore
      //   277: iload_2
      //   278: iconst_4
      //   279: invokestatic 348	cn/com/fmsh/util/FM_Bytes:intToBytes	(II)[B
      //   282: astore 11
      //   284: iconst_2
      //   285: newarray byte
      //   287: astore 12
      //   289: aload 12
      //   291: iload 7
      //   293: iload 8
      //   295: bastore
      //   296: aload 12
      //   298: iload 8
      //   300: aload 11
      //   302: arraylength
      //   303: i2b
      //   304: bastore
      //   305: aload 10
      //   307: aload 12
      //   309: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   312: aload 11
      //   314: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   317: astore 13
      //   319: iconst_2
      //   320: newarray byte
      //   322: astore 14
      //   324: aload 14
      //   326: iload 7
      //   328: iconst_3
      //   329: bastore
      //   330: aload 14
      //   332: iload 8
      //   334: aload 4
      //   336: arraylength
      //   337: i2b
      //   338: bastore
      //   339: aload 13
      //   341: aload 14
      //   343: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   346: aload 4
      //   348: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   351: astore 15
      //   353: aload_0
      //   354: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   357: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   360: invokeinterface 367 1 0
      //   365: aload 5
      //   367: aload 9
      //   369: aload 15
      //   371: invokeinterface 373 4 0
      //   376: astore 16
      //   378: aload 16
      //   380: ifnull +14 -> 394
      //   383: aload_0
      //   384: aload 6
      //   386: aload 16
      //   388: invokespecial 375	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosMainOrder;Lcn/com/fmsh/tsm/business/bean/MainOrder;)V
      //   391: goto +58 -> 449
      //   394: aload_0
      //   395: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   398: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   401: ifnull +45 -> 446
      //   404: aload_0
      //   405: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   408: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   411: aload_0
      //   412: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   415: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   418: bipush 6
      //   420: bipush 11
      //   422: ldc_w 480
      //   425: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   428: invokeinterface 323 3 0
      //   433: iconst_0
      //   434: istore 8
      //   436: goto +13 -> 449
      //   439: bipush 99
      //   441: istore 7
      //   443: goto -389 -> 54
      //   446: iconst_0
      //   447: istore 8
      //   449: iload 8
      //   451: ifne -397 -> 54
      //   454: bipush 99
      //   456: istore 7
      //   458: goto -404 -> 54
      //
      // Exception table:
      //   from	to	target	type
      //   353	433	107	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    // ERROR //
    public int applyRechargeEx(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
      throws RemoteException
    {
      // Byte code:
      //   0: iload_1
      //   1: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   4: astore 6
      //   6: aload 6
      //   8: ifnonnull +47 -> 55
      //   11: aload_0
      //   12: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   15: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   18: ifnull +29 -> 47
      //   21: aload_0
      //   22: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   25: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   28: aload_0
      //   29: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   32: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   35: ldc_w 484
      //   38: iconst_5
      //   39: invokestatic 464	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   42: invokeinterface 323 3 0
      //   47: sipush 9112
      //   50: istore 15
      //   52: iload 15
      //   54: ireturn
      //   55: iload_2
      //   56: ifge +136 -> 192
      //   59: aload_0
      //   60: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   63: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   66: ifnull +29 -> 95
      //   69: aload_0
      //   70: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   73: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   76: aload_0
      //   77: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   80: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   83: iconst_1
      //   84: ldc_w 486
      //   87: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   90: invokeinterface 323 3 0
      //   95: sipush 9112
      //   98: istore 15
      //   100: goto -48 -> 52
      //   103: astore 16
      //   105: aload_0
      //   106: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   109: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   112: ifnull +49 -> 161
      //   115: aload_0
      //   116: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   119: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   122: aload_0
      //   123: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   126: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   129: new 385	java/lang/StringBuilder
      //   132: dup
      //   133: iconst_4
      //   134: bipush 28
      //   136: ldc_w 488
      //   139: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   142: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   145: aload 16
      //   147: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   150: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   153: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   156: invokeinterface 323 3 0
      //   161: aload 16
      //   163: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   166: ifnull +422 -> 588
      //   169: aload_0
      //   170: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   173: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   176: aload 16
      //   178: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   181: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   184: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   187: istore 15
      //   189: goto -137 -> 52
      //   192: iconst_3
      //   193: newarray byte
      //   195: astore 7
      //   197: aload 7
      //   199: iconst_0
      //   200: iconst_2
      //   201: bastore
      //   202: aload 7
      //   204: iconst_1
      //   205: iconst_1
      //   206: bastore
      //   207: aload 7
      //   209: iconst_2
      //   210: iload_3
      //   211: i2b
      //   212: bastore
      //   213: iload_2
      //   214: iconst_4
      //   215: invokestatic 348	cn/com/fmsh/util/FM_Bytes:intToBytes	(II)[B
      //   218: astore 8
      //   220: iconst_2
      //   221: newarray byte
      //   223: astore 9
      //   225: aload 9
      //   227: iconst_0
      //   228: iconst_1
      //   229: bastore
      //   230: aload 9
      //   232: iconst_1
      //   233: aload 8
      //   235: arraylength
      //   236: i2b
      //   237: bastore
      //   238: aload 7
      //   240: aload 9
      //   242: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   245: aload 8
      //   247: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   250: astore 10
      //   252: iconst_2
      //   253: newarray byte
      //   255: astore 11
      //   257: aload 11
      //   259: iconst_0
      //   260: iconst_3
      //   261: bastore
      //   262: aload 11
      //   264: iconst_1
      //   265: aload 4
      //   267: arraylength
      //   268: i2b
      //   269: bastore
      //   270: aload 10
      //   272: aload 11
      //   274: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   277: aload 4
      //   279: invokestatic 352	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
      //   282: astore 12
      //   284: aload_0
      //   285: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   288: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   291: invokeinterface 367 1 0
      //   296: aload 5
      //   298: aload 6
      //   300: aload 12
      //   302: invokeinterface 373 4 0
      //   307: astore 13
      //   309: aload 13
      //   311: ifnonnull +208 -> 519
      //   314: aload_0
      //   315: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   318: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   321: ifnull +29 -> 350
      //   324: aload_0
      //   325: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   328: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   331: aload_0
      //   332: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   335: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   338: ldc_w 490
      //   341: iconst_1
      //   342: invokestatic 464	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   345: invokeinterface 323 3 0
      //   350: getstatic 494	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:business_order_not_exist	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   353: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   356: invokestatic 499	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   359: istore 15
      //   361: goto -309 -> 52
      //   364: aload_0
      //   365: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   368: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   371: invokeinterface 367 1 0
      //   376: aload 17
      //   378: invokevirtual 88	cn/com/fmsh/tsm/business/bean/BusinessOrder:getOrder	()[B
      //   381: aload 4
      //   383: invokeinterface 503 3 0
      //   388: ifeq +9 -> 397
      //   391: iconst_0
      //   392: istore 15
      //   394: goto -342 -> 52
      //   397: getstatic 506	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:trade_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   400: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   403: invokestatic 499	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   406: istore 15
      //   408: goto -356 -> 52
      //   411: aconst_null
      //   412: astore 17
      //   414: aload 13
      //   416: invokevirtual 241	cn/com/fmsh/tsm/business/bean/MainOrder:getBusinessOrders	()[Lcn/com/fmsh/tsm/business/bean/BusinessOrder;
      //   419: astore 18
      //   421: aload 18
      //   423: arraylength
      //   424: istore 19
      //   426: iconst_0
      //   427: istore 20
      //   429: iload 20
      //   431: iload 19
      //   433: if_icmplt +60 -> 493
      //   436: aload 17
      //   438: ifnonnull -74 -> 364
      //   441: aload_0
      //   442: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   445: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   448: ifnull +31 -> 479
      //   451: aload_0
      //   452: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   455: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   458: aload_0
      //   459: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   462: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   465: iconst_4
      //   466: bipush 82
      //   468: ldc_w 508
      //   471: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   474: invokeinterface 323 3 0
      //   479: getstatic 494	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:business_order_not_exist	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   482: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   485: invokestatic 499	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   488: istore 15
      //   490: goto -438 -> 52
      //   493: aload 18
      //   495: iload 20
      //   497: aaload
      //   498: astore 21
      //   500: aload 21
      //   502: ifnull +93 -> 595
      //   505: getstatic 511	cn/com/fmsh/tsm/business/enums/EnumBusinessOrderType:ORDER_TYPE_RECHARGE	Lcn/com/fmsh/tsm/business/enums/EnumBusinessOrderType;
      //   508: aload 21
      //   510: invokevirtual 164	cn/com/fmsh/tsm/business/bean/BusinessOrder:getBusinessOrderType	()Lcn/com/fmsh/tsm/business/enums/EnumBusinessOrderType;
      //   513: if_acmpne +82 -> 595
      //   516: goto +83 -> 599
      //   519: getstatic 514	cn/com/fmsh/tsm/business/enums/EnumOrderStatus:hasPaid	Lcn/com/fmsh/tsm/business/enums/EnumOrderStatus;
      //   522: aload 13
      //   524: invokevirtual 234	cn/com/fmsh/tsm/business/bean/MainOrder:getState	()Lcn/com/fmsh/tsm/business/enums/EnumOrderStatus;
      //   527: if_acmpeq -116 -> 411
      //   530: aload_0
      //   531: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   534: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   537: ifnull +33 -> 570
      //   540: aload_0
      //   541: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   544: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   547: aload_0
      //   548: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   551: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   554: ldc_w 516
      //   557: sipush 324
      //   560: bipush 70
      //   562: invokestatic 331	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   565: invokeinterface 323 3 0
      //   570: getstatic 519	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:business_order_apply_no_pay	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   573: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   576: invokestatic 499	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   579: istore 14
      //   581: iload 14
      //   583: istore 15
      //   585: goto -533 -> 52
      //   588: bipush 99
      //   590: istore 15
      //   592: goto -540 -> 52
      //   595: aload 17
      //   597: astore 21
      //   599: iinc 20 1
      //   602: aload 21
      //   604: astore 17
      //   606: goto -177 -> 429
      //
      // Exception table:
      //   from	to	target	type
      //   284	581	103	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int cancelIssue(int paramInt)
      throws RemoteException
    {
      try
      {
        NfcosService4xm.g(NfcosService4xm.this).getCardAppInstall().cancel();
        label17: return 0;
      }
      catch (a9 locala9)
      {
        break label17;
      }
    }

    // ERROR //
    public int cancelOrder(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
      throws RemoteException
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore_3
      //   4: aload_1
      //   5: ifnull +9 -> 14
      //   8: aload_1
      //   9: arraylength
      //   10: iconst_1
      //   11: if_icmpge +43 -> 54
      //   14: aload_0
      //   15: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   18: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   21: ifnull +31 -> 52
      //   24: aload_0
      //   25: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   28: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   31: aload_0
      //   32: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   35: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   38: sipush 224
      //   41: ldc_w 534
      //   44: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   47: invokeinterface 323 3 0
      //   52: iload_3
      //   53: ireturn
      //   54: aload_2
      //   55: ifnull +9 -> 64
      //   58: aload_2
      //   59: arraylength
      //   60: iconst_1
      //   61: if_icmpge +134 -> 195
      //   64: aload_0
      //   65: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   68: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   71: ifnull -19 -> 52
      //   74: aload_0
      //   75: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   78: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   81: aload_0
      //   82: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   85: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   88: ldc_w 536
      //   91: iconst_2
      //   92: bipush 17
      //   94: invokestatic 472	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
      //   97: invokeinterface 323 3 0
      //   102: goto -50 -> 52
      //   105: astore 5
      //   107: aload_0
      //   108: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   111: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   114: ifnull +51 -> 165
      //   117: aload_0
      //   118: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   121: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   124: aload_0
      //   125: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   128: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   131: new 385	java/lang/StringBuilder
      //   134: dup
      //   135: ldc_w 538
      //   138: sipush 168
      //   141: bipush 108
      //   143: invokestatic 472	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
      //   146: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   149: aload 5
      //   151: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   154: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   157: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   160: invokeinterface 323 3 0
      //   165: aload 5
      //   167: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   170: ifnull +58 -> 228
      //   173: aload_0
      //   174: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   177: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   180: aload 5
      //   182: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   185: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   188: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   191: istore_3
      //   192: goto -140 -> 52
      //   195: aload_0
      //   196: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   199: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   202: invokeinterface 367 1 0
      //   207: aload_2
      //   208: getstatic 544	cn/com/fmsh/tsm/business/enums/EnumOrderType:MAIN	Lcn/com/fmsh/tsm/business/enums/EnumOrderType;
      //   211: aload_1
      //   212: getstatic 547	cn/com/fmsh/tsm/business/enums/EnumOrderStatus:invalid	Lcn/com/fmsh/tsm/business/enums/EnumOrderStatus;
      //   215: invokeinterface 551 5 0
      //   220: istore 4
      //   222: iload 4
      //   224: istore_3
      //   225: goto -173 -> 52
      //   228: bipush 99
      //   230: istore_3
      //   231: goto -179 -> 52
      //
      // Exception table:
      //   from	to	target	type
      //   195	222	105	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    // ERROR //
    public int closeApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString)
      throws RemoteException
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore 5
      //   5: aload_1
      //   6: ifnull +9 -> 15
      //   9: aload_1
      //   10: arraylength
      //   11: iconst_1
      //   12: if_icmpge +235 -> 247
      //   15: aload_0
      //   16: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   19: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   22: ifnull +31 -> 53
      //   25: aload_0
      //   26: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   29: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   32: aload_0
      //   33: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   36: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   39: iconst_2
      //   40: bipush 45
      //   42: ldc_w 555
      //   45: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   48: invokeinterface 323 3 0
      //   53: iload 5
      //   55: ireturn
      //   56: astore 8
      //   58: aload_0
      //   59: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   62: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   65: ifnull +47 -> 112
      //   68: aload_0
      //   69: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   72: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   75: aload_0
      //   76: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   79: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   82: new 385	java/lang/StringBuilder
      //   85: dup
      //   86: ldc_w 557
      //   89: iconst_3
      //   90: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   93: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   96: aload 8
      //   98: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   101: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   104: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   107: invokeinterface 323 3 0
      //   112: aload 8
      //   114: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   117: ifnull +182 -> 299
      //   120: aload_0
      //   121: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   124: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   127: aload 8
      //   129: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   132: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   135: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   138: istore 5
      //   140: goto -87 -> 53
      //   143: aload_3
      //   144: ifnull +9 -> 153
      //   147: aload_3
      //   148: arraylength
      //   149: iconst_1
      //   150: if_icmpge +43 -> 193
      //   153: aload_0
      //   154: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   157: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   160: ifnull -107 -> 53
      //   163: aload_0
      //   164: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   167: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   170: aload_0
      //   171: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   174: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   177: bipush 6
      //   179: ldc_w 559
      //   182: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   185: invokeinterface 323 3 0
      //   190: goto -137 -> 53
      //   193: aload 4
      //   195: ifnull +12 -> 207
      //   198: aload 4
      //   200: invokevirtual 429	java/lang/String:length	()I
      //   203: iconst_1
      //   204: if_icmpge +102 -> 306
      //   207: aload_0
      //   208: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   211: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   214: ifnull -161 -> 53
      //   217: aload_0
      //   218: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   221: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   224: aload_0
      //   225: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   228: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   231: ldc_w 561
      //   234: iconst_2
      //   235: iconst_3
      //   236: invokestatic 338	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   239: invokeinterface 323 3 0
      //   244: goto -191 -> 53
      //   247: iload_2
      //   248: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   251: astore 6
      //   253: aload 6
      //   255: ifnonnull -112 -> 143
      //   258: aload_0
      //   259: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   262: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   265: ifnull -212 -> 53
      //   268: aload_0
      //   269: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   272: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   275: aload_0
      //   276: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   279: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   282: iconst_4
      //   283: bipush 30
      //   285: ldc_w 563
      //   288: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   291: invokeinterface 323 3 0
      //   296: goto -243 -> 53
      //   299: bipush 99
      //   301: istore 5
      //   303: goto -250 -> 53
      //   306: aload_0
      //   307: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   310: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   313: invokeinterface 525 1 0
      //   318: aload_1
      //   319: aload 6
      //   321: aload_3
      //   322: aload 4
      //   324: getstatic 569	cn/com/fmsh/tsm/business/enums/EnumAppManageOperateType:APP_LOCK	Lcn/com/fmsh/tsm/business/enums/EnumAppManageOperateType;
      //   327: invokeinterface 573 6 0
      //   332: ifeq +9 -> 341
      //   335: iconst_0
      //   336: istore 5
      //   338: goto -285 -> 53
      //   341: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:app_issuer_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   344: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   347: invokestatic 499	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   350: istore 7
      //   352: iload 7
      //   354: istore 5
      //   356: goto -303 -> 53
      //
      // Exception table:
      //   from	to	target	type
      //   306	352	56	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    // ERROR //
    public int deleteApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString, CardAppInfo paramCardAppInfo)
      throws RemoteException
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore 6
      //   5: aload_1
      //   6: ifnull +9 -> 15
      //   9: aload_1
      //   10: arraylength
      //   11: iconst_1
      //   12: if_icmpge +97 -> 109
      //   15: aload_0
      //   16: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   19: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   22: ifnull +33 -> 55
      //   25: aload_0
      //   26: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   29: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   32: aload_0
      //   33: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   36: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   39: sipush 152
      //   42: bipush 12
      //   44: ldc_w 580
      //   47: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   50: invokeinterface 323 3 0
      //   55: iload 6
      //   57: ireturn
      //   58: aload_3
      //   59: ifnull +9 -> 68
      //   62: aload_3
      //   63: arraylength
      //   64: iconst_1
      //   65: if_icmpge +96 -> 161
      //   68: aload_0
      //   69: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   72: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   75: ifnull -20 -> 55
      //   78: aload_0
      //   79: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   82: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   85: aload_0
      //   86: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   89: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   92: ldc_w 582
      //   95: iconst_5
      //   96: bipush 52
      //   98: invokestatic 338	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   101: invokeinterface 323 3 0
      //   106: goto -51 -> 55
      //   109: iload_2
      //   110: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   113: astore 7
      //   115: aload 7
      //   117: ifnonnull -59 -> 58
      //   120: aload_0
      //   121: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   124: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   127: ifnull -72 -> 55
      //   130: aload_0
      //   131: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   134: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   137: aload_0
      //   138: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   141: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   144: ldc_w 584
      //   147: sipush 250
      //   150: invokestatic 464	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   153: invokeinterface 323 3 0
      //   158: goto -103 -> 55
      //   161: aload 4
      //   163: ifnull +12 -> 175
      //   166: aload 4
      //   168: invokevirtual 429	java/lang/String:length	()I
      //   171: iconst_1
      //   172: if_icmpge +140 -> 312
      //   175: aload_0
      //   176: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   179: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   182: ifnull -127 -> 55
      //   185: aload_0
      //   186: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   189: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   192: aload_0
      //   193: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   196: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   199: ldc_w 586
      //   202: bipush 114
      //   204: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   207: invokeinterface 323 3 0
      //   212: goto -157 -> 55
      //   215: astore 10
      //   217: aload_0
      //   218: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   221: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   224: ifnull +50 -> 274
      //   227: aload_0
      //   228: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   231: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   234: aload_0
      //   235: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   238: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   241: new 385	java/lang/StringBuilder
      //   244: dup
      //   245: ldc_w 588
      //   248: bipush 90
      //   250: bipush 47
      //   252: invokestatic 472	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
      //   255: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   258: aload 10
      //   260: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   263: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   266: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   269: invokeinterface 323 3 0
      //   274: aload 10
      //   276: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   279: ifnull +26 -> 305
      //   282: aload_0
      //   283: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   286: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   289: aload 10
      //   291: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   294: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   297: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   300: istore 6
      //   302: goto -247 -> 55
      //   305: bipush 99
      //   307: istore 6
      //   309: goto -254 -> 55
      //   312: aload_0
      //   313: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   316: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   319: invokeinterface 525 1 0
      //   324: aload_1
      //   325: aload 7
      //   327: aload_3
      //   328: aload 4
      //   330: invokeinterface 591 5 0
      //   335: astore 8
      //   337: aload 8
      //   339: ifnull +42 -> 381
      //   342: aload 5
      //   344: aload 8
      //   346: invokevirtual 596	cn/com/fmsh/tsm/business/bean/CardAppInfo:getCardAppNo	()[B
      //   349: putfield 601	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:appNo	[B
      //   352: aload 5
      //   354: aload 8
      //   356: invokevirtual 605	cn/com/fmsh/tsm/business/bean/CardAppInfo:getBalance	()Ljava/lang/Integer;
      //   359: invokevirtual 608	java/lang/Integer:intValue	()I
      //   362: putfield 611	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:balance	I
      //   365: aload 5
      //   367: aload 8
      //   369: invokevirtual 614	cn/com/fmsh/tsm/business/bean/CardAppInfo:getMoc	()Ljava/lang/String;
      //   372: putfield 617	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:moc	Ljava/lang/String;
      //   375: iconst_0
      //   376: istore 6
      //   378: goto -323 -> 55
      //   381: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:app_issuer_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   384: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   387: invokestatic 499	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   390: istore 9
      //   392: iload 9
      //   394: istore 6
      //   396: goto -341 -> 55
      //
      // Exception table:
      //   from	to	target	type
      //   312	392	215	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    // ERROR //
    public int doIssue(byte[] paramArrayOfByte1, byte paramByte, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
      throws RemoteException
    {
      // Byte code:
      //   0: bipush 99
      //   2: istore 5
      //   4: aload_1
      //   5: ifnull +9 -> 14
      //   8: aload_1
      //   9: arraylength
      //   10: iconst_1
      //   11: if_icmpge +136 -> 147
      //   14: aload_0
      //   15: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   18: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   21: ifnull +31 -> 52
      //   24: aload_0
      //   25: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   28: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   31: aload_0
      //   32: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   35: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   38: iconst_1
      //   39: bipush 53
      //   41: ldc_w 621
      //   44: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   47: invokeinterface 323 3 0
      //   52: sipush 9112
      //   55: istore 5
      //   57: iload 5
      //   59: ireturn
      //   60: astore 8
      //   62: aload_0
      //   63: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   66: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   69: ifnull +47 -> 116
      //   72: aload_0
      //   73: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   76: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   79: aload_0
      //   80: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   83: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   86: new 385	java/lang/StringBuilder
      //   89: dup
      //   90: ldc_w 623
      //   93: iconst_3
      //   94: invokestatic 464	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   97: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   100: aload 8
      //   102: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   105: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   108: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   111: invokeinterface 323 3 0
      //   116: aload 8
      //   118: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   121: ifnull -64 -> 57
      //   124: aload_0
      //   125: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   128: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   131: aload 8
      //   133: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   136: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   139: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   142: istore 5
      //   144: goto -87 -> 57
      //   147: aload_3
      //   148: ifnull +9 -> 157
      //   151: aload_3
      //   152: arraylength
      //   153: iconst_1
      //   154: if_icmpge +47 -> 201
      //   157: aload_0
      //   158: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   161: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   164: ifnull +29 -> 193
      //   167: aload_0
      //   168: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   171: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   174: aload_0
      //   175: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   178: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   181: iconst_4
      //   182: ldc_w 625
      //   185: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   188: invokeinterface 323 3 0
      //   193: sipush 9112
      //   196: istore 5
      //   198: goto -141 -> 57
      //   201: aload_0
      //   202: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   205: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   208: aload_0
      //   209: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   212: invokestatic 629	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:h	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)[B
      //   215: invokeinterface 633 2 0
      //   220: pop
      //   221: aload_0
      //   222: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   225: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   228: new 635	cn/com/fmsh/nfcos/client/service/business/SocketExceptionHandler4Mi
      //   231: dup
      //   232: invokespecial 636	cn/com/fmsh/nfcos/client/service/business/SocketExceptionHandler4Mi:<init>	()V
      //   235: invokeinterface 640 2 0
      //   240: aload_0
      //   241: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   244: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   247: invokeinterface 525 1 0
      //   252: aload_1
      //   253: bipush 20
      //   255: aload_3
      //   256: aconst_null
      //   257: invokeinterface 644 5 0
      //   262: istore 7
      //   264: iload 7
      //   266: ifeq -209 -> 57
      //   269: iconst_0
      //   270: istore 5
      //   272: goto -215 -> 57
      //
      // Exception table:
      //   from	to	target	type
      //   240	264	60	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int doIssueEx(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte1, String paramString, byte[] paramArrayOfByte2)
      throws RemoteException
    {
      EnumCardAppType localEnumCardAppType = EnumCardAppType.instance(paramInt1);
      int i;
      if (localEnumCardAppType == null)
      {
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_CN.subSequence("穪串叟卼斺ｗ佪儼皌卶丌庁畬簨埉斱效", 2));
        i = 9112;
      }
      while (true)
      {
        return i;
        if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length >= 1))
          break label981;
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Utils.copyValueOf(90, 100, "厔匨讯卄畦议仅欿旳ｅ佭兴F\034桚讇日敁"));
        i = 9112;
        continue;
        label114: if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length >= 1))
          break;
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Int.lastIndexOf(212, "厖匩诫匟畸讻井歰方｜佱儷浨勼罃硗斷攐"));
        i = 9112;
        continue;
        label172: if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), Util4Java.toString("穥乲収匾宓扏３儚倣奮贺", 64, 64));
        i = Integer.parseInt(BusinessException.ErrorMessage.trade_fail.getId());
        continue;
        label225: if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Exception.getChars(5, 67, "稤乬叕匦夻赨"));
        i = Integer.parseInt(BusinessException.ErrorMessage.app_issuer_fail.getId());
      }
      while (true)
      {
        Object localObject1;
        Object localObject2;
        try
        {
          boolean bool1 = NfcosService4xm.g(NfcosService4xm.this).getCardAppInstall().issuerVer2(localObject1.getOrder(), 1, paramArrayOfByte1, null);
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).debug(NfcosService4xm.a(NfcosService4xm.this), FM_CN.subSequence("穪串叟卼纟柇p", 2) + bool1);
          if (!bool1)
            break label1143;
          if (localObject2 == null)
            continue;
          bool2 = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().remoteRecharge(localObject2.getOrder(), null);
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).debug(NfcosService4xm.a(NfcosService4xm.this), FM_Exception.getChars(236, 71, "稿両厂卻宍托＃儓倡纗林h") + bool1);
          if (!bool1)
            break label225;
          if (!bool2)
            break label172;
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).debug(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("穼丹叓危宒扜ｖ儍偪守戂", 5, 14));
          i = 0;
          break;
          if (EnumOrderStatus.hasPaid == localMainOrder.getState())
            continue;
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("穳乹厎卫斣，让匃末攣亏", 296, 43));
          int n = Integer.parseInt(BusinessException.ErrorMessage.business_order_apply_no_pay.getId());
          i = n;
          break;
          if (paramInt2 >= 0)
            continue;
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Exception.getChars(3, 73, "厍匤讬匂申设今步斲！佶兺盌放亂醒频斵敖"));
          i = 9112;
          break;
          localObject1 = null;
          localObject2 = null;
          BusinessOrder[] arrayOfBusinessOrder = localMainOrder.getBusinessOrders();
          int k = arrayOfBusinessOrder.length;
          int m = 0;
          if (m < k)
            continue;
          if (localObject1 != null)
            continue;
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Int.lastIndexOf(2, "稯乻历匹斯ｖ泺杕厌匿久务讣南"));
          i = Integer.parseInt(BusinessException.ErrorMessage.business_order_not_exist.getId());
          break;
          localObject3 = arrayOfBusinessOrder[m];
          if (localObject3 == null)
            break label1149;
          if (EnumBusinessOrderType.ORDER_TYPE_ISSUE != ((BusinessOrder)localObject3).getBusinessOrderType())
            continue;
          localObject1 = localObject3;
          EnumBusinessOrderType localEnumBusinessOrderType1 = EnumBusinessOrderType.ORDER_TYPE_RECHARGE;
          EnumBusinessOrderType localEnumBusinessOrderType2 = ((BusinessOrder)localObject3).getBusinessOrderType();
          if (localEnumBusinessOrderType1 != localEnumBusinessOrderType2)
            break label1149;
          localObject4 = localObject1;
          m++;
          localObject1 = localObject4;
          localObject2 = localObject3;
          continue;
          byte[] arrayOfByte1 = new byte[3];
          arrayOfByte1[0] = 2;
          arrayOfByte1[1] = 1;
          arrayOfByte1[2] = (byte)paramInt3;
          byte[] arrayOfByte2 = FM_Bytes.intToBytes(paramInt2, 4);
          byte[] arrayOfByte3 = new byte[2];
          arrayOfByte3[0] = 1;
          arrayOfByte3[1] = (byte)arrayOfByte2.length;
          byte[] arrayOfByte4 = FM_Bytes.join(FM_Bytes.join(arrayOfByte1, arrayOfByte3), arrayOfByte2);
          byte[] arrayOfByte5 = new byte[2];
          arrayOfByte5[0] = 3;
          arrayOfByte5[1] = (byte)paramArrayOfByte1.length;
          byte[] arrayOfByte6 = FM_Bytes.join(FM_Bytes.join(arrayOfByte4, arrayOfByte5), paramArrayOfByte1);
          byte[] arrayOfByte7 = paramString.getBytes();
          byte[] arrayOfByte8 = new byte[2];
          arrayOfByte8[0] = 5;
          arrayOfByte8[1] = (byte)arrayOfByte7.length;
          byte[] arrayOfByte9 = FM_Bytes.join(FM_Bytes.join(arrayOfByte6, arrayOfByte8), arrayOfByte7);
          MainOrder localMainOrder = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().applyAct4Pay(paramArrayOfByte2, localEnumCardAppType, arrayOfByte9);
          if (localMainOrder != null)
            continue;
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_CN.subSequence("穨丬叁卾斸ｑ帿厫哇庍數捹覥柅乿诱南夠败", 4));
          int j = Integer.parseInt(BusinessException.ErrorMessage.business_order_not_exist.getId());
          i = j;
          break;
          label981: if ((paramString != null) && (paramString.length() >= 1))
            break label114;
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Exception.getChars(86, 53, "厞卥讻匛田讯仕歼斡＀佡关纃端垞厽旿攜"));
          i = 9112;
        }
        catch (BusinessException localBusinessException)
        {
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), CRCUtil.valueOf(6, "稦习厏匮斶旧凸玣弆幭|") + Util4Java.getExceptionInfo(localBusinessException));
          if (localBusinessException.getErrorMsg() == null)
            continue;
          i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
          break;
          bool2 = true;
          continue;
          i = 99;
        }
        break;
        label1143: boolean bool2 = false;
        continue;
        label1149: Object localObject3 = localObject2;
        Object localObject4 = localObject1;
      }
    }

    public int doIssueExByProduct(int paramInt1, String paramString1, int paramInt2, byte[] paramArrayOfByte1, String paramString2, byte[] paramArrayOfByte2)
      throws RemoteException
    {
      int i = 9112;
      EnumCardAppType localEnumCardAppType = EnumCardAppType.instance(paramInt1);
      if (localEnumCardAppType == null)
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), Util4Java.toString("穸乼发匮旨！佼兮盞匨丒廓甾籾域旣攚", 3, 79));
      label57: Object localObject1;
      label536: label557: label699: BusinessOrder[] arrayOfBusinessOrder;
      int m;
      while (true)
      {
        return i;
        if ((paramString2 == null) || (paramString2.length() < 1))
        {
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), CRCUtil.valueOf(3, "厈匫诹匙甮诹仇欮旷～佣儱纍竹垌可早攒"));
          continue;
        }
        do
        {
          MainOrder localMainOrder;
          try
          {
            do
            {
              if (EnumOrderStatus.hasPaid == localMainOrder.getState())
                break label699;
              if (NfcosService4xm.b(NfcosService4xm.this) != null)
                NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Utils.copyValueOf(124, 96, "穽乪厖卦旱ｋ该卒札敨亟"));
              i = Integer.parseInt(BusinessException.ErrorMessage.business_order_apply_no_pay.getId());
              break;
              boolean bool = NfcosService4xm.g(NfcosService4xm.this).getCardAppInstall().issuerVer2(localObject1.getOrder(), 1, paramArrayOfByte1, null);
              if (!bool)
                break label536;
              i = 0;
              break;
              if ((paramString1 != null) && (paramString1.length() >= 1))
                break label557;
              if (NfcosService4xm.b(NfcosService4xm.this) == null)
                break;
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Utils.copyValueOf(4, 79, "叞匿讯匉畸语云欦旱ｚ伥儱令哓罗砑斿攆"));
              break;
              if ((paramArrayOfByte2 == null) || (paramArrayOfByte2.length < 1))
              {
                if (NfcosService4xm.b(NfcosService4xm.this) == null)
                  break;
                NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), Util4Java.toString("叓卧诨匛甡诡亂歠旴ｊ佪八洩勾罌砟斢攎", 3, 36));
                break;
              }
              byte[] arrayOfByte1 = new byte[3];
              arrayOfByte1[0] = 2;
              arrayOfByte1[1] = 1;
              arrayOfByte1[2] = (byte)paramInt2;
              byte[] arrayOfByte2 = paramString1.getBytes();
              byte[] arrayOfByte3 = new byte[2];
              arrayOfByte3[0] = 1;
              arrayOfByte3[1] = (byte)arrayOfByte2.length;
              byte[] arrayOfByte4 = FM_Bytes.join(FM_Bytes.join(arrayOfByte1, arrayOfByte3), arrayOfByte2);
              byte[] arrayOfByte5 = new byte[2];
              arrayOfByte5[0] = 3;
              arrayOfByte5[1] = (byte)paramArrayOfByte1.length;
              byte[] arrayOfByte6 = FM_Bytes.join(FM_Bytes.join(arrayOfByte4, arrayOfByte5), paramArrayOfByte1);
              byte[] arrayOfByte7 = paramString2.getBytes();
              byte[] arrayOfByte8 = new byte[2];
              arrayOfByte8[0] = 5;
              arrayOfByte8[1] = (byte)arrayOfByte7.length;
              byte[] arrayOfByte9 = FM_Bytes.join(FM_Bytes.join(arrayOfByte6, arrayOfByte8), arrayOfByte7);
              localMainOrder = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().applyAct4Pay(paramArrayOfByte2, localEnumCardAppType, arrayOfByte9);
            }
            while (localMainOrder != null);
            if (NfcosService4xm.b(NfcosService4xm.this) != null)
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), BCCUtil.endsWith("稻主厚匡旣ｆ幬古咄床散挦觾枂乼设卄奷贾", 66, 85));
            int j = Integer.parseInt(BusinessException.ErrorMessage.business_order_not_exist.getId());
            i = j;
            break;
            i = Integer.parseInt(BusinessException.ErrorMessage.app_issuer_fail.getId());
            break;
            i = 99;
            break;
            if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length >= 1))
              break label57;
            if (NfcosService4xm.b(NfcosService4xm.this) == null)
              break;
            NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Exception.getChars(232, 57, "厐卻诱卙當让仏歮旿ｎ伻儱^\003桘诞斱敂"));
          }
          catch (BusinessException localBusinessException)
          {
            do
            {
              if (NfcosService4xm.b(NfcosService4xm.this) == null)
                continue;
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), CRCUtil.valueOf(2, "稢乤压匪旪旻凤玿异幩x") + Util4Java.getExceptionInfo(localBusinessException));
            }
            while (localBusinessException.getErrorMsg() == null);
            i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
          }
          break;
          localObject1 = null;
          arrayOfBusinessOrder = localMainOrder.getBusinessOrders();
          int k = arrayOfBusinessOrder.length;
          m = 0;
          if (m < k)
            break label779;
        }
        while (localObject1 != null);
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_CN.subSequence("穨丬叁卾斸ｑ泭杒叛卸丒劶诤匀", 4));
        i = Integer.parseInt(BusinessException.ErrorMessage.business_order_not_exist.getId());
      }
      label779: Object localObject2 = arrayOfBusinessOrder[m];
      if (localObject2 != null)
      {
        EnumBusinessOrderType localEnumBusinessOrderType1 = EnumBusinessOrderType.ORDER_TYPE_ISSUE;
        EnumBusinessOrderType localEnumBusinessOrderType2 = ((BusinessOrder)localObject2).getBusinessOrderType();
        if (localEnumBusinessOrderType1 != localEnumBusinessOrderType2);
      }
      while (true)
      {
        m++;
        localObject1 = localObject2;
        break;
        localObject2 = localObject1;
      }
    }

    public int doRefund(byte[] paramArrayOfByte)
      throws RemoteException
    {
      int i;
      if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 1))
      {
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), Util4Java.toString("奓琕遏欵斱／诽华罁古乵穱", 120, 60));
        i = 9112;
      }
      while (true)
      {
        return i;
        try
        {
          int j = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().doRefund(paramArrayOfByte);
          i = j;
        }
        catch (BusinessException localBusinessException)
        {
          if (NfcosService4xm.b(NfcosService4xm.this) != null)
            NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), CRCUtil.valueOf(5, "奟瑊遝歰夛琖弃帪9") + Util4Java.getExceptionInfo(localBusinessException));
          if (localBusinessException.getErrorMsg() != null)
          {
            i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
            continue;
          }
          i = 99;
        }
      }
    }

    // ERROR //
    public int doUnsolvedOrder(byte[] paramArrayOfByte)
      throws RemoteException
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnull +9 -> 10
      //   4: aload_1
      //   5: arraylength
      //   6: iconst_1
      //   7: if_icmpge +141 -> 148
      //   10: aload_0
      //   11: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   14: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   17: ifnull +31 -> 48
      //   20: aload_0
      //   21: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   24: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   27: aload_0
      //   28: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   31: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   34: ldc_w 714
      //   37: sipush 232
      //   40: invokestatic 464	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   43: invokeinterface 323 3 0
      //   48: sipush 9112
      //   51: istore_2
      //   52: iload_2
      //   53: ireturn
      //   54: astore 5
      //   56: aload_0
      //   57: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   60: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   63: ifnull +49 -> 112
      //   66: aload_0
      //   67: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   70: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   73: aload_0
      //   74: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   77: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   80: new 385	java/lang/StringBuilder
      //   83: dup
      //   84: sipush 212
      //   87: ldc_w 716
      //   90: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   93: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   96: aload 5
      //   98: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   101: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   104: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   107: invokeinterface 323 3 0
      //   112: aload 5
      //   114: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   117: ifnull +25 -> 142
      //   120: aload_0
      //   121: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   124: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   127: aload 5
      //   129: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   132: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   135: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   138: istore_2
      //   139: goto -87 -> 52
      //   142: bipush 99
      //   144: istore_2
      //   145: goto -93 -> 52
      //   148: aload_0
      //   149: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   152: invokestatic 720	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:j	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/util/Map;
      //   155: aload_1
      //   156: invokestatic 723	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
      //   159: invokeinterface 729 2 0
      //   164: checkcast 730	[B
      //   167: astore_3
      //   168: aload_0
      //   169: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   172: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   175: invokeinterface 367 1 0
      //   180: aload_1
      //   181: aload_3
      //   182: invokeinterface 732 3 0
      //   187: istore 4
      //   189: iload 4
      //   191: istore_2
      //   192: goto -140 -> 52
      //
      // Exception table:
      //   from	to	target	type
      //   168	189	54	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    // ERROR //
    public int downloadApplet(int paramInt, byte[] paramArrayOfByte, String paramString)
      throws RemoteException
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore 4
      //   5: iload_1
      //   6: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   9: astore 5
      //   11: aload 5
      //   13: ifnonnull +185 -> 198
      //   16: aload_0
      //   17: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   20: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   23: ifnull +32 -> 55
      //   26: aload_0
      //   27: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   30: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   33: aload_0
      //   34: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   37: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   40: bipush 108
      //   42: bipush 18
      //   44: ldc_w 736
      //   47: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   50: invokeinterface 323 3 0
      //   55: iload 4
      //   57: ireturn
      //   58: astore 7
      //   60: aload_0
      //   61: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   64: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   67: ifnull +47 -> 114
      //   70: aload_0
      //   71: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   74: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   77: aload_0
      //   78: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   81: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   84: new 385	java/lang/StringBuilder
      //   87: dup
      //   88: iconst_5
      //   89: ldc_w 738
      //   92: invokestatic 436	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
      //   95: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   98: aload 7
      //   100: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   103: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   106: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   109: invokeinterface 323 3 0
      //   114: aload 7
      //   116: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   119: ifnull +180 -> 299
      //   122: aload_0
      //   123: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   126: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   129: aload 7
      //   131: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   134: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   137: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   140: istore 4
      //   142: goto -87 -> 55
      //   145: aload_3
      //   146: ifnull +11 -> 157
      //   149: aload_3
      //   150: invokevirtual 429	java/lang/String:length	()I
      //   153: iconst_1
      //   154: if_icmpge +97 -> 251
      //   157: aload_0
      //   158: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   161: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   164: ifnull -109 -> 55
      //   167: aload_0
      //   168: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   171: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   174: aload_0
      //   175: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   178: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   181: iconst_3
      //   182: bipush 68
      //   184: ldc_w 740
      //   187: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   190: invokeinterface 323 3 0
      //   195: goto -140 -> 55
      //   198: aload_2
      //   199: ifnull +9 -> 208
      //   202: aload_2
      //   203: arraylength
      //   204: iconst_1
      //   205: if_icmpge -60 -> 145
      //   208: aload_0
      //   209: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   212: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   215: ifnull -160 -> 55
      //   218: aload_0
      //   219: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   222: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   225: aload_0
      //   226: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   229: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   232: sipush 362
      //   235: bipush 72
      //   237: ldc_w 742
      //   240: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   243: invokeinterface 323 3 0
      //   248: goto -193 -> 55
      //   251: aload_0
      //   252: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   255: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   258: invokeinterface 525 1 0
      //   263: aload 5
      //   265: aload_2
      //   266: aload_3
      //   267: invokeinterface 746 4 0
      //   272: ifeq +9 -> 281
      //   275: iconst_0
      //   276: istore 4
      //   278: goto -223 -> 55
      //   281: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:app_issuer_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   284: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   287: invokestatic 499	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   290: istore 6
      //   292: iload 6
      //   294: istore 4
      //   296: goto -241 -> 55
      //   299: bipush 99
      //   301: istore 4
      //   303: goto -248 -> 55
      //
      // Exception table:
      //   from	to	target	type
      //   251	292	58	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    // ERROR //
    public int getAppIssueStatus(int paramInt, CardAppStatus paramCardAppStatus)
      throws RemoteException
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore_3
      //   4: aload_2
      //   5: ifnonnull +177 -> 182
      //   8: aload_0
      //   9: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   12: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   15: ifnull +31 -> 46
      //   18: aload_0
      //   19: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   22: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   25: aload_0
      //   26: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   29: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   32: ldc_w 750
      //   35: iconst_3
      //   36: bipush 75
      //   38: invokestatic 472	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
      //   41: invokeinterface 323 3 0
      //   46: iload_3
      //   47: ireturn
      //   48: astore 6
      //   50: aload_0
      //   51: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   54: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   57: ifnull +49 -> 106
      //   60: aload_0
      //   61: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   64: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   67: aload_0
      //   68: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   71: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   74: new 385	java/lang/StringBuilder
      //   77: dup
      //   78: ldc_w 752
      //   81: iconst_4
      //   82: bipush 119
      //   84: invokestatic 338	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   87: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   90: aload 6
      //   92: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   95: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   98: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   101: invokeinterface 323 3 0
      //   106: aload 6
      //   108: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   111: ifnull +121 -> 232
      //   114: aload_0
      //   115: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   118: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   121: aload 6
      //   123: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   126: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   129: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   132: istore_3
      //   133: goto -87 -> 46
      //   136: aload_0
      //   137: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   140: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   143: invokeinterface 525 1 0
      //   148: aload 4
      //   150: invokeinterface 756 2 0
      //   155: astore 5
      //   157: aload 5
      //   159: ifnull +17 -> 176
      //   162: aload_2
      //   163: aload 5
      //   165: invokevirtual 759	cn/com/fmsh/tsm/business/enums/EnumCardAppStatus:getId	()I
      //   168: invokevirtual 765	cn/com/fmsh/nfcos/client/service/xm/CardAppStatus:setStatus	(I)V
      //   171: iconst_0
      //   172: istore_3
      //   173: goto -127 -> 46
      //   176: bipush 99
      //   178: istore_3
      //   179: goto -133 -> 46
      //   182: iload_1
      //   183: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   186: astore 4
      //   188: aload 4
      //   190: ifnonnull -54 -> 136
      //   193: aload_0
      //   194: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   197: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   200: ifnull -154 -> 46
      //   203: aload_0
      //   204: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   207: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   210: aload_0
      //   211: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   214: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   217: ldc_w 767
      //   220: iconst_1
      //   221: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   224: invokeinterface 323 3 0
      //   229: goto -183 -> 46
      //   232: bipush 99
      //   234: istore_3
      //   235: goto -189 -> 46
      //
      // Exception table:
      //   from	to	target	type
      //   136	157	48	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int getAppIssueStatusByPlatform(int paramInt, byte[] paramArrayOfByte, String paramString, CardAppStatus paramCardAppStatus)
    {
      int i = 9112;
      if (paramCardAppStatus == null)
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("莴受匾乗廏畱历蠙狥恐方ａ攻挧輺伖乹稻", 2, 126));
      while (true)
      {
        return i;
        try
        {
          do
          {
            EnumCardAppStatus localEnumCardAppStatus = NfcosService4xm.g(NfcosService4xm.this).getCardAppInstall().getAppIssuerStatus4Platform(localEnumCardAppType, paramString, paramArrayOfByte);
            if (localEnumCardAppStatus != null)
            {
              paramCardAppStatus.setStatus(localEnumCardAppStatus.getId());
              i = 0;
              break;
            }
            i = 99;
            break;
            EnumCardAppType localEnumCardAppType = EnumCardAppType.instance(paramInt);
            if (localEnumCardAppType != null)
              break label297;
            if (NfcosService4xm.b(NfcosService4xm.this) == null)
              break;
            NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Utils.copyValueOf(5, 122, "莧叜卥乔廌畺狺恇斶ｖ佴儫匩么庈甾籫垁旤敖"));
            break;
          }
          while ((paramString != null) && (paramString.length() >= 1));
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), Util4Java.toString("莬厃卮乃店畵叆蠝犽恄斩５佳全盃绉窴垞厸觍桿盙衿祫旫攍", 60, 58));
        }
        catch (BusinessException localBusinessException)
        {
          label297: 
          do
          {
            if (NfcosService4xm.b(NfcosService4xm.this) != null)
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), BCCUtil.endsWith("莵友匹丙廚甡厕術独怔冪玻彄帹&", 3, 59) + Util4Java.getExceptionInfo(localBusinessException));
            if (localBusinessException.getErrorMsg() == null)
              break label350;
            i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
            break;
          }
          while ((paramArrayOfByte != null) && (paramArrayOfByte.length >= 1));
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Exception.getChars(184, 10, "莦反匤久廍畫厜蠛犷怊旣３伩其皙\"\002\036盁案讟旣故"));
          continue;
          label350: i = 99;
        }
      }
    }

    // ERROR //
    public int getInfo(int paramInt1, int paramInt2, CardAppInfo paramCardAppInfo)
      throws RemoteException
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 4
      //   3: aload_3
      //   4: ifnonnull +835 -> 839
      //   7: aload_0
      //   8: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   11: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   14: ifnull +29 -> 43
      //   17: aload_0
      //   18: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   21: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   24: aload_0
      //   25: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   28: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   31: iconst_1
      //   32: ldc_w 787
      //   35: invokestatic 436	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
      //   38: invokeinterface 323 3 0
      //   43: sipush 9112
      //   46: istore 4
      //   48: iload 4
      //   50: ireturn
      //   51: bipush 99
      //   53: istore 4
      //   55: goto -7 -> 48
      //   58: astore 12
      //   60: aload_0
      //   61: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   64: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   67: ifnull +49 -> 116
      //   70: aload_0
      //   71: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   74: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   77: aload_0
      //   78: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   81: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   84: new 385	java/lang/StringBuilder
      //   87: dup
      //   88: ldc_w 789
      //   91: iconst_5
      //   92: bipush 22
      //   94: invokestatic 338	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   97: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   100: aload 12
      //   102: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   105: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   108: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   111: invokeinterface 323 3 0
      //   116: aload 12
      //   118: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   121: ifnull +953 -> 1074
      //   124: aload_0
      //   125: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   128: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   131: aload 12
      //   133: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   136: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   139: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   142: istore 4
      //   144: goto -96 -> 48
      //   147: astore 18
      //   149: aload_0
      //   150: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   153: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   156: ifnull +49 -> 205
      //   159: aload_0
      //   160: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   163: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   166: aload_0
      //   167: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   170: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   173: new 385	java/lang/StringBuilder
      //   176: dup
      //   177: iconst_2
      //   178: bipush 59
      //   180: ldc_w 791
      //   183: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   186: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   189: aload 18
      //   191: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   194: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   197: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   200: invokeinterface 323 3 0
      //   205: aload 18
      //   207: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   210: ifnull -159 -> 51
      //   213: aload_0
      //   214: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   217: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   220: aload 18
      //   222: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   225: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   228: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   231: istore 4
      //   233: goto -185 -> 48
      //   236: aload 9
      //   238: iload 15
      //   240: invokeinterface 794 2 0
      //   245: checkcast 796	cn/com/fmsh/tsm/business/bean/CardAppRecord
      //   248: astore 16
      //   250: aload 16
      //   252: ifnull +72 -> 324
      //   255: new 798	cn/com/fmsh/nfcos/client/service/xm/CardAppRecord
      //   258: dup
      //   259: invokespecial 799	cn/com/fmsh/nfcos/client/service/xm/CardAppRecord:<init>	()V
      //   262: astore 17
      //   264: aload 17
      //   266: aload 16
      //   268: invokevirtual 803	cn/com/fmsh/tsm/business/bean/CardAppRecord:getTradeType	()Lcn/com/fmsh/tsm/business/enums/EnumTradeType;
      //   271: invokevirtual 806	cn/com/fmsh/tsm/business/enums/EnumTradeType:getId	()I
      //   274: putfield 809	cn/com/fmsh/nfcos/client/service/xm/CardAppRecord:tradeType	I
      //   277: aload 17
      //   279: aload 16
      //   281: invokevirtual 810	cn/com/fmsh/tsm/business/bean/CardAppRecord:getTradeDate	()Ljava/lang/String;
      //   284: putfield 811	cn/com/fmsh/nfcos/client/service/xm/CardAppRecord:tradeDate	Ljava/lang/String;
      //   287: aload 17
      //   289: aload 16
      //   291: invokevirtual 812	cn/com/fmsh/tsm/business/bean/CardAppRecord:getTradeTime	()Ljava/lang/String;
      //   294: putfield 813	cn/com/fmsh/nfcos/client/service/xm/CardAppRecord:tradeTime	Ljava/lang/String;
      //   297: aload 17
      //   299: aload 16
      //   301: invokevirtual 814	cn/com/fmsh/tsm/business/bean/CardAppRecord:getAmount	()I
      //   304: putfield 815	cn/com/fmsh/nfcos/client/service/xm/CardAppRecord:amount	I
      //   307: aload 17
      //   309: aload 16
      //   311: invokevirtual 817	cn/com/fmsh/tsm/business/bean/CardAppRecord:getBalance	()I
      //   314: putfield 818	cn/com/fmsh/nfcos/client/service/xm/CardAppRecord:balance	I
      //   317: aload 14
      //   319: iload 15
      //   321: aload 17
      //   323: aastore
      //   324: iinc 15 1
      //   327: iload 15
      //   329: aload 9
      //   331: invokeinterface 821 1 0
      //   336: if_icmplt -100 -> 236
      //   339: aload_3
      //   340: aload 14
      //   342: putfield 825	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:records	[Lcn/com/fmsh/nfcos/client/service/xm/CardAppRecord;
      //   345: iload_1
      //   346: bipush 8
      //   348: iand
      //   349: ifeq +30 -> 379
      //   352: aload_0
      //   353: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   356: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   359: invokeinterface 367 1 0
      //   364: aload 5
      //   366: invokeinterface 829 2 0
      //   371: ifeq +683 -> 1054
      //   374: aload_3
      //   375: iconst_1
      //   376: putfield 832	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:appLock	I
      //   379: iload_1
      //   380: bipush 16
      //   382: iand
      //   383: ifeq +26 -> 409
      //   386: aload_3
      //   387: aload_0
      //   388: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   391: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   394: invokeinterface 367 1 0
      //   399: aload 5
      //   401: invokeinterface 836 2 0
      //   406: putfield 617	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:moc	Ljava/lang/String;
      //   409: iload_1
      //   410: bipush 32
      //   412: iand
      //   413: ifeq -365 -> 48
      //   416: aload_3
      //   417: aload_0
      //   418: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   421: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   424: invokeinterface 367 1 0
      //   429: aload 5
      //   431: invokeinterface 839 2 0
      //   436: putfield 842	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:time4Validity	Ljava/lang/String;
      //   439: goto -391 -> 48
      //   442: astore 11
      //   444: aload_0
      //   445: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   448: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   451: ifnull +51 -> 502
      //   454: aload_0
      //   455: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   458: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   461: aload_0
      //   462: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   465: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   468: new 385	java/lang/StringBuilder
      //   471: dup
      //   472: sipush 220
      //   475: bipush 100
      //   477: ldc_w 844
      //   480: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   483: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   486: aload 11
      //   488: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   491: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   494: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   497: invokeinterface 323 3 0
      //   502: aload 11
      //   504: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   507: ifnull +574 -> 1081
      //   510: aload_0
      //   511: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   514: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   517: aload 11
      //   519: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   522: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   525: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   528: istore 4
      //   530: goto -482 -> 48
      //   533: bipush 99
      //   535: istore 4
      //   537: goto -489 -> 48
      //   540: bipush 99
      //   542: istore 4
      //   544: goto -496 -> 48
      //   547: astore 13
      //   549: aload_0
      //   550: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   553: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   556: ifnull +49 -> 605
      //   559: aload_0
      //   560: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   563: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   566: aload_0
      //   567: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   570: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   573: new 385	java/lang/StringBuilder
      //   576: dup
      //   577: iconst_2
      //   578: bipush 112
      //   580: ldc_w 846
      //   583: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   586: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   589: aload 13
      //   591: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   594: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   597: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   600: invokeinterface 323 3 0
      //   605: aload 13
      //   607: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   610: ifnull -70 -> 540
      //   613: aload_0
      //   614: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   617: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   620: aload 13
      //   622: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   625: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   628: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   631: istore 4
      //   633: goto -585 -> 48
      //   636: aload 5
      //   638: getstatic 138	cn/com/fmsh/tsm/business/enums/EnumCardAppType:CARD_APP_TYPE_SH	Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   641: if_acmpne +421 -> 1062
      //   644: aload_3
      //   645: aload 6
      //   647: iconst_4
      //   648: aload 6
      //   650: arraylength
      //   651: invokestatic 852	java/util/Arrays:copyOfRange	([BII)[B
      //   654: invokestatic 204	cn/com/fmsh/tsm/business/card/CardTools:getFaceID4UID	([B)Ljava/lang/String;
      //   657: putfield 855	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:cardFaceNo	Ljava/lang/String;
      //   660: aload_3
      //   661: aload 6
      //   663: putfield 601	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:appNo	[B
      //   666: iload_1
      //   667: iconst_2
      //   668: iand
      //   669: ifeq +29 -> 698
      //   672: aload_3
      //   673: aload_0
      //   674: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   677: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   680: invokeinterface 367 1 0
      //   685: aload 5
      //   687: invokeinterface 858 2 0
      //   692: invokevirtual 608	java/lang/Integer:intValue	()I
      //   695: putfield 611	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:balance	I
      //   698: iload_1
      //   699: iconst_4
      //   700: iand
      //   701: ifeq -356 -> 345
      //   704: aload_0
      //   705: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   708: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   711: invokeinterface 367 1 0
      //   716: aload 5
      //   718: invokeinterface 862 2 0
      //   723: astore 9
      //   725: aload 9
      //   727: ifnull +306 -> 1033
      //   730: aload 9
      //   732: invokeinterface 821 1 0
      //   737: anewarray 798	cn/com/fmsh/nfcos/client/service/xm/CardAppRecord
      //   740: astore 14
      //   742: iconst_0
      //   743: istore 15
      //   745: goto -418 -> 327
      //   748: astore 7
      //   750: aload_0
      //   751: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   754: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   757: ifnull +51 -> 808
      //   760: aload_0
      //   761: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   764: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   767: aload_0
      //   768: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   771: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   774: new 385	java/lang/StringBuilder
      //   777: dup
      //   778: ldc_w 864
      //   781: sipush 142
      //   784: bipush 73
      //   786: invokestatic 338	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   789: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   792: aload 7
      //   794: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   797: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   800: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   803: invokeinterface 323 3 0
      //   808: aload 7
      //   810: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   813: ifnull -280 -> 533
      //   816: aload_0
      //   817: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   820: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   823: aload 7
      //   825: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   828: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   831: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   834: istore 4
      //   836: goto -788 -> 48
      //   839: iload_2
      //   840: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   843: astore 5
      //   845: aload 5
      //   847: ifnonnull +56 -> 903
      //   850: aload_0
      //   851: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   854: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   857: ifnull +31 -> 888
      //   860: aload_0
      //   861: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   864: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   867: aload_0
      //   868: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   871: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   874: iconst_1
      //   875: bipush 82
      //   877: ldc_w 866
      //   880: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   883: invokeinterface 323 3 0
      //   888: sipush 9112
      //   891: istore 4
      //   893: goto -845 -> 48
      //   896: bipush 99
      //   898: istore 4
      //   900: goto -852 -> 48
      //   903: iload_1
      //   904: iconst_1
      //   905: iand
      //   906: ifeq -240 -> 666
      //   909: aload_0
      //   910: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   913: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   916: invokeinterface 367 1 0
      //   921: aload 5
      //   923: invokeinterface 870 2 0
      //   928: astore 6
      //   930: aload 6
      //   932: ifnonnull -296 -> 636
      //   935: aload_3
      //   936: ldc 143
      //   938: putfield 855	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:cardFaceNo	Ljava/lang/String;
      //   941: goto -281 -> 660
      //   944: astore 8
      //   946: aload_0
      //   947: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   950: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   953: ifnull +49 -> 1002
      //   956: aload_0
      //   957: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   960: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   963: aload_0
      //   964: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   967: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   970: new 385	java/lang/StringBuilder
      //   973: dup
      //   974: iconst_2
      //   975: bipush 30
      //   977: ldc_w 872
      //   980: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   983: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   986: aload 8
      //   988: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   991: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   994: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   997: invokeinterface 323 3 0
      //   1002: aload 8
      //   1004: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   1007: ifnull -111 -> 896
      //   1010: aload_0
      //   1011: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   1014: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   1017: aload 8
      //   1019: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   1022: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   1025: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   1028: istore 4
      //   1030: goto -982 -> 48
      //   1033: aload_0
      //   1034: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   1037: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   1040: ldc_w 874
      //   1043: iconst_5
      //   1044: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   1047: invokestatic 880	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   1050: pop
      //   1051: goto -706 -> 345
      //   1054: aload_3
      //   1055: iconst_0
      //   1056: putfield 832	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:appLock	I
      //   1059: goto -680 -> 379
      //   1062: aload_3
      //   1063: aload 6
      //   1065: invokestatic 207	cn/com/fmsh/tsm/business/card/CardTools:getFaceNo4uidByLnt	([B)Ljava/lang/String;
      //   1068: putfield 855	cn/com/fmsh/nfcos/client/service/xm/CardAppInfo:cardFaceNo	Ljava/lang/String;
      //   1071: goto -411 -> 660
      //   1074: bipush 99
      //   1076: istore 4
      //   1078: goto -1030 -> 48
      //   1081: bipush 99
      //   1083: istore 4
      //   1085: goto -1037 -> 48
      //
      // Exception table:
      //   from	to	target	type
      //   386	409	58	cn/com/fmsh/tsm/business/exception/BusinessException
      //   672	698	147	cn/com/fmsh/tsm/business/exception/BusinessException
      //   416	439	442	cn/com/fmsh/tsm/business/exception/BusinessException
      //   352	379	547	cn/com/fmsh/tsm/business/exception/BusinessException
      //   1054	1059	547	cn/com/fmsh/tsm/business/exception/BusinessException
      //   636	666	748	cn/com/fmsh/tsm/business/exception/BusinessException
      //   909	941	748	cn/com/fmsh/tsm/business/exception/BusinessException
      //   1062	1071	748	cn/com/fmsh/tsm/business/exception/BusinessException
      //   704	725	944	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int getInvoiceToken(byte[] paramArrayOfByte, InvoiceToken paramInvoiceToken)
      throws RemoteException
    {
      int i = 9112;
      if (paramInvoiceToken == null)
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("莳反记匜厑礿甽颃凱讒於－伸兪盂淕总轶佑寠谱乽稤", 3, 23));
      while (true)
      {
        return i;
        do
        {
          try
          {
            String str = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().getInvoiceToken(paramArrayOfByte);
            paramInvoiceToken.token = str;
            i = 0;
          }
          catch (BusinessException localBusinessException)
          {
            if (NfcosService4xm.b(NfcosService4xm.this) != null)
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Long.concat("讠単俹恬柫读斲u", 266) + Util4Java.getExceptionInfo(localBusinessException));
            if (localBusinessException.getErrorMsg() != null)
            {
              i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
              break;
            }
            i = 99;
          }
          break;
        }
        while ((paramArrayOfByte != null) && (paramArrayOfByte.length >= 1));
        if (NfcosService4xm.b(NfcosService4xm.this) == null)
          continue;
        NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), CRCUtil.valueOf(4, "菭厝课匘叏祧申颗凯讒斲ｙ佦兲讪卌缜厬斬攕"));
      }
    }

    public int login(String paramString1, String paramString2, LoginInfo paramLoginInfo)
      throws RemoteException
    {
      int i = 99;
      if ((paramString1 == null) || (paramString1.length() < 1))
      {
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Utils.copyValueOf(3, 8, "甦戡癥当旸：甶扱呃乬稤"));
        i = 9112;
      }
      while (true)
      {
        return i;
        label274: 
        do
        {
          cn.com.fmsh.tsm.business.bean.LoginInfo localLoginInfo;
          try
          {
            localLoginInfo = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().loginVer2(paramString1, paramString2);
            if (localLoginInfo != null)
              break label274;
            if (NfcosService4xm.b(NfcosService4xm.this) == null)
              break;
            NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Int.lastIndexOf(2, "畽扡瘬弍斯ｖ\0273:71Iodl举穿"));
          }
          catch (BusinessException localBusinessException)
          {
            if (NfcosService4xm.b(NfcosService4xm.this) != null)
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("甪扬瘯弘彄幧b", 1, 121) + Util4Java.getExceptionInfo(localBusinessException));
          }
          if (localBusinessException.getErrorMsg() == null)
            break;
          i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
          break;
          if ((paramString2 != null) && (paramString2.length() >= 1))
            continue;
          if (NfcosService4xm.b(NfcosService4xm.this) != null)
            NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("甫戴瘸弖旵／畫扴寅砂乹稹", 2, 32));
          i = 9112;
          break;
          paramLoginInfo.loginFailureCount = localLoginInfo.getFailureNum();
          paramLoginInfo.loginResult = localLoginInfo.getResult();
          paramLoginInfo.userLockTime = localLoginInfo.getUserLockTime();
          i = 0;
          break;
        }
        while (paramLoginInfo != null);
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_CN.subSequence("町戶癫彊斸ｑ瘷弎给枅俩恸輻伆乾稩", 4));
        i = 9112;
      }
    }

    public int logout()
      throws RemoteException
    {
      try
      {
        int j = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().logout();
        i = j;
        return i;
      }
      catch (BusinessException localBusinessException)
      {
        while (true)
        {
          if (NfcosService4xm.b(NfcosService4xm.this) != null)
            NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("三浹亽遞匮償偹归幣<", 162, 11) + Util4Java.getExceptionInfo(localBusinessException));
          if (localBusinessException.getErrorMsg() != null)
          {
            i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
            continue;
          }
          int i = 99;
        }
      }
    }

    // ERROR //
    public int modifyPassword(String paramString1, String paramString2)
      throws RemoteException
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore_3
      //   4: aload_1
      //   5: ifnull +11 -> 16
      //   8: aload_1
      //   9: invokevirtual 429	java/lang/String:length	()I
      //   12: iconst_1
      //   13: if_icmpge +129 -> 142
      //   16: aload_0
      //   17: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   20: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   23: ifnull +29 -> 52
      //   26: aload_0
      //   27: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   30: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   33: aload_0
      //   34: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   37: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   40: ldc_w 941
      //   43: iconst_5
      //   44: invokestatic 464	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   47: invokeinterface 323 3 0
      //   52: iload_3
      //   53: ireturn
      //   54: astore 5
      //   56: aload_0
      //   57: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   60: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   63: ifnull +49 -> 112
      //   66: aload_0
      //   67: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   70: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   73: aload_0
      //   74: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   77: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   80: new 385	java/lang/StringBuilder
      //   83: dup
      //   84: ldc_w 943
      //   87: iconst_2
      //   88: bipush 42
      //   90: invokestatic 331	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   93: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   96: aload 5
      //   98: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   101: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   104: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   107: invokeinterface 323 3 0
      //   112: aload 5
      //   114: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   117: ifnull +103 -> 220
      //   120: aload_0
      //   121: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   124: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   127: aload 5
      //   129: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   132: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   135: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   138: istore_3
      //   139: goto -87 -> 52
      //   142: aload_2
      //   143: ifnull +11 -> 154
      //   146: aload_2
      //   147: invokevirtual 429	java/lang/String:length	()I
      //   150: iconst_1
      //   151: if_icmpge +42 -> 193
      //   154: aload_0
      //   155: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   158: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   161: ifnull -109 -> 52
      //   164: aload_0
      //   165: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   168: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   171: aload_0
      //   172: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   175: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   178: iconst_4
      //   179: ldc_w 945
      //   182: invokestatic 436	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
      //   185: invokeinterface 323 3 0
      //   190: goto -138 -> 52
      //   193: aload_0
      //   194: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   197: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   200: invokeinterface 367 1 0
      //   205: aload_1
      //   206: aload_2
      //   207: invokeinterface 947 3 0
      //   212: istore 4
      //   214: iload 4
      //   216: istore_3
      //   217: goto -165 -> 52
      //   220: bipush 99
      //   222: istore_3
      //   223: goto -171 -> 52
      //
      // Exception table:
      //   from	to	target	type
      //   193	214	54	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int modifyUserInfo(UserInfo paramUserInfo)
      throws RemoteException
    {
      int i = 9112;
      if (paramUserInfo == null)
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), BCCUtil.endsWith("畻戩侨总曫旺斣，畣扡俠怣乭穸", 244, 43));
      while (true)
      {
        return i;
        if ((paramUserInfo.username == null) || (paramUserInfo.username.length() < 1))
        {
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("甿扸俦恰暣斿斱３畿戸告斿敟", 278, 56));
          continue;
        }
        do
        {
          while (true)
          {
            cn.com.fmsh.tsm.business.bean.UserInfo localUserInfo = new cn.com.fmsh.tsm.business.bean.UserInfo();
            localUserInfo.setUserName(paramUserInfo.username);
            localUserInfo.setPassword(paramUserInfo.password);
            localUserInfo.setUserType(2);
            try
            {
              int j = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().modifyUserInfo(localUserInfo);
              i = j;
            }
            catch (BusinessException localBusinessException)
            {
              if (NfcosService4xm.b(NfcosService4xm.this) != null)
                NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), BCCUtil.endsWith("産扥俸怯曳旾凯珬彁帲ｋ", 140, 71) + Util4Java.getExceptionInfo(localBusinessException));
              if (localBusinessException.getErrorMsg() == null)
                break label315;
            }
          }
          i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
          break;
        }
        while ((paramUserInfo.password != null) && (paramUserInfo.password.length() >= 1));
        if (NfcosService4xm.b(NfcosService4xm.this) == null)
          continue;
        NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Exception.getChars(4, 112, "畵扺侼怢曩施旫！畵扺宛硌旽故"));
        continue;
        label315: i = 99;
      }
    }

    // ERROR //
    public int moveApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString, VoucherInfo paramVoucherInfo)
      throws RemoteException
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore 6
      //   5: aload 5
      //   7: ifnonnull +282 -> 289
      //   10: aload_0
      //   11: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   14: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   17: ifnull +29 -> 46
      //   20: aload_0
      //   21: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   24: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   27: aload_0
      //   28: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   31: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   34: iconst_4
      //   35: ldc_w 984
      //   38: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   41: invokeinterface 323 3 0
      //   46: iload 6
      //   48: ireturn
      //   49: iload_2
      //   50: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   53: astore 7
      //   55: aload 7
      //   57: ifnonnull +129 -> 186
      //   60: aload_0
      //   61: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   64: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   67: ifnull -21 -> 46
      //   70: aload_0
      //   71: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   74: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   77: aload_0
      //   78: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   81: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   84: ldc_w 986
      //   87: iconst_2
      //   88: invokestatic 464	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   91: invokeinterface 323 3 0
      //   96: goto -50 -> 46
      //   99: astore 10
      //   101: aload_0
      //   102: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   105: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   108: ifnull +47 -> 155
      //   111: aload_0
      //   112: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   115: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   118: aload_0
      //   119: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   122: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   125: new 385	java/lang/StringBuilder
      //   128: dup
      //   129: iconst_2
      //   130: ldc_w 988
      //   133: invokestatic 436	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
      //   136: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   139: aload 10
      //   141: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   144: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   147: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   150: invokeinterface 323 3 0
      //   155: aload 10
      //   157: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   160: ifnull +247 -> 407
      //   163: aload_0
      //   164: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   167: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   170: aload 10
      //   172: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   175: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   178: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   181: istore 6
      //   183: goto -137 -> 46
      //   186: aload_3
      //   187: ifnull +9 -> 196
      //   190: aload_3
      //   191: arraylength
      //   192: iconst_1
      //   193: if_icmpge +42 -> 235
      //   196: aload_0
      //   197: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   200: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   203: ifnull -157 -> 46
      //   206: aload_0
      //   207: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   210: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   213: aload_0
      //   214: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   217: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   220: iconst_5
      //   221: ldc_w 990
      //   224: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   227: invokeinterface 323 3 0
      //   232: goto -186 -> 46
      //   235: aload 4
      //   237: ifnull +12 -> 249
      //   240: aload 4
      //   242: invokevirtual 429	java/lang/String:length	()I
      //   245: iconst_1
      //   246: if_icmpge +94 -> 340
      //   249: aload_0
      //   250: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   253: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   256: ifnull -210 -> 46
      //   259: aload_0
      //   260: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   263: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   266: aload_0
      //   267: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   270: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   273: ldc_w 992
      //   276: bipush 126
      //   278: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   281: invokeinterface 323 3 0
      //   286: goto -240 -> 46
      //   289: aload_1
      //   290: ifnull +9 -> 299
      //   293: aload_1
      //   294: arraylength
      //   295: iconst_1
      //   296: if_icmpge -247 -> 49
      //   299: aload_0
      //   300: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   303: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   306: ifnull -260 -> 46
      //   309: aload_0
      //   310: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   313: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   316: aload_0
      //   317: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   320: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   323: iconst_3
      //   324: bipush 119
      //   326: ldc_w 994
      //   329: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   332: invokeinterface 323 3 0
      //   337: goto -291 -> 46
      //   340: aload_0
      //   341: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   344: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   347: invokeinterface 525 1 0
      //   352: aload_1
      //   353: aload 7
      //   355: aload_3
      //   356: aload 4
      //   358: invokeinterface 997 5 0
      //   363: astore 8
      //   365: aload 8
      //   367: ifnull +22 -> 389
      //   370: aload 8
      //   372: arraylength
      //   373: ifle +16 -> 389
      //   376: aload 5
      //   378: aload 8
      //   380: putfield 1001	cn/com/fmsh/nfcos/client/service/xm/VoucherInfo:token	[B
      //   383: iconst_0
      //   384: istore 6
      //   386: goto -340 -> 46
      //   389: getstatic 576	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:app_issuer_fail	Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   392: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   395: invokestatic 499	java/lang/Integer:parseInt	(Ljava/lang/String;)I
      //   398: istore 9
      //   400: iload 9
      //   402: istore 6
      //   404: goto -358 -> 46
      //   407: bipush 99
      //   409: istore 6
      //   411: goto -365 -> 46
      //
      // Exception table:
      //   from	to	target	type
      //   340	400	99	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int openApp(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, String paramString)
      throws RemoteException
    {
      int i = 9112;
      if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length < 1))
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Utils.copyValueOf(2, 40, "庙甽彝吪旻ｙ佽兠廙畽箼瑃硌旵敕"));
      while (true)
      {
        return i;
        label56: if ((paramArrayOfByte2 == null) || (paramArrayOfByte2.length < 1))
        {
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Long.concat("廖略彘呬斸ｕ伤兪I@栗话斦攙", 202));
          continue;
        }
        try
        {
          do
          {
            if (NfcosService4xm.g(NfcosService4xm.this).getCardAppInstall().setAppVer2(paramArrayOfByte1, localEnumCardAppType, paramArrayOfByte2, paramString, EnumAppManageOperateType.APP_UNLOCK))
            {
              i = 0;
              break;
            }
            int j = Integer.parseInt(BusinessException.ErrorMessage.app_issuer_fail.getId());
            i = j;
            break;
          }
          while ((paramString != null) && (paramString.length() >= 1));
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("庑畽彅呺斳９伥兰绍窺城厢斥敝", 4, 112));
          continue;
          EnumCardAppType localEnumCardAppType = EnumCardAppType.instance(paramInt);
          if (localEnumCardAppType != null)
            break label56;
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), BCCUtil.endsWith("废番弙呿旱２併兩盇卻乛府男簭垆斤敓", 172, 55));
          continue;
          i = 99;
        }
        catch (BusinessException localBusinessException)
        {
          do
          {
            if (NfcosService4xm.b(NfcosService4xm.this) == null)
              continue;
            NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_CN.subSequence("廀甫弒吮*", 102) + Util4Java.getExceptionInfo(localBusinessException));
          }
          while (localBusinessException.getErrorMsg() == null);
          i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
        }
      }
    }

    // ERROR //
    public int paid4order(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
      throws RemoteException
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore_3
      //   4: aload_2
      //   5: ifnull +9 -> 14
      //   8: aload_2
      //   9: arraylength
      //   10: iconst_1
      //   11: if_icmpge +49 -> 60
      //   14: aload_0
      //   15: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   18: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   21: ifnull +31 -> 52
      //   24: aload_0
      //   25: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   28: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   31: aload_0
      //   32: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   35: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   38: ldc_w 1018
      //   41: iconst_5
      //   42: bipush 25
      //   44: invokestatic 331	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   47: invokeinterface 323 3 0
      //   52: iload_3
      //   53: ireturn
      //   54: bipush 99
      //   56: istore_3
      //   57: goto -5 -> 52
      //   60: aload_1
      //   61: ifnull +9 -> 70
      //   64: aload_1
      //   65: arraylength
      //   66: iconst_1
      //   67: if_icmpge +128 -> 195
      //   70: aload_0
      //   71: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   74: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   77: ifnull -25 -> 52
      //   80: aload_0
      //   81: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   84: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   87: aload_0
      //   88: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   91: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   94: iconst_2
      //   95: ldc_w 1020
      //   98: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   101: invokeinterface 323 3 0
      //   106: goto -54 -> 52
      //   109: astore 5
      //   111: aload_0
      //   112: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   115: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   118: ifnull +47 -> 165
      //   121: aload_0
      //   122: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   125: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   128: aload_0
      //   129: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   132: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   135: new 385	java/lang/StringBuilder
      //   138: dup
      //   139: ldc_w 1022
      //   142: iconst_5
      //   143: invokestatic 464	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   146: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   149: aload 5
      //   151: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   154: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   157: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   160: invokeinterface 323 3 0
      //   165: aload 5
      //   167: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   170: ifnull -116 -> 54
      //   173: aload_0
      //   174: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   177: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   180: aload 5
      //   182: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   185: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   188: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   191: istore_3
      //   192: goto -140 -> 52
      //   195: aload_0
      //   196: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   199: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   202: invokeinterface 367 1 0
      //   207: aload_2
      //   208: getstatic 544	cn/com/fmsh/tsm/business/enums/EnumOrderType:MAIN	Lcn/com/fmsh/tsm/business/enums/EnumOrderType;
      //   211: aload_1
      //   212: getstatic 514	cn/com/fmsh/tsm/business/enums/EnumOrderStatus:hasPaid	Lcn/com/fmsh/tsm/business/enums/EnumOrderStatus;
      //   215: invokeinterface 551 5 0
      //   220: istore 4
      //   222: iload 4
      //   224: istore_3
      //   225: goto -173 -> 52
      //
      // Exception table:
      //   from	to	target	type
      //   195	222	109	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    // ERROR //
    public int queryActivities(int paramInt, java.util.List<NfcosActivity> paramList)
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore_3
      //   4: aload_2
      //   5: ifnonnull +194 -> 199
      //   8: aload_0
      //   9: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   12: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   15: ifnull +29 -> 44
      //   18: aload_0
      //   19: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   22: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   25: aload_0
      //   26: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   29: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   32: iconst_4
      //   33: ldc_w 1026
      //   36: invokestatic 436	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
      //   39: invokeinterface 323 3 0
      //   44: iload_3
      //   45: ireturn
      //   46: astore 12
      //   48: aload_0
      //   49: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   52: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   55: ifnull +49 -> 104
      //   58: aload_0
      //   59: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   62: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   65: aload_0
      //   66: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   69: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   72: new 385	java/lang/StringBuilder
      //   75: dup
      //   76: iconst_3
      //   77: bipush 33
      //   79: ldc_w 1028
      //   82: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   85: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   88: aload 12
      //   90: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   93: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   96: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   99: invokeinterface 323 3 0
      //   104: aload 12
      //   106: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   109: ifnull +140 -> 249
      //   112: aload_0
      //   113: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   116: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   119: aload 12
      //   121: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   124: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   127: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   130: istore_3
      //   131: goto -87 -> 44
      //   134: aload 6
      //   136: invokeinterface 1034 1 0
      //   141: checkcast 21	cn/com/fmsh/tsm/business/bean/Activity
      //   144: astore 9
      //   146: new 27	cn/com/fmsh/nfcos/client/service/xm/NfcosActivity
      //   149: dup
      //   150: invokespecial 1035	cn/com/fmsh/nfcos/client/service/xm/NfcosActivity:<init>	()V
      //   153: astore 10
      //   155: aload_0
      //   156: aload 10
      //   158: aload 9
      //   160: invokespecial 1037	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosActivity;Lcn/com/fmsh/tsm/business/bean/Activity;)V
      //   163: aload_2
      //   164: aload 10
      //   166: invokeinterface 264 2 0
      //   171: pop
      //   172: aload 6
      //   174: invokeinterface 1041 1 0
      //   179: istore 7
      //   181: iload 7
      //   183: ifne -49 -> 134
      //   186: iconst_1
      //   187: istore 8
      //   189: iload 8
      //   191: ifeq +64 -> 255
      //   194: iconst_0
      //   195: istore_3
      //   196: goto -152 -> 44
      //   199: iload_1
      //   200: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   203: astore 4
      //   205: aload 4
      //   207: ifnonnull +54 -> 261
      //   210: aload_0
      //   211: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   214: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   217: ifnull -173 -> 44
      //   220: aload_0
      //   221: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   224: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   227: aload_0
      //   228: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   231: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   234: ldc_w 1043
      //   237: iconst_2
      //   238: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   241: invokeinterface 323 3 0
      //   246: goto -202 -> 44
      //   249: bipush 99
      //   251: istore_3
      //   252: goto -208 -> 44
      //   255: bipush 99
      //   257: istore_3
      //   258: goto -214 -> 44
      //   261: aload_0
      //   262: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   265: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   268: invokeinterface 367 1 0
      //   273: aload 4
      //   275: invokeinterface 1045 2 0
      //   280: astore 5
      //   282: aload 5
      //   284: ifnonnull +47 -> 331
      //   287: aload_0
      //   288: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   291: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   294: ifnull +49 -> 343
      //   297: aload_0
      //   298: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   301: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   304: aload_0
      //   305: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   308: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   311: sipush 356
      //   314: ldc_w 1047
      //   317: invokestatic 436	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
      //   320: invokeinterface 323 3 0
      //   325: iconst_0
      //   326: istore 8
      //   328: goto -139 -> 189
      //   331: aload 5
      //   333: invokeinterface 1051 1 0
      //   338: astore 6
      //   340: goto -168 -> 172
      //   343: iconst_0
      //   344: istore 8
      //   346: goto -157 -> 189
      //
      // Exception table:
      //   from	to	target	type
      //   134	181	46	cn/com/fmsh/tsm/business/exception/BusinessException
      //   261	340	46	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int queryBusinessOrder(byte[] paramArrayOfByte, NfcosBusinessOrder paramNfcosBusinessOrder)
      throws RemoteException
    {
      int i = 99;
      label51: BusinessOrder localBusinessOrder;
      if (paramNfcosBusinessOrder == null)
      {
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), Util4Java.toString("丞勽诶匙订纚侵怣枡课既＀伤兹皐涄恫輡伇宵谥书種", 5, 120));
        i = 9112;
        return i;
        paramNfcosBusinessOrder.tradeDate = localBusinessOrder.getTradeDate();
        paramNfcosBusinessOrder.tradeTime = localBusinessOrder.getTradeTime();
        paramNfcosBusinessOrder.order = localBusinessOrder.getOrder();
        paramNfcosBusinessOrder.amount = localBusinessOrder.getAmount();
        if (localBusinessOrder.getTradeState() != null)
          break label406;
      }
      label391: label406: for (paramNfcosBusinessOrder.tradeState = EnumOrderStatus.unknown.getId(); ; paramNfcosBusinessOrder.tradeState = localBusinessOrder.getTradeState().getId())
      {
        if (localBusinessOrder.getCardAppType() == EnumCardAppType.CARD_APP_TYPE_SH)
        {
          if ((localBusinessOrder.getCardNo() != null) && (localBusinessOrder.getCardNo().length >= 4))
            break label391;
          paramNfcosBusinessOrder.faceNo = "";
        }
        while (true)
        {
          paramNfcosBusinessOrder.invoiceStatus = localBusinessOrder.getInvoiceStatus();
          if (localBusinessOrder.getCardIoType() == null);
          for (paramNfcosBusinessOrder.cardIoType = EnumCardIoType.CARD_IO_UNKNOW.getId(); ; paramNfcosBusinessOrder.cardIoType = localBusinessOrder.getCardIoType().getId())
          {
            while (true)
            {
              if (localBusinessOrder.getBusinessOrderType() == null)
              {
                paramNfcosBusinessOrder.businessOrderType = EnumBusinessOrderType.UNKNOW.getId();
                i = 0;
                break;
              }
              try
              {
                localBusinessOrder = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().queryBusinessOrder(paramArrayOfByte);
                if (localBusinessOrder != null)
                  break label51;
                if (NfcosService4xm.b(NfcosService4xm.this) == null)
                  break;
                NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), Util4Java.toString("乙勵讧千诡绞侨怵枮设旻２进嚞纂柞乩穾", 228, 17));
                break;
                paramNfcosBusinessOrder.businessOrderType = localBusinessOrder.getBusinessOrderType().getId();
              }
              catch (BusinessException localBusinessException)
              {
                if (NfcosService4xm.b(NfcosService4xm.this) != null)
                  NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), Util4Java.toString("丘勲详匀讠绑俩恶柯讹斺g", 3, 113) + Util4Java.getExceptionInfo(localBusinessException));
              }
            }
            if (localBusinessException.getErrorMsg() == null)
              break;
            i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
            break;
          }
          paramNfcosBusinessOrder.faceNo = CardTools.getFaceNo4uidByLnt(localBusinessOrder.getCardNo());
          continue;
          paramNfcosBusinessOrder.faceNo = CardTools.getFaceID4UID(localBusinessOrder.getCardNo());
        }
      }
    }

    // ERROR //
    public int queryBusinessOrders(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4, java.util.List<NfcosBusinessOrder> paramList)
    {
      // Byte code:
      //   0: aload 6
      //   2: ifnonnull +143 -> 145
      //   5: aload_0
      //   6: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   9: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   12: ifnull +29 -> 41
      //   15: aload_0
      //   16: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   19: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   22: aload_0
      //   23: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   26: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   29: iconst_5
      //   30: ldc_w 1066
      //   33: invokestatic 436	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
      //   36: invokeinterface 323 3 0
      //   41: sipush 9112
      //   44: istore 10
      //   46: iload 10
      //   48: ireturn
      //   49: astore 17
      //   51: aload_0
      //   52: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   55: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   58: ifnull +49 -> 107
      //   61: aload_0
      //   62: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   65: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   68: aload_0
      //   69: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   72: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   75: new 385	java/lang/StringBuilder
      //   78: dup
      //   79: ldc_w 1068
      //   82: iconst_1
      //   83: bipush 49
      //   85: invokestatic 472	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
      //   88: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   91: aload 17
      //   93: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   96: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   99: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   102: invokeinterface 323 3 0
      //   107: aload 17
      //   109: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   112: ifnull +26 -> 138
      //   115: aload_0
      //   116: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   119: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   122: aload 17
      //   124: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   127: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   130: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   133: istore 10
      //   135: goto -89 -> 46
      //   138: bipush 99
      //   140: istore 10
      //   142: goto -96 -> 46
      //   145: iload_3
      //   146: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   149: astore 7
      //   151: aload 7
      //   153: ifnonnull +49 -> 202
      //   156: aload_0
      //   157: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   160: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   163: ifnull +31 -> 194
      //   166: aload_0
      //   167: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   170: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   173: aload_0
      //   174: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   177: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   180: ldc_w 1070
      //   183: iconst_4
      //   184: bipush 38
      //   186: invokestatic 338	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   189: invokeinterface 323 3 0
      //   194: sipush 9112
      //   197: istore 10
      //   199: goto -153 -> 46
      //   202: aconst_null
      //   203: astore 8
      //   205: aload 4
      //   207: ifnull +33 -> 240
      //   210: aload 4
      //   212: arraylength
      //   213: ifle +27 -> 240
      //   216: new 243	java/util/ArrayList
      //   219: dup
      //   220: invokespecial 244	java/util/ArrayList:<init>	()V
      //   223: astore 8
      //   225: aload 4
      //   227: arraylength
      //   228: istore 18
      //   230: iconst_0
      //   231: istore 19
      //   233: iload 19
      //   235: iload 18
      //   237: if_icmplt +59 -> 296
      //   240: iload 5
      //   242: invokestatic 1073	cn/com/fmsh/tsm/business/enums/EnumBusinessOrderType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumBusinessOrderType;
      //   245: astore 9
      //   247: aload 9
      //   249: ifnonnull +78 -> 327
      //   252: aload_0
      //   253: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   256: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   259: ifnull +29 -> 288
      //   262: aload_0
      //   263: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   266: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   269: aload_0
      //   270: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   273: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   276: iconst_4
      //   277: ldc_w 1075
      //   280: invokestatic 436	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
      //   283: invokeinterface 323 3 0
      //   288: sipush 9112
      //   291: istore 10
      //   293: goto -247 -> 46
      //   296: aload 4
      //   298: iload 19
      //   300: iaload
      //   301: invokestatic 1079	cn/com/fmsh/tsm/business/enums/EnumOrderStatus:getOrderStatus4ID	(I)Lcn/com/fmsh/tsm/business/enums/EnumOrderStatus;
      //   304: astore 20
      //   306: aload 20
      //   308: ifnull +13 -> 321
      //   311: aload 8
      //   313: aload 20
      //   315: invokeinterface 264 2 0
      //   320: pop
      //   321: iinc 19 1
      //   324: goto -91 -> 233
      //   327: iload_1
      //   328: iflt +7 -> 335
      //   331: iload_2
      //   332: ifge +49 -> 381
      //   335: aload_0
      //   336: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   339: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   342: ifnull +31 -> 373
      //   345: aload_0
      //   346: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   349: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   352: aload_0
      //   353: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   356: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   359: iconst_4
      //   360: bipush 91
      //   362: ldc_w 1081
      //   365: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   368: invokeinterface 323 3 0
      //   373: sipush 9112
      //   376: istore 10
      //   378: goto -332 -> 46
      //   381: aload_0
      //   382: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   385: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   388: invokeinterface 367 1 0
      //   393: iload_1
      //   394: iload_2
      //   395: aload 7
      //   397: aload 9
      //   399: aload 8
      //   401: aconst_null
      //   402: invokeinterface 1085 7 0
      //   407: astore 11
      //   409: aload 11
      //   411: ifnonnull +107 -> 518
      //   414: aload_0
      //   415: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   418: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   421: ifnull +116 -> 537
      //   424: aload_0
      //   425: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   428: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   431: aload_0
      //   432: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   435: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   438: ldc_w 1087
      //   441: iconst_3
      //   442: bipush 67
      //   444: invokestatic 338	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   447: invokeinterface 323 3 0
      //   452: iconst_0
      //   453: istore 13
      //   455: goto +85 -> 540
      //   458: aload 12
      //   460: invokeinterface 1034 1 0
      //   465: checkcast 84	cn/com/fmsh/tsm/business/bean/BusinessOrder
      //   468: astore 14
      //   470: aload 14
      //   472: ifnull +30 -> 502
      //   475: new 90	cn/com/fmsh/nfcos/client/service/xm/NfcosBusinessOrder
      //   478: dup
      //   479: invokespecial 256	cn/com/fmsh/nfcos/client/service/xm/NfcosBusinessOrder:<init>	()V
      //   482: astore 15
      //   484: aload_0
      //   485: aload 15
      //   487: aload 14
      //   489: invokespecial 258	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosBusinessOrder;Lcn/com/fmsh/tsm/business/bean/BusinessOrder;)V
      //   492: aload 6
      //   494: aload 15
      //   496: invokeinterface 264 2 0
      //   501: pop
      //   502: aload 12
      //   504: invokeinterface 1041 1 0
      //   509: ifne -51 -> 458
      //   512: iconst_1
      //   513: istore 13
      //   515: goto +25 -> 540
      //   518: aload 11
      //   520: invokeinterface 1051 1 0
      //   525: astore 12
      //   527: goto -25 -> 502
      //   530: bipush 99
      //   532: istore 10
      //   534: goto -488 -> 46
      //   537: iconst_0
      //   538: istore 13
      //   540: iload 13
      //   542: ifeq -12 -> 530
      //   545: iconst_0
      //   546: istore 10
      //   548: goto -502 -> 46
      //
      // Exception table:
      //   from	to	target	type
      //   381	527	49	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int queryMainOrder(byte[] paramArrayOfByte, NfcosMainOrder paramNfcosMainOrder)
    {
      int i = 9112;
      if (paramNfcosMainOrder == null)
      {
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), CRCUtil.valueOf(3, "乢诨匎讪绛俯恰柵诣斤ｏ佴儠皒涏恷轴伉宲谽乷穤"));
        return i;
      }
      while (true)
      {
        try
        {
          a(paramNfcosMainOrder, localMainOrder);
          j = 1;
          if (j == 0)
            continue;
          i = 0;
          break;
          i = 99;
          break;
          if ((paramArrayOfByte != null) && (paramArrayOfByte.length >= 1))
            continue;
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            break;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Long.concat("丧讥升读纎侲怱枬讶斩＆伵入让千罗去斷攊", 292));
        }
        catch (BusinessException localBusinessException)
        {
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Utils.copyValueOf(1, 122, "丷认单讼纒侯性枧设斠冪珺彆带\"") + Util4Java.getExceptionInfo(localBusinessException));
          if (localBusinessException.getErrorMsg() == null)
            continue;
          i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
          break;
          MainOrder localMainOrder = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().queryMainOrder(paramArrayOfByte);
          if (localMainOrder != null)
            continue;
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            break label286;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("丸讹卆训纅侺恼柮计断｟柮诡终柏乱穹", 2, 24));
          j = 0;
          continue;
          i = 99;
        }
        break;
        label286: int j = 0;
      }
    }

    // ERROR //
    public int queryMainOrders(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, java.util.List<NfcosMainOrder> paramList)
      throws RemoteException
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 6
      //   3: aload 5
      //   5: ifnonnull +49 -> 54
      //   8: aload_0
      //   9: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   12: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   15: ifnull +31 -> 46
      //   18: aload_0
      //   19: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   22: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   25: aload_0
      //   26: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   29: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   32: ldc_w 1104
      //   35: iconst_5
      //   36: bipush 38
      //   38: invokestatic 472	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
      //   41: invokeinterface 323 3 0
      //   46: sipush 9112
      //   49: istore 6
      //   51: iload 6
      //   53: ireturn
      //   54: iload_3
      //   55: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   58: astore 7
      //   60: aload 7
      //   62: ifnonnull +368 -> 430
      //   65: aload_0
      //   66: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   69: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   72: ifnull +31 -> 103
      //   75: aload_0
      //   76: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   79: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   82: aload_0
      //   83: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   86: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   89: sipush 158
      //   92: ldc_w 1106
      //   95: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   98: invokeinterface 323 3 0
      //   103: sipush 9112
      //   106: istore 6
      //   108: goto -57 -> 51
      //   111: bipush 99
      //   113: istore 6
      //   115: goto -64 -> 51
      //   118: bipush 99
      //   120: istore 6
      //   122: goto -71 -> 51
      //   125: astore 15
      //   127: aload_0
      //   128: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   131: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   134: ifnull +49 -> 183
      //   137: aload_0
      //   138: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   141: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   144: aload_0
      //   145: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   148: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   151: new 385	java/lang/StringBuilder
      //   154: dup
      //   155: iconst_2
      //   156: bipush 22
      //   158: ldc_w 1108
      //   161: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   164: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   167: aload 15
      //   169: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   172: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   175: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   178: invokeinterface 323 3 0
      //   183: aload 15
      //   185: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   188: ifnull -70 -> 118
      //   191: aload_0
      //   192: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   195: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   198: aload 15
      //   200: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   203: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   206: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   209: istore 6
      //   211: goto -160 -> 51
      //   214: aconst_null
      //   215: astore 8
      //   217: aload 4
      //   219: ifnull +37 -> 256
      //   222: aload 4
      //   224: arraylength
      //   225: ifle +31 -> 256
      //   228: new 243	java/util/ArrayList
      //   231: dup
      //   232: invokespecial 244	java/util/ArrayList:<init>	()V
      //   235: astore 16
      //   237: aload 4
      //   239: arraylength
      //   240: istore 17
      //   242: iconst_0
      //   243: istore 18
      //   245: iload 18
      //   247: iload 17
      //   249: if_icmplt +150 -> 399
      //   252: aload 16
      //   254: astore 8
      //   256: aload_0
      //   257: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   260: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   263: invokeinterface 367 1 0
      //   268: iload_1
      //   269: iload_2
      //   270: aload 8
      //   272: aload 7
      //   274: invokeinterface 1112 5 0
      //   279: astore 9
      //   281: aload 9
      //   283: ifnonnull +104 -> 387
      //   286: aload_0
      //   287: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   290: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   293: ifnull +191 -> 484
      //   296: aload_0
      //   297: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   300: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   303: aload_0
      //   304: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   307: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   310: ldc_w 1114
      //   313: sipush 232
      //   316: bipush 57
      //   318: invokestatic 338	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   321: invokeinterface 323 3 0
      //   326: iconst_0
      //   327: istore 11
      //   329: goto +158 -> 487
      //   332: aload 10
      //   334: invokeinterface 1034 1 0
      //   339: checkcast 210	cn/com/fmsh/tsm/business/bean/MainOrder
      //   342: astore 12
      //   344: new 214	cn/com/fmsh/nfcos/client/service/xm/NfcosMainOrder
      //   347: dup
      //   348: invokespecial 1115	cn/com/fmsh/nfcos/client/service/xm/NfcosMainOrder:<init>	()V
      //   351: astore 13
      //   353: aload_0
      //   354: aload 13
      //   356: aload 12
      //   358: invokespecial 375	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosMainOrder;Lcn/com/fmsh/tsm/business/bean/MainOrder;)V
      //   361: aload 5
      //   363: aload 13
      //   365: invokeinterface 264 2 0
      //   370: pop
      //   371: aload 10
      //   373: invokeinterface 1041 1 0
      //   378: ifne -46 -> 332
      //   381: iconst_1
      //   382: istore 11
      //   384: goto +103 -> 487
      //   387: aload 9
      //   389: invokeinterface 1051 1 0
      //   394: astore 10
      //   396: goto -25 -> 371
      //   399: aload 4
      //   401: iload 18
      //   403: iaload
      //   404: invokestatic 1079	cn/com/fmsh/tsm/business/enums/EnumOrderStatus:getOrderStatus4ID	(I)Lcn/com/fmsh/tsm/business/enums/EnumOrderStatus;
      //   407: astore 19
      //   409: aload 19
      //   411: ifnull +13 -> 424
      //   414: aload 16
      //   416: aload 19
      //   418: invokeinterface 264 2 0
      //   423: pop
      //   424: iinc 18 1
      //   427: goto -182 -> 245
      //   430: iload_1
      //   431: iflt +7 -> 438
      //   434: iload_2
      //   435: ifge -221 -> 214
      //   438: aload_0
      //   439: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   442: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   445: ifnull +31 -> 476
      //   448: aload_0
      //   449: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   452: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   455: aload_0
      //   456: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   459: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   462: iconst_1
      //   463: bipush 68
      //   465: ldc_w 1117
      //   468: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   471: invokeinterface 323 3 0
      //   476: sipush 9112
      //   479: istore 6
      //   481: goto -430 -> 51
      //   484: iconst_0
      //   485: istore 11
      //   487: iload 11
      //   489: ifeq -378 -> 111
      //   492: goto -441 -> 51
      //
      // Exception table:
      //   from	to	target	type
      //   256	396	125	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    // ERROR //
    public int queryPayOrder(byte[] paramArrayOfByte, NfcosPayOrder paramNfcosPayOrder)
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore_3
      //   4: aload_2
      //   5: ifnonnull +131 -> 136
      //   8: aload_0
      //   9: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   12: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   15: ifnull +31 -> 46
      //   18: aload_0
      //   19: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   22: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   25: aload_0
      //   26: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   29: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   32: ldc_w 1121
      //   35: iconst_4
      //   36: bipush 112
      //   38: invokestatic 331	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   41: invokeinterface 323 3 0
      //   46: iload_3
      //   47: ireturn
      //   48: astore 6
      //   50: aload_0
      //   51: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   54: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   57: ifnull +49 -> 106
      //   60: aload_0
      //   61: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   64: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   67: aload_0
      //   68: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   71: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   74: new 385	java/lang/StringBuilder
      //   77: dup
      //   78: ldc_w 1123
      //   81: sipush 158
      //   84: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   87: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   90: aload 6
      //   92: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   95: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   98: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   101: invokeinterface 323 3 0
      //   106: aload 6
      //   108: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   111: ifnull +166 -> 277
      //   114: aload_0
      //   115: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   118: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   121: aload 6
      //   123: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   126: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   129: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   132: istore_3
      //   133: goto -87 -> 46
      //   136: aload_1
      //   137: ifnull +9 -> 146
      //   140: aload_1
      //   141: arraylength
      //   142: iconst_1
      //   143: if_icmpge +46 -> 189
      //   146: aload_0
      //   147: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   150: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   153: ifnull -107 -> 46
      //   156: aload_0
      //   157: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   160: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   163: aload_0
      //   164: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   167: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   170: ldc_w 1125
      //   173: sipush 134
      //   176: bipush 126
      //   178: invokestatic 331	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   181: invokeinterface 323 3 0
      //   186: goto -140 -> 46
      //   189: aload_0
      //   190: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   193: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   196: invokeinterface 367 1 0
      //   201: aload_1
      //   202: invokeinterface 1128 2 0
      //   207: astore 4
      //   209: aload 4
      //   211: ifnonnull +47 -> 258
      //   214: aload_0
      //   215: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   218: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   221: ifnull +62 -> 283
      //   224: aload_0
      //   225: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   228: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   231: aload_0
      //   232: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   235: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   238: ldc_w 1130
      //   241: iconst_3
      //   242: bipush 123
      //   244: invokestatic 472	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
      //   247: invokeinterface 323 3 0
      //   252: iconst_0
      //   253: istore 5
      //   255: goto +31 -> 286
      //   258: aload_0
      //   259: aload_2
      //   260: aload 4
      //   262: invokespecial 270	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosPayOrder;Lcn/com/fmsh/tsm/business/bean/PayOrder;)V
      //   265: iconst_1
      //   266: istore 5
      //   268: goto +18 -> 286
      //   271: bipush 99
      //   273: istore_3
      //   274: goto -228 -> 46
      //   277: bipush 99
      //   279: istore_3
      //   280: goto -234 -> 46
      //   283: iconst_0
      //   284: istore 5
      //   286: iload 5
      //   288: ifeq -17 -> 271
      //   291: iconst_0
      //   292: istore_3
      //   293: goto -247 -> 46
      //
      // Exception table:
      //   from	to	target	type
      //   189	265	48	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int queryPreDeposit(int paramInt, PreDepositInfo paramPreDepositInfo)
      throws RemoteException
    {
      int i = 9112;
      if (paramPreDepositInfo == null)
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Long.concat("食罨釀俽恨柷诿斾｟佾儬盐淗恥轨体寲豷乻稶", 3));
      EnumCardAppType localEnumCardAppType;
      while (true)
      {
        return i;
        localEnumCardAppType = EnumCardAppType.instance(paramInt);
        if (localEnumCardAppType != null)
          break;
        if (NfcosService4xm.b(NfcosService4xm.this) == null)
          continue;
        NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Long.concat("食罨釀俽恨柷诿斾｟佾儬匵盛籱垞无敃", 3));
      }
      while (true)
      {
        try
        {
          paramPreDepositInfo.total = localPreDepositInfo.getTotal();
          paramPreDepositInfo.blance = localPreDepositInfo.getBlance();
          j = 1;
          if (j == 0)
            break label215;
          i = 0;
        }
        catch (BusinessException localBusinessException)
        {
          if (NfcosService4xm.b(NfcosService4xm.this) == null)
            continue;
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), Util4Java.toString("颀罾醍俩恻枥诮凢珴弒帤r", 5, 44) + Util4Java.getExceptionInfo(localBusinessException));
          if (localBusinessException.getErrorMsg() == null)
            break label289;
        }
        i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
        break;
        label215: i = 99;
        break;
        cn.com.fmsh.tsm.business.bean.PreDepositInfo localPreDepositInfo = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().queryPreDeposit(localEnumCardAppType);
        if (localPreDepositInfo != null)
          continue;
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
        {
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Int.lastIndexOf(2, "飑缸醆侹怶枿讹斪ｑ枻讽结枝丸穹"));
          j = 0;
          continue;
          label289: i = 99;
          break;
        }
        int j = 0;
      }
    }

    // ERROR //
    public int queryVersion(VersionInfo paramVersionInfo)
      throws RemoteException
    {
      // Byte code:
      //   0: bipush 99
      //   2: istore_2
      //   3: aload_1
      //   4: ifnonnull +131 -> 135
      //   7: aload_0
      //   8: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   11: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   14: ifnull +29 -> 43
      //   17: aload_0
      //   18: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   21: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   24: aload_0
      //   25: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   28: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   31: iconst_2
      //   32: ldc_w 1159
      //   35: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   38: invokeinterface 323 3 0
      //   43: sipush 9112
      //   46: istore_2
      //   47: iload_2
      //   48: ireturn
      //   49: astore 4
      //   51: aload_0
      //   52: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   55: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   58: ifnull +47 -> 105
      //   61: aload_0
      //   62: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   65: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   68: aload_0
      //   69: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   72: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   75: new 385	java/lang/StringBuilder
      //   78: dup
      //   79: iconst_2
      //   80: ldc_w 1161
      //   83: invokestatic 436	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
      //   86: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   89: aload 4
      //   91: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   94: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   97: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   100: invokeinterface 323 3 0
      //   105: aload 4
      //   107: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   110: ifnull -63 -> 47
      //   113: aload_0
      //   114: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   117: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   120: aload 4
      //   122: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   125: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   128: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   131: istore_2
      //   132: goto -85 -> 47
      //   135: aload_0
      //   136: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   139: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   142: invokeinterface 367 1 0
      //   147: invokeinterface 1164 1 0
      //   152: astore_3
      //   153: aload_3
      //   154: ifnonnull +45 -> 199
      //   157: aload_0
      //   158: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   161: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   164: ifnull -117 -> 47
      //   167: aload_0
      //   168: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   171: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   174: aload_0
      //   175: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   178: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   181: ldc_w 1166
      //   184: bipush 122
      //   186: bipush 97
      //   188: invokestatic 331	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   191: invokeinterface 323 3 0
      //   196: goto -149 -> 47
      //   199: aload_1
      //   200: aload_3
      //   201: invokevirtual 1171	cn/com/fmsh/tsm/business/bean/VersionInfo:getViersion	()Ljava/lang/String;
      //   204: putfield 1176	cn/com/fmsh/nfcos/client/service/xm/VersionInfo:version	Ljava/lang/String;
      //   207: aload_1
      //   208: aload_3
      //   209: invokevirtual 1179	cn/com/fmsh/tsm/business/bean/VersionInfo:isUpdate	()Z
      //   212: putfield 1182	cn/com/fmsh/nfcos/client/service/xm/VersionInfo:isUpdate	Z
      //   215: aload_1
      //   216: aload_3
      //   217: invokevirtual 1185	cn/com/fmsh/tsm/business/bean/VersionInfo:getUrl	()Ljava/lang/String;
      //   220: putfield 1188	cn/com/fmsh/nfcos/client/service/xm/VersionInfo:url	Ljava/lang/String;
      //   223: iconst_0
      //   224: istore_2
      //   225: goto -178 -> 47
      //
      // Exception table:
      //   from	to	target	type
      //   135	153	49	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int recharge(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
      throws RemoteException
    {
      int i = 99;
      if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length < 1))
      {
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Int.lastIndexOf(5, "乒洮仾遁匽儘偢斩，讣南缕右丿穼"));
        i = 9112;
      }
      while (true)
      {
        return i;
        do
        {
          try
          {
            boolean bool = NfcosService4xm.g(NfcosService4xm.this).getCardAppTrade().remoteRecharge(paramArrayOfByte1, paramArrayOfByte2);
            if (!bool)
              break;
            i = 0;
          }
          catch (BusinessException localBusinessException)
          {
            if (NfcosService4xm.b(NfcosService4xm.this) != null)
              NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_Utils.copyValueOf(2, 34, "甥戸便敪寓砖彛幣'") + Util4Java.getExceptionInfo(localBusinessException));
          }
          if (localBusinessException.getErrorMsg() == null)
            break;
          i = NfcosService4xm.i(NfcosService4xm.this).getCode(localBusinessException.getErrorMsg().getId());
          break;
        }
        while ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length >= 1));
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_CN.subSequence("业浨亪逇匭儞偶斯＄卶皂庁畬廜剕厦为穵", 2));
        i = 9112;
      }
    }

    // ERROR //
    public int register(UserInfo paramUserInfo)
      throws RemoteException
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore_2
      //   4: aload_1
      //   5: ifnonnull +190 -> 195
      //   8: aload_0
      //   9: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   12: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   15: ifnull +31 -> 46
      //   18: aload_0
      //   19: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   22: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   25: aload_0
      //   26: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   29: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   32: ldc_w 1198
      //   35: sipush 148
      //   38: invokestatic 464	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   41: invokeinterface 323 3 0
      //   46: iload_2
      //   47: ireturn
      //   48: astore 5
      //   50: aload_0
      //   51: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   54: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   57: ifnull +47 -> 104
      //   60: aload_0
      //   61: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   64: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   67: aload_0
      //   68: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   71: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   74: new 385	java/lang/StringBuilder
      //   77: dup
      //   78: iconst_5
      //   79: ldc_w 1200
      //   82: invokestatic 383	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   85: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   88: aload 5
      //   90: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   93: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   96: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   99: invokeinterface 323 3 0
      //   104: aload 5
      //   106: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   109: ifnull +143 -> 252
      //   112: aload_0
      //   113: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   116: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   119: aload 5
      //   121: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   124: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   127: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   130: istore_2
      //   131: goto -85 -> 46
      //   134: aload_1
      //   135: getfield 967	cn/com/fmsh/nfcos/client/service/xm/UserInfo:password	Ljava/lang/String;
      //   138: ifnull +14 -> 152
      //   141: aload_1
      //   142: getfield 967	cn/com/fmsh/nfcos/client/service/xm/UserInfo:password	Ljava/lang/String;
      //   145: invokevirtual 429	java/lang/String:length	()I
      //   148: iconst_1
      //   149: if_icmpge +109 -> 258
      //   152: aload_0
      //   153: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   156: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   159: ifnull -113 -> 46
      //   162: aload_0
      //   163: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   166: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   169: aload_0
      //   170: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   173: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   176: sipush 216
      //   179: bipush 112
      //   181: ldc_w 1202
      //   184: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   187: invokeinterface 323 3 0
      //   192: goto -146 -> 46
      //   195: aload_1
      //   196: getfield 956	cn/com/fmsh/nfcos/client/service/xm/UserInfo:username	Ljava/lang/String;
      //   199: ifnull +14 -> 213
      //   202: aload_1
      //   203: getfield 956	cn/com/fmsh/nfcos/client/service/xm/UserInfo:username	Ljava/lang/String;
      //   206: invokevirtual 429	java/lang/String:length	()I
      //   209: iconst_1
      //   210: if_icmpge -76 -> 134
      //   213: aload_0
      //   214: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   217: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   220: ifnull -174 -> 46
      //   223: aload_0
      //   224: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   227: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   230: aload_0
      //   231: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   234: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   237: ldc_w 1204
      //   240: iconst_5
      //   241: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   244: invokeinterface 323 3 0
      //   249: goto -203 -> 46
      //   252: bipush 99
      //   254: istore_2
      //   255: goto -209 -> 46
      //   258: new 960	cn/com/fmsh/tsm/business/bean/UserInfo
      //   261: dup
      //   262: invokespecial 961	cn/com/fmsh/tsm/business/bean/UserInfo:<init>	()V
      //   265: astore_3
      //   266: aload_3
      //   267: aload_1
      //   268: getfield 956	cn/com/fmsh/nfcos/client/service/xm/UserInfo:username	Ljava/lang/String;
      //   271: invokevirtual 964	cn/com/fmsh/tsm/business/bean/UserInfo:setUserName	(Ljava/lang/String;)V
      //   274: aload_3
      //   275: aload_1
      //   276: getfield 967	cn/com/fmsh/nfcos/client/service/xm/UserInfo:password	Ljava/lang/String;
      //   279: invokevirtual 970	cn/com/fmsh/tsm/business/bean/UserInfo:setPassword	(Ljava/lang/String;)V
      //   282: aload_3
      //   283: iconst_2
      //   284: invokevirtual 973	cn/com/fmsh/tsm/business/bean/UserInfo:setUserType	(I)V
      //   287: aload_0
      //   288: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   291: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   294: invokeinterface 367 1 0
      //   299: aload_3
      //   300: invokeinterface 1207 2 0
      //   305: istore 4
      //   307: iload 4
      //   309: istore_2
      //   310: goto -264 -> 46
      //
      // Exception table:
      //   from	to	target	type
      //   287	307	48	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    // ERROR //
    public int setApp(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, String paramString, int paramInt2)
    {
      // Byte code:
      //   0: sipush 9112
      //   3: istore 6
      //   5: iload_2
      //   6: invokestatic 342	cn/com/fmsh/tsm/business/enums/EnumCardAppType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumCardAppType;
      //   9: astore 7
      //   11: aload 7
      //   13: ifnonnull +42 -> 55
      //   16: aload_0
      //   17: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   20: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   23: ifnull +29 -> 52
      //   26: aload_0
      //   27: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   30: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   33: aload_0
      //   34: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   37: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   40: ldc_w 1211
      //   43: iconst_4
      //   44: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   47: invokeinterface 323 3 0
      //   52: iload 6
      //   54: ireturn
      //   55: aload_3
      //   56: ifnull +9 -> 65
      //   59: aload_3
      //   60: arraylength
      //   61: iconst_1
      //   62: if_icmpge +243 -> 305
      //   65: aload_0
      //   66: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   69: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   72: ifnull -20 -> 52
      //   75: aload_0
      //   76: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   79: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   82: aload_0
      //   83: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   86: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   89: iconst_3
      //   90: bipush 62
      //   92: ldc_w 1213
      //   95: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   98: invokeinterface 323 3 0
      //   103: goto -51 -> 52
      //   106: astore 10
      //   108: aload_0
      //   109: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   112: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   115: ifnull +51 -> 166
      //   118: aload_0
      //   119: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   122: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   125: aload_0
      //   126: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   129: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   132: new 385	java/lang/StringBuilder
      //   135: dup
      //   136: ldc_w 1215
      //   139: sipush 230
      //   142: bipush 43
      //   144: invokestatic 331	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   147: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   150: aload 10
      //   152: invokestatic 402	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   155: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   158: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   161: invokeinterface 323 3 0
      //   166: aload 10
      //   168: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   171: ifnull +230 -> 401
      //   174: aload_0
      //   175: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   178: invokestatic 417	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:i	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/core/ErrorCodeHandler;
      //   181: aload 10
      //   183: invokevirtual 413	cn/com/fmsh/tsm/business/exception/BusinessException:getErrorMsg	()Lcn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage;
      //   186: invokevirtual 421	cn/com/fmsh/tsm/business/exception/BusinessException$ErrorMessage:getId	()Ljava/lang/String;
      //   189: invokevirtual 426	cn/com/fmsh/tsm/business/core/ErrorCodeHandler:getCode	(Ljava/lang/String;)I
      //   192: istore 6
      //   194: goto -142 -> 52
      //   197: iload 5
      //   199: invokestatic 1218	cn/com/fmsh/tsm/business/enums/EnumAppManageOperateType:instance	(I)Lcn/com/fmsh/tsm/business/enums/EnumAppManageOperateType;
      //   202: astore 8
      //   204: aload 8
      //   206: ifnonnull +42 -> 248
      //   209: aload_0
      //   210: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   213: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   216: ifnull -164 -> 52
      //   219: aload_0
      //   220: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   223: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   226: aload_0
      //   227: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   230: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   233: ldc_w 1220
      //   236: iconst_1
      //   237: invokestatic 393	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   240: invokeinterface 323 3 0
      //   245: goto -193 -> 52
      //   248: aload 8
      //   250: getstatic 569	cn/com/fmsh/tsm/business/enums/EnumAppManageOperateType:APP_LOCK	Lcn/com/fmsh/tsm/business/enums/EnumAppManageOperateType;
      //   253: if_acmpeq +109 -> 362
      //   256: aload 8
      //   258: getstatic 1009	cn/com/fmsh/tsm/business/enums/EnumAppManageOperateType:APP_UNLOCK	Lcn/com/fmsh/tsm/business/enums/EnumAppManageOperateType;
      //   261: if_acmpeq +101 -> 362
      //   264: aload_0
      //   265: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   268: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   271: ifnull -219 -> 52
      //   274: aload_0
      //   275: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   278: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   281: aload_0
      //   282: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   285: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   288: iconst_5
      //   289: bipush 27
      //   291: ldc_w 1222
      //   294: invokestatic 317	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
      //   297: invokeinterface 323 3 0
      //   302: goto -250 -> 52
      //   305: aload 4
      //   307: ifnull +12 -> 319
      //   310: aload 4
      //   312: invokevirtual 429	java/lang/String:length	()I
      //   315: iconst_1
      //   316: if_icmpge -119 -> 197
      //   319: aload_0
      //   320: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   323: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   326: ifnull -274 -> 52
      //   329: aload_0
      //   330: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   333: invokestatic 306	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:b	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/util/log/FMLog;
      //   336: aload_0
      //   337: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   340: invokestatic 309	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:a	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Ljava/lang/String;
      //   343: sipush 178
      //   346: bipush 103
      //   348: ldc_w 1224
      //   351: invokestatic 455	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   354: invokeinterface 323 3 0
      //   359: goto -307 -> 52
      //   362: aload_0
      //   363: getfield 13	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm$AppManger:a	Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;
      //   366: invokestatic 361	cn/com/fmsh/nfcos/client/service/xm/NfcosService4xm:g	(Lcn/com/fmsh/nfcos/client/service/xm/NfcosService4xm;)Lcn/com/fmsh/tsm/business/BusinessManager;
      //   369: invokeinterface 525 1 0
      //   374: aload_1
      //   375: aload 7
      //   377: aload_3
      //   378: aload 4
      //   380: aload 8
      //   382: invokeinterface 1226 6 0
      //   387: istore 9
      //   389: iload 9
      //   391: ifne +17 -> 408
      //   394: bipush 99
      //   396: istore 6
      //   398: goto -346 -> 52
      //   401: bipush 99
      //   403: istore 6
      //   405: goto -353 -> 52
      //   408: iconst_0
      //   409: istore 6
      //   411: goto -359 -> 52
      //
      // Exception table:
      //   from	to	target	type
      //   362	389	106	cn/com/fmsh/tsm/business/exception/BusinessException
    }

    public int switchMode2NFC(Tag paramTag)
      throws RemoteException
    {
      int i = 0;
      try
      {
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).debug(NfcosService4xm.a(NfcosService4xm.this), FM_CN.subSequence("`ux4,6m\021d~l8uv\013\022@</>1", 5));
        NfcosService4xm.a(NfcosService4xm.this, 1);
        NFCApduHandler localNFCApduHandler = new NFCApduHandler();
        if (localNFCApduHandler.setTag(paramTag))
        {
          NfcosService4xm.g(NfcosService4xm.this).setApduHandler(localNFCApduHandler);
        }
        else
        {
          if (NfcosService4xm.b(NfcosService4xm.this) != null)
            NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), Util4Java.toString("仧WV\004诡閻卭三庎畹斾s6L\024\037\007复理噿诰缫\bRM奰赽", 3, 23));
          i = 9110;
        }
      }
      catch (a9 locala9)
      {
      }
      return i;
    }

    public int switchMode2OMA(int paramInt1, int paramInt2)
      throws RemoteException
    {
      if (NfcosService4xm.b(NfcosService4xm.this) != null)
        NfcosService4xm.b(NfcosService4xm.this).debug(NfcosService4xm.a(NfcosService4xm.this), FM_Long.concat("op{i+;~\004;;o52+Y\f\rylcv", 36));
      EnumCardAppType localEnumCardAppType = EnumCardAppType.instance(paramInt2);
      int i;
      if (localEnumCardAppType == null)
      {
        if (NfcosService4xm.b(NfcosService4xm.this) != null)
          NfcosService4xm.b(NfcosService4xm.this).warn(NfcosService4xm.a(NfcosService4xm.this), FM_CN.subSequence("刕捣删P>8\"\026e{a{#斣ｈ佳內皕卡丅床略簧埀斺敁", 4));
        i = 9112;
      }
      byte[] arrayOfByte;
      while (true)
      {
        return i;
        if (localEnumCardAppType != EnumCardAppType.CARD_APP_TYPE_SH)
          break label347;
        arrayOfByte = NfcosService4xm.STPC_AID;
        label102: if (NfcosService4xm.c(NfcosService4xm.this) == null)
          NfcosService4xm.a(NfcosService4xm.this, new OpenMobileApduHandler(NfcosService4xm.d(NfcosService4xm.this), NfcosService4xm.this, arrayOfByte, paramInt1));
        if (NfcosService4xm.e(NfcosService4xm.this) != 0)
          break;
        if (NfcosService4xm.c(NfcosService4xm.this) != null)
        {
          if (!Arrays.equals(arrayOfByte, NfcosService4xm.c(NfcosService4xm.this).getLastOpenAid()))
            break label355;
          NfcosService4xm.b(NfcosService4xm.this).debug(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("6l7!f.+j&x&}q\"/)4mr/hx", 246, 46));
          i = 0;
          continue;
        }
        NfcosService4xm.b(NfcosService4xm.this).debug(NfcosService4xm.a(NfcosService4xm.this), FM_Bytes.startsWith("j6\"fD%)ea+\016`u'\033u{2;}kz2o=0*,m", 4, 97));
      }
      while (true)
      {
        if (NfcosService4xm.f(NfcosService4xm.this))
        {
          NfcosService4xm.g(NfcosService4xm.this).setApduHandler(NfcosService4xm.c(NfcosService4xm.this));
          if (NfcosService4xm.b(NfcosService4xm.this) != null)
            NfcosService4xm.b(NfcosService4xm.this).debug(NfcosService4xm.a(NfcosService4xm.this), FM_Utils.copyValueOf(176, 98, "刜捿副\016\016D"));
          i = 0;
          break;
          NfcosService4xm.a(NfcosService4xm.this, 0);
          continue;
        }
        try
        {
          Thread.sleep(100L);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
        continue;
        label347: arrayOfByte = NfcosService4xm.LINGNAN_PASS_AID;
        break label102;
        label355: NfcosService4xm.b(NfcosService4xm.this).debug(NfcosService4xm.a(NfcosService4xm.this), FM_Int.lastIndexOf(348, ".95r:'u88,y):18~\023!26\f4 (\006!-p") + FM_Bytes.bytesToHexString(NfcosService4xm.c(NfcosService4xm.this).getLastOpenAid()) + FM_Utils.copyValueOf(90, 54, "e;0nyi") + FM_Bytes.bytesToHexString(arrayOfByte));
        NfcosService4xm.c(NfcosService4xm.this).setAid(arrayOfByte);
      }
    }
  }

  public class IssuerProcessHandlerImpl
    implements IssuerProcessHandler
  {
    public IssuerProcessHandlerImpl()
    {
    }

    public void handle(EnumIssueProcess paramEnumIssueProcess)
    {
      try
      {
        BroadCastParameter localBroadCastParameter = new BroadCastParameter();
        localBroadCastParameter.broadcastType = BroadCastType.ISSUER_PROCESS.getId();
        localBroadCastParameter.process = paramEnumIssueProcess.getId();
        NfcosService4xm.this.sendBroadCast(localBroadCastParameter);
        label34: return;
      }
      catch (a9 locala9)
      {
        break label34;
      }
    }
  }

  private class a
    implements SEService.CallBack
  {
    public void serviceConnected(SEService paramSEService)
    {
      try
      {
        Log.d(NfcosService4xm.a(this.a), BCCUtil.endsWith("n&n.ug0v 2vhn7uj8e>0&5a*|-w})", 290, 53));
        NfcosService4xm.a(this.a, true);
        label29: return;
      }
      catch (a9 locala9)
      {
        break label29;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.xm.NfcosService4xm
 * JD-Core Version:    0.6.0
 */