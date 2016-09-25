package com.tsmclient.smartcard.exception;

import java.io.IOException;

public class NfcEeIOException extends IOException
{
  public static final int NFC_DISABLED = 1;
  public static final int NFC_EE_IO_ERROR = 4;
  public static final int NFC_EE_OPENED_ALREADY = 3;
  public static final int NFC_EE_RESTRICTED = 5;
  public static final int NFC_NOT_OPENED = 2;
  private int mErrorCode;

  public NfcEeIOException(String paramString)
  {
    this(paramString, 0);
  }

  public NfcEeIOException(String paramString, int paramInt)
  {
    super(paramString);
    this.mErrorCode = paramInt;
  }

  public int getErrorCode()
  {
    return this.mErrorCode;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.exception.NfcEeIOException
 * JD-Core Version:    0.6.0
 */