package com.tsmclient.smartcard.handler;

import android.text.TextUtils;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.apdu.ReadBinaryCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.model.TradeLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LingNanCardHandler extends BaseTransCardHandler
{
  private static final ByteArray LNT_AID;
  private static final ByteArray LNT_INTERNAL_AID;
  private static final ByteArray LNT_INTERNAL_WALLET_AID;
  private static final String TAG = "LingNanCardHandler";
  private final ByteArray READ_BALANCE_CMD;

  static
  {
    byte[] arrayOfByte1 = new byte[8];
    arrayOfByte1[0] = 80;
    arrayOfByte1[1] = 65;
    arrayOfByte1[2] = 89;
    arrayOfByte1[3] = 46;
    arrayOfByte1[4] = 65;
    arrayOfByte1[5] = 80;
    arrayOfByte1[6] = 80;
    arrayOfByte1[7] = 89;
    LNT_AID = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[8];
    arrayOfByte2[0] = 89;
    arrayOfByte2[1] = 67;
    arrayOfByte2[2] = 84;
    arrayOfByte2[3] = 46;
    arrayOfByte2[4] = 85;
    arrayOfByte2[5] = 83;
    arrayOfByte2[6] = 69;
    arrayOfByte2[7] = 82;
    LNT_INTERNAL_AID = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[2];
    arrayOfByte3[0] = -35;
    arrayOfByte3[1] = -15;
    LNT_INTERNAL_WALLET_AID = ByteArray.wrap(arrayOfByte3);
  }

  public LingNanCardHandler()
  {
    byte[] arrayOfByte = new byte[5];
    arrayOfByte[0] = -128;
    arrayOfByte[1] = 92;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = 2;
    arrayOfByte[4] = 4;
    this.READ_BALANCE_CMD = ByteArray.wrap(arrayOfByte);
  }

  protected int getBalance()
    throws IOException
  {
    SelectCommand localSelectCommand = new SelectCommand();
    localSelectCommand.setP1(0);
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = -83;
    arrayOfByte1[1] = -13;
    localSelectCommand.setData(ByteArray.wrap(arrayOfByte1));
    byte[] arrayOfByte2 = transceive(localSelectCommand.toRawAPDU().toBytes());
    if (!ByteArray.equals(STATUS_OK, ByteArray.wrap(arrayOfByte2, -2 + arrayOfByte2.length, 2)));
    for (int i = -999; ; i = Coder.bytesToInt(transceive(this.READ_BALANCE_CMD.toBytes()), 0, 4))
      return i;
  }

  protected Map<String, String> getCardNumAndValidDate()
    throws IOException, UnProcessableCardException
  {
    HashMap localHashMap = new HashMap();
    ReadBinaryCommand localReadBinaryCommand = new ReadBinaryCommand();
    localReadBinaryCommand.setP1(-107);
    byte[] arrayOfByte = transceive(localReadBinaryCommand.toRawAPDU().toBytes());
    assertResponse(arrayOfByte, "failed to get card num");
    String str1 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 53, 5).toBytes());
    String str2 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 23, 4).toBytes());
    String str3 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 27, 4).toBytes());
    localHashMap.put("account_num", str1);
    localHashMap.put("valid_start", str2);
    localHashMap.put("valid_end", str3);
    return localHashMap;
  }

  protected String getCardType()
  {
    return "013";
  }

  protected ByteArray getConsumeTag()
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[0] = 9;
    arrayOfByte[1] = 6;
    return ByteArray.wrap(arrayOfByte);
  }

  protected void readRecord(ArrayList<TradeLog> paramArrayList, boolean paramBoolean)
    throws IOException
  {
    SelectCommand localSelectCommand = new SelectCommand();
    localSelectCommand.setP1(0);
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 24;
    localSelectCommand.setData(ByteArray.wrap(arrayOfByte1));
    byte[] arrayOfByte2 = transceive(localSelectCommand.toRawAPDU().toBytes());
    if (!ByteArray.equals(STATUS_OK, ByteArray.wrap(arrayOfByte2, -2 + arrayOfByte2.length, 2)))
      throw new IOException("failed to get record");
    super.readRecord(paramArrayList, true);
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      TradeLog localTradeLog = (TradeLog)localIterator.next();
      String str = localTradeLog.getTradeDate();
      if ((TextUtils.isEmpty(str)) || (str.length() <= 4))
        continue;
      localTradeLog.setTradeDate(str.substring(4, str.length()));
    }
  }

  protected void selectVerify()
    throws IOException, UnProcessableCardException
  {
    SelectCommand localSelectCommand = new SelectCommand();
    localSelectCommand.setP1(4);
    localSelectCommand.setData(LNT_INTERNAL_AID);
    byte[] arrayOfByte = transceive(localSelectCommand.toRawAPDU().toBytes());
    if ((arrayOfByte == null) || (arrayOfByte.length < 2))
      throw new IOException("failed to select LingNanTong AID");
    localSelectCommand.setP1(0);
    localSelectCommand.setData(LNT_INTERNAL_WALLET_AID);
    assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select LingNanTong AID");
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.LingNanCardHandler
 * JD-Core Version:    0.6.0
 */