package com.tsmclient.smartcard.handler;

import android.text.TextUtils;
import android.util.Log;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.apdu.ReadBinaryCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.model.TradeLog;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BMACCardHandler extends BaseTransCardHandler
{
  private static final ByteArray BMAC_AID;
  private static final ByteArray FCI;
  private static final ByteArray PRE_SELECT_CMD;
  private static final ByteArray READ_BLACK_LIST_CMD;
  private static final ByteArray READ_CARD_STATAUS_CMD;
  private static final ByteArray READ_OVERDRAW_CMD;
  private static final ByteArray SELECT_DDF_CMD;
  private static final String TAG = "BMACCardHandler";

  static
  {
    byte[] arrayOfByte1 = new byte[18];
    arrayOfByte1[0] = 111;
    arrayOfByte1[1] = 16;
    arrayOfByte1[2] = -124;
    arrayOfByte1[3] = 14;
    arrayOfByte1[4] = 49;
    arrayOfByte1[5] = 80;
    arrayOfByte1[6] = 65;
    arrayOfByte1[7] = 89;
    arrayOfByte1[8] = 46;
    arrayOfByte1[9] = 83;
    arrayOfByte1[10] = 89;
    arrayOfByte1[11] = 83;
    arrayOfByte1[12] = 46;
    arrayOfByte1[13] = 68;
    arrayOfByte1[14] = 68;
    arrayOfByte1[15] = 70;
    arrayOfByte1[16] = 48;
    arrayOfByte1[17] = 49;
    FCI = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[7];
    arrayOfByte2[0] = 0;
    arrayOfByte2[1] = -92;
    arrayOfByte2[2] = 0;
    arrayOfByte2[3] = 0;
    arrayOfByte2[4] = 2;
    arrayOfByte2[5] = 16;
    arrayOfByte2[6] = 1;
    SELECT_DDF_CMD = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[8];
    arrayOfByte3[0] = -111;
    arrayOfByte3[1] = 86;
    arrayOfByte3[2] = 0;
    arrayOfByte3[3] = 0;
    arrayOfByte3[4] = 20;
    arrayOfByte3[5] = 1;
    arrayOfByte3[6] = 0;
    arrayOfByte3[7] = 1;
    BMAC_AID = ByteArray.wrap(arrayOfByte3);
    byte[] arrayOfByte4 = new byte[7];
    arrayOfByte4[0] = 0;
    arrayOfByte4[1] = -92;
    arrayOfByte4[2] = 0;
    arrayOfByte4[3] = 0;
    arrayOfByte4[4] = 2;
    arrayOfByte4[5] = 63;
    arrayOfByte4[6] = 0;
    PRE_SELECT_CMD = ByteArray.wrap(arrayOfByte4);
    byte[] arrayOfByte5 = new byte[5];
    arrayOfByte5[0] = 0;
    arrayOfByte5[1] = -80;
    arrayOfByte5[2] = -123;
    arrayOfByte5[3] = 5;
    arrayOfByte5[4] = 4;
    READ_OVERDRAW_CMD = ByteArray.wrap(arrayOfByte5);
    byte[] arrayOfByte6 = new byte[5];
    arrayOfByte6[0] = 0;
    arrayOfByte6[1] = -80;
    arrayOfByte6[2] = -124;
    arrayOfByte6[3] = 9;
    arrayOfByte6[4] = 1;
    READ_CARD_STATAUS_CMD = ByteArray.wrap(arrayOfByte6);
    byte[] arrayOfByte7 = new byte[5];
    arrayOfByte7[0] = 0;
    arrayOfByte7[1] = -80;
    arrayOfByte7[2] = -123;
    arrayOfByte7[3] = 0;
    arrayOfByte7[4] = 1;
    READ_BLACK_LIST_CMD = ByteArray.wrap(arrayOfByte7);
  }

  protected int getAtc()
    throws IOException, UnProcessableCardException
  {
    ReadBinaryCommand localReadBinaryCommand = new ReadBinaryCommand();
    localReadBinaryCommand.setP1(-123);
    byte[] arrayOfByte = transceive(localReadBinaryCommand.toRawAPDU().toBytes());
    assertResponse(arrayOfByte, "failed to get atc");
    return Coder.bytesToInt(arrayOfByte, 3, 2);
  }

  protected int getBalance()
    throws IOException, UnProcessableCardException
  {
    int i = super.getBalance();
    int j = 0;
    assertResponse(transceive(PRE_SELECT_CMD.toBytes()), "select 1PAY.SYS.DDF01 failed.");
    byte[] arrayOfByte = transceive(READ_OVERDRAW_CMD.toBytes());
    assertResponse(arrayOfByte, "failed to read overdraw");
    String str;
    if (arrayOfByte.length > 4)
    {
      str = Coder.bytesToHexString(arrayOfByte).substring(0, 8);
      if (TextUtils.equals(str, "FFFFFFFF"))
        j = 0;
    }
    else
    {
      i -= j;
    }
    while (true)
    {
      return i;
      j = new BigInteger(str, 16).intValue();
      updateCardInfo("overdrawn", String.valueOf(j));
      if (j >= 0)
        break;
    }
  }

  protected HashMap<String, String> getCardNumAndValidDate()
    throws IOException, UnProcessableCardException
  {
    HashMap localHashMap = new HashMap();
    ReadBinaryCommand localReadBinaryCommand = new ReadBinaryCommand();
    localReadBinaryCommand.setP1(-124);
    byte[] arrayOfByte = transceive(localReadBinaryCommand.toRawAPDU().toBytes());
    assertResponse(arrayOfByte, "failed to get card num");
    String str1 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 0, 8).toBytes());
    String str2 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 24, 4).toBytes());
    String str3 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 28, 4).toBytes());
    localHashMap.put("account_num", str1);
    localHashMap.put("valid_start", str2);
    localHashMap.put("valid_end", str3);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    Date localDate = new Date();
    localSimpleDateFormat.setLenient(false);
    try
    {
      if (localDate.before(localSimpleDateFormat.parse(str2)))
        localHashMap.put("is_valid_start_date", Boolean.FALSE.toString());
    }
    catch (ParseException localParseException1)
    {
      try
      {
        while (true)
        {
          if (localDate.after(localSimpleDateFormat.parse(str3)))
            localHashMap.put("is_valid_end_date", Boolean.FALSE.toString());
          return localHashMap;
          localParseException1 = localParseException1;
          localHashMap.put("is_valid_start_date", Boolean.FALSE.toString());
          Log.e("BMACCardHandler", "parse start date failed.", localParseException1);
        }
      }
      catch (ParseException localParseException2)
      {
        while (true)
        {
          localHashMap.put("is_valid_end_date", Boolean.FALSE.toString());
          Log.e("BMACCardHandler", "parse end date failed.", localParseException2);
        }
      }
    }
  }

  protected String getCardType()
  {
    return "001";
  }

  protected ByteArray getConsumeTag()
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = 6;
    return ByteArray.wrap(arrayOfByte);
  }

  protected void otherVerify()
    throws IOException, UnProcessableCardException
  {
    assertResponse(transceive(SELECT_DDF_CMD.toBytes()), "failed to verify card");
  }

  protected void readCardStatus(Map<String, String> paramMap)
    throws IOException, UnProcessableCardException
  {
    int i = 1;
    super.readCardStatus(paramMap);
    if (paramMap == null)
      paramMap = new HashMap();
    byte[] arrayOfByte1 = transceive(READ_CARD_STATAUS_CMD.toBytes());
    assertResponse(arrayOfByte1, "read card status failed.");
    int j;
    if (Coder.bytesToInt(arrayOfByte1, 0, i) == 2)
    {
      j = 0;
      paramMap.put("status", String.valueOf(j));
      byte[] arrayOfByte2 = transceive(READ_BLACK_LIST_CMD.toBytes());
      assertResponse(arrayOfByte2, "read black list failed.");
      if (!TextUtils.equals(Coder.bytesToHexString(arrayOfByte2).substring(0, 2), "A5"))
        break label122;
    }
    while (true)
    {
      paramMap.put("in_black_list", String.valueOf(i));
      return;
      j = i;
      break;
      label122: i = 0;
    }
  }

  protected void readRecord(ArrayList<TradeLog> paramArrayList, boolean paramBoolean)
    throws IOException
  {
    byte[] arrayOfByte = transceive(SELECT_DDF_CMD.toBytes());
    try
    {
      assertResponse(arrayOfByte, "failed to verify card");
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
    if (this.mInternalRead)
      localSelectCommand.setData(BMAC_AID);
    byte[] arrayOfByte;
    while (true)
    {
      arrayOfByte = transceive(localSelectCommand.toRawAPDU().toBytes());
      if ((arrayOfByte != null) && (arrayOfByte.length >= 2))
        break;
      throw new IOException("failed to select BMAC");
      localSelectCommand.setData(AID_PSE);
    }
    if ((!this.mInternalRead) && (!ByteArray.equals(FCI, ByteArray.wrap(arrayOfByte, 0, -2 + arrayOfByte.length))))
      throw new UnProcessableCardException("BMACCardHandler: unsupported card type");
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.BMACCardHandler
 * JD-Core Version:    0.6.0
 */