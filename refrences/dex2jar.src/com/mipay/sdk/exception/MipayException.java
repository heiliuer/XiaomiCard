package com.mipay.sdk.exception;

import android.os.Bundle;

public class MipayException extends Exception
{
  private static final long serialVersionUID = 1L;
  private int mErrorCode;
  private Bundle mErrorResult;

  public MipayException(int paramInt, String paramString)
  {
    this(paramInt, paramString, null);
  }

  public MipayException(int paramInt, String paramString, Bundle paramBundle)
  {
    super(paramString);
    this.mErrorCode = paramInt;
    if (paramBundle == null);
    for (this.mErrorResult = new Bundle(); ; this.mErrorResult = paramBundle)
      return;
  }

  public MipayException(int paramInt, Throwable paramThrowable)
  {
    super(paramThrowable);
    this.mErrorCode = paramInt;
    this.mErrorResult = new Bundle();
  }

  public int getError()
  {
    return this.mErrorCode;
  }

  public Bundle getErrorResult()
  {
    return this.mErrorResult;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.mipay.sdk.exception.MipayException
 * JD-Core Version:    0.6.0
 */