package cn.com.fmsh.communication.message.tagvalue;

public class StringValueHandler4gbk
  implements StringValueHandler
{
  // ERROR //
  public java.lang.String getTagvalue(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +9 -> 10
    //   4: aload_1
    //   5: arraylength
    //   6: iconst_1
    //   7: if_icmpge +17 -> 24
    //   10: aconst_null
    //   11: astore_2
    //   12: aload_2
    //   13: areturn
    //   14: astore_3
    //   15: aload_3
    //   16: invokevirtual 17	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   19: aconst_null
    //   20: astore_2
    //   21: goto -9 -> 12
    //   24: new 19	java/lang/String
    //   27: dup
    //   28: aload_1
    //   29: sipush 206
    //   32: bipush 82
    //   34: ldc 21
    //   36: invokestatic 27	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
    //   39: invokespecial 30	java/lang/String:<init>	([BLjava/lang/String;)V
    //   42: astore_2
    //   43: goto -31 -> 12
    //
    // Exception table:
    //   from	to	target	type
    //   24	43	14	java/io/UnsupportedEncodingException
  }

  // ERROR //
  public byte[] setTagValue(java.lang.String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_1
    //   3: ifnonnull +15 -> 18
    //   6: aload_2
    //   7: areturn
    //   8: astore 4
    //   10: aload 4
    //   12: invokevirtual 17	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   15: goto -9 -> 6
    //   18: aload_1
    //   19: iconst_4
    //   20: ldc 34
    //   22: invokestatic 40	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   25: invokevirtual 43	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   28: astore_3
    //   29: aload_3
    //   30: astore_2
    //   31: goto -25 -> 6
    //
    // Exception table:
    //   from	to	target	type
    //   18	29	8	java/io/UnsupportedEncodingException
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.tagvalue.StringValueHandler4gbk
 * JD-Core Version:    0.6.0
 */