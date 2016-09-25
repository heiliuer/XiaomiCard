package com.tsmclient.smartcard.handler;

import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.CardConstants;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.apdu.GPOCommand;
import com.tsmclient.smartcard.apdu.GetDataCommand;
import com.tsmclient.smartcard.apdu.ReadRecordCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.InvalidTLVException;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.model.TradeLog;
import com.tsmclient.smartcard.terminal.APDUConstants;
import com.tsmclient.smartcard.terminal.AbstractTerminal;
import com.tsmclient.smartcard.terminal.CommandApdu;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import com.tsmclient.smartcard.tlv.ITLVObject;
import com.tsmclient.smartcard.tlv.ITLVValue;
import com.tsmclient.smartcard.tlv.TLVParser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class BankCardHandler
  implements ISmartCardHandler<IsoDep>
{
  private static final ByteArray AU_AMOUNT;
  private static final ByteArray AU_AMOUNT_OTHER;
  private static final ByteArray CUR_CODE;
  public static final String EXTRAS_KEY_PAN_LIST = "extras_key_pan_list";
  private static final ByteArray NOT_EXISTS;
  private static final ByteArray RANDOM_NUMBER;
  private static final String TAG = "BankCardHandler";
  private static final ByteArray TAG_AEF_ENTRANCE;
  private static final ByteArray TAG_AID;
  private static final ByteArray TAG_APP;
  private static final ByteArray TAG_BANK_CUSTOM_DATA;
  private static final ByteArray TAG_CARD_NUM;
  private static final ByteArray TAG_FCI_DATA_TEMPLATE;
  private static final ByteArray TAG_PDOL;
  private static final ByteArray TER_TRADE_TYPE;
  private static final ByteArray TRADE_TYPE;
  private static final ByteArray TVR;
  public static final ByteArray UNION_PAY_AID;
  public static final ByteArray VISA_AID;
  public static final ByteArray VISA_CREDIT;
  public static final ByteArray VISA_DEBIT;
  private int mCardScheme;

  static
  {
    byte[] arrayOfByte1 = new byte[5];
    arrayOfByte1[0] = -96;
    arrayOfByte1[1] = 0;
    arrayOfByte1[2] = 0;
    arrayOfByte1[3] = 0;
    arrayOfByte1[4] = 3;
    VISA_AID = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[5];
    arrayOfByte2[0] = -96;
    arrayOfByte2[1] = 0;
    arrayOfByte2[2] = 0;
    arrayOfByte2[3] = 3;
    arrayOfByte2[4] = 51;
    UNION_PAY_AID = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[5];
    arrayOfByte3[0] = 68;
    arrayOfByte3[1] = 69;
    arrayOfByte3[2] = 66;
    arrayOfByte3[3] = 73;
    arrayOfByte3[4] = 84;
    VISA_DEBIT = ByteArray.wrap(arrayOfByte3);
    byte[] arrayOfByte4 = new byte[6];
    arrayOfByte4[0] = 67;
    arrayOfByte4[1] = 82;
    arrayOfByte4[2] = 69;
    arrayOfByte4[3] = 68;
    arrayOfByte4[4] = 73;
    arrayOfByte4[5] = 84;
    VISA_CREDIT = ByteArray.wrap(arrayOfByte4);
    byte[] arrayOfByte5 = new byte[1];
    arrayOfByte5[0] = -91;
    TAG_FCI_DATA_TEMPLATE = ByteArray.wrap(arrayOfByte5);
    byte[] arrayOfByte6 = new byte[1];
    arrayOfByte6[0] = 97;
    TAG_AEF_ENTRANCE = ByteArray.wrap(arrayOfByte6);
    byte[] arrayOfByte7 = new byte[1];
    arrayOfByte7[0] = 79;
    TAG_AID = ByteArray.wrap(arrayOfByte7);
    byte[] arrayOfByte8 = new byte[1];
    arrayOfByte8[0] = 80;
    TAG_APP = ByteArray.wrap(arrayOfByte8);
    byte[] arrayOfByte9 = new byte[1];
    arrayOfByte9[0] = 87;
    TAG_CARD_NUM = ByteArray.wrap(arrayOfByte9);
    byte[] arrayOfByte10 = new byte[2];
    arrayOfByte10[0] = -65;
    arrayOfByte10[1] = 12;
    TAG_BANK_CUSTOM_DATA = ByteArray.wrap(arrayOfByte10);
    byte[] arrayOfByte11 = new byte[2];
    arrayOfByte11[0] = -97;
    arrayOfByte11[1] = 56;
    TAG_PDOL = ByteArray.wrap(arrayOfByte11);
    byte[] arrayOfByte12 = new byte[4];
    arrayOfByte12[0] = 126;
    arrayOfByte12[1] = 0;
    arrayOfByte12[2] = 0;
    arrayOfByte12[3] = 0;
    TER_TRADE_TYPE = ByteArray.wrap(arrayOfByte12);
    byte[] arrayOfByte13 = new byte[6];
    arrayOfByte13[0] = 0;
    arrayOfByte13[1] = 0;
    arrayOfByte13[2] = 0;
    arrayOfByte13[3] = 0;
    arrayOfByte13[4] = 0;
    arrayOfByte13[5] = 0;
    AU_AMOUNT = ByteArray.wrap(arrayOfByte13);
    byte[] arrayOfByte14 = new byte[6];
    arrayOfByte14[0] = 0;
    arrayOfByte14[1] = 0;
    arrayOfByte14[2] = 0;
    arrayOfByte14[3] = 0;
    arrayOfByte14[4] = 0;
    arrayOfByte14[5] = 0;
    AU_AMOUNT_OTHER = ByteArray.wrap(arrayOfByte14);
    byte[] arrayOfByte15 = new byte[4];
    arrayOfByte15[0] = 1;
    arrayOfByte15[1] = 2;
    arrayOfByte15[2] = 3;
    arrayOfByte15[3] = 4;
    RANDOM_NUMBER = ByteArray.wrap(arrayOfByte15);
    byte[] arrayOfByte16 = new byte[2];
    arrayOfByte16[0] = 1;
    arrayOfByte16[1] = 86;
    CUR_CODE = ByteArray.wrap(arrayOfByte16);
    byte[] arrayOfByte17 = new byte[5];
    arrayOfByte17[0] = 0;
    arrayOfByte17[1] = 0;
    arrayOfByte17[2] = 0;
    arrayOfByte17[3] = 0;
    arrayOfByte17[4] = 0;
    TVR = ByteArray.wrap(arrayOfByte17);
    byte[] arrayOfByte18 = new byte[1];
    arrayOfByte18[0] = 48;
    TRADE_TYPE = ByteArray.wrap(arrayOfByte18);
    byte[] arrayOfByte19 = new byte[2];
    arrayOfByte19[0] = 106;
    arrayOfByte19[1] = -126;
    NOT_EXISTS = ByteArray.wrap(arrayOfByte19);
  }

  private int getATC(IsoDep paramIsoDep)
    throws IOException
  {
    Object localObject = null;
    GetDataCommand localGetDataCommand = new GetDataCommand();
    localGetDataCommand.setP1(-97);
    localGetDataCommand.setP2(54);
    try
    {
      byte[] arrayOfByte = paramIsoDep.transceive(localGetDataCommand.toRawAPDU().toBytes());
      localObject = arrayOfByte;
      if (localObject == null)
        throw new IOException("failed to get ATC");
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.e("BankCardHandler", "failed to get atc", localIOException);
    }
    return Coder.bytesToInt(localObject, 3, 2);
  }

  private String getCardNumString(byte[] paramArrayOfByte)
  {
    Boolean localBoolean = Boolean.valueOf(false);
    Object localObject = null;
    try
    {
      ByteArray localByteArray = TLVParser.parse(ByteArray.wrap(paramArrayOfByte, 0, -2 + paramArrayOfByte.length)).getValue().findTLV(TAG_CARD_NUM).getValue().toBytes();
      int i = 0;
      while ((0xD0 ^ 0xF0 & localByteArray.get(i)) != 0)
      {
        i++;
        if ((0xD ^ 0xF & localByteArray.get(i)) != 0)
          continue;
        i++;
        localBoolean = Boolean.valueOf(true);
      }
      localObject = ByteArray.wrap(localByteArray.toBytes(), 0, i).toString();
      if (localBoolean.booleanValue())
      {
        String str = ((String)localObject).substring(0, -1 + ((String)localObject).length());
        localObject = str;
      }
      return localObject;
    }
    catch (InvalidTLVException localInvalidTLVException)
    {
      while (true)
        Log.e("BankCardHandler", "Invalid res: " + Coder.bytesToHexString(paramArrayOfByte), localInvalidTLVException);
    }
    catch (TagNotFoundException localTagNotFoundException)
    {
      while (true)
        Log.e("BankCardHandler", "Error when parse tlv", localTagNotFoundException);
    }
  }

  private int getCardType(ByteArray paramByteArray1, ByteArray paramByteArray2)
    throws UnProcessableCardException
  {
    int i = 1;
    if (ByteArray.equals(paramByteArray1.duplicate(0, 5), UNION_PAY_AID))
    {
      this.mCardScheme = i;
      switch (paramByteArray1.get(7))
      {
      case 1:
      case 4:
      case 5:
      default:
      case 2:
      case 3:
      case 6:
      }
    }
    while (true)
    {
      return i;
      i = 2;
      continue;
      i = 3;
      continue;
      i = 4;
      continue;
      if (!ByteArray.equals(paramByteArray1.duplicate(0, 5), VISA_AID))
        break;
      this.mCardScheme = 2;
      if (paramByteArray2.toString().contains(VISA_DEBIT.toString()))
        continue;
      i = 2;
    }
    throw new UnProcessableCardException("BankCardHandler: unsupported card type");
  }

  private float getEBalance(IsoDep paramIsoDep)
    throws IOException
  {
    Object localObject = null;
    GetDataCommand localGetDataCommand = new GetDataCommand();
    localGetDataCommand.setP1(-97);
    localGetDataCommand.setP2(121);
    try
    {
      byte[] arrayOfByte = paramIsoDep.transceive(localGetDataCommand.toRawAPDU().toBytes());
      localObject = arrayOfByte;
      if (localObject == null)
        throw new IOException("failed to get balance");
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.e("BankCardHandler", "failed to get balance", localIOException);
    }
    return getRealMoney(localObject, 3, 5, 8, 1);
  }

  private float getEBanlanceLimit(IsoDep paramIsoDep)
    throws IOException
  {
    Object localObject = null;
    GetDataCommand localGetDataCommand = new GetDataCommand();
    localGetDataCommand.setP1(-97);
    localGetDataCommand.setP2(119);
    try
    {
      byte[] arrayOfByte = paramIsoDep.transceive(localGetDataCommand.toRawAPDU().toBytes());
      localObject = arrayOfByte;
      if (localObject == null)
        throw new IOException("failed to get EBanlanceLimit");
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.e("BankCardHandler", "failed to get balance limit", localIOException);
    }
    return getRealMoney(localObject, 3, 5, 8, 1);
  }

  private float getEPerLimit(IsoDep paramIsoDep)
    throws IOException
  {
    Object localObject = null;
    GetDataCommand localGetDataCommand = new GetDataCommand();
    localGetDataCommand.setP1(-97);
    localGetDataCommand.setP2(120);
    try
    {
      byte[] arrayOfByte = paramIsoDep.transceive(localGetDataCommand.toRawAPDU().toBytes());
      localObject = arrayOfByte;
      if (localObject == null)
        throw new IOException("failed to get EPerLimit");
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.e("BankCardHandler", "failed to get per limit", localIOException);
    }
    return getRealMoney(localObject, 3, 5, 8, 1);
  }

  private byte[] getEntranceOfTrade(byte[] paramArrayOfByte)
  {
    int i = 0;
    byte[] arrayOfByte;
    if (i < -1 + paramArrayOfByte.length)
    {
      if ((paramArrayOfByte[i] == -97) && (paramArrayOfByte[(i + 1)] == 77))
        i += 3;
    }
    else
    {
      if (i != -1 + paramArrayOfByte.length)
        break label69;
      arrayOfByte = new byte[2];
      arrayOfByte[0] = 11;
      arrayOfByte[1] = 10;
    }
    while (true)
    {
      return arrayOfByte;
      i++;
      break;
      label69: ByteBuffer localByteBuffer = ByteBuffer.allocate(2);
      int j = i + 1;
      localByteBuffer.put(paramArrayOfByte[i]).put(paramArrayOfByte[j]);
      arrayOfByte = localByteBuffer.array();
    }
  }

  private ByteArray getGPOInputData(ByteArray paramByteArray)
  {
    int i = 0;
    int j = 0;
    label4: if (i < paramByteArray.length())
      if ((0x1F & paramByteArray.get(i)) != 31);
    int k;
    ByteBuffer localByteBuffer;
    switch (0xFF & paramByteArray.get(i + 1))
    {
    default:
      switch (0xFF & paramByteArray.get(i))
      {
      default:
        k = 0;
        localByteBuffer = ByteBuffer.allocate(j + 2);
        localByteBuffer.put(-125).put(Coder.toBytesLow(j));
      case 149:
      case 156:
      case 154:
      }
    case 102:
    case 2:
    case 3:
    case 26:
    case 55:
    case 33:
    case 42:
    }
    while (true)
    {
      if (k < paramByteArray.length())
        if ((0x1F & paramByteArray.get(k)) != 31);
      switch (0xFF & paramByteArray.get(k + 1))
      {
      default:
        switch (0xFF & paramByteArray.get(k))
        {
        default:
          return ByteArray.wrap(localByteBuffer.array());
          j += 4;
          i += 3;
          break label4;
          j += 6;
          i += 3;
          break label4;
          j += 6;
          i += 3;
          break label4;
          j += 2;
          i += 3;
          break label4;
          j += 4;
          i += 3;
          break label4;
          j += 3;
          i += 3;
          break label4;
          j += 2;
          i += 3;
          break label4;
          j += 5;
          i += 2;
          break label4;
          j++;
          i += 2;
          break label4;
          j += 3;
          i += 2;
        case 149:
        case 156:
        case 154:
        }
        break;
      case 102:
        localByteBuffer.put(TER_TRADE_TYPE.toBytes());
        k += 3;
        break;
      case 2:
        localByteBuffer.put(AU_AMOUNT.toBytes());
        k += 3;
        break;
      case 3:
        localByteBuffer.put(AU_AMOUNT_OTHER.toBytes());
        k += 3;
        break;
      case 26:
        localByteBuffer.put(CUR_CODE.toBytes());
        k += 3;
        break;
      case 55:
        localByteBuffer.put(RANDOM_NUMBER.toBytes());
        k += 3;
        break;
      case 33:
        Date localDate2 = new Date();
        localByteBuffer.put(Coder.str2Bcd(new SimpleDateFormat("HHmmss", Locale.getDefault()).format(localDate2)));
        k += 3;
        break;
      case 42:
        localByteBuffer.put(CUR_CODE.toBytes());
        k += 3;
        continue;
        localByteBuffer.put(TVR.toBytes());
        k += 2;
        continue;
        localByteBuffer.put(TRADE_TYPE.toBytes());
        k += 2;
        continue;
        Date localDate1 = new Date();
        localByteBuffer.put(Coder.str2Bcd(new SimpleDateFormat("yyMMdd", Locale.getDefault()).format(localDate1)));
        k += 2;
      }
    }
  }

  private float getRealMoney(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    byte[] arrayOfByte1 = ByteArray.wrap(paramArrayOfByte).duplicate(paramInt1, paramInt2).toBytes();
    byte[] arrayOfByte2 = ByteArray.wrap(paramArrayOfByte).duplicate(paramInt3, paramInt4).toBytes();
    return Integer.parseInt(Coder.bytesToHexString(arrayOfByte1)) + Float.parseFloat(Coder.bytesToHexString(arrayOfByte2)) / 100.0F;
  }

  private void getTradeLog(IsoDep paramIsoDep, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, ArrayList<TradeLog> paramArrayList)
    throws IOException
  {
    byte b = Coder.toBytesLow(4 + (paramArrayOfByte1[0] << 3));
    ReadRecordCommand localReadRecordCommand = new ReadRecordCommand();
    localReadRecordCommand.setP2(b);
    for (int i = 0; ; i++)
    {
      byte[] arrayOfByte1 = new byte[1];
      arrayOfByte1[0] = paramArrayOfByte1[1];
      Object localObject;
      if (i < Coder.bytesToInt(arrayOfByte1))
      {
        localReadRecordCommand.setP1(Coder.toBytesLow(i + 1));
        localObject = null;
        try
        {
          byte[] arrayOfByte2 = paramIsoDep.transceive(localReadRecordCommand.toRawAPDU().toBytes());
          localObject = arrayOfByte2;
          if (localObject == null)
            throw new IOException("failed to get per trade log");
        }
        catch (IOException localIOException)
        {
          while (true)
            Log.e("BankCardHandler", "failed to get per log", localIOException);
        }
        if (localObject[0] != 106);
      }
      else
      {
        return;
      }
      paramArrayList.add(new TradeLog());
      translateLog(localObject, paramArrayOfByte2, (TradeLog)paramArrayList.get(i));
    }
  }

  private byte[] getTradeLogFormat(IsoDep paramIsoDep)
    throws IOException
  {
    Object localObject = null;
    GetDataCommand localGetDataCommand = new GetDataCommand();
    localGetDataCommand.setP1(-97);
    localGetDataCommand.setP2(79);
    try
    {
      byte[] arrayOfByte = paramIsoDep.transceive(localGetDataCommand.toRawAPDU().toBytes());
      localObject = arrayOfByte;
      if (localObject == null)
        throw new IOException("failed to get TradeLogFormat");
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.e("BankCardHandler", "failed to get trade log format", localIOException);
    }
    return localObject;
  }

  private void translateLog(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, TradeLog paramTradeLog)
  {
    int i = 0;
    int j = 3;
    Object localObject = null;
    while (j < paramArrayOfByte1.length)
    {
      if (paramArrayOfByte2[j] == -102)
      {
        j += 2;
        StringBuilder localStringBuilder4 = new StringBuilder().append("20");
        int i2 = i + 1;
        StringBuilder localStringBuilder5 = localStringBuilder4.append(Coder.bytesToHexString(paramArrayOfByte1[i]));
        int i3 = i2 + 1;
        StringBuilder localStringBuilder6 = localStringBuilder5.append(Coder.bytesToHexString(paramArrayOfByte1[i2]));
        int i4 = i3 + 1;
        paramTradeLog.setTradeDate(Coder.bytesToHexString(paramArrayOfByte1[i3]));
        i = i4;
        continue;
      }
      if (paramArrayOfByte2[j] == -97)
      {
        if (paramArrayOfByte2[(j + 1)] == 33)
        {
          j += 3;
          StringBuilder localStringBuilder1 = new StringBuilder();
          int m = i + 1;
          StringBuilder localStringBuilder2 = localStringBuilder1.append(Coder.bytesToHexString(paramArrayOfByte1[i]));
          int n = m + 1;
          StringBuilder localStringBuilder3 = localStringBuilder2.append(Coder.bytesToHexString(paramArrayOfByte1[m]));
          int i1 = n + 1;
          paramTradeLog.setTradeTime(Coder.bytesToHexString(paramArrayOfByte1[n]));
          i = i1;
          continue;
        }
        if (paramArrayOfByte2[(j + 1)] == 2)
        {
          j += 3;
          paramTradeLog.setAuAmount(getRealMoney(paramArrayOfByte1, i, 5, i + 5, 1));
          i += 6;
          continue;
        }
        if (paramArrayOfByte2[(j + 1)] == 26)
        {
          j += 3;
          paramTradeLog.setCountryCode(" ");
          i += 2;
          continue;
        }
        if (paramArrayOfByte2[(j + 1)] == 78)
        {
          j += 3;
          if (!ByteArray.wrap(paramArrayOfByte1).duplicate(i, 20).toString().matches("0*"));
          while (true)
          {
            try
            {
              String str = new String(ByteArray.wrap(paramArrayOfByte1).duplicate(i, 20).toBytes(), "GBK");
              localObject = str;
              paramTradeLog.setBusinessName(localObject);
              i += 20;
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException)
            {
              Log.e("BankCardHandler", "failed to get shop name", localUnsupportedEncodingException);
              continue;
            }
            paramTradeLog.setBusinessName(null);
          }
        }
        if (paramArrayOfByte2[(j + 1)] == 54)
        {
          j += 3;
          i += 2;
          continue;
        }
        if (paramArrayOfByte2[(j + 1)] == 39)
        {
          j += 3;
          i++;
          continue;
        }
      }
      if ((paramArrayOfByte2[j] == 95) && (paramArrayOfByte2[(j + 1)] == 42))
      {
        j += 3;
        ByteArray localByteArray = ByteArray.wrap(paramArrayOfByte1).duplicate(i, 2);
        if (CardConstants.sCurrencyCodeMap.containsKey(localByteArray.toString()))
          paramTradeLog.setCurCode(((Integer)CardConstants.sCurrencyCodeMap.get(localByteArray.toString())).intValue());
        i += 2;
        continue;
      }
      if (paramArrayOfByte2[j] != -100)
        break;
      j += 2;
      int k;
      switch (paramArrayOfByte1[i])
      {
      default:
        k = 4;
      case 0:
      case 1:
      case 33:
      case 48:
      case 96:
      case 99:
      }
      while (true)
      {
        paramTradeLog.setTradeType(k);
        i++;
        break;
        k = 1;
        continue;
        k = 6;
        continue;
        k = 3;
        continue;
        k = 4;
        continue;
        k = 5;
        continue;
        k = 2;
      }
    }
  }

  public int getTechType()
  {
    return 1;
  }

  public Bundle onHandleCard(IsoDep paramIsoDep)
    throws IOException, UnProcessableCardException
  {
    ArrayList localArrayList = new ArrayList();
    Bundle localBundle = new Bundle();
    byte[] arrayOfByte1 = new byte[5];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = -78;
    arrayOfByte1[2] = 1;
    arrayOfByte1[3] = 12;
    arrayOfByte1[4] = 0;
    paramIsoDep.transceive(ByteArray.wrap(arrayOfByte1).toBytes());
    SelectCommand localSelectCommand = new SelectCommand();
    localSelectCommand.setP1(4);
    localSelectCommand.setData(AID_PPSE);
    byte[] arrayOfByte2 = paramIsoDep.transceive(localSelectCommand.toRawAPDU().toBytes());
    if ((arrayOfByte2 == null) || (arrayOfByte2.length < 2))
      throw new IOException("failed to select PPSE");
    if (ByteArray.equals(NOT_EXISTS, ByteArray.wrap(arrayOfByte2)))
      throw new UnProcessableCardException("BankCardHandler: unsupported card type");
    ByteArray localByteArray1 = null;
    Object localObject1 = null;
    try
    {
      ITLVObject localITLVObject = TLVParser.parse(ByteArray.wrap(arrayOfByte2, 0, -2 + arrayOfByte2.length)).getValue().findTLV(TAG_FCI_DATA_TEMPLATE).getValue().findTLV(TAG_BANK_CUSTOM_DATA).getValue().findTLV(TAG_AEF_ENTRANCE);
      localByteArray1 = localITLVObject.getValue().findTLV(TAG_AID).getValue().toBytes();
      ByteArray localByteArray4 = localITLVObject.getValue().findTLV(TAG_APP).getValue().toBytes();
      localObject1 = localByteArray4;
      if ((localByteArray1 == null) || (localObject1 == null))
        throw new IOException("failed to get aid or appBytes");
    }
    catch (InvalidTLVException localInvalidTLVException1)
    {
      while (true)
        Log.e("BankCardHandler", "invalid res: " + Coder.bytesToHexString(arrayOfByte2));
    }
    catch (TagNotFoundException localTagNotFoundException1)
    {
      Log.e("BankCardHandler", "error when parse tlv", localTagNotFoundException1);
      throw new UnProcessableCardException("BankCardHandler: unsupported card type");
    }
    int i = getCardType(localByteArray1, localObject1);
    localSelectCommand.setData(localByteArray1);
    byte[] arrayOfByte3 = paramIsoDep.transceive(localSelectCommand.toRawAPDU().toBytes());
    if ((arrayOfByte3 == null) || (arrayOfByte3.length < 2))
      throw new IOException("failed to select qPBOC");
    byte[] arrayOfByte4 = getEntranceOfTrade(arrayOfByte3);
    Object localObject2 = null;
    try
    {
      ByteArray localByteArray3 = TLVParser.parse(ByteArray.wrap(arrayOfByte3, 0, -2 + arrayOfByte3.length)).getValue().findTLV(TAG_FCI_DATA_TEMPLATE).getValue().findTLV(TAG_PDOL).getValue().toBytes();
      localObject2 = localByteArray3;
      if (localObject2 == null)
        throw new IOException("failed to get pdol");
    }
    catch (InvalidTLVException localInvalidTLVException2)
    {
      while (true)
        Log.e("BankCardHandler", "Invalid res: " + Coder.bytesToHexString(arrayOfByte3));
    }
    catch (TagNotFoundException localTagNotFoundException2)
    {
      while (true)
        Log.e("BankCardHandler", "Error when parse tlv", localTagNotFoundException2);
      ByteArray localByteArray2 = getGPOInputData(localObject2);
      GPOCommand localGPOCommand = new GPOCommand();
      localGPOCommand.setData(localByteArray2);
      byte[] arrayOfByte5 = paramIsoDep.transceive(localGPOCommand.toRawAPDU().toBytes());
      if ((arrayOfByte5 == null) || (arrayOfByte5.length < 2))
        throw new IOException("failed to get AFL");
      ReadRecordCommand localReadRecordCommand = new ReadRecordCommand();
      localReadRecordCommand.setP1(1);
      localReadRecordCommand.setP2(12);
      byte[] arrayOfByte6 = paramIsoDep.transceive(localReadRecordCommand.toRawAPDU().toBytes());
      if ((arrayOfByte6 == null) || (arrayOfByte6.length < 2))
        throw new IOException("failed to read card number");
      getTradeLog(paramIsoDep, arrayOfByte4, getTradeLogFormat(paramIsoDep), localArrayList);
      String str = getCardNumString(arrayOfByte6);
      if (str == null)
        throw new IOException("failed to get cardNum string");
      int j = getATC(paramIsoDep);
      float f1 = getEBalance(paramIsoDep);
      float f2 = getEPerLimit(paramIsoDep);
      float f3 = getEBanlanceLimit(paramIsoDep);
      localBundle.putBoolean("success", true);
      localBundle.putInt("card_type", 1);
      localBundle.putInt("card_scheme", this.mCardScheme);
      localBundle.putInt("bank_card_type", i);
      localBundle.putString("account_num", str);
      localBundle.putInt("atc", j);
      localBundle.putFloat("e_balance", f1);
      localBundle.putFloat("per_limit", f2);
      localBundle.putFloat("e_balance_limit", f3);
      localBundle.putParcelableArrayList("trade_log", localArrayList);
    }
    return localBundle;
  }

  public Bundle onHandleCard(AbstractTerminal paramAbstractTerminal)
    throws IOException, UnProcessableCardException
  {
    Bundle localBundle = new Bundle();
    ArrayList localArrayList = new ArrayList();
    try
    {
      if (!ByteArray.equals(paramAbstractTerminal.transmit(APDUConstants.SELECT_CRS).getStatus(), ScResponse.STATUS_OK))
        throw new IOException("failed to select CRS");
    }
    catch (InvalidTLVException localInvalidTLVException)
    {
      Log.e("BankCardHandler", "onHandleCard return error when parse tlv", localInvalidTLVException);
      localBundle.putStringArrayList("extras_key_pan_list", localArrayList);
      CommandApdu localCommandApdu;
      ScResponse localScResponse;
      while (true)
      {
        return localBundle;
        byte[] arrayOfByte1 = APDUConstants.PBOC_CARD_AID_PREFFIX;
        byte[] arrayOfByte2 = new byte[5 + (2 + arrayOfByte1.length)];
        arrayOfByte2[0] = 79;
        arrayOfByte2[1] = (byte)(0xFF & arrayOfByte1.length);
        System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 2, arrayOfByte1.length);
        System.arraycopy(APDUConstants.COMM_TAG_GET_STATUS.toBytes(), 0, arrayOfByte2, 2 + arrayOfByte1.length, APDUConstants.COMM_TAG_GET_STATUS.toBytes().length);
        localCommandApdu = APDUConstants.COMM_PREFIX_GET_STATUS.clone();
        localCommandApdu.setData(arrayOfByte2);
        localScResponse = paramAbstractTerminal.transmit(localCommandApdu.toBytes());
        if (!ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_REFERENCE_NOT_FOUND))
          break;
        localBundle = null;
      }
      while ((ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_MORE_DATA_AVAILABLE)) || (ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OK)))
      {
        if (localCommandApdu.getP2() != 1)
          localCommandApdu.setP2(1);
        List localList = TLVParser.parseTLVValue(localScResponse.getData()).findTLVList(TAG_AEF_ENTRANCE);
        SelectCommand localSelectCommand = new SelectCommand();
        localSelectCommand.setP1(4);
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          ByteArray localByteArray = ((ITLVObject)localIterator.next()).getValue().findTLV(TAG_AID).getValue().toBytes();
          localSelectCommand.setData(localByteArray);
          localScResponse = paramAbstractTerminal.transmit(localSelectCommand.toRawAPDU().toBytes());
          if (!ByteArray.equals(localScResponse.getStatus(), ScResponse.STATUS_OK))
            continue;
          ReadRecordCommand localReadRecordCommand = new ReadRecordCommand();
          localReadRecordCommand.setP1(1);
          localReadRecordCommand.setP2(12);
          localScResponse = paramAbstractTerminal.transmit(localReadRecordCommand.toRawAPDU().toBytes());
          String str = getCardNumString(localScResponse.toBytes());
          if (TextUtils.isEmpty(str))
            continue;
          localArrayList.add(localByteArray.toString() + "&" + str);
        }
      }
    }
    catch (TagNotFoundException localTagNotFoundException)
    {
      while (true)
        Log.e("BankCardHandler", "onHandleCard return error when parse tlv", localTagNotFoundException);
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.BankCardHandler
 * JD-Core Version:    0.6.0
 */