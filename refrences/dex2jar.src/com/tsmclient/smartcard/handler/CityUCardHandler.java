package com.tsmclient.smartcard.handler;

import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.text.TextUtils;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.ReaderUtil;
import com.tsmclient.smartcard.apdu.ReadBinaryCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CityUCardHandler extends BaseTransCardHandler
{
  private static final ByteArray CHONG_QING;
  private static final ByteArray CITYU_AID;
  private static final ByteArray DONG_GUAN;
  private static final ByteArray GUI_YANG;
  private static final ByteArray HA_ER_BIN;
  private static final ByteArray KUN_MING;
  private static final ByteArray LAN_ZHOU;
  private static final ByteArray NAN_CHANG;
  private static final ByteArray NING_BO;
  private static final ByteArray QING_DAO;
  private static final ByteArray SHANG_HAI;
  private static final String TAG = "CityUCardHandler";
  private static final ByteArray XI_AN;
  private static final ByteArray ZHENG_ZHOU;
  private static final ByteArray ZHOU_SHAN;
  private String mCardId;

  static
  {
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = 32;
    arrayOfByte1[1] = 0;
    SHANG_HAI = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[2];
    arrayOfByte2[0] = 82;
    arrayOfByte2[1] = 48;
    DONG_GUAN = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[2];
    arrayOfByte3[0] = 64;
    arrayOfByte3[1] = 0;
    CHONG_QING = ByteArray.wrap(arrayOfByte3);
    byte[] arrayOfByte4 = new byte[2];
    arrayOfByte4[0] = 115;
    arrayOfByte4[1] = 0;
    LAN_ZHOU = ByteArray.wrap(arrayOfByte4);
    byte[] arrayOfByte5 = new byte[2];
    arrayOfByte5[0] = 69;
    arrayOfByte5[1] = 0;
    ZHENG_ZHOU = ByteArray.wrap(arrayOfByte5);
    byte[] arrayOfByte6 = new byte[2];
    arrayOfByte6[0] = 85;
    arrayOfByte6[1] = 0;
    GUI_YANG = ByteArray.wrap(arrayOfByte6);
    byte[] arrayOfByte7 = new byte[2];
    arrayOfByte7[0] = 113;
    arrayOfByte7[1] = 0;
    XI_AN = ByteArray.wrap(arrayOfByte7);
    byte[] arrayOfByte8 = new byte[2];
    arrayOfByte8[0] = 101;
    arrayOfByte8[1] = 0;
    KUN_MING = ByteArray.wrap(arrayOfByte8);
    byte[] arrayOfByte9 = new byte[2];
    arrayOfByte9[0] = 21;
    arrayOfByte9[1] = 0;
    HA_ER_BIN = ByteArray.wrap(arrayOfByte9);
    byte[] arrayOfByte10 = new byte[2];
    arrayOfByte10[0] = 38;
    arrayOfByte10[1] = 96;
    QING_DAO = ByteArray.wrap(arrayOfByte10);
    byte[] arrayOfByte11 = new byte[2];
    arrayOfByte11[0] = 51;
    arrayOfByte11[1] = 0;
    NAN_CHANG = ByteArray.wrap(arrayOfByte11);
    byte[] arrayOfByte12 = new byte[2];
    arrayOfByte12[0] = 49;
    arrayOfByte12[1] = 80;
    NING_BO = ByteArray.wrap(arrayOfByte12);
    byte[] arrayOfByte13 = new byte[2];
    arrayOfByte13[0] = 49;
    arrayOfByte13[1] = 96;
    ZHOU_SHAN = ByteArray.wrap(arrayOfByte13);
    byte[] arrayOfByte14 = new byte[9];
    arrayOfByte14[0] = -96;
    arrayOfByte14[1] = 0;
    arrayOfByte14[2] = 0;
    arrayOfByte14[3] = 0;
    arrayOfByte14[4] = 3;
    arrayOfByte14[5] = -122;
    arrayOfByte14[6] = -104;
    arrayOfByte14[7] = 7;
    arrayOfByte14[8] = 1;
    CITYU_AID = ByteArray.wrap(arrayOfByte14);
  }

  private String getDateString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return Coder.bytesToHexString(ByteArray.wrap(paramArrayOfByte, paramInt1, paramInt2).toBytes());
  }

  private String hexInvertAndToInt(byte[] paramArrayOfByte)
  {
    String str = ReaderUtil.invertString(Coder.bytesToHexString(paramArrayOfByte), 2);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(Coder.bytesToInt(Coder.str2Bcd(str)));
    return String.format("%010d", arrayOfObject);
  }

  private String hexToInt(ByteArray paramByteArray, int paramInt)
  {
    String str = "%0" + paramInt + "d";
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(Coder.bytesToInt(paramByteArray.toBytes()));
    return String.format(str, arrayOfObject);
  }

  private String hexToIntAndInvert(ByteArray paramByteArray)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(Coder.bytesToInt(paramByteArray.toBytes()));
    String str1 = String.format("%010d", arrayOfObject);
    if (Integer.parseInt(str1) < 0);
    for (String str2 = null; ; str2 = ReaderUtil.invertString(str1, 2))
      return str2;
  }

  private String readCQCardNum()
    throws IOException, UnProcessableCardException
  {
    SelectCommand localSelectCommand = new SelectCommand();
    localSelectCommand.setP1(4);
    localSelectCommand.setData(AID_PSE);
    assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select CQCard AID");
    ReadBinaryCommand localReadBinaryCommand = new ReadBinaryCommand();
    localReadBinaryCommand.setP1(-123);
    byte[] arrayOfByte = transceive(localReadBinaryCommand.toRawAPDU().toBytes());
    assertResponse(arrayOfByte, "failed to get card num");
    return Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 12, 8).toBytes());
  }

  protected Bundle doHandleCard()
    throws IOException, UnProcessableCardException
  {
    ArrayList localArrayList = new ArrayList();
    Bundle localBundle = new Bundle();
    selectVerify();
    Map localMap = getCardNumAndValidDate();
    if (localMap == null)
      throw new UnProcessableCardException("CityUCardHandler: unsupported card type");
    otherVerify();
    int i = getBalance();
    if (TextUtils.equals(this.mCardId, "014"))
      readRecord(localArrayList, true);
    while (true)
    {
      if (TextUtils.equals(this.mCardId, "003"))
        localMap.put("account_num", readCQCardNum());
      localBundle.putBoolean("success", true);
      localBundle.putInt("card_type", 2);
      localBundle.putString("card_id", getCardType());
      localBundle.putString("account_num", (String)localMap.get("account_num"));
      localBundle.putString("valid_start", (String)localMap.get("valid_start"));
      localBundle.putString("valid_end", (String)localMap.get("valid_end"));
      localBundle.putInt("e_balance", i);
      localBundle.putParcelableArrayList("trade_log", localArrayList);
      return localBundle;
      readRecord(localArrayList, false);
    }
  }

  protected Map<String, String> getCardNumAndValidDate()
    throws IOException, UnProcessableCardException
  {
    String str1 = null;
    String str2 = null;
    String str3 = null;
    HashMap localHashMap = new HashMap();
    ReadBinaryCommand localReadBinaryCommand = new ReadBinaryCommand();
    localReadBinaryCommand.setP1(-107);
    byte[] arrayOfByte = transceive(localReadBinaryCommand.toRawAPDU().toBytes());
    assertResponse(arrayOfByte, "failed to get card num");
    ByteArray localByteArray = ByteArray.wrap(arrayOfByte, 2, 2);
    if (ByteArray.equals(localByteArray, SHANG_HAI))
    {
      this.mCardId = "002";
      str1 = getCardNumByLuhm(ByteArray.wrap(arrayOfByte, 16, 4));
      str2 = getDateString(arrayOfByte, 20, 4);
      str3 = getDateString(arrayOfByte, 24, 4);
    }
    while (true)
    {
      localHashMap.put("account_num", str1);
      localHashMap.put("valid_start", str2);
      localHashMap.put("valid_end", str3);
      return localHashMap;
      if (ByteArray.equals(localByteArray, CHONG_QING))
      {
        this.mCardId = "003";
        str2 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 4, 4).toBytes());
        str3 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 8, 4).toBytes());
        continue;
      }
      if ((ByteArray.equals(localByteArray, LAN_ZHOU)) || (ByteArray.equals(localByteArray, GUI_YANG)) || (ByteArray.equals(localByteArray, NING_BO)))
      {
        if (ByteArray.equals(localByteArray, LAN_ZHOU))
          this.mCardId = "006";
        while (true)
        {
          str1 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 12, 8).toBytes());
          str2 = getDateString(arrayOfByte, 20, 4);
          str3 = getDateString(arrayOfByte, 24, 4);
          break;
          if (ByteArray.equals(localByteArray, GUI_YANG))
          {
            this.mCardId = "009";
            continue;
          }
          if (!ByteArray.equals(localByteArray, NING_BO))
            continue;
          this.mCardId = "016";
        }
      }
      if (ByteArray.equals(localByteArray, ZHENG_ZHOU))
      {
        this.mCardId = "007";
        str1 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 14, 6).toBytes());
        str2 = getDateString(arrayOfByte, 20, 4);
        str3 = getDateString(arrayOfByte, 24, 4);
        continue;
      }
      if ((ByteArray.equals(localByteArray, XI_AN)) || (ByteArray.equals(localByteArray, HA_ER_BIN)) || (ByteArray.equals(localByteArray, QING_DAO)))
      {
        if (ByteArray.equals(localByteArray, XI_AN))
          this.mCardId = "010";
        while (true)
        {
          str1 = hexToInt(ByteArray.wrap(arrayOfByte, 16, 4), 8);
          str2 = getDateString(arrayOfByte, 20, 4);
          str3 = getDateString(arrayOfByte, 24, 4);
          break;
          if (ByteArray.equals(localByteArray, HA_ER_BIN))
          {
            this.mCardId = "012";
            continue;
          }
          if (!ByteArray.equals(localByteArray, QING_DAO))
            continue;
          this.mCardId = "014";
        }
      }
      if (ByteArray.equals(localByteArray, KUN_MING))
      {
        this.mCardId = "011";
        str1 = hexInvertAndToInt(ByteArray.wrap(arrayOfByte, 16, 4).toBytes());
        str2 = getDateString(arrayOfByte, 20, 4);
        str3 = getDateString(arrayOfByte, 24, 4);
        continue;
      }
      if (ByteArray.equals(localByteArray, NAN_CHANG))
      {
        this.mCardId = "015";
        str1 = hexToInt(ByteArray.wrap(arrayOfByte, 16, 4), 8);
        continue;
      }
      if (ByteArray.equals(localByteArray, ZHOU_SHAN))
      {
        this.mCardId = "018";
        str1 = Coder.bytesToHexString(ByteArray.wrap(arrayOfByte, 10, 10).toBytes());
        str2 = getDateString(arrayOfByte, 20, 4);
        str3 = getDateString(arrayOfByte, 24, 4);
        continue;
      }
      this.mCardId = "017";
    }
  }

  public String getCardNumByLuhm(ByteArray paramByteArray)
  {
    String str1 = hexToIntAndInvert(paramByteArray);
    if (TextUtils.isEmpty(str1));
    int m;
    for (String str2 = null; ; str2 = m + str1)
    {
      return str2;
      char[] arrayOfChar = str1.toCharArray();
      int i = 0;
      int j = -1 + arrayOfChar.length;
      for (int k = 0; j >= 0; k++)
      {
        char c = arrayOfChar[j];
        int n = Integer.parseInt(c + "");
        if (k % 2 == 0)
        {
          n *= 2;
          if (n > 9)
            n -= 9;
        }
        i += n;
        j--;
      }
      m = (10 - i % 10) % 10;
    }
  }

  protected String getCardType()
  {
    return this.mCardId;
  }

  protected ByteArray getConsumeTag()
  {
    byte[] arrayOfByte;
    if (TextUtils.equals(this.mCardId, "010"))
    {
      arrayOfByte = new byte[2];
      arrayOfByte[0] = 6;
      arrayOfByte[1] = 9;
    }
    for (ByteArray localByteArray = ByteArray.wrap(arrayOfByte); ; localByteArray = super.getConsumeTag())
      return localByteArray;
  }

  public Bundle onHandleCard(IsoDep paramIsoDep)
    throws IOException, UnProcessableCardException
  {
    return super.onHandleCard(paramIsoDep);
  }

  protected void selectVerify()
    throws IOException, UnProcessableCardException
  {
    SelectCommand localSelectCommand = new SelectCommand();
    localSelectCommand.setP1(4);
    localSelectCommand.setData(CITYU_AID);
    assertResponse(transceive(localSelectCommand.toRawAPDU().toBytes()), "failed to select CityU AID");
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.tsmclient.smartcard.handler.CityUCardHandler
 * JD-Core Version:    0.6.0
 */