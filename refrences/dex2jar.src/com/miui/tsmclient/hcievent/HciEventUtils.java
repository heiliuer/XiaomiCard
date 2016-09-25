package com.miui.tsmclient.hcievent;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.terminal.APDUConstants;

public class HciEventUtils
{
  public static IHciEventHandler getHciEventHandler(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    if (paramArrayOfByte == null);
    while (true)
    {
      return localObject;
      String str = Coder.bytesToHexString(paramArrayOfByte);
      ByteArray localByteArray = ByteArray.wrap(paramArrayOfByte);
      if (str.startsWith(Coder.bytesToHexString(APDUConstants.PBOC_CARD_AID_PREFFIX)))
      {
        localObject = new BankHciEventHandler();
        continue;
      }
      if (ByteArray.equals(localByteArray, APDUConstants.AID_SPTC))
      {
        localObject = new ShHciEventHandler();
        continue;
      }
      if (ByteArray.equals(localByteArray, APDUConstants.AID_SZT))
      {
        localObject = new SZTHciHandler();
        continue;
      }
      if (!CityUCardHciHandler.isSupport(paramArrayOfByte))
        continue;
      localObject = new CityUCardHciHandler();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.hcievent.HciEventUtils
 * JD-Core Version:    0.6.0
 */