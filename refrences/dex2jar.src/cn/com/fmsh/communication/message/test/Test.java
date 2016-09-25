package cn.com.fmsh.communication.message.test;

import a_;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.message.IMessage;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.MessageHandleFactory;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Test
{
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      Test localTest = new Test();
      localTest.load();
      localTest.create();
      label16: return;
    }
    catch (a_ locala_)
    {
      break label16;
    }
  }

  public void create()
  {
    FM_Bytes.hexStringToBytes(FM_Utils.copyValueOf(236, 33, "o +k\031--m\031F\007\004EB\007\0077>z{8=~<!bc %\027e'+i\033+]m\037]vq13\006uD7\n\013=;{~<<bb  ff$$jj(Ydm(,s21pw65t{:9\tx=>"));
    try
    {
      IMessage localIMessage = this.a.createMessage(9000);
      ITag localITag = this.a.createTag(-75);
      localITag.addValue(new byte[1]);
      localIMessage.addTag(localITag);
      byte[] arrayOfByte = localIMessage.toBytes();
      System.out.println(FM_Bytes.bytesToHexString(arrayOfByte));
      return;
    }
    catch (FMCommunicationMessageException localFMCommunicationMessageException)
    {
      while (true)
        localFMCommunicationMessageException.printStackTrace();
    }
  }

  // ERROR //
  public void createMessageTest()
  {
    // Byte code:
    //   0: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
    //   3: ldc 87
    //   5: iconst_2
    //   6: bipush 100
    //   8: invokestatic 91	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   11: invokevirtual 81	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   14: bipush 58
    //   16: newarray byte
    //   18: astore_1
    //   19: aload_1
    //   20: iconst_0
    //   21: bipush 16
    //   23: bastore
    //   24: aload_1
    //   25: iconst_1
    //   26: bipush 33
    //   28: bastore
    //   29: aload_1
    //   30: iconst_2
    //   31: iconst_2
    //   32: bastore
    //   33: aload_1
    //   34: iconst_3
    //   35: bipush 20
    //   37: bastore
    //   38: aload_1
    //   39: bipush 8
    //   41: iconst_1
    //   42: bastore
    //   43: aload_1
    //   44: bipush 13
    //   46: iconst_1
    //   47: bastore
    //   48: aload_1
    //   49: bipush 18
    //   51: iconst_1
    //   52: bastore
    //   53: aload_1
    //   54: bipush 23
    //   56: iconst_1
    //   57: bastore
    //   58: aload_1
    //   59: bipush 24
    //   61: iconst_3
    //   62: bastore
    //   63: aload_1
    //   64: bipush 25
    //   66: bipush 32
    //   68: bastore
    //   69: aload_1
    //   70: bipush 26
    //   72: iconst_1
    //   73: bastore
    //   74: aload_1
    //   75: bipush 27
    //   77: iconst_2
    //   78: bastore
    //   79: aload_1
    //   80: bipush 28
    //   82: iconst_3
    //   83: bastore
    //   84: aload_1
    //   85: bipush 29
    //   87: iconst_4
    //   88: bastore
    //   89: aload_1
    //   90: bipush 30
    //   92: iconst_2
    //   93: bastore
    //   94: aload_1
    //   95: bipush 31
    //   97: iconst_2
    //   98: bastore
    //   99: aload_1
    //   100: bipush 32
    //   102: iconst_3
    //   103: bastore
    //   104: aload_1
    //   105: bipush 33
    //   107: iconst_4
    //   108: bastore
    //   109: aload_1
    //   110: bipush 34
    //   112: iconst_1
    //   113: bastore
    //   114: aload_1
    //   115: bipush 35
    //   117: iconst_2
    //   118: bastore
    //   119: aload_1
    //   120: bipush 36
    //   122: iconst_3
    //   123: bastore
    //   124: aload_1
    //   125: bipush 37
    //   127: iconst_4
    //   128: bastore
    //   129: aload_1
    //   130: bipush 38
    //   132: iconst_2
    //   133: bastore
    //   134: aload_1
    //   135: bipush 39
    //   137: iconst_2
    //   138: bastore
    //   139: aload_1
    //   140: bipush 40
    //   142: iconst_3
    //   143: bastore
    //   144: aload_1
    //   145: bipush 41
    //   147: iconst_4
    //   148: bastore
    //   149: aload_1
    //   150: bipush 42
    //   152: iconst_1
    //   153: bastore
    //   154: aload_1
    //   155: bipush 43
    //   157: iconst_2
    //   158: bastore
    //   159: aload_1
    //   160: bipush 44
    //   162: iconst_3
    //   163: bastore
    //   164: aload_1
    //   165: bipush 45
    //   167: iconst_4
    //   168: bastore
    //   169: aload_1
    //   170: bipush 46
    //   172: iconst_2
    //   173: bastore
    //   174: aload_1
    //   175: bipush 47
    //   177: iconst_2
    //   178: bastore
    //   179: aload_1
    //   180: bipush 48
    //   182: iconst_3
    //   183: bastore
    //   184: aload_1
    //   185: bipush 49
    //   187: iconst_4
    //   188: bastore
    //   189: aload_1
    //   190: bipush 50
    //   192: iconst_1
    //   193: bastore
    //   194: aload_1
    //   195: bipush 51
    //   197: iconst_2
    //   198: bastore
    //   199: aload_1
    //   200: bipush 52
    //   202: iconst_3
    //   203: bastore
    //   204: aload_1
    //   205: bipush 53
    //   207: iconst_4
    //   208: bastore
    //   209: aload_1
    //   210: bipush 54
    //   212: iconst_2
    //   213: bastore
    //   214: aload_1
    //   215: bipush 55
    //   217: iconst_2
    //   218: bastore
    //   219: aload_1
    //   220: bipush 56
    //   222: iconst_3
    //   223: bastore
    //   224: aload_1
    //   225: bipush 57
    //   227: iconst_4
    //   228: bastore
    //   229: aload_0
    //   230: getfield 12	cn/com/fmsh/communication/message/test/Test:a	Lcn/com/fmsh/communication/message/IMessageHandler;
    //   233: sipush 1021
    //   236: invokeinterface 45 2 0
    //   241: astore_2
    //   242: aload_0
    //   243: getfield 12	cn/com/fmsh/communication/message/test/Test:a	Lcn/com/fmsh/communication/message/IMessageHandler;
    //   246: iconst_2
    //   247: invokeinterface 49 2 0
    //   252: astore_3
    //   253: aload_0
    //   254: getfield 12	cn/com/fmsh/communication/message/test/Test:a	Lcn/com/fmsh/communication/message/IMessageHandler;
    //   257: iconst_3
    //   258: invokeinterface 49 2 0
    //   263: astore 4
    //   265: aload_3
    //   266: ldc 93
    //   268: iconst_1
    //   269: iconst_1
    //   270: invokestatic 91	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   273: invokeinterface 96 2 0
    //   278: pop
    //   279: aload 4
    //   281: ldc 98
    //   283: iconst_2
    //   284: bipush 123
    //   286: invokestatic 91	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   289: invokeinterface 96 2 0
    //   294: pop
    //   295: aload_3
    //   296: invokeinterface 102 1 0
    //   301: istore 6
    //   303: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
    //   306: new 104	java/lang/StringBuilder
    //   309: dup
    //   310: iconst_5
    //   311: ldc 106
    //   313: invokestatic 112	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   316: invokespecial 114	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   319: iload 6
    //   321: invokevirtual 118	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   324: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   327: invokevirtual 81	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   330: aload 4
    //   332: invokeinterface 102 1 0
    //   337: istore 7
    //   339: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
    //   342: new 104	java/lang/StringBuilder
    //   345: dup
    //   346: ldc 124
    //   348: iconst_4
    //   349: invokestatic 130	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   352: invokespecial 114	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   355: iload 7
    //   357: invokevirtual 118	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   360: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   363: invokevirtual 81	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   366: aload_2
    //   367: aload_3
    //   368: invokeinterface 61 2 0
    //   373: pop
    //   374: aload_2
    //   375: aload 4
    //   377: invokeinterface 61 2 0
    //   382: pop
    //   383: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
    //   386: aload_2
    //   387: invokeinterface 134 1 0
    //   392: invokevirtual 137	java/io/PrintStream:println	(I)V
    //   395: aload_2
    //   396: invokeinterface 140 1 0
    //   401: istore 9
    //   403: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
    //   406: new 104	java/lang/StringBuilder
    //   409: dup
    //   410: iconst_1
    //   411: ldc 142
    //   413: invokestatic 147	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   416: invokespecial 114	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   419: iload 9
    //   421: invokevirtual 150	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   424: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   427: invokevirtual 81	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   430: aload_2
    //   431: iconst_0
    //   432: invokeinterface 154 2 0
    //   437: astore 14
    //   439: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
    //   442: new 104	java/lang/StringBuilder
    //   445: dup
    //   446: ldc 156
    //   448: iconst_2
    //   449: invokestatic 161	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   452: invokespecial 114	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   455: aload 14
    //   457: invokeinterface 165 1 0
    //   462: invokevirtual 150	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   465: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   468: invokevirtual 81	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   471: aload_2
    //   472: invokeinterface 166 1 0
    //   477: istore 11
    //   479: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
    //   482: new 104	java/lang/StringBuilder
    //   485: dup
    //   486: iconst_5
    //   487: ldc 168
    //   489: invokestatic 112	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   492: invokespecial 114	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   495: iload 11
    //   497: invokevirtual 118	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   500: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   503: invokevirtual 81	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   506: aload_2
    //   507: invokeinterface 65 1 0
    //   512: astore 13
    //   514: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
    //   517: new 104	java/lang/StringBuilder
    //   520: dup
    //   521: ldc 170
    //   523: iconst_5
    //   524: bipush 81
    //   526: invokestatic 174	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   529: invokespecial 114	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   532: aload 13
    //   534: invokestatic 75	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   537: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   540: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   543: invokevirtual 81	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   546: return
    //   547: astore 8
    //   549: aload 8
    //   551: invokevirtual 84	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException:printStackTrace	()V
    //   554: goto -171 -> 383
    //   557: astore 5
    //   559: aload 5
    //   561: invokevirtual 84	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException:printStackTrace	()V
    //   564: goto -269 -> 295
    //   567: astore 10
    //   569: aload 10
    //   571: invokevirtual 84	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException:printStackTrace	()V
    //   574: goto -103 -> 471
    //   577: astore 12
    //   579: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
    //   582: aload 12
    //   584: invokestatic 181	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   587: invokevirtual 81	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   590: goto -44 -> 546
    //
    // Exception table:
    //   from	to	target	type
    //   366	383	547	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   265	295	557	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   430	471	567	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   506	546	577	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
  }

  public void createMessageTest4Byte()
  {
    System.out.println(FM_Exception.getChars(2, 46, "8{2dg$Bx8j&2f\005:~/4*x.|r vd:h>,b0&t*xn<r`6d:(~,\"p&tj8"));
    byte[] arrayOfByte = new byte[58];
    arrayOfByte[0] = 16;
    arrayOfByte[1] = 33;
    arrayOfByte[2] = 2;
    arrayOfByte[3] = 20;
    arrayOfByte[8] = 1;
    arrayOfByte[13] = 1;
    arrayOfByte[18] = 1;
    arrayOfByte[23] = 1;
    arrayOfByte[24] = 3;
    arrayOfByte[25] = 32;
    arrayOfByte[26] = 1;
    arrayOfByte[27] = 2;
    arrayOfByte[28] = 3;
    arrayOfByte[29] = 4;
    arrayOfByte[30] = 2;
    arrayOfByte[31] = 2;
    arrayOfByte[32] = 3;
    arrayOfByte[33] = 4;
    arrayOfByte[34] = 1;
    arrayOfByte[35] = 2;
    arrayOfByte[36] = 3;
    arrayOfByte[37] = 4;
    arrayOfByte[38] = 2;
    arrayOfByte[39] = 2;
    arrayOfByte[40] = 3;
    arrayOfByte[41] = 4;
    arrayOfByte[42] = 1;
    arrayOfByte[43] = 2;
    arrayOfByte[44] = 3;
    arrayOfByte[45] = 4;
    arrayOfByte[46] = 2;
    arrayOfByte[47] = 2;
    arrayOfByte[48] = 3;
    arrayOfByte[49] = 4;
    arrayOfByte[50] = 1;
    arrayOfByte[51] = 2;
    arrayOfByte[52] = 3;
    arrayOfByte[53] = 4;
    arrayOfByte[54] = 2;
    arrayOfByte[55] = 2;
    arrayOfByte[56] = 3;
    arrayOfByte[57] = 4;
    Object localObject = null;
    try
    {
      IMessage localIMessage = this.a.createMessage(arrayOfByte);
      localObject = localIMessage;
      if (localObject == null)
        System.out.println(BCCUtil.endsWith("c!c88z\037 k8v!Co.d#0h5#", 1, 83));
      System.out.println(localObject.getCode());
      int i = localObject.getTagCount();
      System.out.println(Util4Java.toString("a*=ezk", 3, 99) + i);
    }
    catch (FM_Exception localFM_Exception)
    {
      try
      {
        ITag localITag = localObject.getTag4Index(0);
        System.out.println(FM_Bytes.startsWith("r1G\002$", 5, 56) + localITag.getId());
        boolean bool = localObject.isValid();
        System.out.println(FM_Int.lastIndexOf(3, "?$\0168628g") + bool);
        return;
        localFM_Exception = localFM_Exception;
        System.out.println(Util4Java.getExceptionInfo(localFM_Exception));
      }
      catch (FMCommunicationMessageException localFMCommunicationMessageException)
      {
        while (true)
          localFMCommunicationMessageException.printStackTrace();
      }
    }
  }

  public void createTagTest()
  {
    ITag localITag = MessageHandleFactory.getMessageHandler().createTag(1);
    try
    {
      byte[] arrayOfByte = new byte[1];
      arrayOfByte[0] = 2;
      localITag.addValue(arrayOfByte);
      return;
    }
    catch (FMCommunicationMessageException localFMCommunicationMessageException)
    {
      while (true)
        localFMCommunicationMessageException.printStackTrace();
    }
  }

  public void load()
  {
    this.a = MessageHandleFactory.getMessageHandler();
    File localFile = new File(CRCUtil.valueOf(2, "\020su/snkbe?6|%evdq*ekyBoo`m; 51hvV)<s\003\\>4!;\n\034x\025G\\JG食硘r~-O抁朽弃厅jftG讷讻$乕晒q\"%bqrc0h/%u"));
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      this.a.loadDefine(localFileInputStream);
      return;
    }
    catch (FMCommunicationMessageException localFMCommunicationMessageException)
    {
      while (true)
        localFMCommunicationMessageException.printStackTrace();
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
        localFileNotFoundException.printStackTrace();
    }
  }

  public void test()
  {
    byte[] arrayOfByte = FM_Bytes.hexStringToBytes(FM_Exception.getChars(176, 1, "129=O??#WTURSPUY),(-./,qrspuvw\005{yy{\r}\017\021\023dcge\024g\032i\030\031kmilrrppvvttzzxx~\017vfba`gfedkjiho\037jo0"));
    try
    {
      IMessage localIMessage = this.a.createMessage(arrayOfByte);
      System.out.println(localIMessage.getCode());
      int i = localIMessage.getTagCount();
      System.out.println(FM_Bytes.startsWith("8+88+2", 90, 9) + i);
      ITag localITag = localIMessage.getTag4Id(-79);
      System.out.println(FM_Bytes.bytesToHexString(localITag.getBytesVal()));
      return;
    }
    catch (FMCommunicationMessageException localFMCommunicationMessageException)
    {
      while (true)
        localFMCommunicationMessageException.printStackTrace();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.test.Test
 * JD-Core Version:    0.6.0
 */