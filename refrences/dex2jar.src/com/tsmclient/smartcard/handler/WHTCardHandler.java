package com.tsmclient.smartcard.handler;

import android.os.Bundle;
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

public class WHTCardHandler extends BaseTransCardHandler
{
  private static final ByteArray PIN_AUTH_CMD;
  private static final ByteArray SELECT_MF;
  private static final ByteArray WHT_AID;
  private static final ByteArray WHT_INTERNAL_AID;

  static
  {
    byte[] arrayOfByte1 = new byte[9];
    arrayOfByte1[0] = 65;
    arrayOfByte1[1] = 80;
    arrayOfByte1[2] = 49;
    arrayOfByte1[3] = 46;
    arrayOfByte1[4] = 87;
    arrayOfByte1[5] = 72;
    arrayOfByte1[6] = 67;
    arrayOfByte1[7] = 84;
    arrayOfByte1[8] = 67;
    WHT_AID = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[9];
    arrayOfByte2[0] = -96;
    arrayOfByte2[1] = 0;
    arrayOfByte2[2] = 0;
    arrayOfByte2[3] = 83;
    arrayOfByte2[4] = 66;
    arrayOfByte2[5] = 87;
    arrayOfByte2[6] = 72;
    arrayOfByte2[7] = 84;
    arrayOfByte2[8] = 75;
    WHT_INTERNAL_AID = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[2];
    arrayOfByte3[0] = 63;
    arrayOfByte3[1] = 0;
    SELECT_MF = ByteArray.wrap(arrayOfByte3);
    byte[] arrayOfByte4 = new byte[8];
    arrayOfByte4[0] = 0;
    arrayOfByte4[1] = 32;
    arrayOfByte4[2] = 0;
    arrayOfByte4[3] = 0;
    arrayOfByte4[4] = 3;
    arrayOfByte4[5] = 18;
    arrayOfByte4[6] = 52;
    arrayOfByte4[7] = 86;
    PIN_AUTH_CMD = ByteArray.wrap(arrayOfByte4);
  }

  private static String getCardNum(byte[] paramArrayOfByte)
  {
    String str1 = null;
    String str2 = Coder.bytesToHexString(paramArrayOfByte);
    if ((str2 != null) && (str2.length() == 16))
    {
      String str3 = str2.substring(7);
      int i = 0;
      int j = 0;
      if (j < str3.length())
      {
        int m = Integer.valueOf(String.valueOf(str3.charAt(j)), 16).intValue();
        if (j == 0)
          i = m;
        while (true)
        {
          j++;
          break;
          i ^= m;
        }
      }
      int k = i % 10;
      str1 = str3 + k;
    }
    return str1;
  }

  protected Bundle doHandleCard()
    throws IOException, UnProcessableCardException
  {
    ArrayList localArrayList = new ArrayList();
    Bundle localBundle = new Bundle();
    selectVerify();
    int i = getBalance();
    otherVerify();
    HashMap localHashMap = getCardNumAndValidDate();
    readRecord(localArrayList, false);
    localBundle.putBoolean("success", true);
    localBundle.putInt("card_type", 2);
    localBundle.putString("card_id", getCardType());
    if (localHashMap.get("account_num") != null)
      localBundle.putString("account_num", (String)localHashMap.get("account_num"));
    if (localHashMap.get("valid_start") != null)
      localBundle.putString("valid_start", (String)localHashMap.get("valid_start"));
    if (localHashMap.get("valid_end") != null)
      localBundle.putString("valid_end", (String)localHashMap.get("valid_end"));
    localBundle.putInt("e_balance", i);
    localBundle.putParcelableArrayList("trade_log", localArrayList);
    return localBundle;
  }

  protected HashMap<String, String> getCardNumAndValidDate()
    throws IOException, UnProcessableCardException
  {
    if (this.mInternalRead)
      assertResponse(transceive(PIN_AUTH_CMD.toBytes()), "failed to auth pin");
    HashMap localHashMap = new HashMap();
    ReadBinaryCommand localReadBinaryCommand = new ReadBinaryCommand();
    if (this.mInternalRead)
    {
      localReadBinaryCommand.setP1(-107);
      byte[] arrayOfByte3 = transceive(localReadBinaryCommand.toRawAPDU().toBytes());
      assertResponse(arrayOfByte3, "failed to get card num");
      String str4 = getCardNum(ByteArray.wrap(arrayOfByte3, 12, 8).toBytes());
      String str5 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte3, 20, 4).toBytes());
      String str6 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte3, 24, 4).toBytes());
      localHashMap.put("account_num", str4);
      localHashMap.put("valid_start", str5);
      localHashMap.put("valid_end", str6);
    }
    while (true)
    {
      return localHashMap;
      localReadBinaryCommand.setP1(-118);
      byte[] arrayOfByte1 = transceive(localReadBinaryCommand.toRawAPDU().toBytes());
      assertResponse(arrayOfByte1, "failed to get card num");
      String str1 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte1, 0, 5).toBytes());
      localReadBinaryCommand.setP1(-123);
      byte[] arrayOfByte2 = transceive(localReadBinaryCommand.toRawAPDU().toBytes());
      assertResponse(arrayOfByte2, "failed to get valid time");
      String str2 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte2, 20, 4).toBytes());
      String str3 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte2, 16, 4).toBytes());
      localHashMap.put("account_num", str1);
      localHashMap.put("valid_start", str2);
      localHashMap.put("valid_end", str3);
    }
  }

  protected String getCardType()
  {
    return "005";
  }

  protected void otherVerify()
    throws IOException, UnProcessableCardException
  {
    if (!this.mInternalRead)
    {
      SelectCommand localSelectCommand = new SelectCommand();
      localSelectCommand.setP1(0);
      localSelectCommand.setData(SELECT_MF);
      assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select MF");
    }
  }

  protected void readRecord(ArrayList<TradeLog> paramArrayList, boolean paramBoolean)
    throws IOException
  {
    if (this.mInternalRead)
      super.readRecord(paramArrayList, paramBoolean);
  }

  protected void selectVerify()
    throws IOException, UnProcessableCardException
  {
    SelectCommand localSelectCommand = new SelectCommand();
    localSelectCommand.setP1(4);
    if (this.mInternalRead);
    for (ByteArray localByteArray = WHT_INTERNAL_AID; ; localByteArray = WHT_AID)
    {
      localSelectCommand.setData(localByteArray);
      assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select WHT AID");
      if (this.mInternalRead)
      {
        localSelectCommand.setP1(0);
        byte[] arrayOfByte = new byte[2];
        arrayOfByte[0] = 16;
        arrayOfByte[1] = 1;
        localSelectCommand.setData(ByteArray.wrap(arrayOfByte));
        assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select 10 01");
      }
      return;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.WHTCardHandler
 * JD-Core Version:    0.6.0
 */