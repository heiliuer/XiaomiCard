package com.tsmclient.smartcard.handler;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.apdu.ReadBinaryCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.model.TradeLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SuZhouTongCardHandler extends BaseTransCardHandler
{
  private static final ByteArray GET_BALANCE_CMD;
  private static final ByteArray PIN_AUTH_CMD;
  private static final ByteArray SUZHOUTONG_AID;

  static
  {
    byte[] arrayOfByte1 = new byte[8];
    arrayOfByte1[0] = 83;
    arrayOfByte1[1] = 85;
    arrayOfByte1[2] = 88;
    arrayOfByte1[3] = 73;
    arrayOfByte1[4] = 78;
    arrayOfByte1[5] = 46;
    arrayOfByte1[6] = 77;
    arrayOfByte1[7] = 70;
    SUZHOUTONG_AID = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[5];
    arrayOfByte2[0] = -128;
    arrayOfByte2[1] = 92;
    arrayOfByte2[2] = 0;
    arrayOfByte2[3] = 1;
    arrayOfByte2[4] = 4;
    GET_BALANCE_CMD = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[8];
    arrayOfByte3[0] = 0;
    arrayOfByte3[1] = 32;
    arrayOfByte3[2] = 0;
    arrayOfByte3[3] = 0;
    arrayOfByte3[4] = 3;
    arrayOfByte3[5] = 18;
    arrayOfByte3[6] = 52;
    arrayOfByte3[7] = 86;
    PIN_AUTH_CMD = ByteArray.wrap(arrayOfByte3);
  }

  protected int getBalance()
    throws IOException, UnProcessableCardException
  {
    assertResponse(transceive(PIN_AUTH_CMD.toBytes()), "failed to auth pin");
    byte[] arrayOfByte = transceive(GET_BALANCE_CMD.toBytes());
    assertResponse(arrayOfByte, "failed to get balance");
    return Coder.bytesToInt(ByteArray.wrap(arrayOfByte, 0, 4).toBytes());
  }

  protected Map<String, String> getCardNumAndValidDate()
    throws IOException, UnProcessableCardException
  {
    HashMap localHashMap = new HashMap();
    ReadBinaryCommand localReadBinaryCommand = new ReadBinaryCommand();
    localReadBinaryCommand.setP1(-107);
    byte[] arrayOfByte = transceive(localReadBinaryCommand.toRawAPDU().toBytes());
    assertResponse(arrayOfByte, "failed to get card num");
    String str1 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 0, 8).toBytes());
    String str2 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 20, 4).toBytes());
    String str3 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 24, 4).toBytes());
    localHashMap.put("account_num", str1);
    localHashMap.put("valid_start", str2);
    localHashMap.put("valid_end", str3);
    return localHashMap;
  }

  protected String getCardType()
  {
    return "019";
  }

  protected void readRecord(ArrayList<TradeLog> paramArrayList, boolean paramBoolean)
    throws IOException
  {
    byte[] arrayOfByte = transceive(PIN_AUTH_CMD.toBytes());
    try
    {
      assertResponse(arrayOfByte, "failed to auth pin");
      super.readRecord(paramArrayList, paramBoolean);
      label24: return;
    }
    catch (UnProcessableCardException localUnProcessableCardException)
    {
      break label24;
    }
  }

  protected void selectVerify()
    throws IOException, UnProcessableCardException
  {
    SelectCommand localSelectCommand = new SelectCommand();
    localSelectCommand.setP1(4);
    localSelectCommand.setData(SUZHOUTONG_AID);
    assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select SUZHOUTONG AID");
    localSelectCommand.setP1(0);
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[0] = -33;
    arrayOfByte[1] = 1;
    localSelectCommand.setData(ByteArray.wrap(arrayOfByte));
    assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select DF 01");
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.SuZhouTongCardHandler
 * JD-Core Version:    0.6.0
 */