package com.miui.tsmclientsdk;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public abstract interface MiTsmFuture<V>
{
  public abstract boolean cancel(boolean paramBoolean);

  public abstract V getResult()
    throws OperationCanceledException, IOException, ExecutionException;

  public abstract V getResult(long paramLong, TimeUnit paramTimeUnit)
    throws OperationCanceledException, IOException, ExecutionException;

  public abstract boolean isCancelled();

  public abstract boolean isDone();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclientsdk.MiTsmFuture
 * JD-Core Version:    0.6.0
 */