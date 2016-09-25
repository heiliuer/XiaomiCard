package com.nostra13.universalimageloader.cache.disc.impl.ext;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

class StrictLineReader
  implements Closeable
{
  private static final byte CR = 13;
  private static final byte LF = 10;
  private byte[] buf;
  private final Charset charset;
  private int end;
  private final InputStream in;
  private int pos;

  public StrictLineReader(InputStream paramInputStream, int paramInt, Charset paramCharset)
  {
    if ((paramInputStream == null) || (paramCharset == null))
      throw new NullPointerException();
    if (paramInt < 0)
      throw new IllegalArgumentException("capacity <= 0");
    if (!paramCharset.equals(Util.US_ASCII))
      throw new IllegalArgumentException("Unsupported encoding");
    this.in = paramInputStream;
    this.charset = paramCharset;
    this.buf = new byte[paramInt];
  }

  public StrictLineReader(InputStream paramInputStream, Charset paramCharset)
  {
    this(paramInputStream, 8192, paramCharset);
  }

  private void fillBuf()
    throws IOException
  {
    int i = this.in.read(this.buf, 0, this.buf.length);
    if (i == -1)
      throw new EOFException();
    this.pos = 0;
    this.end = i;
  }

  public void close()
    throws IOException
  {
    synchronized (this.in)
    {
      if (this.buf != null)
      {
        this.buf = null;
        this.in.close();
      }
      return;
    }
  }

  // ERROR //
  public String readLine()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 52	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:in	Ljava/io/InputStream;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 56	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:buf	[B
    //   11: ifnonnull +18 -> 29
    //   14: new 64	java/io/IOException
    //   17: dup
    //   18: ldc 84
    //   20: invokespecial 85	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   23: athrow
    //   24: astore_2
    //   25: aload_1
    //   26: monitorexit
    //   27: aload_2
    //   28: athrow
    //   29: aload_0
    //   30: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   33: aload_0
    //   34: getfield 77	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:end	I
    //   37: if_icmplt +7 -> 44
    //   40: aload_0
    //   41: invokespecial 87	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:fillBuf	()V
    //   44: aload_0
    //   45: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   48: istore_3
    //   49: iload_3
    //   50: aload_0
    //   51: getfield 77	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:end	I
    //   54: if_icmpeq +83 -> 137
    //   57: aload_0
    //   58: getfield 56	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:buf	[B
    //   61: iload_3
    //   62: baload
    //   63: bipush 10
    //   65: if_icmpne +217 -> 282
    //   68: iload_3
    //   69: aload_0
    //   70: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   73: if_icmpeq +203 -> 276
    //   76: aload_0
    //   77: getfield 56	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:buf	[B
    //   80: iload_3
    //   81: iconst_1
    //   82: isub
    //   83: baload
    //   84: bipush 13
    //   86: if_icmpne +190 -> 276
    //   89: iload_3
    //   90: iconst_1
    //   91: isub
    //   92: istore 7
    //   94: new 89	java/lang/String
    //   97: dup
    //   98: aload_0
    //   99: getfield 56	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:buf	[B
    //   102: aload_0
    //   103: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   106: iload 7
    //   108: aload_0
    //   109: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   112: isub
    //   113: aload_0
    //   114: getfield 54	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:charset	Ljava/nio/charset/Charset;
    //   117: invokevirtual 92	java/nio/charset/Charset:name	()Ljava/lang/String;
    //   120: invokespecial 95	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   123: astore 6
    //   125: aload_0
    //   126: iload_3
    //   127: iconst_1
    //   128: iadd
    //   129: putfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   132: aload_1
    //   133: monitorexit
    //   134: goto +139 -> 273
    //   137: new 8	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader$1
    //   140: dup
    //   141: aload_0
    //   142: bipush 80
    //   144: aload_0
    //   145: getfield 77	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:end	I
    //   148: aload_0
    //   149: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   152: isub
    //   153: iadd
    //   154: invokespecial 98	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader$1:<init>	(Lcom/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader;I)V
    //   157: astore 4
    //   159: aload 4
    //   161: aload_0
    //   162: getfield 56	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:buf	[B
    //   165: aload_0
    //   166: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   169: aload_0
    //   170: getfield 77	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:end	I
    //   173: aload_0
    //   174: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   177: isub
    //   178: invokevirtual 104	java/io/ByteArrayOutputStream:write	([BII)V
    //   181: aload_0
    //   182: bipush 255
    //   184: putfield 77	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:end	I
    //   187: aload_0
    //   188: invokespecial 87	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:fillBuf	()V
    //   191: aload_0
    //   192: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   195: istore 5
    //   197: iload 5
    //   199: aload_0
    //   200: getfield 77	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:end	I
    //   203: if_icmpeq -44 -> 159
    //   206: aload_0
    //   207: getfield 56	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:buf	[B
    //   210: iload 5
    //   212: baload
    //   213: bipush 10
    //   215: if_icmpne +52 -> 267
    //   218: iload 5
    //   220: aload_0
    //   221: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   224: if_icmpeq +23 -> 247
    //   227: aload 4
    //   229: aload_0
    //   230: getfield 56	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:buf	[B
    //   233: aload_0
    //   234: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   237: iload 5
    //   239: aload_0
    //   240: getfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   243: isub
    //   244: invokevirtual 104	java/io/ByteArrayOutputStream:write	([BII)V
    //   247: aload_0
    //   248: iload 5
    //   250: iconst_1
    //   251: iadd
    //   252: putfield 75	com/nostra13/universalimageloader/cache/disc/impl/ext/StrictLineReader:pos	I
    //   255: aload 4
    //   257: invokevirtual 107	java/io/ByteArrayOutputStream:toString	()Ljava/lang/String;
    //   260: astore 6
    //   262: aload_1
    //   263: monitorexit
    //   264: goto +9 -> 273
    //   267: iinc 5 1
    //   270: goto -73 -> 197
    //   273: aload 6
    //   275: areturn
    //   276: iload_3
    //   277: istore 7
    //   279: goto -185 -> 94
    //   282: iinc 3 1
    //   285: goto -236 -> 49
    //
    // Exception table:
    //   from	to	target	type
    //   7	27	24	finally
    //   29	264	24	finally
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.disc.impl.ext.StrictLineReader
 * JD-Core Version:    0.6.0
 */