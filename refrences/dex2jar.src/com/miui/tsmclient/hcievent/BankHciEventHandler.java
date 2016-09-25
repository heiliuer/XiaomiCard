package com.miui.tsmclient.hcievent;

import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.exception.InvalidTLVException;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import com.tsmclient.smartcard.tlv.ITLVObject;
import com.tsmclient.smartcard.tlv.ITLVValue;
import com.tsmclient.smartcard.tlv.TLVParser;

public class BankHciEventHandler
  implements IHciEventHandler
{
  private ByteArray TAG_AMOUNT;
  private ByteArray TAG_CURRENCY;
  private ByteArray TAG_TIME;
  private ByteArray TAG_TRANSACTION_EVENT;

  public BankHciEventHandler()
  {
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = -30;
    arrayOfByte1[1] = 1;
    this.TAG_TRANSACTION_EVENT = ByteArray.wrap(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[2];
    arrayOfByte2[0] = -97;
    arrayOfByte2[1] = 2;
    this.TAG_AMOUNT = ByteArray.wrap(arrayOfByte2);
    byte[] arrayOfByte3 = new byte[2];
    arrayOfByte3[0] = -102;
    arrayOfByte3[1] = 3;
    this.TAG_TIME = ByteArray.wrap(arrayOfByte3);
    byte[] arrayOfByte4 = new byte[2];
    arrayOfByte4[0] = 95;
    arrayOfByte4[1] = 42;
    this.TAG_CURRENCY = ByteArray.wrap(arrayOfByte4);
  }

  public HciEventInfo handleData(byte[] paramArrayOfByte1, long paramLong, byte[] paramArrayOfByte2)
  {
    HciEventInfo localHciEventInfo = null;
    if ((paramArrayOfByte2 == null) || (paramArrayOfByte2.length == 0));
    while (true)
    {
      return localHciEventInfo;
      if (!ByteArray.equals(this.TAG_TRANSACTION_EVENT, ByteArray.wrap(paramArrayOfByte2, 0, 2)))
        continue;
      if (paramArrayOfByte2.length >= 36);
      try
      {
        ITLVObject localITLVObject = TLVParser.parse(ByteArray.wrap(paramArrayOfByte2, paramArrayOfByte2.length - 36, 36)).getValue().findTLV(this.TAG_AMOUNT);
        localHciEventInfo = new HciEventInfo(Coder.bytesToHexString(paramArrayOfByte1), paramLong, Integer.valueOf(Coder.bytesToHexString(localITLVObject.getValue().toBytes().toBytes())).intValue(), true);
      }
      catch (InvalidTLVException localInvalidTLVException)
      {
        LogUtils.e("failed to handle bank hci event data", localInvalidTLVException);
        localHciEventInfo = new HciEventInfo(Coder.bytesToHexString(paramArrayOfByte1), paramLong, true);
      }
      catch (TagNotFoundException localTagNotFoundException)
      {
        while (true)
          LogUtils.e("failed to handle bank hci event data", localTagNotFoundException);
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.hcievent.BankHciEventHandler
 * JD-Core Version:    0.6.0
 */