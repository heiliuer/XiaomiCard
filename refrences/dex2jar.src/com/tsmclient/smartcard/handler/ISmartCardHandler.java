package com.tsmclient.smartcard.handler;

import android.nfc.tech.TagTechnology;
import android.os.Bundle;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.terminal.AbstractTerminal;
import java.io.IOException;

public abstract interface ISmartCardHandler<T extends TagTechnology>
{
  public static final ByteArray AID_PPSE;
  public static final ByteArray AID_PSE;
  public static final ByteArray EMPTY_RECORD;
  public static final ByteArray EMPTY_RECORD_TWO;
  public static final int ERROR_IO = 1;
  public static final int ERROR_NOT_SUPPORTED = 2;
  public static final int ERROR_UNKNOWN = 3;
  public static final ByteArray GET_BALANCE_CMD;
  public static final String KEY_ERROR = "error";
  public static final String KEY_SUCCESS = "success";
  public static final ByteArray STATUS_APP_NOT_FOUND;
  public static final ByteArray STATUS_ERROR_PARAM;
  public static final ByteArray STATUS_OK;
  public static final ByteArray STATUS_RECORD_END;
  public static final int TYPE_ISODEP = 1;
  public static final int TYPE_NFCF = 2;

  static
  {
    byte[] arrayOfByte1 = new byte[14];
    arrayOfByte1[0] = 50;
    arrayOfByte1[1] = 80;
    arrayOfByte1[2] = 65;
    arrayOfByte1[3] = 89;
    arrayOfByte1[4] = 46;
    arrayOfByte1[5] = 83;
    arrayOfByte1[6] = 89;
    arrayOfByte1[7] = 83;
    arrayOfByte1[8] = 46;
    arrayOfByte1[9] = 68;
    arrayOfByte1[10] = 68;
    arrayOfByte1[11] = 70;
    arrayOfByte1[12] = 48;
    arrayOfByte1[13] = 49;
    AID_PPSE = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[14];
    arrayOfByte2[0] = 49;
    arrayOfByte2[1] = 80;
    arrayOfByte2[2] = 65;
    arrayOfByte2[3] = 89;
    arrayOfByte2[4] = 46;
    arrayOfByte2[5] = 83;
    arrayOfByte2[6] = 89;
    arrayOfByte2[7] = 83;
    arrayOfByte2[8] = 46;
    arrayOfByte2[9] = 68;
    arrayOfByte2[10] = 68;
    arrayOfByte2[11] = 70;
    arrayOfByte2[12] = 48;
    arrayOfByte2[13] = 49;
    AID_PSE = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[23];
    arrayOfByte3[0] = 0;
    arrayOfByte3[1] = 0;
    arrayOfByte3[2] = 0;
    arrayOfByte3[3] = 0;
    arrayOfByte3[4] = 0;
    arrayOfByte3[5] = 0;
    arrayOfByte3[6] = 0;
    arrayOfByte3[7] = 0;
    arrayOfByte3[8] = 0;
    arrayOfByte3[9] = 0;
    arrayOfByte3[10] = 0;
    arrayOfByte3[11] = 0;
    arrayOfByte3[12] = 0;
    arrayOfByte3[13] = 0;
    arrayOfByte3[14] = 0;
    arrayOfByte3[15] = 0;
    arrayOfByte3[16] = 0;
    arrayOfByte3[17] = 0;
    arrayOfByte3[18] = 0;
    arrayOfByte3[19] = 0;
    arrayOfByte3[20] = 0;
    arrayOfByte3[21] = 0;
    arrayOfByte3[22] = 0;
    EMPTY_RECORD = ByteArray.wrap(arrayOfByte3);
    byte[] arrayOfByte4 = new byte[23];
    arrayOfByte4[0] = -1;
    arrayOfByte4[1] = -1;
    arrayOfByte4[2] = -1;
    arrayOfByte4[3] = -1;
    arrayOfByte4[4] = -1;
    arrayOfByte4[5] = -1;
    arrayOfByte4[6] = -1;
    arrayOfByte4[7] = -1;
    arrayOfByte4[8] = -1;
    arrayOfByte4[9] = -1;
    arrayOfByte4[10] = -1;
    arrayOfByte4[11] = -1;
    arrayOfByte4[12] = -1;
    arrayOfByte4[13] = -1;
    arrayOfByte4[14] = -1;
    arrayOfByte4[15] = -1;
    arrayOfByte4[16] = -1;
    arrayOfByte4[17] = -1;
    arrayOfByte4[18] = -1;
    arrayOfByte4[19] = -1;
    arrayOfByte4[20] = -1;
    arrayOfByte4[21] = -1;
    arrayOfByte4[22] = -1;
    EMPTY_RECORD_TWO = ByteArray.wrap(arrayOfByte4);
    byte[] arrayOfByte5 = new byte[5];
    arrayOfByte5[0] = -128;
    arrayOfByte5[1] = 92;
    arrayOfByte5[2] = 0;
    arrayOfByte5[3] = 2;
    arrayOfByte5[4] = 4;
    GET_BALANCE_CMD = ByteArray.wrap(arrayOfByte5);
    byte[] arrayOfByte6 = new byte[2];
    arrayOfByte6[0] = -112;
    arrayOfByte6[1] = 0;
    STATUS_OK = ByteArray.wrap(arrayOfByte6);
    byte[] arrayOfByte7 = new byte[2];
    arrayOfByte7[0] = 106;
    arrayOfByte7[1] = -125;
    STATUS_RECORD_END = ByteArray.wrap(arrayOfByte7);
    byte[] arrayOfByte8 = new byte[2];
    arrayOfByte8[0] = 106;
    arrayOfByte8[1] = -122;
    STATUS_ERROR_PARAM = ByteArray.wrap(arrayOfByte8);
    byte[] arrayOfByte9 = new byte[2];
    arrayOfByte9[0] = 106;
    arrayOfByte9[1] = -126;
    STATUS_APP_NOT_FOUND = ByteArray.wrap(arrayOfByte9);
  }

  public abstract int getTechType();

  public abstract Bundle onHandleCard(T paramT)
    throws IOException, UnProcessableCardException;

  public abstract Bundle onHandleCard(AbstractTerminal paramAbstractTerminal)
    throws IOException, UnProcessableCardException;
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.ISmartCardHandler
 * JD-Core Version:    0.6.0
 */