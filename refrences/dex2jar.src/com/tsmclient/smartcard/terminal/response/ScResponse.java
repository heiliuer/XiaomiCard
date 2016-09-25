package com.tsmclient.smartcard.terminal.response;

import com.tsmclient.smartcard.ByteArray;

public abstract interface ScResponse
{
  public static final ByteArray STATUS_MORE_DATA_AVAILABLE;
  public static final ByteArray STATUS_OK;
  public static final ByteArray STATUS_OPERATION_FAILED;
  public static final ByteArray STATUS_REFERENCE_NOT_FOUND;

  static
  {
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = -112;
    arrayOfByte1[1] = 0;
    STATUS_OK = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[2];
    arrayOfByte2[0] = 99;
    arrayOfByte2[1] = 16;
    STATUS_MORE_DATA_AVAILABLE = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[2];
    arrayOfByte3[0] = 99;
    arrayOfByte3[1] = 32;
    STATUS_OPERATION_FAILED = ByteArray.wrap(arrayOfByte3);
    byte[] arrayOfByte4 = new byte[2];
    arrayOfByte4[0] = 106;
    arrayOfByte4[1] = -120;
    STATUS_REFERENCE_NOT_FOUND = ByteArray.wrap(arrayOfByte4);
  }

  public abstract ByteArray getData();

  public abstract ByteArray getStatus();

  public abstract byte[] toBytes();
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.terminal.response.ScResponse
 * JD-Core Version:    0.6.0
 */