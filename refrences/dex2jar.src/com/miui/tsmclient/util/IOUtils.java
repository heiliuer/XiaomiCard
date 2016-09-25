package com.miui.tsmclient.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class IOUtils
{
  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      label10: return;
    }
    catch (IOException localIOException)
    {
      break label10;
    }
  }

  public static void closeQuietly(InputStream paramInputStream)
  {
    if (paramInputStream != null);
    try
    {
      paramInputStream.close();
      label8: return;
    }
    catch (IOException localIOException)
    {
      break label8;
    }
  }

  public static void closeQuietly(OutputStream paramOutputStream)
  {
    if (paramOutputStream != null);
    try
    {
      paramOutputStream.flush();
      label8: closeQuietly(paramOutputStream);
      return;
    }
    catch (IOException localIOException)
    {
      break label8;
    }
  }

  public static void closeQuietly(Reader paramReader)
  {
    if (paramReader != null);
    try
    {
      paramReader.close();
      label8: return;
    }
    catch (IOException localIOException)
    {
      break label8;
    }
  }

  public static void closeQuietly(Writer paramWriter)
  {
    if (paramWriter != null);
    try
    {
      paramWriter.close();
      label8: return;
    }
    catch (IOException localIOException)
    {
      break label8;
    }
  }

  // ERROR //
  public static boolean retrieveFileFromAssets(android.content.Context paramContext, java.lang.String paramString1, java.lang.String paramString2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore 5
    //   8: aload_0
    //   9: invokevirtual 45	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   12: aload_1
    //   13: invokevirtual 51	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   16: astore 4
    //   18: new 53	java/io/File
    //   21: dup
    //   22: aload_2
    //   23: invokespecial 56	java/io/File:<init>	(Ljava/lang/String;)V
    //   26: astore 8
    //   28: aload 8
    //   30: invokevirtual 60	java/io/File:createNewFile	()Z
    //   33: pop
    //   34: new 62	java/io/FileOutputStream
    //   37: dup
    //   38: aload 8
    //   40: invokespecial 65	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   43: astore 10
    //   45: sipush 1024
    //   48: newarray byte
    //   50: astore 12
    //   52: aload 4
    //   54: aload 12
    //   56: invokevirtual 69	java/io/InputStream:read	([B)I
    //   59: istore 13
    //   61: iload 13
    //   63: ifle +34 -> 97
    //   66: aload 10
    //   68: aload 12
    //   70: iconst_0
    //   71: iload 13
    //   73: invokevirtual 73	java/io/FileOutputStream:write	([BII)V
    //   76: goto -24 -> 52
    //   79: astore 11
    //   81: aload 10
    //   83: astore 5
    //   85: aload 4
    //   87: invokestatic 75	com/miui/tsmclient/util/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   90: aload 5
    //   92: invokestatic 77	com/miui/tsmclient/util/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   95: iload_3
    //   96: ireturn
    //   97: iconst_1
    //   98: istore_3
    //   99: aload 4
    //   101: invokestatic 75	com/miui/tsmclient/util/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   104: aload 10
    //   106: invokestatic 77	com/miui/tsmclient/util/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   109: goto -14 -> 95
    //   112: astore 7
    //   114: aload 4
    //   116: invokestatic 75	com/miui/tsmclient/util/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   119: aload 5
    //   121: invokestatic 77	com/miui/tsmclient/util/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   124: aload 7
    //   126: athrow
    //   127: astore 7
    //   129: aload 10
    //   131: astore 5
    //   133: goto -19 -> 114
    //   136: astore 6
    //   138: goto -53 -> 85
    //
    // Exception table:
    //   from	to	target	type
    //   45	76	79	java/io/IOException
    //   8	45	112	finally
    //   45	76	127	finally
    //   8	45	136	java/io/IOException
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.util.IOUtils
 * JD-Core Version:    0.6.0
 */