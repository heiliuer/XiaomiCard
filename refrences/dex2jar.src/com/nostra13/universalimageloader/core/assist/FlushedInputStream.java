package com.nostra13.universalimageloader.core.assist;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FlushedInputStream extends FilterInputStream
{
  public FlushedInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }

  public long skip(long paramLong)
    throws IOException
  {
    long l1 = 0L;
    while (true)
    {
      if (l1 < paramLong)
      {
        l2 = this.in.skip(paramLong - l1);
        if (l2 != 0L)
          break label39;
        if (read() >= 0);
      }
      else
      {
        return l1;
      }
      long l2 = 1L;
      label39: l1 += l2;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.assist.FlushedInputStream
 * JD-Core Version:    0.6.0
 */