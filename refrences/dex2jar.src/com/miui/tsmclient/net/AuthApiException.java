package com.miui.tsmclient.net;

public class AuthApiException extends Exception
{
  public static final int ERROR_CODE_RESOURCE_EXHAUSTED = 205;
  public static final int ERROR_MIUI_VERSION_RESTRICTED = 211;
  public int mErrorCode;
  public String mErrorMsg;

  public AuthApiException()
  {
  }

  public AuthApiException(int paramInt, String paramString)
  {
    this.mErrorCode = paramInt;
    this.mErrorMsg = paramString;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.net.AuthApiException
 * JD-Core Version:    0.6.0
 */