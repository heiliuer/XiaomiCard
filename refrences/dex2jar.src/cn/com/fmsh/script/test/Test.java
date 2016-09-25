package cn.com.fmsh.script.test;

import bo;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.MessageHandleFactory;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.script.ApduFilterDataInit;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.ApduHandler.ApduHandlerType;
import cn.com.fmsh.script.ScriptHandler;
import cn.com.fmsh.script.ScriptHandlerFactory;
import cn.com.fmsh.script.bean.ApduReponseList;
import cn.com.fmsh.script.bean.ApduRequestList;
import cn.com.fmsh.script.bean.ApduResponse;
import cn.com.fmsh.script.core.FilterPolicy;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.Util4Java;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Test
{
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      Test localTest = new Test();
      localTest.load();
      localTest.testM();
      label16: return;
    }
    catch (bo localbo)
    {
      break label16;
    }
  }

  public void load()
  {
    this.a = MessageHandleFactory.getMessageHandler();
    File localFile = new File(CRCUtil.valueOf(3, "\032pt8xco?l70'$qb5};,r5sc"));
    try
    {
      localFileInputStream = new FileInputStream(localFile);
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      try
      {
        FileInputStream localFileInputStream;
        this.a.loadDefine(localFileInputStream);
        while (true)
        {
          return;
          localFileNotFoundException = localFileNotFoundException;
          localFileNotFoundException.printStackTrace();
        }
      }
      catch (FMCommunicationMessageException localFMCommunicationMessageException)
      {
        while (true)
          localFMCommunicationMessageException.printStackTrace();
      }
    }
  }

  public void testM()
  {
    ScriptHandler localScriptHandler = ScriptHandlerFactory.getInstance().getScriptHandler(new a(null));
    localScriptHandler.setApduFilterDataInit(new b(null));
    ApduRequestList localApduRequestList = new ApduRequestList();
    System.out.println(CRCUtil.valueOf(1, "judw&1 3bm|o~)8+:etgv!0jjs%ny(;*5dwfq 3\"-<o~ix+:%4gvap#2=,?nyh{*5$7fq`s\""));
    ITag localITag1 = this.a.createTag(-95);
    ITag localITag2 = this.a.createTag(-96);
    ITag localITag3 = this.a.createTag(-92);
    ITag localITag4 = this.a.createTag(-92);
    ITag localITag5 = this.a.createTag(-92);
    try
    {
      byte[] arrayOfByte1 = new byte[8];
      arrayOfByte1[0] = 1;
      arrayOfByte1[2] = -92;
      arrayOfByte1[6] = -1;
      arrayOfByte1[7] = -1;
      localITag2.addValue(arrayOfByte1);
      localITag1.addValue(localITag2);
      ITag localITag6 = this.a.createTag(56);
      localITag6.addValue(2);
      localITag3.addValue(localITag6);
      ITag localITag7 = this.a.createTag(57);
      byte[] arrayOfByte2 = new byte[5];
      arrayOfByte2[1] = -92;
      localITag7.addValue(arrayOfByte2);
      localITag3.addValue(localITag7);
      ITag localITag8 = this.a.createTag(60);
      byte[] arrayOfByte3 = new byte[3];
      arrayOfByte3[0] = -112;
      localITag8.addValue(arrayOfByte3);
      localITag3.addValue(localITag8);
      ITag localITag9 = this.a.createTag(60);
      byte[] arrayOfByte4 = new byte[3];
      arrayOfByte4[0] = 105;
      arrayOfByte4[2] = -1;
      localITag9.addValue(arrayOfByte4);
      localITag3.addValue(localITag9);
      localITag1.addValue(localITag3);
      ITag localITag10 = this.a.createTag(56);
      localITag10.addValue(3);
      localITag4.addValue(localITag10);
      ITag localITag11 = this.a.createTag(57);
      byte[] arrayOfByte5 = new byte[5];
      arrayOfByte5[1] = -92;
      localITag11.addValue(arrayOfByte5);
      localITag4.addValue(localITag11);
      ITag localITag12 = this.a.createTag(60);
      byte[] arrayOfByte6 = new byte[3];
      arrayOfByte6[0] = -112;
      arrayOfByte6[2] = 4;
      localITag12.addValue(arrayOfByte6);
      localITag4.addValue(localITag12);
      ITag localITag13 = this.a.createTag(60);
      byte[] arrayOfByte7 = new byte[3];
      arrayOfByte7[0] = 105;
      arrayOfByte7[2] = -1;
      localITag13.addValue(arrayOfByte7);
      localITag4.addValue(localITag13);
      localITag1.addValue(localITag4);
      ITag localITag14 = this.a.createTag(56);
      localITag14.addValue(4);
      localITag5.addValue(localITag14);
      ITag localITag15 = this.a.createTag(57);
      byte[] arrayOfByte8 = new byte[5];
      arrayOfByte8[1] = -92;
      localITag15.addValue(arrayOfByte8);
      localITag5.addValue(localITag15);
      ITag localITag16 = this.a.createTag(60);
      byte[] arrayOfByte9 = new byte[3];
      arrayOfByte9[0] = -112;
      arrayOfByte9[2] = -1;
      localITag16.addValue(arrayOfByte9);
      localITag5.addValue(localITag16);
      ITag localITag17 = this.a.createTag(60);
      byte[] arrayOfByte10 = new byte[3];
      arrayOfByte10[0] = 105;
      arrayOfByte10[2] = -1;
      localITag17.addValue(arrayOfByte10);
      localITag5.addValue(localITag17);
      localITag1.addValue(localITag5);
    }
    catch (FMCommunicationMessageException localFMCommunicationMessageException1)
    {
      try
      {
        while (true)
        {
          localApduRequestList.fromTag(localITag1);
          Object localObject = null;
          try
          {
            ApduReponseList localApduReponseList = localScriptHandler.execute(localApduRequestList);
            localObject = localApduReponseList;
            arrayOfApduResponse = localObject.getApduResponse();
            System.out.println(FM_Bytes.startsWith("e><7^sp%:mmar*5tcz0x", 3, 106) + arrayOfApduResponse.length);
            int i = arrayOfApduResponse.length;
            j = 0;
            if (j >= i)
            {
              return;
              localFMCommunicationMessageException1 = localFMCommunicationMessageException1;
              System.out.println(Util4Java.getExceptionInfo(localFMCommunicationMessageException1));
            }
          }
          catch (FMScriptHandleException localFMScriptHandleException1)
          {
            while (true)
              localFMScriptHandleException1.printStackTrace();
          }
        }
      }
      catch (FMScriptHandleException localFMScriptHandleException2)
      {
        while (true)
        {
          ApduResponse[] arrayOfApduResponse;
          int j;
          System.out.println(Util4Java.getExceptionInfo(localFMScriptHandleException2));
          continue;
          ApduResponse localApduResponse = arrayOfApduResponse[j];
          System.out.println(FM_Long.concat(";9r", 346) + localApduResponse.getId() + FM_Int.lastIndexOf(98, "uv%=)55/8\0350$$x") + FM_Bytes.bytesToHexString(localApduResponse.getResult()));
          j++;
        }
      }
      catch (FMCommunicationMessageException localFMCommunicationMessageException2)
      {
        while (true)
          System.out.println(Util4Java.getExceptionInfo(localFMCommunicationMessageException2));
      }
    }
  }

  // ERROR //
  public void testSingle()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: invokestatic 74	cn/com/fmsh/script/ScriptHandlerFactory:getInstance	()Lcn/com/fmsh/script/ScriptHandlerFactory;
    //   5: new 6	cn/com/fmsh/script/test/Test$a
    //   8: dup
    //   9: aload_0
    //   10: aconst_null
    //   11: invokespecial 77	cn/com/fmsh/script/test/Test$a:<init>	(Lcn/com/fmsh/script/test/Test;Lcn/com/fmsh/script/test/Test$a;)V
    //   14: invokevirtual 81	cn/com/fmsh/script/ScriptHandlerFactory:getScriptHandler	(Lcn/com/fmsh/script/ApduHandler;)Lcn/com/fmsh/script/ScriptHandler;
    //   17: astore_2
    //   18: aload_2
    //   19: new 9	cn/com/fmsh/script/test/Test$b
    //   22: dup
    //   23: aload_0
    //   24: aconst_null
    //   25: invokespecial 84	cn/com/fmsh/script/test/Test$b:<init>	(Lcn/com/fmsh/script/test/Test;Lcn/com/fmsh/script/test/Test$b;)V
    //   28: invokeinterface 90 2 0
    //   33: new 92	cn/com/fmsh/script/bean/ApduRequestList
    //   36: dup
    //   37: invokespecial 93	cn/com/fmsh/script/bean/ApduRequestList:<init>	()V
    //   40: astore_3
    //   41: getstatic 99	java/lang/System:out	Ljava/io/PrintStream;
    //   44: sipush 214
    //   47: ldc 197
    //   49: invokestatic 48	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   52: invokevirtual 106	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   55: aload_0
    //   56: getfield 23	cn/com/fmsh/script/test/Test:a	Lcn/com/fmsh/communication/message/IMessageHandler;
    //   59: bipush 160
    //   61: invokeinterface 110 2 0
    //   66: astore 4
    //   68: bipush 9
    //   70: newarray byte
    //   72: astore 14
    //   74: aload 14
    //   76: iconst_0
    //   77: iconst_1
    //   78: bastore
    //   79: aload 14
    //   81: iconst_1
    //   82: iconst_5
    //   83: bastore
    //   84: aload 14
    //   86: iconst_3
    //   87: bipush 164
    //   89: bastore
    //   90: aload 14
    //   92: bipush 7
    //   94: bipush 144
    //   96: bastore
    //   97: aload 4
    //   99: aload 14
    //   101: invokeinterface 116 2 0
    //   106: pop
    //   107: aload_3
    //   108: aload 4
    //   110: invokevirtual 126	cn/com/fmsh/script/bean/ApduRequestList:fromTag	(Lcn/com/fmsh/communication/message/ITag;)V
    //   113: aload_2
    //   114: aload_3
    //   115: invokeinterface 130 2 0
    //   120: astore 12
    //   122: aload 12
    //   124: astore_1
    //   125: aload_1
    //   126: invokevirtual 136	cn/com/fmsh/script/bean/ApduReponseList:getApduResponse	()[Lcn/com/fmsh/script/bean/ApduResponse;
    //   129: astore 8
    //   131: getstatic 99	java/lang/System:out	Ljava/io/PrintStream;
    //   134: new 138	java/lang/StringBuilder
    //   137: dup
    //   138: ldc 199
    //   140: iconst_2
    //   141: invokestatic 170	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   144: invokespecial 147	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   147: aload 8
    //   149: arraylength
    //   150: invokevirtual 151	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   153: invokevirtual 155	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   156: invokevirtual 106	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   159: aload 8
    //   161: arraylength
    //   162: istore 9
    //   164: iconst_0
    //   165: istore 10
    //   167: iload 10
    //   169: iload 9
    //   171: if_icmplt +30 -> 201
    //   174: return
    //   175: astore 5
    //   177: aload 5
    //   179: invokevirtual 66	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException:printStackTrace	()V
    //   182: goto -75 -> 107
    //   185: astore 13
    //   187: getstatic 99	java/lang/System:out	Ljava/io/PrintStream;
    //   190: aload 13
    //   192: invokestatic 161	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   195: invokevirtual 106	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   198: goto -85 -> 113
    //   201: aload 8
    //   203: iload 10
    //   205: aaload
    //   206: astore 11
    //   208: getstatic 99	java/lang/System:out	Ljava/io/PrintStream;
    //   211: aload 11
    //   213: invokevirtual 190	cn/com/fmsh/script/bean/ApduResponse:getResult	()[B
    //   216: invokestatic 194	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   219: invokevirtual 106	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   222: iinc 10 1
    //   225: goto -58 -> 167
    //   228: astore 6
    //   230: getstatic 99	java/lang/System:out	Ljava/io/PrintStream;
    //   233: aload 6
    //   235: invokestatic 161	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   238: invokevirtual 106	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   241: goto -128 -> 113
    //   244: astore 7
    //   246: aload 7
    //   248: invokevirtual 162	cn/com/fmsh/script/exception/FMScriptHandleException:printStackTrace	()V
    //   251: goto -126 -> 125
    //
    // Exception table:
    //   from	to	target	type
    //   68	107	175	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   107	113	185	cn/com/fmsh/communication/message/exception/FMCommunicationMessageException
    //   107	113	228	cn/com/fmsh/script/exception/FMScriptHandleException
    //   113	122	244	cn/com/fmsh/script/exception/FMScriptHandleException
  }

  private class a
    implements ApduHandler
  {
    public void close()
    {
    }

    public boolean connect()
    {
      return true;
    }

    public ApduHandler.ApduHandlerType getApduHandlerType()
    {
      return ApduHandler.ApduHandlerType.OPEN_MOBILE;
    }

    public boolean isConnect()
    {
      return false;
    }

    public boolean open(byte[] paramArrayOfByte)
    {
      try
      {
        System.out.println(Util4Java.toString("l'.1s&rk9", 4, 116) + FM_Bytes.bytesToHexString(paramArrayOfByte));
        i = 1;
        return i;
      }
      catch (bo localbo)
      {
        while (true)
          int i = 0;
      }
    }

    public byte[] transceive(byte[] paramArrayOfByte)
      throws FMScriptHandleException
    {
      try
      {
        arrayOfByte = new byte[2];
        arrayOfByte[0] = -112;
        return arrayOfByte;
      }
      catch (bo localbo)
      {
        while (true)
          byte[] arrayOfByte = null;
      }
    }
  }

  private class b
    implements ApduFilterDataInit
  {
    public List<FilterPolicy> getFilterPolicies()
    {
      try
      {
        localArrayList = new ArrayList();
        FilterPolicy localFilterPolicy = new FilterPolicy();
        localFilterPolicy.setCla(0);
        localFilterPolicy.setIns(-92);
        localFilterPolicy.addFilterData(new byte[0]);
        localArrayList.add(localFilterPolicy);
        return localArrayList;
      }
      catch (bo localbo)
      {
        while (true)
          ArrayList localArrayList = null;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.test.Test
 * JD-Core Version:    0.6.0
 */