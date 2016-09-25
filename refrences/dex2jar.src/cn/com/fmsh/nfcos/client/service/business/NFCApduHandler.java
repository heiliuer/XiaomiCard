package cn.com.fmsh.nfcos.client.service.business;

import a1;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.util.Log;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.ApduHandler.ApduHandlerType;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import java.io.IOException;

public class NFCApduHandler
  implements ApduHandler
{
  public void close()
  {
    Log.e(this.a, CRCUtil.valueOf(6, "rcpanugc$6*8;|$5\""));
    if (this.c != null);
    try
    {
      this.c.close();
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public boolean connect()
  {
    int i = 0;
    if (this.c == null)
      if (this.b != null)
        this.b.warn(this.a, FM_Long.concat("\035wvh夌琕噶��'0\0160p+unby'.,斵b=a:lc;($0+", 4));
    while (true)
    {
      return i;
      if (this.c.isConnected())
        close();
      if (this.b != null)
        this.b.warn(this.a, FM_Int.lastIndexOf(4, "\026(=/奟瑚嘵\027,oEgs$fiifli\"# "));
      try
      {
        this.c.connect();
        i = 1;
      }
      catch (IOException localIOException)
      {
      }
      if (this.b == null)
        continue;
      this.b.warn(this.a, FM_Utils.copyValueOf(1, 32, "M|(9夈琊嘤\005c\b)|,/#bb)/x旺冶珼弎帴v") + Util4Java.getExceptionInfo(localIOException));
    }
  }

  public ApduHandler.ApduHandlerType getApduHandlerType()
  {
    return ApduHandler.ApduHandlerType.NFC;
  }

  public boolean isConnect()
  {
    int i = 0;
    try
    {
      if (this.c != null)
      {
        boolean bool = this.c.isConnected();
        i = bool;
      }
    }
    catch (a1 locala1)
    {
    }
    return i;
  }

  public boolean open(byte[] paramArrayOfByte)
  {
    return true;
  }

  public boolean setTag(Tag paramTag)
  {
    int i = 1;
    if (paramTag == null);
    try
    {
      if (this.b != null)
      {
        this.b.warn(this.a, BCCUtil.endsWith("@%-(奕瑃噱讳罯Ah:斧ｉ\r,&寬豨丧穫", 2, 116));
        break label88;
        this.c = IsoDep.get(paramTag);
        if (this.c == null)
        {
          if (this.b != null)
            this.b.warn(this.a, FM_Int.lastIndexOf(344, "\n<);奋瑖嘹诬缽��41斡ｔ\r;<盘簦埕乒景\b1,�� 6"));
          i = 0;
        }
      }
    }
    catch (a1 locala1)
    {
      i = 0;
    }
    label88: return i;
  }

  // ERROR //
  public byte[] transceive(byte[] paramArrayOfByte)
    throws cn.com.fmsh.script.exception.FMScriptHandleException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 55	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:c	Landroid/nfc/tech/IsoDep;
    //   4: ifnonnull +9 -> 13
    //   7: aconst_null
    //   8: astore 4
    //   10: aload 4
    //   12: areturn
    //   13: aload_0
    //   14: getfield 55	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:c	Landroid/nfc/tech/IsoDep;
    //   17: invokevirtual 81	android/nfc/tech/IsoDep:isConnected	()Z
    //   20: ifne +10 -> 30
    //   23: aload_0
    //   24: getfield 55	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:c	Landroid/nfc/tech/IsoDep;
    //   27: invokevirtual 91	android/nfc/tech/IsoDep:connect	()V
    //   30: aload_0
    //   31: getfield 36	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:b	Lcn/com/fmsh/util/log/FMLog;
    //   34: ifnull +41 -> 75
    //   37: aload_0
    //   38: getfield 36	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:b	Lcn/com/fmsh/util/log/FMLog;
    //   41: aload_0
    //   42: getfield 24	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:a	Ljava/lang/String;
    //   45: new 93	java/lang/StringBuilder
    //   48: dup
    //   49: ldc 152
    //   51: sipush 182
    //   54: invokestatic 157	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   57: invokespecial 104	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   60: aload_1
    //   61: invokestatic 163	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   64: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokeinterface 166 3 0
    //   75: aload_0
    //   76: getfield 55	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:c	Landroid/nfc/tech/IsoDep;
    //   79: aload_1
    //   80: invokevirtual 168	android/nfc/tech/IsoDep:transceive	([B)[B
    //   83: astore_3
    //   84: aload_3
    //   85: astore 4
    //   87: aload_0
    //   88: getfield 36	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:b	Lcn/com/fmsh/util/log/FMLog;
    //   91: ifnull -81 -> 10
    //   94: aload_0
    //   95: getfield 36	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:b	Lcn/com/fmsh/util/log/FMLog;
    //   98: aload_0
    //   99: getfield 24	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:a	Ljava/lang/String;
    //   102: new 93	java/lang/StringBuilder
    //   105: dup
    //   106: ldc 170
    //   108: sipush 222
    //   111: bipush 23
    //   113: invokestatic 140	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   116: invokespecial 104	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   119: aload 4
    //   121: invokestatic 163	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   124: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   130: invokeinterface 166 3 0
    //   135: goto -125 -> 10
    //   138: astore 5
    //   140: aload_0
    //   141: getfield 36	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:b	Lcn/com/fmsh/util/log/FMLog;
    //   144: ifnull +42 -> 186
    //   147: aload_0
    //   148: getfield 36	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:b	Lcn/com/fmsh/util/log/FMLog;
    //   151: aload_0
    //   152: getfield 24	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:a	Ljava/lang/String;
    //   155: new 93	java/lang/StringBuilder
    //   158: dup
    //   159: ldc 172
    //   161: iconst_4
    //   162: bipush 39
    //   164: invokestatic 174	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   167: invokespecial 104	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   170: aload 5
    //   172: invokestatic 110	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   175: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: invokeinterface 177 3 0
    //   186: new 150	cn/com/fmsh/script/exception/FMScriptHandleException
    //   189: dup
    //   190: aload 5
    //   192: invokevirtual 180	java/io/IOException:getMessage	()Ljava/lang/String;
    //   195: invokespecial 181	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   198: athrow
    //   199: astore_2
    //   200: aload_0
    //   201: getfield 36	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:b	Lcn/com/fmsh/util/log/FMLog;
    //   204: ifnull +39 -> 243
    //   207: aload_0
    //   208: getfield 36	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:b	Lcn/com/fmsh/util/log/FMLog;
    //   211: aload_0
    //   212: getfield 24	cn/com/fmsh/nfcos/client/service/business/NFCApduHandler:a	Ljava/lang/String;
    //   215: new 93	java/lang/StringBuilder
    //   218: dup
    //   219: iconst_3
    //   220: ldc 183
    //   222: invokestatic 47	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   225: invokespecial 104	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   228: aload_2
    //   229: invokestatic 110	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   232: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   238: invokeinterface 177 3 0
    //   243: new 150	cn/com/fmsh/script/exception/FMScriptHandleException
    //   246: dup
    //   247: aload_2
    //   248: invokevirtual 180	java/io/IOException:getMessage	()Ljava/lang/String;
    //   251: invokespecial 181	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   254: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   23	30	138	java/io/IOException
    //   75	84	199	java/io/IOException
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.business.NFCApduHandler
 * JD-Core Version:    0.6.0
 */