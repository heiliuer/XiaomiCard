package com.tsmclient.smartcard.handler;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.ReaderUtil;
import com.tsmclient.smartcard.apdu.ReadBinaryCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.terminal.APDUConstants;
import java.io.IOException;
import java.util.HashMap;

public class SZTCardHandler extends BaseTransCardHandler
{
  private static final ByteArray SZT_FID;
  private static final String TAG = "SZTCardHandler";

  static
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[0] = 16;
    arrayOfByte[1] = 1;
    SZT_FID = ByteArray.wrap(arrayOfByte);
  }

  private String getCardNum(ByteArray paramByteArray)
  {
    return Integer.toString(Coder.bytesToInt(Coder.str2Bcd(ReaderUtil.invertString(Coder.bytesToHexString(paramByteArray.toBytes()), 2))));
  }

  protected int getBalance()
    throws IOException, UnProcessableCardException
  {
    byte[] arrayOfByte = transceive(GET_BALANCE_CMD.toBytes());
    assertResponse(arrayOfByte, "failed to get balance");
    return Coder.bytesToInt(ByteArray.wrap(arrayOfByte, 0, 4).toBytes()) + -2147483648;
  }

  protected HashMap<String, String> getCardNumAndValidDate()
    throws IOException, UnProcessableCardException
  {
    HashMap localHashMap = new HashMap();
    ReadBinaryCommand localReadBinaryCommand = new ReadBinaryCommand();
    localReadBinaryCommand.setP1(-107);
    byte[] arrayOfByte = transceive(localReadBinaryCommand.toRawAPDU().toBytes());
    assertResponse(arrayOfByte, "failed to get card num");
    String str1 = getCardNum(ByteArray.wrap(arrayOfByte, 16, 4));
    String str2 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 20, 4).toBytes());
    String str3 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 24, 4).toBytes());
    localHashMap.put("account_num", str1);
    localHashMap.put("valid_start", str2);
    localHashMap.put("valid_end", str3);
    return localHashMap;
  }

  protected String getCardType()
  {
    return "004";
  }

  protected void selectVerify()
    throws IOException, UnProcessableCardException
  {
    SelectCommand localSelectCommand = new SelectCommand();
    localSelectCommand.setP1(4);
    localSelectCommand.setData(APDUConstants.AID_SZT);
    transceive(localSelectCommand.toRawAPDU().toBytes());
    localSelectCommand.setP1(0);
    localSelectCommand.setData(SZT_FID);
    assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select SZT AID");
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.SZTCardHandler
 * JD-Core Version:    0.6.0
 */