package cn.com.fmsh.script.bean;

import bg;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApduRequestList
{
  public final int c = 1;

  public boolean add(ApduRequest paramApduRequest)
    throws FMScriptHandleException
  {
    int i = 1;
    if (paramApduRequest == null)
      i = 0;
    while (true)
    {
      return i;
      if ((this.d.size() < i) && (i != paramApduRequest.getId()))
        throw new FMScriptHandleException(FM_Int.lastIndexOf(4, "忒房蠕盞筷乜朼捙亻的庎刕叴三昪7＋夅吁脐朧夈琋夿贪"));
      this.d.put(Integer.valueOf(paramApduRequest.getId()), paramApduRequest);
    }
  }

  public void clear()
  {
    try
    {
      this.d.clear();
      label9: return;
    }
    catch (bg localbg)
    {
      break label9;
    }
  }

  // ERROR //
  public void fromBytes(byte[] paramArrayOfByte)
    throws FMScriptHandleException
  {
    // Byte code:
    //   0: iconst_2
    //   1: istore_2
    //   2: aload_1
    //   3: ifnull +9 -> 12
    //   6: aload_1
    //   7: arraylength
    //   8: iconst_1
    //   9: if_icmpge +149 -> 158
    //   12: new 103	cn/com/fmsh/script/exception/FMScriptHandleException
    //   15: dup
    //   16: iconst_4
    //   17: ldc 131
    //   19: invokestatic 135	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
    //   22: invokespecial 115	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   25: athrow
    //   26: aload 6
    //   28: iload 7
    //   30: i2b
    //   31: invokevirtual 139	cn/com/fmsh/script/bean/ApduRequest:setTag	(B)V
    //   34: iload 8
    //   36: newarray byte
    //   38: astore 9
    //   40: aload 5
    //   42: aload 9
    //   44: invokevirtual 63	java/io/ByteArrayInputStream:read	([B)I
    //   47: istore 11
    //   49: iload 11
    //   51: ifge +230 -> 281
    //   54: return
    //   55: new 51	java/io/ByteArrayInputStream
    //   58: dup
    //   59: aload_1
    //   60: aload_1
    //   61: arraylength
    //   62: iload_3
    //   63: isub
    //   64: aload_1
    //   65: arraylength
    //   66: invokestatic 83	java/util/Arrays:copyOfRange	([BII)[B
    //   69: invokespecial 53	java/io/ByteArrayInputStream:<init>	([B)V
    //   72: astore 5
    //   74: new 55	cn/com/fmsh/script/bean/ApduRequest
    //   77: dup
    //   78: invokespecial 56	cn/com/fmsh/script/bean/ApduRequest:<init>	()V
    //   81: astore 6
    //   83: aload 5
    //   85: invokevirtual 60	java/io/ByteArrayInputStream:read	()I
    //   88: istore 7
    //   90: aload 5
    //   92: invokevirtual 60	java/io/ByteArrayInputStream:read	()I
    //   95: istore 8
    //   97: iload 8
    //   99: sipush 255
    //   102: if_icmpne +245 -> 347
    //   105: aload 5
    //   107: aload 4
    //   109: invokevirtual 63	java/io/ByteArrayInputStream:read	([B)I
    //   112: istore 13
    //   114: bipush 255
    //   116: iload 13
    //   118: if_icmpne +222 -> 340
    //   121: goto -67 -> 54
    //   124: astore 10
    //   126: aload_0
    //   127: getfield 29	cn/com/fmsh/script/bean/ApduRequestList:a	Lcn/com/fmsh/util/log/FMLog;
    //   130: ifnull -76 -> 54
    //   133: aload_0
    //   134: getfield 29	cn/com/fmsh/script/bean/ApduRequestList:a	Lcn/com/fmsh/util/log/FMLog;
    //   137: ldc 141
    //   139: sipush 174
    //   142: invokestatic 147	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
    //   145: aload 10
    //   147: invokestatic 153	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   150: invokeinterface 159 3 0
    //   155: goto -101 -> 54
    //   158: aload_1
    //   159: iconst_0
    //   160: baload
    //   161: bipush 161
    //   163: if_icmpeq +55 -> 218
    //   166: new 103	cn/com/fmsh/script/exception/FMScriptHandleException
    //   169: dup
    //   170: sipush 214
    //   173: ldc 161
    //   175: invokestatic 37	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   178: invokespecial 115	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   181: athrow
    //   182: astore 12
    //   184: aload_0
    //   185: getfield 29	cn/com/fmsh/script/bean/ApduRequestList:a	Lcn/com/fmsh/util/log/FMLog;
    //   188: ifnull -134 -> 54
    //   191: aload_0
    //   192: getfield 29	cn/com/fmsh/script/bean/ApduRequestList:a	Lcn/com/fmsh/util/log/FMLog;
    //   195: sipush 200
    //   198: bipush 83
    //   200: ldc 163
    //   202: invokestatic 169	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   205: aload 12
    //   207: invokestatic 153	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   210: invokeinterface 159 3 0
    //   215: goto -161 -> 54
    //   218: aload_1
    //   219: iconst_1
    //   220: baload
    //   221: istore_3
    //   222: iload_2
    //   223: newarray byte
    //   225: astore 4
    //   227: iload_3
    //   228: sipush 255
    //   231: if_icmpne +25 -> 256
    //   234: aload 4
    //   236: iconst_0
    //   237: aload_1
    //   238: iload_2
    //   239: baload
    //   240: bastore
    //   241: aload 4
    //   243: iconst_1
    //   244: aload_1
    //   245: iconst_3
    //   246: baload
    //   247: bastore
    //   248: aload 4
    //   250: invokestatic 174	cn/com/fmsh/util/FM_Bytes:bytesToInt	([B)I
    //   253: istore_3
    //   254: iconst_5
    //   255: istore_2
    //   256: iload_3
    //   257: aload_1
    //   258: arraylength
    //   259: iload_2
    //   260: isub
    //   261: if_icmpeq -206 -> 55
    //   264: new 103	cn/com/fmsh/script/exception/FMScriptHandleException
    //   267: dup
    //   268: ldc 176
    //   270: sipush 150
    //   273: iconst_5
    //   274: invokestatic 182	cn/com/fmsh/util/BCCUtil:endsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   277: invokespecial 115	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   280: athrow
    //   281: iload 7
    //   283: bipush 164
    //   285: if_icmpne +12 -> 297
    //   288: aload_0
    //   289: aload 9
    //   291: invokespecial 184	cn/com/fmsh/script/bean/ApduRequestList:a	([B)V
    //   294: goto -220 -> 74
    //   297: iload 7
    //   299: bipush 160
    //   301: if_icmpne +14 -> 315
    //   304: aload_0
    //   305: aload 9
    //   307: aload 6
    //   309: invokespecial 186	cn/com/fmsh/script/bean/ApduRequestList:a	([BLcn/com/fmsh/script/bean/ApduRequest;)V
    //   312: goto -238 -> 74
    //   315: aload_0
    //   316: getfield 46	cn/com/fmsh/script/bean/ApduRequestList:d	Ljava/util/Map;
    //   319: invokeinterface 128 1 0
    //   324: new 103	cn/com/fmsh/script/exception/FMScriptHandleException
    //   327: dup
    //   328: ldc 188
    //   330: iconst_4
    //   331: bipush 37
    //   333: invokestatic 191	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
    //   336: invokespecial 115	cn/com/fmsh/script/exception/FMScriptHandleException:<init>	(Ljava/lang/String;)V
    //   339: athrow
    //   340: aload 4
    //   342: invokestatic 174	cn/com/fmsh/util/FM_Bytes:bytesToInt	([B)I
    //   345: istore 8
    //   347: iload 8
    //   349: ifne -323 -> 26
    //   352: goto -298 -> 54
    //
    // Exception table:
    //   from	to	target	type
    //   40	49	124	java/io/IOException
    //   288	340	124	java/io/IOException
    //   105	114	182	java/io/IOException
  }

  public void fromTag(ITag paramITag)
    throws FMCommunicationMessageException, FMScriptHandleException
  {
    int i = 0;
    if (paramITag == null);
    while (true)
    {
      try
      {
        throw new FMCommunicationMessageException(FM_CN.subSequence("F@W轳挬乧捋亿雌向旾；\022\024\003乩穸", 4));
        continue;
        this.d.clear();
        String str1 = BCCUtil.endsWith("Eo吖靰厦肻是\033d战耍\003(：讵\036e9\0037T\033斠敒", 5, 90);
        Object[] arrayOfObject1 = new Object[1];
        byte b2;
        arrayOfObject1[0] = Byte.valueOf(b2);
        throw new FMCommunicationMessageException(String.format(str1, arrayOfObject1));
        byte b1 = paramITag.getId();
        if (paramITag.isValid())
          continue;
        String str3 = FM_Exception.getChars(6, 65, "桦挮觢柒左儳揕保皃\034H\rPiU\023觬柀详氐挔亰旣ｚC\031^斺敓");
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = Byte.valueOf(paramITag.getId());
        throw new FMCommunicationMessageException(String.format(str3, arrayOfObject3));
        if (b1 != -96)
          continue;
        ApduRequest localApduRequest2 = new ApduRequest();
        localApduRequest2.setTag(b1);
        a(paramITag.getBytesVal(), localApduRequest2);
        add(localApduRequest2);
        break label328;
        if (b2 != -96)
          continue;
        ITag localITag;
        ApduRequest localApduRequest1;
        a(localITag.getBytesVal(), localApduRequest1);
        add(localApduRequest1);
        break label329;
        int j;
        if (i < j)
        {
          localITag = paramITag.getItemTagVal(i);
          if (localITag == null)
            break label329;
          localApduRequest1 = new ApduRequest();
          b2 = localITag.getId();
          localApduRequest1.setTag(b2);
          if (b2 != -92)
            continue;
          a(localITag.getItemTags(), localApduRequest1);
          continue;
          String str2 = FM_CN.subSequence("U3戄聄\021n戃肠轠捹丰丒厙腍杪ｙPREJe\027\003断攔", 6);
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Byte.valueOf(b1);
          throw new FMCommunicationMessageException(String.format(str2, arrayOfObject2));
          if (b1 != -95)
            continue;
          j = paramITag.getItemCount();
          continue;
        }
      }
      catch (bg localbg)
      {
      }
      label328: return;
      label329: i++;
    }
  }

  public ApduRequest getApduRequest(int paramInt)
  {
    try
    {
      localApduRequest = (ApduRequest)this.d.get(Integer.valueOf(paramInt));
      return localApduRequest;
    }
    catch (bg localbg)
    {
      while (true)
        ApduRequest localApduRequest = null;
    }
  }

  public ApduRequest[] getApduRequests()
  {
    ApduRequest[] arrayOfApduRequest = new ApduRequest[this.d.size()];
    Iterator localIterator = this.d.keySet().iterator();
    for (int i = 0; ; i++)
    {
      if (!localIterator.hasNext())
        return arrayOfApduRequest;
      arrayOfApduRequest[i] = ((ApduRequest)this.d.get(localIterator.next()));
    }
  }

  public List<byte[]> getApdus()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.d.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      ApduRequest localApduRequest = (ApduRequest)this.d.get(localIterator.next());
      if (localApduRequest == null)
        continue;
      localArrayList.add(localApduRequest.getApdu());
    }
  }

  public ApduRequest getFirstApduRequest()
  {
    try
    {
      localApduRequest = (ApduRequest)this.d.get(Integer.valueOf(1));
      return localApduRequest;
    }
    catch (bg localbg)
    {
      while (true)
        ApduRequest localApduRequest = null;
    }
  }

  public byte[] toBytes()
  {
    return null;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.bean.ApduRequestList
 * JD-Core Version:    0.6.0
 */