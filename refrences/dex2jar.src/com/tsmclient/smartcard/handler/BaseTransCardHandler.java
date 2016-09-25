package com.tsmclient.smartcard.handler;

import android.nfc.tech.IsoDep;
import android.os.Bundle;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.apdu.ReadRecordCommand;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.model.TradeLog;
import com.tsmclient.smartcard.terminal.AbstractTerminal;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTransCardHandler
  implements ISmartCardHandler<IsoDep>
{
  private Map<String, String> mCardInfoMap = new HashMap();
  protected boolean mInternalRead;
  protected IsoDep mTech;
  protected AbstractTerminal mTerminal;

  private void processAllLog(ByteArray paramByteArray, ArrayList<TradeLog> paramArrayList)
  {
    int i = 0;
    int j = paramByteArray.length() / 23;
    for (int k = 0; ; k++)
    {
      byte[] arrayOfByte;
      if (k < j)
      {
        arrayOfByte = paramByteArray.duplicate(i, 23).toBytes();
        if (!ByteArray.equals(ByteArray.wrap(arrayOfByte), EMPTY_RECORD));
      }
      else
      {
        return;
      }
      processPerLog(arrayOfByte, paramArrayList);
      i += 23;
    }
  }

  protected void assertResponse(byte[] paramArrayOfByte, ByteArray paramByteArray, String paramString)
    throws IOException, UnProcessableCardException
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 2))
      throw new IOException(paramString);
    if (!ByteArray.equals(paramByteArray, ByteArray.wrap(paramArrayOfByte, -2 + paramArrayOfByte.length, 2)))
      throw new UnProcessableCardException(getClass().getSimpleName() + ": unsupported card type");
  }

  protected void assertResponse(byte[] paramArrayOfByte, String paramString)
    throws IOException, UnProcessableCardException
  {
    assertResponse(paramArrayOfByte, STATUS_OK, paramString);
  }

  protected Bundle doHandleCard()
    throws IOException, UnProcessableCardException
  {
    ArrayList localArrayList = new ArrayList();
    Bundle localBundle = new Bundle();
    selectVerify();
    Map localMap = getCardNumAndValidDate();
    this.mCardInfoMap.putAll(localMap);
    readCardStatus(this.mCardInfoMap);
    int i = getAtc();
    otherVerify();
    int j = getBalance();
    readRecord(localArrayList, false);
    localBundle.putBoolean("success", true);
    localBundle.putInt("card_type", 2);
    localBundle.putString("card_id", getCardType());
    if (this.mCardInfoMap.get("account_num") != null)
      localBundle.putString("account_num", (String)this.mCardInfoMap.get("account_num"));
    if (this.mCardInfoMap.get("valid_start") != null)
      localBundle.putString("valid_start", (String)this.mCardInfoMap.get("valid_start"));
    if (this.mCardInfoMap.get("valid_end") != null)
      localBundle.putString("valid_end", (String)this.mCardInfoMap.get("valid_end"));
    if (i != -999)
      localBundle.putInt("atc", i);
    localBundle.putInt("e_balance", j);
    localBundle.putParcelableArrayList("trade_log", localArrayList);
    if (this.mCardInfoMap.containsKey("status"))
      localBundle.putInt("status", Integer.valueOf((String)this.mCardInfoMap.get("status")).intValue());
    if (this.mCardInfoMap.containsKey("in_black_list"))
      localBundle.putInt("in_black_list", Integer.valueOf((String)this.mCardInfoMap.get("in_black_list")).intValue());
    if (this.mCardInfoMap.containsKey("overdrawn"))
      localBundle.putInt("overdrawn", Integer.valueOf((String)this.mCardInfoMap.get("overdrawn")).intValue());
    if (this.mCardInfoMap.containsKey("is_valid_start_date"))
      localBundle.putBoolean("is_valid_start_date", Boolean.valueOf((String)this.mCardInfoMap.get("is_valid_start_date")).booleanValue());
    if (this.mCardInfoMap.containsKey("is_valid_end_date"))
      localBundle.putBoolean("is_valid_end_date", Boolean.valueOf((String)this.mCardInfoMap.get("is_valid_end_date")).booleanValue());
    return localBundle;
  }

  protected int getAtc()
    throws IOException, UnProcessableCardException
  {
    return -999;
  }

  protected int getBalance()
    throws IOException, UnProcessableCardException
  {
    byte[] arrayOfByte = transceive(GET_BALANCE_CMD.toBytes());
    if ((arrayOfByte == null) || (arrayOfByte.length < 2))
      throw new IOException("failed to get balance");
    if (!ByteArray.equals(STATUS_OK, ByteArray.wrap(arrayOfByte, -2 + arrayOfByte.length, 2)));
    for (int i = -999; ; i = Coder.bytesToInt(arrayOfByte, 0, 4))
      return i;
  }

  protected abstract Map<String, String> getCardNumAndValidDate()
    throws IOException, UnProcessableCardException;

  protected abstract String getCardType();

  protected ByteArray getConsumeTag()
  {
    byte[] arrayOfByte = new byte[5];
    arrayOfByte[0] = 10;
    arrayOfByte[1] = 17;
    arrayOfByte[2] = 9;
    arrayOfByte[3] = 6;
    arrayOfByte[4] = 5;
    return ByteArray.wrap(arrayOfByte);
  }

  public int getTechType()
  {
    return 1;
  }

  public Bundle onHandleCard(IsoDep paramIsoDep)
    throws IOException, UnProcessableCardException
  {
    this.mTech = paramIsoDep;
    this.mInternalRead = false;
    return doHandleCard();
  }

  public Bundle onHandleCard(AbstractTerminal paramAbstractTerminal)
    throws IOException, UnProcessableCardException
  {
    this.mTerminal = paramAbstractTerminal;
    this.mInternalRead = true;
    return doHandleCard();
  }

  protected void otherVerify()
    throws IOException, UnProcessableCardException
  {
  }

  protected void processPerLog(byte[] paramArrayOfByte, ArrayList<TradeLog> paramArrayList)
  {
    if (Coder.bytesToInt(paramArrayOfByte, 5, 4) == 0)
      return;
    TradeLog localTradeLog = new TradeLog();
    if (getConsumeTag().contains(paramArrayOfByte[9]))
      localTradeLog.setTradeType(1);
    while (true)
    {
      localTradeLog.setAuAmount(Coder.bytesToInt(paramArrayOfByte, 5, 4));
      localTradeLog.setTradeDate(Coder.bytesToHexString(ByteArray.wrap(paramArrayOfByte, 16, 4).toBytes()));
      localTradeLog.setTradeTime(Coder.bytesToHexString(ByteArray.wrap(paramArrayOfByte, 20, 3).toBytes()));
      paramArrayList.add(localTradeLog);
      break;
      localTradeLog.setTradeType(2);
    }
  }

  protected void readCardStatus(Map<String, String> paramMap)
    throws IOException, UnProcessableCardException
  {
  }

  protected void readRecord(ArrayList<TradeLog> paramArrayList, boolean paramBoolean)
    throws IOException
  {
    ReadRecordCommand localReadRecordCommand = new ReadRecordCommand();
    localReadRecordCommand.setP1(1);
    localReadRecordCommand.setP2(-59);
    byte[] arrayOfByte1 = transceive(localReadRecordCommand.toRawAPDU().toBytes());
    if (arrayOfByte1 != null)
    {
      if (arrayOfByte1.length < 2)
        throw new IOException("failed to get record");
      if ((!ByteArray.equals(STATUS_ERROR_PARAM, ByteArray.wrap(arrayOfByte1, -2 + arrayOfByte1.length, 2))) && (!paramBoolean))
        processAllLog(ByteArray.wrap(arrayOfByte1, 0, -2 + arrayOfByte1.length), paramArrayList);
    }
    else
    {
      return;
      break label146;
    }
    while (true)
    {
      localReadRecordCommand.setP2(-60);
      int i = 1;
      localReadRecordCommand.setP1(Coder.toBytesLow(i));
      byte[] arrayOfByte2 = transceive(localReadRecordCommand.toRawAPDU().toBytes());
      if ((arrayOfByte2 == null) || (ByteArray.wrap(arrayOfByte2).length() == 2))
        continue;
      label146: if ((ByteArray.equals(STATUS_RECORD_END, ByteArray.wrap(arrayOfByte2))) || (i >= 11) || (ByteArray.equals(EMPTY_RECORD, ByteArray.wrap(arrayOfByte2, 0, -2 + arrayOfByte2.length))) || (ByteArray.equals(EMPTY_RECORD_TWO, ByteArray.wrap(arrayOfByte2, 0, -2 + arrayOfByte2.length))))
        continue;
      processPerLog(arrayOfByte2, paramArrayList);
      i++;
      localReadRecordCommand.setP1(Coder.toBytesLow(i));
      arrayOfByte2 = transceive(localReadRecordCommand.toRawAPDU().toBytes());
      if (arrayOfByte2 != null)
        break;
    }
  }

  protected abstract void selectVerify()
    throws IOException, UnProcessableCardException;

  protected byte[] transceive(byte[] paramArrayOfByte)
    throws IOException
  {
    if (this.mInternalRead);
    for (byte[] arrayOfByte = this.mTerminal.transmit(paramArrayOfByte).toBytes(); ; arrayOfByte = this.mTech.transceive(paramArrayOfByte))
      return arrayOfByte;
  }

  protected void updateCardInfo(String paramString1, String paramString2)
  {
    this.mCardInfoMap.put(paramString1, paramString2);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.BaseTransCardHandler
 * JD-Core Version:    0.6.0
 */