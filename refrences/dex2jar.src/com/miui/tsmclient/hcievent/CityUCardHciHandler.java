package com.miui.tsmclient.hcievent;

import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.terminal.APDUConstants;

public class CityUCardHciHandler extends AbsTransportationEventHandler
{
  private static final int LENGTH_HCI_EVENT_DATA = 23;

  public static boolean isSupport(byte[] paramArrayOfByte)
  {
    ByteArray localByteArray = ByteArray.wrap(paramArrayOfByte);
    if ((ByteArray.equals(localByteArray, APDUConstants.AID_HZT)) || (ByteArray.equals(localByteArray, APDUConstants.AID_LNT)) || (ByteArray.equals(localByteArray, APDUConstants.AID_SUZHOUTONG)) || (ByteArray.equals(localByteArray, APDUConstants.AID_WHT)));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected HciEventInfo doHandleData(String paramString, long paramLong, ByteArray paramByteArray)
  {
    int i = paramByteArray.length();
    ByteArray localByteArray1 = ByteArray.EMPTY;
    ByteArray localByteArray2 = ByteArray.EMPTY;
    int j = 0;
    if (23 <= i)
    {
      localByteArray1 = paramByteArray.duplicate(1, 4);
      localByteArray2 = paramByteArray.duplicate(19, 4);
      j = 1;
    }
    if (j != 0);
    for (int k = Coder.bytesToInt(localByteArray2.toBytes()); ; k = 0)
      return new HciEventInfo(paramString, paramLong, Coder.bytesToInt(localByteArray1.toBytes()), k, false);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     com.miui.tsmclient.hcievent.CityUCardHciHandler
 * JD-Core Version:    0.6.0
 */