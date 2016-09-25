package com.miui.tsmclient.seitsm.Exception;

public class SeiTSMApiException extends Exception
{
  private int mErrorCode;

  public SeiTSMApiException(int paramInt, String paramString)
  {
    super(paramString);
    this.mErrorCode = paramInt;
  }

  public SeiTSMApiException(String paramString)
  {
    super(paramString);
  }

  public int getErrorCode()
  {
    return this.mErrorCode;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.seitsm.Exception.SeiTSMApiException
 * JD-Core Version:    0.6.0
 */