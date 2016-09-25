package com.nostra13.universalimageloader.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IoUtils
{
  public static final int CONTINUE_LOADING_PERCENTAGE = 75;
  public static final int DEFAULT_BUFFER_SIZE = 32768;
  public static final int DEFAULT_IMAGE_TOTAL_SIZE = 512000;

  public static void closeSilently(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      label10: return;
    }
    catch (Exception localException)
    {
      break label10;
    }
  }

  public static boolean copyStream(InputStream paramInputStream, OutputStream paramOutputStream, CopyListener paramCopyListener)
    throws IOException
  {
    return copyStream(paramInputStream, paramOutputStream, paramCopyListener, 32768);
  }

  public static boolean copyStream(InputStream paramInputStream, OutputStream paramOutputStream, CopyListener paramCopyListener, int paramInt)
    throws IOException
  {
    int i = 0;
    int j = 0;
    int k = paramInputStream.available();
    if (k <= 0)
      k = 512000;
    byte[] arrayOfByte = new byte[paramInt];
    if (shouldStopLoading(paramCopyListener, 0, k));
    while (true)
    {
      return i;
      while (true)
      {
        int m = paramInputStream.read(arrayOfByte, 0, paramInt);
        if (m != -1)
        {
          paramOutputStream.write(arrayOfByte, 0, m);
          j += m;
          if (!shouldStopLoading(paramCopyListener, j, k))
            continue;
          break;
        }
      }
      paramOutputStream.flush();
      i = 1;
    }
  }

  // ERROR //
  public static void readAndCloseStream(InputStream paramInputStream)
  {
    // Byte code:
    //   0: ldc 12
    //   2: newarray byte
    //   4: astore_1
    //   5: aload_0
    //   6: aload_1
    //   7: iconst_0
    //   8: ldc 12
    //   10: invokevirtual 48	java/io/InputStream:read	([BII)I
    //   13: istore 4
    //   15: iload 4
    //   17: bipush 255
    //   19: if_icmpne -14 -> 5
    //   22: aload_0
    //   23: invokestatic 61	com/nostra13/universalimageloader/utils/IoUtils:closeSilently	(Ljava/io/Closeable;)V
    //   26: return
    //   27: astore_3
    //   28: aload_0
    //   29: invokestatic 61	com/nostra13/universalimageloader/utils/IoUtils:closeSilently	(Ljava/io/Closeable;)V
    //   32: goto -6 -> 26
    //   35: astore_2
    //   36: aload_0
    //   37: invokestatic 61	com/nostra13/universalimageloader/utils/IoUtils:closeSilently	(Ljava/io/Closeable;)V
    //   40: aload_2
    //   41: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   5	15	27	java/io/IOException
    //   5	15	35	finally
  }

  private static boolean shouldStopLoading(CopyListener paramCopyListener, int paramInt1, int paramInt2)
  {
    if ((paramCopyListener != null) && (!paramCopyListener.onBytesCopied(paramInt1, paramInt2)) && (paramInt1 * 100 / paramInt2 < 75));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static abstract interface CopyListener
  {
    public abstract boolean onBytesCopied(int paramInt1, int paramInt2);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.utils.IoUtils
 * JD-Core Version:    0.6.0
 */