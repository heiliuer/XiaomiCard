package com.tsmclient.smartcard.handler;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.apdu.ReadRecordCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.model.TradeLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HZTCardHandler extends BaseTransCardHandler
{
  private static final ByteArray AID_APPLET;
  private static final ByteArray AID_WALLET;
  private static final ByteArray GET_BALANCE_CMD;
  private static final ByteArray GET_CARDNUM_CMD;

  static
  {
    byte[] arrayOfByte1 = new byte[14];
    arrayOfByte1[0] = 49;
    arrayOfByte1[1] = 80;
    arrayOfByte1[2] = 85;
    arrayOfByte1[3] = 66;
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
    AID_APPLET = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[10];
    arrayOfByte2[0] = -96;
    arrayOfByte2[1] = 0;
    arrayOfByte2[2] = 0;
    arrayOfByte2[3] = 0;
    arrayOfByte2[4] = 3;
    arrayOfByte2[5] = -122;
    arrayOfByte2[6] = -104;
    arrayOfByte2[7] = 7;
    arrayOfByte2[8] = 1;
    arrayOfByte2[9] = 1;
    AID_WALLET = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[5];
    arrayOfByte3[0] = 0;
    arrayOfByte3[1] = -80;
    arrayOfByte3[2] = -107;
    arrayOfByte3[3] = 12;
    arrayOfByte3[4] = 8;
    GET_CARDNUM_CMD = ByteArray.wrap(arrayOfByte3);
    byte[] arrayOfByte4 = new byte[5];
    arrayOfByte4[0] = -128;
    arrayOfByte4[1] = 92;
    arrayOfByte4[2] = 0;
    arrayOfByte4[3] = 2;
    arrayOfByte4[4] = 4;
    GET_BALANCE_CMD = ByteArray.wrap(arrayOfByte4);
  }

  private void readRecord(ArrayList<TradeLog> paramArrayList, byte paramByte)
    throws IOException
  {
    ReadRecordCommand localReadRecordCommand = new ReadRecordCommand();
    localReadRecordCommand.setP1(1);
    localReadRecordCommand.setP2(paramByte);
    byte[] arrayOfByte1 = transceive(localReadRecordCommand.toRawAPDU().toBytes());
    int i;
    byte[] arrayOfByte2;
    if (arrayOfByte1 != null)
    {
      if (arrayOfByte1.length < 2)
        throw new IOException("failed to get record");
      i = 1;
      localReadRecordCommand.setP1(Coder.toBytesLow(i));
      arrayOfByte2 = transceive(localReadRecordCommand.toRawAPDU().toBytes());
      if ((arrayOfByte2 != null) && (ByteArray.wrap(arrayOfByte2).length() != 2))
        if ((!ByteArray.equals(STATUS_RECORD_END, ByteArray.wrap(arrayOfByte2))) && (i < 11) && (!ByteArray.equals(EMPTY_RECORD, ByteArray.wrap(arrayOfByte2, 0, -2 + arrayOfByte2.length))) && (!ByteArray.equals(EMPTY_RECORD_TWO, ByteArray.wrap(arrayOfByte2, 0, -2 + arrayOfByte2.length))))
          break label159;
    }
    while (true)
    {
      return;
      label159: processPerLog(arrayOfByte2, paramArrayList);
      i++;
      localReadRecordCommand.setP1(Coder.toBytesLow(i));
      arrayOfByte2 = transceive(localReadRecordCommand.toRawAPDU().toBytes());
      if (arrayOfByte2 != null)
        break;
    }
  }

  protected int getBalance()
    throws IOException, UnProcessableCardException
  {
    byte[] arrayOfByte = transceive(GET_BALANCE_CMD.toBytes());
    assertResponse(arrayOfByte, "failed to get balance");
    return Coder.bytesToInt(ByteArray.wrap(arrayOfByte, 0, 4).toBytes());
  }

  protected Map<String, String> getCardNumAndValidDate()
    throws IOException, UnProcessableCardException
  {
    HashMap localHashMap = new HashMap();
    byte[] arrayOfByte = transceive(GET_CARDNUM_CMD.toBytes());
    assertResponse(arrayOfByte, "failed to get card num");
    localHashMap.put("account_num", Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 0, -2 + arrayOfByte.length).toBytes()));
    return localHashMap;
  }

  protected String getCardType()
  {
    return "019";
  }

  protected void readRecord(ArrayList<TradeLog> paramArrayList, boolean paramBoolean)
    throws IOException
  {
    readRecord(paramArrayList, -44);
    readRecord(paramArrayList, -124);
    Collections.sort(paramArrayList);
  }

  protected void selectVerify()
    throws IOException, UnProcessableCardException
  {
    SelectCommand localSelectCommand = new SelectCommand();
    localSelectCommand.setP1(4);
    localSelectCommand.setData(AID_APPLET);
    assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select HZT AID_APPLET");
    localSelectCommand.setData(AID_WALLET);
    assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select wallet applet");
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.HZTCardHandler
 * JD-Core Version:    0.6.0
 */